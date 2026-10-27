package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.EnumSet;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface t1 {
    Bundle a(String str, String str2);

    MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, y5 y5Var);

    MAPFuture<Bundle> a(String str, String str2, String str3, Callback callback);
}
