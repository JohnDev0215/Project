package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f1015b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f1016c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1017d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f1018e;
    private final Runnable f;
    private final Runnable g;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1016c = false;
            contentLoadingProgressBar.f1015b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1017d = false;
            if (contentLoadingProgressBar.f1018e) {
                return;
            }
            contentLoadingProgressBar.f1015b = System.currentTimeMillis();
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
        this.f1015b = -1L;
        this.f1016c = false;
        this.f1017d = false;
        this.f1018e = false;
        this.f = new a();
        this.g = new b();
    }
}
