package com.amazon.music.skyfire.platform;

import kotlin.Metadata;

/* JADX INFO: compiled from: MethodQueue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b \u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/skyfire/platform/MethodQueue;", "T", "", "()V", "addMethod", "", "ownerId", "", "methodId", "method", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "DMSFCoreKMM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class MethodQueue<T> {
    public abstract void addMethod(String ownerId, String methodId, T method);
}
