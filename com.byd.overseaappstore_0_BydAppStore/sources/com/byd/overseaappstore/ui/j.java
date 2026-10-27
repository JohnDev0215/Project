package com.byd.overseaappstore.ui;

import android.view.View;
import com.byd.overseaappstore.module.ability.util.notify.router.gear.NotPBanDialogState;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import spi.s4;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ MainActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(MainActivity mainActivity) {
        super(1);
        this.a = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        s4 s4Var = this.a.h;
        if (s4Var != null) {
            s4Var.a();
        }
        NotPBanDialogState.INSTANCE.setShowing(false);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new i(this.a, null), 3, null);
        return Unit.INSTANCE;
    }
}
