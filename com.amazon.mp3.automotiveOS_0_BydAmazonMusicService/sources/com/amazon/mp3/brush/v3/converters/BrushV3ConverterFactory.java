package com.amazon.mp3.brush.v3.converters;

import android.content.Context;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.RenderingType;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3ConverterFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004R \u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3ConverterFactory;", "", "", "key", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "getParentConverter", "getConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "getGenreDetailHeaderConverter", "getBaseDetailHeaderConverter", "getGridListHeaderConverter", "Lcom/amazon/mp3/brush/v3/converters/BrushV3SimpleHorizontalTileModelConverter;", "getPresetSimpleHorizontalTileConverter", "", "converterMap", "Ljava/util/Map;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3ConverterFactory {
    public static final BrushV3ConverterFactory INSTANCE = new BrushV3ConverterFactory();
    private static final Map<String, BaseModelConverter> converterMap;

    private BrushV3ConverterFactory() {
    }

    static {
        HashMap map = new HashMap();
        converterMap = map;
        BrushV3SectionGridConverter brushV3SectionGridConverter = new BrushV3SectionGridConverter();
        BrushV3VerticalTileConverter brushV3VerticalTileConverter = new BrushV3VerticalTileConverter();
        int i = 3;
        BrushV3SimpleVerticalTileConverter brushV3SimpleVerticalTileConverter = new BrushV3SimpleVerticalTileConverter(null, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        BrushV3HorizontalTileConverter brushV3HorizontalTileConverter = new BrushV3HorizontalTileConverter();
        BrushV3NavigationButtonConverter brushV3NavigationButtonConverter = new BrushV3NavigationButtonConverter();
        BrushV3ButtonNavigatorConverter brushV3ButtonNavigatorConverter = new BrushV3ButtonNavigatorConverter();
        BrushV3CarModeButtonNavigatorConverter brushV3CarModeButtonNavigatorConverter = new BrushV3CarModeButtonNavigatorConverter();
        BrushV3CarModeSingleListConverter brushV3CarModeSingleListConverter = new BrushV3CarModeSingleListConverter(0 == true ? 1 : 0, 0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        BrushV3FeaturedBarkerConverter brushV3FeaturedBarkerConverter = new BrushV3FeaturedBarkerConverter();
        BrushV3CarouselConverter brushV3CarouselConverter = new BrushV3CarouselConverter();
        BrushV3ShovelerConverter brushV3ShovelerConverter = new BrushV3ShovelerConverter();
        BrushV3SimpleShovelerConverter brushV3SimpleShovelerConverter = new BrushV3SimpleShovelerConverter();
        BrushV3FeaturedHorizontalTileConverter brushV3FeaturedHorizontalTileConverter = new BrushV3FeaturedHorizontalTileConverter();
        BrushV3GenreDetailHeaderConverter brushV3GenreDetailHeaderConverter = new BrushV3GenreDetailHeaderConverter();
        BrushV3BaseDetailHeaderConverter brushV3BaseDetailHeaderConverter = new BrushV3BaseDetailHeaderConverter();
        BrushV3ThumbnailConverter brushV3ThumbnailConverter = new BrushV3ThumbnailConverter();
        BrushV3MessageConverter brushV3MessageConverter = new BrushV3MessageConverter();
        BrushV3FeaturePlayConverter brushV3FeaturePlayConverter = new BrushV3FeaturePlayConverter();
        map.put("carouselKey", brushV3CarouselConverter);
        map.put("sectionGridKey", brushV3SectionGridConverter);
        map.put("shovelerKey", brushV3ShovelerConverter);
        map.put("simpleShovelerKey", brushV3SimpleShovelerConverter);
        map.put("FEATURED_PLAY", brushV3FeaturePlayConverter);
        map.put(RenderingType.LINK_NAVIGATOR.name(), brushV3NavigationButtonConverter);
        map.put(RenderingType.BUTTON_NAVIGATOR.name(), brushV3ButtonNavigatorConverter);
        map.put(RenderingType.PILL_NAVIGATOR.name(), brushV3ShovelerConverter);
        map.put(RenderingType.THUMBNAIL_NAVIGATOR.name(), brushV3ThumbnailConverter);
        map.put(RenderingType.THUMBNAIL_SHOWCASE.name(), brushV3ThumbnailConverter);
        map.put(RenderingType.THUMBNAIL_LISTING.name(), brushV3ThumbnailConverter);
        map.put(RenderingType.THUMBNAIL_SHOVELER.name(), brushV3ThumbnailConverter);
        map.put(RenderingType.VISUAL_SHOWCASE.name(), brushV3VerticalTileConverter);
        map.put(RenderingType.VISUAL_LISTING.name(), brushV3VerticalTileConverter);
        map.put(RenderingType.VISUAL_SHOVELER.name(), brushV3VerticalTileConverter);
        map.put(RenderingType.DESCRIPTIVE_SHOWCASE.name(), brushV3HorizontalTileConverter);
        map.put(RenderingType.DESCRIPTIVE_LISTING.name(), brushV3HorizontalTileConverter);
        map.put(RenderingType.DESCRIPTIVE_SHOVELER.name(), brushV3HorizontalTileConverter);
        map.put(RenderingType.FEATURED_BANNER.name(), brushV3FeaturedBarkerConverter);
        map.put(RenderingType.FEATURED_CARD.name(), brushV3FeaturedBarkerConverter);
        map.put(RenderingType.FEATURED_CAROUSEL.name(), brushV3FeaturedBarkerConverter);
        map.put(RenderingType.FEATURED_SHOVELER.name(), brushV3FeaturedHorizontalTileConverter);
        map.put(RenderingType.GENRE_DETAIL_HEADER.name(), brushV3GenreDetailHeaderConverter);
        map.put(RenderingType.UHD_DETAIL_HEADER.name(), brushV3BaseDetailHeaderConverter);
        map.put(RenderingType.THREED_DETAIL_HEADER.name(), brushV3BaseDetailHeaderConverter);
        map.put(RenderingType.TEXT_DESCRIPTION.name(), brushV3MessageConverter);
        map.put(RenderingType.CARMODE_VISUAL_SHOVELER.name(), brushV3SimpleVerticalTileConverter);
        map.put(RenderingType.CARMODE_THUMBNAIL_LISTING.name(), brushV3SimpleVerticalTileConverter);
        map.put(RenderingType.CARMODE_BUTTON_NAVIGATOR.name(), brushV3CarModeButtonNavigatorConverter);
        map.put(RenderingType.CARMODE_SINGLE_LIST.name(), brushV3CarModeSingleListConverter);
    }

    public final BaseModelConverter getParentConverter(String key, Context context) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (Intrinsics.areEqual(key, RenderingType.FEATURED_CAROUSEL.name())) {
            BaseModelConverter converter = getConverter("carouselKey");
            if (converter != null) {
                return (BrushV3CarouselConverter) converter;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3CarouselConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.VISUAL_SHOVELER.name())) {
            BaseModelConverter converter2 = getConverter("shovelerKey");
            if (converter2 != null) {
                return (BrushV3ShovelerConverter) converter2;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3ShovelerConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.CARMODE_VISUAL_SHOVELER.name()) || Intrinsics.areEqual(key, RenderingType.CARMODE_BUTTON_NAVIGATOR.name())) {
            BaseModelConverter converter3 = getConverter("simpleShovelerKey");
            if (converter3 != null) {
                return (BrushV3SimpleShovelerConverter) converter3;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3SimpleShovelerConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.FEATURED_SHOVELER.name())) {
            BaseModelConverter converter4 = getConverter("shovelerKey");
            if (converter4 != null) {
                return (BrushV3ShovelerConverter) converter4;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3ShovelerConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.DESCRIPTIVE_SHOVELER.name())) {
            BaseModelConverter converter5 = getConverter("shovelerKey");
            if (converter5 != null) {
                return (BrushV3ShovelerConverter) converter5;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3ShovelerConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.THUMBNAIL_SHOVELER.name())) {
            BaseModelConverter converter6 = getConverter("shovelerKey");
            if (converter6 != null) {
                return (BrushV3ShovelerConverter) converter6;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.v3.converters.BrushV3ShovelerConverter");
        }
        if (Intrinsics.areEqual(key, RenderingType.CARMODE_SINGLE_LIST.name())) {
            return getConverter("CARMODE_SINGLE_LIST");
        }
        if (Intrinsics.areEqual(key, RenderingType.PRESET_SINGLE_ENTITY.name()) || Intrinsics.areEqual(key, RenderingType.PRESET_MULTI_ENTITY.name())) {
            return getPresetSimpleHorizontalTileConverter(context);
        }
        if (Intrinsics.areEqual(key, "FEATURED_PLAY")) {
            return getConverter("FEATURED_PLAY");
        }
        if (getConverter(key) == null) {
            return null;
        }
        BaseModelConverter converter7 = getConverter("sectionGridKey");
        if (converter7 != null) {
            return (BaseContainerModelConverter) converter7;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.BaseContainerModelConverter<com.amazon.music.page.api.model.Widget>");
    }

    public final BaseModelConverter getConverter(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return converterMap.get(key);
    }

    public final SingleBaseModelConverter<Page> getGenreDetailHeaderConverter() {
        return new BrushV3GenreDetailHeaderConverter();
    }

    public final SingleBaseModelConverter<Page> getBaseDetailHeaderConverter() {
        return new BrushV3BaseDetailHeaderConverter();
    }

    public final SingleBaseModelConverter<Page> getGridListHeaderConverter() {
        return new BrushV3GridListHeaderConverter();
    }

    public final BrushV3SimpleHorizontalTileModelConverter getPresetSimpleHorizontalTileConverter(Context context) {
        return new BrushV3SimpleHorizontalTileModelConverter(null, null, context, 3, null);
    }
}
