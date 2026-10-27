package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface f {
    MAPFuture<Bundle> a(Callback callback, y5 y5Var, Bundle bundle);

    MAPFuture<Bundle> a(String str, Callback callback, y5 y5Var, Bundle bundle);

    MAPFuture<Bundle> a(String str, fa faVar, Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var);

    void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var);

    void a(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var);

    void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, y5 y5Var);

    void a(Bundle bundle, Callback callback, y5 y5Var);

    void a(RegistrationType registrationType, Bundle bundle, Callback callback, y5 y5Var);

    boolean a();

    boolean a(String str);

    MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var);

    String b();

    String b(String str);

    void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var);

    @Deprecated
    void b(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var);

    Set<String> c();
}
