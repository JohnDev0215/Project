package com.vivid.spotify.nowplaying;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.lifecycle.b0;
import androidx.lifecycle.u;
import androidx.navigation.NavController;
import androidx.navigation.q;
import androidx.recyclerview.widget.RecyclerView;
import com.spotify.auto.parcel.PlaybackSpeedActionParcelable;
import com.vivid.spotify.b;
import com.vivid.spotify.nowplaying.k;
import com.vivid.spotify.r;
import com.vivid.spotify.rootui.MainUIViewModel;
import com.vivid.spotify.t;
import com.vivid.spotify.v;
import d4.p3;
import d4.u1;
import d4.z1;
import g4.f;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NowPlayingFragment extends com.vivid.spotify.nowplaying.a {

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private PopupWindow f5317k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private k.b f5318l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private int f5319m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private int f5320n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    com.vivid.spotify.b f5321o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    i3.m f5322p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    f4.m f5323q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    g4.f f5324r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    NowPlayingViewModel f5325s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    f.c f5326t0 = new f.c() { // from class: com.vivid.spotify.nowplaying.d
        @Override // g4.f.c
        public final void a(View view, PlaybackStateCompat.CustomAction customAction) {
            this.f5337a.V1(view, customAction);
        }
    };

    class a implements u<b.EnumC0056b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f5327a;

        a(View view) {
            this.f5327a = view;
        }

        @Override // androidx.lifecycle.u
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(b.EnumC0056b enumC0056b) {
            h3.i.b("NowPlayingFragment").b("command " + enumC0056b.getValue(), new Object[0]);
            int i7 = b.f5329a[enumC0056b.ordinal()];
            if (i7 != 1) {
                if (i7 != 2) {
                    return;
                }
                NowPlayingFragment.this.f5321o0.d(b.EnumC0056b.COMMAND_NONE);
                NavController navControllerU1 = NowPlayingFragment.this.U1(this.f5327a);
                if (navControllerU1 != null) {
                    navControllerU1.v(t.nowPlayingFragment, false);
                    return;
                } else {
                    h3.i.b("NowPlayingFragment").b("ignore command", new Object[0]);
                    return;
                }
            }
            NowPlayingFragment.this.f5321o0.d(b.EnumC0056b.COMMAND_NONE);
            NavController navControllerU12 = NowPlayingFragment.this.U1(this.f5327a);
            if (navControllerU12 == null) {
                h3.i.b("NowPlayingFragment").b("ignore command", new Object[0]);
            } else if (navControllerU12.g().k() == t.queueFragment) {
                h3.i.b("NowPlayingFragment").b("current is queue", new Object[0]);
            } else {
                h3.i.b("NowPlayingFragment").b("current is not queue", new Object[0]);
                navControllerU12.o(t.action_global_queueFragment, new Bundle());
            }
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5329a;

        static {
            int[] iArr = new int[b.EnumC0056b.values().length];
            f5329a = iArr;
            try {
                iArr[b.EnumC0056b.COMMAND_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5329a[b.EnumC0056b.COMMAND_CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NavController U1(View view) {
        if (view == null) {
            h3.i.b("NowPlayingFragment").d("getNavController view is null", new Object[0]);
            return null;
        }
        try {
            return q.a(view);
        } catch (Exception e7) {
            h3.i.b("NowPlayingFragment").e(e7, "getNavController", new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W1(Boolean bool) {
        if (!bool.booleanValue() || this.f5317k0 == null) {
            return;
        }
        h3.i.b("NowPlayingFragment").b("metadata changed, dismiss", new Object[0]);
        this.f5317k0.dismiss();
    }

    public static void X1(RecyclerView recyclerView, ArrayList<PlaybackSpeedActionParcelable> arrayList) {
        k.b bVar = (k.b) recyclerView.getAdapter();
        if (bVar != null) {
            bVar.F(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Y1, reason: merged with bridge method [inline-methods] */
    public void V1(View view, PlaybackStateCompat.CustomAction customAction) {
        h3.i.b("NowPlayingFragment").b("a:%s", this);
        if (this.f5317k0 == null) {
            Context contextS = s();
            PopupWindow popupWindow = new PopupWindow(contextS);
            this.f5317k0 = popupWindow;
            popupWindow.setWidth(contextS.getResources().getDimensionPixelSize(r.now_playing_page_speed_list_width));
            int dimensionPixelSize = contextS.getResources().getDimensionPixelSize(r.now_playing_page_speed_list_height);
            this.f5317k0.setHeight(dimensionPixelSize);
            int dimensionPixelSize2 = contextS.getResources().getDimensionPixelSize(r.now_playing_bar_other_button_layout_size);
            this.f5319m0 = dimensionPixelSize2;
            this.f5320n0 = (dimensionPixelSize + dimensionPixelSize2) / 2;
            this.f5317k0.setBackgroundDrawable(null);
            p3 p3Var = (p3) androidx.databinding.g.a(LayoutInflater.from(contextS).inflate(v.list_speed, (ViewGroup) null));
            p3Var.d0(new k(this.f5323q0));
            k.b bVar = new k.b(this.f5317k0, this.f5322p0);
            this.f5318l0 = bVar;
            bVar.E(customAction);
            p3Var.G.setAdapter(this.f5318l0);
            this.f5317k0.setContentView(p3Var.G());
            this.f5317k0.setFocusable(true);
            this.f5317k0.setOutsideTouchable(true);
            this.f5317k0.update();
        }
        this.f5318l0.D(customAction);
        this.f5317k0.showAsDropDown(view, this.f5319m0, this.f5320n0, 8388611);
    }

    @Override // androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        PopupWindow popupWindow = this.f5317k0;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        this.f5324r0.x(this.f5326t0);
    }

    @Override // androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        this.f5324r0.e(this.f5326t0);
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewG;
        MainUIViewModel mainUIViewModel = (MainUIViewModel) new b0(j()).a(MainUIViewModel.class);
        this.f5325s0 = (NowPlayingViewModel) new b0(j()).a(NowPlayingViewModel.class);
        if (mainUIViewModel.isRightDriver.q()) {
            z1 z1Var = (z1) androidx.databinding.g.i(layoutInflater, v.fragment_now_playing_rhd, viewGroup, false);
            z1Var.d0(this.f5325s0);
            viewG = z1Var.G();
        } else {
            u1 u1Var = (u1) androidx.databinding.g.i(layoutInflater, v.fragment_now_playing, viewGroup, false);
            u1Var.d0(this.f5325s0);
            viewG = u1Var.G();
        }
        this.f5325s0.isMetadataChanged.f(X(), new u() { // from class: com.vivid.spotify.nowplaying.c
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5336a.W1((Boolean) obj);
            }
        });
        this.f5321o0.b(X(), new a(viewG));
        return viewG;
    }
}
