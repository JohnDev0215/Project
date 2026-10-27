package org.chromium.chrome.browser.about_settings;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.appcompat.widget.Toolbar;
import defpackage.bi1;
import defpackage.vt;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyActivity extends SynchronousInitializationActivity {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final /* synthetic */ int f53J = 0;
    public ScrollView I;

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final bi1 E1() {
        return new bi1(new AppModalPresenter(this));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.I.removeAllViews();
        this.I.addView(getLayoutInflater().inflate(R$layout.privacy_content_large, (ViewGroup) null, false), new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R$string.privacy_policy);
        setContentView(R$layout.privacy_activity);
        C1((Toolbar) findViewById(R$id.action_bar));
        B1().t(true);
        View viewFindViewById = findViewById(R$id.bt_privacy_cancel);
        viewFindViewById.setVisibility(0);
        viewFindViewById.setOnClickListener(new vt(3, this));
        this.I = (ScrollView) findViewById(R$id.scrollView);
        LayoutInflater layoutInflater = getLayoutInflater();
        getResources().getConfiguration();
        this.I.addView(layoutInflater.inflate(R$layout.privacy_content_large, (ViewGroup) null, false), new ViewGroup.LayoutParams(-1, -1));
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
