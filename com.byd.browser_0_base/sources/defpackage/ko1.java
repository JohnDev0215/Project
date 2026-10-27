package defpackage;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.os.Build;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ko1 extends no1 {
    public IconCompat d;

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {
        public static Notification.BigPictureStyle a(Notification.BigPictureStyle bigPictureStyle, Bitmap bitmap) {
            return bigPictureStyle.bigPicture(bitmap);
        }

        public static Notification.BigPictureStyle b(Notification.Builder builder) {
            return new Notification.BigPictureStyle(builder);
        }

        public static Notification.BigPictureStyle c(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
            return bigPictureStyle.setBigContentTitle(charSequence);
        }

        public static void d(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
            bigPictureStyle.setSummaryText(charSequence);
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class b {
        public static void a(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
            bigPictureStyle.bigPicture(icon);
        }

        public static void b(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
            bigPictureStyle.setContentDescription(charSequence);
        }

        public static void c(Notification.BigPictureStyle bigPictureStyle, boolean z) {
            bigPictureStyle.showBigPictureWhenCollapsed(z);
        }
    }

    @Override // defpackage.no1
    public final void b(oo1 oo1Var) {
        Bitmap bitmap;
        int i = Build.VERSION.SDK_INT;
        Notification.BigPictureStyle bigPictureStyleC = a.c(a.b(oo1Var.b), null);
        IconCompat iconCompat = this.d;
        if (iconCompat != null) {
            if (i >= 31) {
                b.a(bigPictureStyleC, iconCompat.g(oo1Var.a));
            } else {
                int type = iconCompat.a;
                if (type == -1) {
                    type = ((Icon) iconCompat.b).getType();
                }
                if (type == 1) {
                    IconCompat iconCompat2 = this.d;
                    int i2 = iconCompat2.a;
                    if (i2 == -1) {
                        Object obj = iconCompat2.b;
                        bitmap = obj instanceof Bitmap ? (Bitmap) obj : null;
                    } else if (i2 == 1) {
                        bitmap = (Bitmap) iconCompat2.b;
                    } else {
                        if (i2 != 5) {
                            throw new IllegalStateException("called getBitmap() on " + iconCompat2);
                        }
                        Bitmap bitmap2 = (Bitmap) iconCompat2.b;
                        int iMin = (int) (Math.min(bitmap2.getWidth(), bitmap2.getHeight()) * 0.6666667f);
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmapCreateBitmap);
                        Paint paint = new Paint(3);
                        float f = iMin;
                        float f2 = 0.5f * f;
                        float f3 = 0.9166667f * f2;
                        float f4 = 0.010416667f * f;
                        paint.setColor(0);
                        paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
                        canvas.drawCircle(f2, f2, f3, paint);
                        paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
                        canvas.drawCircle(f2, f2, f3, paint);
                        paint.clearShadowLayer();
                        paint.setColor(-16777216);
                        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                        BitmapShader bitmapShader = new BitmapShader(bitmap2, tileMode, tileMode);
                        Matrix matrix = new Matrix();
                        matrix.setTranslate((-(bitmap2.getWidth() - iMin)) / 2.0f, (-(bitmap2.getHeight() - iMin)) / 2.0f);
                        bitmapShader.setLocalMatrix(matrix);
                        paint.setShader(bitmapShader);
                        canvas.drawCircle(f2, f2, f3, paint);
                        canvas.setBitmap(null);
                        bitmap = bitmapCreateBitmap;
                    }
                    bigPictureStyleC = a.a(bigPictureStyleC, bitmap);
                }
            }
        }
        if (this.c) {
            a.d(bigPictureStyleC, this.b);
        }
        if (i >= 31) {
            b.c(bigPictureStyleC, false);
            b.b(bigPictureStyleC, null);
        }
    }

    @Override // defpackage.no1
    public final String c() {
        return "androidx.core.app.NotificationCompat$BigPictureStyle";
    }
}
