package com.gildedrose

import com.gildedrose.models.Item
import com.gildedrose.models.ItemFactory

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items
            .map {
                // Make sure we map every item to the correct type of item prior to updating them
                ItemFactory.determineCustomItem(it)
            }
            .forEach { item ->
                item.updateItem()
            }
    }
}

