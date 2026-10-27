package com.byd.overseaappstore.ui.view.fragment.manage;

import android.view.View;
import androidx.lifecycle.LifecycleOwnerKt;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ UpdateListFragment a;
    public final /* synthetic */ Software b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(UpdateListFragment updateListFragment, Software software) {
        super(1);
        this.a = updateListFragment;
        this.b = software;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.a), null, null, new e(this.a, this.b, null), 3, null);
        return Unit.INSTANCE;
    }
}
