package android.media.tv.tuner.filter;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public abstract class Settings {
    private final int mType;

    Settings(int i) {
        this.mType = i;
    }

    public int getType() {
        return this.mType;
    }
}
