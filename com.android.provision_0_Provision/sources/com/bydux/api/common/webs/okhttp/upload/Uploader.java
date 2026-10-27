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

/* JADX INFO: loaded from: classes.dex */
public class Uploader implements Observer<UploadInfo> {
    private Context mContext;
    private OkHttpClient mHttpClient;
    private BydUploadListener mUploadListener = null;
    private ArrayList<UploadInfo> mUploadList = new ArrayList<>();

    public void onComplete() {
    }

    public void onError(Throwable e) {
    }

    public void onSubscribe(Disposable d) {
    }

    public Uploader(Context context, OkHttpClient httpClient) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mContext = context;
        this.mHttpClient = httpClient;
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

    public void setUploadListener(BydUploadListener listener) {
        this.mUploadListener = listener;
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
                public UploadInfo apply(UploadInfo info) {
                    return Uploader.this.prepareUploadInfo(info);
                }
            }).flatMap(new Function<UploadInfo, ObservableSource<UploadInfo>>() { // from class: com.bydux.api.common.webs.okhttp.upload.Uploader.1
                public ObservableSource<UploadInfo> apply(UploadInfo uploadInfo2) {
                    return Observable.create(new UploadObservable(Uploader.this.mHttpClient, uploadInfo2));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this);
            return true;
        }
    }

    public void resume(int fn) {
        BydLogHelper.logV("fn=" + fn);
        UploadInfo uploadInfo = getUploadInfo(fn);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
        } else {
            upload(uploadInfo);
        }
    }

    public void pause(int fn) {
        BydLogHelper.logV("fn=" + fn);
        UploadInfo uploadInfo = getUploadInfo(fn);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
        } else {
            uploadInfo.setUploadStatus(3);
            cancelUpload(uploadInfo);
        }
    }

    public void cancel(int fn) {
        BydLogHelper.logV("fn=" + fn);
        UploadInfo uploadInfo = getUploadInfo(fn);
        if (uploadInfo == null) {
            BydLogHelper.logE("upload info is null");
            return;
        }
        uploadInfo.setProgress(0L);
        uploadInfo.setUploadStatus(4);
        cancelUpload(uploadInfo);
    }

    private void cancelUpload(UploadInfo srcInfo) {
        if (srcInfo.getCall() != null) {
            srcInfo.getCall().cancel();
        }
        this.mUploadList.remove(srcInfo);
        BydUploadListener bydUploadListener = this.mUploadListener;
        if (bydUploadListener != null) {
            bydUploadListener.onUploadChanged(srcInfo);
        }
    }

    private UploadInfo getUploadInfo(int fn) {
        for (UploadInfo uploadInfo : this.mUploadList) {
            if (uploadInfo.getFn() == fn) {
                return uploadInfo;
            }
        }
        return null;
    }

    private boolean isRepeatFn(UploadInfo srcInfo) {
        return getUploadInfo(srcInfo.getFn()) != null;
    }

    private void dealRepeatPath(UploadInfo srcInfo) {
        for (int i = 0; i < this.mUploadList.size(); i++) {
            UploadInfo uploadInfo = this.mUploadList.get(i);
            if (uploadInfo.getUrl().equals(srcInfo.getUrl()) && uploadInfo.getFilePath().equals(srcInfo.getFilePath())) {
                BydLogHelper.logV("upload path repeat");
                cancel(uploadInfo.getFn());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UploadInfo prepareUploadInfo(UploadInfo srcInfo) {
        long length = new File(srcInfo.getFilePath()).length();
        BydLogHelper.logV("total=" + length);
        srcInfo.setTotal(length);
        return srcInfo;
    }
}
