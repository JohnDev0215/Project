package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
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

    public void setHeightPhysical(int heightPhysical) {
        this.mHeightPhysical = heightPhysical;
    }

    public int getWidthPhysical() {
        return this.mWidthPhysical;
    }

    public void setWidthPhysical(int widthPhysical) {
        this.mWidthPhysical = widthPhysical;
    }

    public int getHeightPixels() {
        return this.mHeightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.mHeightPixels = heightPixels;
    }

    public int getWidthPixels() {
        return this.mWidthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.mWidthPixels = widthPixels;
    }

    public int getMaxFps() {
        return this.mMaxFps;
    }

    public void setMaxFps(int maxFps) {
        this.mMaxFps = maxFps;
    }

    public int getInitialUrl() {
        return this.mInitialUrl;
    }

    public void setInitialUrl(int initialUrl) {
        this.mInitialUrl = initialUrl;
    }

    public int getUiMode() {
        return this.mUiMode;
    }

    public void setUiMode(int uiMode) {
        this.mUiMode = uiMode;
    }

    public int getUiSetting() {
        return this.mUiSetting;
    }

    public void setUiSetting(int uiSetting) {
        this.mUiSetting = uiSetting;
    }

    public boolean isIsSupportMap() {
        return this.mIsSupportMap;
    }

    public void setIsSupportMap(boolean isSupportMap) {
        this.mIsSupportMap = isSupportMap;
    }

    public int getMapMode() {
        return this.mMapMode;
    }

    public void setMapMode(int mapMode) {
        this.mMapMode = mapMode;
    }

    public int getMapSetting() {
        return this.mMapSetting;
    }

    public void setMapSetting(int mapSetting) {
        this.mMapSetting = mapSetting;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String uuid) {
        this.mUuid = uuid;
    }
}
