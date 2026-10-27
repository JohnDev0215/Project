package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class AtscFrontendSettings extends FrontendSettings {
    public static final int MODULATION_AUTO = 1;
    public static final int MODULATION_MOD_16VSB = 8;
    public static final int MODULATION_MOD_8VSB = 4;
    public static final int MODULATION_UNDEFINED = 0;
    private final int mModulation;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Modulation {
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 2;
    }

    private AtscFrontendSettings(int i, int i2) {
        super(i);
        this.mModulation = i2;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mFrequency;
        private int mModulation;

        private Builder() {
            this.mFrequency = 0;
            this.mModulation = 0;
        }

        public Builder setFrequency(int i) {
            this.mFrequency = i;
            return this;
        }

        public Builder setModulation(int i) {
            this.mModulation = i;
            return this;
        }

        public AtscFrontendSettings build() {
            return new AtscFrontendSettings(this.mFrequency, this.mModulation);
        }
    }
}
