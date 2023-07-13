package com.HiWord9.RPRenames.modConfig;

import com.HiWord9.RPRenames.RPRenames;
import com.HiWord9.RPRenames.configGeneration.ConfigManager;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.math.Color;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ModConfigScreenFactory {
    public static Screen create(Screen parent) {
        ModConfig currentConfig = ModConfig.INSTANCE, defaultConfig = new ModConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setDefaultBackgroundTexture(new Identifier("minecraft", "textures/block/bookshelf.png"))
                .setTitle(Text.translatable("rprenames.config.title"))
                .setSavingRunnable(currentConfig::write);

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("rprenames.config.general"));
        ConfigCategory gui = builder.getOrCreateCategory(Text.translatable("rprenames.config.gui"));
        ConfigCategory debug = builder.getOrCreateCategory(Text.translatable("rprenames.config.debug"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        AbstractConfigListEntry<Boolean> ignoreCEM = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.general.ignoreCEM"), currentConfig.ignoreCEM)
                .setTooltip(Text.translatable("rprenames.config.general.ignoreCEM.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.ignoreCEM = newConfig)
                .setDefaultValue(defaultConfig.ignoreCEM)
                .build();

        AbstractConfigListEntry<Boolean> enableAnvilModification = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.general.enableAnvilModification"), currentConfig.enableAnvilModification)
                .setTooltip(Text.translatable("rprenames.config.general.enableAnvilModification.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.enableAnvilModification = newConfig)
                .setDefaultValue(defaultConfig.enableAnvilModification)
                .build();

        AbstractConfigListEntry<Integer> packCheckboxX = entryBuilder.startIntField(Text.translatable("rprenames.config.general.packCheckboxX"), currentConfig.createConfigCheckboxPosX)
                .setTooltip(Text.translatable("rprenames.config.general.packCheckboxX.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.createConfigCheckboxPosX = newConfig)
                .setDefaultValue(defaultConfig.createConfigCheckboxPosX)
                .build();

        AbstractConfigListEntry<Integer> packCheckboxY = entryBuilder.startIntField(Text.translatable("rprenames.config.general.packCheckboxY"), currentConfig.createConfigCheckboxPosY)
                .setTooltip(Text.translatable("rprenames.config.general.packCheckboxY.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.createConfigCheckboxPosY = newConfig)
                .setDefaultValue(defaultConfig.createConfigCheckboxPosY)
                .build();

        AbstractConfigListEntry<Integer> favoriteButtonX = entryBuilder.startIntField(Text.translatable("rprenames.config.general.favoriteButtonX"), currentConfig.favoritePosX)
                .setTooltip(Text.translatable("rprenames.config.general.favoriteButtonX.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.favoritePosX = newConfig)
                .setDefaultValue(defaultConfig.favoritePosX)
                .build();

        AbstractConfigListEntry<Integer> favoriteButtonY = entryBuilder.startIntField(Text.translatable("rprenames.config.general.favoriteButtonY"), currentConfig.favoritePosY)
                .setTooltip(Text.translatable("rprenames.config.general.favoriteButtonY.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.favoritePosY = newConfig)
                .setDefaultValue(defaultConfig.favoritePosY)
                .build();

        AbstractConfigListEntry<Boolean> loadModBuiltinResources = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.general.loadModBuiltinResources"), currentConfig.loadModBuiltinResources)
                .setTooltip(Text.translatable("rprenames.config.general.loadModBuiltinResources.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.loadModBuiltinResources = newConfig)
                .setDefaultValue(defaultConfig.loadModBuiltinResources)
                .requireRestart()
                .build();

        AbstractConfigListEntry<Boolean> openByDefault = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.openByDefault"), currentConfig.openByDefault)
                .setTooltip(Text.translatable("rprenames.config.gui.openByDefault.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.openByDefault = newConfig)
                .setDefaultValue(defaultConfig.openByDefault)
                .build();

        AbstractConfigListEntry<Boolean> highlightSlot = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.highlightSlot"), currentConfig.highlightSlot)
                .setTooltip(Text.translatable("rprenames.config.gui.highlightSlot.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.highlightSlot = newConfig)
                .setDefaultValue(defaultConfig.highlightSlot)
                .build();

        AbstractConfigListEntry<Integer> slotHighlightColor = entryBuilder.startColorField(Text.translatable("rprenames.config.gui.slotHighlightColor"), Color.ofTransparent(currentConfig.slotHighlightColorRGB))
                .setTooltip(Text.translatable("rprenames.config.gui.slotHighlightColor.tooltip"))
                .setDefaultValue(defaultConfig.slotHighlightColorRGB)
                .setSaveConsumer(newConfig -> currentConfig.slotHighlightColorRGB = newConfig)
                .build();

        AbstractConfigListEntry<Integer> slotHighlightALPHA = entryBuilder.startIntSlider(Text.translatable("rprenames.config.gui.slotHighlightALPHA"), currentConfig.slotHighlightColorALPHA, 0, 100)
                .setTooltip(Text.translatable("rprenames.config.gui.slotHighlightALPHA.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.slotHighlightColorALPHA = newConfig)
                .setDefaultValue(defaultConfig.slotHighlightColorALPHA)
                .setTextGetter(percent -> {
                    if (percent == 0) {
                        return Text.translatable("rprenames.config.gui.slotHighlightALPHA.off").formatted(Formatting.RED);
                    }
                    return Text.of(percent.toString());
                })
                .build();

        AbstractConfigListEntry<Boolean> translateMobNames = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.translateMobNames"), currentConfig.translateMobNames)
                .setTooltip(Text.translatable("rprenames.config.gui.translateMobNames.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.translateMobNames = newConfig)
                .setDefaultValue(defaultConfig.translateMobNames)
                .build();

        AbstractConfigListEntry<Boolean> translateItemNames = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.translateItemNames"), currentConfig.translateItemNames)
                .setTooltip(Text.translatable("rprenames.config.gui.translateItemNames.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.translateItemNames = newConfig)
                .setDefaultValue(defaultConfig.translateItemNames)
                .build();

        AbstractConfigListEntry<Boolean> enablePreview = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.enablePreview"), currentConfig.enablePreview)
                .setTooltip(Text.translatable("rprenames.config.gui.enablePreview.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.enablePreview = newConfig)
                .setDefaultValue(defaultConfig.enablePreview)
                .build();

        AbstractConfigListEntry<Boolean> playerPreviewByDefault = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.playerPreviewByDefault"), currentConfig.playerPreviewByDefault)
                .setTooltip(Text.translatable("rprenames.config.gui.playerPreviewByDefault.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.playerPreviewByDefault = newConfig)
                .setDefaultValue(defaultConfig.playerPreviewByDefault)
                .build();

        AbstractConfigListEntry<Boolean> spinMobPreview = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.spinMobPreview"), currentConfig.spinMobPreview)
                .setTooltip(Text.translatable("rprenames.config.gui.spinMobPreview.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.spinMobPreview = newConfig)
                .setDefaultValue(defaultConfig.spinMobPreview)
                .build();

        AbstractConfigListEntry<Boolean> spinPlayerPreview = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.spinPlayerPreview"), currentConfig.spinPlayerPreview)
                .setTooltip(Text.translatable("rprenames.config.gui.spinPlayerPreview.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.spinPlayerPreview = newConfig)
                .setDefaultValue(defaultConfig.spinPlayerPreview)
                .build();

        AbstractConfigListEntry<Boolean> disableSnowGolemPumpkin = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.disableSnowGolemPumpkin"), currentConfig.disableSnowGolemPumpkin)
                .setTooltip(Text.translatable("rprenames.config.gui.disableSnowGolemPumpkin.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.disableSnowGolemPumpkin = newConfig)
                .setDefaultValue(defaultConfig.disableSnowGolemPumpkin)
                .build();

        AbstractConfigListEntry<Double> scaleFactorItem = entryBuilder.startDoubleField(Text.translatable("rprenames.config.gui.scaleFactorItem"), currentConfig.scaleFactorItem)
                .setTooltip(Text.translatable("rprenames.config.gui.scaleFactorItem.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.scaleFactorItem = newConfig)
                .setDefaultValue(defaultConfig.scaleFactorItem)
                .build();

        AbstractConfigListEntry<Double> scaleFactorEntity = entryBuilder.startDoubleField(Text.translatable("rprenames.config.gui.scaleFactorEntity"), currentConfig.scaleFactorEntity)
                .setTooltip(Text.translatable("rprenames.config.gui.scaleFactorEntity.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.scaleFactorEntity = newConfig)
                .setDefaultValue(defaultConfig.scaleFactorEntity)
                .build();

        AbstractConfigListEntry<Boolean> alwaysAllowPlayerPreviewHead = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.alwaysAllowPlayerPreviewHead"), currentConfig.alwaysAllowPlayerPreviewHead)
                .setTooltip(Text.translatable("rprenames.config.gui.alwaysAllowPlayerPreviewHead.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.alwaysAllowPlayerPreviewHead = newConfig)
                .setDefaultValue(defaultConfig.alwaysAllowPlayerPreviewHead)
                .build();

        AbstractConfigListEntry<Boolean> disablePageArrowsTips = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.disablePageArrowsTips"), currentConfig.disablePageArrowsTips)
                .setTooltip(Text.translatable("rprenames.config.gui.disablePageArrowsTips.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.disablePageArrowsTips = newConfig)
                .setDefaultValue(defaultConfig.disablePageArrowsTips)
                .build();

        AbstractConfigListEntry<Boolean> disablePlayerPreviewTips = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.gui.disablePlayerPreviewTips"), currentConfig.disablePlayerPreviewTips)
                .setTooltip(Text.translatable("rprenames.config.gui.disablePlayerPreviewTips.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.disablePlayerPreviewTips = newConfig)
                .setDefaultValue(defaultConfig.disablePlayerPreviewTips)
                .build();

        AbstractConfigListEntry<Boolean> updateConfig = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.debug.updateConfig"), currentConfig.updateConfig)
                .setTooltip(Text.translatable("rprenames.config.debug.updateConfig.tooltip"))
                .setSaveConsumer(newConfig -> currentConfig.updateConfig = newConfig)
                .setDefaultValue(defaultConfig.updateConfig)
                .build();

        class PrevToggle { boolean bl = false; }
        final PrevToggle prevToggleRecreateConfig = new PrevToggle();
        AbstractConfigListEntry<Boolean> recreateConfig = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.debug.recreateConfig"), false)
                .setTooltip(Text.translatable("rprenames.config.debug.recreateConfig.tooltip"))
                .setYesNoTextSupplier((bl) -> {
                    if (bl != prevToggleRecreateConfig.bl) {
                        RPRenames.LOGGER.info("Recreating config manually");
                        ConfigManager.configUpdate();
                        prevToggleRecreateConfig.bl = bl;
                    }
                    return Text.translatable("rprenames.config.debug.recreateConfig.title").fillStyle(Style.EMPTY.withColor(Formatting.GOLD));
                })
                .build();

        final PrevToggle prevToggleClearConfig = new PrevToggle();
        AbstractConfigListEntry<Boolean> clearConfig = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.debug.clearConfig"), false)
                .setTooltip(Text.translatable("rprenames.config.debug.clearConfig.tooltip"))
                .setYesNoTextSupplier((bl) -> {
                    if (bl != prevToggleClearConfig.bl) {
                        RPRenames.LOGGER.info("Deleting config manually");
                        ConfigManager.configClear();
                        prevToggleClearConfig.bl = bl;
                    }
                    return Text.translatable("rprenames.config.debug.clearConfig.title").fillStyle(Style.EMPTY.withColor(Formatting.RED));
                })
                .build();

        final PrevToggle prevToggleOpenConfigFolder = new PrevToggle();
        AbstractConfigListEntry<Boolean> openConfigFolder = entryBuilder.startBooleanToggle(Text.translatable("rprenames.config.debug.openConfigFolder"), false)
                .setTooltip(Text.translatable("rprenames.config.debug.openConfigFolder.tooltip"))
                .setYesNoTextSupplier((bl) -> {
                    if (bl != prevToggleOpenConfigFolder.bl) {
                        RPRenames.LOGGER.info("Opening config folder manually");
                        ConfigManager.openConfigFolder();
                        prevToggleOpenConfigFolder.bl = bl;
                    }
                    return Text.translatable("rprenames.config.debug.openConfigFolder.title").fillStyle(Style.EMPTY.withColor(Formatting.AQUA));
                })
                .build();

        SubCategoryBuilder createConfigCheckboxPosition = entryBuilder.startSubCategory(Text.translatable("rprenames.config.general.subCategory.createConfigCheckboxPosition"));
        createConfigCheckboxPosition.add(0, packCheckboxX);
        createConfigCheckboxPosition.add(1, packCheckboxY);

        SubCategoryBuilder favoriteButtonPosition = entryBuilder.startSubCategory(Text.translatable("rprenames.config.general.subCategory.favoriteButtonPosition"));
        favoriteButtonPosition.add(0, favoriteButtonX);
        favoriteButtonPosition.add(1, favoriteButtonY);

        SubCategoryBuilder slotHighlightColorSettings = entryBuilder.startSubCategory(Text.translatable("rprenames.config.gui.subCategory.slotHighlightColorSettings"));
        slotHighlightColorSettings.add(0, slotHighlightColor);
        slotHighlightColorSettings.add(1, slotHighlightALPHA);

        SubCategoryBuilder tooltipTranslations = entryBuilder.startSubCategory(Text.translatable("rprenames.config.gui.subCategory.tooltipTranslations"));
        tooltipTranslations.add(0, translateItemNames);
        tooltipTranslations.add(1, translateMobNames);

        SubCategoryBuilder previewScale = entryBuilder.startSubCategory(Text.translatable("rprenames.config.gui.subCategory.previewScale"));
        previewScale.add(0, scaleFactorItem);
        previewScale.add(1, scaleFactorEntity);

        general.addEntry(ignoreCEM);
        general.addEntry(enableAnvilModification);
        general.addEntry(createConfigCheckboxPosition.build());
        general.addEntry(favoriteButtonPosition.build());
        general.addEntry(loadModBuiltinResources);
        gui.addEntry(openByDefault);
        gui.addEntry(highlightSlot);
        gui.addEntry(slotHighlightColorSettings.build());
        gui.addEntry(tooltipTranslations.build());
        gui.addEntry(enablePreview);
        gui.addEntry(playerPreviewByDefault);
        gui.addEntry(spinMobPreview);
        gui.addEntry(spinPlayerPreview);
        gui.addEntry(disableSnowGolemPumpkin);
        gui.addEntry(previewScale.build());
        gui.addEntry(alwaysAllowPlayerPreviewHead);
        gui.addEntry(disablePageArrowsTips);
        gui.addEntry(disablePlayerPreviewTips);
        debug.addEntry(updateConfig);
        debug.addEntry(recreateConfig);
        debug.addEntry(clearConfig);
        debug.addEntry(openConfigFolder);

        return builder.build();
    }
}
