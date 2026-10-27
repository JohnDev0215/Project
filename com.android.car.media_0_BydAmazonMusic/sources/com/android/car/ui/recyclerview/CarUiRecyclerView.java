package com.android.car.ui.recyclerview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import spi.j1;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"Instantiatable"})
@TargetApi(28)
public interface CarUiRecyclerView {

    public interface a {
        void a(int i);
    }

    public interface b {
        void a();

        void b();
    }

    public interface c {
        void a();

        void b();
    }

    @Deprecated
    void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration);

    void addOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener);

    void addOnLayoutChangeListener(View.OnLayoutChangeListener onLayoutChangeListener);

    void addOnScrollListener(c cVar);

    int findFirstCompletelyVisibleItemPosition();

    int findFirstVisibleItemPosition();

    int findLastCompletelyVisibleItemPosition();

    int findLastVisibleItemPosition();

    RecyclerView.ViewHolder findViewHolderForAdapterPosition(int i);

    void focusableViewAvailable(View view);

    RecyclerView.Adapter<?> getAdapter();

    int getChildLayoutPosition(View view);

    Context getContext();

    @Nullable
    j1 getLayoutStyle();

    int getPaddingBottom();

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingTop();

    @NonNull
    View getView();

    @Deprecated
    void invalidateItemDecorations();

    boolean post(Runnable runnable);

    void removeOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener);

    void removeOnLayoutChangeListener(View.OnLayoutChangeListener onLayoutChangeListener);

    void removeOnScrollListener(c cVar);

    void scrollToPosition(int i);

    void setAdapter(RecyclerView.Adapter<?> adapter);

    void setAlpha(float f);

    void setClipToPadding(boolean z);

    @Deprecated
    void setItemAnimator(RecyclerView.ItemAnimator itemAnimator);

    void setLayoutStyle(@Nullable j1 j1Var);

    void setOnFlingListener(RecyclerView.OnFlingListener onFlingListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setPadding(int i, int i2, int i3, int i4);

    @Deprecated
    void setVerticalFadingEdgeEnabled(boolean z);
}
