package com.android.car.ui.recyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.preference.PreferenceFragment;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.RecyclerViewAdapterAdapterV1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import spi.e1;
import spi.j1;
import spi.k1;
import spi.o9;
import spi.p9;
import spi.pc;
import spi.w5;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public final class RecyclerViewAdapterV1 extends FrameLayout implements CarUiRecyclerView, PreferenceFragment.b {

    @Nullable
    private RecyclerView.Adapter<?> mAdapter;

    @NonNull
    private final List<RecyclerView.OnChildAttachStateChangeListener> mChildAttachStateChangeListeners;
    private int mHeight;

    @Nullable
    private j1 mLayoutStyle;

    @Nullable
    private spi.e mOEMAdapter;

    @Nullable
    private p9 mOEMRecyclerView;

    @Nullable
    private ProxyRecyclerView mRecyclerView;

    @NonNull
    private final List<CarUiRecyclerView.c> mScrollListeners;
    private int mWidth;

    public RecyclerViewAdapterV1(@NonNull Context context) {
        this(context, null);
    }

    private static int toInternalScrollState(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 2;
        }
        return 1;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
    }

    public void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration, int i) {
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void addOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        this.mChildAttachStateChangeListeners.add(onChildAttachStateChangeListener);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void addOnScrollListener(@NonNull CarUiRecyclerView.c cVar) {
        this.mScrollListeners.add(cVar);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        return this.mOEMRecyclerView.getView().canScrollHorizontally(i);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        return this.mOEMRecyclerView.getView().canScrollVertically(i);
    }

    public void clearOnChildAttachStateChangeListeners() {
        this.mChildAttachStateChangeListeners.clear();
        this.mOEMRecyclerView.s();
    }

    public void clearOnScrollListeners() {
        this.mScrollListeners.clear();
        this.mOEMRecyclerView.s();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public int findFirstCompletelyVisibleItemPosition() {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            return p9Var.findFirstCompletelyVisibleItemPosition();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public int findFirstVisibleItemPosition() {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            return p9Var.findFirstVisibleItemPosition();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public int findLastCompletelyVisibleItemPosition() {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            return p9Var.findLastCompletelyVisibleItemPosition();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public int findLastVisibleItemPosition() {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            return p9Var.findLastVisibleItemPosition();
        }
        return 0;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public RecyclerView.ViewHolder findViewHolderForAdapterPosition(int i) {
        pc pcVarH = this.mOEMRecyclerView.h();
        if (pcVarH instanceof RecyclerViewAdapterAdapterV1.ViewHolderAdapterV1) {
            return ((RecyclerViewAdapterAdapterV1.ViewHolderAdapterV1) pcVarH).getViewHolder();
        }
        return null;
    }

    public RecyclerView.ViewHolder findViewHolderForLayoutPosition(int i) {
        pc pcVarP = this.mOEMRecyclerView.p();
        if (pcVarP instanceof RecyclerViewAdapterAdapterV1.ViewHolderAdapterV1) {
            return ((RecyclerViewAdapterAdapterV1.ViewHolderAdapterV1) pcVarP).getViewHolder();
        }
        return null;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public RecyclerView.Adapter<?> getAdapter() {
        return this.mAdapter;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getChildLayoutPosition(View view) {
        return this.mOEMRecyclerView.o();
    }

    public int getEndAfterPadding() {
        return this.mOEMRecyclerView.q();
    }

    @Nullable
    public RecyclerView.ItemDecoration getItemDecorationAt(int i) {
        return null;
    }

    public int getItemDecorationCount() {
        return 0;
    }

    @Nullable
    @Deprecated
    public RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public j1 getLayoutStyle() {
        return this.mLayoutStyle;
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingBottom() {
        p9 p9Var = this.mOEMRecyclerView;
        return p9Var != null ? p9Var.getView().getPaddingBottom() : super.getPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingEnd() {
        return getContext().getResources().getConfiguration().getLayoutDirection() == 0 ? getPaddingRight() : getPaddingLeft();
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingLeft() {
        p9 p9Var = this.mOEMRecyclerView;
        return p9Var != null ? p9Var.getView().getPaddingLeft() : super.getPaddingLeft();
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingRight() {
        p9 p9Var = this.mOEMRecyclerView;
        return p9Var != null ? p9Var.getView().getPaddingRight() : super.getPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingStart() {
        return getContext().getResources().getConfiguration().getLayoutDirection() == 0 ? getPaddingLeft() : getPaddingRight();
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingTop() {
        p9 p9Var = this.mOEMRecyclerView;
        return p9Var != null ? p9Var.getView().getPaddingTop() : super.getPaddingTop();
    }

    @Override // com.android.car.ui.preference.PreferenceFragment.b
    @Nullable
    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public View getRecyclerViewChildAt(int i) {
        return this.mOEMRecyclerView.v();
    }

    public int getRecyclerViewChildCount() {
        return this.mOEMRecyclerView.u();
    }

    public int getScrollState() {
        return toInternalScrollState(this.mOEMRecyclerView.d());
    }

    public int getStartAfterPadding() {
        return this.mOEMRecyclerView.e();
    }

    public int getTotalSpace() {
        return this.mOEMRecyclerView.a();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    @NonNull
    public View getView() {
        return this;
    }

    public boolean hasFixedSize() {
        return this.mOEMRecyclerView.m();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void invalidateItemDecorations() {
    }

    public void onChildViewAttachedToWindow(@NonNull View view) {
        Iterator<RecyclerView.OnChildAttachStateChangeListener> it = this.mChildAttachStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onChildViewAttachedToWindow(view);
        }
    }

    public void onChildViewDetachedFromWindow(@NonNull View view) {
        Iterator<RecyclerView.OnChildAttachStateChangeListener> it = this.mChildAttachStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onChildViewDetachedFromWindow(view);
        }
    }

    public void onScrollStateChanged(@NonNull p9 p9Var, int i) {
        for (CarUiRecyclerView.c cVar : this.mScrollListeners) {
            toInternalScrollState(i);
            cVar.a();
        }
    }

    public void onScrolled(@NonNull p9 p9Var, int i, int i2) {
        Iterator<CarUiRecyclerView.c> it = this.mScrollListeners.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    public void removeItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
    }

    public void removeItemDecorationAt(int i) {
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void removeOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        this.mChildAttachStateChangeListeners.remove(onChildAttachStateChangeListener);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void removeOnScrollListener(@NonNull CarUiRecyclerView.c cVar) {
        this.mScrollListeners.remove(cVar);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void scrollToPosition(int i) {
        this.mOEMRecyclerView.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setAdapter(RecyclerView.Adapter<?> adapter) {
        if (adapter == 0) {
            this.mAdapter = null;
            this.mOEMAdapter = null;
            this.mOEMRecyclerView.b();
            return;
        }
        Object obj = this.mAdapter;
        if (obj instanceof CarUiRecyclerView.b) {
            ((CarUiRecyclerView.b) obj).b();
        }
        this.mAdapter = adapter;
        this.mOEMAdapter = new RecyclerViewAdapterAdapterV1(getContext(), adapter);
        this.mOEMRecyclerView.b();
        if (adapter instanceof CarUiRecyclerView.b) {
            ((CarUiRecyclerView.b) adapter).a();
        }
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setAlpha(float f) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.i();
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.getView().setBackground(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.getView().setBackgroundColor(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        if (this.mOEMRecyclerView != null) {
            this.mOEMRecyclerView.getView().setBackground(getResources().getDrawable(i));
        }
    }

    @Override // android.view.ViewGroup, com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setClipToPadding(boolean z) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.c();
        }
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence charSequence) {
        super.setContentDescription(charSequence);
        this.mOEMRecyclerView.g();
    }

    public void setHasFixedSize(boolean z) {
        this.mOEMRecyclerView.l();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            setLayoutStyle(e1.a(layoutManager));
        } else {
            setLayoutStyle(k1.a(layoutManager));
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        Rect rect;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            rect = new Rect(Math.max(marginLayoutParams.leftMargin, marginLayoutParams.getMarginStart()), marginLayoutParams.topMargin, marginLayoutParams.rightMargin, Math.max(marginLayoutParams.bottomMargin, marginLayoutParams.getMarginEnd()));
            marginLayoutParams.setMargins(0, 0, 0, 0);
            marginLayoutParams.setMarginStart(0);
            marginLayoutParams.setMarginEnd(0);
        } else {
            rect = null;
        }
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            ViewGroup.LayoutParams layoutParams2 = p9Var.getView().getLayoutParams();
            int i = layoutParams.width;
            if (i == 0) {
                i = -1;
            }
            layoutParams2.width = i;
            int i2 = layoutParams.height;
            layoutParams2.height = i2 != 0 ? i2 : -1;
            if (rect != null && (layoutParams2 instanceof ViewGroup.MarginLayoutParams)) {
                ((ViewGroup.MarginLayoutParams) layoutParams2).setMargins(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
        this.mWidth = layoutParams.width;
        this.mHeight = layoutParams.height;
        super.setLayoutParams(layoutParams);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setLayoutStyle(final j1 j1Var) {
        this.mLayoutStyle = j1Var;
        if (j1Var == null) {
            this.mOEMRecyclerView.j();
            return;
        }
        new w5() { // from class: com.android.car.ui.recyclerview.RecyclerViewAdapterV1.1
            @Override // spi.w5
            public int getLayoutType() {
                return j1Var.getLayoutType();
            }

            @Override // spi.w5
            public int getOrientation() {
                return j1Var.getOrientation();
            }

            @Override // spi.w5
            public boolean getReverseLayout() {
                return j1Var.getReverseLayout();
            }

            @Override // spi.w5
            public int getSpanCount() {
                return j1Var.getSpanCount();
            }

            public int getSpanSize(int i) {
                j1 j1Var2 = j1Var;
                if (j1Var2 instanceof e1) {
                    return ((e1) j1Var2).d.getSpanSize(i);
                }
                return 1;
            }
        };
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.j();
        }
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setOnFlingListener(RecyclerView.OnFlingListener onFlingListener) {
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setPadding(int i, int i2, int i3, int i4) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.getView().setPadding(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        p9 p9Var = this.mOEMRecyclerView;
        if (p9Var != null) {
            p9Var.getView().setPaddingRelative(i, i2, i3, i4);
        }
    }

    public void setRecyclerViewOEMV1(@NonNull p9 p9Var, @Nullable o9 o9Var) {
        this.mOEMRecyclerView = p9Var;
        w5 layoutStyle = p9Var.getLayoutStyle();
        if (layoutStyle.getLayoutType() == 1) {
            e1 e1Var = new e1();
            e1Var.c = layoutStyle.getReverseLayout();
            int spanCount = layoutStyle.getSpanCount();
            if (spanCount <= 0) {
                throw new AssertionError("Span count must be bigger than 0");
            }
            e1Var.a = spanCount;
            e1Var.b = layoutStyle.getOrientation();
            if (o9Var != null) {
                o9Var.getSize();
            }
            this.mLayoutStyle = e1Var;
        } else {
            k1 k1Var = new k1();
            k1Var.b = layoutStyle.getReverseLayout();
            k1Var.a = layoutStyle.getOrientation();
            if (o9Var != null) {
                o9Var.getSize();
            }
            this.mLayoutStyle = k1Var;
        }
        FrameLayout frameLayout = new FrameLayout(getContext());
        ProxyRecyclerView proxyRecyclerView = new ProxyRecyclerView(getContext(), this);
        this.mRecyclerView = proxyRecyclerView;
        frameLayout.addView(proxyRecyclerView);
        this.mOEMRecyclerView.n();
        this.mOEMRecyclerView.k();
        addView(this.mOEMRecyclerView.getView(), new ViewGroup.MarginLayoutParams(this.mWidth, this.mHeight));
    }

    public void setSpanSizeLookup(@NonNull GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        j1 j1Var = this.mLayoutStyle;
        if (j1Var instanceof e1) {
            ((e1) j1Var).d = spanSizeLookup;
            setLayoutStyle(j1Var);
        }
    }

    public void smoothScrollBy(int i, int i2) {
        this.mOEMRecyclerView.t();
    }

    public void smoothScrollToPosition(int i) {
        this.mOEMRecyclerView.r();
    }

    public RecyclerViewAdapterV1(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerViewAdapterV1(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        this.mScrollListeners = new ArrayList();
        this.mChildAttachStateChangeListeners = new ArrayList();
        setBackground(null);
        setPadding(0, 0, 0, 0);
    }
}
