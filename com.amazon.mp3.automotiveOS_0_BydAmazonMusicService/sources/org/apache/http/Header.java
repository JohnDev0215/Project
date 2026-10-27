package org.apache.http;

/* JADX INFO: loaded from: classes7.dex */
public interface Header extends NameValuePair {
    HeaderElement[] getElements() throws ParseException;
}
