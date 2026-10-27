package m5;

import java.util.Calendar;
import java.util.GregorianCalendar;
import q5.C2185a;

/* JADX INFO: renamed from: m5.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1834k implements j5.z {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f19228m;

    public /* synthetic */ C1834k(Object obj, int i7) {
        this.l = i7;
        this.f19228m = obj;
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        switch (this.l) {
            case 0:
                if (c2185a.f21253a == Number.class) {
                    return (C1835l) this.f19228m;
                }
                return null;
            case 1:
                if (c2185a.f21253a == Object.class) {
                    return new C1836m(mVar, (j5.x) this.f19228m);
                }
                return null;
            default:
                Class cls = c2185a.f21253a;
                if (cls == Calendar.class || cls == GregorianCalendar.class) {
                    return (M) this.f19228m;
                }
                return null;
        }
    }

    public String toString() {
        switch (this.l) {
            case 2:
                return "Factory[type=" + Calendar.class.getName() + "+" + GregorianCalendar.class.getName() + ",adapter=" + ((M) this.f19228m) + "]";
            default:
                return super.toString();
        }
    }
}
