package com.amazon.mrn.exceptions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnsupportedMrnVersion.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mrn/exceptions/UnsupportedMrnVersion;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class UnsupportedMrnVersion extends Exception {
    public /* synthetic */ UnsupportedMrnVersion(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedMrnVersion(String message, Throwable th) {
        super(message, th);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
