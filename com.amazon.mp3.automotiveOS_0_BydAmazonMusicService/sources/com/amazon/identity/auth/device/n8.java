package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n8 {
    public static final String e = "com.amazon.identity.auth.device.n8";
    public static n8 f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f460a = new Object[0];
    public final t5 b;
    public final p9 c;
    public final WeakHashMap<Account, a> d;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final p9 f461a;
        public final Context b;
        public final y8 c;
        public final Account d;
        public final String e;

        public a(Context context, p9 p9Var, Account account) {
            this.b = context;
            this.d = account;
            this.c = new y8(context, account);
            this.f461a = p9Var;
            this.e = p9Var.a(account, AccountConstants.KEY_ACCOUNT_UUID);
        }
    }

    public n8(Context context) {
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.c = (p9) t5VarA.getSystemService("dcp_account_manager");
        this.d = new WeakHashMap<>();
    }

    public static synchronized n8 a(Context context) {
        if (f == null) {
            f = new n8(context.getApplicationContext());
        }
        return f;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023 A[Catch: all -> 0x0035, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000d, B:12:0x0031, B:13:0x0033, B:9:0x001b, B:11:0x0023), top: B:18:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.y8 b(android.accounts.Account r6) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.f460a
            monitor-enter(r0)
            java.util.WeakHashMap<android.accounts.Account, com.amazon.identity.auth.device.n8$a> r1 = r5.d     // Catch: java.lang.Throwable -> L35
            java.lang.Object r1 = r1.get(r6)     // Catch: java.lang.Throwable -> L35
            com.amazon.identity.auth.device.n8$a r1 = (com.amazon.identity.auth.device.n8.a) r1     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L23
            com.amazon.identity.auth.device.p9 r2 = r1.f461a     // Catch: java.lang.Throwable -> L35
            android.accounts.Account r3 = r1.d     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = "com.amazon.dcp.sso.property.account.UUID"
            java.lang.String r2 = r2.a(r3, r4)     // Catch: java.lang.Throwable -> L35
            if (r2 != 0) goto L1b
            r2 = 0
            goto L21
        L1b:
            java.lang.String r3 = r1.e     // Catch: java.lang.Throwable -> L35
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> L35
        L21:
            if (r2 != 0) goto L31
        L23:
            com.amazon.identity.auth.device.n8$a r1 = new com.amazon.identity.auth.device.n8$a     // Catch: java.lang.Throwable -> L35
            com.amazon.identity.auth.device.t5 r2 = r5.b     // Catch: java.lang.Throwable -> L35
            com.amazon.identity.auth.device.p9 r3 = r5.c     // Catch: java.lang.Throwable -> L35
            r1.<init>(r2, r3, r6)     // Catch: java.lang.Throwable -> L35
            java.util.WeakHashMap<android.accounts.Account, com.amazon.identity.auth.device.n8$a> r2 = r5.d     // Catch: java.lang.Throwable -> L35
            r2.put(r6, r1)     // Catch: java.lang.Throwable -> L35
        L31:
            com.amazon.identity.auth.device.y8 r6 = r1.c     // Catch: java.lang.Throwable -> L35
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L35
            return r6
        L35:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L35
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.n8.b(android.accounts.Account):com.amazon.identity.auth.device.y8");
    }

    public y8 a(Account account) {
        synchronized (this.f460a) {
            if (!this.c.a(account)) {
                ga.a(e);
                return null;
            }
            return b(account);
        }
    }
}
