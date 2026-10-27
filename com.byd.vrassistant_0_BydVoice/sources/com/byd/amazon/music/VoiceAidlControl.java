package com.byd.amazon.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.byd.amazon.music.IMusicVoiceAidlInterface;
import com.byd.amazon.music.bean.BooleanResult;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.PlayModeResult;
import com.byd.amazon.music.bean.PlayStateResult;
import com.byd.amazon.music.bean.Result;
import com.byd.amazon.music.config.Constant;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.VodType;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class VoiceAidlControl implements IMusicVoiceControl {
    private static final String TAG = "VoiceAidlControl";
    private static volatile VoiceAidlControl mVoiceAidlControl;
    private Context mContext;
    private IMusicVoiceAidlInterface mMusicVoiceAidlInterface;
    private IServiceConnectStateCallBack mServiceConnectStateCallBack;
    private boolean mIsConnect = false;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.byd.amazon.music.VoiceAidlControl.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            VoiceAidlControl.this.mIsConnect = true;
            try {
                iBinder.linkToDeath(VoiceAidlControl.this.mDeathRecipient, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            VoiceAidlControl.this.mMusicVoiceAidlInterface = IMusicVoiceAidlInterface.Stub.asInterface(iBinder);
            if (VoiceAidlControl.this.mServiceConnectStateCallBack != null) {
                VoiceAidlControl.this.mServiceConnectStateCallBack.onConnect();
            }
            Log.d(VoiceAidlControl.TAG, "onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            VoiceAidlControl.this.mIsConnect = false;
            if (VoiceAidlControl.this.mServiceConnectStateCallBack != null) {
                VoiceAidlControl.this.mServiceConnectStateCallBack.disConnect();
            }
            Log.d(VoiceAidlControl.TAG, "onServiceDisconnected");
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.byd.amazon.music.VoiceAidlControl.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (VoiceAidlControl.this.mMusicVoiceAidlInterface != null) {
                VoiceAidlControl.this.mMusicVoiceAidlInterface.asBinder().unlinkToDeath(VoiceAidlControl.this.mDeathRecipient, 0);
                VoiceAidlControl.this.mMusicVoiceAidlInterface = null;
            }
            VoiceAidlControl.this.bindService();
        }
    };

    public static VoiceAidlControl getInstance() {
        if (mVoiceAidlControl == null) {
            synchronized (VoiceAidlControl.class) {
                if (mVoiceAidlControl == null) {
                    mVoiceAidlControl = new VoiceAidlControl();
                }
            }
        }
        return mVoiceAidlControl;
    }

    public Result init(Context context, IServiceConnectStateCallBack iServiceConnectStateCallBack) {
        this.mContext = context;
        this.mServiceConnectStateCallBack = iServiceConnectStateCallBack;
        return bindService();
    }

    public Result unInit() {
        Context context = this.mContext;
        if (context != null) {
            context.unbindService(this.mConnection);
            return Result.success();
        }
        return Result.error();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result bindService() {
        try {
            Intent intent = new Intent();
            Log.e(TAG, "com.android.car.media com.android.car.media.byd.voice.AmazonMusicVoiceService release  SDK_BUILD_TIME: 202405281621 ");
            intent.setComponent(new ComponentName("com.android.car.media", Constant.SERVICE_NAME));
            this.mContext.bindService(intent, this.mConnection, 1);
            return Result.success();
        } catch (Exception e) {
            Log.e(TAG, "bindService Exception" + e + Arrays.toString(new Object[0]));
            return Result.error();
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result play() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.play() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result pause() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.pause() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result playPre() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.playPre() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result playNext() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.playNext() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result replay() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.replay() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result setFavorite() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.setFavorite() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result unSetFavorite() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.unSetFavorite() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result setPlayMode(PlayMode playMode) {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.setPlayMode(playMode) : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result openPlayListPage() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.openPlayListPage() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result closePlayListPage() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.closePlayListPage() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result stop() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.stop() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result exitMusic() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.exitMusic() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result playByVod(String str, VodType vodType, IMusicItemsCallBack iMusicItemsCallBack) {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.playByVod(str, vodType, iMusicItemsCallBack) : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result playByFavorite() {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.playByFavorite() : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public Result playById(String str, IPlayStateCallBack iPlayStateCallBack) {
        Result result = Result.result(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.playById(str, iPlayStateCallBack) : result;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return result;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public BooleanResult isLogIn() {
        BooleanResult booleanResult = new BooleanResult(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.isLogIn() : booleanResult;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return booleanResult;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public PlayStateResult getPlayState() {
        PlayStateResult playStateResult = new PlayStateResult(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.getPlayState() : playStateResult;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return playStateResult;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public MusicItemResult getNowPlayingSong() {
        MusicItemResult musicItemResult = new MusicItemResult(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.getNowPlayingSong() : musicItemResult;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return musicItemResult;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public BooleanResult isPlayListPageOpen() {
        BooleanResult booleanResult = new BooleanResult(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.isPlayListPageOpen() : booleanResult;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return booleanResult;
        }
    }

    @Override // com.byd.amazon.music.IMusicVoiceControl
    public PlayModeResult getCurPlayMode() {
        PlayModeResult playModeResult = new PlayModeResult(getResultCodeBeforeControl());
        try {
            IMusicVoiceAidlInterface iMusicVoiceAidlInterface = this.mMusicVoiceAidlInterface;
            return iMusicVoiceAidlInterface != null ? iMusicVoiceAidlInterface.getCurPlayMode() : playModeResult;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return playModeResult;
        }
    }

    private int getResultCodeBeforeControl() {
        if (this.mIsConnect && this.mMusicVoiceAidlInterface != null) {
            return -1;
        }
        bindService();
        return -1;
    }
}
