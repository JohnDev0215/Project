package com.bydux.api.common.webs.okhttp.download;

import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;
import f.e;
import f.f;
import f.u;
import f.w;
import f.y;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class DownloadObservable implements ObservableOnSubscribe<DownloadInfo> {
    private DownloadInfo mDownloadInfo;
    private u mHttpClient;

    public DownloadObservable(u uVar, DownloadInfo downloadInfo) {
        this.mHttpClient = null;
        this.mHttpClient = uVar;
        this.mDownloadInfo = downloadInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAll(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void subscribe(final ObservableEmitter<DownloadInfo> observableEmitter) throws Exception {
        String url = this.mDownloadInfo.getUrl();
        final String filePath = this.mDownloadInfo.getFilePath();
        long total = this.mDownloadInfo.getTotal();
        final long progress = this.mDownloadInfo.getProgress();
        BydLogHelper.logV("url=" + url + ", filePath=" + filePath + ", total=" + total + ", progress=" + progress);
        if (progress == total) {
            this.mDownloadInfo.setDownloadStatus(5);
            observableEmitter.onNext(this.mDownloadInfo);
            return;
        }
        this.mDownloadInfo.setDownloadStatus(2);
        observableEmitter.onNext(this.mDownloadInfo);
        w.a aVar = new w.a();
        if (total > progress) {
            aVar = aVar.a("RANGE", "bytes=" + progress + "-" + total);
        }
        e eVarO = this.mHttpClient.o(aVar.i(url).b());
        this.mDownloadInfo.setCall(eVarO);
        eVarO.w(new f() { // from class: com.bydux.api.common.webs.okhttp.download.DownloadObservable.1
            @Override // f.f
            public void onFailure(e eVar, IOException iOException) {
                BydLogHelper.logE("error=" + iOException.getMessage());
                DownloadObservable.this.mDownloadInfo.setDownloadStatus(6);
                observableEmitter.onNext(DownloadObservable.this.mDownloadInfo);
                observableEmitter.onComplete();
            }

            @Override // f.f
            public void onResponse(e eVar, y yVar) throws Throwable {
                FileOutputStream fileOutputStream;
                InputStream inputStreamW;
                byte[] bArr;
                BydLogHelper.logV("response code=" + yVar.O());
                BydLogHelper.logV("filePath=" + filePath + ", progress=" + progress);
                File file = new File(filePath);
                InputStream inputStream = null;
                try {
                    inputStreamW = yVar.w().w();
                    try {
                        fileOutputStream = new FileOutputStream(file, true);
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
                try {
                    bArr = new byte[2048];
                } catch (IOException e4) {
                    e = e4;
                    inputStream = inputStreamW;
                    try {
                        BydLogHelper.logE("error=" + e.getMessage());
                        DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = inputStreamW;
                    DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                    throw th;
                }
                while (true) {
                    int i2 = inputStreamW.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                    DownloadObservable.this.mDownloadInfo.setProgress(DownloadObservable.this.mDownloadInfo.getProgress() + ((long) i2));
                    observableEmitter.onNext(DownloadObservable.this.mDownloadInfo);
                    BydLogHelper.logV("complete");
                    observableEmitter.onComplete();
                }
                fileOutputStream.flush();
                DownloadObservable.this.mDownloadInfo.setDownloadStatus(5);
                observableEmitter.onNext(DownloadObservable.this.mDownloadInfo);
                DownloadObservable.this.closeAll(inputStreamW, fileOutputStream);
                BydLogHelper.logV("complete");
                observableEmitter.onComplete();
            }
        });
    }
}
