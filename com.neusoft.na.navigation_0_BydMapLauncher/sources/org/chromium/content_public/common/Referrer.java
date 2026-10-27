package org.chromium.content_public.common;

/* JADX INFO: loaded from: classes3.dex */
public class Referrer {
    public static final int REFERRER_POLICY_ALWAYS = 0;
    public static final int REFERRER_POLICY_DEFAULT = 1;
    private final int mPolicy;
    private final String mUrl;

    public Referrer(String str, int i) {
        this.mUrl = str;
        this.mPolicy = i;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getPolicy() {
        return this.mPolicy;
    }
}
