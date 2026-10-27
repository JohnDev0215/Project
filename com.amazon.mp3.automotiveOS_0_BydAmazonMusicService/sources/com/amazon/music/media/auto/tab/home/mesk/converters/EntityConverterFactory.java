package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemContext;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityConverterFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverterFactory;", "", "()V", "entityToConverterMap", "", "LTouch/SyncInterface/v1_0/WidgetItemContext;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverter;", "getEntityConverter", ContextMappingConstants.WIDGET_ITEM_CONTEXT, "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityConverterFactory {
    public static final EntityConverterFactory INSTANCE = new EntityConverterFactory();
    private static final Map<WidgetItemContext, EntityConverter> entityToConverterMap = MapsKt__MapsKt.mapOf(TuplesKt.to(WidgetItemContext.ENTITY_ALBUM, new AlbumConverter()), TuplesKt.to(WidgetItemContext.ENTITY_ARTIST, new ArtistConverter()), TuplesKt.to(WidgetItemContext.ENTITY_GENRE, new GenreConverter()), TuplesKt.to(WidgetItemContext.ENTITY_TRACK, new TrackConverter()), TuplesKt.to(WidgetItemContext.ENTITY_STATION, new StationConverter()), TuplesKt.to(WidgetItemContext.ENTITY_CATALOG_PLAYLIST, new PlaylistConverter()), TuplesKt.to(WidgetItemContext.ENTITY_LIBRARY_PLAYLIST, new UserPlaylistConverter()));

    private EntityConverterFactory() {
    }

    public final EntityConverter getEntityConverter(WidgetItemContext widgetItemContext) {
        Intrinsics.checkNotNullParameter(widgetItemContext, "widgetItemContext");
        return entityToConverterMap.get(widgetItemContext);
    }
}
