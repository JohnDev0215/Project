package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public interface jb {
    Object a(md mdVar, byte[] bArr) throws ParseErrorException, IOException;

    String a(HttpURLConnection httpURLConnection);
}
