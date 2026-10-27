package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.internal.view.SupportMenu;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.PointerEvent;
import com.facebook.react.uimanager.events.PointerEventHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class JSPointerDispatcher {
    private Map<Integer, float[]> mLastEventCoordinatesByPointerId;
    private Map<Integer, List<TouchTargetHelper.ViewTarget>> mLastHitPathByPointerId;
    private final ViewGroup mRootViewGroup;
    private int mChildHandlingNativeGesture = -1;
    private int mPrimaryPointerId = -1;
    private int mCoalescingKey = 0;
    private int mLastButtonState = 0;

    public JSPointerDispatcher(ViewGroup viewGroup) {
        this.mRootViewGroup = viewGroup;
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (this.mChildHandlingNativeGesture != -1 || view == null) {
            return;
        }
        dispatchCancelEvent(motionEvent, eventDispatcher);
        this.mChildHandlingNativeGesture = view.getId();
    }

    public void onChildEndedNativeGesture() {
        this.mChildHandlingNativeGesture = -1;
    }

    private void onUp(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        List<TouchTargetHelper.ViewTarget> list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId()));
        boolean zSupportsHover = PointerEventHelper.supportsHover(motionEvent);
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.UP, PointerEventHelper.EVENT.UP_CAPTURE)) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerUp", i, pointerEventState, motionEvent));
        }
        if (!zSupportsHover) {
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OUT, PointerEventHelper.EVENT.OUT_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerOut", i, pointerEventState, motionEvent));
            }
            dispatchEventForViewTargets("topPointerLeave", pointerEventState, motionEvent, filterByShouldDispatch(list, PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, false), eventDispatcher);
        }
        if (motionEvent.getActionMasked() == 1) {
            this.mPrimaryPointerId = -1;
        }
    }

    private void incrementCoalescingKey() {
        this.mCoalescingKey = (this.mCoalescingKey + 1) % Integer.MAX_VALUE;
    }

    private short getCoalescingKey() {
        return (short) (this.mCoalescingKey & SupportMenu.USER_MASK);
    }

    private void onDown(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        List<TouchTargetHelper.ViewTarget> list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId()));
        incrementCoalescingKey();
        if (!PointerEventHelper.supportsHover(motionEvent)) {
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OVER, PointerEventHelper.EVENT.OVER_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerOver", i, pointerEventState, motionEvent));
            }
            List<TouchTargetHelper.ViewTarget> listFilterByShouldDispatch = filterByShouldDispatch(list, PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, false);
            Collections.reverse(listFilterByShouldDispatch);
            dispatchEventForViewTargets("topPointerEnter", pointerEventState, motionEvent, listFilterByShouldDispatch, eventDispatcher);
        }
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.DOWN, PointerEventHelper.EVENT.DOWN_CAPTURE)) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerDown", i, pointerEventState, motionEvent));
        }
    }

    private PointerEvent.PointerEventState createEventState(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            float[] fArr = new float[2];
            float y = motionEvent.getY(i);
            float[] fArr2 = {motionEvent.getX(i), y};
            List<TouchTargetHelper.ViewTarget> listFindTargetPathAndCoordinatesForTouch = TouchTargetHelper.findTargetPathAndCoordinatesForTouch(fArr2[0], y, this.mRootViewGroup, fArr);
            int pointerId = motionEvent.getPointerId(i);
            map.put(Integer.valueOf(pointerId), fArr);
            map2.put(Integer.valueOf(pointerId), listFindTargetPathAndCoordinatesForTouch);
            map3.put(Integer.valueOf(pointerId), fArr2);
        }
        return new PointerEvent.PointerEventState(this.mPrimaryPointerId, motionEvent.getPointerId(actionIndex), this.mLastButtonState, UIManagerHelper.getSurfaceId(this.mRootViewGroup), map, map2, map3);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleMotionEvent(android.view.MotionEvent r6, com.facebook.react.uimanager.events.EventDispatcher r7) {
        /*
            r5 = this;
            int r0 = r5.mChildHandlingNativeGesture
            r1 = -1
            if (r0 == r1) goto L6
            return
        L6:
            int r0 = r6.getActionMasked()
            r1 = 0
            if (r0 != 0) goto L13
            int r2 = r6.getPointerId(r1)
            r5.mPrimaryPointerId = r2
        L13:
            com.facebook.react.uimanager.events.PointerEvent$PointerEventState r2 = r5.createEventState(r6)
            java.util.Map r3 = r2.getHitPathByPointerId()
            int r4 = r2.getActivePointerId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r3 = r3.get(r4)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto Lac
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L33
            goto Lac
        L33:
            java.lang.Object r1 = r3.get(r1)
            com.facebook.react.uimanager.TouchTargetHelper$ViewTarget r1 = (com.facebook.react.uimanager.TouchTargetHelper.ViewTarget) r1
            int r1 = r1.getViewId()
            if (r0 == 0) goto L97
            r4 = 1
            if (r0 == r4) goto L90
            r4 = 2
            if (r0 == r4) goto L78
            r3 = 3
            if (r0 == r3) goto L74
            r3 = 5
            if (r0 == r3) goto L97
            r3 = 6
            if (r0 == r3) goto L90
            r3 = 7
            if (r0 == r3) goto L70
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Warning : Motion Event was ignored. Action="
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = " Target="
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = "ReactNative"
            com.facebook.common.logging.FLog.w(r7, r6)
            return
        L70:
            r5.onMove(r1, r2, r6, r7)
            goto L9a
        L74:
            r5.dispatchCancelEvent(r2, r6, r7)
            goto L9a
        L78:
            com.facebook.react.uimanager.events.PointerEventHelper$EVENT r0 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.MOVE
            com.facebook.react.uimanager.events.PointerEventHelper$EVENT r4 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.MOVE_CAPTURE
            boolean r0 = isAnyoneListeningForBubblingEvent(r3, r0, r4)
            if (r0 == 0) goto L9a
            short r0 = r5.getCoalescingKey()
            java.lang.String r3 = "topPointerMove"
            com.facebook.react.uimanager.events.PointerEvent r0 = com.facebook.react.uimanager.events.PointerEvent.obtain(r3, r1, r2, r6, r0)
            r7.dispatchEvent(r0)
            goto L9a
        L90:
            r5.incrementCoalescingKey()
            r5.onUp(r1, r2, r6, r7)
            goto L9a
        L97:
            r5.onDown(r1, r2, r6, r7)
        L9a:
            java.util.Map r7 = r2.getHitPathByPointerId()
            r5.mLastHitPathByPointerId = r7
            java.util.Map r7 = r2.getEventCoordinatesByPointerId()
            r5.mLastEventCoordinatesByPointerId = r7
            int r6 = r6.getButtonState()
            r5.mLastButtonState = r6
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.JSPointerDispatcher.handleMotionEvent(android.view.MotionEvent, com.facebook.react.uimanager.events.EventDispatcher):void");
    }

    private static boolean isAnyoneListeningForBubblingEvent(List<TouchTargetHelper.ViewTarget> list, PointerEventHelper.EVENT event, PointerEventHelper.EVENT event2) {
        for (TouchTargetHelper.ViewTarget viewTarget : list) {
            if (PointerEventHelper.isListening(viewTarget.getView(), event) || PointerEventHelper.isListening(viewTarget.getView(), event2)) {
                return true;
            }
        }
        return false;
    }

    private static List<TouchTargetHelper.ViewTarget> filterByShouldDispatch(List<TouchTargetHelper.ViewTarget> list, PointerEventHelper.EVENT event, PointerEventHelper.EVENT event2, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        if (z) {
            return arrayList;
        }
        boolean z2 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            View view = list.get(size).getView();
            if (!z2 && !PointerEventHelper.isListening(view, event2) && !PointerEventHelper.isListening(view, event)) {
                arrayList.remove(size);
            } else if (!z2 && PointerEventHelper.isListening(view, event2)) {
                z2 = true;
            }
        }
        return arrayList;
    }

    private void dispatchEventForViewTargets(String str, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, List<TouchTargetHelper.ViewTarget> list, EventDispatcher eventDispatcher) {
        Iterator<TouchTargetHelper.ViewTarget> it = list.iterator();
        while (it.hasNext()) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(str, it.next().getViewId(), pointerEventState, motionEvent));
        }
    }

    private void onMove(int i, PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        List<TouchTargetHelper.ViewTarget> arrayList;
        int activePointerId = pointerEventState.getActivePointerId();
        float[] fArr = pointerEventState.getEventCoordinatesByPointerId().get(Integer.valueOf(activePointerId));
        List<TouchTargetHelper.ViewTarget> list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(activePointerId));
        Map<Integer, List<TouchTargetHelper.ViewTarget>> map = this.mLastHitPathByPointerId;
        if (map != null && map.containsKey(Integer.valueOf(activePointerId))) {
            arrayList = this.mLastHitPathByPointerId.get(Integer.valueOf(activePointerId));
        } else {
            arrayList = new ArrayList<>();
        }
        Map<Integer, float[]> map2 = this.mLastEventCoordinatesByPointerId;
        float[] fArr2 = (map2 == null || !map2.containsKey(Integer.valueOf(activePointerId))) ? new float[]{0.0f, 0.0f} : this.mLastEventCoordinatesByPointerId.get(Integer.valueOf(activePointerId));
        if (Math.abs(fArr2[0] - fArr[0]) > 0.1f || Math.abs(fArr2[1] - fArr[1]) > 0.1f) {
            boolean z = false;
            int i2 = 0;
            boolean z2 = false;
            while (i2 < Math.min(list.size(), arrayList.size()) && list.get((list.size() - 1) - i2).equals(arrayList.get((arrayList.size() - 1) - i2))) {
                View view = list.get((list.size() - 1) - i2).getView();
                if (!z2 && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.ENTER_CAPTURE)) {
                    z2 = true;
                }
                if (!z && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.LEAVE_CAPTURE)) {
                    z = true;
                }
                i2++;
            }
            if (i2 < Math.max(list.size(), arrayList.size())) {
                incrementCoalescingKey();
                if (arrayList.size() > 0) {
                    int viewId = arrayList.get(0).getViewId();
                    if (isAnyoneListeningForBubblingEvent(arrayList, PointerEventHelper.EVENT.OUT, PointerEventHelper.EVENT.OUT_CAPTURE)) {
                        eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerOut", viewId, pointerEventState, motionEvent));
                    }
                    List<TouchTargetHelper.ViewTarget> listFilterByShouldDispatch = filterByShouldDispatch(arrayList.subList(0, arrayList.size() - i2), PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, z);
                    if (listFilterByShouldDispatch.size() > 0) {
                        dispatchEventForViewTargets("topPointerLeave", pointerEventState, motionEvent, listFilterByShouldDispatch, eventDispatcher);
                    }
                }
                if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.OVER, PointerEventHelper.EVENT.OVER_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerOver", i, pointerEventState, motionEvent));
                }
                List<TouchTargetHelper.ViewTarget> listFilterByShouldDispatch2 = filterByShouldDispatch(list.subList(0, list.size() - i2), PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, z2);
                if (listFilterByShouldDispatch2.size() > 0) {
                    Collections.reverse(listFilterByShouldDispatch2);
                    dispatchEventForViewTargets("topPointerEnter", pointerEventState, motionEvent, listFilterByShouldDispatch2, eventDispatcher);
                }
            }
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.MOVE, PointerEventHelper.EVENT.MOVE_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain("topPointerMove", i, pointerEventState, motionEvent, getCoalescingKey()));
            }
        }
    }

    private void dispatchCancelEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        Assertions.assertCondition(this.mChildHandlingNativeGesture == -1, "Expected to not have already sent a cancel for this gesture");
        dispatchCancelEvent(createEventState(motionEvent), motionEvent, eventDispatcher);
    }

    private void dispatchCancelEvent(PointerEvent.PointerEventState pointerEventState, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        Assertions.assertCondition(this.mChildHandlingNativeGesture == -1, "Expected to not have already sent a cancel for this gesture");
        List<TouchTargetHelper.ViewTarget> list = pointerEventState.getHitPathByPointerId().get(Integer.valueOf(pointerEventState.getActivePointerId()));
        if (list.isEmpty()) {
            return;
        }
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.CANCEL, PointerEventHelper.EVENT.CANCEL_CAPTURE)) {
            ((EventDispatcher) Assertions.assertNotNull(eventDispatcher)).dispatchEvent(PointerEvent.obtain("topPointerCancel", list.get(0).getViewId(), pointerEventState, motionEvent));
        }
        dispatchEventForViewTargets("topPointerLeave", pointerEventState, motionEvent, filterByShouldDispatch(list, PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, false), eventDispatcher);
        incrementCoalescingKey();
        this.mPrimaryPointerId = -1;
    }
}
