package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: je, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1003je implements Runnable {
    public final /* synthetic */ BinderC1059ke s;

    public RunnableC1003je(BinderC1059ke binderC1059ke) {
        this.s = binderC1059ke;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((C0451Zc) this.s.g).b(new ConnectionResult(4));
    }
}
