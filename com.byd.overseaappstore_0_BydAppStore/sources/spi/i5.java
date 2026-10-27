package spi;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.ui.vm.DownloadViewModel;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import com.byd.overseaappstore.ui.vm.InstalledViewModel;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.ManagementViewModel;
import com.byd.overseaappstore.ui.vm.ProtocolViewModel;
import com.byd.overseaappstore.ui.vm.SearchActiveViewModel;
import com.byd.overseaappstore.ui.vm.SettingViewModel;
import com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class i5 extends tk {
    public final g5 a;
    public ll<DownloadViewModel> b;
    public ll<FeatureViewModel> c;
    public ll<InstalledViewModel> d;
    public ll<MainViewModel> e;
    public ll<ManagementViewModel> f;
    public ll<ProtocolViewModel> g;
    public ll<SearchActiveViewModel> h;
    public ll<SettingViewModel> i;
    public ll<SoftwareDetailViewModel> j;
    public ll<TopicDetailViewModel> k;
    public ll<UpgradeViewModel> l;

    public static final class a<T> implements ll<T> {
        public final g5 a;
        public final i5 b;
        public final int c;

        public a(g5 g5Var, a5 a5Var, i5 i5Var, int i) {
            this.a = g5Var;
            this.b = i5Var;
            this.c = i;
        }

        @Override // spi.ll
        public final T get() {
            switch (this.c) {
                case 0:
                    return (T) new DownloadViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.Q.get(), this.a.s.get(), this.a.M.get(), this.a.k.get(), this.a.l.get(), this.a.c.get());
                case 1:
                    return (T) new FeatureViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.S.get(), this.a.Q.get(), this.a.M.get(), this.a.s.get(), this.a.l.get(), this.a.T.get(), this.a.c.get());
                case 2:
                    return (T) new InstalledViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.Q.get(), this.a.k.get(), this.a.T.get(), this.a.l.get(), this.a.c.get());
                case 3:
                    return (T) new MainViewModel(this.a.Q.get(), this.a.k.get(), this.a.E.get(), this.a.f.get(), this.a.u.get(), this.a.s.get(), this.a.l.get(), this.a.O.get(), this.a.r.get(), this.a.y.get(), this.a.h.get(), this.a.T.get(), this.a.U.get(), this.a.x.get());
                case 4:
                    return (T) new ManagementViewModel(this.a.E.get(), this.a.H.get(), this.a.F.get(), this.a.l.get(), this.a.T.get());
                case 5:
                    return (T) new ProtocolViewModel(this.a.l.get());
                case 6:
                    return (T) new SearchActiveViewModel(new qo(this.b.a.V.get()), this.a.W.get(), this.a.H.get(), this.a.Q.get(), this.a.M.get(), this.a.s.get(), this.a.l.get(), this.a.c.get());
                case 7:
                    return (T) new SettingViewModel(this.a.f.get(), this.a.E.get(), this.a.Q.get(), this.a.k.get(), this.a.s.get(), this.a.l.get());
                case 8:
                    return (T) new SoftwareDetailViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.Q.get(), this.a.M.get(), this.a.s.get(), this.a.l.get(), this.a.c.get());
                case 9:
                    return (T) new TopicDetailViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.Q.get(), this.a.M.get(), this.a.s.get(), this.a.l.get(), this.a.c.get());
                case 10:
                    return (T) new UpgradeViewModel(this.a.E.get(), this.a.H.get(), this.a.I.get(), this.a.k.get(), this.a.Q.get(), this.a.s.get(), this.a.M.get(), this.a.l.get(), this.a.c.get());
                default:
                    throw new AssertionError(this.c);
            }
        }
    }

    private i5(g5 g5Var, a5 a5Var, SavedStateHandle savedStateHandle, hv hvVar) {
        this.a = g5Var;
        this.b = new a(g5Var, a5Var, this, 0);
        this.c = new a(g5Var, a5Var, this, 1);
        this.d = new a(g5Var, a5Var, this, 2);
        this.e = new a(g5Var, a5Var, this, 3);
        this.f = new a(g5Var, a5Var, this, 4);
        this.g = new a(g5Var, a5Var, this, 5);
        this.h = new a(g5Var, a5Var, this, 6);
        this.i = new a(g5Var, a5Var, this, 7);
        this.j = new a(g5Var, a5Var, this, 8);
        this.k = new a(g5Var, a5Var, this, 9);
        this.l = new a(g5Var, a5Var, this, 10);
    }

    public /* synthetic */ i5(g5 g5Var, a5 a5Var, SavedStateHandle savedStateHandle, hv hvVar, e eVar) {
        this(g5Var, a5Var, savedStateHandle, hvVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.b
    public final Map<String, ll<ViewModel>> a() {
        bi biVarA = bi.a();
        biVarA.a.put("com.byd.overseaappstore.ui.vm.DownloadViewModel", (V) this.b);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.FeatureViewModel", (V) this.c);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.InstalledViewModel", (V) this.d);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.MainViewModel", (V) this.e);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.ManagementViewModel", (V) this.f);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.ProtocolViewModel", (V) this.g);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.SearchActiveViewModel", (V) this.h);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.SettingViewModel", (V) this.i);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel", (V) this.j);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.TopicDetailViewModel", (V) this.k);
        biVarA.a.put("com.byd.overseaappstore.ui.vm.UpgradeViewModel", (V) this.l);
        return biVarA.a.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(biVarA.a);
    }
}
