package k6;

import p6.h;

/* JADX INFO: compiled from: Header.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p6.h f7279d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p6.h f7280e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p6.h f7281f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p6.h f7282g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p6.h f7283h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p6.h f7284i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f7285j = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p6.h f7287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p6.h f7288c;

    /* JADX INFO: compiled from: Header.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    static {
        h.a aVar = p6.h.f8870j;
        f7279d = aVar.c(":");
        f7280e = aVar.c(":status");
        f7281f = aVar.c(":method");
        f7282g = aVar.c(":path");
        f7283h = aVar.c(":scheme");
        f7284i = aVar.c(":authority");
    }

    public c(p6.h name, p6.h value) {
        kotlin.jvm.internal.l.e(name, "name");
        kotlin.jvm.internal.l.e(value, "value");
        this.f7287b = name;
        this.f7288c = value;
        this.f7286a = name.r() + 32 + value.r();
    }

    public final p6.h a() {
        return this.f7287b;
    }

    public final p6.h b() {
        return this.f7288c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return kotlin.jvm.internal.l.a(this.f7287b, cVar.f7287b) && kotlin.jvm.internal.l.a(this.f7288c, cVar.f7288c);
    }

    public int hashCode() {
        p6.h hVar = this.f7287b;
        int iHashCode = (hVar != null ? hVar.hashCode() : 0) * 31;
        p6.h hVar2 = this.f7288c;
        return iHashCode + (hVar2 != null ? hVar2.hashCode() : 0);
    }

    public String toString() {
        return this.f7287b.u() + ": " + this.f7288c.u();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public c(String name, String value) {
        kotlin.jvm.internal.l.e(name, "name");
        kotlin.jvm.internal.l.e(value, "value");
        h.a aVar = p6.h.f8870j;
        this(aVar.c(name), aVar.c(value));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public c(p6.h name, String value) {
        this(name, p6.h.f8870j.c(value));
        kotlin.jvm.internal.l.e(name, "name");
        kotlin.jvm.internal.l.e(value, "value");
    }
}
