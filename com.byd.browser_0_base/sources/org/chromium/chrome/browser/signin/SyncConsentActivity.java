package org.chromium.chrome.browser.signin;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import defpackage.bi1;
import java.util.Locale;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class SyncConsentActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int I = 0;

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R$string.statement);
        setContentView(R$layout.signin_activity);
        C1((Toolbar) findViewById(R$id.action_bar));
        B1().t(true);
        TextView textView = (TextView) findViewById(R$id.message);
        if (Locale.getDefault().getLanguage().equals("iw")) {
            textView.setText(getString(R$string.statement_message).replace("BYD Auto Industry", "\u2066BYD\u2069 \u2066Auto\u2069 \u2066Industry\u2069"));
        }
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
