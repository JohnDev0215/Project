package com.byd.engineermode.ui.functiontest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.R;
import c.a.c.a.a.b;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SourceTestActivity extends BaseActivity {
    private TextView v;
    String[] w;

    class a implements b.InterfaceC0060b {

        /* JADX INFO: renamed from: com.byd.engineermode.ui.functiontest.SourceTestActivity$a$a, reason: collision with other inner class name */
        class RunnableC0070a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f2414b;

            RunnableC0070a(int i) {
                this.f2414b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                SourceTestActivity.this.v.setText(SourceTestActivity.this.getString(R.string.source_test_current_source) + SourceTestActivity.this.w[this.f2414b]);
            }
        }

        a() {
        }

        @Override // c.a.c.a.a.b.InterfaceC0060b
        public void a(int i, int i2, b.e eVar) {
            if (!eVar.a().getString("success").equals(b.f2082b)) {
                com.byd.engineermode.utils.a.b("SourceTestActivity", "ACTION_GET_MEDIA_STATUS fail ret = " + eVar.a().getString("reason"));
                return;
            }
            int i3 = eVar.a().getInt("param_current_mode");
            com.byd.engineermode.utils.a.b("SourceTestActivity", "ACTION_GET_SUPPORT_MODE current type = " + i3 + ", ACTION_GET_SUPPORT_MODE type array = " + Arrays.toString(eVar.a().getIntArray("param_support_mode_list")));
            SourceTestActivity sourceTestActivity = SourceTestActivity.this;
            String[] strArr = sourceTestActivity.w;
            if (strArr == null || i3 < 0 || i3 >= strArr.length) {
                return;
            }
            sourceTestActivity.runOnUiThread(new RunnableC0070a(i3));
        }
    }

    public void OnButtonClick(View view) {
        com.byd.engineermode.utils.a.b("SourceTestActivity", "view.getId()  = " + view.getId());
        switch (view.getId()) {
            case R.id.btn_bt_music_play /* 2131296362 */:
                b.c(this, 8, 0, null, null);
                break;
            case R.id.btn_bt_phone_play /* 2131296364 */:
                Intent intent = new Intent();
                intent.setClassName("com.byd.bluetoothcall", "com.byd.bluetoothcall.view.activity.MainActivity");
                startActivity(intent);
                break;
            case R.id.btn_fm_play /* 2131296378 */:
                b.c(this, 11, 0, null, null);
                break;
            case R.id.btn_usb_music_play /* 2131296416 */:
                b.c(this, 1, 0, null, null);
                break;
            case R.id.test_fail_btn /* 2131296722 */:
                I();
                break;
            case R.id.test_pass_btn /* 2131296726 */:
                J();
                break;
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_source_test);
        this.v = (TextView) findViewById(R.id.tv_current_play_music);
        this.w = getResources().getStringArray(R.array.mode_source);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        com.byd.engineermode.utils.a.b("SourceTestActivity", "onResume");
        b.c(this, -1, 72, null, new a());
    }
}
