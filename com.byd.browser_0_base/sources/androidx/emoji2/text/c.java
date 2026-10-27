package androidx.emoji2.text;

import android.os.Build;
import androidx.emoji2.text.d;
import defpackage.a8;

/* JADX INFO: compiled from: EmojiCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d.i {
    public final /* synthetic */ d.a a;

    public c(d.a aVar) {
        this.a = aVar;
    }

    @Override // androidx.emoji2.text.d.i
    public final void a(Throwable th) {
        this.a.a.d(th);
    }

    @Override // androidx.emoji2.text.d.i
    public final void b(h hVar) {
        d.a aVar = this.a;
        aVar.c = hVar;
        h hVar2 = aVar.c;
        d dVar = aVar.a;
        aVar.b = new f(hVar2, dVar.g, dVar.i, Build.VERSION.SDK_INT >= 34 ? a8.B() : a8.B());
        aVar.a.e();
    }
}
