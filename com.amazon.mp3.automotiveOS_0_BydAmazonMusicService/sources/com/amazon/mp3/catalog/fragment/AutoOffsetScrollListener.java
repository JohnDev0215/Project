package com.amazon.mp3.catalog.fragment;

import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.catalog.fragment.helper.BrushPageScrollHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoOffsetScrollListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB7\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0001\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J \u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0016R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "view", "Landroid/view/View;", "viewIndexToAlign", "", "lastScrollPosition", "Landroid/util/Pair;", "onBindCallback", "Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$OnBindCallback;", "(Landroid/view/View;ILandroid/util/Pair;Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$OnBindCallback;)V", "TAG", "", "kotlin.jvm.PlatformType", "state", "Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$State;", "getView", "()Landroid/view/View;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "dy", "onScrolledAfterBinding", "OnBindCallback", "State", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class AutoOffsetScrollListener extends RecyclerView.OnScrollListener {
    private final String TAG;
    private final Pair<Integer, Integer> lastScrollPosition;
    private final OnBindCallback onBindCallback;
    private State state;
    private final View view;
    private final int viewIndexToAlign;

    /* JADX INFO: compiled from: AutoOffsetScrollListener.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$OnBindCallback;", "", "onBind", "", "view", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnBindCallback {
        void onBind(View view);
    }

    /* JADX INFO: compiled from: AutoOffsetScrollListener.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$State;", "", "(Ljava/lang/String;I)V", "NOT_INIT", "UNBOUND", "BOUND", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private enum State {
        NOT_INIT,
        UNBOUND,
        BOUND
    }

    protected final View getView() {
        return this.view;
    }

    public AutoOffsetScrollListener(@NonNull View view, int i, @NonNull Pair<Integer, Integer> lastScrollPosition, OnBindCallback onBindCallback) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(lastScrollPosition, "lastScrollPosition");
        this.view = view;
        this.viewIndexToAlign = i;
        this.lastScrollPosition = lastScrollPosition;
        this.onBindCallback = onBindCallback;
        this.TAG = AutoOffsetScrollListener.class.getSimpleName();
        this.state = State.NOT_INIT;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("can't align a view to the index: " + i + ". Index must be larger than 0.").toString());
        }
        Integer num = (Integer) lastScrollPosition.first;
        if (num != null && num.intValue() == i) {
            if (onBindCallback != null) {
                onBindCallback.onBind(view);
            }
            view.setY(((Number) lastScrollPosition.second).intValue());
            this.state = State.BOUND;
            return;
        }
        this.state = State.UNBOUND;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, dx, dy);
        State state = this.state;
        if (state == State.NOT_INIT) {
            return;
        }
        State state2 = State.BOUND;
        if (state == state2) {
            onScrolledAfterBinding(recyclerView, dx, dy);
            return;
        }
        if (state == State.UNBOUND && (recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (!(linearLayoutManager != null && linearLayoutManager.findFirstVisibleItemPosition() == this.viewIndexToAlign)) {
                if (!(linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == this.viewIndexToAlign)) {
                    return;
                }
            }
            int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition() == this.viewIndexToAlign ? linearLayoutManager.findFirstVisibleItemPosition() : linearLayoutManager.findLastVisibleItemPosition();
            View view = this.view;
            view.setY(view.getY() + BrushPageScrollHelper.getTopScrollOffset(recyclerView, iFindFirstVisibleItemPosition));
            OnBindCallback onBindCallback = this.onBindCallback;
            if (onBindCallback != null) {
                onBindCallback.onBind(this.view);
            }
            this.state = state2;
        }
    }

    public void onScrolledAfterBinding(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        View view = this.view;
        view.setY(view.getY() - dy);
    }
}
