package com.qtxa.hidps.b;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f63a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f64b;
    private int c = 16;

    a(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        this.f63a = str;
        this.f64b = str2;
    }

    private int d(String str) {
        if (str == null) {
            return 16;
        }
        if (str.equalsIgnoreCase("RESUMED")) {
            return 1;
        }
        if (str.equalsIgnoreCase("PAUSING")) {
            return 2;
        }
        return str.equalsIgnoreCase("PAUSED") ? 3 : 16;
    }

    public String a() {
        return this.f63a;
    }

    public String b() {
        return this.f64b;
    }

    public int c() {
        return this.c;
    }

    boolean e() {
        return this.c == 16;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof a)) {
            return super.equals(obj);
        }
        a aVar = (a) obj;
        return this.f63a.equals(aVar.f63a) && this.f64b.equals(aVar.f64b);
    }

    void f(int i) {
        if (i < this.c) {
            this.c = i;
        }
    }

    void g(String str, String str2) {
        int iD = d(str2);
        int iD2 = d(str);
        if (iD2 > iD) {
            return;
        }
        f(iD2);
    }

    public int hashCode() {
        return Objects.hash(this.f63a, this.f64b, Integer.valueOf(this.c));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[packageName=");
        sb.append(this.f63a);
        sb.append(",processName=");
        sb.append(this.f64b);
        sb.append(",state=");
        int i = this.c;
        if (i == 1) {
            sb.append("RESUME");
        } else if (i == 2) {
            sb.append("PAUSING");
        } else if (i != 3) {
            sb.append("unknown");
        } else {
            sb.append("PAUSED");
        }
        sb.append("]");
        return sb.toString();
    }
}
