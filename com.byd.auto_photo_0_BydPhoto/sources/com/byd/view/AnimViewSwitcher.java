package com.byd.view;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class AnimViewSwitcher extends FrameLayout {
    public static final int CENTER_LEFT_TOP = 1;
    public static final int CENTER_POINTER = 0;
    private static final float MAX_SCALE = 1.5f;
    private static final float MIN_SCALE = 0.5f;
    private boolean canSwitch;
    private Point center;
    private int currentIndex;
    private float downDis;
    private AnimItem[] items;
    private float lastDis;
    private long lastSwitchTime;
    private boolean multiTouch;
    private OnViewSwitchListener onViewSwitchListener;
    private int scaleCenterType;

    private class AnimItem {
        AnimItem above;
        AnimItem below;
        float scale = 1.0f;
        View view;

        public AnimItem(View view) {
            this.view = view;
        }

        public AnimItem setBelow(AnimItem animItem) {
            this.below = animItem;
            animItem.above = this;
            return this;
        }
    }

    private class AnimTask extends AsyncTask<Float, Float, Float> {
        private AnimTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Float doInBackground(Float... fArr) {
            float fFloatValue = fArr[0].floatValue();
            float f = 1.0f;
            float f2 = -0.1f;
            if (fFloatValue < 1.0f) {
                if (fFloatValue > 0.75f) {
                    f2 = 0.1f;
                } else {
                    f = AnimViewSwitcher.MIN_SCALE;
                }
            } else if (fFloatValue <= 1.0f) {
                f2 = 0.1f;
                f = 0.0f;
            } else if (fFloatValue >= 1.25f) {
                f = AnimViewSwitcher.MAX_SCALE;
                f2 = 0.1f;
            }
            if (f == 0.0f) {
                return Float.valueOf(fFloatValue);
            }
            while (Math.abs(fFloatValue - f) >= 0.1f) {
                try {
                    Thread.sleep(100L);
                    fFloatValue += f2;
                    publishProgress(Float.valueOf(fFloatValue));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return Float.valueOf(fFloatValue);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Float f) {
            AnimViewSwitcher.this.items[AnimViewSwitcher.this.currentIndex].scale = f.floatValue();
            AnimViewSwitcher animViewSwitcher = AnimViewSwitcher.this;
            int iAfterAnim = animViewSwitcher.afterAnim(animViewSwitcher.currentIndex);
            AnimViewSwitcher.this.log("after anim , current sc : " + AnimViewSwitcher.this.items[AnimViewSwitcher.this.currentIndex].scale + " index : " + AnimViewSwitcher.this.currentIndex + " " + iAfterAnim);
            AnimViewSwitcher.access$112(AnimViewSwitcher.this, iAfterAnim);
            if (AnimViewSwitcher.this.onViewSwitchListener != null) {
                AnimViewSwitcher.this.onViewSwitchListener.onViewSwitched(AnimViewSwitcher.this.currentIndex);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Float... fArr) {
            AnimViewSwitcher.this.doAnim(fArr[0].floatValue(), AnimViewSwitcher.this.currentIndex);
        }
    }

    public interface OnViewSwitchListener {
        void onPointerDown();

        void onPointerUp();

        void onViewSwitched(int i);
    }

    private class SwitchTask extends AsyncTask<Float, Float, Float> {
        private int fromIndex;
        private int toIndex;

        private SwitchTask(int i, int i2) {
            this.fromIndex = i;
            this.toIndex = i2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Float doInBackground(Float... fArr) {
            float fFloatValue = fArr[0].floatValue();
            float fFloatValue2 = fArr[1].floatValue();
            float f = fFloatValue > fFloatValue2 ? -0.1f : 0.1f;
            AnimViewSwitcher.this.log("SwitchTask " + fFloatValue + " " + fFloatValue2 + " " + this.fromIndex + " " + this.toIndex);
            while (Math.abs(fFloatValue - fFloatValue2) >= 0.1f) {
                try {
                    Thread.sleep(50L);
                    fFloatValue += f;
                    publishProgress(Float.valueOf(fFloatValue));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            AnimViewSwitcher.this.log("SwitchTask return : " + fFloatValue);
            return Float.valueOf(fFloatValue);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Float f) {
            float f2;
            if (AnimViewSwitcher.this.currentIndex < this.toIndex) {
                f2 = AnimViewSwitcher.MIN_SCALE;
                AnimViewSwitcher.access$108(AnimViewSwitcher.this);
            } else {
                f2 = AnimViewSwitcher.MAX_SCALE;
                AnimViewSwitcher.access$110(AnimViewSwitcher.this);
                if (AnimViewSwitcher.this.currentIndex < 0) {
                    AnimViewSwitcher.this.currentIndex = 0;
                }
            }
            AnimViewSwitcher.this.items[this.fromIndex].scale = f2;
            AnimViewSwitcher animViewSwitcher = AnimViewSwitcher.this;
            animViewSwitcher.scaleView(animViewSwitcher.items[this.fromIndex]);
            AnimViewSwitcher.this.items[this.fromIndex].view.setVisibility(8);
            if (AnimViewSwitcher.this.currentIndex != this.toIndex) {
                AnimViewSwitcher animViewSwitcher2 = AnimViewSwitcher.this;
                animViewSwitcher2.new SwitchTask(animViewSwitcher2.currentIndex, this.toIndex).execute(Float.valueOf(1.0f), Float.valueOf(f2));
                return;
            }
            AnimViewSwitcher.this.items[AnimViewSwitcher.this.currentIndex].scale = 1.0f;
            AnimViewSwitcher animViewSwitcher3 = AnimViewSwitcher.this;
            animViewSwitcher3.scaleView(animViewSwitcher3.items[AnimViewSwitcher.this.currentIndex]);
            AnimViewSwitcher.this.items[AnimViewSwitcher.this.currentIndex].view.setVisibility(0);
            if (AnimViewSwitcher.this.onViewSwitchListener != null) {
                AnimViewSwitcher.this.onViewSwitchListener.onViewSwitched(AnimViewSwitcher.this.currentIndex);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Float... fArr) {
            AnimViewSwitcher.this.doAnim(fArr[0].floatValue(), this.fromIndex);
        }
    }

    public AnimViewSwitcher(Context context) {
        this(context, null);
    }

    public AnimViewSwitcher(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AnimViewSwitcher(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.center = new Point();
        setClickable(true);
        setLongClickable(true);
        setFocusable(true);
    }

    static /* synthetic */ int access$108(AnimViewSwitcher animViewSwitcher) {
        int i = animViewSwitcher.currentIndex;
        animViewSwitcher.currentIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$110(AnimViewSwitcher animViewSwitcher) {
        int i = animViewSwitcher.currentIndex;
        animViewSwitcher.currentIndex = i - 1;
        return i;
    }

    static /* synthetic */ int access$112(AnimViewSwitcher animViewSwitcher, int i) {
        int i2 = animViewSwitcher.currentIndex + i;
        animViewSwitcher.currentIndex = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int afterAnim(int i) {
        AnimItem animItem;
        AnimItem animItem2 = this.items[i];
        log("afterAnim " + animItem2.scale + " index:" + i);
        int i2 = 1;
        if (Math.abs(animItem2.scale - 1.0f) <= 0.25f) {
            AnimItem animItem3 = animItem2.below;
            if (animItem3 != null) {
                animItem3.scale = MIN_SCALE;
                scaleView(animItem3);
                animItem2.below.view.setVisibility(8);
            }
            AnimItem animItem4 = animItem2.above;
            if (animItem4 != null) {
                animItem4.scale = MAX_SCALE;
                scaleView(animItem4);
                animItem2.above.view.setVisibility(8);
            }
            animItem2.scale = 1.0f;
            scaleView(animItem2);
            return 0;
        }
        if (animItem2.scale < 1.0f) {
            AnimItem animItem5 = animItem2.below;
            if (animItem5 != null) {
                animItem5.scale = MIN_SCALE;
                scaleView(animItem5);
                animItem2.below.view.setVisibility(8);
            }
            if (i >= this.items.length - 1) {
                if (animItem2.scale != 1.0f) {
                    animItem2.scale = 1.0f;
                    scaleView(animItem2);
                }
                return 0;
            }
            animItem2.scale = MIN_SCALE;
            scaleView(animItem2);
            animItem2.view.setVisibility(8);
            animItem = animItem2.above;
        } else {
            if (i <= 0) {
                AnimItem animItem6 = animItem2.above;
                if (animItem6 != null) {
                    animItem6.scale = MAX_SCALE;
                    scaleView(animItem6);
                    animItem2.above.view.setVisibility(8);
                }
                if (animItem2.scale != 1.0f) {
                    animItem2.scale = 1.0f;
                    scaleView(animItem2);
                }
                return 0;
            }
            AnimItem animItem7 = animItem2.above;
            if (animItem7 != null) {
                animItem7.scale = MAX_SCALE;
                scaleView(animItem7);
                animItem2.above.view.setVisibility(8);
            }
            i2 = -1;
            animItem2.scale = MAX_SCALE;
            scaleView(animItem2);
            animItem2.view.setVisibility(8);
            animItem = animItem2.below;
        }
        animItem.scale = 1.0f;
        scaleView(animItem);
        return i2;
    }

    private void autoAnimToTarget() {
        log("==autoAnimToTarget==" + this.currentIndex);
        this.lastSwitchTime = System.currentTimeMillis();
        new AnimTask().execute(Float.valueOf(this.items[this.currentIndex].scale));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAnim(float f, int i) {
        AnimItem animItem;
        AnimItem animItem2;
        AnimItem[] animItemArr = this.items;
        if (animItemArr == null) {
            return;
        }
        AnimItem animItem3 = animItemArr[i];
        animItem3.scale = f;
        if (f < 1.0f) {
            AnimItem animItem4 = animItem3.above;
            if (animItem4 == null) {
                return;
            }
            if (animItem4.view.getVisibility() != 0) {
                animItem3.above.view.setAlpha(0.0f);
                setViewScale(animItem3.above.view, MAX_SCALE);
                AnimItem animItem5 = animItem3.above;
                animItem5.scale = MAX_SCALE;
                animItem5.view.setVisibility(0);
            }
            scaleView(animItem3);
            animItem2 = animItem3.above;
        } else {
            if (f <= 1.0f || (animItem = animItem3.below) == null) {
                return;
            }
            if (animItem.view.getVisibility() != 0) {
                animItem3.below.view.setAlpha(0.0f);
                AnimItem animItem6 = animItem3.below;
                animItem6.scale = MIN_SCALE;
                setViewScale(animItem6.view, MIN_SCALE);
                animItem3.below.view.setVisibility(0);
            }
            scaleView(animItem3);
            animItem2 = animItem3.below;
            f -= 1.0f;
        }
        animItem2.scale = f + MIN_SCALE;
        scaleView(animItem2);
    }

    private void getCenter(MotionEvent motionEvent) {
        if (this.scaleCenterType == 1) {
            return;
        }
        this.center.set((int) ((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f), (int) ((motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f));
    }

    private float getDistance(MotionEvent motionEvent) {
        return (float) Math.sqrt(Math.pow(motionEvent.getX(0) - motionEvent.getX(1), 2.0d) + Math.pow(motionEvent.getY(0) - motionEvent.getY(1), 2.0d));
    }

    private void initAnimItems(int i) {
        this.items = new AnimItem[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.items[i2] = new AnimItem(getChildAt(i2));
            if (i2 > 0) {
                AnimItem[] animItemArr = this.items;
                animItemArr[i2].setBelow(animItemArr[i2 - 1]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("m_tag", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleView(AnimItem animItem) {
        setViewScale(animItem.view, animItem.scale);
        float f = animItem.scale;
        float f2 = 1.0f;
        if (f != 1.0f) {
            float fAbs = 1.0f - (Math.abs(f - 1.0f) / 0.4f);
            if (fAbs < 0.0f) {
                f2 = 0.0f;
            } else if (fAbs <= 1.0f) {
                f2 = fAbs;
            }
        }
        animItem.view.setAlpha(f2);
    }

    private void setViewScale(View view, float f) {
        view.setPivotX(this.center.x);
        view.setPivotY(this.center.y);
        view.setScaleX(f);
        view.setScaleY(f);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.canSwitch
            if (r0 != 0) goto L9
            boolean r5 = super.dispatchTouchEvent(r5)
            return r5
        L9:
            int r0 = r5.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L9a
            r3 = 2
            if (r0 == r3) goto L61
            r3 = 3
            if (r0 == r3) goto L9a
            r3 = 5
            if (r0 == r3) goto L32
            r3 = 6
            if (r0 == r3) goto L21
            goto Lab
        L21:
            boolean r5 = r4.multiTouch
            if (r5 == 0) goto L31
            r4.multiTouch = r1
            r4.autoAnimToTarget()
            com.byd.view.AnimViewSwitcher$OnViewSwitchListener r5 = r4.onViewSwitchListener
            if (r5 == 0) goto L31
            r5.onPointerUp()
        L31:
            return r2
        L32:
            float r5 = r4.getDistance(r5)
            r4.downDis = r5
            r0 = 1120403456(0x42c80000, float:100.0)
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 < 0) goto L5e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "ACTION_POINTER_DOWN : "
            r5.append(r0)
            int r0 = r4.currentIndex
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.log(r5)
            r4.multiTouch = r2
            com.byd.view.AnimViewSwitcher$OnViewSwitchListener r5 = r4.onViewSwitchListener
            if (r5 == 0) goto L60
            r5.onPointerDown()
            goto L60
        L5e:
            r4.multiTouch = r1
        L60:
            return r2
        L61:
            boolean r0 = r4.multiTouch
            if (r0 != 0) goto L66
            goto Lab
        L66:
            int r0 = r5.getPointerCount()
            if (r0 == r3) goto L72
            r4.multiTouch = r1
            r4.autoAnimToTarget()
            goto Lab
        L72:
            r4.getCenter(r5)
            float r5 = r4.getDistance(r5)
            float r0 = r4.lastDis
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L80
            return r2
        L80:
            r4.lastDis = r5
            float r0 = r4.downDis
            float r5 = r5 / r0
            r0 = 1056964608(0x3f000000, float:0.5)
            int r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            r3 = 1069547520(0x3fc00000, float:1.5)
            if (r1 >= 0) goto L8f
            r5 = r0
            goto L94
        L8f:
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 <= 0) goto L94
            r5 = r3
        L94:
            int r0 = r4.currentIndex
            r4.doAnim(r5, r0)
            return r2
        L9a:
            boolean r0 = r4.multiTouch
            if (r0 == 0) goto Lab
            r4.multiTouch = r1
            r4.autoAnimToTarget()
            com.byd.view.AnimViewSwitcher$OnViewSwitchListener r5 = r4.onViewSwitchListener
            if (r5 == 0) goto Laa
            r5.onPointerUp()
        Laa:
            return r2
        Lab:
            boolean r5 = super.dispatchTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.AnimViewSwitcher.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public boolean isCanSwitch() {
        return this.canSwitch;
    }

    public boolean isMultiTouch() {
        return this.multiTouch;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.canSwitch) {
            int childCount = getChildCount();
            if (this.items != null || childCount <= 0) {
                return;
            }
            initAnimItems(childCount);
        }
    }

    public void setCanSwitch(boolean z) {
        log("setCanSwitch : " + z);
        this.canSwitch = z;
    }

    public void setOnViewSwitchListener(OnViewSwitchListener onViewSwitchListener) {
        this.onViewSwitchListener = onViewSwitchListener;
    }

    public void setScaleCenterType(int i) {
        this.scaleCenterType = i;
    }

    public void switchTo(int i, boolean z) {
        switchTo(i, z, false);
    }

    public void switchTo(int i, boolean z, boolean z2) {
        int i2;
        int i3;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastSwitchTime <= 250) {
            str = "switch too fast !!!";
        } else {
            this.lastSwitchTime = jCurrentTimeMillis;
            if (!z2 && !this.canSwitch) {
                str = "can not switch";
            } else {
                if (i != this.currentIndex) {
                    log("switch from :" + this.currentIndex + " to : " + i);
                    float f = MIN_SCALE;
                    if (z) {
                        AnimItem[] animItemArr = this.items;
                        int i4 = this.currentIndex;
                        float f2 = animItemArr[i4].scale;
                        if (i4 >= i) {
                            f = 1.5f;
                        }
                        new SwitchTask(i4, i).execute(Float.valueOf(f2), Float.valueOf(f));
                        return;
                    }
                    do {
                        int i5 = this.currentIndex;
                        if (i5 == i) {
                            return;
                        }
                        AnimItem animItem = this.items[i5];
                        log("current : " + this.currentIndex);
                        if (this.currentIndex < i) {
                            animItem.scale = MIN_SCALE;
                            scaleView(animItem);
                            i2 = this.currentIndex + 1;
                        } else {
                            animItem.scale = MAX_SCALE;
                            scaleView(animItem);
                            i2 = this.currentIndex - 1;
                        }
                        this.currentIndex = i2;
                        log(this.currentIndex + "->" + animItem.scale + " " + i);
                        animItem.view.setVisibility(8);
                        i3 = this.currentIndex;
                    } while (i3 != i);
                    AnimItem animItem2 = this.items[i3];
                    animItem2.scale = 1.0f;
                    animItem2.view.setVisibility(0);
                    scaleView(animItem2);
                    OnViewSwitchListener onViewSwitchListener = this.onViewSwitchListener;
                    if (onViewSwitchListener != null) {
                        onViewSwitchListener.onViewSwitched(this.currentIndex);
                        return;
                    }
                    return;
                }
                str = "switchTo fail : " + i;
            }
        }
        log(str);
    }
}
