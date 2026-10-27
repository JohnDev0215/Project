package org.apache.http.cookie;

import java.util.Date;

/* JADX INFO: loaded from: classes7.dex */
public interface Cookie {
    String getDomain();

    Date getExpiryDate();

    String getName();

    String getPath();

    int[] getPorts();

    String getValue();

    int getVersion();

    boolean isExpired(Date date);

    boolean isSecure();
}
