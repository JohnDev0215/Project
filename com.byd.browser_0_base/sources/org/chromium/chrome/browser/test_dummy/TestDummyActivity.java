package org.chromium.chrome.browser.test_dummy;

import androidx.appcompat.app.AppCompatActivity;
import defpackage.ac2;
import defpackage.hi1;
import defpackage.iv;
import defpackage.ku2;
import defpackage.pq2;
import defpackage.r73;

/* JADX INFO: loaded from: classes2.dex */
public class TestDummyActivity extends AppCompatActivity {
    public static final /* synthetic */ int D = 0;

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        if (!iv.e().g("enable-test-dummy-module")) {
            finish();
            return;
        }
        hi1 hi1Var = ac2.h;
        if (hi1Var.d()) {
            ((r73) hi1Var.a()).a();
            getIntent();
            throw null;
        }
        ku2 ku2VarQ = ku2.q();
        try {
            hi1Var.c(new pq2(29, this));
            ku2VarQ.close();
        } catch (Throwable th) {
            try {
                ku2VarQ.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
