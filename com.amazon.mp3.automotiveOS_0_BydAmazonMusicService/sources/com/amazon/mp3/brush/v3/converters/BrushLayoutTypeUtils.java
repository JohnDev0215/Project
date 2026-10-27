package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.RenderingType;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushLayoutTypeUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushLayoutTypeUtils;", "", "()V", "tileLayoutMap", "", "Lcom/amazon/music/page/api/model/RenderingType;", "", "tileLayoutMap16x9", "get16X9TileLayoutType", "renderingType", "getPodcastEpisodeTileType", "getPodcastShowTileType", "getTileLayoutType", "isListLayoutType", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushLayoutTypeUtils {
    public static final BrushLayoutTypeUtils INSTANCE = new BrushLayoutTypeUtils();
    private static final Map<RenderingType, Integer> tileLayoutMap;
    private static final Map<RenderingType, Integer> tileLayoutMap16x9;

    private BrushLayoutTypeUtils() {
    }

    static {
        HashMap map = new HashMap();
        tileLayoutMap = map;
        map.put(RenderingType.BUTTON_NAVIGATOR, 3);
        map.put(RenderingType.LINK_NAVIGATOR, 8);
        map.put(RenderingType.PILL_NAVIGATOR, 6);
        map.put(RenderingType.THUMBNAIL_NAVIGATOR, 7);
        map.put(RenderingType.THUMBNAIL_SHOWCASE, 15);
        map.put(RenderingType.THUMBNAIL_LISTING, 7);
        map.put(RenderingType.THUMBNAIL_SHOVELER, 16);
        map.put(RenderingType.VISUAL_SHOWCASE, 1);
        RenderingType renderingType = RenderingType.VISUAL_LISTING;
        map.put(renderingType, 1);
        RenderingType renderingType2 = RenderingType.VISUAL_SHOVELER;
        map.put(renderingType2, 5);
        map.put(RenderingType.DESCRIPTIVE_SHOWCASE, 0);
        map.put(RenderingType.DESCRIPTIVE_LISTING, 0);
        map.put(RenderingType.DESCRIPTIVE_SHOVELER, 10);
        map.put(RenderingType.FEATURED_BANNER, 4);
        map.put(RenderingType.FEATURED_SHOVELER, 9);
        map.put(RenderingType.CARMODE_VISUAL_SHOVELER, 21);
        map.put(RenderingType.CARMODE_THUMBNAIL_LISTING, 21);
        HashMap map2 = new HashMap();
        tileLayoutMap16x9 = map2;
        map2.put(renderingType2, 11);
        map2.put(renderingType, 12);
    }

    public final int getTileLayoutType(RenderingType renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        Integer num = tileLayoutMap.get(renderingType);
        if (num == null) {
            return 2;
        }
        return num.intValue();
    }

    public final int get16X9TileLayoutType(RenderingType renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        Integer num = tileLayoutMap16x9.get(renderingType);
        if (num == null) {
            return 2;
        }
        return num.intValue();
    }

    public final boolean isListLayoutType(RenderingType renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        return renderingType == RenderingType.VISUAL_LISTING || renderingType == RenderingType.DESCRIPTIVE_LISTING || renderingType == RenderingType.THUMBNAIL_LISTING;
    }

    public final int getPodcastEpisodeTileType(RenderingType renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        if (isListLayoutType(renderingType)) {
            return 2;
        }
        return getTileLayoutType(renderingType);
    }

    public final int getPodcastShowTileType(RenderingType renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        if (isListLayoutType(renderingType)) {
            return 2;
        }
        return getTileLayoutType(renderingType);
    }
}
