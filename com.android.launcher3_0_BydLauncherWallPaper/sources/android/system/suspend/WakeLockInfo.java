package android.system.suspend;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class WakeLockInfo implements Parcelable {
    public static final Parcelable.Creator<WakeLockInfo> CREATOR = new Parcelable.Creator<WakeLockInfo>() { // from class: android.system.suspend.WakeLockInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeLockInfo createFromParcel(Parcel parcel) {
            WakeLockInfo wakeLockInfo = new WakeLockInfo();
            wakeLockInfo.readFromParcel(parcel);
            return wakeLockInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeLockInfo[] newArray(int i) {
            return new WakeLockInfo[i];
        }
    };
    public long activeCount;
    public long activeTime;
    public long eventCount;
    public long expireCount;
    public boolean isActive;
    public boolean isKernelWakelock;
    public long lastChange;
    public long maxTime;
    public String name;
    public int pid;
    public long preventSuspendTime;
    public long totalTime;
    public long wakeupCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.name);
        parcel.writeLong(this.activeCount);
        parcel.writeLong(this.lastChange);
        parcel.writeLong(this.maxTime);
        parcel.writeLong(this.totalTime);
        parcel.writeInt(this.isActive ? 1 : 0);
        parcel.writeLong(this.activeTime);
        parcel.writeInt(this.isKernelWakelock ? 1 : 0);
        parcel.writeInt(this.pid);
        parcel.writeLong(this.eventCount);
        parcel.writeLong(this.expireCount);
        parcel.writeLong(this.preventSuspendTime);
        parcel.writeLong(this.wakeupCount);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.name = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.activeCount = parcel.readLong();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.lastChange = parcel.readLong();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.maxTime = parcel.readLong();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.totalTime = parcel.readLong();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                boolean z = true;
                                this.isActive = parcel.readInt() != 0;
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.activeTime = parcel.readLong();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        if (parcel.readInt() == 0) {
                                            z = false;
                                        }
                                        this.isKernelWakelock = z;
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.pid = parcel.readInt();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                this.eventCount = parcel.readLong();
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                    this.expireCount = parcel.readLong();
                                                    if (parcel.dataPosition() - iDataPosition < i) {
                                                        this.preventSuspendTime = parcel.readLong();
                                                        if (parcel.dataPosition() - iDataPosition < i) {
                                                            this.wakeupCount = parcel.readLong();
                                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
