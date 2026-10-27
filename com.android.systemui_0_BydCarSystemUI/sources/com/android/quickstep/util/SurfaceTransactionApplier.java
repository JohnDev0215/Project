package com.android.quickstep.util;

import android.os.Handler;
import android.os.Message;
import android.view.SurfaceControl;
import android.view.View;
import com.android.quickstep.RemoteAnimationTargets;
import com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat;
import com.android.systemui.shared.system.TransactionCompat;
import com.android.systemui.shared.system.ViewRootImplCompat;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes.dex */
public class SurfaceTransactionApplier extends RemoteAnimationTargets.ReleaseCheck {
    private static final int MSG_UPDATE_SEQUENCE_NUMBER = 0;
    private final Handler mApplyHandler;
    private final SurfaceControl mBarrierSurfaceControl;
    private int mLastSequenceNumber = 0;
    private final ViewRootImplCompat mTargetViewRootImpl;

    public SurfaceTransactionApplier(View targetView) {
        ViewRootImplCompat viewRootImplCompat = new ViewRootImplCompat(targetView);
        this.mTargetViewRootImpl = viewRootImplCompat;
        this.mBarrierSurfaceControl = viewRootImplCompat.getRenderSurfaceControl();
        this.mApplyHandler = new Handler(new Handler.Callback() { // from class: com.android.quickstep.util.-$$Lambda$iHUe-S0-7c-VRTxNgEMSNce0Bsk
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f$0.onApplyMessage(message);
            }
        });
    }

    protected boolean onApplyMessage(Message msg) {
        if (msg.what != 0) {
            return false;
        }
        setCanRelease(msg.arg1 == this.mLastSequenceNumber);
        return true;
    }

    public void scheduleApply(final SyncRtSurfaceTransactionApplierCompat.SurfaceParams... params) {
        View view = this.mTargetViewRootImpl.getView();
        if (view == null) {
            return;
        }
        final int i = this.mLastSequenceNumber + 1;
        this.mLastSequenceNumber = i;
        setCanRelease(false);
        this.mTargetViewRootImpl.registerRtFrameCallback(new LongConsumer() { // from class: com.android.quickstep.util.-$$Lambda$SurfaceTransactionApplier$Xj9m0ayWHIr2xvQnKB4EJdxg8RU
            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                this.f$0.lambda$scheduleApply$0$SurfaceTransactionApplier(i, params, j);
            }
        });
        view.invalidate();
    }

    public /* synthetic */ void lambda$scheduleApply$0$SurfaceTransactionApplier(int i, SyncRtSurfaceTransactionApplierCompat.SurfaceParams[] surfaceParamsArr, long j) {
        SurfaceControl surfaceControl = this.mBarrierSurfaceControl;
        if (surfaceControl == null || !surfaceControl.isValid()) {
            Message.obtain(this.mApplyHandler, 0, i, 0).sendToTarget();
            return;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        for (int length = surfaceParamsArr.length - 1; length >= 0; length--) {
            SyncRtSurfaceTransactionApplierCompat.SurfaceParams surfaceParams = surfaceParamsArr[length];
            if (surfaceParams.surface.isValid()) {
                TransactionCompat.deferTransactionUntil(transaction, surfaceParams.surface, this.mBarrierSurfaceControl, j);
                surfaceParams.applyTo(transaction);
            }
        }
        transaction.apply();
        Message.obtain(this.mApplyHandler, 0, i, 0).sendToTarget();
    }

    public static void create(final View targetView, final Consumer<SurfaceTransactionApplier> callback) {
        if (targetView == null) {
            callback.accept(null);
        } else if (new ViewRootImplCompat(targetView).isValid()) {
            callback.accept(new SurfaceTransactionApplier(targetView));
        } else {
            targetView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.quickstep.util.SurfaceTransactionApplier.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v) {
                    targetView.removeOnAttachStateChangeListener(this);
                    callback.accept(new SurfaceTransactionApplier(targetView));
                }
            });
        }
    }
}
