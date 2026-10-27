package com.amazon.mp3.auto.carmode.snaphelper;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: CoordinateHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/carmode/snaphelper/CoordinateHelper;", "", "getBaseCoordinate", "", "helper", "Landroidx/recyclerview/widget/OrientationHelper;", "getTargetCoordinate", "targetView", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CoordinateHelper {
    int getBaseCoordinate(OrientationHelper helper);

    int getTargetCoordinate(View targetView, OrientationHelper helper);
}
