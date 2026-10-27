package com.amazon.music.media.auto.tab.home;

import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.VisibleForTesting;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.MediaItemNodeManager;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.MESKMediaBrowserNodeProvider;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.util.AutoLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MESKHomeTab.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B7\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\b\u0010\t\u001a\u00020\bH\u0007R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/MESKHomeTab;", "", "", "getSeeMoreContent", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getMediaNodes", "notifyMediaBrowserUpdate", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "createHomeAutoTabItem", "tabItem", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "Lcom/amazon/music/media/auto/provider/MESKMediaBrowserNodeProvider;", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MESKMediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "Landroidx/media/MediaBrowserServiceCompat;", "browserService", "Landroidx/media/MediaBrowserServiceCompat;", "Lcom/amazon/music/media/auto/provider/SubDeviceTypeProvider;", "subDeviceTypeProvider", "Lcom/amazon/music/media/auto/provider/SubDeviceTypeProvider;", "Lcom/amazon/music/media/auto/MediaItemNode;", "parentNode", "Lcom/amazon/music/media/auto/MediaItemNode;", "<init>", "(Lcom/amazon/music/media/auto/provider/data/AutoTabItem;Lcom/amazon/music/media/auto/provider/MESKMediaBrowserNodeProvider;Lcom/amazon/music/media/auto/MediaItemNodeManager;Lcom/amazon/music/media/auto/AutoMediaBrowser;Landroidx/media/MediaBrowserServiceCompat;Lcom/amazon/music/media/auto/provider/SubDeviceTypeProvider;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class MESKHomeTab {
    private static final String TAG = MESKHomeTab.class.getSimpleName();
    private final MediaBrowserServiceCompat browserService;
    private final AutoMediaBrowser mediaBrowser;
    private final MediaItemNodeManager nodeManager;
    private final MESKMediaBrowserNodeProvider nodeProvider;
    private MediaItemNode parentNode;
    private final SubDeviceTypeProvider subDeviceTypeProvider;
    private final AutoTabItem tabItem;

    public MESKHomeTab(AutoTabItem tabItem, MESKMediaBrowserNodeProvider nodeProvider, MediaItemNodeManager nodeManager, AutoMediaBrowser mediaBrowser, MediaBrowserServiceCompat browserService, SubDeviceTypeProvider subDeviceTypeProvider) {
        Intrinsics.checkNotNullParameter(tabItem, "tabItem");
        Intrinsics.checkNotNullParameter(nodeProvider, "nodeProvider");
        Intrinsics.checkNotNullParameter(nodeManager, "nodeManager");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        Intrinsics.checkNotNullParameter(browserService, "browserService");
        Intrinsics.checkNotNullParameter(subDeviceTypeProvider, "subDeviceTypeProvider");
        this.tabItem = tabItem;
        this.nodeProvider = nodeProvider;
        this.nodeManager = nodeManager;
        this.mediaBrowser = mediaBrowser;
        this.browserService = browserService;
        this.subDeviceTypeProvider = subDeviceTypeProvider;
    }

    public List<MediaBrowserCompat.MediaItem> getMediaNodes() {
        if (this.subDeviceTypeProvider.isMultiLevelMediaBrowserHierarchy()) {
            MediaItemNode mediaItemNodeCreateMeskHomeTabNode = this.nodeProvider.createMeskHomeTabNode(createHomeAutoTabItem());
            this.parentNode = mediaItemNodeCreateMeskHomeTabNode;
            MediaItemNodeManager mediaItemNodeManager = this.nodeManager;
            if (mediaItemNodeCreateMeskHomeTabNode == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentNode");
                mediaItemNodeCreateMeskHomeTabNode = null;
            }
            List<MediaBrowserCompat.MediaItem> children = mediaItemNodeManager.getChildren(mediaItemNodeCreateMeskHomeTabNode.getMediaItem().getMediaId());
            this.mediaBrowser.setHomePageReady(true);
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("MESK getMediaNodes: Sending childrenList of size ", Integer.valueOf(children.size())));
            return children;
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public void notifyMediaBrowserUpdate() {
        getSeeMoreContent();
        this.browserService.notifyChildrenChanged("BROWSER_ROOT");
        this.browserService.notifyChildrenChanged(MediaItemNodeManager.FirstLayerDrawerId.HOME.name());
        this.mediaBrowser.playPendingPlayback();
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "notifyMediaBrowserUpdate: Triggered see more, Called notify children for root and MESK home node & playing any pending playback");
    }

    @VisibleForTesting
    public final AutoTabItem createHomeAutoTabItem() {
        ArrayList arrayList = new ArrayList();
        for (AutoWidgetItem autoWidgetItem : this.tabItem.getWidgets()) {
            String title = autoWidgetItem.getTitle();
            int initialEntityCount = autoWidgetItem.getInitialEntityCount();
            List<AutoMediaItem> entities = autoWidgetItem.getEntities();
            arrayList.add(new AutoWidgetItem(title, autoWidgetItem.getSecondaryPresentationViewType(), autoWidgetItem.getIsBrowsable(), initialEntityCount, entities));
        }
        return new AutoTabItem(this.tabItem.getWidgetsExpandType(), arrayList);
    }

    private final void getSeeMoreContent() {
        Iterator<AutoWidgetItem> it = this.tabItem.getWidgets().iterator();
        while (it.hasNext()) {
            this.mediaBrowser.onMESKHomeSeeMorePageFetched(it.next());
        }
    }
}
