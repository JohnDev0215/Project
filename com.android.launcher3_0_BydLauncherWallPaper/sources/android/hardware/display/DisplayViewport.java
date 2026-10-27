package android.hardware.display;

import android.graphics.Rect;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public final class DisplayViewport {
    public static final int VIEWPORT_EXTERNAL = 2;
    public static final int VIEWPORT_INTERNAL = 1;
    public static final int VIEWPORT_VIRTUAL = 3;
    public int deviceHeight;
    public int deviceWidth;
    public int displayId;
    public boolean isActive;
    public int orientation;
    public Byte physicalPort;
    public int type;
    public String uniqueId;
    public boolean valid;
    public final Rect logicalFrame = new Rect();
    public final Rect physicalFrame = new Rect();

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewportType {
    }

    public void copyFrom(DisplayViewport displayViewport) {
        this.valid = displayViewport.valid;
        this.isActive = displayViewport.isActive;
        this.displayId = displayViewport.displayId;
        this.orientation = displayViewport.orientation;
        this.logicalFrame.set(displayViewport.logicalFrame);
        this.physicalFrame.set(displayViewport.physicalFrame);
        this.deviceWidth = displayViewport.deviceWidth;
        this.deviceHeight = displayViewport.deviceHeight;
        this.uniqueId = displayViewport.uniqueId;
        this.physicalPort = displayViewport.physicalPort;
        this.type = displayViewport.type;
    }

    public DisplayViewport makeCopy() {
        DisplayViewport displayViewport = new DisplayViewport();
        displayViewport.copyFrom(this);
        return displayViewport;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DisplayViewport)) {
            return false;
        }
        DisplayViewport displayViewport = (DisplayViewport) obj;
        return this.valid == displayViewport.valid && this.isActive == displayViewport.isActive && this.displayId == displayViewport.displayId && this.orientation == displayViewport.orientation && this.logicalFrame.equals(displayViewport.logicalFrame) && this.physicalFrame.equals(displayViewport.physicalFrame) && this.deviceWidth == displayViewport.deviceWidth && this.deviceHeight == displayViewport.deviceHeight && TextUtils.equals(this.uniqueId, displayViewport.uniqueId) && this.physicalPort == displayViewport.physicalPort && this.type == displayViewport.type;
    }

    public int hashCode() {
        int i = 31 + (this.valid ? 1 : 0) + 1;
        int i2 = i + (i * 31) + (this.isActive ? 1 : 0);
        int i3 = i2 + (i2 * 31) + this.displayId;
        int i4 = i3 + (i3 * 31) + this.orientation;
        int iHashCode = i4 + (i4 * 31) + this.logicalFrame.hashCode();
        int iHashCode2 = iHashCode + (iHashCode * 31) + this.physicalFrame.hashCode();
        int i5 = iHashCode2 + (iHashCode2 * 31) + this.deviceWidth;
        int i6 = i5 + (i5 * 31) + this.deviceHeight;
        int iHashCode3 = i6 + (i6 * 31) + this.uniqueId.hashCode();
        Byte b = this.physicalPort;
        if (b != null) {
            iHashCode3 += (iHashCode3 * 31) + b.hashCode();
        }
        return iHashCode3 + (iHashCode3 * 31) + this.type;
    }

    public String toString() {
        Byte b = this.physicalPort;
        return "DisplayViewport{type=" + typeToString(this.type) + ", valid=" + this.valid + ", isActive=" + this.isActive + ", displayId=" + this.displayId + ", uniqueId='" + this.uniqueId + "', physicalPort=" + (b == null ? null : Integer.valueOf(Byte.toUnsignedInt(b.byteValue()))) + ", orientation=" + this.orientation + ", logicalFrame=" + this.logicalFrame + ", physicalFrame=" + this.physicalFrame + ", deviceWidth=" + this.deviceWidth + ", deviceHeight=" + this.deviceHeight + "}";
    }

    public static String typeToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN (" + i + ")" : "VIRTUAL" : "EXTERNAL" : "INTERNAL";
    }
}
