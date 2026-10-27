package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DestinationInformation {
    private static final String TAG = "DestinationInformation";
    private String mAddress;
    private String mDestinationIdentifier;
    private String mDisplayName;
    private String mLocale;
    private String mSourceName;
    private final List<DestinationInformationCoordinate> mDestinationInformationCoordinates = new ArrayList();
    private boolean mDestinationIdentifierValid = false;
    private boolean mDisplayNameValid = false;
    private boolean mCenterCoordinateLatitudeValid = false;
    private boolean mCenterCoordinateLongitudeValid = false;
    private boolean mAddressValid = false;
    private boolean mCoordinateThresholdValid = false;
    private boolean mLocaleValid = false;
    private boolean mSourceNameValid = false;
    private int mCenterCoordinateLatitude = -1;
    private int mCenterCoordinateLongitude = -1;
    private long mCoordinateThreshold = -1;

    public String getDestinationIndentifier() {
        return this.mDestinationIdentifier;
    }

    public void setDestinationIdentifier(String destinationIdentifier) {
        this.mDestinationIdentifier = destinationIdentifier;
        this.mDestinationIdentifierValid = true;
    }

    public boolean isDestinationIdentifierValid() {
        return this.mDestinationIdentifierValid;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
        this.mDisplayNameValid = true;
    }

    public boolean isDisplayNameValid() {
        return this.mDisplayNameValid;
    }

    public int getCenterCoordinateLatitude() {
        return this.mCenterCoordinateLatitude;
    }

    public void setCenterCoordinateLatitude(int centerCoordinateLatitude) {
        this.mCenterCoordinateLatitude = centerCoordinateLatitude;
        this.mCenterCoordinateLatitudeValid = true;
    }

    public boolean isCenterCoordinateLatitudeValid() {
        return this.mCenterCoordinateLatitudeValid;
    }

    public int getCenterCoordinateLongitude() {
        return this.mCenterCoordinateLongitude;
    }

    public void setCenterCoordinateLongitude(int centerCoordinateLongitude) {
        this.mCenterCoordinateLongitude = centerCoordinateLongitude;
        this.mCenterCoordinateLongitudeValid = true;
    }

    public boolean isCenterCoordinateLongitudeValid() {
        return this.mCenterCoordinateLongitudeValid;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
        this.mAddressValid = true;
    }

    public boolean isAddressValid() {
        return this.mAddressValid;
    }

    public long getCoordinateThreshold() {
        return this.mCoordinateThreshold;
    }

    public void setCoordinateThreshold(long coordinateThreshold) {
        this.mCoordinateThreshold = coordinateThreshold;
        this.mCoordinateThresholdValid = true;
    }

    public boolean isCoordinateThresholdValid() {
        return this.mCoordinateThresholdValid;
    }

    public String getLocale() {
        return this.mLocale;
    }

    public void setLocale(String locale) {
        this.mLocale = locale;
        this.mLocaleValid = true;
    }

    public boolean isLocaleValid() {
        return this.mLocaleValid;
    }

    public String getSourceName() {
        return this.mSourceName;
    }

    public void setSourceName(String sourceName) {
        this.mSourceName = sourceName;
        this.mSourceNameValid = true;
    }

    public boolean isSourceNameValid() {
        return this.mSourceNameValid;
    }

    public void addEntryPoint(DestinationInformationCoordinate entryPoint) {
        if (entryPoint == null) {
            return;
        }
        this.mDestinationInformationCoordinates.add(entryPoint);
    }

    public List<DestinationInformationCoordinate> getEntryPointList() {
        return this.mDestinationInformationCoordinates;
    }

    public String toString() {
        return "DestinationInformation [mDestinationIdentifier=" + this.mDestinationIdentifier + ", mDisplayName=" + this.mDisplayName + ", mCenterCoordinateLatitude=" + this.mCenterCoordinateLatitude + ", mCenterCoordinateLongitude=" + this.mCenterCoordinateLongitude + ", mAddress=" + this.mAddress + ", mDestinationInformationCoordinates=" + this.mDestinationInformationCoordinates + ", mCoordinateThreshold=" + this.mCoordinateThreshold + ", mLocale=" + this.mLocale + ", mSourceName=" + this.mSourceName + "]";
    }
}
