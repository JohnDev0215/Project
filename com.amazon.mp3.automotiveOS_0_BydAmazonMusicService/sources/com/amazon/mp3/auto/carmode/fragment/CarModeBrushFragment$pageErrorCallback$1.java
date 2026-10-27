package com.amazon.mp3.auto.carmode.fragment;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: CarModeBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "throwable", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
final class CarModeBrushFragment$pageErrorCallback$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CarModeBrushFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CarModeBrushFragment$pageErrorCallback$1(CarModeBrushFragment carModeBrushFragment) {
        super(1);
        this.this$0 = carModeBrushFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable th) {
        Log.error(this.this$0.TAG, "Error on fetching Car Mode page", th);
        AutoFlexEventUtil.INSTANCE.emitCarModePageErrorFlexEvent(String.valueOf(th));
        final FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return;
        }
        final CarModeBrushFragment carModeBrushFragment = this.this$0;
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$pageErrorCallback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CarModeBrushFragment$pageErrorCallback$1.m267invoke$lambda1$lambda0(carModeBrushFragment, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invoke$lambda-1$lambda-0, reason: not valid java name */
    public static final void m267invoke$lambda1$lambda0(CarModeBrushFragment this$0, FragmentActivity it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.showErrorDialog(it);
    }
}
