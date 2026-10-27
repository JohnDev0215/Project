package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.y8;
import java.util.HashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p8 implements y8.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y8 f491a;
    public final /* synthetic */ String b;
    public final /* synthetic */ fa c;
    public final /* synthetic */ String d;
    public final /* synthetic */ s2 e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ q8 g;

    public p8(q8 q8Var, y8 y8Var, String str, fa faVar, String str2, s2 s2Var, boolean z) {
        this.g = q8Var;
        this.f491a = y8Var;
        this.b = str;
        this.c = faVar;
        this.d = str2;
        this.e = s2Var;
        this.f = z;
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a() {
        String strC = this.f491a.c(this.b);
        c8 c8Var = new c8(this.g.f513a);
        String strA = e.a(this.c.b, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT);
        HashMap map = new HashMap();
        map.put(strA, Long.toString(System.currentTimeMillis()));
        c8Var.b(this.d, map);
        a9.a((Callback) this.e, strC, false);
        if (this.f) {
            c8Var.d(this.d, "force_refresh_dms_to_oauth_done_once", "true");
            yd.a("fixCentralTokenOnGrover/Canary", new String[0]);
        }
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a(String str, MAPError mAPError, Bundle bundle) {
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, mAPError.getErrorMessage());
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        this.e.onError(bundle);
    }

    @Override // com.amazon.identity.auth.device.y8.b
    public void a(String str, MAPError mAPError, String str2, int i, String str3) {
        a9.b(this.e, mAPError, str2, i, str3);
    }
}
