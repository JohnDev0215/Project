package com.byd.vrdownload;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.ArraySet;
import androidx.core.content.ContextCompat;
import com.byd.vrdownload.bean.ArbitrationProfile;
import com.byd.vrdownload.bean.BeanTransfer;
import com.byd.vrdownload.dao.LanguageTaskDatabase;
import com.byd.vrdownload.exception.SSLCertificateValidationException;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.i.IDownloadService;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.IProfile;
import com.byd.vrdownload.model.LanguageOTA;
import com.byd.vrdownload.model.LanguageOTAResult;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrdownload.model.PkgVersion;
import com.byd.vrdownload.model.ProfileDownloadTask;
import com.byd.vrdownload.model.Result;
import com.byd.vrdownload.model.SuccessResult;
import com.byd.vrdownload.net.NetRequest;
import com.byd.vrdownload.utils.FileUtils;
import com.byd.vrdownload.utils.LanguageHelper;
import com.byd.vrdownload.utils.LogUtils;
import com.byd.vrdownload.utils.ParamUtils;
import com.byd.vrdownload.utils.SharedPreferencesUtils;
import com.byd.vrdownload.utils.ThreadPool;
import com.google.gson.Gson;
import com.liulishuo.filedownloader.FileDownloader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class DownloadServiceHandler extends IDownloadService.Stub implements IDownloadServiceHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "DownloadServiceHandler";
    private static DownloadServiceHandler instance;
    private final LanguageTaskDatabase cacheHelper;
    CallbackHandler callbackHandler;
    private final RemoteCallbackList<IDownloadCallback> callbackList;
    String currentApkVersion;
    TaskDownloadListener downloadListener;
    SparseArray<LanguageUpdate> downloadRecord;
    HashMap<String, LanguageUpdate> downloadRecordMap;
    HashMap<String, PkgVersion> keyLanguageInstalled;
    HashMap<String, LanguageUpdate> languageToUpdate;
    Context mContext;
    HashMap<String, LanguageUpdate> temHashMap;

    private interface copyProfileInter {
        void copyFailed(Exception exc);

        void copySucc();
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public String getDownloadVersionType() {
        return "release";
    }

    @Override // com.byd.vrdownload.IDownloadServiceHandler
    public IBinder onBind(Intent intent) {
        return this;
    }

    @Override // com.byd.vrdownload.IDownloadServiceHandler
    public void onDestroy() {
    }

    @Override // com.byd.vrdownload.IDownloadServiceHandler
    public void onStartCommand(Intent intent, int i, int i2) {
    }

    public static DownloadServiceHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DownloadServiceHandler(context);
        }
        return instance;
    }

    private DownloadServiceHandler(Context context) {
        LanguageTaskDatabase languageTaskDatabase = new LanguageTaskDatabase();
        this.cacheHelper = languageTaskDatabase;
        RemoteCallbackList<IDownloadCallback> remoteCallbackList = new RemoteCallbackList<>();
        this.callbackList = remoteCallbackList;
        this.callbackHandler = new CallbackHandler(remoteCallbackList);
        this.downloadRecord = languageTaskDatabase.getAllIdMap();
        this.temHashMap = new HashMap<>();
        this.downloadRecordMap = languageTaskDatabase.getAllMap();
        this.downloadListener = new TaskDownloadListener(this.callbackHandler, this.downloadRecord);
        this.languageToUpdate = new HashMap<>();
        this.keyLanguageInstalled = new HashMap<>();
        this.mContext = context;
        LogUtils.i(TAG, "DownloadServiceHandler: " + this.downloadRecord.size());
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void registerCallback(IDownloadCallback iDownloadCallback, String str) throws RemoteException {
        LogUtils.i(TAG, "registerCallback:IDownloadCallback= " + iDownloadCallback + ",identify=" + str);
        this.callbackList.register(iDownloadCallback, str);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void unregisterCallback(IDownloadCallback iDownloadCallback) throws RemoteException {
        LogUtils.i(TAG, "unregisterCallback:IDownloadCallback= " + iDownloadCallback);
        this.callbackList.unregister(iDownloadCallback);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void getLanguageOTA(final GetLanguagesUpdate getLanguagesUpdate) throws RemoteException {
        LogUtils.i(TAG, "getLanguageOTA: 进行下载任务的语言列表=" + Arrays.toString(this.downloadRecordMap.keySet().toArray()));
        this.currentApkVersion = getLanguagesUpdate.getApkVersion();
        ThreadPool.execute(new Runnable() { // from class: com.byd.vrdownload.DownloadServiceHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m9x92e2b36d(getLanguagesUpdate);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getLanguageOTA$0$com-byd-vrdownload-DownloadServiceHandler, reason: not valid java name */
    /* synthetic */ void m9x92e2b36d(GetLanguagesUpdate getLanguagesUpdate) {
        LanguageOTAResult languageOTAResultFail;
        for (int i = 0; i < getLanguagesUpdate.getPkgVersion().size(); i++) {
            PkgVersion pkgVersion = getLanguagesUpdate.getPkgVersion().get(i);
            this.keyLanguageInstalled.put(pkgVersion.getLanguage(), pkgVersion);
        }
        Result<List<LanguageOTA>> languageOTA = NetRequest.getLanguageOTA(getLanguagesUpdate);
        ArraySet arraySet = new ArraySet();
        if (languageOTA.isSuccess()) {
            for (int i2 = 0; i2 < languageOTA.getOrNull().size(); i2++) {
                LanguageUpdate languageUpdate = BeanTransfer.getLanguageUpdate(languageOTA.getOrNull().get(i2), this.keyLanguageInstalled, this.currentApkVersion);
                LogUtils.i(TAG, "getLanguageOTA: language=" + languageOTA.getOrNull().get(i2).getLanguage());
                LanguageUpdate languageUpdate2 = this.downloadRecordMap.get(languageOTA.getOrNull().get(i2).getLanguage());
                if (languageUpdate2 != null && !Objects.equals(languageUpdate.getPkgKeyToDownload(), languageUpdate2.getDownloadingPkgKey())) {
                    LogUtils.i(TAG, "getLanguageOTA:oldUpdate != null key=" + languageUpdate2.getDownloadingPkgKey());
                    LogUtils.i(TAG, "getLanguageOTA:languageUpdate key= " + languageUpdate.getPkgKeyToDownload());
                    languageUpdate = LanguageHelper.compareVersion(languageUpdate2, languageUpdate);
                    LogUtils.i(TAG, "getLanguageOTA: " + languageUpdate.getPkgKeyToDownload());
                    if (!Objects.equals(languageUpdate2.getDownloadingPkgKey(), languageUpdate.getPkgKeyToDownload())) {
                        deleteTask(languageUpdate2);
                        LogUtils.e(TAG, "getLanguageOTA:new version coming languageUpdate=" + languageUpdate);
                    }
                }
                languageUpdate.setDownloadingPath(FileUtils.getFilePath(languageUpdate));
                this.languageToUpdate.put(languageOTA.getOrNull().get(i2).getLanguage(), languageUpdate);
                arraySet.remove(languageUpdate);
                arraySet.add(languageUpdate);
            }
            LogUtils.i(TAG, "getLanguageOTA:result list size= " + arraySet.size());
            languageOTAResultFail = LanguageOTAResult.success(new ArrayList(arraySet));
        } else {
            LogUtils.i(TAG, "getLanguageOTA: exception " + new Gson().toJson(languageOTA.getExceptionMessage().getLocalizedMessage()));
            if (languageOTA.getExceptionMessage() != null && languageOTA.getExceptionMessage().getLocalizedMessage() != null && (languageOTA.getExceptionMessage().getLocalizedMessage().startsWith("Unacceptable certificate:") || languageOTA.getExceptionMessage().getLocalizedMessage().startsWith("Chain validation failed"))) {
                languageOTAResultFail = LanguageOTAResult.fail(new SSLCertificateValidationException(languageOTA.getExceptionMessage().getLocalizedMessage()));
            } else {
                languageOTAResultFail = LanguageOTAResult.fail(languageOTA.exceptionOrNull());
            }
        }
        this.callbackHandler.getLanguageOTA(languageOTAResultFail);
    }

    private LanguageUpdate checkLanguage(LanguageUpdate languageUpdate, HashMap<String, LanguageUpdate> map) {
        if (map.containsKey(languageUpdate.getLanguage())) {
            LanguageUpdate languageUpdate2 = map.get(languageUpdate.getLanguage());
            LogUtils.e(TAG, "checkLanguage: languageUpdate language=" + languageUpdate.getLanguage() + ",this pkgVersion=" + languageUpdate.getLatestPkgVersion() + "lastValue pkgversion=" + languageUpdate2.getLatestPkgVersion());
            if (!LanguageHelper.compareVersion(languageUpdate2.getLatestPkgVersion().replace("V.", ""), languageUpdate.getLatestPkgVersion().replace("V.", ""))) {
                LogUtils.i(TAG, "checkLanguage: use last value=" + languageUpdate2.getLatestPkgVersion());
                languageUpdate = languageUpdate2;
            }
            LogUtils.e(TAG, "checkLanguage: result language=" + languageUpdate.getLanguage() + ",pkg version==" + languageUpdate.getLatestPkgVersion());
        }
        return languageUpdate;
    }

    public AidlResult deleteTask(LanguageUpdate languageUpdate) {
        this.cacheHelper.remove(languageUpdate.getId());
        this.downloadRecordMap.remove(this.downloadRecord.get(languageUpdate.getId()).getLanguage());
        this.downloadRecord.remove(languageUpdate.getId());
        FileDownloader.getImpl().getStatus(languageUpdate.getId(), languageUpdate.getDownloadingPath());
        boolean zClear = FileDownloader.getImpl().clear(languageUpdate.getId(), languageUpdate.getDownloadingPath());
        LogUtils.i(TAG, "cancel: id=" + languageUpdate.getId() + ",language=" + languageUpdate.getLanguage() + ",clear success=" + zClear);
        if (!zClear) {
            return AidlResult.fail(new Exception("文件目录有误，删除失败"));
        }
        return AidlResult.success(new SuccessResult("suceess"), SuccessResult.class);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void removeFile(String str) throws RemoteException {
        FileUtils.delete(str);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void downloadLanguagePkg(final String str) throws RemoteException {
        LogUtils.i(TAG, "downloadLanguagePkg: language=" + str);
        final LanguageUpdate languageUpdate = this.languageToUpdate.get(str);
        if (languageUpdate == null) {
            this.callbackHandler.onStartDownloadResult(AidlResult.fail(new Exception("可更新语言中不包括" + str)));
            LogUtils.i(TAG, "downloadLanguagePkg: languageToUpdate size=" + this.languageToUpdate.size());
            return;
        }
        if (this.mContext == null) {
            LogUtils.i(TAG, "downloadLanguagePkg mContext == null");
            return;
        }
        if (this.temHashMap.containsKey(languageUpdate.getLanguage())) {
            LogUtils.i(TAG, "downloadRecordMap.containsKey(" + languageUpdate.getLanguage() + ")");
            return;
        }
        this.temHashMap.put(languageUpdate.getLanguage(), languageUpdate);
        File file = new File(FileUtils.getFilePath(languageUpdate));
        if (file.exists() && FileUtils.checkMd5(file, languageUpdate.getLatestPkgMd5())) {
            LogUtils.e(TAG, "downloadLanguagePkg: 文件已存在且是最新的");
        }
        ThreadPool.execute(new Runnable() { // from class: com.byd.vrdownload.DownloadServiceHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m7x47c92f92(languageUpdate, str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$downloadLanguagePkg$1$com-byd-vrdownload-DownloadServiceHandler, reason: not valid java name */
    /* synthetic */ void m7x47c92f92(LanguageUpdate languageUpdate, String str) {
        Result<String> downloadLink = NetRequest.getDownloadLink(languageUpdate.getPkgKeyToDownload());
        LogUtils.i(TAG, "downloadLanguagePkg:url= " + downloadLink.getOrNull());
        if (this.temHashMap.containsKey(languageUpdate.getLanguage())) {
            this.temHashMap.remove(languageUpdate.getLanguage());
        }
        if (!downloadLink.isSuccess()) {
            LogUtils.e(TAG, "downloadLanguagePkg: " + downloadLink.exceptionOrNull());
            this.callbackHandler.onStartDownloadResult(AidlResult.fail(downloadLink.exceptionOrNull()));
            return;
        }
        languageUpdate.setLatestPkgUrl(downloadLink.getOrNull());
        languageUpdate.setDownloadingPkgUrl(downloadLink.getOrNull());
        languageUpdate.startDownload(languageUpdate.getPkgKeyToDownload());
        int iStart = FileDownloader.getImpl().create(downloadLink.getOrNull()).setPath(FileUtils.getFilePath(languageUpdate), false).setCallbackProgressTimes(100).setListener(this.downloadListener).start();
        languageUpdate.setId(iStart);
        LogUtils.i(TAG, "downloadLanguagePkg: id=" + iStart);
        this.cacheHelper.insert(languageUpdate);
        this.downloadRecord.put(iStart, this.languageToUpdate.get(str));
        this.downloadRecordMap.put(languageUpdate.getLanguage(), languageUpdate);
        LogUtils.i(TAG, "downloadLanguagePkg: languageToUpdate=" + languageUpdate);
        LogUtils.i(TAG, "downloadLanguagePkg: isDifferential Update" + languageUpdate.isDifferentialUpdate());
        LogUtils.i(TAG, "downloadLanguagePkg: " + this.downloadRecordMap.size());
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public AidlResult pause(int i) throws RemoteException {
        LogUtils.i(TAG, "pause: id=" + i);
        if (FileDownloader.getImpl().pause(i) > 0) {
            return AidlResult.success(new SuccessResult("success"), SuccessResult.class);
        }
        return AidlResult.fail(new Exception("任务不存在"));
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void resume(final int i) throws RemoteException {
        ThreadPool.execute(new Runnable() { // from class: com.byd.vrdownload.DownloadServiceHandler$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m10lambda$resume$2$combydvrdownloadDownloadServiceHandler(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$resume$2$com-byd-vrdownload-DownloadServiceHandler, reason: not valid java name */
    /* synthetic */ void m10lambda$resume$2$combydvrdownloadDownloadServiceHandler(int i) {
        LanguageUpdate languageUpdateFind = this.cacheHelper.find(i);
        LogUtils.i(TAG, "resume: id=" + i + ",model=" + languageUpdateFind);
        String downloadingPkgUrl = languageUpdateFind.getDownloadingPkgUrl();
        if (TextUtils.isEmpty(downloadingPkgUrl)) {
            downloadingPkgUrl = languageUpdateFind.getLatestPkgUrl();
        }
        if (downloadingPkgUrl != null) {
            long j = Long.parseLong(ParamUtils.getUrlParams(downloadingPkgUrl, "Expires"));
            LogUtils.i(TAG, "resume download: urlTime=" + j);
            if (j * 1000 < System.currentTimeMillis()) {
                Result<String> downloadLink = NetRequest.getDownloadLink(languageUpdateFind.getDownloadingPkgKey());
                if (!downloadLink.isSuccess()) {
                    LogUtils.e(TAG, "resume: get new Url error=" + downloadLink.exceptionOrNull());
                    return;
                }
                downloadingPkgUrl = downloadLink.getOrNull();
                if (TextUtils.isEmpty(downloadingPkgUrl)) {
                    LogUtils.e(TAG, "resume: url is null return");
                    return;
                } else {
                    languageUpdateFind.setDownloadingPkgUrl(downloadingPkgUrl);
                    this.cacheHelper.update(languageUpdateFind);
                }
            }
            LogUtils.i(TAG, "run: resume id=" + FileDownloader.getImpl().create(downloadingPkgUrl).setPath(languageUpdateFind.getDownloadingPath(), false).setCallbackProgressTimes(100).setListener(this.downloadListener).start());
            return;
        }
        try {
            downloadLanguagePkg(languageUpdateFind.getLanguage());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public AidlResult cancel(int i) throws RemoteException {
        LogUtils.i(TAG, "cancel: id=" + i);
        LanguageUpdate languageUpdateFind = this.cacheHelper.find(i);
        if (languageUpdateFind == null) {
            return AidlResult.fail(new Exception("这个id对应的任务不存在"));
        }
        return deleteTask(languageUpdateFind);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public void pauseAllTasks() throws RemoteException {
        FileDownloader.getImpl().pauseAll();
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public long getSofar(int i) throws RemoteException {
        return FileDownloader.getImpl().getSoFar(i);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public long getTotal(int i) throws RemoteException {
        return FileDownloader.getImpl().getTotal(i);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public byte getStatus(int i) throws RemoteException {
        return FileDownloader.getImpl().getStatus(i, this.cacheHelper.find(i).getDownloadingPath());
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public List<DownloadTask> getAllTasks() throws RemoteException {
        List<LanguageUpdate> all = this.cacheHelper.getAll();
        LogUtils.i(TAG, "getAllTasks:download history size= " + all.size());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < all.size(); i++) {
            LanguageUpdate languageUpdate = all.get(i);
            boolean z = FileUtils.isFileExists(FileUtils.getFilePath(languageUpdate)) || FileUtils.isFileExists(FileUtils.getFilePathTemp(languageUpdate));
            boolean zContainsKey = this.downloadRecordMap.containsKey(languageUpdate.getLanguage());
            if (z || zContainsKey) {
                LogUtils.i(TAG, "getAllTasks:this file is valid, filepath=" + languageUpdate.getDownloadingPath());
                arrayList.add(languageUpdate);
            } else {
                LogUtils.i(TAG, "getAllTasks: remove task because file is not exist ,path=" + languageUpdate.getDownloadingPath());
                FileDownloader.getImpl().clear(languageUpdate.getId(), languageUpdate.getDownloadingPath());
                this.cacheHelper.remove(languageUpdate.getId());
                this.downloadRecord.remove(languageUpdate.getId());
                this.downloadRecordMap.remove(languageUpdate.getLanguage());
            }
        }
        return BeanTransfer.getDownloadTaskList(arrayList);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public boolean isIdle() throws RemoteException {
        return this.downloadRecordMap.size() == 0;
    }

    public boolean checkPermission() throws RemoteException {
        if (Build.VERSION.SDK_INT >= 30) {
            return Environment.isExternalStorageManager();
        }
        String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= 2) {
                z = true;
                break;
            }
            String str = strArr[i];
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(this.mContext, str);
            Log.d(TAG, "checkPermission=" + String.format("%s - %d", str, Integer.valueOf(iCheckSelfPermission)));
            if (iCheckSelfPermission != 0) {
                break;
            }
            i++;
        }
        Log.i(TAG, "checkPermission: hasPermission=" + z);
        return z;
    }

    public void requestPermission(String str) {
        if (this.mContext == null) {
            LogUtils.i(TAG, "requestPermission mContext == null");
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.mContext, MainActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        intent.putExtra("source", str);
        intent.putExtra("requestPermission", true);
        this.mContext.startActivity(intent);
    }

    @Override // com.byd.vrdownload.IDownloadServiceHandler
    public void getAttributeProfile() throws RemoteException {
        if (this.mContext == null) {
            LogUtils.i(TAG, "getAttributeProfile mContext == null");
        } else {
            downloadOrCopy();
        }
    }

    @Override // com.byd.vrdownload.IDownloadServiceHandler
    public void notifyPermissionResult(boolean z) {
        Log.e(TAG, "notifyPermissionResult:" + z);
        if (z) {
            downloadOrCopy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProfilePath(String str) {
        File file = new File(FileUtils.getProfileTempPath(str));
        File file2 = new File(FileUtils.getProfilePath());
        LogUtils.e(TAG, "destFiles exists:" + file2.exists());
        if (file2.exists() && file2.listFiles().length > 0) {
            file2.listFiles()[0].delete();
            LogUtils.e(TAG, "delete destFiles" + file2.getPath());
        }
        try {
            Thread.sleep(2500L);
        } catch (Exception unused) {
        }
        File file3 = new File(FileUtils.getProfilePath() + "/" + str);
        file2.mkdir();
        file.renameTo(file3);
    }

    @Override // com.byd.vrdownload.i.IDownloadService
    public String getDownloadVersion() {
        return "1.9.1-" + "r".toUpperCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadProfile() {
        Context context = this.mContext;
        if (context == null) {
            LogUtils.i(TAG, "downloadProfile mContext == null");
        } else {
            final String str = (String) SharedPreferencesUtils.getParam(context, SharedPreferencesUtils.KEY_PROFILE_VERSION, "");
            ThreadPool.execute(new Runnable() { // from class: com.byd.vrdownload.DownloadServiceHandler$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m8x5b0e5969(str);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$downloadProfile$3$com-byd-vrdownload-DownloadServiceHandler, reason: not valid java name */
    /* synthetic */ void m8x5b0e5969(String str) {
        Result<ArbitrationProfile> arbitrationProfile = NetRequest.getArbitrationProfile(str);
        LogUtils.i(TAG, "getAttributeProfile:url= " + arbitrationProfile);
        if (!arbitrationProfile.isSuccess()) {
            LogUtils.e(TAG, "getAttributeProfile: " + arbitrationProfile.exceptionOrNull());
            return;
        }
        ArbitrationProfile orNull = arbitrationProfile.getOrNull();
        final String version = orNull.getVersion();
        String profileMd5 = orNull.getProfileMd5();
        final String profileKey = orNull.getProfileKey();
        if (TextUtils.isEmpty(profileKey) || TextUtils.isEmpty(version) || TextUtils.isEmpty(profileMd5)) {
            LogUtils.e(TAG, "getAttributeProfile null profileKey: " + profileKey + ",ver:" + version + ",md5:" + profileMd5);
            return;
        }
        File file = new File(FileUtils.getProfilePath());
        if (file.exists() && file.listFiles().length > 0 && version.equals(str)) {
            LogUtils.e(TAG, "getAttributeProfile equal localVer: " + str + ",ver:" + version);
            return;
        }
        Result<String> downloadLink = NetRequest.getDownloadLink(profileKey);
        if (!downloadLink.isSuccess()) {
            LogUtils.e(TAG, "getAttributeProfileUrl: " + arbitrationProfile.exceptionOrNull());
        } else {
            LogUtils.i(TAG, "downloadProfile start: id=" + FileDownloader.getImpl().create(downloadLink.getOrNull()).setPath(FileUtils.getProfileTempPath(profileKey), false).setCallbackProgressTimes(100).setListener(new TaskProfilekDownloadListener(new IProfile() { // from class: com.byd.vrdownload.DownloadServiceHandler.1
                @Override // com.byd.vrdownload.model.IProfile
                public void onDownload(ProfileDownloadTask profileDownloadTask) {
                }

                @Override // com.byd.vrdownload.model.IProfile
                public void onProgress(ProfileDownloadTask profileDownloadTask, long j, long j2) {
                }

                @Override // com.byd.vrdownload.model.IProfile
                public void onDownloadComplete(ProfileDownloadTask profileDownloadTask) {
                    Log.e(DownloadServiceHandler.TAG, "onDownloadComplete");
                    if (DownloadServiceHandler.this.mContext != null) {
                        DownloadServiceHandler.this.updateProfilePath(profileKey);
                        SharedPreferencesUtils.setParam(DownloadServiceHandler.this.mContext, SharedPreferencesUtils.KEY_PROFILE_VERSION, version);
                    } else {
                        LogUtils.i(DownloadServiceHandler.TAG, "onDownloadComplete mContext == null");
                    }
                }
            }, profileKey, profileMd5)).start());
        }
    }

    private void copyDefalutProfile(copyProfileInter copyprofileinter) {
        try {
            InputStream inputStreamOpen = App.CONTEXT.getAssets().open("default_edge2cloud_arbitration_rule_v3.json");
            File file = new File(FileUtils.getProfilePath());
            if (!file.exists() || file.listFiles().length == 0) {
                Log.d(TAG, "file do not exists");
                file.mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(file + File.separator + "default_edge2cloud_arbitration_rule_v3.json");
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStreamOpen.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.close();
                inputStreamOpen.close();
                if (copyprofileinter != null) {
                    copyprofileinter.copySucc();
                }
            }
        } catch (Exception e) {
            if (copyprofileinter != null) {
                copyprofileinter.copyFailed(e);
            }
        }
    }

    private void downloadOrCopy() {
        File file = new File(FileUtils.getProfilePath());
        if (!file.exists() || file.listFiles().length == 0) {
            copyDefalutProfile(new copyProfileInter() { // from class: com.byd.vrdownload.DownloadServiceHandler.2
                @Override // com.byd.vrdownload.DownloadServiceHandler.copyProfileInter
                public void copyFailed(Exception exc) {
                    LogUtils.e(DownloadServiceHandler.TAG, "copyDefalutProfile Exception:" + exc.getMessage());
                }

                @Override // com.byd.vrdownload.DownloadServiceHandler.copyProfileInter
                public void copySucc() {
                    LogUtils.e(DownloadServiceHandler.TAG, "copyDefalutProfile success");
                    DownloadServiceHandler.this.downloadProfile();
                }
            });
        } else {
            LogUtils.e(TAG, "downloadOrCopy downloadProfile");
            downloadProfile();
        }
    }
}
