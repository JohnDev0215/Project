package com.byd.sing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.sing.service.MicConnectService;
import com.byd.sing.service.MicKeyService;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MainActivity.this.G()) {
                MicKeyService.g(MainActivity.this);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startService(new Intent(mainActivity, (Class<?>) MicConnectService.class));
                e.i();
                return;
            }
            MainActivity.this.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + MainActivity.this.getPackageName())), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean G() {
        return Settings.canDrawOverlays(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && Settings.canDrawOverlays(this)) {
            e.h();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_hello).setOnClickListener(new a());
    }
}
