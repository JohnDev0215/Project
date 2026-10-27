package defpackage;

import android.graphics.Typeface;
import android.os.SystemClock;
import defpackage.rb2;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$font;

/* JADX INFO: compiled from: FontPreloader.java */
/* JADX INFO: loaded from: classes.dex */
public final class mo0 {
    public static mo0 i;
    public static final Integer[] j = {Integer.valueOf(R$font.chrome_google_sans), Integer.valueOf(R$font.chrome_google_sans_medium), Integer.valueOf(R$font.chrome_google_sans_bold)};
    public final ThreadUtils.a a = new ThreadUtils.a();
    public final Integer[] b = j;
    public boolean c;
    public Long d;
    public long e;
    public String f;
    public Long g;
    public String h;

    public static mo0 a() {
        if (i == null) {
            i = new mo0();
        }
        return i;
    }

    public final void b(String str) {
        if (this.h != null) {
            return;
        }
        this.h = str;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.g == null) {
            this.g = Long.valueOf(jElapsedRealtime);
        } else {
            oj2.b0(jElapsedRealtime - this.g.longValue(), String.format("%s.%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedBeforeFirstDraw", this.h));
            oj2.b0(jElapsedRealtime - this.g.longValue(), String.format("%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedBeforeFirstDraw"));
        }
    }

    public final void c(String str) {
        if (this.f != null) {
            return;
        }
        this.f = str;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.d == null) {
            this.d = Long.valueOf(jElapsedRealtime);
        } else {
            oj2.b0(jElapsedRealtime - this.d.longValue(), String.format("%s.%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedBeforePostInflationStartup", str));
        }
    }

    /* JADX INFO: compiled from: FontPreloader.java */
    public class a extends rb2.c {
        public int a;

        public a() {
        }

        @Override // rb2.c
        public final void d(Typeface typeface) {
            mo0 mo0Var = mo0.this;
            mo0Var.a.getClass();
            int i = this.a + 1;
            this.a = i;
            if (i == mo0Var.b.length) {
                mo0Var.getClass();
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                oj2.b0(jElapsedRealtime - mo0Var.e, String.format("%s.%s", "Android.Fonts", "TimeToRetrieveDownloadableFontsAfterOnCreate"));
                if (mo0Var.d == null) {
                    mo0Var.d = Long.valueOf(jElapsedRealtime);
                } else {
                    oj2.b0(jElapsedRealtime - mo0Var.d.longValue(), String.format("%s.%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedAfterPostInflationStartup", mo0Var.f));
                }
                if (mo0Var.g == null) {
                    mo0Var.g = Long.valueOf(jElapsedRealtime);
                } else {
                    oj2.b0(jElapsedRealtime - mo0Var.g.longValue(), String.format("%s.%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedAfterFirstDraw", mo0Var.h));
                    oj2.b0(jElapsedRealtime - mo0Var.g.longValue(), String.format("%s.%s", "Android.Fonts", "TimeDownloadableFontsRetrievedAfterFirstDraw"));
                }
            }
        }

        @Override // rb2.c
        public final void c(int i) {
        }
    }
}
