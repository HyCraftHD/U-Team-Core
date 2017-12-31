package info.u_team.u_team_core.render;

import org.lwjgl.opengl.GL11;

import info.u_team.u_team_core.intern.UCoreConstants;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

/**
 * Entry for the MC standard VertexBuffer
 * 
 * @author MrTroble
 * @date 17.9.2017
 *
 */

public class BufferEntry {
	
	private static BufferEntry INSTANCE;
	
	public static BufferEntry getBufferEntry() {
		if (INSTANCE == null)
			INSTANCE = new BufferEntry();
		return INSTANCE;
	}
	
	private Tessellator tesllator;
	private WorldRenderer buffer;
	
	public BufferEntry() {
		this.tesllator = Tessellator.getInstance();
		this.buffer = this.tesllator.getWorldRenderer();
	}
	
	public BufferEntry vertex(float x, float y, float z) {
		this.buffer.putPosition(x, y, z);
		return this;
	}
	
	public BufferEntry tex(float u, float v) {
		this.buffer.tex(u, v);
		return this;
	}
	
	public BufferEntry color(float r, float b, float g, float a) {
		this.buffer.color(r, g, b, a);
		return this;
	}
	
	public BufferEntry normal(float x, float y, float z) {
		this.buffer.putNormal(x, y, z);
		return this;
	}
	
	public BufferEntry start(DrawFormat format) {
		if (format == null) {
			UCoreConstants.LOGGER.error("DrawFormar is null in BufferEntry.start()");
		}
		VertexFormat v = null;
		switch (format) {
		case POS_COLOR:
			v = DefaultVertexFormats.POSITION_COLOR;
			break;
		case POS_TEX:
			v = DefaultVertexFormats.POSITION_TEX;
			break;
		case POS_TEX_COLOR:
			v = DefaultVertexFormats.POSITION_TEX_COLOR;
			break;
		case POS_TEX_COLOR_NORMAL:
			v = DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL;
			break;
		case POS_TEX_NORMAL:
			v = DefaultVertexFormats.POSITION_TEX_NORMAL;
			break;
		}
		if (v == null) {
			UCoreConstants.LOGGER.error("Somthing went wrong with the draw start (Wrong vertex Format)");
		}
		this.buffer.begin(GL11.GL_POLYGON_BIT, v);
		return this;
	}
	
	public void end() {
		this.buffer.endVertex();
	}
	
	public void endDraw() {
		this.tesllator.draw();
	}
}
