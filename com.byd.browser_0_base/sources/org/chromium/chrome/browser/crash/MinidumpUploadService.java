package org.chromium.chrome.browser.crash;

import org.chromium.chrome.browser.base.SplitCompatIntentService;

/* JADX INFO: loaded from: classes.dex */
public class MinidumpUploadService extends SplitCompatIntentService {
    public MinidumpUploadService() {
        super("org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl", "MinidmpUploadService");
    }
}
