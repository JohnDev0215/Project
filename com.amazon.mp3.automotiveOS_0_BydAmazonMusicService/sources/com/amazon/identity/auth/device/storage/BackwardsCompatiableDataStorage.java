package com.amazon.identity.auth.device.storage;

import android.accounts.Account;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.c4;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.g4;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.l7;
import com.amazon.identity.auth.device.o7;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.r7;
import com.amazon.identity.auth.device.r9;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w4;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.BadPaddingException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class BackwardsCompatiableDataStorage extends u7 {
    public static final String e = "com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage";
    public static AtomicInteger f = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u7 f548a;
    public final i5 b;
    public final l7 c;
    public final boolean d;

    /* JADX INFO: compiled from: DCP */
    public static class BackwardsCompatibleDataStorageException extends Exception implements d7.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f549a;
        public static final String b;
        private d7 mAccountRecoverContext;

        static {
            MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.INTERNAL_ERROR;
            f549a = registrationError.value();
            b = registrationError.getName();
        }

        public BackwardsCompatibleDataStorageException(d7 d7Var) {
            super(b);
            this.mAccountRecoverContext = d7Var;
        }

        @Override // com.amazon.identity.auth.device.d7.a
        public d7 a() {
            return this.mAccountRecoverContext;
        }

        @Override // com.amazon.identity.auth.device.d7.a
        public String b() {
            return super.getMessage();
        }

        @Override // com.amazon.identity.auth.device.d7.a
        public int c() {
            return f549a;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class a extends l7 {
        public final /* synthetic */ String b;

        public a(BackwardsCompatiableDataStorage backwardsCompatiableDataStorage, String str) {
            this.b = str;
        }

        @Override // com.amazon.identity.auth.device.l7
        public byte[] b() {
            return Base64.decode(this.b, 0);
        }
    }

    public BackwardsCompatiableDataStorage(t5 t5Var) {
        this(t5Var, t5Var.a());
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        return this.f548a.a();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        return this.f548a.b();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        return this.f548a.c(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d() {
        this.f548a.d();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
        this.f548a.e();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
        this.f548a.f();
    }

    public final boolean g() {
        return this.f548a instanceof r7;
    }

    public BackwardsCompatiableDataStorage(t5 t5Var, u7 u7Var) {
        this(u7Var, (i5) t5Var.getSystemService("sso_platform"), new w4(t5Var));
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        a aVar2;
        String strA = null;
        if (this.d) {
            aVar2 = null;
        } else {
            strA = c4.a();
            aVar2 = new a(this, strA);
        }
        o7 o7VarA = a(o7Var, aVar2);
        if (strA != null) {
            o7VarA.b.put(AccountConstants.KEY_TOKEN_ENCRYPT_KEY, strA);
        }
        return this.f548a.a(str, o7VarA, aVar);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        return this.f548a.b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        return f(str2) ? d(str, str2) : this.f548a.c(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        try {
            return e(str, str2);
        } catch (BackwardsCompatibleDataStorageException e2) {
            Log.e(ga.a(e), "BadPaddingException occurs. Swallow this exception here.", e2);
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        this.f548a.e(str);
    }

    public final boolean f(String str) {
        return r9.a(str) || r9.b(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        this.f548a.b(str, str2, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String e(java.lang.String r23, java.lang.String r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage.e(java.lang.String, java.lang.String):java.lang.String");
    }

    public BackwardsCompatiableDataStorage(u7 u7Var, i5 i5Var, l7 l7Var) {
        this.f548a = u7Var;
        this.b = i5Var;
        this.c = l7Var;
        this.d = g();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        return this.f548a.b(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void c(String str, String str2, String str3) {
        if (f(str2)) {
            d(str, str2, str3);
        } else {
            this.f548a.c(str, str2, str3);
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        if (this.d) {
            this.f548a.d(str, str2, str3);
            return;
        }
        g4 g4Var = new g4(this.f548a, str);
        if (r9.b(str2)) {
            str3 = g4Var.b(str3);
        } else if (r9.a(str2)) {
            str3 = this.c.b(str3);
        }
        this.f548a.d(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        return this.f548a.c();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        if (f(str2)) {
            d(str, str2, null);
        } else {
            this.f548a.a(str, str2);
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        return this.f548a.d(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        this.f548a.a(a(o7Var, this.d ? null : new g4(this.f548a, o7Var.f476a)));
    }

    public final o7 a(o7 o7Var, l7 l7Var) {
        HashMap map = new HashMap(o7Var.b);
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : o7Var.c.entrySet()) {
            if (f(entry.getKey())) {
                map.put(entry.getKey(), entry.getValue());
            } else {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        if (l7Var != null) {
            for (Map.Entry entry2 : map.entrySet()) {
                String str = (String) entry2.getKey();
                String strB = (String) entry2.getValue();
                if (r9.b(str)) {
                    strB = l7Var.b(strB);
                } else if (r9.a(str)) {
                    strB = this.c.b(strB);
                }
                entry2.setValue(strB);
            }
        }
        return new o7(o7Var.f476a, map, map2, null);
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        return this.f548a.a(str, o7Var, aVar, list);
    }

    public final String e(String str, String str2, String str3) throws BadPaddingException {
        l7 g4Var;
        if (r9.a(str2)) {
            String str4 = e;
            ga.a(str4);
            if (qe.i(this.b.f382a)) {
                g4Var = new g4(this.f548a, str);
            } else {
                g4Var = this.c;
            }
            String strA = g4Var.a(str3);
            if (strA == null) {
                Log.w(ga.a(str4), "Could not decrypt tokens using expected methods.");
            }
            return strA;
        }
        if (r9.b(str2)) {
            ga.a(e);
            return new g4(this.f548a, str).a(str3);
        }
        ga.a(e);
        return str3;
    }
}
