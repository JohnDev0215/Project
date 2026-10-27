package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.util.LinkedList;
import javax.crypto.BadPaddingException;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m9 {
    public static final String c = "com.amazon.identity.auth.device.m9";
    public static final Object d = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u7 f433a;
    public l7 b;

    /* JADX INFO: compiled from: DCP */
    public class a extends l7 {
        public final /* synthetic */ t5 b;

        public a(m9 m9Var, t5 t5Var) {
            this.b = t5Var;
        }

        @Override // com.amazon.identity.auth.device.l7
        public byte[] b() {
            String strA = u4.a(this.b).a();
            if (strA != null) {
                return Base64.decode(strA, 0);
            }
            Log.e(ga.a(m9.c), "Could not generate a MAP only encryption key. Aborting.");
            return null;
        }
    }

    public m9(t5 t5Var) {
        this.f433a = t5Var.a();
        this.b = new a(this, t5Var);
    }

    public b a() {
        b bVar;
        String strB = this.f433a.b("user_dictionary", "user_dictionary_content");
        try {
            synchronized (d) {
                bVar = new b(a(strB), 5);
            }
            return bVar;
        } catch (Exception unused) {
            ga.a(c);
            synchronized (d) {
                this.f433a.b("user_dictionary", "user_dictionary_content", null);
                return new b(5);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public LinkedList<String> f434a = new LinkedList<>();

        public b(JSONArray jSONArray, int i) throws JSONException {
            i = i >= jSONArray.length() ? jSONArray.length() : i;
            for (int i2 = 0; i2 < i; i2++) {
                this.f434a.add(jSONArray.getString(i2));
            }
        }

        public b(int i) {
        }
    }

    public final JSONArray a(String str) throws JSONException {
        String strA;
        try {
            strA = this.b.a(str);
        } catch (BadPaddingException unused) {
            Log.e(ga.a(c), "Cannot get encrypted data due to BadPaddingException");
            strA = null;
        }
        if (TextUtils.isEmpty(strA)) {
            return new JSONArray();
        }
        ga.a(c);
        return new JSONArray(strA);
    }
}
