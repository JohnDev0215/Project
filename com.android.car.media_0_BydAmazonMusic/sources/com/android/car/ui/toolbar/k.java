package com.android.car.ui.toolbar;

import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k implements Function {
    public static final /* synthetic */ k b = new k(0);
    public static final /* synthetic */ k c = new k(1);
    public static final /* synthetic */ k d = new k(2);
    public static final /* synthetic */ k e = new k(3);
    public static final /* synthetic */ k f = new k(4);
    public static final /* synthetic */ k g = new k(5);
    public final /* synthetic */ int a;

    public /* synthetic */ k(int i) {
        this.a = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
        }
        return ((TabAdapterV1) obj).getPluginTab();
    }
}
