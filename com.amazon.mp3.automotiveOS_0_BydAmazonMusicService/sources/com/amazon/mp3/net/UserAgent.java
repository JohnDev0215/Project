package com.amazon.mp3.net;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.capability.PlatformNameProvider;
import com.amazon.mp3.config.ApplicationAttributes;

/* JADX INFO: loaded from: classes3.dex */
public class UserAgent {
    public static synchronized String getUserAgent() {
        return String.format("Music%s/%s", PlatformNameProvider.getPlatformNameForUserAgent(), ApplicationAttributes.getInstance(AmazonApplication.getContext()).getVersion());
    }
}
