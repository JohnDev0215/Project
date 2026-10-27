package defpackage;

import J.N;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: Bp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0033Bp implements Callback {
    public boolean a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ int d;
    public final /* synthetic */ C0069Dp e;

    public C0033Bp(C0069Dp c0069Dp, int i, int i2, int i3) {
        this.e = c0069Dp;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        String[] strArr = (String[]) obj;
        if (this.a) {
            throw new IllegalStateException("Duplicate showFileChooser result");
        }
        this.a = true;
        if (strArr == null) {
            N.MB5V$olG(this.b, this.c, this.d, null, null);
        } else {
            new C0051Cp(this.e.d, this.b, this.c, this.d, strArr).d(Su.f);
        }
    }
}
