package d.a.a.c;

import android.os.IBinder;

/* JADX INFO: compiled from: DeathRecipientCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f4675b;

    public void a(String str) {
        this.f4674a = str;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        b bVar = this.f4675b;
        if (bVar != null) {
            bVar.a(this.f4674a);
        }
    }
}
