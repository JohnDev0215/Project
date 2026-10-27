package com.amazon.avod.util;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import javax.annotation.Nonnull;
import okhttp3.Headers;
import okhttp3.MediaType;

/* JADX INFO: loaded from: classes2.dex */
public class CharsetUtils {
    public static String getCharset(@Nonnull Headers headers) {
        MediaType mediaType;
        String str = headers.get(HttpHeader.CONTENT_TYPE);
        return (str == null || (mediaType = MediaType.parse(str)) == null || mediaType.charset() == null) ? ParserUtil.UTF_8 : mediaType.charset().name();
    }
}
