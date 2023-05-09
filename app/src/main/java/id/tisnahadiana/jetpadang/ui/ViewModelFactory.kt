package id.tisnahadiana.jetpadang.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.tisnahadiana.jetpadang.data.OrderRepository
import id.tisnahadiana.jetpadang.ui.screen.detail.DetailViewModel
import id.tisnahadiana.jetpadang.ui.screen.home.HomeViewModel
import id.tisnahadiana.jetpadang.ui.screen.order.OrderViewModel

class ViewModelFactory(private val repository: OrderRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}