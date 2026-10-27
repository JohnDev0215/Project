package com.amazon.mp3.billing;

import android.util.Base64;
import com.amazon.epic.gpb.utils.idgen.IdGenerationKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObfuscatedAccountIdEncryptionUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/billing/ObfuscatedAccountIdEncryptionUtil;", "", "()V", "generateObfuscatedAccountId", "", "purchaseHandleId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ObfuscatedAccountIdEncryptionUtil {
    public String generateObfuscatedAccountId(String purchaseHandleId) {
        Intrinsics.checkNotNullParameter(purchaseHandleId, "purchaseHandleId");
        return IdGenerationKt.generateObfuscatedAccountId(purchaseHandleId, new Function1<byte[], String>() { // from class: com.amazon.mp3.billing.ObfuscatedAccountIdEncryptionUtil$generateObfuscatedAccountId$base64Encoder$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(byte[] bytes) {
                Intrinsics.checkNotNullParameter(bytes, "bytes");
                return Base64.encodeToString(bytes, 2);
            }
        });
    }
}
