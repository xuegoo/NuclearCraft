package nc;

import java.io.File;

import nc.armour.BoronArmour;
import nc.armour.BronzeArmour;
import nc.armour.DUArmour;
import nc.armour.ToughArmour;
import nc.block.NCBlocks;
import nc.block.accelerator.BlockSuperElectromagnet;
import nc.block.accelerator.BlockSupercooler;
import nc.block.accelerator.BlockSynchrotron;
import nc.block.basic.BlockBlock;
import nc.block.basic.BlockMachineBlock;
import nc.block.basic.BlockOre;
import nc.block.crafting.BlockNuclearWorkspace;
import nc.block.fluid.BlockHelium;
import nc.block.fluid.FluidHelium;
import nc.block.generator.BlockElectromagnet;
import nc.block.generator.BlockFissionReactor;
import nc.block.generator.BlockFusionReactor;
import nc.block.generator.BlockFusionReactorBlock;
import nc.block.generator.BlockRTG;
import nc.block.generator.BlockReactionGenerator;
import nc.block.generator.BlockSolarPanel;
import nc.block.generator.BlockWRTG;
import nc.block.itemblock.ItemBlockBlock;
import nc.block.itemblock.ItemBlockOre;
import nc.block.machine.BlockCollector;
import nc.block.machine.BlockCooler;
import nc.block.machine.BlockCrusher;
import nc.block.machine.BlockElectricCrusher;
import nc.block.machine.BlockElectricFurnace;
import nc.block.machine.BlockElectrolyser;
import nc.block.machine.BlockFactory;
import nc.block.machine.BlockFurnace;
import nc.block.machine.BlockHastener;
import nc.block.machine.BlockHeliumExtractor;
import nc.block.machine.BlockIoniser;
import nc.block.machine.BlockIrradiator;
import nc.block.machine.BlockNuclearFurnace;
import nc.block.machine.BlockOxidiser;
import nc.block.machine.BlockSeparator;
import nc.block.nuke.BlockNuke;
import nc.block.nuke.BlockNukeExploding;
import nc.block.quantum.BlockSimpleQuantum;
import nc.block.reactor.BlockBlastBlock;
import nc.block.reactor.BlockCellBlock;
import nc.block.reactor.BlockCoolerBlock;
import nc.block.reactor.BlockGraphiteBlock;
import nc.block.reactor.BlockReactorBlock;
import nc.block.reactor.BlockSpeedBlock;
import nc.block.reactor.BlockTubing1;
import nc.block.reactor.BlockTubing2;
import nc.entity.EntityBullet;
import nc.entity.EntityNuclearGrenade;
import nc.entity.EntityNuclearMonster;
import nc.entity.EntityNukePrimed;
import nc.entity.EntityPaul;
import nc.gui.GuiHandler;
import nc.handler.EntityHandler;
import nc.handler.FuelHandler;
import nc.item.ItemEnderChest;
import nc.item.ItemFuel;
import nc.item.ItemMaterial;
import nc.item.ItemNuclearGrenade;
import nc.item.ItemPart;
import nc.item.ItemPistol;
import nc.item.ItemToughBow;
import nc.item.NCAxe;
import nc.item.NCHoe;
import nc.item.NCItems;
import nc.item.NCPaxel;
import nc.item.NCPickaxe;
import nc.item.NCRecord;
import nc.item.NCShovel;
import nc.item.NCSword;
import nc.proxy.CommonProxy;
import nc.tile.accelerator.TileSuperElectromagnet;
import nc.tile.accelerator.TileSupercooler;
import nc.tile.accelerator.TileSynchrotron;
import nc.tile.crafting.TileNuclearWorkspace;
import nc.tile.generator.TileElectromagnet;
import nc.tile.generator.TileFissionReactor;
import nc.tile.generator.TileFusionReactor;
import nc.tile.generator.TileFusionReactorBlock;
import nc.tile.generator.TileRTG;
import nc.tile.generator.TileReactionGenerator;
import nc.tile.generator.TileSolarPanel;
import nc.tile.generator.TileWRTG;
import nc.tile.machine.TileAutoWorkspace;
import nc.tile.machine.TileCollector;
import nc.tile.machine.TileCooler;
import nc.tile.machine.TileCrusher;
import nc.tile.machine.TileElectricCrusher;
import nc.tile.machine.TileElectricFurnace;
import nc.tile.machine.TileElectrolyser;
import nc.tile.machine.TileFactory;
import nc.tile.machine.TileFurnace;
import nc.tile.machine.TileHastener;
import nc.tile.machine.TileHeliumExtractor;
import nc.tile.machine.TileIoniser;
import nc.tile.machine.TileIrradiator;
import nc.tile.machine.TileNuclearFurnace;
import nc.tile.machine.TileOxidiser;
import nc.tile.machine.TileSeparator;
import nc.tile.other.TileTubing1;
import nc.tile.other.TileTubing2;
import nc.tile.quantum.TileSimpleQuantum;
import nc.util.Achievements;
import nc.worldgen.OreGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = NuclearCraft.modid, version = NuclearCraft.version)

public class NuclearCraft {
	public static final String modid = "NuclearCraft";
	public static final String version = "1.6e";
	 
	public static final CreativeTabs tabNC = new CreativeTabs("tabNC") {
		// Creative Tab Shown Item
		public Item getTabIconItem() {
			return Item.getItemFromBlock(NCBlocks.fusionReactor);
		}
	};
	
		// Tool Materials
	public static final ToolMaterial Bronze = EnumHelper.addToolMaterial("Bronze", 2, 300, 7.0F, 2.0F, 12);
	public static final ToolMaterial ToughAlloy = EnumHelper.addToolMaterial("ToughAlloy", 4, 2500, 16.0F, 12.0F, 10);
	public static final ToolMaterial ToughPaxel = EnumHelper.addToolMaterial("ToughPaxel", 4, 15000, 16.0F, 14.0F, 10);
	public static final ToolMaterial dU = EnumHelper.addToolMaterial("dU", 5, 7500, 25.0F, 18.0F, 50);
	public static final ToolMaterial dUPaxel = EnumHelper.addToolMaterial("dUPaxel", 5, 45000, 25.0F, 20.0F, 50);
	public static final ToolMaterial Boron = EnumHelper.addToolMaterial("Boron", 2, 1200, 9.0F, 3.0F, 5);
	
	public static final ArmorMaterial ToughArmorMaterial = EnumHelper.addArmorMaterial("ToughArmorMaterial", 50, new int [] {5, 9, 5, 3}, 10);
	public static final ArmorMaterial BoronArmorMaterial = EnumHelper.addArmorMaterial("BoronArmorMaterial", 30, new int [] {3, 7, 5, 3}, 5);
	public static final ArmorMaterial BronzeArmorMaterial = EnumHelper.addArmorMaterial("BronzeArmorMaterial", 20, new int [] {2, 6, 5, 2}, 9);
	public static final ArmorMaterial dUArmorMaterial = EnumHelper.addArmorMaterial("dUArmorMaterial", 100, new int [] {5, 9, 6, 4}, 50);
	
		// Mod Checker
	public static boolean isIC2Loaded;
	public static boolean isTELoaded;
	
		// Mod Hooks
	public static IC2Recipes IC2Hook;
	public static TERecipes TEHook;
	
	@Instance(modid)
	public static NuclearCraft instance;
	
	public static final int guiIdNuclearFurnace = 0;
	public static final int guiIdFurnace = 1;
	public static final int guiIdCrusher = 2;
	public static final int guiIdElectricCrusher = 3;
	public static final int guiIdElectricFurnace = 4;
	public static final int guiIdReactionGenerator = 5;
	public static final int guiIdSeparator = 6;
	public static final int guiIdHastener = 7;
	public static final int guiIdFissionReactorGraphite = 8;
	public static final int guiIdNuclearWorkspace = 9;
	public static final int guiIdCollector = 10;
	public static final int guiIdFusionReactor = 11;
	public static final int guiIdElectrolyser = 12;
	public static final int guiIdOxidiser = 13;
	public static final int guiIdIoniser = 14;
	public static final int guiIdIrradiator = 15;
	public static final int guiIdCooler = 16;
	public static final int guiIdFactory = 17;
	public static final int guiIdHeliumExtractor = 18;
	public static final int guiIdSynchrotron = 19;
	public static final int guiIdAutoWorkspace = 20;
	
	// Config File
	public static boolean workspace;
	
	public static boolean oreGenCopper;
	public static int oreSizeCopper;
	public static int oreRarityCopper;
	public static int oreMaxHeightCopper;
	public static boolean oreGenTin;
	public static int oreSizeTin;
	public static int oreRarityTin;
	public static int oreMaxHeightTin;
	public static boolean oreGenLead;
	public static int oreSizeLead;
	public static int oreRarityLead;
	public static int oreMaxHeightLead;
	public static boolean oreGenSilver;
	public static int oreSizeSilver;
	public static int oreRaritySilver;
	public static int oreMaxHeightSilver;
	public static boolean oreGenUranium;
	public static int oreSizeUranium;
	public static int oreRarityUranium;
	public static int oreMaxHeightUranium;
	public static boolean oreGenThorium;
	public static int oreSizeThorium;
	public static int oreRarityThorium;
	public static int oreMaxHeightThorium;
	public static boolean oreGenLithium;
	public static int oreSizeLithium;
	public static int oreRarityLithium;
	public static int oreMaxHeightLithium;
	public static boolean oreGenBoron;
	public static int oreSizeBoron;
	public static int oreRarityBoron;
	public static int oreMaxHeightBoron;
	public static boolean oreGenMagnesium;
	public static int oreSizeMagnesium;
	public static int oreRarityMagnesium;
	public static int oreMaxHeightMagnesium;
	public static boolean oreGenPlutonium;
	public static int oreSizePlutonium;
	public static int oreRarityPlutonium;
	public static int oreMaxHeightPlutonium;
	
	public static int nuclearFurnaceCookSpeed;
	public static int nuclearFurnaceCookEfficiency;
	public static int metalFurnaceCookSpeed;
	public static int metalFurnaceCookEfficiency;
	public static int crusherCrushSpeed;
	public static int crusherCrushEfficiency;
	public static int electricCrusherCrushSpeed;
	public static int electricCrusherCrushEfficiency;
	public static int electricFurnaceSmeltSpeed;
	public static int electricFurnaceSmeltEfficiency;
	public static int separatorSpeed;
	public static int separatorEfficiency;
	public static int hastenerSpeed;
	public static int hastenerEfficiency;
	public static int collectorSpeed;
	public static int electrolyserSpeed;
	public static int electrolyserEfficiency;
	public static int oxidiserSpeed;
	public static int oxidiserEfficiency;
	public static int ioniserSpeed;
	public static int ioniserEfficiency;
	public static int irradiatorSpeed;
	public static int irradiatorEfficiency;
	public static int coolerSpeed;
	public static int coolerEfficiency;
	public static int factorySpeed;
	public static int factoryEfficiency;
	public static int heliumExtractorSpeed;
	public static int heliumExtractorEfficiency;
	public static int reactionGeneratorRF;
	public static int reactionGeneratorEfficiency;
	public static int RTGRF;
	public static int WRTGRF;
	public static int solarRF;
	
	public static boolean enablePaul;
	public static boolean enableNuclearMonster;
	public static boolean enableNukes;
	public static boolean enableLoot;
	public static int lootModifier;
	
	public static int fissionMaxLength;
	public static int fissionRF;
	public static int fissionEfficiency;
	public static int fissionHeat;
	public static boolean nuclearMeltdowns;
	public static int baseRFLEU;
	public static int baseRFHEU;
	public static int baseRFLEP;
	public static int baseRFHEP;
	public static int baseRFMOX;
	public static int baseRFTBU;
	public static int baseRFLEUOx;
	public static int baseRFHEUOx;
	public static int baseRFLEPOx;
	public static int baseRFHEPOx;
	public static int baseFuelLEU;
	public static int baseFuelHEU;
	public static int baseFuelLEP;
	public static int baseFuelHEP;
	public static int baseFuelMOX;
	public static int baseFuelTBU;
	public static int baseFuelLEUOx;
	public static int baseFuelHEUOx;
	public static int baseFuelLEPOx;
	public static int baseFuelHEPOx;
	public static int baseHeatLEU;
	public static int baseHeatHEU;
	public static int baseHeatLEP;
	public static int baseHeatHEP;
	public static int baseHeatMOX;
	public static int baseHeatTBU;
	public static int baseHeatLEUOx;
	public static int baseHeatHEUOx;
	public static int baseHeatLEPOx;
	public static int baseHeatHEPOx;
	
	public static int fusionMaxRadius;
	public static int fusionRF;
	public static int fusionEfficiency;
	public static int fusionHeat;
	public static int electromagnetRF;
	public static boolean fusionMeltdowns;
	public static int baseRFHH;
	public static int baseRFHD;
	public static int baseRFHT;
	public static int baseRFHHe;
	public static int baseRFHB;
	public static int baseRFHLi6;
	public static int baseRFHLi7;
	public static int baseRFDD;
	public static int baseRFDT;
	public static int baseRFDHe;
	public static int baseRFDB;
	public static int baseRFDLi6;
	public static int baseRFDLi7;
	public static int baseRFTT;
	public static int baseRFTHe;
	public static int baseRFTB;
	public static int baseRFTLi6;
	public static int baseRFTLi7;
	public static int baseRFHeHe;
	public static int baseRFHeB;
	public static int baseRFHeLi6;
	public static int baseRFHeLi7;	
	public static int baseRFBB;
	public static int baseRFBLi6;
	public static int baseRFBLi7;	
	public static int baseRFLi6Li6;
	public static int baseRFLi6Li7;
	public static int baseRFLi7Li7;
	public static int baseFuelHH;
	public static int baseFuelHD;
	public static int baseFuelHT;
	public static int baseFuelHHe;
	public static int baseFuelHB;
	public static int baseFuelHLi6;
	public static int baseFuelHLi7;
	public static int baseFuelDD;
	public static int baseFuelDT;
	public static int baseFuelDHe;
	public static int baseFuelDB;
	public static int baseFuelDLi6;
	public static int baseFuelDLi7;
	public static int baseFuelTT;
	public static int baseFuelTHe;
	public static int baseFuelTB;
	public static int baseFuelTLi6;
	public static int baseFuelTLi7;
	public static int baseFuelHeHe;
	public static int baseFuelHeB;
	public static int baseFuelHeLi6;
	public static int baseFuelHeLi7;	
	public static int baseFuelBB;
	public static int baseFuelBLi6;
	public static int baseFuelBLi7;	
	public static int baseFuelLi6Li6;
	public static int baseFuelLi6Li7;
	public static int baseFuelLi7Li7;
	public static int heatHH;
	public static int heatHD;
	public static int heatHT;
	public static int heatHHe;
	public static int heatHB;
	public static int heatHLi6;
	public static int heatHLi7;
	public static int heatDD;
	public static int heatDT;
	public static int heatDHe;
	public static int heatDB;
	public static int heatDLi6;
	public static int heatDLi7;
	public static int heatTT;
	public static int heatTHe;
	public static int heatTB;
	public static int heatTLi6;
	public static int heatTLi7;
	public static int heatHeHe;
	public static int heatHeB;
	public static int heatHeLi6;
	public static int heatHeLi7;	
	public static int heatBB;
	public static int heatBLi6;
	public static int heatBLi7;	
	public static int heatLi6Li6;
	public static int heatLi6Li7;
	public static int heatLi7Li7;
	
