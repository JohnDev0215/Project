package com.amazon.mp3.brush.converters;

import com.amazon.music.find.model.FindRenderingType;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushLayoutTypeUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushLayoutTypeUtils;", "", "()V", "tileLayout16X9Map", "", "", "", "tileLayoutMap", "get16X9TileLayoutType", "renderingType", "getTileLayoutType", "isListLayoutType", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushLayoutTypeUtils {
    public static final BrushLayoutTypeUtils INSTANCE = new BrushLayoutTypeUtils();
    private static final Map<String, Integer> tileLayout16X9Map;
    private static final Map<String, Integer> tileLayoutMap;

    private BrushLayoutTypeUtils() {
    }

    static {
        HashMap map = new HashMap();
        tileLayoutMap = map;
        map.put("VISUAL_SHOVELER", 5);
        map.put("VISUAL_LISTING", 1);
        map.put("VISUAL_SHOWCASE", 1);
        map.put("DESCRIPTIVE_LISTING", 0);
        map.put("DESCRIPTIVE_SHOVELER", 10);
        map.put("PLAYBACK_CONTAINER_MULTI_ITEM", 20);
        map.put("DESCRIPTIVE_SHOWCASE", 0);
        map.put("BUTTON_NAVIGATOR", 3);
        map.put("LINK_NAVIGATOR", 8);
        map.put("PILL_NAVIGATOR", 6);
        map.put("FEATURED_CAROUSEL", 4);
        map.put("FEATURED_BANNER", 4);
        map.put("FEATURED_CARD", 4);
        map.put("FEATURED_PLAY", 4);
        map.put("FEATURED_SHOVELER", 9);
        map.put("LIVE_STREAM_BARKER", 4);
        map.put("VISUAL_SHOVELER_COMPACT", 17);
        map.put("VISUAL_SHOVELER_LARGE", 22);
        map.put(FindRenderingType.COMPACT_DESCRIPTIVE_SHOWCASE.name(), 13);
        map.put(FindRenderingType.COMPACT_LINK_NAVIGATOR.name(), 13);
        map.put(FindRenderingType.COMPACT_LINK_NAVIGATOR_LEGACY.name(), 14);
        HashMap map2 = new HashMap();
        tileLayout16X9Map = map2;
        map2.put("VISUAL_SHOVELER", 11);
        map2.put("VISUAL_LISTING", 12);
    }

    public final int getTileLayoutType(String renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        Integer num = tileLayoutMap.get(renderingType);
        if (num == null) {
            return 2;
        }
        return num.intValue();
    }

    public final int get16X9TileLayoutType(String renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        Integer num = tileLayout16X9Map.get(renderingType);
        if (num == null) {
            return 2;
        }
        return num.intValue();
    }

    public final boolean isListLayoutType(String renderingType) {
        Intrinsics.checkNotNullParameter(renderingType, "renderingType");
        return Intrinsics.areEqual(renderingType, "VISUAL_LISTING") || Intrinsics.areEqual(renderingType, "DESCRIPTIVE_LISTING");
    }
}
