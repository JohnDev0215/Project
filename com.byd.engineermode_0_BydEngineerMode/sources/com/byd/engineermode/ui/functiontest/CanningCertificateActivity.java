package com.byd.engineermode.ui.functiontest;

import android.hardware.bydauto.pki.PkiManager;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class CanningCertificateActivity extends BaseActivity {
    private Button v;
    PkiManager w;
    BYDAutoVersionDevice x;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ byte[] f2390b;

        a(byte[] bArr) {
            this.f2390b = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < this.f2390b.length; i++) {
                com.byd.engineermode.utils.a.a("mItemHeight mByte i = " + ((int) this.f2390b[i]));
            }
        }
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
        setContentView(R.layout.activity_canning_certificate);
        this.v = (Button) findViewById(R.id.btn_status);
        PkiManager pkiManager = PkiManager.getInstance(this);
        this.w = pkiManager;
        byte[] deviceRsaCertHSM = pkiManager.getDeviceRsaCertHSM();
        BYDAutoVersionDevice bYDAutoVersionDevice = BYDAutoVersionDevice.getInstance(this);
        this.x = bYDAutoVersionDevice;
        String vehicleSN = bYDAutoVersionDevice.getVehicleSN();
        if (deviceRsaCertHSM.length != 0) {
            this.v.setText(R.string.str_canning_certificate_filled);
        }
        com.byd.engineermode.utils.a.a("mItemHeight mByte = " + deviceRsaCertHSM + ", mByte.length =  " + deviceRsaCertHSM.length + ", sn = " + vehicleSN + "....");
        new Thread(new a(deviceRsaCertHSM)).start();
    }
}
