package spi;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
public final class qn extends z1 {
    public static final byte[] c = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(ig.a);
    public final int b;

    public qn(int i) {
        dl.a(i > 0, "roundingRadius must be greater than 0.");
        this.b = i;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(c);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.b).array());
    }

    @Override // spi.z1
    public final Bitmap c(@NonNull w1 w1Var, @NonNull Bitmap bitmap, int i, int i2) {
        Bitmap bitmapD;
        int i3 = this.b;
        Paint paint = st.a;
        dl.a(i3 > 0, "roundingRadius must be greater than 0.");
        rt rtVar = new rt(i3);
        Bitmap.Config config = Bitmap.Config.RGBA_F16.equals(bitmap.getConfig()) ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        Bitmap.Config config2 = Bitmap.Config.RGBA_F16.equals(bitmap.getConfig()) ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        if (config2.equals(bitmap.getConfig())) {
            bitmapD = bitmap;
        } else {
            bitmapD = w1Var.d(bitmap.getWidth(), bitmap.getHeight(), config2);
            new Canvas(bitmapD).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        Bitmap bitmapD2 = w1Var.d(bitmapD.getWidth(), bitmapD.getHeight(), config);
        bitmapD2.setHasAlpha(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmapD, tileMode, tileMode);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setShader(bitmapShader);
        RectF rectF = new RectF(0.0f, 0.0f, bitmapD2.getWidth(), bitmapD2.getHeight());
        Lock lock = st.c;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmapD2);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            float f = rtVar.a;
            canvas.drawRoundRect(rectF, f, f, paint2);
            canvas.setBitmap(null);
            lock.unlock();
            if (!bitmapD.equals(bitmap)) {
                w1Var.c(bitmapD);
            }
            return bitmapD2;
        } catch (Throwable th) {
            st.c.unlock();
            throw th;
        }
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        return (obj instanceof qn) && this.b == ((qn) obj).b;
    }

    @Override // spi.ig
    public final int hashCode() {
        int i = this.b;
        char[] cArr = tu.a;
        return ((i + 527) * 31) - 569625254;
    }
}
