package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class IpPayloadEvent extends FilterEvent {
    private final int mDataLength;

    private IpPayloadEvent(int i) {
        this.mDataLength = i;
    }

    public int getDataLength() {
        return this.mDataLength;
    }
}
