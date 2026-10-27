package com.netinsight.sye.syeClient.internal;

import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/IHTTPSessionWrapper;", "", "", ImagesContract.URL, "", "httpResultHandlerPointer", "", "doGET", "(Ljava/lang/String;J)V", "headersJsonMap", "body", "doPOST", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IHTTPSessionWrapper {
    void doGET(String url, long httpResultHandlerPointer);

    void doPOST(String url, String headersJsonMap, String body, long httpResultHandlerPointer);
}
