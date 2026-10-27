package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class ServiceInfo extends ComponentInfo implements Parcelable {
    public static final Parcelable.Creator<ServiceInfo> CREATOR = new Parcelable.Creator<ServiceInfo>() { // from class: android.content.pm.ServiceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo createFromParcel(Parcel parcel) {
            return new ServiceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceInfo[] newArray(int i) {
            return new ServiceInfo[i];
        }
    };
    public static final int FLAG_EXTERNAL_SERVICE = 4;
    public static final int FLAG_ISOLATED_PROCESS = 2;
    public static final int FLAG_SINGLE_USER = 1073741824;
    public static final int FLAG_STOP_WITH_TASK = 1;
    public static final int FLAG_USE_APP_ZYGOTE = 8;
    public static final int FLAG_VISIBLE_TO_INSTANT_APP = 1048576;
    public static final int FOREGROUND_SERVICE_TYPE_CAMERA = 64;
    public static final int FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE = 16;
    public static final int FOREGROUND_SERVICE_TYPE_DATA_SYNC = 1;
    public static final int FOREGROUND_SERVICE_TYPE_LOCATION = 8;
    public static final int FOREGROUND_SERVICE_TYPE_MANIFEST = -1;
    public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK = 2;
    public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION = 32;
    public static final int FOREGROUND_SERVICE_TYPE_MICROPHONE = 128;
    public static final int FOREGROUND_SERVICE_TYPE_NONE = 0;
    public static final int FOREGROUND_SERVICE_TYPE_PHONE_CALL = 4;
    public int flags;
    public int mForegroundServiceType;
    public String permission;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ForegroundServiceType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ServiceInfo() {
        this.mForegroundServiceType = 0;
    }

    public ServiceInfo(ServiceInfo serviceInfo) {
        super(serviceInfo);
        this.mForegroundServiceType = 0;
        this.permission = serviceInfo.permission;
        this.flags = serviceInfo.flags;
        this.mForegroundServiceType = serviceInfo.mForegroundServiceType;
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    public void dump(Printer printer, String str) {
        dump(printer, str, 3);
    }

    void dump(Printer printer, String str, int i) {
        super.dumpFront(printer, str);
        printer.println(str + "permission=" + this.permission);
        printer.println(str + "flags=0x" + Integer.toHexString(this.flags));
        super.dumpBack(printer, str, i);
    }

    public String toString() {
        return "ServiceInfo{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.name + "}";
    }

    @Override // android.content.pm.ComponentInfo, android.content.pm.PackageItemInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString8(this.permission);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.mForegroundServiceType);
    }

    private ServiceInfo(Parcel parcel) {
        super(parcel);
        this.mForegroundServiceType = 0;
        this.permission = parcel.readString8();
        this.flags = parcel.readInt();
        this.mForegroundServiceType = parcel.readInt();
    }
}
