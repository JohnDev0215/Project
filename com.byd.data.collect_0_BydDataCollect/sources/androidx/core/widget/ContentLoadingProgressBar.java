package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f1259b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1260c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1261d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1262e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Runnable f1263f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f1264g;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1260c = false;
            contentLoadingProgressBar.f1259b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1261d = false;
            if (contentLoadingProgressBar.f1262e) {
                return;
            }
            contentLoadingProgressBar.f1259b = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    private void a() {
        removeCallbacks(this.f1263f);
        removeCallbacks(this.f1264g);
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
        this.f1259b = -1L;
        this.f1260c = false;
        this.f1261d = false;
        this.f1262e = false;
        this.f1263f = new a();
        this.f1264g = new b();
    }
}
