package org.chromium.ui.resources.statics;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Rect;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes4.dex */
class NinePatchData {
    private Rect mAperture;
    private final int[] mDivX;
    private final int[] mDivY;
    private final int mHeight;
    private final Rect mPadding;
    private final int mWidth;

    private NinePatchData(int i, int i2, Rect rect, int[] iArr, int[] iArr2) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mPadding = new Rect(rect.left, rect.top, i - rect.right, i2 - rect.bottom);
        int[] iArr3 = new int[iArr.length];
        this.mDivX = iArr3;
        int[] iArr4 = new int[iArr2.length];
        this.mDivY = iArr4;
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr4, 0, iArr2.length);
        this.mAperture = new Rect(iArr3[0], iArr4[0], iArr3[1], iArr4[1]);
    }

    public Rect getPadding() {
        return this.mPadding;
    }

    public Rect getAperture() {
        return this.mAperture;
    }

    public static NinePatchData create(Bitmap bitmap) {
        int i;
        int i2;
        if (bitmap == null) {
            return null;
        }
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
                    return new NinePatchData(bitmap.getWidth(), bitmap.getHeight(), rect, iArr, iArr2);
                }
            }
        } catch (BufferUnderflowException unused) {
        }
        return null;
    }
}
