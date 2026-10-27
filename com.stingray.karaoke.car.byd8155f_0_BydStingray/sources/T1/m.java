package T1;

import android.os.Trace;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {
    public final /* synthetic */ int l;

    public /* synthetic */ m(int i7) {
        this.l = i7;
    }

    private final void a() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                try {
                    Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                    if (i.c()) {
                        i.a().d();
                        break;
                    }
                    return;
                } finally {
                    Trace.endSection();
                }
            default:
                return;
        }
    }
}
