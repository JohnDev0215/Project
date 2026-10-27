package com.google.zxing.n.r.g;

/* JADX INFO: compiled from: ExpandedPair.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4372a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.zxing.n.r.b f4373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.google.zxing.n.r.b f4374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.zxing.n.r.c f4375d;

    b(com.google.zxing.n.r.b bVar, com.google.zxing.n.r.b bVar2, com.google.zxing.n.r.c cVar, boolean z) {
        this.f4373b = bVar;
        this.f4374c = bVar2;
        this.f4375d = cVar;
        this.f4372a = z;
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    private static int e(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    com.google.zxing.n.r.c b() {
        return this.f4375d;
    }

    com.google.zxing.n.r.b c() {
        return this.f4373b;
    }

    com.google.zxing.n.r.b d() {
        return this.f4374c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a(this.f4373b, bVar.f4373b) && a(this.f4374c, bVar.f4374c) && a(this.f4375d, bVar.f4375d);
    }

    public boolean f() {
        return this.f4374c == null;
    }

    public int hashCode() {
        return (e(this.f4373b) ^ e(this.f4374c)) ^ e(this.f4375d);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.f4373b);
        sb.append(" , ");
        sb.append(this.f4374c);
        sb.append(" : ");
        com.google.zxing.n.r.c cVar = this.f4375d;
        sb.append(cVar == null ? "null" : Integer.valueOf(cVar.c()));
        sb.append(" ]");
        return sb.toString();
    }
}
