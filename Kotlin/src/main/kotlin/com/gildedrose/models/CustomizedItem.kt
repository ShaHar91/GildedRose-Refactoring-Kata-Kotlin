package com.gildedrose.models

/**
 * @param item The item object that holds the name, sellIn and quality. Only sellIn and quality can be updated in this item.
 * @param isLegendary Used to determine if this item can be counted as a legendary item. A legendary item won't be degraded by default.
 * @param isConjured Used to determine if this item has been conjured. A conjured item degrades twice as fast by default.
 *
 * This is a personal choice to have the `isLegendary` and `isConjured` here as parameters.
 * Should be discussed with the team if the value is ok like this or if we really want specific "Sulfuras" and "Conjured ...." models.
 */
open class CustomizedItem(private val item: Item, private val isLegendary: Boolean = false, private val isConjured: Boolean = false) {
    val name: String
        get() = item.name

    var sellIn: Int
        get() = item.sellIn
        set(value) {
            // A legendary item does not have to be sold
            if (isLegendary) return

            item.sellIn = value
        }

    var quality: Int
        get() = item.quality
        set(value) {
            // Depending on the requirements or use-cases, we could also work with throwing errors when an unsupported value is set
            //   require(value in 0..50)

            if (isLegendary) return

            // No regular item can have a negative quality or a quality that is higher than 50
            // This is a personal choice and should be discussed with the team if the value has to be changed internally or an exception should be thrown
            item.quality = value.coerceIn(0..50)
        }

    override fun toString(): String = item.toString()

    fun updateItem() {
        updateSellIn()
        updateQuality()
    }

    protected open fun updateSellIn() {
        sellIn--
    }

    /**
     * By default, the quality will decrease for every day that passes.
     * Whenever the "sell by date" has passed, the quality will decrease twice as fast.
     */
    protected open fun updateQuality() {
        val degradationForNormalItems = if (sellIn < 0) 2 else 1
        val degradingMultiplier = if (isConjured) 2 else 1

        quality -= degradationForNormalItems * degradingMultiplier
    }
}

