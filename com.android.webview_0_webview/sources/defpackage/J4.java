package defpackage;

import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class J4 extends H4 {
    public final WeakReference c;
    public C0581c1 a = new C0581c1();
    public int d = 0;
    public boolean e = false;
    public boolean f = false;
    public ArrayList g = new ArrayList();
    public G4 b = G4.INITIALIZED;

    public J4(I4 i4) {
        this.c = new WeakReference(i4);
    }

    public static G4 a(F4 f4) {
        int iOrdinal = f4.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    return G4.RESUMED;
                }
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        if (iOrdinal == 5) {
                            return G4.DESTROYED;
                        }
                        throw new IllegalArgumentException("Unexpected event value " + f4);
                    }
                }
            }
            return G4.STARTED;
        }
        return G4.CREATED;
    }

    public void b(F4 f4) {
        c(a(f4));
    }

    public final void c(G4 g4) {
        if (this.b == g4) {
            return;
        }
        this.b = g4;
        if (this.e) {
            this.f = true;
            return;
        }
        this.e = true;
        if (((I4) this.c.get()) == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
        } else {
            Objects.requireNonNull(this.a);
            this.f = false;
        }
        this.e = false;
    }
}
