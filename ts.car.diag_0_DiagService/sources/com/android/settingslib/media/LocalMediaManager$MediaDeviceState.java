package com.android.settingslib.media;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface LocalMediaManager$MediaDeviceState {
    public static final int STATE_CONNECTED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_CONNECTING_FAILED = 3;
    public static final int STATE_DISCONNECTED = 2;
}
