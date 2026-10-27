package com.byd.data.collect.h;

import com.byd.data.collect.server.DataCollectService;
import com.byd.data.common.energy.TravelBean;
import com.byd.data.common.energy.a;
import com.byd.data.common.sqlite.o;
import com.byd.data.common.sqlite.p;
import java.util.List;

/* JADX INFO: compiled from: EnergyCollectProxy.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a.AbstractBinderC0066a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DataCollectService f3134a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private p f3135b = o.f().h();

    /* JADX INFO: compiled from: EnergyCollectProxy.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long[] f3136b;

        a(long[] jArr) {
            this.f3136b = jArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f3135b.c(this.f3136b);
        }
    }

    /* JADX INFO: renamed from: com.byd.data.collect.h.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EnergyCollectProxy.java */
    class RunnableC0060b implements Runnable {
        RunnableC0060b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f3135b.b();
        }
    }

    public b(DataCollectService dataCollectService) {
        this.f3134a = dataCollectService;
    }

    @Override // com.byd.data.common.energy.a
    public void d() {
        new Thread(new RunnableC0060b()).start();
    }

    @Override // com.byd.data.common.energy.a
    public void g(long[] jArr) {
        new Thread(new a(jArr)).start();
    }

    @Override // com.byd.data.common.energy.a
    public List<TravelBean> h(long j, long j2, int i2, int i3) {
        return this.f3135b.g(j, j2, i2, i3);
    }

    @Override // com.byd.data.common.energy.a
    public List<TravelBean> p(long j, long j2) {
        return this.f3135b.f(j, j2);
    }
}
