package id.tisnahadiana.jetpadang.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tisnahadiana.jetpadang.data.OrderRepository
import id.tisnahadiana.jetpadang.model.Order
import id.tisnahadiana.jetpadang.model.OrderBill
import id.tisnahadiana.jetpadang.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: OrderRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderBill>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderBill>>
        get() = _uiState

    fun getOrderById(orderId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderBillById(orderId))
        }
    }

    fun addToOrder(order: Order, count: Int) {
        viewModelScope.launch {
            repository.updateOrderBill(order.id, count)
        }
    }
}