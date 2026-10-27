package com.byd.music;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.byd.mediacenter.api.media.IMediaControlCallback;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.api.ApiMethod;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MediaService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x009e A[Catch: all -> 0x099c, TryCatch #2 {all -> 0x099c, blocks: (B:12:0x007a, B:13:0x0096, B:15:0x009e, B:17:0x00aa, B:19:0x00b9, B:21:0x00c3, B:23:0x00cf, B:34:0x0102, B:35:0x0105, B:80:0x0195, B:160:0x031c, B:161:0x031f, B:162:0x0322, B:165:0x0329, B:167:0x032e, B:169:0x0332, B:171:0x035a, B:173:0x0368, B:175:0x03bf, B:176:0x03c1, B:178:0x03c7, B:184:0x0409, B:186:0x0415, B:190:0x0428, B:191:0x042b, B:193:0x0439, B:179:0x03ca, B:181:0x03d8, B:183:0x03ec, B:194:0x0462, B:195:0x047b, B:197:0x04bc, B:198:0x04c3, B:199:0x04c6, B:225:0x0512, B:227:0x0548, B:228:0x054b, B:230:0x0555, B:231:0x0559, B:233:0x0563, B:234:0x0568, B:236:0x0572, B:237:0x0577, B:239:0x057d, B:243:0x058c, B:247:0x0598, B:249:0x05a5, B:250:0x05a8, B:252:0x05b0, B:253:0x05b4, B:255:0x05ba, B:256:0x05be, B:258:0x05c4, B:259:0x05c8, B:261:0x05ce, B:262:0x05d2, B:264:0x05d8, B:265:0x05dc, B:267:0x05e2, B:268:0x05e6, B:270:0x05ec, B:271:0x05f0, B:273:0x05f6, B:274:0x05fa, B:276:0x0600, B:277:0x0604, B:279:0x060c, B:280:0x0610, B:282:0x0618, B:283:0x061c, B:285:0x0624, B:286:0x0628, B:240:0x0582, B:242:0x0588, B:287:0x0678, B:288:0x068c, B:289:0x068f, B:313:0x06d3, B:317:0x06f6, B:318:0x0704, B:323:0x075f, B:330:0x0775, B:331:0x077e, B:332:0x0790, B:333:0x07a2, B:334:0x07b4, B:335:0x07ca, B:337:0x07ce, B:338:0x0812, B:340:0x083f, B:341:0x0883, B:342:0x0895, B:354:0x08e9, B:355:0x08f2, B:356:0x0909, B:358:0x090f, B:360:0x0914, B:364:0x0926, B:362:0x0918, B:365:0x092d, B:369:0x0958, B:370:0x095c, B:372:0x0967, B:373:0x096d, B:374:0x0985, B:375:0x0996, B:85:0x01a0, B:100:0x01ee, B:101:0x01f6, B:109:0x0235, B:112:0x0252, B:113:0x026d, B:107:0x022e, B:116:0x027c, B:118:0x028a, B:122:0x029c, B:124:0x02aa, B:129:0x02c3, B:136:0x02d9, B:139:0x02e9, B:130:0x02c7, B:131:0x02cb, B:132:0x02d0, B:87:0x01a4, B:89:0x01ac, B:91:0x01bc, B:94:0x01d3, B:96:0x01d9, B:98:0x01e7, B:74:0x0187, B:57:0x014e, B:59:0x015c, B:63:0x0171, B:69:0x017d), top: B:388:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b9 A[Catch: all -> 0x099c, TryCatch #2 {all -> 0x099c, blocks: (B:12:0x007a, B:13:0x0096, B:15:0x009e, B:17:0x00aa, B:19:0x00b9, B:21:0x00c3, B:23:0x00cf, B:34:0x0102, B:35:0x0105, B:80:0x0195, B:160:0x031c, B:161:0x031f, B:162:0x0322, B:165:0x0329, B:167:0x032e, B:169:0x0332, B:171:0x035a, B:173:0x0368, B:175:0x03bf, B:176:0x03c1, B:178:0x03c7, B:184:0x0409, B:186:0x0415, B:190:0x0428, B:191:0x042b, B:193:0x0439, B:179:0x03ca, B:181:0x03d8, B:183:0x03ec, B:194:0x0462, B:195:0x047b, B:197:0x04bc, B:198:0x04c3, B:199:0x04c6, B:225:0x0512, B:227:0x0548, B:228:0x054b, B:230:0x0555, B:231:0x0559, B:233:0x0563, B:234:0x0568, B:236:0x0572, B:237:0x0577, B:239:0x057d, B:243:0x058c, B:247:0x0598, B:249:0x05a5, B:250:0x05a8, B:252:0x05b0, B:253:0x05b4, B:255:0x05ba, B:256:0x05be, B:258:0x05c4, B:259:0x05c8, B:261:0x05ce, B:262:0x05d2, B:264:0x05d8, B:265:0x05dc, B:267:0x05e2, B:268:0x05e6, B:270:0x05ec, B:271:0x05f0, B:273:0x05f6, B:274:0x05fa, B:276:0x0600, B:277:0x0604, B:279:0x060c, B:280:0x0610, B:282:0x0618, B:283:0x061c, B:285:0x0624, B:286:0x0628, B:240:0x0582, B:242:0x0588, B:287:0x0678, B:288:0x068c, B:289:0x068f, B:313:0x06d3, B:317:0x06f6, B:318:0x0704, B:323:0x075f, B:330:0x0775, B:331:0x077e, B:332:0x0790, B:333:0x07a2, B:334:0x07b4, B:335:0x07ca, B:337:0x07ce, B:338:0x0812, B:340:0x083f, B:341:0x0883, B:342:0x0895, B:354:0x08e9, B:355:0x08f2, B:356:0x0909, B:358:0x090f, B:360:0x0914, B:364:0x0926, B:362:0x0918, B:365:0x092d, B:369:0x0958, B:370:0x095c, B:372:0x0967, B:373:0x096d, B:374:0x0985, B:375:0x0996, B:85:0x01a0, B:100:0x01ee, B:101:0x01f6, B:109:0x0235, B:112:0x0252, B:113:0x026d, B:107:0x022e, B:116:0x027c, B:118:0x028a, B:122:0x029c, B:124:0x02aa, B:129:0x02c3, B:136:0x02d9, B:139:0x02e9, B:130:0x02c7, B:131:0x02cb, B:132:0x02d0, B:87:0x01a4, B:89:0x01ac, B:91:0x01bc, B:94:0x01d3, B:96:0x01d9, B:98:0x01e7, B:74:0x0187, B:57:0x014e, B:59:0x015c, B:63:0x0171, B:69:0x017d), top: B:388:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c3 A[Catch: all -> 0x099c, TryCatch #2 {all -> 0x099c, blocks: (B:12:0x007a, B:13:0x0096, B:15:0x009e, B:17:0x00aa, B:19:0x00b9, B:21:0x00c3, B:23:0x00cf, B:34:0x0102, B:35:0x0105, B:80:0x0195, B:160:0x031c, B:161:0x031f, B:162:0x0322, B:165:0x0329, B:167:0x032e, B:169:0x0332, B:171:0x035a, B:173:0x0368, B:175:0x03bf, B:176:0x03c1, B:178:0x03c7, B:184:0x0409, B:186:0x0415, B:190:0x0428, B:191:0x042b, B:193:0x0439, B:179:0x03ca, B:181:0x03d8, B:183:0x03ec, B:194:0x0462, B:195:0x047b, B:197:0x04bc, B:198:0x04c3, B:199:0x04c6, B:225:0x0512, B:227:0x0548, B:228:0x054b, B:230:0x0555, B:231:0x0559, B:233:0x0563, B:234:0x0568, B:236:0x0572, B:237:0x0577, B:239:0x057d, B:243:0x058c, B:247:0x0598, B:249:0x05a5, B:250:0x05a8, B:252:0x05b0, B:253:0x05b4, B:255:0x05ba, B:256:0x05be, B:258:0x05c4, B:259:0x05c8, B:261:0x05ce, B:262:0x05d2, B:264:0x05d8, B:265:0x05dc, B:267:0x05e2, B:268:0x05e6, B:270:0x05ec, B:271:0x05f0, B:273:0x05f6, B:274:0x05fa, B:276:0x0600, B:277:0x0604, B:279:0x060c, B:280:0x0610, B:282:0x0618, B:283:0x061c, B:285:0x0624, B:286:0x0628, B:240:0x0582, B:242:0x0588, B:287:0x0678, B:288:0x068c, B:289:0x068f, B:313:0x06d3, B:317:0x06f6, B:318:0x0704, B:323:0x075f, B:330:0x0775, B:331:0x077e, B:332:0x0790, B:333:0x07a2, B:334:0x07b4, B:335:0x07ca, B:337:0x07ce, B:338:0x0812, B:340:0x083f, B:341:0x0883, B:342:0x0895, B:354:0x08e9, B:355:0x08f2, B:356:0x0909, B:358:0x090f, B:360:0x0914, B:364:0x0926, B:362:0x0918, B:365:0x092d, B:369:0x0958, B:370:0x095c, B:372:0x0967, B:373:0x096d, B:374:0x0985, B:375:0x0996, B:85:0x01a0, B:100:0x01ee, B:101:0x01f6, B:109:0x0235, B:112:0x0252, B:113:0x026d, B:107:0x022e, B:116:0x027c, B:118:0x028a, B:122:0x029c, B:124:0x02aa, B:129:0x02c3, B:136:0x02d9, B:139:0x02e9, B:130:0x02c7, B:131:0x02cb, B:132:0x02d0, B:87:0x01a4, B:89:0x01ac, B:91:0x01bc, B:94:0x01d3, B:96:0x01d9, B:98:0x01e7, B:74:0x0187, B:57:0x014e, B:59:0x015c, B:63:0x0171, B:69:0x017d), top: B:388:0x007a }] */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int onStartCommand(android.content.Intent r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 2712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.MediaService.onStartCommand(android.content.Intent, int, int):int");
    }

    static /* synthetic */ void lambda$onStartCommand$0(long j) {
        if (j == RadioServiceWrapper.getInstance().isGetDadBER) {
            LogUtil.d("DIAL", "getDabBER after 60s, force set false.");
            RadioServiceWrapper.getInstance().isGetDadBER = -1L;
        }
    }

    static /* synthetic */ void lambda$onStartCommand$1(IMediaControlCallback iMediaControlCallback, int i, int i2) {
        while (RadioServiceWrapper.getInstance().isGetDadBER > 0) {
            try {
                LogUtil.d("DIAL", "isGetDadBER just 500ms, continue ~ ");
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (RadioServiceWrapper.getInstance().DAB_BER >= 0) {
            LogUtil.d("DIAL", "getDabBER go to success ~ ");
            Bundle bundle = new Bundle();
            bundle.putInt(MediaControl.PARAM_DAB_BER, RadioServiceWrapper.getInstance().DAB_BER);
            callback(iMediaControlCallback, i, i2, true, MediaControl.PARAM_SUCCESS, bundle);
        } else {
            LogUtil.d("DIAL", "getDabBER go to failed ~ ");
            callback(iMediaControlCallback, i, i2, false, MediaControl.REASON_UNSUPPORTED_OPERATION);
        }
        RadioServiceWrapper.getInstance().DAB_BER = -1;
    }

    static /* synthetic */ void lambda$onStartCommand$2(long j) {
        if (j == RadioServiceWrapper.getInstance().isGetFiledStrength) {
            LogUtil.d("DIAL", "getFieldStrength after 5000, force set false.");
            RadioServiceWrapper.getInstance().isGetFiledStrength = -1L;
        }
    }

    static /* synthetic */ void lambda$onStartCommand$3(IMediaControlCallback iMediaControlCallback, int i, int i2) {
        while (RadioServiceWrapper.getInstance().isGetFiledStrength > 0) {
            try {
                LogUtil.d("DIAL", "getFieldStrength just 500, continue ~ ");
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (RadioServiceWrapper.getInstance().ID_TWO_STRENGTH >= 0 && RadioServiceWrapper.getInstance().ID_ONE_STRENGTH >= 0) {
            LogUtil.d("DIAL", "getFieldStrength go to success ~ ");
            Bundle bundle = new Bundle();
            bundle.putInt(MediaControl.PARAM_AM_FM1_FIELD_STRENGTH, RadioServiceWrapper.getInstance().ID_ONE_STRENGTH);
            bundle.putInt(MediaControl.PARAM_FM2_FIELD_STRENGTH, RadioServiceWrapper.getInstance().ID_TWO_STRENGTH);
            RadioServiceWrapper.getInstance().ID_ONE_STRENGTH = -1;
            RadioServiceWrapper.getInstance().ID_TWO_STRENGTH = -1;
            callback(iMediaControlCallback, i, i2, true, MediaControl.PARAM_SUCCESS, bundle);
            return;
        }
        LogUtil.d("DIAL", "getFieldStrength go to failed ~ ");
        RadioServiceWrapper.getInstance().ID_ONE_STRENGTH = -1;
        RadioServiceWrapper.getInstance().ID_TWO_STRENGTH = -1;
        callback(iMediaControlCallback, i, i2, false, MediaControl.REASON_UNSUPPORTED_OPERATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callback(IMediaControlCallback iMediaControlCallback, int i, int i2, boolean z, String str) {
        callback(iMediaControlCallback, i, i2, z, str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callback(IMediaControlCallback iMediaControlCallback, int i, int i2, boolean z, String str, Bundle bundle) {
        if (iMediaControlCallback != null) {
            LogUtil.d(" MediaService --> callback enter, result = " + bundle);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(MediaControl.PARAM_SUCCESS, z ? MediaControl.TRUE : MediaControl.FALSE);
            if (!z) {
                bundle.putString("reason", str);
            }
            try {
                iMediaControlCallback.onControlResult(i, i2, bundle);
                return;
            } catch (Throwable th) {
                LogUtil.e(" MediaService --> callback error:" + th);
                th.printStackTrace();
                return;
            }
        }
        LogUtil.e(" MediaService --> iMediaControlCallback = null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String playLocalFromVoice(boolean z, String str, HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" playLocalFromVoice param=");
        sb.append(map);
        String str2 = null;
        CharSequence charSequenceSubSequence = null;
        int i = -1;
        if (map != null) {
            String str3 = map.get("fliename");
            String str4 = map.get("source");
            String str5 = map.get(MediaControl.PARAM_WITH_UI);
            sb.append(" fileName= ");
            sb.append(str3);
            sb.append(" source= ");
            sb.append(str4);
            if (!TextUtils.isEmpty(str3)) {
                int iLastIndexOf = str3.lastIndexOf(46);
                if (iLastIndexOf > 0) {
                    charSequenceSubSequence = str3.subSequence(0, iLastIndexOf);
                } else {
                    sb.append(" endIndex=");
                    sb.append(iLastIndexOf);
                    charSequenceSubSequence = str3;
                }
                LogUtil.d("source type = " + str4 + ", file name = " + ((Object) charSequenceSubSequence));
            }
            if (!TextUtils.isEmpty(str4)) {
                int iIntValue = Integer.valueOf(str4).intValue();
                int i2 = iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? -1 : 1 : 2 : 0;
                if (i2 != -1) {
                    z = MusicPlayHelper.getInstance().initMusicListForVoice(i2, charSequenceSubSequence);
                } else {
                    z = isExistMusicInStorage(z, charSequenceSubSequence);
                }
                str2 = str5;
                i = i2;
            } else {
                int iConvertMusicEngineToStorageType = MusicPlayHelper.getInstance().convertMusicEngineToStorageType(OnLineMusic.musicEngine);
                LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(iConvertMusicEngineToStorageType);
                if (currentPlayLocalMusic == null) {
                    currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentMusicFromSp(iConvertMusicEngineToStorageType);
                }
                if (currentPlayLocalMusic == null) {
                    ArrayList arrayList = new ArrayList(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                    if (arrayList.size() > 0) {
                        currentPlayLocalMusic = (LocalMusicBean) arrayList.get(0);
                    }
                }
                boolean zEquals = Boolean.TRUE.equals(MusicPlayHelper.getInstance().getIsPlayingMaps().getOrDefault(Integer.valueOf(iConvertMusicEngineToStorageType), false));
                sb.append(" isExistLocalBean= ");
                sb.append(currentPlayLocalMusic != null);
                sb.append(" storageType= ");
                sb.append(currentPlayLocalMusic != null ? currentPlayLocalMusic.getStorageType() : -1);
                sb.append(" isPlaying= ");
                sb.append(zEquals);
                if (!TextUtils.isEmpty(charSequenceSubSequence)) {
                    z = isExistMusicInStorage(z, charSequenceSubSequence);
                } else {
                    if (currentPlayLocalMusic != null && LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri()) && !zEquals) {
                        str2 = str5;
                        z = true;
                    }
                    i = iConvertMusicEngineToStorageType;
                }
                str2 = str5;
                i = iConvertMusicEngineToStorageType;
            }
        }
        sb.append(" storageType=");
        sb.append(i);
        sb.append(" isExistMusic=");
        sb.append(z);
        if (z) {
            boolean zEnsureCurrentMusicEngineData = MusicPlayHelper.getInstance().ensureCurrentMusicEngineData();
            sb.append(" ensureResult=");
            sb.append(zEnsureCurrentMusicEngineData);
            if (zEnsureCurrentMusicEngineData) {
                str = ApiMethod.playOrStop(true);
                MusicPlayHelper.getInstance().checkUpdateLocalDataForCMIU();
            }
        } else {
            if (!TextUtils.isEmpty(str2) && Boolean.parseBoolean(str2)) {
                MusicPlayHelper.getInstance().updateLocalPlayStateWhenMusicIsNotExist();
            }
            str = MediaControl.REASON_NOT_FOUND;
        }
        sb.append(" withUi=");
        sb.append(str2);
        sb.append(" tName=");
        sb.append(Thread.currentThread().getName());
        LogUtil.e(sb.toString());
        return str;
    }

    private boolean isExistMusicInStorage(boolean z, CharSequence charSequence) {
        for (int i = 0; i < 3 && !(z = MusicPlayHelper.getInstance().initMusicListForVoice(i, charSequence)); i++) {
        }
        return z;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.e("MediaService -- onBind");
        return new MyBinder();
    }

    class MyBinder extends Binder {
        MyBinder() {
        }
    }

    class MyConn implements ServiceConnection {
        IBinder binder;

        MyConn() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.binder = (MyBinder) iBinder;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.binder = null;
        }
    }

    public int getFocusedWindow() {
        try {
            ActivityManager.StackInfo focusedStackInfo = ActivityTaskManager.getService().getFocusedStackInfo();
            getStackInfo(true);
            return focusedStackInfo.stackId == getStackInfo(false).stackId ? 4 : 3;
        } catch (Throwable th) {
            th.printStackTrace();
            return 3;
        }
    }

    public ActivityManager.StackInfo getStackInfo(boolean z) {
        try {
            return ActivityTaskManager.getService().getStackInfo(z ? 3 : 4, 0);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ActivityManager.RunningTaskInfo getRunningTask() {
        try {
            List<ActivityManager.RunningTaskInfo> filteredTasks = ActivityTaskManager.getService().getFilteredTasks(1, false);
            if (filteredTasks.isEmpty()) {
                return null;
            }
            return filteredTasks.get(0);
        } catch (Throwable unused) {
            return null;
        }
    }
}
