package k6;

import S8.AbstractC0727y;
import S8.E;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import java.util.UUID;

/* JADX INFO: renamed from: k6.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1699g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f18263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K5.t f18264b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final X8.c f18265e;

    public C1699g(Context context, K5.t tVar) {
        A7.m.f(context, "appContext");
        A7.m.f(tVar, "dataStoreManager");
        this.f18263a = context;
        this.f18264b = tVar;
        this.c = "";
        this.d = "";
        Z8.e eVar = E.f8084a;
        X8.c cVarA = AbstractC0727y.a(Z8.d.f11010n);
        this.f18265e = cVarA;
        AbstractC0727y.w(cVarA, null, null, new C1697e(this, null), 3);
    }

    public final String a() {
        String strF;
        String str = C1696d.f18245s;
        if (str != null && str.length() != 0) {
            String str2 = C1696d.f18245s;
            A7.m.c(str2);
            return str2;
        }
        if (this.c.length() == 0) {
            String string = Settings.Secure.getString(this.f18263a.getContentResolver(), "android_id");
            if (string == null || string.length() == 0 || "9774d56d682e549c".equals(string)) {
                strF = null;
            } else {
                String str3 = Build.MANUFACTURER;
                String str4 = Build.MODEL;
                if (str4 != null) {
                    string = string.concat(str4);
                }
                if (str3 != null) {
                    string = android.hardware.bydauto.ac.tsimpl.a.h(string, str3);
                }
                try {
                    byte[] bytes = string.getBytes(Q8.a.f7211a);
                    A7.m.e(bytes, "getBytes(...)");
                    strF = UUID.nameUUIDFromBytes(bytes).toString();
                } catch (Exception unused) {
                    strF = "";
                }
            }
            if (strF == null) {
                if (this.d.length() > 0) {
                    strF = this.d;
                } else {
                    strF = A7.k.f("toString(...)");
                    AbstractC0727y.w(this.f18265e, null, null, new C1698f(this, strF, null), 3);
                }
            }
            this.c = strF;
        }
        return this.c;
    }
}
