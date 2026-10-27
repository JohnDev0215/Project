package p2;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/* JADX INFO: renamed from: p2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2089d extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ContentResolver f20699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Uri f20700b;
    public final /* synthetic */ C2090e c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2089d(C2090e c2090e, Handler handler, ContentResolver contentResolver, Uri uri) {
        super(handler);
        this.c = c2090e;
        this.f20699a = contentResolver;
        this.f20700b = uri;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z6) {
        C2090e c2090e = this.c;
        c2090e.a(C2087b.c(c2090e.f20701a, c2090e.f20707i, c2090e.f20706h));
    }
}
