package spi;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class eb extends Lambda implements Function3<Software, Integer, Integer, Unit> {
    public final /* synthetic */ FeatureCategoryThemeAdapter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public eb(FeatureCategoryThemeAdapter featureCategoryThemeAdapter) {
        super(3);
        this.a = featureCategoryThemeAdapter;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Unit invoke(Software software, Integer num, Integer num2) {
        Software software2 = software;
        int iIntValue = num.intValue();
        int iIntValue2 = num2.intValue();
        Intrinsics.checkNotNullParameter(software2, "software");
        Function3<? super Software, ? super Integer, ? super Integer, Unit> function3 = this.a.e;
        if (function3 != null) {
            function3.invoke(software2, Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2));
        }
        return Unit.INSTANCE;
    }
}
