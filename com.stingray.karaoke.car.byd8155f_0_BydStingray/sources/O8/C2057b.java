package o8;

/* JADX INFO: renamed from: o8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2057b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2058c f20564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2058c f20565b;
    public final boolean c;

    public C2057b(C2058c c2058c, C2058c c2058c2, boolean z6) {
        if (c2058c == null) {
            a(1);
            throw null;
        }
        this.f20564a = c2058c;
        this.f20565b = c2058c2;
        this.c = z6;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r10) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o8.C2057b.a(int):void");
    }

    public static C2057b e(String str, boolean z6) {
        String str2;
        if (str == null) {
            a(12);
            throw null;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        if (iLastIndexOf == -1) {
            str2 = "";
        } else {
            String strReplace = str.substring(0, iLastIndexOf).replace('/', '.');
            str = str.substring(iLastIndexOf + 1);
            str2 = strReplace;
        }
        return new C2057b(new C2058c(str2), new C2058c(str), z6);
    }

    public static C2057b j(C2058c c2058c) {
        if (c2058c != null) {
            return new C2057b(c2058c.e(), c2058c.f());
        }
        a(0);
        throw null;
    }

    public final C2058c b() {
        C2058c c2058c = this.f20564a;
        boolean zD = c2058c.d();
        C2058c c2058c2 = this.f20565b;
        if (zD) {
            if (c2058c2 != null) {
                return c2058c2;
            }
            a(9);
            throw null;
        }
        return new C2058c(c2058c.b() + "." + c2058c2.b());
    }

    public final String c() {
        C2058c c2058c = this.f20564a;
        boolean zD = c2058c.d();
        C2058c c2058c2 = this.f20565b;
        if (zD) {
            return c2058c2.b();
        }
        String str = c2058c.b().replace('.', '/') + "/" + c2058c2.b();
        if (str != null) {
            return str;
        }
        a(14);
        throw null;
    }

    public final C2057b d(C2061f c2061f) {
        if (c2061f != null) {
            return new C2057b(g(), this.f20565b.c(c2061f), this.c);
        }
        a(8);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2057b.class != obj.getClass()) {
            return false;
        }
        C2057b c2057b = (C2057b) obj;
        return this.f20564a.equals(c2057b.f20564a) && this.f20565b.equals(c2057b.f20565b) && this.c == c2057b.c;
    }

    public final C2057b f() {
        C2058c c2058cE = this.f20565b.e();
        if (c2058cE.d()) {
            return null;
        }
        return new C2057b(g(), c2058cE, this.c);
    }

    public final C2058c g() {
        C2058c c2058c = this.f20564a;
        if (c2058c != null) {
            return c2058c;
        }
        a(5);
        throw null;
    }

    public final C2058c h() {
        C2058c c2058c = this.f20565b;
        if (c2058c != null) {
            return c2058c;
        }
        a(6);
        throw null;
    }

    public final int hashCode() {
        return Boolean.valueOf(this.c).hashCode() + ((this.f20565b.hashCode() + (this.f20564a.hashCode() * 31)) * 31);
    }

    public final C2061f i() {
        C2061f c2061fF = this.f20565b.f();
        if (c2061fF != null) {
            return c2061fF;
        }
        a(7);
        throw null;
    }

    public final String toString() {
        boolean zD = this.f20564a.d();
        String strC = c();
        return zD ? "/".concat(strC) : strC;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2057b(C2058c c2058c, C2061f c2061f) {
        this(c2058c, C2058c.j(c2061f), false);
        if (c2058c == null) {
            a(3);
            throw null;
        }
        if (c2061f != null) {
        } else {
            a(4);
            throw null;
        }
    }
}
