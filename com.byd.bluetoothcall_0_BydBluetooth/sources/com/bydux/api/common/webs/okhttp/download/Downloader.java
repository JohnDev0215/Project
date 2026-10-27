package com.bydux.api.common.webs.okhttp.download;

import android.content.Context;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.net.BydNetHelper;
import com.bydux.api.common.webs.okhttp.BydDownloadListener;
import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;
import d.a.e;
import d.a.h;
import d.a.i;
import d.a.l.c.a;
import d.a.m.f;
import io.reactivex.disposables.b;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public class Downloader implements i<DownloadInfo> {
    private Context mContext;
    private OkHttpClient mHttpClient;
    private BydDownloadListener mDownloadListener = null;
    private ArrayList<DownloadInfo> mDownloadList = new ArrayList<>();

    /* JADX INFO: renamed from: com.bydux.api.common.webs.okhttp.download.Downloader$1 */
    class AnonymousClass1 implements f<DownloadInfo, h<DownloadInfo>> {
        AnonymousClass1() {
        }

        @Override // d.a.m.f
        public h<DownloadInfo> apply(DownloadInfo downloadInfo) {
            return e.c(new DownloadObservable(Downloader.this.mHttpClient, downloadInfo));
        }
    }

    /* JADX INFO: renamed from: com.bydux.api.common.webs.okhttp.download.Downloader$2 */
    class AnonymousClass2 implements f<DownloadInfo, DownloadInfo> {
        final /* synthetic */ boolean val$renewal;

        AnonymousClass2(boolean z) {
            z = z;
        }

        @Override // d.a.m.f
        public DownloadInfo apply(DownloadInfo downloadInfo) {
            return Downloader.this.prepareDownloadInfo(downloadInfo, z);
        }
    }

    public Downloader(Context context, OkHttpClient okHttpClient) {
        this.mContext = null;
        this.mHttpClient = null;
        this.mContext = context;
        this.mHttpClient = okHttpClient;
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
        for (int i = 0; i < this.mDownloadList.size(); i++) {
            DownloadInfo downloadInfo2 = this.mDownloadList.get(i);
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
            Response responseExecute = this.mHttpClient.newCall(new Request.Builder().url(downloadInfo.getUrl()).build()).execute();
            if (responseExecute == null || !responseExecute.isSuccessful()) {
                return 0L;
            }
            long jContentLength = responseExecute.body().contentLength();
            responseExecute.close();
            return jContentLength;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private DownloadInfo getDownloadInfo(int i) {
        for (DownloadInfo downloadInfo : this.mDownloadList) {
            if (downloadInfo.getFn() == i) {
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
            for (int i = 0; i < strArrSplit.length - 1; i++) {
                str = str + strArrSplit[i] + "/";
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

    public void cancel(int i) {
        BydLogHelper.logV("fn=" + i);
        DownloadInfo downloadInfo = getDownloadInfo(i);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
            return;
        }
        downloadInfo.setProgress(0L);
        downloadInfo.setDownloadStatus(4);
        cancelDownload(downloadInfo);
        deleteFile(downloadInfo.getFilePath());
    }

    public boolean download(DownloadInfo downloadInfo, boolean z) {
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
            e.k(downloadInfo).l(new f<DownloadInfo, DownloadInfo>() { // from class: com.bydux.api.common.webs.okhttp.download.Downloader.2
                final /* synthetic */ boolean val$renewal;

                AnonymousClass2(boolean z2) {
                    z = z2;
                }

                @Override // d.a.m.f
                public DownloadInfo apply(DownloadInfo downloadInfo2) {
                    return Downloader.this.prepareDownloadInfo(downloadInfo2, z);
                }
            }).g(new f<DownloadInfo, h<DownloadInfo>>() { // from class: com.bydux.api.common.webs.okhttp.download.Downloader.1
                AnonymousClass1() {
                }

                @Override // d.a.m.f
                public h<DownloadInfo> apply(DownloadInfo downloadInfo2) {
                    return e.c(new DownloadObservable(Downloader.this.mHttpClient, downloadInfo2));
                }
            }).m(a.a()).p(d.a.q.a.b()).a(this);
            return true;
        }
    }

    @Override // d.a.i
    public void onComplete() {
    }

    @Override // d.a.i
    public void onError(Throwable th) {
    }

    @Override // d.a.i
    public void onSubscribe(b bVar) {
    }

    public void pause(int i) {
        BydLogHelper.logV("fn=" + i);
        DownloadInfo downloadInfo = getDownloadInfo(i);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            downloadInfo.setDownloadStatus(3);
            cancelDownload(downloadInfo);
        }
    }

    public void resume(int i) {
        BydLogHelper.logV("fn=" + i);
        DownloadInfo downloadInfo = getDownloadInfo(i);
        if (downloadInfo == null) {
            BydLogHelper.logE("download info is null");
        } else {
            download(downloadInfo, false);
        }
    }

    public void setDownloadListener(BydDownloadListener bydDownloadListener) {
        this.mDownloadListener = bydDownloadListener;
    }

    @Override // d.a.i
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
