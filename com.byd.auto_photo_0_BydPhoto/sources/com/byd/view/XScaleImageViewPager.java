package com.byd.view;

import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import com.byd.util.e;
import com.byd.util.w;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class XScaleImageViewPager extends ViewPager {
    private static final int DELAY_TIME = 5000;
    private static final int MODE_DRAG = 0;
    private static final int MODE_ZOOM = 1;
    private static final int MSG_NEXT = 1;
    int currentIndex;
    private RectF imageRect;
    private XImageView imageView;
    private int lastX;
    private int lastY;
    private boolean loop;
    private Handler mHandler;
    private int mode;
    private ToBoundRun toBoundRun;

    private static class MHandle extends Handler {
        private WeakReference<XScaleImageViewPager> p;

        public MHandle(XScaleImageViewPager xScaleImageViewPager) {
            this.p = new WeakReference<>(xScaleImageViewPager);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (this.p.get() != null && message.what == 1) {
                this.p.get().nextPager();
            }
        }
    }

    private class ToBoundRun implements Runnable {
        int index;

        private ToBoundRun() {
        }

        @Override // java.lang.Runnable
        public void run() {
            XScaleImageViewPager.this.setCurrentItem(this.index, false);
        }
    }

    public XScaleImageViewPager(Context context) {
        super(context);
        init();
    }

    public XScaleImageViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBoundIndex(int i) {
        ToBoundRun toBoundRun;
        if (getAdapter() != null) {
            ToBoundRun toBoundRun2 = this.toBoundRun;
            if (toBoundRun2 == null) {
                this.toBoundRun = new ToBoundRun();
            } else {
                this.mHandler.removeCallbacks(toBoundRun2);
            }
            int count = getAdapter().getCount();
            if (i == 0) {
                toBoundRun = this.toBoundRun;
                toBoundRun.index = count - 2;
            } else {
                if (i != count - 1) {
                    return;
                }
                toBoundRun = this.toBoundRun;
                toBoundRun.index = 1;
            }
            this.mHandler.postDelayed(toBoundRun, 200L);
        }
    }

    private void init() {
        this.mHandler = new MHandle(this);
        if (e.E()) {
            return;
        }
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.byd.view.XScaleImageViewPager.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
                if (i == 0 && XScaleImageViewPager.this.loop) {
                    XScaleImageViewPager xScaleImageViewPager = XScaleImageViewPager.this;
                    xScaleImageViewPager.checkBoundIndex(xScaleImageViewPager.getCurrentItem());
                    XScaleImageViewPager.this.startLoop();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                XScaleImageViewPager xScaleImageViewPager = XScaleImageViewPager.this;
                int i2 = xScaleImageViewPager.currentIndex;
                if (i2 != i) {
                    XImageView xImageView = (XImageView) xScaleImageViewPager.findViewWithTag(Integer.valueOf(i2));
                    if (xImageView != null) {
                        w.c("onPageSelected , reset");
                        xImageView.resetImage();
                        xImageView.clearAllState();
                    }
                    XScaleImageViewPager.this.currentIndex = i;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextPager() {
        int currentItem = getCurrentItem() + 1;
        setCurrentItem(currentItem, true);
        checkBoundIndex(currentItem);
        this.mHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    public boolean isLoop() {
        return this.loop;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.loop) {
            stopLoop();
        }
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00a6  */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.XScaleImageViewPager.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int i, int i2) {
        try {
            super.onMeasure(i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        this.currentIndex = i;
        try {
            super.setCurrentItem(i);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("m_tag", "XScaleImageViewPager setCurrentItem exception : " + e.getMessage());
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        this.currentIndex = i;
        try {
            super.setCurrentItem(i, z);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("m_tag", "XScaleImageViewPager setCurrentItem2 exception : " + e.getMessage());
        }
    }

    public void setLoop(boolean z) {
        this.loop = z;
    }

    public void startLoop() {
        if (this.loop) {
            w.c("==start loop==");
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 5000L);
        }
    }

    public void stopLoop() {
        w.c("==stop loop==");
        this.mHandler.removeMessages(1);
    }
}
