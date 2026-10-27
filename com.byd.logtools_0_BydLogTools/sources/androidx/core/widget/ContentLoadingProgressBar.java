package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f1363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Runnable f1367f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f1368g;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1364c = false;
            contentLoadingProgressBar.f1363b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1365d = false;
            if (contentLoadingProgressBar.f1366e) {
                return;
            }
            contentLoadingProgressBar.f1363b = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    private void a() {
        removeCallbacks(this.f1367f);
        removeCallbacks(this.f1368g);
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
        this.f1363b = -1L;
        this.f1364c = false;
        this.f1365d = false;
        this.f1366e = false;
        this.f1367f = new a();
        this.f1368g = new b();
    }
}
