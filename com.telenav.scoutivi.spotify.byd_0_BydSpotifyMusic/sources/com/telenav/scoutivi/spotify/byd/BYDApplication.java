package com.telenav.scoutivi.spotify.byd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import com.vivid.carcontroller.byd.dx.l;
import com.vivid.spotify.z;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import l3.d;
import q3.a;

/* JADX INFO: loaded from: classes.dex */
public class BYDApplication extends t implements com.vivid.spotify.common.base.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.g f5013l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    z f5014m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.a f5015n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.b f5016o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.h f5017p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    com.vivid.carcontroller.byd.dx.l f5018q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    f4.b f5019r;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final String f5012k = "BYDApplication";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private d.a f5020s = new a();

    class a implements d.a {
        a() {
        }

        @Override // l3.d.a
        public void a(boolean z6) {
            BYDApplication.this.f5014m.g(z6);
        }
    }

    private void s() {
        q3.d.b().c(new a.C0141a(getApplicationContext()).i(4).g(true).h(false).e());
    }

    private boolean t() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ MediaMetadataCompat u() {
        return this.f5019r.d();
    }

    private void v() {
        if (this.f5013l.p() && this.f5013l.s()) {
            this.f5013l.c(this.f5020s);
        }
        if (this.f5015n.o()) {
            this.f5015n.m();
        }
        this.f5017p.b();
        this.f5018q.l(new l.a() { // from class: com.telenav.scoutivi.spotify.byd.a
            @Override // com.vivid.carcontroller.byd.dx.l.a
            public final MediaMetadataCompat get() {
                return this.f5024a.u();
            }
        });
    }

    @Override // com.vivid.spotify.common.base.l
    public void a(Map<String, Boolean> map) {
        if (this.f5013l.p() && this.f5013l.s()) {
            this.f5013l.c(this.f5020s);
        }
        if (this.f5015n.o()) {
            this.f5015n.m();
        }
    }

    @Override // com.vivid.spotify.common.base.l
    public void b(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("com.byd.carsettings.ui.activity.AppDetailActivity");
        Bundle bundle = new Bundle();
        bundle.putString("app_package", getPackageName());
        bundle.putString("jump_to", "jump_permission");
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override // com.vivid.spotify.common.base.l
    public Set<String> c(Context context) {
        HashSet hashSet = new HashSet();
        if (t()) {
            if (!this.f5013l.p()) {
                hashSet.addAll(this.f5013l.o());
            }
            if (!this.f5015n.o()) {
                hashSet.addAll(this.f5015n.n());
            }
        }
        return hashSet;
    }

    @Override // com.vivid.spotify.common.base.l
    public boolean d() {
        if (t()) {
            if (!this.f5013l.p()) {
                h3.i.b("BYDApplication").b("need mDriveModeManager", new Object[0]);
                return false;
            }
            if (this.f5013l.s()) {
                this.f5013l.c(this.f5020s);
            }
        }
        return true;
    }

    @Override // com.vivid.spotify.common.base.l
    public boolean e() {
        if (t()) {
            return (this.f5015n.o() && this.f5013l.p()) ? false : true;
        }
        return false;
    }

    @Override // com.vivid.spotify.common.base.e
    public boolean h() {
        return true;
    }

    @Override // com.vivid.spotify.common.base.e
    public boolean k() {
        return this.f5016o.b();
    }

    @Override // com.vivid.spotify.common.base.e
    public boolean l() {
        return false;
    }

    @Override // com.telenav.scoutivi.spotify.byd.t, com.vivid.spotify.common.base.e, android.app.Application
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.Theme_VIVID_MUSIC_DX);
        s();
        h3.i.b("BYDApplication").b("onCreate", new Object[0]);
        v();
        this.f5018q.k(false);
        this.f5018q.d(false);
    }
}
