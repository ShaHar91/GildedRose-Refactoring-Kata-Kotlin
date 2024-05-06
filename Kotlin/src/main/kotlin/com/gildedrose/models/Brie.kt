package com.gildedrose.models

class Brie(item: Item) : CustomizedItem(item) {

    /**
     * The quality increases the older this item gets
     */
    override fun updateQuality() {
        quality++
    }
}