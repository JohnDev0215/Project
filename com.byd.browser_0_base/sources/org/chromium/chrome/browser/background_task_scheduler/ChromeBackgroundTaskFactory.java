package org.chromium.chrome.browser.background_task_scheduler;

import defpackage.a8;
import defpackage.gc;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ChromeBackgroundTaskFactory implements gc {

    public static class a {
        public static final ChromeBackgroundTaskFactory a = new ChromeBackgroundTaskFactory();
    }

    @CalledByNative
    public static void setAsDefault() {
        a8.b = a.a;
    }
}
