package com.spotify.connectivity.httpwebgate;

import p.ul0;

/* JADX INFO: loaded from: classes.dex */
public final class WebgateHelper_Factory implements ul0 {

    public static final class InstanceHolder {
        private static final WebgateHelper_Factory INSTANCE = new WebgateHelper_Factory();

        private InstanceHolder() {
        }
    }

    public static WebgateHelper_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static WebgateHelper newInstance() {
        return new WebgateHelper();
    }

    @Override // p.xy1
    public WebgateHelper get() {
        return newInstance();
    }
}
