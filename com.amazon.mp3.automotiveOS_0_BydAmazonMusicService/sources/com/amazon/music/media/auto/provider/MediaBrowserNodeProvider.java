package com.amazon.music.media.auto.provider;

import Touch.SyncInterface.v1_0.WidgetContext;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import androidx.media.utils.MediaConstants;
import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.AutoMusicMediaBrowserService;
import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.MediaItemNodeManager;
import com.amazon.music.media.auto.R$string;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import com.amazon.music.media.auto.provider.data.AutoWidgetItem;
import com.amazon.music.media.auto.ui.PresentationViewType;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.LibraryExtensionsKt;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MediaBrowserNodeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\f\u0018\u0000 W2\u00020\u0001:\u0001WB/\u0012\u0006\u0010<\u001a\u00020;\u0012\u0006\u0010?\u001a\u00020>\u0012\u0006\u0010B\u001a\u00020A\u0012\u0006\u0010E\u001a\u00020D\u0012\u0006\u0010G\u001a\u00020*¢\u0006\u0004\bU\u0010VJ6\u0010\r\u001a\u00020\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J\u001e\u0010\u0012\u001a\u00020\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u001e\u0010\u0013\u001a\u00020\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J \u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J2\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0002J8\u0010 \u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\tH\u0002J*\u0010!\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002J\u0010\u0010#\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0010H\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020$H\u0002J*\u0010)\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020$2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002J\u0006\u0010+\u001a\u00020*J\"\u0010.\u001a\u00020\u00102\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020,H\u0007J \u0010/\u001a\u00020\u00102\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020,J \u00101\u001a\u00020\u00102\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u000200\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020,J\u0010\u00104\u001a\u00020\u00102\u0006\u00103\u001a\u000202H\u0016J*\u00106\u001a\u00020\f2\u0018\u00105\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020,2\u0006\u0010'\u001a\u00020\u0005H\u0007J\u0012\u00107\u001a\u0004\u0018\u00010\u00102\u0006\u0010'\u001a\u00020\u0005H\u0007J\u0010\u00109\u001a\u00020\f2\u0006\u0010\u0014\u001a\u000208H\u0016J&\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00100\u00022\u0018\u0010-\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020,R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR#\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001e0L8\u0006¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR \u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001e0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010NR \u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001e0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010NR#\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100L8\u0006¢\u0006\f\n\u0004\bS\u0010N\u001a\u0004\bT\u0010P¨\u0006X"}, d2 = {"Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "Lcom/amazon/music/media/auto/provider/MESKMediaBrowserNodeProvider;", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widget", "", "sectionTitle", "Landroid/net/Uri;", "widgetIconUri", "", "isWidgetSubPageOnly", "isWidgetSubPageGrid", "", "createLibraryTabSection", "Lcom/amazon/music/media/auto/provider/HomeTabWidgetInfo;", "homeTabWidgetsInfo", "Lcom/amazon/music/media/auto/MediaItemNode;", "home", "createMeskHomeTabNodeOnAAuto", "createMeskHomeTabNodeOnAAOS", "widgetInfo", "Landroid/os/Bundle;", "extras", "createMESKHomeTopWidget", "parent", "mediaId", "iconUri", "createSectionNode", "section", "item", "", "limitPerWidget", "createWidgetEntities", "createEntityNode", "node", "downloadMediaArtwork", "Lcom/amazon/music/media/auto/ui/PresentationViewType;", "presentationViewType", "getSeeMorePlayableHintValue", "widgetTitle", "seeMorePresentationViewType", "addSeeMoreNode", "Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "getImageUriProvider", "", "widgetsMap", "createHomeTabNode", "createWidgetNode", "LTouch/SyncInterface/v1_0/WidgetContext;", "createLibraryTabNode", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "tabItem", "createMeskHomeTabNode", "widgetMap", "expandSeeMoreNode", "getSeeMoreNode", "Lcom/amazon/music/media/auto/provider/data/AutoWidgetItem;", "expandMeskSeeMoreNode", "createSearchResultNodes", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "mediaBrowserService", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "nodeManager", "Lcom/amazon/music/media/auto/MediaItemNodeManager;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "imageUriProvider", "Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "defaultUriProvider", "Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "", "widgetEntitiesCount", "Ljava/util/Map;", "getWidgetEntitiesCount", "()Ljava/util/Map;", "widgetEntitiesCountHomeTab", "widgetEntitiesCountLibraryTab", "seeMoreNodeMap", "getSeeMoreNodeMap", "<init>", "(Landroid/content/Context;Lcom/amazon/music/media/auto/AutoMusicMediaBrowserService;Lcom/amazon/music/media/auto/MediaItemNodeManager;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class MediaBrowserNodeProvider implements MESKMediaBrowserNodeProvider {
    private static final String TAG = MediaBrowserNodeProvider.class.getSimpleName();
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DefaultImageUriProvider defaultUriProvider;
    private final LocalImageUriProvider imageUriProvider;
    private final AutoMusicMediaBrowserService mediaBrowserService;
    private final MediaItemNodeManager nodeManager;
    private final Map<String, MediaItemNode> seeMoreNodeMap;
    private final Map<String, Integer> widgetEntitiesCount;
    private final Map<String, Integer> widgetEntitiesCountHomeTab;
    private final Map<String, Integer> widgetEntitiesCountLibraryTab;

    /* JADX INFO: compiled from: MediaBrowserNodeProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[WidgetContext.values().length];
            iArr[WidgetContext.SHOVELER_RECENTLY_PLAYED.ordinal()] = 1;
            iArr[WidgetContext.SHOVELER_PLAYLISTS.ordinal()] = 2;
            iArr[WidgetContext.SHOVELER_MADE_FOR_YOU.ordinal()] = 3;
            iArr[WidgetContext.SHOVELER_ARTISTS.ordinal()] = 4;
            iArr[WidgetContext.SHOVELER_ALBUMS.ordinal()] = 5;
            iArr[WidgetContext.SHOVELER_LIBRARY_TRACKS.ordinal()] = 6;
            iArr[WidgetContext.SHOVELER_MY_PRESETS.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PresentationViewType.values().length];
            iArr2[PresentationViewType.LIST.ordinal()] = 1;
            iArr2[PresentationViewType.GRID.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public MediaBrowserNodeProvider(Context context, AutoMusicMediaBrowserService mediaBrowserService, MediaItemNodeManager nodeManager, CoroutineScope coroutineScope, LocalImageUriProvider imageUriProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaBrowserService, "mediaBrowserService");
        Intrinsics.checkNotNullParameter(nodeManager, "nodeManager");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(imageUriProvider, "imageUriProvider");
        this.context = context;
        this.mediaBrowserService = mediaBrowserService;
        this.nodeManager = nodeManager;
        this.coroutineScope = coroutineScope;
        this.imageUriProvider = imageUriProvider;
        this.defaultUriProvider = new DefaultImageUriProvider(context);
        this.widgetEntitiesCount = new LinkedHashMap();
        this.widgetEntitiesCountHomeTab = new LinkedHashMap();
        this.widgetEntitiesCountLibraryTab = new LinkedHashMap();
        this.seeMoreNodeMap = new LinkedHashMap();
    }

    public final LocalImageUriProvider getImageUriProvider() {
        return this.imageUriProvider;
    }

    @Deprecated(message = "Use the new createMeskHomeTabNode() method")
    public final MediaItemNode createHomeTabNode(Map<String, ? extends List<AutoMediaItem>> widgetsMap) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        MediaItemNode homeNode = this.nodeManager.getHomeNode();
        for (Map.Entry<String, ? extends List<AutoMediaItem>> entry : widgetsMap.entrySet()) {
            String key = entry.getKey();
            Bundle bundle = new Bundle();
            bundle.putString(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_GROUP_TITLE, key);
            for (AutoMediaItem autoMediaItem : entry.getValue()) {
                Map<String, Integer> map = this.widgetEntitiesCount;
                Integer num = map.get(key);
                if (num == null) {
                    num = 0;
                    map.put(key, num);
                }
                map.put(key, Integer.valueOf(num.intValue() + 1));
                String str = autoMediaItem.getTitle() + '@' + key;
                Integer num2 = this.widgetEntitiesCount.get(key);
                int number_of_entities_per_widget = AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET();
                if (num2 != null && num2.intValue() == number_of_entities_per_widget) {
                    String str2 = this.context.getString(R$string.dmusic_automotive_see_more) + '@' + key;
                    bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 1);
                    this.seeMoreNodeMap.put(key, this.nodeManager.createSeeMoreNode(homeNode, str2, this.defaultUriProvider.getSeeMoreIconLocalUri(), bundle));
                } else {
                    createEntityNode(homeNode, autoMediaItem, str, bundle);
                }
            }
        }
        return homeNode;
    }

    public final synchronized MediaItemNode createWidgetNode(Map<String, ? extends List<AutoMediaItem>> widgetsMap) {
        MediaItemNode rootNode;
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        synchronized (widgetsMap) {
            rootNode = this.nodeManager.getRootNode();
            Bundle bundle = new Bundle();
            bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 1);
            Iterator<Map.Entry<String, ? extends List<AutoMediaItem>>> it = widgetsMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, ? extends List<AutoMediaItem>> next = it.next();
                String key = next.getKey();
                Iterator<Map.Entry<String, ? extends List<AutoMediaItem>>> it2 = it;
                MediaItemNode mediaItemNodeCreateMediaItemNode$default = MediaItemNodeManager.createMediaItemNode$default(this.nodeManager, rootNode, key, key, null, true, null, null, null, 232, null);
                for (AutoMediaItem autoMediaItem : next.getValue()) {
                    MediaItemNodeManager.createMediaItemNode$default(this.nodeManager, mediaItemNodeCreateMediaItemNode$default, autoMediaItem.getTitle() + '@' + key, autoMediaItem.getTitle(), autoMediaItem.getSubTitle(), false, autoMediaItem, null, bundle, 64, null);
                }
                it = it2;
            }
        }
        return rootNode;
    }

    public final MediaItemNode createLibraryTabNode(Map<WidgetContext, ? extends List<AutoMediaItem>> widgetsMap) {
        String string;
        Uri recentIconUri;
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        WidgetContext widgetContext = WidgetContext.SHOVELER_ARTISTS;
        WidgetContext widgetContext2 = WidgetContext.SHOVELER_ALBUMS;
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new WidgetContext[]{widgetContext, widgetContext2, WidgetContext.SHOVELER_LIBRARY_TRACKS, WidgetContext.SHOVELER_MY_PRESETS});
        List listListOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new WidgetContext[]{widgetContext2, WidgetContext.SHOVELER_PLAYLISTS, widgetContext, WidgetContext.SHOVELER_MADE_FOR_YOU, WidgetContext.SHOVELER_RECENTLY_PLAYED});
        for (Map.Entry<WidgetContext, ? extends List<AutoMediaItem>> entry : widgetsMap.entrySet()) {
            WidgetContext key = entry.getKey();
            List<AutoMediaItem> value = entry.getValue();
            int[] iArr = WhenMappings.$EnumSwitchMapping$0;
            switch (iArr[key.ordinal()]) {
                case 1:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_recently_played);
                    break;
                case 2:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_playlists);
                    break;
                case 3:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_made_for_you);
                    break;
                case 4:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_artists);
                    break;
                case 5:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_albums);
                    break;
                case 6:
                    string = this.context.getString(R$string.dmusic_media_browser_service_library_songs);
                    break;
                case 7:
                    string = this.context.getString(R$string.dmusic_automotive_presets_my_presets);
                    break;
                default:
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog.error(TAG2, "Do not support the node type, will not display the media item.");
                    continue;
            }
            String str = string;
            Intrinsics.checkNotNullExpressionValue(str, "when (widgetContext) {\n …          }\n            }");
            switch (iArr[key.ordinal()]) {
                case 1:
                    recentIconUri = this.defaultUriProvider.getRecentIconUri();
                    break;
                case 2:
                    recentIconUri = this.defaultUriProvider.getPlaylistIconUri();
                    break;
                case 3:
                    recentIconUri = this.defaultUriProvider.getFireIconUri();
                    break;
                case 4:
                    recentIconUri = this.defaultUriProvider.getArtistIconUri();
                    break;
                case 5:
                    recentIconUri = this.defaultUriProvider.getAlbumIconUri();
                    break;
                case 6:
                    recentIconUri = this.defaultUriProvider.getSongIconUri();
                    break;
                case 7:
                    recentIconUri = this.defaultUriProvider.getPresetsIconUri();
                    break;
                default:
                    AutoLog autoLog2 = AutoLog.INSTANCE;
                    String TAG3 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                    autoLog2.error(TAG3, "Do not support the node type, will not display the default icon URI for widget.");
                    continue;
            }
            Uri uri = recentIconUri;
            if (!value.isEmpty()) {
                createLibraryTabSection(value, str, uri, listListOf.contains(key), listListOf2.contains(key));
            }
        }
        return this.nodeManager.getLibraryNode();
    }

    private final void createLibraryTabSection(List<AutoMediaItem> widget, String sectionTitle, Uri widgetIconUri, boolean isWidgetSubPageOnly, boolean isWidgetSubPageGrid) {
        MediaItemNode libraryNode = this.nodeManager.getLibraryNode();
        Bundle bundle = new Bundle();
        bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_SINGLE_ITEM, 1);
        MediaItemNode mediaItemNodeCreateSectionNode = createSectionNode(libraryNode, sectionTitle, bundle, widgetIconUri, isWidgetSubPageGrid);
        Iterator<AutoMediaItem> it = widget.iterator();
        while (it.hasNext()) {
            createWidgetEntities(libraryNode, mediaItemNodeCreateSectionNode, it.next(), sectionTitle, 3, isWidgetSubPageOnly);
        }
    }

    @Override // com.amazon.music.media.auto.provider.MESKMediaBrowserNodeProvider
    public MediaItemNode createMeskHomeTabNode(AutoTabItem tabItem) {
        Intrinsics.checkNotNullParameter(tabItem, "tabItem");
        List<AutoWidgetItem> widgets = tabItem.getWidgets();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(widgets, 10));
        for (AutoWidgetItem autoWidgetItem : widgets) {
            arrayList.add(new HomeTabWidgetInfo(autoWidgetItem.getTitle(), autoWidgetItem.getEntities(), autoWidgetItem.getEntities().size() > autoWidgetItem.getInitialEntityCount(), autoWidgetItem.getSecondaryPresentationViewType()));
        }
        List<HomeTabWidgetInfo> list = CollectionsKt___CollectionsKt.toList(arrayList);
        MediaItemNode homeNode = this.nodeManager.getHomeNode();
        if (Intrinsics.areEqual(this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "Creating MESK Home tab for AAOS");
            return createMeskHomeTabNodeOnAAOS(list, homeNode);
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "Creating MESK Home tab for AAuto");
        return createMeskHomeTabNodeOnAAuto(list, homeNode);
    }

    private final MediaItemNode createMeskHomeTabNodeOnAAuto(List<HomeTabWidgetInfo> homeTabWidgetsInfo, MediaItemNode home) {
        Bundle bundle = new Bundle();
        bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_SINGLE_ITEM, 1);
        int i = 0;
        for (HomeTabWidgetInfo homeTabWidgetInfo : homeTabWidgetsInfo) {
            int i2 = i + 1;
            if (i2 > 1) {
                boolean z = i2 > 6;
                MediaItemNode mediaItemNodeCreateSectionNode = createSectionNode(home, homeTabWidgetInfo.getTitle(), bundle, this.defaultUriProvider.getSongIconUri(), false);
                Iterator<AutoMediaItem> it = homeTabWidgetInfo.getEntities().iterator();
                while (it.hasNext()) {
                    createWidgetEntities(home, mediaItemNodeCreateSectionNode, it.next(), homeTabWidgetInfo.getTitle(), 3, z);
                }
            } else {
                createMESKHomeTopWidget(home, homeTabWidgetInfo, bundle);
            }
            i = i2;
        }
        return home;
    }

    private final MediaItemNode createMeskHomeTabNodeOnAAOS(List<HomeTabWidgetInfo> homeTabWidgetsInfo, MediaItemNode home) {
        for (HomeTabWidgetInfo homeTabWidgetInfo : homeTabWidgetsInfo) {
            Bundle bundle = new Bundle();
            bundle.putString(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_GROUP_TITLE, homeTabWidgetInfo.getTitle());
            for (AutoMediaItem autoMediaItem : homeTabWidgetInfo.getEntities()) {
                Map<String, Integer> map = this.widgetEntitiesCount;
                String title = homeTabWidgetInfo.getTitle();
                Map<String, Integer> map2 = this.widgetEntitiesCount;
                String title2 = homeTabWidgetInfo.getTitle();
                Integer num = map2.get(title2);
                if (num == null) {
                    num = 0;
                    map2.put(title2, num);
                }
                map.put(title, Integer.valueOf(num.intValue() + 1));
                Integer num2 = this.widgetEntitiesCount.get(homeTabWidgetInfo.getTitle());
                String str = autoMediaItem.getTitle() + '@' + homeTabWidgetInfo.getTitle();
                if (num2 != null) {
                    if (num2.intValue() == AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET()) {
                        String str2 = this.context.getString(R$string.dmusic_automotive_see_more) + '@' + homeTabWidgetInfo.getTitle();
                        bundle.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 1);
                        this.seeMoreNodeMap.put(homeTabWidgetInfo.getTitle(), this.nodeManager.createSeeMoreNode(home, str2, this.defaultUriProvider.getSeeMoreIconLocalUri(), bundle));
                    }
                }
                if (num2 != null && num2.intValue() < AutoBrowserConstants.INSTANCE.getNUMBER_OF_ENTITIES_PER_WIDGET()) {
                    createEntityNode(home, autoMediaItem, str, bundle);
                }
            }
        }
        return home;
    }

    @Deprecated(message = "Use the new expandMeskSeeMoreNode() method")
    public final void expandSeeMoreNode(Map<String, ? extends List<AutoMediaItem>> widgetMap, String widgetTitle) {
        List<AutoMediaItem> list;
        Intrinsics.checkNotNullParameter(widgetMap, "widgetMap");
        Intrinsics.checkNotNullParameter(widgetTitle, "widgetTitle");
        MediaItemNode mediaItemNode = this.seeMoreNodeMap.get(widgetTitle);
        if (mediaItemNode == null || (list = widgetMap.get(widgetTitle)) == null) {
            return;
        }
        for (AutoMediaItem autoMediaItem : list) {
            createEntityNode(mediaItemNode, autoMediaItem, autoMediaItem.getTitle() + '@' + widgetTitle, null);
        }
    }

    private final void createMESKHomeTopWidget(MediaItemNode home, HomeTabWidgetInfo widgetInfo, Bundle extras) {
        MediaItemNode mediaItemNodeCreateSectionNode = createSectionNode(home, widgetInfo.getTitle(), extras, this.defaultUriProvider.getSongIconUri(), false);
        for (AutoMediaItem autoMediaItem : widgetInfo.getEntities()) {
            createEntityNode(mediaItemNodeCreateSectionNode, autoMediaItem, autoMediaItem.getTitle() + '@' + widgetInfo.getTitle(), null);
        }
        if (widgetInfo.getShouldHaveSeeMoreNode()) {
            widgetInfo.setEntities(CollectionsKt___CollectionsKt.take(widgetInfo.getEntities(), 5));
        }
        for (AutoMediaItem autoMediaItem2 : widgetInfo.getEntities()) {
            createEntityNode(home, autoMediaItem2, autoMediaItem2.getTitle() + '@' + widgetInfo.getTitle(), null);
        }
        if (widgetInfo.getShouldHaveSeeMoreNode()) {
            addSeeMoreNode(home, widgetInfo.getTitle(), widgetInfo.getSeeMorePresentationViewType(), null);
        }
    }

    private final MediaItemNode createSectionNode(MediaItemNode parent, String mediaId, Bundle extras, Uri iconUri, boolean isWidgetSubPageGrid) {
        if (isWidgetSubPageGrid) {
            extras.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 2);
        } else {
            extras.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 1);
        }
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "Creating section " + mediaId + " on " + ((Object) parent.getMediaItem().getMediaId()) + " page");
        MediaItemNode mediaItemNodeFindChild = parent.findChild(mediaId);
        return mediaItemNodeFindChild == null ? MediaItemNodeManager.createMediaItemNode$default(this.nodeManager, parent, mediaId, mediaId, null, true, null, iconUri, extras, 40, null) : mediaItemNodeFindChild;
    }

    private final void createWidgetEntities(MediaItemNode parent, MediaItemNode section, AutoMediaItem item, String sectionTitle, int limitPerWidget, boolean isWidgetSubPageOnly) {
        Map<String, Integer> map;
        String str = item.getTitle() + '@' + sectionTitle;
        if (StringsKt__StringsJVMKt.equals$default(parent.getMediaItem().getMediaId(), MediaItemNodeManager.FirstLayerDrawerId.LIBRARY.name(), false, 2, null)) {
            map = this.widgetEntitiesCountLibraryTab;
        } else {
            map = this.widgetEntitiesCountHomeTab;
        }
        Integer num = map.get(sectionTitle);
        if (num == null) {
            num = 0;
            map.put(sectionTitle, num);
        }
        int iIntValue = num.intValue();
        if (iIntValue < limitPerWidget && !isWidgetSubPageOnly && !Intrinsics.areEqual(this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "adding top level item " + str + " on " + ((Object) parent.getMediaItem().getMediaId()) + " page");
            createEntityNode(parent, item, str, null);
            map.put(sectionTitle, Integer.valueOf(iIntValue + 1));
        }
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "adding sub-page item " + str + " on " + ((Object) parent.getMediaItem().getMediaId()) + " page");
        createEntityNode(section, item, str, null);
    }

    private final MediaItemNode createEntityNode(MediaItemNode parent, AutoMediaItem item, String mediaId, Bundle extras) {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, Intrinsics.stringPlus("Creating entity node with mediaId ", mediaId));
        Boolean isBrowsable = item.getIsBrowsable();
        boolean zBooleanValue = isBrowsable == null ? false : isBrowsable.booleanValue();
        if (Intrinsics.areEqual(this.context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
            MediaItemNode mediaItemNodeCreateMediaItemNode = this.nodeManager.createMediaItemNode(parent, mediaId, item.getTitle(), item.getSubTitle(), zBooleanValue, item, this.defaultUriProvider.getDefaultMediaIconLocalUri(), extras);
            if (parent.getMediaId() != null) {
                downloadMediaArtwork(mediaItemNodeCreateMediaItemNode);
            }
            return mediaItemNodeCreateMediaItemNode;
        }
        MediaItemNode mediaItemNodeFindChild = parent.findChild(mediaId);
        return mediaItemNodeFindChild == null ? MediaItemNodeManager.createMediaItemNode$default(this.nodeManager, parent, mediaId, item.getTitle(), item.getSubTitle(), zBooleanValue, item, null, extras, 64, null) : mediaItemNodeFindChild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, android.net.Uri] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void downloadMediaArtwork(MediaItemNode node) {
        ContentMetadata metadata;
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        StringBuilder sb = new StringBuilder();
        sb.append("downloadMediaArtwork node='");
        sb.append((Object) node.getMediaId());
        sb.append("', parentMediaId='");
        MediaItemNode parent = node.getParent();
        Uri imageUrl = null;
        sb.append((Object) (parent == null ? null : parent.getMediaId()));
        sb.append('\'');
        autoLog.debug(TAG2, sb.toString());
        AutoMediaItem autoMediaItem = node.getAutoMediaItem();
        if (autoMediaItem != null && (metadata = autoMediaItem.getMetadata()) != null) {
            imageUrl = LibraryExtensionsKt.getImageUrl(metadata);
        }
        Uri uri = imageUrl;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? localImageUri = this.imageUriProvider.getLocalImageUri(uri);
        objectRef.element = localImageUri;
        if (localImageUri == 0) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(objectRef, this, uri, node, null), 3, null);
        } else {
            this.nodeManager.updateNodeWithImageUri(node, localImageUri);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.provider.MediaBrowserNodeProvider$downloadMediaArtwork$1, reason: invalid class name */
    /* JADX INFO: compiled from: MediaBrowserNodeProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.provider.MediaBrowserNodeProvider$downloadMediaArtwork$1", f = "MediaBrowserNodeProvider.kt", i = {}, l = {398}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Uri> $localImageUri;
        final /* synthetic */ MediaItemNode $node;
        final /* synthetic */ Uri $remoteImageUrl;
        Object L$0;
        int label;
        final /* synthetic */ MediaBrowserNodeProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<Uri> objectRef, MediaBrowserNodeProvider mediaBrowserNodeProvider, Uri uri, MediaItemNode mediaItemNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$localImageUri = objectRef;
            this.this$0 = mediaBrowserNodeProvider;
            this.$remoteImageUrl = uri;
            this.$node = mediaItemNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$localImageUri, this.this$0, this.$remoteImageUrl, this.$node, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<Uri> objectRef;
            T t;
            String mediaId;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<Uri> objectRef2 = this.$localImageUri;
                LocalImageUriProvider localImageUriProvider = this.this$0.imageUriProvider;
                Uri uri = this.$remoteImageUrl;
                this.L$0 = objectRef2;
                this.label = 1;
                Object objCreateLocalImageUri = localImageUriProvider.createLocalImageUri(uri, this);
                if (objCreateLocalImageUri == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = objCreateLocalImageUri;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            if (this.$localImageUri.element != null) {
                this.this$0.nodeManager.updateNodeWithImageUri(this.$node, this.$localImageUri.element);
                MediaItemNode parent = this.$node.getParent();
                if (parent != null && (mediaId = parent.getMediaId()) != null) {
                    this.this$0.mediaBrowserService.notifyChildrenChanged(mediaId);
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final int getSeeMorePlayableHintValue(PresentationViewType presentationViewType) {
        int i = WhenMappings.$EnumSwitchMapping$1[presentationViewType.ordinal()];
        return (i == 1 || i != 2) ? 1 : 2;
    }

    private final void addSeeMoreNode(MediaItemNode parent, String widgetTitle, PresentationViewType seeMorePresentationViewType, Bundle extras) {
        String str = this.context.getString(R$string.dmusic_automotive_see_more) + '@' + widgetTitle;
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, getSeeMorePlayableHintValue(seeMorePresentationViewType));
        this.seeMoreNodeMap.put(widgetTitle, this.nodeManager.createSeeMoreNode(parent, str, this.defaultUriProvider.getSeeMoreIconLocalUri(), extras));
    }

    @VisibleForTesting
    public final MediaItemNode getSeeMoreNode(String widgetTitle) {
        Intrinsics.checkNotNullParameter(widgetTitle, "widgetTitle");
        MediaItemNode mediaItemNode = this.seeMoreNodeMap.get(widgetTitle);
        if (mediaItemNode == null) {
            return null;
        }
        return mediaItemNode;
    }

    public void expandMeskSeeMoreNode(AutoWidgetItem widgetInfo) {
        Intrinsics.checkNotNullParameter(widgetInfo, "widgetInfo");
        MediaItemNode seeMoreNode = getSeeMoreNode(widgetInfo.getTitle());
        if (seeMoreNode == null) {
            return;
        }
        for (AutoMediaItem autoMediaItem : widgetInfo.getEntities()) {
            createEntityNode(seeMoreNode, autoMediaItem, autoMediaItem.getTitle() + '@' + widgetInfo.getTitle(), null);
        }
    }

    public final List<MediaItemNode> createSearchResultNodes(Map<String, ? extends List<AutoMediaItem>> widgetsMap) {
        Intrinsics.checkNotNullParameter(widgetsMap, "widgetsMap");
        ArrayList arrayList = new ArrayList();
        this.nodeManager.getSearchResultsParent().removeAllChildren();
        int i = 0;
        for (Object obj : widgetsMap.entrySet()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            Map.Entry entry = (Map.Entry) obj;
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            Bundle bundle = new Bundle();
            bundle.putString(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_GROUP_TITLE, str);
            int i3 = 0;
            for (Object obj2 : list) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                AutoMediaItem autoMediaItem = (AutoMediaItem) obj2;
                String str2 = "SEARCH_RESULTS-" + i + '-' + i3 + '@' + ((Object) LibraryExtensionsKt.getIdentifier(autoMediaItem.getMetadata()));
                MediaItemNode mediaItemNodeCreateEntityNode = createEntityNode(this.nodeManager.getSearchResultsParent(), autoMediaItem, str2, bundle);
                arrayList.add(mediaItemNodeCreateEntityNode);
                this.nodeManager.addSearchResultNodeToIndex(str2, mediaItemNodeCreateEntityNode);
                i3 = i4;
            }
            i = i2;
        }
        return arrayList;
    }
}
