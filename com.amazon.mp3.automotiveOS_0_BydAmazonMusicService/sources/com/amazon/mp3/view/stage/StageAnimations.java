package com.amazon.mp3.view.stage;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageAnimations.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/view/stage/StageAnimations;", "", "Landroid/util/DisplayMetrics;", "displayMetrics", "Landroid/util/DisplayMetrics;", "", "deviceWidth", "F", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageAnimations {
    private final float deviceWidth;
    private final DisplayMetrics displayMetrics;

    public StageAnimations(Context context) {
        Resources resources;
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        DisplayMetrics displayMetrics = null;
        if (applicationContext != null && (resources = applicationContext.getResources()) != null) {
            displayMetrics = resources.getDisplayMetrics();
        }
        this.displayMetrics = displayMetrics;
        this.deviceWidth = displayMetrics == null ? 0.0f : displayMetrics.widthPixels;
    }
}
