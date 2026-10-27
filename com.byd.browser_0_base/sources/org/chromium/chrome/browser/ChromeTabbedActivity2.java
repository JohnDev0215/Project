package org.chromium.chrome.browser;

import android.content.Intent;
import android.os.Bundle;
import defpackage.vj1;

/* JADX INFO: loaded from: classes.dex */
public class ChromeTabbedActivity2 extends ChromeTabbedActivity {
    @Override // org.chromium.chrome.browser.ChromeTabbedActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final int T1(Intent intent, Bundle bundle) {
        if (!vj1.p()) {
            return super.T1(intent, bundle);
        }
        startActivity(vj1.c(this, bundle != null ? bundle.getInt("window_index", -1) : -1, false, false), bundle);
        return 2;
    }
}
