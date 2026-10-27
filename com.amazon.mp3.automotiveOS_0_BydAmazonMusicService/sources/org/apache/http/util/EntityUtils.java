package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

/* JADX INFO: loaded from: classes7.dex */
public final class EntityUtils {
    private EntityUtils() {
    }

    public static void consume(HttpEntity httpEntity) throws IOException {
        InputStream content;
        if (httpEntity == null || !httpEntity.isStreaming() || (content = httpEntity.getContent()) == null) {
            return;
        }
        content.close();
    }
}
