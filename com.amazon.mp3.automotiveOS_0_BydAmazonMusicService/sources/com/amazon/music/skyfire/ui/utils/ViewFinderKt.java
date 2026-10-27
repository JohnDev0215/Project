package com.amazon.music.skyfire.ui.utils;

import android.app.Activity;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewFinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"findViews", "", "Landroid/app/Activity;", "obj", "", "Landroid/view/View;", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewFinderKt {
    public static /* synthetic */ void findViews$default(Activity activity, Object obj, int i, Object obj2) throws IllegalAccessException {
        if ((i & 1) != 0) {
            obj = activity;
        }
        findViews(activity, obj);
    }

    public static final void findViews(Activity activity, Object obj) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(obj, "obj");
        ViewFinder.INSTANCE.findViews(activity, obj);
    }

    public static final View findViews(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(obj, "obj");
        return ViewFinder.INSTANCE.findViews(view, obj);
    }

    public static /* synthetic */ View findViews$default(View view, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = view;
        }
        return findViews(view, obj);
    }
}
