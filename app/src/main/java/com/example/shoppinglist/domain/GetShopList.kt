package com.example.shoppinglist.domain

class GetShopList(private val shopListRepository: ShopListRepository) {
    fun getShopList(): List<ShopItem>{
        return shopListRepository.getShopList()
    }
}