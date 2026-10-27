package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.c;
import b.a.e.h;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.util.n;
import com.byd.util.w;
import com.byd.view.ListItemDecoration;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public abstract class SuperBaseEditActivity extends SubBaseTaskActivity implements c.b {
    protected View B;
    private String C;
    protected FrameLayout E;
    protected ImageView F;
    protected ImageView G;
    protected LinearLayout H;
    private boolean I;
    private f K;
    private ProgressDialogFragment L;
    protected Bitmap s;
    protected Uri t;
    private int u;
    protected boolean v;
    protected h w;
    protected RecyclerView x;
    protected View y;
    protected int z;
    private int r = 100;
    protected Point A = new Point();
    protected boolean D = true;
    protected int J = 10;
    private View.OnClickListener M = new e();

    class a extends com.byd.taskmanager.b.a<Bitmap> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Uri f1137a;

        a(Uri uri) {
            this.f1137a = uri;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap doTask() {
            StringBuilder sb;
            SuperBaseEditActivity superBaseEditActivity = SuperBaseEditActivity.this;
            superBaseEditActivity.C = n.q(superBaseEditActivity, this.f1137a);
            w.c("image edit : " + this.f1137a + " " + SuperBaseEditActivity.this.C);
            if (SuperBaseEditActivity.this.C == null) {
                try {
                    return BitmapFactory.decodeStream(SuperBaseEditActivity.this.getContentResolver().openInputStream(this.f1137a));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            SuperBaseEditActivity superBaseEditActivity2 = SuperBaseEditActivity.this;
            Bitmap bitmapJ = b.a.h.a.j(SuperBaseEditActivity.this.C, SuperBaseEditActivity.this.u, SuperBaseEditActivity.this.u, superBaseEditActivity2.k0(superBaseEditActivity2.C), Bitmap.Config.ARGB_8888, false);
            if (bitmapJ != null) {
                sb = new StringBuilder();
                sb.append("BaseEditActivity decode ok : ");
                sb.append(bitmapJ.getWidth());
                sb.append(" ");
                sb.append(bitmapJ.getHeight());
                sb.append(" setMaxSize:");
                sb.append(SuperBaseEditActivity.this.u);
            } else {
                sb = new StringBuilder();
                sb.append("BaseEditActivity decode fail : ");
                sb.append(SuperBaseEditActivity.this.C);
                sb.append(" ");
                sb.append(this.f1137a);
            }
            w.c(sb.toString());
            return bitmapJ;
        }
    }

    class b extends com.byd.taskmanager.b.c<Bitmap> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Bitmap bitmap) {
            if (bitmap == null) {
                w.c("image edit -> decode bitmap fail");
                w.e(SuperBaseEditActivity.this, R.string.load_fail);
                SuperBaseEditActivity.this.finish();
            } else {
                SuperBaseEditActivity superBaseEditActivity = SuperBaseEditActivity.this;
                superBaseEditActivity.s = bitmap;
                superBaseEditActivity.x0(bitmap);
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class c implements ViewTreeObserver.OnPreDrawListener {
        c() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            SuperBaseEditActivity.this.x.getViewTreeObserver().removeOnPreDrawListener(this);
            SuperBaseEditActivity.this.j0();
            return true;
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SuperBaseEditActivity.this.o0();
        }
    }

    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_cancel) {
                SuperBaseEditActivity.this.finish();
            } else {
                if (id != R.id.btn_sure) {
                    return;
                }
                SuperBaseEditActivity.this.w0();
            }
        }
    }

    private static class f extends b.a.d<SuperBaseEditActivity> {
        public f(SuperBaseEditActivity superBaseEditActivity) {
            super(superBaseEditActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(SuperBaseEditActivity superBaseEditActivity, Message message) {
            if (message.what != 1) {
                return;
            }
            superBaseEditActivity.o0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o0() {
        ProgressDialogFragment progressDialogFragment = this.L;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.L = null;
        }
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        setContentView(p0());
        Configuration configuration = getResources().getConfiguration();
        this.z = configuration.orientation;
        this.A.set(configuration.screenWidthDp, configuration.screenHeightDp);
        this.y = x(R.id.edit_main_view);
        this.E = (FrameLayout) x(R.id.container);
        this.B = x(R.id.base_edit_tool);
        u0(this.E);
        View viewV0 = v0(getLayoutInflater(), this.E);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(r0(), q0());
        Log.e("m_tag", "ImageEdit container width : " + layoutParams.width + " height : " + layoutParams.height);
        layoutParams.gravity = 17;
        this.E.addView(viewV0, layoutParams);
        this.G = (ImageView) findViewById(R.id.btn_cancel);
        ImageView imageView = (ImageView) findViewById(R.id.btn_sure);
        this.F = imageView;
        imageView.setOnClickListener(this.M);
        this.F.setEnabled(this.I);
        this.G.setOnClickListener(this.M);
        RecyclerView recyclerView = (RecyclerView) x(R.id.recycler_edit_items);
        this.x = recyclerView;
        recyclerView.setHasFixedSize(true);
        h hVar = this.w;
        if (hVar == null) {
            this.w = new h(this);
        } else {
            hVar.k();
        }
        int i = getResources().getConfiguration().orientation;
        this.w.t(i == 2 ? 0 : 1);
        l0();
        this.x.setAdapter(this.w);
        this.w.s(this);
        t0();
        this.x.getViewTreeObserver().addOnPreDrawListener(new c());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.edit_fun_group);
        this.H = linearLayout;
        m0(linearLayout, i);
        i0(this.H);
        n0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        super.S();
        w.c("SuperBaseEdit onGrantedPermission");
        Bitmap bitmap = this.s;
        if (bitmap == null) {
            Intent intent = getIntent();
            this.v = intent.getBooleanExtra("return-data", false);
            this.t = (Uri) intent.getParcelableExtra("output");
            w.c("Image Edit saveUri : " + this.t + " returnData:" + this.v);
            if (!intent.hasExtra("photo_intent_data")) {
                Uri data = intent.getData();
                if (data != null) {
                    v(this.r, new a(data), new b(), true).w(this.r);
                    return;
                }
                return;
            }
            bitmap = (Bitmap) intent.getParcelableExtra("photo_intent_data");
            this.s = bitmap;
        }
        x0(bitmap);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        h hVar = this.w;
        if (hVar != null) {
            hVar.notifyDataSetChanged();
        }
        this.H.removeAllViews();
        i0(this.H);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        int i = this.z;
        int i2 = configuration.orientation;
        if (i == i2) {
            Point point = this.A;
            if (point.x == configuration.screenWidthDp && point.y == configuration.screenHeightDp) {
                return;
            }
        }
        this.z = i2;
        this.A.set(configuration.screenWidthDp, configuration.screenHeightDp);
        w.c("SuperBaseEdit onNormalConfigurationChanged");
        N();
    }

    protected abstract void i0(LinearLayout linearLayout);

    protected void j0() {
        int measuredHeight = this.x.getMeasuredHeight();
        int measuredWidth = this.x.getMeasuredWidth();
        if (getResources().getConfiguration().orientation == 2) {
            measuredHeight = getResources().getDisplayMetrics().heightPixels - getResources().getDimensionPixelSize(R.dimen.dp_50);
        } else {
            measuredWidth = getResources().getDisplayMetrics().widthPixels;
        }
        this.w.A(measuredWidth, measuredHeight, this.J);
    }

    protected int k0(String str) {
        this.u = Math.max(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        int iP = b.a.h.a.p(this.C);
        if (iP <= 1) {
            this.u = 0;
        }
        return iP;
    }

    protected void l0() {
        if (getResources().getConfiguration().orientation == 1) {
            this.x.setLayoutManager(new LinearLayoutManager(this, 0, false));
            this.x.addItemDecoration(new ListItemDecoration(this.J, true));
        } else {
            this.x.setLayoutManager(new LinearLayoutManager(this));
            this.x.addItemDecoration(new ListItemDecoration(this.J));
        }
    }

    protected boolean m0(LinearLayout linearLayout, int i) {
        return false;
    }

    protected void n0() {
    }

    public boolean o(int i) {
        this.x.smoothScrollToPosition(i);
        return true;
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.K = new f(this);
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        Bitmap bitmap = this.s;
        if (bitmap != null) {
            bitmap.recycle();
            this.s = null;
        }
        super.onDestroy();
        w.c("sup BaseEditActivity onDestroy");
        f fVar = this.K;
        if (fVar != null) {
            fVar.release();
            this.K = null;
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
        String str2 = this.C;
        if (str2 != null && str2.startsWith(str)) {
            finish();
        }
    }

    public boolean onItemClick(int i) {
        this.x.smoothScrollToPosition(i);
        return true;
    }

    public void p(int i) {
    }

    protected int p0() {
        return R.layout.activity_base_edit;
    }

    protected int q0() {
        return -1;
    }

    protected int r0() {
        return -1;
    }

    protected void s0() {
        f fVar = this.K;
        if (fVar == null) {
            runOnUiThread(new d());
        } else {
            fVar.sendEmptyMessage(1);
        }
    }

    protected void t0() {
    }

    protected void u0(View view) {
    }

    protected abstract View v0(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract void w0();

    protected abstract void x0(Bitmap bitmap);

    protected void y0() {
        ProgressDialogFragment progressDialogFragment = this.L;
        if (progressDialogFragment == null || !progressDialogFragment.isShowing()) {
            if (this.L == null) {
                this.L = new ProgressDialogFragment(R.string.saving_tips);
            }
            this.L.show(getSupportFragmentManager(), "save");
        }
    }

    protected void z0(boolean z) {
        if (this.I != z) {
            this.I = z;
            this.F.setEnabled(z);
        }
    }
}
