package com.fasterxml.jackson.dataformat.xml.util;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import okhttp3.HttpUrl;
import org.codehaus.stax2.typed.Base64Variant;

/* JADX INFO: loaded from: classes6.dex */
public class StaxUtil {
    @Deprecated
    public static <T> T throwXmlAsIOException(XMLStreamException xMLStreamException) throws IOException {
        throw new IOException(_unwrap(xMLStreamException));
    }

    public static <T> T throwAsParseException(XMLStreamException xMLStreamException, JsonParser jsonParser) throws IOException {
        Throwable th_unwrap = _unwrap(xMLStreamException);
        throw new JsonParseException(jsonParser, _message(th_unwrap, xMLStreamException), th_unwrap);
    }

    public static <T> T throwAsGenerationException(XMLStreamException xMLStreamException, JsonGenerator jsonGenerator) throws IOException {
        Throwable th_unwrap = _unwrap(xMLStreamException);
        throw new JsonGenerationException(_message(th_unwrap, xMLStreamException), th_unwrap, jsonGenerator);
    }

    private static Throwable _unwrap(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        return th;
    }

    private static String _message(Throwable th, Throwable th2) {
        String message = th.getMessage();
        return message == null ? th2.getMessage() : message;
    }

    public static String sanitizeXmlTypeName(String str) {
        StringBuilder sb;
        int i;
        if (str == null) {
            return null;
        }
        if (str.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            i = 0;
            do {
                str = str.substring(0, str.length() - 2);
                i++;
            } while (str.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
            sb = new StringBuilder(str);
            if (str.endsWith("s")) {
                sb.append("es");
            } else {
                sb.append('s');
            }
        } else {
            sb = new StringBuilder(str);
            i = 0;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt <= 127 && ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < '0' || cCharAt > '9') && cCharAt != '_' && cCharAt != '.' && cCharAt != '-')))) {
                i++;
                if (cCharAt == '$') {
                    sb.setCharAt(i2, '.');
                } else {
                    sb.setCharAt(i2, '_');
                }
            }
        }
        return i == 0 ? str : sb.toString();
    }

    public static Base64Variant toStax2Base64Variant(com.fasterxml.jackson.core.Base64Variant base64Variant) {
        return Base64Mapper.instance.map(base64Variant);
    }

    private static class Base64Mapper {
        public static final Base64Mapper instance = new Base64Mapper();
        private final Map<String, Base64Variant> j2stax2;

        private Base64Mapper() {
            HashMap map = new HashMap();
            this.j2stax2 = map;
            map.put(Base64Variants.MIME.getName(), org.codehaus.stax2.typed.Base64Variants.MIME);
            map.put(Base64Variants.MIME_NO_LINEFEEDS.getName(), org.codehaus.stax2.typed.Base64Variants.MIME_NO_LINEFEEDS);
            map.put(Base64Variants.MODIFIED_FOR_URL.getName(), org.codehaus.stax2.typed.Base64Variants.MODIFIED_FOR_URL);
            map.put(Base64Variants.PEM.getName(), org.codehaus.stax2.typed.Base64Variants.PEM);
        }

        public Base64Variant map(com.fasterxml.jackson.core.Base64Variant base64Variant) {
            Base64Variant base64Variant2 = this.j2stax2.get(base64Variant.getName());
            return base64Variant2 == null ? org.codehaus.stax2.typed.Base64Variants.getDefaultVariant() : base64Variant2;
        }
    }
}
