package com.amazon.ui.foundations.utils;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpacerParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/ui/foundations/utils/SpacerParser;", "", "Landroid/content/res/Resources;", "resources", "", "getDeviceDensity", "", "px", "pixelsToDp", "dp", "dpToPixels", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final class SpacerParser {
    public static final SpacerParser INSTANCE = new SpacerParser();

    private SpacerParser() {
    }

    public final int pixelsToDp(Resources resources, int px) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        return (int) (px / getDeviceDensity(resources));
    }

    public final int dpToPixels(Resources resources, int dp) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        return (int) (dp * getDeviceDensity(resources));
    }

    private final float getDeviceDensity(Resources resources) {
        return resources.getDisplayMetrics().densityDpi / 160.0f;
    }
}
