package com.progwml6.ironshulkerbox.client.gui;

import com.progwml6.ironshulkerbox.common.blocks.IronShulkerBoxType;
import com.progwml6.ironshulkerbox.common.gui.ContainerIronShulkerBox;
import com.progwml6.ironshulkerbox.common.tileentity.TileEntityIronShulkerBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GUIShulkerBox extends GuiContainer
{
    public enum ResourceList
    {
        //@formatter:off
        IRON(new ResourceLocation("ironshulkerbox", "textures/gui/iron_container.png")),
        COPPER(new ResourceLocation("ironshulkerbox", "textures/gui/copper_container.png")),
        SILVER(new ResourceLocation("ironshulkerbox", "textures/gui/silver_container.png")),
        GOLD(new ResourceLocation("ironshulkerbox", "textures/gui/gold_container.png")),
        DIAMOND(new ResourceLocation("ironshulkerbox", "textures/gui/diamond_container.png"));
        //@formatter:on

        public final ResourceLocation location;

        ResourceList(ResourceLocation loc)
        {
            this.location = loc;
        }
    }

    public enum GUI
    {
        //@formatter:off
        IRON(184, 202, ResourceList.IRON, IronShulkerBoxType.IRON, new ResourceLocation("ironshulkerbox:iron")),
        GOLD(184, 256, ResourceList.GOLD, IronShulkerBoxType.GOLD, new ResourceLocation("ironshulkerbox:gold")),
        DIAMOND(238, 256, ResourceList.DIAMOND, IronShulkerBoxType.DIAMOND, new ResourceLocation("ironshulkerbox:diamond")),
        COPPER(184, 184, ResourceList.COPPER, IronShulkerBoxType.COPPER, new ResourceLocation("ironshulkerbox:copper")),
        SILVER(184, 238, ResourceList.SILVER, IronShulkerBoxType.SILVER, new ResourceLocation("ironshulkerbox:silver")),
        CRYSTAL(238, 256, ResourceList.DIAMOND, IronShulkerBoxType.CRYSTAL, new ResourceLocation("ironshulkerbox:crystal")),
        OBSIDIAN(238, 256, ResourceList.DIAMOND,IronShulkerBoxType.OBSIDIAN, new ResourceLocation("ironshulkerbox:obsidian"));
        //@formatter:on

        private int xSize;

        private int ySize;

        private ResourceList resourceList;

        private IronShulkerBoxType ironShulkerBoxType;

        private ResourceLocation guiId;

        GUI(int xSize, int ySize, ResourceList resourceList, IronShulkerBoxType ironShulkerBoxType, ResourceLocation guiId)
        {
            this.xSize = xSize;
            this.ySize = ySize;
            this.resourceList = resourceList;
            this.ironShulkerBoxType = ironShulkerBoxType;
            this.guiId = guiId;
        }

        protected Container makeContainer(IInventory playerInventory, IInventory chestInventory, EntityPlayer entityPlayer)
        {
            return new ContainerIronShulkerBox(playerInventory, chestInventory, this.ironShulkerBoxType, entityPlayer, this.xSize, this.ySize);
        }

        public ResourceLocation getGuiId()
        {
            return this.guiId;
        }
    }

    private GUI type;

    public GUIShulkerBox(GUI type, IInventory player, IInventory shulker)
    {
        super(type.makeContainer(player, shulker, Minecraft.getInstance().player));

        this.type = type;
        this.xSize = type.xSize;
        this.ySize = type.ySize;

        this.allowUserInput = false;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(this.type.resourceList.location);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}