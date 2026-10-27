package org.chromium.chrome.browser.app.bookmarks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.activity.OnBackPressedDispatcher;
import defpackage.bq;
import defpackage.eg;
import defpackage.nf;
import defpackage.q11;
import defpackage.xb;
import defpackage.zb;
import java.util.Objects;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkActivity extends SnackbarActivity {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public eg f55J;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 14 && i2 == -1) {
            this.f55J.i.d(BookmarkId.a(intent.getStringExtra("BookmarkEditActivity.VisitBookmarkId")));
        }
    }

    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f55J = new eg(this, (ComponentName) q11.n(getIntent(), "org.chromium.chrome.browser.parent_component"), true, q11.i(getIntent(), "org.chromium.chrome.browser.incognito_mode", false), this.I, Profile.d());
        String dataString = getIntent().getDataString();
        if (TextUtils.isEmpty(dataString)) {
            dataString = "chrome-native://bookmarks/";
        }
        this.f55J.h(dataString);
        setContentView(this.f55J.f);
        SparseIntArray sparseIntArray = zb.k;
        boolean zA = bq.b.a();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (zA) {
            xb.a(this, onBackPressedDispatcher, this.f55J);
            return;
        }
        eg egVar = this.f55J;
        Objects.requireNonNull(egVar);
        xb.b(this, onBackPressedDispatcher, new nf(egVar));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.f55J.f();
    }
}
