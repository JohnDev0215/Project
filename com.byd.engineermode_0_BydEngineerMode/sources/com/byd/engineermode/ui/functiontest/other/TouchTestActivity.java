package com.byd.engineermode.ui.functiontest.other;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.utils.g;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class TouchTestActivity extends BaseActivity {
    private OrientationEventListener D;
    private Paint v;
    private DisplayMetrics w;
    private final int z = 10;
    private final int A = 18;
    private int x;
    private float B = this.x / 10;
    private int y;
    private float C = this.y / 18;
    private Boolean E = Boolean.FALSE;

    public class MyView extends View {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Bitmap f2482b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Canvas f2483c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Path f2484d;
        private Paint e;
        private Paint f;
        private Paint g;
        private float h;
        private float i;
        private int[][] j;
        private boolean k;

        public MyView(Context context) {
            super(context);
            this.k = false;
            TouchTestActivity.this.w = new DisplayMetrics();
            TouchTestActivity.this.getWindowManager().getDefaultDisplay().getMetrics(TouchTestActivity.this.w);
            TouchTestActivity.this.x = TouchTestActivity.this.w.widthPixels;
            TouchTestActivity.this.y = TouchTestActivity.this.w.heightPixels + ((int) g.b(60.0f));
            TouchTestActivity.this.B = TouchTestActivity.this.x / 10.0f;
            TouchTestActivity.this.C = TouchTestActivity.this.y / 18.0f;
            TouchTestActivity.this.a0("mScrWidth:" + TouchTestActivity.this.x + ",mScrHeight:" + TouchTestActivity.this.y + "mBoxWidth:" + TouchTestActivity.this.B + ",mBoxHeight:" + TouchTestActivity.this.C);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(TouchTestActivity.this.x, TouchTestActivity.this.y, Bitmap.Config.ARGB_8888);
            this.f2482b = bitmapCreateBitmap;
            this.f2483c = new Canvas(bitmapCreateBitmap);
            this.f2484d = new Path();
            this.e = new Paint(4);
            Paint paint = new Paint();
            this.f = paint;
            paint.setAntiAlias(true);
            this.f.setColor(-10250772);
            this.f.setStyle(Paint.Style.FILL);
            this.g = new Paint();
            this.f.setColor(-16777216);
            this.j = (int[][]) Array.newInstance((Class<?>) int.class, 10, 18);
        }

        private void a(Canvas canvas, float f, float f2) {
            for (int i = 0; i < 10; i++) {
                int i2 = 0;
                while (true) {
                    if (i2 < 18) {
                        int i3 = i2 + 1;
                        if (f2 < i3 * TouchTestActivity.this.C && f2 > 0.0f && f > i * TouchTestActivity.this.B && f < (i + 1) * TouchTestActivity.this.B) {
                            this.j[i][i2] = 1;
                            break;
                        }
                        i2 = i3;
                    }
                }
            }
        }

        private void b(float f, float f2) {
            float fAbs = Math.abs(f - this.h);
            float fAbs2 = Math.abs(f2 - this.i);
            if (fAbs >= 4.0f || fAbs2 >= 4.0f) {
                Path path = this.f2484d;
                float f3 = this.h;
                float f4 = this.i;
                path.quadTo(f3, f4, (f + f3) / 2.0f, (f2 + f4) / 2.0f);
                this.h = f;
                this.i = f2;
            }
        }

        private void c(float f, float f2) {
            this.f2484d.reset();
            this.f2484d.moveTo(f, f2);
            this.h = f;
            this.i = f2;
        }

        private void d() {
            this.f2484d.lineTo(this.h, this.i);
            this.f2483c.drawPath(this.f2484d, TouchTestActivity.this.v);
            this.f2484d.reset();
        }

        @Override // android.view.View
        protected void onDetachedFromWindow() {
            Bitmap bitmap = this.f2482b;
            if (bitmap != null) {
                bitmap.recycle();
            }
            super.onDetachedFromWindow();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            if (TouchTestActivity.this.E.booleanValue()) {
                return;
            }
            canvas.drawColor(-1);
            for (int i = 0; i < 10; i++) {
                int i2 = 0;
                while (true) {
                    if (i2 >= 18) {
                        break;
                    }
                    if (this.j[i][i2] != 1) {
                        this.k = false;
                        break;
                    } else {
                        this.k = true;
                        i2++;
                    }
                }
                if (!this.k) {
                    break;
                }
            }
            TouchTestActivity.this.a0("mAllDraw:" + this.k);
            if (true == this.k && !TouchTestActivity.this.E.booleanValue()) {
                TouchTestActivity.this.E = Boolean.TRUE;
                TouchTestActivity.this.J();
                return;
            }
            canvas.drawBitmap(this.f2482b, 0.0f, 0.0f, this.e);
            a(canvas, this.h, this.i);
            for (int i3 = 0; i3 < 10; i3++) {
                for (int i4 = 0; i4 < 18; i4++) {
                    if (this.j[i3][i4] == 0) {
                        this.f.setColor(-10250772);
                        this.f.setStyle(Paint.Style.FILL);
                        float f = i3;
                        float f2 = i4;
                        canvas.drawRect(f * TouchTestActivity.this.B, f2 * TouchTestActivity.this.C, TouchTestActivity.this.B + (TouchTestActivity.this.B * f), TouchTestActivity.this.C + (TouchTestActivity.this.C * f2), this.f);
                        canvas.drawText("0", (f * TouchTestActivity.this.B) + (TouchTestActivity.this.B / 2.0f), (f2 * TouchTestActivity.this.C) + (TouchTestActivity.this.C / 2.0f), this.g);
                    } else {
                        this.f.setColor(-16711936);
                        this.f.setStyle(Paint.Style.FILL);
                        float f3 = i3;
                        float f4 = i4;
                        canvas.drawRect(f3 * TouchTestActivity.this.B, f4 * TouchTestActivity.this.C, TouchTestActivity.this.B + (TouchTestActivity.this.B * f3), TouchTestActivity.this.C + (TouchTestActivity.this.C * f4), this.f);
                        canvas.drawText("1", (f3 * TouchTestActivity.this.B) + (TouchTestActivity.this.B / 2.0f), (f4 * TouchTestActivity.this.C) + (TouchTestActivity.this.C / 2.0f), this.g);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                c(x, y);
                invalidate();
            } else if (action == 1) {
                d();
                invalidate();
            } else if (action == 2) {
                b(x, y);
                invalidate();
            }
            return true;
        }
    }

    private class a extends OrientationEventListener {
        public a(Context context) {
            super(context);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            int rotation = ((WindowManager) TouchTestActivity.this.getSystemService("window")).getDefaultDisplay().getRotation();
            TouchTestActivity.this.a0("MyOrientationEventListener:newRotation=" + rotation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0(String str) {
        Log.d("touchtest", str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        a0("onConfigurationChanged:" + configuration);
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        a0("onConfigurationChanged:widthDP=" + i);
        a0("onConfigurationChanged:heightDP=" + i2);
        super.onConfigurationChanged(configuration);
        int i3 = configuration.orientation;
        getResources().getConfiguration();
        if (i3 == 1) {
            a0("onConfigurationChanged:portrait=" + i);
            return;
        }
        int i4 = configuration.orientation;
        getResources().getConfiguration();
        if (i4 == 2) {
            a0("onConfigurationChanged:landscape=" + i);
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(new MyView(this));
        Paint paint = new Paint();
        this.v = paint;
        paint.setAntiAlias(true);
        this.v.setDither(true);
        this.v.setColor(-16777216);
        this.v.setStyle(Paint.Style.STROKE);
        this.v.setStrokeJoin(Paint.Join.ROUND);
        this.v.setStrokeCap(Paint.Cap.ROUND);
        this.v.setStrokeWidth(2.0f);
        a aVar = new a(this);
        this.D = aVar;
        aVar.enable();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        I();
        return true;
    }
}
