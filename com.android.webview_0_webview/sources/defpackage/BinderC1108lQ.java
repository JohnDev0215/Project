package defpackage;

import J.N;
import android.os.RemoteException;
import android.util.AndroidRuntimeException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.PathUtils;
import org.json.JSONObject;

/* JADX INFO: renamed from: lQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC1108lQ extends AbstractBinderC1330pR {
    public InterfaceC1494sR a;
    public Px b;
    public boolean c;

    public BinderC1108lQ(C0996jQ c0996jQ) {
    }

    public static BinderC1108lQ d() {
        return AbstractC1052kQ.a;
    }

    public final Px c() {
        if (this.b == null) {
            File file = new File(PathUtils.getCacheDirectory());
            file.mkdir();
            this.b = new Px(file);
        }
        return this.b;
    }

    public final String[] f() {
        c().a();
        File[] fileArrH = c().h(3);
        ArrayList arrayList = new ArrayList(fileArrH.length);
        for (File file : fileArrH) {
            arrayList.add(Px.g(file.getName()));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public final void i() {
        ((Pu) Su.f).execute(new Runnable(this) { // from class: iQ
            public final BinderC1108lQ s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                BinderC1108lQ binderC1108lQ = this.s;
                Map mapI = binderC1108lQ.c().i();
                ArrayList arrayList = new ArrayList(mapI.size());
                for (Map.Entry entry : mapI.entrySet()) {
                    JSONObject jSONObject = new JSONObject((Map) entry.getValue());
                    String str = (String) entry.getKey();
                    arrayList.add(Px.g(str + ".dmp"));
                    File fileU = binderC1108lQ.u(str);
                    if (fileU != null) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(fileU);
                            try {
                                fileOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable th2) {
                                    AbstractC0279Pk.a.a(th, th2);
                                }
                                throw th;
                            }
                        } catch (IOException unused) {
                            Ts.f("CrashReporter", Z7.c("Failed to write crash keys JSON for crash ", str), new Object[0]);
                            fileU.delete();
                        }
                    }
                }
                Px pxC = binderC1108lQ.c();
                Objects.requireNonNull(pxC);
                File file = new File(pxC.a, "Crashpad");
                if (file.exists() && pxC.d()) {
                    N.MjZjkDM7(file.getAbsolutePath(), pxC.e().getAbsolutePath());
                }
                for (File file2 : pxC.k(Px.c)) {
                    file2.renameTo(new File(file2.getPath() + ".try0"));
                }
                String[] strArr = (String[]) arrayList.toArray(new String[0]);
                if (strArr.length > 0) {
                    try {
                        ((C1385qR) binderC1108lQ.a).i(strArr);
                    } catch (RemoteException e) {
                        throw new AndroidRuntimeException(e);
                    }
                }
            }
        });
    }

    public final File u(String str) {
        File fileF = c().f(str);
        if (fileF == null) {
            return null;
        }
        return new File(fileF.getParent(), Z7.c(fileF.getName().split("\\.")[0], ".json"));
    }
}
