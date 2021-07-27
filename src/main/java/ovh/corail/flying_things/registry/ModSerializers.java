package ovh.corail.flying_things.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ovh.corail.flying_things.loot.BossLootCondition;
import ovh.corail.flying_things.loot.LootTableAdder;
import ovh.corail.flying_things.loot.MobdropPhial;
import ovh.corail.flying_things.loot.MobdropPumpkinStick;
import ovh.corail.flying_things.loot.MonsterLootCondition;
import ovh.corail.flying_things.recipe.RecipeColoredBroom;
import ovh.corail.flying_things.recipe.RecipePumkinBroom;
import ovh.corail.flying_things.recipe.RecipeRaidCarpet;

import static ovh.corail.flying_things.ModFlyingThings.MOD_ID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSerializers {
	
	public static LootConditionType IS_BOSS;
	public static LootConditionType IS_MONSTER;

    @SubscribeEvent
    public static void onRegisterSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
    	event.getRegistry().register(new RecipeColoredBroom.Serializer().setRegistryName(MOD_ID, "colored_broom"));
        event.getRegistry().register(new SpecialRecipeSerializer<>(RecipePumkinBroom::new).setRegistryName(MOD_ID, "pumpkin_broom"));
        event.getRegistry().register(new SpecialRecipeSerializer<>(rl -> new RecipeRaidCarpet(rl, 18, Ingredient.fromStacks(new ItemStack(Items.PRISMARINE_CRYSTALS)))).setRegistryName(MOD_ID, "pillage_0"));
        event.getRegistry().register(new SpecialRecipeSerializer<>(rl -> new RecipeRaidCarpet(rl, 19, Ingredient.fromStacks(new ItemStack(Items.QUARTZ)))).setRegistryName(MOD_ID, "pillage_1"));
    }
    
    @SubscribeEvent
    public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
    	event.getRegistry().register(new LootTableAdder.Serializer().setRegistryName(MOD_ID, "loottable_adder"));
    	event.getRegistry().register(new MobdropPhial.Serializer().setRegistryName(MOD_ID, "mobdrop_phial"));
    	event.getRegistry().register(new MobdropPumpkinStick.Serializer().setRegistryName(MOD_ID, "mobdrop_pumpkin_stick"));
    }
    
    public static void registerLootConditions() {
    	IS_BOSS = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MOD_ID, "is_boss"), new LootConditionType(new BossLootCondition.Serializer()));
    	IS_MONSTER = Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MOD_ID, "is_monster"), new LootConditionType(new MonsterLootCondition.Serializer()));
    }
}
