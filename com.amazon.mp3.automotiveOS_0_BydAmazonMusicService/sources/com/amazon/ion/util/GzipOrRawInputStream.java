package com.amazon.ion.util;

import java.io.FilterInputStream;

/* JADX INFO: loaded from: classes3.dex */
class GzipOrRawInputStream extends FilterInputStream {
    static final byte[] GZIP_HEADER = {31, -117};
}
