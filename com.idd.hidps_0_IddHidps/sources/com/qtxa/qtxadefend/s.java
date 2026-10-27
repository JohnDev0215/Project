package com.qtxa.qtxadefend;

import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f143a = "";

    public interface a {
        void a(String str);
    }

    class b {
        b() {
        }

        private String b(String str) {
            try {
                return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            } catch (Exception unused) {
                return null;
            }
        }

        public String a() {
            for (String str : q.d().h()) {
                w.d("rootcheck detectFileSu check if exist: " + str);
                File file = new File(str);
                if (file.exists()) {
                    StringBuilder sb = new StringBuilder();
                    s sVar = s.this;
                    sb.append(sVar.f143a);
                    sb.append("check su command:rootcheck Check Su commands: ");
                    sb.append(str);
                    sb.append(" exists. canExecute? :");
                    sb.append(file.canExecute());
                    sb.append("\n");
                    sVar.f143a = sb.toString();
                }
            }
            return null;
        }

        public boolean c() {
            for (String str : q.d().f()) {
                String strB = b(str);
                w.d("rootcheck isSecureConfig check properity: " + str + " vlues: " + strB);
                if (q.d().i(str, strB)) {
                    StringBuilder sb = new StringBuilder();
                    s sVar = s.this;
                    sb.append(sVar.f143a);
                    sb.append("check root prop:rootcheck system prop is root. for: ");
                    sb.append(str);
                    sb.append(" value: ");
                    sb.append(strB);
                    sb.append("\n");
                    sVar.f143a = sb.toString();
                }
            }
            return true;
        }
    }

    void a(a aVar) {
        this.f143a = "";
        c(aVar);
        b(aVar);
        if (this.f143a.equals("")) {
            return;
        }
        w.j(500, 3, "check root", this.f143a);
    }

    void b(a aVar) {
        List<m> listG = j.a().g(1);
        if (listG != null) {
            for (m mVar : listG) {
                if (q.d().g(mVar.f133a)) {
                    this.f143a += "check superapk:rootcheck superapk exist, try delete on ips mode: " + mVar.f133a + "\n";
                }
            }
        }
    }

    void c(a aVar) {
        b bVar = new b();
        if (!bVar.c()) {
            aVar.a("ro.secure is disabled OR ro.debuggable is enabled");
        }
        String strA = bVar.a();
        if (strA != null) {
            aVar.a("has detect file su at " + strA);
        }
    }
}
