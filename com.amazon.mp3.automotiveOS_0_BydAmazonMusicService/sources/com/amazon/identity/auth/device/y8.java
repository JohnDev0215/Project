package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.p9;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.crypto.BadPaddingException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class y8 {
    public static final String g = "com.amazon.identity.auth.device.y8";
    public static ExecutorService h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f677a;
    public p9 b;
    public final Account c;
    public g4 d;
    public final ConcurrentHashMap<String, c> e;
    public final String f;

    /* JADX INFO: compiled from: DCP */
    public class a implements AccountManagerCallback<Bundle> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Stack f678a;
        public final /* synthetic */ b b;

        public a(Stack stack, b bVar) {
            this.f678a = stack;
            this.b = bVar;
        }

        @Override // android.accounts.AccountManagerCallback
        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            String str = (String) this.f678a.peek();
            try {
                Bundle result = accountManagerFuture.getResult();
                if (!result.containsKey("authtoken")) {
                    this.b.a(str, MAPError.TokenError.FETCH_TOKEN_FAILED, result);
                    return;
                }
                y8.a(y8.this, str, result.getString("authtoken"));
                y8 y8Var = y8.this;
                if (y8.a(y8Var, y8Var.c, this.f678a, this)) {
                    return;
                }
                this.b.a();
            } catch (AuthenticatorException e) {
                this.b.a(str, MAPError.CommonError.INVALID_RESPONSE, "Authentication Exception occurred with message: " + e.getMessage(), 5, e.getMessage());
            } catch (OperationCanceledException e2) {
                this.b.a(str, MAPError.CommonError.OPERATION_CANCELLED, "Operation was cancelled with message: " + e2.getMessage(), 4, e2.getMessage());
            } catch (IOException e3) {
                String message = e3.getMessage();
                ea.a(y8.this.f677a, message);
                yd.b("NetworkError7:TokenCache", new String[0]);
                this.b.a(str, MAPError.CommonError.NETWORK_ERROR, "Network Error occurred with message: " + e3.getMessage(), 3, message);
            } catch (IllegalArgumentException e4) {
                this.b.a(str, MAPError.CommonError.BAD_REQUEST, "IllegalArgumentException occurred with message: " + e4.getMessage(), 7, e4.getMessage());
            } catch (RuntimeException e5) {
                Log.e(ga.a(y8.g), "Generic error while fetching Tokens", e5);
                this.b.a(str, MAPError.CommonError.INTERNAL_ERROR, "An internal error occurred while fetching token: " + e5.getMessage(), 1, e5.getMessage());
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
        void a();

        void a(String str, MAPError mAPError, Bundle bundle);

        void a(String str, MAPError mAPError, String str2, int i, String str3);
    }

    /* JADX INFO: compiled from: DCP */
    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f679a;
        public final String b;

        public c(y8 y8Var, String str, String str2) {
            this.f679a = str;
            this.b = str2;
        }
    }

    static {
        Executor executor = va.f637a;
        h = Executors.newSingleThreadExecutor(new ka("MAP-TokenCacheThread"));
    }

    public y8(Context context, Account account) {
        if (context == null || account == null) {
            throw new IllegalArgumentException("One or more arguments are null");
        }
        t5 t5VarA = t5.a(context);
        this.f677a = t5VarA;
        String packageName = t5VarA.getPackageName();
        this.f = packageName;
        ga.a(g, packageName + " created a new Token Cache");
        this.b = (p9) this.f677a.getSystemService("dcp_account_manager");
        this.c = account;
        this.d = new g4(this.f677a, account);
        this.e = new ConcurrentHashMap<>();
    }

    public static boolean a(y8 y8Var, Account account, Stack stack, AccountManagerCallback accountManagerCallback) {
        y8Var.getClass();
        stack.pop();
        if (stack.size() == 0) {
            return false;
        }
        y8Var.a((String) stack.peek(), accountManagerCallback);
        return true;
    }

    public final c b(String str, String str2) {
        if (str2 == null) {
            this.e.remove(str);
            return null;
        }
        if (!this.e.containsKey(str)) {
            c cVar = new c(this, str2, b(str2));
            this.e.put(str, cVar);
            return cVar;
        }
        c cVar2 = this.e.get(str);
        if (str2.equals(cVar2.f679a)) {
            return cVar2;
        }
        c cVar3 = new c(this, str2, b(str2));
        this.e.put(str, cVar3);
        return cVar3;
    }

    public String c(String str) {
        c cVarB = b(str, d(str));
        if (cVarB != null) {
            return cVarB.b;
        }
        return null;
    }

    public final String d(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("com.amazon.dcp.sso.property.account.extratokens")) {
            return this.b.a(this.c, str);
        }
        Account account = this.c;
        p9 p9Var = this.b;
        p9Var.getClass();
        y9.a("peekAuthToken");
        if (p9Var.b == null) {
            return null;
        }
        de deVarB = yd.b("AccountManagerWrapper", "peekAuthToken");
        try {
            return p9Var.b.peekAuthToken(account, str);
        } finally {
            deVarB.c();
        }
    }

    public String b(String str) {
        try {
            return this.d.a(str);
        } catch (BadPaddingException unused) {
            Log.e(ga.a(g), "The decrypt throw BadPaddingException. This should not happen in AccountTokenEncryptor!");
            return null;
        }
    }

    public void a(String[] strArr, b bVar) {
        Log.i(ga.a(g), this.f + ": fetchTokens: " + TextUtils.join(",", strArr));
        if (strArr.length == 0) {
            bVar.a();
            return;
        }
        Stack stack = new Stack();
        stack.addAll(Arrays.asList(strArr));
        a((String) stack.peek(), new a(stack, bVar));
    }

    public String a(String str) throws OperationCanceledException, IOException, AuthenticatorException {
        c cVarB;
        Log.i(ga.a(g), this.f + ": blockingFetchToken: " + str);
        Bundle bundle = (Bundle) a(str, (AccountManagerCallback) null).getResult();
        if (bundle == null || (cVarB = b(str, bundle.getString("authtoken"))) == null) {
            return null;
        }
        return cVarB.b;
    }

    public void a(String str, String str2) {
        Log.i(ga.a(g), this.f + ": setAuthToken: " + str);
        String strB = this.d.b(str2);
        this.e.put(str, new c(this, strB, str2));
        Account account = this.c;
        p9 p9Var = this.b;
        p9Var.getClass();
        y9.a("setAuthToken");
        if (p9Var.b == null) {
            return;
        }
        de deVarB = yd.b("AccountManagerWrapper", "setAuthToken");
        try {
            p9Var.b.setAuthToken(account, str, strB);
        } finally {
            deVarB.c();
        }
    }

    public static c a(y8 y8Var, String str, String str2) {
        c cVar = new c(y8Var, str2, y8Var.b(str2));
        y8Var.e.put(str, cVar);
        return cVar;
    }

    public void a(Account account, String str) {
        p9 p9Var = this.b;
        String str2 = account.type;
        p9Var.getClass();
        y9.a("invalidateAuthToken");
        if (p9Var.b == null) {
            return;
        }
        de deVarB = yd.b("AccountManagerWrapper", "invalidateAuthToken");
        try {
            p9Var.b.invalidateAuthToken(str2, str);
        } finally {
            deVarB.c();
        }
    }

    public AccountManagerFuture a(String str, AccountManagerCallback accountManagerCallback) {
        z8 z8Var = accountManagerCallback != null ? new z8(this, accountManagerCallback) : null;
        p9 p9Var = this.b;
        Account account = this.c;
        p9Var.getClass();
        y9.a("getAuthToken");
        if (p9Var.b == null) {
            return null;
        }
        return p9Var.b.getAuthToken(account, str, (Bundle) null, (Activity) null, new p9.a(z8Var, yd.b("AccountManagerWrapper", "getAuthToken")), (Handler) null);
    }
}
