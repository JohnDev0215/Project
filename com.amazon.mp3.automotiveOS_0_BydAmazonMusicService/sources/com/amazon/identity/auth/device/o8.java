package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.y8;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o8 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f477a;
    public final /* synthetic */ Account b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ s2 e;
    public final /* synthetic */ y5 f;
    public final /* synthetic */ q8 g;

    public o8(q8 q8Var, String str, Account account, String str2, Bundle bundle, s2 s2Var, y5 y5Var) {
        this.g = q8Var;
        this.f477a = str;
        this.b = account;
        this.c = str2;
        this.d = bundle;
        this.e = s2Var;
        this.f = y5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        q8 q8Var = this.g;
        String str = this.f477a;
        Account account = this.b;
        String str2 = this.c;
        Bundle bundle = this.d;
        s2 s2Var = this.e;
        y5 y5Var = this.f;
        q8Var.getClass();
        fa faVarA = fa.a(str);
        y8 y8VarA = n8.a(q8Var.f513a).a(account);
        boolean z = !TextUtils.equals("true", q8Var.f513a.a().d(str2, "force_refresh_dms_to_oauth_done_once"));
        if (TextUtils.equals(faVarA.c, "com.amazon.dcp.sso.token.oauth.amazon.access_token")) {
            if (z || (bundle != null && bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_DMS_TO_OAUTH))) {
                y8.c cVarB = y8VarA.b("com.amazon.dcp.sso.token.oauth.amazon.access_token", y8VarA.d("com.amazon.dcp.sso.token.oauth.amazon.access_token"));
                r9.a(q8Var.f513a, str2, cVarB != null ? cVarB.b : null);
                y8.c cVarB2 = y8VarA.b(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, y8VarA.d(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
                r9.a(q8Var.f513a, str2, cVarB2 != null ? cVarB2.b : null);
            } else if (q8Var.b.d(str2, faVarA, bundle, y5Var)) {
                y8.c cVarB3 = y8VarA.b("com.amazon.dcp.sso.token.oauth.amazon.access_token", y8VarA.d("com.amazon.dcp.sso.token.oauth.amazon.access_token"));
                r9.a(q8Var.f513a, str2, cVarB3 != null ? cVarB3.b : null);
            }
        }
        String str3 = faVarA.c;
        y8VarA.a(new String[]{str3}, new p8(q8Var, y8VarA, str3, faVarA, str2, s2Var, z));
    }
}
