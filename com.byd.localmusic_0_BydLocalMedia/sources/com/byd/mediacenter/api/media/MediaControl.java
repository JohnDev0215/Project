package com.byd.mediacenter.api.media;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.byd.mediacenter.api.media.IMediaControlCallback;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaControl {
    public static final int ACTION_ADD_FAVORITE = 64;
    public static final int ACTION_CHECK_FREQUENCY = 74;
    public static final int ACTION_CUSTOM = 256;
    public static final int ACTION_ENTER = 0;
    public static final int ACTION_GET_MEDIA_STATUS = 70;
    public static final int ACTION_GET_RADIO_DAB_BER = 73;
    public static final int ACTION_GET_RADIO_DAB_FREQUENCY_AND_SERVICE_ID = 62;
    public static final int ACTION_GET_RADIO_FIELD_STRENGTH = 69;
    public static final int ACTION_GET_RADIO_FREQUENCY = 68;
    public static final int ACTION_GET_SUPPORT_MODE = 72;
    public static final int ACTION_GO_TO_KILL = 254;
    public static final int ACTION_NAVIGATION = 2;
    public static final int ACTION_NONE = 255;
    public static final int ACTION_PAUSE = 20;
    public static final int ACTION_PLAY = 19;
    public static final int ACTION_PLAY_ARTIST = 28;
    public static final int ACTION_PLAY_FAVORITE = 25;
    public static final int ACTION_PLAY_NEXT = 22;
    public static final int ACTION_PLAY_PRE = 21;
    public static final int ACTION_REMOVE_FAVORITE = 65;
    public static final int ACTION_SEEK = 27;
    public static final int ACTION_SET_PLAY_PATTERN = 61;
    public static final String ACTION_START_MEDIA = "byd.intent.action.START_MEDIA";
    public static final int ACTION_SYNC_MEDIA_INFO = 75;
    public static final String MEDIA_ACTION = "MediaAction";
    private static final String MEDIA_CENTER_APP = "com.byd.localmusic";
    public static final String MEDIA_CONTROL_CALLBACK = "MEDIA_CONTROL_CALLBACK";
    public static final String MEDIA_MODE = "MediaMode";
    public static final String MEDIA_PARAM = "MediaParam";
    public static final int MODE_AMAZON = 104;
    public static final int MODE_ANDROIDAUTO = 101;
    public static final int MODE_BLUETOOTH_MUSIC = 8;
    public static final int MODE_CARPLAY = 102;
    public static final int MODE_FCE_AUDIALS = 106;
    public static final int MODE_FCE_AUDIBLE = 107;
    public static final int MODE_FCE_BLOOMBERG = 108;
    public static final int MODE_FCE_DEEZER = 109;
    public static final int MODE_FCE_IHEART = 110;
    public static final int MODE_FCE_LIBBY = 111;
    public static final int MODE_FCE_MELON = 112;
    public static final int MODE_FCE_POCKETCASTS = 113;
    public static final int MODE_FCE_RADIOLINE = 114;
    public static final int MODE_FCE_TIDAL = 115;
    public static final int MODE_FCE_TUNEIN = 116;
    public static final int MODE_FLO = 105;
    public static final int MODE_MUSIC = 1;
    public static final int MODE_NONE = -1;
    public static final int MODE_RADIO = 0;
    public static final int MODE_RADIO_AM = 12;
    public static final int MODE_RADIO_DAB = 13;
    public static final int MODE_RADIO_FM = 11;
    public static final int MODE_SPOTIFY = 103;
    public static final String NAVIGATION_DEST_ALL_LIST = "all_list";
    public static final String NAVIGATION_DEST_FAVORITES = "favorites";
    public static final String PARAM_AM_FM1_FIELD_STRENGTH = "amfm1_field_strength";
    public static final String PARAM_ANDROIDAUTO_FROM_MODE = "PARAM_ANDROIDAUTO_FROM_MODE";
    public static final String PARAM_ARTIST_NAME = "artistname";
    public static final String PARAM_AUDIO_SESSION_ID = "audio_session_id";
    public static final String PARAM_AUTO_PLAY = "auto_play";
    public static final String PARAM_AUTO_VOICE_CODE = "param_auto_voice_code";
    public static final String PARAM_AUTO_VOICE_METHOD = "auto_voice_method";
    public static final String PARAM_AUTO_VOICE_PARAM = "auto_voice_param";
    public static final String PARAM_AUTO_VOICE_RESULT = "auto_voice_result";
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_CURRENT_MODE = "param_current_mode";
    public static final String PARAM_CUSTOM_ACTION = "custom_action";
    public static final String PARAM_DAB_BER = "param_dab_ber";
    public static final String PARAM_DAB_CATEGORY = "param_dab_category";
    public static final String PARAM_DAB_SERVICE_ID = "param_dab_sid";
    public static final String PARAM_FILE_NAME = "fliename";
    public static final String PARAM_FM2_FIELD_STRENGTH = "fm2_field_strength";
    public static final String PARAM_FOREGROUND = "foreground";
    public static final String PARAM_MEDIA_ALBUM = "mediaalbum";
    public static final String PARAM_MEDIA_ARTIST = "mediaartist";
    public static final String PARAM_MEDIA_DURATION = "mediaduration";
    public static final String PARAM_MEDIA_ID = "media_id";
    public static final String PARAM_MEDIA_MODE = "param_media_mode";
    public static final String PARAM_MEDIA_PLAY_STATE = "param_media_play_state";
    public static final String PARAM_MEDIA_POSITION = "mediaposition";
    public static final String PARAM_MEDIA_TITLE = "mediatitle";
    public static final String PARAM_MEDIA_TYPE = "param_media_type";
    public static final String PARAM_NAVIGATION_DEST = "navigation_dest";
    public static final String PARAM_NAVIGATION_IS_CLOSE = "navigation_is_close";
    public static final String PARAM_PACKAGE = "package";
    public static final String PARAM_PATTERN = "pattern";
    public static final String PARAM_PLAY_FROM_MODE = "play_from_mode";
    public static final String PARAM_POSITION = "position";
    public static final String PARAM_PROGRESS = "progress";
    public static final String PARAM_PROVINCE = "province";
    public static final String PARAM_RADIO_FREQ = "radiofreq";
    public static final String PARAM_REASON = "reason";
    public static final String PARAM_SD_PLUGIN = "param_sd_plugin";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_SUCCESS = "success";
    public static final String PARAM_SUPPORT_MODE_LIST = "param_support_mode_list";
    public static final String PARAM_USB_PLUGIN = "param_usb_plugin";
    public static final String PARAM_WITH_UI = "withui";
    public static final int PATTERN_CYCLE = 0;
    public static final int PATTERN_FULL_SCREEN = 1;
    public static final int PATTERN_HALF_SCREEN = 0;
    public static final int PATTERN_RANDOM = 1;
    public static final int PATTERN_SINGLE = 2;
    public static final String REASON_ALREADY_COLLECTED = "already collected";
    public static final String REASON_AUDIO_OUT_OF_FOCUS = "audio_out_of_focus";
    public static final String REASON_DUPLICATED_ACTION = "duplicated_action";
    public static final String REASON_NOT_COLLECTED = "not collected";
    public static final String REASON_NOT_FOUND = "not_found";
    public static final String REASON_NO_MATCH_FILE = "no match media file";
    public static final String REASON_OUT_OF_RANGE_FREQUENCY = "out_of_range_frequency";
    public static final String REASON_UNKNOWN = "unknown reason";
    public static final String REASON_UNKNOWN_PARAM = "unknown_param";
    public static final String REASON_UNSUPPORTED_FREQUENCY = "unsupported_frequency";
    public static final String REASON_UNSUPPORTED_OPERATION = "unsupported_operation";
    public static final int SOURCE_LOCAL = 0;
    public static final int SOURCE_NONE = -1;
    public static final int SOURCE_SD = 2;
    public static final int SOURCE_USB = 1;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_STOP = 2;
    private static final String TAG = "MediaControl";
    private Context mContext;
    public static final String TRUE = Boolean.TRUE.toString();
    public static final String FALSE = Boolean.FALSE.toString();
    private static volatile MediaControl mMediaControl = null;

    public interface Callback {
        void onControlResult(int i, int i2, Result result);
    }

    private MediaControl(Context context) {
        this.mContext = null;
        this.mContext = context;
        Log.d(TAG, "package: " + this.mContext.getPackageName());
    }

    public static MediaControl getInstance(Context context) {
        if (mMediaControl == null) {
            mMediaControl = new MediaControl(context.getApplicationContext());
        }
        return mMediaControl;
    }

    public void controlMedia(int i, int i2, Param param) {
        controlMedia(i, i2, param, null);
    }

    public void controlMedia(int i, int i2, Param param, Callback callback) {
        Log.d(TAG, "controlMedia, mode is: " + i + " action is: " + i2);
        Intent intent = new Intent("byd.intent.action.START_MEDIA");
        intent.setPackage("com.byd.localmusic");
        intent.putExtra("MediaMode", i);
        intent.putExtra("MediaAction", i2);
        if (param == null) {
            param = new Param();
        }
        param.setParam("package", this.mContext.getPackageName());
        intent.putExtra("MediaParam", param.getParam());
        if (callback != null) {
            Bundle bundle = new Bundle();
            bundle.putBinder(MEDIA_CONTROL_CALLBACK, new RemoteMediaControlCallback(callback).asBinder());
            intent.putExtra(MEDIA_CONTROL_CALLBACK, bundle);
        }
        this.mContext.startService(intent);
    }

    public static void controlMedia(Context context, int i, int i2, Param param, Callback callback) {
        getInstance(context).controlMedia(i, i2, param, callback);
    }

    public static class Result {
        private final Bundle result;

        public Result() {
            this.result = new Bundle();
        }

        public Result setResult(String str, String str2) {
            this.result.putString(str, str2);
            return this;
        }

        public Result setResult(String str, int i) {
            this.result.putInt(str, i);
            return this;
        }

        public Result setResult(Map<String, String> map) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.result.putString(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public String getString(String str) {
            return this.result.getString(str);
        }

        public int getInt(String str, int i) {
            return this.result.getInt(str, i);
        }

        public static Result wrap(Map<String, String> map) {
            return new Result(map);
        }

        public Bundle getResult() {
            return new Bundle(this.result);
        }

        private Result(Map<String, String> map) {
            this.result = new Bundle();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.result.putString(entry.getKey(), entry.getValue());
                }
            }
        }

        private Result(Bundle bundle) {
            Bundle bundle2 = new Bundle();
            this.result = bundle2;
            bundle2.putAll(bundle);
        }

        public String toString() {
            return MediaControl.bundleToString("Result", this.result);
        }
    }

    private static class RemoteMediaControlCallback extends IMediaControlCallback.Stub {
        private Callback callback;

        RemoteMediaControlCallback(Callback callback) {
            this.callback = callback;
        }

        @Override // com.byd.mediacenter.api.media.IMediaControlCallback
        public void onControlResult(int i, int i2, Bundle bundle) {
            Callback callback = this.callback;
            if (callback != null) {
                callback.onControlResult(i, i2, new Result(bundle));
            }
        }
    }

    public static class Param {
        private HashMap<String, String> mParam;

        public Param() {
            this.mParam = new HashMap<>();
        }

        public Param(HashMap<String, String> map) {
            this.mParam = new HashMap<>();
            if (map == null) {
                Log.d(MediaControl.TAG, "MediaControlParam, param is null");
            } else {
                this.mParam = map;
            }
        }

        public void setParam(String str, int i) {
            this.mParam.put(str, String.valueOf(i));
        }

        public void setParam(String str, String str2) {
            this.mParam.put(str, str2);
        }

        public HashMap<String, String> getParam() {
            return this.mParam;
        }

        public int getIntParam(String str) {
            String str2 = this.mParam.get(str);
            if (str2 == null) {
                return -1;
            }
            int i = Integer.parseInt(str2);
            Log.d(MediaControl.TAG, "getIntParam, key is: " + str + " value is: " + i);
            return i;
        }

        public String getStringParam(String str) {
            return this.mParam.get(str);
        }
    }

    public static String bundleToString(String str, Bundle bundle) {
        if (bundle.size() == 0) {
            return str + "{null}";
        }
        Set<String> setKeySet = bundle.keySet();
        String[] strArr = (String[]) setKeySet.toArray(new String[setKeySet.size()]);
        StringBuilder sb = new StringBuilder(strArr.length * 28);
        sb.append(str);
        sb.append("{");
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(strArr[i]);
            sb.append("=");
            sb.append(bundle.get(strArr[i]));
        }
        sb.append('}');
        return sb.toString();
    }

    public static void onSuccess(int i, int i2, Callback callback) {
        if (callback != null) {
            Result result = new Result();
            result.setResult(PARAM_SUCCESS, TRUE);
            callback.onControlResult(i, i2, result);
        }
    }

    public static void onFailed(int i, int i2, String str, Callback callback) {
        if (callback != null) {
            Result result = new Result();
            result.setResult(PARAM_SUCCESS, FALSE);
            result.setResult("reason", str);
            callback.onControlResult(i, i2, result);
        }
    }
}
