package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface e9 {
    MAPFuture<Bundle> a(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, r8 r8Var, y5 y5Var);

    MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> a(String str, String str2, String str3, Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> c(String str, String str2, Bundle bundle, Callback callback, y5 y5Var);
}
