package f2;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: ParseContext.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f4901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f4902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f4903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Type f4905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient String f4906f;

    public g(g gVar, Object obj, Object obj2) {
        this.f4902b = gVar;
        this.f4901a = obj;
        this.f4903c = obj2;
        this.f4904d = gVar == null ? 0 : gVar.f4904d + 1;
    }

    public String toString() {
        if (this.f4906f == null) {
            if (this.f4902b == null) {
                this.f4906f = "$";
            } else if (this.f4903c instanceof Integer) {
                this.f4906f = this.f4902b.toString() + "[" + this.f4903c + "]";
            } else {
                this.f4906f = this.f4902b.toString() + "." + this.f4903c;
            }
        }
        return this.f4906f;
    }
}
