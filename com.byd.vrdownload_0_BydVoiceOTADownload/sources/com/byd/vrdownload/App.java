package com.byd.vrdownload;

import android.app.Application;
import android.util.Log;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrdownload.utils.LogUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

/* JADX INFO: loaded from: classes.dex */
public class App extends Application {
    public static App CONTEXT = null;
    private static final String TAG = "Download_App";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "onCreate: ");
        CONTEXT = this;
        CountryCodeUtil.getInstance().initCountryData(this);
        FileDownloadLog.NEED_LOG = false;
        FileDownloader.setupOnApplicationOnCreate(this).connectionCreator(new FileDownloadUrlConnection.Creator(new FileDownloadUrlConnection.Configuration().connectTimeout(15000))).idGenerator(new FileDownloadHelper.IdGenerator() { // from class: com.byd.vrdownload.App.1
            @Override // com.liulishuo.filedownloader.util.FileDownloadHelper.IdGenerator
            public int transOldId(int i, String str, String str2, boolean z) {
                return generateId(str, str2, z);
            }

            @Override // com.liulishuo.filedownloader.util.FileDownloadHelper.IdGenerator
            public int generateId(String str, String str2, boolean z) {
                Log.i(App.TAG, "generateId: path=" + str2 + ",pathAsDirectory=" + z);
                if (z) {
                    return FileDownloadUtils.md5(FileDownloadUtils.formatString("%sp%s@dir", str, str2)).hashCode();
                }
                return FileDownloadUtils.md5(str2).hashCode();
            }
        }).commit();
    }
}
