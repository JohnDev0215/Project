package s9;

import A7.o;
import A7.v;
import A7.y;
import A7.z;
import java.io.IOException;
import m7.q;
import r9.D;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ v f22408m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f22409n;
    public final /* synthetic */ y o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D f22410p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ y f22411q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ y f22412r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ z f22413s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ z f22414t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ z f22415u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(v vVar, long j3, y yVar, D d, y yVar2, y yVar3, z zVar, z zVar2, z zVar3) {
        super(2);
        this.f22408m = vVar;
        this.f22409n = j3;
        this.o = yVar;
        this.f22410p = d;
        this.f22411q = yVar2;
        this.f22412r = yVar3;
        this.f22413s = zVar;
        this.f22414t = zVar2;
        this.f22415u = zVar3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws IOException {
        int iIntValue = ((Number) obj).intValue();
        long jLongValue = ((Number) obj2).longValue();
        D d = this.f22410p;
        if (iIntValue == 1) {
            v vVar = this.f22408m;
            if (vVar.l) {
                throw new IOException("bad zip: zip64 extra repeated");
            }
            vVar.l = true;
            if (jLongValue < this.f22409n) {
                throw new IOException("bad zip: zip64 extra too short");
            }
            y yVar = this.o;
            long jY = yVar.l;
            if (jY == 4294967295L) {
                jY = d.y();
            }
            yVar.l = jY;
            y yVar2 = this.f22411q;
            yVar2.l = yVar2.l == 4294967295L ? d.y() : 0L;
            y yVar3 = this.f22412r;
            yVar3.l = yVar3.l == 4294967295L ? d.y() : 0L;
        } else if (iIntValue == 10) {
            if (jLongValue < 4) {
                throw new IOException("bad zip: NTFS extra too short");
            }
            d.K(4L);
            b.e(d, (int) (jLongValue - 4), new h(this.f22413s, d, this.f22414t, this.f22415u));
        }
        return q.f19267a;
    }
}
