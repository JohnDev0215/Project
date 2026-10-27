package com.ts.appservice.engineeringmode.d;

import b.a.b.a.e;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class f {
    private static f d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.b.a.d f45a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.ts.appservice.engineeringmode.a.a f46b;
    e.a c = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends e.a {
        a() {
        }

        @Override // b.a.b.a.e
        public void e() {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", "receive reportNotEnoughSpace ");
        }

        @Override // b.a.b.a.e
        public void f(boolean z) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportSetLogLevel " + z);
        }

        @Override // b.a.b.a.e
        public void g(String str) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportClearLogStat " + str);
        }

        @Override // b.a.b.a.e
        public void j(String str) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportMarkResult " + str);
        }

        @Override // b.a.b.a.e
        public void l(boolean z) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportSetLogEnable " + z);
        }

        @Override // b.a.b.a.e
        public void m(ArrayList<b.a.b.a.g> arrayList) {
            if (arrayList != null) {
                com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportGetLogLevel " + arrayList.toString());
            }
        }

        @Override // b.a.b.a.e
        public void o(String str, b.a.b.a.b bVar) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportExportLogStat " + str + " exportResult " + bVar.toString());
        }

        @Override // b.a.b.a.e
        public void p(boolean z) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", "receive reportGetKernelLogEnableStatus 1: " + z);
            f.this.f46b.i(1, z);
        }

        @Override // b.a.b.a.e
        public void r(boolean z) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", " reportGetLogEnableStatus " + z);
        }

        @Override // b.a.b.a.e
        public void t(boolean z) {
            com.ts.appservice.engineeringmode.e.a.a("LogManger", "receive reportGetTcpdumpEnableStatus 2: " + z);
            f.this.f46b.i(2, z);
        }
    }

    private f() {
        try {
            this.f45a = b.a.b.a.d.d();
            g();
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
        }
        com.ts.appservice.engineeringmode.e.a.g("LogManger", "mLogService : " + this.f45a);
    }

    public static synchronized f e() {
        if (d == null) {
            d = new f();
        }
        return d;
    }

    private void g() {
        try {
            if (this.f45a != null) {
                this.f45a.h(this.c);
            }
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
        }
    }

    public int b(boolean z) {
        com.ts.appservice.engineeringmode.e.a.a("LogManger", " enableConsoleSuspand state " + z);
        b.a.b.a.d dVar = this.f45a;
        if (dVar == null) {
            com.ts.appservice.engineeringmode.e.a.c("LogManger", " enableConsoleSuspand.mLogService == null");
            return -2147482648;
        }
        try {
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
        }
        return dVar.s(z) ? 0 : -2147482648;
    }

    public int c(boolean z) {
        com.ts.appservice.engineeringmode.e.a.a("LogManger", " enableTCPDump state " + z);
        b.a.b.a.d dVar = this.f45a;
        if (dVar == null) {
            com.ts.appservice.engineeringmode.e.a.c("LogManger", " enableTCPDump.mLogService == null");
            return -2147482648;
        }
        try {
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
        }
        return dVar.u(z) ? 0 : -2147482648;
    }

    public boolean d() {
        com.ts.appservice.engineeringmode.e.a.a("LogManger", "getConsoleSuspandState.in");
        b.a.b.a.d dVar = this.f45a;
        if (dVar == null) {
            com.ts.appservice.engineeringmode.e.a.c("LogManger", "getConsoleSuspandState.mLogService == null");
            return false;
        }
        try {
            return dVar.q();
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
            return false;
        }
    }

    public boolean f() {
        com.ts.appservice.engineeringmode.e.a.a("LogManger", "getTcpDumpState.in");
        b.a.b.a.d dVar = this.f45a;
        if (dVar == null) {
            com.ts.appservice.engineeringmode.e.a.c("LogManger", "getTcpDumpState.mLogService == null");
            return false;
        }
        try {
            return dVar.n();
        } catch (Exception e) {
            com.ts.appservice.engineeringmode.e.a.b("LogManger", e);
            return false;
        }
    }

    public void h(com.ts.appservice.engineeringmode.a.a aVar) {
        this.f46b = aVar;
    }
}
