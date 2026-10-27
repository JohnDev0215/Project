package B5;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;

/* JADX INFO: renamed from: B5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0081e implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ z f676m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Date f677n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0083g f678p;

    public /* synthetic */ RunnableC0081e(C0083g c0083g, z zVar, Date date, String str, int i7) {
        this.l = i7;
        this.f678p = c0083g;
        this.f676m = zVar;
        this.f677n = date;
        this.o = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                z zVar = this.f676m;
                if (zVar == null) {
                    zVar = C0083g.f691C;
                }
                C5.h hVar = new C5.h(1);
                hVar.f998b = this.f677n;
                String str = this.o;
                D5.g.b(str, "event");
                hVar.f1007i = str;
                D5.g.a(zVar, "properties");
                hVar.f1008j = Collections.unmodifiableMap(new LinkedHashMap(zVar));
                this.f678p.b(hVar);
                break;
            default:
                z zVar2 = this.f676m;
                if (zVar2 == null) {
                    zVar2 = C0083g.f691C;
                }
                C5.h hVar2 = new C5.h(0);
                hVar2.f998b = this.f677n;
                hVar2.f1007i = this.o;
                D5.g.a(zVar2, "properties");
                hVar2.f1008j = Collections.unmodifiableMap(new LinkedHashMap(zVar2));
                this.f678p.b(hVar2);
                break;
        }
    }
}
