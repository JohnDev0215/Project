package com.amazon.music.media.auto.tab;

import Touch.SyncInterface.v1_0.WidgetContext;
import android.content.Context;
import android.support.v4.media.MediaBrowserCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.MediaItemNodeManager;
import com.amazon.music.media.auto.R$string;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.MediaBrowserNodeProvider;
import com.amazon.music.media.auto.util.AutoLog;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NaviAppPage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u0001:\u0001/B/\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b-\u0010.J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\u000b\u001a\u00020\u00052\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00020\u0007J\"\u0010\r\u001a\u00020\u00052\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0002\u0018\u00010\u0007J\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0005R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010!Rl\u0010$\u001aZ\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002 #*,\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002\u0018\u00010\u00070\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%Rl\u0010&\u001aZ\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002 #*,\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002\u0018\u00010\u00070\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010%Rl\u0010'\u001aZ\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002 #*,\u0012\f\u0012\n #*\u0004\u0018\u00010\b0\b\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t #*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00020\u0002\u0018\u00010\u00070\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010%R\u0017\u0010)\u001a\u00020(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b)\u0010+R\u0017\u0010,\u001a\u00020(8\u0006¢\u0006\f\n\u0004\b,\u0010*\u001a\u0004\b,\u0010+¨\u00060"}, d2 = {"Lcom/amazon/music/media/auto/tab/NaviAppPage;", "", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "getMediaNodes", "", "notifyMediaBrowserUpdate", "", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetsMap", "setHomeWidgetsMap", "LTouch/SyncInterface/v1_0/WidgetContext;", "setLibraryWidgetsMap", "setMixedWidgetsMap", "clear", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "mediaBrowser", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "Landroidx/media/MediaBrowserServiceCompat;", "browserService", "Landroidx/media/MediaBrowserServiceCompat;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/media/auto/MediaItemNode;", "parent", "Lcom/amazon/music/media/auto/MediaItemNode;", "", "kotlin.jvm.PlatformType", "libraryWidgetsMap", "Ljava/util/Map;", "homeWidgetsMap", "mixedWidgetsMap", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isHomeWidgetsMapSet", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "isLibraryWidgetsMapSet", "<init>", "(Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;Lcom/amazon/music/media/auto/MediaItemNodeManager;Lcom/amazon/music/media/auto/AutoMediaBrowser;Landroidx/media/MediaBrowserServiceCompat;Landroid/content/Context;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class NaviAppPage {
    private static final String TAG = NaviAppPage.class.getSimpleName();
    private final MediaBrowserServiceCompat browserService;
    private final Context context;
    private final Map<String, List<AutoMediaItem>> homeWidgetsMap;
    private final AtomicBoolean isHomeWidgetsMapSet;
    private final AtomicBoolean isLibraryWidgetsMapSet;
    private final Map<String, List<AutoMediaItem>> libraryWidgetsMap;
    private final AutoMediaBrowser mediaBrowser;
    private final Map<String, List<AutoMediaItem>> mixedWidgetsMap;
    private final MediaItemNodeManager nodeManager;
    private final MediaBrowserNodeProvider nodeProvider;
    private MediaItemNode parent;

    /* JADX INFO: compiled from: NaviAppPage.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WidgetContext.values().length];
            iArr[WidgetContext.SHOVELER_RECENTLY_PLAYED.ordinal()] = 1;
            iArr[WidgetContext.SHOVELER_PLAYLISTS.ordinal()] = 2;
            iArr[WidgetContext.SHOVELER_MADE_FOR_YOU.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NaviAppPage(MediaBrowserNodeProvider nodeProvider, MediaItemNodeManager nodeManager, AutoMediaBrowser mediaBrowser, MediaBrowserServiceCompat browserService, Context context) {
        Intrinsics.checkNotNullParameter(nodeProvider, "nodeProvider");
        Intrinsics.checkNotNullParameter(nodeManager, "nodeManager");
        Intrinsics.checkNotNullParameter(mediaBrowser, "mediaBrowser");
        Intrinsics.checkNotNullParameter(browserService, "browserService");
        Intrinsics.checkNotNullParameter(context, "context");
        this.nodeProvider = nodeProvider;
        this.nodeManager = nodeManager;
        this.mediaBrowser = mediaBrowser;
        this.browserService = browserService;
        this.context = context;
        this.libraryWidgetsMap = Collections.synchronizedMap(new LinkedHashMap());
        this.homeWidgetsMap = Collections.synchronizedMap(new LinkedHashMap());
        this.mixedWidgetsMap = Collections.synchronizedMap(new LinkedHashMap());
        this.isHomeWidgetsMapSet = new AtomicBoolean(false);
        this.isLibraryWidgetsMapSet = new AtomicBoolean(false);
    }

    /* JADX INFO: renamed from: isHomeWidgetsMapSet, reason: from getter */
    public final AtomicBoolean getIsHomeWidgetsMapSet() {
        return this.isHomeWidgetsMapSet;
    }

    /* JADX INFO: renamed from: isLibraryWidgetsMapSet, reason: from getter */
    public final AtomicBoolean getIsLibraryWidgetsMapSet() {
        return this.isLibraryWidgetsMapSet;
    }

    public List<MediaBrowserCompat.MediaItem> getMediaNodes() {
        MediaBrowserNodeProvider mediaBrowserNodeProvider = this.nodeProvider;
        Map<String, List<AutoMediaItem>> mixedWidgetsMap = this.mixedWidgetsMap;
        Intrinsics.checkNotNullExpressionValue(mixedWidgetsMap, "mixedWidgetsMap");
        MediaItemNode mediaItemNodeCreateWidgetNode = mediaBrowserNodeProvider.createWidgetNode(mixedWidgetsMap);
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
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "notifyMediaBrowserUpdate for navi app page: Called notify children for root and home node & playing any pending playback");
        this.browserService.notifyChildrenChanged("BROWSER_ROOT");
        this.browserService.notifyChildrenChanged(MediaItemNodeManager.FirstLayerDrawerId.HOME.name());
        this.mediaBrowser.playPendingPlayback();
    }

    public final void setHomeWidgetsMap(Map<String, ? extends List<AutoMediaItem>> widgetsMap) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        if (this.homeWidgetsMap.isEmpty()) {
            this.homeWidgetsMap.putAll(widgetsMap);
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "setHomeWidgetsMap with " + this.homeWidgetsMap.size() + " elements");
        }
        this.isHomeWidgetsMapSet.set(true);
    }

    public final void setMixedWidgetsMap() {
        Map<String, List<AutoMediaItem>> mixedWidgetsMap = this.mixedWidgetsMap;
        Intrinsics.checkNotNullExpressionValue(mixedWidgetsMap, "mixedWidgetsMap");
        if (!mixedWidgetsMap.isEmpty()) {
            this.mixedWidgetsMap.clear();
        }
        Map<String, List<AutoMediaItem>> map = this.mixedWidgetsMap;
        Map<String, List<AutoMediaItem>> libraryWidgetsMap = this.libraryWidgetsMap;
        Intrinsics.checkNotNullExpressionValue(libraryWidgetsMap, "libraryWidgetsMap");
        map.putAll(libraryWidgetsMap);
        Map<String, List<AutoMediaItem>> map2 = this.mixedWidgetsMap;
        Map<String, List<AutoMediaItem>> homeWidgetsMap = this.homeWidgetsMap;
        Intrinsics.checkNotNullExpressionValue(homeWidgetsMap, "homeWidgetsMap");
        map2.putAll(homeWidgetsMap);
    }

    public final void clear() {
        this.mixedWidgetsMap.clear();
        this.libraryWidgetsMap.clear();
        this.homeWidgetsMap.clear();
        this.isHomeWidgetsMapSet.set(false);
        this.isLibraryWidgetsMapSet.set(false);
    }

    public final void setLibraryWidgetsMap(Map<WidgetContext, ? extends List<AutoMediaItem>> widgetsMap) {
        String string;
        if (widgetsMap != null) {
            for (Map.Entry<WidgetContext, ? extends List<AutoMediaItem>> entry : widgetsMap.entrySet()) {
                WidgetContext key = entry.getKey();
                List<AutoMediaItem> value = entry.getValue();
                int i = WhenMappings.$EnumSwitchMapping$0[key.ordinal()];
                if (i == 1) {
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_recently_played);
                } else if (i == 2) {
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_playlists);
                } else if (i == 3) {
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_made_for_you);
                } else {
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.error(TAG2, "Do not support the node type, will not display the media item.");
                }
                Intrinsics.checkNotNullExpressionValue(string, "when (widgetContext) {\n …          }\n            }");
                Map<String, List<AutoMediaItem>> libraryWidgetsMap = this.libraryWidgetsMap;
                Intrinsics.checkNotNullExpressionValue(libraryWidgetsMap, "libraryWidgetsMap");
                libraryWidgetsMap.put(string, value);
            }
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "setLibraryWidgetsMap with " + this.libraryWidgetsMap.size() + " elements");
        this.isLibraryWidgetsMapSet.set(true);
    }
}
