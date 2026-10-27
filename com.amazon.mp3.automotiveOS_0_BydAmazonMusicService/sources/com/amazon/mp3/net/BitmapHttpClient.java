package com.amazon.mp3.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes3.dex */
public class BitmapHttpClient extends AbstractHttpClient<Bitmap> {
    private static final String LOGTAG = "BitmapHttpClient";
    private static final BitmapFactory.Options sOptions;
    private BitmapFactory.Options mOptions;
    private Bitmap mResult;

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected boolean wantsRawResponse() {
        return true;
    }

    static {
        BitmapFactory.Options options = new BitmapFactory.Options();
        sOptions = options;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = false;
    }

    public static Bitmap bitmapFromUrl(String url) {
        return bitmapFromUrl(url, null);
    }

    public static Bitmap bitmapFromUrl(String url, BitmapFactory.Options options) {
        if (AmazonApplication.getCapabilities().isMemoryConstrainted() && SyncService.isRunning()) {
            return null;
        }
        BitmapHttpClient bitmapHttpClient = new BitmapHttpClient();
        bitmapHttpClient.mOptions = options;
        try {
            bitmapHttpClient.execute(HttpRequestBuilder.fromUri(Uri.parse(url)).setConnectionTimeout(10000).setReadTimeout(10000));
            return bitmapHttpClient.getResult();
        } catch (Exception unused) {
            Log.error(LOGTAG, "Unable to load bitmap at: %s", url);
            return null;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.mp3.net.AbstractHttpClient
    public Bitmap getResult() throws AbstractHttpClient.InvalidResultException, AbstractHttpClient.IncompleteResultException {
        Bitmap bitmap = this.mResult;
        if (bitmap != null) {
            return bitmap;
        }
        throw new AbstractHttpClient.InvalidResultException();
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void onDataReceived(byte[] data, int length) throws AbstractHttpClient.HttpClientException {
        throw new RuntimeException("FastBitmapHttpClient.onDataReceived: should never be callled");
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected boolean onProcessRawResponse(HttpURLConnection httpConnection, InputStream responseStream) throws AbstractHttpClient.HttpClientException {
        try {
            int contentLength = httpConnection.getContentLength();
            if (contentLength > 0) {
                byte[] bArr = new byte[contentLength];
                int i = 0;
                while (true) {
                    int i2 = responseStream.read(bArr, i, contentLength - i);
                    if (i2 == -1) {
                        break;
                    }
                    i += i2;
                }
                if (i != contentLength) {
                    return true;
                }
                BitmapFactory.Options options = this.mOptions;
                if (options == null) {
                    options = sOptions;
                }
                this.mResult = BitmapFactory.decodeByteArray(bArr, 0, i, options);
                return true;
            }
            BitmapFactory.Options options2 = this.mOptions;
            if (options2 == null) {
                options2 = sOptions;
            }
            this.mResult = BitmapFactory.decodeStream(responseStream, null, options2);
            return true;
        } catch (IOException e) {
            httpConnection.disconnect();
            throw new AbstractHttpClient.IncompleteResultException(e);
        }
    }
}
