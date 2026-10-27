package io.sentry.android.replay.capture;

import io.sentry.protocol.t;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f16345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c f16346b;
    public final /* synthetic */ c c;

    public b(c cVar, c cVar2, int i7) {
        switch (i7) {
            case 2:
                this.f16346b = cVar;
                this.c = cVar2;
                this.f16345a = new AtomicReference(-1);
                break;
            case 3:
                this.f16346b = cVar;
                this.c = cVar2;
                this.f16345a = new AtomicReference(null);
                break;
            case 4:
                this.f16346b = cVar;
                this.c = cVar2;
                this.f16345a = new AtomicReference(null);
                break;
            case 5:
                this.f16346b = cVar;
                this.c = cVar2;
                this.f16345a = new AtomicReference(null);
                break;
            default:
                this.f16346b = cVar;
                this.c = cVar2;
                this.f16345a = new AtomicReference(null);
                break;
        }
    }

    public b(t tVar, c cVar, c cVar2) {
        this.f16346b = cVar;
        this.c = cVar2;
        this.f16345a = new AtomicReference(tVar);
    }
}
