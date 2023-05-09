package id.tisnahadiana.jetpadang.data

import id.tisnahadiana.jetpadang.model.FakeOrderDataSource
import id.tisnahadiana.jetpadang.model.OrderBill
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class OrderRepository {
    private val orderBills = mutableListOf<OrderBill>()

    init {
        if (orderBills.isEmpty()) {
            FakeOrderDataSource.dummyOrders.forEach {
                orderBills.add(OrderBill(it, 0))
            }
        }
    }

    fun getAllOrders(): Flow<List<OrderBill>> {
        return flowOf(orderBills)
    }

    fun getOrderBillById(orderId: Long): OrderBill {
        return orderBills.first {
            it.order.id == orderId
        }
    }

    fun updateOrderBill(orderId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderBills.indexOfFirst { it.order.id == orderId }
        val result = if (index >= 0) {
            val orderBill = orderBills[index]
            orderBills[index] =
                orderBill.copy(order = orderBill.order, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderBill(): Flow<List<OrderBill>> {
        return getAllOrders()
            .map { orderBills ->
                orderBills.filter { orderBills ->
                    orderBills.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: OrderRepository? = null

        fun getInstance(): OrderRepository =
            instance ?: synchronized(this) {
                OrderRepository().apply {
                    instance = this
                }
            }
    }
}