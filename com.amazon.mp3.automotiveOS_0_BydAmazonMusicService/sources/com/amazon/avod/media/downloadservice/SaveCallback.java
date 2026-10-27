package com.amazon.avod.media.downloadservice;

import com.amazon.avod.media.framework.error.MediaException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface SaveCallback {
    void save(InputStream inputStream, int i) throws MediaException;
}
