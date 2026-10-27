package com.byd.imageedit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import com.byd.drawable.BaseDressDrawable;
import com.byd.drawable.TextDressDrawable;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class DressUpImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f2865a = ViewConfiguration.getDoubleTapTimeout();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LinkedList<BaseDressDrawable> f2866b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2867c;
    private BaseDressDrawable d;
    private BaseDressDrawable e;
    private b f;

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<DressUpImageView> f2868a;

        public a(DressUpImageView dressUpImageView) {
            this.f2868a = new WeakReference<>(dressUpImageView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<DressUpImageView> weakReference = this.f2868a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            int i = message.what;
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                removeMessages(3);
                return;
            }
            removeCallbacksAndMessages(null);
            this.f2868a.get().performClick();
            BaseDressDrawable baseDressDrawable = this.f2868a.get().e;
            Log.e("m_tag", "click : " + baseDressDrawable);
            if (baseDressDrawable == null || this.f2868a.get().f == null) {
                return;
            }
            this.f2868a.get().f.b(baseDressDrawable);
        }
    }

    public interface b {
        void a(BaseDressDrawable baseDressDrawable, int i);

        void b(BaseDressDrawable baseDressDrawable);
    }

    public DressUpImageView(Context context) {
        this(context, null);
    }

    public DressUpImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2867c = new a(this);
        this.f2866b = new LinkedList<>();
    }

    private void e(Canvas canvas) {
        if (this.f2866b.isEmpty()) {
            return;
        }
        Iterator<BaseDressDrawable> it = this.f2866b.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas);
        }
    }

    private void h(Rect rect, float f, float f2) {
        rect.set((int) (rect.left / f), (int) (rect.top / f2), (int) (rect.right / f), (int) (rect.bottom / f2));
    }

    private void setControllMode(boolean z) {
        Iterator<BaseDressDrawable> it = this.f2866b.iterator();
        while (it.hasNext()) {
            it.next().setControllMode(z);
        }
    }

    public void c(BaseDressDrawable baseDressDrawable) {
        d();
        this.f2866b.add(baseDressDrawable);
        baseDressDrawable.setControllMode(true);
        invalidate();
        this.e = baseDressDrawable;
    }

    public void d() {
        if (this.f2866b.size() == 0) {
            return;
        }
        Iterator<BaseDressDrawable> it = this.f2866b.iterator();
        while (it.hasNext()) {
            it.next().setControllMode(false);
        }
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            int r0 = (int) r0
            float r1 = r8.getY()
            int r1 = (int) r1
            int r8 = r8.getAction()
            r2 = 4
            r3 = 3
            r4 = 1
            if (r8 == 0) goto L36
            if (r8 == r4) goto L26
            r5 = 2
            if (r8 == r5) goto L1b
            if (r8 == r3) goto L26
            goto L8a
        L1b:
            com.byd.drawable.BaseDressDrawable r8 = r7.d
            if (r8 == 0) goto L8a
            r8.doActionMove(r0, r1)
            r7.invalidate()
            goto L8a
        L26:
            android.os.Handler r8 = r7.f2867c
            r8.removeMessages(r2)
            com.byd.drawable.BaseDressDrawable r8 = r7.d
            if (r8 == 0) goto L8a
            r8.doActionUp(r0, r1)
            r8 = 0
            r7.d = r8
            goto L8a
        L36:
            r7.d()
            java.util.LinkedList<com.byd.drawable.BaseDressDrawable> r8 = r7.f2866b
            int r5 = r8.size()
            java.util.ListIterator r8 = r8.listIterator(r5)
        L43:
            boolean r5 = r8.hasPrevious()
            if (r5 == 0) goto L5f
            java.lang.Object r5 = r8.previous()
            com.byd.drawable.BaseDressDrawable r5 = (com.byd.drawable.BaseDressDrawable) r5
            int r6 = r5.checkControllerPos(r0, r1)
            if (r6 <= 0) goto L43
            r7.d = r5
            r5.setControllMode(r4)
            com.byd.drawable.BaseDressDrawable r8 = r7.d
            r8.doActionDown(r0, r1, r6)
        L5f:
            r7.invalidate()
            com.byd.drawable.BaseDressDrawable r8 = r7.e
            if (r8 == 0) goto L86
            boolean r8 = r8.isControllMode()
            if (r8 == 0) goto L86
            com.byd.drawable.BaseDressDrawable r8 = r7.e
            com.byd.drawable.BaseDressDrawable r0 = r7.d
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L86
            android.os.Handler r8 = r7.f2867c
            int r0 = com.byd.imageedit.DressUpImageView.f2865a
            long r0 = (long) r0
            r8.sendEmptyMessageDelayed(r3, r0)
            android.os.Handler r8 = r7.f2867c
            r0 = 150(0x96, double:7.4E-322)
            r8.sendEmptyMessageDelayed(r2, r0)
            goto L8a
        L86:
            com.byd.drawable.BaseDressDrawable r8 = r7.d
            r7.e = r8
        L8a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.imageedit.DressUpImageView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void f(BaseDressDrawable baseDressDrawable, int i) {
        this.f2867c.removeCallbacksAndMessages(null);
        b bVar = this.f;
        if (bVar != null) {
            bVar.a(baseDressDrawable, i);
        }
    }

    public void g(BaseDressDrawable baseDressDrawable) {
        if (this.f2866b.remove(baseDressDrawable)) {
            invalidate();
        }
    }

    public Bitmap getBitmap() {
        setControllMode(false);
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(((BitmapDrawable) getDrawable()).getBitmap(), 0.0f, 0.0f, (Paint) null);
        if (!this.f2866b.isEmpty()) {
            Rect imageInfo = getImageInfo();
            for (BaseDressDrawable baseDressDrawable : this.f2866b) {
                Rect rect = new Rect(baseDressDrawable.getBounds());
                int iWidth = rect.width();
                int iHeight = rect.height();
                int i = rect.left - imageInfo.left;
                rect.left = i;
                int i2 = rect.top - imageInfo.top;
                rect.top = i2;
                rect.right = i + iWidth;
                rect.bottom = i2 + iHeight;
                h(rect, fArr[0], fArr[4]);
                baseDressDrawable.setBounds(rect);
                if (baseDressDrawable instanceof TextDressDrawable) {
                    baseDressDrawable.onScale(1.0f / fArr[0]);
                }
                baseDressDrawable.draw(canvas);
            }
        }
        return bitmapCreateBitmap;
    }

    public int getDrawableCounts() {
        return this.f2866b.size();
    }

    public LinkedList<BaseDressDrawable> getDressDrawables() {
        return this.f2866b;
    }

    public Rect getImageInfo() {
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        float fWidth = bounds.width() * fArr[0];
        float fHeight = bounds.height() * fArr[4];
        float f = fArr[2];
        float f2 = fArr[5];
        return new Rect((int) f, (int) f2, (int) (fWidth + f), (int) (fHeight + f2));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        e(canvas);
    }

    public void setOnDressUpListener(b bVar) {
        this.f = bVar;
    }
}
