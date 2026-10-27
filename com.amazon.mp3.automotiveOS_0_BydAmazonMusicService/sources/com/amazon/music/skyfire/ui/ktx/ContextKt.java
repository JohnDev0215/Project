package com.amazon.music.skyfire.ui.ktx;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"screenWidth", "", "Landroid/content/Context;", "getScreenWidth", "(Landroid/content/Context;)I", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ContextKt {
    public static final int getScreenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
