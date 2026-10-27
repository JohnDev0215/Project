package io.sentry.android.timber;

import A7.m;
import B9.b;
import io.sentry.C1489n1;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import io.sentry.Z;
import java.io.Closeable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import n7.z;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/sentry/android/timber/SentryTimberIntegration;", "Lio/sentry/Z;", "Ljava/io/Closeable;", "Lio/sentry/p1;", "minEventLevel", "minBreadcrumbLevel", "<init>", "(Lio/sentry/p1;Lio/sentry/p1;)V", "sentry-android-timber_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SentryTimberIntegration implements Z, Closeable {
    public final EnumC1495p1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final EnumC1495p1 f16470m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a f16471n;
    public K o;

    /* JADX WARN: Multi-variable type inference failed */
    public SentryTimberIntegration() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a aVar = this.f16471n;
        if (aVar != null) {
            if (aVar == null) {
                m.j("tree");
                throw null;
            }
            Timber.f22577a.getClass();
            ArrayList arrayList = Timber.f22578b;
            synchronized (arrayList) {
                if (!arrayList.remove(aVar)) {
                    throw new IllegalArgumentException(("Cannot uproot tree which is not planted: " + aVar).toString());
                }
                Object[] array = arrayList.toArray(new b[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                Timber.c = (b[]) array;
            }
            K k10 = this.o;
            if (k10 != null) {
                if (k10 != null) {
                    k10.k(EnumC1495p1.DEBUG, "SentryTimberIntegration removed.", new Object[0]);
                } else {
                    m.j("logger");
                    throw null;
                }
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        K logger = f12.getLogger();
        m.e(logger, "options.logger");
        this.o = logger;
        a aVar = new a(this.l, this.f16470m);
        this.f16471n = aVar;
        B9.a aVar2 = Timber.f22577a;
        aVar2.getClass();
        if (aVar == aVar2) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        }
        ArrayList arrayList = Timber.f22578b;
        synchronized (arrayList) {
            arrayList.add(aVar);
            Object[] array = arrayList.toArray(new b[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            Timber.c = (b[]) array;
        }
        K k10 = this.o;
        if (k10 == null) {
            m.j("logger");
            throw null;
        }
        k10.k(EnumC1495p1.DEBUG, "SentryTimberIntegration installed.", new Object[0]);
        C1489n1.s().g("maven:io.sentry:sentry-android-timber");
        z.c("Timber");
    }

    public SentryTimberIntegration(EnumC1495p1 enumC1495p1, EnumC1495p1 enumC1495p12) {
        m.f(enumC1495p1, "minEventLevel");
        m.f(enumC1495p12, "minBreadcrumbLevel");
        this.l = enumC1495p1;
        this.f16470m = enumC1495p12;
    }

    public /* synthetic */ SentryTimberIntegration(EnumC1495p1 enumC1495p1, EnumC1495p1 enumC1495p12, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? EnumC1495p1.ERROR : enumC1495p1, (i7 & 2) != 0 ? EnumC1495p1.INFO : enumC1495p12);
    }
}
