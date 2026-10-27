package io.sentry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class L1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f15892a;

    public L1(F1 f12, int i7) {
        switch (i7) {
            case 1:
                h6.e.q(f12, "options are required");
                this.f15892a = f12;
                break;
            default:
                this.f15892a = f12;
                break;
        }
    }

    public ArrayList a(StackTraceElement[] stackTraceElementArr, boolean z6) {
        if (stackTraceElementArr == null || stackTraceElementArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            if (stackTraceElement != null) {
                String className = stackTraceElement.getClassName();
                if (z6 || !className.startsWith("io.sentry.") || className.startsWith("io.sentry.samples.") || className.startsWith("io.sentry.mobile.")) {
                    io.sentry.protocol.x xVar = new io.sentry.protocol.x();
                    xVar.f16798s = b(className);
                    xVar.f16794n = className;
                    xVar.f16793m = stackTraceElement.getMethodName();
                    xVar.l = stackTraceElement.getFileName();
                    if (stackTraceElement.getLineNumber() >= 0) {
                        xVar.o = Integer.valueOf(stackTraceElement.getLineNumber());
                    }
                    xVar.f16800u = Boolean.valueOf(stackTraceElement.isNativeMethod());
                    arrayList.add(xVar);
                    if (arrayList.size() >= 100) {
                        break;
                    }
                }
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public Boolean b(String str) {
        if (str == null || str.isEmpty()) {
            return Boolean.TRUE;
        }
        F1 f12 = this.f15892a;
        Iterator<String> it = f12.getInAppIncludes().iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return Boolean.TRUE;
            }
        }
        Iterator<String> it2 = f12.getInAppExcludes().iterator();
        while (it2.hasNext()) {
            if (str.startsWith(it2.next())) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public B8.t c(e7.f fVar) {
        b2 b2Var = (b2) fVar.f14480m;
        B8.t tVar = b2Var.o;
        if (tVar != null) {
            return tVar;
        }
        F1 f12 = this.f15892a;
        f12.getProfilesSampler();
        Double profilesSampleRate = f12.getProfilesSampleRate();
        Boolean boolValueOf = Boolean.valueOf(profilesSampleRate != null && profilesSampleRate.doubleValue() >= ((io.sentry.util.g) io.sentry.util.h.f16924a.get()).b());
        f12.getTracesSampler();
        B8.t tVar2 = b2Var.f16478x;
        if (tVar2 != null) {
            return tVar2;
        }
        Double tracesSampleRate = f12.getTracesSampleRate();
        Double dValueOf = Boolean.TRUE.equals(f12.getEnableTracing()) ? Double.valueOf(1.0d) : null;
        if (tracesSampleRate == null) {
            tracesSampleRate = dValueOf;
        }
        Double dValueOf2 = tracesSampleRate == null ? null : Double.valueOf(tracesSampleRate.doubleValue() / Math.pow(2.0d, f12.getBackpressureMonitor().a()));
        if (dValueOf2 != null) {
            return new B8.t(Boolean.valueOf(dValueOf2.doubleValue() >= ((io.sentry.util.g) io.sentry.util.h.f16924a.get()).b()), dValueOf2, boolValueOf, profilesSampleRate);
        }
        Boolean bool = Boolean.FALSE;
        return new B8.t(bool, (Double) null, bool, (Double) null);
    }
}
