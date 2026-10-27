package io.sentry.okhttp;

import A7.m;
import A7.o;
import io.sentry.U;
import io.sentry.W1;
import java.io.IOException;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16636m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ IOException f16637n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i7, IOException iOException) {
        super(1);
        this.f16636m = i7;
        this.f16637n = iOException;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16636m) {
            case 0:
                U u4 = (U) obj;
                m.f(u4, "it");
                u4.b(W1.INTERNAL_ERROR);
                u4.o(this.f16637n);
                break;
            case 1:
                U u10 = (U) obj;
                m.f(u10, "it");
                u10.o(this.f16637n);
                u10.b(W1.INTERNAL_ERROR);
                break;
            case 2:
                U u11 = (U) obj;
                m.f(u11, "it");
                if (!u11.g()) {
                    u11.b(W1.INTERNAL_ERROR);
                    u11.o(this.f16637n);
                }
                break;
            case 3:
                U u12 = (U) obj;
                m.f(u12, "it");
                u12.b(W1.INTERNAL_ERROR);
                u12.o(this.f16637n);
                break;
            case 4:
                U u13 = (U) obj;
                m.f(u13, "it");
                if (!u13.g()) {
                    u13.b(W1.INTERNAL_ERROR);
                    u13.o(this.f16637n);
                }
                break;
            default:
                U u14 = (U) obj;
                m.f(u14, "it");
                u14.b(W1.INTERNAL_ERROR);
                u14.o(this.f16637n);
                break;
        }
        return q.f19267a;
    }
}
