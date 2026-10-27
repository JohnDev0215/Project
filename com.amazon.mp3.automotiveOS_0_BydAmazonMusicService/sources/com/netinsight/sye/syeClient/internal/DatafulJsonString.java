package com.netinsight.sye.syeClient.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/DatafulJsonString;", "", "", "a", "Ljava/lang/String;", "jsonString", "", "", "b", "[[B", "byteArrays", "<init>", "(Ljava/lang/String;[[B)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class DatafulJsonString {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    @JvmField
    public final String jsonString;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    @JvmField
    public final byte[][] byteArrays;

    public DatafulJsonString(String str, byte[][] bArr) {
        this.jsonString = str;
        this.byteArrays = bArr;
    }
}
