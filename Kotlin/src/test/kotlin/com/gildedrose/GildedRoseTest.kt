package com.gildedrose

import com.gildedrose.models.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `day passed, values get lowered`() {
        val items = listOf(Item("Random item", 10, 50))
        val app = GildedRose(items)

        app.updateQuality()

        // Assert after day 1
        assertEquals(49, app.items.first().quality)

        app.updateQuality()

        // Assert after day 2
        assertEquals(48, app.items.first().quality)
    }

    @Test
    fun `sell by date has been passed, quality passed twice as fast`() {
        val items = listOf(Item("Random item", -10, 50))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(48, app.items.first().quality)
    }

    @Test
    fun `quality can never be negative`() {
        val items = listOf(Item("Random item", -10, 3))
        val app = GildedRose(items)

        // 2 days passed
        app.updateQuality()
        app.updateQuality()

        assertEquals(0, app.items.first().quality)
    }

    @Test
    fun `sell by date has been passed, Aged Brie increases in quality`() {
        val items = listOf(Item("Aged Brie", -10, 25))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(26, app.items.first().quality)
    }

    @Test
    fun `quality of item should never surpass 50`() {
        val items = listOf(
            Item("Aged Brie", -10, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 3, 46)
        )
        val app = GildedRose(items)

        // 2 days passed
        app.updateQuality()
        app.updateQuality()

        assertEquals(50, app.items.first().quality)
        assertEquals(50, app.items[1].quality)
    }

    @Test
    fun `Sulfuras never degrades`() {
        val items = listOf(
            Item("Sulfuras, Hand of Ragnaros", -10, 80),
        )
        val app = GildedRose(items)

        // 4 days passed
        app.updateQuality()
        app.updateQuality()
        app.updateQuality()
        app.updateQuality()

        assertEquals(80, app.items.first().quality)
    }

    @Test
    fun `Backstage pass increases if day passes`() {
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 20, 25))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(26, app.items.first().quality)
    }

    @Test
    fun `Backstage pass increase by 2 if less than 10 days remaining`() {
        val items = listOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)
        )
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(12, app.items.first().quality)
    }


    @Test
    fun `Backstage pass increase by 3 if less than 5 days remaining`() {
        val items = listOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        )
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(13, app.items.first().quality)
    }

    @Test
    fun `Backstage pass quality is 0 when not sold in time`() {
        val items = listOf(
            Item("Backstage passes to a TAFKAL80ETC concert", 0, 30)
        )
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(0, app.items.first().quality)
    }

    @Test
    fun `Conjured items degrade twice as fast`() {
        val items = listOf(
            Item("Conjured Mana Cake", 5, 10)
        )
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(8, app.items.first().quality)
    }
}
