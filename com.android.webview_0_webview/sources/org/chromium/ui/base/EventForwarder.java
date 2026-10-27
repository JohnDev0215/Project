package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import defpackage.SN;
import java.util.Objects;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EventForwarder {
    public final boolean a;
    public long b;
    public float c;
    public float d;
    public int e;

    public EventForwarder(long j, boolean z) {
        this.b = j;
        this.a = z;
    }

    public static EventForwarder create(long j, boolean z) {
        return new EventForwarder(j, z);
    }

    private void destroy() {
        this.b = 0L;
    }

    public MotionEvent a(MotionEvent motionEvent) {
        if (!c()) {
            return motionEvent;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(this.c, this.d);
        return motionEventObtain;
    }

    public final float b() {
        Objects.requireNonNull(((WindowAndroid) N.M4_mlka_(this.b, this)).t);
        return 1.0f;
    }

    public final boolean c() {
        return (this.c == 0.0f && this.d == 0.0f) ? false : true;
    }

    public boolean d(DragEvent dragEvent, View view) {
        if (this.b == 0 || Build.VERSION.SDK_INT <= 23) {
            return false;
        }
        ClipDescription clipDescription = dragEvent.getClipDescription();
        String[] strArrFilterMimeTypes = clipDescription == null ? new String[0] : clipDescription.filterMimeTypes("text/*");
        if (dragEvent.getAction() == 1) {
            return strArrFilterMimeTypes != null && strArrFilterMimeTypes.length > 0 && this.a;
        }
        StringBuilder sb = new StringBuilder("");
        if (dragEvent.getAction() == 3) {
            ClipData clipData = dragEvent.getClipData();
            int itemCount = clipData.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                sb.append(clipData.getItemAt(i).coerceToStyledText(view.getContext()));
            }
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int x = (int) (dragEvent.getX() + this.c);
        int y = (int) (dragEvent.getY() + this.d);
        int i2 = iArr[0] + x;
        int i3 = iArr[1] + y;
        b();
        N.MZ1ZkPta(this.b, this, dragEvent.getAction(), (int) (x / 1.0f), (int) (y / 1.0f), (int) (i2 / 1.0f), (int) (i3 / 1.0f), strArrFilterMimeTypes, sb.toString());
        return true;
    }

    public boolean e(MotionEvent motionEvent) {
        int actionMasked;
        if (this.b == 0) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            this.e = motionEvent.getButtonState();
        }
        return N.MvdB06Zi(this.b, this, motionEvent, motionEvent.getEventTime());
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(android.view.MotionEvent r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.EventForwarder.f(android.view.MotionEvent):boolean");
    }

    public boolean g(MotionEvent motionEvent) {
        boolean z = false;
        if (motionEvent.getToolType(0) == 3) {
            int i = Build.VERSION.SDK_INT;
            boolean z2 = motionEvent.getButtonState() == 0 && (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 2 || motionEvent.getActionMasked() == 1);
            if (i >= 23 && !z2) {
                TraceEvent.x("sendMouseEvent", null);
                try {
                    if (c()) {
                        motionEvent = a(motionEvent);
                        z = true;
                    }
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked == 11 || actionMasked == 12) {
                        this.e = motionEvent.getButtonState();
                    }
                    return h(motionEvent);
                } finally {
                    if (z) {
                        motionEvent.recycle();
                    }
                    TraceEvent.A("sendMouseEvent");
                }
            }
        }
        return i(motionEvent, false);
    }

    public final boolean h(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9 || actionMasked == 10) {
            return false;
        }
        if (actionMasked == 0 || actionMasked == 1) {
            return true;
        }
        b();
        N.M$2oj6EQ(this.b, this, motionEvent.getEventTime(), actionMasked, motionEvent.getX() / 1.0f, motionEvent.getY() / 1.0f, motionEvent.getPointerId(0), motionEvent.getPressure(0), motionEvent.getOrientation(0), motionEvent.getAxisValue(25, 0), Build.VERSION.SDK_INT >= 23 ? motionEvent.getActionButton() : 0, motionEvent.getButtonState(), motionEvent.getMetaState(), motionEvent.getToolType(0));
        return true;
    }

    public final boolean i(MotionEvent motionEvent, boolean z) {
        MotionEvent motionEventA;
        long eventTime;
        boolean z2;
        TraceEvent.x("sendTouchEvent", null);
        try {
            if (motionEvent.getHistorySize() > 0) {
                motionEventA = motionEvent;
                eventTime = motionEventA.getHistoricalEventTime(0);
            } else {
                motionEventA = motionEvent;
                eventTime = motionEvent.getEventTime();
            }
            long j = eventTime;
            int iA = SN.a(motionEvent.getActionMasked());
            if (!(iA == 0 || iA == 1 || iA == 3 || iA == 2 || iA == 5 || iA == 6)) {
                return false;
            }
            if (c()) {
                motionEventA = a(motionEvent);
                z2 = true;
            } else {
                z2 = false;
            }
            int pointerCount = motionEventA.getPointerCount();
            float[] fArr = new float[2];
            fArr[0] = motionEventA.getTouchMajor();
            fArr[1] = pointerCount > 1 ? motionEventA.getTouchMajor(1) : 0.0f;
            float[] fArr2 = new float[2];
            fArr2[0] = motionEventA.getTouchMinor();
            fArr2[1] = pointerCount > 1 ? motionEventA.getTouchMinor(1) : 0.0f;
            for (int i = 0; i < 2; i++) {
                if (fArr[i] < fArr2[i]) {
                    float f = fArr[i];
                    fArr[i] = fArr2[i];
                    fArr2[i] = f;
                }
            }
            float x = pointerCount > 1 ? motionEventA.getX(1) : 0.0f;
            float y = pointerCount > 1 ? motionEventA.getY(1) : 0.0f;
            b();
            boolean zMcw1yi1C = N.Mcw1yi1C(this.b, this, motionEventA, j, iA, pointerCount, motionEventA.getHistorySize(), motionEventA.getActionIndex(), motionEventA.getX() / 1.0f, motionEventA.getY() / 1.0f, x / 1.0f, y / 1.0f, motionEventA.getPointerId(0), pointerCount > 1 ? motionEventA.getPointerId(1) : -1, fArr[0] / 1.0f, fArr[1] / 1.0f, fArr2[0] / 1.0f, fArr2[1] / 1.0f, motionEventA.getOrientation(), pointerCount > 1 ? motionEventA.getOrientation(1) : 0.0f, motionEventA.getAxisValue(25), pointerCount > 1 ? motionEventA.getAxisValue(25, 1) : 0.0f, motionEventA.getRawX() / 1.0f, motionEventA.getRawY() / 1.0f, motionEventA.getToolType(0), pointerCount > 1 ? motionEventA.getToolType(1) : 0, motionEventA.getButtonState(), motionEventA.getMetaState(), z);
            if (z2) {
                motionEventA.recycle();
            }
            return zMcw1yi1C;
        } finally {
            TraceEvent.A("sendTouchEvent");
        }
    }

    public void j(long j, float f, float f2, boolean z, boolean z2) {
        if (this.b == 0) {
            return;
        }
        N.MT1C98PL(this.b, this, j, f, f2, z, z2);
    }
}
