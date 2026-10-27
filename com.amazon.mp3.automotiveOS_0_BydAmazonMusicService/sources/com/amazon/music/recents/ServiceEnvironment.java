package com.amazon.music.recents;

import com.amazon.music.marketplace.Marketplace;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ServiceEnvironment {
    private final Map<Marketplace, String> contents = getContent();
    private final String name;

    protected abstract Map<Marketplace, String> getContent();

    public String getUrl(Marketplace marketplace) {
        String str = this.contents.get(marketplace);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("No URL were defined for the ServiceEnvironment " + this.name + " in the Marketplace " + marketplace.getCountryCode());
    }

    public ServiceEnvironment(String str) {
        this.name = str;
    }
}
