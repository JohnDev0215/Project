package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VF implements InterfaceC1537tF {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public VF(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        TF tf = new TF();
        tf.b = (C1208nF) obj;
        this.b.q(tf.b(this.a, new C1045kJ(2, 2, this.c)));
    }
}
