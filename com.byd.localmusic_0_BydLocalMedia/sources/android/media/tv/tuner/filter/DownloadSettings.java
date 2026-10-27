package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DownloadSettings extends Settings {
    private final int mDownloadId;

    private DownloadSettings(int i, int i2) {
        super(TunerUtils.getFilterSubtype(i, 5));
        this.mDownloadId = i2;
    }

    public int getDownloadId() {
        return this.mDownloadId;
    }

    public static Builder builder(int i) {
        return new Builder(i);
    }

    public static class Builder {
        private int mDownloadId;
        private final int mMainType;

        private Builder(int i) {
            this.mMainType = i;
        }

        public Builder setDownloadId(int i) {
            this.mDownloadId = i;
            return this;
        }

        public DownloadSettings build() {
            return new DownloadSettings(this.mMainType, this.mDownloadId);
        }
    }
}
