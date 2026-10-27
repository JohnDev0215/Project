package com.micphone;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import com.audio.MicphoneService.R;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class TsBydSoundPool {
    private static final String TAG = "TsBydSoundPool";
    private static volatile TsBydSoundPool mTsBydSoundPool;
    private Boolean LOAD_RAW_FLAG;
    private final int[] byd_effect_tip;
    private final int[] byd_pair_tip;
    private Context mContext;
    private final HashMap<Integer, Integer> mPairData;
    private final HashMap<Integer, Integer> mSoundData;
    private SoundPool mSoundPool;

    private TsBydSoundPool(Context context) {
        int[] iArr = {R.raw.thunder_tip_lyp, R.raw.thunder_tip_ktv, R.raw.thunder_tip_yyt, R.raw.thunder_tip_ys};
        this.byd_effect_tip = iArr;
        HashMap<Integer, Integer> map = new HashMap<>(iArr.length);
        this.mSoundData = map;
        int[] iArr2 = {R.raw.thunder_tip_mic_pair};
        this.byd_pair_tip = iArr2;
        HashMap<Integer, Integer> map2 = new HashMap<>(iArr2.length);
        this.mPairData = map2;
        this.LOAD_RAW_FLAG = Boolean.FALSE;
        String str = TAG;
        Log.e(str, "TsBydSoundPool start!");
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);
            AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
            builder2.setLegacyStreamType(1);
            builder.setAudioAttributes(builder2.build());
            this.mSoundPool = builder.build();
        } else {
            this.mSoundPool = new SoundPool(1, 1, 0);
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(iArr[0]);
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd2 = context.getResources().openRawResourceFd(iArr[1]);
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd3 = context.getResources().openRawResourceFd(iArr[2]);
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd4 = context.getResources().openRawResourceFd(iArr[3]);
            int iLoad = this.mSoundPool.load(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength(), 1);
            if (iLoad == 0) {
                Log.e(str, "lyp load error");
            }
            int iLoad2 = this.mSoundPool.load(assetFileDescriptorOpenRawResourceFd2.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd2.getStartOffset(), assetFileDescriptorOpenRawResourceFd2.getLength(), 1);
            if (iLoad2 == 0) {
                Log.e(str, "ktv load error");
            }
            int iLoad3 = this.mSoundPool.load(assetFileDescriptorOpenRawResourceFd3.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd3.getStartOffset(), assetFileDescriptorOpenRawResourceFd3.getLength(), 1);
            if (iLoad3 == 0) {
                Log.e(str, "yyt load error");
            }
            int iLoad4 = this.mSoundPool.load(assetFileDescriptorOpenRawResourceFd4.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd4.getStartOffset(), assetFileDescriptorOpenRawResourceFd4.getLength(), 1);
            if (iLoad4 == 0) {
                Log.e(str, "ys load error");
            }
            map.put(0, Integer.valueOf(iLoad));
            map.put(1, Integer.valueOf(iLoad2));
            map.put(2, Integer.valueOf(iLoad3));
            map.put(3, Integer.valueOf(iLoad4));
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd5 = context.getResources().openRawResourceFd(iArr2[0]);
            int iLoad5 = this.mSoundPool.load(assetFileDescriptorOpenRawResourceFd5.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd5.getStartOffset(), assetFileDescriptorOpenRawResourceFd5.getLength(), 1);
            if (iLoad5 == 0) {
                Log.e(str, "pair load error");
            }
            map2.put(0, Integer.valueOf(iLoad5));
            this.mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.micphone.TsBydSoundPool.1
                @Override // android.media.SoundPool.OnLoadCompleteListener
                public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                    TsBydSoundPool.this.LOAD_RAW_FLAG = Boolean.TRUE;
                    Log.e(TsBydSoundPool.TAG, "soundPool LoadComplete! " + i + i2);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "soundPool Load error " + e.getMessage());
        }
        Log.e(TAG, "TsBydSoundPool end!");
    }

    public static TsBydSoundPool getInstance(Context context) {
        if (mTsBydSoundPool == null) {
            synchronized (TsBydSoundPool.class) {
                if (mTsBydSoundPool == null) {
                    mTsBydSoundPool = new TsBydSoundPool(context);
                }
            }
        }
        return mTsBydSoundPool;
    }

    public void playTip(int i) {
        SoundPool soundPool;
        if (this.mContext == null) {
            return;
        }
        if (!this.LOAD_RAW_FLAG.booleanValue() || (soundPool = this.mSoundPool) == null) {
            Log.e(TAG, "init no ready");
            return;
        }
        int iPlay = soundPool.play(this.mSoundData.get(Integer.valueOf(i)).intValue(), 1.0f, 1.0f, 1, 0, 1.0f);
        Log.e(TAG, "play result = " + iPlay);
    }

    public void playTipPair() {
        SoundPool soundPool;
        if (this.mContext == null) {
            return;
        }
        if (!this.LOAD_RAW_FLAG.booleanValue() || (soundPool = this.mSoundPool) == null) {
            Log.e(TAG, "init no ready");
            return;
        }
        int iPlay = soundPool.play(this.mPairData.get(0).intValue(), 1.0f, 1.0f, 1, 0, 1.0f);
        Log.e(TAG, "playTipPair result = " + iPlay);
    }
}
