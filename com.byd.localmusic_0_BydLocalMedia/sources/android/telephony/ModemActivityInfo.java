package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Range;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class ModemActivityInfo implements Parcelable {
    public static final int TX_POWER_LEVELS = 5;
    public static final int TX_POWER_LEVEL_0 = 0;
    public static final int TX_POWER_LEVEL_1 = 1;
    public static final int TX_POWER_LEVEL_2 = 2;
    public static final int TX_POWER_LEVEL_3 = 3;
    public static final int TX_POWER_LEVEL_4 = 4;
    private int mIdleTimeMs;
    private int mRxTimeMs;
    private int mSleepTimeMs;
    private long mTimestamp;
    private List<TransmitPower> mTransmitPowerInfo = new ArrayList(5);
    private static final Range<Integer>[] TX_POWER_RANGES = {new Range<>(Integer.MIN_VALUE, 0), new Range<>(0, 5), new Range<>(5, 15), new Range<>(15, 20), new Range<>(20, Integer.MAX_VALUE)};
    public static final Parcelable.Creator<ModemActivityInfo> CREATOR = new Parcelable.Creator<ModemActivityInfo>() { // from class: android.telephony.ModemActivityInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModemActivityInfo createFromParcel(Parcel parcel) {
            long j = parcel.readLong();
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            int[] iArr = new int[5];
            for (int i3 = 0; i3 < 5; i3++) {
                iArr[i3] = parcel.readInt();
            }
            return new ModemActivityInfo(j, i, i2, iArr, parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModemActivityInfo[] newArray(int i) {
            return new ModemActivityInfo[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface TxPowerLevel {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ModemActivityInfo(long j, int i, int i2, int[] iArr, int i3) {
        this.mTimestamp = j;
        this.mSleepTimeMs = i;
        this.mIdleTimeMs = i2;
        populateTransmitPowerRange(iArr);
        this.mRxTimeMs = i3;
    }

    private void populateTransmitPowerRange(int[] iArr) {
        int i = 0;
        while (i < Math.min(iArr.length, 5)) {
            this.mTransmitPowerInfo.add(i, new TransmitPower(TX_POWER_RANGES[i], iArr[i]));
            i++;
        }
        while (i < 5) {
            this.mTransmitPowerInfo.add(i, new TransmitPower(TX_POWER_RANGES[i], 0));
            i++;
        }
    }

    public String toString() {
        return "ModemActivityInfo{ mTimestamp=" + this.mTimestamp + " mSleepTimeMs=" + this.mSleepTimeMs + " mIdleTimeMs=" + this.mIdleTimeMs + " mTransmitPowerInfo[]=" + this.mTransmitPowerInfo.toString() + " mRxTimeMs=" + this.mRxTimeMs + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTimestamp);
        parcel.writeInt(this.mSleepTimeMs);
        parcel.writeInt(this.mIdleTimeMs);
        for (int i2 = 0; i2 < 5; i2++) {
            parcel.writeInt(this.mTransmitPowerInfo.get(i2).getTimeInMillis());
        }
        parcel.writeInt(this.mRxTimeMs);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public List<TransmitPower> getTransmitPowerInfo() {
        return this.mTransmitPowerInfo;
    }

    public void setTransmitTimeMillis(int[] iArr) {
        populateTransmitPowerRange(iArr);
    }

    public int[] getTransmitTimeMillis() {
        int[] iArr = new int[5];
        for (int i = 0; i < 5; i++) {
            iArr[i] = this.mTransmitPowerInfo.get(i).getTimeInMillis();
        }
        return iArr;
    }

    public int getSleepTimeMillis() {
        return this.mSleepTimeMs;
    }

    public void setSleepTimeMillis(int i) {
        this.mSleepTimeMs = i;
    }

    public int getIdleTimeMillis() {
        return this.mIdleTimeMs;
    }

    public void setIdleTimeMillis(int i) {
        this.mIdleTimeMs = i;
    }

    public int getReceiveTimeMillis() {
        return this.mRxTimeMs;
    }

    public void setReceiveTimeMillis(int i) {
        this.mRxTimeMs = i;
    }

    public boolean isValid() {
        Iterator<TransmitPower> it = getTransmitPowerInfo().iterator();
        while (it.hasNext()) {
            if (it.next().getTimeInMillis() < 0) {
                return false;
            }
        }
        return getIdleTimeMillis() >= 0 && getSleepTimeMillis() >= 0 && getReceiveTimeMillis() >= 0 && !isEmpty();
    }

    private boolean isEmpty() {
        Iterator<TransmitPower> it = getTransmitPowerInfo().iterator();
        while (it.hasNext()) {
            if (it.next().getTimeInMillis() != 0) {
                return false;
            }
        }
        return getIdleTimeMillis() == 0 && getSleepTimeMillis() == 0 && getReceiveTimeMillis() == 0;
    }

    public class TransmitPower {
        private Range<Integer> mPowerRangeInDbm;
        private int mTimeInMillis;

        public TransmitPower(Range<Integer> range, int i) {
            this.mTimeInMillis = i;
            this.mPowerRangeInDbm = range;
        }

        public int getTimeInMillis() {
            return this.mTimeInMillis;
        }

        public Range<Integer> getPowerRangeInDbm() {
            return this.mPowerRangeInDbm;
        }

        public String toString() {
            return "TransmitPower{ mTimeInMillis=" + this.mTimeInMillis + " mPowerRangeInDbm={" + this.mPowerRangeInDbm.getLower() + "," + this.mPowerRangeInDbm.getUpper() + "}}";
        }
    }
}
