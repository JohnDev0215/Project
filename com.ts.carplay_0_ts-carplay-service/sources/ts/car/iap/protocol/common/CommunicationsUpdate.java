package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CommunicationsUpdate {
    private String mCarrierName;
    private int mSignalStrength = -1;
    private int mRegistrationStatus = -1;
    private int mAirplaneModeStatus = -1;
    private boolean mCarrierNameValid = false;
    private int mCellularSupported = -1;
    private int mTelephonyEnabled = -1;
    private int mFaceTimeAudioEnabled = -1;
    private int mFaceTimeVideoEnabled = -1;
    private int mMuteStatus = -1;
    private int mCurrentCallCount = -1;
    private int mNewVoicemailCount = -1;
    private int mInitiateCallAvailable = -1;
    private int mEndAndAcceptAvailable = -1;
    private int mHoldAndAcceptAvailable = -1;
    private int mSwapAvailable = -1;
    private int mMergeAvailable = -1;
    private int mHoldAvailable = -1;

    public int getSignalStrength() {
        return this.mSignalStrength;
    }

    public void setSignalStrength(int strength) {
        this.mSignalStrength = strength;
    }

    public boolean isSignalStrengthValid() {
        return -1 != this.mSignalStrength;
    }

    public int getRegistrationStatus() {
        return this.mRegistrationStatus;
    }

    public void setRegistrationStatus(int status) {
        this.mRegistrationStatus = status;
    }

    public boolean isRegistrationStatusValid() {
        return -1 != this.mRegistrationStatus;
    }

    public int getAirplaneModeStatus() {
        return this.mAirplaneModeStatus;
    }

    public void setAirplaneModeStatus(int status) {
        this.mAirplaneModeStatus = status;
    }

    public boolean isAirplaneModeValid() {
        return -1 != this.mAirplaneModeStatus;
    }

    public String getCarrierName() {
        return this.mCarrierName;
    }

    public void setCarrierName(String name) {
        this.mCarrierName = name;
        this.mCarrierNameValid = true;
    }

    public boolean isCarrierNameValid() {
        return this.mCarrierNameValid;
    }

    public int getCellularSupported() {
        return this.mCellularSupported;
    }

    public void setCellularSupported(int supported) {
        this.mCellularSupported = supported;
    }

    public boolean isCellularSupportedValid() {
        return -1 != this.mCellularSupported;
    }

    public int getTelephonyEnabled() {
        return this.mTelephonyEnabled;
    }

    public void setTelephonyEnabled(int enabled) {
        this.mTelephonyEnabled = enabled;
    }

    public boolean isTelephonyEnabledValid() {
        return -1 != this.mTelephonyEnabled;
    }

    public int getFaceTimeAudioEnabled() {
        return this.mFaceTimeAudioEnabled;
    }

    public void setFaceTimeAudioEnabled(int enabled) {
        this.mFaceTimeAudioEnabled = enabled;
    }

    public boolean isFaceTimeAudioEnabledValid() {
        return -1 != this.mFaceTimeAudioEnabled;
    }

    public int getFaceTimeVideoEnabled() {
        return this.mFaceTimeVideoEnabled;
    }

    public void setFaceTimeVideoEnabled(int enabled) {
        this.mFaceTimeVideoEnabled = enabled;
    }

    public boolean isFaceTimeVideoEnabledValid() {
        return -1 != this.mFaceTimeVideoEnabled;
    }

    public int getMuteStatus() {
        return this.mMuteStatus;
    }

    public void setMuteStatus(int status) {
        this.mMuteStatus = status;
    }

    public boolean isMuteStatusValid() {
        return -1 != this.mMuteStatus;
    }

    public int getCurrentCallCount() {
        return this.mCurrentCallCount;
    }

    public void setCurrentCallCount(int count) {
        this.mCurrentCallCount = count;
    }

    public boolean isCurrentCallCountValid() {
        return -1 != this.mCurrentCallCount;
    }

    public int getNewVoicemailCount() {
        return this.mNewVoicemailCount;
    }

    public void setNewVoicemailCount(int count) {
        this.mNewVoicemailCount = count;
    }

    public boolean isNewVoicemailCountValid() {
        return -1 != this.mNewVoicemailCount;
    }

    public int getInitiateCallAvailable() {
        return this.mInitiateCallAvailable;
    }

    public void setInitiateCallAvailable(int available) {
        this.mInitiateCallAvailable = available;
    }

    public boolean isInitiateCallAvailableValid() {
        return -1 != this.mInitiateCallAvailable;
    }

    public int getEndAndAcceptAvailable() {
        return this.mEndAndAcceptAvailable;
    }

    public void setEndAndAcceptAvailable(int available) {
        this.mEndAndAcceptAvailable = available;
    }

    public boolean isEndAndAcceptAvailableValid() {
        return -1 != this.mEndAndAcceptAvailable;
    }

    public int getHoldAndAcceptAvailable() {
        return this.mHoldAndAcceptAvailable;
    }

    public void setHoldAndAcceptAvailable(int available) {
        this.mHoldAndAcceptAvailable = available;
    }

    public boolean isHoldAndAcceptAvailableValid() {
        return -1 != this.mHoldAndAcceptAvailable;
    }

    public int getSwapAvailable() {
        return this.mSwapAvailable;
    }

    public void setSwapAvailable(int available) {
        this.mSwapAvailable = available;
    }

    public boolean isSwapAvailableValid() {
        return -1 != this.mSwapAvailable;
    }

    public int getMergeAvailable() {
        return this.mMergeAvailable;
    }

    public void setMergeAvailable(int available) {
        this.mMergeAvailable = available;
    }

    public boolean isMergeAvailableValid() {
        return -1 != this.mMergeAvailable;
    }

    public int getHoldAvailable() {
        return this.mHoldAvailable;
    }

    public void setHoldAvailable(int available) {
        this.mHoldAvailable = available;
    }

    public boolean isHoldAvailableValid() {
        return -1 != this.mHoldAvailable;
    }
}
