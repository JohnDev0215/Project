package android.hardware.bydauto.instrument.tsimpl;

import android.content.Context;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.common.IClusterCommCallback;
import java.util.ArrayList;
import java.util.Iterator;
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
    private static final String KEY_BUNDLE_DIST_TO_DEST = "distToDest";
    private static final String KEY_BUNDLE_DIST_TO_DEST_UNIT = "distToDestUnit";
    private static final String KEY_BUNDLE_INSTRUMENT_UI_STATE = "uiState";
    private static final String KEY_BUNDLE_MAP_TYPE = "mapType";
    private static final String KEY_BUNDLE_NAVI_DISPLAY_MODE = "mode";
    private static final String KEY_BUNDLE_NAVI_PRESENTATION_STATE = "state";
    private static final String KEY_BUNDLE_NAVI_STATE = "naviState";
    private static final String KEY_BUNDLE_NEXT_DIST_UNIT = "nextDistUnit";
    private static final String KEY_BUNDLE_NEXT_NEXT_DIST_UNIT = "nextNextDistUnit";
    private static final String KEY_BUNDLE_NEXT_NEXT_ROAD_DIST = "nextNextRoadDist";
    private static final String KEY_BUNDLE_NEXT_NEXT_TBT_ICON = "nextNextTbtIcon";
    private static final String KEY_BUNDLE_NEXT_ROAD_DIST = "nextRoadDist";
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
    private static final int SUB_TYPE_NAVI_SET_MODE = 3;
    private static final int SUB_TYPE_NAVI_SIMPLE_INFO = 7;
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
        public void onReceiveCommand(Bundle bundle) {
            if (bundle == null) {
                Log.w(InstrumentMediaManagerImpl.TAG, "onReceiveCommand bundle data is null, check the code!");
                return;
            }
            Log.d(InstrumentMediaManagerImpl.TAG, "onReceiveCommand bundle data:" + bundle);
            int i = bundle.getInt("DataType");
            Message messageObtainMessage = InstrumentMediaManagerImpl.this.mLocalHandler.obtainMessage();
            messageObtainMessage.obj = bundle;
            messageObtainMessage.what = i;
            InstrumentMediaManagerImpl.this.mLocalHandler.sendMessage(messageObtainMessage);
        }
    };

    private class LocalHandler extends Handler {
        public LocalHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            Bundle bundle = (Bundle) message.obj;
            if (i == 1) {
                InstrumentMediaManagerImpl.this.callbackMusicInfoToListeners(bundle);
                return;
            }
            if (i == 30) {
                InstrumentMediaManagerImpl.this.callbackDisplayModeChangedToListeners(bundle);
                return;
            }
            if (i == 33) {
                InstrumentMediaManagerImpl.this.callbackMapFocusChangedToListeners(bundle);
                return;
            }
            if (i == 36) {
                InstrumentMediaManagerImpl.this.callbackUiStateToListeners(bundle);
                return;
            }
            Log.d(InstrumentMediaManagerImpl.TAG, "unknown DataType " + i + ", do not callback this bundle data");
        }
    }

    private InstrumentMediaManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        HandlerThread handlerThread = new HandlerThread(BYDAutoInstrumentDevice.class.getName() + "_LocalThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mLocalHandler = new LocalHandler(this.mHandlerThread.getLooper());
        this.mClusterCommManager = ClusterCommManager.getInstance(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDisplayModeChangedToListeners(Bundle bundle) {
        int i = bundle.getInt(KEY_BUNDLE_NAVI_DISPLAY_MODE, 0);
        Log.d(TAG, "callback displayMode info, mode:" + i);
        ArrayList arrayList = new ArrayList(this.mListeners.size());
        synchronized (this) {
            arrayList.addAll(this.mListeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoInstrumentListener) it.next()).onINSNavDisplayMode(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMapFocusChangedToListeners(Bundle bundle) {
        int i = bundle.getInt(KEY_BUNDLE_MAP_TYPE, 0);
        Log.d(TAG, "callback focus mapType:" + i);
        ArrayList arrayList = new ArrayList(this.mListeners.size());
        synchronized (this) {
            arrayList.addAll(this.mListeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoInstrumentListener) it.next()).onMapFocusChanged(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackMusicInfoToListeners(Bundle bundle) {
        String string = bundle.getString("MediaName", null);
        String string2 = bundle.getString("MediaSinger", null);
        int i = bundle.getInt("MediaState", 0);
        int i2 = bundle.getInt("MusicSource", 11);
        String string3 = bundle.getString("MediaCoverUri", null);
        Log.d(TAG, "callback music info:, mediaState:" + i + ", musicSource:" + i2 + ", imgUrl:" + string3);
        ArrayList arrayList = new ArrayList(this.mListeners.size());
        synchronized (this) {
            arrayList.addAll(this.mListeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoInstrumentListener) it.next()).onMusicInfoUrlChange(string, string2, i, i2, string3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackUiStateToListeners(Bundle bundle) {
        int i = bundle.getInt(KEY_BUNDLE_INSTRUMENT_UI_STATE, 0);
        Log.d(TAG, "callback uiState to apps, uiState:" + i);
        ArrayList arrayList = new ArrayList(this.mListeners.size());
        synchronized (this) {
            arrayList.addAll(this.mListeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((AbsBYDAutoInstrumentListener) it.next()).onInsUiChanged(i);
        }
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static InstrumentMediaManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (InstrumentMediaManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new InstrumentMediaManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int abandonMapFocus(int i) {
        String str;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (this.mClusterCommManager.isServiceReady()) {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_MSG_TYPE, 3);
            bundle.putInt("DataType", 34);
            bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
            if (this.mClusterCommManager.sendCommand(16, bundle)) {
                return 0;
            }
            str = "abandonMapFocus failed, may be not connected, pls retry!";
        } else {
            str = "abandonMapFocus failed, service not connected, pls retry!";
        }
        Log.w(TAG, str);
        return -2147482648;
    }

    public int getFocusMapType() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTINGS_KEY_FOCUS_MAP, 0);
    }

    public int getInsNaviDisplayMode() {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_GET);
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTINGS_KEY_DISPLAY_MODE, 0);
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        Log.d(TAG, "subscribeInstrumentCallback, listener:" + absBYDAutoInstrumentListener);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (absBYDAutoInstrumentListener == null) {
            return;
        }
        synchronized (this) {
            if (!this.mListeners.contains(absBYDAutoInstrumentListener)) {
                this.mListeners.add(absBYDAutoInstrumentListener);
            }
            if (!this.isCallbackRegistered) {
                this.isCallbackRegistered = true;
                this.mClusterCommManager.registerCallback(1, this.mClusterCallback);
                this.mClusterCommManager.registerCallback(64, this.mClusterCallback);
            }
        }
    }

    public int requestMapFocus(int i) {
        String str;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (this.mClusterCommManager.isServiceReady()) {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_MSG_TYPE, 3);
            bundle.putInt("DataType", 33);
            bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
            if (this.mClusterCommManager.sendCommand(16, bundle)) {
                return 0;
            }
            str = "requestMapFocus failed, may be not connected, pls retry!";
        } else {
            str = "requestMapFocus failed, service not connected, pls retry!";
        }
        Log.w(TAG, str);
        return -2147482648;
    }

    public int sendCallInfo(String str, String str2, int i, int i2) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 2);
        bundle.putInt(KEY_SUB_TYPE, 6);
        bundle.putInt("DataType", 11);
        if (str == null) {
            str = "";
        }
        bundle.putString("CallNum", str);
        if (str2 == null) {
            str2 = "";
        }
        bundle.putString("CallName", str2);
        bundle.putInt("CallState", i);
        bundle.putInt("CallActiveTime", i2);
        if (this.mClusterCommManager.sendCommand(8, bundle)) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendMusicInfoUrl(String str, String str2, int i, int i2, String str3) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 1);
        bundle.putInt(KEY_SUB_TYPE, 4);
        bundle.putInt("DataType", 1);
        if (str == null) {
            str = "";
        }
        bundle.putString("MediaName", str);
        if (str2 == null) {
            str2 = "";
        }
        bundle.putString("MediaSinger", str2);
        bundle.putInt("MediaState", i);
        bundle.putInt("MusicSource", i2);
        if (str3 == null) {
            str3 = "";
        }
        bundle.putString("MediaCoverUri", str3);
        if (this.mClusterCommManager.sendCommand(1, bundle)) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendMusicProgress(int i, int i2) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 1);
        bundle.putInt(KEY_SUB_TYPE, 5);
        bundle.putInt("DataType", 2);
        bundle.putInt("MediaCurTime", i);
        bundle.putInt("MediaTotalTime", i2);
        if (this.mClusterCommManager.sendCommand(1, bundle)) {
            return 0;
        }
        Log.w(TAG, "sendCommand failed, may be not connected with service, pls retry!");
        return -2147482648;
    }

    public int sendNaviPresentationState(int i, int i2) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 15);
        bundle.putInt("DataType", 31);
        bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
        bundle.putInt(KEY_BUNDLE_NAVI_PRESENTATION_STATE, i2);
        if (this.mClusterCommManager.sendCommand(16, bundle)) {
            return 0;
        }
        Log.w(TAG, "sendNaviPresentationState failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public int sendNaviState(int i, int i2) {
        String str;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mClusterCommManager.isServiceReady()) {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_MSG_TYPE, 3);
            bundle.putInt(KEY_SUB_TYPE, 2);
            bundle.putInt("DataType", 35);
            bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
            bundle.putInt(KEY_BUNDLE_NAVI_STATE, i2);
            if (this.mClusterCommManager.sendCommand(16, bundle)) {
                return 0;
            }
            str = "sendNaviState failed, may be not connected, pls retry!";
        } else {
            str = "sendNaviState failed, service not connected, pls retry!";
        }
        Log.w(TAG, str);
        return -2147482648;
    }

    public int sendTbtNaviInfo(int i, int i2, String str, float f, int i3, int i4, float f2, int i5, float f3, int i6, int i7, int i8, int i9, int i10) {
        String str2;
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (this.mClusterCommManager.isServiceReady()) {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_MSG_TYPE, 3);
            bundle.putInt(KEY_SUB_TYPE, 7);
            bundle.putInt("DataType", 32);
            bundle.putInt(KEY_BUNDLE_MAP_TYPE, i);
            bundle.putInt(KEY_BUNDLE_NEXT_TBT_ICON, i2);
            bundle.putString(KEY_BUNDLE_NEXT_ROAD_NAME, str);
            bundle.putFloat(KEY_BUNDLE_NEXT_ROAD_DIST, f);
            bundle.putInt(KEY_BUNDLE_NEXT_DIST_UNIT, i3);
            bundle.putInt(KEY_BUNDLE_NEXT_NEXT_TBT_ICON, i4);
            bundle.putFloat(KEY_BUNDLE_NEXT_NEXT_ROAD_DIST, f2);
            bundle.putInt(KEY_BUNDLE_NEXT_NEXT_DIST_UNIT, i5);
            bundle.putFloat(KEY_BUNDLE_DIST_TO_DEST, f3);
            bundle.putInt(KEY_BUNDLE_DIST_TO_DEST_UNIT, i6);
            bundle.putInt(KEY_BUNDLE_TO_DEST_TIME, i7);
            bundle.putInt(KEY_BUNDLE_TO_DEST_DAYS, i8);
            bundle.putInt(KEY_BUNDLE_TO_DEST_HOURS, i9);
            bundle.putInt(KEY_BUNDLE_TO_DEST_MINUTES, i10);
            if (this.mClusterCommManager.sendCommand(16, bundle)) {
                return 0;
            }
            str2 = "sendTbtNaviInfo failed, may be not connected, pls retry!";
        } else {
            str2 = "sendTbtNaviInfo failed, service not connected, pls retry!";
        }
        Log.w(TAG, str2);
        return -2147482648;
    }

    public int setInsNaviDisplayMode(int i) {
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_SET);
        if (!this.mClusterCommManager.isServiceReady()) {
            return -2147482648;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MSG_TYPE, 3);
        bundle.putInt(KEY_SUB_TYPE, 3);
        bundle.putInt("DataType", 30);
        bundle.putInt(KEY_BUNDLE_NAVI_DISPLAY_MODE, i);
        if (this.mClusterCommManager.sendCommand(16, bundle)) {
            return 0;
        }
        Log.w(TAG, "setInsNaviDisplayMode failed, may be not connected, pls retry!");
        return -2147482648;
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        Log.d(TAG, "unsubscribeInstrumentCallback, listener:" + absBYDAutoInstrumentListener);
        enforcePermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        if (absBYDAutoInstrumentListener == null) {
            return;
        }
        synchronized (this) {
            this.mListeners.remove(absBYDAutoInstrumentListener);
            if (this.isCallbackRegistered && this.mListeners.size() == 0) {
                this.isCallbackRegistered = false;
                this.mClusterCommManager.unregisterCallback(1, this.mClusterCallback);
                this.mClusterCommManager.unregisterCallback(64, this.mClusterCallback);
            }
        }
    }
}
