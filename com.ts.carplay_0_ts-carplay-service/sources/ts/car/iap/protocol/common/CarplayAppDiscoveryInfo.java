package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CarplayAppDiscoveryInfo {
    public static final int CARPLAY_ALL_CARPLAY_APPS = 1;
    public static final int CARPLAY_AUDIO = 16;
    public static final int CARPLAY_AUTOMAKER = 32;
    public static final int CARPLAY_CALLING = 4;
    public static final int CARPLAY_EV_CHARGING = 128;
    public static final int CARPLAY_MESSAGING = 2;
    public static final int CARPLAY_NAVIGATION = 8;
    public static final int CARPLAY_PARKING = 256;
    public static final int CARPLAY_PRODUCTIVITY = 512;
    public static final int CARPLAY_QUICK_ORDERING = 64;
    private int mCarPlayAppCategories;
    private int mCarPlayAppIconSize;
    private int mCarPlayAppListMax;

    public int getAppCategories() {
        return this.mCarPlayAppCategories;
    }

    public void setCarPlayAppCategories(int categories) {
        this.mCarPlayAppCategories = categories;
    }

    public int getCarPlayAppListMax() {
        return this.mCarPlayAppListMax;
    }

    public void setCarPlayAppListMax(int listMax) {
        this.mCarPlayAppListMax = listMax;
    }

    public int getCarPlayAppIconSize() {
        return this.mCarPlayAppIconSize;
    }

    public void setCarPlayAppIconSize(int appIconSize) {
        this.mCarPlayAppIconSize = appIconSize;
    }

    public String toString() {
        return "CarplayAppDiscoveryInfo [CarPlayAppCategories=" + this.mCarPlayAppCategories + ", CarPlayAppListMax=" + this.mCarPlayAppListMax + ", CarPlayAppIconSize=" + this.mCarPlayAppIconSize + "]";
    }

    public boolean checkValidity() {
        return this.mCarPlayAppIconSize <= 180 && (this.mCarPlayAppCategories & (~1023)) == 0;
    }
}
