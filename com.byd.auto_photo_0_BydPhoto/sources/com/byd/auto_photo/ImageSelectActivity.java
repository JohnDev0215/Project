package com.byd.auto_photo;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import b.a.e.i;
import b.a.e.l;
import com.byd.util.e;
import com.byd.util.h;
import com.byd.view.WrapContentListView;

/* JADX INFO: loaded from: classes.dex */
public class ImageSelectActivity extends ImageSelectBaseActivity {
    private ImageView H;
    private ImageView I;
    private OTAReceiver J;

    private void L0() {
        Resources resources;
        int i;
        if (b.a.a.a(this) != 0 || (getResources().getConfiguration().uiMode & 48) == 32) {
            resources = getResources();
            i = R.color.tool_elements_color_night;
        } else {
            resources = getResources();
            i = R.color.tool_elements_color_day;
        }
        this.I.setImageDrawable(h.a(getResources().getDrawable(R.drawable.icon_cancel), resources.getColor(i)));
        this.v.setTextColor(e.l(this, R.attr.text_color));
        this.u.notifyDataSetChanged();
        PopupWindow popupWindow = this.E;
        M0(popupWindow != null && popupWindow.isShowing());
    }

    private void M0(boolean z) {
        Resources resources;
        int i;
        ImageView imageView;
        int i2;
        if ((getResources().getConfiguration().uiMode & 48) != 32) {
            resources = getResources();
            i = R.color.tool_elements_color_day;
        } else {
            resources = getResources();
            i = R.color.tool_elements_color_night;
        }
        int color = resources.getColor(i);
        if (z) {
            imageView = this.H;
            i2 = R.drawable.icon_select_shrink;
        } else {
            imageView = this.H;
            i2 = R.drawable.icon_select_expand;
        }
        imageView.setImageDrawable(h.a(getDrawable(i2), color));
    }

    private void N0() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected void D0() {
        this.E.showAsDropDown(this.v, -getResources().getDimensionPixelOffset(R.dimen.dp_27), 30);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected void K0(boolean z) {
        super.K0(z);
        M0(z);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity, com.byd.theme.ThemeActivity
    public void N() {
        super.N();
        this.H = (ImageView) findViewById(R.id.view_expand);
        ImageView imageView = (ImageView) findViewById(R.id.view_close);
        this.I = imageView;
        imageView.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.H.setOnClickListener(this);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.s.getLayoutParams();
        layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.image_select_horizontal_margin);
        layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.image_select_horizontal_margin);
        L0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        b0(z, false);
        L0();
        PopupWindow popupWindow = this.E;
        if (popupWindow != null) {
            boolean zIsShowing = popupWindow.isShowing();
            if (zIsShowing) {
                this.E.dismiss();
            }
            this.E.setBackgroundDrawable(e.p(this, R.attr.menu_bg));
            if (zIsShowing) {
                F0();
            }
        }
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.tv_select_folder) {
            if (id == R.id.view_close) {
                finish();
                return;
            } else if (id != R.id.view_expand) {
                return;
            }
        }
        J0();
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.J = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.J.b();
        super.onDestroy();
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity, android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        M0(false);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        N0();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            N0();
        }
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected l p0() {
        return new i(this);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected int t0() {
        return getResources().getConfiguration().orientation == 1 ? 4 : 8;
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected int u0() {
        return getResources().getDisplayMetrics().widthPixels - (getResources().getDimensionPixelSize(R.dimen.image_select_horizontal_margin) * 2);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected int w0() {
        return -2;
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected int x0() {
        return getResources().getDimensionPixelSize(R.dimen.dp_138);
    }

    @Override // com.byd.auto_photo.ImageSelectBaseActivity
    protected void y0() {
        WrapContentListView wrapContentListView = new WrapContentListView(this);
        this.F = wrapContentListView;
        wrapContentListView.setDividerHeight(0);
        super.y0();
        this.E.setBackgroundDrawable(e.p(this, R.attr.menu_bg));
    }
}
