package com.bydux.api.common.webs.okhttp.upload;

import android.content.Context;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.BydUploadListener;
import com.bydux.api.common.webs.okhttp.bean.UploadInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class Uploader implements Observer<UploadInfo> {
    private Context mContext;
    private OkHttpClient mHttpClient;
    private BydUploadListener mUploadListener = null;
    private ArrayList<UploadInfo> mUploadList = new ArrayList<>();

    public Uploader(Context context, OkHttpClient okHttpClient) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mContext = context;
        this.mHttpClient = okHttpClient;
    }

    private void cancelUpload(UploadInfo uploadInfo) {
        if (uploadInfo.getCall() != null) {
            uploadInfo.getCall().cancel();
        }
        this.mUploadList.remove(uploadInfo);
        BydUploadListener bydUploadListener = this.mUploadListener;
        if (bydUploadListener != null) {
            bydUploadListener.onUploadChanged(uploadInfo);
        }
    }

    private void dealRepeatPath(UploadInfo uploadInfo) {
        for (int i = 0; i < this.mUploadList.size(); i++) {
            UploadInfo uploadInfo2 = this.mUploadList.get(i);
            if (uploadInfo2.getUrl().equals(uploadInfo.getUrl()) && uploadInfo2.getFilePath().equals(uploadInfo.getFilePath())) {
                BydLogHelper.logV("upload path repeat");
                cancel(uploadInfo2.getFn());
                return;
            }
        }
    }

    private UploadInfo getUploadInfo(int i) {
        for (UploadInfo uploadInfo : this.mUploadList) {
            if (uploadInfo.getFn() == i) {
                return uploadInfo;
            }
        }
        return null;
    }

    private boolean isRepeatFn(UploadInfo uploadInfo) {
        return getUploadInfo(uploadInfo.getFn()) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UploadInfo prepareUploadInfo(UploadInfo uploadInfo) {
        long length = new File(uploadInfo.getFilePath()).length();
        BydLogHelper.logV("total=" + length);
        uploadInfo.setTotal(length);
        return uploadInfo;
    }

    public void cancel(int i) {
        BydLogHelper.logV("fn=" + i);
        UploadInfo uploadInfo = getUploadInfo(i);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
            return;
        }
        uploadInfo.setProgress(0L);
        uploadInfo.setUploadStatus(4);
        cancelUpload(uploadInfo);
    }

    public void onComplete() {
    }

    public void onError(Throwable th) {
    }

    public void onSubscribe(Disposable disposable) {
    }

    public void pause(int i) {
        BydLogHelper.logV("fn=" + i);
        UploadInfo uploadInfo = getUploadInfo(i);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
        } else {
            uploadInfo.setUploadStatus(3);
            cancelUpload(uploadInfo);
        }
    }

    public void resume(int i) {
        BydLogHelper.logV("fn=" + i);
        UploadInfo uploadInfo = getUploadInfo(i);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
        } else {
            upload(uploadInfo);
        }
    }

    public void setUploadListener(BydUploadListener bydUploadListener) {
        this.mUploadListener = bydUploadListener;
    }

    public boolean upload(UploadInfo uploadInfo) {
        if (uploadInfo == null || TextUtils.isEmpty(uploadInfo.getFilePath())) {
            BydLogHelper.logE("upload info is null");
            return false;
        }
        BydLogHelper.logV("url=" + uploadInfo.getUrl());
        BydLogHelper.logV("file=" + uploadInfo.getFilePath());
        if (!BydNetHelper.isNetworkConnected(this.mContext)) {
            BydLogHelper.logV("current network disconnect");
            BydUploadListener bydUploadListener = this.mUploadListener;
            if (bydUploadListener != null) {
                bydUploadListener.onNetworkDisconnect(uploadInfo.getFn());
            }
            return false;
        }
        synchronized (this.mUploadList) {
            if (isRepeatFn(uploadInfo)) {
                BydLogHelper.logE("upload mission repeat");
                return false;
            }
            dealRepeatPath(uploadInfo);
            this.mUploadList.add(uploadInfo);
            uploadInfo.setUploadStatus(1);
            BydUploadListener bydUploadListener2 = this.mUploadListener;
            if (bydUploadListener2 != null) {
                bydUploadListener2.onUploadChanged(uploadInfo);
            }
            Observable.just(uploadInfo).map(new Function<UploadInfo, UploadInfo>() { // from class: com.bydux.api.common.webs.okhttp.upload.Uploader.2
                public UploadInfo apply(UploadInfo uploadInfo2) {
                    return Uploader.this.prepareUploadInfo(uploadInfo2);
                }
            }).flatMap(new Function<UploadInfo, ObservableSource<UploadInfo>>() { // from class: com.bydux.api.common.webs.okhttp.upload.Uploader.1
                public ObservableSource<UploadInfo> apply(UploadInfo uploadInfo2) {
                    return Observable.create(new UploadObservable(Uploader.this.mHttpClient, uploadInfo2));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this);
            return true;
        }
    }

    public void onNext(UploadInfo uploadInfo) {
        if (uploadInfo.getUploadStatus() == 5 || uploadInfo.getUploadStatus() == 6) {
            BydLogHelper.logV("upload stop status=" + uploadInfo.getUploadStatus());
            BydLogHelper.logV("upload stop total=" + uploadInfo.getTotal() + ", progress=" + uploadInfo.getProgress());
            synchronized (this.mUploadList) {
                this.mUploadList.remove(uploadInfo);
            }
        }
        BydUploadListener bydUploadListener = this.mUploadListener;
        if (bydUploadListener != null) {
            bydUploadListener.onUploadChanged(uploadInfo);
        }
    }
}
