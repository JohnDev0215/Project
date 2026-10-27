package com.byd.engineermode.ui.functiontest;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.ui.dialog.b;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class ScreenDisplayTestActivity extends BaseActivity implements View.OnClickListener {
    private static int v;
    private static View x;
    private static a z;
    private int A = 0;
    private static final int[] w = {-1, -16777216, -16777216, -65536, -16711936, -16776961};
    private static Dialog y = null;

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<ScreenDisplayTestActivity> f2410a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ScreenDisplayTestActivity f2411b;

        a(ScreenDisplayTestActivity screenDisplayTestActivity) {
            this.f2410a = new WeakReference<>(screenDisplayTestActivity);
            this.f2411b = screenDisplayTestActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2410a.get() == null) {
                com.byd.engineermode.utils.a.a("outer is null");
                return;
            }
            com.byd.engineermode.utils.a.a("outer is null mCycleCount = " + ScreenDisplayTestActivity.v);
            ScreenDisplayTestActivity.N();
            if (ScreenDisplayTestActivity.v >= ScreenDisplayTestActivity.w.length) {
                this.f2411b.findViewById(R.id.main_frame).setClickable(false);
                Dialog unused = ScreenDisplayTestActivity.y = b.b(this.f2411b.getApplicationContext()).f(this.f2411b.getString(R.string.dialog_title), this.f2411b.getString(R.string.dialog_content), 0);
            } else {
                if (ScreenDisplayTestActivity.v == 2) {
                    ScreenDisplayTestActivity.x.setBackgroundResource(R.drawable.shape_gradient);
                } else {
                    ScreenDisplayTestActivity.x.setBackgroundColor(ScreenDisplayTestActivity.w[ScreenDisplayTestActivity.v]);
                }
                ScreenDisplayTestActivity.z.sendEmptyMessageDelayed(1001, 3000L);
            }
        }
    }

    static /* synthetic */ int N() {
        int i = v + 1;
        v = i;
        return i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.main_frame) {
            int i = this.A;
            int[] iArr = w;
            if (i == iArr.length) {
                com.byd.engineermode.ui.dialog.a.a(this).b(R.string.loop_testing_under_way);
                return;
            }
            int i2 = i + 1;
            this.A = i2;
            if (i2 < iArr.length) {
                if (i2 == 2) {
                    x.setBackgroundResource(R.drawable.shape_gradient);
                    return;
                } else {
                    x.setBackgroundColor(iArr[i2]);
                    return;
                }
            }
            if (i2 == iArr.length) {
                com.byd.engineermode.ui.dialog.a.a(this).b(R.string.entered_loop_test_phone);
                z.sendEmptyMessage(1001);
            }
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(R.layout.full_screen_layout);
        View viewFindViewById = findViewById(R.id.screen);
        x = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        x.setBackgroundColor(w[0]);
        findViewById(R.id.main_frame).setOnClickListener(this);
        z = new a(this);
        v = 0;
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.byd.engineermode.utils.a.a("onDestroy");
        Dialog dialog = y;
        if (dialog != null) {
            dialog.dismiss();
            y = null;
        }
        a aVar = z;
        if (aVar != null) {
            aVar.removeMessages(1001);
            z = null;
        }
    }
}
