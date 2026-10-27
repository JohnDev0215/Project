package spi;

import android.app.Activity;
import com.byd.overseaappstore.broadcast.LanguageChangeBroadcastReceiver;
import com.byd.overseaappstore.broadcast.NetWorkStateBroadcastReceiver;
import com.byd.overseaappstore.broadcast.SoftwareBroadcastReceiver;
import com.byd.overseaappstore.ui.MainActivity;
import java.util.Collections;
import java.util.HashSet;
import spi.b6;

/* JADX INFO: loaded from: classes.dex */
public final class y4 extends ok {
    public final g5 a;
    public final a5 b;
    public final y4 c;

    private y4(g5 g5Var, a5 a5Var, Activity activity) {
        this.c = this;
        this.a = g5Var;
        this.b = a5Var;
    }

    public /* synthetic */ y4(g5 g5Var, a5 a5Var, Activity activity, d dVar) {
        this(g5Var, a5Var, activity);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.ArrayList, java.util.List<T>] */
    @Override // spi.b6.a
    public final b6.c a() {
        ap apVarA = ap.a();
        apVarA.a.add("com.byd.overseaappstore.ui.vm.DownloadViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.FeatureViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.InstalledViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.MainViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.ManagementViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.ProtocolViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.SearchActiveViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.SettingViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.TopicDetailViewModel");
        apVarA.a.add("com.byd.overseaappstore.ui.vm.UpgradeViewModel");
        return new b6.c(apVarA.a.isEmpty() ? Collections.emptySet() : apVarA.a.size() == 1 ? Collections.singleton(apVarA.a.get(0)) : Collections.unmodifiableSet(new HashSet(apVarA.a)), new h5(this.a, this.b, null));
    }

    @Override // spi.ph
    public final void b(MainActivity mainActivity) {
        SoftwareBroadcastReceiver softwareBroadcastReceiver = new SoftwareBroadcastReceiver();
        softwareBroadcastReceiver.c = this.a.k.get();
        softwareBroadcastReceiver.d = this.a.l.get();
        softwareBroadcastReceiver.e = this.a.u.get();
        mainActivity.i = softwareBroadcastReceiver;
        mainActivity.j = new NetWorkStateBroadcastReceiver(this.a.k.get(), this.a.y.get(), this.a.g.get());
        LanguageChangeBroadcastReceiver languageChangeBroadcastReceiver = new LanguageChangeBroadcastReceiver();
        languageChangeBroadcastReceiver.c = this.a.c.get();
        mainActivity.k = languageChangeBroadcastReceiver;
        mainActivity.l = this.a.r.get();
        mainActivity.m = this.a.y.get();
        mainActivity.n = this.a.h.get();
        mainActivity.o = this.a.z.get();
        mainActivity.p = this.a.k.get();
        mainActivity.q = this.a.g.get();
    }

    @Override // spi.oc.a
    public final nc c() {
        return new c5(this.a, this.b, this.c, null);
    }
}
