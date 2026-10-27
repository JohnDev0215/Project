package com.android.car;

import android.car.storagemonitoring.IoStatsEntry;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.car.-$$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0, reason: invalid class name */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0 implements Function {
    public static final /* synthetic */ $$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0 INSTANCE = new $$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0();

    private /* synthetic */ $$Lambda$N7T3rOI2b6LP6BgZNum38gpOWD0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((IoStatsEntry) obj).toString();
    }
}
