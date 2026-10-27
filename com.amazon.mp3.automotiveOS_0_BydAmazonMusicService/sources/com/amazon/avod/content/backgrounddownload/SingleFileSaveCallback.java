package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRange;
import com.amazon.avod.content.smoothstream.storage.singlefile.MediaFile;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.util.DLog;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
class SingleFileSaveCallback implements SaveCallback {
    private final ByteRange mByteRange;
    private final MediaFile mMediaFile;

    SingleFileSaveCallback(MediaFile mediaFile, ByteRange byteRange) {
        this.mMediaFile = mediaFile;
        this.mByteRange = byteRange;
    }

    @Override // com.amazon.avod.media.downloadservice.SaveCallback
    public void save(InputStream inputStream, int i) throws MediaException {
        try {
            this.mMediaFile.put(inputStream, this.mByteRange);
        } catch (IOException e) {
            DLog.errorf(e.getMessage());
            throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
        }
    }
}
