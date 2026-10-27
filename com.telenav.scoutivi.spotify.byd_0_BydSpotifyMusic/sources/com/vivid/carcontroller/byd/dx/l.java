package com.vivid.carcontroller.byd.dx;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;

/* JADX INFO: compiled from: BYDStateManager.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String f5151i = "com.vivid.carcontroller.byd.dx.l";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String f5152j = "com.vivid.carcontroller.byd.dx.l";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f5153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f5154b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5155c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f5156d = 30;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f5157e = 150;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f5158f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    i3.j f5159g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    i3.m f5160h;

    /* JADX INFO: compiled from: BYDStateManager.java */
    public interface a {
        MediaMetadataCompat get();
    }

    public l(Context context) {
        this.f5153a = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(f5152j, 0);
        this.f5154b = sharedPreferences.getBoolean("stop_from_recent", false);
        this.f5155c = sharedPreferences.getBoolean("enable_recover_player_state", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(boolean z6) {
        SharedPreferences.Editor editorEdit = this.f5153a.getApplicationContext().getSharedPreferences(f5152j, 0).edit();
        editorEdit.putBoolean("enable_recover_player_state", z6);
        editorEdit.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        a aVar;
        while (!this.f5159g.i()) {
            h3.i.b(f5151i).b("wait service connect", new Object[0]);
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e7) {
                h3.i.b(f5151i).e(e7, "resumeMedia", new Object[0]);
            }
        }
        h3.i.b(f5151i).b("service is connected", new Object[0]);
        String strM = "";
        int i7 = 0;
        while (i7 < 30 && TextUtils.isEmpty(strM)) {
            i7++;
            h3.i.b(f5151i).b("get media uri " + i7, new Object[0]);
            synchronized (this) {
                aVar = this.f5158f;
            }
            if (aVar == null) {
                break;
            }
            strM = aVar.get().m("android.media.metadata.MEDIA_URI");
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e8) {
                h3.i.b(f5151i).e(e8, "get media uri", new Object[0]);
            }
        }
        String str = f5151i;
        h3.i.b(str).b("mediaUri = " + strM, new Object[0]);
        if (TextUtils.isEmpty(strM)) {
            h3.i.b(str).d("current media is null", new Object[0]);
            return;
        }
        boolean zI = this.f5160h.I();
        h3.i.b(str).b("play music result  = " + zI, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(boolean z6) {
        SharedPreferences.Editor editorEdit = this.f5153a.getApplicationContext().getSharedPreferences(f5152j, 0).edit();
        editorEdit.putBoolean("stop_from_recent", z6);
        editorEdit.commit();
    }

    public void d(final boolean z6) {
        e5.a.b().b(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f5147e.g(z6);
            }
        });
    }

    public boolean e() {
        return this.f5155c;
    }

    public boolean f() {
        return this.f5154b;
    }

    public void j() {
        e5.a.b().b(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f5146e.h();
            }
        });
    }

    public void k(final boolean z6) {
        e5.a.b().b(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f5149e.i(z6);
            }
        });
    }

    public void l(a aVar) {
        synchronized (this) {
            this.f5158f = aVar;
        }
    }
}
