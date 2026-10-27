package com.vivid.spotify.queue;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.b0;
import androidx.lifecycle.u;
import androidx.navigation.q;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import com.vivid.spotify.MainActivity;
import com.vivid.spotify.b;
import com.vivid.spotify.common.ui.BaseListItemViewModel;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;
import com.vivid.spotify.r;
import com.vivid.spotify.rootui.MainUIViewModel;
import com.vivid.spotify.s;
import com.vivid.spotify.t;
import com.vivid.spotify.x;
import d4.d5;
import d4.j2;
import d4.o2;
import f4.m;
import i3.m;
import i3.v;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class QueueFragment extends com.vivid.spotify.queue.a {

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    m f5357p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    i3.m f5358q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    y3.b f5359r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    QueueViewModel f5360s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    com.vivid.spotify.b f5361t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    NowPlayingViewModel f5362u0;

    class a implements u<b.EnumC0056b> {
        a() {
        }

        @Override // androidx.lifecycle.u
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(b.EnumC0056b enumC0056b) {
            h3.i.b("QueueFragment").b("command " + enumC0056b.getValue(), new Object[0]);
            int i7 = b.f5364a[enumC0056b.ordinal()];
            if (i7 == 1) {
                QueueFragment.this.f5361t0.d(b.EnumC0056b.COMMAND_NONE);
            } else {
                if (i7 != 2) {
                    return;
                }
                QueueFragment.this.f5361t0.d(b.EnumC0056b.COMMAND_NONE);
                QueueFragment.this.T1();
            }
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5364a;

        static {
            int[] iArr = new int[b.EnumC0056b.values().length];
            f5364a = iArr;
            try {
                iArr[b.EnumC0056b.COMMAND_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5364a[b.EnumC0056b.COMMAND_CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public class c extends com.vivid.spotify.common.base.d<RecyclerView.c0> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<k> f5365c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Application f5366d;

        class a extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final d4.f f5368t;

            a(d4.f fVar) {
                super(fVar.G());
                this.f5368t = fVar;
            }
        }

        public class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            d5 f5370t;

            public b(d5 d5Var) {
                super(d5Var.G());
                this.f5370t = d5Var;
            }
        }

        public c(Application application) {
            this.f5366d = application;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void F(BaseListItemViewModel baseListItemViewModel, View view) {
            MediaSessionCompat.QueueItem queueItemA = ((k) baseListItemViewModel.getContent()).a();
            if (queueItemA != null) {
                long jC = v.c(queueItemA);
                if (QueueFragment.this.f5358q0.k() != jC) {
                    QueueFragment.this.f5362u0.cover.r(null);
                }
                QueueFragment.this.f5358q0.U(jC);
                q.a(QueueFragment.this.f5218j0.G()).v(t.nowPlayingFragment, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void G() {
            QueueFragment queueFragment = QueueFragment.this;
            queueFragment.f5359r0.c(queueFragment.T(x.added_to_queue));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void H(BaseListItemViewModel baseListItemViewModel, View view) {
            MediaSessionCompat.QueueItem queueItemA = ((k) baseListItemViewModel.getContent()).a();
            if (queueItemA == null) {
                return;
            }
            if (v.c(queueItemA) == QueueFragment.this.f5358q0.k()) {
                QueueFragment.this.f5358q0.j(queueItemA.h(), new m.d() { // from class: com.vivid.spotify.queue.d
                    @Override // i3.m.d
                    public final void b() {
                        this.f5381a.G();
                    }
                });
            } else {
                QueueFragment.this.f5358q0.L(queueItemA.h());
            }
        }

        @Override // com.vivid.spotify.common.base.d
        public int B() {
            return QueueFragment.this.N().getDimensionPixelSize(r.queue_title_min_height);
        }

        public void I(List<k> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new v3.b(this.f5365c, list));
            this.f5365c.clear();
            this.f5365c.addAll(list);
            cVarA.e(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5365c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int g(int i7) {
            return "type_title".equals(this.f5365c.get(i7).c()) ? 12 : 13;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            k kVar = this.f5365c.get(i7);
            if (!(c0Var instanceof a)) {
                if (c0Var instanceof b) {
                    ((b) c0Var).f5370t.d0().f5382a.r(kVar.b());
                    return;
                }
                return;
            }
            BaseListItemViewModel baseListItemViewModelD0 = ((a) c0Var).f5368t.d0();
            MediaSessionCompat.QueueItem queueItemA = kVar.a();
            if (queueItemA == null) {
                return;
            }
            baseListItemViewModelD0.setContent(kVar);
            int iH = (int) (i.h(queueItemA) * 1000.0d);
            baseListItemViewModelD0.isCompletionPercentageShow.r(iH < 1000 && iH > 0);
            baseListItemViewModelD0.completionPercentage.r(iH);
            baseListItemViewModelD0.title.r(BidiFormatter.getInstance().unicodeWrap(v.f(queueItemA)));
            baseListItemViewModelD0.subTitle.r(BidiFormatter.getInstance().unicodeWrap(v.e(queueItemA)));
            baseListItemViewModelD0.isExplicit.r(v.g(queueItemA));
            baseListItemViewModelD0.is19Plus.r(i.i(queueItemA));
            baseListItemViewModelD0.setPlaceHolderByUri(v.b(queueItemA));
            if (QueueFragment.this.f5358q0.z()) {
                baseListItemViewModelD0.showRightButton.r(true);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            if (i7 == 12) {
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
                j jVar = new j();
                d5 d5VarE0 = d5.e0(layoutInflaterFrom, viewGroup, false);
                d5VarE0.g0(jVar);
                return new b(d5VarE0);
            }
            LayoutInflater layoutInflaterFrom2 = LayoutInflater.from(viewGroup.getContext());
            Application application = this.f5366d;
            QueueFragment queueFragment = QueueFragment.this;
            final BaseListItemViewModel baseListItemViewModel = new BaseListItemViewModel(application, queueFragment.f5357p0, queueFragment.f5358q0);
            QueueFragment.this.N1(baseListItemViewModel);
            baseListItemViewModel.setBackground(QueueFragment.this.N().getDrawable(s.queue_list_item_bg, QueueFragment.this.s().getTheme()));
            d4.f fVarE0 = d4.f.e0(layoutInflaterFrom2, viewGroup, false);
            baseListItemViewModel.setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.queue.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5379e.F(baseListItemViewModel, view);
                }
            });
            baseListItemViewModel.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.queue.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5377e.H(baseListItemViewModel, view);
                }
            });
            fVarE0.g0(baseListItemViewModel);
            return new a(fVarE0);
        }
    }

    public static void a2(ShimmerRecyclerView shimmerRecyclerView, List<k> list) {
        if (list.size() != 0) {
            shimmerRecyclerView.J1();
            c cVar = (c) shimmerRecyclerView.getAdapter();
            if (cVar != null) {
                cVar.I(list);
            }
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        androidx.fragment.app.d dVarJ = j();
        if (dVarJ instanceof MainActivity) {
            ((MainActivity) dVarJ).c0(true);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        androidx.fragment.app.d dVarJ = j();
        if (dVarJ instanceof MainActivity) {
            ((MainActivity) dVarJ).c0(false);
        }
    }

    @Override // com.vivid.spotify.common.base.h, com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MainUIViewModel mainUIViewModel = (MainUIViewModel) new b0(j()).a(MainUIViewModel.class);
        c cVar = new c(j().getApplication());
        this.f5360s0 = (QueueViewModel) new b0(this).a(QueueViewModel.class);
        this.f5362u0 = (NowPlayingViewModel) new b0(j()).a(NowPlayingViewModel.class);
        if (mainUIViewModel.isRightDriver.q()) {
            o2 o2Var = (o2) androidx.databinding.g.i(layoutInflater, com.vivid.spotify.v.fragment_queue_rhd, viewGroup, false);
            o2Var.d0(this.f5360s0);
            o2Var.H.h(new com.vivid.spotify.common.ui.c(1, N().getDimensionPixelSize(r.base_list_item_decoration_size)));
            o2Var.H.setAdapter(cVar);
            this.f5218j0 = o2Var;
        } else {
            j2 j2Var = (j2) androidx.databinding.g.i(layoutInflater, com.vivid.spotify.v.fragment_queue, viewGroup, false);
            j2Var.d0(this.f5360s0);
            j2Var.H.h(new com.vivid.spotify.common.ui.c(1, N().getDimensionPixelSize(r.base_list_item_decoration_size)));
            j2Var.H.setAdapter(cVar);
            this.f5218j0 = j2Var;
        }
        this.f5361t0.b(X(), new a());
        return this.f5218j0.G();
    }
}
