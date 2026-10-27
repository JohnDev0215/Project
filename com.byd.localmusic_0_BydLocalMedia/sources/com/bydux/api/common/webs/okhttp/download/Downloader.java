package com.bydux.api.common.webs.okhttp.download;

import android.content.Context;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.BydDownloadListener;
import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
public class Downloader implements Observer<DownloadInfo> {
    private Context mContext;
    private OkHttpClient mHttpClient;
    private BydDownloadListener mDownloadListener = null;
    private ArrayList<DownloadInfo> mDownloadList = new ArrayList<>();

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable e) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable d) {
    }

    public Downloader(Context context, OkHttpClient httpClient) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mContext = context;
        this.mHttpClient = httpClient;
    }

    @Override // io.reactivex.Observer
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

    public void setDownloadListener(BydDownloadListener listener) {
        this.mDownloadListener = listener;
    }

    public boolean download(DownloadInfo downloadInfo, final boolean renewal) {
        if (downloadInfo == null || TextUtils.isEmpty(downloadInfo.getFilePath())) {
            BydLogHelper.logE("download info is null");
            return false;
        }
        BydLogHelper.logV("url=" + downloadInfo.getUrl() + ", renewal=" + renewal);
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
                @Override // io.reactivex.functions.Function
                public DownloadInfo apply(DownloadInfo info) {
                    return Downloader.this.prepareDownloadInfo(info, renewal);
                }
            }).flatMap(new Function<DownloadInfo, ObservableSource<DownloadInfo>>() { // from class: com.bydux.api.common.webs.okhttp.download.Downloader.1
                @Override // io.reactivex.functions.Function
                public ObservableSource<DownloadInfo> apply(DownloadInfo downloadInfo2) {
                    return Observable.create(new DownloadObservable(Downloader.this.mHttpClient, downloadInfo2));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this);
            return true;
        }
    }

    public void resume(int fn) {
        BydLogHelper.logV("fn=" + fn);
        DownloadInfo downloadInfo = getDownloadInfo(fn);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            download(downloadInfo, false);
        }
    }

    public void pause(int fn) {
        BydLogHelper.logV("fn=" + fn);
        DownloadInfo downloadInfo = getDownloadInfo(fn);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            downloadInfo.setDownloadStatus(3);
            cancelDownload(downloadInfo);
        }
    }

    public void cancel(int fn) {
        BydLogHelper.logV("fn=" + fn);
        DownloadInfo downloadInfo = getDownloadInfo(fn);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
            return;
        }
        downloadInfo.setProgress(0L);
        downloadInfo.setDownloadStatus(4);
        cancelDownload(downloadInfo);
        deleteFile(downloadInfo.getFilePath());
    }

    private void cancelDownload(DownloadInfo srcInfo) {
        if (srcInfo.getCall() != null) {
            srcInfo.getCall().cancel();
        }
        this.mDownloadList.remove(srcInfo);
        BydDownloadListener bydDownloadListener = this.mDownloadListener;
        if (bydDownloadListener != null) {
            bydDownloadListener.onDownloadChanged(srcInfo);
        }
    }

    private DownloadInfo getDownloadInfo(int fn) {
        for (DownloadInfo downloadInfo : this.mDownloadList) {
            if (downloadInfo.getFn() == fn) {
                return downloadInfo;
            }
        }
        return null;
    }

    private boolean isRepeatFn(DownloadInfo srcInfo) {
        return getDownloadInfo(srcInfo.getFn()) != null;
    }

    private boolean mkdir(DownloadInfo srcInfo) {
        String filePath = srcInfo.getFilePath();
        BydLogHelper.logV("file path=" + filePath);
        try {
            String[] strArrSplit = filePath.split("/");
            String str = "/";
            for (int i = 0; i < strArrSplit.length - 1; i++) {
                str = str + strArrSplit[i] + "/";
                File file = new File(str);
                if (!file.exists()) {
                    BydLogHelper.logV("folder path=" + str);
                    file.mkdir();
                }
            }
            return true;
        } catch (Exception e) {
            BydLogHelper.logE("error=" + e.getMessage());
            return false;
        }
    }

    private void dealRepeatPath(DownloadInfo srcInfo) {
        for (int i = 0; i < this.mDownloadList.size(); i++) {
            DownloadInfo downloadInfo = this.mDownloadList.get(i);
            if (downloadInfo.getFilePath().equals(srcInfo.getFilePath())) {
                BydLogHelper.logV("download path repeat");
                cancel(downloadInfo.getFn());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo prepareDownloadInfo(DownloadInfo srcInfo, boolean renewal) {
        long contentLength = getContentLength(srcInfo);
        BydLogHelper.logV("total=" + contentLength + ", renewal=" + renewal);
        srcInfo.setTotal(contentLength);
        if (renewal) {
            srcInfo.setProgress(0L);
        } else {
            File file = new File(srcInfo.getFilePath());
            if (file.exists()) {
                long length = file.length();
                BydLogHelper.logV("progress=" + length);
                if (length > contentLength) {
                    srcInfo.setProgress(0L);
                    deleteFile(srcInfo.getFilePath());
                } else {
                    srcInfo.setProgress(file.length());
                }
            }
        }
        return srcInfo;
    }

    private long getContentLength(DownloadInfo srcInfo) {
        try {
            Response responseExecute = this.mHttpClient.newCall(new Request.Builder().url(srcInfo.getUrl()).build()).execute();
            if (responseExecute == null || !responseExecute.isSuccessful()) {
                return 0L;
            }
            long jContentLength = responseExecute.body().contentLength();
            responseExecute.close();
            return jContentLength;
        } catch (IOException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    private boolean deleteFile(String filePath) {
        SecurityManager securityManager = new SecurityManager();
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        securityManager.checkDelete(file.toString());
        if (!file.isFile()) {
            return false;
        }
        try {
            file.delete();
            BydLogHelper.logV("delete success! file=" + filePath);
            return true;
        } catch (SecurityException e) {
            BydLogHelper.logE("error=" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
