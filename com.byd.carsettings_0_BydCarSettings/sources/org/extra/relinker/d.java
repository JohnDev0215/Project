package org.extra.relinker;

import android.content.Context;
import org.extra.relinker.c;

/* JADX INFO: loaded from: classes3.dex */
public class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f3976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f3977b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f3978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ c.InterfaceC0108c f3979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ f f3980e;

    public d(f fVar, Context context, String str, String str2, c.InterfaceC0108c interfaceC0108c) {
        this.f3980e = fVar;
        this.f3976a = context;
        this.f3977b = str;
        this.f3978c = str2;
        this.f3979d = interfaceC0108c;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            this.f3980e.c(this.f3976a, this.f3977b, this.f3978c);
            this.f3979d.a();
        } catch (UnsatisfiedLinkError e2) {
            this.f3979d.a(e2);
        } catch (b e3) {
            this.f3979d.a(e3);
        }
    }
}
