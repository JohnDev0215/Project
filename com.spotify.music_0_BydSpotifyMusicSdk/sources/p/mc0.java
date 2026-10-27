package p;

/* JADX INFO: loaded from: classes.dex */
public final class mc0 implements Runnable {
    public final /* synthetic */ int c;

    public /* synthetic */ mc0(int i) {
        this.c = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.c) {
            case 0:
                try {
                    int i = lm2.a;
                    km2.a("EmojiCompat.EmojiCompatInitializer.run");
                    if (jc0.j != null) {
                        jc0.a().c();
                        break;
                    }
                    km2.b();
                    return;
                } catch (Throwable th) {
                    int i2 = lm2.a;
                    km2.b();
                    throw th;
                }
            default:
                return;
        }
    }

    public final String toString() {
        switch (this.c) {
            case 1:
                return "EmptyRunnable";
            default:
                return super.toString();
        }
    }
}
