package android.companion;

import android.annotation.NonNull;
import android.net.MacAddress;
import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;
import java.util.Objects;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class WifiDeviceFilter implements DeviceFilter<ScanResult> {
    public static final Parcelable.Creator<WifiDeviceFilter> CREATOR;
    static Parcelling<Pattern> sParcellingForNamePattern;
    private MacAddress mBssid;
    private MacAddress mBssidMask;
    private Pattern mNamePattern;

    @Deprecated
    private void __metadata() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.companion.DeviceFilter
    public int getMediumType() {
        return 2;
    }

    @Override // android.companion.DeviceFilter
    public boolean matches(ScanResult scanResult) {
        return BluetoothDeviceFilterUtils.matchesName(getNamePattern(), scanResult) && (this.mBssid == null || MacAddress.fromString(scanResult.BSSID).matches(this.mBssid, this.mBssidMask));
    }

    @Override // android.companion.DeviceFilter
    public String getDeviceDisplayName(ScanResult scanResult) {
        return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(scanResult);
    }

    WifiDeviceFilter(Pattern pattern, MacAddress macAddress, MacAddress macAddress2) {
        this.mNamePattern = null;
        this.mBssid = null;
        this.mBssidMask = MacAddress.BROADCAST_ADDRESS;
        this.mNamePattern = pattern;
        this.mBssid = macAddress;
        this.mBssidMask = macAddress2;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) macAddress2);
    }

    public Pattern getNamePattern() {
        return this.mNamePattern;
    }

    public MacAddress getBssid() {
        return this.mBssid;
    }

    public MacAddress getBssidMask() {
        return this.mBssidMask;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WifiDeviceFilter wifiDeviceFilter = (WifiDeviceFilter) obj;
        return Objects.equals(this.mNamePattern, wifiDeviceFilter.mNamePattern) && Objects.equals(this.mBssid, wifiDeviceFilter.mBssid) && Objects.equals(this.mBssidMask, wifiDeviceFilter.mBssidMask);
    }

    public int hashCode() {
        return ((((Objects.hashCode(this.mNamePattern) + 31) * 31) + Objects.hashCode(this.mBssid)) * 31) + Objects.hashCode(this.mBssidMask);
    }

    static {
        Parcelling<Pattern> parcelling = Parcelling.Cache.get(Parcelling.BuiltIn.ForPattern.class);
        sParcellingForNamePattern = parcelling;
        if (parcelling == null) {
            sParcellingForNamePattern = Parcelling.Cache.put(new Parcelling.BuiltIn.ForPattern());
        }
        CREATOR = new Parcelable.Creator<WifiDeviceFilter>() { // from class: android.companion.WifiDeviceFilter.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WifiDeviceFilter[] newArray(int i) {
                return new WifiDeviceFilter[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WifiDeviceFilter createFromParcel(Parcel parcel) {
                return new WifiDeviceFilter(parcel);
            }
        };
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        byte b = this.mNamePattern != null ? (byte) 1 : (byte) 0;
        if (this.mBssid != null) {
            b = (byte) (b | 2);
        }
        parcel.writeByte(b);
        sParcellingForNamePattern.parcel(this.mNamePattern, parcel, i);
        MacAddress macAddress = this.mBssid;
        if (macAddress != null) {
            parcel.writeTypedObject(macAddress, i);
        }
        parcel.writeTypedObject(this.mBssidMask, i);
    }

    WifiDeviceFilter(Parcel parcel) {
        this.mNamePattern = null;
        this.mBssid = null;
        this.mBssidMask = MacAddress.BROADCAST_ADDRESS;
        byte b = parcel.readByte();
        Pattern patternUnparcel = sParcellingForNamePattern.unparcel(parcel);
        MacAddress macAddress = (b & 2) == 0 ? null : (MacAddress) parcel.readTypedObject(MacAddress.CREATOR);
        MacAddress macAddress2 = (MacAddress) parcel.readTypedObject(MacAddress.CREATOR);
        this.mNamePattern = patternUnparcel;
        this.mBssid = macAddress;
        this.mBssidMask = macAddress2;
        AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) macAddress2);
    }

    public static final class Builder {
        private MacAddress mBssid;
        private MacAddress mBssidMask;
        private long mBuilderFieldsSet = 0;
        private Pattern mNamePattern;

        public Builder setNamePattern(Pattern pattern) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 1;
            this.mNamePattern = pattern;
            return this;
        }

        public Builder setBssid(MacAddress macAddress) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 2;
            this.mBssid = macAddress;
            return this;
        }

        public Builder setBssidMask(MacAddress macAddress) {
            checkNotUsed();
            this.mBuilderFieldsSet |= 4;
            this.mBssidMask = macAddress;
            return this;
        }

        public WifiDeviceFilter build() {
            checkNotUsed();
            long j = this.mBuilderFieldsSet | 8;
            this.mBuilderFieldsSet = j;
            if ((j & 1) == 0) {
                this.mNamePattern = null;
            }
            if ((this.mBuilderFieldsSet & 2) == 0) {
                this.mBssid = null;
            }
            if ((this.mBuilderFieldsSet & 4) == 0) {
                this.mBssidMask = MacAddress.BROADCAST_ADDRESS;
            }
            return new WifiDeviceFilter(this.mNamePattern, this.mBssid, this.mBssidMask);
        }

        private void checkNotUsed() {
            if ((this.mBuilderFieldsSet & 8) != 0) {
                throw new IllegalStateException("This Builder should not be reused. Use a new Builder instance instead");
            }
        }
    }
}
