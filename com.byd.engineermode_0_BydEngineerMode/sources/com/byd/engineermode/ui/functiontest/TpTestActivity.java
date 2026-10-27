package com.byd.engineermode.ui.functiontest;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.textclassifier.Log;
import android.widget.Switch;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class TpTestActivity extends BaseActivity implements View.OnClickListener {
    private Switch v;

    private void M() {
        if (Settings.System.getInt(getContentResolver(), "pointer_location", 0) == 0) {
            Settings.System.putInt(getContentResolver(), "pointer_location", 1);
            this.v.setChecked(true);
        } else {
            Settings.System.putInt(getContentResolver(), "pointer_location", 0);
            this.v.setChecked(false);
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        M();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_click_test);
        Switch r3 = (Switch) findViewById(R.id.sw_touch_data);
        this.v = r3;
        r3.setOnClickListener(this);
        if (Settings.System.getInt(getContentResolver(), "pointer_location", 0) == 0) {
            this.v.setChecked(false);
        } else {
            this.v.setChecked(true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause");
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (Settings.System.getInt(getContentResolver(), "pointer_location", 0) == 0) {
            this.v.setChecked(false);
        } else {
            this.v.setChecked(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop");
        Settings.System.putInt(getContentResolver(), "pointer_location", 0);
    }
}
