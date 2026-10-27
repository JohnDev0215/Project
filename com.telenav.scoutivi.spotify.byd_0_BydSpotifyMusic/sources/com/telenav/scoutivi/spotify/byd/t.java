package com.telenav.scoutivi.spotify.byd;

/* JADX INFO: compiled from: Hilt_BYDApplication.java */
/* JADX INFO: loaded from: classes.dex */
abstract class t extends com.vivid.spotify.common.base.e implements o4.c {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final dagger.hilt.android.internal.managers.d f5113j = new dagger.hilt.android.internal.managers.d(new a());

    /* JADX INFO: compiled from: Hilt_BYDApplication.java */
    class a implements dagger.hilt.android.internal.managers.f {
        a() {
        }

        @Override // dagger.hilt.android.internal.managers.f
        public Object get() {
            return s.W().a(new n4.a(t.this)).b();
        }
    }

    t() {
    }

    @Override // o4.b
    public final Object f() {
        return q().f();
    }

    @Override // com.vivid.spotify.common.base.e, android.app.Application
    public void onCreate() {
        ((b) f()).a((BYDApplication) o4.e.a(this));
        super.onCreate();
    }

    public final dagger.hilt.android.internal.managers.d q() {
        return this.f5113j;
    }
}
