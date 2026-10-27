package com.amazon.music.media.auto.tab.library.skyfire;

import CoreInterface.v1_0.Method;
import Touch.SyncInterface.v1_0.EntityIdType;
import Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod;
import Touch.SyncInterface.v1_0.WidgetContext;
import Touch.SyncInterface.v1_0.WidgetItemContext;
import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.media.auto.AutoBrowserConstants;
import com.amazon.music.media.auto.R$string;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.provider.SubDeviceType;
import com.amazon.music.media.auto.provider.SubDeviceTypeProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkyfireContentMetadataConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\"\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\"\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J0\u0010\u0016\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\"\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J&\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00180\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0018J\"\u0010\"\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J(\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00180\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018H\u0002J\"\u0010%\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u0018\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J4\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\n2\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u001b2\b\u0010,\u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010-\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J>\u0010.\u001a*\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00180/j\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0018`02\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0018H\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/music/media/auto/tab/library/skyfire/SkyfireContentMetadataConverter;", "", "ContentPlaybackUtils", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "context", "Landroid/content/Context;", "(Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;Landroid/content/Context;)V", "getContentPlaybackUtils", "()Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "TAG", "", "kotlin.jvm.PlatformType", "convertAlbum", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetSyncElement", "LTouch/SyncInterface/v1_0/WidgetSyncElement;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "id", "convertArtist", "convertCatalogPlaylist", "convertItemSyncElement", "convertItemTracks", "tracksMetadata", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "trackIndex", "", "convertLibraryPlaylist", "convertResponse", "", "LTouch/SyncInterface/v1_0/WidgetContext;", "skyfireResponse", "LCoreInterface/v1_0/Method;", "convertStation", "convertSyncElements", "widgetSyncElements", "convertTrack", "convertTracksMetadata", "createAutoMediaItem", "title", "subTitle", "metadata", "contentType", "blockTitle", "getSubtitleForEntityType", "insertWidgetsMapKeys", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "supportedWidgetTypes", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SkyfireContentMetadataConverter {
    private final ContentPlaybackUtil ContentPlaybackUtils;
    private final String TAG;
    private final Context context;

    /* JADX INFO: compiled from: SkyfireContentMetadataConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[SubDeviceType.values().length];
            iArr[SubDeviceType.GOOGLE_ASSISTANT.ordinal()] = 1;
            iArr[SubDeviceType.WAZE_ANDROID.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[WidgetContext.values().length];
            iArr2[WidgetContext.SHOVELER_MY_PRESETS.ordinal()] = 1;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[WidgetItemContext.values().length];
            iArr3[WidgetItemContext.ENTITY_ALBUM.ordinal()] = 1;
            iArr3[WidgetItemContext.ENTITY_LIBRARY_PLAYLIST.ordinal()] = 2;
            iArr3[WidgetItemContext.ENTITY_CATALOG_PLAYLIST.ordinal()] = 3;
            iArr3[WidgetItemContext.ENTITY_STATION.ordinal()] = 4;
            iArr3[WidgetItemContext.ENTITY_PODCAST.ordinal()] = 5;
            iArr3[WidgetItemContext.ENTITY_ARTIST.ordinal()] = 6;
            iArr3[WidgetItemContext.ENTITY_TRACK.ordinal()] = 7;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public SkyfireContentMetadataConverter(ContentPlaybackUtil ContentPlaybackUtils, Context context) {
        Intrinsics.checkNotNullParameter(ContentPlaybackUtils, "ContentPlaybackUtils");
        Intrinsics.checkNotNullParameter(context, "context");
        this.ContentPlaybackUtils = ContentPlaybackUtils;
        this.context = context;
        this.TAG = SkyfireContentMetadataConverter.class.getSimpleName();
    }

    protected final ContentPlaybackUtil getContentPlaybackUtils() {
        return this.ContentPlaybackUtils;
    }

    public final Map<WidgetContext, List<AutoMediaItem>> convertResponse(List<? extends Method> skyfireResponse) {
        Intrinsics.checkNotNullParameter(skyfireResponse, "skyfireResponse");
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        autoLog.debug(TAG, "Converting Skyfire response for library tab");
        Map<WidgetContext, List<AutoMediaItem>> mapEmptyMap = MapsKt__MapsKt.emptyMap();
        for (Method method : skyfireResponse) {
            if (method instanceof SetWidgetSyncElementsMethod) {
                List<WidgetSyncElement> listElements = ((SetWidgetSyncElementsMethod) method).elements();
                Intrinsics.checkNotNullExpressionValue(listElements, "method.elements()");
                mapEmptyMap = convertSyncElements(listElements);
            } else {
                AutoLog autoLog2 = AutoLog.INSTANCE;
                String TAG2 = this.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog2.error(TAG2, "The method is not SetWidgetSyncElementsMethod");
            }
        }
        return mapEmptyMap;
    }

    private final Map<WidgetContext, List<AutoMediaItem>> convertSyncElements(List<? extends WidgetSyncElement> widgetSyncElements) {
        int i;
        Unit unit;
        SubDeviceTypeProvider subDeviceTypeProvider = SubDeviceTypeProvider.INSTANCE;
        List<WidgetContext> supported_widget_types = subDeviceTypeProvider.isMultiLevelMediaBrowserHierarchy() ? AutoBrowserConstants.INSTANCE.getSUPPORTED_WIDGET_TYPES() : AutoBrowserConstants.INSTANCE.getSUPPORTED_WIDGET_TYPES_NAVIAPP();
        LinkedHashMap<WidgetContext, List<AutoMediaItem>> linkedHashMapInsertWidgetsMapKeys = insertWidgetsMapKeys(supported_widget_types);
        SubDeviceType latestSubDeviceType = subDeviceTypeProvider.getLatestSubDeviceType();
        int i2 = latestSubDeviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[latestSubDeviceType.ordinal()];
        int i3 = i2 != 1 ? i2 != 2 ? 50 : 6 : 20;
        int i4 = 10;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(widgetSyncElements, 10));
        for (WidgetSyncElement widgetSyncElement : widgetSyncElements) {
            WidgetContext widgetContext = widgetSyncElement.widgetContext();
            if (widgetContext != null && supported_widget_types.contains(widgetContext)) {
                CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                if (widgetContext == WidgetContext.SHOVELER_LIBRARY_TRACKS) {
                    List<ContentMetadata> listConvertTracksMetadata = convertTracksMetadata(widgetSyncElement);
                    if (listConvertTracksMetadata == null) {
                        AutoLog autoLog = AutoLog.INSTANCE;
                        String TAG = this.TAG;
                        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                        autoLog.error(TAG, "The tracksMetadata is null, won't display songs.");
                        i = i4;
                    } else {
                        List<WidgetItemSyncElement> listItems = widgetSyncElement.items();
                        Intrinsics.checkNotNullExpressionValue(listItems, "widgetSyncElement.items()");
                        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listItems, i4));
                        int i5 = 0;
                        for (WidgetItemSyncElement widgetItemSyncElement : listItems) {
                            Intrinsics.checkNotNullExpressionValue(widgetItemSyncElement, "widgetItemSyncElement");
                            AutoMediaItem autoMediaItemConvertItemTracks = convertItemTracks(widgetSyncElement, widgetItemSyncElement, listConvertTracksMetadata, i5);
                            if (autoMediaItemConvertItemTracks != null) {
                                copyOnWriteArrayList.add(autoMediaItemConvertItemTracks);
                            }
                            arrayList2.add(Integer.valueOf(i5));
                            i5++;
                            i4 = 10;
                        }
                        i = i4;
                    }
                } else {
                    List<WidgetItemSyncElement> listItems2 = widgetSyncElement.items();
                    Intrinsics.checkNotNullExpressionValue(listItems2, "widgetSyncElement.items()");
                    i = 10;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listItems2, 10));
                    for (WidgetItemSyncElement widgetItemSyncElement2 : listItems2) {
                        Intrinsics.checkNotNullExpressionValue(widgetItemSyncElement2, "widgetItemSyncElement");
                        AutoMediaItem autoMediaItemConvertItemSyncElement = convertItemSyncElement(widgetSyncElement, widgetItemSyncElement2);
                        if (autoMediaItemConvertItemSyncElement == null) {
                            unit = null;
                        } else {
                            if (copyOnWriteArrayList.size() < i3) {
                                copyOnWriteArrayList.add(autoMediaItemConvertItemSyncElement);
                            }
                            unit = Unit.INSTANCE;
                        }
                        arrayList3.add(unit);
                    }
                }
                AutoLog autoLog2 = AutoLog.INSTANCE;
                String TAG2 = this.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog2.debug(TAG2, "For library widget: " + widgetContext + ", it has " + copyOnWriteArrayList.size() + " entities. Called from subDeviceType: " + SubDeviceTypeProvider.INSTANCE.getLatestSubDeviceType());
                linkedHashMapInsertWidgetsMapKeys.put(widgetContext, copyOnWriteArrayList);
            } else {
                i = i4;
            }
            arrayList.add(Unit.INSTANCE);
            i4 = i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<WidgetContext, List<AutoMediaItem>> entry : linkedHashMapInsertWidgetsMapKeys.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return MapsKt__MapsKt.toMap(linkedHashMap);
    }

    private final String getSubtitleForEntityType(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement) {
        WidgetContext widgetContext = widgetSyncElement.widgetContext();
        if ((widgetContext == null ? -1 : WhenMappings.$EnumSwitchMapping$1[widgetContext.ordinal()]) == 1) {
            WidgetItemContext widgetItemContext = widgetItemSyncElement.widgetItemContext();
            int i = widgetItemContext != null ? WhenMappings.$EnumSwitchMapping$2[widgetItemContext.ordinal()] : -1;
            if (i == 1) {
                return this.context.getString(R$string.dmusic_automotive_presets_album);
            }
            if (i == 2) {
                return this.context.getString(R$string.dmusic_automotive_presets_user_playlist);
            }
            if (i == 3) {
                return this.context.getString(R$string.dmusic_automotive_presets_playlist);
            }
            if (i == 4) {
                return this.context.getString(R$string.dmusic_automotive_presets_station);
            }
            if (i == 5) {
                return this.context.getString(R$string.dmusic_automotive_presets_podcast);
            }
            return widgetItemSyncElement.secondaryText();
        }
        return widgetItemSyncElement.secondaryText();
    }

    @VisibleForTesting
    public final LinkedHashMap<WidgetContext, List<AutoMediaItem>> insertWidgetsMapKeys(List<? extends WidgetContext> supportedWidgetTypes) {
        Intrinsics.checkNotNullParameter(supportedWidgetTypes, "supportedWidgetTypes");
        LinkedHashMap<WidgetContext, List<AutoMediaItem>> linkedHashMap = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedWidgetTypes, 10));
        Iterator<T> it = supportedWidgetTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(linkedHashMap.put((WidgetContext) it.next(), new ArrayList()));
        }
        return linkedHashMap;
    }

    private final List<ContentMetadata> convertTracksMetadata(WidgetSyncElement widgetSyncElement) {
        ArrayList arrayList = new ArrayList();
        List<WidgetItemSyncElement> listItems = widgetSyncElement.items();
        if (listItems == null) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
            autoLog.error(TAG, "Cannot convert null track list");
            return null;
        }
        for (WidgetItemSyncElement widgetItemSyncElement : listItems) {
            String strEntityId = widgetItemSyncElement.entityId();
            Uri trackUri = strEntityId == null ? null : getContentPlaybackUtils().getTrackUri(strEntityId);
            if (trackUri == null) {
                trackUri = Uri.EMPTY;
            }
            Uri uri = trackUri;
            String strEntityId2 = widgetItemSyncElement.entityId();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String strPrimaryText = widgetItemSyncElement.primaryText();
            String strImage = widgetItemSyncElement.image();
            String strSecondaryText = widgetItemSyncElement.secondaryText();
            Intrinsics.checkNotNullExpressionValue(uri, "track.entityId()?.let { …asin = it) } ?: Uri.EMPTY");
            arrayList.add(new TrackMetadata(null, uri, null, strEntityId2, strPrimaryText, strImage, null, null, null, null, null, strSecondaryText, null, null, null, null, null, null, null, linkedHashSet, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1574971, 15, null));
        }
        return arrayList;
    }

    private final AutoMediaItem convertItemTracks(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, List<? extends ContentMetadata> tracksMetadata, int trackIndex) {
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return new AutoMediaItem(strPrimaryText, subtitleForEntityType, null, tracksMetadata.get(trackIndex), null, 2, tracksMetadata, trackIndex, widgetItemSyncElement.primaryText(), null, 532, null);
    }

    @VisibleForTesting
    public final AutoMediaItem convertItemSyncElement(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement) {
        AutoMediaItem autoMediaItemConvertAlbum;
        Intrinsics.checkNotNullParameter(widgetSyncElement, "widgetSyncElement");
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        String strEntityId = widgetItemSyncElement.entityId();
        if (strEntityId == null) {
            return null;
        }
        WidgetItemContext widgetItemContext = widgetItemSyncElement.widgetItemContext();
        int i = widgetItemContext == null ? -1 : WhenMappings.$EnumSwitchMapping$2[widgetItemContext.ordinal()];
        if (i == 1) {
            autoMediaItemConvertAlbum = convertAlbum(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else if (i == 2) {
            autoMediaItemConvertAlbum = convertLibraryPlaylist(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else if (i == 3) {
            autoMediaItemConvertAlbum = convertCatalogPlaylist(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else if (i == 4) {
            autoMediaItemConvertAlbum = convertStation(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else if (i == 6) {
            autoMediaItemConvertAlbum = convertArtist(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else if (i == 7) {
            autoMediaItemConvertAlbum = convertTrack(widgetSyncElement, widgetItemSyncElement, strEntityId);
        } else {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
            autoLog.error(TAG, "Do not support the element type, will not start playback");
            return null;
        }
        return autoMediaItemConvertAlbum;
    }

    private final AutoMediaItem createAutoMediaItem(String title, String subTitle, ContentMetadata metadata, int contentType, String blockTitle) {
        return new AutoMediaItem(title, subTitle, null, metadata, null, contentType, null, 0, blockTitle, null, 724, null);
    }

    private final AutoMediaItem convertAlbum(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        AlbumMetadata albumMetadata = new AlbumMetadata(null, this.ContentPlaybackUtils.getAlbumUri(widgetItemSyncElement.secondaryText(), widgetItemSyncElement.primaryText(), id), null, id, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, null, widgetItemSyncElement.secondaryText(), null, null, new LinkedHashSet(), false, null, null, null, null, null, null, null, 1046213, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText, subtitleForEntityType, albumMetadata, 0, widgetItemSyncElement.primaryText());
    }

    private final AutoMediaItem convertArtist(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        ArtistMetadata artistMetadata = new ArtistMetadata(null, this.ContentPlaybackUtils.getArtistUri(id), null, null, id, widgetItemSyncElement.primaryText(), null, widgetItemSyncElement.image(), null, null, null, false, null, null, false, null, null, false, false, null, 1048397, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText, subtitleForEntityType, artistMetadata, 4, widgetItemSyncElement.primaryText());
    }

    private final AutoMediaItem convertStation(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        Uri stationUri;
        if (widgetItemSyncElement.entityIdType() == EntityIdType.STATION_SEED_ID) {
            stationUri = this.ContentPlaybackUtils.getStationUriWithSeed(id);
        } else {
            stationUri = this.ContentPlaybackUtils.getStationUri(id);
        }
        StationMetadata stationMetadata = new StationMetadata(null, stationUri, null, id, widgetItemSyncElement.primaryText(), null, null, null, widgetItemSyncElement.image(), MapsKt__MapsKt.emptyMap(), new LinkedHashSet(), null, null, null, 14565, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText, subtitleForEntityType, stationMetadata, 3, widgetItemSyncElement.primaryText());
    }

    private final AutoMediaItem convertLibraryPlaylist(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        UserPlaylistMetadata userPlaylistMetadata = new UserPlaylistMetadata(null, this.ContentPlaybackUtils.getUserPlaylistUri(), null, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, null, null, true, id, null, null, null, false, 15589, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText, subtitleForEntityType, userPlaylistMetadata, 1, widgetItemSyncElement.primaryText());
    }

    private final AutoMediaItem convertCatalogPlaylist(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        PlaylistMetadata playlistMetadata = new PlaylistMetadata(null, this.ContentPlaybackUtils.getPlaylistUri(id), null, id, widgetItemSyncElement.primaryText(), widgetItemSyncElement.image(), null, null, null, null, null, new LinkedHashSet(), CollectionsKt__CollectionsKt.emptyList(), false, null, 0 == true ? 1 : 0, null, null, false, null, false, null, null, false, null, 33548229, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText, subtitleForEntityType, playlistMetadata, 1, widgetItemSyncElement.primaryText());
    }

    private final AutoMediaItem convertTrack(WidgetSyncElement widgetSyncElement, WidgetItemSyncElement widgetItemSyncElement, String id) {
        Uri trackUri = this.ContentPlaybackUtils.getTrackUri(id);
        if (trackUri == null) {
            trackUri = Uri.EMPTY;
        }
        Uri uri = trackUri;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String strPrimaryText = widgetItemSyncElement.primaryText();
        String strImage = widgetItemSyncElement.image();
        String strSecondaryText = widgetItemSyncElement.secondaryText();
        Intrinsics.checkNotNullExpressionValue(uri, "ContentPlaybackUtils.get…i(asin = id) ?: Uri.EMPTY");
        TrackMetadata trackMetadata = new TrackMetadata(null, uri, null, id, strPrimaryText, strImage, null, null, null, null, null, strSecondaryText, null, null, null, null, null, null, null, linkedHashSet, "AUDIO", null, 0, null, null, null, null, null, null, false, null, false, null, null, null, null, -1574971, 15, null);
        String subtitleForEntityType = getSubtitleForEntityType(widgetSyncElement, widgetItemSyncElement);
        String strPrimaryText2 = widgetItemSyncElement.primaryText();
        if (strPrimaryText2 == null) {
            return null;
        }
        return createAutoMediaItem(strPrimaryText2, subtitleForEntityType, trackMetadata, 2, widgetItemSyncElement.primaryText());
    }
}
