package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import static net.minecraftforge.common.util.ForgeDirection.*;

@SideOnly(Side.CLIENT)
public class RenderWoodPanel implements ISimpleBlockRenderingHandler {

	private IIcon coverIcon;
	private IIcon innerIcon;
	private IIcon rodIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		int dirMeta = (meta & 3) + 2;
		this.coverIcon = block.getBlockTextureFromSide(0);
		this.innerIcon = block.getBlockTextureFromSide(4);
		this.rodIcon = Blocks.coal_block.getBlockTextureFromSide(0);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 16.0F / 16.0F,
					16.0F / 16.0F, this.coverIcon);
			renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F,
					16.0F / 16.0F, this.innerIcon);
			renderInvCuboid(renderer, block, 7.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
					16.0F / 16.0F, this.coverIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		int dirMeta = (meta & 3) + 2;
		int texNum = (x + y * z) & 3;
		this.coverIcon = block.getBlockTextureFromSide(texNum);
		this.innerIcon = block.getBlockTextureFromSide(4);
		this.rodIcon = Blocks.coal_block.getBlockTextureFromSide(0);
		ForgeDirection dir = ForgeDirection.getOrientation(dirMeta);

		if (modelId == this.getRenderId()) {
			if (dir == NORTH) {
				Block get = world.getBlock(x, y, z - 1);
				if (!get.isAir(world, x, y, z - 1) && !get.isNormalCube()) {
					renderer.setOverrideBlockTexture(this.rodIcon);
					block.setBlockBounds(7.0F / 16.0F, 5.0F / 16.0F, 0.0F / 16.0F, 9.0F / 16.0F, 6.0F / 16.0F,
							8.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						9.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 9.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						15.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == SOUTH) {
				Block get = world.getBlock(x, y, z + 1);
				if (!get.isAir(world, x, y, z + 1) && !get.isNormalCube()) {
					renderer.setOverrideBlockTexture(this.rodIcon);
					block.setBlockBounds(7.0F / 16.0F, 5.0F / 16.0F, 8.0F / 16.0F, 9.0F / 16.0F, 6.0F / 16.0F,
							16.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						1.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						7.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == WEST) {
				Block get = world.getBlock(x + 1, y, z);
				if (!get.isAir(world, x + 1, y, z) && !get.isNormalCube()) {
					renderer.setOverrideBlockTexture(this.rodIcon);
					block.setBlockBounds(8.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F, 6.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(1.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(7.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == EAST) {
				Block get = world.getBlock(x - 1, y, z);
				if (!get.isAir(world, x - 1, y, z) && !get.isNormalCube()) {
					renderer.setOverrideBlockTexture(this.rodIcon);
					block.setBlockBounds(0.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 8.0F / 16.0F, 6.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(8.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 9.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(9.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(15.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}

			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int a) {

		return true;
	}

	@Override
	public int getRenderId() {

		return DCsAppleMilk.modelWoodPanel;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX,
			float maxY, float maxZ, IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}
