package a.h.h;

import a.h.h.f;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f736b;

    public c(i iVar, Handler handler) {
        this.f735a = iVar;
        this.f736b = handler;
    }

    public void a(f.e eVar) {
        if (eVar.f759b == 0) {
            this.f736b.post(new a(this, this.f735a, eVar.f758a));
        } else {
            this.f736b.post(new b(this, this.f735a, eVar.f759b));
        }
    }
}
