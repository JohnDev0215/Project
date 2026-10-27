package androidx.emoji2.text;

import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.Spanned;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.emoji2.text.f;
import defpackage.cj3;
import defpackage.kq2;
import defpackage.n3;
import defpackage.pg0;
import defpackage.q8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: EmojiCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final Object j = new Object();
    public static volatile d k;
    public final ReentrantReadWriteLock a;
    public final q8 b;
    public volatile int c;
    public final Handler d;
    public final a e;
    public final h f;
    public final C0008d g;
    public final int h;
    public final androidx.emoji2.text.b i;

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static final class a extends b {
        public volatile androidx.emoji2.text.f b;
        public volatile androidx.emoji2.text.h c;

        public a(d dVar) {
            super(dVar);
        }
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static class b {
        public final d a;

        public b(d dVar) {
            this.a = dVar;
        }
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static abstract class c {
        public final h a;
        public int b = 0;
        public final androidx.emoji2.text.b c = new androidx.emoji2.text.b();

        public c(h hVar) {
            this.a = hVar;
        }
    }

    /* JADX INFO: renamed from: androidx.emoji2.text.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EmojiCompat.java */
    public static class C0008d implements j {
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public interface e {
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static class g implements Runnable {
        public final ArrayList e;
        public final int f;

        public g(List list, int i, Throwable th) {
            if (list == null) {
                throw new NullPointerException("initCallbacks cannot be null");
            }
            this.e = new ArrayList(list);
            this.f = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList arrayList = this.e;
            int size = arrayList.size();
            int i = 0;
            if (this.f != 1) {
                while (i < size) {
                    ((f) arrayList.get(i)).a();
                    i++;
                }
            } else {
                while (i < size) {
                    ((f) arrayList.get(i)).b();
                    i++;
                }
            }
        }
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public interface h {
        void a(i iVar);
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static abstract class i {
        public abstract void a(Throwable th);

        public abstract void b(androidx.emoji2.text.h hVar);
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public interface j {
    }

    public d(EmojiCompatInitializer.a aVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.a = reentrantReadWriteLock;
        this.c = 3;
        h hVar = aVar.a;
        this.f = hVar;
        int i2 = aVar.b;
        this.h = i2;
        this.i = aVar.c;
        this.d = new Handler(Looper.getMainLooper());
        this.b = new q8(0);
        this.g = new C0008d();
        a aVar2 = new a(this);
        this.e = aVar2;
        reentrantReadWriteLock.writeLock().lock();
        if (i2 == 0) {
            try {
                this.c = 0;
            } catch (Throwable th) {
                this.a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            try {
                hVar.a(new androidx.emoji2.text.c(aVar2));
            } catch (Throwable th2) {
                d(th2);
            }
        }
    }

    public static d a() {
        d dVar;
        synchronized (j) {
            dVar = k;
            if (!(dVar != null)) {
                throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
            }
        }
        return dVar;
    }

    public final int b() {
        this.a.readLock().lock();
        try {
            return this.c;
        } finally {
            this.a.readLock().unlock();
        }
    }

    public final void c() {
        if (!(this.h == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (b() == 1) {
            return;
        }
        this.a.writeLock().lock();
        try {
            if (this.c == 0) {
                return;
            }
            this.c = 0;
            this.a.writeLock().unlock();
            a aVar = this.e;
            d dVar = aVar.a;
            try {
                dVar.f.a(new androidx.emoji2.text.c(aVar));
            } catch (Throwable th) {
                dVar.d(th);
            }
        } finally {
            this.a.writeLock().unlock();
        }
    }

    public final void d(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.a.writeLock().lock();
        try {
            this.c = 2;
            arrayList.addAll(this.b);
            this.b.clear();
            this.a.writeLock().unlock();
            this.d.post(new g(arrayList, this.c, th));
        } catch (Throwable th2) {
            this.a.writeLock().unlock();
            throw th2;
        }
    }

    public final void e() {
        ArrayList arrayList = new ArrayList();
        this.a.writeLock().lock();
        try {
            this.c = 1;
            arrayList.addAll(this.b);
            this.b.clear();
            this.a.writeLock().unlock();
            this.d.post(new g(arrayList, this.c, null));
        } catch (Throwable th) {
            this.a.writeLock().unlock();
            throw th;
        }
    }

    public final CharSequence f(int i2, int i3, CharSequence charSequence) {
        pg0[] pg0VarArr;
        if (!(b() == 1)) {
            throw new IllegalStateException("Not initialized yet");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("end cannot be negative");
        }
        n3.s("start should be <= than end", i2 <= i3);
        cj3 cj3Var = null;
        if (charSequence == null) {
            return null;
        }
        n3.s("start should be < than charSequence length", i2 <= charSequence.length());
        n3.s("end should be < than charSequence length", i3 <= charSequence.length());
        if (charSequence.length() == 0 || i2 == i3) {
            return charSequence;
        }
        androidx.emoji2.text.f fVar = this.e.b;
        fVar.getClass();
        boolean z = charSequence instanceof kq2;
        if (z) {
            ((kq2) charSequence).a();
        }
        if (z) {
            cj3Var = new cj3((Spannable) charSequence);
        } else {
            try {
                if (charSequence instanceof Spannable) {
                    cj3Var = new cj3((Spannable) charSequence);
                } else if ((charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(i2 - 1, i3 + 1, pg0.class) <= i3) {
                    cj3Var = new cj3(charSequence);
                }
            } finally {
                if (z) {
                    ((kq2) charSequence).b();
                }
            }
        }
        if (cj3Var != null && (pg0VarArr = (pg0[]) cj3Var.getSpans(i2, i3, pg0.class)) != null && pg0VarArr.length > 0) {
            for (pg0 pg0Var : pg0VarArr) {
                int spanStart = cj3Var.getSpanStart(pg0Var);
                int spanEnd = cj3Var.getSpanEnd(pg0Var);
                if (spanStart != i3) {
                    cj3Var.removeSpan(pg0Var);
                }
                i2 = Math.min(spanStart, i2);
                i3 = Math.max(spanEnd, i3);
            }
        }
        int i4 = i2;
        int i5 = i3;
        if (i4 != i5 && i4 < charSequence.length()) {
            cj3 cj3Var2 = (cj3) fVar.c(charSequence, i4, i5, Integer.MAX_VALUE, false, new f.a(cj3Var, fVar.a));
            if (cj3Var2 != null) {
                Spannable spannable = cj3Var2.f;
                if (z) {
                    ((kq2) charSequence).b();
                }
                return spannable;
            }
            if (!z) {
                return charSequence;
            }
        } else if (!z) {
            return charSequence;
        }
        return charSequence;
    }

    public final void g(f fVar) {
        if (fVar == null) {
            throw new NullPointerException("initCallback cannot be null");
        }
        this.a.writeLock().lock();
        try {
            if (this.c == 1 || this.c == 2) {
                this.d.post(new g(Arrays.asList(fVar), this.c, null));
            } else {
                this.b.add(fVar);
            }
        } finally {
            this.a.writeLock().unlock();
        }
    }

    /* JADX INFO: compiled from: EmojiCompat.java */
    public static abstract class f {
        public void a() {
        }

        public void b() {
        }
    }
}
