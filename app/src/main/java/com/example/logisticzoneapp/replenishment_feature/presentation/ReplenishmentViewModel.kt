package com.example.logisticzoneapp.replenishment_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logisticzoneapp.core.data.api.MovexApiService
import com.example.logisticzoneapp.core.domain.zebra.BarcodeScanState
import com.example.logisticzoneapp.core.domain.zebra.BarcodeScannerHandlerImpl
import com.example.logisticzoneapp.replenishment_feature.domain.model.ReplenishmentListAffectationDTO
import com.example.logisticzoneapp.replenishment_feature.domain.model.ReplenishmentListRemainingQuantityToAffectWithLastReelDTO
import com.example.logisticzoneapp.replenishment_feature.domain.model.ReplenishmentListStateType
import com.example.logisticzoneapp.replenishment_feature.domain.use_case.CheckIfReplenishmentListIsCompleteUseCase
import com.example.logisticzoneapp.replenishment_feature.presentation.state.ReplenishmentListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReplenishmentViewModel @Inject constructor(
    private val checkIfReplenishmentListIsCompleteUseCase: CheckIfReplenishmentListIsCompleteUseCase,
    private val movexApiService: MovexApiService,
    private val barcodeScannerHandlerImpl: BarcodeScannerHandlerImpl,
) : ViewModel() {

    var replenishmentListState by mutableStateOf(ReplenishmentListState())
        private set

    fun handleBarcodeScan(){

    }

    private fun getReplenishmentList(){
        replenishmentListState = replenishmentListState.copy(isLoading = true)
        viewModelScope.launch {
            replenishmentListState = try {
                val response = movexApiService.getReplenishment(replenishmentListState.code)
                if(response.isSuccessful){
                    replenishmentListState.copy(remainingReelsToScan = response.body()!!)
                } else {
                    replenishmentListState.copy(errorMessage = response.errorBody()!!.string())
                }

            } catch (e: Exception){
                replenishmentListState.copy(errorMessage = "Erreur")
            } finally {
                replenishmentListState = replenishmentListState.copy(isLoading = false)
            }
        }
    }

    private fun affectReelToReplenishmentList(){
        replenishmentListState = replenishmentListState.copy(isLoading = true)
        viewModelScope.launch {
            replenishmentListState = try {
                val response = movexApiService.affectReelToReplenishmentList(
                    ReplenishmentListAffectationDTO(replenishmentListState.code, replenishmentListState.code)
                )
                if(response.isSuccessful){
                    val data: ReplenishmentListRemainingQuantityToAffectWithLastReelDTO = response.body()!!
                    replenishmentListState.listOfAffectedReels.add(data.reel)
                    replenishmentListState.copy(remainingReelsToScan = data.remainingReelsQuantityToAffect)
                } else {
                    replenishmentListState.copy(errorMessage = response.errorBody()!!.string())
                }

            } catch (e: Exception){
                replenishmentListState.copy(errorMessage = "Erreur")
            } finally {
                replenishmentListState = replenishmentListState.copy(isLoading = false)
            }
        }
    }

    private fun checkIfReplenishmentListIsComplete(){
        val isCompleteResult = checkIfReplenishmentListIsCompleteUseCase(
            replenishmentListState.remainingReelsToScan
        )
        processReplenishmentListStateType(isCompleteResult)
    }

    // TODO : c'est nul à refaire
    private fun processReplenishmentListStateType(type: ReplenishmentListStateType){
        replenishmentListState = when(type){
            ReplenishmentListStateType.Completed -> {
                replenishmentListState.copy(isComplete = true)
            }
            ReplenishmentListStateType.CantBeCompleted -> {
                replenishmentListState.copy(errorMessage = "Liste ne peut être complétée")
            }
            ReplenishmentListStateType.Uncompleted -> {
                replenishmentListState.copy(errorMessage = "Liste incomplète")
            }
            ReplenishmentListStateType.Unknown -> {
                replenishmentListState.copy(errorMessage = "Liste inconnue")
            }
        }
    }
}