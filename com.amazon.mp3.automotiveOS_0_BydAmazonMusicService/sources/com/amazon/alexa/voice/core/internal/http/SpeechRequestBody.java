package com.amazon.alexa.voice.core.internal.http;

import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.util.IOUtils;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Source;

/* JADX INFO: loaded from: classes2.dex */
public final class SpeechRequestBody extends RequestBody {
    private final MediaType mediaType;
    private final Source source;

    public SpeechRequestBody(MediaType mediaType, Source source) {
        this.mediaType = mediaType;
        this.source = source;
    }

    @Override // okhttp3.RequestBody
    /* JADX INFO: renamed from: contentType */
    public MediaType getContentType() {
        return this.mediaType;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        try {
            Logger.debug("Writing a request payload...");
            long jCurrentTimeMillis = System.currentTimeMillis();
            Buffer buffer = new Buffer();
            long j = jCurrentTimeMillis;
            long j2 = 0;
            long j3 = 0;
            long j4 = 80;
            long j5 = 0;
            while (true) {
                long j6 = this.source.read(buffer, 4096L);
                if (j6 == -1) {
                    Logger.debug("Written a request payload of %d bytes in %dms", Long.valueOf(j2), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    return;
                }
                bufferedSink.write(buffer, j6);
                j2 += j6;
                j3 += j6;
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (j3 > 0 && jCurrentTimeMillis2 - j >= j4) {
                    if (j5 == 0) {
                        j5 = j3;
                    }
                    j4 = (j4 * j3) / j5;
                    if (j4 < 80) {
                        j4 = 80;
                    }
                    if (j4 > 1000) {
                        j4 = 1000;
                    }
                    Logger.debug("Produced %d -> %d bytes (%dms)", Long.valueOf(j5), Long.valueOf(j3), Long.valueOf(j4));
                    bufferedSink.flush();
                    j = jCurrentTimeMillis2;
                    j5 = j3;
                    j3 = 0;
                }
            }
        } finally {
            IOUtils.closeQuietly(this.source);
        }
    }
}
