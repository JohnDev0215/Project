package com.amazon.music.media.auto.tab.home;

import android.support.v4.media.MediaBrowserCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.MediaItemNodeManager;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.MediaBrowserNodeProvider;
import com.amazon.music.media.auto.provider.MuseBrushPageProvider;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.tab.home.brush.BrushPageConverter;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.CredentialStorage;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeTab.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0001$BQ\u0012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\"\u0010#J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0002H\u0016R&\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/HomeTab;", "", "", "getSeeMoreContent", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getMediaNodes", "notifyMediaBrowserUpdate", "", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetsMap", "Ljava/util/Map;", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "Landroidx/media/MediaBrowserServiceCompat;", "browserService", "Landroidx/media/MediaBrowserServiceCompat;", "Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "converter", "Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "Lcom/amazon/music/media/auto/MediaItemNode;", "parent", "Lcom/amazon/music/media/auto/MediaItemNode;", "<init>", "(Ljava/util/Map;Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;Lcom/amazon/music/media/auto/MediaItemNodeManager;Lcom/amazon/music/media/auto/AutoMediaBrowser;Landroidx/media/MediaBrowserServiceCompat;Lcom/amazon/music/media/auto/tab/home/brush/BrushPageConverter;Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class HomeTab {
    private static final String TAG = HomeTab.class.getSimpleName();
    private final MediaBrowserServiceCompat browserService;
    private final BrushPageConverter converter;
    private final CredentialStorage.Credentials credentials;
    private final AutoMediaBrowser mediaBrowser;
    private final MediaItemNodeManager nodeManager;
    private final MediaBrowserNodeProvider nodeProvider;
    private MediaItemNode parent;
    private final Map<String, List<AutoMediaItem>> widgetsMap;

    /* JADX WARN: Multi-variable type inference failed */
    public HomeTab(Map<String, ? extends List<AutoMediaItem>> widgetsMap, MediaBrowserNodeProvider nodeProvider, MediaItemNodeManager nodeManager, AutoMediaBrowser mediaBrowser, MediaBrowserServiceCompat browserService, BrushPageConverter converter, CredentialStorage.Credentials credentials) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        Intrinsics.checkNotNullParameter(nodeProvider, "nodeProvider");
        Intrinsics.checkNotNullParameter(nodeManager, "nodeManager");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        Intrinsics.checkNotNullParameter(browserService, "browserService");
        Intrinsics.checkNotNullParameter(converter, "converter");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        this.widgetsMap = widgetsMap;
        this.nodeProvider = nodeProvider;
        this.nodeManager = nodeManager;
        this.mediaBrowser = mediaBrowser;
        this.browserService = browserService;
        this.converter = converter;
        this.credentials = credentials;
    }

    public List<MediaBrowserCompat.MediaItem> getMediaNodes() {
        MediaItemNode mediaItemNodeCreateWidgetNode;
        if (SubDeviceTypeProvider.INSTANCE.isMultiLevelMediaBrowserHierarchy()) {
            mediaItemNodeCreateWidgetNode = this.nodeProvider.createHomeTabNode(this.widgetsMap);
        } else {
            mediaItemNodeCreateWidgetNode = this.nodeProvider.createWidgetNode(this.widgetsMap);
        }
        this.parent = mediaItemNodeCreateWidgetNode;
        MediaItemNodeManager mediaItemNodeManager = this.nodeManager;
        if (mediaItemNodeCreateWidgetNode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            mediaItemNodeCreateWidgetNode = null;
        }
        List<MediaBrowserCompat.MediaItem> children = mediaItemNodeManager.getChildren(mediaItemNodeCreateWidgetNode.getMediaItem().getMediaId());
        this.mediaBrowser.setHomePageReady(true);
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("getMediaNodes: Sending childrenList of size ", Integer.valueOf(children.size())));
        return children;
    }

    public void notifyMediaBrowserUpdate() {
        getSeeMoreContent();
        this.browserService.notifyChildrenChanged("BROWSER_ROOT");
        this.browserService.notifyChildrenChanged(MediaItemNodeManager.FirstLayerDrawerId.HOME.name());
        this.mediaBrowser.playPendingPlayback();
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "notifyMediaBrowserUpdate: Triggered see more, Called notify children for root and home node & playing any pending playback");
    }

    private final void getSeeMoreContent() {
        for (Map.Entry<String, String> entry : this.converter.getWidgetUriMap().entrySet()) {
            MuseBrushPageProvider.INSTANCE.getSeeMoreContent(this.browserService, entry.getKey(), entry.getValue(), this.credentials, this.converter, this.mediaBrowser);
        }
    }
}
