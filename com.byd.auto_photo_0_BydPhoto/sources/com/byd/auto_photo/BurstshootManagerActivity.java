package com.byd.auto_photo;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.byd.util.e;
import com.byd.util.h;

/* JADX INFO: loaded from: classes.dex */
public class BurstshootManagerActivity extends BurstshootBaseActivity {
    private TextView O;
    private TextView P;
    private OTAReceiver Q;

    private void J0() {
        Resources resources;
        int i;
        if (b.a.a.a(this) != 0 || (getResources().getConfiguration().uiMode & 48) == 32) {
            resources = getResources();
            i = R.color.tool_elements_color_night;
        } else {
            resources = getResources();
            i = R.color.tool_elements_color_day;
        }
        int color = resources.getColor(i);
        this.r.setBackground(h.a(getResources().getDrawable(R.drawable.icon_edit_sure), color));
        this.s.setBackground(h.a(getResources().getDrawable(R.drawable.icon_cancel), color));
        int iL = e.l(this, R.attr.text_color);
        this.P.setTextColor(iL);
        if (this.z == 0) {
            this.O.setTextColor(iL);
        } else {
            this.O.setTextColor(e.l(this, R.attr.text_color_press));
        }
    }

    private void K0() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity
    protected void H0() {
        TextView textView;
        int i;
        super.H0();
        if (this.z == 0) {
            textView = this.O;
            i = R.attr.text_color;
        } else {
            textView = this.O;
            i = R.attr.text_color_press;
        }
        textView.setTextColor(e.l(this, i));
        this.O.setText("(" + this.z + "/" + this.y.size() + ")");
        this.P.setText(R.string.pieces);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity, com.byd.theme.ThemeActivity
    public void N() {
        super.N();
        this.O = (TextView) findViewById(R.id.tv_select_num);
        this.P = (TextView) findViewById(R.id.tv_pieces);
        J0();
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        super.V(z);
        J0();
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.Q = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.Q.b();
        super.onDestroy();
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        K0();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            K0();
        }
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity
    protected int y0(int i, float f) {
        return getResources().getDimensionPixelSize(R.dimen.dp_480);
    }

    @Override // com.byd.auto_photo.BurstshootBaseActivity
    protected int z0(int i) {
        Resources resources;
        int i2;
        if (i == 0) {
            resources = getResources();
            i2 = R.dimen.dp_7_5;
        } else {
            resources = getResources();
            i2 = R.dimen.dp_15;
        }
        return resources.getDimensionPixelSize(i2);
    }
}
