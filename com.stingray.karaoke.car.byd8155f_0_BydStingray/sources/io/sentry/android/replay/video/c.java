package io.sentry.android.replay.video;

import A7.m;
import A7.o;
import Q8.h;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f16450m = new c(0);

    @Override // z7.InterfaceC2772a
    public final Object a() {
        boolean z6 = false;
        MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
        m.e(codecInfos, "MediaCodecList(MediaCode…)\n            .codecInfos");
        int length = codecInfos.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                break;
            }
            String name = codecInfos[i7].getName();
            m.e(name, "it.name");
            if (h.C0(name, "c2.exynos", false)) {
                z6 = true;
                break;
            }
            i7++;
        }
        return Boolean.valueOf(z6);
    }
}
