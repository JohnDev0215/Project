package com.byd.engineermode.ui.functiontest.carplayandroidauto;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAndroidTestActivity extends BaseActivity {
    private Button v;
    private Button w;
    b x;
    com.byd.engineermode.ui.functiontest.carplayandroidauto.a y;
    private a z;

    private class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<CarPlayAndroidTestActivity> f2436a;

        a(CarPlayAndroidTestActivity carPlayAndroidTestActivity) {
            this.f2436a = new WeakReference<>(carPlayAndroidTestActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2436a.get() == null) {
                com.byd.engineermode.utils.a.c("outer is null");
                return;
            }
            int i = message.what;
            if (i == 1001) {
                int iIntValue = ((Integer) message.obj).intValue();
                com.byd.engineermode.utils.a.b("CarPlayAndroidTestActivity", " CarPlay mCarPlayStatus = " + iIntValue);
                if (iIntValue == 2) {
                    CarPlayAndroidTestActivity.this.v.setText(R.string.connected);
                    return;
                } else {
                    CarPlayAndroidTestActivity.this.v.setText(R.string.disconnect);
                    return;
                }
            }
            if (i != 1002) {
                return;
            }
            int iIntValue2 = ((Integer) message.obj).intValue();
            com.byd.engineermode.utils.a.b("CarPlayAndroidTestActivity", " Android Auto mAndroidAutoStatus = " + iIntValue2);
            if (iIntValue2 == 4) {
                CarPlayAndroidTestActivity.this.w.setText(R.string.connected);
            } else {
                CarPlayAndroidTestActivity.this.w.setText(R.string.disconnect);
            }
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void I() {
        super.I();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void J() {
        super.J();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void K() {
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
        } else if (view.getId() == R.id.test_retry_btn) {
            K();
        } else if (view.getId() == R.id.test_fail_btn) {
            I();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_carplay_android_test);
        this.v = (Button) findViewById(R.id.btn_carplay);
        this.w = (Button) findViewById(R.id.btn_android_auto);
        this.z = new a(this);
        this.x = new b(getApplicationContext(), this.z);
        this.y = new com.byd.engineermode.ui.functiontest.carplayandroidauto.a(getApplicationContext(), this.z);
    }
}
