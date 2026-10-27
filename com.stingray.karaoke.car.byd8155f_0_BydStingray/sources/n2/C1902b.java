package n2;

import B2.C0075o;
import android.content.Context;
import android.media.AudioManager;
import c5.InterfaceC1031h;
import d5.d0;
import u2.C2514q;

/* JADX INFO: renamed from: n2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1902b implements InterfaceC1031h {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f19624m;

    public /* synthetic */ C1902b(Context context, int i7) {
        this.l = i7;
        this.f19624m = context;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        x2.h hVar;
        switch (this.l) {
            case 0:
                AudioManager audioManager = (AudioManager) this.f19624m.getApplicationContext().getSystemService("audio");
                audioManager.getClass();
                return audioManager;
            case 1:
                return new C1912l(this.f19624m);
            case 2:
                return new C2514q(this.f19624m, new C0075o());
            case 3:
                return new w2.o(this.f19624m);
            default:
                Context context = this.f19624m;
                d0 d0Var = x2.h.f23733n;
                synchronized (x2.h.class) {
                    try {
                        if (x2.h.f23738t == null) {
                            x2.g gVar = new x2.g(context);
                            x2.h.f23738t = new x2.h(gVar.f23730a, gVar.f23731b, gVar.c, gVar.d, gVar.f23732e);
                        }
                        hVar = x2.h.f23738t;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return hVar;
        }
    }
}
