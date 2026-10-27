package org.chromium.components.media_router.caf.remoting;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import defpackage.oj2;
import org.chromium.third_party.android.media.MediaController;

/* JADX INFO: loaded from: classes.dex */
public class CafExpandedControllerActivity extends FragmentActivity {

    public class a implements MediaController.e {
        public a() {
        }
    }

    public CafExpandedControllerActivity() {
        new a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        int intExtra;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null && (intExtra = intent.getIntExtra("org.chromium.chrome.browser.metrics.org.chromium.components.browser_ui.media.MediaNotificationUma.EXTRA_CLICK_SOURCE", -1)) != -1 && intExtra < 3) {
            oj2.U(intExtra, 3, "Media.Notification.Click");
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        throw null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        finish();
    }
}
