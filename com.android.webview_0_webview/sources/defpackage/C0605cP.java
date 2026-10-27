package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: renamed from: cP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0605cP implements QO {
    public Bitmap a;
    public final C0549bP b;
    public final Rect c;

    public C0605cP(Bitmap bitmap) {
        int i;
        int i2;
        this.a = bitmap;
        C0549bP c0549bP = null;
        if (bitmap != null) {
            try {
                byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
                    ByteBuffer byteBufferOrder = ByteBuffer.wrap(ninePatchChunk).order(ByteOrder.nativeOrder());
                    if (byteBufferOrder.get() != 0 && (i = byteBufferOrder.get()) != 0 && (i & 1) == 0 && (i2 = byteBufferOrder.get()) != 0 && (i2 & 1) == 0) {
                        byteBufferOrder.get();
                        byteBufferOrder.getInt();
                        byteBufferOrder.getInt();
                        Rect rect = new Rect();
                        rect.left = byteBufferOrder.getInt();
                        rect.right = byteBufferOrder.getInt();
                        rect.top = byteBufferOrder.getInt();
                        rect.bottom = byteBufferOrder.getInt();
                        byteBufferOrder.getInt();
                        int[] iArr = new int[i];
                        for (int i3 = 0; i3 < i; i3++) {
                            iArr[i3] = byteBufferOrder.getInt();
                        }
                        int[] iArr2 = new int[i2];
                        for (int i4 = 0; i4 < i2; i4++) {
                            iArr2[i4] = byteBufferOrder.getInt();
                        }
                        c0549bP = new C0549bP(bitmap.getWidth(), bitmap.getHeight(), rect, iArr, iArr2);
                    }
                }
            } catch (BufferUnderflowException unused) {
            }
        }
        this.b = c0549bP;
        this.c = new Rect(0, 0, this.a.getWidth(), this.a.getHeight());
    }

    public static C0605cP e(Resources resources, int i, int i2, int i3) {
        if (i <= 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        if (i2 != 0 && i3 != 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, options);
            options.inJustDecodeBounds = false;
            if (options.outHeight > i3 || options.outWidth > i2) {
                options.inSampleSize = Math.min(Math.round(options.outHeight / i3), Math.round(options.outWidth / i2));
            }
        }
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, i, options);
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = null;
        } else if (bitmapDecodeResource.getConfig() != options.inPreferredConfig) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), options.inPreferredConfig);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, (Paint) null);
            bitmapDecodeResource.recycle();
            bitmapDecodeResource = bitmapCreateBitmap;
        }
        if (bitmapDecodeResource == null) {
            try {
                Drawable drawableC = AbstractC1623us.c(resources, i);
                int iMax = Math.max(drawableC.getMinimumWidth(), Math.max(i2, 1));
                int iMax2 = Math.max(drawableC.getMinimumHeight(), Math.max(i3, 1));
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap2);
                drawableC.setBounds(0, 0, iMax, iMax2);
                drawableC.draw(canvas);
                bitmapDecodeResource = bitmapCreateBitmap2;
            } catch (Resources.NotFoundException unused) {
                bitmapDecodeResource = null;
            }
        }
        if (bitmapDecodeResource == null) {
            return null;
        }
        return new C0605cP(bitmapDecodeResource);
    }

    @Override // defpackage.QO
    public Rect a() {
        return this.c;
    }

    @Override // defpackage.QO
    public C0549bP b() {
        return this.b;
    }

    @Override // defpackage.QO
    public Bitmap c() {
        Bitmap bitmap = this.a;
        this.a = null;
        return bitmap;
    }

    @Override // defpackage.QO
    public long d() {
        return SO.a(this.b);
    }
}
