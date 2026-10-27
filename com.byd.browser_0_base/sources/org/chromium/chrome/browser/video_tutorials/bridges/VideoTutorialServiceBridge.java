package org.chromium.chrome.browser.video_tutorials.bridges;

import J.N;
import defpackage.em3;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* JADX INFO: loaded from: classes2.dex */
public class VideoTutorialServiceBridge implements em3 {
    public long a;

    public VideoTutorialServiceBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static VideoTutorialServiceBridge create(long j) {
        return new VideoTutorialServiceBridge(j);
    }

    @Override // defpackage.em3
    public final List<String> a(int i) {
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return Arrays.asList(N.MxyQGOBV(j, this, i));
    }

    @Override // defpackage.em3
    public final void b(Callback callback, int i) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MSP6HvY8(j, this, i, callback);
    }

    @Override // defpackage.em3
    public final String c() {
        long j = this.a;
        if (j == 0) {
            return null;
        }
        return N.MKTDYLaI(j, this);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }

    @Override // defpackage.em3
    public final void d(Callback<List<Tutorial>> callback) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MscHdp7R(j, this, callback);
    }

    @Override // defpackage.em3
    public final void e(String str) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MPZw2yWD(j, this, str);
    }
}
