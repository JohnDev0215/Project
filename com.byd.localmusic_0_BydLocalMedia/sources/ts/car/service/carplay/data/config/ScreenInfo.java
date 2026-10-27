package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes5.dex */
public class ScreenInfo {
    private String mUuid = null;
    private int mHeightPhysical = -1;
    private int mWidthPhysical = -1;
    private int mHeightPixels = -1;
    private int mWidthPixels = -1;
    private int mMaxFps = -1;
    private int mInitialUrl = -1;
    private int mUiMode = -1;
    private int mUiSetting = -1;
    private boolean mIsSupportMap = false;
    private int mMapMode = -1;
    private int mMapSetting = -1;

    public static final class InitialUrl {
        public static final int INURL_INSTRUCTION_CARD = 2;
        public static final int INURL_INSTRUMENT_CLUSTER = 3;
        public static final int INURL_INVALID = -1;
        public static final int INURL_MAP = 1;
    }

    public static final class InitialUrlParameter {
        public static final int URL_SHOW_COMPASS = 0;
        public static final int URL_SHOW_ETA = 1;
        public static final int URL_SHOW_SPEED_LIMIT = 2;
    }

    public static final class Mode {
        public static final int DARK = 1;
        public static final int LIGHT = 0;
    }

    public static final class Setting {
        public static final int ALWAYS = 2;
        public static final int AUTOMATIC = 0;
        public static final int USERCHOICE = 1;
    }

    public int getHeightPhysical() {
        return this.mHeightPhysical;
    }

    public void setHeightPhysical(int i) {
        this.mHeightPhysical = i;
    }

    public int getWidthPhysical() {
        return this.mWidthPhysical;
    }

    public void setWidthPhysical(int i) {
        this.mWidthPhysical = i;
    }

    public int getHeightPixels() {
        return this.mHeightPixels;
    }

    public void setHeightPixels(int i) {
        this.mHeightPixels = i;
    }

    public int getWidthPixels() {
        return this.mWidthPixels;
    }

    public void setWidthPixels(int i) {
        this.mWidthPixels = i;
    }

    public int getMaxFps() {
        return this.mMaxFps;
    }

    public void setMaxFps(int i) {
        this.mMaxFps = i;
    }

    public int getInitialUrl() {
        return this.mInitialUrl;
    }

    public void setInitialUrl(int i) {
        this.mInitialUrl = i;
    }

    public int getUiMode() {
        return this.mUiMode;
    }

    public void setUiMode(int i) {
        this.mUiMode = i;
    }

    public int getUiSetting() {
        return this.mUiSetting;
    }

    public void setUiSetting(int i) {
        this.mUiSetting = i;
    }

    public boolean isIsSupportMap() {
        return this.mIsSupportMap;
    }

    public void setIsSupportMap(boolean z) {
        this.mIsSupportMap = z;
    }

    public int getMapMode() {
        return this.mMapMode;
    }

    public void setMapMode(int i) {
        this.mMapMode = i;
    }

    public int getMapSetting() {
        return this.mMapSetting;
    }

    public void setMapSetting(int i) {
        this.mMapSetting = i;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }
}
