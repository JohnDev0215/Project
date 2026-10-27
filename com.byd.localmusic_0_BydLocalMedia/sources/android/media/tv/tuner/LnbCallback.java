package android.media.tv.tuner;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public interface LnbCallback {
    void onDiseqcMessage(byte[] bArr);

    void onEvent(int i);
}
