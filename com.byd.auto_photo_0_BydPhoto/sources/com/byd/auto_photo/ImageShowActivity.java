package com.byd.auto_photo;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.byd.bean.FileBean;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.fragment.RenameFileFragment;
import com.byd.fragment.WallpaperSetFailDialog;
import com.byd.util.e;
import com.byd.util.h;
import com.byd.util.n;
import com.byd.util.w;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* JADX INFO: loaded from: classes.dex */
public class ImageShowActivity extends ImageShowBaseActivity {
    private View J0;
    private TextView K0;
    private ImageView L0;
    private int M0;
    private OTAReceiver N0;
    private ViewGroup O0;
    private int P0;
    private WallpaperSetFailDialog Q0;
    private Handler R0;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            ImageShowActivity.b2(ImageShowActivity.this);
            ImageShowActivity.this.h2();
            if (ImageShowActivity.this.P0 < 5) {
                sendEmptyMessageDelayed(1, 100L);
            }
        }
    }

    class b implements ViewTreeObserver.OnPreDrawListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ImageShowActivity.this.O0.getViewTreeObserver().removeOnPreDrawListener(this);
            int measuredWidth = ImageShowActivity.this.O0.getMeasuredWidth();
            View childAt = ImageShowActivity.this.O0.getChildAt(0);
            int measuredWidth2 = childAt.getMeasuredWidth();
            int paddingLeft = ImageShowActivity.this.O0.getPaddingLeft() + ImageShowActivity.this.O0.getPaddingRight();
            w.c("childWidth : " + measuredWidth2 + " padding : " + paddingLeft + " containerWidth : " + measuredWidth);
            ((FrameLayout.LayoutParams) childAt.getLayoutParams()).gravity = measuredWidth2 + paddingLeft >= measuredWidth ? 8388691 : 81;
            childAt.requestLayout();
            return true;
        }
    }

    static /* synthetic */ int b2(ImageShowActivity imageShowActivity) {
        int i = imageShowActivity.P0;
        imageShowActivity.P0 = i + 1;
        return i;
    }

    private void e2() {
        this.O0.getViewTreeObserver().addOnPreDrawListener(new b());
    }

    private ColorStateList f2() {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, new int[]{android.R.attr.state_enabled}, new int[0]}, new int[]{e.l(this, R.attr.text_color_press), this.M0, getResources().getColor(R.color.text_color_night_no_enable)});
    }

    private void g2() {
        this.P0 = 0;
        if (this.t == null) {
            h2();
            return;
        }
        if (this.R0 == null) {
            this.R0 = new a(Looper.getMainLooper());
        }
        this.R0.removeCallbacksAndMessages(null);
        this.R0.sendEmptyMessageDelayed(1, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h2() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT < 21) {
            window.addFlags(67108864);
            window.addFlags(134217728);
            return;
        }
        View decorView = window.getDecorView();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.clearFlags(134217728);
        decorView.setSystemUiVisibility(this.J);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }

    private void i2() {
        k2(this.Q, R.drawable.icon_set_wallpaper);
        k2(this.B, R.drawable.icon_rename_in_full);
        k2(this.w, R.drawable.icon_collect_in_full);
        k2(this.F, R.drawable.icon_edit_in_full);
        k2(this.N, R.drawable.icon_share_in_full);
        k2(this.P, R.drawable.icon_detail_in_full);
        k2(this.O, R.drawable.icon_del_in_full);
        this.L0.setImageDrawable(h.a(getResources().getDrawable(R.drawable.icon_back_in_full), this.M0));
        this.K0.setTextColor(this.M0);
        if (getResources().getConfiguration().orientation == 1) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_108);
            j2(this.Q, dimensionPixelSize);
            j2(this.B, dimensionPixelSize);
            j2(this.w, dimensionPixelSize);
            j2(this.F, dimensionPixelSize);
            j2(this.N, dimensionPixelSize);
            j2(this.P, dimensionPixelSize);
            j2(this.O, dimensionPixelSize);
        }
    }

    private void j2(TextView textView, int i) {
        textView.setLines(2);
        textView.setMaxWidth(i);
        textView.setGravity(1);
    }

    private void k2(TextView textView, int i) {
        Drawable drawable = getResources().getDrawable(i);
        ColorStateList colorStateListF2 = f2();
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, h.c(drawable, colorStateListF2), (Drawable) null, (Drawable) null);
        textView.setTextColor(colorStateListF2);
    }

    private void l2() {
        if ((getResources().getConfiguration().uiMode & 48) != 32 && b.a.a.a(this) == 0) {
            this.f.setBackgroundResource(R.drawable.bg_night_drawable);
        }
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void B1(int i) {
        super.B1(i);
        TextView textView = this.K0;
        if (textView == null) {
            return;
        }
        textView.setVisibility((i == 1 && this.q0) ? 0 : 4);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void D1(File file, File file2) {
        this.K0.setText(file2.getName());
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void E1(FileBean fileBean) {
        TextView textView = this.K0;
        if (textView != null) {
            textView.setText(fileBean.getName());
        }
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void F1() {
        super.F1();
        TextView textView = this.K0;
        if (textView == null) {
            return;
        }
        textView.setVisibility(0);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int H() {
        return 0;
    }

    @Override // com.byd.theme.ThemeActivity
    protected int J() {
        return 0;
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void J1(FileBean fileBean) {
        this.K0.setText(fileBean.getName());
        super.J1(fileBean);
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? super.K(z) : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void K1(boolean z) {
        TextView textView;
        int i;
        super.K1(z);
        if (z) {
            textView = this.w;
            i = R.drawable.icon_collected_in_full;
        } else {
            textView = this.w;
            i = R.drawable.icon_collect_in_full;
        }
        k2(textView, i);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void M1(int i, FileBean fileBean) {
        if (i != 0 || fileBean.getType() != 2) {
            super.M1(i, fileBean);
            return;
        }
        ProgressDialogFragment progressDialogFragment = this.u0;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.u0 = null;
        }
        if (this.Q0 == null) {
            WallpaperSetFailDialog wallpaperSetFailDialogNewInstance = WallpaperSetFailDialog.newInstance(R.string.video_wallpaper_tips_title, 0);
            this.Q0 = wallpaperSetFailDialogNewInstance;
            wallpaperSetFailDialogNewInstance.setUiVisibility(this.J);
        }
        this.Q0.show(getSupportFragmentManager(), "video_paper_set_fail");
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, com.byd.theme.ThemeActivity
    public void N() {
        String string;
        int iLastIndexOf;
        this.M0 = getResources().getColor(R.color.tool_elements_color_night, getTheme());
        super.N();
        l2();
        this.J0 = findViewById(R.id.full_show_title_container);
        this.K0 = (TextView) findViewById(R.id.tv_full_show_title);
        ImageView imageView = (ImageView) findViewById(R.id.iv_full_show_close);
        this.L0 = imageView;
        imageView.setOnClickListener(this);
        this.M.setText(R.string.app_name);
        this.M.setBackgroundResource(R.drawable.bg_btn_to_album);
        this.O0 = (ViewGroup) findViewById(R.id.img_show_tool_layout);
        e2();
        i2();
        Intent intent = getIntent();
        if (intent.getData() != null) {
            Uri data = intent.getData();
            if (n.q(this, data) == null && (iLastIndexOf = (string = data.toString()).lastIndexOf("/")) > -1) {
                String strSubstring = string.substring(iLastIndexOf + 1);
                try {
                    strSubstring = URLDecoder.decode(strSubstring, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                this.K0.setText(strSubstring);
            }
        }
        findViewById(R.id.spacing_share).setVisibility(8);
        this.N.setVisibility(8);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void N1() {
        super.N1();
        h2();
        Y();
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        super.T(configuration);
        this.M.setText(R.string.app_name);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void T0() {
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        c0(z, true, false);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void V1() {
        super.V1();
        this.J0.setVisibility(0);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void Y0(RenameFileFragment renameFileFragment) {
        renameFileFragment.setSoftInputMode(35);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected void n1() {
        super.n1();
        this.f.setPadding(0, 0, 0, 0);
        View view = this.J0;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.J0.setVisibility(8);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.iv_full_show_close) {
            super.onClick(view);
        } else {
            if (e.y()) {
                return;
            }
            finish();
        }
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.N0 = oTAReceiver;
        oTAReceiver.a();
        this.J = 5894;
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.N0.b();
        super.onDestroy();
        Handler handler = this.R0;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.R0 = null;
        }
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        h2();
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        h2();
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            g2();
            return;
        }
        Handler handler = this.R0;
        if (handler == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected boolean s1() {
        return t1();
    }

    @Override // com.byd.auto_photo.ImageShowBaseActivity
    protected int w1(Bitmap bitmap, Bitmap bitmap2) {
        try {
            w.c("set wall paper bitmap size : " + bitmap.getWidth() + " " + bitmap.getHeight());
            WallpaperManager.getInstance(this.g).setBitmap(bitmap);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            w.c("set wallpaper exception : " + e.getMessage());
            return 0;
        }
    }
}
