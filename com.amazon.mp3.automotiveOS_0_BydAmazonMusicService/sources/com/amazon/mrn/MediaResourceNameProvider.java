package com.amazon.mrn;

import com.amazon.mrn.exceptions.UnsupportedMrnVersion;
import com.amazon.mrn.model.MrnVersion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: MediaResourceNameProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mrn/MediaResourceNameProvider;", "", "()V", "fromString", "Lcom/amazon/mrn/MediaResourceName;", "mrnString", "", "AmazonMusicMediaResourceNameKotlin"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MediaResourceNameProvider {
    public static final MediaResourceNameProvider INSTANCE = new MediaResourceNameProvider();

    private MediaResourceNameProvider() {
    }

    public final MediaResourceName fromString(String mrnString) throws UnsupportedMrnVersion {
        Intrinsics.checkNotNullParameter(mrnString, "mrnString");
        if (StringsKt__StringsJVMKt.startsWith$default(mrnString, "mrn:" + MrnVersion.V1.getValue() + ':', false, 2, null)) {
            return com.amazon.mrn.v1.MediaResourceNameProvider.INSTANCE.fromString(mrnString);
        }
        throw new UnsupportedMrnVersion(mrnString + " cannot be parsed because its version is unsupported. Supported values are one of " + MrnVersion.values() + '.', null, 2, null);
    }
}
