package com.byd.overseaappstore.ui.view.fragment.manage;

import android.view.View;
import androidx.lifecycle.LifecycleOwnerKt;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ DownloadListFragment a;
    public final /* synthetic */ Software b;
    public final /* synthetic */ Pair<Boolean, Integer> c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(DownloadListFragment downloadListFragment, Software software, Pair<Boolean, Integer> pair) {
        super(1);
        this.a = downloadListFragment;
        this.b = software;
        this.c = pair;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.a), null, null, new a(this.a, this.b, this.c, null), 3, null);
        return Unit.INSTANCE;
    }
}
