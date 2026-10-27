package c.a.c.c;

import android.os.IBinder;

/* JADX INFO: compiled from: DeathRecipientCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2623a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f2624b;

    public void a(String str) {
        this.f2623a = str;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        b bVar = this.f2624b;
        if (bVar != null) {
            bVar.a(this.f2623a);
        }
    }
}
