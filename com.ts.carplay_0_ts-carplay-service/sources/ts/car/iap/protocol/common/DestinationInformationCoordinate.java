package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class DestinationInformationCoordinate {
    private int mLatitude;
    private boolean mLatitudeValid;
    private int mLongitude;
    private boolean mLongitudeValid;

    public void setLatitudeValid(boolean valid) {
        this.mLatitudeValid = valid;
    }

    public boolean getLatitudeValid() {
        return this.mLatitudeValid;
    }

    public void setLatitude(int latitude) {
        this.mLatitude = latitude;
        this.mLatitudeValid = true;
    }

    public int getLatitude() {
        return this.mLatitude;
    }

    public void setLongitudeValid(boolean valid) {
        this.mLongitudeValid = valid;
    }

    public boolean getLongitudeValid() {
        return this.mLongitudeValid;
    }

    public void setLongitude(int longitude) {
        this.mLongitude = longitude;
        this.mLongitudeValid = true;
    }

    public int getLongitude() {
        return this.mLongitude;
    }

    public String toString() {
        return "DestinationInformationCoordinate [mLatitudeValid=" + this.mLatitudeValid + ", mLatitude=" + this.mLatitude + ", mLongitudeValid=" + this.mLongitudeValid + ", mLongitude=" + this.mLongitude + "]";
    }
}
