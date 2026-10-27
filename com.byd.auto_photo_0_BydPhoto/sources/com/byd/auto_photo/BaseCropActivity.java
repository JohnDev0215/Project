package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.bean.ImageEditItem;
import com.byd.imageedit.CropImageView;
import com.byd.util.e;
import com.byd.util.w;
import com.byd.util.x;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseCropActivity extends BaseEditActivity {
    protected CropImageView Q;
    protected boolean R;
    protected boolean S;
    private ImageEditItem T;
    private int U;
    private TextView V;
    private boolean W;
    private View.OnClickListener X = new a();
    private CropImageView.a Y = new b();
    private int Z = -1;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BaseCropActivity.this.W) {
                BaseCropActivity baseCropActivity = BaseCropActivity.this;
                if (!baseCropActivity.R) {
                    baseCropActivity.Q.j(-1, -1);
                    BaseCropActivity.this.Q.setCropType(1);
                    BaseCropActivity.this.T.setIcon(R.drawable.icon_edit_crop_circle).setNameRes(R.string.crop_circle);
                    BaseCropActivity baseCropActivity2 = BaseCropActivity.this;
                    baseCropActivity2.w.n(baseCropActivity2.U);
                    BaseCropActivity baseCropActivity3 = BaseCropActivity.this;
                    baseCropActivity3.Z = baseCropActivity3.U;
                }
                BaseCropActivity.this.Q.g();
                BaseCropActivity.this.O0();
            }
        }
    }

    class b implements CropImageView.a {
        b() {
        }

        @Override // com.byd.imageedit.CropImageView.a
        public void a(float f, float f2, int i) {
            if (f == 0.0f && f2 == 0.0f && i == 0) {
                if (BaseCropActivity.this.W) {
                    BaseCropActivity.this.O0();
                }
            } else {
                if (BaseCropActivity.this.W) {
                    return;
                }
                BaseCropActivity.this.J0();
            }
        }

        @Override // com.byd.imageedit.CropImageView.a
        public void b() {
            if (BaseCropActivity.this.W) {
                return;
            }
            BaseCropActivity.this.J0();
        }
    }

    class c extends Thread {
        c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r3v10 */
        /* JADX WARN: Type inference failed for: r3v11, types: [java.io.FileOutputStream] */
        /* JADX WARN: Type inference failed for: r3v12 */
        /* JADX WARN: Type inference failed for: r3v13 */
        /* JADX WARN: Type inference failed for: r3v14 */
        /* JADX WARN: Type inference failed for: r3v20 */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v23 */
        /* JADX WARN: Type inference failed for: r3v9, types: [android.net.Uri, java.lang.Object] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0091 -> B:40:0x00ee). Please report as a decompilation issue!!! */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 270
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseCropActivity.c.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        this.W = true;
        this.V.setText(R.string.reset);
        this.V.setTextColor(e.l(this, R.attr.text_color_press));
        z0(true);
    }

    private Point K0() {
        WindowManager windowManager = (WindowManager) getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point;
    }

    private void L0() {
        ImageEditItem[] imageEditItemArr;
        boolean z = this.S;
        this.U = z ? 1 : 0;
        if (this.R) {
            imageEditItemArr = new ImageEditItem[z ? 3 : 2];
            imageEditItemArr[z ? 1 : 0] = new ImageEditItem(R.drawable.icon_edit_rotate_90).setNameRes(R.string.rotate_90);
            imageEditItemArr[(z ? 1 : 0) + 1] = new ImageEditItem(R.drawable.icon_edit_rotate_mirrow).setNameRes(R.string.rotate_mirro);
        } else {
            imageEditItemArr = new ImageEditItem[z ? 7 : 6];
            int iL = e.l(this, R.attr.text_color_press);
            imageEditItemArr[z ? 1 : 0] = new ImageEditItem(R.drawable.icon_edit_crop_any).setNameRes(R.string.crop_any).setSelectColor(iL);
            imageEditItemArr[(z ? 1 : 0) + 1] = new ImageEditItem(R.drawable.icon_edit_crop_screen).setNameRes(R.string.crop_screen).setSelectColor(iL);
            imageEditItemArr[(z ? 1 : 0) + 2] = new ImageEditItem(R.drawable.icon_edit_crop_11).setNameRes(R.string.crop_1_1).setSelectColor(iL);
            imageEditItemArr[(z ? 1 : 0) + 3] = new ImageEditItem(R.drawable.icon_edit_crop_169).setNameRes(R.string.crop_16_9).setSelectColor(iL);
            imageEditItemArr[(z ? 1 : 0) + 4] = new ImageEditItem(R.drawable.icon_edit_crop_916).setNameRes(R.string.crop_9_16).setSelectColor(iL);
            int i = (z ? 1 : 0) + 5;
            ImageEditItem selectColor = new ImageEditItem(R.drawable.icon_edit_crop_circle).setNameRes(R.string.crop_circle).setSelectColor(iL);
            imageEditItemArr[i] = selectColor;
            this.T = selectColor;
            this.w.n(this.U);
        }
        if (this.S) {
            imageEditItemArr[0] = new ImageEditItem(R.drawable.icon_edit_reset).setNameRes(R.string.reset).setTintColor(e.l(this, R.attr.text_color)).setDisableColor(e.l(this, R.attr.text_color_no_enable)).setEnable(false);
        }
        this.w.q(imageEditItemArr);
        w.c("crop activity iniData");
    }

    private boolean M0(int i, boolean z) {
        if (this.S && i == 0) {
            w.c("CropActivity click reset");
            this.X.onClick(this.V);
            return false;
        }
        if (this.R || i == this.U + 5 || this.Z != i) {
            this.Z = i;
            if (z) {
                super.o(i);
            } else {
                super.onItemClick(i);
            }
            P0(i - this.U);
            return true;
        }
        w.c("CropActivity click position no change : " + this.Z + " " + i);
        return false;
    }

    private void N0() {
        int iR0 = r0();
        int iQ0 = q0();
        if (iR0 <= 0 || iQ0 <= 0) {
            return;
        }
        int width = this.s.getWidth();
        int height = this.s.getHeight();
        getWindowManager().getDefaultDisplay().getSize(new Point());
        w.c("CropActivity before resize viewWidth : " + iR0 + " viewHeight : " + iQ0);
        int i = width * iQ0;
        int i2 = iR0 * height;
        if (i < i2) {
            iR0 = i / height;
        } else if (i > i2) {
            iQ0 = i2 / width;
        }
        w.c("viewWidth : " + iR0 + " viewHeight : " + iQ0);
        ViewGroup.LayoutParams layoutParams = this.Q.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(iR0, iQ0);
        } else {
            layoutParams.width = iR0;
            layoutParams.height = iQ0;
        }
        this.Q.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0() {
        TextView textView;
        int i;
        this.W = false;
        if (this.R) {
            textView = this.V;
            i = R.string.rotate;
        } else {
            textView = this.V;
            i = R.string.crop;
        }
        textView.setText(i);
        this.V.setTextColor(e.l(this, R.attr.text_color));
        z0(false);
    }

    private void P0(int i) {
        CropImageView cropImageView;
        int i2;
        ImageEditItem icon;
        int i3;
        if (i != 0) {
            i2 = 1;
            if (i == 1) {
                if (this.R) {
                    this.Q.setMirror(1);
                    return;
                } else {
                    Point pointK0 = K0();
                    this.Q.j(pointK0.x, pointK0.y);
                    return;
                }
            }
            if (i != 2) {
                if (i == 3) {
                    this.Q.j(16, 9);
                    return;
                }
                if (i == 4) {
                    this.Q.j(9, 16);
                    return;
                }
                if (i != 5) {
                    return;
                }
                if (this.Q.getCropType() != 2) {
                    this.Q.setCropType(2);
                    icon = this.T.setIcon(R.drawable.icon_edit_crop_rect);
                    i3 = R.string.crop_rect;
                } else {
                    this.Q.setCropType(1);
                    icon = this.T.setIcon(R.drawable.icon_edit_crop_circle);
                    i3 = R.string.crop_circle;
                }
                icon.setNameRes(i3);
                this.w.notifyItemChanged(this.U + i);
                if (this.W) {
                    return;
                }
                J0();
                return;
            }
            cropImageView = this.Q;
        } else if (this.R) {
            this.Q.setDegree(this.Q.getDegree() - 90.0f);
            return;
        } else {
            cropImageView = this.Q;
            i2 = -1;
        }
        cropImageView.j(i2, i2);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    public void N() {
        super.N();
        CropImageView cropImageView = (CropImageView) findViewById(R.id.iv_crop);
        this.Q = cropImageView;
        if (this.R) {
            cropImageView.setSizeAfterOrientation(this.D);
            this.Q.setCropType(3);
            this.Q.setScalePlateColor(e.l(this, R.attr.text_color));
            this.Q.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.rotate_image_bottom_padding));
            Integer num = com.byd.auto_photo.a.f1178a;
            if (num.intValue() >= 35) {
                this.Q.setLineScalePlate(true);
                this.Q.setGradientScalePlate(num.intValue() >= 50);
            }
            this.Q.setInnerLine(num.intValue() >= 50);
        } else {
            cropImageView.setPadding(10, 10, 10, 10);
            Intent intent = getIntent();
            this.w.v(true);
            if (intent.getBooleanExtra("circleCrop", false)) {
                this.Q.setCropType(2);
                int i = this.U + 5;
                this.Z = i;
                this.w.n(i);
                this.w.i(this.U + 5).setIcon(R.drawable.icon_edit_crop_rect).setName(getResources().getString(R.string.crop_rect)).setSelectColor(e.l(this, R.attr.text_color_press));
            } else {
                this.Q.setCropType(1);
                int i2 = this.U;
                this.Z = i2;
                this.w.n(i2);
            }
        }
        this.Q.setOnCropChange(this.Y);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void R() {
        super.R();
        if (L(this, x.e((PhotoBaseApp) getApplication()))) {
            S();
        } else {
            w.e(this, R.string.permission_denied_and_finish);
            finish();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        if (this.W) {
            this.W = false;
        }
        w.c("Crop onNormalConfigurationChanged");
        super.U(configuration);
        x0(this.s);
        if (!this.R) {
            this.Q.setCropType(1);
            this.w.i(this.U + 5).setIcon(R.drawable.icon_edit_crop_circle).setNameRes(R.string.crop_circle);
        }
        if (this.S) {
            this.w.i(0).setEnable(false);
        }
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        w.c("Crop onUIModeChanged");
        super.V(z);
        if (this.S) {
            this.w.i(0).setTintColor(e.l(this, R.attr.text_color)).setDisableColor(e.l(this, R.attr.text_color_no_enable));
        }
        this.V.setTextColor(e.l(this, this.W ? R.attr.text_color_press : R.attr.text_color));
        this.Q.setScalePlateColor(e.l(this, R.attr.text_color));
        this.Q.invalidate();
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        return x.f(this);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
        TextView textView;
        int i;
        TextView textView2 = (TextView) getLayoutInflater().inflate(R.layout.view_img_edit_title, (ViewGroup) null);
        this.V = textView2;
        textView2.setTextColor(e.l(this, R.attr.text_color));
        this.V.setGravity(17);
        if (this.W) {
            this.V.setText(R.string.reset);
            this.V.setTextColor(e.l(this, R.attr.text_color_press));
        } else {
            if (this.R) {
                textView = this.V;
                i = R.string.rotate;
            } else {
                textView = this.V;
                i = R.string.crop;
            }
            textView.setText(i);
        }
        linearLayout.addView(this.V);
        this.V.setOnClickListener(this.X);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean o(int i) {
        return M0(i, true);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.R = getIntent().getBooleanExtra("just_rotate", false);
        this.S = com.byd.auto_photo.a.f1178a.intValue() >= 50;
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        return M0(i, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        CropImageView cropImageView = this.Q;
        if (cropImageView != null) {
            cropImageView.setUiStop(true);
        }
        super.onPause();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int q0() {
        return (this.R || !this.D) ? super.q0() : getResources().getDimensionPixelSize(R.dimen.edit_image_view_height);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int r0() {
        return (this.R || !this.D) ? super.r0() : getResources().getDimensionPixelSize(R.dimen.edit_image_view_width);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        if (this.w.getItemCount() == 0) {
            L0();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected View v0(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_crop, (ViewGroup) null);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void w0() {
        if (this.R && this.Q.getDegree() == 0.0f && this.Q.getMirrorType() == 0 && this.Q.getSwapDegrees() == 0.0f) {
            w.c("onSureClick for nothing");
            finish();
        } else {
            y0();
            new c().start();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void x0(Bitmap bitmap) {
        w.c("CropActivity set Bitmap , rotate->" + this.R);
        if (!this.R && bitmap != null) {
            N0();
        }
        this.Q.setImageBitmap(bitmap);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void z0(boolean z) {
        super.z0(z);
        if (this.S && this.w.i(0).isEnable() != z) {
            this.w.i(0).setEnable(z);
            this.w.notifyItemChanged(0);
        }
    }
}
