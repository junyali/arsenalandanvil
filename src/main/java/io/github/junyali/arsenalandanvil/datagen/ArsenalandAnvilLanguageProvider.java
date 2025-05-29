package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ArsenalandAnvilLanguageProvider extends LanguageProvider {
    public ArsenalandAnvilLanguageProvider(PackOutput output, String locale) {
        super(output, ArsenalandAnvil.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("creativetab.arsenalandanvil.arsenalandanvil", "Arsenal and Anvil");

        for (var entry: ArsenalandAnvilItems.ITEMS.getEntries()) {
            String itemName = entry.getId().getPath();
            String displayName = generateDisplayName(itemName);
            add(entry.get(), displayName);
        }
    }

    private String generateDisplayName(String itemName) {
        // an extremely bandaid solution to generating translation from item id X_X
        String[] parts = itemName.split("_");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            if (result.length() > 0) result.append(" ");
            result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }

        return result.toString();
    }
}
