package com.amazon.mp3.util;

import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.recyclerview.indexer.FastScroller;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FastScrollerHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/util/FastScrollerHelper;", "", "()V", "initFastScroller", "", "fastScroller", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "doAfterInit", "Lkotlin/Function1;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FastScrollerHelper {
    public static final FastScrollerHelper INSTANCE = new FastScrollerHelper();

    private FastScrollerHelper() {
    }

    public final void initFastScroller(final FastScroller fastScroller, RecyclerView recyclerView, final Function1<? super FastScroller, Unit> doAfterInit) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(doAfterInit, "doAfterInit");
        if (fastScroller == null) {
            return;
        }
        fastScroller.setRecyclerView(recyclerView);
        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (recyclerView.getChildCount() != 0) {
            doAfterInit.invoke(fastScroller);
            fastScroller.setVisibility(0);
        } else {
            if (adapter == null) {
                return;
            }
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.amazon.mp3.util.FastScrollerHelper$initFastScroller$2$1
                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    if (adapter.getItemCount() > 0) {
                        adapter.unregisterAdapterDataObserver(this);
                        doAfterInit.invoke(fastScroller);
                        fastScroller.setVisibility(0);
                    }
                }
            });
        }
    }
}
