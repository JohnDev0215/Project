package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1175d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1176e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f1177f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f1178g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1174c = false;
            contentLoadingProgressBar.f1173b = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f1175d = false;
            if (contentLoadingProgressBar.f1176e) {
                return;
            }
            contentLoadingProgressBar.f1173b = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(Context context) {
        this(context, null);
    }

    public final void a() {
        removeCallbacks(this.f1177f);
        removeCallbacks(this.f1178g);
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
        this.f1173b = -1L;
        this.f1174c = false;
        this.f1175d = false;
        this.f1176e = false;
        this.f1177f = new a();
        this.f1178g = new b();
    }
}
