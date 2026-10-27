package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f1267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1270e;
    private final Runnable f;
    private final Runnable g;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1268c = false;
            contentLoadingProgressBar.f1267b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1269d = false;
            if (contentLoadingProgressBar.f1270e) {
                return;
            }
            contentLoadingProgressBar.f1267b = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    private void a() {
        removeCallbacks(this.f);
        removeCallbacks(this.g);
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
        this.f1267b = -1L;
        this.f1268c = false;
        this.f1269d = false;
        this.f1270e = false;
        this.f = new a();
        this.g = new b();
    }
}
