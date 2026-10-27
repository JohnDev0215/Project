package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import b.a.d.e.c.b.a;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class BydRecyclerView extends RefreshWrapperRecyclerView {
    public LoadStatus s;
    public a t;
    public a u;
    public int v;
    public View w;
    public int x;

    public enum LoadStatus {
        NORMAL,
        PULL_UP_LOADING,
        LOOSEN_LOADING,
        LOADING
    }

    public BydRecyclerView(Context context) {
        super(context);
        this.s = LoadStatus.NORMAL;
        this.v = 0;
    }

    private void setLoadViewMarginBottom(int i) {
        View view = this.w;
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i2 = this.v;
        if (i < (-i2) + 1) {
            i = (-i2) + 1;
        }
        if (i > 20) {
            return;
        }
        marginLayoutParams.bottomMargin = i;
        this.w.setLayoutParams(marginLayoutParams);
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.x = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean h() {
        return Build.VERSION.SDK_INT < 14 ? ViewCompat.canScrollVertically(this, 1) || getScrollY() < 0 : ViewCompat.canScrollVertically(this, 1);
    }

    public void i() {
        b.a.d.b.a.a("BydRecyclerView", "mLoadCreator = " + this.u);
        if (this.u == null) {
            return;
        }
        View view = this.w;
        if (view != null) {
            b(view);
        }
        this.u = null;
        this.w = null;
    }

    public final void j() {
        b.a.d.b.a.a("BydRecyclerView", "curStatus = " + this.s);
        if (this.s != LoadStatus.LOOSEN_LOADING) {
            return;
        }
        this.s = LoadStatus.LOADING;
        a aVar = this.u;
        if (aVar != null) {
            aVar.c(this.w);
        }
        WrapRecyclerView.a aVar2 = this.h;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.w;
        if (view == null || this.v != 0) {
            return;
        }
        int i3 = view.getLayoutParams().height;
        this.w.measure(i, i3 == -2 ? View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE) : i3 != -1 ? View.MeasureSpec.makeMeasureSpec(i3, 1073741824) : 0);
        this.v = this.w.getMeasuredHeight();
        b.a.d.b.a.a("BydRecyclerView", "mLoadViewHeight = " + this.v);
        int i4 = this.v;
        if (i4 > 0) {
            setLoadViewMarginBottom((-i4) + 1);
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            b.a.d.b.a.a("BydRecyclerView", "ACTION_DOWN------");
        } else if (action == 1) {
            b.a.d.b.a.a("BydRecyclerView", "ACTION_UP------curStatus = " + this.s);
            if (this.s == LoadStatus.PULL_UP_LOADING) {
                this.s = LoadStatus.LOOSEN_LOADING;
                j();
            }
        } else if (action == 2) {
            b.a.d.b.a.a("BydRecyclerView", "ACTION_MOVE------");
            if (h() || this.s == LoadStatus.LOADING || this.u == null || this.w == null) {
                return super.onTouchEvent(motionEvent);
            }
            int rawY = (int) ((motionEvent.getRawY() - this.x) * this.i);
            b.a.d.b.a.a("BydRecyclerView", "ACTION_MOVE------distanceY = " + rawY);
            if (rawY < 0) {
                this.s = LoadStatus.PULL_UP_LOADING;
                b.a.d.b.a.a("BydRecyclerView", "ACTION_MOVE------curStatus = " + this.s);
                scrollToPosition(getAdapter().getItemCount() - 1);
                int i = -rawY;
                int i2 = i - this.v;
                b.a.d.b.a.a("BydRecyclerView", "ACTION_MOVE------marginBottom = " + i2);
                setLoadViewMarginBottom(i2);
                a aVar = this.u;
                if (aVar != null) {
                    aVar.a(this.w, i, this.v);
                }
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setFootViewHolderOnDataSize(int i) {
        if (i < 10) {
            i();
        } else {
            setFooterView();
        }
    }

    public void setFooterView() {
        b.a.d.b.a.a("BydRecyclerView", "mLoadCreator = " + this.u);
        a aVar = this.t;
        if (aVar == null || this.u != null) {
            return;
        }
        this.u = aVar;
        View viewB = aVar.b(getContext(), this);
        if (viewB != null) {
            this.w = viewB;
            a(viewB);
        }
    }

    public BydRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = LoadStatus.NORMAL;
        this.v = 0;
    }

    public BydRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.s = LoadStatus.NORMAL;
        this.v = 0;
    }
}
