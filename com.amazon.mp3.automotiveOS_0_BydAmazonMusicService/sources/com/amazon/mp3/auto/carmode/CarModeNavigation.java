package com.amazon.mp3.auto.carmode;

import android.os.Bundle;
import kotlin.Metadata;

/* JADX INFO: compiled from: CarModeNavigation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "", "navigateTo", "", "action", "Lcom/amazon/mp3/auto/carmode/CarModeAction;", "bundle", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CarModeNavigation {
    void navigateTo(CarModeAction action, Bundle bundle);

    /* JADX INFO: compiled from: CarModeNavigation.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void navigateTo$default(CarModeNavigation carModeNavigation, CarModeAction carModeAction, Bundle bundle, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: navigateTo");
            }
            if ((i & 2) != 0) {
                bundle = null;
            }
            carModeNavigation.navigateTo(carModeAction, bundle);
        }
    }
}
