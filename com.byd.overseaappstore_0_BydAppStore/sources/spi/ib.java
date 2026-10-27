package spi;

import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.view.adapter.FeatureCenterAdapter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ib extends Lambda implements Function1<Boolean, Unit> {
    public final /* synthetic */ FeatureCenterAdapter a;
    public final /* synthetic */ Software b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ib(FeatureCenterAdapter featureCenterAdapter, Software software) {
        super(1);
        this.a = featureCenterAdapter;
        this.b = software;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(Boolean bool) {
        bool.booleanValue();
        this.a.c.add(this.b.getPackageName());
        return Unit.INSTANCE;
    }
}
