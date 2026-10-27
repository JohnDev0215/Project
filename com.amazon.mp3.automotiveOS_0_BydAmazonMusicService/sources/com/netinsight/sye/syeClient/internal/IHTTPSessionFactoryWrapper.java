package com.netinsight.sye.syeClient.internal;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/IHTTPSessionFactoryWrapper;", "", "", "timeoutMillis", "Lcom/netinsight/sye/syeClient/internal/IHTTPSessionWrapper;", "createHttpSession", "(I)Lcom/netinsight/sye/syeClient/internal/IHTTPSessionWrapper;", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IHTTPSessionFactoryWrapper {
    IHTTPSessionWrapper createHttpSession(int timeoutMillis);
}
