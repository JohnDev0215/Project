package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.m9;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import java.util.Collection;
import org.json.JSONArray;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n9 implements l9 {
    public static n9 b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m9 f462a;

    public n9(Context context) {
        this.f462a = new m9(t5.a(context));
    }

    public void a(String str) throws UserDictionaryHelper.UserDictionaryInvalidUserLoginException {
        m9 m9Var = this.f462a;
        m9Var.getClass();
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(m9.c), "Empty username");
            throw new UserDictionaryHelper.UserDictionaryInvalidUserLoginException("Try to write an empty username");
        }
        if (str.length() > 64) {
            Log.e(ga.a(m9.c), "username exceeds the size limit 64");
            throw new UserDictionaryHelper.UserDictionaryInvalidUserLoginException("Username exceeds size limit 64");
        }
        synchronized (m9.d) {
            m9.b bVarA = m9Var.a();
            bVarA.f434a.remove(str);
            if (bVarA.f434a.size() >= 5) {
                bVarA.f434a.removeLast();
            }
            bVarA.f434a.addFirst(str);
            m9Var.f433a.b("user_dictionary", "user_dictionary_content", m9Var.b.b(new JSONArray((Collection<?>) bVarA.f434a).toString()));
        }
    }
}
