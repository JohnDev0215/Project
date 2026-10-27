package com.amazon.ui.foundations.ktx;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¨\u0006\u0003"}, d2 = {"Landroid/widget/TextView;", "", "collapseIfEmpty", "DMMUIFoundations_release"}, k = 2, mv = {1, 6, 0})
public final class TextViewKt {
    public static final void collapseIfEmpty(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        CharSequence text = textView.getText();
        textView.setVisibility(text == null || text.length() == 0 ? 8 : 0);
    }
}
