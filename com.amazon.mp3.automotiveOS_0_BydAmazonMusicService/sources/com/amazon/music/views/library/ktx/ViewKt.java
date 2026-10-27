package com.amazon.music.views.library.ktx;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"setHorizontalMargin", "", "Landroid/view/View;", "value", "", "setHorizontalPadding", "DMMViewLibrary_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewKt {
    public static final void setHorizontalMargin(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.leftMargin = i;
        marginLayoutParams.rightMargin = i;
        view.requestLayout();
    }

    public static final void setHorizontalPadding(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setPadding(i, view.getPaddingTop(), i, view.getPaddingBottom());
    }
}
