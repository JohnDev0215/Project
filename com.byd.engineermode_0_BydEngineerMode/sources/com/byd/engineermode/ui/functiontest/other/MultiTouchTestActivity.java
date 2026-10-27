package com.byd.engineermode.ui.functiontest.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.ui.dialog.b;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiTouchTestActivity extends BaseActivity {
    public static final int[][] v = {new int[]{255, 0, 0}, new int[]{0, 255, 0}, new int[]{0, 0, 255}, new int[]{255, 255, 0}, new int[]{0, 255, 255}, new int[]{255, 0, 255}, new int[]{100, 0, 0}, new int[]{0, 100, 0}, new int[]{0, 0, 100}, new int[]{100, 100, 0}, new int[]{0, 100, 100}, new int[]{100, 0, 100}, new int[]{255, 255, 255}};
    DisplayMetrics w = new DisplayMetrics();
    private int x = 10;
    PointView y = null;
    private int z = 5;
    b A = null;

    public class PointView extends View {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private HashMap<Integer, com.byd.engineermode.a.c.b> f2477b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ArrayList<com.byd.engineermode.a.c.b> f2478c;

        public PointView(Context context) {
            super(context);
            this.f2477b = new HashMap<>();
            this.f2478c = new ArrayList<>();
        }

        private Paint c(int i) {
            Paint paint = new Paint();
            paint.setAntiAlias(false);
            int[][] iArr = MultiTouchTestActivity.v;
            if (i < iArr.length) {
                paint.setARGB(255, iArr[i][0], iArr[i][1], iArr[i][2]);
            } else {
                paint.setARGB(255, 255, 255, 255);
            }
            paint.setTextSize((int) ((((double) (MultiTouchTestActivity.this.x / 2 >= 1 ? r8 : 1)) * 3.63d) + 7.37d));
            return paint;
        }

        public void a(Canvas canvas) {
            Paint paint = new Paint();
            Rect rect = new Rect();
            String str = String.format(MultiTouchTestActivity.this.getString(R.string.multitouch_tip), Integer.valueOf(MultiTouchTestActivity.this.z));
            paint.getTextBounds(str, 0, str.length(), rect);
            paint.setTextSize(26.0f);
            paint.setColor(-16776961);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, (MultiTouchTestActivity.this.w.widthPixels / 2) - (str.length() / 2), (MultiTouchTestActivity.this.w.heightPixels / 2) - (rect.height() / 2), paint);
        }

        Paint b(int i) {
            Paint paint = new Paint();
            paint.setAntiAlias(false);
            int[][] iArr = MultiTouchTestActivity.v;
            if (i < iArr.length) {
                paint.setARGB(255, iArr[i][0], iArr[i][1], iArr[i][2]);
            } else {
                paint.setARGB(255, 255, 255, 255);
            }
            paint.setTextSize((int) ((((double) MultiTouchTestActivity.this.x) * 3.63d) + 7.37d));
            return paint;
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            for (com.byd.engineermode.a.c.b bVar : this.f2477b.values()) {
                Paint paintB = b(bVar.c());
                String str = "pid " + String.valueOf(bVar.c()) + " x=" + String.valueOf(bVar.a()) + ", y=" + String.valueOf(bVar.b());
                Log.i("MultiTouchTestActivity", "Touch pos: " + bVar.a() + "," + bVar.b());
                Rect rect = new Rect();
                paintB.getTextBounds("pid 9 x=999, y=999", 0, 18, rect);
                if (rect.width() > MultiTouchTestActivity.this.w.widthPixels) {
                    paintB = c(bVar.c());
                }
                paintB.getTextBounds(str, 0, str.length(), rect);
                int iA = bVar.a() - (rect.width() / 2);
                int iB = bVar.b() - (rect.height() * 3);
                if (iA >= 0 && iA > MultiTouchTestActivity.this.w.widthPixels - rect.width()) {
                    int i = MultiTouchTestActivity.this.w.widthPixels;
                    rect.width();
                }
                if (iB < rect.height()) {
                    rect.height();
                } else {
                    int i2 = MultiTouchTestActivity.this.w.heightPixels;
                }
                canvas.drawCircle(bVar.a(), bVar.b(), MultiTouchTestActivity.this.x * 3, paintB);
            }
            a(canvas);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            Log.i("MultiTouchTestActivity", "onTouchEvent: Pointer counts: " + motionEvent.getPointerCount() + " Action: " + motionEvent.getAction());
            for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                Log.i("MultiTouchTestActivity", "onTouchEvent: idx: " + i + " pid: " + motionEvent.getPointerId(i) + " (" + motionEvent.getX(i) + ", " + motionEvent.getY(i) + ")");
                int pointerId = motionEvent.getPointerId(i);
                com.byd.engineermode.a.c.b bVar = new com.byd.engineermode.a.c.b();
                bVar.d((int) motionEvent.getX(i));
                bVar.e((int) motionEvent.getY(i));
                bVar.f(pointerId);
                this.f2477b.put(Integer.valueOf(pointerId), bVar);
                this.f2478c.add(bVar);
            }
            if (motionEvent.getPointerCount() == MultiTouchTestActivity.this.z) {
                MultiTouchTestActivity multiTouchTestActivity = MultiTouchTestActivity.this;
                if (multiTouchTestActivity.A == null) {
                    multiTouchTestActivity.A = b.b(multiTouchTestActivity);
                    MultiTouchTestActivity.this.P();
                }
            }
            invalidate();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        String str = String.format(getString(R.string.multitouch_tip), Integer.valueOf(this.z));
        this.A.g("MultiTouchTestActivity", "(" + str + ") Pass.", true);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        PointView pointView = new PointView(this);
        this.y = pointView;
        setContentView(pointView);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        b bVar = this.A;
        if (bVar != null) {
            bVar.dismiss();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        I();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getWindowManager().getDefaultDisplay().getMetrics(this.w);
    }
}
