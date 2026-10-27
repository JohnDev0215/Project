package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.byd.bean.ImageEditItem;
import com.byd.drawable.BaseDressDrawable;
import com.byd.drawable.BitmapDressDrawable;
import com.byd.drawable.TextDressDrawable;
import com.byd.fragment.MarkTextDialogBaseFragment;
import com.byd.fragment.MarkTextDialogFragment;
import com.byd.imageedit.DressUpImageView;
import com.byd.util.n;
import com.byd.util.w;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public abstract class WatermarkBaseActivity extends BaseEditActivity {
    public int Q = -16776961;
    private DressUpImageView R;
    private ViewGroup S;
    private boolean T;
    private int[] U;
    private int[] V;
    private int W;
    private FrameLayout.LayoutParams X;
    private BaseDressDrawable Y;
    private MarkTextDialogFragment Z;

    class a implements DressUpImageView.b {
        a() {
        }

        @Override // com.byd.imageedit.DressUpImageView.b
        public void a(BaseDressDrawable baseDressDrawable, int i) {
            if (i != 1) {
                return;
            }
            WatermarkBaseActivity.this.R.g(baseDressDrawable);
            if (WatermarkBaseActivity.this.R.getDrawableCounts() == 0) {
                WatermarkBaseActivity.this.z0(false);
            }
        }

        @Override // com.byd.imageedit.DressUpImageView.b
        public void b(BaseDressDrawable baseDressDrawable) {
            if (baseDressDrawable instanceof TextDressDrawable) {
                WatermarkBaseActivity.this.M0((TextDressDrawable) baseDressDrawable);
            }
        }
    }

    class b implements ViewTreeObserver.OnPreDrawListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            WatermarkBaseActivity watermarkBaseActivity = WatermarkBaseActivity.this;
            if (watermarkBaseActivity.s == null) {
                return false;
            }
            watermarkBaseActivity.R.getViewTreeObserver().removeOnPreDrawListener(this);
            int measuredWidth = WatermarkBaseActivity.this.R.getMeasuredWidth();
            int measuredHeight = WatermarkBaseActivity.this.R.getMeasuredHeight();
            int width = WatermarkBaseActivity.this.s.getWidth();
            int height = WatermarkBaseActivity.this.s.getHeight();
            w.c("====water mark onPreDraw : " + measuredWidth + "x" + measuredHeight + "  bitmap size : " + WatermarkBaseActivity.this.s.getWidth() + "x" + WatermarkBaseActivity.this.s.getHeight());
            if (width > measuredWidth || height > measuredHeight) {
                return true;
            }
            ViewGroup.LayoutParams layoutParams = WatermarkBaseActivity.this.R.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            WatermarkBaseActivity.this.R.requestLayout();
            return true;
        }
    }

    class c extends Thread {
        c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WatermarkBaseActivity watermarkBaseActivity = WatermarkBaseActivity.this;
            if (watermarkBaseActivity.v) {
                Intent intent = new Intent();
                intent.putExtra("photo_intent_data", WatermarkBaseActivity.this.R.getBitmap());
                WatermarkBaseActivity.this.setResult(-1, intent);
            } else if (watermarkBaseActivity.t != null && b.a.h.a.r(watermarkBaseActivity.R.getBitmap(), WatermarkBaseActivity.this.t.getPath(), Bitmap.Config.RGB_565)) {
                WatermarkBaseActivity.this.setResult(-1);
            }
            WatermarkBaseActivity.this.s0();
            WatermarkBaseActivity.this.finish();
        }
    }

    class d implements MarkTextDialogBaseFragment.OnTextInputListener {
        d() {
        }

        @Override // com.byd.fragment.MarkTextDialogBaseFragment.OnTextInputListener
        public void onInputFinish(String str, int i) {
            WatermarkBaseActivity.this.K0(str, i);
        }
    }

    class e extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1172a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f1173b;

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ TextDressDrawable f1175a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ LinkedList f1176b;

            a(TextDressDrawable textDressDrawable, LinkedList linkedList) {
                this.f1175a = textDressDrawable;
                this.f1176b = linkedList;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (WatermarkBaseActivity.this.Y != null) {
                    ((TextDressDrawable) WatermarkBaseActivity.this.Y).setContents(this.f1176b);
                } else {
                    this.f1175a.setContents(this.f1176b);
                    WatermarkBaseActivity.this.J0(this.f1175a);
                }
            }
        }

        e(int i, String str) {
            this.f1172a = i;
            this.f1173b = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            w.c("startTextConvert");
            TextDressDrawable textDressDrawable = WatermarkBaseActivity.this.Y == null ? new TextDressDrawable(WatermarkBaseActivity.this.R, "") : (TextDressDrawable) WatermarkBaseActivity.this.Y;
            textDressDrawable.setTextColorNotInvalidate(this.f1172a);
            textDressDrawable.setTextNotInvalidate(this.f1173b);
            LinkedList<String> linkedList = new LinkedList<>();
            textDressDrawable.convertContent(linkedList);
            w.c("convert finish");
            WatermarkBaseActivity.this.runOnUiThread(new a(textDressDrawable, linkedList));
        }
    }

    private void I0(int i, String str) {
        this.R.d();
        BitmapDressDrawable bitmapDressDrawable = new BitmapDressDrawable(this.R, ((BitmapDrawable) getResources().getDrawable(i)).getBitmap(), str);
        bitmapDressDrawable.setControllerRadius(this.W);
        bitmapDressDrawable.setDashLine(false);
        L0(bitmapDressDrawable);
        this.R.c(bitmapDressDrawable);
        z0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0(TextDressDrawable textDressDrawable) {
        textDressDrawable.setControllerRadius(this.W);
        textDressDrawable.setDashLine(false);
        L0(textDressDrawable);
        this.R.c(textDressDrawable);
        z0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K0(String str, int i) {
        N0(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0(TextDressDrawable textDressDrawable) {
        this.Y = textDressDrawable;
        if (this.Z == null) {
            MarkTextDialogFragment markTextDialogFragment = new MarkTextDialogFragment();
            this.Z = markTextDialogFragment;
            markTextDialogFragment.setOnTextInputListener(new d());
        }
        this.Z.setEditDrawable(textDressDrawable);
        this.Z.show(getSupportFragmentManager(), "edit_text");
    }

    private void N0(String str, int i) {
        new e(i, str).start();
    }

    protected void L0(BaseDressDrawable baseDressDrawable) {
        int i;
        int i2;
        if ((getResources().getConfiguration().uiMode & 48) == 32) {
            baseDressDrawable.setLineColor(getResources().getColor(R.color.color_paper_border_night, getTheme()));
            i = R.drawable.icon_edit_watermark_del;
            i2 = R.drawable.icon_edit_watermark_rotate;
        } else {
            baseDressDrawable.setLineColor(-1275396609);
            i = R.drawable.icon_edit_watermark_del_day;
            i2 = R.drawable.icon_edit_watermark_rotate_day;
        }
        baseDressDrawable.initControllerItems(i, 0, 0, i2);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        this.S.removeAllViews();
        super.U(configuration);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        super.V(z);
        LinkedList<BaseDressDrawable> dressDrawables = this.R.getDressDrawables();
        if (dressDrawables == null || dressDrawables.size() <= 0) {
            return;
        }
        Iterator<BaseDressDrawable> it = dressDrawables.iterator();
        while (it.hasNext()) {
            L0(it.next());
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            Uri data = intent.getData();
            this.R.c(new BitmapDressDrawable(this.R, b.a.h.a.h(data != null ? n.q(this, data) : intent.getStringExtra("photo_intent_data"), 0, 0, 1, null, false)));
        }
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.T = getIntent().getBooleanExtra("just_word", false);
        super.onCreate(bundle);
        this.W = getResources().getDimensionPixelSize(R.dimen.water_mark_controller_radius);
        this.Q = getResources().getColor(R.color.water_mark_def_text_color);
        if (this.T) {
            this.U = new int[]{R.string.add_text};
            this.V = new int[]{R.drawable.icon_edit_add_text};
        } else {
            this.U = new int[]{R.string.water_mark_1, R.string.water_mark_2, R.string.water_mark_3, R.string.water_mark_4, R.string.water_mark_5};
            this.V = new int[]{R.drawable.tags_1, R.drawable.tags_2, R.drawable.tags_3, R.drawable.tags_4, R.drawable.tags_5};
        }
        ImageEditItem[] imageEditItemArr = new ImageEditItem[this.U.length];
        for (int i = 0; i < this.U.length; i++) {
            imageEditItemArr[i] = new ImageEditItem(this.V[i]);
            if (this.T) {
                imageEditItemArr[i].setNameRes(this.U[i]);
            } else {
                imageEditItemArr[i].setCanTintDrawable(false);
            }
        }
        this.w.w(true ^ this.T);
        this.w.q(imageEditItemArr);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        MarkTextDialogFragment markTextDialogFragment = this.Z;
        if (markTextDialogFragment != null) {
            if (markTextDialogFragment.isShowing()) {
                this.Z.dismissAllowingStateLoss();
            }
            this.Z = null;
        }
        super.onDestroy();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        super.onItemClick(i);
        if (!this.T) {
            I0(this.V[i], String.valueOf(i));
            return true;
        }
        String string = getResources().getString(R.string.please_input_text);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.add_text_def_text_size);
        if (!getResources().getConfiguration().locale.toLanguageTag().toLowerCase().contains("zh")) {
            length = string.length() > 16 ? string.length() : 16;
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.add_text_def_text_size_zh);
        }
        TextDressDrawable textDressDrawable = new TextDressDrawable(this.R, string, dimensionPixelSize, length, getResources().getDimensionPixelOffset(R.dimen.add_text_def_text_offset));
        textDressDrawable.setTextColor(this.Q);
        J0(textDressDrawable);
        return true;
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int q0() {
        return !this.D ? super.q0() : getResources().getDimensionPixelSize(R.dimen.edit_image_view_height);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected int r0() {
        return !this.D ? super.r0() : getResources().getDimensionPixelSize(R.dimen.edit_image_view_width);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        if (this.R == null) {
            this.R = new DressUpImageView(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(r0(), q0());
            this.X = layoutParams;
            layoutParams.gravity = 17;
            this.R.setOnDressUpListener(new a());
            this.R.getViewTreeObserver().addOnPreDrawListener(new b());
        }
        this.S.addView(this.R, this.X);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected View v0(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_watermark, (ViewGroup) null);
        this.S = (ViewGroup) viewInflate.findViewById(R.id.container_watermark);
        return viewInflate;
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void w0() {
        if (this.R.getDrawableCounts() <= 0) {
            finish();
        } else {
            y0();
            new c().start();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void x0(Bitmap bitmap) {
        this.R.setImageBitmap(bitmap);
    }
}
