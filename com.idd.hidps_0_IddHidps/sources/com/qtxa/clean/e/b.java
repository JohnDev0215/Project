package com.qtxa.clean.e;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f32a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f33b;
    private boolean h;
    private int l = -1;
    private String c = null;
    private List<String> d = new ArrayList();
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private long i = -1;
    private int j = -1;
    private int k = -1;

    b(int i, String str) {
        this.f32a = i;
        this.f33b = str;
    }

    int a() {
        return this.j;
    }

    int b() {
        return this.l;
    }

    long c() {
        return this.i;
    }

    String d() {
        return this.c;
    }

    int e() {
        return this.f32a;
    }

    String f() {
        return this.f33b;
    }

    int g() {
        return this.k;
    }

    String h() {
        StringBuilder sb = new StringBuilder();
        sb.append("kill background");
        int i = this.l;
        if (i == 1) {
            sb.append(" - mem");
        } else if (i == 2) {
            sb.append(" - cpu");
        }
        return sb.toString();
    }

    boolean i() {
        return (j() || (this.e & 2) == 0) ? false : true;
    }

    boolean j() {
        return (this.e & 4) != 0;
    }

    boolean k() {
        String str;
        return (!this.g || (str = this.c) == null || str.isEmpty()) ? false : true;
    }

    boolean l() {
        return this.h;
    }

    boolean m() {
        return this.f;
    }

    void n(int i) {
        int i2 = this.e;
        if (i2 == i) {
            return;
        }
        if (i2 != 0) {
            this.e = i | i2;
        } else {
            this.e = i;
        }
    }

    void o(int i) {
        this.l = i;
    }

    void p(boolean z) {
        this.g = z;
    }

    void q(long j) {
        this.i = j;
    }

    void r(boolean z) {
        this.h = z;
    }

    void s(String str) {
        this.c = str;
    }

    public void t(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                if (!this.d.contains(str)) {
                    this.d.add(str);
                }
            }
        }
    }

    public String toString() {
        return "ProcessBean [pid=" + this.f32a + ", processName=" + this.f33b + "]";
    }
}
