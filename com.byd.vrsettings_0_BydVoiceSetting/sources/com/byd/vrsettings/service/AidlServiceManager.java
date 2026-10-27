package com.byd.vrsettings.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.i.IDownloadService;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.utils.Tools;
import com.chad.library.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AidlServiceManager {
    private static AidlServiceManager instance;
    private IDownloadCallback downloadCallback;
    private IDownloadService downloadService;
    private IBinder iBinder;
    private ServiceCallBack<Boolean> serviceCallBack;
    private String TAG = getClass().getSimpleName();
    private String packageName = "com.byd.vrdownload";
    private String action = "com.byd.vrdownload.downloadservice";
    private String action2 = "com.byd.vrdownload.MainActivity";
    private boolean serviceConnect = false;
    private Handler mHandler = new Handler(new Handler.Callback() { // from class: com.byd.vrsettings.service.AidlServiceManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    AidlServiceManager.this.resumePauseList();
                }
            } else if (NetUtils.isNetAvailable(BydVoiceSettingApplication.getContext())) {
                AidlServiceManager.this.mHandler.sendEmptyMessageDelayed(1, 5000L);
            } else {
                AidlServiceManager.this.mHandler.sendEmptyMessageDelayed(0, 5000L);
            }
            return false;
        }
    });
    ServiceConnection connection = new ServiceConnection() { // from class: com.byd.vrsettings.service.AidlServiceManager.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.i(AidlServiceManager.this.TAG, "onServiceConnected");
            AidlServiceManager.this.iBinder = iBinder;
            AidlServiceManager.this.downloadCallback = IDownloadCallback.Stub.asInterface(iBinder);
            AidlServiceManager.this.downloadService = IDownloadService.Stub.asInterface(iBinder);
            AidlServiceManager.this.serviceConnect = true;
            if (AidlServiceManager.this.serviceCallBack != null) {
                AidlServiceManager.this.serviceCallBack.next(true);
            }
            try {
                LogUtils.i(AidlServiceManager.this.TAG, "download version：" + AidlServiceManager.getInstance().getDownloadAppVersion());
            } catch (RemoteException e) {
                LogUtils.i(AidlServiceManager.this.TAG, "download version：" + e.getMessage());
            }
            AidlServiceManager.this.mHandler.sendEmptyMessageDelayed(0, 1500L);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.i(AidlServiceManager.this.TAG, "onServiceDisconnected");
            AidlServiceManager.this.downloadCallback = null;
            AidlServiceManager.this.downloadService = null;
            AidlServiceManager.this.serviceConnect = false;
            if (AidlServiceManager.this.serviceCallBack != null) {
                AidlServiceManager.this.serviceCallBack.next(false);
            }
        }
    };

    public interface ServiceCallBack<T> {
        void next(T t);
    }

    public static AidlServiceManager getInstance() {
        if (instance == null) {
            synchronized (AidlServiceManager.class) {
                if (instance == null) {
                    instance = new AidlServiceManager();
                }
            }
        }
        return instance;
    }

    public void setCallBack(ServiceCallBack<Boolean> serviceCallBack) {
        this.serviceCallBack = serviceCallBack;
    }

    public IBinder getIBiner() {
        return this.iBinder;
    }

    public boolean isServiceConnect() {
        return this.serviceConnect;
    }

    public void requestLanguageList(GetLanguagesUpdate getLanguagesUpdate) {
        try {
            if (this.downloadService != null) {
                RLog.i(this.TAG, "requestLanguageList");
                this.downloadService.getLanguageOTA(getLanguagesUpdate);
            }
        } catch (RemoteException e) {
            RLog.e(this.TAG, "requestLanguageList-->" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registDownLoadCallBack(IDownloadCallback iDownloadCallback, String str) {
        try {
            if (this.downloadService != null) {
                LogUtils.i(this.TAG, "注册DownLoadCallBack..");
                this.downloadService.registerCallback(iDownloadCallback, str);
            }
        } catch (RemoteException e) {
            RLog.e(this.TAG, "registDownLoadCallBack-->" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void unRegistDownLoadCallBack(IDownloadCallback iDownloadCallback) {
        try {
            IDownloadService iDownloadService = this.downloadService;
            if (iDownloadService != null) {
                iDownloadService.unregisterCallback(iDownloadCallback);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void downLoadPkg(String str) {
        LogUtils.i(this.TAG, "downLoadPkg...");
        removePauseList(str);
        try {
            IDownloadService iDownloadService = this.downloadService;
            if (iDownloadService != null) {
                iDownloadService.downloadLanguagePkg(str);
            }
        } catch (RemoteException e) {
            RLog.e(this.TAG, "downLoadPkg-->" + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getProgress(int i) {
        long sofar;
        long j;
        IDownloadService iDownloadService;
        long total = 0;
        try {
            iDownloadService = this.downloadService;
        } catch (RemoteException e) {
            e = e;
            sofar = 0;
        }
        if (iDownloadService != null) {
            sofar = iDownloadService.getSofar(i);
            try {
                total = this.downloadService.getTotal(i);
            } catch (RemoteException e2) {
                e = e2;
                LogUtils.e(this.TAG, "getProgress-->" + e.getMessage());
                e.printStackTrace();
            }
            long j2 = total;
            total = sofar;
            j = j2;
            return Tools.percentInt(total, j);
        }
        j = 0;
        return Tools.percentInt(total, j);
        LogUtils.e(this.TAG, "getProgress-->" + e.getMessage());
        e.printStackTrace();
        long j22 = total;
        total = sofar;
        j = j22;
        return Tools.percentInt(total, j);
    }

    public AidlResult pause(LanguageBean languageBean) {
        addPauseList(languageBean.getLanguageRemote());
        if (this.downloadService != null) {
            try {
                RLog.i(this.TAG, "pause...taskid===" + languageBean.getTaskId());
                return this.downloadService.pause(languageBean.getTaskId());
            } catch (RemoteException e) {
                LogUtils.e(this.TAG, "pause-->" + e.getMessage());
                e.printStackTrace();
            }
        }
        return AidlResult.fail(new Exception());
    }

    public void resume(LanguageBean languageBean) {
        resume(languageBean.getTaskId(), languageBean.getLanguageRemote());
    }

    public void resume(int i, LanguageUpdate languageUpdate) {
        removePauseList(languageUpdate);
        if (this.downloadService != null) {
            try {
                LogUtils.i(this.TAG, "resume...taskid==" + i);
                this.downloadService.resume(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public AidlResult cancel(LanguageBean languageBean) {
        LogUtils.i(this.TAG, "cancel...taskid==" + languageBean.getTaskId());
        removePauseList(languageBean.getLanguageRemote());
        IDownloadService iDownloadService = this.downloadService;
        if (iDownloadService != null) {
            try {
                return iDownloadService.cancel(languageBean.getTaskId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return AidlResult.fail(new Exception());
    }

    public AidlResult cancel(String str) {
        LogUtils.i(this.TAG, "cancel...language==" + str);
        removePauseList(str);
        if (this.downloadService != null) {
            try {
                int iFindTask = findTask(str);
                if (iFindTask != -1) {
                    LogUtils.i(this.TAG, "cancel...taskId:-1");
                    return this.downloadService.cancel(iFindTask);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return AidlResult.fail(new Exception());
    }

    public int findTask(String str) {
        for (DownloadTask downloadTask : getAllTask()) {
            if (str.equals(downloadTask.getLanguageUpdate().getLanguage())) {
                return downloadTask.getId();
            }
        }
        return -1;
    }

    public List<DownloadTask> getAllTask() {
        if (this.downloadService == null) {
            return null;
        }
        LogUtils.i(this.TAG, "getAllTask");
        try {
            return this.downloadService.getAllTasks();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startDownloadActivity(Context context) {
        if (this.serviceConnect) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.packageName, this.action2));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public void bindAidlServer(Context context) {
        if (this.serviceConnect) {
            return;
        }
        LogUtils.i(this.TAG, "bindAidlServer");
        Intent intent = new Intent();
        intent.setAction(this.action);
        intent.setPackage(this.packageName);
        context.bindService(intent, this.connection, 1);
    }

    public void unBindService(Context context) {
        ServiceConnection serviceConnection = this.connection;
        if (serviceConnection != null) {
            context.unbindService(serviceConnection);
            this.serviceConnect = false;
        }
    }

    public static List<String> getPauseList() {
        String str = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_PAUSE_LIST, BuildConfig.FLAVOR);
        if (!TextUtils.isEmpty(str)) {
            return (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: com.byd.vrsettings.service.AidlServiceManager.3
            }.getType());
        }
        return new ArrayList();
    }

    public void resumePauseList() {
        List<DownloadTask> allTask = getInstance().getAllTask();
        if (allTask == null) {
            LogUtils.i(this.TAG, "resumePauseList allTask == null");
            return;
        }
        List<String> pauseList = getPauseList();
        LogUtils.e(this.TAG, "pauseList:" + new Gson().toJson(pauseList));
        LogUtils.i(this.TAG, "allTask:" + allTask.toString());
        for (DownloadTask downloadTask : allTask) {
            LogUtils.i(this.TAG, "task.getLanguageUpdate():" + downloadTask.getLanguageUpdate());
            LogUtils.i(this.TAG, "非手动暂停:" + (!pauseList.contains(downloadTask.getLanguageUpdate().getLanguage())));
            if (downloadTask.getStatus() == -2 && downloadTask.getLanguageUpdate() != null && !pauseList.contains(downloadTask.getLanguageUpdate().getLanguage())) {
                LogUtils.i(this.TAG, "AidlServiceManager.getInstance().resume");
                getInstance().resume(downloadTask.getId(), downloadTask.getLanguageUpdate());
            }
        }
    }

    public static void addPauseList(LanguageUpdate languageUpdate) {
        List arrayList;
        if (languageUpdate != null) {
            String language = languageUpdate.getLanguage();
            String str = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_PAUSE_LIST, BuildConfig.FLAVOR);
            if (TextUtils.isEmpty(str)) {
                arrayList = new ArrayList();
                arrayList.add(language);
            } else {
                arrayList = (List) new Gson().fromJson(str, new TypeToken<List<String>>() { // from class: com.byd.vrsettings.service.AidlServiceManager.4
                }.getType());
                if (!arrayList.contains(language)) {
                    arrayList.add(language);
                }
            }
            SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_PAUSE_LIST, arrayList.toString());
        }
    }

    public static void removePauseList(LanguageUpdate languageUpdate) {
        if (languageUpdate != null) {
            removePauseList(languageUpdate.getLanguage());
        }
    }

    public static void removePauseList(String str) {
        String str2 = (String) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_PAUSE_LIST, BuildConfig.FLAVOR);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        List list = (List) new Gson().fromJson(str2, new TypeToken<List<String>>() { // from class: com.byd.vrsettings.service.AidlServiceManager.5
        }.getType());
        if (list.contains(str)) {
            list.remove(str);
        }
        SharedPreferencesUtils.setParam(BydVoiceSettingApplication.getContext(), SharedPreferencesUtils.SP_KEY_PAUSE_LIST, list.toString());
    }

    public String getDownloadAppVersion() throws RemoteException {
        if (this.downloadService != null) {
            return "V." + this.downloadService.getDownloadVersion();
        }
        return BydVoiceSettingApplication.getContext().getString(R.string.text_smart_voice_version);
    }
}
