package com.amazon.device.ads;

/* JADX INFO: compiled from: WebUtils.java */
/* JADX INFO: loaded from: classes2.dex */
class WebUtils2 {
    private final WebUtilsStatic webUtilsAdapter = new WebUtilsStatic();

    WebUtils2() {
    }

    public String getURLEncodedString(String str) {
        return this.webUtilsAdapter.getURLEncodedString(str);
    }

    /* JADX INFO: compiled from: WebUtils.java */
    private static class WebUtilsStatic {
        private WebUtilsStatic() {
        }

        String getURLEncodedString(String str) {
            return WebUtils.getURLEncodedString(str);
        }
    }
}
