package com.amazon.alexa.comms.mediaInsights.service.utils;

import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class StreamUtils {
    private static final Charset UTF8_CHARSET = Charset.forName(ParserUtil.UTF_8);

    public static void closeStreams(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    Log.d("MediaInsightsPublisher", "failed to close stream", th);
                }
            }
        }
    }

    public static byte[] gzipCompress(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        long jCurrentTimeMillis = System.currentTimeMillis();
        GZIPOutputStream gZIPOutputStream = null;
        try {
            byte[] bytes = str.getBytes(UTF8_CHARSET);
            byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream2.write(bytes);
                    gZIPOutputStream2.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    closeStreams(gZIPOutputStream2);
                    closeStreams(byteArrayOutputStream);
                    Log.d("MediaInsightsPublisher", String.format("gzip compression time : %s ms", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                    return byteArray;
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    closeStreams(gZIPOutputStream);
                    closeStreams(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }
}
