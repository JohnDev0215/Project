package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import java.util.Collections;
import java.util.HashSet;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f355a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ y5 c;
    public final /* synthetic */ g d;

    public h(g gVar, String str, Bundle bundle, y5 y5Var) {
        this.d = gVar;
        this.f355a = str;
        this.b = bundle;
        this.c = y5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        g gVar = this.d;
        String str = this.f355a;
        Bundle bundle = this.b;
        y5 y5Var = this.c;
        gVar.getClass();
        HashSet<String> hashSet = new HashSet();
        JSONObject jSONObjectA = new i2(str, bundle, new HashSet(Collections.singletonList("email")), y5Var).a();
        if (jSONObjectA == null) {
            Log.e(ga.a(g.r), "cannot get user profile");
        } else {
            String strOptString = jSONObjectA.optString("email");
            if (!TextUtils.isEmpty(strOptString)) {
                ga.a(g.r);
                hashSet.add(strOptString);
            }
            if (hashSet.isEmpty()) {
                Log.e(ga.a(g.r), "Account has no login claim");
            }
        }
        UserDictionaryHelper userDictionaryHelperA = UserDictionaryHelper.a(this.d.f335a);
        for (String str2 : hashSet) {
            if (userDictionaryHelperA.f617a instanceof n9) {
                String strA = UserDictionaryHelper.a("addNewLogin");
                de deVarA = yd.a(UserDictionaryHelper.c, "addNewLogin");
                try {
                    try {
                        ((n9) userDictionaryHelperA.f617a).a(str2);
                        yd.a(strA, "Success");
                    } catch (UserDictionaryHelper.UserDictionaryInvalidUserLoginException e) {
                        Log.e(ga.a(UserDictionaryHelper.b), "username is invalid", e);
                        yd.a(strA, "InvalidUserLoginException");
                    }
                } finally {
                    deVarA.c();
                }
            }
        }
    }
}
