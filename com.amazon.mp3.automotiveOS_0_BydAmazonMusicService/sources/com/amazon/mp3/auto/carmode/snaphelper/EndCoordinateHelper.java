package com.amazon.mp3.auto.carmode.snaphelper;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EndCoordinateHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/auto/carmode/snaphelper/EndCoordinateHelper;", "Lcom/amazon/mp3/auto/carmode/snaphelper/CoordinateHelper;", "()V", "getBaseCoordinate", "", "helper", "Landroidx/recyclerview/widget/OrientationHelper;", "getTargetCoordinate", "targetView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EndCoordinateHelper implements CoordinateHelper {
    @Override // com.amazon.mp3.auto.carmode.snaphelper.CoordinateHelper
    public int getBaseCoordinate(OrientationHelper helper) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        return helper.getEndAfterPadding();
    }

    @Override // com.amazon.mp3.auto.carmode.snaphelper.CoordinateHelper
    public int getTargetCoordinate(View targetView, OrientationHelper helper) {
        Intrinsics.checkNotNullParameter(targetView, "targetView");
        Intrinsics.checkNotNullParameter(helper, "helper");
        return helper.getDecoratedStart(targetView) + helper.getDecoratedMeasurement(targetView);
    }
}
