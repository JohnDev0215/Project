package com.android.launcher3.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.icons.BitmapRenderer;
import com.android.launcher3.util.UiThreadHelper;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import com.android.launcher3.widget.PendingAppWidgetHostView;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public class DragPreviewProvider {
    protected final int blurSizeOutline;
    public Bitmap generatedDragOutline;
    private OutlineGeneratorCallback mOutlineGeneratorCallback;
    private final Rect mTempRect;
    protected final View mView;
    public final int previewPadding;

    public DragPreviewProvider(View view) {
        this(view, view.getContext());
    }

    public DragPreviewProvider(View view, Context context) {
        this.mTempRect = new Rect();
        this.mView = view;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.blur_size_medium_outline);
        this.blurSizeOutline = dimensionPixelSize;
        if (view instanceof BubbleTextView) {
            Rect drawableBounds = getDrawableBounds(((BubbleTextView) view).getIcon());
            this.previewPadding = (dimensionPixelSize - drawableBounds.left) - drawableBounds.top;
        } else {
            this.previewPadding = dimensionPixelSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: drawDragView, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$createDragBitmap$1$DragPreviewProvider(Canvas destCanvas, float scale) {
        destCanvas.save();
        destCanvas.scale(scale, scale);
        View view = this.mView;
        if (view instanceof BubbleTextView) {
            Drawable icon = ((BubbleTextView) view).getIcon();
            Rect drawableBounds = getDrawableBounds(icon);
            destCanvas.translate((this.blurSizeOutline / 2) - drawableBounds.left, (this.blurSizeOutline / 2) - drawableBounds.top);
            icon.draw(destCanvas);
        } else {
            Rect rect = this.mTempRect;
            view.getDrawingRect(rect);
            View view2 = this.mView;
            boolean z = false;
            if ((view2 instanceof FolderIcon) && ((FolderIcon) view2).getTextVisible()) {
                ((FolderIcon) this.mView).setTextVisible(false);
                z = true;
            }
            destCanvas.translate((-this.mView.getScrollX()) + (this.blurSizeOutline / 2), (-this.mView.getScrollY()) + (this.blurSizeOutline / 2));
            destCanvas.clipRect(rect);
            this.mView.draw(destCanvas);
            if (z) {
                ((FolderIcon) this.mView).setTextVisible(true);
            }
        }
        destCanvas.restore();
    }

    public Bitmap createDragBitmap() {
        int width = this.mView.getWidth();
        int height = this.mView.getHeight();
        View view = this.mView;
        if (view instanceof BubbleTextView) {
            Rect drawableBounds = getDrawableBounds(((BubbleTextView) view).getIcon());
            int iWidth = drawableBounds.width();
            height = drawableBounds.height();
            width = iWidth;
        } else if (view instanceof LauncherAppWidgetHostView) {
            final float scaleToFit = ((LauncherAppWidgetHostView) view).getScaleToFit();
            int width2 = (int) (this.mView.getWidth() * scaleToFit);
            int height2 = (int) (this.mView.getHeight() * scaleToFit);
            if (this.mView instanceof PendingAppWidgetHostView) {
                int i = this.blurSizeOutline;
                return BitmapRenderer.createHardwareBitmap(width2 + i, height2 + i, new BitmapRenderer() { // from class: com.android.launcher3.graphics.-$$Lambda$DragPreviewProvider$Z4RcRIKiebLV8mRZtsw9vhEVg9M
                    @Override // com.android.launcher3.icons.BitmapRenderer
                    public final void draw(Canvas canvas) {
                        this.f$0.lambda$createDragBitmap$0$DragPreviewProvider(scaleToFit, canvas);
                    }
                });
            }
            int i2 = this.blurSizeOutline;
            return BitmapRenderer.createSoftwareBitmap(width2 + i2, height2 + i2, new BitmapRenderer() { // from class: com.android.launcher3.graphics.-$$Lambda$DragPreviewProvider$_0JlnfdSskVVnFHUMCfQgYc8Td8
                @Override // com.android.launcher3.icons.BitmapRenderer
                public final void draw(Canvas canvas) {
                    this.f$0.lambda$createDragBitmap$1$DragPreviewProvider(scaleToFit, canvas);
                }
            });
        }
        int i3 = this.blurSizeOutline;
        return BitmapRenderer.createHardwareBitmap(width + i3, height + i3, new BitmapRenderer() { // from class: com.android.launcher3.graphics.-$$Lambda$DragPreviewProvider$k812zBEIC_lqaAtPaqZ_um7Ga68
            @Override // com.android.launcher3.icons.BitmapRenderer
            public final void draw(Canvas canvas) {
                this.f$0.lambda$createDragBitmap$2$DragPreviewProvider(canvas);
            }
        });
    }

    public /* synthetic */ void lambda$createDragBitmap$2$DragPreviewProvider(Canvas canvas) {
        lambda$createDragBitmap$1$DragPreviewProvider(canvas, 1.0f);
    }

    public final void generateDragOutline(Bitmap preview) {
        this.mOutlineGeneratorCallback = new OutlineGeneratorCallback(preview);
        new Handler(UiThreadHelper.getBackgroundLooper()).post(this.mOutlineGeneratorCallback);
    }

    protected static Rect getDrawableBounds(Drawable d) {
        Rect rect = new Rect();
        d.copyBounds(rect);
        if (rect.width() == 0 || rect.height() == 0) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        } else {
            rect.offsetTo(0, 0);
        }
        return rect;
    }

    public float getScaleAndPosition(Bitmap preview, int[] outPos) {
        float locationInDragLayer = Launcher.getLauncher(this.mView.getContext()).getDragLayer().getLocationInDragLayer(this.mView, outPos);
        View view = this.mView;
        if (view instanceof LauncherAppWidgetHostView) {
            locationInDragLayer /= ((LauncherAppWidgetHostView) view).getScaleToFit();
        }
        outPos[0] = Math.round(outPos[0] - ((preview.getWidth() - ((this.mView.getWidth() * locationInDragLayer) * this.mView.getScaleX())) / 2.0f));
        outPos[1] = Math.round((outPos[1] - (((1.0f - locationInDragLayer) * preview.getHeight()) / 2.0f)) - (this.previewPadding / 2));
        return locationInDragLayer;
    }

    protected Bitmap convertPreviewToAlphaBitmap(Bitmap preview) {
        return preview.copy(Bitmap.Config.ALPHA_8, true);
    }

    private class OutlineGeneratorCallback implements Runnable {
        private final Context mContext;
        private final Bitmap mPreviewSnapshot;

        OutlineGeneratorCallback(Bitmap preview) {
            this.mPreviewSnapshot = preview;
            this.mContext = DragPreviewProvider.this.mView.getContext();
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmapConvertPreviewToAlphaBitmap = DragPreviewProvider.this.convertPreviewToAlphaBitmap(this.mPreviewSnapshot);
            int width = bitmapConvertPreviewToAlphaBitmap.getWidth() * bitmapConvertPreviewToAlphaBitmap.getHeight();
            byte[] bArr = new byte[width];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            byteBufferWrap.rewind();
            bitmapConvertPreviewToAlphaBitmap.copyPixelsToBuffer(byteBufferWrap);
            for (int i = 0; i < width; i++) {
                if ((bArr[i] & 255) < 188) {
                    bArr[i] = 0;
                }
            }
            byteBufferWrap.rewind();
            bitmapConvertPreviewToAlphaBitmap.copyPixelsFromBuffer(byteBufferWrap);
            Paint paint = new Paint(3);
            Canvas canvas = new Canvas();
            paint.setMaskFilter(new BlurMaskFilter(DragPreviewProvider.this.blurSizeOutline, BlurMaskFilter.Blur.OUTER));
            Bitmap bitmapExtractAlpha = bitmapConvertPreviewToAlphaBitmap.extractAlpha(paint, new int[2]);
            paint.setMaskFilter(new BlurMaskFilter(this.mContext.getResources().getDimension(R.dimen.blur_size_thin_outline), BlurMaskFilter.Blur.OUTER));
            Bitmap bitmapExtractAlpha2 = bitmapConvertPreviewToAlphaBitmap.extractAlpha(paint, new int[2]);
            canvas.setBitmap(bitmapConvertPreviewToAlphaBitmap);
            canvas.drawColor(-16777216, PorterDuff.Mode.SRC_OUT);
            paint.setMaskFilter(new BlurMaskFilter(DragPreviewProvider.this.blurSizeOutline, BlurMaskFilter.Blur.NORMAL));
            Bitmap bitmapExtractAlpha3 = bitmapConvertPreviewToAlphaBitmap.extractAlpha(paint, new int[2]);
            paint.setMaskFilter(null);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.setBitmap(bitmapExtractAlpha3);
            canvas.drawBitmap(bitmapConvertPreviewToAlphaBitmap, -r4[0], -r4[1], paint);
            canvas.drawRect(0.0f, 0.0f, -r4[0], bitmapExtractAlpha3.getHeight(), paint);
            canvas.drawRect(0.0f, 0.0f, bitmapExtractAlpha3.getWidth(), -r4[1], paint);
            paint.setXfermode(null);
            canvas.setBitmap(bitmapConvertPreviewToAlphaBitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(bitmapExtractAlpha3, r4[0], r4[1], paint);
            canvas.drawBitmap(bitmapExtractAlpha, r6[0], r6[1], paint);
            canvas.drawBitmap(bitmapExtractAlpha2, r14[0], r14[1], paint);
            canvas.setBitmap(null);
            bitmapExtractAlpha2.recycle();
            bitmapExtractAlpha.recycle();
            bitmapExtractAlpha3.recycle();
            DragPreviewProvider.this.generatedDragOutline = bitmapConvertPreviewToAlphaBitmap;
        }
    }
}
