package com.spotify.esperanto.esperanto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface Transport {
    Single<byte[]> callSingle(String str, String str2, byte[] bArr);

    Observable<byte[]> callStream(String str, String str2, byte[] bArr);

    byte[] callSync(String str, String str2, byte[] bArr);
}
