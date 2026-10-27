package android.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.proto.ProtoInputStream;
import android.util.proto.ProtoOutputStream;
import android.util.proto.WireTypeMismatchException;
import android.view.Surface;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class WindowConfiguration implements Parcelable, Comparable<WindowConfiguration> {
    public static final int ACTIVITY_TYPE_ASSISTANT = 4;
    public static final int ACTIVITY_TYPE_DREAM = 5;
    public static final int ACTIVITY_TYPE_HOME = 2;
    public static final int ACTIVITY_TYPE_RECENTS = 3;
    public static final int ACTIVITY_TYPE_STANDARD = 1;
    public static final int ACTIVITY_TYPE_UNDEFINED = 0;
    private static final int ALWAYS_ON_TOP_OFF = 2;
    private static final int ALWAYS_ON_TOP_ON = 1;
    private static final int ALWAYS_ON_TOP_UNDEFINED = 0;
    public static final Parcelable.Creator<WindowConfiguration> CREATOR = new Parcelable.Creator<WindowConfiguration>() { // from class: android.app.WindowConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowConfiguration createFromParcel(Parcel parcel) {
            return new WindowConfiguration(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WindowConfiguration[] newArray(int i) {
            return new WindowConfiguration[i];
        }
    };
    public static final int PINNED_WINDOWING_MODE_ELEVATION_IN_DIP = 5;
    public static final int ROTATION_UNDEFINED = -1;
    public static final int WINDOWING_MODE_FREEFORM = 5;
    public static final int WINDOWING_MODE_FULLSCREEN = 1;
    public static final int WINDOWING_MODE_FULLSCREEN_OR_SPLIT_SCREEN_SECONDARY = 4;
    public static final int WINDOWING_MODE_MULTI_WINDOW = 6;
    public static final int WINDOWING_MODE_PINNED = 2;
    public static final int WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 3;
    public static final int WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 4;
    public static final int WINDOWING_MODE_UNDEFINED = 0;
    public static final int WINDOW_CONFIG_ACTIVITY_TYPE = 8;
    public static final int WINDOW_CONFIG_ALWAYS_ON_TOP = 16;
    public static final int WINDOW_CONFIG_APP_BOUNDS = 2;
    public static final int WINDOW_CONFIG_BOUNDS = 1;
    public static final int WINDOW_CONFIG_DISPLAY_WINDOWING_MODE = 64;
    public static final int WINDOW_CONFIG_ROTATION = 32;
    public static final int WINDOW_CONFIG_WINDOWING_MODE = 4;
    private int mActivityType;
    private int mAlwaysOnTop;
    private Rect mAppBounds;
    private Rect mBounds;
    private int mDisplayWindowingMode;
    private int mRotation;
    private int mWindowingMode;

    public @interface ActivityType {
    }

    private @interface AlwaysOnTop {
    }

    public @interface WindowConfig {
    }

    public @interface WindowingMode {
    }

    public static boolean inMultiWindowMode(int i) {
        return (i == 1 || i == 0) ? false : true;
    }

    public static boolean isFloating(int i) {
        return i == 5 || i == 2;
    }

    public static boolean isSplitScreenWindowingMode(int i) {
        return i == 3 || i == 4;
    }

    public static boolean supportSplitScreenWindowingMode(int i) {
        return (i == 4 || i == 5) ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WindowConfiguration() {
        this.mBounds = new Rect();
        this.mRotation = -1;
        unset();
    }

    public WindowConfiguration(WindowConfiguration windowConfiguration) {
        this.mBounds = new Rect();
        this.mRotation = -1;
        setTo(windowConfiguration);
    }

    private WindowConfiguration(Parcel parcel) {
        this.mBounds = new Rect();
        this.mRotation = -1;
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mBounds, i);
        parcel.writeParcelable(this.mAppBounds, i);
        parcel.writeInt(this.mWindowingMode);
        parcel.writeInt(this.mActivityType);
        parcel.writeInt(this.mAlwaysOnTop);
        parcel.writeInt(this.mRotation);
        parcel.writeInt(this.mDisplayWindowingMode);
    }

    private void readFromParcel(Parcel parcel) {
        this.mBounds = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
        this.mAppBounds = (Rect) parcel.readParcelable(Rect.class.getClassLoader());
        this.mWindowingMode = parcel.readInt();
        this.mActivityType = parcel.readInt();
        this.mAlwaysOnTop = parcel.readInt();
        this.mRotation = parcel.readInt();
        this.mDisplayWindowingMode = parcel.readInt();
    }

    public void setBounds(Rect rect) {
        if (rect == null) {
            this.mBounds.setEmpty();
        } else {
            this.mBounds.set(rect);
        }
    }

    public void setAppBounds(Rect rect) {
        if (rect == null) {
            this.mAppBounds = null;
        } else {
            setAppBounds(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    public void setAlwaysOnTop(boolean z) {
        this.mAlwaysOnTop = z ? 1 : 2;
    }

    private void setAlwaysOnTop(int i) {
        this.mAlwaysOnTop = i;
    }

    public void setAppBounds(int i, int i2, int i3, int i4) {
        if (this.mAppBounds == null) {
            this.mAppBounds = new Rect();
        }
        this.mAppBounds.set(i, i2, i3, i4);
    }

    public Rect getAppBounds() {
        return this.mAppBounds;
    }

    public Rect getBounds() {
        return this.mBounds;
    }

    public int getRotation() {
        return this.mRotation;
    }

    public void setRotation(int i) {
        this.mRotation = i;
    }

    public void setWindowingMode(int i) {
        this.mWindowingMode = i;
    }

    public int getWindowingMode() {
        return this.mWindowingMode;
    }

    public void setDisplayWindowingMode(int i) {
        this.mDisplayWindowingMode = i;
    }

    public int getDisplayWindowingMode() {
        return this.mDisplayWindowingMode;
    }

    public void setActivityType(int i) {
        int i2;
        if (this.mActivityType == i) {
            return;
        }
        if (ActivityThread.isSystem() && (i2 = this.mActivityType) != 0 && i != 0 && i != 2 && i2 != 2) {
            throw new IllegalStateException("Can't change activity type once set: " + this + " activityType=" + activityTypeToString(i));
        }
        this.mActivityType = i;
    }

    public int getActivityType() {
        return this.mActivityType;
    }

    public void setTo(WindowConfiguration windowConfiguration) {
        setBounds(windowConfiguration.mBounds);
        setAppBounds(windowConfiguration.mAppBounds);
        setWindowingMode(windowConfiguration.mWindowingMode);
        setActivityType(windowConfiguration.mActivityType);
        setAlwaysOnTop(windowConfiguration.mAlwaysOnTop);
        setRotation(windowConfiguration.mRotation);
        setDisplayWindowingMode(windowConfiguration.mDisplayWindowingMode);
    }

    public void unset() {
        setToDefaults();
    }

    public void setToDefaults() {
        setAppBounds(null);
        setBounds(null);
        setWindowingMode(0);
        setActivityType(0);
        setAlwaysOnTop(0);
        setRotation(-1);
        setDisplayWindowingMode(0);
    }

    public int updateFrom(WindowConfiguration windowConfiguration) {
        int i;
        if (windowConfiguration.mBounds.isEmpty() || windowConfiguration.mBounds.equals(this.mBounds)) {
            i = 0;
        } else {
            i = 1;
            setBounds(windowConfiguration.mBounds);
        }
        Rect rect = windowConfiguration.mAppBounds;
        if (rect != null && !rect.equals(this.mAppBounds)) {
            i |= 2;
            setAppBounds(windowConfiguration.mAppBounds);
        }
        int i2 = windowConfiguration.mWindowingMode;
        if (i2 != 0 && this.mWindowingMode != i2) {
            i |= 4;
            setWindowingMode(i2);
        }
        int i3 = windowConfiguration.mActivityType;
        if (i3 != 0 && this.mActivityType != i3) {
            i |= 8;
            setActivityType(i3);
        }
        int i4 = windowConfiguration.mAlwaysOnTop;
        if (i4 != 0 && this.mAlwaysOnTop != i4) {
            i |= 16;
            setAlwaysOnTop(i4);
        }
        int i5 = windowConfiguration.mRotation;
        if (i5 != -1 && i5 != this.mRotation) {
            i |= 32;
            setRotation(i5);
        }
        int i6 = windowConfiguration.mDisplayWindowingMode;
        if (i6 == 0 || this.mDisplayWindowingMode == i6) {
            return i;
        }
        int i7 = i | 64;
        setDisplayWindowingMode(i6);
        return i7;
    }

    public void setTo(WindowConfiguration windowConfiguration, int i) {
        if ((i & 1) != 0) {
            setBounds(windowConfiguration.mBounds);
        }
        if ((i & 2) != 0) {
            setAppBounds(windowConfiguration.mAppBounds);
        }
        if ((i & 4) != 0) {
            setWindowingMode(windowConfiguration.mWindowingMode);
        }
        if ((i & 8) != 0) {
            setActivityType(windowConfiguration.mActivityType);
        }
        if ((i & 16) != 0) {
            setAlwaysOnTop(windowConfiguration.mAlwaysOnTop);
        }
        if ((i & 32) != 0) {
            setRotation(windowConfiguration.mRotation);
        }
        if ((i & 64) != 0) {
            setDisplayWindowingMode(windowConfiguration.mDisplayWindowingMode);
        }
    }

    public long diff(WindowConfiguration windowConfiguration, boolean z) {
        Rect rect;
        Rect rect2;
        long j = !this.mBounds.equals(windowConfiguration.mBounds) ? 1L : 0L;
        if ((z || windowConfiguration.mAppBounds != null) && (rect = this.mAppBounds) != (rect2 = windowConfiguration.mAppBounds) && (rect == null || !rect.equals(rect2))) {
            j |= 2;
        }
        if ((z || windowConfiguration.mWindowingMode != 0) && this.mWindowingMode != windowConfiguration.mWindowingMode) {
            j |= 4;
        }
        if ((z || windowConfiguration.mActivityType != 0) && this.mActivityType != windowConfiguration.mActivityType) {
            j |= 8;
        }
        if ((z || windowConfiguration.mAlwaysOnTop != 0) && this.mAlwaysOnTop != windowConfiguration.mAlwaysOnTop) {
            j |= 16;
        }
        if ((z || windowConfiguration.mRotation != -1) && this.mRotation != windowConfiguration.mRotation) {
            j |= 32;
        }
        return ((z || windowConfiguration.mDisplayWindowingMode != 0) && this.mDisplayWindowingMode != windowConfiguration.mDisplayWindowingMode) ? j | 64 : j;
    }

    @Override // java.lang.Comparable
    public int compareTo(WindowConfiguration windowConfiguration) {
        Rect rect = this.mAppBounds;
        if (rect == null && windowConfiguration.mAppBounds != null) {
            return 1;
        }
        if (rect != null && windowConfiguration.mAppBounds == null) {
            return -1;
        }
        if (rect != null && windowConfiguration.mAppBounds != null) {
            int i = rect.left - windowConfiguration.mAppBounds.left;
            if (i != 0) {
                return i;
            }
            int i2 = this.mAppBounds.top - windowConfiguration.mAppBounds.top;
            if (i2 != 0) {
                return i2;
            }
            int i3 = this.mAppBounds.right - windowConfiguration.mAppBounds.right;
            if (i3 != 0) {
                return i3;
            }
            int i4 = this.mAppBounds.bottom - windowConfiguration.mAppBounds.bottom;
            if (i4 != 0) {
                return i4;
            }
        }
        int i5 = this.mBounds.left - windowConfiguration.mBounds.left;
        if (i5 != 0) {
            return i5;
        }
        int i6 = this.mBounds.top - windowConfiguration.mBounds.top;
        if (i6 != 0) {
            return i6;
        }
        int i7 = this.mBounds.right - windowConfiguration.mBounds.right;
        if (i7 != 0) {
            return i7;
        }
        int i8 = this.mBounds.bottom - windowConfiguration.mBounds.bottom;
        if (i8 != 0) {
            return i8;
        }
        int i9 = this.mWindowingMode - windowConfiguration.mWindowingMode;
        if (i9 != 0) {
            return i9;
        }
        int i10 = this.mActivityType - windowConfiguration.mActivityType;
        if (i10 != 0) {
            return i10;
        }
        int i11 = this.mAlwaysOnTop - windowConfiguration.mAlwaysOnTop;
        if (i11 != 0) {
            return i11;
        }
        int i12 = this.mRotation - windowConfiguration.mRotation;
        if (i12 != 0) {
            return i12;
        }
        int i13 = this.mDisplayWindowingMode - windowConfiguration.mDisplayWindowingMode;
        if (i13 != 0) {
        }
        return i13;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof WindowConfiguration) && compareTo((WindowConfiguration) obj) == 0;
    }

    public int hashCode() {
        Rect rect = this.mAppBounds;
        return ((((((((((((rect != null ? 0 + rect.hashCode() : 0) * 31) + this.mBounds.hashCode()) * 31) + this.mWindowingMode) * 31) + this.mActivityType) * 31) + this.mAlwaysOnTop) * 31) + this.mRotation) * 31) + this.mDisplayWindowingMode;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("{ mBounds=").append(this.mBounds).append(" mAppBounds=").append(this.mAppBounds).append(" mWindowingMode=").append(windowingModeToString(this.mWindowingMode)).append(" mDisplayWindowingMode=").append(windowingModeToString(this.mDisplayWindowingMode)).append(" mActivityType=").append(activityTypeToString(this.mActivityType)).append(" mAlwaysOnTop=").append(alwaysOnTopToString(this.mAlwaysOnTop)).append(" mRotation=");
        int i = this.mRotation;
        return sbAppend.append(i == -1 ? "undefined" : Surface.rotationToString(i)).append("}").toString();
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        Rect rect = this.mAppBounds;
        if (rect != null) {
            rect.dumpDebug(protoOutputStream, 1146756268033L);
        }
        protoOutputStream.write(1120986464258L, this.mWindowingMode);
        protoOutputStream.write(1120986464259L, this.mActivityType);
        Rect rect2 = this.mBounds;
        if (rect2 != null) {
            rect2.dumpDebug(protoOutputStream, 1146756268036L);
        }
        protoOutputStream.end(jStart);
    }

    public void readFromProto(ProtoInputStream protoInputStream, long j) throws WireTypeMismatchException, IOException {
        long jStart = protoInputStream.start(j);
        while (protoInputStream.nextField() != -1) {
            try {
                int fieldNumber = protoInputStream.getFieldNumber();
                if (fieldNumber == 1) {
                    Rect rect = new Rect();
                    this.mAppBounds = rect;
                    rect.readFromProto(protoInputStream, 1146756268033L);
                } else if (fieldNumber == 2) {
                    this.mWindowingMode = protoInputStream.readInt(1120986464258L);
                } else if (fieldNumber == 3) {
                    this.mActivityType = protoInputStream.readInt(1120986464259L);
                } else if (fieldNumber == 4) {
                    Rect rect2 = new Rect();
                    this.mBounds = rect2;
                    rect2.readFromProto(protoInputStream, 1146756268036L);
                }
            } finally {
                protoInputStream.end(jStart);
            }
        }
    }

    public boolean hasWindowShadow() {
        return this.mWindowingMode != 6 && tasksAreFloating();
    }

    public boolean hasWindowDecorCaption() {
        return this.mActivityType == 1 && (this.mWindowingMode == 5 || this.mDisplayWindowingMode == 5);
    }

    public boolean canResizeTask() {
        int i = this.mWindowingMode;
        return i == 5 || i == 6;
    }

    public boolean persistTaskBounds() {
        return this.mWindowingMode == 5;
    }

    public boolean tasksAreFloating() {
        return isFloating(this.mWindowingMode);
    }

    public boolean canReceiveKeys() {
        return this.mWindowingMode != 2;
    }

    public boolean isAlwaysOnTop() {
        int i = this.mWindowingMode;
        if (i == 2 || this.mActivityType == 5) {
            return true;
        }
        if (this.mAlwaysOnTop != 1) {
            return false;
        }
        return i == 5 || i == 6;
    }

    public boolean keepVisibleDeadAppWindowOnScreen() {
        return this.mWindowingMode != 2;
    }

    public boolean useWindowFrameForBackdrop() {
        int i = this.mWindowingMode;
        return i == 5 || i == 2;
    }

    public boolean windowsAreScaleable() {
        return this.mWindowingMode == 2;
    }

    public boolean hasMovementAnimations() {
        return this.mWindowingMode != 2;
    }

    public boolean supportSplitScreenWindowingMode() {
        return supportSplitScreenWindowingMode(this.mActivityType);
    }

    public static String windowingModeToString(int i) {
        switch (i) {
            case 0:
                return "undefined";
            case 1:
                return "fullscreen";
            case 2:
                return ContactsContract.ContactOptionsColumns.PINNED;
            case 3:
                return "split-screen-primary";
            case 4:
                return "split-screen-secondary";
            case 5:
                return "freeform";
            case 6:
                return "multi-window";
            default:
                return String.valueOf(i);
        }
    }

    public static String activityTypeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : Context.DREAM_SERVICE : Settings.Secure.ASSISTANT : "recents" : CalendarContract.CalendarCache.TIMEZONE_TYPE_HOME : "standard" : "undefined";
    }

    public static String alwaysOnTopToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? String.valueOf(i) : "off" : "on" : "undefined";
    }
}
