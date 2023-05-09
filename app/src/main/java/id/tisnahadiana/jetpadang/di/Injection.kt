package id.tisnahadiana.jetpadang.di

import id.tisnahadiana.jetpadang.data.OrderRepository

object Injection {
    fun provideRepository(): OrderRepository {
        return OrderRepository.getInstance()
    }
}