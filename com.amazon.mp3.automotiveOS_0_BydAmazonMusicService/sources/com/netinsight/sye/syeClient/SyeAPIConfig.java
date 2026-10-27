package com.netinsight.sye.syeClient;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/netinsight/sye/syeClient/SyeAPIConfig;", "", "Landroid/os/Bundle;", "additionalSettings", "<init>", "(Landroid/os/Bundle;)V", "()V", "Companion", "a", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeAPIConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Bundle f729a = new Bundle();

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.SyeAPIConfig$a, reason: from kotlin metadata */
    public static final class Companion {
    }

    public SyeAPIConfig() {
        f729a = new Bundle();
    }

    public SyeAPIConfig(Bundle bundle) {
        f729a = bundle;
    }

    @JvmStatic
    public static final Bundle get() {
        INSTANCE.getClass();
        return f729a;
    }
}
