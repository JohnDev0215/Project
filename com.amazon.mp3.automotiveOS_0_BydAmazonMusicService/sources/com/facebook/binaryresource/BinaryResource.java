package com.facebook.binaryresource;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public interface BinaryResource {
    InputStream openStream() throws IOException;

    long size();
}
