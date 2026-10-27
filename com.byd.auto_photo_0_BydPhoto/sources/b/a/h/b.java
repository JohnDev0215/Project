package b.a.h;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.byd.util.w;
import java.lang.ref.WeakReference;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
class b implements Runnable, Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected f f476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected j f477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected k f478c;
    private Handler d = new a(this, this);

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<b> f479a;

        a(b bVar, Handler.Callback callback) {
            super(Looper.getMainLooper(), callback);
            this.f479a = new WeakReference<>(bVar);
        }

        @Override // android.os.Handler
        public void dispatchMessage(@NonNull Message message) {
            WeakReference<b> weakReference = this.f479a;
            if (weakReference == null || weakReference.get() == null) {
                w.c("WeakReference obj is null");
            } else {
                super.dispatchMessage(message);
            }
        }
    }

    b(f fVar, k kVar, j jVar) {
        this.f476a = fVar;
        this.f478c = kVar;
        this.f477b = jVar;
    }

    protected Message a(int i, Object obj) {
        return this.d.obtainMessage(i, obj);
    }

    protected void b(int i) {
        this.d.sendEmptyMessage(i);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@NonNull Message message) {
        Set<ImageView> setF = this.f477b.f(this.f476a);
        if (setF == null) {
            this.f477b.e(this.f476a);
            return false;
        }
        for (ImageView imageView : setF) {
            String str = this.f476a.j;
            int i = l.f501a;
            if (str.equals(imageView.getTag(i))) {
                int i2 = message.what;
                if (i2 != 0) {
                    if (i2 == 1 && imageView.getVisibility() == 0) {
                        imageView.setImageBitmap((Bitmap) message.obj);
                        if (this.f476a.f486b.endsWith("gif") || this.f476a.f486b.endsWith("GIF")) {
                            this.f477b.c(imageView, this.f476a.j);
                        }
                    }
                } else if (imageView.getVisibility() == 0 && this.f476a.e != 0) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setImageResource(this.f476a.e);
                }
            } else {
                w.c("iv tag not same -> " + this.f476a.j + " | " + imageView.getTag(i));
            }
        }
        this.f477b.e(this.f476a);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.h.b.run():void");
    }
}
