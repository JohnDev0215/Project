package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f812e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f813f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f814g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f810c = false;
            contentLoadingProgressBar.f809b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f811d = false;
            if (contentLoadingProgressBar.f812e) {
                return;
            }
            contentLoadingProgressBar.f809b = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public final void a() {
        removeCallbacks(this.f813f);
        removeCallbacks(this.f814g);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f809b = -1L;
        this.f810c = false;
        this.f811d = false;
        this.f812e = false;
        this.f813f = new a();
        this.f814g = new b();
    }
}
