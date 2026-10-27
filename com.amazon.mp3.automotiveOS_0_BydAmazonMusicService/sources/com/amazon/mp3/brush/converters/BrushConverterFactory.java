package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.cache.AlbumCache;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.recentlyplayed.views.RecentsVerticalTileConverter;
import com.amazon.music.find.converter.BrushSearchResponseMessageConverter;
import com.amazon.music.find.model.FindRenderingType;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Entity;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushConverterFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b%\u0010&J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004J@\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fJ\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u000fJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\fJ\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bJ*\u0010\u001c\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\fJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010 \u001a\u00020\u001f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010!\u001a\u00020\u001f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004R \u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00060\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushConverterFactory;", "", "Lcom/amazon/musicensembleservice/brush/Block;", "data", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "getParentConverter", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "cachedActionProviderImpl", "Lcom/amazon/mp3/cache/AlbumCache;", "cache", "getAlbumConverter", "", "key", "getConverter", "", "layoutType", "getRecentlyPlayedConverter", "getCustomerProfileConverter", "albumCache", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "getDetailHeaderConverter", "getRefinementsConverter", "parentConfiguration", "getDescriptiveRowItemConverter", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "getPresetShovelerConverter", "Lcom/amazon/mp3/brush/converters/BrushSimpleHorizontalTileConverter;", "getSimpleHorizontalTileConverter", "getSearchSimpleHorizontalTileConverter", "", "converterMap", "Ljava/util/Map;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushConverterFactory {
    public static final BrushConverterFactory INSTANCE = new BrushConverterFactory();
    private static final Map<String, BaseModelConverter> converterMap;

    private BrushConverterFactory() {
    }

    static {
        BrushSectionGridConverter brushSectionGridConverter = new BrushSectionGridConverter();
        BrushVerticalTileConverter brushVerticalTileConverter = new BrushVerticalTileConverter();
        BrushHorizontalTileConverter brushHorizontalTileConverter = new BrushHorizontalTileConverter();
        BrushPlaybackContainerHorizontalTileConverter brushPlaybackContainerHorizontalTileConverter = new BrushPlaybackContainerHorizontalTileConverter();
        BrushNavigationButtonConverter brushNavigationButtonConverter = new BrushNavigationButtonConverter();
        BrushButtonNavigatorConverter brushButtonNavigatorConverter = new BrushButtonNavigatorConverter(null, 1, 0 == true ? 1 : 0);
        BrushFeaturedBarkerConverter brushFeaturedBarkerConverter = new BrushFeaturedBarkerConverter();
        BrushCarouselConverter brushCarouselConverter = new BrushCarouselConverter();
        BrushMessageConverter brushMessageConverter = new BrushMessageConverter();
        BrushShovelerConverter brushShovelerConverter = new BrushShovelerConverter();
        BrushFeaturedPlayConverter brushFeaturedPlayConverter = new BrushFeaturedPlayConverter();
        BrushFeaturedHorizontalConverter brushFeaturedHorizontalConverter = new BrushFeaturedHorizontalConverter();
        BrushLivestreamBarkerConverter brushLivestreamBarkerConverter = new BrushLivestreamBarkerConverter();
        BrushCompactHorizontalConverter brushCompactHorizontalConverter = new BrushCompactHorizontalConverter();
        BrushSearchResponseMessageConverter brushSearchResponseMessageConverter = new BrushSearchResponseMessageConverter();
        BrushPodcastTileConverter brushPodcastTileConverter = new BrushPodcastTileConverter();
        BrushPlaybackContainerConverter brushPlaybackContainerConverter = new BrushPlaybackContainerConverter();
        VideoStoryTileConverter videoStoryTileConverter = new VideoStoryTileConverter();
        HashMap map = new HashMap();
        converterMap = map;
        map.put("carouselKey", brushCarouselConverter);
        map.put("sectionGridKey", brushSectionGridConverter);
        map.put("shovelerKey", brushShovelerConverter);
        map.put("pillNavigatorKey", brushShovelerConverter);
        map.put("VISUAL_SHOWCASE", brushVerticalTileConverter);
        map.put("VISUAL_LISTING", brushVerticalTileConverter);
        map.put("VISUAL_SHOVELER", brushVerticalTileConverter);
        map.put("VISUAL_SHOVELER_COMPACT", brushVerticalTileConverter);
        map.put("VISUAL_SHOVELER_LARGE", brushVerticalTileConverter);
        map.put("DESCRIPTIVE_SHOWCASE", brushHorizontalTileConverter);
        map.put("DESCRIPTIVE_LISTING", brushHorizontalTileConverter);
        map.put("DESCRIPTIVE_SHOVELER", brushHorizontalTileConverter);
        map.put(FindRenderingType.COMPACT_DESCRIPTIVE_SHOWCASE.name(), brushCompactHorizontalConverter);
        map.put("PLAYBACK_CONTAINER_MULTI_ITEM", brushPlaybackContainerHorizontalTileConverter);
        map.put("BUTTON_NAVIGATOR", brushButtonNavigatorConverter);
        map.put("LINK_NAVIGATOR", brushNavigationButtonConverter);
        map.put(FindRenderingType.COMPACT_LINK_NAVIGATOR.name(), brushNavigationButtonConverter);
        map.put(FindRenderingType.COMPACT_LINK_NAVIGATOR_LEGACY.name(), brushNavigationButtonConverter);
        map.put(FindRenderingType.LINK_TEXT_DESCRIPTION.name(), brushSearchResponseMessageConverter);
        map.put("PILL_NAVIGATOR", brushNavigationButtonConverter);
        map.put("FEATURED_CAROUSEL", brushFeaturedBarkerConverter);
        map.put("FEATURED_BANNER", brushFeaturedBarkerConverter);
        map.put("FEATURED_CARD", brushFeaturedBarkerConverter);
        map.put("TEXT_DESCRIPTION", brushMessageConverter);
        map.put("FEATURED_PLAY", brushFeaturedPlayConverter);
        map.put("FEATURED_SHOVELER", brushFeaturedHorizontalConverter);
        map.put("LIVE_STREAM_BARKER", brushLivestreamBarkerConverter);
        map.put("podcast-view", brushPodcastTileConverter);
        map.put("playbackContainerKey", brushPlaybackContainerConverter);
        map.put("videoStoryTileKey", videoStoryTileConverter);
    }

    public final BaseModelConverter getParentConverter(Block data, Context context) {
        Intrinsics.checkNotNullParameter(data, "data");
        String renderingType = data.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "data.renderingType");
        return getParentConverter(renderingType, context);
    }

    public final BaseModelConverter getAlbumConverter(Block data, ModelConfiguration modelConfiguration, CachedCapabilityActionProviderImpl cachedActionProviderImpl, Context context, AlbumCache cache) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (BrushAlbumDetailSectionConverter.INSTANCE.isAlbumDetailUseCase(data)) {
            return new BrushAlbumDetailSectionConverter(modelConfiguration, cachedActionProviderImpl, cache);
        }
        String renderingType = data.getRenderingType();
        Intrinsics.checkNotNullExpressionValue(renderingType, "data.renderingType");
        return getParentConverter(renderingType, context);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final BaseModelConverter getParentConverter(String key, Context context) {
        Intrinsics.checkNotNullParameter(key, "key");
        switch (key.hashCode()) {
            case -1887028559:
                if (key.equals("PILL_NAVIGATOR")) {
                    BaseModelConverter converter = getConverter("pillNavigatorKey");
                    if (converter != null) {
                        return (BrushShovelerConverter) converter;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case -1850661517:
                if (key.equals("VISUAL_SHOVELER_COMPACT")) {
                    BaseModelConverter converter2 = getConverter("shovelerKey");
                    if (converter2 != null) {
                        return (BrushShovelerConverter) converter2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case -1784632959:
                if (key.equals("FEATURED_SHOVELER")) {
                    BaseModelConverter converter3 = getConverter("shovelerKey");
                    if (converter3 != null) {
                        return (BrushShovelerConverter) converter3;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case -1442479535:
                if (key.equals("FEATURED_CAROUSEL")) {
                    BaseModelConverter converter4 = getConverter("carouselKey");
                    if (converter4 != null) {
                        return (BrushCarouselConverter) converter4;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushCarouselConverter");
                }
                break;
            case -1269001533:
                if (key.equals("DESCRIPTIVE_SHOVELER")) {
                    BaseModelConverter converter5 = getConverter("shovelerKey");
                    if (converter5 != null) {
                        return (BrushShovelerConverter) converter5;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case -1129422053:
                if (key.equals("PRESETS_SHOVELER")) {
                    return getPresetShovelerConverter(context);
                }
                break;
            case -1082884933:
                if (key.equals("PLAYBACK_CONTAINER_MULTI_ITEM")) {
                    if (!FMPMFeatureGating.PLAYBACK_CONTAINER.isEnabled()) {
                        return getConverter("sectionGridKey");
                    }
                    BaseModelConverter converter6 = getConverter("playbackContainerKey");
                    if (converter6 != null) {
                        return (BrushPlaybackContainerConverter) converter6;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushPlaybackContainerConverter");
                }
                break;
            case -300099921:
                if (key.equals("VISUAL_SHOVELER")) {
                    BaseModelConverter converter7 = getConverter("shovelerKey");
                    if (converter7 != null) {
                        return (BrushShovelerConverter) converter7;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case 779156875:
                if (key.equals("VISUAL_SHOVELER_LARGE")) {
                    BaseModelConverter converter8 = getConverter("shovelerKey");
                    if (converter8 != null) {
                        return (BrushShovelerConverter) converter8;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushShovelerConverter");
                }
                break;
            case 2100352165:
                if (key.equals("FEATURED_PLAY")) {
                    BaseModelConverter converter9 = getConverter(key);
                    if (converter9 != null) {
                        return (BrushFeaturedPlayConverter) converter9;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.brush.converters.BrushFeaturedPlayConverter");
                }
                break;
        }
        if (getConverter(key) == null) {
            return null;
        }
        BaseModelConverter converter10 = getConverter("sectionGridKey");
        if (converter10 != null) {
            return (BaseContainerModelConverter) converter10;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.converter.BaseContainerModelConverter<com.amazon.musicensembleservice.brush.Block>");
    }

    public final BaseModelConverter getConverter(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return converterMap.get(key);
    }

    public final BaseModelConverter getRecentlyPlayedConverter(int layoutType, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (layoutType == 1 || layoutType == 5) {
            return new RecentsVerticalTileConverter(context);
        }
        return null;
    }

    public final BaseModelConverter getCustomerProfileConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BrushCustomerProfileConverter(context);
    }

    public static /* synthetic */ SingleBaseModelConverter getDetailHeaderConverter$default(BrushConverterFactory brushConverterFactory, Context context, ModelConfiguration modelConfiguration, AlbumCache albumCache, int i, Object obj) {
        if ((i & 2) != 0) {
            modelConfiguration = null;
        }
        if ((i & 4) != 0) {
            albumCache = null;
        }
        return brushConverterFactory.getDetailHeaderConverter(context, modelConfiguration, albumCache);
    }

    public final SingleBaseModelConverter<Entity> getDetailHeaderConverter(Context context, ModelConfiguration modelConfiguration, AlbumCache albumCache) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BrushDetailHeaderConverter(context, modelConfiguration, albumCache);
    }

    public final SingleBaseModelConverter<Entity> getRefinementsConverter(ModelConfiguration modelConfiguration) {
        return new BrushRefinementsConverter(modelConfiguration);
    }

    public final BaseModelConverter getDescriptiveRowItemConverter(ModelConfiguration modelConfiguration, ModelConfiguration parentConfiguration, AlbumCache albumCache) {
        return new BrushDescriptiveRowItemConverter(modelConfiguration, parentConfiguration, albumCache);
    }

    public final BaseContainerModelConverter<Block> getPresetShovelerConverter(Context context) {
        return new BrushPresetShovelerConverter(context);
    }

    public final BrushSimpleHorizontalTileConverter getSimpleHorizontalTileConverter(Context context) {
        return new BrushSimpleHorizontalTileConverter(context);
    }

    public final BrushSimpleHorizontalTileConverter getSearchSimpleHorizontalTileConverter(Context context) {
        return new BrushDetailedSimpleHorizontalTileConverter(context);
    }
}
