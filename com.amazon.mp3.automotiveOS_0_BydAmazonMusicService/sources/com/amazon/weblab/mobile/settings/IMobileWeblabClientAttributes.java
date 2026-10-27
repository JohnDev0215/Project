package com.amazon.weblab.mobile.settings;

import com.amazon.weblab.mobile.TooManyRegisteredWeblabsException;
import com.amazon.weblab.mobile.service.MobileWeblabDomain;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface IMobileWeblabClientAttributes {
    void addWeblab(String str, String str2) throws TooManyRegisteredWeblabsException, IllegalArgumentException;

    String getApplicationName();

    String getApplicationVersion();

    Map<String, String> getClientAttributes();

    MobileWeblabDomain getDomain();

    String getIdentifier();

    MobileWeblabOS getOSName();

    String getOSVersion();

    Map<String, String> getWeblabs();
}
