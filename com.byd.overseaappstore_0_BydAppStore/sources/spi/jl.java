package spi;

import android.content.Context;
import android.view.View;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.ui.view.fragment.ProtocolFragment;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: loaded from: classes.dex */
public final class jl extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ ProtocolFragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jl(ProtocolFragment protocolFragment) {
        super(1);
        this.a = protocolFragment;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        SettingUtil settingUtil = SettingUtil.a;
        Context contextRequireContext = this.a.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        settingUtil.b(contextRequireContext, false);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new il(this.a, null), 3, null);
        return Unit.INSTANCE;
    }
}
