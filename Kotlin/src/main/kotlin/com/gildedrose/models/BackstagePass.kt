package com.gildedrose.models

class BackstagePass(item: Item) : CustomizedItem(item) {

    /**
     * A backstage pass is special, as in, it gets more valuable the closer it gets to the concert date, but is worthless after the date
     */
    override fun updateQuality() {
        if (sellIn < 0) {
            quality = 0
            return
        }

        quality += when {
            sellIn < 6 -> 3
            sellIn < 11 -> 2
            else -> 1
        }
    }
}