package id.tisnahadiana.jetpadang.ui.screen.order

import id.tisnahadiana.jetpadang.model.OrderBill

data class OrderState(
    val orderBill: List<OrderBill>,
    val totalBill: Int
)