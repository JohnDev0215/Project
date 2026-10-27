package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.byd.bean.ImageEditItem;
import com.byd.util.w;
import com.byd.view.OverlayImageView;

/* JADX INFO: loaded from: classes.dex */
public class BaseBlurActivity extends BaseEditActivity {
    protected OverlayImageView Q;
    private View R;
    protected SeekBar S;
    protected float T;
    protected TextView U;
    protected boolean V;
    private int W;
    private f X;
    private View Y;
    private int Z;
    private int a0;
    private TextView b0;
    private boolean c0;
    protected float f0;
    private View.OnClickListener d0 = new b();
    private int e0 = -1;
    private SeekBar.OnSeekBarChangeListener g0 = new c();

    class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BaseBlurActivity baseBlurActivity = BaseBlurActivity.this;
            Bitmap resultBitmap = baseBlurActivity.Q.getResultBitmap(baseBlurActivity.Z, BaseBlurActivity.this.a0);
            BaseBlurActivity baseBlurActivity2 = BaseBlurActivity.this;
            if (baseBlurActivity2.v) {
                Intent intent = new Intent();
                intent.putExtra("photo_intent_data", Bitmap.createBitmap(resultBitmap));
                BaseBlurActivity.this.setResult(-1, intent);
            } else {
                b.a.h.a.r(resultBitmap, baseBlurActivity2.t.getPath(), Bitmap.Config.ARGB_8888);
                BaseBlurActivity.this.setResult(-1);
            }
            BaseBlurActivity.this.s0();
            BaseBlurActivity.this.finish();
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            w.c("onReset click : " + BaseBlurActivity.this.c0);
            if (BaseBlurActivity.this.c0) {
                BaseBlurActivity.this.Q.setShape(0);
                BaseBlurActivity.this.Q.setAddOverlay(false);
                BaseBlurActivity.this.T0(0.0f);
                BaseBlurActivity.this.S.setProgress(0);
                BaseBlurActivity.this.S0(false);
                BaseBlurActivity.this.w.n(-1);
                BaseBlurActivity.this.e0 = -1;
                BaseBlurActivity.this.c0 = false;
                BaseBlurActivity.this.z0(false);
            }
        }
    }

    class c implements SeekBar.OnSeekBarChangeListener {
        c() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            BaseBlurActivity baseBlurActivity = BaseBlurActivity.this;
            baseBlurActivity.f0 = (i * 25.0f) / 100.0f;
            baseBlurActivity.R0();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            float progress = (seekBar.getProgress() * 25.0f) / 100.0f;
            BaseBlurActivity baseBlurActivity = BaseBlurActivity.this;
            baseBlurActivity.f0 = progress;
            if (progress == 0.0f) {
                baseBlurActivity.c0 = false;
                BaseBlurActivity.this.z0(false);
                BaseBlurActivity.this.S0(false);
            }
            BaseBlurActivity.this.T0(progress);
        }
    }

    class d extends com.byd.taskmanager.b.c<Bitmap> {
        d() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Bitmap bitmap) {
            BaseBlurActivity baseBlurActivity = BaseBlurActivity.this;
            baseBlurActivity.Q.setBlurRadius(baseBlurActivity.T);
            BaseBlurActivity.this.Q.setImageBitmap(bitmap);
            BaseBlurActivity.this.M0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class e extends com.byd.taskmanager.b.a<Bitmap> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private float f991a;

        private e() {
        }

        /* synthetic */ e(BaseBlurActivity baseBlurActivity, a aVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap doTask() {
            BaseBlurActivity baseBlurActivity = BaseBlurActivity.this;
            return b.a.h.a.a(baseBlurActivity, baseBlurActivity.s, this.f991a, false);
        }

        public void c(float f) {
            this.f991a = f;
        }
    }

    private static class f extends b.a.d<BaseBlurActivity> {
        public f(BaseBlurActivity baseBlurActivity) {
            super(baseBlurActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(BaseBlurActivity baseBlurActivity, Message message) {
            baseBlurActivity.Q0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0() {
        if (this.c0) {
            return;
        }
        this.c0 = true;
        this.b0.setText(R.string.reset);
        this.b0.setTextColor(com.byd.util.e.l(this, R.attr.text_color_press));
        z0(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void N0(int r3) {
        /*
            r2 = this;
            r0 = 1
            if (r3 == 0) goto L15
            if (r3 == r0) goto L6
            goto L27
        L6:
            com.byd.view.OverlayImageView r3 = r2.Q
            r1 = 1058642330(0x3f19999a, float:0.6)
            r3.setOverlayRadiusRatio(r1)
            com.byd.view.OverlayImageView r3 = r2.Q
            r1 = 2
            r3.setShape(r1)
            goto L22
        L15:
            com.byd.view.OverlayImageView r3 = r2.Q
            r1 = 1061997773(0x3f4ccccd, float:0.8)
            r3.setOverlayRadiusRatio(r1)
            com.byd.view.OverlayImageView r3 = r2.Q
            r3.setShape(r0)
        L22:
            com.byd.view.OverlayImageView r3 = r2.Q
            r3.setAddOverlay(r0)
        L27:
            r2.M0()
            float r3 = r2.T
            r0 = 0
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L3d
            r3 = 1095237632(0x41480000, float:12.5)
            r2.T0(r3)
            android.widget.SeekBar r3 = r2.S
            r0 = 50
            r3.setProgress(r0)
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseBlurActivity.N0(int):void");
    }

    private void O0() {
        boolean z = this.V;
        this.W = z ? 1 : 0;
        ImageEditItem[] imageEditItemArr = new ImageEditItem[z ? 3 : 2];
        if (z) {
            imageEditItemArr[0] = new ImageEditItem(R.drawable.icon_edit_reset).setNameRes(R.string.reset).setTintColor(com.byd.util.e.l(this, R.attr.text_color)).setDisableColor(com.byd.util.e.l(this, R.attr.text_color_no_enable)).setEnable(false);
        }
        int iL = com.byd.util.e.l(this, R.attr.text_color_press);
        imageEditItemArr[this.W] = new ImageEditItem(R.drawable.icon_edit_blur_circle).setNameRes(R.string.crop_circle).setSelectColor(iL);
        imageEditItemArr[this.W + 1] = new ImageEditItem(R.drawable.icon_edit_blur_rect).setNameRes(R.string.blur_line).setSelectColor(iL);
        this.w.q(imageEditItemArr);
        w.c("==blur initData==");
    }

    private boolean P0(int i) {
        if (this.e0 == i) {
            return false;
        }
        if (!this.V || i != 0) {
            this.e0 = i;
            N0(i - this.W);
            return true;
        }
        if (!this.c0) {
            return false;
        }
        this.e0 = i;
        this.d0.onClick(this.b0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0() {
        OverlayImageView overlayImageView = this.Q;
        if (overlayImageView == null || overlayImageView.getShape() <= 0) {
            return;
        }
        this.Q.setAddOverlay(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0(boolean z) {
        if (z) {
            this.b0.setText(R.string.reset);
            this.b0.setTextColor(com.byd.util.e.l(this, R.attr.text_color_press));
        } else {
            this.b0.setTextColor(com.byd.util.e.l(this, R.attr.text_color));
            this.b0.setText(R.string.blur);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T0(float f2) {
        this.T = f2;
        R0();
        if (f2 == 0.0f) {
            this.Q.setBlurRadius(0.0f);
            this.Q.setImageBitmap(this.s);
        } else {
            if (!z(0)) {
                v(0, new e(this, null), new d(), true);
            }
            ((e) y(0)).c(f2);
            w(0);
        }
    }

    protected void R0() {
        this.U.setText(R.string.blur);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        super.T(configuration);
        R0();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        this.E.removeAllViews();
        super.U(configuration);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        super.V(z);
        boolean z2 = this.V;
        int i = R.attr.text_color;
        if (z2) {
            this.w.i(0).setTintColor(com.byd.util.e.l(this, R.attr.text_color)).setDisableColor(com.byd.util.e.l(this, R.attr.text_color_no_enable));
        }
        TextView textView = this.b0;
        if (this.c0) {
            i = R.attr.text_color_press;
        }
        textView.setTextColor(com.byd.util.e.l(this, i));
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
        this.b0 = (TextView) getLayoutInflater().inflate(R.layout.view_img_edit_title, (ViewGroup) null);
        S0(this.c0);
        linearLayout.addView(this.b0);
        this.b0.setOnClickListener(this.d0);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int k0(String str) {
        int iK0 = super.k0(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int iMax = Math.max(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        int i = options.outWidth;
        this.Z = i;
        int i2 = options.outHeight;
        this.a0 = i2;
        if (iK0 > 1) {
            this.Z = i / iK0;
            this.a0 = i2 / iK0;
        }
        if (this.Z <= iMax && this.a0 <= iMax) {
            return iK0;
        }
        float f2 = iMax;
        return (int) Math.max(Math.ceil(r1 / f2), Math.ceil(this.a0 / f2));
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean o(int i) {
        return P0(i);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.X = new f(this);
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        return P0(i);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.X.removeMessages(1);
        OverlayImageView overlayImageView = this.Q;
        if (overlayImageView == null || overlayImageView.getShape() <= 0) {
            return;
        }
        this.X.sendEmptyMessageDelayed(1, 300L);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        OverlayImageView overlayImageView = this.Q;
        if (overlayImageView == null || overlayImageView.getShape() <= 0) {
            return;
        }
        this.Q.setAddOverlay(false);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        if (this.w.getItemCount() == 0) {
            O0();
        }
        this.w.v(true);
        this.Q = (OverlayImageView) x(R.id.iv_image);
        this.R = findViewById(R.id.tool_blur_radius);
        this.U = (TextView) this.Y.findViewById(R.id.tv_seek_blur_tips);
        SeekBar seekBar = (SeekBar) x(R.id.seek_blur);
        this.S = seekBar;
        seekBar.setMax(100);
        R0();
        this.S.setOnSeekBarChangeListener(this.g0);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected View v0(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (this.Y == null) {
            this.Y = layoutInflater.inflate(R.layout.activity_blur, (ViewGroup) null);
        }
        return this.Y;
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void w0() {
        if (this.T > 0.0f) {
            y0();
            new a().start();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void x0(Bitmap bitmap) {
        this.Q.setImageBitmap(bitmap);
        this.Q.setOverlayBitmap(bitmap);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void z0(boolean z) {
        super.z0(z);
        if (this.V && this.w.i(0).isEnable() != z) {
            this.w.i(0).setEnable(z);
            this.w.notifyItemChanged(0);
        }
    }
}
