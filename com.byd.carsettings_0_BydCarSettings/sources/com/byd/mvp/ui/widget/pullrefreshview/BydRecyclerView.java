package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import b.b.m.f.f.c.a;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class BydRecyclerView extends RefreshWrapperRecyclerView {
    public LoadStatus r;
    public a s;
    public a t;
    public int u;
    public View v;
    public int w;

    public enum LoadStatus {
        NORMAL,
        PULL_UP_LOADING,
        LOOSEN_LOADING,
        LOADING
    }

    public BydRecyclerView(Context context) {
        super(context);
        this.r = LoadStatus.NORMAL;
        this.u = 0;
    }

    private void setLoadViewMarginBottom(int i) {
        View view = this.v;
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i2 = this.u;
        if (i < (-i2) + 1) {
            i = (-i2) + 1;
        }
        if (i > 20) {
            return;
        }
        marginLayoutParams.bottomMargin = i;
        this.v.setLayoutParams(marginLayoutParams);
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.w = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean h() {
        return Build.VERSION.SDK_INT < 14 ? ViewCompat.canScrollVertically(this, 1) || getScrollY() < 0 : ViewCompat.canScrollVertically(this, 1);
    }

    public void i() {
        b.b.m.c.a.a("BydRecyclerView", "mLoadCreator = " + this.t);
        if (this.t == null) {
            return;
        }
        View view = this.v;
        if (view != null) {
            b(view);
        }
        this.t = null;
        this.v = null;
    }

    public final void j() {
        b.b.m.c.a.a("BydRecyclerView", "curStatus = " + this.r);
        if (this.r != LoadStatus.LOOSEN_LOADING) {
            return;
        }
        this.r = LoadStatus.LOADING;
        a aVar = this.t;
        if (aVar != null) {
            aVar.c(this.v);
        }
        WrapRecyclerView.a aVar2 = this.g;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View view = this.v;
        if (view == null || this.u != 0) {
            return;
        }
        int i3 = view.getLayoutParams().height;
        this.v.measure(i, i3 == -2 ? View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE) : i3 != -1 ? View.MeasureSpec.makeMeasureSpec(i3, 1073741824) : 0);
        this.u = this.v.getMeasuredHeight();
        b.b.m.c.a.a("BydRecyclerView", "mLoadViewHeight = " + this.u);
        int i4 = this.u;
        if (i4 > 0) {
            setLoadViewMarginBottom((-i4) + 1);
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            b.b.m.c.a.a("BydRecyclerView", "ACTION_DOWN------");
        } else if (action == 1) {
            b.b.m.c.a.a("BydRecyclerView", "ACTION_UP------curStatus = " + this.r);
            if (this.r == LoadStatus.PULL_UP_LOADING) {
                this.r = LoadStatus.LOOSEN_LOADING;
                j();
            }
        } else if (action == 2) {
            b.b.m.c.a.a("BydRecyclerView", "ACTION_MOVE------");
            if (h() || this.r == LoadStatus.LOADING || this.t == null || this.v == null) {
                return super.onTouchEvent(motionEvent);
            }
            int rawY = (int) ((motionEvent.getRawY() - this.w) * this.h);
            b.b.m.c.a.a("BydRecyclerView", "ACTION_MOVE------distanceY = " + rawY);
            if (rawY < 0) {
                this.r = LoadStatus.PULL_UP_LOADING;
                b.b.m.c.a.a("BydRecyclerView", "ACTION_MOVE------curStatus = " + this.r);
                scrollToPosition(getAdapter().getItemCount() - 1);
                int i = -rawY;
                int i2 = i - this.u;
                b.b.m.c.a.a("BydRecyclerView", "ACTION_MOVE------marginBottom = " + i2);
                setLoadViewMarginBottom(i2);
                a aVar = this.t;
                if (aVar != null) {
                    aVar.a(this.v, i, this.u);
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
        b.b.m.c.a.a("BydRecyclerView", "mLoadCreator = " + this.t);
        a aVar = this.s;
        if (aVar == null || this.t != null) {
            return;
        }
        this.t = aVar;
        View viewB = aVar.b(getContext(), this);
        if (viewB != null) {
            this.v = viewB;
            a(viewB);
        }
    }

    public BydRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = LoadStatus.NORMAL;
        this.u = 0;
    }

    public BydRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = LoadStatus.NORMAL;
        this.u = 0;
    }
}
