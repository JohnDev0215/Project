package com.airbnb.lottie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: KeyPath.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f2601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f2602b;

    public d(String... strArr) {
        this.f2601a = Arrays.asList(strArr);
    }

    private boolean b() {
        return this.f2601a.get(r0.size() - 1).equals("**");
    }

    private boolean f(String str) {
        return "__container".equals(str);
    }

    public d a(String str) {
        d dVar = new d(this);
        dVar.f2601a.add(str);
        return dVar;
    }

    public boolean c(String str, int i) {
        if (i >= this.f2601a.size()) {
            return false;
        }
        boolean z = i == this.f2601a.size() - 1;
        String str2 = this.f2601a.get(i);
        if (!str2.equals("**")) {
            return (z || (i == this.f2601a.size() + (-2) && b())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z && this.f2601a.get(i + 1).equals(str)) {
            return i == this.f2601a.size() + (-2) || (i == this.f2601a.size() + (-3) && b());
        }
        if (z) {
            return true;
        }
        int i2 = i + 1;
        if (i2 < this.f2601a.size() - 1) {
            return false;
        }
        return this.f2601a.get(i2).equals(str);
    }

    public e d() {
        return this.f2602b;
    }

    public int e(String str, int i) {
        if (f(str)) {
            return 0;
        }
        if (this.f2601a.get(i).equals("**")) {
            return (i != this.f2601a.size() - 1 && this.f2601a.get(i + 1).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public boolean g(String str, int i) {
        if (f(str)) {
            return true;
        }
        if (i >= this.f2601a.size()) {
            return false;
        }
        return this.f2601a.get(i).equals(str) || this.f2601a.get(i).equals("**") || this.f2601a.get(i).equals("*");
    }

    public boolean h(String str, int i) {
        return "__container".equals(str) || i < this.f2601a.size() - 1 || this.f2601a.get(i).equals("**");
    }

    public d i(e eVar) {
        d dVar = new d(this);
        dVar.f2602b = eVar;
        return dVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f2601a);
        sb.append(",resolved=");
        sb.append(this.f2602b != null);
        sb.append('}');
        return sb.toString();
    }

    private d(d dVar) {
        this.f2601a = new ArrayList(dVar.f2601a);
        this.f2602b = dVar.f2602b;
    }
}
