package id.tisnahadiana.jetpadang.ui.screen.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tisnahadiana.jetpadang.data.OrderRepository
import id.tisnahadiana.jetpadang.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: OrderRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderState>>
        get() = _uiState

    fun getAddedOrderBills() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderBill()
                .collect { orderBill ->
                    val totalBill =
                        orderBill.sumOf { it.order.price * it.count }
                    _uiState.value = UiState.Success(OrderState(orderBill, totalBill))
                }
        }
    }

    fun updateOrderBill(orderId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderBill(orderId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderBills()
                    }
                }
        }
    }
}