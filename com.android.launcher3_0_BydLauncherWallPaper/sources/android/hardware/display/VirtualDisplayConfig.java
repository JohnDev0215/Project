package android.hardware.display;

import android.annotation.IntRange;
import android.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.internal.util.AnnotationValidations;

/* JADX INFO: loaded from: classes2.dex */
public final class VirtualDisplayConfig implements Parcelable {
    public static final Parcelable.Creator<VirtualDisplayConfig> CREATOR = new Parcelable.Creator<VirtualDisplayConfig>() { // from class: android.hardware.display.VirtualDisplayConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VirtualDisplayConfig[] newArray(int i) {
            return new VirtualDisplayConfig[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VirtualDisplayConfig createFromParcel(Parcel parcel) {
            return new VirtualDisplayConfig(parcel);
        }
    };
    private int mDensityDpi;
    private int mDisplayIdToMirror;
    private int mFlags;
    private int mHeight;
    private String mName;
    private Surface mSurface;
    private String mUniqueId;
    private int mWidth;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    VirtualDisplayConfig(String str, int i, int i2, int i3, int i4, Surface surface, String str2, int i5) {
        this.mFlags = 0;
        this.mSurface = null;
        this.mUniqueId = null;
        this.mDisplayIdToMirror = 0;
        this.mName = str;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
        this.mWidth = i;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, TypedValues.TransitionType.S_FROM, 1L);
        this.mHeight = i2;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i2, TypedValues.TransitionType.S_FROM, 1L);
        this.mDensityDpi = i3;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i3, TypedValues.TransitionType.S_FROM, 1L);
        this.mFlags = i4;
        this.mSurface = surface;
        this.mUniqueId = str2;
        this.mDisplayIdToMirror = i5;
    }

    public String getName() {
        return this.mName;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getDensityDpi() {
        return this.mDensityDpi;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public String getUniqueId() {
        return this.mUniqueId;
    }

    public int getDisplayIdToMirror() {
        return this.mDisplayIdToMirror;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.mSurface != null ? 32 : 0;
        if (this.mUniqueId != null) {
            i2 |= 64;
        }
        parcel.writeInt(i2);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mDensityDpi);
        parcel.writeInt(this.mFlags);
        Surface surface = this.mSurface;
        if (surface != null) {
            parcel.writeTypedObject(surface, i);
        }
        String str = this.mUniqueId;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeInt(this.mDisplayIdToMirror);
    }

    VirtualDisplayConfig(Parcel parcel) {
        this.mFlags = 0;
        this.mSurface = null;
        this.mUniqueId = null;
        this.mDisplayIdToMirror = 0;
        int i = parcel.readInt();
        String string = parcel.readString();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        int i4 = parcel.readInt();
        int i5 = parcel.readInt();
        Surface surface = (i & 32) == 0 ? null : (Surface) parcel.readTypedObject(Surface.CREATOR);
        String string2 = (i & 64) == 0 ? null : parcel.readString();
        int i6 = parcel.readInt();
        this.mName = string;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) string);
        this.mWidth = i2;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i2, TypedValues.TransitionType.S_FROM, 1L);
        this.mHeight = i3;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i3, TypedValues.TransitionType.S_FROM, 1L);
        this.mDensityDpi = i4;
        AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i4, TypedValues.TransitionType.S_FROM, 1L);
        this.mFlags = i5;
        this.mSurface = surface;
        this.mUniqueId = string2;
        this.mDisplayIdToMirror = i6;
    }

    public static final class Builder {
        private long mBuilderFieldsSet = 0;
        private int mDensityDpi;
        private int mDisplayIdToMirror;
        private int mFlags;
        private int mHeight;
        private String mName;
        private Surface mSurface;
        private String mUniqueId;
        private int mWidth;

        public Builder(String str, int i, int i2, int i3) {
            this.mName = str;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) str);
            this.mWidth = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, TypedValues.TransitionType.S_FROM, 1L);
            this.mHeight = i2;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i2, TypedValues.TransitionType.S_FROM, 1L);
            this.mDensityDpi = i3;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i3, TypedValues.TransitionType.S_FROM, 1L);
        }

        public Builder setName(String str) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 1;
            this.mName = str;
            return this;
        }

        public Builder setWidth(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 2;
            this.mWidth = i;
            return this;
        }

        public Builder setHeight(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 4;
            this.mHeight = i;
            return this;
        }

        public Builder setDensityDpi(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 8;
            this.mDensityDpi = i;
            return this;
        }

        public Builder setFlags(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 16;
            this.mFlags = i;
            return this;
        }

        public Builder setSurface(Surface surface) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 32;
            this.mSurface = surface;
            return this;
        }

        public Builder setUniqueId(String str) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 64;
            this.mUniqueId = str;
            return this;
        }

        public Builder setDisplayIdToMirror(int i) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 128;
            this.mDisplayIdToMirror = i;
            return this;
        }

        public VirtualDisplayConfig build() {
            checkNotUsed();
            long j = this.mBuilderFieldsSet | 256;
            this.mBuilderFieldsSet = j;
            if ((16 & j) == 0) {
                this.mFlags = 0;
            }
            if ((32 & j) == 0) {
                this.mSurface = null;
            }
            if ((64 & j) == 0) {
                this.mUniqueId = null;
            }
            if ((j & 128) == 0) {
                this.mDisplayIdToMirror = 0;
            }
            return new VirtualDisplayConfig(this.mName, this.mWidth, this.mHeight, this.mDensityDpi, this.mFlags, this.mSurface, this.mUniqueId, this.mDisplayIdToMirror);
        }

        private void checkNotUsed() {
            if ((this.mBuilderFieldsSet & 256) != 0) {
                throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
            }
        }
    }
}
