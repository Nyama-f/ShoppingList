package com.example.shoppinglist.domain

class DeleteShopItem(private val shopListRepository: ShopListRepository) {
    fun deleteShopitem(shopItem: ShopItem){
        shopListRepository.deleteShopitem(shopItem)
    }
}