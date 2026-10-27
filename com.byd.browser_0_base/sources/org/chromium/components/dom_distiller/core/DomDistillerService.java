package org.chromium.components.dom_distiller.core;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public final class DomDistillerService {
    public final DistilledPagePrefs a;

    public DomDistillerService(long j) {
        this.a = new DistilledPagePrefs(N.MzVEzhvu(j));
    }

    @CalledByNative
    public static DomDistillerService create(long j) {
        Object obj = ThreadUtils.a;
        return new DomDistillerService(j);
    }
}
