package defpackage;

import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EK implements QK {
    public final /* synthetic */ NetworkChangeNotifier a;

    public EK(NetworkChangeNotifier networkChangeNotifier) {
        this.a = networkChangeNotifier;
    }

    @Override // defpackage.QK
    public void a(int i) {
        NetworkChangeNotifier networkChangeNotifier = this.a;
        networkChangeNotifier.e = i;
        networkChangeNotifier.b(i, networkChangeNotifier.getCurrentDefaultNetId());
    }

    @Override // defpackage.QK
    public void b(long[] jArr) {
        this.a.f(jArr);
    }

    @Override // defpackage.QK
    public void c(long j, int i) {
        this.a.c(j, i);
    }

    @Override // defpackage.QK
    public void d(long j) {
        this.a.d(j);
    }

    @Override // defpackage.QK
    public void e(int i) {
        this.a.a(i);
    }

    @Override // defpackage.QK
    public void f(long j) {
        this.a.e(j);
    }
}
