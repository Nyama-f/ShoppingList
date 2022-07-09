package com.example.shoppinglist.data.mappers

import com.example.shoppinglist.data.database.ShopItemDbModel
import com.example.shoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            id = shopItemDbModel.id,
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            enabled = shopItemDbModel.enabled
        )
    }
    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}