package b.a.e.c;

import android.os.IBinder;

/* JADX INFO: compiled from: DeathRecipientCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f371b;

    public void a(String str) {
        this.f370a = str;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        b bVar = this.f371b;
        if (bVar != null) {
            bVar.a(this.f370a);
        }
    }
}
