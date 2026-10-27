package com.byd.music.fragment.radio;

import com.byd.music.beans.RadioDBBean;
import java.util.function.Function;

/* JADX INFO: renamed from: com.byd.music.fragment.radio.-$$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class $$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc implements Function {
    public static final /* synthetic */ $$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc INSTANCE = new $$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc();

    private /* synthetic */ $$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((RadioDBBean) obj).getRadioName();
    }
}
