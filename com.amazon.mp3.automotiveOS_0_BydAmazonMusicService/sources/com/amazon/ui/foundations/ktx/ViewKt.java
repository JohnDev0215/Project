package com.amazon.ui.foundations.ktx;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"collapseIf", "", "Landroid/view/View;", "flag", "", "hideIf", "DMMUIFoundations_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewKt {
    public static final void collapseIf(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public static final void hideIf(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 4 : 0);
    }
}
