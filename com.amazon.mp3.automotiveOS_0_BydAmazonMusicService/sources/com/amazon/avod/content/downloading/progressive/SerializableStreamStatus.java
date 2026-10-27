package com.amazon.avod.content.downloading.progressive;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.serialization.SerializationException;
import com.amazon.avod.util.serialization.SerializationUtils;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SerializableStreamStatus implements Serializable {
    private static final long serialVersionUID = -8235364848320757401L;
    private final int mLastDownloaded;
    private final int mTotalChunks;

    public SerializableStreamStatus(int i, int i2) {
        this.mTotalChunks = i;
        this.mLastDownloaded = i2;
    }

    public int getLastDownloaded() {
        return this.mLastDownloaded;
    }

    public int getTotalChunks() {
        return this.mTotalChunks;
    }

    public byte[] serialize() throws ContentException {
        try {
            return SerializationUtils.serialize(this);
        } catch (SerializationException e) {
            DLog.exceptionf(e);
            throw new ContentException(ContentException.ContentError.DISK_ERROR, "Serialization exception.", e);
        }
    }

    public static SerializableStreamStatus deserialize(InputStream inputStream) throws Throwable {
        int i;
        try {
            Object objDeserialize = SerializationUtils.deserialize(inputStream);
            if (objDeserialize instanceof SerializableStreamStatus) {
                return (SerializableStreamStatus) objDeserialize;
            }
            ArrayList arrayList = (ArrayList) objDeserialize;
            int i2 = -1;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                i = i2;
                i2 = i4;
                if (i2 >= arrayList.size() || !((ProgressiveChunkInfo) arrayList.get(i2)).isDownloaded()) {
                    break;
                }
                i3 = i2 + 1;
            }
            return new SerializableStreamStatus(arrayList.size(), i);
        } catch (SerializationException e) {
            DLog.exceptionf(e);
            throw new ContentException(ContentException.ContentError.DISK_ERROR, "Serialization exception.", e);
        }
    }
}
