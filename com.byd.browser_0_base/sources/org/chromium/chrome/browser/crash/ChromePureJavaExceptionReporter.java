package org.chromium.chrome.browser.crash;

import defpackage.a9;
import defpackage.d73;
import defpackage.fm3;
import org.chromium.base.task.PostTask;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.components.crash.a;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection
public class ChromePureJavaExceptionReporter extends a {
    @UsedByReflection
    public ChromePureJavaExceptionReporter() {
    }

    public static void d(Throwable th) {
        PostTask.c(d73.g, new a9(th, 26, new fm3(2)));
    }
}
