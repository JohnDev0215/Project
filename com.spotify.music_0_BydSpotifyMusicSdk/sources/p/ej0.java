package p;

import io.reactivex.rxjava3.exceptions.CompositeException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ej0 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.eventsender.disk.a e;

    public /* synthetic */ ej0(com.spotify.eventsender.disk.a aVar, int i) {
        this.c = i;
        this.e = aVar;
    }

    public final void a(Throwable th) {
        int i = this.c;
        com.spotify.eventsender.disk.a aVar = this.e;
        switch (i) {
            case 0:
                if (th instanceof FileNotFoundException) {
                    aVar.f.accept(Boolean.TRUE);
                    ((u11) aVar.c).getClass();
                } else {
                    if (th instanceof CompositeException) {
                        List listB = ((CompositeException) th).b();
                        v03.i(listB, "t.exceptions");
                        if (!listB.isEmpty()) {
                            Iterator it = listB.iterator();
                            while (it.hasNext()) {
                                if (!(((Throwable) it.next()) instanceof FileNotFoundException)) {
                                }
                            }
                        }
                        aVar.f.accept(Boolean.TRUE);
                        ((u11) aVar.c).getClass();
                    }
                    v03.i(th, "t");
                    aVar.f.accept(Boolean.FALSE);
                    Iterator it2 = aVar.a.iterator();
                    while (it2.hasNext()) {
                        ((cj0) it2.next()).a(th);
                    }
                    ((u11) aVar.c).y("Error while reading from disk.", th);
                }
                break;
            default:
                for (cj0 cj0Var : aVar.a) {
                    v03.i(th, "t");
                    cj0Var.d(th);
                }
                ((u11) aVar.c).y("Fatal error while writing to disk.", th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final /* bridge */ /* synthetic */ void accept(Object obj) {
        switch (this.c) {
            case 0:
                a((Throwable) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
    }
}
