package mods.defeatedcrow.common.block.container;

import java.util.Random;

import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCharcoalBox extends Block implements IFuelHandler {

	@SideOnly(Side.CLIENT)
	private IIcon charcoalBoxTop;
	@SideOnly(Side.CLIENT)
	private IIcon charcoalBoxSide;

	public BlockCharcoalBox() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {

		Item i = fuel.getItem();
		return i == Item.getItemFromBlock(this) ? 16000 : 0;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.charcoalBoxTop : (par1 == 0 ? this.charcoalBoxSide
				: (par1 != 2 && par1 != 4 ? this.blockIcon : this.charcoalBoxSide));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
		this.charcoalBoxTop = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_charcoal_T");
		this.charcoalBoxSide = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
	}

	// ハーフブロック化

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int meta = par6ItemStack.getItemDamage();
		int next = meta;

		if (par5EntityLivingBase.isSneaking()) {
			next = next | 4;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, next, 3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.updateThisBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void updateThisBounds(int meta) {
		if ((meta & 4) == 4) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

}
