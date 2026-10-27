package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class VehicleStatusUpdate {
    private static final String TAG = "VehicleStatusUpdate";
    private boolean mRangeValid = false;
    private int mRange = -1;
    private boolean mOutsideTemperatureValid = false;
    private int mOutsideTemperature = -1;
    private boolean mRangeWarningValid = false;
    private boolean mRangeWarning = false;
    private boolean mRangeGasolineValid = false;
    private int mRangeGasoline = -1;
    private boolean mRangeDieselValid = false;
    private int mRangeDiesel = -1;
    private boolean mRangeElectricValid = false;
    private int mRangeElectric = -1;
    private boolean mRangeCNGValid = false;
    private int mRangeCNG = -1;
    private boolean mRangeWarningGasolineValid = false;
    private boolean mRangeWarningDieselValid = false;
    private boolean mRangeWarningElectricValid = false;
    private boolean mRangeWarningCNGValid = false;
    private boolean mRangeWarningGasoline = false;
    private boolean mRangeWarningDiesel = false;
    private boolean mRangeWarningElectric = false;
    private boolean mRangeWarningCNG = false;
    private boolean mMinBatteryChargeValid = false;
    private long mMinBatteryCharge = -1;
    private boolean mCurrentBatteryChargeValid = false;
    private long mCurrentBatteryCharge = -1;
    private boolean mMaxBatteryChargeValid = false;
    private long mMaxBatteryCharge = -1;
    private boolean mDisplayedBatteryPercentageValid = false;
    private long mDisplayedBatteryPercentage = -1;
    private boolean mIsChargingValid = false;
    private boolean mIsCharging = false;
    private boolean mChargingParameterValid = false;
    private String mChargingParameter = null;
    private boolean mConsumptionParameterValid = false;
    private String mConsumptionParameter = null;
    private boolean mActiveConnectorValid = false;
    private int mActiveConnector = -1;
    private boolean mMaxRangeElectricValid = false;
    private int mMaxRangeElectric = -1;

    public int getRange() {
        return this.mRange;
    }

    public boolean getRangeValid() {
        return this.mRangeValid;
    }

    public void setRange(int range) {
        this.mRange = range;
        this.mRangeValid = true;
    }

    public boolean isRangeValid() {
        return this.mRangeValid;
    }

    public int getOutsideTemperature() {
        return this.mOutsideTemperature;
    }

    public boolean getOutsideTemperatureValid() {
        return this.mOutsideTemperatureValid;
    }

    public void setOutsideTemperature(int temperature) {
        this.mOutsideTemperature = temperature;
        this.mOutsideTemperatureValid = true;
    }

    public boolean isOutsideTemperatureValid() {
        return this.mOutsideTemperatureValid;
    }

    public boolean getRangeWarning() {
        return this.mRangeWarning;
    }

    public boolean getRangeWarningValid() {
        return this.mRangeWarningValid;
    }

    public void setRangeWarning(boolean rangeWarning) {
        this.mRangeWarning = rangeWarning;
        this.mRangeWarningValid = true;
    }

    public boolean isRangeWarningValid() {
        return this.mRangeWarningValid;
    }

    public int getRangeGasoline() {
        return this.mRangeGasoline;
    }

    public boolean getRangeGasolineValid() {
        return this.mRangeGasolineValid;
    }

    public void setRangeGasoline(int range) {
        this.mRangeGasoline = range;
        this.mRangeGasolineValid = true;
    }

    public boolean isRangeGasolineValid() {
        return this.mRangeGasolineValid;
    }

    public int getRangeDiesel() {
        return this.mRangeDiesel;
    }

    public boolean getRangeDieselValid() {
        return this.mRangeDieselValid;
    }

    public void setRangeDiesel(int range) {
        this.mRangeDiesel = range;
        this.mRangeDieselValid = true;
    }

    public boolean isRangeDieselValid() {
        return this.mRangeDieselValid;
    }

    public int getRangeElectric() {
        return this.mRangeElectric;
    }

    public boolean getRangeElectricValid() {
        return this.mRangeElectricValid;
    }

    public void setRangeElectric(int range) {
        this.mRangeElectric = range;
        this.mRangeElectricValid = true;
    }

    public boolean isRangeElectricValid() {
        return this.mRangeElectricValid;
    }

    public int getRangeCNG() {
        return this.mRangeCNG;
    }

    public boolean getRangeCNGValid() {
        return this.mRangeCNGValid;
    }

    public void setRangeCNG(int range) {
        this.mRangeCNG = range;
        this.mRangeCNGValid = true;
    }

    public boolean isRangeCNGValid() {
        return this.mRangeCNGValid;
    }

    public boolean getRangeWarningGasoline() {
        return this.mRangeWarningGasoline;
    }

    public boolean getRangeWarningGasolineValid() {
        return this.mRangeWarningGasolineValid;
    }

    public void setRangeWarningGasoline(boolean rangeWarning) {
        this.mRangeWarningGasoline = rangeWarning;
        this.mRangeWarningGasolineValid = true;
    }

    public boolean isRangeWarningGasolineValid() {
        return this.mRangeWarningGasolineValid;
    }

    public boolean getRangeWarningDiesel() {
        return this.mRangeWarningDiesel;
    }

    public boolean getRangeWarningDieselValid() {
        return this.mRangeWarningDieselValid;
    }

    public void setRangeWarningDiesel(boolean rangeWarning) {
        this.mRangeWarningDiesel = rangeWarning;
        this.mRangeWarningDieselValid = true;
    }

    public boolean isRangeWarningDieselValid() {
        return this.mRangeWarningDieselValid;
    }

    public boolean getRangeWarningElectric() {
        return this.mRangeWarningElectric;
    }

    public boolean getRangeWarningElectricValid() {
        return this.mRangeWarningElectricValid;
    }

    public void setRangeWarningElectric(boolean rangeWarning) {
        this.mRangeWarningElectric = rangeWarning;
        this.mRangeWarningElectricValid = true;
    }

    public boolean isRangeWarningElectricValid() {
        return this.mRangeWarningElectricValid;
    }

    public boolean getRangeWarningCNG() {
        return this.mRangeWarningCNG;
    }

    public boolean getRangeWarningCNGValid() {
        return this.mRangeWarningCNGValid;
    }

    public void setRangeWarningCNG(boolean rangeWarning) {
        this.mRangeWarningCNG = rangeWarning;
        this.mRangeWarningCNGValid = true;
    }

    public boolean isRangeWarningCNGValid() {
        return this.mRangeWarningCNGValid;
    }

    public long getMinBatteryCharge() {
        return this.mMinBatteryCharge;
    }

    public boolean getMinBatteryChargeValid() {
        return this.mMinBatteryChargeValid;
    }

    public void setMinBatteryCharge(long battery) {
        this.mMinBatteryCharge = battery;
        this.mMinBatteryChargeValid = true;
    }

    public boolean isMinBatteryChargeValid() {
        return this.mMinBatteryChargeValid;
    }

    public long getCurrentBatteryCharge() {
        return this.mCurrentBatteryCharge;
    }

    public boolean getCurrentBatteryChargeValid() {
        return this.mCurrentBatteryChargeValid;
    }

    public void setCurrentBatteryCharge(long battery) {
        this.mCurrentBatteryCharge = battery;
        this.mCurrentBatteryChargeValid = true;
    }

    public boolean isCurrentBatteryChargeValid() {
        return this.mCurrentBatteryChargeValid;
    }

    public long getMaxBatteryCharge() {
        return this.mMaxBatteryCharge;
    }

    public boolean getMaxBatteryChargeValid() {
        return this.mMaxBatteryChargeValid;
    }

    public void setMaxBatteryCharge(long battery) {
        this.mMaxBatteryCharge = battery;
        this.mMaxBatteryChargeValid = true;
    }

    public boolean isMaxBatteryChargeValid() {
        return this.mMaxBatteryChargeValid;
    }

    public long getDisplayedBatteryPercentage() {
        return this.mDisplayedBatteryPercentage;
    }

    public boolean getDisplayedBatteryPercentageValid() {
        return this.mDisplayedBatteryPercentageValid;
    }

    public void setDisplayedBatteryPercentage(long battery) {
        this.mDisplayedBatteryPercentage = battery;
        this.mDisplayedBatteryPercentageValid = true;
    }

    public boolean isDisplayedBatteryPercentageValid() {
        return this.mDisplayedBatteryPercentageValid;
    }

    public boolean getIsCharging() {
        return this.mIsCharging;
    }

    public boolean getIsChargingValid() {
        return this.mIsChargingValid;
    }

    public void setIsCharging(boolean isCharging) {
        this.mIsCharging = isCharging;
        this.mIsChargingValid = true;
    }

    public boolean isIsChargingValid() {
        return this.mIsChargingValid;
    }

    public String getChargingParameter() {
        return this.mChargingParameter;
    }

    public boolean getChargingParameterValid() {
        return this.mChargingParameterValid;
    }

    public void setChargingParameter(String chargingParam) {
        this.mChargingParameter = chargingParam;
        this.mChargingParameterValid = true;
    }

    public boolean isChargingParameterValid() {
        return this.mChargingParameterValid;
    }

    public String getConsumptionParameter() {
        return this.mConsumptionParameter;
    }

    public boolean getConsumptionParameterValid() {
        return this.mConsumptionParameterValid;
    }

    public void setConsumptionParameter(String consumptionParam) {
        this.mConsumptionParameter = consumptionParam;
        this.mConsumptionParameterValid = true;
    }

    public boolean isConsumptionParameterValid() {
        return this.mConsumptionParameterValid;
    }

    public int getActiveConnector() {
        return this.mActiveConnector;
    }

    public boolean getActiveConnectorValid() {
        return this.mActiveConnectorValid;
    }

    public void setActiveConnector(int activeConnector) {
        this.mActiveConnector = activeConnector;
        this.mActiveConnectorValid = true;
    }

    public boolean isActiveConnectorValid() {
        return this.mActiveConnectorValid;
    }

    public int getMaxRangeElectric() {
        return this.mMaxRangeElectric;
    }

    public boolean getMaxRangeElectricValid() {
        return this.mMaxRangeElectricValid;
    }

    public void setMaxRangeElectric(int range) {
        this.mMaxRangeElectric = range;
        this.mMaxRangeElectricValid = true;
    }

    public boolean isMaxRangeElectricValid() {
        return this.mMaxRangeElectricValid;
    }

    public String toString() {
        return "VehicleStatusUpdate [mRange=" + this.mRange + ", mOutsideTemperature=" + this.mOutsideTemperature + ", mRangeWarning=" + this.mRangeWarning + ", mRangeGasoline=" + this.mRangeGasoline + ", mRangeDiesel=" + this.mRangeDiesel + ", mRangeElectric=" + this.mRangeElectric + ", mRangeCNG=" + this.mRangeCNG + ", mRangeWarningGasoline=" + this.mRangeWarningGasoline + ", mRangeWarningDiesel=" + this.mRangeWarningDiesel + ", mRangeWarningElectric=" + this.mRangeWarningElectric + ", mRangeWarningCNG=" + this.mRangeWarningCNG + ", mMinBatteryCharge=" + this.mMinBatteryCharge + ", mCurrentBatteryCharge=" + this.mCurrentBatteryCharge + ", mMaxBatteryCharge=" + this.mMaxBatteryCharge + ", mDisplayedBatteryPercentage=" + this.mDisplayedBatteryPercentage + ", mIsCharging=" + this.mIsCharging + ", mChargingParameter=" + this.mChargingParameter + ", mConsumptionParameter=" + this.mConsumptionParameter + ", mActiveConnector=" + this.mActiveConnector + ", mMaxRangeElectric=" + this.mMaxRangeElectric + "]";
    }
}
