package spi;

import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class cb extends Lambda implements Function1<Boolean, Unit> {
    public final /* synthetic */ FeatureCategoryThemeAdapter a;
    public final /* synthetic */ FeatureThemTopicGroup b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cb(FeatureCategoryThemeAdapter featureCategoryThemeAdapter, FeatureThemTopicGroup featureThemTopicGroup) {
        super(1);
        this.a = featureCategoryThemeAdapter;
        this.b = featureThemTopicGroup;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(Boolean bool) {
        bool.booleanValue();
        this.a.c.add(Integer.valueOf(this.b.getId()));
        return Unit.INSTANCE;
    }
}
