package b.b.g.p.j;

import android.database.ContentObserver;
import android.net.Uri;

/* JADX INFO: compiled from: SettingsCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1408a;

    public d(String str) {
        super(null);
        this.f1408a = str;
    }

    public abstract void a();

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z);
        String lastPathSegment = uri.getLastPathSegment();
        b.b.m.c.a.a("SettingsCallback", "---onSettingsValueChanged---key:" + lastPathSegment);
        String str = this.f1408a;
        if (str == null || !str.equals(lastPathSegment)) {
            return;
        }
        a();
    }
}
