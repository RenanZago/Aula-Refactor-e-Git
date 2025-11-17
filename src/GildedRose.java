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
    public static final int STANDARD_QUALITY_DECREASE = 1;
    public static final int STANDARD_QUALITY_INCREASE = 1;
    public static final int DAILY_SELLIN_DECREASE = 1;
    public static final int EXPIRATION_THRESHOLD = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);

            updateSellIn(item);

            handleExpiratedItem(item);

            enforceQualityBounds(item);
        }
    }

    private void enforceQualityBounds(Item item) {
        // Ensure quality bounds
        if (item.getQuality() > MAX_QUALITY && !item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.setQuality(MAX_QUALITY);
        }
        if (item.getQuality() < MIN_QUALITY) {
            item.setQuality(MIN_QUALITY);
        }
    }

    private void handleExpiratedItem(Item item) {
        if (item.getSellIn() < EXPIRATION_THRESHOLD) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    if (item.getQuality() > MIN_QUALITY) {
                        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                            item.setQuality(item.getQuality() - STANDARD_QUALITY_DECREASE);
                            if (item.name.equals(CONJURED_MANA_CAKE)) {
                                item.setQuality(item.getQuality() - STANDARD_QUALITY_DECREASE); // Extra degradation
                            }
                            // Handle perishable post-sellIn
                            if (item.name.contains(PERISHABLE)) {
                                item.setQuality(item.getQuality() - STANDARD_QUALITY_DECREASE * 2);
                            }
                        }
                    }
                } else {
                    item.setQuality(MIN_QUALITY);
                }
            } else {
                if (item.getQuality() < MAX_QUALITY) {
                    item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE);
                }
            }
            // Additional logic for eternal items after sellIn (though sellIn doesn't change)
            if (item.name.equals(ETERNAL_ARTIFACT) && item.getQuality() < MAX_QUALITY) {
                item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE);
            }
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS) && !item.name.equals(ETERNAL_ARTIFACT)) {
            item.setSellIn(item.getSellIn() - DAILY_SELLIN_DECREASE);
        }
    }

    private void updateItemQuality(Item item) {
        if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)
                && !item.name.equals(CONJURED_MANA_CAKE)
                && !item.name.equals(ETERNAL_ARTIFACT)) {
            if (item.getQuality() > MIN_QUALITY) {
                if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                    item.setQuality(item.getQuality() - STANDARD_QUALITY_DECREASE);
                    // Additional degradation for perishable items
                    if (item.name.contains(PERISHABLE)) {
                        item.setQuality(item.getQuality() - STANDARD_QUALITY_DECREASE);
                    }
                }
            }
        } else {
            if (item.getQuality() < MAX_QUALITY) {
                item.setQuality(item.getQuality() + 1);
                if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    if (item.getSellIn() < BACKSTAGE_PASS_THRESHOLD_1) {
                        if (item.getQuality() < MAX_QUALITY) {
                            item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE);
                        }
                    }
                    if (item.getSellIn() < BACKSTAGE_PASS_THRESHOLD_2) {
                        if (item.getQuality() < MAX_QUALITY) {
                            item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE);
                        }
                    }
                } else if (item.name.equals(CONJURED_MANA_CAKE)) {
                    // Conjured items degrade twice as fast
                    item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE); // But for quality increase? Wait, adjust logic
                } else if (item.name.equals(ETERNAL_ARTIFACT)) {
                    // Increases quality over time, but slowly
                    if (item.getSellIn() % 2 == 0) {
                        item.setQuality(item.getQuality() + STANDARD_QUALITY_INCREASE);
                    }
                }
            }
        }
    }
}