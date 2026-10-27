package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public abstract class SectionSettings extends Settings {
    final boolean mCrcEnabled;
    final boolean mIsRaw;
    final boolean mIsRepeat;

    SectionSettings(int i, boolean z, boolean z2, boolean z3) {
        super(TunerUtils.getFilterSubtype(i, 1));
        this.mCrcEnabled = z;
        this.mIsRepeat = z2;
        this.mIsRaw = z3;
    }

    public boolean isCrcEnabled() {
        return this.mCrcEnabled;
    }

    public boolean isRepeat() {
        return this.mIsRepeat;
    }

    public boolean isRaw() {
        return this.mIsRaw;
    }

    public static abstract class Builder<T extends Builder<T>> {
        boolean mCrcEnabled;
        boolean mIsRaw;
        boolean mIsRepeat;
        final int mMainType;

        abstract T self();

        Builder(int i) {
            this.mMainType = i;
        }

        public T setCrcEnabled(boolean z) {
            this.mCrcEnabled = z;
            return (T) self();
        }

        public T setRepeat(boolean z) {
            this.mIsRepeat = z;
            return (T) self();
        }

        public T setRaw(boolean z) {
            this.mIsRaw = z;
            return (T) self();
        }
    }
}
