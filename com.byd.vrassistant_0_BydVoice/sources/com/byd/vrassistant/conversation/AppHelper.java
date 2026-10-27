package com.byd.vrassistant.conversation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.SPUtils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.R;
import com.byd.vrassistant.utils.NavUtil;
import com.byd.vrassistant.utils.RLog;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppHelper {
    private static final String TAG = "NEU_AppHelper";
    private boolean blIsHeb;
    private String[] mVoiceOperateApps;
    private Map mapAppLabel;
    private Map<String, String> mapEngLabelApp;
    private Map<String, String> mapGenericsLabel;
    private Map<String, String> mapLabelApp;
    private String navipackage;
    private Context newContext;

    private static boolean isHebrewCharacter(char c) {
        return c >= 1424 && c <= 1535;
    }

    private static final class SingletonHolder {
        private static final AppHelper instance = new AppHelper();

        private SingletonHolder() {
        }
    }

    public static AppHelper getInstance() {
        return SingletonHolder.instance;
    }

    private AppHelper() {
        this.mVoiceOperateApps = null;
        this.mapEngLabelApp = null;
        this.mapAppLabel = null;
        this.mapLabelApp = null;
        this.mapGenericsLabel = null;
        this.blIsHeb = false;
    }

    public void loadEngLabelApp() {
        HashMap map = new HashMap();
        this.mapEngLabelApp = map;
        map.put("phone", Constant.VoiceControlApps8155[0]);
        this.mapEngLabelApp.put("radio", Constant.VoiceControlApps8155[1]);
        this.mapEngLabelApp.put("vehicle image", Constant.VoiceControlApps8155[2]);
        this.mapEngLabelApp.put("camera", Constant.VoiceControlApps8155[4]);
        this.mapEngLabelApp.put("album", Constant.VoiceControlApps8155[5]);
        this.mapEngLabelApp.put(CarSettings.UserTableData.TABLE_NAME_SETTINGS, Constant.VoiceControlApps8155[6]);
        this.mapEngLabelApp.put("file manager", Constant.VoiceControlApps8155[7]);
        this.mapEngLabelApp.put("dab plus", Constant.VoiceControlApps8155[8]);
        this.mapEngLabelApp.put("android auto", Constant.VoiceControlApps8155[9]);
        this.mapEngLabelApp.put("apple carplay", Constant.VoiceControlApps8155[10]);
        this.mapEngLabelApp.put("carplay", Constant.VoiceControlApps8155[10]);
        this.mapEngLabelApp.put("spotify", Constant.VoiceControlApps8155[11]);
        this.mapEngLabelApp.put("user manual", Constant.VoiceControlApps8155[13]);
        this.mapEngLabelApp.put("karaoke", Constant.VoiceControlApps8155[15]);
        this.mapEngLabelApp.put("my car", Constant.VoiceControlApps8155[14]);
        this.mapEngLabelApp.put("variety theme", Constant.VoiceControlApps8155[16]);
        this.mapEngLabelApp.put("amazon music", Constant.VoiceControlApps8155[17]);
        this.mapEngLabelApp.put("zoom", Constant.VoiceControlApps8155[18]);
        this.mapEngLabelApp.put("youtube", Constant.VoiceControlApps8155[19]);
        this.mapEngLabelApp.put("flo", Constant.VoiceControlApps8155[20]);
        this.mapEngLabelApp.put("deezer", Constant.VoiceControlApps8155[21]);
        this.mapEngLabelApp.put("tidal", Constant.VoiceControlApps8155[22]);
        this.mapEngLabelApp.put("melon", Constant.VoiceControlApps8155[23]);
        this.mapEngLabelApp.put("tunein radio", Constant.VoiceControlApps8155[24]);
        this.mapEngLabelApp.put("pocket casts", Constant.VoiceControlApps8155[25]);
        this.mapEngLabelApp.put("audials play", Constant.VoiceControlApps8155[26]);
        this.mapEngLabelApp.put("iheart radio", Constant.VoiceControlApps8155[27]);
        this.mapEngLabelApp.put("radioline", Constant.VoiceControlApps8155[28]);
        this.mapEngLabelApp.put("audible", Constant.VoiceControlApps8155[29]);
        this.mapEngLabelApp.put("libby", Constant.VoiceControlApps8155[30]);
        this.mapEngLabelApp.put("bloomberg", Constant.VoiceControlApps8155[31]);
        this.mapEngLabelApp.put("byd store", Constant.VoiceControlApps8155[32]);
    }

    public void loadAppLabel() {
        loadEngLabelApp();
        String strCodeToPackage = NavUtil.codeToPackage(CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext()));
        this.navipackage = strCodeToPackage;
        this.mapEngLabelApp.put(NotificationCompat.CATEGORY_NAVIGATION, strCodeToPackage);
        this.mapEngLabelApp.put("map", this.navipackage);
        this.mVoiceOperateApps = new String[Constant.VoiceControlApps8155.length + 1];
        System.arraycopy(Constant.VoiceControlApps8155, 0, this.mVoiceOperateApps, 0, Constant.VoiceControlApps8155.length);
        this.mVoiceOperateApps[Constant.VoiceControlApps8155.length] = this.navipackage;
        PackageManager packageManager = BydVoiceApplication.getContext().getPackageManager();
        this.mapAppLabel = new HashMap();
        this.mapLabelApp = new HashMap();
        for (String str : this.mVoiceOperateApps) {
            try {
                String lowerCase = packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString().trim().toLowerCase();
                if (lowerCase.equals("dab+")) {
                    lowerCase = "dab plus";
                }
                this.mapAppLabel.put(str, lowerCase);
                this.mapLabelApp.put(lowerCase, str);
            } catch (PackageManager.NameNotFoundException e) {
                RLog.e(TAG, str + " NameNotFoundException", e);
            }
        }
        loadGenericsLabel();
    }

    public void loadGenericsLabel() {
        HashMap map = new HashMap();
        this.mapGenericsLabel = map;
        map.put("bluetooth phone", Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put("bt phone", Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put("bluetooth music", Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put("bt music", Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put("media", Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put("panorama", Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put("photo", Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put("photos", Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put("gallery", Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put("vehicle settings", Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put("car settings", Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put("file", Constant.VoiceControlApps8155[7]);
        this.mapGenericsLabel.put("dab", Constant.VoiceControlApps8155[8]);
        this.mapGenericsLabel.put("user manual", Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put("user guide", Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put("karaoke", Constant.VoiceControlApps8155[15]);
        this.mapGenericsLabel.put("my car", Constant.VoiceControlApps8155[14]);
        this.mapGenericsLabel.put("amazon music", Constant.VoiceControlApps8155[17]);
        this.mapGenericsLabel.put("zoom", Constant.VoiceControlApps8155[18]);
        this.mapGenericsLabel.put("youtube", Constant.VoiceControlApps8155[19]);
        this.mapGenericsLabel.put("flo", Constant.VoiceControlApps8155[20]);
        this.mapGenericsLabel.put("deezer", Constant.VoiceControlApps8155[21]);
        this.mapGenericsLabel.put("tidal", Constant.VoiceControlApps8155[22]);
        this.mapGenericsLabel.put("melon", Constant.VoiceControlApps8155[23]);
        this.mapGenericsLabel.put("tunein radio", Constant.VoiceControlApps8155[24]);
        this.mapGenericsLabel.put("pocket casts", Constant.VoiceControlApps8155[25]);
        this.mapGenericsLabel.put("audials play", Constant.VoiceControlApps8155[26]);
        this.mapGenericsLabel.put("iheart radio", Constant.VoiceControlApps8155[27]);
        this.mapGenericsLabel.put("radioline", Constant.VoiceControlApps8155[28]);
        this.mapGenericsLabel.put("audible", Constant.VoiceControlApps8155[29]);
        this.mapGenericsLabel.put("libby", Constant.VoiceControlApps8155[30]);
        this.mapGenericsLabel.put("bloomberg", Constant.VoiceControlApps8155[31]);
        this.blIsHeb = false;
        String string = SPUtils.getInstance().getString(Constant.SP_KEY_CUR_LANGUAGE);
        if (!TextUtils.isEmpty(string)) {
            if (string.contains("data_eng")) {
                this.newContext = getNewContext("en", "");
                RLog.e(TAG, " data_eng  newContext ");
            } else if (string.contains("data_nor")) {
                this.newContext = getNewContext("nb", "");
                RLog.e(TAG, " data_nor  newContext ");
            } else if (string.contains("data_spa")) {
                if (string.contains("XLA")) {
                    this.newContext = getNewContext("es", "MX");
                    RLog.e(TAG, " data_spa XLA  newContext ");
                } else if (string.contains(BYDAutoADASDevice.FEATURE_ADAS_ESP)) {
                    this.newContext = getNewContext("es", "ES");
                    RLog.e(TAG, " data_spa ESP  newContext ");
                } else {
                    this.newContext = getNewContext("es", "");
                    RLog.e(TAG, " data_spa  newContext ");
                }
            } else if (string.contains("data_por")) {
                if (string.contains("BRA")) {
                    this.newContext = getNewContext("pt", "BR");
                    RLog.e(TAG, " data_por BRA  newContext ");
                } else if (string.contains("PRT")) {
                    this.newContext = getNewContext("pt", "PT");
                    RLog.e(TAG, " data_por PRT  newContext ");
                } else {
                    this.newContext = getNewContext("pt", "");
                    RLog.e(TAG, " data_por  newContext ");
                }
            } else if (string.contains("data_deu")) {
                this.newContext = getNewContext("de", "");
                RLog.e(TAG, " data_deu  newContext ");
            } else if (string.contains("data_fra")) {
                this.newContext = getNewContext("fr", "");
                RLog.e(TAG, " data_fra  newContext ");
            } else if (string.contains("data_jpn")) {
                this.newContext = getNewContext("ja", "");
                RLog.e(TAG, " data_jpn  newContext ");
            } else if (string.contains("data_tha")) {
                this.newContext = getNewContext("th", "");
                RLog.e(TAG, " data_tha  newContext ");
            } else if (string.contains("data_ita")) {
                this.newContext = getNewContext("it", "");
                RLog.e(TAG, " data_ita  newContext ");
            } else if (string.contains("data_cmn")) {
                this.newContext = getNewContext("zh", "CN");
                RLog.e(TAG, " data_cmn  newContext ");
            } else if (string.contains("data_yue")) {
                this.newContext = getNewContext("zh", "HK");
                RLog.e(TAG, " data_yue  newContext ");
            } else if (string.contains("data_swe")) {
                this.newContext = getNewContext("sv", "");
                RLog.e(TAG, " data_swe  newContext ");
            } else if (string.contains("data_kor")) {
                this.newContext = getNewContext("ko", "");
                RLog.e(TAG, " data_kor  newContext ");
            } else if (string.contains("data_dan")) {
                this.newContext = getNewContext("da", "");
                RLog.e(TAG, " data_dan  newContext ");
            } else if (string.contains("data_nld")) {
                this.newContext = getNewContext("nl", "");
                RLog.e(TAG, " data_nld  newContext ");
            } else if (string.contains("data_pol")) {
                this.newContext = getNewContext("pl", "");
                RLog.e(TAG, " data_pol  newContext ");
            } else if (string.contains("data_fin")) {
                this.newContext = getNewContext("fi", "");
                RLog.e(TAG, " data_fin  newContext ");
            } else if (string.contains("data_tur")) {
                this.newContext = getNewContext("tr", "");
                RLog.e(TAG, " data_tur  newContext ");
            } else if (string.contains("data_heb")) {
                this.newContext = getNewContext("iw", "");
                RLog.e(TAG, " data_heb  newContext ");
                this.blIsHeb = true;
            } else if (string.contains("data_hun")) {
                this.newContext = getNewContext("hu", "");
                RLog.e(TAG, " data_hun  newContext ");
            } else {
                this.newContext = BydVoiceApplication.getContext();
                RLog.e(TAG, " def  newContext ");
            }
        } else {
            this.newContext = BydVoiceApplication.getContext();
            RLog.e(TAG, " dataPath null  newContext ");
        }
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_bluetoothmusic), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_btmusic), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_radio), this.blIsHeb);
        String strMirrorMixedText4 = mirrorMixedText(this.newContext.getString(R.string.app_radio1), this.blIsHeb);
        String strMirrorMixedText5 = mirrorMixedText(this.newContext.getString(R.string.app_radio2), this.blIsHeb);
        String strMirrorMixedText6 = mirrorMixedText(this.newContext.getString(R.string.app_radio3), this.blIsHeb);
        String strMirrorMixedText7 = mirrorMixedText(this.newContext.getString(R.string.app_video), this.blIsHeb);
        String strMirrorMixedText8 = mirrorMixedText(this.newContext.getString(R.string.app_video1), this.blIsHeb);
        String strMirrorMixedText9 = mirrorMixedText(this.newContext.getString(R.string.app_video2), this.blIsHeb);
        String strMirrorMixedText10 = mirrorMixedText(this.newContext.getString(R.string.app_video3), this.blIsHeb);
        String strMirrorMixedText11 = mirrorMixedText(this.newContext.getString(R.string.app_music), this.blIsHeb);
        String strMirrorMixedText12 = mirrorMixedText(this.newContext.getString(R.string.app_music1), this.blIsHeb);
        String strMirrorMixedText13 = mirrorMixedText(this.newContext.getString(R.string.app_btmusic1), this.blIsHeb);
        String strMirrorMixedText14 = mirrorMixedText(this.newContext.getString(R.string.app_mediacenter), this.blIsHeb);
        String strMirrorMixedText15 = mirrorMixedText(this.newContext.getString(R.string.app_media), this.blIsHeb);
        String strMirrorMixedText16 = mirrorMixedText(this.newContext.getString(R.string.app_media_ex), this.blIsHeb);
        String strMirrorMixedText17 = mirrorMixedText(this.newContext.getString(R.string.app_media_ex1), this.blIsHeb);
        String strMirrorMixedText18 = mirrorMixedText(this.newContext.getString(R.string.app_panorama), this.blIsHeb);
        String strMirrorMixedText19 = mirrorMixedText(this.newContext.getString(R.string.app_panorama1), this.blIsHeb);
        String strMirrorMixedText20 = mirrorMixedText(this.newContext.getString(R.string.app_panorama2), this.blIsHeb);
        String strMirrorMixedText21 = mirrorMixedText(this.newContext.getString(R.string.app_panorama3), this.blIsHeb);
        String strMirrorMixedText22 = mirrorMixedText(this.newContext.getString(R.string.app_panorama4), this.blIsHeb);
        String strMirrorMixedText23 = mirrorMixedText(this.newContext.getString(R.string.app_vehicleimage), this.blIsHeb);
        String strMirrorMixedText24 = mirrorMixedText(this.newContext.getString(R.string.app_vehicleimage1), this.blIsHeb);
        String strMirrorMixedText25 = mirrorMixedText(this.newContext.getString(R.string.app_vehicleimage2), this.blIsHeb);
        String strMirrorMixedText26 = mirrorMixedText(this.newContext.getString(R.string.app_dab), this.blIsHeb);
        String strMirrorMixedText27 = mirrorMixedText(this.newContext.getString(R.string.app_dabplus), this.blIsHeb);
        String strMirrorMixedText28 = mirrorMixedText(this.newContext.getString(R.string.app_dab1), this.blIsHeb);
        String strMirrorMixedText29 = mirrorMixedText(this.newContext.getString(R.string.app_phone), this.blIsHeb);
        String strMirrorMixedText30 = mirrorMixedText(this.newContext.getString(R.string.app_btphone), this.blIsHeb);
        String strMirrorMixedText31 = mirrorMixedText(this.newContext.getString(R.string.app_bluetoothphone), this.blIsHeb);
        String strMirrorMixedText32 = mirrorMixedText(this.newContext.getString(R.string.app_btphone1), this.blIsHeb);
        String strMirrorMixedText33 = mirrorMixedText(this.newContext.getString(R.string.app_album), this.blIsHeb);
        String strMirrorMixedText34 = mirrorMixedText(this.newContext.getString(R.string.app_album1), this.blIsHeb);
        String strMirrorMixedText35 = mirrorMixedText(this.newContext.getString(R.string.app_album2), this.blIsHeb);
        String strMirrorMixedText36 = mirrorMixedText(this.newContext.getString(R.string.app_album3), this.blIsHeb);
        String strMirrorMixedText37 = mirrorMixedText(this.newContext.getString(R.string.app_navigation), this.blIsHeb);
        String strMirrorMixedText38 = mirrorMixedText(this.newContext.getString(R.string.app_map), this.blIsHeb);
        String strMirrorMixedText39 = mirrorMixedText(this.newContext.getString(R.string.app_map1), this.blIsHeb);
        String strMirrorMixedText40 = mirrorMixedText(this.newContext.getString(R.string.app_map2), this.blIsHeb);
        String strMirrorMixedText41 = mirrorMixedText(this.newContext.getString(R.string.app_map3), this.blIsHeb);
        String strMirrorMixedText42 = mirrorMixedText(this.newContext.getString(R.string.android_auto), this.blIsHeb);
        String strMirrorMixedText43 = mirrorMixedText(this.newContext.getString(R.string.android_auto1), this.blIsHeb);
        String strMirrorMixedText44 = mirrorMixedText(this.newContext.getString(R.string.app_carsettings), this.blIsHeb);
        String strMirrorMixedText45 = mirrorMixedText(this.newContext.getString(R.string.app_carsettings1), this.blIsHeb);
        String strMirrorMixedText46 = mirrorMixedText(this.newContext.getString(R.string.app_carsettings2), this.blIsHeb);
        String strMirrorMixedText47 = mirrorMixedText(this.newContext.getString(R.string.app_carsettings3), this.blIsHeb);
        String strMirrorMixedText48 = mirrorMixedText(this.newContext.getString(R.string.app_gallery), this.blIsHeb);
        String strMirrorMixedText49 = mirrorMixedText(this.newContext.getString(R.string.app_photo), this.blIsHeb);
        String strMirrorMixedText50 = mirrorMixedText(this.newContext.getString(R.string.app_photos), this.blIsHeb);
        String strMirrorMixedText51 = mirrorMixedText(this.newContext.getString(R.string.app_file), this.blIsHeb);
        String strMirrorMixedText52 = mirrorMixedText(this.newContext.getString(R.string.app_filemanager), this.blIsHeb);
        String strMirrorMixedText53 = mirrorMixedText(this.newContext.getString(R.string.app_file_ex), this.blIsHeb);
        String strMirrorMixedText54 = mirrorMixedText(this.newContext.getString(R.string.app_carplay), this.blIsHeb);
        String strMirrorMixedText55 = mirrorMixedText(this.newContext.getString(R.string.app_applecarplay), this.blIsHeb);
        String strMirrorMixedText56 = mirrorMixedText(this.newContext.getString(R.string.app_carplay1), this.blIsHeb);
        String strMirrorMixedText57 = mirrorMixedText(this.newContext.getString(R.string.app_camera), this.blIsHeb);
        String strMirrorMixedText58 = mirrorMixedText(this.newContext.getString(R.string.app_camera_ex), this.blIsHeb);
        String strMirrorMixedText59 = mirrorMixedText(this.newContext.getString(R.string.user_manual), this.blIsHeb);
        String strMirrorMixedText60 = mirrorMixedText(this.newContext.getString(R.string.user_manual1), this.blIsHeb);
        String strMirrorMixedText61 = mirrorMixedText(this.newContext.getString(R.string.user_manual2), this.blIsHeb);
        String strMirrorMixedText62 = mirrorMixedText(this.newContext.getString(R.string.user_manual3), this.blIsHeb);
        String strMirrorMixedText63 = mirrorMixedText(this.newContext.getString(R.string.user_manual4), this.blIsHeb);
        String strMirrorMixedText64 = mirrorMixedText(this.newContext.getString(R.string.byd_store), this.blIsHeb);
        String strMirrorMixedText65 = mirrorMixedText(this.newContext.getString(R.string.byd_store1), this.blIsHeb);
        String strMirrorMixedText66 = mirrorMixedText(this.newContext.getString(R.string.byd_store2), this.blIsHeb);
        String strMirrorMixedText67 = mirrorMixedText(this.newContext.getString(R.string.byd_store3), this.blIsHeb);
        String strMirrorMixedText68 = mirrorMixedText(this.newContext.getString(R.string.app_multipletheme), this.blIsHeb);
        String strMirrorMixedText69 = mirrorMixedText(this.newContext.getString(R.string.app_multipletheme1), this.blIsHeb);
        String strMirrorMixedText70 = mirrorMixedText(this.newContext.getString(R.string.app_multipletheme2), this.blIsHeb);
        String strMirrorMixedText71 = mirrorMixedText(this.newContext.getString(R.string.app_multipletheme3), this.blIsHeb);
        String strMirrorMixedText72 = mirrorMixedText(this.newContext.getString(R.string.app_karaoke), this.blIsHeb);
        String strMirrorMixedText73 = mirrorMixedText(this.newContext.getString(R.string.app_karaoke1), this.blIsHeb);
        String strMirrorMixedText74 = mirrorMixedText(this.newContext.getString(R.string.app_karaoke2), this.blIsHeb);
        String strMirrorMixedText75 = mirrorMixedText(this.newContext.getString(R.string.app_my_car), this.blIsHeb);
        String strMirrorMixedText76 = mirrorMixedText(this.newContext.getString(R.string.app_my_car1), this.blIsHeb);
        String strMirrorMixedText77 = mirrorMixedText(this.newContext.getString(R.string.app_amazon_music), this.blIsHeb);
        String strMirrorMixedText78 = mirrorMixedText(this.newContext.getString(R.string.app_amazon_music1), this.blIsHeb);
        String strMirrorMixedText79 = mirrorMixedText(this.newContext.getString(R.string.app_spotify), this.blIsHeb);
        String strMirrorMixedText80 = mirrorMixedText(this.newContext.getString(R.string.app_spotify1), this.blIsHeb);
        String strMirrorMixedText81 = mirrorMixedText(this.newContext.getString(R.string.app_assistant), this.blIsHeb);
        String strMirrorMixedText82 = mirrorMixedText(this.newContext.getString(R.string.app_assistant1), this.blIsHeb);
        String strMirrorMixedText83 = mirrorMixedText(this.newContext.getString(R.string.app_assistant2), this.blIsHeb);
        String strMirrorMixedText84 = mirrorMixedText(this.newContext.getString(R.string.app_assistant3), this.blIsHeb);
        String strMirrorMixedText85 = mirrorMixedText(this.newContext.getString(R.string.app_assistant4), this.blIsHeb);
        String strMirrorMixedText86 = mirrorMixedText(this.newContext.getString(R.string.app_assistant5), this.blIsHeb);
        String strMirrorMixedText87 = mirrorMixedText(this.newContext.getString(R.string.app_assistant6), this.blIsHeb);
        String strMirrorMixedText88 = mirrorMixedText(this.newContext.getString(R.string.app_assistant7), this.blIsHeb);
        String strMirrorMixedText89 = mirrorMixedText(this.newContext.getString(R.string.app_assistant8), this.blIsHeb);
        String strMirrorMixedText90 = mirrorMixedText(this.newContext.getString(R.string.app_assistant9), this.blIsHeb);
        String strMirrorMixedText91 = mirrorMixedText(this.newContext.getString(R.string.app_assistant10), this.blIsHeb);
        String strMirrorMixedText92 = mirrorMixedText(this.newContext.getString(R.string.app_assistant11), this.blIsHeb);
        String strMirrorMixedText93 = mirrorMixedText(this.newContext.getString(R.string.app_assistant12), this.blIsHeb);
        RLog.i(TAG, "Current Language AppName: app_btmusic = " + strMirrorMixedText + " app_radio = " + strMirrorMixedText3 + " app_video = " + strMirrorMixedText7 + " app_music = " + strMirrorMixedText11 + " app_mediacenter = " + strMirrorMixedText14 + " app_panorama = " + strMirrorMixedText18 + " app_vehicleimage = " + strMirrorMixedText23 + " app_dab = " + strMirrorMixedText26 + " app_phone = " + strMirrorMixedText29 + " app_btphone = " + strMirrorMixedText30 + ",navigation=" + strMirrorMixedText37 + " app_assistant = " + strMirrorMixedText81);
        this.mapGenericsLabel.put(strMirrorMixedText29, Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put(strMirrorMixedText30, Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put(strMirrorMixedText31, Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put(strMirrorMixedText32, Constant.VoiceControlApps8155[0]);
        this.mapGenericsLabel.put(strMirrorMixedText, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText2, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText3, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText4, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText5, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText6, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText7, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText8, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText9, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText10, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText11, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText12, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText13, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText14, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText15, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText16, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText17, Constant.VoiceControlApps8155[1]);
        this.mapGenericsLabel.put(strMirrorMixedText18, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText19, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText20, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText21, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText22, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText23, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText24, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText25, Constant.VoiceControlApps8155[2]);
        this.mapGenericsLabel.put(strMirrorMixedText57, Constant.VoiceControlApps8155[4]);
        this.mapGenericsLabel.put(strMirrorMixedText58, Constant.VoiceControlApps8155[4]);
        this.mapGenericsLabel.put(strMirrorMixedText49, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText50, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText48, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText33, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText34, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText35, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText36, Constant.VoiceControlApps8155[5]);
        this.mapGenericsLabel.put(strMirrorMixedText44, Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put(strMirrorMixedText45, Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put(strMirrorMixedText46, Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put(strMirrorMixedText47, Constant.VoiceControlApps8155[6]);
        this.mapGenericsLabel.put(strMirrorMixedText51, Constant.VoiceControlApps8155[7]);
        this.mapGenericsLabel.put(strMirrorMixedText53, Constant.VoiceControlApps8155[7]);
        this.mapGenericsLabel.put(strMirrorMixedText52, Constant.VoiceControlApps8155[7]);
        this.mapGenericsLabel.put(strMirrorMixedText26, Constant.VoiceControlApps8155[8]);
        this.mapGenericsLabel.put(strMirrorMixedText27, Constant.VoiceControlApps8155[8]);
        this.mapGenericsLabel.put(strMirrorMixedText28, Constant.VoiceControlApps8155[8]);
        this.mapGenericsLabel.put(strMirrorMixedText42, Constant.VoiceControlApps8155[9]);
        this.mapGenericsLabel.put(strMirrorMixedText43, Constant.VoiceControlApps8155[9]);
        this.mapGenericsLabel.put(strMirrorMixedText54, Constant.VoiceControlApps8155[10]);
        this.mapGenericsLabel.put(strMirrorMixedText55, Constant.VoiceControlApps8155[10]);
        this.mapGenericsLabel.put(strMirrorMixedText56, Constant.VoiceControlApps8155[10]);
        this.mapGenericsLabel.put(strMirrorMixedText59, Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put(strMirrorMixedText60, Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put(strMirrorMixedText61, Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put(strMirrorMixedText62, Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put(strMirrorMixedText63, Constant.VoiceControlApps8155[13]);
        this.mapGenericsLabel.put(strMirrorMixedText64, Constant.VoiceControlApps8155[32]);
        this.mapGenericsLabel.put(strMirrorMixedText65, Constant.VoiceControlApps8155[32]);
        this.mapGenericsLabel.put(strMirrorMixedText66, Constant.VoiceControlApps8155[32]);
        this.mapGenericsLabel.put(strMirrorMixedText67, Constant.VoiceControlApps8155[32]);
        this.mapGenericsLabel.put(strMirrorMixedText37, this.navipackage);
        this.mapGenericsLabel.put(strMirrorMixedText38, this.navipackage);
        this.mapGenericsLabel.put(strMirrorMixedText39, this.navipackage);
        this.mapGenericsLabel.put(strMirrorMixedText40, this.navipackage);
        this.mapGenericsLabel.put(strMirrorMixedText41, this.navipackage);
        this.mapGenericsLabel.put(strMirrorMixedText72, Constant.VoiceControlApps8155[15]);
        this.mapGenericsLabel.put(strMirrorMixedText73, Constant.VoiceControlApps8155[15]);
        this.mapGenericsLabel.put(strMirrorMixedText74, Constant.VoiceControlApps8155[15]);
        this.mapGenericsLabel.put(strMirrorMixedText75, Constant.VoiceControlApps8155[14]);
        this.mapGenericsLabel.put(strMirrorMixedText76, Constant.VoiceControlApps8155[14]);
        this.mapGenericsLabel.put(strMirrorMixedText68, Constant.VoiceControlApps8155[16]);
        this.mapGenericsLabel.put(strMirrorMixedText69, Constant.VoiceControlApps8155[16]);
        this.mapGenericsLabel.put(strMirrorMixedText70, Constant.VoiceControlApps8155[16]);
        this.mapGenericsLabel.put(strMirrorMixedText71, Constant.VoiceControlApps8155[16]);
        this.mapGenericsLabel.put(strMirrorMixedText77, Constant.VoiceControlApps8155[17]);
        this.mapGenericsLabel.put(strMirrorMixedText78, Constant.VoiceControlApps8155[17]);
        this.mapGenericsLabel.put(strMirrorMixedText81, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText82, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText83, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText84, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText85, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText86, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText87, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText88, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText89, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText90, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText91, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText92, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText93, Constant.VoiceControlApps8155[12]);
        this.mapGenericsLabel.put(strMirrorMixedText79, Constant.VoiceControlApps8155[11]);
        this.mapGenericsLabel.put(strMirrorMixedText80, Constant.VoiceControlApps8155[11]);
    }

    private Context getNewContext(String str, String str2) {
        Locale locale;
        BydVoiceApplication context = BydVoiceApplication.getContext();
        if (TextUtils.isEmpty(str2)) {
            locale = new Locale(str);
        } else {
            locale = new Locale(str, str2);
        }
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    public String getAppByInputParam(Context context, String str) {
        if (str.equals("dab+") || str.equals("dab pluss") || str.equals("dab más") || str.equals("dab mais")) {
            str = "dab plus";
        }
        String str2 = this.mapEngLabelApp.get(str);
        if (!TextUtils.isEmpty(str2)) {
            RLog.i(TAG, "match App[" + str2 + "] by English");
            return str2;
        }
        String str3 = this.mapLabelApp.get(str);
        if (!TextUtils.isEmpty(str3)) {
            RLog.i(TAG, "match App[" + str3 + "] by AppLabel");
            return str3;
        }
        String str4 = this.mapGenericsLabel.get(str);
        if (TextUtils.isEmpty(str4)) {
            return null;
        }
        RLog.i(TAG, "match App[" + str4 + "] by mapGenericsLabel");
        return str4;
    }

    public String[] getVoiceOperateApps() {
        return this.mVoiceOperateApps;
    }

    public Map getMapAppLabel() {
        return this.mapAppLabel;
    }

    public boolean isRadioText(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_radio), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_radio1), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_radio2), this.blIsHeb);
        String strMirrorMixedText4 = mirrorMixedText(this.newContext.getString(R.string.app_radio3), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("radio") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2) || str.equals(strMirrorMixedText3) || str.equals(strMirrorMixedText4);
    }

    public boolean isVideoText(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_video), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_video1), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_video2), this.blIsHeb);
        String strMirrorMixedText4 = mirrorMixedText(this.newContext.getString(R.string.app_video3), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("video") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2) || str.equals(strMirrorMixedText3) || str.equals(strMirrorMixedText4);
    }

    public boolean isBTMusic(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_btmusic), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_bluetoothmusic), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_btmusic1), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("bluetooth music") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2) || str.equals(strMirrorMixedText3);
    }

    public boolean isLocalMusic(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_music), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_music1), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("music") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2);
    }

    public boolean isMediaCenterText(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_mediacenter), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_media), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_media_ex), this.blIsHeb);
        String strMirrorMixedText4 = mirrorMixedText(this.newContext.getString(R.string.app_media_ex1), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("media center") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2) || str.equals(strMirrorMixedText3) || str.equals(strMirrorMixedText4);
    }

    public boolean isDABText(Context context, String str) {
        String strMirrorMixedText = mirrorMixedText(this.newContext.getString(R.string.app_dab), this.blIsHeb);
        String strMirrorMixedText2 = mirrorMixedText(this.newContext.getString(R.string.app_dabplus), this.blIsHeb);
        String strMirrorMixedText3 = mirrorMixedText(this.newContext.getString(R.string.app_dab1), this.blIsHeb);
        if (str == null) {
            return false;
        }
        return str.equals("dab") || str.equals("dab plus") || str.equals(strMirrorMixedText) || str.equals(strMirrorMixedText2) || str.equals("dab+") || str.equals(strMirrorMixedText3);
    }

    public Collection<String> getGenericsLabel() {
        loadGenericsLabel();
        Map<String, String> map = this.mapGenericsLabel;
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    public static String mirrorMixedText(String str, boolean z) {
        if (!z) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isHebrewCharacter(cCharAt)) {
                sb3.append(cCharAt);
                sb2.append(cCharAt);
            } else {
                if (sb3.length() > 0) {
                    sb2.setLength(0);
                    sb2.append(" ");
                    sb2.append(sb3.toString());
                    sb3.setLength(0);
                }
                sb4.append(cCharAt);
            }
        }
        sb.append(sb4.toString());
        sb.append(sb2.toString());
        return sb.toString().trim();
    }
}
