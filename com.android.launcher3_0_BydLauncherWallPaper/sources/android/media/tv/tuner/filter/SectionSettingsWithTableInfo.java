package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.filter.SectionSettings;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class SectionSettingsWithTableInfo extends SectionSettings {
    private final int mTableId;
    private final int mVersion;

    private SectionSettingsWithTableInfo(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        super(i, z, z2, z3);
        this.mTableId = i2;
        this.mVersion = i3;
    }

    public int getTableId() {
        return this.mTableId;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public static Builder builder(int i) {
        return new Builder(i);
    }

    public static class Builder extends SectionSettings.Builder<Builder> {
        private int mTableId;
        private int mVersion;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.media.tv.tuner.filter.SectionSettings.Builder
        public Builder self() {
            return this;
        }

        private Builder(int i) {
            super(i);
        }

        public Builder setTableId(int i) {
            this.mTableId = i;
            return this;
        }

        public Builder setVersion(int i) {
            this.mVersion = i;
            return this;
        }

        public SectionSettingsWithTableInfo build() {
            return new SectionSettingsWithTableInfo(this.mMainType, this.mCrcEnabled, this.mIsRepeat, this.mIsRaw, this.mTableId, this.mVersion);
        }
    }
}
