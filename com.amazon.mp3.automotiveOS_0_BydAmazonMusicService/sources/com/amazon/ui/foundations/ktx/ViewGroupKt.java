package com.amazon.ui.foundations.ktx;

import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¨\u0006\u0003"}, d2 = {"Landroid/view/ViewGroup;", "", "collapseIfEmpty", "DMMUIFoundations_release"}, k = 2, mv = {1, 6, 0})
public final class ViewGroupKt {
    public static final void collapseIfEmpty(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        viewGroup.setVisibility(viewGroup.getChildCount() == 0 ? 8 : 0);
    }
}
