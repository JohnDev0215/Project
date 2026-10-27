package com.amazon.music.account;

import com.amazon.music.marketplace.Marketplace;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public interface StratusUrlProvider {
    URL getDefaultStratusUrl();

    URL getStratusUrl(Marketplace marketplace);
}
