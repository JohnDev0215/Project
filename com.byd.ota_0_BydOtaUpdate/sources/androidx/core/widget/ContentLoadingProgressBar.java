package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    public final Runnable E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f463d;
    public boolean s;
    public boolean x;
    public final Runnable y;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f463d = false;
            contentLoadingProgressBar.f462c = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.s = false;
            if (contentLoadingProgressBar.x) {
                return;
            }
            contentLoadingProgressBar.f462c = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public final void a() {
        removeCallbacks(this.y);
        removeCallbacks(this.E3);
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
        this.f462c = -1L;
        this.f463d = false;
        this.s = false;
        this.x = false;
        this.y = new a();
        this.E3 = new b();
    }
}
