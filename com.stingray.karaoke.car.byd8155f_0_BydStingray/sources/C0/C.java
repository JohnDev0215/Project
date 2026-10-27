package C0;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class C implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ L f843b;
    public final /* synthetic */ G c;
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ L f844e;

    public /* synthetic */ C(L l, G g7, int i7, L l10, int i10) {
        this.f842a = i10;
        this.c = g7;
        this.d = i7;
        this.f844e = l10;
        this.f843b = l;
    }

    @Override // C0.L
    public final Map a() {
        switch (this.f842a) {
        }
        return this.f843b.a();
    }

    @Override // C0.L
    public final void b() {
        boolean z6;
        switch (this.f842a) {
            case 0:
                G g7 = this.c;
                g7.f851p = this.d;
                this.f844e.b();
                Set setEntrySet = g7.f858w.entrySet();
                A7.m.f(setEntrySet, "<this>");
                Iterator it = setEntrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    d0 d0Var = (d0) entry.getValue();
                    int iK = g7.f859x.k(key);
                    if (iK < 0 || iK >= g7.f851p) {
                        d0Var.a();
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (z6) {
                        it.remove();
                    }
                }
                break;
            default:
                G g10 = this.c;
                g10.o = this.d;
                this.f844e.b();
                g10.a(g10.o);
                break;
        }
    }

    @Override // C0.L
    public final int c() {
        switch (this.f842a) {
        }
        return this.f843b.c();
    }

    @Override // C0.L
    public final InterfaceC2782k d() {
        switch (this.f842a) {
        }
        return this.f843b.d();
    }

    @Override // C0.L
    public final int e() {
        switch (this.f842a) {
        }
        return this.f843b.e();
    }
}
