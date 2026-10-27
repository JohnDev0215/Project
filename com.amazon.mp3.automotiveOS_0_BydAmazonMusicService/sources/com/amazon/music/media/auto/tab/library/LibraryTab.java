package com.amazon.music.media.auto.tab.library;

import Touch.SyncInterface.v1_0.WidgetContext;
import android.support.v4.media.MediaBrowserCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.MediaItemNodeManager;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.MediaBrowserNodeProvider;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryTab.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001BA\u0012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016R&\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/media/auto/tab/library/LibraryTab;", "", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getMediaNodes", "", "notifyMediaBrowserUpdate", "", "LTouch/SyncInterface/v1_0/WidgetContext;", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetsMap", "Ljava/util/Map;", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Landroidx/media/MediaBrowserServiceCompat;", "browserService", "Landroidx/media/MediaBrowserServiceCompat;", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "Lcom/amazon/music/media/auto/MediaItemNode;", "parent", "Lcom/amazon/music/media/auto/MediaItemNode;", "<init>", "(Ljava/util/Map;Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;Lcom/amazon/music/media/auto/MediaItemNodeManager;Landroidx/media/MediaBrowserServiceCompat;Lcom/amazon/music/media/auto/AutoMediaBrowser;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class LibraryTab {
    private final MediaBrowserServiceCompat browserService;
    private final AutoMediaBrowser mediaBrowser;
    private final MediaItemNodeManager nodeManager;
    private final MediaBrowserNodeProvider nodeProvider;
    private MediaItemNode parent;
    private final Map<WidgetContext, List<AutoMediaItem>> widgetsMap;

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryTab(Map<WidgetContext, ? extends List<AutoMediaItem>> widgetsMap, MediaBrowserNodeProvider nodeProvider, MediaItemNodeManager nodeManager, MediaBrowserServiceCompat browserService, AutoMediaBrowser mediaBrowser) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        Intrinsics.checkNotNullParameter(nodeProvider, "nodeProvider");
        Intrinsics.checkNotNullParameter(nodeManager, "nodeManager");
        Intrinsics.checkNotNullParameter(browserService, "browserService");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        this.widgetsMap = widgetsMap;
        this.nodeProvider = nodeProvider;
        this.nodeManager = nodeManager;
        this.browserService = browserService;
        this.mediaBrowser = mediaBrowser;
    }

    public List<MediaBrowserCompat.MediaItem> getMediaNodes() {
        this.parent = this.nodeProvider.createLibraryTabNode(this.widgetsMap);
        this.mediaBrowser.setLibraryPageReady(true);
        MediaItemNodeManager mediaItemNodeManager = this.nodeManager;
        MediaItemNode mediaItemNode = this.parent;
        if (mediaItemNode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            mediaItemNode = null;
        }
        return mediaItemNodeManager.getChildren(mediaItemNode.getMediaItem().getMediaId());
    }

    public void notifyMediaBrowserUpdate() {
        this.browserService.notifyChildrenChanged("BROWSER_ROOT");
        this.browserService.notifyChildrenChanged(MediaItemNodeManager.FirstLayerDrawerId.LIBRARY.name());
    }
}
