package io.sentry.android.replay.capture;

import io.sentry.A;
import java.util.concurrent.Executors;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class a extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f16344m = new a(0);

    @Override // z7.InterfaceC2772a
    public final Object a() {
        return Executors.newSingleThreadScheduledExecutor(new A(5));
    }
}
