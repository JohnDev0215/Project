package com.amazon.music.views.library.recyclerview.indexer;

import android.graphics.Rect;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Views.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\u001a\u001a\u0010\u0005\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001\u001a\f\u0010\u0007\u001a\u00020\u0006*\u00020\u0000H\u0002\"\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroid/view/View;", "", "x", "y", "", "isPointInsideBounds", "", "getWindowCoordinates", "getWindowY", "(Landroid/view/View;)F", "windowY", "DMMViewLibrary_release"}, k = 2, mv = {1, 6, 0})
@JvmName(name = "ViewExtensions")
public final class ViewExtensions {
    public static final float getWindowY(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return getWindowCoordinates(view)[1];
    }

    public static final boolean isPointInsideBounds(View view, float f, float f2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0] - view.getWidth(), iArr[1] - view.getHeight(), iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        return rect.contains((int) f, (int) f2);
    }

    private static final int[] getWindowCoordinates(View view) {
        int[] iArr = {0, (int) view.getY()};
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 != null) {
            view2.getLocationInWindow(iArr);
        }
        return iArr;
    }
}
