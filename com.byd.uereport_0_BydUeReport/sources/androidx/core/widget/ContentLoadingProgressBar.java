package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1262d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1263e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1264f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1265g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Runnable f1266h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runnable f1267i;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1263e = false;
            contentLoadingProgressBar.f1262d = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1264f = false;
            if (contentLoadingProgressBar.f1265g) {
                return;
            }
            contentLoadingProgressBar.f1262d = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public final void a() {
        removeCallbacks(this.f1266h);
        removeCallbacks(this.f1267i);
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
        this.f1262d = -1L;
        this.f1263e = false;
        this.f1264f = false;
        this.f1265g = false;
        this.f1266h = new a();
        this.f1267i = new b();
    }
}
