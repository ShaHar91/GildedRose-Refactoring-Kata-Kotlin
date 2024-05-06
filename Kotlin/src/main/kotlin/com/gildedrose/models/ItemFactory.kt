package com.gildedrose.models

object ItemFactory {

    private val legendaryItems = listOf("Sulfuras, Hand of Ragnaros")
    private const val AGED_BRIE = "Aged Brie"
    private const val BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert"
    private const val CONJURED_ITEM_PREFIX = "Conjured"

    fun determineCustomItem(item: Item): CustomizedItem {
        return when (item.name) {
            AGED_BRIE -> Brie(item)
            BACKSTAGE_PASS -> BackstagePass(item)
            else -> {
                val isConjured = item.name.startsWith(CONJURED_ITEM_PREFIX)
                val itemIsLegendary = checkIfItemIsLegendary(item)

                CustomizedItem(item, isLegendary = itemIsLegendary, isConjured = isConjured)
            }
        }
    }

    private fun checkIfItemIsLegendary(item: Item): Boolean {
        return item.name in legendaryItems
    }
}