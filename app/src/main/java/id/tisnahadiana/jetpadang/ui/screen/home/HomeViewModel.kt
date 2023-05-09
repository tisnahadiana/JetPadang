package id.tisnahadiana.jetpadang.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tisnahadiana.jetpadang.data.OrderRepository
import id.tisnahadiana.jetpadang.model.OrderBill
import id.tisnahadiana.jetpadang.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: OrderRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderBill>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderBill>>>
        get() = _uiState

    fun getAllOrders() {
        viewModelScope.launch {
            repository.getAllOrders()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}