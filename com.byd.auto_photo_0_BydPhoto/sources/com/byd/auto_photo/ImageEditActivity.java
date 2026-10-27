package com.byd.auto_photo;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.util.h;
import com.byd.util.x;

/* JADX INFO: loaded from: classes.dex */
public class ImageEditActivity extends BaseImageEditActivity {
    private TextView P;
    private OTAReceiver Q;

    private void x0() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    private void y0(TextView textView, int i, int i2) {
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, h.a(getResources().getDrawable(i), i2), (Drawable) null, (Drawable) null);
        textView.setTextColor(i2);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.BaseImageEditActivity, com.byd.theme.ThemeActivity
    public void N() {
        Resources resources;
        int i;
        super.N();
        TextView textView = (TextView) findViewById(R.id.btn_water_mark_word);
        this.P = textView;
        textView.setOnClickListener(this);
        Configuration configuration = getResources().getConfiguration();
        if (b.a.a.a(this) != 0 || (configuration.uiMode & 48) == 32) {
            resources = getResources();
            i = R.color.tool_elements_color_night;
        } else {
            resources = getResources();
            i = R.color.tool_elements_color_day;
        }
        int color = resources.getColor(i, getTheme());
        y0(this.F, R.drawable.icon_edit_crop, color);
        y0(this.G, R.drawable.icon_edit_filter, color);
        y0(this.H, R.drawable.icon_edit_scrawl, color);
        y0(this.J, R.drawable.icon_edit_rotate, color);
        y0(this.I, R.drawable.icon_edit_paper, color);
        y0(this.P, R.drawable.icon_edit_word, color);
        y0(this.K, R.drawable.icon_edit_masic, color);
        y0(this.L, R.drawable.icon_edit_blur, color);
        ((ImageView) this.B).setImageDrawable(h.a(getResources().getDrawable(R.drawable.icon_cancel), color));
        ((ImageView) this.A).setImageDrawable(x.s(this, R.drawable.icon_edit_sure));
        this.G.setVisibility(8);
        findViewById(R.id.spacing_filter).setVisibility(8);
    }

    @Override // com.byd.auto_photo.BaseImageEditActivity, com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        super.T(configuration);
        this.P.setText(R.string.water_mark_word);
    }

    @Override // com.byd.auto_photo.BaseImageEditActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_water_mark_word) {
            super.onClick(view);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("just_word", true);
        v0(WatermarkActivity.class, 4, bundle);
    }

    @Override // com.byd.auto_photo.BaseImageEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.Q = oTAReceiver;
        oTAReceiver.a();
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.BaseImageEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.Q.b();
        super.onDestroy();
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        x0();
        super.onStart();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            x0();
        }
    }
}
