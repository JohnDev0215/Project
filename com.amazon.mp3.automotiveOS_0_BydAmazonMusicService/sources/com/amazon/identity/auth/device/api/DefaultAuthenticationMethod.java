package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.s2;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class DefaultAuthenticationMethod extends AuthenticationMethod {
    public DefaultAuthenticationMethod(Context context, String str) {
        super(context, str, AuthenticationType.None);
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    public MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, s2 s2Var) throws IOException {
        Log.w(ga.a("com.amazon.identity.auth.device.api.DefaultAuthenticationMethod"), "The AuthenticationType is not supported, so using DefaultAuthenticationMethod but doing nothing.");
        AuthenticationMethod.a(s2Var, 5, "Probably you are passing non-exist authentication type. Please check AuthenticationType", null);
        return s2Var;
    }
}
