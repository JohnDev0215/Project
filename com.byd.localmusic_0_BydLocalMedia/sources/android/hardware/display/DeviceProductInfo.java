package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceProductInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceProductInfo> CREATOR = new Parcelable.Creator<DeviceProductInfo>() { // from class: android.hardware.display.DeviceProductInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProductInfo createFromParcel(Parcel parcel) {
            return new DeviceProductInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProductInfo[] newArray(int i) {
            return new DeviceProductInfo[i];
        }
    };
    private final ManufactureDate mManufactureDate;
    private final String mManufacturerPnpId;
    private final Integer mModelYear;
    private final String mName;
    private final String mProductId;
    private final int[] mRelativeAddress;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceProductInfo(String str, String str2, String str3, Integer num, ManufactureDate manufactureDate, int[] iArr) {
        this.mName = str;
        this.mManufacturerPnpId = str2;
        this.mProductId = str3;
        this.mModelYear = num;
        this.mManufactureDate = manufactureDate;
        this.mRelativeAddress = iArr;
    }

    private DeviceProductInfo(Parcel parcel) {
        this.mName = parcel.readString();
        this.mManufacturerPnpId = parcel.readString();
        this.mProductId = (String) parcel.readValue(null);
        this.mModelYear = (Integer) parcel.readValue(null);
        this.mManufactureDate = (ManufactureDate) parcel.readValue(null);
        this.mRelativeAddress = parcel.createIntArray();
    }

    public String getName() {
        return this.mName;
    }

    public String getManufacturerPnpId() {
        return this.mManufacturerPnpId;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public Integer getModelYear() {
        return this.mModelYear;
    }

    public ManufactureDate getManufactureDate() {
        return this.mManufactureDate;
    }

    public int[] getRelativeAddress() {
        return this.mRelativeAddress;
    }

    public String toString() {
        return "DeviceProductInfo{name=" + this.mName + ", manufacturerPnpId=" + this.mManufacturerPnpId + ", productId=" + this.mProductId + ", modelYear=" + this.mModelYear + ", manufactureDate=" + this.mManufactureDate + ", relativeAddress=" + Arrays.toString(this.mRelativeAddress) + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeviceProductInfo deviceProductInfo = (DeviceProductInfo) obj;
        return Objects.equals(this.mName, deviceProductInfo.mName) && Objects.equals(this.mManufacturerPnpId, deviceProductInfo.mManufacturerPnpId) && Objects.equals(this.mProductId, deviceProductInfo.mProductId) && Objects.equals(this.mModelYear, deviceProductInfo.mModelYear) && Objects.equals(this.mManufactureDate, deviceProductInfo.mManufactureDate) && Arrays.equals(this.mRelativeAddress, deviceProductInfo.mRelativeAddress);
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mManufacturerPnpId, this.mProductId, this.mModelYear, this.mManufactureDate, Integer.valueOf(Arrays.hashCode(this.mRelativeAddress)));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mManufacturerPnpId);
        parcel.writeValue(this.mProductId);
        parcel.writeValue(this.mModelYear);
        parcel.writeValue(this.mManufactureDate);
        parcel.writeIntArray(this.mRelativeAddress);
    }

    public static class ManufactureDate implements Parcelable {
        public static final Parcelable.Creator<ManufactureDate> CREATOR = new Parcelable.Creator<ManufactureDate>() { // from class: android.hardware.display.DeviceProductInfo.ManufactureDate.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ManufactureDate createFromParcel(Parcel parcel) {
                return new ManufactureDate(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ManufactureDate[] newArray(int i) {
                return new ManufactureDate[i];
            }
        };
        private final Integer mWeek;
        private final Integer mYear;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ManufactureDate(Integer num, Integer num2) {
            this.mWeek = num;
            this.mYear = num2;
        }

        protected ManufactureDate(Parcel parcel) {
            this.mWeek = (Integer) parcel.readValue(null);
            this.mYear = (Integer) parcel.readValue(null);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(this.mWeek);
            parcel.writeValue(this.mYear);
        }

        public Integer getYear() {
            return this.mYear;
        }

        public Integer getWeek() {
            return this.mWeek;
        }

        public String toString() {
            return "ManufactureDate{week=" + this.mWeek + ", year=" + this.mYear + '}';
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ManufactureDate manufactureDate = (ManufactureDate) obj;
            return Objects.equals(this.mWeek, manufactureDate.mWeek) && Objects.equals(this.mYear, manufactureDate.mYear);
        }

        public int hashCode() {
            return Objects.hash(this.mWeek, this.mYear);
        }
    }
}
