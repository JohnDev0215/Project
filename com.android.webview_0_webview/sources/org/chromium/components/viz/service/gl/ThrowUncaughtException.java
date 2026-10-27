package org.chromium.components.viz.service.gl;

import defpackage.RunnableC1739wy;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ThrowUncaughtException {
    public static void post() {
        ThreadUtils.e(new RunnableC1739wy());
    }
}
