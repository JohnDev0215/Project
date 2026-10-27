package com.amazon.music.explore.widgets.listeners;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.amazon.music.explore.ktx.SnapHelperExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapOnScrollListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0016J \u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/explore/widgets/listeners/SnapOnScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "snapHelper", "Landroidx/recyclerview/widget/SnapHelper;", "snapOnPositionChangeListener", "Lcom/amazon/music/explore/widgets/listeners/SnapOnPositionChangeListener;", "behavior", "Lcom/amazon/music/explore/widgets/listeners/SnapOnScrollListener$Behavior;", "(Landroidx/recyclerview/widget/SnapHelper;Lcom/amazon/music/explore/widgets/listeners/SnapOnPositionChangeListener;Lcom/amazon/music/explore/widgets/listeners/SnapOnScrollListener$Behavior;)V", "currentPosition", "", "notifyPositionChange", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onScrollStateChanged", "newState", "onScrolled", "dx", "dy", "Behavior", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SnapOnScrollListener extends RecyclerView.OnScrollListener {
    private Behavior behavior;
    private int currentPosition;
    private final SnapHelper snapHelper;
    private SnapOnPositionChangeListener snapOnPositionChangeListener;

    /* JADX INFO: compiled from: SnapOnScrollListener.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/explore/widgets/listeners/SnapOnScrollListener$Behavior;", "", "(Ljava/lang/String;I)V", "ON_SCROLL", "ON_SCROLL_STATE_IDLE", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Behavior {
        ON_SCROLL,
        ON_SCROLL_STATE_IDLE
    }

    public /* synthetic */ SnapOnScrollListener(SnapHelper snapHelper, SnapOnPositionChangeListener snapOnPositionChangeListener, Behavior behavior, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(snapHelper, (i & 2) != 0 ? null : snapOnPositionChangeListener, (i & 4) != 0 ? Behavior.ON_SCROLL : behavior);
    }

    public SnapOnScrollListener(SnapHelper snapHelper, SnapOnPositionChangeListener snapOnPositionChangeListener, Behavior behavior) {
        Intrinsics.checkNotNullParameter(snapHelper, "snapHelper");
        Intrinsics.checkNotNullParameter(behavior, "behavior");
        this.snapHelper = snapHelper;
        this.snapOnPositionChangeListener = snapOnPositionChangeListener;
        this.behavior = behavior;
        this.currentPosition = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (this.behavior == Behavior.ON_SCROLL) {
            notifyPositionChange(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        if (this.behavior == Behavior.ON_SCROLL_STATE_IDLE && newState == 0) {
            notifyPositionChange(recyclerView);
        }
    }

    private final void notifyPositionChange(RecyclerView recyclerView) {
        int snapPosition = SnapHelperExtKt.getSnapPosition(this.snapHelper, recyclerView);
        if (this.currentPosition != snapPosition) {
            SnapOnPositionChangeListener snapOnPositionChangeListener = this.snapOnPositionChangeListener;
            if (snapOnPositionChangeListener != null) {
                snapOnPositionChangeListener.onSnapPositionChange(snapPosition);
            }
            this.currentPosition = snapPosition;
        }
    }
}
