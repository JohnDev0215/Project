package spi;

import com.byd.overseaappstore.databinding.ItemFeatureCategoryHotAppBinding;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class db extends Lambda implements Function3<Software, ItemFeatureCategoryHotAppBinding, Integer, Unit> {
    public final /* synthetic */ FeatureCategoryThemeAdapter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public db(FeatureCategoryThemeAdapter featureCategoryThemeAdapter) {
        super(3);
        this.a = featureCategoryThemeAdapter;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Unit invoke(Software software, ItemFeatureCategoryHotAppBinding itemFeatureCategoryHotAppBinding, Integer num) {
        Software software2 = software;
        ItemFeatureCategoryHotAppBinding itemFeatureCategoryHotAppBinding2 = itemFeatureCategoryHotAppBinding;
        int iIntValue = num.intValue();
        Intrinsics.checkNotNullParameter(software2, "software");
        Intrinsics.checkNotNullParameter(itemFeatureCategoryHotAppBinding2, "itemFeatureCategoryHotAppBinding");
        Function3<? super Software, ? super ItemFeatureCategoryHotAppBinding, ? super Integer, Unit> function3 = this.a.d;
        if (function3 != null) {
            function3.invoke(software2, itemFeatureCategoryHotAppBinding2, Integer.valueOf(iIntValue));
        }
        return Unit.INSTANCE;
    }
}
