package com.byd.card.bean;

/* JADX INFO: loaded from: classes.dex */
public interface Constant {
    public static final String AVM_DEFAULT = "-1";
    public static final String AVM_ISP = "persist.vendor.bosch.cfg.supplies.avm.camera.isp";
    public static final String AVM_NAME = "persist.vendor.bosch.cfg.supplies.avm.name";
    public static final String CAMERA_ISP = "persist.vendor.bosch.cfg.supplies.ims.camera.isp";
    public static final String CAMERA_NAME = "persist.vendor.bosch.cfg.supplies.ims.name";
    public static final String HONG_KONG_CODE = "852";
    public static final String POWER_TYPE = "power_type";

    public interface Activity {
        public static final String AUTO_ACTIVITY = "com.ts.androidauto.app.display.AapActivity";
        public static final String CARPLAY_ACTIVITY = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";
        public static final String CARSETTINGS_ACTIVITY = "com.byd.carsettings.MainActivity";
        public static final String MORE_APP_ACTIVITY = "com.byd.card.view.activity.MoreAppActivity";
        public static final String START_UP_ACTIVITY = "com.byd.carsettings.ui.activity.StartupAppManageActivity";
    }

    public interface CarType {
        public static final String CAR_TYPE_EV = "1";
        public static final String CAR_TYPE_FUEL = "0";
        public static final String CAR_TYPE_HEV = "2";
    }

    public interface EventBusMessage {
        public static final String ACTION_CHANGE_SKIN_SUCCESS = "action.change.skin.success";
        public static final String ACTION_PACKAGE_ADDED = "action_package_added";
        public static final String ACTION_PACKAGE_REMOVED = "action_package_removed";
        public static final String ACTION_SHORTCUTS_REMOVED = "action_shortcuts_removed";
        public static final String ADD_NAVI = "add_navi";
        public static final String AUTO_STATUS = "auto_status";
        public static final String BACK_KEY_DOWN = "back_key_down";
        public static final String CANCEL_APP_CARD = "cancel_app_card";
        public static final String CARPLAY_STATUS = "carplay_status";
        public static final String CHECK_CARD_FORBIDDEN = "check_card_forbidden";
        public static final String CLICK_UI_MODE = "click_ui_mode";
        public static final String CPAA_DIALOG_DISMISS = "cpaa_dialog_dismiss";
        public static final String FINISH_APP_CENTER = "finish_app_center";
        public static final String HIDE_ADD_CARD_DIALOG = "hide_add_card_dialog";
        public static final String HIDE_ADD_CARD_EDIT = "hide_add_card_edit";
        public static final String HIDE_CARD_NO_ANIMATOR = "hide_card_no_animator";
        public static final String HIDE_FLOAT_WINDOW = "hide_float_window";
        public static final String HIDE_MEDIA_POPUP = "hide_media_popup";
        public static final String HOME_KEY = "home_key";
        public static final String REMOVE_APP_CARD = "remove_app_card";
        public static final String REMOVE_SHORTCUTS_CARD = "remove_shortcuts_card";
        public static final String REMOVE_WIDGET_CARD = "remove_navi";
        public static final String SELECT_APP_CARD = "Select_APP_CARD";
        public static final String SHOW_ADD_CARD_EDIT = "show_add_card_edit";
        public static final String SHOW_FLOAT_WINDOW = "show_float_window";
        public static final String SHOW_MEDIA_POPUP = "show_media_popup";
        public static final String SPLIT_SCREEN = "split_screen";
        public static final String SPLIT_SCREEN_NOT = "split_screen_not";
        public static final String USER_UNLOCKED = "user_unlocked";
    }

    public interface FolderType {
        public static final String DEFAULT_FOLDER = "default_folder";
        public static final String TOOLS_FOLDER = "tools_folder";
    }

    public interface HandlerMessage {
        public static final int MESSAGE_ADD_NAVI_CARD = 6;
        public static final int MESSAGE_CHECK_FORBIDDEN = 8;
        public static final int MESSAGE_DELAY_SHOW_CARD = 4;
        public static final int MESSAGE_LOAD_SPLIT_APP_LIST = 5;
        public static final int MESSAGE_REMOVE_NAVI_CARD = 7;
        public static final int MESSAGE_SET_CONFIGURATIONCHANGED = 2;
        public static final int MESSAGE_SET_SKIN_PATH = 1;
        public static final int MESSAGE_UPDATA_VIEW = 3;
    }

    public interface LeftAndRight {
        public static final int LEFT_RUDDER = 0;
        public static final int RIGHT_RUDDER = 1;
    }

