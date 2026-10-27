package com.byd.logtools.apkinstall.b;

import java.util.Arrays;

/* JADX INFO: compiled from: InstallConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3053b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String[] f3054c;

    public b(boolean z, boolean z2, String[] strArr) {
        this.f3052a = z;
        this.f3053b = z2;
        this.f3054c = strArr;
    }

    public String[] a() {
        return this.f3054c;
    }

    public boolean b() {
        return this.f3053b;
    }

    public boolean c() {
        return this.f3052a;
    }

    public void d(boolean z) {
        this.f3053b = z;
    }

    public void e(String[] strArr) {
        this.f3054c = strArr;
    }

    public void f(boolean z) {
        this.f3052a = z;
    }

    public String toString() {
        return "InstallConfig{isSupport=" + this.f3052a + ", isAllApkSupport=" + this.f3053b + ", apkInstallWhiteList=" + Arrays.toString(this.f3054c) + '}';
    }
}
