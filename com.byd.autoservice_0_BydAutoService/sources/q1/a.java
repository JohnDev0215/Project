package q1;

import android.os.IBinder;

/* JADX INFO: compiled from: DeathRecipientCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f4561b;

    public void a(String str) {
        this.f4560a = str;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        b bVar = this.f4561b;
        if (bVar != null) {
            bVar.a(this.f4560a);
        }
    }
}
