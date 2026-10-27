package com.byd.mvp.ui.widget.pullrefreshview;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.byd.mvp.ui.widget.pullrefreshview.WrapRecyclerView;
import l4.a;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
public class BydRecyclerView extends RefreshWrapperRecyclerView {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public LoadStatus f2978u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public a f2979v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public a f2980w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2981x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f2982y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f2983z;

    public enum LoadStatus {
        NORMAL,
        PULL_UP_LOADING,
        LOOSEN_LOADING,
        LOADING
    }

    public BydRecyclerView(Context context) {
        super(context);
        this.f2978u = LoadStatus.NORMAL;
        this.f2981x = 0;
    }

    private void setLoadViewMarginBottom(int i8) {
        View view = this.f2982y;
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i9 = this.f2981x;
        if (i8 < (-i9) + 1) {
            i8 = (-i9) + 1;
        }
        if (i8 > 20) {
            return;
        }
        marginLayoutParams.bottomMargin = i8;
        this.f2982y.setLayoutParams(marginLayoutParams);
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f2983z = (int) motionEvent.getRawY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean i() {
        return Build.VERSION.SDK_INT < 14 ? t.d(this, 1) || getScrollY() < 0 : t.d(this, 1);
    }

    public void j() {
        x3.a.a("BydRecyclerView", "mLoadCreator = " + this.f2980w);
        if (this.f2980w == null) {
            return;
        }
        View view = this.f2982y;
        if (view != null) {
            c(view);
        }
        this.f2980w = null;
        this.f2982y = null;
    }

    public void k() {
        x3.a.a("BydRecyclerView", "mLoadCreator = " + this.f2980w);
        a aVar = this.f2979v;
        if (aVar == null || this.f2980w != null) {
            return;
        }
        this.f2980w = aVar;
        View viewB = aVar.b(getContext(), this);
        if (viewB != null) {
            this.f2982y = viewB;
            a(viewB);
        }
    }

    public final void l() {
        x3.a.a("BydRecyclerView", "curStatus = " + this.f2978u);
        if (this.f2978u != LoadStatus.LOOSEN_LOADING) {
            return;
        }
        this.f2978u = LoadStatus.LOADING;
        a aVar = this.f2980w;
        if (aVar != null) {
            aVar.c(this.f2982y);
        }
        WrapRecyclerView.a aVar2 = this.f3001j;
        if (aVar2 != null) {
            aVar2.b();
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i8, int i9) {
        int iMakeMeasureSpec;
        super.onMeasure(i8, i9);
        View view = this.f2982y;
        if (view == null || this.f2981x != 0) {
            return;
        }
        int i10 = view.getLayoutParams().height;
        if (i10 == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE);
        } else {
            if (i10 == -1) {
                throw new RuntimeException("LoadView 的高度不能指定为 Match_Parent");
            }
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        this.f2982y.measure(i8, iMakeMeasureSpec);
        this.f2981x = this.f2982y.getMeasuredHeight();
        x3.a.a("BydRecyclerView", "mLoadViewHeight = " + this.f2981x);
        int i11 = this.f2981x;
        if (i11 > 0) {
            setLoadViewMarginBottom((-i11) + 1);
        }
    }

    @Override // com.byd.mvp.ui.widget.pullrefreshview.RefreshWrapperRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            x3.a.a("BydRecyclerView", "ACTION_DOWN------");
        } else if (action == 1) {
            x3.a.a("BydRecyclerView", "ACTION_UP------curStatus = " + this.f2978u);
            if (this.f2978u == LoadStatus.PULL_UP_LOADING) {
                this.f2978u = LoadStatus.LOOSEN_LOADING;
                l();
            }
        } else if (action == 2) {
            x3.a.a("BydRecyclerView", "ACTION_MOVE------");
            if (i() || this.f2978u == LoadStatus.LOADING || this.f2980w == null || this.f2982y == null) {
                return super.onTouchEvent(motionEvent);
            }
            int rawY = (int) ((motionEvent.getRawY() - this.f2983z) * this.f2984k);
            x3.a.a("BydRecyclerView", "ACTION_MOVE------distanceY = " + rawY);
            if (rawY < 0) {
                this.f2978u = LoadStatus.PULL_UP_LOADING;
                x3.a.a("BydRecyclerView", "ACTION_MOVE------curStatus = " + this.f2978u);
                scrollToPosition(getAdapter().getItemCount() - 1);
                int i8 = -rawY;
                int i9 = i8 - this.f2981x;
                x3.a.a("BydRecyclerView", "ACTION_MOVE------marginBottom = " + i9);
                setLoadViewMarginBottom(i9);
                a aVar = this.f2980w;
                if (aVar != null) {
                    aVar.a(this.f2982y, i8, this.f2981x);
                }
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setFootViewHolderOnDataSize(int i8) {
        if (i8 < 10) {
            j();
        } else {
            k();
        }
    }

    public BydRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2978u = LoadStatus.NORMAL;
        this.f2981x = 0;
    }

    public BydRecyclerView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2978u = LoadStatus.NORMAL;
        this.f2981x = 0;
    }
}
