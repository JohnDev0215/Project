package com.airbnb.lottie.t;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private f f795b;

    private e(e eVar) {
        this.f794a = new ArrayList(eVar.f794a);
        this.f795b = eVar.f795b;
    }

    public e(String... strArr) {
        this.f794a = Arrays.asList(strArr);
    }

    private boolean b() {
        return this.f794a.get(r0.size() - 1).equals("**");
    }

    private boolean f(String str) {
        return "__container".equals(str);
    }

    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public e a(String str) {
        e eVar = new e(this);
        eVar.f794a.add(str);
        return eVar;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean c(String str, int i) {
        if (i >= this.f794a.size()) {
            return false;
        }
        boolean z = i == this.f794a.size() - 1;
        String str2 = this.f794a.get(i);
        if (!str2.equals("**")) {
            return (z || (i == this.f794a.size() + (-2) && b())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z && this.f794a.get(i + 1).equals(str)) {
            return i == this.f794a.size() + (-2) || (i == this.f794a.size() + (-3) && b());
        }
        if (z) {
            return true;
        }
        int i2 = i + 1;
        if (i2 < this.f794a.size() - 1) {
            return false;
        }
        return this.f794a.get(i2).equals(str);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public f d() {
        return this.f795b;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int e(String str, int i) {
        if (f(str)) {
            return 0;
        }
        if (this.f794a.get(i).equals("**")) {
            return (i != this.f794a.size() - 1 && this.f794a.get(i + 1).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean g(String str, int i) {
        if (f(str)) {
            return true;
        }
        if (i >= this.f794a.size()) {
            return false;
        }
        return this.f794a.get(i).equals(str) || this.f794a.get(i).equals("**") || this.f794a.get(i).equals("*");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean h(String str, int i) {
        return "__container".equals(str) || i < this.f794a.size() - 1 || this.f794a.get(i).equals("**");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public e i(f fVar) {
        e eVar = new e(this);
        eVar.f795b = fVar;
        return eVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f794a);
        sb.append(",resolved=");
        sb.append(this.f795b != null);
        sb.append('}');
        return sb.toString();
    }
}
