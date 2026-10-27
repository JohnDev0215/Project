package com.bydux.api.common.webs.okhttp.upload;

import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.bean.UploadInfo;
import f.e;
import f.f;
import f.s;
import f.t;
import f.u;
import f.w;
import f.x;
import f.y;
import g.d;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UploadObservable implements ObservableOnSubscribe<UploadInfo> {
    private u mHttpClient;
    private UploadInfo mUploadInfo;

    public UploadObservable(u uVar, UploadInfo uploadInfo) {
        this.mHttpClient = null;
        this.mHttpClient = uVar;
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
        t.a aVar = new t.a();
        if (paramMap != null && paramMap.size() > 0) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                aVar.a(entry.getKey(), entry.getValue());
            }
        }
        aVar.b("file", filePath.substring(filePath.lastIndexOf("/") + 1), new x() { // from class: com.bydux.api.common.webs.okhttp.upload.UploadObservable.1
            @Override // f.x
            public s contentType() {
                return null;
            }

            @Override // f.x
            public void writeTo(d dVar) throws IOException {
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
                            int i2 = randomAccessFile.read(bArr);
                            if (i2 == -1) {
                                break;
                            }
                            dVar.d(bArr, 0, i2);
                            UploadObservable.this.mUploadInfo.setProgress(UploadObservable.this.mUploadInfo.getProgress() + ((long) i2));
                            observableEmitter.onNext(UploadObservable.this.mUploadInfo);
                        } catch (IOException e2) {
                            BydLogHelper.logE("error=" + e2.getMessage());
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
        t tVarD = aVar.d();
        w.a aVar2 = new w.a();
        if (total > progress) {
            aVar2 = aVar2.a("RANGE", "bytes=" + progress + "-" + total);
        }
        e eVarO = this.mHttpClient.o(aVar2.i(url).g(tVarD).b());
        this.mUploadInfo.setCall(eVarO);
        eVarO.w(new f() { // from class: com.bydux.api.common.webs.okhttp.upload.UploadObservable.2
            @Override // f.f
            public void onFailure(e eVar, IOException iOException) {
                BydLogHelper.logE("error=" + iOException.getMessage());
                observableEmitter.onComplete();
            }

            @Override // f.f
            public void onResponse(e eVar, y yVar) {
                BydLogHelper.logV("response code=" + yVar.O());
            }
        });
    }
}
