package com.facebook.react.modules.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.GZIPOutputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: loaded from: classes6.dex */
class RequestBodyUtil {
    RequestBodyUtil() {
    }

    public static boolean isGzipEncoding(@Nullable String str) {
        return "gzip".equalsIgnoreCase(str);
    }

    @Nullable
    public static InputStream getFileInputStream(Context context, String str) {
        try {
            Uri uri = Uri.parse(str);
            if (uri.getScheme().startsWith("http")) {
                return getDownloadFileInputStream(context, uri);
            }
            if (str.startsWith("data:")) {
                byte[] bArrDecode = Base64.decode(str.split(",")[1], 0);
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapDecodeByteArray.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            return context.getContentResolver().openInputStream(uri);
        } catch (Exception e) {
            FLog.e("ReactNative", "Could not retrieve file for contentUri " + str, e);
            return null;
        }
    }

    private static InputStream getDownloadFileInputStream(Context context, Uri uri) throws IOException {
        File fileCreateTempFile = File.createTempFile("RequestBodyUtil", "temp", context.getApplicationContext().getCacheDir());
        fileCreateTempFile.deleteOnExit();
        InputStream inputStreamOpenStream = FirebasePerfUrlConnection.openStream(new URL(uri.toString()));
        try {
            ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(inputStreamOpenStream);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                try {
                    fileOutputStream.getChannel().transferFrom(readableByteChannelNewChannel, 0L, Long.MAX_VALUE);
                    return new FileInputStream(fileCreateTempFile);
                } finally {
                    fileOutputStream.close();
                }
            } finally {
                readableByteChannelNewChannel.close();
            }
        } finally {
            inputStreamOpenStream.close();
        }
    }

    @Nullable
    public static RequestBody createGzip(MediaType mediaType, String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            return RequestBody.create(mediaType, byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            return null;
        }
    }

    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() { // from class: com.facebook.react.modules.network.RequestBodyUtil.1
            @Override // okhttp3.RequestBody
            /* JADX INFO: renamed from: contentType */
            public MediaType getContentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                try {
                    return inputStream.available();
                } catch (IOException unused) {
                    return 0L;
                }
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }

    public static ProgressRequestBody createProgressRequest(RequestBody requestBody, ProgressListener progressListener) {
        return new ProgressRequestBody(requestBody, progressListener);
    }

    public static RequestBody getEmptyBody(String str) {
        if (str.equals(FirebasePerformance.HttpMethod.POST) || str.equals(FirebasePerformance.HttpMethod.PUT) || str.equals(FirebasePerformance.HttpMethod.PATCH)) {
            return RequestBody.create((MediaType) null, ByteString.EMPTY);
        }
        return null;
    }
}
