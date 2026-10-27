package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;

/* JADX INFO: loaded from: classes2.dex */
public final class RecommendedStreamConfiguration extends StreamConfiguration {
    private final int mUsecaseBitmap;

    public RecommendedStreamConfiguration(int i, int i2, int i3, boolean z, int i4) {
        super(i, i2, i3, z);
        this.mUsecaseBitmap = i4;
    }

    public int getUsecaseBitmap() {
        return this.mUsecaseBitmap;
    }

    @Override // android.hardware.camera2.params.StreamConfiguration
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecommendedStreamConfiguration)) {
            return false;
        }
        RecommendedStreamConfiguration recommendedStreamConfiguration = (RecommendedStreamConfiguration) obj;
        return this.mFormat == recommendedStreamConfiguration.mFormat && this.mWidth == recommendedStreamConfiguration.mWidth && this.mHeight == recommendedStreamConfiguration.mHeight && this.mUsecaseBitmap == recommendedStreamConfiguration.mUsecaseBitmap && this.mInput == recommendedStreamConfiguration.mInput;
    }

    @Override // android.hardware.camera2.params.StreamConfiguration
    public int hashCode() {
        return HashCodeHelpers.hashCode(this.mFormat, this.mWidth, this.mHeight, this.mInput ? 1 : 0, this.mUsecaseBitmap);
    }
}
