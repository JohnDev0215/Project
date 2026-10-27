package org.apache.http.client.entity;

import java.nio.charset.Charset;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

/* JADX INFO: loaded from: classes7.dex */
public class UrlEncodedFormEntity extends StringEntity {
    public UrlEncodedFormEntity(Iterable<? extends NameValuePair> iterable, Charset charset) {
        super(URLEncodedUtils.format(iterable, charset != null ? charset : HTTP.DEF_CONTENT_CHARSET), ContentType.create("application/x-www-form-urlencoded", charset));
    }
}
