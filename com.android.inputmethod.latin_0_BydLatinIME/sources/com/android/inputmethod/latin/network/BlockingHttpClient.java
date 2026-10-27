package com.android.inputmethod.latin.network;

import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BlockingHttpClient {
    private static final boolean DEBUG = false;
    private static final String TAG = "BlockingHttpClient";
    private final HttpURLConnection mConnection;

    public interface ResponseProcessor<T> {
        T onSuccess(InputStream response) throws IOException;
    }

    public BlockingHttpClient(HttpURLConnection connection) {
        this.mConnection = connection;
    }

    public <T> T execute(@Nullable byte[] request, @Nonnull ResponseProcessor<T> responseProcessor) throws IOException, AuthException, HttpException {
        if (request != null) {
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.mConnection.getOutputStream());
                bufferedOutputStream.write(request);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } finally {
                this.mConnection.disconnect();
            }
        }
        int responseCode = this.mConnection.getResponseCode();
        if (responseCode != 200) {
            Log.w(TAG, "Response error: " + responseCode + ", Message: " + this.mConnection.getResponseMessage());
            if (responseCode == 401) {
                throw new AuthException(this.mConnection.getResponseMessage());
            }
            throw new HttpException(responseCode);
        }
        return responseProcessor.onSuccess(this.mConnection.getInputStream());
    }
}
