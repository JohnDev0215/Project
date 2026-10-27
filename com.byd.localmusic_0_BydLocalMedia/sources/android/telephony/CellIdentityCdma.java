package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.cdma.CdmaCellLocation;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellIdentityCdma extends CellIdentity {
    private static final int BASESTATION_ID_MAX = 65535;
    private static final boolean DBG = false;
    private static final int LATITUDE_MAX = 1296000;
    private static final int LATITUDE_MIN = -1296000;
    private static final int LONGITUDE_MAX = 2592000;
    private static final int LONGITUDE_MIN = -2592000;
    private static final int NETWORK_ID_MAX = 65535;
    private static final int SYSTEM_ID_MAX = 32767;
    private final int mBasestationId;
    private final int mLatitude;
    private final int mLongitude;
    private final int mNetworkId;
    private final int mSystemId;
    private static final String TAG = CellIdentityCdma.class.getSimpleName();
    public static final Parcelable.Creator<CellIdentityCdma> CREATOR = new Parcelable.Creator<CellIdentityCdma>() { // from class: android.telephony.CellIdentityCdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellIdentityCdma createFromParcel(Parcel parcel) {
            parcel.readInt();
            return CellIdentityCdma.createFromParcelBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellIdentityCdma[] newArray(int i) {
            return new CellIdentityCdma[i];
        }
    };

    public CellIdentityCdma() {
        super(TAG, 2, null, null, null, null);
        this.mNetworkId = Integer.MAX_VALUE;
        this.mSystemId = Integer.MAX_VALUE;
        this.mBasestationId = Integer.MAX_VALUE;
        this.mLongitude = Integer.MAX_VALUE;
        this.mLatitude = Integer.MAX_VALUE;
        this.mGlobalCellId = null;
    }

    public CellIdentityCdma(int i, int i2, int i3, int i4, int i5, String str, String str2) {
        super(TAG, 2, null, null, str, str2);
        this.mNetworkId = inRangeOrUnavailable(i, 0, 65535);
        this.mSystemId = inRangeOrUnavailable(i2, 0, 32767);
        this.mBasestationId = inRangeOrUnavailable(i3, 0, 65535);
        int iInRangeOrUnavailable = inRangeOrUnavailable(i5, LATITUDE_MIN, LATITUDE_MAX);
        int iInRangeOrUnavailable2 = inRangeOrUnavailable(i4, LONGITUDE_MIN, LONGITUDE_MAX);
        if (!isNullIsland(iInRangeOrUnavailable, iInRangeOrUnavailable2)) {
            this.mLongitude = iInRangeOrUnavailable2;
            this.mLatitude = iInRangeOrUnavailable;
        } else {
            this.mLatitude = Integer.MAX_VALUE;
            this.mLongitude = Integer.MAX_VALUE;
        }
        updateGlobalCellId();
    }

    public CellIdentityCdma(android.hardware.radio.V1_0.CellIdentityCdma cellIdentityCdma) {
        this(cellIdentityCdma.networkId, cellIdentityCdma.systemId, cellIdentityCdma.baseStationId, cellIdentityCdma.longitude, cellIdentityCdma.latitude, "", "");
    }

    public CellIdentityCdma(android.hardware.radio.V1_2.CellIdentityCdma cellIdentityCdma) {
        this(cellIdentityCdma.base.networkId, cellIdentityCdma.base.systemId, cellIdentityCdma.base.baseStationId, cellIdentityCdma.base.longitude, cellIdentityCdma.base.latitude, cellIdentityCdma.operatorNames.alphaLong, cellIdentityCdma.operatorNames.alphaShort);
    }

    private CellIdentityCdma(CellIdentityCdma cellIdentityCdma) {
        this(cellIdentityCdma.mNetworkId, cellIdentityCdma.mSystemId, cellIdentityCdma.mBasestationId, cellIdentityCdma.mLongitude, cellIdentityCdma.mLatitude, cellIdentityCdma.mAlphaLong, cellIdentityCdma.mAlphaShort);
    }

    CellIdentityCdma copy() {
        return new CellIdentityCdma(this);
    }

    @Override // android.telephony.CellIdentity
    public CellIdentityCdma sanitizeLocationInfo() {
        return new CellIdentityCdma(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, this.mAlphaLong, this.mAlphaShort);
    }

    @Override // android.telephony.CellIdentity
    protected void updateGlobalCellId() {
        int i;
        this.mGlobalCellId = null;
        if (this.mNetworkId == Integer.MAX_VALUE || (i = this.mSystemId) == Integer.MAX_VALUE || this.mBasestationId == Integer.MAX_VALUE) {
            return;
        }
        this.mGlobalCellId = String.format("%04x%04x%04x", Integer.valueOf(i), Integer.valueOf(this.mNetworkId), Integer.valueOf(this.mBasestationId));
    }

    private boolean isNullIsland(int i, int i2) {
        return Math.abs(i) <= 1 && Math.abs(i2) <= 1;
    }

    public int getNetworkId() {
        return this.mNetworkId;
    }

    public int getSystemId() {
        return this.mSystemId;
    }

    public int getBasestationId() {
        return this.mBasestationId;
    }

    public int getLongitude() {
        return this.mLongitude;
    }

    public int getLatitude() {
        return this.mLatitude;
    }

    @Override // android.telephony.CellIdentity
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mNetworkId), Integer.valueOf(this.mSystemId), Integer.valueOf(this.mBasestationId), Integer.valueOf(this.mLatitude), Integer.valueOf(this.mLongitude), Integer.valueOf(super.hashCode()));
    }

    @Override // android.telephony.CellIdentity
    public CdmaCellLocation asCellLocation() {
        CdmaCellLocation cdmaCellLocation = new CdmaCellLocation();
        int i = this.mBasestationId;
        int i2 = i != Integer.MAX_VALUE ? i : -1;
        int i3 = this.mSystemId;
        int i4 = i3 != Integer.MAX_VALUE ? i3 : -1;
        int i5 = this.mNetworkId;
        cdmaCellLocation.setCellLocationData(i2, this.mLatitude, this.mLongitude, i4, i5 != Integer.MAX_VALUE ? i5 : -1);
        return cdmaCellLocation;
    }

    @Override // android.telephony.CellIdentity
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CellIdentityCdma)) {
            return false;
        }
        CellIdentityCdma cellIdentityCdma = (CellIdentityCdma) obj;
        return this.mNetworkId == cellIdentityCdma.mNetworkId && this.mSystemId == cellIdentityCdma.mSystemId && this.mBasestationId == cellIdentityCdma.mBasestationId && this.mLatitude == cellIdentityCdma.mLatitude && this.mLongitude == cellIdentityCdma.mLongitude && super.equals(obj);
    }

    public String toString() {
        return TAG + ":{ mNetworkId=" + this.mNetworkId + " mSystemId=" + this.mSystemId + " mBasestationId=" + this.mBasestationId + " mLongitude=" + this.mLongitude + " mLatitude=" + this.mLatitude + " mAlphaLong=" + this.mAlphaLong + " mAlphaShort=" + this.mAlphaShort + "}";
    }

    @Override // android.telephony.CellIdentity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, 2);
        parcel.writeInt(this.mNetworkId);
        parcel.writeInt(this.mSystemId);
        parcel.writeInt(this.mBasestationId);
        parcel.writeInt(this.mLongitude);
        parcel.writeInt(this.mLatitude);
    }

    private CellIdentityCdma(Parcel parcel) {
        super(TAG, 2, parcel);
        this.mNetworkId = parcel.readInt();
        this.mSystemId = parcel.readInt();
        this.mBasestationId = parcel.readInt();
        this.mLongitude = parcel.readInt();
        this.mLatitude = parcel.readInt();
        updateGlobalCellId();
    }

    protected static CellIdentityCdma createFromParcelBody(Parcel parcel) {
        return new CellIdentityCdma(parcel);
    }
}
