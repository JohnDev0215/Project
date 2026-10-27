package org.chromium.chrome.browser.app.feed.followmanagement;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import defpackage.io0;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.SnackbarActivity;

/* JADX INFO: loaded from: classes.dex */
public class FollowManagementActivity extends SnackbarActivity {
    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new io0(this).b);
        C1((Toolbar) findViewById(R$id.action_bar));
        B1().t(true);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
