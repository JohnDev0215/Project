package com.byd.music.fragment.radio;

import com.byd.music.beans.RadioDBBean;
import java.util.function.Function;

/* JADX INFO: renamed from: com.byd.music.fragment.radio.-$$Lambda$8Y32Tgs7xewHIm-ORUk5d-K_WSc, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$8Y32Tgs7xewHImORUk5dK_WSc implements Function {
    public static final /* synthetic */ $$Lambda$8Y32Tgs7xewHImORUk5dK_WSc INSTANCE = new $$Lambda$8Y32Tgs7xewHImORUk5dK_WSc();

    private /* synthetic */ $$Lambda$8Y32Tgs7xewHImORUk5dK_WSc() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(((RadioDBBean) obj).getRadioFrequency());
    }
}
