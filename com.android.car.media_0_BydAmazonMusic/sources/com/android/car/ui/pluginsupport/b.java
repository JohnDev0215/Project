package com.android.car.ui.pluginsupport;

import android.content.Context;
import java.util.function.Function;
import spi.l1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b implements Function {
    public static final /* synthetic */ b b = new b(0);
    public static final /* synthetic */ b c = new b(1);
    public static final /* synthetic */ b d = new b(2);
    public static final /* synthetic */ b e = new b(3);
    public static final /* synthetic */ b f = new b(4);
    public static final /* synthetic */ b g = new b(5);
    public final /* synthetic */ int a;

    public /* synthetic */ b(int i) {
        this.a = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return PluginFactoryAdapterV2.access$000((l1) obj);
            case 1:
                return PluginFactoryAdapterV1.lambda$new$0((Context) obj);
            case 2:
                return PluginFactoryAdapterV1.lambda$new$1((Context) obj);
            case 3:
                return PluginFactoryAdapterV2.toOemListItem((l1) obj);
            case 4:
                return PluginFactoryAdapterV2.lambda$new$0((Context) obj);
            default:
                return PluginFactoryAdapterV2.lambda$new$1((Context) obj);
        }
    }
}
