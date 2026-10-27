package pl.droidsonroids.gif;

import android.car.Car;
import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.MediaController;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class c extends Drawable implements Animatable, MediaController.MediaPlayerControl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ScheduledThreadPoolExecutor f3186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    volatile boolean f3187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f3188c;
    private final Rect d;
    protected final Paint e;
    final Bitmap f;
    final GifInfoHandle g;
    final ConcurrentLinkedQueue<pl.droidsonroids.gif.a> h;
    private ColorStateList i;
    private PorterDuffColorFilter j;
    private PorterDuff.Mode k;
    final boolean l;
    final h m;
    private final k n;
    private final Rect o;
    ScheduledFuture<?> p;
    private int q;
    private int r;
    private pl.droidsonroids.gif.m.a s;

    class a extends l {
        a(c cVar) {
            super(cVar);
        }

        @Override // pl.droidsonroids.gif.l
        public void a() {
            if (c.this.g.s()) {
                c.this.start();
            }
        }
    }

    class b extends l {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3190b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c cVar, int i) {
            super(cVar);
            this.f3190b = i;
        }

        @Override // pl.droidsonroids.gif.l
        public void a() {
            c cVar = c.this;
            cVar.g.w(this.f3190b, cVar.f);
            this.f3203a.m.sendEmptyMessageAtTime(-1, 0L);
        }
    }

    public c(@Nullable ContentResolver contentResolver, @NonNull Uri uri) {
        this(GifInfoHandle.o(contentResolver, uri), null, null, true);
    }

    public c(@NonNull AssetFileDescriptor assetFileDescriptor) {
        this(new GifInfoHandle(assetFileDescriptor), null, null, true);
    }

    public c(@NonNull Resources resources, @DrawableRes @RawRes int i) {
        this(resources.openRawResourceFd(i));
        float fB = f.b(resources, i);
        this.r = (int) (this.g.e() * fB);
        this.q = (int) (this.g.k() * fB);
    }

    public c(@NonNull InputStream inputStream) {
        this(new GifInfoHandle(inputStream), null, null, true);
    }

    public c(@NonNull String str) {
        this(new GifInfoHandle(str), null, null, true);
    }

    c(GifInfoHandle gifInfoHandle, c cVar, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, boolean z) {
        this.f3187b = true;
        this.f3188c = Long.MIN_VALUE;
        this.d = new Rect();
        this.e = new Paint(6);
        this.h = new ConcurrentLinkedQueue<>();
        k kVar = new k(this);
        this.n = kVar;
        this.l = z;
        this.f3186a = scheduledThreadPoolExecutor == null ? e.a() : scheduledThreadPoolExecutor;
        this.g = gifInfoHandle;
        Bitmap bitmap = null;
        if (cVar != null) {
            synchronized (cVar.g) {
                if (!cVar.g.m() && cVar.g.e() >= gifInfoHandle.e() && cVar.g.k() >= gifInfoHandle.k()) {
                    cVar.i();
                    Bitmap bitmap2 = cVar.f;
                    bitmap2.eraseColor(0);
                    bitmap = bitmap2;
                }
            }
        }
        if (bitmap == null) {
            this.f = Bitmap.createBitmap(gifInfoHandle.k(), gifInfoHandle.e(), Bitmap.Config.ARGB_8888);
        } else {
            this.f = bitmap;
        }
        this.f.setHasAlpha(!gifInfoHandle.l());
        this.o = new Rect(0, 0, gifInfoHandle.k(), gifInfoHandle.e());
        this.m = new h(this);
        kVar.a();
        this.q = gifInfoHandle.k();
        this.r = gifInfoHandle.e();
    }

    private void a() {
        ScheduledFuture<?> scheduledFuture = this.p;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.m.removeMessages(-1);
    }

    private void g() {
        if (this.l && this.f3187b) {
            long j = this.f3188c;
            if (j != Long.MIN_VALUE) {
                long jMax = Math.max(0L, j - SystemClock.uptimeMillis());
                this.f3188c = Long.MIN_VALUE;
                this.f3186a.remove(this.n);
                this.p = this.f3186a.schedule(this.n, jMax, TimeUnit.MILLISECONDS);
            }
        }
    }

    private void i() {
        this.f3187b = false;
        this.m.removeMessages(-1);
        this.g.q();
    }

    private PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public int b() {
        return this.g.a();
    }

    public int c() {
        int iB = this.g.b();
        return (iB == 0 || iB < this.g.f()) ? iB : iB - 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return d() > 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return d() > 1;
    }

    public int d() {
        return this.g.i();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        boolean z;
        if (this.j == null || this.e.getColorFilter() != null) {
            z = false;
        } else {
            this.e.setColorFilter(this.j);
            z = true;
        }
        pl.droidsonroids.gif.m.a aVar = this.s;
        if (aVar == null) {
            canvas.drawBitmap(this.f, this.o, this.d, this.e);
        } else {
            aVar.a(canvas, this.e, this.f);
        }
        if (z) {
            this.e.setColorFilter(null);
        }
    }

    public boolean e() {
        return this.g.m();
    }

    public void f() {
        this.f3186a.execute(new a(this));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.e.getAlpha();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return 100;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.e.getColorFilter();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return this.g.c();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return this.g.d();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.r;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.q;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return (!this.g.l() || this.e.getAlpha() < 255) ? -2 : -1;
    }

    public void h(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 65535) int i) {
        this.g.x(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        g();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.f3187b;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f3187b;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        return super.isStateful() || ((colorStateList = this.i) != null && colorStateList.isStateful());
    }

    void j(long j) {
        if (this.l) {
            this.f3188c = 0L;
            this.m.sendEmptyMessageAtTime(-1, 0L);
        } else {
            a();
            this.p = this.f3186a.schedule(this.n, Math.max(j, 0L), TimeUnit.MILLISECONDS);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.d.set(rect);
        pl.droidsonroids.gif.m.a aVar = this.s;
        if (aVar != null) {
            aVar.b(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.i;
        if (colorStateList == null || (mode = this.k) == null) {
            return false;
        }
        this.j = k(colorStateList, mode);
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        stop();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 2147483647L) int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Position is not positive");
        }
        this.f3186a.execute(new b(this, i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 255) int i) {
        this.e.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.e.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.e.setFilterBitmap(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.i = colorStateList;
        this.j = k(colorStateList, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(@Nullable PorterDuff.Mode mode) {
        this.k = mode;
        this.j = k(this.i, mode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!this.l) {
            if (z) {
                if (z2) {
                    f();
                }
                if (visible) {
                    start();
                }
            } else if (visible) {
                stop();
            }
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable, android.widget.MediaController.MediaPlayerControl
    public void start() {
        synchronized (this) {
            if (this.f3187b) {
                return;
            }
            this.f3187b = true;
            j(this.g.t());
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        synchronized (this) {
            if (this.f3187b) {
                this.f3187b = false;
                a();
                this.g.v();
            }
        }
    }

    @NonNull
    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", Integer.valueOf(this.g.k()), Integer.valueOf(this.g.e()), Integer.valueOf(this.g.i()), Integer.valueOf(this.g.g()));
    }
}
