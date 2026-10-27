package com.byd.auto_photo;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.byd.util.e;
import com.byd.util.h;
import com.byd.util.w;
import com.byd.util.x;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseEditActivity extends SuperBaseEditActivity {
    private OTAReceiver N;
    protected View O;
    protected View P;

    private void A0() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    private void B0(int i, int i2, boolean z, boolean z2) {
        this.w.p(i);
        this.w.o(i2);
        this.w.notifyDataSetChanged();
        if (z2) {
            ViewGroup.LayoutParams layoutParams = this.x.getLayoutParams();
            if (z) {
                layoutParams.height = i;
            } else {
                layoutParams.width = i;
            }
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams).gravity = 17;
            }
        }
    }

    private void C0(int i, boolean z) {
        B0(i, i, z, true);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    public void N() {
        super.N();
        w.c("==initUI==>" + getClass().getName());
        String name = getClass().getName();
        if (name.equals(CropActivity.class.getName())) {
            if (!getIntent().getBooleanExtra("just_rotate", false)) {
                return;
            }
        } else if (!name.equals(BlurActivity.class.getName())) {
            if (getClass().getName().equals(Filter2Activity.class.getName())) {
                this.w.r(-1);
                return;
            } else if (!name.equals(WatermarkActivity.class.getName()) || !getIntent().getBooleanExtra("just_word", false)) {
                return;
            }
        }
        this.O.setVisibility(0);
        this.P.setVisibility(0);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        b0(z, true);
        n0();
        this.w.k();
        if (getClass().getName().equals(Filter2Activity.class.getName())) {
            this.w.r(-1);
        }
        this.w.notifyDataSetChanged();
        int iL = e.l(this, R.attr.line_color);
        this.O.setBackgroundColor(iL);
        this.P.setBackgroundColor(iL);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void j0() {
        String name = getClass().getName();
        if (name.equals(CropActivity.class.getName())) {
            if (!getIntent().getBooleanExtra("just_rotate", false)) {
                B0(getResources().getDimensionPixelSize(R.dimen.dp_60), this.x.getMeasuredHeight(), true, false);
                return;
            }
        } else {
            if (name.equals(Filter2Activity.class.getName())) {
                C0(getResources().getDimensionPixelSize(R.dimen.dp_54), this.w.j() == 1);
                return;
            }
            if (name.equals(WatermarkActivity.class.getName())) {
                if (!getIntent().getBooleanExtra("just_word", false)) {
                    C0(getResources().getDimensionPixelSize(R.dimen.dp_54), this.w.j() == 1);
                    return;
                }
            } else if (name.equals(ScrawlActivity.class.getName())) {
                C0(getResources().getDimensionPixelSize(R.dimen.dp_36), this.w.j() == 1);
                return;
            } else if (name.equals(MosicActivity.class.getName())) {
                C0(getResources().getDimensionPixelSize(R.dimen.dp_37_5), this.w.j() == 1);
                return;
            }
        }
        int measuredHeight = this.x.getMeasuredHeight();
        int measuredWidth = this.x.getMeasuredWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.image_edit_tool_horizontal_padding) + getResources().getDimensionPixelSize(R.dimen.dp_36);
        if (findViewById(R.id.line_left).getVisibility() != 8) {
            dimensionPixelSize += getResources().getDimensionPixelSize(R.dimen.dp_21);
        }
        if (this.w.j() == 1) {
            measuredWidth = getResources().getDisplayMetrics().widthPixels - (dimensionPixelSize * 2);
        } else {
            measuredHeight = getResources().getDisplayMetrics().heightPixels - (dimensionPixelSize * 2);
        }
        this.w.A(measuredWidth, measuredHeight, this.J);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x009f, code lost:
    
        if (r0.equals(com.byd.auto_photo.BlurActivity.class.getName()) != false) goto L23;
     */
    @Override // com.byd.auto_photo.SuperBaseEditActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void l0() {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseEditActivity.l0():void");
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected boolean m0(LinearLayout linearLayout, int i) {
        String name = getClass().getName();
        if (!name.equals(ScrawlActivity.class.getName()) && !name.equals(MosicActivity.class.getName())) {
            return false;
        }
        linearLayout.setOrientation(0);
        return true;
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void n0() {
        Resources resources;
        int i;
        if (b.a.a.a(this) != 0 || (getResources().getConfiguration().uiMode & 48) == 32) {
            resources = getResources();
            i = R.color.tool_elements_color_night;
        } else {
            resources = getResources();
            i = R.color.tool_elements_color_day;
        }
        int color = resources.getColor(i, getTheme());
        this.F.setImageDrawable(x.s(this, R.drawable.icon_edit_sure));
        this.G.setImageDrawable(h.a(getResources().getDrawable(R.drawable.icon_cancel), color));
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.N = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.N.b();
        super.onDestroy();
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        A0();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            A0();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int p0() {
        String name = getClass().getName();
        return (name.equals(ScrawlActivity.class.getName()) || name.equals(MosicActivity.class.getName())) ? (e.H(this) && getResources().getConfiguration().orientation == 2) ? R.layout.activity_base_scrawl_right_driver : R.layout.activity_base_scrawl : super.p0();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        this.O = findViewById(R.id.line_left);
        this.P = findViewById(R.id.line_right);
    }
}
