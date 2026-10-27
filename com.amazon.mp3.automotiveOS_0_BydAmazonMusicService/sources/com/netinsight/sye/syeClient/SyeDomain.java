package com.netinsight.sye.syeClient;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\n\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/SyeDomain;", "", "", "b", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "name", "a", "getBaseUrl", "baseUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeDomain {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final String baseUrl;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final String name;

    public SyeDomain(String str, String str2) {
        this.baseUrl = str;
        this.name = str2;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final String getName() {
        return this.name;
    }
}
