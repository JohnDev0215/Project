package com.android.car.ui.toolbar;

import com.android.car.ui.toolbar.ToolbarControllerAdapterV1;
import java.util.function.Predicate;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class l implements Predicate {
    public static final /* synthetic */ l b = new l(0);
    public static final /* synthetic */ l c = new l(1);
    public final /* synthetic */ int a;

    public /* synthetic */ l(int i) {
        this.a = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                return ToolbarControllerAdapterV1.ToolbarAdapterState.lambda$getShownMenuItems$0((MenuItemAdapterV1) obj);
            default:
                return ((MenuItemAdapterV1) obj).isVisible();
        }
    }
}
