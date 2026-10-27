package org.chromium.chrome.browser.app.download.home;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.activity.OnBackPressedDispatcher;
import defpackage.bi1;
import defpackage.bq;
import defpackage.ke0;
import defpackage.l2;
import defpackage.n3;
import defpackage.q11;
import defpackage.qc0;
import defpackage.rc0;
import defpackage.rh3;
import defpackage.uc0;
import defpackage.vb;
import defpackage.wc0;
import defpackage.xb;
import defpackage.z7;
import defpackage.z8;
import defpackage.zb;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;

/* JADX INFO: loaded from: classes.dex */
public class DownloadActivity extends SnackbarActivity {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public uc0 f60J;
    public l2 K;
    public bi1 L;
    public String M;
    public final a N = new a();
    public OTRProfileID O;

    public class a implements qc0.a {
        public a() {
        }

        @Override // qc0.a
        public final void b(String str) {
            DownloadActivity.this.M = str;
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, defpackage.ci1
    public final bi1 P0() {
        return this.L;
    }

    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.M = bundle == null ? "csacsacascasc@vsdvdsvdvdv%^*dsvdsvvdsvdsv" : bundle.getString("current_url");
        Log.i(getClass().getSimpleName(), "fww  onCreate: ");
        z7.L();
        boolean zI = q11.i(getIntent(), "org.chromium.chrome.browser.download.SHOW_PREFETCHED_CONTENT", false);
        this.K = new l2(new WeakReference(this));
        this.O = OTRProfileID.a(q11.p(getIntent().getExtras(), "org.chromium.chrome.browser.download.OTR_PROFILE_ID"));
        wc0.a aVar = new wc0.a();
        aVar.e = true;
        aVar.a = this.O;
        aVar.b = true;
        aVar.f = DownloadUtils.h();
        aVar.g = zI;
        wc0 wc0Var = new wc0(aVar);
        bi1 bi1Var = new bi1(new AppModalPresenter(this));
        this.L = bi1Var;
        uc0 uc0VarA = rc0.a(this, wc0Var, this.I, bi1Var);
        this.f60J = uc0VarA;
        setContentView(uc0VarA.i);
        if (!zI) {
            this.f60J.b(this.M);
        }
        uc0 uc0Var = this.f60J;
        b<qc0.a> bVar = uc0Var.a;
        a aVar2 = this.N;
        bVar.a(aVar2);
        PostTask.c(rh3.a, new z8(uc0Var, 11, aVar2));
        SparseIntArray sparseIntArray = zb.k;
        boolean zA = bq.b.a();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (!zA) {
            uc0 uc0Var2 = this.f60J;
            Objects.requireNonNull(uc0Var2);
            xb.b(this, onBackPressedDispatcher, new ke0(2, uc0Var2));
        } else {
            vb[] vbVarArr = this.f60J.h;
            int length = vbVarArr.length;
            while (true) {
                length--;
                if (length < 0) {
                    return;
                } else {
                    xb.a(this, onBackPressedDispatcher, vbVarArr[length]);
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        this.f60J.a.c(this.N);
        this.f60J.a();
        this.L.a();
        super.onDestroy();
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.K.h(i, strArr, iArr);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        DownloadUtils.a(n3.l0(this.O));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String str = this.M;
        if (str != null) {
            bundle.putString("current_url", str);
        }
    }
}
