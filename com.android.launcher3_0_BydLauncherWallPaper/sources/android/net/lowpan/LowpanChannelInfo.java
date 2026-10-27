package android.net.lowpan;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LowpanChannelInfo implements Parcelable {
    public static final Parcelable.Creator<LowpanChannelInfo> CREATOR = new Parcelable.Creator<LowpanChannelInfo>() { // from class: android.net.lowpan.LowpanChannelInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LowpanChannelInfo createFromParcel(Parcel parcel) {
            LowpanChannelInfo lowpanChannelInfo = new LowpanChannelInfo();
            lowpanChannelInfo.mIndex = parcel.readInt();
            lowpanChannelInfo.mName = parcel.readString();
            lowpanChannelInfo.mSpectrumCenterFrequency = parcel.readFloat();
            lowpanChannelInfo.mSpectrumBandwidth = parcel.readFloat();
            lowpanChannelInfo.mMaxTransmitPower = parcel.readInt();
            lowpanChannelInfo.mIsMaskedByRegulatoryDomain = parcel.readBoolean();
            return lowpanChannelInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LowpanChannelInfo[] newArray(int i) {
            return new LowpanChannelInfo[i];
        }
    };
    public static final float UNKNOWN_BANDWIDTH = 0.0f;
    public static final float UNKNOWN_FREQUENCY = 0.0f;
    public static final int UNKNOWN_POWER = Integer.MAX_VALUE;
    private int mIndex;
    private boolean mIsMaskedByRegulatoryDomain;
    private int mMaxTransmitPower;
    private String mName;
    private float mSpectrumBandwidth;
    private float mSpectrumCenterFrequency;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.net.lowpan.LowpanChannelInfo getChannelInfoForIeee802154Page0(int r5) {
        /*
            android.net.lowpan.LowpanChannelInfo r0 = new android.net.lowpan.LowpanChannelInfo
            r0.<init>()
            r1 = 0
            if (r5 >= 0) goto La
        L8:
            r0 = r1
            goto L3b
        La:
            if (r5 != 0) goto L17
            r1 = 1313801428(0x4e4f04d4, float:8.683E8)
            r0.mSpectrumCenterFrequency = r1
            r1 = 1225948160(0x49127c00, float:600000.0)
            r0.mSpectrumBandwidth = r1
            goto L3b
        L17:
            r2 = 11
            r3 = 1240736768(0x49f42400, float:2000000.0)
            if (r5 >= r2) goto L2a
            r1 = 1314359240(0x4e5787c8, float:9.04E8)
            float r2 = (float) r5
            float r2 = r2 * r3
            float r2 = r2 + r1
            r0.mSpectrumCenterFrequency = r2
            r1 = 0
            r0.mSpectrumBandwidth = r1
            goto L3b
        L2a:
            r2 = 26
            if (r5 >= r2) goto L8
            r1 = 1326191143(0x4f0c1227, float:2.3499999E9)
            r2 = 1251513984(0x4a989680, float:5000000.0)
            float r4 = (float) r5
            float r4 = r4 * r2
            float r4 = r4 + r1
            r0.mSpectrumCenterFrequency = r4
            r0.mSpectrumBandwidth = r3
        L3b:
            java.lang.String r5 = java.lang.Integer.toString(r5)
            r0.mName = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.lowpan.LowpanChannelInfo.getChannelInfoForIeee802154Page0(int):android.net.lowpan.LowpanChannelInfo");
    }

    private LowpanChannelInfo() {
        this.mIndex = 0;
        this.mName = null;
        this.mSpectrumCenterFrequency = 0.0f;
        this.mSpectrumBandwidth = 0.0f;
        this.mMaxTransmitPower = Integer.MAX_VALUE;
        this.mIsMaskedByRegulatoryDomain = false;
    }

    private LowpanChannelInfo(int i, String str, float f, float f2) {
        this.mIndex = 0;
        this.mName = null;
        this.mSpectrumCenterFrequency = 0.0f;
        this.mSpectrumBandwidth = 0.0f;
        this.mMaxTransmitPower = Integer.MAX_VALUE;
        this.mIsMaskedByRegulatoryDomain = false;
        this.mIndex = i;
        this.mName = str;
        this.mSpectrumCenterFrequency = f;
        this.mSpectrumBandwidth = f2;
    }

    public String getName() {
        return this.mName;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getMaxTransmitPower() {
        return this.mMaxTransmitPower;
    }

    public boolean isMaskedByRegulatoryDomain() {
        return this.mIsMaskedByRegulatoryDomain;
    }

    public float getSpectrumCenterFrequency() {
        return this.mSpectrumCenterFrequency;
    }

    public float getSpectrumBandwidth() {
        return this.mSpectrumBandwidth;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Channel ").append(this.mIndex);
        String str = this.mName;
        if (str != null && !str.equals(Integer.toString(this.mIndex))) {
            stringBuffer.append(" (").append(this.mName).append(")");
        }
        float f = this.mSpectrumCenterFrequency;
        if (f > 0.0f) {
            if (f > 1.0E9f) {
                stringBuffer.append(", SpectrumCenterFrequency: ").append(this.mSpectrumCenterFrequency / 1.0E9f).append("GHz");
            } else if (f > 1000000.0f) {
                stringBuffer.append(", SpectrumCenterFrequency: ").append(this.mSpectrumCenterFrequency / 1000000.0f).append("MHz");
            } else {
                stringBuffer.append(", SpectrumCenterFrequency: ").append(this.mSpectrumCenterFrequency / 1000.0f).append("kHz");
            }
        }
        float f2 = this.mSpectrumBandwidth;
        if (f2 > 0.0f) {
            if (f2 > 1.0E9f) {
                stringBuffer.append(", SpectrumBandwidth: ").append(this.mSpectrumBandwidth / 1.0E9f).append("GHz");
            } else if (f2 > 1000000.0f) {
                stringBuffer.append(", SpectrumBandwidth: ").append(this.mSpectrumBandwidth / 1000000.0f).append("MHz");
            } else {
                stringBuffer.append(", SpectrumBandwidth: ").append(this.mSpectrumBandwidth / 1000.0f).append("kHz");
            }
        }
        if (this.mMaxTransmitPower != Integer.MAX_VALUE) {
            stringBuffer.append(", MaxTransmitPower: ").append(this.mMaxTransmitPower).append("dBm");
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LowpanChannelInfo)) {
            return false;
        }
        LowpanChannelInfo lowpanChannelInfo = (LowpanChannelInfo) obj;
        return Objects.equals(this.mName, lowpanChannelInfo.mName) && this.mIndex == lowpanChannelInfo.mIndex && this.mIsMaskedByRegulatoryDomain == lowpanChannelInfo.mIsMaskedByRegulatoryDomain && this.mSpectrumCenterFrequency == lowpanChannelInfo.mSpectrumCenterFrequency && this.mSpectrumBandwidth == lowpanChannelInfo.mSpectrumBandwidth && this.mMaxTransmitPower == lowpanChannelInfo.mMaxTransmitPower;
    }

    public int hashCode() {
        return Objects.hash(this.mName, Integer.valueOf(this.mIndex), Boolean.valueOf(this.mIsMaskedByRegulatoryDomain), Float.valueOf(this.mSpectrumCenterFrequency), Float.valueOf(this.mSpectrumBandwidth), Integer.valueOf(this.mMaxTransmitPower));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mIndex);
        parcel.writeString(this.mName);
        parcel.writeFloat(this.mSpectrumCenterFrequency);
        parcel.writeFloat(this.mSpectrumBandwidth);
        parcel.writeInt(this.mMaxTransmitPower);
        parcel.writeBoolean(this.mIsMaskedByRegulatoryDomain);
    }
}