	public static int ringMaxSize;
	public static int colliderRF;
	public static int colliderProduction;
	public static int synchrotronRF;
	public static int synchrotronProduction;
	public static int superElectromagnetRF;
	public static int electromagnetHe;
	public static int acceleratorProduction;
	
	//Armor
	public static int toughHelmID;
	public static int toughChestID;
	public static int toughLegsID;
	public static int toughBootsID;
	
	public static int boronHelmID;
	public static int boronChestID;
	public static int boronLegsID;
	public static int boronBootsID;
	
	public static int bronzeHelmID;
	public static int bronzeChestID;
	public static int bronzeLegsID;
	public static int bronzeBootsID;
	
	public static int dUHelmID;
	public static int dUChestID;
	public static int dULegsID;
	public static int dUBootsID;
	
	public static Fluid liquidHelium;
	
	@SidedProxy(clientSide = "nc.proxy.ClientProxy", serverSide = "nc.proxy.CommonProxy")
	public static CommonProxy NCProxy;
	
	public static final Material liquidhelium = (new MaterialLiquid(MapColor.tntColor));
	public static DamageSource heliumfreeze = (new DamageSource("heliumfreeze")).setDamageBypassesArmor();
	
	public static Achievements achievements;
	
	public static Achievement nuclearFurnaceAchievement;
	public static Achievement dominosAchievement;
	public static Achievement fishAndRicecakeAchievement;
	public static Achievement heavyDutyWorkspaceAchievement;
	public static Achievement nukeAchievement;
	public static Achievement toolAchievement;
	public static Achievement reactionGeneratorAchievement;
	public static Achievement fissionControllerAchievement;
	public static Achievement RTGAchievement;
	public static Achievement fusionReactorAchievement;
	public static Achievement factoryAchievement;
	public static Achievement separatorAchievement;
	public static Achievement pistolAchievement;
	public static Achievement solarAchievement;
	public static Achievement synchrotronAchievement;
	public static Achievement oxidiserAchievement;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		//config
		Configuration config = new Configuration(new File("config/NuclearCraft/NCConfig.cfg"));
		Configuration fissionConfig = new Configuration(new File("config/NuclearCraft/FissionConfig.cfg"));
		Configuration fusionConfig = new Configuration(new File("config/NuclearCraft/FusionConfig.cfg"));
		Configuration acceleratorConfig = new Configuration(new File("config/NuclearCraft/AcceleratorConfig.cfg"));
		config.load();
		fissionConfig.load();
		fusionConfig.load();
		acceleratorConfig.load();
		
		workspace = config.getBoolean("If disabled, all crafting recipes will be vanilla crafting table recipes, and the Heavy Duty Workspace will be disabled", "!: Enable Heavy Duty Workspace", true, "");
		
		oreGenCopper = config.getBoolean("Generation", "0.0: Copper Ore", true, "");
		oreSizeCopper = config.getInt("Chunk Size", "0.0: Copper Ore", 8, 1, 100, "");
		oreRarityCopper = config.getInt("Gen Rate", "0.0: Copper Ore", 12, 1, 100, "");
		oreMaxHeightCopper = config.getInt("Max Height", "0.0: Copper Ore", 60, 1, 255, "");
		oreGenTin = config.getBoolean("Generation", "0.1: Tin Ore", true, "");
		oreSizeTin = config.getInt("Chunk Size", "0.1: Tin Ore", 8, 1, 100, "");
		oreRarityTin = config.getInt("Gen Rate", "0.1: Tin Ore", 11, 1, 100, "");
		oreMaxHeightTin = config.getInt("Max Height", "0.1: Tin Ore", 60, 1, 255, "");
		oreGenLead = config.getBoolean("Generation", "0.2: Lead Ore", true, "");
		oreSizeLead = config.getInt("Chunk Size", "0.2: Lead Ore", 7, 1, 100, "");
		oreRarityLead = config.getInt("Gen Rate", "0.2: Lead Ore", 11, 1, 100, "");
		oreMaxHeightLead = config.getInt("Max Height", "0.2: Lead Ore", 40, 1, 255, "");
		oreGenSilver = config.getBoolean("Generation", "0.3: Silver Ore", true, "");
		oreSizeSilver = config.getInt("Chunk Size", "0.3: Silver Ore", 7, 1, 100, "");
		oreRaritySilver = config.getInt("Gen Rate", "0.3: Silver Ore", 10, 1, 100, "");
		oreMaxHeightSilver = config.getInt("Max Height", "0.3: Silver Ore", 40, 1, 255, "");
		oreGenUranium = config.getBoolean("Generation", "0.4: Uranium Ore", true, "");
		oreSizeUranium = config.getInt("Chunk Size", "0.4: Uranium Ore", 3, 1, 100, "");
		oreRarityUranium = config.getInt("Gen Rate", "0.4: Uranium Ore", 3, 1, 100, "");
		oreMaxHeightUranium = config.getInt("Max Height", "0.4: Uranium Ore", 40, 1, 255, "");
		oreGenThorium = config.getBoolean("Generation", "0.5: Thorium Ore", true, "");
		oreSizeThorium = config.getInt("Chunk Size", "0.5: Thorium Ore", 3, 1, 100, "");
		oreRarityThorium = config.getInt("Gen Rate", "0.5: Thorium Ore", 3, 1, 100, "");
		oreMaxHeightThorium = config.getInt("Max Height", "0.5: Thorium Ore", 40, 1, 255, "");
		oreGenLithium = config.getBoolean("Generation", "0.6: Lithium Ore", true, "");
		oreSizeLithium = config.getInt("Chunk Size", "0.6: Lithium Ore", 6, 1, 100, "");
		oreRarityLithium = config.getInt("Gen Rate", "0.6: Lithium Ore", 7, 1, 100, "");
		oreMaxHeightLithium = config.getInt("Max Height", "0.6: Lithium Ore", 32, 1, 255, "");
		oreGenBoron = config.getBoolean("Generation", "0.7: Boron Ore", true, "");
		oreSizeBoron = config.getInt("Chunk Size", "0.7: Boron Ore", 6, 1, 100, "");
		oreRarityBoron = config.getInt("Gen Rate", "0.7: Boron Ore", 7, 1, 100, "");
		oreMaxHeightBoron = config.getInt("Max Height", "0.7: Boron Ore", 24, 1, 255, "");
		oreGenMagnesium = config.getBoolean("Generation", "0.8: Magnesium Ore", true, "");
		oreSizeMagnesium = config.getInt("Chunk Size", "0.8: Magnesium Ore", 7, 1, 100, "");
		oreRarityMagnesium = config.getInt("Gen Rate", "0.8: Magnesium Ore", 8, 1, 100, "");
		oreMaxHeightMagnesium = config.getInt("Max Height", "0.8: Magnesium Ore", 24, 1, 255, "");
		oreGenPlutonium = config.getBoolean("Generation", "0.9: Plutonium Ore", true, "");
		oreSizePlutonium = config.getInt("Chunk Size", "0.9: Plutonium Ore", 3, 1, 100, "");
		oreRarityPlutonium = config.getInt("Gen Rate", "0.9: Plutonium Ore", 3, 1, 100, "");
		oreMaxHeightPlutonium = config.getInt("Max Height", "0.9: Plutonium Ore", 255, 1, 255, "");

