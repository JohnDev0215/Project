package com.byd.auto_photo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import com.byd.bean.ImageEditItem;
import com.byd.util.e;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public abstract class ScrawlSuperActivity extends BaseScrawlActivity {
    protected int i0 = -1;
    private int j0;
    private ImageEditItem k0;
    private long l0;

    class a implements ViewTreeObserver.OnPreDrawListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (ScrawlSuperActivity.this.j0 == ScrawlSuperActivity.this.W.getMeasuredWidth()) {
                ScrawlSuperActivity.this.W.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
            ScrawlSuperActivity scrawlSuperActivity = ScrawlSuperActivity.this;
            scrawlSuperActivity.j0 = scrawlSuperActivity.W.getMeasuredWidth();
            int iJ0 = ScrawlSuperActivity.this.J0("scrawl_color", 0);
            w.c("colorIndex:" + iJ0);
            if (iJ0 == 0) {
                return true;
            }
            ScrawlSuperActivity.this.W.setThumbOffset(iJ0);
            return true;
        }
    }

    private boolean U0(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.l0 <= 500) {
            w.c("ScrawlActivity item click too fast");
            return false;
        }
        this.l0 = jCurrentTimeMillis;
        if (this.i0 == i) {
            return true;
        }
        if (i == 0 && !this.Q.h()) {
            return false;
        }
        V0(i);
        if (i <= 0) {
            return true;
        }
        P0("scrawl_style", i);
        return true;
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity
    protected int K0() {
        return (super.K0() * 5) / 6;
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity
    protected int L0() {
        return (super.L0() * 5) / 6;
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity
    protected String M0() {
        return getResources().getString(R.string.scrawl);
    }

    protected ImageEditItem[] S0() {
        ImageEditItem[] imageEditItemArr = new ImageEditItem[T0()];
        int iL = e.l(this, R.attr.text_color_press);
        ImageEditItem disableColor = new ImageEditItem(R.drawable.icon_edit_scrawl_eraser).setSelectColor(iL).setEnable(false).setDisableColor(e.l(this, R.attr.text_color_no_enable));
        this.k0 = disableColor;
        imageEditItemArr[0] = disableColor;
        imageEditItemArr[1] = new ImageEditItem(R.drawable.icon_curve).setSelectColor(iL);
        imageEditItemArr[2] = new ImageEditItem(R.drawable.icon_arrow).setSelectColor(iL);
        imageEditItemArr[3] = new ImageEditItem(R.drawable.icon_line).setSelectColor(iL);
        imageEditItemArr[4] = new ImageEditItem(R.drawable.icon_rect).setSelectColor(iL);
        imageEditItemArr[5] = new ImageEditItem(R.drawable.icon_circle).setSelectColor(iL);
        return imageEditItemArr;
    }

    protected int T0() {
        return 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0012 A[PHI: r0
  0x0012: PHI (r0v2 int) = (r0v1 int), (r0v3 int), (r0v4 int), (r0v5 int), (r0v6 int) binds: [B:4:0x0003, B:6:0x0006, B:8:0x0009, B:10:0x000c, B:12:0x000f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void V0(int r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L18
            r0 = 1
            if (r3 == r0) goto L12
            r0 = 2
            if (r3 == r0) goto L12
            r0 = 3
            if (r3 == r0) goto L12
            r0 = 4
            if (r3 == r0) goto L12
            r0 = 5
            if (r3 == r0) goto L12
            goto L1f
        L12:
            com.byd.imageedit.ScrawlImageView r1 = r2.Q
            r1.n(r0)
            goto L1f
        L18:
            com.byd.imageedit.ScrawlImageView r0 = r2.Q
            r1 = 10
            r0.n(r1)
        L1f:
            r2.i0 = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.ScrawlSuperActivity.V0(int):void");
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean o(int i) {
        return U0(i);
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity, com.byd.view.ColorSelectBar.OnColorChangeListener
    public void onColorChange(int i) {
        w.c("onColorChange : " + i + " " + this.W.getThumbOffset());
        this.Q.m(i);
        P0("scrawl_color", this.W.getThumbOffset());
        P0("scrawl_color_value", i);
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.j0 = 0;
        super.onConfigurationChanged(configuration);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w.q(S0());
        this.w.w(true);
        this.w.v(true);
        int iJ0 = J0("scrawl_style", 1);
        V0(iJ0);
        int iJ02 = J0("scrawl_size", 2);
        this.Q.p(this.X[iJ02]);
        this.w.n(iJ0);
        this.T.f(iJ02);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        return U0(i);
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity, b.a.e.q.a
    public void onStyleItemClick(int i) {
        super.onStyleItemClick(i);
        this.Q.p(this.X[i]);
        P0("scrawl_size", i);
    }

    @Override // com.byd.auto_photo.BaseScrawlActivity, com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        int iJ0 = J0("scrawl_color_value", 0);
        if (iJ0 != 0) {
            this.Q.m(iJ0);
        }
        this.W.getViewTreeObserver().addOnPreDrawListener(new a());
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void z0(boolean z) {
        super.z0(z);
        ImageEditItem imageEditItem = this.k0;
        if (imageEditItem == null || imageEditItem.isEnable() == z) {
            return;
        }
        this.k0.setEnable(z);
        this.w.notifyDataSetChanged();
    }
}
