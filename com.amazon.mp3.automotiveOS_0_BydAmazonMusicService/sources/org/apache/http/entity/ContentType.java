package org.apache.http.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.TextUtils;

/* JADX INFO: loaded from: classes7.dex */
public final class ContentType implements Serializable {
    public static final ContentType APPLICATION_ATOM_XML;
    public static final ContentType APPLICATION_FORM_URLENCODED;
    public static final ContentType APPLICATION_JSON;
    public static final ContentType APPLICATION_OCTET_STREAM;
    public static final ContentType APPLICATION_SVG_XML;
    public static final ContentType APPLICATION_XHTML_XML;
    public static final ContentType APPLICATION_XML;
    private static final Map<String, ContentType> CONTENT_TYPE_MAP;
    public static final ContentType DEFAULT_BINARY;
    public static final ContentType DEFAULT_TEXT;
    public static final ContentType IMAGE_BMP;
    public static final ContentType IMAGE_GIF;
    public static final ContentType IMAGE_JPEG;
    public static final ContentType IMAGE_PNG;
    public static final ContentType IMAGE_SVG;
    public static final ContentType IMAGE_TIFF;
    public static final ContentType IMAGE_WEBP;
    public static final ContentType MULTIPART_FORM_DATA;
    public static final ContentType TEXT_HTML;
    public static final ContentType TEXT_PLAIN;
    public static final ContentType TEXT_XML;
    public static final ContentType WILDCARD;
    private final Charset charset;
    private final String mimeType;
    private final NameValuePair[] params;

    static {
        Charset charset = Consts.ISO_8859_1;
        ContentType contentTypeCreate = create("application/atom+xml", charset);
        APPLICATION_ATOM_XML = contentTypeCreate;
        ContentType contentTypeCreate2 = create("application/x-www-form-urlencoded", charset);
        APPLICATION_FORM_URLENCODED = contentTypeCreate2;
        ContentType contentTypeCreate3 = create("application/json", Consts.UTF_8);
        APPLICATION_JSON = contentTypeCreate3;
        APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset) null);
        ContentType contentTypeCreate4 = create("application/svg+xml", charset);
        APPLICATION_SVG_XML = contentTypeCreate4;
        ContentType contentTypeCreate5 = create("application/xhtml+xml", charset);
        APPLICATION_XHTML_XML = contentTypeCreate5;
        ContentType contentTypeCreate6 = create("application/xml", charset);
        APPLICATION_XML = contentTypeCreate6;
        ContentType contentTypeCreate7 = create("image/bmp");
        IMAGE_BMP = contentTypeCreate7;
        ContentType contentTypeCreate8 = create("image/gif");
        IMAGE_GIF = contentTypeCreate8;
        ContentType contentTypeCreate9 = create("image/jpeg");
        IMAGE_JPEG = contentTypeCreate9;
        ContentType contentTypeCreate10 = create("image/png");
        IMAGE_PNG = contentTypeCreate10;
        ContentType contentTypeCreate11 = create("image/svg+xml");
        IMAGE_SVG = contentTypeCreate11;
        ContentType contentTypeCreate12 = create("image/tiff");
        IMAGE_TIFF = contentTypeCreate12;
        ContentType contentTypeCreate13 = create("image/webp");
        IMAGE_WEBP = contentTypeCreate13;
        ContentType contentTypeCreate14 = create("multipart/form-data", charset);
        MULTIPART_FORM_DATA = contentTypeCreate14;
        ContentType contentTypeCreate15 = create("text/html", charset);
        TEXT_HTML = contentTypeCreate15;
        ContentType contentTypeCreate16 = create("text/plain", charset);
        TEXT_PLAIN = contentTypeCreate16;
        ContentType contentTypeCreate17 = create("text/xml", charset);
        TEXT_XML = contentTypeCreate17;
        WILDCARD = create("*/*", (Charset) null);
        ContentType[] contentTypeArr = {contentTypeCreate, contentTypeCreate2, contentTypeCreate3, contentTypeCreate4, contentTypeCreate5, contentTypeCreate6, contentTypeCreate7, contentTypeCreate8, contentTypeCreate9, contentTypeCreate10, contentTypeCreate11, contentTypeCreate12, contentTypeCreate13, contentTypeCreate14, contentTypeCreate15, contentTypeCreate16, contentTypeCreate17};
        HashMap map = new HashMap();
        for (int i = 0; i < 17; i++) {
            ContentType contentType = contentTypeArr[i];
            map.put(contentType.getMimeType(), contentType);
        }
        CONTENT_TYPE_MAP = Collections.unmodifiableMap(map);
        DEFAULT_TEXT = TEXT_PLAIN;
        DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
    }

    ContentType(String str, Charset charset) {
        this.mimeType = str;
        this.charset = charset;
        this.params = null;
    }

    ContentType(String str, Charset charset, NameValuePair[] nameValuePairArr) {
        this.mimeType = str;
        this.charset = charset;
        this.params = nameValuePairArr;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.append(this.mimeType);
        if (this.params != null) {
            charArrayBuffer.append("; ");
            BasicHeaderValueFormatter.INSTANCE.formatParameters(charArrayBuffer, this.params, false);
        } else if (this.charset != null) {
            charArrayBuffer.append("; charset=");
            charArrayBuffer.append(this.charset.name());
        }
        return charArrayBuffer.toString();
    }

    private static boolean valid(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == ',' || cCharAt == ';') {
                return false;
            }
        }
        return true;
    }

    public static ContentType create(String str, Charset charset) {
        String lowerCase = ((String) Args.notBlank(str, "MIME type")).toLowerCase(Locale.ROOT);
        Args.check(valid(lowerCase), "MIME type may not contain reserved characters");
        return new ContentType(lowerCase, charset);
    }

    public static ContentType create(String str) {
        return create(str, (Charset) null);
    }

    private static ContentType create(HeaderElement headerElement, boolean z) {
        return create(headerElement.getName(), headerElement.getParameters(), z);
    }

    private static ContentType create(String str, NameValuePair[] nameValuePairArr, boolean z) {
        Charset charsetForName;
        int length = nameValuePairArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            NameValuePair nameValuePair = nameValuePairArr[i];
            if (nameValuePair.getName().equalsIgnoreCase("charset")) {
                String value = nameValuePair.getValue();
                if (!TextUtils.isBlank(value)) {
                    try {
                        charsetForName = Charset.forName(value);
                    } catch (UnsupportedCharsetException e) {
                        if (z) {
                            throw e;
                        }
                        charsetForName = null;
                        if (nameValuePairArr.length <= 0) {
                            nameValuePairArr = null;
                        }
                        return new ContentType(str, charsetForName, nameValuePairArr);
                    }
                }
            } else {
                i++;
            }
        }
    }

    public static ContentType get(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        Header contentType;
        if (httpEntity != null && (contentType = httpEntity.getContentType()) != null) {
            HeaderElement[] elements = contentType.getElements();
            if (elements.length > 0) {
                return create(elements[0], true);
            }
        }
        return null;
    }
}
