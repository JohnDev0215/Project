package c4;

import android.text.TextUtils;
import h3.i;
import java.util.List;

/* JADX INFO: compiled from: SearchKeywordsManager.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3624a = 50;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f3625b;

    public g(h hVar) {
        this.f3625b = hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(q4.f fVar) {
        this.f3625b.a();
        fVar.b(Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(String str, q4.f fVar) {
        if (TextUtils.isEmpty(str)) {
            fVar.a(new RuntimeException("keyword is empty"));
            return;
        }
        a aVarF = this.f3625b.f(str);
        if (aVarF != null) {
            fVar.b(Long.valueOf(aVarF.f3601a));
            return;
        }
        a aVar = new a();
        aVar.f3602b = str;
        long jA = z3.b.a();
        aVar.f3606f = jA;
        aVar.f3605e = jA;
        i();
        fVar.b(Long.valueOf(this.f3625b.c(aVar)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(q4.f fVar) {
        fVar.b(this.f3625b.e());
    }

    private void i() {
        int iB = this.f3625b.b();
        if (iB >= 50) {
            i.b("SearchKeywordsManager").g("remove excess data ,  current num is " + iB);
            this.f3625b.d(49);
        }
    }

    public q4.e<Boolean> d() {
        return q4.e.b(new q4.h() { // from class: c4.d
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f3620a.f(fVar);
            }
        });
    }

    public q4.e<Long> e(final String str) {
        return q4.e.b(new q4.h() { // from class: c4.f
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f3622a.g(str, fVar);
            }
        });
    }

    public q4.e<List<a>> j() {
        return q4.e.b(new q4.h() { // from class: c4.e
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f3621a.h(fVar);
            }
        });
    }
}
