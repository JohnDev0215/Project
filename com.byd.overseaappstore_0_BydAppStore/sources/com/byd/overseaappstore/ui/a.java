package com.byd.overseaappstore.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Lambda implements Function0<Unit> {
    public final /* synthetic */ MainActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(MainActivity mainActivity) {
        super(0);
        this.a = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Unit invoke() {
        this.a.finish();
        return Unit.INSTANCE;
    }
}
