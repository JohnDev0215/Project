package com.ts.lib.cluster.adapter.someip;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.adapter.MediaCoverUtil;
import com.ts.lib.cluster.adapter.OnDataFinishedListener;
import com.ts.lib.cluster.adapter.someip.SomeIpConstants;
import com.ts.lib.cluster.adapter.utils.MapFocusManager;
import com.ts.lib.cluster.adapter.utils.OnMapFocusChangeListener;
import com.ts.lib.cluster.utils.LogUtil;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.qnxmanager.QnxManager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SomeIpClusterAdapter {
    private static final int DATA_TYPE_INSTRUMENT_UI_STATE = 36;
    private static final int DATA_TYPE_NAVI_ABANDON_MAP_FOCUS = 34;
    private static final int DATA_TYPE_NAVI_REQUEST_MAP_FOCUS = 33;
    private static final int DATA_TYPE_NAVI_SEND_DISPLAY_MODE = 30;
    private static final int DATA_TYPE_NAVI_SEND_NAVI_STATE = 35;
    private static final int DATA_TYPE_NAVI_SEND_PRESENTATION_STATE = 31;
    private static final int DATA_TYPE_NAVI_TBT_INFO = 32;
    private static final int DATA_TYPE_NEW_NAVI_TBT_INFO = 37;
    private static final int EXIST_COVER = 1;
    private static final int INEXIST_COVER = 0;
    private static final int IVI_MODE_FULL = 3;
    private static final int IVI_MODE_ORG = 1;
    private static final int IVI_NAVI_CLOSED = 2;
    private static final String KEY_BUNDLE_INSTRUMENT_UI_STATE = "uiState";
    private static final String KEY_BUNDLE_MAP_TYPE = "mapType";
    private static final String KEY_BUNDLE_NAVI_DISPLAY_MODE = "mode";
    private static final String KEY_BUNDLE_QUERY_NUMBER = "queryNumber";
    private static final String KEY_MSG_TYPE = "msgType";
    private static final String KEY_SUB_TYPE = "subtype";
    private static final int MSG_TYPE_NAVI = 3;
    private static final int NAVI_ON = 1;
    private static final int NUM_NEGATIVE_ONE = -1;
    private static final int QUERY_NUMBER_REQUEST_SYNC_NAVI_MODE = 1;
    private static final int RETRY_DELAYED = 300;
    private static final String SETTINGS_KEY_DISPLAY_MODE = "moet_extension_navi_display_mode";
    private static final String SETTINGS_KEY_FOCUS_MAP = "moet_extension_navi_focus_map";
    private static final int SUB_TYPE_NAVI_FOCUS = 14;
    private static final int SUB_TYPE_NAVI_ON_MODE_CHANGED = 1;
    private static final int SUB_TYPE_NAVI_SET_MODE = 3;
    private static final int SUB_TYPE_NAVI_SYNC_MODE_REQUEST = 13;
    private static final int SUB_TYPE_ON_INSTRUMENT_UI_CHANGED = 5;
    public static final String TAG = "SomeIpClusterAdapter";
    private static final int TIME_NUMBER = 500;
    private static final int UNGET_THEME = -1;
    private static int sRegCmds = 25;
    private ClusterCommManager mClusterCommManager;
    private Context mContext;
    private Handler mHandler;
    private MapFocusManager mMapFocusManager;
    private boolean mNaviStateFromQnx;
    private SomeIpPackageUtils mPackageUtils;
    private QnxManager mQnxManager;
    private int mRequestNaviAreaDisplay = -1;
    private String mAlbumPicture = "";
    private String mMediaCoverUri = "";
    private byte[] mMediaCover = null;
    private Bundle mMusicInfoBundle = null;
    private String mLyrics = "";
    private int mMusicStatus = -1;
    private long mMusicPlayTime = 0;
    private String mMusicSinger = "";
    private String mMusicName = "";
    private int mMusicRadioFocus = 0;
    private int mDisplayResult = -1;
    private int mNaviGpsSignal = -1;
    private IQnxCallBack.Stub mCallBackFromInstrument = new IQnxCallBack.Stub() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.3
        public void onMessageChanged(Bundle bundle) {
            LogUtil.logD(SomeIpClusterAdapter.TAG, "onMessageChanged bundle: " + bundle);
            int msgType = bundle.getInt(SomeIpClusterAdapter.KEY_MSG_TYPE);
            if (msgType == 3) {
                SomeIpClusterAdapter.this.processCallbackAboutNavi(bundle);
                return;
            }
            LogUtil.logD(SomeIpClusterAdapter.TAG, "onMessageChanged, illegal msgType, bundle:" + bundle);
        }
    };
    private OnMapFocusChangeListener mFocusListener = new OnMapFocusChangeListener() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.4
        @Override // com.ts.lib.cluster.adapter.utils.OnMapFocusChangeListener
        public void onMapFocusChanged(int previousMap, int focusMap) {
            SomeIpClusterAdapter.this.saveFocusMapType(focusMap);
            Bundle bundle = new Bundle();
            bundle.putInt("DataType", SomeIpClusterAdapter.DATA_TYPE_NAVI_REQUEST_MAP_FOCUS);
            bundle.putInt(SomeIpClusterAdapter.KEY_BUNDLE_MAP_TYPE, focusMap);
            bundle.putInt(SomeIpClusterAdapter.KEY_MSG_TYPE, 3);
            bundle.putInt("subtype", 14);
            SomeIpClusterAdapter.this.sendDataToQnxMessage(bundle);
            SomeIpClusterAdapter.this.sendDataToCallbacks(bundle);
        }
    };
    private ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.5
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            int mode = SomeIpClusterAdapter.this.readInsNaviDisplayMode();
            Log.d(SomeIpClusterAdapter.TAG, "navi display mode changed, mode:" + mode + "mNaviStateFromQnx : " + SomeIpClusterAdapter.this.mNaviStateFromQnx);
            Bundle bundle = new Bundle();
            bundle.putInt("DataType", SomeIpClusterAdapter.DATA_TYPE_NAVI_SEND_DISPLAY_MODE);
            bundle.putInt(SomeIpClusterAdapter.KEY_BUNDLE_NAVI_DISPLAY_MODE, mode);
            bundle.putInt(SomeIpClusterAdapter.KEY_MSG_TYPE, 3);
            bundle.putInt("subtype", 3);
            if (!SomeIpClusterAdapter.this.mNaviStateFromQnx) {
                SomeIpClusterAdapter.this.sendDataToQnxMessage(bundle);
            }
            SomeIpClusterAdapter.this.mNaviStateFromQnx = false;
            SomeIpClusterAdapter.this.sendDataToCallbacks(bundle);
        }
    };
    private IQnxCallBack.Stub callBack = new IQnxCallBack.Stub() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.8
        public void onMessageChanged(Bundle bundle) {
            LogUtil.logD(SomeIpClusterAdapter.TAG, "onMessageChanged bundle: " + bundle);
            SomeIpClusterAdapter.this.receiveClusterReq(bundle);
        }
    };

    public SomeIpClusterAdapter(Handler handler, Context context) {
        this.mHandler = handler;
        this.mContext = context;
        saveFocusMapType(0);
        MapFocusManager mapFocusManager = new MapFocusManager();
        this.mMapFocusManager = mapFocusManager;
        mapFocusManager.registerMapFocusChangeListener(this.mFocusListener);
        this.mClusterCommManager = ClusterCommManager.getInstance(this.mContext);
        this.mQnxManager = QnxManager.getInstance();
        this.mPackageUtils = SomeIpPackageUtils.getInstance();
        registerDisplayModeChangeObserver();
        initAdapterService();
        setAlbumFinishedListener();
    }

    public int getRegCmds() {
        return sRegCmds;
    }

    public boolean initAdapterService() {
        return checkQnxManagerConnected();
    }

    public boolean handleDataToCluster(Bundle bundle) {
        int type = bundle.getInt("DataType", 0);
        int audioSource = bundle.getInt("MusicSource");
        switch (type) {
            case 1:
                if (!isUserVersion()) {
                    LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                } else {
                    LogUtil.logI(TAG, "handleDataToCluster, music dataType:" + type + ", MEDIA_STATE:" + bundle.getInt("MediaState") + ", MUSIC_SOURCE:" + bundle.getInt("MusicSource") + ", MEDIA_COVER_URI:" + bundle.getString("MediaCoverUri"));
                }
                sendDataToQnxMessage(bundle);
                return true;
            case 2:
            case DATA_TYPE_NAVI_SEND_PRESENTATION_STATE /* 31 */:
            case DATA_TYPE_NAVI_TBT_INFO /* 32 */:
            case DATA_TYPE_NAVI_SEND_NAVI_STATE /* 35 */:
            case DATA_TYPE_NEW_NAVI_TBT_INFO /* 37 */:
                LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                sendDataToQnxMessage(bundle);
                return true;
            case 3:
                if (this.mMusicRadioFocus != audioSource) {
                    return false;
                }
                boolean result = sendMusicStateToCluster(bundle);
                return result;
            case 4:
                boolean result2 = judgeRadioOrMusic(bundle);
                return result2;
            case 5:
                if (this.mMusicRadioFocus != 6) {
                    return false;
                }
                makeMusicSingerAndMusicNameToNull();
                boolean result3 = sendRadioInfoToCluster(bundle);
                return result3;
            case 6:
                if (this.mMusicRadioFocus != 6) {
                    return false;
                }
                boolean result4 = sendRadioStateToCluster(bundle);
                return result4;
            case 7:
                boolean result5 = sendInternetRadioInfoToCluster(bundle);
                return result5;
            case 8:
                boolean result6 = sendInternetRadioPlayingTime(bundle);
                return result6;
            case SomeIpConstants.StateOrigin.ONLINE_VOICE /* 9 */:
                boolean result7 = sendInternetRadioState(bundle);
                return result7;
            case SomeIpConstants.StateOrigin.NEWS /* 10 */:
            case SomeIpConstants.StateOrigin.USB_2_VIDEO /* 12 */:
            case 14:
            case 19:
            case 21:
            case 22:
            case 29:
            case DATA_TYPE_INSTRUMENT_UI_STATE /* 36 */:
            default:
                LogUtil.logW(TAG, "unexpected type in switch " + type);
                return false;
            case SomeIpConstants.StateOrigin.USB_1_VIDEO /* 11 */:
                if (!isUserVersion()) {
                    LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                } else {
                    LogUtil.logI(TAG, "handleDataToCluster, call info dataType:" + type + ", CALL_STATE:" + bundle.getInt(SomeIpConstants.Key.CALL_STATE) + ", CALL_ACTIVIE_TIME:" + bundle.getInt("CallActiveTime"));
                }
                sendDataToQnxMessage(bundle);
                return true;
            case 13:
                boolean result8 = sendEbCallInfoToCluster(bundle);
                return result8;
            case SomeIpConstants.StateOrigin.SCREEN /* 15 */:
                boolean result9 = sendNaviSwitchToCluster(bundle);
                return result9;
            case 16:
                boolean result10 = sendNaviInfoToCluster(bundle);
                return result10;
            case 17:
                boolean result11 = sendNaviCarplayInfoToCluster(bundle);
                return result11;
            case 18:
                boolean result12 = sendCarplayCallInfoToCluster(bundle);
                return result12;
            case 20:
                boolean result13 = sendFullMapRespToCluster(bundle);
                return result13;
            case 23:
                boolean result14 = sendCallTimeToCluster(bundle);
                return result14;
            case 24:
                boolean result15 = sendCallTimeToCluster(bundle);
                return result15;
            case 25:
                boolean result16 = sendCallTimeToCluster(bundle);
                return result16;
            case 26:
                boolean result17 = sendMusicLyricsToCluster(bundle);
                return result17;
            case 27:
                boolean result18 = sendMusicAndRadioLoadStatusToCluster(bundle);
                return result18;
            case 28:
                boolean result19 = sendNaviGpsSignalToCluster(bundle);
                return result19;
            case DATA_TYPE_NAVI_SEND_DISPLAY_MODE /* 30 */:
                LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                int mode = bundle.getInt(KEY_BUNDLE_NAVI_DISPLAY_MODE);
                this.mNaviStateFromQnx = false;
                saveInsNaviDisplayMode(mode);
                return true;
            case DATA_TYPE_NAVI_REQUEST_MAP_FOCUS /* 33 */:
                LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                int mapType = bundle.getInt(KEY_BUNDLE_MAP_TYPE);
                this.mMapFocusManager.requestMapFocus(mapType);
                return true;
            case DATA_TYPE_NAVI_ABANDON_MAP_FOCUS /* 34 */:
                LogUtil.logI(TAG, "handleDataToCluster, bundle's value: " + bundle);
                int abandonMapType = bundle.getInt(KEY_BUNDLE_MAP_TYPE);
                this.mMapFocusManager.abandonMapFocus(abandonMapType);
                return true;
        }
    }

    private boolean isUserVersion() {
        return Build.IS_USER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSyncDisplayModeRequestToInstrument() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_BUNDLE_QUERY_NUMBER, 1);
        boolean res = this.mQnxManager.sendMessage(3, 13, (String) null, bundle);
        LogUtil.logD(TAG, "request sync display mode with instrument, bundle:" + bundle + ", res:" + res);
        if (!res) {
            LogUtil.logW(TAG, "sendSyncDisplayModeRequestToInstrument failed, retry!");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    SomeIpClusterAdapter.this.sendSyncDisplayModeRequestToInstrument();
                }
            }, 300L);
        }
    }

    private boolean checkQnxManagerConnected() {
        try {
            Field serviceField = this.mQnxManager.getClass().getDeclaredField("mService");
            serviceField.setAccessible(true);
            Object serviceObject = serviceField.get(this.mQnxManager);
            if (serviceObject != null) {
                LogUtil.logD(TAG, "qnxMessage connected!");
                registerCallbacksToQnxMessage();
                sendSyncDisplayModeRequestToInstrument();
                return true;
            }
            return false;
        } catch (IllegalAccessException ex) {
            Log.w(TAG, "checkQnxManagerConnected error.", ex);
            return false;
        } catch (NoSuchFieldException ex2) {
            Log.w(TAG, "checkQnxManagerConnected error.", ex2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerCallbacksToQnxMessage() {
        boolean res = this.mQnxManager.registerCallback(3, 0, this.mCallBackFromInstrument);
        Log.d(TAG, "mQnxManager registerCallback msgType:3, res:" + res);
        if (!res) {
            Log.w(TAG, "mQnxManager registerCallback failed!, retry!");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    SomeIpClusterAdapter.this.registerCallbacksToQnxMessage();
                }
            }, 300L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCallbackAboutNavi(Bundle bundle) {
        int subType = bundle.getInt("subtype");
        if (subType == 1) {
            LogUtil.logD(TAG, "receive mode changed from Instrument, bundle:" + bundle);
            this.mNaviStateFromQnx = true;
            int mode = bundle.getInt(KEY_BUNDLE_NAVI_DISPLAY_MODE);
            saveInsNaviDisplayMode(mode);
            return;
        }
        if (subType == 5) {
            LogUtil.logD(TAG, "receive ui changed from Instrument, bundle:" + bundle);
            int state = bundle.getInt(KEY_BUNDLE_INSTRUMENT_UI_STATE);
            Bundle bundleToCallbacks = new Bundle();
            bundleToCallbacks.putInt(KEY_BUNDLE_INSTRUMENT_UI_STATE, state);
            bundleToCallbacks.putInt("DataType", DATA_TYPE_INSTRUMENT_UI_STATE);
            sendDataToCallbacks(bundleToCallbacks);
            return;
        }
        LogUtil.logD(TAG, "receive unknown subType, not process this bundle:" + bundle);
    }

    private void saveInsNaviDisplayMode(int mode) {
        Settings.Global.putInt(this.mContext.getContentResolver(), SETTINGS_KEY_DISPLAY_MODE, mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readInsNaviDisplayMode() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), SETTINGS_KEY_DISPLAY_MODE, 0);
    }

    private void registerDisplayModeChangeObserver() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SETTINGS_KEY_DISPLAY_MODE), true, this.mObserver);
    }

    public void saveFocusMapType(int mayType) {
        Settings.Global.putInt(this.mContext.getContentResolver(), SETTINGS_KEY_FOCUS_MAP, mayType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDataToQnxMessage(final Bundle bundle) {
        int msgType = bundle.getInt(KEY_MSG_TYPE);
        int subType = bundle.getInt("subtype");
        Bundle bundleToBeLeave = bundle.deepCopy();
        bundleToBeLeave.remove(KEY_MSG_TYPE);
        bundleToBeLeave.remove("subtype");
        bundleToBeLeave.remove("DataType");
        boolean result = this.mQnxManager.sendMessage(msgType, subType, (String) null, bundleToBeLeave);
        if (!result) {
            Log.w(TAG, "sendMessage failed, Qnx interface error.");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.6
                @Override // java.lang.Runnable
                public void run() {
                    SomeIpClusterAdapter.this.sendDataToQnxMessage(bundle);
                }
            }, 300L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDataToCallbacks(Bundle bundle) {
        this.mClusterCommManager.sendCommand(64, bundle);
    }

    private boolean judgeRadioOrMusic(Bundle bundle) {
        int musicSource = bundle.getInt("MusicSource");
        if (this.mMusicRadioFocus != musicSource) {
            bundle.putString("MediaName", "");
            bundle.putString("MediaSinger", "");
            bundle.putString("MediaCoverUri", "");
            bundle.putString(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, "true");
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
            bundle.putLong("MediaTotalTime", 0L);
            bundle.putLong("MediaCurTime", 0L);
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 6, bundle);
            bundle.putString("MediaLyrics", "");
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 7, bundle);
            this.mMediaCover = null;
            this.mMediaCoverUri = "";
        }
        this.mMusicRadioFocus = musicSource;
        LogUtil.logD(TAG, "mMusicRadioFocus：" + this.mMusicRadioFocus);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDataToCluster(int msgType, int subType, Bundle bundle) {
        if (msgType == 4097) {
            sendMusicData(msgType, subType, bundle);
        } else if (msgType == 4098) {
            sendRadioData(msgType, subType, bundle);
        } else if (msgType == 4100) {
            sendNaviData(msgType, subType, bundle);
        }
    }

    private void sendMusicData(int msgType, int subType, Bundle bundle) {
        String musicInfo = null;
        if (subType == 0) {
            musicInfo = this.mPackageUtils.packageMusicInfo(bundle);
        } else if (subType == 1) {
            musicInfo = this.mPackageUtils.packageMusicState(bundle);
        } else if (subType == 6) {
            musicInfo = this.mPackageUtils.packageMusicTime(bundle);
        } else if (subType == 7) {
            musicInfo = this.mPackageUtils.packageMusicLyrics(bundle);
        } else if (subType == 8) {
            musicInfo = this.mPackageUtils.packageMusicLoadState(bundle);
        }
        this.mQnxManager.sendMessage(msgType, subType, musicInfo, (Bundle) null);
    }

    private void sendRadioData(int msgType, int subType, Bundle bundle) {
        String radioInfo = null;
        if (subType == 0) {
            radioInfo = this.mPackageUtils.packageRadioInfo(bundle);
        } else if (subType == 1) {
            radioInfo = this.mPackageUtils.packageRadioState(bundle);
        } else if (subType == 4) {
            radioInfo = this.mPackageUtils.packageRadioLoadState(bundle);
        } else if (subType == 5) {
            radioInfo = this.mPackageUtils.packageRadioRealPlayState(bundle);
        }
        this.mQnxManager.sendMessage(msgType, subType, radioInfo, (Bundle) null);
    }

    private void sendNaviData(int msgType, int subType, Bundle bundle) {
        String naviInfo = null;
        if (subType == 0) {
            naviInfo = this.mPackageUtils.packageNaviLaneInfo(bundle);
        } else if (subType == 1) {
            naviInfo = this.mPackageUtils.packageNaviRoadInfo(bundle);
        } else if (subType == 2) {
            naviInfo = this.mPackageUtils.packageNaviIsShowOnCluster(bundle);
        } else if (subType == 3) {
            naviInfo = this.mPackageUtils.packageNaviTotalInfo(bundle);
        } else if (subType == 4) {
            naviInfo = this.mPackageUtils.packageNaviElectronicEyeInfo(bundle);
        } else if (subType == 5) {
            naviInfo = this.mPackageUtils.packageDisplayAreaAndResult(bundle);
        } else if (subType == 7) {
            naviInfo = this.mPackageUtils.packageNaviGpsSignalInfo(bundle);
        }
        this.mQnxManager.sendMessage(msgType, subType, naviInfo, (Bundle) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean sendMusicInfoToCluster(android.os.Bundle r5) {
        /*
            r4 = this;
            java.lang.String r0 = "MusicSource"
            int r0 = r5.getInt(r0)
            r4.mMusicRadioFocus = r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "musicSource："
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "SomeIpClusterAdapter"
            com.ts.lib.cluster.utils.LogUtil.logD(r2, r1)
            java.lang.String r1 = "MediaName"
            java.lang.String r3 = ""
            java.lang.String r1 = r5.getString(r1, r3)
            if (r1 == 0) goto L51
            int r3 = r1.length()
            if (r3 != 0) goto L2f
            goto L51
        L2f:
            r4.mMusicInfoBundle = r5
            r2 = 1
            if (r0 == r2) goto L4c
            r3 = 2
            if (r0 == r3) goto L48
            r3 = 3
            if (r0 == r3) goto L44
            r3 = 4
            if (r0 == r3) goto L48
            r3 = 5
            if (r0 == r3) goto L48
            r3 = 7
            if (r0 == r3) goto L44
            goto L50
        L44:
            r4.sendOnlineMediaInfo(r5)
            goto L50
        L48:
            r4.sendUsbMediaInfo(r5)
            goto L50
        L4c:
            r4.sendBtMediaInfo(r5)
        L50:
            return r2
        L51:
            java.lang.String r3 = "media name is invalid!"
            com.ts.lib.cluster.utils.LogUtil.logD(r2, r3)
            r4.sendNoneMediaInfo(r5)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.sendMusicInfoToCluster(android.os.Bundle):boolean");
    }

    private void sendNoneMediaInfo(Bundle bundle) {
        bundle.putInt("MusicSource", 0);
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
    }

    private void sendBtMediaInfo(Bundle bundle) {
        String mediaCover;
        String mediaCover2 = bundle.getString("MediaCoverUri");
        if (mediaCover2 == null) {
            LogUtil.logD(TAG, "BlueTeeth music photo is null");
            mediaCover = "";
        } else {
            mediaCover = bitToBase64(mediaCover2);
            LogUtil.logD(TAG, "BlueTeeth music：" + mediaCover);
        }
        bundle.putString("MediaCoverUri", mediaCover);
        bundle.putString(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, "true");
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
        String mediaName = bundle.getString("MediaName", "");
        String mediaSinger = bundle.getString("MediaSinger", "");
        this.mMusicName = mediaName;
        this.mMusicSinger = mediaSinger;
    }

    private void sendUsbMediaInfo(Bundle bundle) {
        String mediaName = bundle.getString("MediaName", "");
        String mediaSinger = bundle.getString("MediaSinger", "");
        byte[] mediaCover = bundle.getByteArray("MediaCover");
        if (mediaCover != null) {
            if (!Arrays.equals(mediaCover, this.mMediaCover)) {
                this.mMediaCover = mediaCover;
                LogUtil.logD(TAG, "store byte[] usb music mediaCover.");
                MediaCoverUtil.getInstance().storeByteAlbum(mediaCover);
            } else {
                LogUtil.logD(TAG, "same usb music mediaCover.");
                if (mediaName.equals(this.mMusicName) && mediaSinger.equals(this.mMusicSinger)) {
                    LogUtil.logD(TAG, "Music message is same then before,don't send musicInfoBundle");
                } else {
                    this.mMusicInfoBundle.putString("MediaCoverUri", this.mAlbumPicture);
                    sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, this.mMusicInfoBundle);
                }
            }
        } else {
            LogUtil.logD(TAG, "usb music has no cover.");
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
        }
        this.mMusicName = mediaName;
        this.mMusicSinger = mediaSinger;
    }

    private void sendOnlineMediaInfo(Bundle bundle) {
        String mediaName = bundle.getString("MediaName", "");
        String mediaSinger = bundle.getString("MediaSinger", "");
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
        if (!mediaName.equals(this.mMusicName) || !mediaSinger.equals(this.mMusicSinger)) {
            bundle.putString("MediaLyrics", "");
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 7, bundle);
        }
        String mediaCoverUri = bundle.getString("MediaCoverUri");
        if (mediaCoverUri == null) {
            LogUtil.logD(TAG, "online music has no cover.");
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, bundle);
        } else if (this.mMediaCoverUri.equals(mediaCoverUri)) {
            LogUtil.logD(TAG, "same online mediaCoverUri.");
            this.mMusicInfoBundle.putString("MediaCoverUri", this.mAlbumPicture);
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, this.mMusicInfoBundle);
        } else {
            this.mMediaCoverUri = mediaCoverUri;
            LogUtil.logD(TAG, "download online mediaCover.");
            MediaCoverUtil.getInstance().executeLoadAlbum(mediaCoverUri);
        }
        this.mMusicName = mediaName;
        this.mMusicSinger = mediaSinger;
    }

    private void setAlbumFinishedListener() {
        MediaCoverUtil.getInstance().setOnDataFinishedListener(new OnDataFinishedListener() { // from class: com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter.7
            @Override // com.ts.lib.cluster.adapter.OnDataFinishedListener
            public void onDataReceived(String albumLocalPath) {
                if (albumLocalPath == null) {
                    LogUtil.logW(SomeIpClusterAdapter.TAG, "albumLocalPath is null");
                    return;
                }
                LogUtil.logD(SomeIpClusterAdapter.TAG, "albumLocalPath : " + albumLocalPath);
                SomeIpClusterAdapter someIpClusterAdapter = SomeIpClusterAdapter.this;
                someIpClusterAdapter.mAlbumPicture = someIpClusterAdapter.bitToBase64(albumLocalPath);
                SomeIpClusterAdapter.this.mMusicInfoBundle.putString("MediaCoverUri", SomeIpClusterAdapter.this.mAlbumPicture);
                SomeIpClusterAdapter.this.mMusicInfoBundle.putString(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, "true");
                SomeIpClusterAdapter someIpClusterAdapter2 = SomeIpClusterAdapter.this;
                someIpClusterAdapter2.sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, someIpClusterAdapter2.mMusicInfoBundle);
            }

            @Override // com.ts.lib.cluster.adapter.OnDataFinishedListener
            public void onSaveFailReceived(int haveCover) {
                if (haveCover == 0) {
                    SomeIpClusterAdapter.this.mAlbumPicture = null;
                    SomeIpClusterAdapter.this.mMusicInfoBundle.putString("MediaCoverUri", "");
                    SomeIpClusterAdapter.this.mMusicInfoBundle.putString(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, "true");
                    SomeIpClusterAdapter someIpClusterAdapter = SomeIpClusterAdapter.this;
                    someIpClusterAdapter.sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 0, someIpClusterAdapter.mMusicInfoBundle);
                    return;
                }
                LogUtil.logD(SomeIpClusterAdapter.TAG, "album picture saved successfully.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bitToBase64(String picPath) {
        InputStream in = null;
        String base64Str = "";
        if (picPath == null) {
            return "";
        }
        try {
            try {
                try {
                    in = new FileInputStream(picPath);
                    byte[] data = new byte[in.available()];
                    if (in.read(data) != -1) {
                        Base64.Encoder encoder = Base64.getEncoder();
                        base64Str = encoder.encodeToString(data);
                    }
                    in.close();
                } catch (Throwable th) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
                if (in != null) {
                    in.close();
                }
            }
        } catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return base64Str;
    }

    private void makeMusicSingerAndMusicNameToNull() {
        this.mMusicName = "";
        this.mMusicSinger = "";
    }

    private boolean sendMusicPlayingTimeToCluster(Bundle bundle) {
        long playingTime = bundle.getLong("MediaCurTime", 0L);
        LogUtil.logD(TAG, "music play time bundle：" + bundle + " music play CurTime bundle：" + playingTime);
        long interval = Math.abs(playingTime - this.mMusicPlayTime);
        if (interval >= 500) {
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 6, bundle);
            this.mMusicPlayTime = playingTime;
            return true;
        }
        LogUtil.logD(TAG, "get same play time too  much, Don't send to cluster");
        return true;
    }

    private boolean sendMusicStateToCluster(Bundle bundle) {
        int musicStatus = bundle.getInt("MediaState", -1);
        if (musicStatus != this.mMusicStatus) {
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 1, bundle);
            this.mMusicStatus = musicStatus;
        } else {
            LogUtil.logD(TAG, "Same play status, Don't send to cluster");
        }
        return true;
    }

    private boolean sendMusicLyricsToCluster(Bundle bundle) {
        String musicLyrics = bundle.getString("MediaLyrics", "");
        LogUtil.logD(TAG, "lyrics：" + musicLyrics);
        LogUtil.logD(TAG, "mLyrics：" + this.mLyrics);
        if (!musicLyrics.equals(this.mLyrics)) {
            sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 7, bundle);
            this.mLyrics = musicLyrics;
            return true;
        }
        LogUtil.logD(TAG, "Same lyrics, Don't send to cluster");
        return true;
    }

    private boolean sendMusicAndRadioLoadStatusToCluster(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.RADIO, 4, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 8, bundle);
        return true;
    }

    private boolean sendRadioInfoToCluster(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.RADIO, 0, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.RADIO, 1, bundle);
        return true;
    }

    private boolean sendRadioStateToCluster(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.RADIO, 1, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.RADIO, 5, bundle);
        return true;
    }

    private boolean sendInternetRadioInfoToCluster(Bundle bundle) {
        this.mMusicInfoBundle = bundle;
        bundle.putInt("MusicSource", 3);
        sendOnlineMediaInfo(this.mMusicInfoBundle);
        return true;
    }

    private boolean sendInternetRadioPlayingTime(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 6, bundle);
        return true;
    }

    private boolean sendInternetRadioState(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.MUSIC, 1, bundle);
        return true;
    }

    private boolean sendBtCallInfoToCluster(Bundle bundle) {
        String callInfo = this.mPackageUtils.packageBtCallInfo(bundle);
        this.mQnxManager.sendMessage(SomeIpConstants.MsgType.PHONE, 2, callInfo, (Bundle) null);
        sendDataToCluster(SomeIpConstants.MsgType.PHONE, 3, bundle);
        sendCallTimeToCluster(bundle);
        return true;
    }

    private boolean sendEbCallInfoToCluster(Bundle bundle) {
        String callInfo = this.mPackageUtils.packageEbCallInfo(bundle);
        this.mQnxManager.sendMessage(SomeIpConstants.MsgType.PHONE, 2, callInfo, (Bundle) null);
        sendDataToCluster(SomeIpConstants.MsgType.PHONE, 3, bundle);
        sendCallTimeToCluster(bundle);
        return true;
    }

    private boolean sendCarplayCallInfoToCluster(Bundle bundle) {
        String callInfo = this.mPackageUtils.packageCpCallInfo(bundle);
        this.mQnxManager.sendMessage(SomeIpConstants.MsgType.PHONE, 2, callInfo, (Bundle) null);
        sendDataToCluster(SomeIpConstants.MsgType.PHONE, 3, bundle);
        sendCallTimeToCluster(bundle);
        return true;
    }

    private boolean sendCallTimeToCluster(Bundle bundle) {
        String phoneInfo = this.mPackageUtils.packageCallTime(bundle);
        this.mQnxManager.sendMessage(SomeIpConstants.MsgType.PHONE, 3, phoneInfo, (Bundle) null);
        return true;
    }

    private boolean sendNaviSwitchToCluster(Bundle bundle) {
        int naviSource = bundle.getInt("NaviSource", 0);
        int naviTbtSwitch = bundle.getInt("NaviTbtSwitch", 0);
        LogUtil.logD(TAG, "sendNaviSwitchToCluster  naviSource " + naviSource + " naviTbtSwitch " + naviTbtSwitch);
        if (naviTbtSwitch == 1) {
            this.mDisplayResult = 2;
        } else {
            this.mDisplayResult = 0;
        }
        if (naviTbtSwitch != 1) {
            Bundle naviState = new Bundle();
            naviState.putInt("NaviState", 0);
            this.mDisplayResult = 0;
            LogUtil.logD(TAG, "naviState's value：" + naviState);
            sendDataToCluster(SomeIpConstants.MsgType.NAVI, 4, naviState);
            bundle.putInt("NaviState", 2);
            sendDataToCluster(SomeIpConstants.MsgType.NAVI, 2, bundle);
        }
        if (this.mRequestNaviAreaDisplay == -1) {
            Bundle requestTheme = new Bundle();
            requestTheme.putInt("NaviState", 0);
            LogUtil.logD(TAG, "when start system and don't get theme value ");
            sendDataToCluster(SomeIpConstants.MsgType.NAVI, 2, requestTheme);
        }
        return true;
    }

    private boolean sendNaviInfoToCluster(Bundle bundle) {
        LogUtil.logD(TAG, "this mDisplayResult: " + this.mDisplayResult);
        bundle.putInt("NaviState", this.mDisplayResult);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 0, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 1, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 2, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 3, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 4, bundle);
        return true;
    }

    private boolean sendNaviGpsSignalToCluster(Bundle bundle) {
        int naviGpsSignal = bundle.getInt("NaviGpsSignal");
        if (this.mNaviGpsSignal != naviGpsSignal) {
            this.mNaviGpsSignal = naviGpsSignal;
            sendDataToCluster(SomeIpConstants.MsgType.NAVI, 7, bundle);
            return true;
        }
        return true;
    }

    private boolean sendNaviCarplayInfoToCluster(Bundle bundle) {
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 1, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 2, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 3, bundle);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 4, bundle);
        return true;
    }

    private boolean sendFullMapRespToCluster(Bundle bundle) {
        bundle.putInt(SomeIpConstants.Key.NAVI_DISPLAY_AREA, this.mRequestNaviAreaDisplay);
        sendDataToCluster(SomeIpConstants.MsgType.NAVI, 5, bundle);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveClusterReq(Bundle bundle) {
        int msgType = bundle.getInt(SomeIpConstants.Key.MSG_TYPE);
        int subType = bundle.getInt("subtype");
        String content = bundle.getString(SomeIpConstants.Key.CONTENT);
        if (msgType == 8196 && subType == 1) {
            handleNaviReq(content);
        }
    }

    private void handleNaviReq(String content) {
        try {
            JSONObject json = new JSONObject(content);
            this.mRequestNaviAreaDisplay = json.getInt(SomeIpConstants.Key.REQUEST_NAVI_AREA_DISPLAY);
            LogUtil.logD(TAG, "mRequestNaviAreaDisplay." + this.mRequestNaviAreaDisplay);
            Bundle displayReq = new Bundle();
            displayReq.putInt("DataType", 20);
            int i = this.mRequestNaviAreaDisplay;
            if (i == 0) {
                displayReq.putInt("ClusterThemeType", 2);
                this.mClusterCommManager.sendCommand(DATA_TYPE_NAVI_TBT_INFO, displayReq);
            } else if (i == 3) {
                displayReq.putInt("ClusterThemeType", 1);
                this.mClusterCommManager.sendCommand(DATA_TYPE_NAVI_TBT_INFO, displayReq);
            } else if (i != 4) {
                LogUtil.logD(TAG, "Invalid map projection request.");
            } else {
                displayReq.putInt("ClusterThemeType", 3);
                this.mClusterCommManager.sendCommand(DATA_TYPE_NAVI_TBT_INFO, displayReq);
            }
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
    }
}
