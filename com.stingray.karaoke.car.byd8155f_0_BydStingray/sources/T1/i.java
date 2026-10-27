package T1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.inputmethod.EditorInfo;
import io.sentry.C1489n1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import l3.P;
import u.C2466f;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f8494j = new Object();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static volatile i f8495k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantReadWriteLock f8496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2466f f8497b;
    public volatile int c;
    public final Handler d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f8498e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f8499f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C8.d f8500g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f8501h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c f8502i;

    public i(s sVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f8496a = reentrantReadWriteLock;
        this.c = 3;
        h hVar = (h) sVar.f8493b;
        this.f8499f = hVar;
        int i7 = sVar.f8492a;
        this.f8501h = i7;
        this.f8502i = (c) sVar.c;
        this.d = new Handler(Looper.getMainLooper());
        this.f8497b = new C2466f(null);
        this.f8500g = new C8.d(10);
        e eVar = new e(this);
        this.f8498e = eVar;
        reentrantReadWriteLock.writeLock().lock();
        if (i7 == 0) {
            try {
                this.c = 0;
            } catch (Throwable th) {
                this.f8496a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            try {
                hVar.a(new d(eVar));
            } catch (Throwable th2) {
                e(th2);
            }
        }
    }

    public static i a() {
        i iVar;
        synchronized (f8494j) {
            try {
                iVar = f8495k;
                if (!(iVar != null)) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } finally {
            }
        }
        return iVar;
    }

    public static boolean c() {
        return f8495k != null;
    }

    public final int b() {
        this.f8496a.readLock().lock();
        try {
            return this.c;
        } finally {
            this.f8496a.readLock().unlock();
        }
    }

    public final void d() {
        if (!(this.f8501h == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (b() == 1) {
            return;
        }
        this.f8496a.writeLock().lock();
        try {
            if (this.c == 0) {
                return;
            }
            this.c = 0;
            this.f8496a.writeLock().unlock();
            e eVar = this.f8498e;
            i iVar = eVar.f8490a;
            try {
                iVar.f8499f.a(new d(eVar));
            } catch (Throwable th) {
                iVar.e(th);
            }
        } finally {
            this.f8496a.writeLock().unlock();
        }
    }

    public final void e(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f8496a.writeLock().lock();
        try {
            this.c = 2;
            arrayList.addAll(this.f8497b);
            this.f8497b.clear();
            this.f8496a.writeLock().unlock();
            this.d.post(new C1.a(arrayList, this.c, th));
        } catch (Throwable th2) {
            this.f8496a.writeLock().unlock();
            throw th2;
        }
    }

    public final CharSequence f(int i7, int i10, int i11, CharSequence charSequence) {
        x[] xVarArr;
        if (!(b() == 1)) {
            throw new IllegalStateException("Not initialized yet");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (i10 < 0) {
            throw new IllegalArgumentException("end cannot be negative");
        }
        l9.l.C("start should be <= than end", i7 <= i10);
        y yVar = null;
        if (charSequence == null) {
            return null;
        }
        l9.l.C("start should be < than charSequence length", i7 <= charSequence.length());
        l9.l.C("end should be < than charSequence length", i10 <= charSequence.length());
        if (charSequence.length() == 0 || i7 == i10) {
            return charSequence;
        }
        boolean z6 = i11 == 1;
        P p10 = this.f8498e.f8491b;
        p10.getClass();
        boolean z10 = charSequence instanceof v;
        if (z10) {
            ((v) charSequence).a();
        }
        if (z10) {
            yVar = new y((Spannable) charSequence);
        } else {
            try {
                if (charSequence instanceof Spannable) {
                    yVar = new y((Spannable) charSequence);
                } else if ((charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(i7 - 1, i10 + 1, x.class) <= i10) {
                    yVar = new y();
                    yVar.l = false;
                    yVar.f8529m = new SpannableString(charSequence);
                }
            } finally {
                if (z10) {
                    ((v) charSequence).b();
                }
            }
        }
        if (yVar != null && (xVarArr = (x[]) yVar.f8529m.getSpans(i7, i10, x.class)) != null && xVarArr.length > 0) {
            for (x xVar : xVarArr) {
                int spanStart = yVar.f8529m.getSpanStart(xVar);
                int spanEnd = yVar.f8529m.getSpanEnd(xVar);
                if (spanStart != i10) {
                    yVar.removeSpan(xVar);
                }
                i7 = Math.min(spanStart, i7);
                i10 = Math.max(spanEnd, i10);
            }
        }
        int i12 = i7;
        int i13 = i10;
        if (i12 != i13 && i12 < charSequence.length()) {
            y yVar2 = (y) p10.z(charSequence, i12, i13, Integer.MAX_VALUE, z6, new C1489n1(yVar, 11, (C8.d) p10.f18537m));
            if (yVar2 != null) {
                Spannable spannable = yVar2.f8529m;
                if (z10) {
                    ((v) charSequence).b();
                }
                return spannable;
            }
            if (!z10) {
                return charSequence;
            }
        } else if (!z10) {
            return charSequence;
        }
        return charSequence;
    }

    public final void g(g gVar) {
        l9.l.F(gVar, "initCallback cannot be null");
        this.f8496a.writeLock().lock();
        try {
            if (this.c == 1 || this.c == 2) {
                this.d.post(new C1.a(Arrays.asList(gVar), this.c, null));
            } else {
                this.f8497b.add(gVar);
            }
            this.f8496a.writeLock().unlock();
        } catch (Throwable th) {
            this.f8496a.writeLock().unlock();
            throw th;
        }
    }

    public final void h(EditorInfo editorInfo) {
        if (b() != 1 || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        e eVar = this.f8498e;
        eVar.getClass();
        Bundle bundle = editorInfo.extras;
        U1.b bVar = (U1.b) eVar.c.f808n;
        int iB = bVar.b(4);
        bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iB != 0 ? ((ByteBuffer) bVar.o).getInt(iB + bVar.l) : 0);
        Bundle bundle2 = editorInfo.extras;
        eVar.f8490a.getClass();
        bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
    }
}
