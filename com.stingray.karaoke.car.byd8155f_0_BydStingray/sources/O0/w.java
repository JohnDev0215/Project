package O0;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;

/* JADX INFO: loaded from: classes.dex */
public final class w extends Canvas {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f5046a;

    @Override // android.graphics.Canvas
    public final boolean clipOutPath(Path path) {
        f fVar = f.f5012a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return fVar.a(canvas, path);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(RectF rectF) {
        f fVar = f.f5012a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return fVar.e(canvas, rectF);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path, Region.Op op) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipPath(path, op);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF, Region.Op op) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(rectF, op);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void concat(Matrix matrix) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.concat(matrix);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void disableZ() {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.a(canvas);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawARGB(int i7, int i10, int i11, int i12) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawARGB(i7, i10, i11, i12);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawArc(RectF rectF, float f10, float f11, boolean z6, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawArc(rectF, f10, f11, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmapMesh(Bitmap bitmap, int i7, int i10, float[] fArr, int i11, int[] iArr, int i12, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmapMesh(bitmap, i7, i10, fArr, i11, iArr, i12, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawCircle(float f10, float f11, float f12, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawCircle(f10, f11, f12, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawColor(i7);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float f10, float f11, RectF rectF2, float f12, float f13, Paint paint) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.e(canvas, rectF, f10, f11, rectF2, f12, f13, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawGlyphs(int[] iArr, int i7, float[] fArr, int i10, int i11, Font font, Paint paint) {
        i iVar = i.f5015a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            iVar.a(canvas, iArr, i7, fArr, i10, i11, font, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLine(float f10, float f11, float f12, float f13, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawLine(f10, f11, f12, f13, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, int i7, int i10, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawLines(fArr, i7, i10, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(RectF rectF, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawOval(rectF, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPaint(Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPaint(paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, Rect rect, Paint paint) {
        i iVar = i.f5015a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            iVar.b(canvas, ninePatch, rect, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPath(Path path, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPath(path, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPicture(picture);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoint(float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPoint(f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, int i7, int i10, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPoints(fArr, i7, i10, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(char[] cArr, int i7, int i10, float[] fArr, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPosText(cArr, i7, i10, fArr, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRGB(int i7, int i10, int i11) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRGB(i7, i10, i11);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(RectF rectF, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRect(rectF, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRenderNode(RenderNode renderNode) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.g(canvas, renderNode);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(RectF rectF, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRoundRect(rectF, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(char[] cArr, int i7, int i10, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawText(cArr, i7, i10, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(char[] cArr, int i7, int i10, Path path, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawTextOnPath(cArr, i7, i10, path, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(char[] cArr, int i7, int i10, int i11, int i12, float f10, float f11, boolean z6, Paint paint) {
        e eVar = e.f5011a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            eVar.b(canvas, cArr, i7, i10, i11, i12, f10, f11, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawVertices(Canvas.VertexMode vertexMode, int i7, float[] fArr, int i10, float[] fArr2, int i11, int[] iArr, int i12, short[] sArr, int i13, int i14, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawVertices(vertexMode, i7, fArr, i10, fArr2, i11, iArr, i12, sArr, i13, i14, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void enableZ() {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.i(canvas);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean getClipBounds(Rect rect) {
        Canvas canvas = this.f5046a;
        if (canvas == null) {
            A7.m.j("nativeCanvas");
            throw null;
        }
        boolean clipBounds = canvas.getClipBounds(rect);
        if (clipBounds) {
            rect.set(0, 0, rect.width(), Integer.MAX_VALUE);
        }
        return clipBounds;
    }

    @Override // android.graphics.Canvas
    public final int getDensity() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getDensity();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final DrawFilter getDrawFilter() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getDrawFilter();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getHeight() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getHeight();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void getMatrix(Matrix matrix) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.getMatrix(matrix);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapHeight() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getMaximumBitmapHeight();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getMaximumBitmapWidth() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getMaximumBitmapWidth();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getSaveCount() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getSaveCount();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int getWidth() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.getWidth();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean isOpaque() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.isOpaque();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.quickReject(rectF, edgeType);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void restore() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.restore();
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void restoreToCount(int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.restoreToCount(i7);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void rotate(float f10) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.rotate(f10);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final int save() {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.save();
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint, int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint, i7);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i7, int i10) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i7, i10);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void scale(float f10, float f11) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.scale(f10, f11);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setBitmap(Bitmap bitmap) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.setBitmap(bitmap);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDensity(int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.setDensity(i7);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setDrawFilter(DrawFilter drawFilter) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.setDrawFilter(drawFilter);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void setMatrix(Matrix matrix) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.setMatrix(matrix);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void skew(float f10, float f11) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.skew(f10, f11);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void translate(float f10, float f11) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.translate(f10, f11);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(Rect rect) {
        f fVar = f.f5012a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return fVar.d(canvas, rect);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipPath(Path path) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipPath(path);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect, Region.Op op) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(rect, op);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawArc(float f10, float f11, float f12, float f13, float f14, float f15, boolean z6, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawArc(f10, f11, f12, f13, f14, f15, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, RectF rectF, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rectF, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j3) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.c(canvas, j3);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawLines(float[] fArr, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawLines(fArr, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawOval(float f10, float f11, float f12, float f13, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawOval(f10, f11, f12, f13, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPatch(NinePatch ninePatch, RectF rectF, Paint paint) {
        i iVar = i.f5015a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            iVar.c(canvas, ninePatch, rectF, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, RectF rectF) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPicture(picture, rectF);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPoints(float[] fArr, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPoints(fArr, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPosText(String str, float[] fArr, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPosText(str, fArr, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(Rect rect, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRect(rect, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRoundRect(float f10, float f11, float f12, float f13, float f14, float f15, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRoundRect(f10, f11, f12, f13, f14, f15, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawText(str, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextOnPath(String str, Path path, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawTextOnPath(str, path, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(RectF rectF) {
        h hVar = h.f5014a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return hVar.c(canvas, rectF);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(RectF rectF, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayer(rectF, paint);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(RectF rectF, int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(rectF, i7);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(float f10, float f11, float f12, float f13) {
        f fVar = f.f5012a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return fVar.b(canvas, f10, f11, f12, f13);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(RectF rectF) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(rectF);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Rect rect, Rect rect2, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, rect, rect2, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i7, PorterDuff.Mode mode) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawColor(i7, mode);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawPicture(Picture picture, Rect rect) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawPicture(picture, rect);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawRect(float f10, float f11, float f12, float f13, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawRect(f10, f11, f12, f13, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(String str, int i7, int i10, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawText(str, i7, i10, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.quickReject(path, edgeType);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f10, float f11, float f12, float f13, Paint paint, int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayer(f10, f11, f12, f13, paint, i7);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f10, float f11, float f12, float f13, int i7, int i10) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f10, f11, f12, f13, i7, i10);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipOutRect(int i7, int i10, int i11, int i12) {
        f fVar = f.f5012a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return fVar.c(canvas, i7, i10, i11, i12);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(Rect rect) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(rect);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i7, int i10, float f10, float f11, int i11, int i12, boolean z6, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i7, i10, f10, f11, i11, i12, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(int i7, BlendMode blendMode) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.b(canvas, i7, blendMode);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawText(CharSequence charSequence, int i7, int i10, float f10, float f11, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawText(charSequence, i7, i10, f10, f11, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(Path path) {
        h hVar = h.f5014a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return hVar.b(canvas, path);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayer(float f10, float f11, float f12, float f13, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayer(f10, f11, f12, f13, paint);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final int saveLayerAlpha(float f10, float f11, float f12, float f13, int i7) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.saveLayerAlpha(f10, f11, f12, f13, i7);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f10, float f11, float f12, float f13, Region.Op op) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(f10, f11, f12, f13, op);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(int[] iArr, int i7, int i10, int i11, int i12, int i13, int i14, boolean z6, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(iArr, i7, i10, i11, i12, i13, i14, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawColor(long j3, BlendMode blendMode) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.d(canvas, j3, blendMode);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawDoubleRoundRect(RectF rectF, float[] fArr, RectF rectF2, float[] fArr2, Paint paint) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.f(canvas, rectF, fArr, rectF2, fArr2, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(CharSequence charSequence, int i7, int i10, int i11, int i12, float f10, float f11, boolean z6, Paint paint) {
        e eVar = e.f5011a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            eVar.a(canvas, charSequence, i7, i10, i11, i12, f10, f11, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f10, float f11, float f12, float f13, Canvas.EdgeType edgeType) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.quickReject(f10, f11, f12, f13, edgeType);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(float f10, float f11, float f12, float f13) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(f10, f11, f12, f13);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            canvas.drawBitmap(bitmap, matrix, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }

    @Override // android.graphics.Canvas
    public final boolean quickReject(float f10, float f11, float f12, float f13) {
        h hVar = h.f5014a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return hVar.a(canvas, f10, f11, f12, f13);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final boolean clipRect(int i7, int i10, int i11, int i12) {
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            return canvas.clipRect(i7, i10, i11, i12);
        }
        A7.m.j("nativeCanvas");
        throw null;
    }

    @Override // android.graphics.Canvas
    public final void drawTextRun(MeasuredText measuredText, int i7, int i10, int i11, int i12, float f10, float f11, boolean z6, Paint paint) {
        g gVar = g.f5013a;
        Canvas canvas = this.f5046a;
        if (canvas != null) {
            gVar.h(canvas, measuredText, i7, i10, i11, i12, f10, f11, z6, paint);
        } else {
            A7.m.j("nativeCanvas");
            throw null;
        }
    }
}
