package com.byd.filemanager;

import java.util.function.Consumer;

/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Consumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f3522a;

    public /* synthetic */ b(e eVar) {
        this.f3522a = eVar;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f3522a.a((com.byd.filemanager.bean.d) obj);
    }
}
