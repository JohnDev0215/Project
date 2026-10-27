package org.chromium.chrome.browser.history;

import android.os.Bundle;
import android.util.SparseIntArray;
import androidx.activity.OnBackPressedDispatcher;
import defpackage.bq;
import defpackage.gv0;
import defpackage.ke0;
import defpackage.q11;
import defpackage.xb;
import defpackage.zb;
import java.util.Objects;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class HistoryActivity extends SnackbarActivity {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public gv0 f67J;

    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        gv0 gv0Var = new gv0(this, true, this.I, q11.i(getIntent(), "org.chromium.chrome.browser.incognito_mode", false), null, q11.i(getIntent(), "org.chromium.chrome.browser.history_clusters.show", false), q11.r(getIntent(), "org.chromium.chrome.browser.history_clusters.query"), new BrowsingHistoryBridge(Profile.d()));
        this.f67J = gv0Var;
        setContentView(gv0Var.i);
        SparseIntArray sparseIntArray = zb.k;
        boolean zA = bq.b.a();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (zA) {
            xb.a(this, onBackPressedDispatcher, this.f67J);
            return;
        }
        gv0 gv0Var2 = this.f67J;
        Objects.requireNonNull(gv0Var2);
        xb.b(this, onBackPressedDispatcher, new ke0(13, gv0Var2));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        this.f67J.E();
        this.f67J = null;
        super.onDestroy();
    }
}
