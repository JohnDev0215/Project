package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.q;
import com.byd.imageedit.ScrawlImageView;
import com.byd.util.h;
import com.byd.util.w;
import com.byd.view.ColorSelectBar;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseScrawlActivity extends BaseEditActivity implements q.a, ColorSelectBar.OnColorChangeListener {
    protected ScrawlImageView Q;
    private FrameLayout.LayoutParams R;
    protected RecyclerView S;
    protected q T;
    protected FrameLayout U;
    protected View V;
    protected ColorSelectBar W;
    protected int[] X;
    private int Y;
    private int Z;
    protected TextView a0;
    private LinearLayout b0;
    protected TextView c0;
    protected ImageView d0;
    protected ImageView e0;
    private LinearLayout.LayoutParams f0;
    private LinearLayout.LayoutParams g0;
    private long h0;

    class a implements ScrawlImageView.b {
        a() {
        }

        @Override // com.byd.imageedit.ScrawlImageView.b
        public void a(ScrawlImageView scrawlImageView) {
            ImageView imageView;
            boolean z = false;
            if (scrawlImageView.h()) {
                if (BaseScrawlActivity.this.c0.getVisibility() == 0) {
                    BaseScrawlActivity.this.c0.setVisibility(8);
                    BaseScrawlActivity.this.d0.setVisibility(0);
                    BaseScrawlActivity.this.e0.setVisibility(0);
                }
                imageView = BaseScrawlActivity.this.d0;
                z = true;
            } else {
                imageView = BaseScrawlActivity.this.d0;
            }
            imageView.setEnabled(z);
            BaseScrawlActivity.this.z0(z);
            BaseScrawlActivity.this.e0.setEnabled(scrawlImageView.b());
        }
    }

    class b implements ViewTreeObserver.OnPreDrawListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            int measuredHeight;
            int measuredWidth;
            int i = BaseScrawlActivity.this.getResources().getConfiguration().orientation;
            if (i == 2) {
                measuredWidth = BaseScrawlActivity.this.S.getMeasuredWidth();
                measuredHeight = BaseScrawlActivity.this.getResources().getDisplayMetrics().heightPixels;
            } else {
                measuredHeight = BaseScrawlActivity.this.S.getMeasuredHeight();
                measuredWidth = BaseScrawlActivity.this.getResources().getDisplayMetrics().widthPixels;
            }
            if (BaseScrawlActivity.this.Y == measuredWidth && BaseScrawlActivity.this.Z == measuredHeight) {
                BaseScrawlActivity.this.S.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
            BaseScrawlActivity.this.Y = measuredWidth;
            BaseScrawlActivity.this.Z = measuredHeight;
            BaseScrawlActivity baseScrawlActivity = BaseScrawlActivity.this;
            baseScrawlActivity.T.b(baseScrawlActivity.Y, BaseScrawlActivity.this.Z, i);
            return true;
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseScrawlActivity.this.Q.q();
        }
    }

    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseScrawlActivity.this.Q.i();
        }
    }

    class e extends Thread {
        e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Bitmap bitmap = BaseScrawlActivity.this.Q.getBitmap();
            BaseScrawlActivity baseScrawlActivity = BaseScrawlActivity.this;
            if (baseScrawlActivity.v) {
                Intent intent = new Intent();
                intent.putExtra("photo_intent_data", bitmap);
                BaseScrawlActivity.this.setResult(-1, intent);
            } else {
                Uri uri = baseScrawlActivity.t;
                if (uri != null && b.a.h.a.r(bitmap, uri.getPath(), Bitmap.Config.RGB_565)) {
                    BaseScrawlActivity.this.setResult(-1);
                    bitmap.recycle();
                }
            }
            BaseScrawlActivity.this.s0();
            BaseScrawlActivity.this.finish();
        }
    }

    private Drawable H0(int i) {
        return h.c(getResources().getDrawable(i), new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected, android.R.attr.state_enabled}, new int[]{android.R.attr.state_enabled}, new int[0]}, new int[]{com.byd.util.e.l(this, R.attr.text_color_press), com.byd.util.e.l(this, R.attr.text_color), -8355712}));
    }

    private Drawable I0(int i) {
        return h.c(getResources().getDrawable(i), new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, new int[]{android.R.attr.state_enabled}, new int[0]}, new int[]{com.byd.util.e.l(this, R.attr.text_color_press), com.byd.util.e.l(this, R.attr.text_color), getColor(R.color.color_disable)}));
    }

    protected int J0(String str, int i) {
        return getSharedPreferences("stylesetting", 0).getInt(str, i);
    }

    protected int K0() {
        return getResources().getDimensionPixelSize(R.dimen.edit_image_view_height);
    }

    protected int L0() {
        return getResources().getDimensionPixelSize(R.dimen.edit_image_view_width);
    }

    protected abstract String M0();

    protected void N0() {
        this.a0 = (TextView) findViewById(R.id.tv_color_tips);
        this.V = findViewById(R.id.color_select_container);
        this.W = (ColorSelectBar) x(R.id.seek_color);
        if (com.byd.auto_photo.a.f1178a.intValue() >= 35) {
            this.W.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.dp_30);
            this.W.setProgressRadius(getResources().getDimensionPixelSize(R.dimen.dp_2));
            this.W.setThumbRadius(getResources().getDimensionPixelSize(R.dimen.dp_8));
        }
    }

    protected void O0() {
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        this.S = (RecyclerView) x(R.id.recycler_styles);
        if (getResources().getConfiguration().orientation == 2) {
            recyclerView = this.S;
            linearLayoutManager = new LinearLayoutManager(this);
        } else {
            recyclerView = this.S;
            linearLayoutManager = new LinearLayoutManager(this, 0, false);
        }
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    protected void P0(String str, int i) {
        getSharedPreferences("stylesetting", 0).edit().putInt(str, i).apply();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        super.T(configuration);
        this.a0.setText(R.string.color);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        this.U.removeAllViews();
        this.b0.removeAllViews();
        super.U(configuration);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        super.V(z);
        this.d0.setImageDrawable(I0(R.drawable.icon_edit_undo));
        this.e0.setImageDrawable(I0(R.drawable.icon_edit_redo));
        this.c0.setTextColor(com.byd.util.e.l(this, R.attr.text_color));
        q qVar = this.T;
        if (qVar != null) {
            qVar.h(new Drawable[]{H0(R.drawable.icon_edit_paint_size_0), H0(R.drawable.icon_edit_paint_size_1), H0(R.drawable.icon_edit_paint_size_2), H0(R.drawable.icon_edit_paint_size_3), H0(R.drawable.icon_edit_paint_size_4)});
            this.T.notifyDataSetChanged();
        }
        this.a0.setTextColor(com.byd.util.e.l(this, R.attr.text_color));
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
        this.b0 = linearLayout;
        if (this.c0 == null) {
            this.c0 = (TextView) getLayoutInflater().inflate(R.layout.view_img_edit_title, (ViewGroup) null);
        }
        this.c0.setTextColor(com.byd.util.e.l(this, R.attr.text_color));
        this.c0.setText(M0());
        if (this.d0 == null) {
            ImageView imageView = new ImageView(this);
            this.d0 = imageView;
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.d0.setOnClickListener(new c());
        }
        this.d0.setImageDrawable(I0(R.drawable.icon_edit_undo));
        this.f0 = new LinearLayout.LayoutParams(-2, 60);
        if (linearLayout.getOrientation() == 1) {
            LinearLayout.LayoutParams layoutParams = this.f0;
            layoutParams.rightMargin = 0;
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.undo_redo_vertical_margin);
        } else {
            this.f0.rightMargin = getResources().getDimensionPixelSize(R.dimen.undo_redo_horizontal_margin);
            this.f0.bottomMargin = 0;
        }
        if (this.e0 == null) {
            ImageView imageView2 = new ImageView(this);
            this.e0 = imageView2;
            imageView2.setScaleType(ImageView.ScaleType.CENTER);
            this.e0.setOnClickListener(new d());
        }
        this.e0.setImageDrawable(I0(R.drawable.icon_edit_redo));
        this.g0 = new LinearLayout.LayoutParams(-2, 60);
        linearLayout.addView(this.c0);
        linearLayout.addView(this.d0, this.f0);
        linearLayout.addView(this.e0, this.g0);
        if (!this.Q.h() && !this.Q.b()) {
            this.c0.setVisibility(0);
            this.e0.setVisibility(8);
            this.d0.setVisibility(8);
        } else {
            this.c0.setVisibility(8);
            this.d0.setVisibility(0);
            this.e0.setVisibility(0);
            this.d0.setEnabled(this.Q.h());
            this.e0.setEnabled(this.Q.b());
        }
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected boolean m0(LinearLayout linearLayout, int i) {
        String str;
        if (super.m0(linearLayout, i)) {
            return true;
        }
        if (getResources().getConfiguration().orientation == 2) {
            linearLayout.setOrientation(1);
            str = "addEditTitleView vertical";
        } else {
            linearLayout.setOrientation(0);
            str = "addEditTitleView HORIZONTAL";
        }
        w.c(str);
        return true;
    }

    @Override // com.byd.view.ColorSelectBar.OnColorChangeListener
    public void onColorChange(int i) {
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.Q.j();
        super.onDestroy();
    }

    @Override // b.a.e.q.a
    public void onStyleItemClick(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.h0 <= 500) {
            w.c("click too fast!!");
        } else {
            this.h0 = jCurrentTimeMillis;
            this.T.f(i);
        }
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity
    protected void t0() {
        super.t0();
        this.U = (FrameLayout) x(R.id.scrawl_container);
        int iL0 = L0();
        int iK0 = K0();
        w.c("BaseScrawlActivity initLayout " + iL0 + " " + iK0);
        if (this.Q == null) {
            this.Q = new ScrawlImageView(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iL0, iK0);
            this.R = layoutParams;
            layoutParams.gravity = 17;
            this.Q.setOnScrawlListener(new a());
        } else {
            FrameLayout.LayoutParams layoutParams2 = this.R;
            layoutParams2.width = iL0;
            layoutParams2.height = iK0;
        }
        this.U.addView(this.Q, this.R);
        N0();
        O0();
        if (this.T == null) {
            q qVar = new q(this);
            this.T = qVar;
            qVar.i(this);
        }
        this.S.setAdapter(this.T);
        this.S.getViewTreeObserver().addOnPreDrawListener(new b());
        if (this.X == null) {
            this.X = new int[]{5, 10, 15, 20, 25};
        }
        if (this.T.c() == null) {
            this.T.h(new Drawable[]{H0(R.drawable.icon_edit_paint_size_0), H0(R.drawable.icon_edit_paint_size_1), H0(R.drawable.icon_edit_paint_size_2), H0(R.drawable.icon_edit_paint_size_3), H0(R.drawable.icon_edit_paint_size_4)});
        }
        this.T.g(this.X);
        this.W.setOnColorChangeListener(this);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected View v0(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate((com.byd.util.e.H(this) && getResources().getConfiguration().orientation == 2) ? R.layout.activity_scrawl_right_driver : R.layout.activity_scrawl, (ViewGroup) null);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void w0() {
        if (!this.Q.h()) {
            finish();
        } else {
            y0();
            new e().start();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void x0(Bitmap bitmap) {
        w.c("BaseScrawlActivity setBitmap");
        this.Q.setImageBitmap(bitmap);
    }
}
