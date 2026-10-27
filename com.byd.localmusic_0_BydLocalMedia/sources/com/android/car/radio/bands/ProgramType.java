package com.android.car.radio.bands;

import android.hardware.radio.ProgramSelector;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.car.broadcastradio.support.platform.ProgramSelectorExt;
import com.android.car.radio.platform.RadioTunerExt;
import com.byd.music.utils.LogUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ProgramType implements Parcelable {
    public static final int ID_AM = 1;
    public static final int ID_DAB = 3;
    public static final int ID_FM = 2;
    private static final String TAG = "BcRadioApp.ProgramType";
    public final int id;
    public static final ProgramType AM = new AMProgramType(1);
    public static final ProgramType FM = new FMProgramType(2);
    public static final ProgramType DAB = new DABProgramType(3);
    public static final Parcelable.Creator<ProgramType> CREATOR = new Parcelable.Creator<ProgramType>() { // from class: com.android.car.radio.bands.ProgramType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProgramType createFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            if (i == 1) {
                return ProgramType.AM;
            }
            if (i == 2) {
                return ProgramType.FM;
            }
            if (i == 3) {
                return ProgramType.DAB;
            }
            LogUtil.w(ProgramType.TAG, "Unknown ProgramType ID: " + i);
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProgramType[] newArray(int i) {
            return new ProgramType[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeId {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public abstract String getEnglishName();

    public abstract int getLocalizedName();

    public abstract int getResourceId();

    public abstract boolean[] getValidAppendices(RegionConfig regionConfig, int i);

    public abstract boolean isComplete(RegionConfig regionConfig, int i);

    public abstract ProgramSelector parseDigits(float f);

    public abstract void tuneToDefault(RadioTunerExt radioTunerExt, RegionConfig regionConfig, RadioTunerExt.TuneCallback tuneCallback);

    protected ProgramType(int i) {
        this.id = i;
    }

    public static ProgramType fromSelector(ProgramSelector programSelector) {
        if (programSelector == null) {
            return null;
        }
        if (programSelector.getPrimaryId().getType() == 5) {
            return DAB;
        }
        if (!ProgramSelectorExt.isAmFmProgram(programSelector)) {
            return null;
        }
        if (!ProgramSelectorExt.hasId(programSelector, 1)) {
            LogUtil.e(TAG, "AM/FM program selector with missing frequency");
            return FM;
        }
        long firstId = programSelector.getFirstId(1);
        if (ProgramSelectorExt.isAmFrequency(firstId)) {
            return AM;
        }
        if (ProgramSelectorExt.isFmFrequency(firstId)) {
            return FM;
        }
        LogUtil.e(TAG, "AM/FM program selector with frequency out of range: " + firstId);
        return FM;
    }

    public String format(int i) {
        if (i >= 0) {
            return i == 0 ? "" : Integer.toString(i);
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        return getEnglishName();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ProgramType) && ((ProgramType) obj).id == this.id;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
    }
}
