package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopItem
import com.example.shoppinglist.domain.EditShopItem
import com.example.shoppinglist.domain.GetShopList
import com.example.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {
    // Здесб нужно передавать через иъекцию зависимостей наш репозиторий data,
    // но пока создам экземпляр класса
    private val repository = ShopListRepositoryImpl

    private val getShopList = GetShopList(repository)
    private val deleteShopitem = DeleteShopItem(repository)
    private val editShopItem = EditShopItem(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        // Получаем наш список элементов
        val list = getShopList.getShopList()
        // Устанавливаем его в LiveData
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopitem.deleteShopitem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItem.editShopItem(newItem)
        getShopList()
    }

}