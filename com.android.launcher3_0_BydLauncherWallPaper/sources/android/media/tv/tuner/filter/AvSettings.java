package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AvSettings extends Settings {
    private final boolean mIsPassthrough;

    private AvSettings(int i, boolean z, boolean z2) {
        super(TunerUtils.getFilterSubtype(i, z ? 3 : 4));
        this.mIsPassthrough = z2;
    }

    public boolean isPassthrough() {
        return this.mIsPassthrough;
    }

    public static Builder builder(int i, boolean z) {
        return new Builder(i, z);
    }

    public static class Builder {
        private final boolean mIsAudio;
        private boolean mIsPassthrough;
        private final int mMainType;

        private Builder(int i, boolean z) {
            this.mMainType = i;
            this.mIsAudio = z;
        }

        public Builder setPassthrough(boolean z) {
            this.mIsPassthrough = z;
            return this;
        }

        public AvSettings build() {
            return new AvSettings(this.mMainType, this.mIsAudio, this.mIsPassthrough);
        }
    }
}