		electricCrusherCrushSpeed = config.getInt("Electic Crusher Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		electricCrusherCrushEfficiency = config.getInt("Electic Crusher Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		electricFurnaceSmeltSpeed = config.getInt("Electic Furnace Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		electricFurnaceSmeltEfficiency = config.getInt("Electic Furnace Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		separatorSpeed = config.getInt("Isotope Separator Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		separatorEfficiency = config.getInt("Isotope Separator Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		hastenerSpeed = config.getInt("Decay Hastener Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		hastenerEfficiency = config.getInt("Decay Hastener Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		electrolyserSpeed = config.getInt("Electrolyser Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		electrolyserEfficiency = config.getInt("Electrolyser Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		oxidiserSpeed = config.getInt("Oxidiser Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		oxidiserEfficiency = config.getInt("Oxidiser Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		ioniserSpeed = config.getInt("Ioniser Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		ioniserEfficiency = config.getInt("Ioniser Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		irradiatorSpeed = config.getInt("Neutron Irradiator Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		irradiatorEfficiency = config.getInt("Neutron Irradiator Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		coolerSpeed = config.getInt("Supercooler Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		coolerEfficiency = config.getInt("Supercooler Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		factorySpeed = config.getInt("Manufactory Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		factoryEfficiency = config.getInt("Manufactory Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		heliumExtractorSpeed = config.getInt("Helium Extractor Speed Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		heliumExtractorEfficiency = config.getInt("Helium Extractor Efficiency Multiplier", "1.0: RF Machines", 100, 10, 1000, "");
		reactionGeneratorRF = config.getInt("Reaction Generator RF Production Multiplier", "1.1: RF Generators", 100, 10, 1000, "");
		reactionGeneratorEfficiency = config.getInt("Reaction Generator Efficiency Multiplier", "1.1: RF Generators", 100, 10, 1000, "");
		RTGRF = config.getInt("RTG RF/t", "1.1: RF Generators", 150, 10, 1000, "");
		WRTGRF = config.getInt("WRTG RF/t", "1.1: RF Generators", 5, 0, 50, "");
		solarRF = config.getInt("Solar Panel RF/t", "1.1: RF Generators", 10, 1, 100, "");
		nuclearFurnaceCookSpeed = config.getInt("Nuclear Furnace Speed Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		nuclearFurnaceCookEfficiency = config.getInt("Nuclear Furnace Fuel Usage Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		metalFurnaceCookSpeed = config.getInt("Metal Furnace Speed Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		metalFurnaceCookEfficiency = config.getInt("Metal Furnace Fuel Usage Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		crusherCrushSpeed = config.getInt("Crusher Speed Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		crusherCrushEfficiency = config.getInt("Crusher Fuel Usage Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		collectorSpeed = config.getInt("Helium Collector Speed Multiplier", "1.2: Non-RF Machines", 100, 10, 1000, "");
		
		enableNuclearMonster = config.getBoolean("Enable Nuclear Monsters Spawning", "2.0: Mobs", true, "");
		enablePaul = config.getBoolean("Enable Paul", "2.0: Mobs", true, "");
		enableNukes = config.getBoolean("Enable Nuclear Weapons", "2.1: Other", true, "");
		enableLoot = config.getBoolean("Enable Loot in Generated Chests", "2.1: Other", true, "");
		lootModifier = config.getInt("Loot Gen Rate Modifier", "2.1: Other", 10, 1, 100, "");
		
		fissionMaxLength = fissionConfig.getInt("Fission Reactor Maximum Interior Length", "0: General", 50, 5, 500, "");
		fissionRF = fissionConfig.getInt("Fission Reactor RF Production Multiplier", "0: General", 100, 10, 1000, "");
		fissionEfficiency = fissionConfig.getInt("Fission Reactor Fuel Efficiency Multiplier", "0: General", 100, 10, 1000, "");
		fissionHeat = fissionConfig.getInt("Fission Reactor Heat Production Multiplier", "0: General", 100, 10, 1000, "");
		nuclearMeltdowns = fissionConfig.getBoolean("Enable Fission Reactor Meltdowns", "0: General", true, "");
		baseRFLEU = fissionConfig.getInt("LEU Base Power", "1: Fission Fuel Base Power", 150, 15, 1500, "");
		baseRFHEU = fissionConfig.getInt("HEU Base Power", "1: Fission Fuel Base Power", 600, 60, 6000, "");
		baseRFLEP = fissionConfig.getInt("LEP Base Power", "1: Fission Fuel Base Power", 300, 30, 3000, "");
		baseRFHEP = fissionConfig.getInt("HEP Base Power", "1: Fission Fuel Base Power", 1200, 120, 12000, "");
		baseRFMOX = fissionConfig.getInt("MOX Base Power", "1: Fission Fuel Base Power", 360, 36, 3600, "");
		baseRFTBU = fissionConfig.getInt("TBU Base Power", "1: Fission Fuel Base Power", 80, 8, 800, "");
		baseRFLEUOx = fissionConfig.getInt("LEU-Ox Base Power", "1: Fission Fuel Base Power", 225, 22, 2250, "");
		baseRFHEUOx = fissionConfig.getInt("HEU-Ox Base Power", "1: Fission Fuel Base Power", 900, 90, 9000, "");
		baseRFLEPOx = fissionConfig.getInt("LEP-Ox Base Power", "1: Fission Fuel Base Power", 450, 45, 4500, "");
		baseRFHEPOx = fissionConfig.getInt("HEP-Ox Base Power", "1: Fission Fuel Base Power", 1800, 180, 18000, "");
		baseFuelLEU = fissionConfig.getInt("LEU Usage Rate", "2: Fission Fuel Usage Rate", 15000, 1500, 150000, "");
		baseFuelHEU = fissionConfig.getInt("HEU Usage Rate", "2: Fission Fuel Usage Rate", 15000, 1500, 150000, "");
		baseFuelLEP = fissionConfig.getInt("LEP Usage Rate", "2: Fission Fuel Usage Rate", 28800, 2880, 288000, "");
		baseFuelHEP = fissionConfig.getInt("HEP Usage Rate", "2: Fission Fuel Usage Rate", 28800, 2880, 288000, "");
		baseFuelMOX = fissionConfig.getInt("MOX Usage Rate", "2: Fission Fuel Usage Rate", 20000, 2000, 200000, "");
		baseFuelTBU = fissionConfig.getInt("TBU Usage Rate", "2: Fission Fuel Usage Rate", 7500, 750, 75000, "");
		baseFuelLEUOx = fissionConfig.getInt("LEU-Ox Usage Rate", "2: Fission Fuel Usage Rate", 15000, 1500, 150000, "");
		baseFuelHEUOx = fissionConfig.getInt("HEU-Ox Usage Rate", "2: Fission Fuel Usage Rate", 15000, 1500, 150000, "");
		baseFuelLEPOx = fissionConfig.getInt("LEP-Ox Usage Rate", "2: Fission Fuel Usage Rate", 28800, 2880, 288000, "");
		baseFuelHEPOx = fissionConfig.getInt("HEP-Ox Usage Rate", "2: Fission Fuel Usage Rate", 28800, 2880, 288000, "");
		baseHeatLEU = fissionConfig.getInt("LEU Base Heat", "3: Fission Fuel Base Heat", 28, 2, 280, "");
		baseHeatHEU = fissionConfig.getInt("HEU Base Heat", "3: Fission Fuel Base Heat", 280, 28, 2800, "");
		baseHeatLEP = fissionConfig.getInt("LEP Base Heat", "3: Fission Fuel Base Heat", 84, 8, 840, "");
		baseHeatHEP = fissionConfig.getInt("HEP Base Heat", "3: Fission Fuel Base Heat", 840, 84, 8400, "");
		baseHeatMOX = fissionConfig.getInt("MOX Base Heat", "3: Fission Fuel Base Heat", 90, 9, 900, "");
		baseHeatTBU = fissionConfig.getInt("TBU Base Heat", "3: Fission Fuel Base Heat", 4, 0, 40, "");
		baseHeatLEUOx = fissionConfig.getInt("LEU-Ox Base Heat", "3: Fission Fuel Base Heat", 35, 3, 350, "");
		baseHeatHEUOx = fissionConfig.getInt("HEU-Ox Base Heat", "3: Fission Fuel Base Heat", 350, 35, 3500, "");
		baseHeatLEPOx = fissionConfig.getInt("LEP-Ox Base Heat", "3: Fission Fuel Base Heat", 105, 10, 1050, "");
		baseHeatHEPOx = fissionConfig.getInt("HEP-Ox Base Heat", "3: Fission Fuel Base Heat", 1050, 105, 10500, "");
		
		fusionMaxRadius = fusionConfig.getInt("Fusion Reactor Maximum Radius - Defined as Number of Blocks Between the Middle of the Control Chunk and a Central Inner Electromagnet", "0: General", 50, 5, 500, "");
		fusionRF = fusionConfig.getInt("Fusion Reactor RF Production Multiplier", "0: General", 100, 10, 1000, "");
		fusionEfficiency = fusionConfig.getInt("Fusion Reactor Fuel Efficiency Multiplier", "0: General", 100, 10, 1000, "");
		fusionHeat = fusionConfig.getInt("Fusion Reactor Heat Production Multiplier", "0: General", 100, 10, 1000, "");
		electromagnetRF = fusionConfig.getInt("Electromagnet RF/t Requirement", "0: General", 50, 0, 1000, "");
		fusionMeltdowns = fusionConfig.getBoolean("Enable Fusion Reactor Overheating", "0: General", true, "");
		baseRFHH = fusionConfig.getInt("HH Base Power", "1: Fusion Combo Base Power", 80, 8, 800, "");
		baseRFHD = fusionConfig.getInt("HD Base Power", "1: Fusion Combo Base Power", 60, 6, 600, "");
		baseRFHT = fusionConfig.getInt("HT Base Power", "1: Fusion Combo Base Power", 20, 2, 200, "");
		baseRFHHe = fusionConfig.getInt("HHe Base Power", "1: Fusion Combo Base Power", 20, 2, 200, "");
		baseRFHB = fusionConfig.getInt("HB Base Power", "1: Fusion Combo Base Power", 80, 8, 800, "");
		baseRFHLi6 = fusionConfig.getInt("HLi6 Base Power", "1: Fusion Combo Base Power", 30, 3, 300, "");
		baseRFHLi7 = fusionConfig.getInt("HLi7 Base Power", "1: Fusion Combo Base Power", 120, 12, 1200, "");
		baseRFDD = fusionConfig.getInt("DD Base Power", "1: Fusion Combo Base Power", 140, 14, 1400, "");
		baseRFDT = fusionConfig.getInt("DT Base Power", "1: Fusion Combo Base Power", 200, 20, 2000, "");
		baseRFDHe = fusionConfig.getInt("DHe Base Power", "1: Fusion Combo Base Power", 160, 16, 1600, "");
		baseRFDB = fusionConfig.getInt("DB Base Power", "1: Fusion Combo Base Power", 20, 2, 200, "");
		baseRFDLi6 = fusionConfig.getInt("DLi6 Base Power", "1: Fusion Combo Base Power", 150, 15, 1500, "");
		baseRFDLi7 = fusionConfig.getInt("DLi7 Base Power", "1: Fusion Combo Base Power", 10, 1, 100, "");
		baseRFTT = fusionConfig.getInt("TT Base Power", "1: Fusion Combo Base Power", 60, 6, 600, "");
		baseRFTHe = fusionConfig.getInt("THe Base Power", "1: Fusion Combo Base Power", 40, 4, 400, "");
		baseRFTB = fusionConfig.getInt("TB Base Power", "1: Fusion Combo Base Power", 10, 1, 100, "");
		baseRFTLi6 = fusionConfig.getInt("TLi6 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFTLi7 = fusionConfig.getInt("TLi7 Base Power", "1: Fusion Combo Base Power", 10, 1, 100, "");
		baseRFHeHe = fusionConfig.getInt("HeHe Base Power", "1: Fusion Combo Base Power", 120, 12, 1200, "");
		baseRFHeB = fusionConfig.getInt("HeB Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFHeLi6 = fusionConfig.getInt("HeLi6 Base Power", "1: Fusion Combo Base Power", 140, 14, 1400, "");
		baseRFHeLi7 = fusionConfig.getInt("HeLi7 Base Power", "1: Fusion Combo Base Power", 30, 3, 300, "");
		baseRFBB = fusionConfig.getInt("BB Base Power", "1: Fusion Combo Base Power", 10, 1, 100, "");
		baseRFBLi6 = fusionConfig.getInt("BLi6 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFBLi7 = fusionConfig.getInt("BLi7 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFLi6Li6 = fusionConfig.getInt("Li6Li6 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFLi6Li7 = fusionConfig.getInt("Li6Li7 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseRFLi7Li7 = fusionConfig.getInt("Li7Li7 Base Power", "1: Fusion Combo Base Power", 5, 0, 50, "");
		baseFuelHH = fusionConfig.getInt("HH Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 100, 10, 1000, "");
		baseFuelHD = fusionConfig.getInt("HD Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 60, 6, 600, "");
		baseFuelHT = fusionConfig.getInt("HT Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 40, 4, 400, "");
		baseFuelHHe = fusionConfig.getInt("HHe Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 40, 4, 400, "");
		baseFuelHB = fusionConfig.getInt("HB Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelHLi6 = fusionConfig.getInt("HLi6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelHLi7 = fusionConfig.getInt("HLi7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 20, 2, 200, "");
		baseFuelDD = fusionConfig.getInt("DD Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 40, 4, 400, "");
		baseFuelDT = fusionConfig.getInt("DT Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 80, 8, 800, "");
		baseFuelDHe = fusionConfig.getInt("DHe Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 25, 2, 250, "");
		baseFuelDB = fusionConfig.getInt("DB Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelDLi6 = fusionConfig.getInt("DLi6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 25, 2, 250, "");
		baseFuelDLi7 = fusionConfig.getInt("DLi7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelTT = fusionConfig.getInt("TT Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 20, 2, 200, "");
		baseFuelTHe = fusionConfig.getInt("THe Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelTB = fusionConfig.getInt("TB Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelTLi6 = fusionConfig.getInt("TLi6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 2, 0, 20, "");
		baseFuelTLi7 = fusionConfig.getInt("TLi7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 4, 0, 40, "");
		baseFuelHeHe = fusionConfig.getInt("HeHe Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 20, 2, 200, "");
		baseFuelHeB = fusionConfig.getInt("HeB Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelHeLi6 = fusionConfig.getInt("HeLi6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 28, 2, 280, "");
		baseFuelHeLi7 = fusionConfig.getInt("HeLi7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 10, 1, 100, "");
		baseFuelBB = fusionConfig.getInt("BB Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelBLi6 = fusionConfig.getInt("BLi6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelBLi7 = fusionConfig.getInt("BLi7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelLi6Li6 = fusionConfig.getInt("Li6Li6 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelLi6Li7 = fusionConfig.getInt("Li6Li7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		baseFuelLi7Li7 = fusionConfig.getInt("Li7Li7 Base Fuel Usage Rate", "2: Fusion Combo Base Fuel Usage Rate", 5, 0, 50, "");
		heatHH = fusionConfig.getInt("HH Heat Variable", "3: Fusion Combo Heat Variable", 2140, 500, 20000, "");
		heatHD = fusionConfig.getInt("HD Heat Variable", "3: Fusion Combo Heat Variable", 1380, 500, 20000, "");
		heatHT = fusionConfig.getInt("HT Heat Variable", "3: Fusion Combo Heat Variable", 4700, 500, 20000, "");
		heatHHe = fusionConfig.getInt("HHe Heat Variable", "3: Fusion Combo Heat Variable", 4820, 500, 20000, "");
		heatHB = fusionConfig.getInt("HB Heat Variable", "3: Fusion Combo Heat Variable", 5660, 500, 20000, "");
		heatHLi6 = fusionConfig.getInt("HLi6 Heat Variable", "3: Fusion Combo Heat Variable", 4550, 500, 20000, "");
		heatHLi7 = fusionConfig.getInt("HLi7 Heat Variable", "3: Fusion Combo Heat Variable", 4640, 500, 20000, "");
		heatDD = fusionConfig.getInt("DD Heat Variable", "3: Fusion Combo Heat Variable", 4780, 500, 20000, "");
		heatDT = fusionConfig.getInt("DT Heat Variable", "3: Fusion Combo Heat Variable", 670, 500, 20000, "");
		heatDHe = fusionConfig.getInt("DHe Heat Variable", "3: Fusion Combo Heat Variable", 2370, 500, 20000, "");
		heatDB = fusionConfig.getInt("DB Heat Variable", "3: Fusion Combo Heat Variable", 5955, 500, 20000, "");
		heatDLi6 = fusionConfig.getInt("DLi6 Heat Variable", "3: Fusion Combo Heat Variable", 5335, 500, 20000, "");
		heatDLi7 = fusionConfig.getInt("DLi7 Heat Variable", "3: Fusion Combo Heat Variable", 7345, 500, 20000, "");
		heatTT = fusionConfig.getInt("TT Heat Variable", "3: Fusion Combo Heat Variable", 3875, 500, 20000, "");
		heatTHe = fusionConfig.getInt("THe Heat Variable", "3: Fusion Combo Heat Variable", 5070, 500, 20000, "");
		heatTB = fusionConfig.getInt("TB Heat Variable", "3: Fusion Combo Heat Variable", 7810, 500, 20000, "");
		heatTLi6 = fusionConfig.getInt("TLi6 Heat Variable", "3: Fusion Combo Heat Variable", 7510, 500, 20000, "");
		heatTLi7 = fusionConfig.getInt("TLi7 Heat Variable", "3: Fusion Combo Heat Variable", 8060, 500, 20000, "");
		heatHeHe = fusionConfig.getInt("HeHe Heat Variable", "3: Fusion Combo Heat Variable", 6800, 500, 20000, "");
		heatHeB = fusionConfig.getInt("HeB Heat Variable", "3: Fusion Combo Heat Variable", 8060, 500, 20000, "");
		heatHeLi6 = fusionConfig.getInt("HeLi6 Heat Variable", "3: Fusion Combo Heat Variable", 8800, 500, 20000, "");
		heatHeLi7 = fusionConfig.getInt("HeLi7 Heat Variable", "3: Fusion Combo Heat Variable", 12500, 500, 20000, "");
		heatBB = fusionConfig.getInt("BB Heat Variable", "3: Fusion Combo Heat Variable", 8500, 500, 20000, "");
		heatBLi6 = fusionConfig.getInt("BLi6 Heat Variable", "3: Fusion Combo Heat Variable", 9200, 500, 20000, "");
		heatBLi7 = fusionConfig.getInt("BLi7 Heat Variable", "3: Fusion Combo Heat Variable", 13000, 500, 20000, "");
		heatLi6Li6 = fusionConfig.getInt("Li6Li6 Heat Variable", "3: Fusion Combo Heat Variable", 12000, 500, 20000, "");
		heatLi6Li7 = fusionConfig.getInt("Li6Li7 Heat Variable", "3: Fusion Combo Heat Variable", 11000, 500, 20000, "");
		heatLi7Li7 = fusionConfig.getInt("Li7Li7 Heat Variable", "3: Fusion Combo Heat Variable", 14000, 500, 20000, "");
		
		ringMaxSize = acceleratorConfig.getInt("Maximum Ring Size", "0: General", 200, 20, 2000, "");
		colliderRF = acceleratorConfig.getInt("Collider RF Requirement Multiplier", "0: General", 100, 10, 1000, "");
		colliderProduction = acceleratorConfig.getInt("Collider Production Multiplier", "0: General", 100, 10, 1000, "");
		synchrotronRF = acceleratorConfig.getInt("Synchrotron RF Requirement Multiplier", "0: General", 100, 10, 1000, "");
		synchrotronProduction = acceleratorConfig.getInt("Synchrotron Production Multiplier", "0: General", 100, 10, 1000, "");
		superElectromagnetRF = acceleratorConfig.getInt("Superconducting Electromagnet RF/t Requirement", "0: General", 500, 0, 10000, "");
		electromagnetHe = acceleratorConfig.getInt("Superconducting Electromagnet Liquid Helium mB/s Requirement", "0: General", 1, 0, 100, "");
		acceleratorProduction = acceleratorConfig.getInt("Synchrotron Production Multiplier", "0: General", 100, 10, 1000, "");
		
		config.save();
		fissionConfig.save();
		fusionConfig.save();
		acceleratorConfig.save();
		
		// Fusion
		//TileEntityFusionReactor.registerReactions();
		
		// Fluid Registry
		liquidHelium = new FluidHelium().setLuminosity(0).setDensity(125).setViscosity(1).setTemperature(4).setUnlocalizedName("liquidHelium").setRarity(net.minecraft.item.EnumRarity.rare);
		FluidRegistry.registerFluid(liquidHelium);
		NCBlocks.blockHelium = new BlockHelium(liquidHelium, liquidhelium.setReplaceable()).setBlockName("liquidHeliumBlock");
		GameRegistry.registerBlock(NCBlocks.blockHelium, "liquidHeliumBlock");
		
		// Ore Registry
		GameRegistry.registerBlock(NCBlocks.blockOre = new BlockOre("blockOre", Material.rock), ItemBlockOre.class, "blockOre");
		
		// Block Registry
		GameRegistry.registerBlock(NCBlocks.blockBlock = new BlockBlock("blockBlock", Material.iron), ItemBlockBlock.class, "blockBlock");
		
		NCBlocks.simpleQuantumUp = new BlockSimpleQuantum(true).setBlockName("simpleQuantumUp").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.simpleQuantumUp, "simpleQuantumUp");
		NCBlocks.simpleQuantumDown = new BlockSimpleQuantum(false).setBlockName("simpleQuantumDown").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.simpleQuantumDown, "simpleQuantumDown");
		
		NCBlocks.graphiteBlock = new BlockGraphiteBlock().setBlockName("graphiteBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeStone).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.graphiteBlock, "graphiteBlock");
		NCBlocks.cellBlock = new BlockCellBlock().setBlockName("cellBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.cellBlock, "cellBlock");
		NCBlocks.reactorBlock = new BlockReactorBlock().setBlockName("reactorBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.reactorBlock, "reactorBlock");
		NCBlocks.coolerBlock = new BlockCoolerBlock().setBlockName("coolerBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.coolerBlock, "coolerBlock");
		NCBlocks.speedBlock = new BlockSpeedBlock().setBlockName("speedBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(2.0F);
		GameRegistry.registerBlock(NCBlocks.speedBlock, "speedBlock");
		NCBlocks.blastBlock = new BlockBlastBlock().setBlockName("blastBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(4000.0F).setHardness(30.0F);
		GameRegistry.registerBlock(NCBlocks.blastBlock, "blastBlock");
		NCBlocks.machineBlock = new BlockMachineBlock().setBlockName("machineBlock").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(4000.0F).setHardness(30.0F);
		GameRegistry.registerBlock(NCBlocks.machineBlock, "machineBlock");
		
		NCBlocks.tubing1 = new BlockTubing1().setBlockName("tubing1").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal)
				.setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.tubing1, "tubing1");
		NCBlocks.tubing2 = new BlockTubing2().setBlockName("tubing2").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal)
				.setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.tubing2, "tubing2");
	
		// Machine Registry
			// Block
		NCBlocks.electromagnetIdle = new BlockElectromagnet(false).setBlockName("electromagnetIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.electromagnetIdle, "electromagnetIdle");
		NCBlocks.electromagnetActive = new BlockElectromagnet(true).setBlockName("electromagnetActive").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.electromagnetActive, "electromagnetActive");
		NCBlocks.superElectromagnetIdle = new BlockSuperElectromagnet(false).setBlockName("superElectromagnetIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.superElectromagnetIdle, "superElectromagnetIdle");
		NCBlocks.superElectromagnetActive = new BlockSuperElectromagnet(true).setBlockName("superElectromagnetActive").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.superElectromagnetActive, "superElectromagnetActive");
		NCBlocks.supercoolerIdle = new BlockSupercooler(false).setBlockName("supercoolerIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.supercoolerIdle, "supercoolerIdle");
		NCBlocks.supercoolerActive = new BlockSupercooler(true).setBlockName("supercoolerActive").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.supercoolerActive, "supercoolerActive");
		NCBlocks.synchrotronIdle = new BlockSynchrotron(false).setBlockName("synchrotronIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.synchrotronIdle, "synchrotronIdle");
		NCBlocks.synchrotronActive = new BlockSynchrotron(true).setBlockName("synchrotronActive").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(8.0F).setHardness(3.0F);
		GameRegistry.registerBlock(NCBlocks.synchrotronActive, "synchrotronActive");
		
		NCBlocks.nuclearWorkspace = new BlockNuclearWorkspace(Material.iron).setBlockName("nuclearWorkspace").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.nuclearWorkspace, "nuclearWorkspace");
		
		NCBlocks.fusionReactor = new BlockFusionReactor(Material.iron).setBlockName("fusionReactor").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.fusionReactor, "fusionReactor");
		NCBlocks.fusionReactorBlock = new BlockFusionReactorBlock().setBlockName("fusionReactorBlock").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.fusionReactorBlock, "fusionReactorBlock");
		
		NCBlocks.nuclearFurnaceIdle = new BlockNuclearFurnace(false).setBlockName("nuclearFurnaceIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.nuclearFurnaceIdle, "nuclearFurnaceIdle");
		NCBlocks.nuclearFurnaceActive = new BlockNuclearFurnace(true).setBlockName("nuclearFurnaceActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.nuclearFurnaceActive, "nuclearFurnaceActive");
		NCBlocks.furnaceIdle = new BlockFurnace(false).setBlockName("furnaceIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.furnaceIdle, "furnaceIdle");
		NCBlocks.furnaceActive = new BlockFurnace(true).setBlockName("furnaceActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.furnaceActive, "furnaceActive");
		NCBlocks.crusherIdle = new BlockCrusher(false).setBlockName("crusherIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.crusherIdle, "crusherIdle");
		NCBlocks.crusherActive = new BlockCrusher(true).setBlockName("crusherActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.crusherActive, "crusherActive");
		NCBlocks.electricCrusherIdle = new BlockElectricCrusher(false).setBlockName("electricCrusherIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electricCrusherIdle, "electricCrusherIdle");
		NCBlocks.electricCrusherActive = new BlockElectricCrusher(true).setBlockName("electricCrusherActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electricCrusherActive, "electricCrusherActive");
		NCBlocks.electricFurnaceIdle = new BlockElectricFurnace(false).setBlockName("electricFurnaceIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electricFurnaceIdle, "electricFurnaceIdle");
		NCBlocks.electricFurnaceActive = new BlockElectricFurnace(true).setBlockName("electricFurnaceActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electricFurnaceActive, "electricFurnaceActive");
		NCBlocks.reactionGeneratorIdle = new BlockReactionGenerator(false).setBlockName("reactionGeneratorIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.reactionGeneratorIdle, "reactionGeneratorIdle");
		NCBlocks.reactionGeneratorActive = new BlockReactionGenerator(true).setBlockName("reactionGeneratorActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.reactionGeneratorActive, "reactionGeneratorActive");
		NCBlocks.separatorIdle = new BlockSeparator(false).setBlockName("separatorIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.separatorIdle, "separatorIdle");
		NCBlocks.separatorActive = new BlockSeparator(true).setBlockName("separatorActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.separatorActive, "separatorActive");
		NCBlocks.hastenerIdle = new BlockHastener(false).setBlockName("hastenerIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.hastenerIdle, "hastenerIdle");
		NCBlocks.hastenerActive = new BlockHastener(true).setBlockName("hastenerActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.hastenerActive, "hastenerActive");
		NCBlocks.electrolyserIdle = new BlockElectrolyser(false).setBlockName("electrolyserIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electrolyserIdle, "electrolyserIdle");
		NCBlocks.electrolyserActive = new BlockElectrolyser(true).setBlockName("electrolyserActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.electrolyserActive, "electrolyserActive");
		NCBlocks.fissionReactorGraphiteIdle = new BlockFissionReactor(false).setBlockName("fissionReactorGraphiteIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.fissionReactorGraphiteIdle, "fissionReactorGraphiteIdle");
		NCBlocks.fissionReactorGraphiteActive = new BlockFissionReactor(true).setBlockName("fissionReactorGraphiteActive").setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.fissionReactorGraphiteActive, "fissionReactorGraphiteActive");
		NCBlocks.RTG = new BlockRTG().setBlockName("RTG").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.RTG, "RTG");
		NCBlocks.WRTG = new BlockWRTG().setBlockName("WRTG").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.WRTG, "WRTG");
		NCBlocks.solarPanel = new BlockSolarPanel().setBlockName("solarPanel").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(5.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.solarPanel, "solarPanel");
		NCBlocks.collectorIdle = new BlockCollector(false).setBlockName("collectorIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.collectorIdle, "collectorIdle");
		NCBlocks.collectorActive = new BlockCollector(true).setBlockName("collectorActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.collectorActive, "collectorActive");
		NCBlocks.oxidiserIdle = new BlockOxidiser(false).setBlockName("oxidiserIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.oxidiserIdle, "oxidiserIdle");
		NCBlocks.oxidiserActive = new BlockOxidiser(true).setBlockName("oxidiserActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.oxidiserActive, "oxidiserActive");
		NCBlocks.ioniserIdle = new BlockIoniser(false).setBlockName("ioniserIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.ioniserIdle, "ioniserIdle");
		NCBlocks.ioniserActive = new BlockIoniser(true).setBlockName("ioniserActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.ioniserActive, "ioniserActive");
		NCBlocks.irradiatorIdle = new BlockIrradiator(false).setBlockName("irradiatorIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.irradiatorIdle, "irradiatorIdle");
		NCBlocks.irradiatorActive = new BlockIrradiator(true).setBlockName("irradiatorActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.irradiatorActive, "irradiatorActive");
		NCBlocks.coolerIdle = new BlockCooler(false).setBlockName("coolerIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.coolerIdle, "coolerIdle");
		NCBlocks.coolerActive = new BlockCooler(true).setBlockName("coolerActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.coolerActive, "coolerActive");
		NCBlocks.factoryIdle = new BlockFactory(false).setBlockName("factoryIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.factoryIdle, "factoryIdle");
		NCBlocks.factoryActive = new BlockFactory(true).setBlockName("factoryActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.factoryActive, "factoryActive");
		NCBlocks.heliumExtractorIdle = new BlockHeliumExtractor(false).setBlockName("heliumExtractorIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.heliumExtractorIdle, "heliumExtractorIdle");
		NCBlocks.heliumExtractorActive = new BlockHeliumExtractor(true).setBlockName("heliumExtractorActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.heliumExtractorActive, "heliumExtractorActive");
		
		/*NCBlocks.autoWorkspaceIdle = new BlockAutoWorkspace(false).setBlockName("autoWorkspaceIdle").setCreativeTab(tabNC).setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.autoWorkspaceIdle, "autoWorkspaceIdle");
		NCBlocks.autoWorkspaceActive = new BlockAutoWorkspace(true).setBlockName("autoWorkspaceActive").setStepSound(Block.soundTypeMetal).setResistance(20.0F).setHardness(5.0F);
		GameRegistry.registerBlock(NCBlocks.autoWorkspaceActive, "autoWorkspaceActive");*/
		
		NCBlocks.nuke = new BlockNuke().setBlockName("nuke").setCreativeTab(tabNC).setStepSound(Block.soundTypeCloth).setHardness(0.0F);
		GameRegistry.registerBlock(NCBlocks.nuke, "nuke");
		NCBlocks.nukeE = new BlockNukeExploding().setBlockName("nukeE").setStepSound(Block.soundTypeCloth).setHardness(0.0F);
		GameRegistry.registerBlock(NCBlocks.nukeE, "nukeE");
			
			// Tile Entity
		GameRegistry.registerTileEntity(TileNuclearFurnace.class, "nuclearFurnace");
		GameRegistry.registerTileEntity(TileFurnace.class, "furnace");
		GameRegistry.registerTileEntity(TileCrusher.class, "crusher");
		GameRegistry.registerTileEntity(TileElectricCrusher.class, "electricCrusher");
		GameRegistry.registerTileEntity(TileElectricFurnace.class, "electricFurnace");
		GameRegistry.registerTileEntity(TileReactionGenerator.class, "reactionGenerator");
		GameRegistry.registerTileEntity(TileSeparator.class, "separator");
		GameRegistry.registerTileEntity(TileHastener.class, "hastener");
		GameRegistry.registerTileEntity(TileCollector.class, "collector");
		GameRegistry.registerTileEntity(TileElectrolyser.class, "electrolyser");
		GameRegistry.registerTileEntity(TileFissionReactor.class, "fissionReactorGraphite");
		GameRegistry.registerTileEntity(TileNuclearWorkspace.class, "nuclearWorkspace");
		GameRegistry.registerTileEntity(TileFusionReactor.class, "fusionReactor");
		GameRegistry.registerTileEntity(TileTubing1.class, "TEtubing1");
		GameRegistry.registerTileEntity(TileTubing2.class, "TEtubing2");
		GameRegistry.registerTileEntity(TileRTG.class, "RTG");
		GameRegistry.registerTileEntity(TileWRTG.class, "WRTG");
		GameRegistry.registerTileEntity(TileFusionReactorBlock.class, "fusionReactorBlock");
		GameRegistry.registerTileEntity(TileOxidiser.class, "oxidiser");
		GameRegistry.registerTileEntity(TileIoniser.class, "ioniser");
		GameRegistry.registerTileEntity(TileIrradiator.class, "irradiator");
		GameRegistry.registerTileEntity(TileCooler.class, "cooler");
		GameRegistry.registerTileEntity(TileFactory.class, "factory");
		GameRegistry.registerTileEntity(TileHeliumExtractor.class, "heliumExtractor");
		GameRegistry.registerTileEntity(TileSolarPanel.class, "solarPanel");
		GameRegistry.registerTileEntity(TileAutoWorkspace.class, "autoWorkspace");
		
		GameRegistry.registerTileEntity(TileElectromagnet.class, "electromagnet");
		GameRegistry.registerTileEntity(TileSuperElectromagnet.class, "superElectromagnet");
		GameRegistry.registerTileEntity(TileSupercooler.class, "supercooler");
		GameRegistry.registerTileEntity(TileSynchrotron.class, "synchrotron");
		
		GameRegistry.registerTileEntity(TileSimpleQuantum.class, "simpleQuantum");
	
		// Item Registry	
		NCItems.dominoes = new ItemFood(12, 1.4F, false).setCreativeTab(tabNC).setUnlocalizedName("dominoes").setTextureName("nc:food/" + "dominoes");
		GameRegistry.registerItem(NCItems.dominoes, "dominoes");
		NCItems.boiledEgg = new ItemFood(5, 0.6F, false).setCreativeTab(tabNC).setUnlocalizedName("boiledEgg").setTextureName("nc:food/" + "boiledEgg");
		GameRegistry.registerItem(NCItems.boiledEgg, "boiledEgg");
		
		NCItems.ricecake = new ItemFood(4, 0.4F, false).setCreativeTab(tabNC).setUnlocalizedName("ricecake").setTextureName("nc:food/" + "ricecake");
		GameRegistry.registerItem(NCItems.ricecake, "ricecake");
		NCItems.fishAndRicecake = new ItemFood(8, 0.6F, false).setCreativeTab(tabNC).setUnlocalizedName("fishAndRicecake").setTextureName("nc:food/" + "fishAndRicecake");
		GameRegistry.registerItem(NCItems.fishAndRicecake, "fishAndRicecake");
		
		NCItems.upgrade = new Item().setCreativeTab(tabNC).setUnlocalizedName("upgrade").setTextureName("nc:upgrades/" + "upgrade").setMaxStackSize(8);
		GameRegistry.registerItem(NCItems.upgrade, "upgrade");
		NCItems.upgradeSpeed = new Item().setCreativeTab(tabNC).setUnlocalizedName("upgradeSpeed").setTextureName("nc:upgrades/" + "upgradeSpeed").setMaxStackSize(8);
		GameRegistry.registerItem(NCItems.upgradeSpeed, "upgradeSpeed");
		NCItems.upgradeEnergy = new Item().setCreativeTab(tabNC).setUnlocalizedName("upgradeEnergy").setTextureName("nc:upgrades/" + "upgradeEnergy").setMaxStackSize(8);
		GameRegistry.registerItem(NCItems.upgradeEnergy, "upgradeEnergy");
		NCItems.ringUpgrade = new Item().setCreativeTab(tabNC).setUnlocalizedName("ringUpgrade").setTextureName("nc:upgrades/" + "ringUpgrade");
		GameRegistry.registerItem(NCItems.ringUpgrade, "ringUpgrade");
		
		NCItems.tabItem = new Item().setUnlocalizedName("tabItem").setTextureName("nc:fuel/" + "11");
		GameRegistry.registerItem(NCItems.tabItem, "tabItem");
		
		GameRegistry.registerItem(NCItems.fuel = new ItemFuel("fuel"), "fuel");
		GameRegistry.registerItem(NCItems.material = new ItemMaterial("material"), "material");
		GameRegistry.registerItem(NCItems.parts = new ItemPart("parts"), "parts");
		
		NCItems.nuclearGrenade = new ItemNuclearGrenade().setCreativeTab(tabNC).setUnlocalizedName("nuclearGrenade").setTextureName("nc:weapons/" + "nuclearGrenade");
		GameRegistry.registerItem(NCItems.nuclearGrenade, "nuclearGrenade");
		NCItems.nuclearGrenadeThrown = new Item().setUnlocalizedName("nuclearGrenadeThrown").setTextureName("nc:weapons/" + "nuclearGrenadeThrown");
		GameRegistry.registerItem(NCItems.nuclearGrenadeThrown, "nuclearGrenadeThrown");
		
		NCItems.portableEnderChest = new ItemEnderChest().setCreativeTab(tabNC).setUnlocalizedName("portableEnderChest").setTextureName("nc:" + "portableEnderChest").setMaxStackSize(1);
		GameRegistry.registerItem(NCItems.portableEnderChest, "portableEnderChest");
		
		// Tool Registry
		NCItems.bronzePickaxe = new NCPickaxe(Bronze).setCreativeTab(tabNC).setUnlocalizedName("bronzePickaxe").setTextureName("nc:tools/" + "bronzePickaxe");
		GameRegistry.registerItem(NCItems.bronzePickaxe, "bronzePickaxe");
		NCItems.bronzeShovel = new NCShovel(Bronze).setCreativeTab(tabNC).setUnlocalizedName("bronzeShovel").setTextureName("nc:tools/" + "bronzeShovel");
		GameRegistry.registerItem(NCItems.bronzeShovel, "bronzeShovel");
		NCItems.bronzeAxe = new NCAxe(Bronze).setCreativeTab(tabNC).setUnlocalizedName("bronzeAxe").setTextureName("nc:tools/" + "bronzeAxe");
		GameRegistry.registerItem(NCItems.bronzeAxe, "bronzeAxe");
		NCItems.bronzeHoe = new NCHoe(Bronze).setCreativeTab(tabNC).setUnlocalizedName("bronzeHoe").setTextureName("nc:tools/" + "bronzeHoe");
		GameRegistry.registerItem(NCItems.bronzeHoe, "bronzeHoe");
		NCItems.bronzeSword = new NCSword(Bronze).setCreativeTab(tabNC).setUnlocalizedName("bronzeSword").setTextureName("nc:tools/" + "bronzeSword");
		GameRegistry.registerItem(NCItems.bronzeSword, "bronzeSword");
		
		NCItems.boronPickaxe = new NCPickaxe(Boron).setCreativeTab(tabNC).setUnlocalizedName("boronPickaxe").setTextureName("nc:tools/" + "boronPickaxe");
		GameRegistry.registerItem(NCItems.boronPickaxe, "boronPickaxe");
		NCItems.boronShovel = new NCShovel(Boron).setCreativeTab(tabNC).setUnlocalizedName("boronShovel").setTextureName("nc:tools/" + "boronShovel");
		GameRegistry.registerItem(NCItems.boronShovel, "boronShovel");
		NCItems.boronAxe = new NCAxe(Boron).setCreativeTab(tabNC).setUnlocalizedName("boronAxe").setTextureName("nc:tools/" + "boronAxe");
		GameRegistry.registerItem(NCItems.boronAxe, "boronAxe");
		NCItems.boronHoe = new NCHoe(Boron).setCreativeTab(tabNC).setUnlocalizedName("boronHoe").setTextureName("nc:tools/" + "boronHoe");
		GameRegistry.registerItem(NCItems.boronHoe, "boronHoe");
		NCItems.boronSword = new NCSword(Boron).setCreativeTab(tabNC).setUnlocalizedName("boronSword").setTextureName("nc:tools/" + "boronSword");
		GameRegistry.registerItem(NCItems.boronSword, "boronSword");
		
		NCItems.toughAlloyPickaxe = new NCPickaxe(ToughAlloy).setCreativeTab(tabNC).setUnlocalizedName("toughAlloyPickaxe").setTextureName("nc:tools/" + "toughAlloyPickaxe");
		GameRegistry.registerItem(NCItems.toughAlloyPickaxe, "toughAlloyPickaxe");
		NCItems.toughAlloyShovel = new NCShovel(ToughAlloy).setCreativeTab(tabNC).setUnlocalizedName("toughAlloyShovel").setTextureName("nc:tools/" + "toughAlloyShovel");
		GameRegistry.registerItem(NCItems.toughAlloyShovel, "toughAlloyShovel");
		NCItems.toughAlloyAxe = new NCAxe(ToughAlloy).setCreativeTab(tabNC).setUnlocalizedName("toughAlloyAxe").setTextureName("nc:tools/" + "toughAlloyAxe");
		GameRegistry.registerItem(NCItems.toughAlloyAxe, "toughAlloyAxe");
		NCItems.toughAlloyHoe = new NCHoe(ToughAlloy).setCreativeTab(tabNC).setUnlocalizedName("toughAlloyHoe").setTextureName("nc:tools/" + "toughAlloyHoe");
		GameRegistry.registerItem(NCItems.toughAlloyHoe, "toughAlloyHoe");
		NCItems.toughAlloySword = new NCSword(ToughAlloy).setCreativeTab(tabNC).setUnlocalizedName("toughAlloySword").setTextureName("nc:tools/" + "toughAlloySword");
		GameRegistry.registerItem(NCItems.toughAlloySword, "toughAlloySword");
		NCItems.toughAlloyPaxel = new NCPaxel(ToughPaxel).setCreativeTab(tabNC).setUnlocalizedName("toughAlloyPaxel").setTextureName("nc:tools/" + "toughAlloyPaxel");
		GameRegistry.registerItem(NCItems.toughAlloyPaxel, "toughAlloyPaxel");
		
		NCItems.dUPickaxe = new NCPickaxe(dU).setCreativeTab(tabNC).setUnlocalizedName("dUPickaxe").setTextureName("nc:tools/" + "dUPickaxe");
		GameRegistry.registerItem(NCItems.dUPickaxe, "dUPickaxe");
		NCItems.dUShovel = new NCShovel(dU).setCreativeTab(tabNC).setUnlocalizedName("dUShovel").setTextureName("nc:tools/" + "dUShovel");
		GameRegistry.registerItem(NCItems.dUShovel, "dUShovel");
		NCItems.dUAxe = new NCAxe(dU).setCreativeTab(tabNC).setUnlocalizedName("dUAxe").setTextureName("nc:tools/" + "dUAxe");
		GameRegistry.registerItem(NCItems.dUAxe, "dUAxe");
		NCItems.dUHoe = new NCHoe(dU).setCreativeTab(tabNC).setUnlocalizedName("dUHoe").setTextureName("nc:tools/" + "dUHoe");
		GameRegistry.registerItem(NCItems.dUHoe, "dUHoe");
		NCItems.dUSword = new NCSword(dU).setCreativeTab(tabNC).setUnlocalizedName("dUSword").setTextureName("nc:tools/" + "dUSword");
		GameRegistry.registerItem(NCItems.dUSword, "dUSword");
		NCItems.dUPaxel = new NCPaxel(dUPaxel).setCreativeTab(tabNC).setUnlocalizedName("dUPaxel").setTextureName("nc:tools/" + "dUPaxel");
		GameRegistry.registerItem(NCItems.dUPaxel, "dUPaxel");
		
		NCItems.toughBow = new ItemToughBow().setCreativeTab(tabNC).setUnlocalizedName("toughBow").setMaxStackSize(1);
		GameRegistry.registerItem(NCItems.toughBow, "toughBow");
		NCItems.pistol = new ItemPistol().setCreativeTab(tabNC).setUnlocalizedName("pistol").setMaxStackSize(1).setTextureName("nc:tools/" + "pistol");
		GameRegistry.registerItem(NCItems.pistol, "pistol");
		NCItems.dUBullet = new Item().setCreativeTab(tabNC).setUnlocalizedName("dUBullet").setTextureName("nc:tools/" + "dUBullet");
		GameRegistry.registerItem(NCItems.dUBullet, "dUBullet");
		
		//Armor Registry
		NCItems.toughHelm = new ToughArmour(ToughArmorMaterial, toughHelmID, 0).setUnlocalizedName("toughHelm").setTextureName("nc:armour/" + "toughHelm");
		GameRegistry.registerItem(NCItems.toughHelm, "toughHelm");
		NCItems.toughChest = new ToughArmour(ToughArmorMaterial, toughChestID, 1).setUnlocalizedName("toughChest").setTextureName("nc:armour/" + "toughChest");
		GameRegistry.registerItem(NCItems.toughChest, "toughChest");
		NCItems.toughLegs = new ToughArmour(ToughArmorMaterial, toughLegsID, 2).setUnlocalizedName("toughLegs").setTextureName("nc:armour/" + "toughLegs");
		GameRegistry.registerItem(NCItems.toughLegs, "toughLegs");
		NCItems.toughBoots = new ToughArmour(ToughArmorMaterial, toughBootsID, 3).setUnlocalizedName("toughBoots").setTextureName("nc:armour/" + "toughBoots");
		GameRegistry.registerItem(NCItems.toughBoots, "toughBoots");
		
		NCItems.boronHelm = new BoronArmour(BoronArmorMaterial, boronHelmID, 0).setUnlocalizedName("boronHelm").setTextureName("nc:armour/" + "boronHelm");
		GameRegistry.registerItem(NCItems.boronHelm, "boronHelm");
		NCItems.boronChest = new BoronArmour(BoronArmorMaterial, boronChestID, 1).setUnlocalizedName("boronChest").setTextureName("nc:armour/" + "boronChest");
		GameRegistry.registerItem(NCItems.boronChest, "boronChest");
		NCItems.boronLegs = new BoronArmour(BoronArmorMaterial, boronLegsID, 2).setUnlocalizedName("boronLegs").setTextureName("nc:armour/" + "boronLegs");
		GameRegistry.registerItem(NCItems.boronLegs, "boronLegs");
		NCItems.boronBoots = new BoronArmour(BoronArmorMaterial, boronBootsID, 3).setUnlocalizedName("boronBoots").setTextureName("nc:armour/" + "boronBoots");
		GameRegistry.registerItem(NCItems.boronBoots, "boronBoots");
		
		NCItems.bronzeHelm = new BronzeArmour(BronzeArmorMaterial, bronzeHelmID, 0).setUnlocalizedName("bronzeHelm").setTextureName("nc:armour/" + "bronzeHelm");
		GameRegistry.registerItem(NCItems.bronzeHelm, "bronzeHelm");
		NCItems.bronzeChest = new BronzeArmour(BronzeArmorMaterial, bronzeChestID, 1).setUnlocalizedName("bronzeChest").setTextureName("nc:armour/" + "bronzeChest");
		GameRegistry.registerItem(NCItems.bronzeChest, "bronzeChest");
		NCItems.bronzeLegs = new BronzeArmour(BronzeArmorMaterial, bronzeLegsID, 2).setUnlocalizedName("bronzeLegs").setTextureName("nc:armour/" + "bronzeLegs");
		GameRegistry.registerItem(NCItems.bronzeLegs, "bronzeLegs");
		NCItems.bronzeBoots = new BronzeArmour(BronzeArmorMaterial, bronzeBootsID, 3).setUnlocalizedName("bronzeBoots").setTextureName("nc:armour/" + "bronzeBoots");
		GameRegistry.registerItem(NCItems.bronzeBoots, "bronzeBoots");
		
		NCItems.dUHelm = new DUArmour(dUArmorMaterial, dUHelmID, 0).setUnlocalizedName("dUHelm").setTextureName("nc:armour/" + "dUHelm");
		GameRegistry.registerItem(NCItems.dUHelm, "dUHelm");
		NCItems.dUChest = new DUArmour(dUArmorMaterial, dUChestID, 1).setUnlocalizedName("dUChest").setTextureName("nc:armour/" + "dUChest");
		GameRegistry.registerItem(NCItems.dUChest, "dUChest");
		NCItems.dULegs = new DUArmour(dUArmorMaterial, dULegsID, 2).setUnlocalizedName("dULegs").setTextureName("nc:armour/" + "dULegs");
		GameRegistry.registerItem(NCItems.dULegs, "dULegs");
		NCItems.dUBoots = new DUArmour(dUArmorMaterial, dUBootsID, 3).setUnlocalizedName("dUBoots").setTextureName("nc:armour/" + "dUBoots");
		GameRegistry.registerItem(NCItems.dUBoots, "dUBoots");
		
		//Records
		NCItems.recordPractice = new NCRecord(0, "Practice").setCreativeTab(tabNC).setUnlocalizedName("recordPractice").setTextureName("nc:record/" + "recordPractice");
		GameRegistry.registerItem(NCItems.recordPractice, "recordPractice");
		NCItems.recordArea51 = new NCRecord(0, "Area51").setCreativeTab(tabNC).setUnlocalizedName("recordArea51").setTextureName("nc:record/" + "recordArea51");
		GameRegistry.registerItem(NCItems.recordArea51, "recordArea51");
		NCItems.recordNeighborhood = new NCRecord(0, "Neighborhood").setCreativeTab(tabNC).setUnlocalizedName("recordNeighborhood").setTextureName("nc:record/" + "recordNeighborhood");
		GameRegistry.registerItem(NCItems.recordNeighborhood, "recordNeighborhood");
		
		// Block Crafting Recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 4), true, new Object[] {"XXX", "XXX", "XXX", 'X', "ingotUranium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 0), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotCopper"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 1), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotTin"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 2), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotLead"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 3), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotSilver"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 6), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotBronze"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 5), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotThorium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 8), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotLithium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 9), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotBoron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCBlocks.blockBlock, 1, 10), true, new Object[] {"XXX", "XXX", "XXX", 'X',  "ingotMagnesium"}));
		
		// Tiny Dust to Full Dust
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 17), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 23)}));
		
		// Isotope Lump Recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 28), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 29)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 26), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 27)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 24), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 25)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 30), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 31)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 32), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 33)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 34), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 35)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 36), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 37)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 38), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 39)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 40), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 41)}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 59), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 60)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 57), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 58)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 55), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 56)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 61), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 62)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 63), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 64)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 65), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 66)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 67), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 68)}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 46), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 69)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (NCItems.material, 1, 48), true, new Object[] {"XXX", "XXX", "XXX", 'X', new ItemStack (NCItems.material, 1, 70)}));
		
		// Shaped Crafting Recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.fuel, 16, 33), true, new Object[] {" I ", "I I", " I ", 'I', "plateIron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.fuel, 16, 45), true, new Object[] {" I ", "I I", " I ", 'I', "plateTin"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 2, 0), true, new Object[] {"LLL", "CCC", 'L', "ingotLead", 'C', "dustCoal"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 1, 2), true, new Object[] {"FFF", "CCC", "SSS", 'F', Items.flint, 'C', "cobblestone", 'S', Items.stick}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 12, 1), true, new Object[] {"III", "IBI", "III", 'I', "ingotIron", 'B', "blockIron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 12, 6), true, new Object[] {"III", "IBI", "III", 'I', "ingotTin", 'B', "blockTin"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.nuclearFurnaceIdle, true, new Object[] {"XPX", "P P", "XPX", 'P', "plateBasic", 'X', "dustObsidian"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.furnaceIdle, true, new Object[] {"PPP", "P P", "PPP", 'P', "plateIron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.crusherIdle, true, new Object[] {"PPP", "PCP", "PPP", 'P', "plateIron", 'C', new ItemStack(NCItems.parts, 1, 2)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.electricCrusherIdle, true, new Object[] {"PRP", "RCR", "PRP", 'P', "plateIron", 'R', Items.redstone, 'C', NCBlocks.crusherIdle}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.electricFurnaceIdle, true, new Object[] {"PRP", "RCR", "PRP", 'P', "plateIron", 'R', Items.redstone, 'C', NCBlocks.furnaceIdle}));
		
		if (workspace) GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.nuclearWorkspace, true, new Object[] {"NNN", " T ", "TTT", 'N', "plateBasic", 'T', "ingotTough"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.graphiteBlock, true, new Object[] {"CDC", "DCD", "CDC", 'D', "dustCoal", 'C', Items.coal}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.upgrade, true, new Object[] {"PPP", "PCP", "PPP", 'P', Items.redstone, 'C', new ItemStack(NCItems.parts, 1, 3)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.upgradeSpeed, true, new Object[] {"PPP", "PCP", "PPP", 'P', "dustLapis", 'C', "plateIron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.upgradeEnergy, true, new Object[] {"PPP", "PCP", "PPP", 'P', "universalReactant", 'C', "plateIron"}));
	
		// Tool Crafting Recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzePickaxe, true, new Object[] {"XXX", " S ", " S ", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeShovel, true, new Object[] {"X", "S", "S", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeAxe, true, new Object[] {"XX", "XS", " S", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeAxe, true, new Object[] {"XX", "SX", "S ", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeHoe, true, new Object[] {"XX", "S ", "S ", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeHoe, true, new Object[] {"XX", " S", " S", 'X', "ingotBronze", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.bronzeSword, true, new Object[] {"X", "X", "S", 'X', "ingotBronze", 'S', Items.stick}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronPickaxe, true, new Object[] {"XXX", " S ", " S ", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronShovel, true, new Object[] {"X", "S", "S", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronAxe, true, new Object[] {"XX", "XS", " S", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronAxe, true, new Object[] {"XX", "SX", "S ", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronHoe, true, new Object[] {"XX", "S ", "S ", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronHoe, true, new Object[] {"XX", " S", " S", 'X', "ingotBoron", 'S', Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.boronSword, true, new Object[] {"X", "X", "S", 'X', "ingotBoron", 'S', Items.stick}));
		
		// Armour Crafting Recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.boronHelm, 1), true, new Object[] {"XXX", "X X", 'X', "ingotBoron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.boronChest, 1), true, new Object[] {"X X", "XXX", "XXX", 'X', "ingotBoron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.boronLegs, 1), true, new Object[] {"XXX", "X X", "X X", 'X', "ingotBoron"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.boronBoots, 1), true, new Object[] {"X X", "X X", 'X', "ingotBoron"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.bronzeHelm, 1), true, new Object[] {"XXX", "X X", 'X', "ingotBronze"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.bronzeChest, 1), true, new Object[] {"X X", "XXX", "XXX", 'X', "ingotBronze"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.bronzeLegs, 1), true, new Object[] {"XXX", "X X", "X X", 'X', "ingotBronze"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.bronzeBoots, 1), true, new Object[] {"X X", "X X", 'X', "ingotBronze"}));
	
		// Simple Shapeless Crafting Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 4), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 0), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 1), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 2), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 2)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 3), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 6), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 6)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 5), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 42), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 8)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 43), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 9)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 25, 7), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.material, 9, 50), new Object[] {new ItemStack(NCBlocks.blockBlock, 1, 10)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(NCBlocks.tubing1, 1), new Object[] {new ItemStack(NCBlocks.tubing2)});
		GameRegistry.addShapelessRecipe(new ItemStack(NCBlocks.tubing2, 1), new Object[] {new ItemStack(NCBlocks.tubing1)});
		
		// Complex Shapeless Crafting Recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.dominoes, 4), new Object[] {Items.cooked_beef, Items.cooked_porkchop, Items.cooked_chicken, Blocks.brown_mushroom, Blocks.brown_mushroom, Items.bread, Items.bread}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 4, 6), new Object[] {"ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 4, 21), new Object[] {"dustCopper", "dustCopper", "dustCopper", "dustTin"}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 3, 71), new Object[] {"ingotMagnesium", "ingotBoron", "ingotBoron"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 3, 72), new Object[] {"dustMagnesium", "dustBoron", "dustBoron"}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 0), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 26)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 6), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 28)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 1), new Object[] {"U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 26), new ItemStack(NCItems.material, 1, 26), new ItemStack(NCItems.material, 1, 26), new ItemStack(NCItems.material, 1, 26)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 7), new Object[] {"U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 28), new ItemStack(NCItems.material, 1, 28), new ItemStack(NCItems.material, 1, 28), new ItemStack(NCItems.material, 1, 28)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 2), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 32)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 8), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 36)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 3), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 32), new ItemStack(NCItems.material, 1, 32), new ItemStack(NCItems.material, 1, 32), new ItemStack(NCItems.material, 1, 32)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 9), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 36), new ItemStack(NCItems.material, 1, 36), new ItemStack(NCItems.material, 1, 36), new ItemStack(NCItems.material, 1, 36)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 5), new Object[] {new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38), new ItemStack(NCItems.material, 1, 38)}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 51), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 57)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 55), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 59)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 52), new Object[] {"U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 57), new ItemStack(NCItems.material, 1, 57), new ItemStack(NCItems.material, 1, 57), new ItemStack(NCItems.material, 1, 57)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 56), new Object[] {"U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 59), new ItemStack(NCItems.material, 1, 59), new ItemStack(NCItems.material, 1, 59), new ItemStack(NCItems.material, 1, 59)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 53), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 63)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 57), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 67)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 54), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 63), new ItemStack(NCItems.material, 1, 63), new ItemStack(NCItems.material, 1, 63), new ItemStack(NCItems.material, 1, 63)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 58), new Object[] {"Pu242", "Pu242", "Pu242", "Pu242", "Pu242", new ItemStack(NCItems.material, 1, 67), new ItemStack(NCItems.material, 1, 67), new ItemStack(NCItems.material, 1, 67), new ItemStack(NCItems.material, 1, 67)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 4), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 63)}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 10), new Object[] {"U238", "U238", "U238", "U238", "U238", "U238", "U238", "U238", new ItemStack(NCItems.material, 1, 67)}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.parts, 3, 4), new Object[] {Items.sugar, "dustLapis", Items.redstone}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fishAndRicecake, 1), new Object[] {Items.cooked_fished, NCItems.ricecake}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.recordPractice, 1), new Object[] {"record", "ingotBoron"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.recordArea51, 1), new Object[] {"record", "ingotTough"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.recordNeighborhood, 1), new Object[] {"record", "universalReactant"}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 4, 22), new Object[] {new ItemStack(NCItems.parts, 1, 4), "dustCoal", "dustCoal", "dustLead", "dustLead", "dustSilver", "dustSilver", "dustIron", "dustIron"}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.material, 4, 7), new Object[] {new ItemStack(NCItems.parts, 1, 4), "dustCoal", "dustCoal", "ingotLead", "ingotLead", "ingotSilver", "ingotSilver", "ingotIron", "ingotIron"}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 45), new Object[] {"filledNCGasCell"}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 11), (new ItemStack (NCItems.fuel, 1, 0)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 17), (new ItemStack (NCItems.fuel, 1, 6)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 12), (new ItemStack (NCItems.fuel, 1, 1)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 18), (new ItemStack (NCItems.fuel, 1, 7)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 13), (new ItemStack (NCItems.fuel, 1, 2)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 19), (new ItemStack (NCItems.fuel, 1, 8)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 14), (new ItemStack (NCItems.fuel, 1, 3)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 20), (new ItemStack (NCItems.fuel, 1, 9)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 15), (new ItemStack (NCItems.fuel, 1, 4)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 21), (new ItemStack (NCItems.fuel, 1, 10)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 16), (new ItemStack (NCItems.fuel, 1, 5)), (new ItemStack (NCItems.fuel, 1, 33)));
		
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 59), (new ItemStack (NCItems.fuel, 1, 51)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 63), (new ItemStack (NCItems.fuel, 1, 55)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 60), (new ItemStack (NCItems.fuel, 1, 52)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 64), (new ItemStack (NCItems.fuel, 1, 56)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 61), (new ItemStack (NCItems.fuel, 1, 53)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 65), (new ItemStack (NCItems.fuel, 1, 57)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 62), (new ItemStack (NCItems.fuel, 1, 54)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 66), (new ItemStack (NCItems.fuel, 1, 58)), (new ItemStack (NCItems.fuel, 1, 33)));
		
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 41), (new ItemStack (NCItems.material, 1, 46)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 42), (new ItemStack (NCItems.material, 1, 47)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 43), (new ItemStack (NCItems.material, 1, 48)), (new ItemStack (NCItems.fuel, 1, 33)));
		GameRegistry.addShapelessRecipe(new ItemStack(NCItems.fuel, 1, 44), (new ItemStack (NCItems.material, 1, 49)), (new ItemStack (NCItems.fuel, 1, 33)));
		
		// Workspace Recipes
		if (!workspace) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(NCBlocks.machineBlock, new Object[] {"plateBasic", "plateLead", "plateLead", new ItemStack(NCItems.parts, 1, 10), new ItemStack(NCItems.parts, 1, 11), new ItemStack(NCItems.parts, 1, 12), new ItemStack(NCItems.parts, 1, 13), new ItemStack(NCItems.parts, 1, 16), "dustRedstone"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCBlocks.reactorBlock, 8), true, new Object[] {"ABA", "B B", "ABA", 'A', "ingotTough", 'B', "plateBasic"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCBlocks.cellBlock, 2), true, new Object[] {"ABA", "CDC", "ABA", 'A', "blockGlass", 'B', "plateBasic", 'C', "ingotTough", 'D', "plateLead"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCBlocks.coolerBlock, 4), true, new Object[] {"ABA", "BCB", "ABA", 'A', "universalReactant", 'B', "plateBasic", 'C', "dustRedstone"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCBlocks.speedBlock, 4), true, new Object[] {"ABA", "BCB", "ABA", 'A', Items.blaze_powder, 'B', "plateBasic", 'C', "dustRedstone"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.fissionReactorGraphiteIdle, true, new Object[] {"AAA", "ABA", "AAA", 'A', "plateReinforced", 'B', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(NCBlocks.blastBlock, new Object[] {NCBlocks.reactorBlock, "oreObsidian"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 1, 9), true, new Object[] {"AAA", "BCB", "AAA", 'A', new ItemStack(NCItems.material, 1, 48), 'B', "plateDU", 'C', "dustDiamond"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.separatorIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateLead", 'B', "ingotTough", 'C', "dustRedstone", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.hastenerIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateLead", 'B', "universalReactant", 'C', "ingotTough", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.collectorIdle, true, new Object[] {"ABA", "BBB", "ABA", 'A', "plateBasic", 'B', new ItemStack(NCItems.material, 1, 40)}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.reactionGeneratorIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateLead", 'B', new ItemStack(NCItems.parts, 1, 5), 'C', "plateBasic", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.electrolyserIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateReinforced", 'B', new ItemStack(NCItems.parts, 1, 7), 'C', "universalReactant", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.oxidiserIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateDU", 'B', "universalReactant", 'C', "plateLead", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.ioniserIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateDU", 'B', "dustRedstone", 'C', "plateLead", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.irradiatorIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateDU", 'B', "universalReactant", 'C', "ingotTough", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.coolerIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateDU", 'B', "universalReactant", 'C', "plateBasic", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.factoryIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "ingotTough", 'B', "plateBasic", 'C', "plateIron", 'D', Blocks.piston}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.heliumExtractorIdle, true, new Object[] {"ABA", "CDC", "ABA", 'A', "plateReinforced", 'B', new ItemStack(NCItems.parts, 1, 5), 'C', "plateTin", 'D', NCBlocks.machineBlock}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.electromagnetIdle, true, new Object[] {"AAA", "BCB", "AAA", 'A', "plateReinforced", 'B', new ItemStack(NCItems.parts, 1, 12), 'C', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.fusionReactor, true, new Object[] {"ABA", "BCB", "ABA", 'A', NCBlocks.reactionGeneratorIdle, 'B', "plateAdvanced", 'C', NCBlocks.electromagnetIdle}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.superElectromagnetIdle, true, new Object[] {"AAA", "BCB", "AAA", 'A', "plateAdvanced", 'B', new ItemStack(NCItems.parts, 1, 17), 'C', "ingotTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.supercoolerIdle, true, new Object[] {"AAA", "BCB", "AAA", 'A', "plateAdvanced", 'B', new ItemStack(NCItems.parts, 1, 13), 'C', "universalReactant"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyPickaxe, true, new Object[] {"XXX", " S ", " S ", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyShovel, true, new Object[] {"X", "S", "S", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyAxe, true, new Object[] {"XX", "XS", " S", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyAxe, true, new Object[] {"XX", "SX", "S ", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyHoe, true, new Object[] {"XX", "S ", "S ", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyHoe, true, new Object[] {"XX", " S", " S", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloySword, true, new Object[] {"X", "X", "S", 'X', "ingotTough", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughAlloyPaxel, true, new Object[] {"ASP", "HIW", " I ", 'I', "ingotIron", 'A', NCItems.toughAlloyAxe, 'S', NCItems.toughAlloyShovel, 'P', NCItems.toughAlloyPickaxe, 'H', NCItems.toughAlloyHoe, 'W', NCItems.toughAlloySword}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUPickaxe, true, new Object[] {"XXX", " S ", " S ", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUShovel, true, new Object[] {"X", "S", "S", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUAxe, true, new Object[] {"XX", "XS", " S", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUAxe, true, new Object[] {"XX", "SX", "S ", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUHoe, true, new Object[] {"XX", "S ", "S ", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUHoe, true, new Object[] {"XX", " S", " S", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUSword, true, new Object[] {"X", "X", "S", 'X', "plateDU", 'S', "ingotIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUPaxel, true, new Object[] {"ASP", "HIW", " I ", 'I', "ingotIron", 'A', NCItems.dUAxe, 'S', NCItems.dUShovel, 'P', NCItems.dUPickaxe, 'H', NCItems.dUHoe, 'W', NCItems.dUSword}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.toughHelm, 1), true, new Object[] {"XXX", "X X", 'X', "ingotTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.toughChest, 1), true, new Object[] {"X X", "XXX", "XXX", 'X', "ingotTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.toughLegs, 1), true, new Object[] {"XXX", "X X", "X X", 'X', "ingotTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.toughBoots, 1), true, new Object[] {"X X", "X X", 'X', "ingotTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.dUHelm, 1), true, new Object[] {"XXX", "X X", 'X', "plateDU"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.dUChest, 1), true, new Object[] {"X X", "XXX", "XXX", 'X', "plateDU"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.dULegs, 1), true, new Object[] {"XXX", "X X", "X X", 'X', "plateDU"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.dUBoots, 1), true, new Object[] {"X X", "X X", 'X', "plateDU"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.RTG, true, new Object[] {"ABA", "BCB", "ABA", 'A', new ItemStack(NCItems.parts, 1, 11), 'B', new ItemStack(NCItems.parts, 1, 15), 'C', new ItemStack(NCItems.fuel, 1, 46)}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.WRTG, true, new Object[] {"ABA", "BBB", "ABA", 'A', "plateLead", 'B', "U238"}));
			if (enableNukes) {
				GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.nuke, true, new Object[] {"ABA", "BBB", "ABA", 'A', "plateReinforced", 'B', new ItemStack(NCItems.material, 1, 67)}));
				GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.nuclearGrenade, true, new Object[] {"  S", " S ", "N  ", 'S', Items.string, 'N', NCBlocks.nuke}));
			}
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.solarPanel, true, new Object[] {"DDD", "ECE", "ABA", 'A', new ItemStack(NCItems.parts, 1, 12), 'B', Blocks.iron_block, 'C', "dustCoal", 'D', new ItemStack(NCItems.parts, 1, 15), 'E', "universalReactant"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.portableEnderChest, true, new Object[] {"ABA", "CDC", "AAA", 'A', Blocks.wool, 'B', Items.string, 'C', "plateLead", 'D', Items.ender_eye}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.pistol, true, new Object[] {"AAA", "BBA", "CBA", 'A', "plateReinforced", 'B', "ingotTough", 'C', "plateAdvanced"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 2, 5), true, new Object[] {"ABA", "B B", "ABA", 'A', "universalReactant", 'B', "plateBasic"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 1, 7), true, new Object[] {"ABA", "B B", "ABA", 'A', "plateTin", 'B', new ItemStack(NCItems.fuel, 1, 34)}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 1, 3), true, new Object[] {" A ", "ABA", " A ", 'A', "ingotTough", 'B', "plateBasic"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 1, 8), true, new Object[] {"AAA", "BBB", "AAA", 'A', "U238", 'B', "plateReinforced"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.tubing1, true, new Object[] {"AAA", "BBB", "AAA", 'A', "plateLead", 'B', "plateIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCBlocks.tubing2, true, new Object[] {"ABA", "ABA", "ABA", 'A', "plateLead", 'B', "plateIron"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughBow, true, new Object[] {"BA ", "B A", "BA ", 'A', "ingotTough", 'B', Items.string}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.toughBow, true, new Object[] {" AB", "A B", " AB", 'A', "ingotTough", 'B', Items.string}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.parts, 12, 0), true, new Object[] {"AAA", "BBB", 'A', "ingotTough", 'B', "dustTough"}));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(NCItems.fuel, 8, 48), true, new Object[] {"ABA", "BCB", "ABA", 'B', new ItemStack(NCItems.parts, 1, 15), 'C', "ingotTough", 'A', new ItemStack (NCItems.parts, 1, 3)}));
			GameRegistry.addRecipe(new ShapedOreRecipe(NCItems.dUBullet, true, new Object[] {"ABC", 'A', "U238", 'B', Items.gunpowder, 'C', "ingotTough"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(NCItems.fuel, 1, 46), new Object[] {new ItemStack(NCItems.fuel, 1, 48), "Pu238"}));
		}
		
		// Smelting Recipes
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 4), new ItemStack (NCItems.material, 1, 4), 1.2F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 0), new ItemStack(NCItems.material, 1, 0), 0.6F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 1), new ItemStack(NCItems.material, 1, 1), 0.6F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 2), new ItemStack(NCItems.material, 1, 2), 0.8F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 3), new ItemStack(NCItems.material, 1, 3), 0.8F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 5), new ItemStack(NCItems.material, 1, 5), 1.2F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 6), new ItemStack(NCItems.material, 1, 33), 1.2F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 7), new ItemStack(NCItems.material, 1, 42), 0.8F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 8), new ItemStack(NCItems.material, 1, 43), 0.8F);
		GameRegistry.addSmelting(new ItemStack(NCBlocks.blockOre, 1, 9), new ItemStack(NCItems.material, 1, 50), 0.8F);
		
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 8), new ItemStack(Items.iron_ingot), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 9), new ItemStack(Items.gold_ingot), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 15), new ItemStack(NCItems.material, 1, 0), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 17), new ItemStack(NCItems.material, 1, 2), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 16), new ItemStack(NCItems.material, 1, 1), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 18), new ItemStack(NCItems.material, 1, 3), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 19), new ItemStack(NCItems.material, 1, 4), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 20), new ItemStack(NCItems.material, 1, 5), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 21), new ItemStack(NCItems.material, 1, 6), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 22), new ItemStack(NCItems.material, 1, 7), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 44), new ItemStack(NCItems.material, 1, 42), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 45), new ItemStack(NCItems.material, 1, 43), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 51), new ItemStack(NCItems.material, 1, 50), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 54), new ItemStack(NCItems.material, 1, 53), 0.0F);
		GameRegistry.addSmelting(new ItemStack (NCItems.material, 1, 72), new ItemStack(NCItems.material, 1, 71), 0.0F);
		
		GameRegistry.addSmelting(new ItemStack (Items.egg, 1), new ItemStack(NCItems.boiledEgg, 1), 0.1F);
		
		// Gui Handler
		@SuppressWarnings("unused")
		GuiHandler guiHandler = new GuiHandler();
		
		// Proxy
		NCProxy.registerRenderThings();
		NCProxy.registerSounds();
		NCProxy.registerTileEntitySpecialRenderer();
		
		// Entities
		EntityHandler.registerMonsters(EntityNuclearMonster.class, "NuclearMonster");
		EntityHandler.registerPaul(EntityPaul.class, "Paul");
		EntityHandler.registerNuke(EntityNukePrimed.class, "NukePrimed");
		EntityHandler.registerNuclearGrenade(EntityNuclearGrenade.class, "NuclearGrenade");
		EntityHandler.registerEntityBullet(EntityBullet.class, "EntityBullet");
				
		// Fuel Handler	
		GameRegistry.registerFuelHandler(new FuelHandler());
			
		// Random Chest Loot
		if (enableLoot) {
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.pistol, 1), 1, 1, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dUBullet, 1), 6, 8, 50/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.pistol, 1), 1, 1, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dUBullet, 1), 6, 8, 50/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughBow, 1), 1, 1, 200/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.pistol, 1), 1, 1, 100/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dUBullet, 1), 6, 8, 200/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.pistol, 1), 1, 1, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dUBullet, 1), 6, 8, 50/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 4, 5, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.upgrade, 1), 2, 3, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 4), 1, 2, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.WRTG, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 32), 2, 5, 40/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.material, 1, 59), 3, 4, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.fuel, 1, 47), 1, 2, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordPractice, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordArea51, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.recordNeighborhood, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCBlocks.blastBlock, 1), 6, 12, 50/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughHelm, 1), 1, 1, 80/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughChest, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughLegs, 1), 1, 1, 60/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughBoots, 1), 1, 1, 80/NuclearCraft.lootModifier));
			
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.dominoes, 1), 2, 4, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.ricecake, 1), 2, 4, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boiledEgg, 1), 3, 5, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronHelm, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronChest, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronLegs, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.boronBoots, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.bronzeHelm, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.bronzeChest, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.bronzeLegs, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.bronzeBoots, 1), 1, 1, 20/NuclearCraft.lootModifier));
			ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(NCItems.toughAlloyShovel, 1), 1, 1, 20/NuclearCraft.lootModifier));
		}
			
		// World Generation Registry
		GameRegistry.registerWorldGenerator(new OreGen(), 1);
		
		// Inter Mod Comms - Mekanism
		NBTTagCompound copperOreEnrichment = new NBTTagCompound();
		copperOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 0).writeToNBT(new NBTTagCompound()));
		copperOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 15).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", copperOreEnrichment);
		
		NBTTagCompound tinOreEnrichment = new NBTTagCompound();
		tinOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 1).writeToNBT(new NBTTagCompound()));
		tinOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 16).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", tinOreEnrichment);
		
