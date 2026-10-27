package com.amazon.identity.auth.device.userdictionary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.k9;
import com.amazon.identity.auth.device.l9;
import com.amazon.identity.auth.device.n9;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.yd;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class UserDictionaryHelper {
    public static final String b = "UserDictionaryHelper";
    public static final String c = "UserDictionaryHelper";
    public static UserDictionaryHelper d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l9 f617a;

    /* JADX INFO: compiled from: DCP */
    public static class UserDictionaryInvalidUserLoginException extends Exception {
        public UserDictionaryInvalidUserLoginException(String str) {
            super(str);
        }
    }

    public UserDictionaryHelper(Context context) {
        l9 l9VarB = b(context);
        this.f617a = l9VarB;
        if (l9VarB instanceof n9) {
            a();
        }
    }

    public static synchronized UserDictionaryHelper a(Context context) {
        if (d == null) {
            d = new UserDictionaryHelper(context);
        }
        return d;
    }

    public static l9 b(Context context) {
        n9 n9Var;
        if (!qe.m(context)) {
            return new k9();
        }
        synchronized (n9.class) {
            if (n9.b == null) {
                n9.b = new n9(context);
            }
            n9Var = n9.b;
        }
        return n9Var;
    }

    public List<String> a() {
        if (!(this.f617a instanceof n9)) {
            return null;
        }
        String strA = a("getUserDictionary");
        de deVarA = yd.a(c, "getUserDictionary");
        try {
            List<String> arrayList = ((n9) this.f617a).f462a.a().f434a;
            yd.a(strA, "Success");
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e(ga.a(b), "JSONException when tyring to get user dict cache", e);
            yd.a(strA, "JSONException");
            return null;
        } finally {
            deVarA.c();
        }
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? c : String.format("%s_%s", c, str);
    }
}
