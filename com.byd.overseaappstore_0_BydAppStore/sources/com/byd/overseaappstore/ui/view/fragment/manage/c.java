package com.byd.overseaappstore.ui.view.fragment.manage;

import android.view.View;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.ui.vm.DownloadViewModel;
import java.util.Objects;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import spi.ct;
import spi.s9;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Lambda implements Function1<View, Unit> {
    public final /* synthetic */ DownloadListFragment a;
    public final /* synthetic */ Software b;
    public final /* synthetic */ Pair<Boolean, Integer> c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(DownloadListFragment downloadListFragment, Software software, Pair<Boolean, Integer> pair) {
        super(1);
        this.a = downloadListFragment;
        this.b = software;
        this.c = pair;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Unit invoke(View view) {
        View it = view;
        Intrinsics.checkNotNullParameter(it, "it");
        DownloadViewModel downloadViewModelI = DownloadListFragment.i(this.a);
        Software software = this.b;
        int iIntValue = this.c.getSecond().intValue();
        Objects.requireNonNull(downloadViewModelI);
        Intrinsics.checkNotNullParameter(software, "software");
        ct.a(xi.e("checkToContinueTask beforeDeleteState:", iIntValue, " "), new Object[0]);
        if (iIntValue == 3) {
            s9.c(downloadViewModelI.d, downloadViewModelI.g.parseToSoftwareOptParam(software));
        }
        return Unit.INSTANCE;
    }
}
