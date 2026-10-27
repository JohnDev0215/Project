package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPFuture;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface f2 {
    Bundle a(String str);

    Bundle a(String str, Bundle bundle);

    Bundle a(String str, String str2);

    Bundle a(String str, String str2, String str3);

    MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback, y5 y5Var);

    MAPFuture<Bundle> a(@NonNull MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, String str, Bundle bundle, Callback callback, y5 y5Var);

    Bundle b(String str);

    Bundle b(String str, String str2);

    Bundle c(String str, String str2);
}
