package com.cak.deepslate_processing;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DPTabs {
    
    public static final TabInfo DEEPSLATE_PROCESSING = PlatformTab.register("deepslate_processing",
        () -> PlatformTab.buildTab(new DPTabs.RegistrateDisplayItemsGenerator(true, () -> DPTabs.DEEPSLATE_PROCESSING)));
    
    public static void register() {
        // fabric: just load the class
    }
    
    private static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {
        
        @Environment(EnvType.CLIENT)
        private static Predicate<Item> makeClient3dItemPredicate() {
            return item -> {
                ItemRenderer itemRenderer = Minecraft.getInstance()
                    .getItemRenderer();
                BakedModel model = itemRenderer.getModel(new ItemStack(item), null, null, 0);
                return model.isGui3d();
            };
        }
        
        private final boolean addItems;
        private final Supplier<TabInfo> tabFilter;
        
        public RegistrateDisplayItemsGenerator(boolean addItems, Supplier<TabInfo> tabFilter) {
            this.addItems = addItems;
            this.tabFilter = tabFilter;
        }
        
        private static Function<Item, ItemStack> makeStackFunc() {
            return ItemStack::new;
        }
        
        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
            Function<Item, ItemStack> stackFunc = makeStackFunc();
            
            List<Item> items = new LinkedList<>();
            if (addItems) {
                items.addAll(collectItems());
            }
            items.addAll(collectBlocks(item -> false));
            
            outputAll(output, items, stackFunc);
        }
        
        private List<Item> collectBlocks(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Block> entry : DeepslateProcessingCommon.REGISTRATE.getAll(Registries.BLOCK)) {
                if (!PlatformTab.isInCreativeTab(entry, tabFilter.get()))
                    continue;
                Item item = entry.get()
                    .asItem();
                if (item == Items.AIR)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            items = new ReferenceArrayList<>(new ReferenceLinkedOpenHashSet<>(items));
            return items;
        }
        
        private List<Item> collectItems() {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Item> entry : DeepslateProcessingCommon.REGISTRATE.getAll(Registries.ITEM)) {
                if (!PlatformTab.isInCreativeTab(entry, tabFilter.get()))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                items.add(item);
            }
            return items;
        }
        
        private static void applyOrderings(List<Item> items, List<ItemOrdering> orderings) {
            for (ItemOrdering ordering : orderings) {
                int anchorIndex = items.indexOf(ordering.anchor());
                if (anchorIndex != -1) {
                    Item item = ordering.item();
                    int itemIndex = items.indexOf(item);
                    if (itemIndex != -1) {
                        items.remove(itemIndex);
                        if (itemIndex < anchorIndex) {
                            anchorIndex--;
                        }
                    }
                    if (ordering.type() == ItemOrdering.Type.AFTER) {
                        items.add(anchorIndex + 1, item);
                    } else {
                        items.add(anchorIndex, item);
                    }
                }
            }
        }
        
        private static void outputAll(CreativeModeTab.Output output, List<Item> items, Function<Item, ItemStack> stackFunc) {
            for (Item item : items) {
                output.accept(stackFunc.apply(item));
            }
        }
        
        private record ItemOrdering(Item item, Item anchor, Type type) {
            public static ItemOrdering before(Item item, Item anchor) {
                return new ItemOrdering(item, anchor, Type.BEFORE);
            }
            
            public static ItemOrdering after(Item item, Item anchor) {
                return new ItemOrdering(item, anchor, Type.AFTER);
            }
            
            public enum Type {
                BEFORE,
                AFTER;
            }
        }
    }
    
    public record TabInfo(ResourceKey<CreativeModeTab> key, Supplier<CreativeModeTab> tab) {
    }
}
