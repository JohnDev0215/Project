package com.android.car.media;

import android.car.utils.Logs;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.ui.toolbar.g;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import spi.g0;

/* JADX INFO: compiled from: BrowseViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Consumer<g> {
    public final /* synthetic */ MediaItemMetadata a;
    public final /* synthetic */ List b;
    public final /* synthetic */ List c;
    public final /* synthetic */ a d;

    public b(a aVar, MediaItemMetadata mediaItemMetadata, List list, List list2) {
        this.d = aVar;
        this.a = mediaItemMetadata;
        this.b = list;
        this.c = list2;
    }

    @Override // java.util.function.Consumer
    public final void accept(g gVar) {
        StringBuilder sbK = spi.c.k("tab2 click item:");
        sbK.append(this.a.g());
        Logs.d("BrowseViewController", sbK.toString());
        a aVar = this.d;
        List<MediaItemMetadata> listD = aVar.d(this.b, aVar.l.getTabs().get(this.d.l.getSelectedTab()).a);
        a aVar2 = this.d;
        aVar2.m.o(aVar2.c, listD);
        a aVar3 = this.d;
        aVar3.r = "";
        int iA = aVar3.a((List) this.c.stream().map(new g0(this)).collect(Collectors.toList()), gVar.a, false);
        this.d.t.g(iA);
        Logs.d("BrowseViewController", "tab2 click item index:" + iA);
    }
}
