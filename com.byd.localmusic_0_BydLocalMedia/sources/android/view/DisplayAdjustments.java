package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class DisplayAdjustments {
    public static final DisplayAdjustments DEFAULT_DISPLAY_ADJUSTMENTS = new DisplayAdjustments();
    private volatile CompatibilityInfo mCompatInfo;
    private final Configuration mConfiguration;
    private FixedRotationAdjustments mFixedRotationAdjustments;

    public DisplayAdjustments() {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        this.mConfiguration = new Configuration(Configuration.EMPTY);
    }

    public DisplayAdjustments(Configuration configuration) {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        Configuration configuration2 = new Configuration(Configuration.EMPTY);
        this.mConfiguration = configuration2;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
    }

    public DisplayAdjustments(DisplayAdjustments displayAdjustments) {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        this.mConfiguration = new Configuration(Configuration.EMPTY);
        setCompatibilityInfo(displayAdjustments.mCompatInfo);
        this.mConfiguration.setTo(displayAdjustments.getConfiguration());
        this.mFixedRotationAdjustments = displayAdjustments.mFixedRotationAdjustments;
    }

    public void setCompatibilityInfo(CompatibilityInfo compatibilityInfo) {
        if (this == DEFAULT_DISPLAY_ADJUSTMENTS) {
            throw new IllegalArgumentException("setCompatbilityInfo: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
        }
        if (compatibilityInfo != null && (compatibilityInfo.isScalingRequired() || !compatibilityInfo.supportsScreen())) {
            this.mCompatInfo = compatibilityInfo;
        } else {
            this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        }
    }

    public CompatibilityInfo getCompatibilityInfo() {
        return this.mCompatInfo;
    }

    public void setConfiguration(Configuration configuration) {
        if (this == DEFAULT_DISPLAY_ADJUSTMENTS) {
            throw new IllegalArgumentException("setConfiguration: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
        }
        Configuration configuration2 = this.mConfiguration;
        if (configuration == null) {
            configuration = Configuration.EMPTY;
        }
        configuration2.setTo(configuration);
    }

    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    public void setFixedRotationAdjustments(FixedRotationAdjustments fixedRotationAdjustments) {
        this.mFixedRotationAdjustments = fixedRotationAdjustments;
    }

    public FixedRotationAdjustments getFixedRotationAdjustments() {
        return this.mFixedRotationAdjustments;
    }

    private boolean noFlip(int i) {
        FixedRotationAdjustments fixedRotationAdjustments = this.mFixedRotationAdjustments;
        return fixedRotationAdjustments == null || ((i - fixedRotationAdjustments.mRotation) + 4) % 2 == 0;
    }

    public void adjustSize(Point point, int i) {
        if (noFlip(i)) {
            return;
        }
        int i2 = point.x;
        point.x = point.y;
        point.y = i2;
    }

    public void adjustMetrics(DisplayMetrics displayMetrics, int i) {
        if (noFlip(i)) {
            return;
        }
        int i2 = displayMetrics.widthPixels;
        displayMetrics.widthPixels = displayMetrics.heightPixels;
        displayMetrics.heightPixels = i2;
        int i3 = displayMetrics.noncompatWidthPixels;
        displayMetrics.noncompatWidthPixels = displayMetrics.noncompatHeightPixels;
        displayMetrics.noncompatHeightPixels = i3;
    }

    public void adjustGlobalAppMetrics(DisplayMetrics displayMetrics) {
        FixedRotationAdjustments fixedRotationAdjustments = this.mFixedRotationAdjustments;
        if (fixedRotationAdjustments == null) {
            return;
        }
        int i = fixedRotationAdjustments.mAppWidth;
        displayMetrics.widthPixels = i;
        displayMetrics.noncompatWidthPixels = i;
        int i2 = fixedRotationAdjustments.mAppHeight;
        displayMetrics.heightPixels = i2;
        displayMetrics.noncompatHeightPixels = i2;
    }

    public DisplayCutout getDisplayCutout(DisplayCutout displayCutout) {
        FixedRotationAdjustments fixedRotationAdjustments = this.mFixedRotationAdjustments;
        return (fixedRotationAdjustments == null || fixedRotationAdjustments.mRotatedDisplayCutout == null) ? displayCutout : fixedRotationAdjustments.mRotatedDisplayCutout;
    }

    public int getRotation(int i) {
        FixedRotationAdjustments fixedRotationAdjustments = this.mFixedRotationAdjustments;
        return fixedRotationAdjustments != null ? fixedRotationAdjustments.mRotation : i;
    }

    public int hashCode() {
        return ((((527 + Objects.hashCode(this.mCompatInfo)) * 31) + Objects.hashCode(this.mConfiguration)) * 31) + Objects.hashCode(this.mFixedRotationAdjustments);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DisplayAdjustments)) {
            return false;
        }
        DisplayAdjustments displayAdjustments = (DisplayAdjustments) obj;
        return Objects.equals(displayAdjustments.mCompatInfo, this.mCompatInfo) && Objects.equals(displayAdjustments.mConfiguration, this.mConfiguration) && Objects.equals(displayAdjustments.mFixedRotationAdjustments, this.mFixedRotationAdjustments);
    }

    public static class FixedRotationAdjustments implements Parcelable {
        public static final Parcelable.Creator<FixedRotationAdjustments> CREATOR = new Parcelable.Creator<FixedRotationAdjustments>() { // from class: android.view.DisplayAdjustments.FixedRotationAdjustments.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FixedRotationAdjustments createFromParcel(Parcel parcel) {
                return new FixedRotationAdjustments(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FixedRotationAdjustments[] newArray(int i) {
                return new FixedRotationAdjustments[i];
            }
        };
        final int mAppHeight;
        final int mAppWidth;
        final DisplayCutout mRotatedDisplayCutout;
        final int mRotation;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public FixedRotationAdjustments(int i, int i2, int i3, DisplayCutout displayCutout) {
            this.mRotation = i;
            this.mAppWidth = i2;
            this.mAppHeight = i3;
            this.mRotatedDisplayCutout = displayCutout;
        }

        public int hashCode() {
            return ((((((527 + this.mRotation) * 31) + this.mAppWidth) * 31) + this.mAppHeight) * 31) + Objects.hashCode(this.mRotatedDisplayCutout);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof FixedRotationAdjustments)) {
                return false;
            }
            FixedRotationAdjustments fixedRotationAdjustments = (FixedRotationAdjustments) obj;
            return this.mRotation == fixedRotationAdjustments.mRotation && this.mAppWidth == fixedRotationAdjustments.mAppWidth && this.mAppHeight == fixedRotationAdjustments.mAppHeight && Objects.equals(this.mRotatedDisplayCutout, fixedRotationAdjustments.mRotatedDisplayCutout);
        }

        public String toString() {
            return "FixedRotationAdjustments{rotation=" + Surface.rotationToString(this.mRotation) + " appWidth=" + this.mAppWidth + " appHeight=" + this.mAppHeight + " cutout=" + this.mRotatedDisplayCutout + "}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mRotation);
            parcel.writeInt(this.mAppWidth);
            parcel.writeInt(this.mAppHeight);
            parcel.writeTypedObject(new DisplayCutout.ParcelableWrapper(this.mRotatedDisplayCutout), i);
        }

        private FixedRotationAdjustments(Parcel parcel) {
            this.mRotation = parcel.readInt();
            this.mAppWidth = parcel.readInt();
            this.mAppHeight = parcel.readInt();
            DisplayCutout.ParcelableWrapper parcelableWrapper = (DisplayCutout.ParcelableWrapper) parcel.readTypedObject(DisplayCutout.ParcelableWrapper.CREATOR);
            this.mRotatedDisplayCutout = parcelableWrapper != null ? parcelableWrapper.get() : null;
        }
    }
}
