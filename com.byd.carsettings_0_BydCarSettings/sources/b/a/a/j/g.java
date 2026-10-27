package b.a.a.j;

import java.lang.reflect.Type;

/* JADX INFO: compiled from: ParseContext.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f246b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f247c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f248d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Type f249e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient String f250f;

    public g(g gVar, Object obj, Object obj2) {
        this.f246b = gVar;
        this.f245a = obj;
        this.f247c = obj2;
        this.f248d = gVar == null ? 0 : gVar.f248d + 1;
    }

    public String toString() {
        if (this.f250f == null) {
            if (this.f246b == null) {
                this.f250f = "$";
            } else if (this.f247c instanceof Integer) {
                this.f250f = this.f246b.toString() + "[" + this.f247c + "]";
            } else {
                this.f250f = this.f246b.toString() + "." + this.f247c;
            }
        }
        return this.f250f;
    }
}
