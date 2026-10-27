package com.vivid.spotify.nowplaying;

import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f;
import com.spotify.auto.parcel.PlaybackSpeedActionParcelable;
import com.vivid.spotify.q;
import d4.u3;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SpeedListViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.databinding.k<PlaybackSpeedActionParcelable> f5342a = new androidx.databinding.k<>();

    /* JADX INFO: compiled from: SpeedListViewModel.java */
    class a implements q4.g<ArrayList<PlaybackSpeedActionParcelable>> {
        a() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("SpeedListViewModel").e(th, "a error", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ArrayList<PlaybackSpeedActionParcelable> arrayList) {
            k.this.f5342a.addAll(arrayList);
        }
    }

    /* JADX INFO: compiled from: SpeedListViewModel.java */
    public static class b extends RecyclerView.g<RecyclerView.c0> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final ArrayList<PlaybackSpeedActionParcelable> f5344c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f5345d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final PopupWindow f5346e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final i3.m f5347f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private PlaybackStateCompat.CustomAction f5348g;

        /* JADX INFO: compiled from: SpeedListViewModel.java */
        static class a extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            private final u3 f5349t;

            a(u3 u3Var) {
                super(u3Var.G());
                this.f5349t = u3Var;
            }
        }

        public b(PopupWindow popupWindow, i3.m mVar) {
            this.f5346e = popupWindow;
            this.f5347f = mVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void C(u3 u3Var, View view) {
            this.f5347f.P(this.f5344c.get(u3Var.d0().a()).f5010g, new Bundle());
            this.f5346e.dismiss();
        }

        public void D(PlaybackStateCompat.CustomAction customAction) {
            if (customAction == null) {
                return;
            }
            String strC = customAction.c();
            int size = this.f5344c.size();
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    break;
                }
                if (TextUtils.equals(this.f5344c.get(i8).f5010g, strC)) {
                    i7 = i8;
                    break;
                }
                i8++;
            }
            int i9 = i7 == 0 ? size - 1 : i7 - 1;
            int i10 = this.f5345d;
            if (i10 == i9) {
                k(i10);
                return;
            }
            if (i10 != -1) {
                k(i10);
            }
            this.f5345d = i9;
            k(i9);
        }

        public void E(PlaybackStateCompat.CustomAction customAction) {
            this.f5348g = customAction;
        }

        public void F(List<PlaybackSpeedActionParcelable> list) {
            f.c cVarA = androidx.recyclerview.widget.f.a(new v3.b(this.f5344c, list));
            this.f5344c.clear();
            this.f5344c.addAll(list);
            cVarA.e(this);
            D(this.f5348g);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            return this.f5344c.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void q(RecyclerView.c0 c0Var, int i7) {
            j jVarD0 = ((a) c0Var).f5349t.d0();
            jVarD0.f5339b.r(y.b.b(this.f5347f.m(), this.f5344c.get(i7).f5011h, null));
            jVarD0.f5341d.r(this.f5345d == i7);
            jVarD0.b(i7);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 s(ViewGroup viewGroup, int i7) {
            final u3 u3VarE0 = u3.e0(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
            j jVar = new j();
            jVar.f5340c.r(Integer.valueOf(viewGroup.getContext().getColor(q.colorPrimary)));
            u3VarE0.g0(jVar);
            u3VarE0.G().setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.nowplaying.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f5350e.C(u3VarE0, view);
                }
            });
            return new a(u3VarE0);
        }
    }

    public k(f4.m mVar) {
        mVar.H().f(s4.a.a()).h(e5.a.b()).a(new a());
    }
}
