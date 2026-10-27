package com.ts.lib.cluster.adapter;

import android.car.Car;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.ts.appservice.cluster.ClusterBroadcastReceiver;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.adapter.ClusterConstants;
import com.ts.lib.cluster.adapter.someip.SomeIpConstants;
import com.ts.lib.cluster.utils.LogUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ClusterAdapter {
    private static final String B_CALL_NAME = "B-CALL";
    private static final String DEFAULT_DISTANCE = "-1";
    private static final int DEFAULT_INT = -1;
    private static final String DEFAULT_STRING = "default_string";
    private static final int DELAY_TIME = 1000;
    private static final int EXIST_COVER = 1;
    private static final String E_CALL_NAME = "E-CALL";
    private static final int HANG_UP = 1;
    private static final String HAVE_MEDIA_COVER = "haveMediaCover";
    private static final int ICM_MODE_FULL = 1;
    private static final int ICM_MODE_ORG = 0;
    private static final int ICM_MODE_SPORT = 2;
    private static final int INEXIST_COVER = 0;
    private static final int INVALID_MEDIA_COVER = -1;
    private static final int IVI_FAIL = 1;
    private static final int IVI_MODE_FULL = 3;
    private static final int IVI_MODE_ORG = 2;
    private static final int IVI_MODE_SPORT = 4;
    private static final int IVI_SUCCESS = 0;
    private static final int MEDIA_STATE_DEFAULT = -2;
    private static final int NAVI_ON = 1;
    private static final int ONE_HUNDRED_METERS = 100;
    private static final int ONE_THOUSAND_METERS = 1000;
    private static final String ONLINE_URL_PREFIX = "http";
    private static final int PHONE_STATE_DEFAULT = -2;
    private static final String TAG = "ClusterAdapter";
    private static int sRegCmds = 127;
    private Car mCar;
    private Context mContext;
    private Handler mDelayHandler;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int mBtCallState = -2;
    private int mEbCallState = -2;
    private int mCarplayCallState = -2;
    private int mMusicSource = 0;
    private String mMsgBitmap = "";
    private String mMediaCoverUri = "";
    private byte[] mMediaCover = null;
    private byte[] mFullMapReq = null;
    private Bundle mBundle = new Bundle();
    private ServiceConnection mServicesConn = new ServiceConnection() { // from class: com.ts.lib.cluster.adapter.ClusterAdapter.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.logI(ClusterAdapter.TAG, "bind car service onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.logI(ClusterAdapter.TAG, "onServiceDisconnected");
        }
    };

    public ClusterAdapter(Handler handler, Context context) {
        this.mHandler = handler;
        startThread();
        this.mContext = context;
        Car carCreateCar = Car.createCar(context, this.mServicesConn);
        this.mCar = carCreateCar;
        if (carCreateCar == null) {
            LogUtil.logD(TAG, "Car.createCar's result is null!");
        } else {
            carCreateCar.connect();
        }
        setAlbumFinishedListener();
        LogUtil.logD(TAG, "setAlbumFinishedListener success");
    }

    private void startThread() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mDelayHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.lib.cluster.adapter.ClusterAdapter.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                ClusterAdapter.this.sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NO_PHONE));
                ClusterPackageUtils.getInstance().clearPhoneInfor();
            }
        };
    }

    public void setAlbumFinishedListener() {
        MediaCoverUtil.getInstance().setOnDataFinishedListener(new OnDataFinishedListener() { // from class: com.ts.lib.cluster.adapter.ClusterAdapter.2
            @Override // com.ts.lib.cluster.adapter.OnDataFinishedListener
            public void onDataReceived(String albumLocalPath) {
                if (albumLocalPath == null) {
                    LogUtil.logW(ClusterAdapter.TAG, "albumLocalPath is null");
                    return;
                }
                LogUtil.logD(ClusterAdapter.TAG, "albumLocalPath : " + albumLocalPath);
                ClusterAdapter.this.sendBitmapToCluster(ClusterConstants.BitMapType.Meida, albumLocalPath);
            }

            @Override // com.ts.lib.cluster.adapter.OnDataFinishedListener
            public void onSaveFailReceived(int haveCover) {
                if (haveCover == 0) {
                    ClusterAdapter.this.setNotHaveMediaCover();
                } else if (haveCover == 1) {
                    ClusterAdapter.this.setHaveMediaCover();
                }
            }
        });
    }

    public int getRegCmds() {
        return sRegCmds;
    }

    public boolean initAdapterService() {
        return true;
    }

    public boolean handleDataToCluster(Bundle bundle) {
        int type = bundle.getInt("DataType", 0);
        LogUtil.logD(TAG, "handleDataToCluster received type: " + type);
        switch (type) {
            case 1:
                boolean result = sendMusicInfoToCluster(bundle);
                return result;
            case 2:
                boolean result2 = sendMusicPlayingTimeToCluster(bundle);
                return result2;
            case 3:
                boolean result3 = sendMusicStateToCluster(bundle);
                return result3;
            case 4:
            case SomeIpConstants.StateOrigin.USB_2_VIDEO /* 12 */:
            case SomeIpConstants.StateOrigin.CARLIFE /* 14 */:
            case 19:
            default:
                LogUtil.logW(TAG, "unexpected type in switch " + type);
                return false;
            case 5:
                boolean result4 = sendRadioInfoToCluster(bundle);
                return result4;
            case 6:
                boolean result5 = sendRadioStateToCluster(bundle);
                return result5;
            case 7:
                boolean result6 = sendInternetRadioInfoToCluster(bundle);
                return result6;
            case 8:
                boolean result7 = sendInternetRadioPlayingTime(bundle);
                return result7;
            case SomeIpConstants.StateOrigin.ONLINE_VOICE /* 9 */:
                boolean result8 = sendInternetRadioState(bundle);
                return result8;
            case SomeIpConstants.StateOrigin.NEWS /* 10 */:
                boolean result9 = sendBtConnectState(bundle);
                return result9;
            case SomeIpConstants.StateOrigin.USB_1_VIDEO /* 11 */:
                boolean result10 = sendBtCallInfoToCluster(bundle);
                return result10;
            case SomeIpConstants.StateOrigin.CARPLAY /* 13 */:
                boolean result11 = sendEbCallInfoToCluster(bundle);
                return result11;
            case SomeIpConstants.StateOrigin.SCREEN /* 15 */:
                boolean result12 = sendNaviSwitchToCluster(bundle);
                return result12;
            case 16:
                boolean result13 = sendNaviInfoToCluster(bundle);
                return result13;
            case 17:
                boolean result14 = sendNaviCarplayInfoToCluster(bundle);
                return result14;
            case 18:
                boolean result15 = sendCarplayCallInfoToCluster(bundle);
                return result15;
            case 20:
                boolean result16 = sendFullMapRespToCluster(bundle);
                return result16;
            case 21:
                boolean result17 = reqClusterInfo(bundle);
                return result17;
            case 22:
                boolean result18 = reqClusterOpenAreas(bundle);
                return result18;
            case 23:
                boolean result19 = sendBtCallInfoToCluster(bundle);
                return result19;
            case 24:
                boolean result20 = sendEbCallInfoToCluster(bundle);
                return result20;
            case 25:
                boolean result21 = sendCarplayCallInfoToCluster(bundle);
                return result21;
        }
    }

    boolean sendMusicInfoToCluster(Bundle bundle) {
        int musicSource = bundle.getInt("MusicSource", -1);
        String mediaName = bundle.getString("MediaName");
        String mediaSinger = bundle.getString("MediaSinger");
        String mediaCoverUri = bundle.getString("MediaCoverUri");
        byte[] mediaCover = bundle.getByteArray("MediaCover");
        LogUtil.logD(TAG, "sendMusicInfoToCluster   musicSource " + musicSource + " mediaName " + mediaName + " mediaSinger " + mediaSinger + " mediaCoverUri " + mediaCoverUri);
        if (musicSource == 0) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NO_MEDIA));
            ClusterPackageUtils.getInstance().clearMusicInfor();
            return true;
        }
        if (musicSource != this.mMusicSource) {
            this.mMusicSource = musicSource;
            ClusterPackageUtils.getInstance().clearMusicInfor();
            this.mMediaCoverUri = "";
            this.mMediaCover = null;
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
            setNotHaveMediaCover();
        } else {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
        }
        if (mediaName != null) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaName(mediaName));
        }
        if (mediaSinger != null) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageArtistName(mediaSinger));
        }
        if (musicSource == 1) {
            sendBtMediaCover(mediaCoverUri);
        } else if (musicSource == 2) {
            sendUsbMediaCover(mediaCover);
        } else if (musicSource == 3) {
            sendOnlineMediaCover(mediaCoverUri);
        }
        return true;
    }

    private void sendBtMediaCover(String mediaCoverUri) {
        if (mediaCoverUri != null) {
            LogUtil.logD(TAG, "send btmusic cover path to cluster.");
            sendBitmapToCluster(ClusterConstants.BitMapType.Meida, mediaCoverUri);
            setHaveMediaCover();
        } else {
            LogUtil.logD(TAG, "btmusic has no cover.");
            setNotHaveMediaCover();
        }
    }

    private void sendUsbMediaCover(byte[] mediaCover) {
        if (mediaCover == null) {
            LogUtil.logD(TAG, "usb music has no cover.");
            setNotHaveMediaCover();
        } else {
            if (Arrays.equals(mediaCover, this.mMediaCover)) {
                LogUtil.logD(TAG, "same usb music mediaCover.");
                return;
            }
            this.mMediaCover = mediaCover;
            LogUtil.logD(TAG, "store byte[] usb music mediaCover.");
            MediaCoverUtil.getInstance().storeByteAlbum(mediaCover);
        }
    }

    private void sendOnlineMediaCover(String mediaCoverUri) {
        if (mediaCoverUri == null) {
            LogUtil.logD(TAG, "online music has no cover.");
            setNotHaveMediaCover();
        } else {
            if (this.mMediaCoverUri.equals(mediaCoverUri)) {
                LogUtil.logD(TAG, "same online mediaCoverUri.");
                return;
            }
            this.mMediaCoverUri = mediaCoverUri;
            LogUtil.logD(TAG, "download online mediaCover.");
            MediaCoverUtil.getInstance().executeLoadAlbum(mediaCoverUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHaveMediaCover() {
        this.mBundle.putInt(HAVE_MEDIA_COVER, 1);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(this.mBundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNotHaveMediaCover() {
        this.mBundle.putInt(HAVE_MEDIA_COVER, 0);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(this.mBundle));
    }

    private boolean sendMusicPlayingTimeToCluster(Bundle bundle) {
        int musicSource = bundle.getInt("MusicSource");
        long mediaTotalTime = bundle.getLong("MediaTotalTime");
        long mediaCurTime = bundle.getLong("MediaCurTime");
        LogUtil.logD(TAG, "sendMusicPlayingTimeToCluster  musicSource " + musicSource + " mediaTotalTIme " + mediaTotalTime + " mediaCurTime " + mediaCurTime);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(bundle));
        return true;
    }

    private boolean sendMusicStateToCluster(Bundle bundle) {
        int musicSource = bundle.getInt("MusicSource");
        int mediaState = bundle.getInt("MediaState");
        LogUtil.logD(TAG, "sendMusicStateToCluster  musicSource " + musicSource + " mediaState " + mediaState);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(bundle));
        return true;
    }

    protected boolean sendBtCallInfoToCluster(Bundle bundle) {
        int callIndex = bundle.getInt("CallIndex", -1);
        String callName = bundle.getString("CallName");
        String callNum = bundle.getString("CallNum");
        String callPhotoUrl = bundle.getString("CallPhotoUrl");
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -2);
        long callActiveTime = bundle.getLong("CallActiveTime");
        LogUtil.logD(TAG, "sendBtCallInfoToCluster  callIndex " + callIndex + " callName " + callName + " callNum " + callNum + " callPhotoUrl " + callPhotoUrl + " CallState " + callState + " callActiveTime " + callActiveTime);
        if (callState != -2) {
            btCallStateTransform(callState);
        }
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.CALL_CAME));
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneInfo(0, callPhotoUrl, this.mBtCallState, callActiveTime));
        if (callName != null && !"".equals(callName)) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageCallName(callName));
            if (callNum != null && !"".equals(callNum)) {
                sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneNum(callNum));
            }
            LogUtil.logD(TAG, "send callName to cluster" + callName + "  callNum: " + callNum);
        } else if (callNum != null && !"".equals(callNum)) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneNum(callNum));
            LogUtil.logW(TAG, "send callNum to cluster " + callNum);
        } else {
            LogUtil.logW(TAG, "not send callName or callNum ");
        }
        if (this.mBtCallState == 4) {
            this.mDelayHandler.sendEmptyMessageDelayed(1, 1000L);
        }
        return true;
    }

    private boolean sendEbCallInfoToCluster(Bundle bundle) {
        int callType = bundle.getInt("EbcallType", -1);
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -2);
        long callActiveTime = bundle.getLong("CallActiveTime");
        LogUtil.logD(TAG, "sendEbCallInfoToCluster  callType " + callType + " CallState " + callState + " callActiveTime " + callActiveTime);
        if (callType != 0) {
            if (callType == 1) {
                sendTextDataToCluster(ClusterPackageUtils.getInstance().packageCallName(B_CALL_NAME));
            } else {
                LogUtil.logW(TAG, "unexpected callType in switch: " + callType);
            }
        } else {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageCallName(E_CALL_NAME));
        }
        if (callState != -2) {
            ebCallStateTransform(callState);
        }
        if (this.mEbCallState == 0) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NO_PHONE));
            ClusterPackageUtils.getInstance().clearPhoneInfor();
        } else {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.CALL_CAME));
        }
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneInfo(0, null, this.mEbCallState, callActiveTime));
        return true;
    }

    private boolean sendCarplayCallInfoToCluster(Bundle bundle) {
        String callName = bundle.getString("CallName");
        String callNum = bundle.getString("CallNum");
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -2);
        long callActiveTime = bundle.getLong("CallActiveTime");
        LogUtil.logD(TAG, "sendCarplayCallInfoToCluster  callName " + callName + " callNum " + callNum + " CallState " + callState + " callActiveTime " + callActiveTime);
        if (callState != -2) {
            carplayCallStateTransform(callState);
        }
        if (this.mEbCallState != 3 || this.mBtCallState != 3) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.CALL_CAME));
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneInfo(1, null, this.mCarplayCallState, callActiveTime));
            if (callName != null && !"".equals(callName)) {
                sendTextDataToCluster(ClusterPackageUtils.getInstance().packageCallName(callName));
                LogUtil.logD(TAG, "send callName to cluster" + callName);
            } else {
                LogUtil.logD(TAG, "callname is invalid");
            }
            if (callNum != null && !"".equals(callNum)) {
                sendTextDataToCluster(ClusterPackageUtils.getInstance().packagePhoneNum(callNum));
                LogUtil.logW(TAG, "send callNum to cluster " + callNum);
            } else {
                LogUtil.logW(TAG, "callNum is invalid");
            }
            if (this.mCarplayCallState == 4) {
                this.mDelayHandler.sendEmptyMessageDelayed(1, 1000L);
            }
        } else {
            LogUtil.logD(TAG, "sendCarplayCallInfoToCluster failed: EBCall is on the line");
        }
        return true;
    }

    protected boolean sendNaviSwitchToCluster(Bundle bundle) {
        int naviSource = bundle.getInt("NaviSource", -1);
        int naviTbtSwitch = bundle.getInt("NaviTbtSwitch", -1);
        LogUtil.logD(TAG, "sendNaviSwitchToCluster  naviSource " + naviSource + " naviTbtSwitch " + naviTbtSwitch);
        if (naviTbtSwitch != 1) {
            LogUtil.logD(TAG, "sendNaviSwitchToCluster synchronizeStateToCluster NO_NAVI_INFO");
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NO_NAVI_INFO));
            ClusterPackageUtils.getInstance().clearNaviInfor();
        }
        return true;
    }

    protected boolean sendNaviInfoToCluster(Bundle bundle) {
        LogUtil.logD(TAG, "sendNaviInfoToCluster bundle " + bundle);
        int naviSource = bundle.getInt("NaviSource", -1);
        int naviIconTbt = bundle.getInt("NaviIconTbt", -1);
        int naviNextRoadDistance = bundle.getInt("NaviNextRoadDistance", -1);
        int naviDistanceToDest = bundle.getInt("NaviDistanceToDest", -1);
        int naviToDestTime = bundle.getInt("NaviToDestTime", -1);
        int naviIconLaneSwitch = bundle.getInt("NaviIconLaneSwitch", -1);
        bundle.getIntArray("NaviIconLaneInfo");
        int naviIconElectronicType = bundle.getInt("NaviIconElectronicType", -1);
        int naviIconRoadSafetyType = bundle.getInt("NaviIconRoadSafetyType", -1);
        int naviSpeedlimit = bundle.getInt("NaviSpeedLimit", -1);
        LogUtil.logD(TAG, "sendNaviInfoToCluster:  naviSource: " + naviSource + " naviIconTbt: " + naviIconTbt + " naviNextRoadDistance: " + naviNextRoadDistance + " naviDistanceToDest: " + naviDistanceToDest + " naviToDestTime: " + naviToDestTime + " naviIconLaneSwitch: " + naviIconLaneSwitch + " naviIconElectronicType: " + naviIconElectronicType + " naviIconRoadSafetyType: " + naviIconRoadSafetyType + " naviSpeedlimit: " + naviSpeedlimit);
        LogUtil.logD(TAG, "sendNaviInfoToCluster synchronizeStateToCluster ");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NAVI_INFO_INPUT));
        LogUtil.logD(TAG, "sendNaviInfoToCluster packageNaviAlarmInfo ");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNaviAlarmInfo(naviIconRoadSafetyType, naviSpeedlimit, naviIconElectronicType));
        LogUtil.logD(TAG, "sendNaviInfoToCluster packageNaviInfo ");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNaviInfo(bundle));
        String naviNextRoadName = bundle.getString("NaviNextRoadName");
        if (naviNextRoadName != null) {
            LogUtil.logD(TAG, "sendNaviInfoToCluster packageNextIntersectionName: " + naviNextRoadName);
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNextIntersectionName(naviNextRoadName));
        }
        String destinationDistance = DEFAULT_DISTANCE;
        String guidanceDistance = DEFAULT_DISTANCE;
        if (naviDistanceToDest != -1) {
            if (naviDistanceToDest >= 1000) {
                destinationDistance = String.valueOf(naviDistanceToDest / 1000) + "." + String.valueOf((naviDistanceToDest % 1000) / ONE_HUNDRED_METERS);
            } else {
                destinationDistance = String.valueOf(naviDistanceToDest);
            }
        }
        if (naviNextRoadDistance != -1) {
            if (naviNextRoadDistance >= 1000) {
                StringBuilder sb = new StringBuilder();
                int naviIconTbt2 = naviNextRoadDistance / 1000;
                sb.append(String.valueOf(naviIconTbt2));
                sb.append(".");
                sb.append(String.valueOf((naviNextRoadDistance % 1000) / ONE_HUNDRED_METERS));
                guidanceDistance = sb.toString();
            } else {
                guidanceDistance = String.valueOf(naviNextRoadDistance);
            }
        }
        LogUtil.logD(TAG, "sendNaviInfoToCluster packageDistanceInfo");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageDistanceInfo(destinationDistance, guidanceDistance));
        return true;
    }

    protected boolean sendNaviCarplayInfoToCluster(Bundle bundle) {
        LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster bundle " + bundle);
        int naviSource = bundle.getInt("NaviSource", -1);
        int naviIconTbt = bundle.getInt("NaviIconTbt", -1);
        String naviNextRoadName = bundle.getString("NaviNextRoadName");
        String naviNextRoadDistanceCp = bundle.getString("NaviNextRoadDistanceCp");
        int naviNextDistanceUnit = bundle.getInt("NaviNextDistanceUnit", -1);
        String naviDistanceToDestCp = bundle.getString("NaviDistanceToDestCp");
        int naviDistanceToDestUnit = bundle.getInt("NaviDistanceToDestUnit", -1);
        int naviToDestTime = bundle.getInt("NaviToDestTime", -1);
        LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster:  naviSource: " + naviSource + " naviIconTbt: " + naviIconTbt + " naviNextRoadName: " + naviNextRoadName + " naviNextRoadDistanceCp: " + naviNextRoadDistanceCp + " naviNextDistanceUnit: " + naviNextDistanceUnit + " naviDistanceToDestCp: " + naviDistanceToDestCp + " naviDistanceToDestUnit: " + naviDistanceToDestUnit + " naviToDestTime: " + naviToDestTime);
        LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster synchronizeStateToCluster ");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NAVI_INFO_INPUT));
        LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster packageNaviAlarmInfo :hide alarm info");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNaviAlarmInfo(0, 0, 0));
        LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster packageNaviInfo ");
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNaviInfo(bundle));
        if (naviNextRoadName != null) {
            LogUtil.logD(TAG, "sendNaviCarplayInfoToCluster packageNextIntersectionName: " + naviNextRoadName);
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageNextIntersectionName(naviNextRoadName));
        }
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageDistanceInfo(naviDistanceToDestCp, naviNextRoadDistanceCp));
        return true;
    }

    protected boolean sendRadioInfoToCluster(Bundle bundle) {
        int radioType = bundle.getInt("RadioType", -1);
        String mediaName = bundle.getString("MediaName");
        byte[] mediaCover = bundle.getByteArray("MediaCover");
        String mediaCoverUri = bundle.getString("MediaCoverUri", DEFAULT_STRING);
        bundle.getInt("MediaState", -1);
        LogUtil.logD(TAG, "sendRadioInfoToCluster  radioType " + radioType + " mediaName " + mediaName + " mediaCover " + mediaCover + " mediaCoverUri " + mediaCoverUri);
        if (mediaName == null) {
            LogUtil.logW(TAG, "sendRadioInfoToCluster mediaName is null so return ");
            return true;
        }
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.RADIO_OPEN));
        if (radioType == 1) {
            if (mediaName.contains(".")) {
                String begin = mediaName.substring(0, mediaName.indexOf("."));
                String end = mediaName.substring(begin.length() + 1, mediaName.length());
                try {
                    int integerPart = Integer.parseInt(begin);
                    int fractionalPart = Integer.parseInt(end);
                    LogUtil.logD(TAG, "sendRadioInfoToCluster integerPart:" + integerPart + " fractionalPart:" + fractionalPart);
                    sendTextDataToCluster(ClusterPackageUtils.getInstance().packageFmData(mediaCoverUri, integerPart, fractionalPart));
                } catch (Exception exc) {
                    LogUtil.logI(TAG, Log.getStackTraceString(exc));
                }
            } else {
                LogUtil.logW(TAG, "sendRadioInfoToCluster mediaName is unsupport: " + mediaName);
            }
        } else if (radioType == 0) {
            try {
                int frequency = Integer.parseInt(mediaName);
                sendTextDataToCluster(ClusterPackageUtils.getInstance().packageAmData(mediaCoverUri, frequency));
            } catch (Exception exc2) {
                LogUtil.logI(TAG, Log.getStackTraceString(exc2));
            }
        } else {
            LogUtil.logW(TAG, "sendRadioInfoToCluster radioType unsupport: " + radioType);
        }
        sendBitmapToCluster(ClusterConstants.BitMapType.Radio, mediaCoverUri);
        return true;
    }

    private boolean sendRadioStateToCluster(Bundle bundle) {
        bundle.getInt("MediaState");
        return true;
    }

    private boolean sendInternetRadioInfoToCluster(Bundle bundle) {
        int internetRadioType = bundle.getInt("InternetRadioType", -1);
        String mediaName = bundle.getString("MediaName");
        String mediaCoverUri = bundle.getString("MediaCoverUri");
        LogUtil.logD(TAG, "sendInternetRadioInfoToCluster  internetRadioType " + internetRadioType + " mediaName " + mediaName + " mediaCoverUri " + mediaCoverUri);
        if (3 != this.mMusicSource) {
            this.mMusicSource = 3;
            ClusterPackageUtils.getInstance().clearMusicInfor();
            this.mMediaCoverUri = "";
            sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
            setNotHaveMediaCover();
        }
        if (mediaName != null) {
            sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaName(mediaName));
        }
        sendOnlineMediaCover(mediaCoverUri);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(bundle));
        return true;
    }

    private boolean sendInternetRadioPlayingTime(Bundle bundle) {
        long mediaTotalTime = bundle.getLong("MediaTotalTime", -1L);
        long mediaCurTime = bundle.getLong("MediaCurTime");
        LogUtil.logD(TAG, "sendInternetRadioPlayingTime  mediaTotalTime " + mediaTotalTime + " mediaCurTime " + mediaCurTime);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(bundle));
        return true;
    }

    private boolean sendInternetRadioState(Bundle bundle) {
        int mediaState = bundle.getInt("MediaState");
        LogUtil.logD(TAG, "sendInternetRadioState  mediaState " + mediaState);
        sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.MUSIC_OPEN));
        sendTextDataToCluster(ClusterPackageUtils.getInstance().packageMediaInfo(bundle));
        return true;
    }

    private boolean reqClusterOpenAreas(Bundle bundle) {
        return true;
    }

    private boolean reqClusterInfo(Bundle bundle) {
        return true;
    }

    private boolean sendBtConnectState(Bundle bundle) {
        int btConnectState = bundle.getInt("BtConnectState");
        LogUtil.logD(TAG, "sendBtConnectState btConnectState: " + btConnectState);
        if (btConnectState == 0) {
            int i = this.mBtCallState;
            if (i != 4 && i != -2) {
                sendTextDataToCluster(ClusterPackageUtils.getInstance().synchronizeStateToCluster(ClusterConstants.IviState.NO_PHONE));
                LogUtil.logD(TAG, "current BtCallState: " + this.mBtCallState);
                this.mBtCallState = 4;
                ClusterPackageUtils.getInstance().clearPhoneInfor();
                return true;
            }
            LogUtil.logD(TAG, "current BtCallState: " + this.mBtCallState);
            return true;
        }
        return true;
    }

    private boolean sendDataToAndroid() {
        Bundle bundle = new Bundle();
        Message message = Message.obtain(this.mHandler, 5);
        message.setData(bundle);
        this.mHandler.sendMessage(message);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBitmapToCluster(ClusterConstants.BitMapType bitmapType, String msg) {
        LogUtil.logD(TAG, "enter sendBitmapToCluster");
        if (msg == null || msg.length() == 0) {
            LogUtil.logW(TAG, "sendBitmapToCluster msg is empty");
            return;
        }
        String str = this.mMsgBitmap;
        if (str != null && !"".equals(str) && this.mMsgBitmap.equals(msg)) {
            LogUtil.logW(TAG, "sendBitmapToCluster msg is same");
            return;
        }
        this.mMsgBitmap = msg;
        ClusterConstants.BitMapType.UNKNOWN.ordinal();
        int i = AnonymousClass4.$SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType[bitmapType.ordinal()];
        if (i == 1) {
            ClusterConstants.BitMapType.UNKNOWN.ordinal();
        } else if (i == 2) {
            ClusterConstants.BitMapType.Meida.ordinal();
        } else if (i == 3) {
            ClusterConstants.BitMapType.Radio.ordinal();
        } else if (i != 4) {
            LogUtil.logW(TAG, "unhandle switch in sendBitmapToCluster");
        } else {
            ClusterConstants.BitMapType.Phone.ordinal();
        }
        int type = ClusterConstants.BitMapType.Meida.ordinal();
        LogUtil.logD(TAG, "sendBitmapToCluster mMsgBitmap:" + this.mMsgBitmap + " type:" + type);
        LogUtil.logD(TAG, "exit sendBitmapToCluster");
    }

    /* JADX INFO: renamed from: com.ts.lib.cluster.adapter.ClusterAdapter$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType;

        static {
            int[] iArr = new int[ClusterConstants.BitMapType.values().length];
            $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType = iArr;
            try {
                iArr[ClusterConstants.BitMapType.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType[ClusterConstants.BitMapType.Meida.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType[ClusterConstants.BitMapType.Radio.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$lib$cluster$adapter$ClusterConstants$BitMapType[ClusterConstants.BitMapType.Phone.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTextDataToCluster(byte[] msg) {
        LogUtil.logD(TAG, "enter sendTextDataToCluster");
        if (msg == null || msg.length == 0) {
            LogUtil.logW(TAG, "sendTextDataToCluster msg is empty");
        } else {
            LogUtil.logD(TAG, "exit sendTextDataToCluster");
        }
    }

    private void receiveFullMapReqFromCluster(byte[] msg) {
        LogUtil.logD(TAG, "enter receiveFullMapReqFromCluster");
        if (msg == null || msg.length < 4) {
            LogUtil.logW(TAG, "receiveFullMapReqFromCluster msg is not expected");
            return;
        }
        this.mFullMapReq = msg;
        LogUtil.logD(TAG, "receiveFullMapReqFromCluster:  byte[0]:" + ((int) this.mFullMapReq[0]) + " byte[1]:" + ((int) this.mFullMapReq[1]) + " byte[2]:" + ((int) this.mFullMapReq[2]) + " byte[3]:" + ((int) this.mFullMapReq[3]));
        ClusterPackageUtils.getInstance().printHexBinary(this.mFullMapReq);
        ClusterCommManager clusterCommManager = ClusterCommManager.getInstance(this.mContext);
        Bundle bundle = new Bundle();
        bundle.putInt("DataType", 20);
        byte b = this.mFullMapReq[2];
        if (b == 0) {
            bundle.putInt("ClusterThemeType", 2);
            clusterCommManager.sendCommand(32, bundle);
        } else if (b == 1) {
            bundle.putInt("ClusterThemeType", 3);
            clusterCommManager.sendCommand(32, bundle);
        } else if (b != 2) {
            LogUtil.logD(TAG, "Invalid map projection request.");
            bundle.putInt("ClusterThemeType", 1);
            sendFullMapRespToCluster(bundle);
        } else {
            bundle.putInt("ClusterThemeType", 4);
            clusterCommManager.sendCommand(32, bundle);
        }
        LogUtil.logD(TAG, "exit receiveFullMapReqFromCluster");
    }

    private boolean sendFullMapRespToCluster(Bundle bundle) {
        LogUtil.logD(TAG, "enter sendFullMapRespToCluster");
        int clusterThemeType = bundle.getInt("ClusterThemeType", -1);
        LogUtil.logD(TAG, "clusterThemeType: " + clusterThemeType);
        if (clusterThemeType == 0 || clusterThemeType == 1) {
            if (this.mFullMapReq == null) {
                LogUtil.logW(TAG, "sendFullMapRespToCluster mFullMapReq is null");
                return false;
            }
            byte[] msg = ClusterPackageUtils.getInstance().packageFullMapModeResp(clusterThemeType, this.mFullMapReq);
            if (msg != null && msg.length >= 5) {
                LogUtil.logD(TAG, "sendFullMapRespToCluster:  byte[0]:" + ((int) msg[0]) + " byte[1]:" + ((int) msg[1]) + " byte[2]:" + ((int) msg[2]) + " byte[3]:" + ((int) msg[3]) + " byte[4]:" + ((int) msg[4]));
            }
        } else {
            LogUtil.logD(TAG, "unexpected clusterThemeType is :" + clusterThemeType);
        }
        LogUtil.logD(TAG, "exit sendFullMapRespToCluster");
        return true;
    }

    private void btCallStateTransform(int callState) {
        if (callState == 0) {
            this.mBtCallState = 3;
            return;
        }
        if (callState != 7) {
            if (callState == 2 || callState == 3) {
                this.mBtCallState = 1;
                return;
            }
            if (callState == 4 || callState == 5) {
                this.mBtCallState = 2;
                return;
            }
            LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
            return;
        }
        this.mBtCallState = 4;
    }

    private void ebCallStateTransform(int callState) {
        if (callState == 0) {
            this.mEbCallState = 0;
            return;
        }
        if (callState == 2) {
            this.mEbCallState = 1;
            return;
        }
        if (callState == 3 || callState == 4) {
            this.mEbCallState = 3;
            return;
        }
        if (callState == 5) {
            this.mEbCallState = 4;
            return;
        }
        LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
    }

    private void carplayCallStateTransform(int callState) {
        switch (callState) {
            case ClusterBroadcastReceiver.DEFAULT /* -1 */:
                this.mCarplayCallState = 0;
                break;
            case 0:
                this.mCarplayCallState = 4;
                break;
            case 1:
                this.mCarplayCallState = 1;
                break;
            case 2:
                this.mCarplayCallState = 2;
                break;
            case 3:
            case 5:
            case 6:
                break;
            case 4:
                this.mCarplayCallState = 3;
                break;
            default:
                LogUtil.logW(TAG, "unexpected CallState in switch " + callState);
                break;
        }
    }

    protected void sendTestBytes() {
    }
}
