package com.gildedrose

import com.gildedrose.models.Item

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        // Loop over items
        items.forEach { item ->
            // Check if item is not Aged Brie or any Backstage passes
            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0 && item.name != "Sulfuras, Hand of Ragnaros") {
                    // Only lower quality if item is not the Sulfuras
                    item.quality -= 1
                }
            } else {
                // Only get here when item is Aged Brie or any of the Backstage passes
                increaseQualityIfLessThan50(item)

                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    // Increase quality for the second time because it's under 10 or equal
                    if (item.sellIn < 11) {
                        increaseQualityIfLessThan50(item)
                    }

                    if (item.sellIn < 6) {
                        // Increase quality for the second time because it's under 5 or equal
                        increaseQualityIfLessThan50(item)
                    }
                }
            }

            // Dirty fix to decrease the quality for a second time of the conjured items
            if (item.name == "Conjured Mana Cake" && item.quality > 0) {
                item.quality -= 1
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                // any item "Except" Sulfuras should decrease the sellIn date
                item.sellIn -= 1
            }

            if (item.sellIn < 0 && item.name != "Aged Brie") {
                if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.quality > 0 && item.name != "Sulfuras, Hand of Ragnaros") {
                        // Once sell by data has passed, quality degrades twice as fast
                        item.quality -= 1
                    }
                } else {
                    // When the concert is finished, the quality should be set to 0
                    item.quality = 0
                }
            }
        }
    }

    private fun increaseQualityIfLessThan50(item: Item) {
        if (item.quality < 50) {
            item.quality += 1
        }
    }
}

