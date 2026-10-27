package com.byd.music.protocol;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.music.activity.main.MediaMainActivity;
import com.byd.music.protocol.IProtocolAidlInterface;
import com.byd.music.protocol.client.IProtocolMusicPlayObserver;
import com.byd.music.protocol.listener.IProtocolCallback;
import com.byd.music.protocol.model.base.ProtocolBaseModel;
import com.byd.music.protocol.model.base.ProtocolErrorModel;
import com.byd.music.utils.LogUtil;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolService extends Service {
    private static final String PROTOCOL_SUCCESS = "操作成功";
    private static final int SET_CALLBACK_DELAY = 500;
    private static final String TAG = ProtocolService.class.getSimpleName();
    private IProtocolCallback infoUpdateCallback;
    private IProtocolCallback protocolCallback;
    private ProtocolBaseModel protocolModel;
    private Lock mLock = new ReentrantLock();
    private int mProgress = 0;
    private int mMusicType = 1;
    private String mName = "";
    private String mArtist = "";
    private String mImgUrl = "";
    private boolean mIsPlaying = false;
    IProtocolMusicPlayObserver musicPlayObserver = new IProtocolMusicPlayObserver() { // from class: com.byd.music.protocol.ProtocolService.1
        @Override // com.byd.music.protocol.client.IProtocolMusicPlayObserver
        public boolean processUpdate(int i) {
            ProtocolService.this.mProgress = i;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("music_progress", i);
                jSONObject.put("music_update_type", ProtocolUtils.MUSIC_UPDATE_TYPE_PROGRESS);
                if (ProtocolService.this.infoUpdateCallback == null) {
                    return false;
                }
                ProtocolService.this.infoUpdateCallback.onJSONResult(jSONObject.toString());
                return false;
            } catch (RemoteException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override // com.byd.music.protocol.client.IProtocolMusicPlayObserver
        public boolean infoUpdate(int i, String str, String str2, String str3) {
            if (!TextUtils.isEmpty(str)) {
                ProtocolService.this.mMusicType = i;
                ProtocolService.this.mName = str;
                ProtocolService.this.mArtist = str2;
                ProtocolService.this.mImgUrl = str3;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("music_type", i);
                jSONObject.put("music_name", str);
                jSONObject.put("music_artist", str2);
                jSONObject.put("music_imgUrl", str3);
                jSONObject.put("music_update_type", ProtocolUtils.MUSIC_UPDATE_TYPE_INFO);
                if (ProtocolService.this.infoUpdateCallback == null) {
                    return false;
                }
                ProtocolService.this.infoUpdateCallback.onJSONResult(jSONObject.toString());
                return false;
            } catch (RemoteException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override // com.byd.music.protocol.client.IProtocolMusicPlayObserver
        public boolean playingStatusUpdate(boolean z) {
            ProtocolService.this.mIsPlaying = z;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("music_status", z);
                jSONObject.put("music_update_type", ProtocolUtils.MUSIC_UPDATE_TYPE_PLAYINGSTATUS);
                if (ProtocolService.this.infoUpdateCallback == null) {
                    return false;
                }
                ProtocolService.this.infoUpdateCallback.onJSONResult(jSONObject.toString());
                return false;
            } catch (RemoteException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
    };
    private final IProtocolAidlInterface.Stub mProtocolServiceManager = new IProtocolAidlInterface.Stub() { // from class: com.byd.music.protocol.ProtocolService.2
        @Override // com.byd.music.protocol.IProtocolAidlInterface
        public void setProtocolModelData(ProtocolBaseModel protocolBaseModel) throws RemoteException {
            ProtocolService.this.protocolModel = protocolBaseModel;
            if (protocolBaseModel != null) {
                ProtocolService protocolService = ProtocolService.this;
                protocolService.doOperate(protocolService.protocolModel.getProtocolID());
            }
        }

        @Override // com.byd.music.protocol.IProtocolAidlInterface
        public void registCallBack(IProtocolCallback iProtocolCallback) throws RemoteException {
            ProtocolService.this.protocolCallback = iProtocolCallback;
            if (ProtocolService.this.infoUpdateCallback == null) {
                ProtocolService.this.setInfoUpdateCallback(iProtocolCallback);
            }
            if (ProtocolService.this.protocolCallback != null) {
                ProtocolService.this.mListenerList.register(ProtocolService.this.protocolCallback);
            }
        }
    };
    private RemoteCallbackList<IProtocolCallback> mListenerList = new RemoteCallbackList<>();

    public ProtocolService() {
        ProtocolUtils.getInstance().setMusicPlayObserver(this.musicPlayObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOperate(int i) {
        switch (i) {
            case 10001:
                startActivity(new Intent(getApplication(), (Class<?>) MediaMainActivity.class).setFlags(268435456));
                callback(true);
                break;
            case 10002:
            case 10003:
            case 10004:
            case 10005:
            case 10006:
            case 10007:
                callback(true);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInfoUpdateCallback(final IProtocolCallback iProtocolCallback) {
        new Timer().schedule(new TimerTask() { // from class: com.byd.music.protocol.ProtocolService.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (ProtocolService.this.protocolModel == null || ProtocolService.this.protocolModel.getProtocolID() != 10002) {
                    return;
                }
                ProtocolService.this.infoUpdateCallback = iProtocolCallback;
            }
        }, 500L);
    }

    private void callback(boolean z) {
        this.mLock.lock();
        try {
            int iBeginBroadcast = this.mListenerList.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                IProtocolCallback iProtocolCallback = (IProtocolCallback) this.mListenerList.getBroadcastItem(i);
                if (iProtocolCallback != null) {
                    int protocolID = this.protocolModel.getProtocolID();
                    if (z) {
                        switch (protocolID) {
                            case 10001:
                            case 10002:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            case 10003:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            case 10004:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            case 10005:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            case 10006:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            case 10007:
                                iProtocolCallback.onSuccess(PROTOCOL_SUCCESS);
                                break;
                            default:
                                iProtocolCallback.onSuccess("");
                                break;
                        }
                    } else {
                        int i2 = 10007;
                        switch (protocolID) {
                            case 10002:
                                i2 = ProtocolResultCode.RESULT_CAN_NO_GET_MUSIC_INFO;
                                break;
                            case 10003:
                            case 10004:
                            case 10005:
                            case 10006:
                                i2 = ProtocolResultCode.RESULT_NO_PLAYING_MUSIC;
                                break;
                        }
                        iProtocolCallback.onFail(new ProtocolErrorModel(i2, this.protocolModel.getProtocolID(), this.protocolModel.getCallbackId(), this.protocolModel.getPackageName(), this.protocolModel.getTimeStamp()));
                    }
                    this.mListenerList.unregister(iProtocolCallback);
                }
                this.mListenerList.finishBroadcast();
            }
        } catch (Exception e) {
            LogUtil.d(TAG, "callback exception:" + e);
        }
        this.mLock.unlock();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mProtocolServiceManager;
    }

    @Override // android.app.Service
    public void onDestroy() {
        synchronized (this) {
            this.mListenerList.kill();
        }
        super.onDestroy();
    }
}
