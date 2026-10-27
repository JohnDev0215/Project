package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DestinationInformationStatus {
    private static final String TAG = "DestinationInformationStatus";
    private String mAddress;
    private boolean mAddressValid;
    private String mDestinationIdentifier;
    private final List<DestinationInformationCoordinate> mDestinationInformationStatusCoordinates = new ArrayList();
    private boolean mDestinationIdentifierValid = false;
    private boolean mFailureOccurredValid = false;
    private boolean mCenterCoordinateLatitudeValid = false;
    private boolean mCenterCoordinateLongitudeValid = false;
    private int mFailureOccurred = -1;
    private int mCenterCoordinateLatitude = -1;
    private int mCenterCoordinateLongitude = -1;

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

    public int getFailureOccurred() {
        return this.mFailureOccurred;
    }

    public void setFailureOccurred(int failureOccurred) {
        this.mFailureOccurred = failureOccurred;
        this.mFailureOccurredValid = true;
    }

    public boolean isFailureOccurredValid() {
        return this.mFailureOccurredValid;
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

    public void addEntryPoint(DestinationInformationCoordinate entryPoint) {
        if (entryPoint == null) {
            return;
        }
        this.mDestinationInformationStatusCoordinates.add(entryPoint);
    }

    public List<DestinationInformationCoordinate> getEntryPointList() {
        return this.mDestinationInformationStatusCoordinates;
    }

    public String toString() {
        return "DestinationInformation [mDestinationIdentifier=" + this.mDestinationIdentifier + ", mCenterCoordinateLatitude=" + this.mCenterCoordinateLatitude + ", mCenterCoordinateLongitude=" + this.mCenterCoordinateLongitude + ", mAddress=" + this.mAddress + ", mDestinationInformationCoordinates=" + this.mDestinationInformationStatusCoordinates + "]";
    }
}
