package com.byd.engineermode.ui.functiontest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class CenterControlKeyTestActivity extends BaseActivity {
    public void OnButtonClick(View view) {
        Log.d("TemperatureTestActivity", "view.getId()  = " + view.getId());
        int id = view.getId();
        if (id == R.id.test_fail_btn) {
            I();
        } else {
            if (id != R.id.test_pass_btn) {
                return;
            }
            J();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_center_control_key_test);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
