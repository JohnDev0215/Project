package com.bydux.api.common.webs.okhttp.download;

import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.bean.DownloadInfo;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadObservable implements ObservableOnSubscribe<DownloadInfo> {
    private DownloadInfo mDownloadInfo;
    private OkHttpClient mHttpClient;

    public DownloadObservable(OkHttpClient httpClient, DownloadInfo downloadInfo) {
        this.mHttpClient = null;
        this.mHttpClient = httpClient;
        this.mDownloadInfo = downloadInfo;
    }

    public void subscribe(final ObservableEmitter<DownloadInfo> emitter) throws Exception {
        String url = this.mDownloadInfo.getUrl();
        final String filePath = this.mDownloadInfo.getFilePath();
        long total = this.mDownloadInfo.getTotal();
        final long progress = this.mDownloadInfo.getProgress();
        BydLogHelper.logV("url=" + url + ", filePath=" + filePath + ", total=" + total + ", progress=" + progress);
        if (progress == total) {
            this.mDownloadInfo.setDownloadStatus(5);
            emitter.onNext(this.mDownloadInfo);
            return;
        }
        this.mDownloadInfo.setDownloadStatus(2);
        emitter.onNext(this.mDownloadInfo);
        Request.Builder builder = new Request.Builder();
        if (total > progress) {
            builder = builder.addHeader("RANGE", "bytes=" + progress + "-" + total);
        }
        Call callNewCall = this.mHttpClient.newCall(builder.url(url).build());
        this.mDownloadInfo.setCall(callNewCall);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.download.DownloadObservable.1
            public void onResponse(Call call, Response response) throws Throwable {
                FileOutputStream fileOutputStream;
                InputStream inputStreamByteStream;
                byte[] bArr;
                BydLogHelper.logV("response code=" + response.code());
                BydLogHelper.logV("filePath=" + filePath + ", progress=" + progress);
                File file = new File(filePath);
                InputStream inputStream = null;
                try {
                    inputStreamByteStream = response.body().byteStream();
                    try {
                        fileOutputStream = new FileOutputStream(file, true);
                        try {
                            bArr = new byte[2048];
                        } catch (IOException e) {
                            e = e;
                            inputStream = inputStreamByteStream;
                            try {
                                BydLogHelper.logE("error=" + e.getMessage());
                                DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                            } catch (Throwable th) {
                                th = th;
                                DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = inputStreamByteStream;
                            DownloadObservable.this.closeAll(inputStream, fileOutputStream);
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                }
                while (true) {
                    int i = inputStreamByteStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                    DownloadObservable.this.mDownloadInfo.setProgress(DownloadObservable.this.mDownloadInfo.getProgress() + ((long) i));
                    emitter.onNext(DownloadObservable.this.mDownloadInfo);
                    BydLogHelper.logV("complete");
                    emitter.onComplete();
                }
                fileOutputStream.flush();
                DownloadObservable.this.mDownloadInfo.setDownloadStatus(5);
                emitter.onNext(DownloadObservable.this.mDownloadInfo);
                DownloadObservable.this.closeAll(inputStreamByteStream, fileOutputStream);
                BydLogHelper.logV("complete");
                emitter.onComplete();
            }

            public void onFailure(Call call, IOException e) {
                BydLogHelper.logE("error=" + e.getMessage());
                DownloadObservable.this.mDownloadInfo.setDownloadStatus(6);
                emitter.onNext(DownloadObservable.this.mDownloadInfo);
                emitter.onComplete();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAll(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
