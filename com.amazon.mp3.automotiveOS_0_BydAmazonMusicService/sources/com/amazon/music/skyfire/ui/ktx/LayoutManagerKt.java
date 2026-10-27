package com.amazon.music.skyfire.ui.ktx;

import androidx.recyclerview.widget.LinearLayoutManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: LayoutManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"visibleItemRange", "Lkotlin/ranges/IntRange;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LayoutManagerKt {
    public static final IntRange visibleItemRange(LinearLayoutManager linearLayoutManager) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "<this>");
        return new IntRange(linearLayoutManager.findFirstVisibleItemPosition(), linearLayoutManager.findLastVisibleItemPosition());
    }
}
