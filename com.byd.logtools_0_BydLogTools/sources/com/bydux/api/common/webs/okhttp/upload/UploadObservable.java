package com.bydux.api.common.webs.okhttp.upload;

import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.bean.UploadInfo;
import e.a.f;
import e.a.g;
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
import okio.BufferedSink;

/* JADX INFO: loaded from: classes.dex */
public class UploadObservable implements g<UploadInfo> {
    private OkHttpClient mHttpClient;
    private UploadInfo mUploadInfo;

    public UploadObservable(OkHttpClient okHttpClient, UploadInfo uploadInfo) {
        this.mHttpClient = null;
        this.mHttpClient = okHttpClient;
        this.mUploadInfo = uploadInfo;
    }

    @Override // e.a.g
    public void subscribe(final f<UploadInfo> fVar) throws Exception {
        String url = this.mUploadInfo.getUrl();
        final String filePath = this.mUploadInfo.getFilePath();
        long total = this.mUploadInfo.getTotal();
        final long progress = this.mUploadInfo.getProgress();
        Map<String, String> paramMap = this.mUploadInfo.getParamMap();
        BydLogHelper.logV("url=" + url + ", filePath=" + filePath + ", total=" + total + ", progress=" + progress);
        this.mUploadInfo.setUploadStatus(2);
        fVar.onNext(this.mUploadInfo);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (paramMap != null && paramMap.size() > 0) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        builder.addFormDataPart("file", filePath.substring(filePath.lastIndexOf("/") + 1), new RequestBody() { // from class: com.bydux.api.common.webs.okhttp.upload.UploadObservable.1
            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return null;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                BydLogHelper.logV("filePath=" + filePath + ", progress=" + progress);
                RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
                long j = progress;
                if (j > 0) {
                    randomAccessFile.seek(j);
                }
                byte[] bArr = new byte[2048];
                while (true) {
                    try {
                        try {
                            int i = randomAccessFile.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            bufferedSink.write(bArr, 0, i);
                            UploadObservable.this.mUploadInfo.setProgress(UploadObservable.this.mUploadInfo.getProgress() + ((long) i));
                            fVar.onNext(UploadObservable.this.mUploadInfo);
                        } catch (IOException e2) {
                            BydLogHelper.logE("error=" + e2.getMessage());
                        }
                        BydLogHelper.logV("complete");
                        fVar.onComplete();
                    } finally {
                        randomAccessFile.close();
                    }
                }
                UploadObservable.this.mUploadInfo.setUploadStatus(5);
                fVar.onNext(UploadObservable.this.mUploadInfo);
                BydLogHelper.logV("complete");
                fVar.onComplete();
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
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                BydLogHelper.logE("error=" + iOException.getMessage());
                fVar.onComplete();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                BydLogHelper.logV("response code=" + response.code());
            }
        });
    }
}
