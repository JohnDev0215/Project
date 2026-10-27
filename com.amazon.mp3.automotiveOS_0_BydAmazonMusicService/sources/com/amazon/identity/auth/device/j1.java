package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f390a;
    public final String b;
    public final List<String> c;

    public j1(String str, String str2, List<String> list) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || list == null || list.isEmpty()) {
            throw new IllegalArgumentException("One or more parameters are null or empty");
        }
        this.f390a = str;
        this.b = str2;
        this.c = Collections.unmodifiableList(new ArrayList(list));
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.adp_token";
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.email";
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.dsn";
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.device_type";
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.private_key";
    }

    public static String g(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.storeAuthCookie";
    }

    public static String h(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.device_name";
    }

    public static String i(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.user_firstname";
    }

    public static String j(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.user_name";
    }

    public static String k(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.xmain";
    }

    public final List<String> a() {
        String str;
        String str2;
        String str3;
        String str4;
        String strB = b(this.f390a);
        String str5 = this.f390a;
        String str6 = null;
        if (str5 == null) {
            str = null;
        } else {
            str = str5 + ".tokens.private_key";
        }
        String strE = e(this.f390a);
        String str7 = this.f390a;
        if (str7 == null) {
            str2 = null;
        } else {
            str2 = str7 + ".tokens.dsn";
        }
        String strC = c(this.f390a);
        String str8 = this.f390a;
        if (str8 == null) {
            str3 = null;
        } else {
            str3 = str8 + ".tokens.storeAuthCookie";
        }
        String str9 = this.f390a;
        if (str9 == null) {
            str4 = null;
        } else {
            str4 = str9 + ".tokens.xmain";
        }
        String str10 = this.f390a;
        if (str10 != null) {
            str6 = str10 + ".tokens.account_pool";
        }
        return Collections.unmodifiableList(Arrays.asList(strB, str, strE, str2, strC, str3, str4, str6));
    }

    public j1(String str, String str2, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && !e.i(str2) && !TextUtils.isEmpty(str3)) {
            this.f390a = str;
            this.b = str2;
            this.c = a();
            return;
        }
        throw new IllegalArgumentException("One or more parameters are null or empty");
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        return str + ".tokens.account_pool";
    }
}
