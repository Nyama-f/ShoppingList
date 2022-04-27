package com.example.shoppinglist.domain

class EditShopItem(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}