package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.graphics.PointF;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsLayoutManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsLayoutManager;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "smoothScroller", "com/amazon/mp3/playback/view/lyrics/LyricsLayoutManager$smoothScroller$1", "Lcom/amazon/mp3/playback/view/lyrics/LyricsLayoutManager$smoothScroller$1;", "smoothScrollToPosition", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "position", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsLayoutManager extends LinearLayoutManager {
    private final Context mContext;
    private final LyricsLayoutManager$smoothScroller$1 smoothScroller;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.amazon.mp3.playback.view.lyrics.LyricsLayoutManager$smoothScroller$1] */
    public LyricsLayoutManager(final Context mContext) {
        super(mContext);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.smoothScroller = new LinearSmoothScroller(mContext) { // from class: com.amazon.mp3.playback.view.lyrics.LyricsLayoutManager$smoothScroller$1
            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int calculateTimeForScrolling(int dx) {
                return ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected int getVerticalSnapPreference() {
                return 0;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return this.this$0.computeScrollVectorForPosition(targetPosition);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
                return (boxStart + ((boxEnd - boxStart) / 2)) - (viewStart + ((viewEnd - viewStart) / 2));
            }
        };
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        setTargetPosition(position);
        startSmoothScroll(this.smoothScroller);
    }
}
