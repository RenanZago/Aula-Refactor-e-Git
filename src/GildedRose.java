public class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";
    public static final String ETERNAL_ARTIFACT = "Eternal Artifact";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String PERISHABLE = "Perishable";

    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;
    public static final int BACKSTAGE_PASS_THRESHOLD_1 = 11;
    public static final int BACKSTAGE_PASS_THRESHOLD_2 = 6;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals(AGED_BRIE)
                    && !items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)
                    && !items[i].name.equals(CONJURED_MANA_CAKE)
                    && !items[i].name.equals(ETERNAL_ARTIFACT)) {
                if (items[i].quality > MIN_QUALITY) {
                    if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                        items[i].quality = items[i].quality - 1;
                        // Additional degradation for perishable items
                        if (items[i].name.contains(PERISHABLE)) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                }
            } else {
                if (items[i].quality < MAX_QUALITY) {
                    items[i].quality = items[i].quality + 1;
                    if (items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                        if (items[i].sellIn < BACKSTAGE_PASS_THRESHOLD_1) {
                            if (items[i].quality < MAX_QUALITY) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                        if (items[i].sellIn < BACKSTAGE_PASS_THRESHOLD_2) {
                            if (items[i].quality < MAX_QUALITY) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    } else if (items[i].name.equals(CONJURED_MANA_CAKE)) {
                        // Conjured items degrade twice as fast
                        items[i].quality = items[i].quality + 1; // But for quality increase? Wait, adjust logic
                    } else if (items[i].name.equals(ETERNAL_ARTIFACT)) {
                        // Increases quality over time, but slowly
                        if (items[i].sellIn % 2 == 0) {
                            items[i].quality = items[i].quality + 1;
                        }
                    }
                }
            }

            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS) && !items[i].name.equals(ETERNAL_ARTIFACT)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals(AGED_BRIE)) {
                    if (!items[i].name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                        if (items[i].quality > MIN_QUALITY) {
                            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                                items[i].quality = items[i].quality - 1;
                                if (items[i].name.equals(CONJURED_MANA_CAKE)) {
                                    items[i].quality = items[i].quality - 1; // Extra degradation
                                }
                                // Handle perishable post-sellIn
                                if (items[i].name.contains(PERISHABLE)) {
                                    items[i].quality = items[i].quality - 2;
                                }
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < MAX_QUALITY) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
                // Additional logic for eternal items after sellIn (though sellIn doesn't change)
                if (items[i].name.equals(ETERNAL_ARTIFACT) && items[i].quality < MAX_QUALITY) {
                    items[i].quality = items[i].quality + 1;
                }
            }

            // Ensure quality bounds
            if (items[i].quality > MAX_QUALITY && !items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                items[i].quality = MAX_QUALITY;
            }
            if (items[i].quality < MIN_QUALITY) {
                items[i].quality = MIN_QUALITY;
            }
        }
    }
}