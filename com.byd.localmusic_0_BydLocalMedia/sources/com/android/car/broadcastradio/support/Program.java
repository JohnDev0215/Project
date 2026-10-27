package com.android.car.broadcastradio.support;

import android.graphics.Bitmap;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.car.broadcastradio.support.platform.ProgramInfoExt;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class Program implements Parcelable {
    public static final Parcelable.Creator<Program> CREATOR = new Parcelable.Creator<Program>() { // from class: com.android.car.broadcastradio.support.Program.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Program createFromParcel(Parcel parcel) {
            return new Program(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Program[] newArray(int i) {
            return new Program[i];
        }
    };
    private final String mName;
    private final ProgramSelector mSelector;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getIcon() {
        return null;
    }

    public Program(ProgramSelector programSelector, String str) {
        this.mSelector = (ProgramSelector) Objects.requireNonNull(programSelector);
        this.mName = (String) Objects.requireNonNull(str);
    }

    public ProgramSelector getSelector() {
        return this.mSelector;
    }

    public String getName() {
        return this.mName;
    }

    public String toString() {
        return "Program(\"" + this.mName + "\", " + this.mSelector + ")";
    }

    public int hashCode() {
        return this.mSelector.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Program) {
            return this.mSelector.equals(((Program) obj).mSelector);
        }
        return false;
    }

    public static Program fromProgramInfo(RadioManager.ProgramInfo programInfo) {
        return new Program(programInfo.getSelector(), ProgramInfoExt.getProgramName(programInfo, 0));
    }

    private Program(Parcel parcel) {
        this.mSelector = (ProgramSelector) Objects.requireNonNull((ProgramSelector) parcel.readTypedObject(ProgramSelector.CREATOR));
        this.mName = (String) Objects.requireNonNull(parcel.readString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mSelector, 0);
        parcel.writeString(this.mName);
    }
}
