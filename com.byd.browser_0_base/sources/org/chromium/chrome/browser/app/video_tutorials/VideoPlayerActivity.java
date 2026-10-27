package org.chromium.chrome.browser.app.video_tutorials;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import defpackage.a8;
import defpackage.aw;
import defpackage.c3;
import defpackage.em3;
import defpackage.g3;
import defpackage.ke0;
import defpackage.p11;
import defpackage.q11;
import defpackage.r12;
import defpackage.so;
import defpackage.vl3;
import defpackage.xb;
import java.util.Objects;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.content.browser.MediaSessionImpl;
import org.chromium.ui.base.a;
import org.chromium.ui.base.c;
import org.chromium.ui.base.d;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayerActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int K = 0;
    public g3 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public vl3 f61J;

    public static void K1(Context context, int i) {
        Intent intent = new Intent();
        intent.setClass(context, VideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("extra_video_tutorial", i);
        context.startActivity(intent);
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(16777216);
        em3 em3Var = (em3) N.MBuXqyoS(Profile.d());
        d dVar = new d(new a(this));
        this.I = new g3((Context) this, true, (c) dVar);
        vl3 vl3Var = new vl3(this, em3Var, new p11(1, this), new a8(), new c3(3, this), new aw(6, this), dVar);
        this.f61J = vl3Var;
        setContentView(vl3Var.a.a);
        int iL = q11.l(getIntent(), "extra_video_tutorial", 0);
        vl3 vl3Var2 = this.f61J;
        Objects.requireNonNull(vl3Var2);
        em3Var.b(new so(6, vl3Var2), iL);
        vl3 vl3Var3 = this.f61J;
        Objects.requireNonNull(vl3Var3);
        xb.b(this, this.k, new ke0(3, vl3Var3));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        vl3 vl3Var = this.f61J;
        r12 r12Var = vl3Var.f;
        MediaSessionImpl mediaSessionImpl = r12Var.a;
        if (mediaSessionImpl != null) {
            mediaSessionImpl.l.c(r12Var);
            r12Var.a = null;
        }
        ((ThinWebViewImpl) vl3Var.a.b).b();
        vl3Var.d.destroy();
        super.onDestroy();
    }
}
