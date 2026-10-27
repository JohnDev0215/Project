package defpackage;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Rect;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: StaticResource.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vs2 implements pb2 {
    public Bitmap a;
    public final l50 b;
    public final Rect c;

    public vs2(Bitmap bitmap) {
        int i;
        int i2;
        this.a = bitmap;
        l50 l50Var = null;
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
                        l50Var = new l50(bitmap.getWidth(), bitmap.getHeight(), rect, iArr, iArr2);
                    }
                }
            } catch (BufferUnderflowException unused) {
            }
        }
        this.b = l50Var;
        this.c = new Rect(0, 0, this.a.getWidth(), this.a.getHeight());
    }

    @Override // defpackage.pb2
    public final boolean a() {
        return false;
    }

    @Override // defpackage.pb2
    public final Bitmap b() {
        Bitmap bitmap = this.a;
        this.a = null;
        return bitmap;
    }

    @Override // defpackage.pb2
    public final long c() {
        return a8.u(this.b);
    }

    @Override // defpackage.pb2
    public final Rect d() {
        return this.c;
    }

    @Override // defpackage.pb2
    public final l50 e() {
        return this.b;
    }
}
