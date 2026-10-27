package io.sentry.android.replay;

import android.view.View;
import java.lang.ref.WeakReference;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class y extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16462m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ View f16463n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(View view, int i7) {
        super(1);
        this.f16462m = i7;
        this.f16463n = view;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16462m) {
            case 0:
                WeakReference weakReference = (WeakReference) obj;
                A7.m.f(weakReference, "it");
                return Boolean.valueOf(A7.m.a(weakReference.get(), this.f16463n));
            default:
                WeakReference weakReference2 = (WeakReference) obj;
                A7.m.f(weakReference2, "it");
                return Boolean.valueOf(A7.m.a(weakReference2.get(), this.f16463n));
        }
    }
}
