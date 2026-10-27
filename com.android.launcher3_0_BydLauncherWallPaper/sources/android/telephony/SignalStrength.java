package android.telephony;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SignalStrength implements Parcelable {
    public static final Parcelable.Creator<SignalStrength> CREATOR = new Parcelable.Creator<SignalStrength>() { // from class: android.telephony.SignalStrength.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignalStrength createFromParcel(Parcel parcel) {
            return new SignalStrength(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignalStrength[] newArray(int i) {
            return new SignalStrength[i];
        }
    };
    private static final boolean DBG = false;
    public static final int INVALID = Integer.MAX_VALUE;
    private static final String LOG_TAG = "SignalStrength";
    private static final int LTE_RSRP_THRESHOLDS_NUM = 4;
    private static final String MEASUREMENT_TYPE_RSCP = "rscp";
    public static final int NUM_SIGNAL_STRENGTH_BINS = 5;
    public static final int SIGNAL_STRENGTH_GOOD = 3;
    public static final int SIGNAL_STRENGTH_GREAT = 4;
    public static final int SIGNAL_STRENGTH_MODERATE = 2;
    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN = 0;
    public static final int SIGNAL_STRENGTH_POOR = 1;
    private static final int WCDMA_RSCP_THRESHOLDS_NUM = 4;
    CellSignalStrengthCdma mCdma;
    CellSignalStrengthGsm mGsm;
    CellSignalStrengthLte mLte;
    private boolean mLteAsPrimaryInNrNsa;
    CellSignalStrengthNr mNr;
    CellSignalStrengthTdscdma mTdscdma;
    private long mTimestampMillis;
    CellSignalStrengthWcdma mWcdma;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static SignalStrength newFromBundle(Bundle bundle) {
        SignalStrength signalStrength = new SignalStrength();
        signalStrength.setFromNotifierBundle(bundle);
        return signalStrength;
    }

    public SignalStrength() {
        this(new CellSignalStrengthCdma(), new CellSignalStrengthGsm(), new CellSignalStrengthWcdma(), new CellSignalStrengthTdscdma(), new CellSignalStrengthLte(), new CellSignalStrengthNr());
    }

    public SignalStrength(CellSignalStrengthCdma cellSignalStrengthCdma, CellSignalStrengthGsm cellSignalStrengthGsm, CellSignalStrengthWcdma cellSignalStrengthWcdma, CellSignalStrengthTdscdma cellSignalStrengthTdscdma, CellSignalStrengthLte cellSignalStrengthLte, CellSignalStrengthNr cellSignalStrengthNr) {
        this.mLteAsPrimaryInNrNsa = true;
        this.mCdma = cellSignalStrengthCdma;
        this.mGsm = cellSignalStrengthGsm;
        this.mWcdma = cellSignalStrengthWcdma;
        this.mTdscdma = cellSignalStrengthTdscdma;
        this.mLte = cellSignalStrengthLte;
        this.mNr = cellSignalStrengthNr;
        this.mTimestampMillis = SystemClock.elapsedRealtime();
    }

    public SignalStrength(android.hardware.radio.V1_0.SignalStrength signalStrength) {
        this(new CellSignalStrengthCdma(signalStrength.cdma, signalStrength.evdo), new CellSignalStrengthGsm(signalStrength.gw), new CellSignalStrengthWcdma(), new CellSignalStrengthTdscdma(signalStrength.tdScdma), new CellSignalStrengthLte(signalStrength.lte), new CellSignalStrengthNr());
    }

    public SignalStrength(android.hardware.radio.V1_2.SignalStrength signalStrength) {
        this(new CellSignalStrengthCdma(signalStrength.cdma, signalStrength.evdo), new CellSignalStrengthGsm(signalStrength.gsm), new CellSignalStrengthWcdma(signalStrength.wcdma), new CellSignalStrengthTdscdma(signalStrength.tdScdma), new CellSignalStrengthLte(signalStrength.lte), new CellSignalStrengthNr());
    }

    public SignalStrength(android.hardware.radio.V1_4.SignalStrength signalStrength) {
        this(new CellSignalStrengthCdma(signalStrength.cdma, signalStrength.evdo), new CellSignalStrengthGsm(signalStrength.gsm), new CellSignalStrengthWcdma(signalStrength.wcdma), new CellSignalStrengthTdscdma(signalStrength.tdscdma), new CellSignalStrengthLte(signalStrength.lte), new CellSignalStrengthNr(signalStrength.nr));
    }

    private CellSignalStrength getPrimary() {
        return (this.mLteAsPrimaryInNrNsa && this.mLte.isValid()) ? this.mLte : this.mNr.isValid() ? this.mNr : this.mLte.isValid() ? this.mLte : this.mCdma.isValid() ? this.mCdma : this.mTdscdma.isValid() ? this.mTdscdma : this.mWcdma.isValid() ? this.mWcdma : this.mGsm.isValid() ? this.mGsm : this.mLte;
    }

    public List<CellSignalStrength> getCellSignalStrengths() {
        return getCellSignalStrengths(CellSignalStrength.class);
    }

    public <T extends CellSignalStrength> List<T> getCellSignalStrengths(Class<T> cls) {
        ArrayList arrayList = new ArrayList(2);
        if (this.mLte.isValid() && cls.isAssignableFrom(CellSignalStrengthLte.class)) {
            arrayList.add(this.mLte);
        }
        if (this.mCdma.isValid() && cls.isAssignableFrom(CellSignalStrengthCdma.class)) {
            arrayList.add(this.mCdma);
        }
        if (this.mTdscdma.isValid() && cls.isAssignableFrom(CellSignalStrengthTdscdma.class)) {
            arrayList.add(this.mTdscdma);
        }
        if (this.mWcdma.isValid() && cls.isAssignableFrom(CellSignalStrengthWcdma.class)) {
            arrayList.add(this.mWcdma);
        }
        if (this.mGsm.isValid() && cls.isAssignableFrom(CellSignalStrengthGsm.class)) {
            arrayList.add(this.mGsm);
        }
        if (this.mNr.isValid() && cls.isAssignableFrom(CellSignalStrengthNr.class)) {
            arrayList.add(this.mNr);
        }
        return arrayList;
    }

    public void updateLevel(PersistableBundle persistableBundle, ServiceState serviceState) {
        if (persistableBundle != null) {
            this.mLteAsPrimaryInNrNsa = persistableBundle.getBoolean(CarrierConfigManager.KEY_SIGNAL_STRENGTH_NR_NSA_USE_LTE_AS_PRIMARY_BOOL, true);
        }
        this.mCdma.updateLevel(persistableBundle, serviceState);
        this.mGsm.updateLevel(persistableBundle, serviceState);
        this.mWcdma.updateLevel(persistableBundle, serviceState);
        this.mTdscdma.updateLevel(persistableBundle, serviceState);
        this.mLte.updateLevel(persistableBundle, serviceState);
        this.mNr.updateLevel(persistableBundle, serviceState);
    }

    public SignalStrength(SignalStrength signalStrength) {
        this.mLteAsPrimaryInNrNsa = true;
        copyFrom(signalStrength);
    }

    protected void copyFrom(SignalStrength signalStrength) {
        this.mCdma = new CellSignalStrengthCdma(signalStrength.mCdma);
        this.mGsm = new CellSignalStrengthGsm(signalStrength.mGsm);
        this.mWcdma = new CellSignalStrengthWcdma(signalStrength.mWcdma);
        this.mTdscdma = new CellSignalStrengthTdscdma(signalStrength.mTdscdma);
        this.mLte = new CellSignalStrengthLte(signalStrength.mLte);
        this.mNr = new CellSignalStrengthNr(signalStrength.mNr);
        this.mTimestampMillis = signalStrength.getTimestampMillis();
    }

    public SignalStrength(Parcel parcel) {
        this.mLteAsPrimaryInNrNsa = true;
        this.mCdma = (CellSignalStrengthCdma) parcel.readParcelable(CellSignalStrengthCdma.class.getClassLoader());
        this.mGsm = (CellSignalStrengthGsm) parcel.readParcelable(CellSignalStrengthGsm.class.getClassLoader());
        this.mWcdma = (CellSignalStrengthWcdma) parcel.readParcelable(CellSignalStrengthWcdma.class.getClassLoader());
        this.mTdscdma = (CellSignalStrengthTdscdma) parcel.readParcelable(CellSignalStrengthTdscdma.class.getClassLoader());
        this.mLte = (CellSignalStrengthLte) parcel.readParcelable(CellSignalStrengthLte.class.getClassLoader());
        this.mNr = (CellSignalStrengthNr) parcel.readParcelable(CellSignalStrengthLte.class.getClassLoader());
        this.mTimestampMillis = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCdma, i);
        parcel.writeParcelable(this.mGsm, i);
        parcel.writeParcelable(this.mWcdma, i);
        parcel.writeParcelable(this.mTdscdma, i);
        parcel.writeParcelable(this.mLte, i);
        parcel.writeParcelable(this.mNr, i);
        parcel.writeLong(this.mTimestampMillis);
    }

    public long getTimestampMillis() {
        return this.mTimestampMillis;
    }

    @Deprecated
    public int getGsmSignalStrength() {
        return this.mGsm.getAsuLevel();
    }

    @Deprecated
    public int getGsmBitErrorRate() {
        return this.mGsm.getBitErrorRate();
    }

    @Deprecated
    public int getCdmaDbm() {
        return this.mCdma.getCdmaDbm();
    }

    @Deprecated
    public int getCdmaEcio() {
        return this.mCdma.getCdmaEcio();
    }

    @Deprecated
    public int getEvdoDbm() {
        return this.mCdma.getEvdoDbm();
    }

    @Deprecated
    public int getEvdoEcio() {
        return this.mCdma.getEvdoEcio();
    }

    @Deprecated
    public int getEvdoSnr() {
        return this.mCdma.getEvdoSnr();
    }

    @Deprecated
    public int getLteSignalStrength() {
        return this.mLte.getRssi();
    }

    @Deprecated
    public int getLteRsrp() {
        return this.mLte.getRsrp();
    }

    @Deprecated
    public int getLteRsrq() {
        return this.mLte.getRsrq();
    }

    @Deprecated
    public int getLteRssnr() {
        return this.mLte.getRssnr();
    }

    @Deprecated
    public int getLteCqi() {
        return this.mLte.getCqi();
    }

    public int getLevel() {
        int level = getPrimary().getLevel();
        if (level < 0 || level > 4) {
            loge("Invalid Level " + level + ", this=" + this);
            return 0;
        }
        return getPrimary().getLevel();
    }

    @Deprecated
    public int getAsuLevel() {
        return getPrimary().getAsuLevel();
    }

    @Deprecated
    public int getDbm() {
        return getPrimary().getDbm();
    }

    @Deprecated
    public int getGsmDbm() {
        return this.mGsm.getDbm();
    }

    @Deprecated
    public int getGsmLevel() {
        return this.mGsm.getLevel();
    }

    @Deprecated
    public int getGsmAsuLevel() {
        return this.mGsm.getAsuLevel();
    }

    @Deprecated
    public int getCdmaLevel() {
        return this.mCdma.getLevel();
    }

    @Deprecated
    public int getCdmaAsuLevel() {
        return this.mCdma.getAsuLevel();
    }

    @Deprecated
    public int getEvdoLevel() {
        return this.mCdma.getEvdoLevel();
    }

    @Deprecated
    public int getEvdoAsuLevel() {
        return this.mCdma.getEvdoAsuLevel();
    }

    @Deprecated
    public int getLteDbm() {
        return this.mLte.getRsrp();
    }

    @Deprecated
    public int getLteLevel() {
        return this.mLte.getLevel();
    }

    @Deprecated
    public int getLteAsuLevel() {
        return this.mLte.getAsuLevel();
    }

    @Deprecated
    public boolean isGsm() {
        return !(getPrimary() instanceof CellSignalStrengthCdma);
    }

    @Deprecated
    public int getTdScdmaDbm() {
        return this.mTdscdma.getRscp();
    }

    @Deprecated
    public int getTdScdmaLevel() {
        return this.mTdscdma.getLevel();
    }

    @Deprecated
    public int getTdScdmaAsuLevel() {
        return this.mTdscdma.getAsuLevel();
    }

    @Deprecated
    public int getWcdmaRscp() {
        return this.mWcdma.getRscp();
    }

    @Deprecated
    public int getWcdmaAsuLevel() {
        return this.mWcdma.getAsuLevel();
    }

    @Deprecated
    public int getWcdmaDbm() {
        return this.mWcdma.getDbm();
    }

    @Deprecated
    public int getWcdmaLevel() {
        return this.mWcdma.getLevel();
    }

    public int hashCode() {
        return Objects.hash(this.mCdma, this.mGsm, this.mWcdma, this.mTdscdma, this.mLte, this.mNr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignalStrength)) {
            return false;
        }
        SignalStrength signalStrength = (SignalStrength) obj;
        return this.mCdma.equals(signalStrength.mCdma) && this.mGsm.equals(signalStrength.mGsm) && this.mWcdma.equals(signalStrength.mWcdma) && this.mTdscdma.equals(signalStrength.mTdscdma) && this.mLte.equals(signalStrength.mLte) && this.mNr.equals(signalStrength.mNr);
    }

    public String toString() {
        return "SignalStrength:{mCdma=" + this.mCdma + ",mGsm=" + this.mGsm + ",mWcdma=" + this.mWcdma + ",mTdscdma=" + this.mTdscdma + ",mLte=" + this.mLte + ",mNr=" + this.mNr + ",primary=" + getPrimary().getClass().getSimpleName() + "}";
    }

    @Deprecated
    private void setFromNotifierBundle(Bundle bundle) {
        this.mCdma = (CellSignalStrengthCdma) bundle.getParcelable("Cdma");
        this.mGsm = (CellSignalStrengthGsm) bundle.getParcelable("Gsm");
        this.mWcdma = (CellSignalStrengthWcdma) bundle.getParcelable("Wcdma");
        this.mTdscdma = (CellSignalStrengthTdscdma) bundle.getParcelable("Tdscdma");
        this.mLte = (CellSignalStrengthLte) bundle.getParcelable("Lte");
        this.mNr = (CellSignalStrengthNr) bundle.getParcelable("Nr");
    }

    @Deprecated
    public void fillInNotifierBundle(Bundle bundle) {
        bundle.putParcelable("Cdma", this.mCdma);
        bundle.putParcelable("Gsm", this.mGsm);
        bundle.putParcelable("Wcdma", this.mWcdma);
        bundle.putParcelable("Tdscdma", this.mTdscdma);
        bundle.putParcelable("Lte", this.mLte);
        bundle.putParcelable("Nr", this.mNr);
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }

    private static void loge(String str) {
        com.android.telephony.Rlog.e(LOG_TAG, str);
    }
}
