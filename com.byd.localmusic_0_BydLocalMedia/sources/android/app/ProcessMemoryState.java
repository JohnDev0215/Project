package android.app;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessMemoryState implements Parcelable {
    public static final Parcelable.Creator<ProcessMemoryState> CREATOR = new Parcelable.Creator<ProcessMemoryState>() { // from class: android.app.ProcessMemoryState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcessMemoryState createFromParcel(Parcel parcel) {
            return new ProcessMemoryState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcessMemoryState[] newArray(int i) {
            return new ProcessMemoryState[i];
        }
    };
    public final int oomScore;
    public final int pid;
    public final String processName;
    public final int uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProcessMemoryState(int i, int i2, String str, int i3) {
        this.uid = i;
        this.pid = i2;
        this.processName = str;
        this.oomScore = i3;
    }

    private ProcessMemoryState(Parcel parcel) {
        this.uid = parcel.readInt();
        this.pid = parcel.readInt();
        this.processName = parcel.readString();
        this.oomScore = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.uid);
        parcel.writeInt(this.pid);
        parcel.writeString(this.processName);
        parcel.writeInt(this.oomScore);
    }
}