    public interface PackageName {
        public static final String AMAZON = "com.amazon.mp3.automotiveOS";
        public static final String ANDROID_SETTINGS = "com.android.car.settings";
        public static final String AUTO = "com.ts.androidauto.app";
        public static final String AUTO_MAP_PKG = "com.byd.automap";
        public static final String AUTO_MULTIPLE_THEME_PKG = "com.byd.automultipletheme";
        public static final String AUTO_PHOTO_PKG = "com.byd.auto_photo";
        public static final String AUTO_SERVICE = "com.ts.androidauto.projectionservice";
        public static final String AVM_PKG = "com.byd.avm";
        public static final String BLUE_PKG = "com.byd.bluetoothcall";
        public static final String CAMERA_PKG = "com.byd.auto_camera";
        public static final String CARD = "com.byd.card";
        public static final String CARPLAY = "com.ts.carplay.app";
        public static final String CARSETTINGS_PKG = "com.byd.carsettings";
        public static final String CAR_SETTINGS = "com.byd.carsettings";
        public static final String DATA_COLLECT_PKG = "com.byd.data.collect";
        public static final String EMANUAL = "com.byd.auto_emanual";
        public static final String ENGINEERMODE_PKG = "com.byd.engineermode";
        public static final String FILE_MANAGER_PKG = "com.byd.filemanager";
        public static final String GLOBAL_NOTIFICATION = "com.byd.globalnotification";
        public static final String HAVC_PKG = "com.byd.hvac";
        public static final String HUAWEI_MAP = "com.huawei.maps.auto.app";
        public static final String HUAWEI_MAP_PKG = "com.huawei.maps.auto.app";
        public static final String IQQIJNI_INPUT = "com.iqqijni.car.keyboard";
        public static final String KARAOKE = "com.stingray.karaoke.car.byd8155f";
        public static final String LATIN = "com.android.inputmethod.latin";
        public static final String LAUNCHER3 = "com.android.launcher3";
        public static final String MEDIA_PKG = "com.byd.localmusic";
        public static final String MY_CAR_PKG = "com.byd.mycar";
        public static final String MY_CAR_PKG_TS = "com.byd.mycar.ts";
        public static final String NEU_SOFT_MAP_PKG = "com.neusoft.na.navigation";
        public static final String OVER_SEA_APP_STORE = "com.byd.overseaappstore";
        public static final String PACKAGE_BROWSER = "com.byd.browser";
        public static final String PACKAGE_DVR = "com.byd.dvr";
        public static final String PACKAGE_GPACK = "com.gpack.agent";
        public static final String PACKAGE_MEDIA = "com.android.car.media";
        public static final String SMART_TRAVEL_PKG = "com.byd.smarttravel";
        public static final String SPOTIFY_PKG = "com.telenav.scoutivi.spotify.byd";
        public static final String SPOTIFY_SDK = "com.spotify.music";
        public static final String START_UP_PKG = "com.byd.appstartmanagement";
        public static final String SYSTEM_UI = "com.android.systemui";
        public static final String TRAFFICMONITOR_PKG = "com.byd.trafficmonitor";
        public static final String T_MAP_PKG = "com.tmap.auto.byd";
        public static final String VRSETTINGS_PKG = "com.byd.vrsettings";
        public static final String VRSETTINGS_XF = "com.byd.vrassistant.xf";
        public static final String WALL_PAPER_ACTIVITY = "com.byd.launcherwallpaper";
        public static final String ZNAV_MAP_PKG = "net.zmap.znav.byd8155";
    }

    public interface SkinPath {
        public static final String FIELD_BYDUI_SKIN_PATH = "bydux_skin_path";
        public static final String SKIN_PATH_1 = "/system/etc/AppSkins/CardResource_011.skin";
        public static final String SKIN_PATH_2 = "/system/etc/AppSkins/CardResource_012.skin";
        public static final String SKIN_PATH_3 = "/system/etc/AppSkins/CardResource_013.skin";
        public static final String SKIN_PATH_4 = "/system/etc/AppSkins/CardResource_014.skin";
        public static final String SKIN_PATH_5 = "/system/etc/AppSkins/CardResource_015.skin";
        public static final int THEME_0 = 100000010;
        public static final int THEME_1 = 100000011;
        public static final int THEME_2 = 100000012;
        public static final int THEME_3 = 100000013;
        public static final int THEME_4 = 100000014;
        public static final int THEME_5 = 100000015;
    }

    public interface TopActivity {
        public static final String ADD_CARD_ACTIVITY = "com.byd.card.view.activity.AddCardPopActivity";
        public static final String ALL_APP_ACTIVITY = "com.byd.card.view.activity.MoreAppActivity";
        public static final String BLUETOOTH_CALL_ACTIVITY = "com.byd.bluetoothcall.view.activity.MainActivity";
        public static final String LAUNCHER = "com.byd.launcher";
        public static final String LAUNCHER3 = "com.android.launcher3.home.MainActivity";
        public static final String MAP_ACTIVITY = "com.neusoft.na.mots";
        public static final String MEDIA_ACTIVITY = "com.byd.music.activity.main.MediaMainActivity";
        public static final String MY_CAR_ACTIVITY = "com.byd.mycar.CarMainActivity";
        public static final String VR_UPDATE_INFO_ACTIVITY = "com.byd.otaupdate.ui.activity.QueryUpdateInfoActivity";
        public static final String WALL_PAPER_ACTIVITY = "com.byd.launcherwallpaper";
    }

    public interface UiMode {
        public static final int FIELD_DAY_MODE = 1;
        public static final int FIELD_NIGHT_MODE = 2;
    }

    public interface WidgetName {
        public static final String AUTO_MAP_FAKE_WIDGET = "com.byd.card.view.broadcast.AutoMapWidgetProvider";
        public static final String BLUE_WIDGET = "com.byd.bluetoothcall.widget.WidgetProvide";
        public static final String HUAWEI_MAP_WIDGET = "com.huawei.maps.auto.app.bydmeacusthome.WidgetProvider";
        public static final String MEDIA_WIDGET = "com.byd.music.utils.mediahelper.MusicWidgetProvider";
        public static final String MY_CAR_WIDGET = "com.byd.mycar.widget.CarWidget";
        public static final String NAVI_WIDGET = "com.neusoft.na.byd.BydAppWidget";
    }
}
