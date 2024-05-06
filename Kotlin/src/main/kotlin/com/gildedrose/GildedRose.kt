package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        // Loop over items
        for (i in items.indices) {
            // Check if item is not Aged Brie or any Backstage passes
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) { // check if quality is higher than 0
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        // Only lower quality if item is not the Sulfuras
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                // Only get here when item is Aged Brie or any of the Backstage passes

                if (items[i].quality < 50) { // Quality can never be higher than 50
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            // Increase quality for the second time because it's under 10 or equal
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            // Increase quality for the second time because it's under 5 or equal
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                // any item "Except" Sulfuras should decrease the sellIn date
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                // Once sell by data has passed, quality degrades twice as fast
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        // When the concert is finished, the quality should be set to 0
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    // This is wrong according to the requirements, Aged brie should NOT be increased a second time!
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}

