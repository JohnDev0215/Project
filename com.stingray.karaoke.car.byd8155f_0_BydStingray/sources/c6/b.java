package C6;

import A6.c;
import A6.d;
import A7.m;
import B5.C0083g;
import B5.z;
import S8.AbstractC0727y;
import S8.E;
import Z8.e;
import android.content.Context;
import java.util.LinkedHashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static UUID f1011e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1013b;
    public C0083g c;
    public String d;

    public b(Context context, c cVar) {
        m.f(context, "context");
        this.f1012a = context;
        this.f1013b = cVar;
        this.d = "";
    }

    public final void a(String str, String str2) {
        m.f(str, "name");
        if (str.length() <= 0 || str.equals(this.d)) {
            return;
        }
        UUID uuidRandomUUID = UUID.randomUUID();
        z zVar = new z();
        zVar.i(str);
        zVar.k(String.valueOf(f1011e), "previous_navigation_uid");
        LinkedHashMap linkedHashMap = d.f439a;
        com.bumptech.glide.d.p(zVar);
        zVar.k(uuidRandomUUID, "navigation_uid");
        if (str2 != null) {
            zVar.k(str2, "swimlane_id");
        }
        f1011e = uuidRandomUUID;
        e eVar = E.f8084a;
        AbstractC0727y.w(AbstractC0727y.a(Z8.d.f11010n), null, null, new a(this, str, zVar, null), 3);
        this.d = str;
    }
}
