package com.swmansion.gesturehandler.core;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0011\u0018\u0000 K2\u00020\u0001:\u0001KB\u001f\u0012\u0006\u0010,\u001a\u00020'\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u00102\u001a\u000201¢\u0006\u0004\bI\u0010JJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\"\u0010\f\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ\u0018\u0010\u000f\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u0002J\u0018\u0010\u0012\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0010J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\u0014\u0010\u0015\u001a\u00020\u00042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u0014\u0010\u0016\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0002J\u0014\u0010\u0018\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u001a\u001a\u00020\u000bH\u0002J\u001c\u0010\u001c\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u001b\u001a\u00020\u0002H\u0002J\u0012\u0010\u001d\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002J\u0014\u0010\u001e\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0002J\u001c\u0010\u001f\u001a\u00020\u000b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002J \u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bH\u0002J \u0010%\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bH\u0002J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010&\u001a\u00020\u00042\u0006\u0010(\u001a\u00020'2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bH\u0002J \u0010)\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\bH\u0002J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u0014\u0010,\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\"\u00105\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R \u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R \u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010=R \u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010=R \u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010=R\u0016\u0010A\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010BR\u0016\u0010D\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010BR\u0016\u0010G\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010ER\u0016\u0010H\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010B¨\u0006L"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "", "onTouchEvent", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "handler", "", "newState", "prevState", "", "onHandlerStateChange", "Landroid/view/View;", "view", "transformEventToViewCoords", "Landroid/graphics/PointF;", "point", "transformPointToViewCoords", "scheduleFinishedHandlersCleanup", "cleanupFinishedHandlers", "hasOtherHandlerToWaitFor", "tryActivate", "cleanupAwaitingHandlers", "makeActive", "deliverEventToGestureHandlers", "cancelAll", "sourceEvent", "deliverEventToGestureHandler", "isViewAttachedUnderWrapper", "addAwaitingHandler", "recordHandlerIfNotPresent", "isViewOverflowingParent", "", "coords", "pointerId", "extractAncestorHandlers", "recordViewHandlersForPointer", "extractGestureHandlers", "Landroid/view/ViewGroup;", "viewGroup", "traverseWithPointerEvents", "canReceiveEvents", "isClipping", "wrapperView", "Landroid/view/ViewGroup;", "Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;", "handlerRegistry", "Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "viewConfigHelper", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "", "minimumAlphaForTraversal", "F", "getMinimumAlphaForTraversal", "()F", "setMinimumAlphaForTraversal", "(F)V", "", "gestureHandlers", "[Lcom/swmansion/gesturehandler/core/GestureHandler;", "awaitingHandlers", "preparedHandlers", "handlersToCancel", "gestureHandlersCount", "I", "awaitingHandlersCount", "isHandlingTouch", "Z", "handlingChangeSemaphore", "finishedHandlersCleanupScheduled", "activationIndex", "<init>", "(Landroid/view/ViewGroup;Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;)V", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0})
public final class GestureHandlerOrchestrator {
    private int activationIndex;
    private final GestureHandler<?>[] awaitingHandlers;
    private int awaitingHandlersCount;
    private boolean finishedHandlersCleanupScheduled;
    private final GestureHandler<?>[] gestureHandlers;
    private int gestureHandlersCount;
    private final GestureHandlerRegistry handlerRegistry;
    private final GestureHandler<?>[] handlersToCancel;
    private int handlingChangeSemaphore;
    private boolean isHandlingTouch;
    private float minimumAlphaForTraversal;
    private final GestureHandler<?>[] preparedHandlers;
    private final ViewConfigurationHelper viewConfigHelper;
    private final ViewGroup wrapperView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PointF tempPoint = new PointF();
    private static final float[] matrixTransformCoords = new float[2];
    private static final Matrix inverseMatrix = new Matrix();
    private static final float[] tempCoords = new float[2];
    private static final Comparator<GestureHandler<?>> handlersComparator = new Comparator() { // from class: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return GestureHandlerOrchestrator.m2749handlersComparator$lambda12((GestureHandler) obj, (GestureHandler) obj2);
        }
    };

    /* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PointerEventsConfig.values().length];
            iArr[PointerEventsConfig.NONE.ordinal()] = 1;
            iArr[PointerEventsConfig.BOX_ONLY.ordinal()] = 2;
            iArr[PointerEventsConfig.BOX_NONE.ordinal()] = 3;
            iArr[PointerEventsConfig.AUTO.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public GestureHandlerOrchestrator(ViewGroup wrapperView, GestureHandlerRegistry handlerRegistry, ViewConfigurationHelper viewConfigHelper) {
        Intrinsics.checkNotNullParameter(wrapperView, "wrapperView");
        Intrinsics.checkNotNullParameter(handlerRegistry, "handlerRegistry");
        Intrinsics.checkNotNullParameter(viewConfigHelper, "viewConfigHelper");
        this.wrapperView = wrapperView;
        this.handlerRegistry = handlerRegistry;
        this.viewConfigHelper = viewConfigHelper;
        this.gestureHandlers = new GestureHandler[20];
        this.awaitingHandlers = new GestureHandler[20];
        this.preparedHandlers = new GestureHandler[20];
        this.handlersToCancel = new GestureHandler[20];
    }

    public final float getMinimumAlphaForTraversal() {
        return this.minimumAlphaForTraversal;
    }

    public final void setMinimumAlphaForTraversal(float f) {
        this.minimumAlphaForTraversal = f;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 1
            r3.isHandlingTouch = r0
            int r1 = r4.getActionMasked()
            if (r1 == 0) goto L19
            r2 = 3
            if (r1 == r2) goto L15
            r2 = 5
            if (r1 == r2) goto L19
            goto L1c
        L15:
            r3.cancelAll()
            goto L1c
        L19:
            r3.extractGestureHandlers(r4)
        L1c:
            r3.deliverEventToGestureHandlers(r4)
            r4 = 0
            r3.isHandlingTouch = r4
            boolean r4 = r3.finishedHandlersCleanupScheduled
            if (r4 == 0) goto L2d
            int r4 = r3.handlingChangeSemaphore
            if (r4 != 0) goto L2d
            r3.cleanupFinishedHandlers()
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private final void scheduleFinishedHandlersCleanup() {
        if (this.isHandlingTouch || this.handlingChangeSemaphore != 0) {
            this.finishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private final void cleanupFinishedHandlers() {
        int i = this.gestureHandlersCount - 1;
        boolean z = false;
        if (i >= 0) {
            while (true) {
                int i2 = i - 1;
                GestureHandler<?> gestureHandler = this.gestureHandlers[i];
                Intrinsics.checkNotNull(gestureHandler);
                if (INSTANCE.isFinished(gestureHandler.getState()) && !gestureHandler.getIsAwaiting()) {
                    this.gestureHandlers[i] = null;
                    gestureHandler.reset();
                    gestureHandler.setActive(false);
                    gestureHandler.setAwaiting(false);
                    gestureHandler.setActivationIndex(Integer.MAX_VALUE);
                    z = true;
                }
                if (i2 < 0) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        if (z) {
            GestureHandler<?>[] gestureHandlerArr = this.gestureHandlers;
            int i3 = this.gestureHandlersCount;
            int i4 = 0;
            int i5 = 0;
            while (i4 < i3) {
                int i6 = i4 + 1;
                GestureHandler<?> gestureHandler2 = gestureHandlerArr[i4];
                if (gestureHandler2 != null) {
                    gestureHandlerArr[i5] = gestureHandler2;
                    i4 = i6;
                    i5++;
                } else {
                    i4 = i6;
                }
            }
            this.gestureHandlersCount = i5;
        }
        this.finishedHandlersCleanupScheduled = false;
    }

    private final boolean hasOtherHandlerToWaitFor(GestureHandler<?> handler) {
        int i = this.gestureHandlersCount;
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            GestureHandler<?> gestureHandler = this.gestureHandlers[i2];
            Intrinsics.checkNotNull(gestureHandler);
            Companion companion = INSTANCE;
            if (!companion.isFinished(gestureHandler.getState()) && companion.shouldHandlerWaitForOther(handler, gestureHandler)) {
                return true;
            }
            i2 = i3;
        }
        return false;
    }

    private final void tryActivate(GestureHandler<?> handler) {
        if (hasOtherHandlerToWaitFor(handler)) {
            addAwaitingHandler(handler);
        } else {
            makeActive(handler);
            handler.setAwaiting(false);
        }
    }

    private final void cleanupAwaitingHandlers() {
        GestureHandler<?>[] gestureHandlerArr = this.awaitingHandlers;
        int i = this.awaitingHandlersCount;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i2 + 1;
            GestureHandler<?> gestureHandler = gestureHandlerArr[i2];
            Intrinsics.checkNotNull(gestureHandler);
            if (gestureHandler.getIsAwaiting()) {
                gestureHandlerArr[i3] = gestureHandlerArr[i2];
                i2 = i4;
                i3++;
            } else {
                i2 = i4;
            }
        }
        this.awaitingHandlersCount = i3;
    }

    public final void onHandlerStateChange(GestureHandler<?> handler, int newState, int prevState) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.handlingChangeSemaphore++;
        if (INSTANCE.isFinished(newState)) {
            int i = this.awaitingHandlersCount;
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                GestureHandler<?> gestureHandler = this.awaitingHandlers[i2];
                Companion companion = INSTANCE;
                Intrinsics.checkNotNull(gestureHandler);
                if (companion.shouldHandlerWaitForOther(gestureHandler, handler)) {
                    if (newState == 5) {
                        gestureHandler.cancel();
                        if (gestureHandler.getState() == 5) {
                            gestureHandler.dispatchStateChange(3, 2);
                        }
                        gestureHandler.setAwaiting(false);
                    } else {
                        tryActivate(gestureHandler);
                    }
                }
                i2 = i3;
            }
            cleanupAwaitingHandlers();
        }
        if (newState == 4) {
            tryActivate(handler);
        } else if (prevState == 4 || prevState == 5) {
            if (handler.getIsActive()) {
                handler.dispatchStateChange(newState, prevState);
            } else if (prevState == 4 && (newState == 3 || newState == 1)) {
                handler.dispatchStateChange(newState, 2);
            }
        } else if (prevState != 0 || newState != 3) {
            handler.dispatchStateChange(newState, prevState);
        }
        this.handlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    private final void makeActive(GestureHandler<?> handler) {
        int state = handler.getState();
        handler.setAwaiting(false);
        handler.setActive(true);
        handler.setShouldResetProgress(true);
        int i = this.activationIndex;
        this.activationIndex = i + 1;
        handler.setActivationIndex(i);
        int i2 = this.gestureHandlersCount;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i3 + 1;
            GestureHandler<?> gestureHandler = this.gestureHandlers[i3];
            Intrinsics.checkNotNull(gestureHandler);
            if (INSTANCE.shouldHandlerBeCancelledBy(gestureHandler, handler)) {
                this.handlersToCancel[i4] = gestureHandler;
                i3 = i5;
                i4++;
            } else {
                i3 = i5;
            }
        }
        int i6 = i4 - 1;
        if (i6 >= 0) {
            while (true) {
                int i7 = i6 - 1;
                GestureHandler<?> gestureHandler2 = this.handlersToCancel[i6];
                Intrinsics.checkNotNull(gestureHandler2);
                gestureHandler2.cancel();
                if (i7 < 0) {
                    break;
                } else {
                    i6 = i7;
                }
            }
        }
        int i8 = this.awaitingHandlersCount - 1;
        if (i8 >= 0) {
            while (true) {
                int i9 = i8 - 1;
                GestureHandler<?> gestureHandler3 = this.awaitingHandlers[i8];
                Intrinsics.checkNotNull(gestureHandler3);
                if (INSTANCE.shouldHandlerBeCancelledBy(gestureHandler3, handler)) {
                    gestureHandler3.cancel();
                    gestureHandler3.setAwaiting(false);
                }
                if (i9 < 0) {
                    break;
                } else {
                    i8 = i9;
                }
            }
        }
        cleanupAwaitingHandlers();
        handler.dispatchStateChange(4, 2);
        if (state != 4) {
            handler.dispatchStateChange(5, 4);
            if (state != 5) {
                handler.dispatchStateChange(0, 5);
            }
        }
    }

    private final void deliverEventToGestureHandlers(MotionEvent event) {
        int i = this.gestureHandlersCount;
        ArraysKt___ArraysJvmKt.copyInto(this.gestureHandlers, this.preparedHandlers, 0, 0, i);
        ArraysKt___ArraysJvmKt.sortWith(this.preparedHandlers, handlersComparator, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            GestureHandler<?> gestureHandler = this.preparedHandlers[i2];
            Intrinsics.checkNotNull(gestureHandler);
            deliverEventToGestureHandler(gestureHandler, event);
        }
    }

    private final void cancelAll() {
        int i = this.awaitingHandlersCount - 1;
        if (i >= 0) {
            while (true) {
                int i2 = i - 1;
                GestureHandler<?> gestureHandler = this.awaitingHandlers[i];
                Intrinsics.checkNotNull(gestureHandler);
                gestureHandler.cancel();
                if (i2 < 0) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        int i3 = this.gestureHandlersCount;
        for (int i4 = 0; i4 < i3; i4++) {
            this.preparedHandlers[i4] = this.gestureHandlers[i4];
        }
        int i5 = i3 - 1;
        if (i5 < 0) {
            return;
        }
        while (true) {
            int i6 = i5 - 1;
            GestureHandler<?> gestureHandler2 = this.preparedHandlers[i5];
            Intrinsics.checkNotNull(gestureHandler2);
            gestureHandler2.cancel();
            if (i6 < 0) {
                return;
            } else {
                i5 = i6;
            }
        }
    }

    private final void deliverEventToGestureHandler(GestureHandler<?> handler, MotionEvent sourceEvent) {
        if (!isViewAttachedUnderWrapper(handler.getView())) {
            handler.cancel();
            return;
        }
        if (handler.wantEvents()) {
            int actionMasked = sourceEvent.getActionMasked();
            View view = handler.getView();
            MotionEvent motionEventObtain = MotionEvent.obtain(sourceEvent);
            Intrinsics.checkNotNullExpressionValue(motionEventObtain, "obtain(sourceEvent)");
            MotionEvent motionEventTransformEventToViewCoords = transformEventToViewCoords(view, motionEventObtain);
            if (handler.getNeedsPointerData() && handler.getState() != 0) {
                handler.updatePointerData(motionEventTransformEventToViewCoords);
            }
            if (!handler.getIsAwaiting() || actionMasked != 2) {
                boolean z = handler.getState() == 0;
                handler.handle(motionEventTransformEventToViewCoords, sourceEvent);
                if (handler.getIsActive()) {
                    if (handler.getShouldResetProgress()) {
                        handler.setShouldResetProgress(false);
                        handler.resetProgress();
                    }
                    handler.dispatchHandlerUpdate(motionEventTransformEventToViewCoords);
                }
                if (handler.getNeedsPointerData() && z) {
                    handler.updatePointerData(motionEventTransformEventToViewCoords);
                }
                if (actionMasked == 1 || actionMasked == 6) {
                    handler.stopTrackingPointer(motionEventTransformEventToViewCoords.getPointerId(motionEventTransformEventToViewCoords.getActionIndex()));
                }
            }
            motionEventTransformEventToViewCoords.recycle();
        }
    }

    private final boolean isViewAttachedUnderWrapper(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.wrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.wrapperView) {
            parent = parent.getParent();
        }
        return parent == this.wrapperView;
    }

    public final MotionEvent transformEventToViewCoords(View view, MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (view == null) {
            return event;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
            transformEventToViewCoords(viewGroup, event);
        }
        if (viewGroup != null) {
            event.setLocation((event.getX() + viewGroup.getScrollX()) - view.getLeft(), (event.getY() + viewGroup.getScrollY()) - view.getTop());
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            event.transform(matrix2);
        }
        return event;
    }

    public final PointF transformPointToViewCoords(View view, PointF point) {
        Intrinsics.checkNotNullParameter(point, "point");
        if (view == null) {
            return point;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
            transformPointToViewCoords(viewGroup, point);
        }
        if (viewGroup != null) {
            point.x += viewGroup.getScrollX() - view.getLeft();
            point.y += viewGroup.getScrollY() - view.getTop();
        }
        if (!view.getMatrix().isIdentity()) {
            Matrix matrix = view.getMatrix();
            Matrix matrix2 = inverseMatrix;
            matrix.invert(matrix2);
            float[] fArr = tempCoords;
            fArr[0] = point.x;
            fArr[1] = point.y;
            matrix2.mapPoints(fArr);
            point.x = fArr[0];
            point.y = fArr[1];
        }
        return point;
    }

    private final void addAwaitingHandler(GestureHandler<?> handler) {
        int i = this.awaitingHandlersCount;
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            if (this.awaitingHandlers[i2] == handler) {
                return;
            } else {
                i2 = i3;
            }
        }
        int i4 = this.awaitingHandlersCount;
        GestureHandler<?>[] gestureHandlerArr = this.awaitingHandlers;
        if (!(i4 < gestureHandlerArr.length)) {
            throw new IllegalStateException("Too many recognizers".toString());
        }
        this.awaitingHandlersCount = i4 + 1;
        gestureHandlerArr[i4] = handler;
        handler.setAwaiting(true);
        int i5 = this.activationIndex;
        this.activationIndex = i5 + 1;
        handler.setActivationIndex(i5);
    }

    private final void recordHandlerIfNotPresent(GestureHandler<?> handler, View view) {
        int i = this.gestureHandlersCount;
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            if (this.gestureHandlers[i2] == handler) {
                return;
            } else {
                i2 = i3;
            }
        }
        int i4 = this.gestureHandlersCount;
        GestureHandler<?>[] gestureHandlerArr = this.gestureHandlers;
        if (!(i4 < gestureHandlerArr.length)) {
            throw new IllegalStateException("Too many recognizers".toString());
        }
        this.gestureHandlersCount = i4 + 1;
        gestureHandlerArr[i4] = handler;
        handler.setActive(false);
        handler.setAwaiting(false);
        handler.setActivationIndex(Integer.MAX_VALUE);
        handler.prepare(view, this);
    }

    private final boolean isViewOverflowingParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return false;
        }
        Matrix matrix = view.getMatrix();
        float[] fArr = matrixTransformCoords;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        matrix.mapPoints(fArr);
        float left = fArr[0] + view.getLeft();
        float top = fArr[1] + view.getTop();
        return left < 0.0f || left + ((float) view.getWidth()) > ((float) viewGroup.getWidth()) || top < 0.0f || top + ((float) view.getHeight()) > ((float) viewGroup.getHeight());
    }

    private final boolean extractAncestorHandlers(View view, float[] coords, int pointerId) {
        boolean z = false;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                ArrayList<GestureHandler<?>> handlersForView = this.handlerRegistry.getHandlersForView((View) parent);
                if (handlersForView == null) {
                    continue;
                } else {
                    synchronized (handlersForView) {
                        for (GestureHandler<?> handler : handlersForView) {
                            if (handler.getIsEnabled() && handler.isWithinBounds(view, coords[0], coords[1])) {
                                Intrinsics.checkNotNullExpressionValue(handler, "handler");
                                recordHandlerIfNotPresent(handler, viewGroup);
                                handler.startTrackingPointer(pointerId);
                                z = true;
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        }
        return z;
    }

    private final boolean recordViewHandlersForPointer(View view, float[] coords, int pointerId) {
        boolean z;
        ArrayList<GestureHandler<?>> handlersForView = this.handlerRegistry.getHandlersForView(view);
        boolean z2 = false;
        if (handlersForView == null) {
            z = false;
        } else {
            synchronized (handlersForView) {
                z = false;
                for (GestureHandler<?> handler : handlersForView) {
                    if (handler.getIsEnabled() && handler.isWithinBounds(view, coords[0], coords[1])) {
                        Intrinsics.checkNotNullExpressionValue(handler, "handler");
                        recordHandlerIfNotPresent(handler, view);
                        handler.startTrackingPointer(pointerId);
                        z = true;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        float width = view.getWidth();
        float f = coords[0];
        if (0.0f <= f && f <= width) {
            float height = view.getHeight();
            float f2 = coords[1];
            if (0.0f <= f2 && f2 <= height) {
                z2 = true;
            }
            if (z2 && isViewOverflowingParent(view) && extractAncestorHandlers(view, coords, pointerId)) {
                return true;
            }
        }
        return z;
    }

    private final void extractGestureHandlers(MotionEvent event) {
        int actionIndex = event.getActionIndex();
        int pointerId = event.getPointerId(actionIndex);
        float[] fArr = tempCoords;
        fArr[0] = event.getX(actionIndex);
        fArr[1] = event.getY(actionIndex);
        traverseWithPointerEvents(this.wrapperView, fArr, pointerId);
        extractGestureHandlers(this.wrapperView, fArr, pointerId);
    }

    private final boolean extractGestureHandlers(ViewGroup viewGroup, float[] coords, int pointerId) {
        int childCount = viewGroup.getChildCount() - 1;
        if (childCount >= 0) {
            while (true) {
                int i = childCount - 1;
                View childInDrawingOrderAtIndex = this.viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
                if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                    PointF pointF = tempPoint;
                    Companion companion = INSTANCE;
                    companion.transformPointToChildViewCoords(coords[0], coords[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                    float f = coords[0];
                    float f2 = coords[1];
                    coords[0] = pointF.x;
                    coords[1] = pointF.y;
                    boolean zTraverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || companion.isTransformedTouchPointInView(coords[0], coords[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, coords, pointerId) : false;
                    coords[0] = f;
                    coords[1] = f2;
                    if (zTraverseWithPointerEvents) {
                        return true;
                    }
                }
                if (i < 0) {
                    break;
                }
                childCount = i;
            }
        }
        return false;
    }

    private final boolean traverseWithPointerEvents(View view, float[] coords, int pointerId) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.viewConfigHelper.getPointerEventsConfigForView(view).ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    boolean zExtractGestureHandlers = view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, coords, pointerId) : false;
                    if (recordViewHandlersForPointer(view, coords, pointerId) || zExtractGestureHandlers || INSTANCE.shouldHandlerlessViewBecomeTouchTarget(view, coords)) {
                        return true;
                    }
                } else {
                    if (view instanceof ViewGroup) {
                        boolean zExtractGestureHandlers2 = extractGestureHandlers((ViewGroup) view, coords, pointerId);
                        if (!zExtractGestureHandlers2) {
                            return zExtractGestureHandlers2;
                        }
                        recordViewHandlersForPointer(view, coords, pointerId);
                        return zExtractGestureHandlers2;
                    }
                    if (view instanceof EditText) {
                        return recordViewHandlersForPointer(view, coords, pointerId);
                    }
                }
            } else if (recordViewHandlersForPointer(view, coords, pointerId) || INSTANCE.shouldHandlerlessViewBecomeTouchTarget(view, coords)) {
                return true;
            }
        }
        return false;
    }

    private final boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.minimumAlphaForTraversal;
    }

    private final boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.viewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    /* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J \u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J \u0010\u001c\u001a\u00020\u00122\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J \u0010\u001f\u001a\u00020\u00122\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\rH\u0002J0\u0010#\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator$Companion;", "", "()V", "DEFAULT_MIN_ALPHA_FOR_TRAVERSAL", "", "SIMULTANEOUS_GESTURE_HANDLER_LIMIT", "", "handlersComparator", "Ljava/util/Comparator;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "inverseMatrix", "Landroid/graphics/Matrix;", "matrixTransformCoords", "", "tempCoords", "tempPoint", "Landroid/graphics/PointF;", "canRunSimultaneously", "", "a", "b", "isFinished", "state", "isTransformedTouchPointInView", "x", "y", "child", "Landroid/view/View;", "shouldHandlerBeCancelledBy", "handler", "other", "shouldHandlerWaitForOther", "shouldHandlerlessViewBecomeTouchTarget", "view", "coords", "transformPointToChildViewCoords", "", "parent", "Landroid/view/ViewGroup;", "outLocalPoint", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isFinished(int state) {
            return state == 3 || state == 1 || state == 5;
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] coords) {
            return (!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(coords[0], coords[1], view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void transformPointToChildViewCoords(float x, float y, ViewGroup parent, View child, PointF outLocalPoint) {
            float scrollX = (x + parent.getScrollX()) - child.getLeft();
            float scrollY = (y + parent.getScrollY()) - child.getTop();
            Matrix matrix = child.getMatrix();
            if (!matrix.isIdentity()) {
                float[] fArr = GestureHandlerOrchestrator.matrixTransformCoords;
                fArr[0] = scrollX;
                fArr[1] = scrollY;
                matrix.invert(GestureHandlerOrchestrator.inverseMatrix);
                GestureHandlerOrchestrator.inverseMatrix.mapPoints(fArr);
                float f = fArr[0];
                scrollY = fArr[1];
                scrollX = f;
            }
            outLocalPoint.set(scrollX, scrollY);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTransformedTouchPointInView(float x, float y, View child) {
            if (0.0f <= x && x <= ((float) child.getWidth())) {
                if (0.0f <= y && y <= ((float) child.getHeight())) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerWaitForOther(GestureHandler<?> handler, GestureHandler<?> other) {
            return handler != other && (handler.shouldWaitForHandlerFailure(other) || other.shouldRequireToWaitForFailure(handler));
        }

        private final boolean canRunSimultaneously(GestureHandler<?> a2, GestureHandler<?> b) {
            return a2 == b || a2.shouldRecognizeSimultaneously(b) || b.shouldRecognizeSimultaneously(a2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldHandlerBeCancelledBy(GestureHandler<?> handler, GestureHandler<?> other) {
            if (!handler.hasCommonPointers(other) || canRunSimultaneously(handler, other)) {
                return false;
            }
            if (handler == other || !(handler.getIsAwaiting() || handler.getState() == 4)) {
                return true;
            }
            return handler.shouldBeCancelledBy(other);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlersComparator$lambda-12, reason: not valid java name */
    public static final int m2749handlersComparator$lambda12(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if ((gestureHandler.getIsActive() && gestureHandler2.getIsActive()) || (gestureHandler.getIsAwaiting() && gestureHandler2.getIsAwaiting())) {
            return Integer.signum(gestureHandler2.getActivationIndex() - gestureHandler.getActivationIndex());
        }
        if (!gestureHandler.getIsActive()) {
            if (gestureHandler2.getIsActive()) {
                return 1;
            }
            if (!gestureHandler.getIsAwaiting()) {
                return gestureHandler2.getIsAwaiting() ? 1 : 0;
            }
        }
        return -1;
    }
}
