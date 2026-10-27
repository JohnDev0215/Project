package com.android.settingslib.graph;

import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import kotlin.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16}, pn = "", xi = 0, xs = "")
final class ThemedBatteryDrawable$invalidateRunnable$1 extends Lambda implements kotlin.jvm.b.a<q> {
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ThemedBatteryDrawable$invalidateRunnable$1(b bVar) {
        super(0);
        this.this$0 = bVar;
    }

    @Override // kotlin.jvm.b.a
    public /* bridge */ /* synthetic */ q invoke() {
        invoke2();
        return q.f1526a;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        this.this$0.invalidateSelf();
    }
}
