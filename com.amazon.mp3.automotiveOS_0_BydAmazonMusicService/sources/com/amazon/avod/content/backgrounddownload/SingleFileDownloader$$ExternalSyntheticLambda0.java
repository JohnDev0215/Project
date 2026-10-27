package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.media.downloadservice.DownloadCallback;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.framework.error.MediaException;
import java.io.InputStream;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class SingleFileDownloader$$ExternalSyntheticLambda0 implements DownloadCallback {
    public final /* synthetic */ SaveCallback f$0;

    @Override // com.amazon.avod.media.downloadservice.SaveCallback
    public final void save(InputStream inputStream, int i) throws MediaException {
        this.f$0.save(inputStream, i);
    }
}
