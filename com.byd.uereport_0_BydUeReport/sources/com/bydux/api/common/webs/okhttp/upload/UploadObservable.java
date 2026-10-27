package com.bydux.api.common.webs.okhttp.upload;

import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.bean.UploadInfo;
import i7.c;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class UploadObservable implements ObservableOnSubscribe<UploadInfo> {
    private OkHttpClient mHttpClient;
    private UploadInfo mUploadInfo;

    public UploadObservable(OkHttpClient okHttpClient, UploadInfo uploadInfo) {
        this.mHttpClient = null;
        this.mHttpClient = okHttpClient;
        this.mUploadInfo = uploadInfo;
    }

    public void subscribe(final ObservableEmitter<UploadInfo> observableEmitter) throws Exception {
        String url = this.mUploadInfo.getUrl();
        final String filePath = this.mUploadInfo.getFilePath();
        long total = this.mUploadInfo.getTotal();
        final long progress = this.mUploadInfo.getProgress();
        Map<String, String> paramMap = this.mUploadInfo.getParamMap();
        BydLogHelper.logV("url=" + url + ", filePath=" + filePath + ", total=" + total + ", progress=" + progress);
        this.mUploadInfo.setUploadStatus(2);
        observableEmitter.onNext(this.mUploadInfo);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (paramMap != null && paramMap.size() > 0) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        builder.addFormDataPart("file", filePath.substring(filePath.lastIndexOf("/") + 1), new RequestBody() { // from class: com.bydux.api.common.webs.okhttp.upload.UploadObservable.1
            public MediaType contentType() {
                return null;
            }

            public void writeTo(c cVar) throws IOException {
                BydLogHelper.logV("filePath=" + filePath + ", progress=" + progress);
                RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
                long j8 = progress;
                if (j8 > 0) {
                    randomAccessFile.seek(j8);
                }
                byte[] bArr = new byte[2048];
                while (true) {
                    try {
                        try {
                            int i8 = randomAccessFile.read(bArr);
                            if (i8 == -1) {
                                break;
                            }
                            cVar.b(bArr, 0, i8);
                            UploadObservable.this.mUploadInfo.setProgress(UploadObservable.this.mUploadInfo.getProgress() + ((long) i8));
                            observableEmitter.onNext(UploadObservable.this.mUploadInfo);
                        } catch (IOException e8) {
                            BydLogHelper.logE("error=" + e8.getMessage());
                        }
                        BydLogHelper.logV("complete");
                        observableEmitter.onComplete();
                    } finally {
                        randomAccessFile.close();
                    }
                }
                UploadObservable.this.mUploadInfo.setUploadStatus(5);
                observableEmitter.onNext(UploadObservable.this.mUploadInfo);
                BydLogHelper.logV("complete");
                observableEmitter.onComplete();
            }
        });
        MultipartBody multipartBodyBuild = builder.build();
        Request.Builder builder2 = new Request.Builder();
        if (total > progress) {
            builder2 = builder2.addHeader("RANGE", "bytes=" + progress + "-" + total);
        }
        Call callNewCall = this.mHttpClient.newCall(builder2.url(url).post(multipartBodyBuild).build());
        this.mUploadInfo.setCall(callNewCall);
        callNewCall.enqueue(new Callback() { // from class: com.bydux.api.common.webs.okhttp.upload.UploadObservable.2
            public void onFailure(Call call, IOException iOException) {
                BydLogHelper.logE("error=" + iOException.getMessage());
                observableEmitter.onComplete();
            }

            public void onResponse(Call call, Response response) {
                BydLogHelper.logV("response code=" + response.code());
            }
        });
    }
}
