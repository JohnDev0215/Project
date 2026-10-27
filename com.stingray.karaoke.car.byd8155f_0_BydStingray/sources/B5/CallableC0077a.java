package B5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: B5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class CallableC0077a implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f672b;

    public /* synthetic */ CallableC0077a(Object obj, int i7) {
        this.f671a = i7;
        this.f672b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f671a) {
            case 0:
                C0083g c0083g = (C0083g) this.f672b;
                n nVarF = null;
                try {
                    nVarF = c0083g.f701k.F();
                    LinkedHashMap linkedHashMapA = c0083g.l.a(new BufferedReader(new InputStreamReader(nVarF.f726m)));
                    linkedHashMapA.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                    return new y(linkedHashMapA);
                } finally {
                    D5.g.c(nVarF);
                }
            default:
                synchronized (((c4.c) this.f672b)) {
                    try {
                        c4.c cVar = (c4.c) this.f672b;
                        if (cVar.f12389t == null) {
                            return null;
                        }
                        cVar.G();
                        if (((c4.c) this.f672b).o()) {
                            ((c4.c) this.f672b).C();
                            ((c4.c) this.f672b).f12391v = 0;
                        }
                        return null;
                    } finally {
                    }
                }
        }
    }
}
