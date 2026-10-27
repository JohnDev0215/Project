package com.byd.music.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.beans.EventString;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.constant.SPConstants;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class StorageService extends Service {
    private ContentObserver mAudioObserver;
    private LocalMusicBean mMediaScannerLocalMusic;
    private MediaScannerConnection mediaScannerConnection;
    private Runnable runnable;
    private Handler scanHandler;
    String scanPath;
    StorageManager storageManager;
    private String storageTypeMounted;
    private String TAG = "StorageServiceByd";
    private StorageBinder mBinder = new StorageBinder();
    private final int SCAN_MESSAGE = 110;
    private final int SCAN_MESSAGE_COMPLETE = 119;
    String[] path = {"/storage/emulated/0", "", ""};
    int storageType = 0;
    private int mDataChangeCount = 0;
    private boolean isNeedRemoveMsg = false;
    private String scanUriPath = null;
    private boolean mediaConnect = false;
    private boolean isFileMoved = false;
    private final StorageEventListener storageEventListener = new StorageEventListener() { // from class: com.byd.music.service.StorageService.1
        @Override // android.os.storage.StorageEventListener
        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            super.onVolumeStateChanged(volumeInfo, i, i2);
            StringBuilder sb = new StringBuilder();
            sb.append("onVolumeStateChanged newState:");
            sb.append(i2);
            sb.append(" isNeedPlay:");
            sb.append(MusicPlayHelper.getInstance().isNeedPlay);
            if (i2 != 2) {
                if (i2 == 5) {
                    StorageService.this.storageTypeMounted = null;
                    StorageService.this.isNeedRemoveMsg = true;
                    StorageService.this.scanHandler.removeMessages(119);
                    String deviceType = volumeInfo.getDeviceType();
                    boolean z = !TextUtils.isEmpty(deviceType);
                    sb.append(" StorageManagerService MEDIA_EJECT storageDeviceType:");
                    sb.append(deviceType);
                    sb.append(" vodType=");
                    sb.append(volumeInfo.getType());
                    LogUtil.e(StorageService.this.TAG, sb.toString());
                    if (volumeInfo.getType() == 0 && z) {
                        if (deviceType.equals(LocalMusicUtils.SD_PATH)) {
                            StorageService.this.path[1] = "";
                            MusicPlayHelper.getInstance().stopPlayUSBOrSD(false);
                            SPUtils.getInstance().put(SPConstants.SP_EXISTED_SD, false);
                        } else if (deviceType.equals(LocalMusicUtils.USB_PATH)) {
                            StorageService.this.path[2] = "";
                            MusicPlayHelper.getInstance().stopPlayUSBOrSD(true);
                            SPUtils.getInstance().put(SPConstants.SP_EXISTED_USB, false);
                        }
                        EventBus.getDefault().post("MEDIA_EJECT");
                        return;
                    }
                    return;
                }
                return;
            }
            StorageService.this.isNeedRemoveMsg = true;
            StorageService.this.scanHandler.removeMessages(119);
            String deviceType2 = volumeInfo.getDeviceType();
            boolean z2 = !TextUtils.isEmpty(deviceType2);
            sb.append("  StorageManagerService STATE_MOUNTED storageTypeMounted=");
            sb.append(StorageService.this.storageTypeMounted);
            sb.append(" notEmpty=" + z2);
            if (z2) {
                sb.append(" storageDeviceType:");
                sb.append(deviceType2);
                StorageService.this.storageTypeMounted = deviceType2;
                if (LocalMusicUtils.SD_PATH.equals(deviceType2)) {
                    MusicPlayHelper.getInstance().setIsSDPlug(true);
                    StorageService.this.path[1] = volumeInfo.getPath().getAbsolutePath();
                    SPUtils.getInstance().put(SPConstants.SP_EXISTED_SD, true);
                    sb.append(" sd_path = ");
                    sb.append(StorageService.this.path[1]);
                } else if (LocalMusicUtils.USB_PATH.equals(deviceType2)) {
                    MusicPlayHelper.getInstance().setIsUsbPlug(true);
                    StorageService.this.path[2] = volumeInfo.getPath().getAbsolutePath();
                    SPUtils.getInstance().put(SPConstants.SP_EXISTED_USB, true);
                    sb.append(" usb_path = ");
                    sb.append(StorageService.this.path[2]);
                }
            }
            LogUtil.e(StorageService.this.TAG, sb.toString());
        }
    };
    private BroadcastReceiver scanReceiver = new BroadcastReceiver() { // from class: com.byd.music.service.StorageService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(intent.getAction())) {
                    String path = intent.getData().getPath();
                    StringBuilder sb = new StringBuilder();
                    sb.append("scanReceiver ACTION_MEDIA_SCANNER_FINISHED data path=");
                    sb.append(StringSubUtil.subString(path));
                    int i = 0;
                    StorageService.this.isNeedRemoveMsg = false;
                    if (!TextUtils.isEmpty(path)) {
                        while (true) {
                            if (i >= 3) {
                                break;
                            }
                            if (intent.getData().getPath().equals(StorageService.this.path[i])) {
                                StorageService.this.storageType = i;
                                StorageService.this.scanPath = path;
                                sb.append(" i=");
                                sb.append(i);
                                break;
                            }
                            i++;
                        }
                        sb.append(" isMainThread=");
                        sb.append(ThreadUtils.isMainThread());
                        LogUtil.e(StorageService.this.TAG, sb.toString());
                        AppExecutors.getInstance().diskIO().execute(StorageService.this.runnable);
                    }
                    sb.append(" scan end");
                    LogUtil.e(StorageService.this.TAG, sb.toString());
                    return;
                }
                if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(intent.getAction())) {
                    LogUtil.e(StorageService.this.TAG, "scanReceiver action ACTION_MEDIA_SCANNER_STARTED=" + StringSubUtil.subString(intent.getData().getPath()));
                    LocalMusicBean curEngineMusicFromSp = MusicPlayHelper.getInstance().getCurEngineMusicFromSp();
                    if (curEngineMusicFromSp != null && (OnLineMusic.getNeedRestore() || (MusicPlayHelper.getInstance().getCurrentLocalMusic().get() == null && LocalMusicUtils.checkMusicFileExist(curEngineMusicFromSp.getUri())))) {
                        StorageService.this.initMediaScannerConnect(curEngineMusicFromSp);
                    }
                    StorageService.this.scanHandler.removeMessages(119);
                    return;
                }
                if (Intent.ACTION_MEDIA_MOUNTED.equals(intent.getAction())) {
                    String path2 = intent.getData().getPath();
                    LogUtil.e(StorageService.this.TAG, "Storage device mounted at ACTION_MEDIA_MOUNTED path=" + path2);
                    return;
                }
                if (Intent.ACTION_MEDIA_EJECT.equals(intent.getAction())) {
                    String path3 = intent.getData().getPath();
                    LogUtil.e(StorageService.this.TAG, "Storage device mounted at ACTION_MEDIA_EJECT path=" + path3);
                }
            }
        }
    };

    private void initCurrentMusic() {
        final LocalMusicBean currentMusicFromSp;
        if (MusicPlayHelper.getInstance().getCurrentLocalMusic().get() == null && (currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp()) != null && LocalMusicUtils.checkMusicFileExist(currentMusicFromSp.getUri())) {
            MediaScannerConnection.scanFile(Utils.getApp().getApplicationContext(), new String[]{currentMusicFromSp.getUri()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.music.service.StorageService.2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                    long localProgressByStorageType = MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType());
                    LogUtil.e(StorageService.this.TAG, "initCurrentMusic scanReceiver action scanFile=" + StringSubUtil.subString(str) + " uri=" + StringSubUtil.subString(currentMusicFromSp.getUri()) + "spProgress=" + localProgressByStorageType);
                    if (LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri())) {
                        MusicPlayHelper.getInstance().setCurrentMusic(currentMusicFromSp);
                        MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType()), currentMusicFromSp.getDuration(), 0, currentMusicFromSp.getStorageType()));
                        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                            CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(localProgressByStorageType, localProgressByStorageType, currentMusicFromSp.getDuration());
                        }
                        MusicPlayHelper.getInstance().setMemoryPlay(true);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCurrentMusicAndPlay(final LocalMusicBean localMusicBean) {
        if (localMusicBean != null) {
            LogUtil.d(this.TAG, "initCurrentMusicAndPlay uri= uri=" + StringSubUtil.subString(localMusicBean.getUri()) + " title=" + StringSubUtil.subString(localMusicBean.getTitle()) + " storageType=" + localMusicBean.getStorageType() + " getNeedRestore=" + OnLineMusic.getNeedRestore());
            if (OnLineMusic.getNeedRestore()) {
                MediaScannerConnection.scanFile(Utils.getApp().getApplicationContext(), new String[]{localMusicBean.getUri()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.music.service.StorageService.4
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str, Uri uri) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (MusicPlayHelper.getInstance().getLocalMusicBeanList().size() == 0) {
                            MusicPlayHelper.getInstance().getLocalMusicBeanList().add(localMusicBean);
                        }
                        MusicPlayHelper.getInstance().memoryPlayAfterScanStart(localMusicBean);
                        StorageService.this.mediaScannerConnection.disconnect();
                        LogUtil.e(StorageService.this.TAG, "scanReceiver action scanFile" + StringSubUtil.subString(str) + " storageType=" + localMusicBean.getStorageType() + " costTime=" + (System.currentTimeMillis() - jCurrentTimeMillis) + " isMainThread=" + ThreadUtils.isMainThread());
                    }
                });
            } else if (MusicPlayHelper.getInstance().getCurrentLocalMusic().get() == null && LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                MediaScannerConnection.scanFile(Utils.getApp().getApplicationContext(), new String[]{localMusicBean.getUri()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.byd.music.service.StorageService.5
                    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                    public void onScanCompleted(String str, Uri uri) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        long localProgressByStorageType = MusicPlayHelper.getInstance().getLocalProgressByStorageType(localMusicBean.getStorageType());
                        if (LocalMusicUtils.checkMusicFileLength(localMusicBean.getUri())) {
                            MusicPlayHelper.getInstance().setCurrentMusic(localMusicBean);
                            MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(localMusicBean.getStorageType()), localMusicBean.getDuration(), 0, localMusicBean.getStorageType()));
                            if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                                CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(localProgressByStorageType, localProgressByStorageType, localMusicBean.getDuration());
                            }
                            MusicPlayHelper.getInstance().setMemoryPlay(true);
                        }
                        LogUtil.e(StorageService.this.TAG, "initCurrentMusic scanReceiver action scanFile=" + StringSubUtil.subString(str) + " uri=" + StringSubUtil.subString(localMusicBean.getUri()) + " title=" + StringSubUtil.subString(localMusicBean.getTitle()) + " isEquals=" + TextUtils.equals(localMusicBean.getUri(), str) + " storageType=" + localMusicBean.getStorageType() + " spProgress=" + localProgressByStorageType + " costTime=" + (System.currentTimeMillis() - jCurrentTimeMillis) + " isMainThread=" + ThreadUtils.isMainThread());
                        StorageService.this.mediaScannerConnection.disconnect();
                    }
                });
            }
        }
    }

    public class StorageBinder extends Binder {
        public StorageBinder() {
        }

        public StorageService getStorageService() {
            return StorageService.this;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.e(this.TAG, "onCreate: ");
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        this.storageManager = storageManager;
        storageManager.registerListener(this.storageEventListener);
        this.scanHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.service.StorageService.6
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i != 110) {
                    if (i != 119) {
                        return;
                    }
                    LogUtil.e(StorageService.this.TAG, "handleMessage SCAN_MESSAGE_COMPLETE =" + StorageService.this.scanUriPath + " isFileMoved=" + StorageService.this.isFileMoved);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.byd.music.service.StorageService.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                int i2 = 0;
                                if (!StorageService.this.isFileMoved) {
                                    LocalMusicUtils.initMusicDataByPath(StorageService.this.scanUriPath);
                                    while (true) {
                                        if (i2 < 3) {
                                            if (!TextUtils.isEmpty(StorageService.this.path[i2]) && StorageService.this.scanUriPath.contains(StorageService.this.path[i2])) {
                                                EventBus.getDefault().postSticky(new EventString("update_data_storage", String.valueOf(i2)));
                                                break;
                                            }
                                            i2++;
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    LocalMusicUtils.initMusicDataByPath(null);
                                    EventBus.getDefault().postSticky(new EventString("SCAN_MESSAGE_COMPLETE", "StorageService"));
                                    StorageService.this.isFileMoved = false;
                                }
                                MusicPlayHelper.getInstance().playAfterUpdateData();
                            } catch (Throwable th) {
                                th.printStackTrace();
                                LogUtil.e(StorageService.this.TAG, "error handleMessage SCAN_MESSAGE_COMPLETE:" + th.getMessage());
                            }
                        }
                    });
                    StorageService.this.mDataChangeCount = 0;
                    return;
                }
                StringBuilder sb = new StringBuilder();
                int iIntValue = ((Integer) message.obj).intValue();
                long jCurrentTimeMillis = System.currentTimeMillis();
                sb.append("handleMessage SCAN_MESSAGE isNeedPlay:");
                sb.append(MusicPlayHelper.getInstance().isNeedPlay);
                sb.append(" type=");
                sb.append(iIntValue);
                if (iIntValue == 2 || iIntValue == 1) {
                    if (MusicPlayHelper.getInstance().isNeedPlay) {
                        MusicPlayHelper.getInstance().isNeedPlay = false;
                        OnLineMusic.initPlayMusic(false);
                    }
                    StorageService.this.storageTypeMounted = iIntValue == 1 ? LocalMusicUtils.SD_PATH : LocalMusicUtils.USB_PATH;
                    MusicPlayHelper.getInstance().isNeedUpdateData = false;
                    sb.append(" storageTypeMounted= ");
                    sb.append(StorageService.this.storageTypeMounted);
                    EventBus.getDefault().post(new EventString("MEDIA_MOUNTED", StorageService.this.storageTypeMounted));
                    StorageService.this.storageTypeMounted = null;
                } else {
                    EventBus.getDefault().post(new EventString("MEDIA_MOUNTED", " "));
                }
                sb.append(" costTime=");
                sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                sb.append(" isMainThread=");
                sb.append(ThreadUtils.isMainThread());
                LogUtil.e(StorageService.this.TAG, sb.toString());
            }
        };
        this.runnable = new Runnable() { // from class: com.byd.music.service.StorageService.7
            @Override // java.lang.Runnable
            public void run() {
                boolean zIsEmpty = TextUtils.isEmpty(StorageService.this.scanPath);
                StringBuilder sb = new StringBuilder();
                sb.append("run:initMusicDataByPath：");
                sb.append(StorageService.this.scanPath);
                sb.append(" type=");
                sb.append(StorageService.this.storageType);
                sb.append(" isPathNull=");
                sb.append(zIsEmpty);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (!zIsEmpty) {
                    Message message = new Message();
                    message.what = 110;
                    message.obj = Integer.valueOf(StorageService.this.storageType);
                    LocalMusicUtils.initMusicDataByPath(false, StorageService.this.scanPath, StorageService.this.storageType);
                    StorageService.this.scanHandler.sendMessage(message);
                }
                sb.append(" costTime=");
                sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                sb.append(" isMainThread=");
                sb.append(ThreadUtils.isMainThread());
                LogUtil.e(StorageService.this.TAG, sb.toString());
            }
        };
        initContentObserver();
        registerBroadReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMediaScannerConnect(LocalMusicBean localMusicBean) {
        LogUtil.e(this.TAG, "initMediaScannerConnect:");
        this.mMediaScannerLocalMusic = localMusicBean;
        if (this.mediaScannerConnection == null) {
            this.mediaScannerConnection = new MediaScannerConnection(Utils.getApp().getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.byd.music.service.StorageService.8
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String str, Uri uri) {
                }

                @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
                public void onMediaScannerConnected() {
                    LogUtil.d(StorageService.this.TAG, "MediaScannerConnect: after getUri=" + StringSubUtil.subString(StorageService.this.mMediaScannerLocalMusic.getUri()) + " title=" + StringSubUtil.subString(StorageService.this.mMediaScannerLocalMusic.getTitle()) + " storageType=" + StorageService.this.mMediaScannerLocalMusic.getStorageType());
                    StorageService storageService = StorageService.this;
                    storageService.initCurrentMusicAndPlay(storageService.mMediaScannerLocalMusic);
                    StorageService.this.mMediaScannerLocalMusic = null;
                }
            });
        }
        if (this.mediaScannerConnection.isConnected()) {
            return;
        }
        LogUtil.e(this.TAG, "initMediaScannerConnect:connect");
        this.mediaScannerConnection.connect();
    }

    private void registerBroadReceiver() {
        LogUtil.e(this.TAG, "registerBroadReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addDataScheme(ContentResolver.SCHEME_FILE);
        registerReceiver(this.scanReceiver, intentFilter);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.e(this.TAG, "onDestroy: ");
        unregisterReceiver(this.scanReceiver);
        MediaScannerConnection mediaScannerConnection = this.mediaScannerConnection;
        if (mediaScannerConnection != null) {
            mediaScannerConnection.disconnect();
            this.mediaScannerConnection = null;
        }
        this.storageManager.unregisterListener(this.storageEventListener);
        getContentResolver().unregisterContentObserver(this.mAudioObserver);
        super.onDestroy();
    }

    private void initContentObserver() {
        this.mAudioObserver = new ContentObserver(this.scanHandler) { // from class: com.byd.music.service.StorageService.9
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                LogUtil.e(StorageService.this.TAG, "initContentObserver onChange 01 url is null");
                onChange(z, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if ("content://media/external".equals(uri.toString())) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                StorageService storageService = StorageService.this;
                storageService.scanUriPath = storageService.getRealPath(uri);
                MusicPlayHelper.getInstance().isNeedUpdateData = true;
                StringBuilder sb = new StringBuilder();
                sb.append("initContentObserver onChange: ");
                sb.append(" url=");
                sb.append(uri != null ? uri.toString() : "null");
                sb.append(" path=");
                sb.append(StringSubUtil.subString(StorageService.this.scanUriPath));
                if (StorageService.this.scanUriPath == null) {
                    StorageService.this.isFileMoved = true;
                }
                sb.append(" isNeedUpdateData=");
                sb.append(MusicPlayHelper.getInstance().isNeedUpdateData);
                sb.append(" isNeedRemoveMsg:");
                sb.append(StorageService.this.isNeedRemoveMsg);
                sb.append(" isFileMoved=");
                sb.append(StorageService.this.isFileMoved);
                MusicPlayHelper.getInstance().checkCurrentPlayMusic(false);
                sb.append(" costTime=");
                sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                sb.append(" isMainThread=");
                sb.append(ThreadUtils.isMainThread());
                LogUtil.e(StorageService.this.TAG, sb.toString());
                if (!StorageService.this.isNeedRemoveMsg) {
                    StorageService.this.scanHandler.removeMessages(119);
                    StorageService.this.scanHandler.sendEmptyMessageDelayed(119, 1500L);
                } else {
                    StorageService.this.isNeedRemoveMsg = false;
                }
            }
        };
        getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, this.mAudioObserver);
    }

    public String getRealPath(Uri uri) {
        if (uri == null) {
            return null;
        }
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursorQuery = getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                return null;
            }
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            cursorQuery.close();
            return string;
        }
        if (uri.getScheme().compareTo(ContentResolver.SCHEME_FILE) != 0) {
            return null;
        }
        uri.toString();
        return uri.toString().replace("file://", "");
    }
}
