package F0;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class p1 extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ U8.l f2248a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p1(U8.l lVar, Handler handler) {
        super(handler);
        this.f2248a = lVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z6, Uri uri) {
        this.f2248a.p(m7.q.f19267a);
    }
}
