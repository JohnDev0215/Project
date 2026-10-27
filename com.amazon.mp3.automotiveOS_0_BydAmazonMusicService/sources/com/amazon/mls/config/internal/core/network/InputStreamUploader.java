package com.amazon.mls.config.internal.core.network;

import com.amazon.mls.config.internal.core.exceptions.ClientSideException;
import com.amazon.mls.config.internal.core.exceptions.ServerSideException;
import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import com.amazon.mls.config.internal.core.network.UploadConnection;
import com.amazon.mls.config.internal.core.util.SafeStreamCloser;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class InputStreamUploader {
    private final String sourceGroup;
    private final UploadConnection.Factory uploadConnectionFactory;

    InputStreamUploader(String str) {
        this(str, new UploadConnection.Factory());
    }

    InputStreamUploader(String str, UploadConnection.Factory factory) {
        this.sourceGroup = str;
        this.uploadConnectionFactory = factory;
    }

    public boolean upload(InputStream inputStream, long j, SushiEndpoint sushiEndpoint) throws ClientSideException, ServerSideException {
        try {
            uploadInputStreamToURL(inputStream, sushiEndpoint.getURL(this.sourceGroup), j);
            return true;
        } catch (MalformedURLException e) {
            LogcatProxy.log(LogLevel.ERROR, "Can't upload files because the url is malformed!", e);
            return false;
        } catch (IOException e2) {
            LogcatProxy.log(LogLevel.ERROR, "Exception caught during file upload!", e2);
            return false;
        }
    }

    private void uploadInputStreamToURL(InputStream inputStream, URL url, long j) throws ClientSideException, IOException, ServerSideException {
        UploadConnection uploadConnectionCreateWithFixedLength = null;
        try {
            uploadConnectionCreateWithFixedLength = this.uploadConnectionFactory.createWithFixedLength(url, j);
            writeBuffered(inputStream, uploadConnectionCreateWithFixedLength.getOutputStream());
            int responseCode = uploadConnectionCreateWithFixedLength.getResponseCode();
            SafeStreamCloser.close(uploadConnectionCreateWithFixedLength);
            if (responseCode >= 500 || responseCode == 429) {
                throw new ServerSideException(String.format(Locale.US, "Request was rejected by backend service with HTTP code %d. Retry later", Integer.valueOf(responseCode)));
            }
            if (responseCode >= 400) {
                throw new ClientSideException("The request was rejected by the backend service");
            }
            if (responseCode == 200) {
                LogcatProxy.log(LogLevel.INFO, "Received HTTP_OK when uploading file");
                return;
            }
            LogLevel logLevel = LogLevel.ERROR;
            Locale locale = Locale.US;
            LogcatProxy.log(logLevel, String.format(locale, "Unexpected message code received from the server %d", Integer.valueOf(responseCode)));
            throw new ServerSideException(String.format(locale, "Unexpected response code received from the server %d. Retry later", Integer.valueOf(responseCode)));
        } catch (Throwable th) {
            SafeStreamCloser.close(uploadConnectionCreateWithFixedLength);
            throw th;
        }
    }

    private void writeBuffered(InputStream inputStream, OutputStream outputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        byte[] bArr = new byte[8192];
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(outputStream);
                while (true) {
                    try {
                        int i = bufferedInputStream.read(bArr);
                        if (i > 0) {
                            bufferedOutputStream2.write(bArr, 0, i);
                        } else {
                            SafeStreamCloser.close(bufferedOutputStream2);
                            SafeStreamCloser.close(bufferedInputStream);
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        SafeStreamCloser.close(bufferedOutputStream);
                        SafeStreamCloser.close(bufferedInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }
}
