package com.bydux.api.common.webs.okhttp.download;

import android.content.Context;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.BydDownloadListener;
import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;
import f.u;
import f.w;
import f.y;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Downloader implements Observer<DownloadInfo> {
    private Context mContext;
    private u mHttpClient;
    private BydDownloadListener mDownloadListener = null;
    private ArrayList<DownloadInfo> mDownloadList = new ArrayList<>();

    public Downloader(Context context, u uVar) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mContext = context;
        this.mHttpClient = uVar;
    }

    private void cancelDownload(DownloadInfo downloadInfo) {
        if (downloadInfo.getCall() != null) {
            downloadInfo.getCall().cancel();
        }
        this.mDownloadList.remove(downloadInfo);
        BydDownloadListener bydDownloadListener = this.mDownloadListener;
        if (bydDownloadListener != null) {
            bydDownloadListener.onDownloadChanged(downloadInfo);
        }
    }

    private void dealRepeatPath(DownloadInfo downloadInfo) {
        for (int i2 = 0; i2 < this.mDownloadList.size(); i2++) {
            DownloadInfo downloadInfo2 = this.mDownloadList.get(i2);
            if (downloadInfo2.getFilePath().equals(downloadInfo.getFilePath())) {
                BydLogHelper.logV("download path repeat");
                cancel(downloadInfo2.getFn());
                return;
            }
        }
    }

    private boolean deleteFile(String str) {
        SecurityManager securityManager = new SecurityManager();
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        securityManager.checkDelete(file.toString());
        if (!file.isFile()) {
            return false;
        }
        try {
            file.delete();
            BydLogHelper.logV("delete success! file=" + str);
            return true;
        } catch (SecurityException e2) {
            BydLogHelper.logE("error=" + e2.getMessage());
            e2.printStackTrace();
            return false;
        }
    }

    private long getContentLength(DownloadInfo downloadInfo) {
        try {
            y yVarZ = this.mHttpClient.o(new w.a().i(downloadInfo.getUrl()).b()).z();
            if (yVarZ == null || !yVarZ.T()) {
                return 0L;
            }
            long jO = yVarZ.w().O();
            yVarZ.close();
            return jO;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private DownloadInfo getDownloadInfo(int i2) {
        for (DownloadInfo downloadInfo : this.mDownloadList) {
            if (downloadInfo.getFn() == i2) {
                return downloadInfo;
            }
        }
        return null;
    }

    private boolean isRepeatFn(DownloadInfo downloadInfo) {
        return getDownloadInfo(downloadInfo.getFn()) != null;
    }

    private boolean mkdir(DownloadInfo downloadInfo) {
        String filePath = downloadInfo.getFilePath();
        BydLogHelper.logV("file path=" + filePath);
        try {
            String[] strArrSplit = filePath.split("/");
            String str = "/";
            for (int i2 = 0; i2 < strArrSplit.length - 1; i2++) {
                str = str + strArrSplit[i2] + "/";
                File file = new File(str);
                if (!file.exists()) {
                    BydLogHelper.logV("folder path=" + str);
                    file.mkdir();
                }
            }
            return true;
        } catch (Exception e2) {
            BydLogHelper.logE("error=" + e2.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo prepareDownloadInfo(DownloadInfo downloadInfo, boolean z) {
        long contentLength = getContentLength(downloadInfo);
        BydLogHelper.logV("total=" + contentLength + ", renewal=" + z);
        downloadInfo.setTotal(contentLength);
        if (z) {
            downloadInfo.setProgress(0L);
        } else {
            File file = new File(downloadInfo.getFilePath());
            if (file.exists()) {
                long length = file.length();
                BydLogHelper.logV("progress=" + length);
                if (length > contentLength) {
                    downloadInfo.setProgress(0L);
                    deleteFile(downloadInfo.getFilePath());
                } else {
                    downloadInfo.setProgress(file.length());
                }
            }
        }
        return downloadInfo;
    }

    public void cancel(int i2) {
        BydLogHelper.logV("fn=" + i2);
        DownloadInfo downloadInfo = getDownloadInfo(i2);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
            return;
        }
        downloadInfo.setProgress(0L);
        downloadInfo.setDownloadStatus(4);
        cancelDownload(downloadInfo);
        deleteFile(downloadInfo.getFilePath());
    }

    public boolean download(DownloadInfo downloadInfo, final boolean z) {
        if (downloadInfo == null || TextUtils.isEmpty(downloadInfo.getFilePath())) {
            BydLogHelper.logE("download info is null");
            return false;
        }
        BydLogHelper.logV("url=" + downloadInfo.getUrl() + ", renewal=" + z);
        StringBuilder sb = new StringBuilder();
        sb.append("file=");
        sb.append(downloadInfo.getFilePath());
        BydLogHelper.logV(sb.toString());
        if (!BydNetHelper.isNetworkConnected(this.mContext)) {
            BydLogHelper.logV("current network disconnect");
            BydDownloadListener bydDownloadListener = this.mDownloadListener;
            if (bydDownloadListener != null) {
                bydDownloadListener.onNetworkDisconnect(downloadInfo.getFn());
            }
            return false;
        }
        synchronized (this.mDownloadList) {
            if (isRepeatFn(downloadInfo)) {
                BydLogHelper.logE("download mission repeat");
                return false;
            }
            if (!mkdir(downloadInfo)) {
                BydLogHelper.logE("mkdir fail");
                return false;
            }
            dealRepeatPath(downloadInfo);
            this.mDownloadList.add(downloadInfo);
            downloadInfo.setDownloadStatus(1);
            BydDownloadListener bydDownloadListener2 = this.mDownloadListener;
            if (bydDownloadListener2 != null) {
                bydDownloadListener2.onDownloadChanged(downloadInfo);
            }
            Observable.just(downloadInfo).map(new Function<DownloadInfo, DownloadInfo>() { // from class: com.bydux.api.common.webs.okhttp.download.Downloader.2
                public DownloadInfo apply(DownloadInfo downloadInfo2) {
                    return Downloader.this.prepareDownloadInfo(downloadInfo2, z);
                }
            }).flatMap(new Function<DownloadInfo, ObservableSource<DownloadInfo>>() { // from class: com.bydux.api.common.webs.okhttp.download.Downloader.1
                public ObservableSource<DownloadInfo> apply(DownloadInfo downloadInfo2) {
                    return Observable.create(new DownloadObservable(Downloader.this.mHttpClient, downloadInfo2));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this);
            return true;
        }
    }

    public void onComplete() {
    }

    public void onError(Throwable th) {
    }

    public void onSubscribe(Disposable disposable) {
    }

    public void pause(int i2) {
        BydLogHelper.logV("fn=" + i2);
        DownloadInfo downloadInfo = getDownloadInfo(i2);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            downloadInfo.setDownloadStatus(3);
            cancelDownload(downloadInfo);
        }
    }

    public void resume(int i2) {
        BydLogHelper.logV("fn=" + i2);
        DownloadInfo downloadInfo = getDownloadInfo(i2);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            download(downloadInfo, false);
        }
    }

    public void setDownloadListener(BydDownloadListener bydDownloadListener) {
        this.mDownloadListener = bydDownloadListener;
    }

    public void onNext(DownloadInfo downloadInfo) {
        if (downloadInfo.getDownloadStatus() == 5 || downloadInfo.getDownloadStatus() == 6) {
            BydLogHelper.logV("download stop status=" + downloadInfo.getDownloadStatus());
            BydLogHelper.logV("download stop total=" + downloadInfo.getTotal() + ", progress=" + downloadInfo.getProgress());
            synchronized (this.mDownloadList) {
                this.mDownloadList.remove(downloadInfo);
            }
        }
        BydDownloadListener bydDownloadListener = this.mDownloadListener;
        if (bydDownloadListener != null) {
            bydDownloadListener.onDownloadChanged(downloadInfo);
        }
    }
}
