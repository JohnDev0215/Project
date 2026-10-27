package vendor.ts.iap.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HalVehicleStatusInfo {
    public boolean rangeValid = false;
    public short range = 0;
    public boolean outsideTemperatureValid = false;
    public short outsideTemperature = 0;
    public boolean rangeWarningValid = false;
    public boolean rangeWarning = false;
    public boolean rangeGasolineValid = false;
    public short rangeGasoline = 0;
    public boolean rangeDieselValid = false;
    public short rangeDiesel = 0;
    public boolean rangeElectricValid = false;
    public short rangeElectric = 0;
    public boolean rangeCNGValid = false;
    public short rangeCNG = 0;
    public boolean rangeWarningGasolineValid = false;
    public boolean rangeWarningGasoline = false;
    public boolean rangeWarningDieselValid = false;
    public boolean rangeWarningDiesel = false;
    public boolean rangeWarningElectricValid = false;
    public boolean rangeWarningElectric = false;
    public boolean rangeWarningCNGValid = false;
    public boolean rangeWarningCNG = false;
    public boolean minBatteryChargeValid = false;
    public int minBatteryCharge = 0;
    public boolean currentBatteryChargeValid = false;
    public int currentBatteryCharge = 0;
    public boolean maxBatteryChargeValid = false;
    public int maxBatteryCharge = 0;
    public boolean displayedBatteryPercentageValid = false;
    public int displayedBatteryPercentage = 0;
    public boolean isChargingValid = false;
    public boolean isCharging = false;
    public boolean chargingParameterValid = false;
    public String chargingParameter = new String();
    public boolean consumptionParameterValid = false;
    public String consumptionParameter = new String();
    public boolean activeConnectorValid = false;
    public byte activeConnector = 0;
    public boolean maxRangeElectricValid = false;
    public short maxRangeElectric = 0;

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != HalVehicleStatusInfo.class) {
            return false;
        }
        HalVehicleStatusInfo other = (HalVehicleStatusInfo) otherObject;
        if (this.rangeValid == other.rangeValid && this.range == other.range && this.outsideTemperatureValid == other.outsideTemperatureValid && this.outsideTemperature == other.outsideTemperature && this.rangeWarningValid == other.rangeWarningValid && this.rangeWarning == other.rangeWarning && this.rangeGasolineValid == other.rangeGasolineValid && this.rangeGasoline == other.rangeGasoline && this.rangeDieselValid == other.rangeDieselValid && this.rangeDiesel == other.rangeDiesel && this.rangeElectricValid == other.rangeElectricValid && this.rangeElectric == other.rangeElectric && this.rangeCNGValid == other.rangeCNGValid && this.rangeCNG == other.rangeCNG && this.rangeWarningGasolineValid == other.rangeWarningGasolineValid && this.rangeWarningGasoline == other.rangeWarningGasoline && this.rangeWarningDieselValid == other.rangeWarningDieselValid && this.rangeWarningDiesel == other.rangeWarningDiesel && this.rangeWarningElectricValid == other.rangeWarningElectricValid && this.rangeWarningElectric == other.rangeWarningElectric && this.rangeWarningCNGValid == other.rangeWarningCNGValid && this.rangeWarningCNG == other.rangeWarningCNG && this.minBatteryChargeValid == other.minBatteryChargeValid && this.minBatteryCharge == other.minBatteryCharge && this.currentBatteryChargeValid == other.currentBatteryChargeValid && this.currentBatteryCharge == other.currentBatteryCharge && this.maxBatteryChargeValid == other.maxBatteryChargeValid && this.maxBatteryCharge == other.maxBatteryCharge && this.displayedBatteryPercentageValid == other.displayedBatteryPercentageValid && this.displayedBatteryPercentage == other.displayedBatteryPercentage && this.isChargingValid == other.isChargingValid && this.isCharging == other.isCharging && this.chargingParameterValid == other.chargingParameterValid && HidlSupport.deepEquals(this.chargingParameter, other.chargingParameter) && this.consumptionParameterValid == other.consumptionParameterValid && HidlSupport.deepEquals(this.consumptionParameter, other.consumptionParameter) && this.activeConnectorValid == other.activeConnectorValid && this.activeConnector == other.activeConnector && this.maxRangeElectricValid == other.maxRangeElectricValid && this.maxRangeElectric == other.maxRangeElectric) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.range))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.outsideTemperatureValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.outsideTemperature))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarning))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeGasolineValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.rangeGasoline))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeDieselValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.rangeDiesel))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeElectricValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.rangeElectric))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeCNGValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.rangeCNG))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningGasolineValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningGasoline))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningDieselValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningDiesel))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningElectricValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningElectric))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningCNGValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.rangeWarningCNG))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.minBatteryChargeValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.minBatteryCharge))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.currentBatteryChargeValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.currentBatteryCharge))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.maxBatteryChargeValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.maxBatteryCharge))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.displayedBatteryPercentageValid))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.displayedBatteryPercentage))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isChargingValid))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isCharging))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.chargingParameterValid))), Integer.valueOf(HidlSupport.deepHashCode(this.chargingParameter)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.consumptionParameterValid))), Integer.valueOf(HidlSupport.deepHashCode(this.consumptionParameter)), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.activeConnectorValid))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.activeConnector))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.maxRangeElectricValid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.maxRangeElectric))));
    }

    public final String toString() {
        return "{.rangeValid = " + this.rangeValid + ", .range = " + ((int) this.range) + ", .outsideTemperatureValid = " + this.outsideTemperatureValid + ", .outsideTemperature = " + ((int) this.outsideTemperature) + ", .rangeWarningValid = " + this.rangeWarningValid + ", .rangeWarning = " + this.rangeWarning + ", .rangeGasolineValid = " + this.rangeGasolineValid + ", .rangeGasoline = " + ((int) this.rangeGasoline) + ", .rangeDieselValid = " + this.rangeDieselValid + ", .rangeDiesel = " + ((int) this.rangeDiesel) + ", .rangeElectricValid = " + this.rangeElectricValid + ", .rangeElectric = " + ((int) this.rangeElectric) + ", .rangeCNGValid = " + this.rangeCNGValid + ", .rangeCNG = " + ((int) this.rangeCNG) + ", .rangeWarningGasolineValid = " + this.rangeWarningGasolineValid + ", .rangeWarningGasoline = " + this.rangeWarningGasoline + ", .rangeWarningDieselValid = " + this.rangeWarningDieselValid + ", .rangeWarningDiesel = " + this.rangeWarningDiesel + ", .rangeWarningElectricValid = " + this.rangeWarningElectricValid + ", .rangeWarningElectric = " + this.rangeWarningElectric + ", .rangeWarningCNGValid = " + this.rangeWarningCNGValid + ", .rangeWarningCNG = " + this.rangeWarningCNG + ", .minBatteryChargeValid = " + this.minBatteryChargeValid + ", .minBatteryCharge = " + this.minBatteryCharge + ", .currentBatteryChargeValid = " + this.currentBatteryChargeValid + ", .currentBatteryCharge = " + this.currentBatteryCharge + ", .maxBatteryChargeValid = " + this.maxBatteryChargeValid + ", .maxBatteryCharge = " + this.maxBatteryCharge + ", .displayedBatteryPercentageValid = " + this.displayedBatteryPercentageValid + ", .displayedBatteryPercentage = " + this.displayedBatteryPercentage + ", .isChargingValid = " + this.isChargingValid + ", .isCharging = " + this.isCharging + ", .chargingParameterValid = " + this.chargingParameterValid + ", .chargingParameter = " + this.chargingParameter + ", .consumptionParameterValid = " + this.consumptionParameterValid + ", .consumptionParameter = " + this.consumptionParameter + ", .activeConnectorValid = " + this.activeConnectorValid + ", .activeConnector = " + ((int) this.activeConnector) + ", .maxRangeElectricValid = " + this.maxRangeElectricValid + ", .maxRangeElectric = " + ((int) this.maxRangeElectric) + "}";
    }

    public final void readFromParcel(HwParcel parcel) {
        HwBlob blob = parcel.readBuffer(120L);
        readEmbeddedFromParcel(parcel, blob, 0L);
    }

    public static final ArrayList<HalVehicleStatusInfo> readVectorFromParcel(HwParcel parcel) {
        ArrayList<HalVehicleStatusInfo> _hidl_vec = new ArrayList<>();
        HwBlob _hidl_blob = parcel.readBuffer(16L);
        int _hidl_vec_size = _hidl_blob.getInt32(8L);
        HwBlob childBlob = parcel.readEmbeddedBuffer(_hidl_vec_size * 120, _hidl_blob.handle(), 0L, true);
        _hidl_vec.clear();
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            HalVehicleStatusInfo _hidl_vec_element = new HalVehicleStatusInfo();
            _hidl_vec_element.readEmbeddedFromParcel(parcel, childBlob, _hidl_index_0 * 120);
            _hidl_vec.add(_hidl_vec_element);
        }
        return _hidl_vec;
    }

    public final void readEmbeddedFromParcel(HwParcel parcel, HwBlob _hidl_blob, long _hidl_offset) {
        this.rangeValid = _hidl_blob.getBool(_hidl_offset + 0);
        this.range = _hidl_blob.getInt16(_hidl_offset + 2);
        this.outsideTemperatureValid = _hidl_blob.getBool(_hidl_offset + 4);
        this.outsideTemperature = _hidl_blob.getInt16(_hidl_offset + 6);
        this.rangeWarningValid = _hidl_blob.getBool(_hidl_offset + 8);
        this.rangeWarning = _hidl_blob.getBool(_hidl_offset + 9);
        this.rangeGasolineValid = _hidl_blob.getBool(_hidl_offset + 10);
        this.rangeGasoline = _hidl_blob.getInt16(_hidl_offset + 12);
        this.rangeDieselValid = _hidl_blob.getBool(_hidl_offset + 14);
        this.rangeDiesel = _hidl_blob.getInt16(_hidl_offset + 16);
        this.rangeElectricValid = _hidl_blob.getBool(_hidl_offset + 18);
        this.rangeElectric = _hidl_blob.getInt16(_hidl_offset + 20);
        this.rangeCNGValid = _hidl_blob.getBool(_hidl_offset + 22);
        this.rangeCNG = _hidl_blob.getInt16(_hidl_offset + 24);
        this.rangeWarningGasolineValid = _hidl_blob.getBool(_hidl_offset + 26);
        this.rangeWarningGasoline = _hidl_blob.getBool(_hidl_offset + 27);
        this.rangeWarningDieselValid = _hidl_blob.getBool(_hidl_offset + 28);
        this.rangeWarningDiesel = _hidl_blob.getBool(_hidl_offset + 29);
        this.rangeWarningElectricValid = _hidl_blob.getBool(_hidl_offset + 30);
        this.rangeWarningElectric = _hidl_blob.getBool(_hidl_offset + 31);
        this.rangeWarningCNGValid = _hidl_blob.getBool(_hidl_offset + 32);
        this.rangeWarningCNG = _hidl_blob.getBool(_hidl_offset + 33);
        this.minBatteryChargeValid = _hidl_blob.getBool(_hidl_offset + 34);
        this.minBatteryCharge = _hidl_blob.getInt32(_hidl_offset + 36);
        this.currentBatteryChargeValid = _hidl_blob.getBool(_hidl_offset + 40);
        this.currentBatteryCharge = _hidl_blob.getInt32(_hidl_offset + 44);
        this.maxBatteryChargeValid = _hidl_blob.getBool(_hidl_offset + 48);
        this.maxBatteryCharge = _hidl_blob.getInt32(_hidl_offset + 52);
        this.displayedBatteryPercentageValid = _hidl_blob.getBool(_hidl_offset + 56);
        this.displayedBatteryPercentage = _hidl_blob.getInt32(_hidl_offset + 60);
        this.isChargingValid = _hidl_blob.getBool(_hidl_offset + 64);
        this.isCharging = _hidl_blob.getBool(_hidl_offset + 65);
        this.chargingParameterValid = _hidl_blob.getBool(_hidl_offset + 66);
        this.chargingParameter = _hidl_blob.getString(_hidl_offset + 72);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 72 + 0, false);
        this.consumptionParameterValid = _hidl_blob.getBool(_hidl_offset + 88);
        this.consumptionParameter = _hidl_blob.getString(_hidl_offset + 96);
        parcel.readEmbeddedBuffer(r6.getBytes().length + 1, _hidl_blob.handle(), _hidl_offset + 96 + 0, false);
        this.activeConnectorValid = _hidl_blob.getBool(_hidl_offset + 112);
        this.activeConnector = _hidl_blob.getInt8(_hidl_offset + 113);
        this.maxRangeElectricValid = _hidl_blob.getBool(_hidl_offset + 114);
        this.maxRangeElectric = _hidl_blob.getInt16(_hidl_offset + 116);
    }

    public final void writeToParcel(HwParcel parcel) {
        HwBlob _hidl_blob = new HwBlob(120);
        writeEmbeddedToBlob(_hidl_blob, 0L);
        parcel.writeBuffer(_hidl_blob);
    }

    public static final void writeVectorToParcel(HwParcel parcel, ArrayList<HalVehicleStatusInfo> _hidl_vec) {
        HwBlob _hidl_blob = new HwBlob(16);
        int _hidl_vec_size = _hidl_vec.size();
        _hidl_blob.putInt32(8L, _hidl_vec_size);
        _hidl_blob.putBool(12L, false);
        HwBlob childBlob = new HwBlob(_hidl_vec_size * 120);
        for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
            _hidl_vec.get(_hidl_index_0).writeEmbeddedToBlob(childBlob, _hidl_index_0 * 120);
        }
        _hidl_blob.putBlob(0L, childBlob);
        parcel.writeBuffer(_hidl_blob);
    }

    public final void writeEmbeddedToBlob(HwBlob _hidl_blob, long _hidl_offset) {
        _hidl_blob.putBool(0 + _hidl_offset, this.rangeValid);
        _hidl_blob.putInt16(2 + _hidl_offset, this.range);
        _hidl_blob.putBool(4 + _hidl_offset, this.outsideTemperatureValid);
        _hidl_blob.putInt16(6 + _hidl_offset, this.outsideTemperature);
        _hidl_blob.putBool(8 + _hidl_offset, this.rangeWarningValid);
        _hidl_blob.putBool(9 + _hidl_offset, this.rangeWarning);
        _hidl_blob.putBool(10 + _hidl_offset, this.rangeGasolineValid);
        _hidl_blob.putInt16(12 + _hidl_offset, this.rangeGasoline);
        _hidl_blob.putBool(14 + _hidl_offset, this.rangeDieselValid);
        _hidl_blob.putInt16(16 + _hidl_offset, this.rangeDiesel);
        _hidl_blob.putBool(18 + _hidl_offset, this.rangeElectricValid);
        _hidl_blob.putInt16(20 + _hidl_offset, this.rangeElectric);
        _hidl_blob.putBool(22 + _hidl_offset, this.rangeCNGValid);
        _hidl_blob.putInt16(24 + _hidl_offset, this.rangeCNG);
        _hidl_blob.putBool(26 + _hidl_offset, this.rangeWarningGasolineValid);
        _hidl_blob.putBool(27 + _hidl_offset, this.rangeWarningGasoline);
        _hidl_blob.putBool(28 + _hidl_offset, this.rangeWarningDieselValid);
        _hidl_blob.putBool(29 + _hidl_offset, this.rangeWarningDiesel);
        _hidl_blob.putBool(30 + _hidl_offset, this.rangeWarningElectricValid);
        _hidl_blob.putBool(31 + _hidl_offset, this.rangeWarningElectric);
        _hidl_blob.putBool(32 + _hidl_offset, this.rangeWarningCNGValid);
        _hidl_blob.putBool(33 + _hidl_offset, this.rangeWarningCNG);
        _hidl_blob.putBool(34 + _hidl_offset, this.minBatteryChargeValid);
        _hidl_blob.putInt32(36 + _hidl_offset, this.minBatteryCharge);
        _hidl_blob.putBool(40 + _hidl_offset, this.currentBatteryChargeValid);
        _hidl_blob.putInt32(44 + _hidl_offset, this.currentBatteryCharge);
        _hidl_blob.putBool(48 + _hidl_offset, this.maxBatteryChargeValid);
        _hidl_blob.putInt32(52 + _hidl_offset, this.maxBatteryCharge);
        _hidl_blob.putBool(56 + _hidl_offset, this.displayedBatteryPercentageValid);
        _hidl_blob.putInt32(60 + _hidl_offset, this.displayedBatteryPercentage);
        _hidl_blob.putBool(64 + _hidl_offset, this.isChargingValid);
        _hidl_blob.putBool(65 + _hidl_offset, this.isCharging);
        _hidl_blob.putBool(66 + _hidl_offset, this.chargingParameterValid);
        _hidl_blob.putString(72 + _hidl_offset, this.chargingParameter);
        _hidl_blob.putBool(88 + _hidl_offset, this.consumptionParameterValid);
        _hidl_blob.putString(96 + _hidl_offset, this.consumptionParameter);
        _hidl_blob.putBool(112 + _hidl_offset, this.activeConnectorValid);
        _hidl_blob.putInt8(113 + _hidl_offset, this.activeConnector);
        _hidl_blob.putBool(114 + _hidl_offset, this.maxRangeElectricValid);
        _hidl_blob.putInt16(116 + _hidl_offset, this.maxRangeElectric);
    }
}