		NBTTagCompound leadOreEnrichment = new NBTTagCompound();
		leadOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 2).writeToNBT(new NBTTagCompound()));
		leadOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 17).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", leadOreEnrichment);
		
		NBTTagCompound silverOreEnrichment = new NBTTagCompound();
		silverOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 3).writeToNBT(new NBTTagCompound()));
		silverOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 18).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", silverOreEnrichment);
		
		NBTTagCompound uraniumOreEnrichment = new NBTTagCompound();
		uraniumOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 4).writeToNBT(new NBTTagCompound()));
		uraniumOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 19).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", uraniumOreEnrichment);
		
		NBTTagCompound thoriumOreEnrichment = new NBTTagCompound();
		thoriumOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 5).writeToNBT(new NBTTagCompound()));
		thoriumOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 20).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", thoriumOreEnrichment);
		
		NBTTagCompound plutoniumOreEnrichment = new NBTTagCompound();
		plutoniumOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 6).writeToNBT(new NBTTagCompound()));
		plutoniumOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 33).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", plutoniumOreEnrichment);
		
		NBTTagCompound lithiumOreEnrichment = new NBTTagCompound();
		lithiumOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 7).writeToNBT(new NBTTagCompound()));
		lithiumOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 44).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", lithiumOreEnrichment);
		
		NBTTagCompound boronOreEnrichment = new NBTTagCompound();
		boronOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 8).writeToNBT(new NBTTagCompound()));
		boronOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 45).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", boronOreEnrichment);
		
		NBTTagCompound magnesiumOreEnrichment = new NBTTagCompound();
		magnesiumOreEnrichment.setTag("input", new ItemStack(NCBlocks.blockOre, 1, 9).writeToNBT(new NBTTagCompound()));
		magnesiumOreEnrichment.setTag("output", new ItemStack(NCItems.material, 2, 51).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", magnesiumOreEnrichment);
		
		NBTTagCompound basicPlatingEnrichment = new NBTTagCompound();
		basicPlatingEnrichment.setTag("input", new ItemStack(NCItems.parts, 6, 0).writeToNBT(new NBTTagCompound()));
		basicPlatingEnrichment.setTag("output", new ItemStack(NCItems.parts, 1, 3).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", basicPlatingEnrichment);
		
		NBTTagCompound ingotToPlatingEnrichment = new NBTTagCompound();
		ingotToPlatingEnrichment.setTag("input", new ItemStack(NCItems.material, 1, 7).writeToNBT(new NBTTagCompound()));
		ingotToPlatingEnrichment.setTag("output", new ItemStack(NCItems.parts, 2, 0).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "EnrichmentChamberRecipe", ingotToPlatingEnrichment);
		
		NBTTagCompound uraniumIngotCrushing = new NBTTagCompound();
		uraniumIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 4).writeToNBT(new NBTTagCompound()));
		uraniumIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 19).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", uraniumIngotCrushing);
		
		NBTTagCompound thoriumIngotCrushing = new NBTTagCompound();
		thoriumIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 5).writeToNBT(new NBTTagCompound()));
		thoriumIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 20).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", thoriumIngotCrushing);
		
		NBTTagCompound bronzeIngotCrushing = new NBTTagCompound();
		bronzeIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 6).writeToNBT(new NBTTagCompound()));
		bronzeIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 21).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", bronzeIngotCrushing);
		
		NBTTagCompound toughIngotCrushing = new NBTTagCompound();
		toughIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 7).writeToNBT(new NBTTagCompound()));
		toughIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 22).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", toughIngotCrushing);
		
		NBTTagCompound lithiumIngotCrushing = new NBTTagCompound();
		lithiumIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 42).writeToNBT(new NBTTagCompound()));
		lithiumIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 44).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", lithiumIngotCrushing);
		
		NBTTagCompound boronIngotCrushing = new NBTTagCompound();
		boronIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 43).writeToNBT(new NBTTagCompound()));
		boronIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 45).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", boronIngotCrushing);
		
		NBTTagCompound magnesiumIngotCrushing = new NBTTagCompound();
		magnesiumIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 50).writeToNBT(new NBTTagCompound()));
		magnesiumIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 51).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", magnesiumIngotCrushing);
		
		NBTTagCompound mgbIngotCrushing = new NBTTagCompound();
		mgbIngotCrushing.setTag("input", new ItemStack(NCItems.material, 1, 71).writeToNBT(new NBTTagCompound()));
		mgbIngotCrushing.setTag("output", new ItemStack(NCItems.material, 1, 72).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", mgbIngotCrushing);
		
		// Inter Mod Comms - AE2
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileNuclearFurnace.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileFurnace.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileCrusher.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileElectricCrusher.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileElectricFurnace.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileReactionGenerator.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSeparator.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileHastener.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileCollector.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileAutoWorkspace.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileFissionReactor.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileNuclearWorkspace.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileFusionReactor.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileTubing1.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileTubing2.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileRTG.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileWRTG.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileFusionReactorBlock.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileElectrolyser.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileOxidiser.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileIoniser.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileIrradiator.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileCooler.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileFactory.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileHeliumExtractor.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSolarPanel.class.getCanonicalName());
		
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileElectromagnet.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSuperElectromagnet.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSupercooler.class.getCanonicalName());
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSynchrotron.class.getCanonicalName());
		
		FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", TileSimpleQuantum.class.getCanonicalName());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Ores Ore Dictionary
		OreDictionary.registerOre("oreUranium", new ItemStack(NCBlocks.blockOre, 1, 4));
		OreDictionary.registerOre("oreCopper", new ItemStack(NCBlocks.blockOre, 1, 0));
		OreDictionary.registerOre("oreTin", new ItemStack(NCBlocks.blockOre, 1, 1));
		OreDictionary.registerOre("oreLead", new ItemStack(NCBlocks.blockOre, 1, 2));
		OreDictionary.registerOre("oreSilver", new ItemStack(NCBlocks.blockOre, 1, 3));
		OreDictionary.registerOre("oreThorium", new ItemStack(NCBlocks.blockOre, 1, 5));
		OreDictionary.registerOre("orePlutonium", new ItemStack(NCBlocks.blockOre, 1, 6));
		OreDictionary.registerOre("oreLithium", new ItemStack(NCBlocks.blockOre, 1, 7));
		OreDictionary.registerOre("oreBoron", new ItemStack(NCBlocks.blockOre, 1, 8));
		OreDictionary.registerOre("oreMagnesium", new ItemStack(NCBlocks.blockOre, 1, 9));
		
		// Items Ore Dictionary
		OreDictionary.registerOre("ingotUranium", new ItemStack(NCItems.material, 1, 4));
		OreDictionary.registerOre("ingotCopper", new ItemStack(NCItems.material, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(NCItems.material, 1, 1));
		OreDictionary.registerOre("ingotLead", new ItemStack(NCItems.material, 1, 2));
		OreDictionary.registerOre("ingotSilver", new ItemStack(NCItems.material, 1, 3));
		OreDictionary.registerOre("ingotBronze", new ItemStack(NCItems.material, 1, 6));
		OreDictionary.registerOre("ingotThorium", new ItemStack(NCItems.material, 1, 5));
		OreDictionary.registerOre("ingotLithium", new ItemStack(NCItems.material, 1, 42));
		OreDictionary.registerOre("ingotBoron", new ItemStack(NCItems.material, 1, 43));
		OreDictionary.registerOre("ingotTough", new ItemStack(NCItems.material, 1, 7));
		OreDictionary.registerOre("ingotMagnesium", new ItemStack(NCItems.material, 1, 50));
		OreDictionary.registerOre("ingotUraniumOxide", new ItemStack(NCItems.material, 1, 53));
		OreDictionary.registerOre("ingotMagnesiumDiboride", new ItemStack(NCItems.material, 1, 71));
			
		// Dusts Ore Dictionary
		OreDictionary.registerOre("dustIron", new ItemStack(NCItems.material, 1, 8));
		OreDictionary.registerOre("dustGold", new ItemStack(NCItems.material, 1, 9));
		OreDictionary.registerOre("dustLapis", new ItemStack(NCItems.material, 1, 10));
		OreDictionary.registerOre("dustDiamond", new ItemStack(NCItems.material, 1, 11));
		OreDictionary.registerOre("dustEmerald", new ItemStack(NCItems.material, 1, 12));
		OreDictionary.registerOre("dustQuartz", new ItemStack(NCItems.material, 1, 13));
		OreDictionary.registerOre("dustCoal", new ItemStack(NCItems.material, 1, 14));
		OreDictionary.registerOre("dustCopper", new ItemStack(NCItems.material, 1, 15));
		OreDictionary.registerOre("dustLead", new ItemStack(NCItems.material, 1, 17));
		OreDictionary.registerOre("dustTin", new ItemStack(NCItems.material, 1, 16));
		OreDictionary.registerOre("dustSilver", new ItemStack(NCItems.material, 1, 18));
		OreDictionary.registerOre("dustUranium", new ItemStack(NCItems.material, 1, 19));
		OreDictionary.registerOre("dustThorium", new ItemStack(NCItems.material, 1, 20));
		OreDictionary.registerOre("dustBronze", new ItemStack(NCItems.material, 1, 21));
		OreDictionary.registerOre("dustLithium", new ItemStack(NCItems.material, 1, 44));
		OreDictionary.registerOre("dustBoron", new ItemStack(NCItems.material, 1, 45));
		OreDictionary.registerOre("dustTough", new ItemStack(NCItems.material, 1, 22));
		OreDictionary.registerOre("dustMagnesium", new ItemStack(NCItems.material, 1, 51));
		OreDictionary.registerOre("dustObsidian", new ItemStack(NCItems.material, 1, 52));
		OreDictionary.registerOre("dustUraniumOxide", new ItemStack(NCItems.material, 1, 54));
		OreDictionary.registerOre("dustMagnesiumDiboride", new ItemStack(NCItems.material, 1, 72));
		
		// Blocks Ore Dictionary
		OreDictionary.registerOre("blockUranium", new ItemStack(NCBlocks.blockBlock, 1, 4));
		OreDictionary.registerOre("blockCopper", new ItemStack(NCBlocks.blockBlock, 1, 0));
		OreDictionary.registerOre("blockTin", new ItemStack(NCBlocks.blockBlock, 1, 1));
		OreDictionary.registerOre("blockLead", new ItemStack(NCBlocks.blockBlock, 1, 2));
		OreDictionary.registerOre("blockSilver", new ItemStack(NCBlocks.blockBlock, 1, 3));
		OreDictionary.registerOre("blockBronze", new ItemStack(NCBlocks.blockBlock, 1, 6));
		OreDictionary.registerOre("blockThorium", new ItemStack(NCBlocks.blockBlock, 1, 5));
		OreDictionary.registerOre("blockTough", new ItemStack(NCBlocks.blockBlock, 1, 7));
		OreDictionary.registerOre("blockLithium", new ItemStack(NCBlocks.blockBlock, 1, 8));
		OreDictionary.registerOre("blockBoron", new ItemStack(NCBlocks.blockBlock, 1, 9));
		OreDictionary.registerOre("blockMagnesium", new ItemStack(NCBlocks.blockBlock, 1, 10));
		
		// Parts Ore Dictionary
		OreDictionary.registerOre("universalReactant", new ItemStack(NCItems.parts, 1, 4));
		OreDictionary.registerOre("plateBasic", new ItemStack(NCItems.parts, 1, 0));
		OreDictionary.registerOre("plateReinforced", new ItemStack(NCItems.parts, 1, 3));
		OreDictionary.registerOre("plateDU", new ItemStack(NCItems.parts, 1, 8));
		OreDictionary.registerOre("plateAdvanced", new ItemStack(NCItems.parts, 1, 9));
		OreDictionary.registerOre("plateLead", new ItemStack(NCItems.parts, 1, 14));
		OreDictionary.registerOre("plateIron", new ItemStack(NCItems.parts, 1, 1));
		OreDictionary.registerOre("plateTin", new ItemStack(NCItems.parts, 1, 6));
		
		// Non-Fissile Materials Ore Dictionary
		OreDictionary.registerOre("U238", new ItemStack(NCItems.material, 1, 24));
		OreDictionary.registerOre("U238", new ItemStack(NCItems.material, 1, 55));
		OreDictionary.registerOre("tinyU238", new ItemStack(NCItems.material, 1, 25));
		OreDictionary.registerOre("tinyU238", new ItemStack(NCItems.material, 1, 56));
		OreDictionary.registerOre("U235", new ItemStack(NCItems.material, 1, 26));
		OreDictionary.registerOre("U235", new ItemStack(NCItems.material, 1, 57));
		OreDictionary.registerOre("tinyU235", new ItemStack(NCItems.material, 1, 27));
		OreDictionary.registerOre("tinyU235", new ItemStack(NCItems.material, 1, 58));
		OreDictionary.registerOre("U233", new ItemStack(NCItems.material, 1, 28));
		OreDictionary.registerOre("U233", new ItemStack(NCItems.material, 1, 59));
		OreDictionary.registerOre("tinyU233", new ItemStack(NCItems.material, 1, 29));
		OreDictionary.registerOre("tinyU233", new ItemStack(NCItems.material, 1, 60));
		OreDictionary.registerOre("Pu238", new ItemStack(NCItems.material, 1, 30));
		OreDictionary.registerOre("Pu238", new ItemStack(NCItems.material, 1, 61));
		OreDictionary.registerOre("tinyPu238", new ItemStack(NCItems.material, 1, 31));
		OreDictionary.registerOre("tinyPu238", new ItemStack(NCItems.material, 1, 62));
		OreDictionary.registerOre("Pu239", new ItemStack(NCItems.material, 1, 32));
		OreDictionary.registerOre("Pu239", new ItemStack(NCItems.material, 1, 63));
		OreDictionary.registerOre("tinyPu239", new ItemStack(NCItems.material, 1, 33));
		OreDictionary.registerOre("tinyPu239", new ItemStack(NCItems.material, 1, 64));
		OreDictionary.registerOre("Pu242", new ItemStack(NCItems.material, 1, 34));
		OreDictionary.registerOre("Pu242", new ItemStack(NCItems.material, 1, 65));
		OreDictionary.registerOre("tinyPu242", new ItemStack(NCItems.material, 1, 35));
		OreDictionary.registerOre("tinyPu242", new ItemStack(NCItems.material, 1, 66));
		OreDictionary.registerOre("Pu241", new ItemStack(NCItems.material, 1, 36));
		OreDictionary.registerOre("Pu241", new ItemStack(NCItems.material, 1, 67));
		OreDictionary.registerOre("tinyPu241", new ItemStack(NCItems.material, 1, 37));
		OreDictionary.registerOre("tinyPu241", new ItemStack(NCItems.material, 1, 68));
		
		// Vanilla Ore Dictionary
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.coal, 1));
		OreDictionary.registerOre("oreObsidian", new ItemStack(Blocks.obsidian, 1));
		
		// Filled Fluid Cell Dictionary
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 34));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 35));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 36));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 37));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 38));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 39));
		OreDictionary.registerOre("filledNCGasCell", new ItemStack(NCItems.fuel, 1, 40));
		
		// Record Ore Dictionary
		OreDictionary.registerOre("record", new ItemStack(NCItems.recordPractice, 1));
		OreDictionary.registerOre("record", new ItemStack(NCItems.recordArea51, 1));
		OreDictionary.registerOre("record", new ItemStack(NCItems.recordNeighborhood, 1));
		
		achievements = new Achievements("NuclearCraft");
		FMLCommonHandler.instance().bus().register(achievements);
		
		nuclearFurnaceAchievement = a("nuclearFurnace", 4, -2, NCBlocks.nuclearFurnaceIdle, null);
		dominosAchievement = a("dominos", -4, -2, NCItems.dominoes, null);
		fishAndRicecakeAchievement = a("fishAndRicecake", -6, -2, NCItems.fishAndRicecake, null);
		if (workspace) heavyDutyWorkspaceAchievement = a("heavyDutyWorkspace", 0, 0, NCBlocks.nuclearWorkspace, null);
		nukeAchievement = a("nuke", -2, -2, NCBlocks.nuke, workspace ? heavyDutyWorkspaceAchievement : null);
		toolAchievement = a("tool", 2, -2, NCItems.dUPaxel, workspace ? heavyDutyWorkspaceAchievement : null);
		reactionGeneratorAchievement = a("reactionGenerator", -2, 0, NCBlocks.reactionGeneratorIdle, workspace ? heavyDutyWorkspaceAchievement : null);
		factoryAchievement = a("factory", 0, 2, NCBlocks.factoryIdle, workspace ? heavyDutyWorkspaceAchievement : null);
		fissionControllerAchievement = a("fissionController", 2, 2, NCBlocks.fissionReactorGraphiteIdle, factoryAchievement);
		RTGAchievement = a("RTG", 2, 0, NCBlocks.RTG, fissionControllerAchievement);
		fusionReactorAchievement = a("fusionReactor", 4, 2, NCBlocks.fusionReactor, fissionControllerAchievement);
		separatorAchievement = a("separator", -2, 2, NCBlocks.separatorIdle, factoryAchievement);
		oxidiserAchievement = a("oxidiser", -4, 4, NCBlocks.oxidiserIdle, separatorAchievement);
		pistolAchievement = a("pistol", -4, 2, NCItems.pistol, separatorAchievement);
		solarAchievement = a("solar", 2, 4, NCBlocks.solarPanel, factoryAchievement);
		synchrotronAchievement = a("synchrotron", 4, 6, NCBlocks.synchrotronIdle, factoryAchievement);
	}
	
	public Achievement a(String name, int x, int y, Block req, Achievement pre) {
		return achievements.registerAchievement(new Achievement("achievement." + name, name, x, y, req, pre));
	}
	
	public Achievement a(String name, int x, int y, Item req, Achievement pre) {
		return achievements.registerAchievement(new Achievement("achievement." + name, name, x, y, req, pre));
	}
	
	public Achievement a(String name, int x, int y, ItemStack req, Achievement pre) {
		return achievements.registerAchievement(new Achievement("achievement." + name, name, x, y, req, pre));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Mod Recipes
		IC2Hook = new IC2Recipes();
		IC2Hook.hook();
		
		TEHook = new TERecipes();
		TEHook.hook();
	}
}