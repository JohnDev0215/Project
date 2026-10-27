package com.byd.auto_emanual.update;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.OnUpdateListener;
import com.byd.auto_emanual.update.AppStoreHelper;
import com.byd.auto_emanual.util.FileUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class UpdateCheckUtil {
    private static final int MSG_DOWNLOAD_FAIL = 2;
    private static final int MSG_DOWNLOAD_OK = 1;
    private static final int MSG_DOWNLOAD_PROGRESS = 6;
    private static final int MSG_HAS_NEW_VERSION = 3;
    private static final int MSG_NO_NEW_VERSION = 4;
    private static final int MSG_UPDATE_CHECK_ERROR = 5;
    private static String SALT = "42BAFD3AC0CD38C9A46C2FED1B8747A4";
    private static String SIGNENDER = "4d0d34315d8c1e041ee2616b84c35839ca192320941";
    private static String SIGNHEADER = "7c8c2f1fe02e7aa404e6d087940d946a7f517d";
    private static final String TAG = "UpdateCheckUtil";
    private static volatile UpdateCheckUtil instance;
    private AppStoreHelper appStoreHelper;
    private final AppStoreHelper.OnAppStoreUpDateCallback appStoreUpDateCallback = new AppStoreHelper.OnAppStoreUpDateCallback() { // from class: com.byd.auto_emanual.update.UpdateCheckUtil.1
        @Override // com.byd.auto_emanual.update.AppStoreHelper.OnAppStoreUpDateCallback
        public void OnAppStoreUpDateInfo(boolean z, NewVersionInfo newVersionInfo) {
            if (newVersionInfo != null && newVersionInfo.getVersionCode() > Utils.getVersionCode(UpdateCheckUtil.this.context)) {
                UpdateCheckUtil.this.mHandler.obtainMessage(3, z ? 1 : 0, 0, newVersionInfo).sendToTarget();
            } else {
                if (z) {
                    return;
                }
                UpdateCheckUtil.this.mHandler.sendEmptyMessage(4);
            }
        }

        @Override // com.byd.auto_emanual.update.AppStoreHelper.OnAppStoreUpDateCallback
        public void OnRemoteServiceIsNull(boolean checkInBackground) {
            sendErrorMessage(checkInBackground, 5, R.string.check_update_error);
        }

        @Override // com.byd.auto_emanual.update.AppStoreHelper.OnAppStoreUpDateCallback
        public void OnError(boolean checkInBackground, Exception e) {
            sendErrorMessage(checkInBackground, 5, R.string.data_analysis);
        }

        private void sendErrorMessage(boolean checkInBackground, int what, int resId) {
            if (checkInBackground) {
                return;
            }
            UpdateCheckUtil.this.mHandler.obtainMessage(what, UpdateCheckUtil.this.getString(resId)).sendToTarget();
        }
    };
    private Context context;
    private Future downloadFuture;
    private ExecutorService executorService;
    private Handler mHandler;
    private OnUpdateListener onUpdateListener;

    public void release() {
    }

    private UpdateCheckUtil() {
    }

    public static UpdateCheckUtil getInstance() {
        synchronized (UpdateCheckUtil.class) {
            if (instance == null) {
                instance = new UpdateCheckUtil();
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.executorService = Executors.newCachedThreadPool();
        this.context = context.getApplicationContext();
        MyHandler myHandler = new MyHandler();
        this.mHandler = myHandler;
        myHandler.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.update.-$$Lambda$UpdateCheckUtil$MJGSNWiJBTN7Ap5JJT4ngycapEY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$init$0$UpdateCheckUtil();
            }
        }, 10000L);
    }

    public /* synthetic */ void lambda$init$0$UpdateCheckUtil() {
        this.appStoreHelper = new AppStoreHelper(this.context, this.appStoreUpDateCallback);
    }

    public void startDownload(NewVersionInfo info) {
        Future future = this.downloadFuture;
        if (future == null || future.isDone() || this.downloadFuture.isCancelled()) {
            this.downloadFuture = this.executorService.submit(new DownloadRunnable(info));
        }
    }

    public boolean isDownloading() {
        Future future = this.downloadFuture;
        return (future == null || future.isDone() || this.downloadFuture.isCancelled()) ? false : true;
    }

    public void startCheckUpdate(boolean checkInBackground) {
        this.appStoreHelper.check(checkInBackground);
    }

    class DownloadRunnable implements Runnable {
        private NewVersionInfo info;
        private String url;

        DownloadRunnable(NewVersionInfo info) {
            this.info = info;
            this.url = info.getDownloadUrl();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
                if (httpURLConnection.getResponseCode() != 200) {
                    UpdateCheckUtil.this.mHandler.obtainMessage(2, this.info.getUpgradeStyle(), 0, null).sendToTarget();
                } else {
                    int contentLength = httpURLConnection.getContentLength();
                    UpdateCheckUtil updateCheckUtil = UpdateCheckUtil.this;
                    String savePath = updateCheckUtil.getSavePath(updateCheckUtil.context, this.info.getVersionCode(), this.info.getVersionName(), contentLength);
                    if (savePath == null) {
                        MLog.e(UpdateCheckUtil.TAG, "无法保存文件");
                        UpdateCheckUtil.this.mHandler.obtainMessage(2, this.info.getUpgradeStyle(), 0, null).sendToTarget();
                        httpURLConnection.disconnect();
                        return;
                    }
                    File file = new File(savePath);
                    if (file.exists() && file.length() == contentLength) {
                        UpdateCheckUtil.this.mHandler.obtainMessage(1, this.info.getUpgradeStyle(), 0, savePath).sendToTarget();
                        httpURLConnection.disconnect();
                        return;
                    }
                    File[] fileArrListFiles = file.getParentFile().listFiles();
                    if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                        for (File file2 : fileArrListFiles) {
                            file2.delete();
                        }
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(savePath);
                    byte[] bArr = new byte[2048];
                    int i = 0;
                    while (true) {
                        int i2 = inputStream.read(bArr);
                        if (i2 == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i2);
                        i += i2;
                        UpdateCheckUtil.this.mHandler.obtainMessage(6, Integer.valueOf((i * 100) / contentLength)).sendToTarget();
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    UpdateCheckUtil.this.mHandler.obtainMessage(1, this.info.getUpgradeStyle(), 0, savePath).sendToTarget();
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                UpdateCheckUtil.this.mHandler.obtainMessage(2, this.info.getUpgradeStyle(), 0, null).sendToTarget();
            }
        }
    }

    private void sendMessage(int what, int resId, boolean checkInBackground) {
        if (checkInBackground) {
            return;
        }
        this.mHandler.obtainMessage(what, getString(resId)).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getString(int resId) {
        return this.context.getResources().getString(resId);
    }

    private static class MyHandler extends Handler {
        private WeakReference<UpdateCheckUtil> util;

        private MyHandler(UpdateCheckUtil u) {
            this.util = new WeakReference<>(u);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (this.util.get() == null) {
            }
            switch (msg.what) {
                case 1:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onDownloadOk((String) msg.obj, msg.arg1);
                    }
                    break;
                case 2:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onDownloadFail(msg.arg1);
                    }
                    break;
                case 3:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onNewVersion((NewVersionInfo) msg.obj, msg.arg1 == 1);
                    }
                    break;
                case 4:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onNewVersion(null, false);
                    }
                    break;
                case 5:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onCheckUpdateError((String) msg.obj);
                    }
                    break;
                case 6:
                    if (this.util.get().onUpdateListener != null) {
                        this.util.get().onUpdateListener.onDownloadProgress(((Integer) msg.obj).intValue());
                    }
                    break;
            }
        }
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSavePath(Context context, int versionCode, String versionName, int fileLen) {
        String folderPath = getFolderPath(context, "PlayDiLink");
        if (!checkAndCreateFolder(folderPath)) {
            return null;
        }
        String str = folderPath + File.separator + versionCode + "_" + versionName + "_" + fileLen + ".apk";
        MLog.d(TAG, "getSavePath = " + str);
        return str;
    }

    private boolean checkAndCreateFolder(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    private String getFolderPath(Context context, String dirName) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return FileUtil.getCacheDir(context).getPath();
        }
        return context.getFilesDir().getAbsolutePath();
    }
}
