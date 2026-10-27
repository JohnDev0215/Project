package com.byd.auto_photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class StartServiceActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = 1;
        attributes.width = 1;
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        w.c("Photo Launch Activity :" + action);
        try {
            if ("com.byd.action_dev_share_service".equals(action)) {
                MoreDevShareService.d(this, intent.getBooleanExtra("mount", false));
            } else if ("com.byd.boot_complete".equals(action)) {
                startService(new Intent(this, (Class<?>) ApiService.class));
            } else {
                Intent intent2 = new Intent(this, (Class<?>) ApiService.class);
                if (intent.getExtras() != null) {
                    intent2.putExtras(intent.getExtras());
                }
                startService(intent2);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        finish();
    }
}
