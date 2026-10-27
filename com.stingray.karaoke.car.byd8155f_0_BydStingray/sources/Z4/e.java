package Z4;

import android.os.Handler;
import android.os.Message;
import r4.C2292e;
import r4.C2294g;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Handler.Callback {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10901m;

    public /* synthetic */ e(Object obj, int i7) {
        this.l = i7;
        this.f10901m = obj;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.l) {
            case 0:
                if (message.what != 0) {
                    return false;
                }
                X.e eVar = (X.e) this.f10901m;
                if (message.obj != null) {
                    throw new ClassCastException();
                }
                synchronized (eVar.f9952a) {
                    throw null;
                }
            default:
                int i7 = message.what;
                C2294g c2294g = (C2294g) this.f10901m;
                if (i7 == 1) {
                    c2294g.b((C2292e) message.obj);
                    return true;
                }
                if (i7 == 2) {
                    c2294g.d.a((C2292e) message.obj);
                }
                return false;
        }
    }
}
