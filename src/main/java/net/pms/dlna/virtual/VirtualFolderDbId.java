package net.pms.dlna.virtual;

import net.pms.configuration.RendererConfiguration;
import net.pms.dlna.DLNAResource;
import net.pms.network.DbIdResourceLocator;
import net.pms.network.DbIdResourceLocator.DbidMediaType;

/**
 * This VirtualFolder implements support for RealFileDbId's database backed IDs.
 */
public class VirtualFolderDbId extends VirtualFolder {

	private DbIdResourceLocator dbIdResourceLocator = new DbIdResourceLocator();

	private DbidMediaType mediaType;

	public VirtualFolderDbId(DbidMediaType type, String name, String thumbnailIcon) {
		super(name, thumbnailIcon);
		this.mediaType = type;
		String id = dbIdResourceLocator.encodeDbid(name, type);
		setId(id);
		setDefaultRenderer(RendererConfiguration.getDefaultConf());
	}

	public VirtualFolderDbId(DbidMediaType type, String name, String id, String thumbnailIcon) {
		super(name, thumbnailIcon);
		this.mediaType = type;
		id = dbIdResourceLocator.encodeDbid(id, type);
		setId(id);
		setDefaultRenderer(RendererConfiguration.getDefaultConf());
	}

	@Override
	public void addChild(DLNAResource child) {
		addChild(child, false, false);
		getChildren().add(child);
		child.setParent(this);
	}

	public DbidMediaType getMediaType() {
		return mediaType;
	}

	public String getMediaTypeUclass() {
		return mediaType.uclass;
	}
}
