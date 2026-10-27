package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.display.DeviceProductInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.proto.ProtoOutputStream;
import android.view.Display;
import android.view.DisplayCutout;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class DisplayInfo implements Parcelable {
    public static final Parcelable.Creator<DisplayInfo> CREATOR = new Parcelable.Creator<DisplayInfo>() { // from class: android.view.DisplayInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayInfo createFromParcel(Parcel parcel) {
            return new DisplayInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayInfo[] newArray(int i) {
            return new DisplayInfo[i];
        }
    };
    public DisplayAddress address;
    public int appHeight;
    public long appVsyncOffsetNanos;
    public int appWidth;
    public int colorMode;
    public int defaultModeId;
    public DeviceProductInfo deviceProductInfo;
    public DisplayCutout displayCutout;
    public int displayId;
    public int flags;
    public Display.HdrCapabilities hdrCapabilities;
    public int largestNominalAppHeight;
    public int largestNominalAppWidth;
    public int layerStack;
    public int logicalDensityDpi;
    public int logicalHeight;
    public int logicalWidth;
    public boolean minimalPostProcessingSupported;
    public int modeId;
    public String name;
    public String ownerPackageName;
    public int ownerUid;
    public float physicalXDpi;
    public float physicalYDpi;
    public long presentationDeadlineNanos;
    public int removeMode;
    public int rotation;
    public int smallestNominalAppHeight;
    public int smallestNominalAppWidth;
    public int state;
    public int[] supportedColorModes;
    public Display.Mode[] supportedModes;
    public int type;
    public String uniqueId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public DisplayInfo() {
        this.supportedModes = Display.Mode.EMPTY_ARRAY;
        this.supportedColorModes = new int[]{0};
        this.removeMode = 0;
    }

    public DisplayInfo(DisplayInfo displayInfo) {
        this.supportedModes = Display.Mode.EMPTY_ARRAY;
        this.supportedColorModes = new int[]{0};
        this.removeMode = 0;
        copyFrom(displayInfo);
    }

    private DisplayInfo(Parcel parcel) {
        this.supportedModes = Display.Mode.EMPTY_ARRAY;
        this.supportedColorModes = new int[]{0};
        this.removeMode = 0;
        readFromParcel(parcel);
    }

    public boolean equals(Object obj) {
        return (obj instanceof DisplayInfo) && equals((DisplayInfo) obj);
    }

    public boolean equals(DisplayInfo displayInfo) {
        return displayInfo != null && this.layerStack == displayInfo.layerStack && this.flags == displayInfo.flags && this.type == displayInfo.type && this.displayId == displayInfo.displayId && Objects.equals(this.address, displayInfo.address) && Objects.equals(this.deviceProductInfo, displayInfo.deviceProductInfo) && Objects.equals(this.uniqueId, displayInfo.uniqueId) && this.appWidth == displayInfo.appWidth && this.appHeight == displayInfo.appHeight && this.smallestNominalAppWidth == displayInfo.smallestNominalAppWidth && this.smallestNominalAppHeight == displayInfo.smallestNominalAppHeight && this.largestNominalAppWidth == displayInfo.largestNominalAppWidth && this.largestNominalAppHeight == displayInfo.largestNominalAppHeight && this.logicalWidth == displayInfo.logicalWidth && this.logicalHeight == displayInfo.logicalHeight && Objects.equals(this.displayCutout, displayInfo.displayCutout) && this.rotation == displayInfo.rotation && this.modeId == displayInfo.modeId && this.defaultModeId == displayInfo.defaultModeId && this.colorMode == displayInfo.colorMode && Arrays.equals(this.supportedColorModes, displayInfo.supportedColorModes) && Objects.equals(this.hdrCapabilities, displayInfo.hdrCapabilities) && this.minimalPostProcessingSupported == displayInfo.minimalPostProcessingSupported && this.logicalDensityDpi == displayInfo.logicalDensityDpi && this.physicalXDpi == displayInfo.physicalXDpi && this.physicalYDpi == displayInfo.physicalYDpi && this.appVsyncOffsetNanos == displayInfo.appVsyncOffsetNanos && this.presentationDeadlineNanos == displayInfo.presentationDeadlineNanos && this.state == displayInfo.state && this.ownerUid == displayInfo.ownerUid && Objects.equals(this.ownerPackageName, displayInfo.ownerPackageName) && this.removeMode == displayInfo.removeMode;
    }

    public void copyFrom(DisplayInfo displayInfo) {
        this.layerStack = displayInfo.layerStack;
        this.flags = displayInfo.flags;
        this.type = displayInfo.type;
        this.displayId = displayInfo.displayId;
        this.address = displayInfo.address;
        this.deviceProductInfo = displayInfo.deviceProductInfo;
        this.name = displayInfo.name;
        this.uniqueId = displayInfo.uniqueId;
        this.appWidth = displayInfo.appWidth;
        this.appHeight = displayInfo.appHeight;
        this.smallestNominalAppWidth = displayInfo.smallestNominalAppWidth;
        this.smallestNominalAppHeight = displayInfo.smallestNominalAppHeight;
        this.largestNominalAppWidth = displayInfo.largestNominalAppWidth;
        this.largestNominalAppHeight = displayInfo.largestNominalAppHeight;
        this.logicalWidth = displayInfo.logicalWidth;
        this.logicalHeight = displayInfo.logicalHeight;
        this.displayCutout = displayInfo.displayCutout;
        this.rotation = displayInfo.rotation;
        this.modeId = displayInfo.modeId;
        this.defaultModeId = displayInfo.defaultModeId;
        Display.Mode[] modeArr = displayInfo.supportedModes;
        this.supportedModes = (Display.Mode[]) Arrays.copyOf(modeArr, modeArr.length);
        this.colorMode = displayInfo.colorMode;
        int[] iArr = displayInfo.supportedColorModes;
        this.supportedColorModes = Arrays.copyOf(iArr, iArr.length);
        this.hdrCapabilities = displayInfo.hdrCapabilities;
        this.minimalPostProcessingSupported = displayInfo.minimalPostProcessingSupported;
        this.logicalDensityDpi = displayInfo.logicalDensityDpi;
        this.physicalXDpi = displayInfo.physicalXDpi;
        this.physicalYDpi = displayInfo.physicalYDpi;
        this.appVsyncOffsetNanos = displayInfo.appVsyncOffsetNanos;
        this.presentationDeadlineNanos = displayInfo.presentationDeadlineNanos;
        this.state = displayInfo.state;
        this.ownerUid = displayInfo.ownerUid;
        this.ownerPackageName = displayInfo.ownerPackageName;
        this.removeMode = displayInfo.removeMode;
    }

    public void readFromParcel(Parcel parcel) {
        this.layerStack = parcel.readInt();
        this.flags = parcel.readInt();
        this.type = parcel.readInt();
        this.displayId = parcel.readInt();
        this.address = (DisplayAddress) parcel.readParcelable(null);
        this.deviceProductInfo = (DeviceProductInfo) parcel.readParcelable(null);
        this.name = parcel.readString8();
        this.appWidth = parcel.readInt();
        this.appHeight = parcel.readInt();
        this.smallestNominalAppWidth = parcel.readInt();
        this.smallestNominalAppHeight = parcel.readInt();
        this.largestNominalAppWidth = parcel.readInt();
        this.largestNominalAppHeight = parcel.readInt();
        this.logicalWidth = parcel.readInt();
        this.logicalHeight = parcel.readInt();
        this.displayCutout = DisplayCutout.ParcelableWrapper.readCutoutFromParcel(parcel);
        this.rotation = parcel.readInt();
        this.modeId = parcel.readInt();
        this.defaultModeId = parcel.readInt();
        int i = parcel.readInt();
        this.supportedModes = new Display.Mode[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.supportedModes[i2] = Display.Mode.CREATOR.createFromParcel(parcel);
        }
        this.colorMode = parcel.readInt();
        int i3 = parcel.readInt();
        this.supportedColorModes = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            this.supportedColorModes[i4] = parcel.readInt();
        }
        this.hdrCapabilities = (Display.HdrCapabilities) parcel.readParcelable(null);
        this.minimalPostProcessingSupported = parcel.readBoolean();
        this.logicalDensityDpi = parcel.readInt();
        this.physicalXDpi = parcel.readFloat();
        this.physicalYDpi = parcel.readFloat();
        this.appVsyncOffsetNanos = parcel.readLong();
        this.presentationDeadlineNanos = parcel.readLong();
        this.state = parcel.readInt();
        this.ownerUid = parcel.readInt();
        this.ownerPackageName = parcel.readString8();
        this.uniqueId = parcel.readString8();
        this.removeMode = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.layerStack);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.type);
        parcel.writeInt(this.displayId);
        parcel.writeParcelable(this.address, i);
        parcel.writeParcelable(this.deviceProductInfo, i);
        parcel.writeString8(this.name);
        parcel.writeInt(this.appWidth);
        parcel.writeInt(this.appHeight);
        parcel.writeInt(this.smallestNominalAppWidth);
        parcel.writeInt(this.smallestNominalAppHeight);
        parcel.writeInt(this.largestNominalAppWidth);
        parcel.writeInt(this.largestNominalAppHeight);
        parcel.writeInt(this.logicalWidth);
        parcel.writeInt(this.logicalHeight);
        DisplayCutout.ParcelableWrapper.writeCutoutToParcel(this.displayCutout, parcel, i);
        parcel.writeInt(this.rotation);
        parcel.writeInt(this.modeId);
        parcel.writeInt(this.defaultModeId);
        parcel.writeInt(this.supportedModes.length);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            Display.Mode[] modeArr = this.supportedModes;
            if (i3 >= modeArr.length) {
                break;
            }
            modeArr[i3].writeToParcel(parcel, i);
            i3++;
        }
        parcel.writeInt(this.colorMode);
        parcel.writeInt(this.supportedColorModes.length);
        while (true) {
            int[] iArr = this.supportedColorModes;
            if (i2 < iArr.length) {
                parcel.writeInt(iArr[i2]);
                i2++;
            } else {
                parcel.writeParcelable(this.hdrCapabilities, i);
                parcel.writeBoolean(this.minimalPostProcessingSupported);
                parcel.writeInt(this.logicalDensityDpi);
                parcel.writeFloat(this.physicalXDpi);
                parcel.writeFloat(this.physicalYDpi);
                parcel.writeLong(this.appVsyncOffsetNanos);
                parcel.writeLong(this.presentationDeadlineNanos);
                parcel.writeInt(this.state);
                parcel.writeInt(this.ownerUid);
                parcel.writeString8(this.ownerPackageName);
                parcel.writeString8(this.uniqueId);
                parcel.writeInt(this.removeMode);
                return;
            }
        }
    }

    public Display.Mode getMode() {
        return findMode(this.modeId);
    }

    public Display.Mode getDefaultMode() {
        return findMode(this.defaultModeId);
    }

    private Display.Mode findMode(int i) {
        int i2 = 0;
        while (true) {
            Display.Mode[] modeArr = this.supportedModes;
            if (i2 < modeArr.length) {
                if (modeArr[i2].getModeId() == i) {
                    return this.supportedModes[i2];
                }
                i2++;
            } else {
                throw new IllegalStateException("Unable to locate mode " + i);
            }
        }
    }

    public int findDefaultModeByRefreshRate(float f) {
        Display.Mode[] modeArr = this.supportedModes;
        Display.Mode defaultMode = getDefaultMode();
        for (int i = 0; i < modeArr.length; i++) {
            if (modeArr[i].matches(defaultMode.getPhysicalWidth(), defaultMode.getPhysicalHeight(), f)) {
                return modeArr[i].getModeId();
            }
        }
        return 0;
    }

    public float[] getDefaultRefreshRates() {
        Display.Mode[] modeArr = this.supportedModes;
        ArraySet arraySet = new ArraySet();
        Display.Mode defaultMode = getDefaultMode();
        int i = 0;
        for (Display.Mode mode : modeArr) {
            if (mode.getPhysicalWidth() == defaultMode.getPhysicalWidth() && mode.getPhysicalHeight() == defaultMode.getPhysicalHeight()) {
                arraySet.add(Float.valueOf(mode.getRefreshRate()));
            }
        }
        float[] fArr = new float[arraySet.size()];
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            fArr[i] = ((Float) it.next()).floatValue();
            i++;
        }
        return fArr;
    }

    public void getAppMetrics(DisplayMetrics displayMetrics) {
        getAppMetrics(displayMetrics, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO, null);
    }

    public void getAppMetrics(DisplayMetrics displayMetrics, DisplayAdjustments displayAdjustments) {
        getMetricsWithSize(displayMetrics, displayAdjustments.getCompatibilityInfo(), displayAdjustments.getConfiguration(), this.appWidth, this.appHeight);
    }

    public void getAppMetrics(DisplayMetrics displayMetrics, CompatibilityInfo compatibilityInfo, Configuration configuration) {
        getMetricsWithSize(displayMetrics, compatibilityInfo, configuration, this.appWidth, this.appHeight);
    }

    public void getLogicalMetrics(DisplayMetrics displayMetrics, CompatibilityInfo compatibilityInfo, Configuration configuration) {
        getMetricsWithSize(displayMetrics, compatibilityInfo, configuration, this.logicalWidth, this.logicalHeight);
    }

    public int getNaturalWidth() {
        int i = this.rotation;
        return (i == 0 || i == 2) ? this.logicalWidth : this.logicalHeight;
    }

    public int getNaturalHeight() {
        int i = this.rotation;
        return (i == 0 || i == 2) ? this.logicalHeight : this.logicalWidth;
    }

    public boolean isHdr() {
        Display.HdrCapabilities hdrCapabilities = this.hdrCapabilities;
        int[] supportedHdrTypes = hdrCapabilities != null ? hdrCapabilities.getSupportedHdrTypes() : null;
        return supportedHdrTypes != null && supportedHdrTypes.length > 0;
    }

    public boolean isWideColorGamut() {
        for (int i : this.supportedColorModes) {
            if (i == 6 || i > 7) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAccess(int i) {
        return Display.hasAccess(i, this.flags, this.ownerUid, this.displayId);
    }

    private void getMetricsWithSize(DisplayMetrics displayMetrics, CompatibilityInfo compatibilityInfo, Configuration configuration, int i, int i2) {
        int i3 = this.logicalDensityDpi;
        displayMetrics.noncompatDensityDpi = i3;
        displayMetrics.densityDpi = i3;
        float f = this.logicalDensityDpi * 0.00625f;
        displayMetrics.noncompatDensity = f;
        displayMetrics.density = f;
        float f2 = displayMetrics.density;
        displayMetrics.noncompatScaledDensity = f2;
        displayMetrics.scaledDensity = f2;
        float f3 = this.physicalXDpi;
        displayMetrics.noncompatXdpi = f3;
        displayMetrics.xdpi = f3;
        float f4 = this.physicalYDpi;
        displayMetrics.noncompatYdpi = f4;
        displayMetrics.ydpi = f4;
        Rect appBounds = configuration != null ? configuration.windowConfiguration.getAppBounds() : null;
        if (appBounds != null) {
            i = appBounds.width();
        }
        if (appBounds != null) {
            i2 = appBounds.height();
        }
        displayMetrics.widthPixels = i;
        displayMetrics.noncompatWidthPixels = i;
        displayMetrics.heightPixels = i2;
        displayMetrics.noncompatHeightPixels = i2;
        if (compatibilityInfo.equals(CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO)) {
            return;
        }
        compatibilityInfo.applyToDisplayMetrics(displayMetrics);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DisplayInfo{\"");
        sb.append(this.name);
        sb.append("\", displayId ");
        sb.append(this.displayId);
        sb.append(flagsToString(this.flags));
        sb.append(", real ");
        sb.append(this.logicalWidth);
        sb.append(" x ");
        sb.append(this.logicalHeight);
        sb.append(", largest app ");
        sb.append(this.largestNominalAppWidth);
        sb.append(" x ");
        sb.append(this.largestNominalAppHeight);
        sb.append(", smallest app ");
        sb.append(this.smallestNominalAppWidth);
        sb.append(" x ");
        sb.append(this.smallestNominalAppHeight);
        sb.append(", appVsyncOff ");
        sb.append(this.appVsyncOffsetNanos);
        sb.append(", presDeadline ");
        sb.append(this.presentationDeadlineNanos);
        sb.append(", mode ");
        sb.append(this.modeId);
        sb.append(", defaultMode ");
        sb.append(this.defaultModeId);
        sb.append(", modes ");
        sb.append(Arrays.toString(this.supportedModes));
        sb.append(", hdrCapabilities ");
        sb.append(this.hdrCapabilities);
        sb.append(", minimalPostProcessingSupported ");
        sb.append(this.minimalPostProcessingSupported);
        sb.append(", rotation ");
        sb.append(this.rotation);
        sb.append(", state ");
        sb.append(Display.stateToString(this.state));
        if (Process.myUid() != 1000) {
            sb.append("}");
            return sb.toString();
        }
        sb.append(", type ");
        sb.append(Display.typeToString(this.type));
        sb.append(", uniqueId \"");
        sb.append(this.uniqueId);
        sb.append("\", app ");
        sb.append(this.appWidth);
        sb.append(" x ");
        sb.append(this.appHeight);
        sb.append(", density ");
        sb.append(this.logicalDensityDpi);
        sb.append(" (");
        sb.append(this.physicalXDpi);
        sb.append(" x ");
        sb.append(this.physicalYDpi);
        sb.append(") dpi, layerStack ");
        sb.append(this.layerStack);
        sb.append(", colorMode ");
        sb.append(this.colorMode);
        sb.append(", supportedColorModes ");
        sb.append(Arrays.toString(this.supportedColorModes));
        if (this.address != null) {
            sb.append(", address ");
            sb.append(this.address);
        }
        sb.append(", deviceProductInfo ");
        sb.append(this.deviceProductInfo);
        if (this.ownerUid != 0 || this.ownerPackageName != null) {
            sb.append(", owner ");
            sb.append(this.ownerPackageName);
            sb.append(" (uid ");
            sb.append(this.ownerUid);
            sb.append(")");
        }
        sb.append(", removeMode ");
        sb.append(this.removeMode);
        sb.append("}");
        return sb.toString();
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1120986464257L, this.logicalWidth);
        protoOutputStream.write(1120986464258L, this.logicalHeight);
        protoOutputStream.write(1120986464259L, this.appWidth);
        protoOutputStream.write(1120986464260L, this.appHeight);
        protoOutputStream.write(1138166333445L, this.name);
        protoOutputStream.write(1120986464262L, this.flags);
        protoOutputStream.end(jStart);
    }

    private static String flagsToString(int i) {
        StringBuilder sb = new StringBuilder();
        if ((i & 2) != 0) {
            sb.append(", FLAG_SECURE");
        }
        if ((i & 1) != 0) {
            sb.append(", FLAG_SUPPORTS_PROTECTED_BUFFERS");
        }
        if ((i & 4) != 0) {
            sb.append(", FLAG_PRIVATE");
        }
        if ((i & 8) != 0) {
            sb.append(", FLAG_PRESENTATION");
        }
        if ((1073741824 & i) != 0) {
            sb.append(", FLAG_SCALING_DISABLED");
        }
        if ((i & 16) != 0) {
            sb.append(", FLAG_ROUND");
        }
        if ((i & 32) != 0) {
            sb.append(", FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD");
        }
        if ((i & 64) != 0) {
            sb.append(", FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS");
        }
        if ((i & 128) != 0) {
            sb.append(", FLAG_TRUSTED");
        }
        return sb.toString();
    }
}
