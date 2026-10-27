package com.qtxa.qtxadefend;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f113b;
    int c;
    int d;
    int e;
    ArrayList<String> f = new ArrayList<>();

    b0() {
    }

    private static b0 a(String str, int i) {
        b0 b0Var = new b0();
        b0Var.f112a = str;
        b0Var.c = i;
        b0Var.d = i;
        b0Var.e = i;
        b0Var.f113b = "ok";
        return b0Var;
    }

    private static b0 b(String str, int i, int i2) {
        b0 b0Var = new b0();
        b0Var.f112a = str;
        b0Var.c = i;
        b0Var.d = i;
        b0Var.e = i2;
        b0Var.f113b = "yesno";
        return b0Var;
    }

    static b0 c(String str, String str2, String str3) {
        int i = a.j().i();
        b0 b0VarA = i == 1 ? a("ids_ducarsecurity_installpackage", 1) : a("ids_ducarsecurity_installpackage", 0);
        w.h(601, i, "install by " + str3, str, str);
        b0VarA.f.add(str);
        return b0VarA;
    }

    static b0 d(String str, String str2) {
        b0 b0VarB = b("ids_ducarsecurity_permission", 0, 1);
        b0VarB.f.add(str);
        b0VarB.f.add(str2);
        return b0VarB;
    }

    static b0 e(String str) {
        int i = a.j().i();
        w.h(604, i, "tampered  block " + str, str, str);
        b0 b0VarA = i == 1 ? a("ids_ducarsecurity_repairpackage", 2) : a("ids_ducarsecurity_repairpackage", 0);
        b0VarA.f.add(str);
        return b0VarA;
    }

    static b0 f(String str) {
        int i = a.j().i();
        w.h(602, i, "running block " + str, str, str);
        b0 b0VarB = i == 1 ? b("ids_ducarsecurity_runpackage", 2, 1) : b("ids_ducarsecurity_runpackage", 0, 0);
        b0VarB.f.add(str);
        return b0VarB;
    }

    static b0 g(String str) {
        int i = a.j().i();
        w.h(602, i, "scanning running block " + str, str, str);
        b0 b0VarB = i == 1 ? b("ids_ducarsecurity_scanpackage", 2, 0) : b("ids_ducarsecurity_scanpackage", 0, 0);
        b0VarB.f.add(str);
        return b0VarB;
    }

    static b0 h(String str, String str2) {
        int i = a.j().i();
        b0 b0VarA = i == 1 ? a("ids_ducarsecurity_uninstallpackage", 1) : a("ids_ducarsecurity_uninstallpackage", 0);
        w.h(603, i, "uninstall " + str + "by " + str2, str, str);
        b0VarA.f.add(str2);
        b0VarA.f.add(str);
        return b0VarA;
    }

    public String toString() {
        String str = this.f112a + " ";
        ArrayList<String> arrayList = this.f;
        if (arrayList != null) {
            for (String str2 : arrayList) {
                if (str2 != null) {
                    str = str + str2 + " ";
                }
            }
        }
        return str;
    }
}
