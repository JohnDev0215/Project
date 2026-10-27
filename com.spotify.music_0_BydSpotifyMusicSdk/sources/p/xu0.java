package p;

import android.content.Context;
import android.text.TextUtils;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class xu0 {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    public xu0(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int i = jg2.a;
        if (!(true ^ (str == null || str.trim().isEmpty()))) {
            throw new IllegalStateException("ApplicationId must be set.");
        }
        this.b = str;
        this.a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }

    public static xu0 a(Context context) {
        ww1 ww1Var = new ww1(context, 6);
        String strW = ww1Var.w("google_app_id");
        if (TextUtils.isEmpty(strW)) {
            return null;
        }
        return new xu0(strW, ww1Var.w("google_api_key"), ww1Var.w("firebase_database_url"), ww1Var.w("ga_trackingId"), ww1Var.w("gcm_defaultSenderId"), ww1Var.w("google_storage_bucket"), ww1Var.w("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof xu0)) {
            return false;
        }
        xu0 xu0Var = (xu0) obj;
        return ci1.s(this.b, xu0Var.b) && ci1.s(this.a, xu0Var.a) && ci1.s(this.c, xu0Var.c) && ci1.s(this.d, xu0Var.d) && ci1.s(this.e, xu0Var.e) && ci1.s(this.f, xu0Var.f) && ci1.s(this.g, xu0Var.g);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.a, this.c, this.d, this.e, this.f, this.g});
    }

    public final String toString() {
        g90 g90Var = new g90(this);
        g90Var.a(this.b, "applicationId");
        g90Var.a(this.a, "apiKey");
        g90Var.a(this.c, "databaseUrl");
        g90Var.a(this.e, "gcmSenderId");
        g90Var.a(this.f, "storageBucket");
        g90Var.a(this.g, "projectId");
        return g90Var.toString();
    }
}
