package com.netinsight.sye.syeClient.drm;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/netinsight/sye/syeClient/drm/SyeDRMKeyIdentifier;", "", "", "a", "Ljava/lang/String;", "getKeyId", "()Ljava/lang/String;", "keyId", "<init>", "(Ljava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeDRMKeyIdentifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final String keyId;

    public SyeDRMKeyIdentifier(String str) {
        this.keyId = str;
    }

    public final String getKeyId() {
        return this.keyId;
    }
}
