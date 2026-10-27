package com.amazon.mrn;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UUID.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"ValidateUUID", "", "value", "", "AmazonMusicMediaResourceNameKotlin"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class UUIDKt {
    public static final boolean ValidateUUID(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
