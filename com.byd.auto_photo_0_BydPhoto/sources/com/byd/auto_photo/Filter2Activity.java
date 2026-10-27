package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.d;
import com.byd.bean.ImageEditFilterItem;
import com.byd.filter2.GLImageSurfaceView;
import com.byd.filter2.f.a;
import com.byd.filter2.g.c;
import com.byd.filter2.g.f;
import com.byd.filter2.g.g;
import com.byd.filter2.g.h;
import com.byd.filter2.g.i;
import com.byd.filter2.g.j;
import com.byd.filter2.g.k;
import com.byd.filter2.g.l;
import com.byd.filter2.g.m;
import com.byd.util.e;
import com.byd.util.w;
import java.io.File;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class Filter2Activity extends BaseEditActivity {
    private GLImageSurfaceView Q;
    private ImageEditFilterItem[] S;
    private TextView T;
    private TextView U;
    private long W;
    private TextView Y;
    private PopupWindow Z;
    private Handler a0;
    private Handler R = new Handler(Looper.getMainLooper());
    private int V = -1;
    private int X = -1;

    class a implements a.InterfaceC0076a {

        /* JADX INFO: renamed from: com.byd.auto_photo.Filter2Activity$a$a, reason: collision with other inner class name */
        class RunnableC0029a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f1057a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f1058b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ ByteBuffer f1059c;

            /* JADX INFO: renamed from: com.byd.auto_photo.Filter2Activity$a$a$a, reason: collision with other inner class name */
            class C0030a extends Thread {
                C0030a() {
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    RunnableC0029a runnableC0029a = RunnableC0029a.this;
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(runnableC0029a.f1057a, runnableC0029a.f1058b, Bitmap.Config.ARGB_8888);
                    bitmapCreateBitmap.copyPixelsFromBuffer(RunnableC0029a.this.f1059c);
                    Matrix matrix = new Matrix();
                    matrix.reset();
                    matrix.postRotate(180.0f);
                    matrix.postScale(-1.0f, 1.0f);
                    matrix.postTranslate(bitmapCreateBitmap.getWidth(), 0.0f);
                    Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), matrix, true);
                    if (!bitmapCreateBitmap.isRecycled()) {
                        bitmapCreateBitmap.recycle();
                    }
                    Filter2Activity filter2Activity = Filter2Activity.this;
                    if (filter2Activity.v) {
                        Intent intent = new Intent();
                        intent.putExtra("photo_intent_data", bitmapCreateBitmap2);
                        Filter2Activity.this.setResult(-1, intent);
                        w.c("==filter save 000==");
                    } else {
                        b.a.h.a.r(bitmapCreateBitmap2, filter2Activity.t.getPath(), Bitmap.Config.ARGB_8888);
                        File file = new File(Filter2Activity.this.t.getPath());
                        w.c("==filter save 111==" + Filter2Activity.this.t.getPath() + " " + file.exists() + " " + file.length());
                        Filter2Activity.this.setResult(-1);
                        bitmapCreateBitmap2.recycle();
                    }
                    Filter2Activity.this.s0();
                    Filter2Activity.this.finish();
                }
            }

            RunnableC0029a(int i, int i2, ByteBuffer byteBuffer) {
                this.f1057a = i;
                this.f1058b = i2;
                this.f1059c = byteBuffer;
            }

            @Override // java.lang.Runnable
            public void run() {
                Filter2Activity.this.y0();
                new C0030a().start();
            }
        }

        a() {
        }

        @Override // com.byd.filter2.f.a.InterfaceC0076a
        public void a(ByteBuffer byteBuffer, int i, int i2) {
            Filter2Activity.this.R.post(new RunnableC0029a(i, i2, byteBuffer));
        }
    }

    private static class b extends d<Filter2Activity> {
        public b(Filter2Activity filter2Activity) {
            super(filter2Activity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(Filter2Activity filter2Activity, Message message) {
            int i = message.what;
            if (i == 1) {
                filter2Activity.I0();
            } else {
                if (i != 2) {
                    return;
                }
                filter2Activity.G0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G0() {
        this.Q.h(this.S[this.w.g()].getFilter());
        this.x.scrollToPosition(this.w.g());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void H0(int i) {
        boolean z;
        ImageEditFilterItem imageEditFilterItem;
        com.byd.filter2.e.b hVar;
        switch (i) {
            case 0:
                this.Q.h(null);
                L0(getResources().getString(this.S[i].getNameRes()));
                z = false;
                break;
            case 1:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new h(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 2:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new c(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 3:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new com.byd.filter2.g.b(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 4:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new f(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 5:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new j(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 6:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new g(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 7:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new m(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 8:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new i(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 9:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new com.byd.filter2.g.a(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 10:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new l(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            case 11:
                if (this.S[i].getFilter() == null) {
                    imageEditFilterItem = this.S[i];
                    hVar = new k(getResources());
                    imageEditFilterItem.setFilter(hVar);
                }
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
            default:
                this.Q.h(this.S[i].getFilter());
                L0(getResources().getString(this.S[i].getNameRes()));
                z = true;
                break;
        }
        z0(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0() {
        PopupWindow popupWindow = this.Z;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.Z.dismiss();
    }

    private void J0() {
        ImageEditFilterItem[] imageEditFilterItemArr = new ImageEditFilterItem[12];
        this.S = imageEditFilterItemArr;
        imageEditFilterItemArr[0] = new ImageEditFilterItem(R.drawable.icon_filter_none);
        this.S[0].setNameRes(R.string.filter_original);
        this.S[1] = new ImageEditFilterItem(R.drawable.icon_filter_lomo);
        this.S[1].setNameRes(R.string.filter_lomo);
        this.S[2] = new ImageEditFilterItem(R.drawable.icon_filter_classify);
        this.S[2].setNameRes(R.string.filter_classical);
        this.S[3] = new ImageEditFilterItem(R.drawable.icon_filter_freshness);
        this.S[3].setNameRes(R.string.filter_freshness);
        this.S[4] = new ImageEditFilterItem(R.drawable.icon_filter_gorgeous);
        this.S[4].setNameRes(R.string.filter_gorgeous);
        this.S[5] = new ImageEditFilterItem(R.drawable.icon_filter_romance);
        this.S[5].setNameRes(R.string.filter_romantic);
        this.S[6] = new ImageEditFilterItem(R.drawable.icon_filter_height_cold);
        this.S[6].setNameRes(R.string.filter_reserved);
        this.S[7] = new ImageEditFilterItem(R.drawable.icon_filter_dream);
        this.S[7].setNameRes(R.string.filter_dream);
        this.S[8] = new ImageEditFilterItem(R.drawable.icon_filter_nostalgic);
        this.S[8].setNameRes(R.string.filter_retro);
        this.S[9] = new ImageEditFilterItem(R.drawable.icon_filter_black_wite);
        this.S[9].setNameRes(R.string.filter_gray);
        this.S[10] = new ImageEditFilterItem(R.drawable.icon_filter_sketch);
        this.S[10].setNameRes(R.string.filter_sketch);
        this.S[11] = new ImageEditFilterItem(R.drawable.icon_filter_simple_and_elegant);
        this.S[11].setNameRes(R.string.filter_elegant);
        this.w.q(this.S);
    }

    private void K0(Bitmap bitmap) {
        this.Q.setBitmap(bitmap);
        this.Q.requestRender();
    }

    private void L0(String str) {
        this.a0.removeMessages(1);
        if (this.Z == null) {
            TextView textView = new TextView(this);
            this.Y = textView;
            textView.setBackgroundResource(R.drawable.bg_filter_name_drawable);
            this.Y.setTextColor(-1);
            this.Y.setLayoutParams(new WindowManager.LayoutParams(-2, -2));
            PopupWindow popupWindow = new PopupWindow(this.Y, -2, -2);
            this.Z = popupWindow;
            popupWindow.setOutsideTouchable(false);
            this.Z.setFocusable(false);
        }
        this.Y.setText(str);
        this.Y.setGravity(17);
        if (!this.Z.isShowing()) {
            this.Z.showAsDropDown(this.T, 0, 0);
        }
        this.a0.sendEmptyMessageDelayed(1, 1500L);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        super.V(z);
        this.U.setTextColor(e.l(this, R.attr.text_color));
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void i0(LinearLayout linearLayout) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.view_img_edit_title, (ViewGroup) null);
        this.U = textView;
        textView.setTextColor(e.l(this, R.attr.text_color));
        this.U.setText(R.string.filter);
        linearLayout.addView(this.U);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean o(int i) {
        int i2 = this.V;
        if (i2 != i) {
            super.o(i);
            this.V = i;
            H0(i);
            return true;
        }
        if (i2 == 0) {
            return true;
        }
        this.X = i;
        this.Q.h(null);
        return true;
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        I0();
        super.onConfigurationChanged(configuration);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.a0 = new b(this);
        super.onCreate(bundle);
        J0();
        this.w.w(true);
        this.w.v(true);
        this.w.n(0);
    }

    @Override // com.byd.auto_photo.BaseEditActivity, com.byd.auto_photo.SuperBaseEditActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.Q.g();
        super.onDestroy();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public boolean onItemClick(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.W <= 500) {
            w.c("Filter2Activity item click too fast");
            return false;
        }
        this.W = jCurrentTimeMillis;
        if (this.V == i) {
            return false;
        }
        this.V = i;
        super.onItemClick(i);
        H0(i);
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity, b.a.e.c.b
    public void p(int i) {
        if (this.X != -1) {
            this.Q.h(this.S[i].getFilter());
            this.X = -1;
        }
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
    public void t0() {
        super.t0();
        this.Q = (GLImageSurfaceView) x(R.id.gl_surface);
        this.T = (TextView) x(R.id.tv_file_name_tips);
        if (this.s != null) {
            w.c("Filter2 setBitmap initLayout");
            K0(this.s);
            if (this.w.g() > 0) {
                this.a0.removeMessages(2);
                this.a0.sendEmptyMessageDelayed(2, 300L);
            }
        }
        this.Q.setCaptureCallback(new a());
        RecyclerView.LayoutManager layoutManager = this.x.getLayoutManager();
        if ((layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).getOrientation() == 1) {
            ((ViewGroup.MarginLayoutParams) this.x.getLayoutParams()).leftMargin = 60;
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected View v0(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_filter2, (ViewGroup) null);
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void w0() {
        if (this.Q.getEffectFilter() == null) {
            finish();
        } else {
            this.Q.i();
        }
    }

    @Override // com.byd.auto_photo.SuperBaseEditActivity
    protected void x0(Bitmap bitmap) {
        w.c("Filter2Activity setBitmap");
        K0(bitmap);
    }
}
