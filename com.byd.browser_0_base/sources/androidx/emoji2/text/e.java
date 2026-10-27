package androidx.emoji2.text;

import androidx.emoji2.text.d;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: EmojiCompatInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends d.i {
    public final /* synthetic */ d.i a;
    public final /* synthetic */ ThreadPoolExecutor b;

    public e(d.i iVar, ThreadPoolExecutor threadPoolExecutor) {
        this.a = iVar;
        this.b = threadPoolExecutor;
    }

    @Override // androidx.emoji2.text.d.i
    public final void a(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.b;
        try {
            this.a.a(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // androidx.emoji2.text.d.i
    public final void b(h hVar) {
        ThreadPoolExecutor threadPoolExecutor = this.b;
        try {
            this.a.b(hVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
