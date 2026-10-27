package android.hardware.bydauto.instrument.tsimpl;

import android.content.Context;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.data.Constants;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InstrumentMediaManagerImpl {
    private static final int DATA_TYPE_INSTRUMENT_UI_STATE = 36;
    private static final int DATA_TYPE_NAVI_ABANDON_MAP_FOCUS = 34;
    private static final int DATA_TYPE_NAVI_REQUEST_MAP_FOCUS = 33;
    private static final int DATA_TYPE_NAVI_SEND_DISPLAY_MODE = 30;
    private static final int DATA_TYPE_NAVI_SEND_NAVI_STATE = 35;
    private static final int DATA_TYPE_NAVI_SEND_PRESENTATION_STATE = 31;
    private static final int DATA_TYPE_NAVI_TBT_INFO = 32;
    private static final int DATA_TYPE_NEW_NAVI_TBT_INFO = 37;
    private static final String KEY_BUNDLE_ARRIVAL_DEST_TIME = "arriveToDestTime";
    private static final String KEY_BUNDLE_ARRIVAL_DEST_TIME_DAY = "arriveToDestTimeDay";
    private static final String KEY_BUNDLE_DIST_TO_DEST = "distToDest";
    private static final String KEY_BUNDLE_DIST_TO_DEST_DIS_DATA = "distToDestDisData";
    private static final String KEY_BUNDLE_DIST_TO_DEST_DIS_UNIT = "distToDestDisUnit";
    private static final String KEY_BUNDLE_DIST_TO_DEST_TIME = "distToDestTime";
    private static final String KEY_BUNDLE_DIST_TO_DEST_UNIT = "distToDestUnit";
    private static final String KEY_BUNDLE_INSTRUMENT_UI_STATE = "uiState";
    private static final String KEY_BUNDLE_MAP_TYPE = "mapType";
    private static final String KEY_BUNDLE_MULTIPLE_TEXT = "multipleText";
    private static final String KEY_BUNDLE_MULTIPLE_TEXT_ONE = "multipleText1";
    private static final String KEY_BUNDLE_MULTIPLE_TEXT_TWO = "multipleText2";
    private static final String KEY_BUNDLE_NAVI_DISPLAY_MODE = "mode";
    private static final String KEY_BUNDLE_NAVI_IS_FOREGROUND = "isForeground";
    private static final String KEY_BUNDLE_NAVI_MAP_SOURCE = "mapSource";
    private static final String KEY_BUNDLE_NAVI_PRESENTATION_STATE = "state";
    private static final String KEY_BUNDLE_NAVI_STATE = "naviState";
    private static final String KEY_BUNDLE_NEXT_DIST_UNIT = "nextDistUnit";
    private static final String KEY_BUNDLE_NEXT_NEXT_DIST_UNIT = "nextNextDistUnit";
    private static final String KEY_BUNDLE_NEXT_NEXT_ROAD_DIST = "nextNextRoadDist";
    private static final String KEY_BUNDLE_NEXT_NEXT_ROAD_DIST_DATA = "nextNextDistData";
    private static final String KEY_BUNDLE_NEXT_NEXT_ROAD_DIST_UNIT = "nextNextDistAndUnit";
    private static final String KEY_BUNDLE_NEXT_NEXT_TBT_ICON = "nextNextTbtIcon";
    private static final String KEY_BUNDLE_NEXT_ROAD_DIST = "nextRoadDist";
    private static final String KEY_BUNDLE_NEXT_ROAD_DIST_DATA = "nextRoadDistData";
    private static final String KEY_BUNDLE_NEXT_ROAD_DIST_UNIT = "nextRoadDistUnit";
    private static final String KEY_BUNDLE_NEXT_ROAD_NAME = "nextRoadName";
    private static final String KEY_BUNDLE_NEXT_TBT_ICON = "nextTbtIcon";
    private static final String KEY_BUNDLE_TO_DEST_DAYS = "toDestDays";
    private static final String KEY_BUNDLE_TO_DEST_HOURS = "toDestHours";
    private static final String KEY_BUNDLE_TO_DEST_MINUTES = "toDestMinutes";
    private static final String KEY_BUNDLE_TO_DEST_TIME = "toDestTime";
    private static final String KEY_MSG_TYPE = "msgType";
    private static final String KEY_SUB_TYPE = "subtype";
    private static final int MSG_TYPE_MEDIA = 1;
    private static final int MSG_TYPE_NAVI = 3;
    private static final int MSG_TYPE_PHONE = 2;
    private static final String SETTINGS_KEY_DISPLAY_MODE = "moet_extension_navi_display_mode";
    private static final String SETTINGS_KEY_FOCUS_MAP = "moet_extension_navi_focus_map";
    private static final int SUB_TYPE_CALL_INFO = 6;
    private static final int SUB_TYPE_MUSIC_INFO = 4;
    private static final int SUB_TYPE_MUSIC_PROGRESS = 5;
    private static final int SUB_TYPE_NAVI_PRESENTATION_STATE = 15;
    private static final int SUB_TYPE_NAVI_SEND_NAVI_STATE = 2;
    private static final int SUB_TYPE_NAVI_SEND_NAVI_STATE_2_AVM = 103;
    private static final int SUB_TYPE_NAVI_SET_MODE = 3;
    private static final int SUB_TYPE_NAVI_SIMPLE_INFO = 7;
    private static final int SUB_TYPE_NEW_NAVI_SIMPLE_TBT_INFO = 20;
    private static final String TAG = "InstrumentManagerImpl";
    private static volatile InstrumentMediaManagerImpl sInstance;
    private ClusterCommManager mClusterCommManager;
    private Context mContext;
    private HandlerThread mHandlerThread;
    private Handler mLocalHandler;
    private final List<AbsBYDAutoInstrumentListener> mListeners = new ArrayList();
    private boolean isCallbackRegistered = false;
    private boolean mIgnoreBydPermission = false;
    private IClusterCommCallback.Stub mClusterCallback = new IClusterCommCallback.Stub() { // from class: android.hardware.bydauto.instrument.tsimpl.InstrumentMediaManagerImpl.1
        @Override // com.ts.lib.cluster.common.IClusterCommCallback
        public void onReceiveCommand(Bundle bundle) throws RemoteException {
            if (bundle == null) {
                Log.w(InstrumentMediaManagerImpl.TAG, "onReceiveCommand bundle data is null, check the code!");
                return;
            }
            Log.d(InstrumentMediaManagerImpl.TAG, "onReceiveCommand bundle data:" + bundle);
            int dataType = bundle.getInt(Constants.Key.DATA_TYPE);
            Message message = InstrumentMediaManagerImpl.this.mLocalHandler.obtainMessage();
            message.obj = bundle;
            message.what = dataType;
            InstrumentMediaManagerImpl.this.mLocalHandler.sendMessage(message);
        }
    };

    private InstrumentMediaManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        HandlerThread handlerThread = new HandlerThread(BYDAutoInstrumentDevice.class.getName() + "_LocalThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mLocalHandler = new LocalHandler(this.mHandlerThread.getLooper());
        this.mClusterCommManager = ClusterCommManager.getInstance(this.mContext);
    }

    public static InstrumentMediaManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (InstrumentMediaManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentMediaManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public void registerListener(AbsBYDAutoInstrumentListener listener) {
        Log.d(TAG, "subscribeInstrumentCallback, listener:" + listener);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (listener == null) {
            return;
        }
        synchronized (this) {
            if (!this.mListeners.contains(listener)) {
                this.mListeners.add(listener);
            }
            if (!this.isCallbackRegistered) {
                this.isCallbackRegistered = true;
                this.mClusterCommManager.registerCallback(1, this.mClusterCallback);
                this.mClusterCommManager.registerCallback(64, this.mClusterCallback);
            }
        }
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener listener) {
        Log.d(TAG, "unsubscribeInstrumentCallback, listener:" + listener);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (listener == null) {
            return;
        }
        synchronized (this) {
            this.mListeners.remove(listener);
            if (this.isCallbackRegistered && this.mListeners.size() == 0) {
                this.isCallbackRegistered = false;
                this.mClusterCommManager.unregisterCallback(1, this.mClusterCallback);
                this.mClusterCommManager.unregisterCallback(64, this.mClusterCallback);
            }
        }
    }

    public int sendMusicInfoUrl(String musicName, String artistName, int playState, int musicSource, String imgUrl) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 1);
        bundle.putInt(KEY_SUB_TYPE, 4);
        bundle.putInt(Constants.Key.DATA_TYPE, 1);
        bundle.putString(Constants.Key.MEDIA_NAME, musicName == null ? "" : musicName);
        bundle.putString(Constants.Key.MEDIA_SINGER, artistName == null ? "" : artistName);
        bundle.putInt(Constants.Key.MEDIA_STATE, playState);
        bundle.putInt(Constants.Key.MUSIC_SOURCE, musicSource);
        bundle.putString(Constants.Key.MEDIA_COVER_URI, imgUrl != null ? imgUrl : "");
        boolean res = this.mClusterCommManager.sendCommand(1, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendMusicProgress(int currentProgress, int maxProgress) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 1);
        bundle.putInt(KEY_SUB_TYPE, 5);
        bundle.putInt(Constants.Key.DATA_TYPE, 2);
        bundle.putInt(Constants.Key.MEDIA_CUR_TIME, currentProgress);
        bundle.putInt(Constants.Key.MEDIA_TOTAL_TIME, maxProgress);
        boolean res = this.mClusterCommManager.sendCommand(1, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendCallInfo(String phoneNumber, String contactName, int callState, int callTime) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 2);
        bundle.putInt(KEY_SUB_TYPE, 6);
        bundle.putInt(Constants.Key.DATA_TYPE, 11);
        bundle.putString(Constants.Key.CALL_NUM, phoneNumber == null ? "" : phoneNumber);
        bundle.putString(Constants.Key.CALL_NAME, contactName != null ? contactName : "");
        bundle.putInt(Constants.Key.CALL_STATE, callState);
        bundle.putInt(Constants.Key.CALL_ACTIVIE_TIME, callTime);
        boolean res = this.mClusterCommManager.sendCommand(8, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendCallInfo(String phoneNumber, String contactName, int callState, int callTime, int callType) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 2);
        bundle.putInt(KEY_SUB_TYPE, 6);
        bundle.putInt(Constants.Key.DATA_TYPE, 11);
        bundle.putString(Constants.Key.CALL_NUM, phoneNumber == null ? "" : phoneNumber);
        bundle.putString(Constants.Key.CALL_NAME, contactName != null ? contactName : "");
        bundle.putInt(Constants.Key.CALL_STATE, callState);
        bundle.putInt(Constants.Key.CALL_ACTIVIE_TIME, callTime);
        bundle.putInt(Constants.Key.CALL_TYPE, callType);
        boolean res = this.mClusterCommManager.sendCommand(8, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int setNaviState(int i, int i2, boolean z, String str) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        Log.i(TAG, "setNaviState:mapType" + i + " naviState:" + i2 + " isForeground:" + z + " mapSource:" + str);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "setNaviState failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 103);
        bundle.putInt(Constants.Key.DATA_TYPE, 103);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
        bundle.putInt(KEY_BUNDLE_NAVI_STATE, i2);
        bundle.putInt(KEY_BUNDLE_NAVI_IS_FOREGROUND, z ? 1 : 0);
        bundle.putString(KEY_BUNDLE_NAVI_MAP_SOURCE, str);
        if (this.mClusterCommManager.sendCommand(16, bundle)) {
            return 0;
        }
        Log.w(TAG, "setNaviState failed, may be not connected, pls retry!");
        return -2147482648;
    }

    private class LocalHandler extends Handler {
        public LocalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int dataType = msg.what;
            Bundle bundle = (Bundle) msg.obj;
            if (dataType == 1) {
                InstrumentMediaManagerImpl.this.callbackMusicInfoToListeners(bundle);
                return;
            }
            if (dataType == 30) {
                InstrumentMediaManagerImpl.this.callbackDisplayModeChangedToListeners(bundle);
                return;
            }
            if (dataType == 33) {
                InstrumentMediaManagerImpl.this.callbackMapFocusChangedToListeners(bundle);
                return;
            }
            if (dataType == 36) {
                InstrumentMediaManagerImpl.this.callbackUiStateToListeners(bundle);
                return;
            }
            Log.d(InstrumentMediaManagerImpl.TAG, "unknown DataType " + dataType + ", do not callback this bundle data");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMusicInfoToListeners(Bundle bundle) {
        String musicName = bundle.getString(Constants.Key.MEDIA_NAME, null);
        String artist = bundle.getString(Constants.Key.MEDIA_SINGER, null);
        int mediaState = bundle.getInt(Constants.Key.MEDIA_STATE, 0);
        int musicSource = bundle.getInt(Constants.Key.MUSIC_SOURCE, 11);
        String imgUrl = bundle.getString(Constants.Key.MEDIA_COVER_URI, null);
        Log.d(TAG, "callback music info:, mediaState:" + mediaState + ", musicSource:" + musicSource + ", imgUrl:" + imgUrl);
        List<AbsBYDAutoInstrumentListener> instrumentListeners = new ArrayList<>(this.mListeners.size());
        synchronized (this) {
            instrumentListeners.addAll(this.mListeners);
        }
        for (AbsBYDAutoInstrumentListener listener : instrumentListeners) {
            listener.onMusicInfoUrlChange(musicName, artist, mediaState, musicSource, imgUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDisplayModeChangedToListeners(Bundle bundle) {
        int mode = bundle.getInt(KEY_BUNDLE_NAVI_DISPLAY_MODE, 0);
        Log.d(TAG, "callback displayMode info, mode:" + mode);
        List<AbsBYDAutoInstrumentListener> instrumentListeners = new ArrayList<>(this.mListeners.size());
        synchronized (this) {
            instrumentListeners.addAll(this.mListeners);
        }
        for (AbsBYDAutoInstrumentListener listener : instrumentListeners) {
            listener.onINSNavDisplayMode(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMapFocusChangedToListeners(Bundle bundle) {
        int mapType = bundle.getInt(KEY_BUNDLE_MAP_TYPE, 0);
        Log.d(TAG, "callback focus mapType:" + mapType);
        List<AbsBYDAutoInstrumentListener> instrumentListeners = new ArrayList<>(this.mListeners.size());
        synchronized (this) {
            instrumentListeners.addAll(this.mListeners);
        }
        for (AbsBYDAutoInstrumentListener listener : instrumentListeners) {
            listener.onMapFocusChanged(mapType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackUiStateToListeners(Bundle bundle) {
        int uiState = bundle.getInt(KEY_BUNDLE_INSTRUMENT_UI_STATE, 0);
        Log.d(TAG, "callback uiState to apps, uiState:" + uiState);
        List<AbsBYDAutoInstrumentListener> instrumentListeners = new ArrayList<>(this.mListeners.size());
        synchronized (this) {
            instrumentListeners.addAll(this.mListeners);
        }
        for (AbsBYDAutoInstrumentListener listener : instrumentListeners) {
            listener.onInsUiChanged(uiState);
        }
    }

    public int setInsNaviDisplayMode(int mode) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 3);
        bundle.putInt(Constants.Key.DATA_TYPE, 30);
        bundle.putInt(KEY_BUNDLE_NAVI_DISPLAY_MODE, mode);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "setInsNaviDisplayMode failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int getInsNaviDisplayMode() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTINGS_KEY_DISPLAY_MODE, 0);
    }

    public int sendNaviPresentationState(int mapType, int state) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 15);
        bundle.putInt(Constants.Key.DATA_TYPE, 31);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        bundle.putInt(KEY_BUNDLE_NAVI_PRESENTATION_STATE, state);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendNaviPresentationState failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int sendTbtNaviInfo(int mapType, int nextTbtIcon, String nextRoadName, float nextRoadDist, int nextDistUnit, int nextNextTbtIcon, float nextNextRoadDist, int nextNextDistUnit, float distToDest, int distToDestUnit, int toDestTime, int toDestDays, int toDestHours, int toDestMinutes) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "sendTbtNaviInfo failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 7);
        bundle.putInt(Constants.Key.DATA_TYPE, 32);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        bundle.putInt(KEY_BUNDLE_NEXT_TBT_ICON, nextTbtIcon);
        bundle.putString(KEY_BUNDLE_NEXT_ROAD_NAME, nextRoadName);
        bundle.putFloat(KEY_BUNDLE_NEXT_ROAD_DIST, nextRoadDist);
        bundle.putInt(KEY_BUNDLE_NEXT_DIST_UNIT, nextDistUnit);
        bundle.putInt(KEY_BUNDLE_NEXT_NEXT_TBT_ICON, nextNextTbtIcon);
        bundle.putFloat(KEY_BUNDLE_NEXT_NEXT_ROAD_DIST, nextNextRoadDist);
        bundle.putInt(KEY_BUNDLE_NEXT_NEXT_DIST_UNIT, nextNextDistUnit);
        bundle.putFloat(KEY_BUNDLE_DIST_TO_DEST, distToDest);
        bundle.putInt(KEY_BUNDLE_DIST_TO_DEST_UNIT, distToDestUnit);
        bundle.putInt(KEY_BUNDLE_TO_DEST_TIME, toDestTime);
        bundle.putInt(KEY_BUNDLE_TO_DEST_DAYS, toDestDays);
        bundle.putInt(KEY_BUNDLE_TO_DEST_HOURS, toDestHours);
        bundle.putInt(KEY_BUNDLE_TO_DEST_MINUTES, toDestMinutes);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendTbtNaviInfo failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int sendTbtSimpleNaviInfo(int mapType, int nextTbtIcon, String nextRoadName, String nextRoadDistData, String nextRoadDistUnit, int nextNextTbtIcon, String nextNextDistData, String nextNextDistUnit, String distToDestDisData, String distToDestDisUnit, String distToDestTime, String toDestDays, String toDestTime, String multipleText, String multipleText1, String multipleText2) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "sendTbtNaviInfo failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 20);
        bundle.putInt(Constants.Key.DATA_TYPE, 37);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        bundle.putInt(KEY_BUNDLE_NEXT_TBT_ICON, nextTbtIcon);
        bundle.putString(KEY_BUNDLE_NEXT_ROAD_NAME, nextRoadName);
        bundle.putString(KEY_BUNDLE_NEXT_ROAD_DIST_DATA, nextRoadDistData);
        bundle.putString(KEY_BUNDLE_NEXT_ROAD_DIST_UNIT, nextRoadDistUnit);
        bundle.putInt(KEY_BUNDLE_NEXT_NEXT_TBT_ICON, nextNextTbtIcon);
        bundle.putString(KEY_BUNDLE_NEXT_NEXT_ROAD_DIST_DATA, nextNextDistData);
        bundle.putString(KEY_BUNDLE_NEXT_NEXT_ROAD_DIST_UNIT, nextNextDistUnit);
        bundle.putString(KEY_BUNDLE_DIST_TO_DEST_DIS_DATA, distToDestDisData);
        bundle.putString(KEY_BUNDLE_DIST_TO_DEST_DIS_UNIT, distToDestDisUnit);
        bundle.putString(KEY_BUNDLE_DIST_TO_DEST_TIME, distToDestTime);
        bundle.putString(KEY_BUNDLE_ARRIVAL_DEST_TIME_DAY, toDestDays);
        bundle.putString(KEY_BUNDLE_ARRIVAL_DEST_TIME, toDestTime);
        bundle.putString(KEY_BUNDLE_MULTIPLE_TEXT, multipleText);
        bundle.putString(KEY_BUNDLE_MULTIPLE_TEXT_ONE, multipleText1);
        bundle.putString(KEY_BUNDLE_MULTIPLE_TEXT_TWO, multipleText2);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendTbtSimpleNaviInfo failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int requestMapFocus(int mapType) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "requestMapFocus failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(Constants.Key.DATA_TYPE, 33);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "requestMapFocus failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int abandonMapFocus(int mapType) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "abandonMapFocus failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(Constants.Key.DATA_TYPE, 34);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "abandonMapFocus failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int getFocusMapType() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTINGS_KEY_FOCUS_MAP, 0);
    }

    public int sendNaviState(int mapType, int naviState) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            Log.w(TAG, "sendNaviState failed, service not connected, pls retry!");
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 2);
        bundle.putInt(Constants.Key.DATA_TYPE, 35);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, mapType);
        bundle.putInt(KEY_BUNDLE_NAVI_STATE, naviState);
        boolean res = this.mClusterCommManager.sendCommand(16, bundle);
        if (res) {
            return 0;
        }
        Log.w(TAG, "sendNaviState failed, may be not connected, pls retry!");
        return -2147482648;
    }
}
