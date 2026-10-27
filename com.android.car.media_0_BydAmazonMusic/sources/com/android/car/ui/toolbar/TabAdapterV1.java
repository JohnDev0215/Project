package com.android.car.ui.toolbar;

import android.annotation.TargetApi;
import java.util.function.Consumer;
import spi.sa;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
class TabAdapterV1 {
    private final g mClientTab;
    private final sa mPluginTab;

    public TabAdapterV1(g gVar) {
        this.mClientTab = gVar;
        Consumer<g> consumer = gVar.c;
        sa.b bVar = new sa.b();
        bVar.a = gVar.a;
        this.mPluginTab = new sa(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Consumer consumer) {
        consumer.accept(this.mClientTab);
    }

    public g getClientTab() {
        return this.mClientTab;
    }

    public sa getPluginTab() {
        return this.mPluginTab;
    }
}
