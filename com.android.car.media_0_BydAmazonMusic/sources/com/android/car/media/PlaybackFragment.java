package com.android.car.media;

import android.car.utils.Logs;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.apps.common.BackgroundImageView;
import com.android.car.media.MediaActivity;
import com.android.car.media.PlaybackFragment;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.MinimizedPlaybackControlBar;
import com.android.car.media.common.PlaybackControlsActionBar;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerViewImpl;
import com.android.car.ui.recyclerview.ScrollingLimitedViewHolder;
import com.android.car.ui.toolbar.b;
import com.android.car.ui.toolbar.j;
import com.android.car.uxr.LifeCycleObserverUxrContentLimiter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import spi.a1;
import spi.c3;
import spi.jc;
import spi.k8;
import spi.kc;
import spi.l4;
import spi.lc;
import spi.m;
import spi.m8;
import spi.n8;
import spi.s6;
import spi.s7;
import spi.s8;
import spi.u9;
import spi.uc;
import spi.v4;
import spi.w2;
import spi.x4;
import spi.z7;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackFragment extends Fragment {
    public static final /* synthetic */ int J = 0;
    public boolean A;
    public boolean B;
    public boolean C;
    public int D;
    public MediaActivity.ViewModel E;
    public com.android.car.ui.toolbar.b F;
    public int G;
    public e H;
    public a I = new a();
    public LifeCycleObserverUxrContentLimiter a;
    public v4<MediaItemMetadata.b> b;
    public m c;
    public BackgroundImageView d;
    public View e;
    public PlaybackControlsActionBar f;
    public MinimizedPlaybackControlBar g;
    public c h;
    public CarUiRecyclerView i;
    public View j;
    public ViewGroup k;
    public SeekBar l;
    public List<View> m;
    public List<View> n;
    public List<View> o;
    public List<View> p;
    public List<View> q;
    public DefaultItemAnimator r;
    public b s;
    public PlaybackViewModel.d t;
    public Long u;
    public CharSequence v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    public class QueueViewHolder extends RecyclerView.ViewHolder {
        public final View a;
        public final ViewGroup b;
        public final TextView c;
        public final TextView d;
        public final TextView e;
        public final TextView f;
        public final TextView g;
        public final ImageView h;
        public final x4<MediaItemMetadata.b> i;

        public QueueViewHolder(View view) {
            super(view);
            this.a = view;
            this.b = (ViewGroup) view.findViewById(com.android.car.media.main.R$id.thumbnail_container);
            ImageView imageView = (ImageView) view.findViewById(com.android.car.media.main.R$id.thumbnail);
            view.findViewById(com.android.car.media.main.R$id.spacer);
            this.c = (TextView) view.findViewById(com.android.car.media.main.R$id.queue_list_item_title);
            this.d = (TextView) view.findViewById(com.android.car.media.main.R$id.queue_list_item_subtitle);
            this.e = (TextView) view.findViewById(com.android.car.media.main.R$id.current_time);
            this.f = (TextView) view.findViewById(com.android.car.media.main.R$id.max_time);
            this.g = (TextView) view.findViewById(com.android.car.media.main.R$id.separator);
            this.h = (ImageView) view.findViewById(com.android.car.media.main.R$id.now_playing_icon);
            this.i = new x4<>(s6.a(view.getContext()), imageView);
        }
    }

    public class a implements CarUiRecyclerView.c {
        public a() {
        }

        @Override // com.android.car.ui.recyclerview.CarUiRecyclerView.c
        public final void a() {
        }

        @Override // com.android.car.ui.recyclerview.CarUiRecyclerView.c
        public final void b() {
            PlaybackFragment playbackFragment = PlaybackFragment.this;
            int i = PlaybackFragment.J;
            playbackFragment.f(null);
        }
    }

    public interface b {
    }

    public class c extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements w2 {
        public Integer e;
        public boolean f;
        public Integer g;
        public int h;
        public List<MediaItemMetadata> b = Collections.emptyList();
        public String c = "";
        public String d = "";
        public kc a = kc.a;

        public c() {
        }

        @Override // spi.w2
        public final void a(int i) {
            if (i >= 0) {
                this.a = new lc(this, i);
            } else {
                this.a = kc.a;
            }
            d();
        }

        @Override // spi.w2
        public final void b(int i) {
            Integer num = this.g;
            if (num == null || num.intValue() != i) {
                this.g = Integer.valueOf(i);
                this.a.c();
            }
        }

        @Override // spi.w2
        public final int c() {
            return com.android.car.media.main.R$id.playback_fragment_now_playing_list_uxr_config;
        }

        public final void d() {
            kc kcVar = this.a;
            List<MediaItemMetadata> list = this.b;
            kcVar.a(list != null ? list.size() : 0, e());
            notifyDataSetChanged();
        }

        public final int e() {
            Integer num = this.e;
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }

        public final void f() {
            Integer num = this.e;
            int iF = num == null ? -1 : this.a.f(num.intValue());
            if (iF != -1) {
                notifyItemChanged(iF);
            }
        }

        public final void g() {
            Integer num = this.e;
            int iF = num == null ? -1 : this.a.f(num.intValue());
            if (iF != -1) {
                PlaybackFragment.this.i.scrollToPosition(iF);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return this.a.b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final long getItemId(int i) {
            if (this.a.d(i) == -1) {
                return -1L;
            }
            Long l = this.b.get(i).b;
            if (l == null) {
                Log.e("PlaybackFragment", "getItemId: id == null !!!");
            }
            if (l == null) {
                return -1L;
            }
            return l.longValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemViewType(int i) {
            return this.a.d(i) == -1 ? -1 : 0;
        }

        public final void h(boolean z) {
            Long l;
            Integer numValueOf = null;
            if (this.b == null || (l = PlaybackFragment.this.u) == null) {
                this.e = null;
                d();
                return;
            }
            int i = 0;
            if (l.longValue() != -1) {
                while (true) {
                    if (i >= this.b.size()) {
                        break;
                    }
                    if (Objects.equals(this.b.get(i).b, PlaybackFragment.this.u)) {
                        numValueOf = Integer.valueOf(i);
                        break;
                    }
                    i++;
                }
                f();
                this.e = numValueOf;
                if (z) {
                    d();
                } else {
                    this.a.e(e());
                }
                g();
                f();
                return;
            }
            if (this.b == null || PlaybackFragment.this.v == null) {
                this.e = null;
                d();
                return;
            }
            while (true) {
                if (i >= this.b.size()) {
                    break;
                }
                if (Objects.equals(this.b.get(i).f(), PlaybackFragment.this.v)) {
                    numValueOf = Integer.valueOf(i);
                    break;
                }
                i++;
            }
            f();
            this.e = numValueOf;
            if (z) {
                d();
            } else {
                this.a.e(e());
            }
            g();
            f();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            CharSequence charSequence;
            if (!(viewHolder instanceof QueueViewHolder)) {
                if (viewHolder instanceof ScrollingLimitedViewHolder) {
                    ((ScrollingLimitedViewHolder) viewHolder).a(this.g);
                    return;
                } else {
                    StringBuilder sbK = spi.c.k("unknown holder class ");
                    sbK.append(viewHolder.getClass());
                    throw new IllegalArgumentException(sbK.toString());
                }
            }
            int iD = this.a.d(i);
            if (iD == -1) {
                StringBuilder sbL = spi.c.l("onBindViewHolder invalid position ", i, " ");
                sbL.append(this.a.toString());
                Log.e("PlaybackFragment", sbL.toString());
                return;
            }
            int size = this.b.size();
            if (iD < 0 || iD >= size) {
                StringBuilder sbM = spi.c.m("onBindViewHolder pos: ", i, " gave index: ", iD, " out of bounds size: ");
                sbM.append(size);
                sbM.append(" ");
                sbM.append(this.a.toString());
                Log.e("PlaybackFragment", sbM.toString());
                return;
            }
            QueueViewHolder queueViewHolder = (QueueViewHolder) viewHolder;
            MediaItemMetadata mediaItemMetadata = this.b.get(iD);
            queueViewHolder.a.setOnClickListener(new s8(queueViewHolder, mediaItemMetadata, 0));
            uc.e(queueViewHolder.b, PlaybackFragment.this.A);
            if (PlaybackFragment.this.A) {
                queueViewHolder.i.d(queueViewHolder.a.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
            }
            queueViewHolder.c.setText(mediaItemMetadata == null ? "" : mediaItemMetadata.f());
            boolean z = (mediaItemMetadata == null || (charSequence = PlaybackFragment.this.v) == null || !charSequence.equals(mediaItemMetadata.f())) ? false : true;
            if (z) {
                queueViewHolder.e.setText(PlaybackFragment.this.h.c);
                queueViewHolder.f.setText(PlaybackFragment.this.h.d);
            }
            PlaybackFragment playbackFragment = PlaybackFragment.this;
            boolean z2 = playbackFragment.y && z && playbackFragment.h.f;
            uc.e(queueViewHolder.e, z2);
            uc.e(queueViewHolder.f, z2);
            uc.e(queueViewHolder.g, z2);
            queueViewHolder.a.setSelected(z);
            PlaybackFragment playbackFragment2 = PlaybackFragment.this;
            boolean z3 = playbackFragment2.z && z;
            if (z3) {
                queueViewHolder.c.setTextColor(playbackFragment2.getContext().getColor(com.android.car.media.main.R$color.byd_play_list_active_icon_color));
                PlaybackFragment playbackFragment3 = PlaybackFragment.this;
                playbackFragment3.h.h = i;
                queueViewHolder.d.setTextColor(playbackFragment3.getContext().getColor(com.android.car.media.main.R$color.byd_play_list_active_subtitle_color));
            } else {
                queueViewHolder.c.setTextColor(playbackFragment2.getContext().getColor(com.android.car.media.main.R$color.queue_list_item_title_text_color));
                queueViewHolder.d.setTextColor(PlaybackFragment.this.getContext().getColor(com.android.car.media.main.R$color.queue_list_item_subtitle_text_color));
            }
            AnimationDrawable animationDrawable = (AnimationDrawable) AppCompatResources.getDrawable(BaseMediaApplication.b, com.android.car.media.main.R$drawable.ic_equalizer_anim_bg_2);
            if (animationDrawable != null) {
                if (PlaybackFragment.this.G == 3) {
                    animationDrawable.start();
                } else {
                    animationDrawable.stop();
                }
            }
            queueViewHolder.h.setImageDrawable(animationDrawable);
            uc.e(queueViewHolder.h, z3);
            if (PlaybackFragment.this.B) {
                CharSequence charSequenceE = mediaItemMetadata != null ? mediaItemMetadata.e() : "";
                if (charSequenceE == null || charSequenceE.length() <= 0) {
                    queueViewHolder.d.setVisibility(8);
                } else {
                    queueViewHolder.d.setText(charSequenceE);
                    queueViewHolder.d.setVisibility(0);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == -1) {
                return ScrollingLimitedViewHolder.b(viewGroup);
            }
            return PlaybackFragment.this.new QueueViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(com.android.car.media.main.R$layout.queue_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
            super.onViewAttachedToWindow(viewHolder);
            if (viewHolder instanceof QueueViewHolder) {
                QueueViewHolder queueViewHolder = (QueueViewHolder) viewHolder;
                if (PlaybackFragment.this.A) {
                    Context context = queueViewHolder.a.getContext();
                    x4<MediaItemMetadata.b> x4Var = queueViewHolder.i;
                    if (x4Var.k) {
                        x4Var.d(context, x4Var.j);
                    }
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
            super.onViewDetachedFromWindow(viewHolder);
            if (viewHolder instanceof QueueViewHolder) {
                QueueViewHolder queueViewHolder = (QueueViewHolder) viewHolder;
                if (PlaybackFragment.this.A) {
                    queueViewHolder.i.e(queueViewHolder.a.getContext());
                }
            }
        }
    }

    public static class d extends RecyclerView.ItemDecoration {
        public int a;
        public int b;

        public d(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            if (recyclerView.getChildAdapterPosition(view) == this.b) {
                rect.top = this.a;
            }
        }
    }

    public static void c(PlaybackFragment playbackFragment, PlaybackViewModel.e eVar) {
        Objects.requireNonNull(playbackFragment);
        if (eVar == null || playbackFragment.G == eVar.c()) {
            return;
        }
        playbackFragment.G = eVar.c();
        StringBuilder sbK = spi.c.k("playbackStateWrapper state: ");
        sbK.append(eVar.c());
        Log.d("PlaybackFragment", sbK.toString());
        int i = playbackFragment.G;
        if (i == 3 || i == 2) {
            c cVar = playbackFragment.h;
            cVar.notifyItemChanged(cVar.h);
        }
    }

    public final PlaybackViewModel d() {
        return PlaybackViewModel.a(BaseMediaApplication.b, 1);
    }

    public final void e(boolean z, boolean z2, boolean z3) {
        j jVar;
        int i = z3 ? 0 : this.D;
        if (this.w != z) {
            this.w = z;
            h(z);
        }
        com.android.car.ui.toolbar.b bVar = this.F;
        if (bVar != null) {
            bVar.c(z2);
        }
        m mVar = this.c;
        if (mVar != null && (jVar = mVar.c) != null) {
            jVar.showLineView(z2);
        }
        if (this.x != z2) {
            this.x = z2;
            if (z2) {
                uc.h(this.o, i);
                uc.c(this.n, i);
                m mVar2 = this.c;
                if (mVar2 != null) {
                    mVar2.e(s7.CLOSE);
                }
                uc.e(this.g, true);
                uc.e(this.f, false);
            } else {
                uc.c(this.o, i);
                uc.h(this.n, i);
                m mVar3 = this.c;
                if (mVar3 != null) {
                    mVar3.e(s7.DOWN);
                }
                uc.e(this.g, false);
                uc.e(this.f, true);
            }
            uc.f(this.q, this.x);
            uc.f(this.p, !this.x);
        }
    }

    public final void f(@Nullable Boolean bool) {
        if (bool != null) {
            try {
                View view = this.j;
                if (view != null) {
                    view.setVisibility(bool.booleanValue() ? 0 : 8);
                    return;
                }
            } catch (Exception e) {
                Logs.e("PlaybackFragment", "showQueueBottom: " + e);
                return;
            }
        }
        u9 scrollBar = ((CarUiRecyclerViewImpl) this.i).getScrollBar();
        boolean z = (scrollBar == null || scrollBar.isAtEnd()) ? false : true;
        View view2 = this.j;
        if (view2 != null) {
            view2.setVisibility(z ? 0 : 8);
        }
    }

    public final void g(boolean z) {
        boolean z2 = !this.x;
        Log.e("PlaybackFragment", "toggleQueueVisibility: updatedQueueVisibility=" + z2 + " isVoice=" + z);
        e(this.w, z2, z);
        this.E.c().g = z2;
        f(z2 ? null : Boolean.FALSE);
        if (z) {
            return;
        }
        d().k.postValue(Boolean.valueOf(z2));
    }

    public final void h(boolean z) {
        if (z && this.F == null) {
            b.C0013b c0013b = new b.C0013b(getContext());
            c0013b.b(com.android.car.media.main.R$drawable.ic_queue_button);
            c0013b.q = true;
            c0013b.i = new n8(this);
            this.F = c0013b.a();
        }
        this.c.c.setMenuItems(z ? Collections.singletonList(this.F) : Collections.emptyList());
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        e eVar = new e(this);
        this.H = eVar;
        NetStateChangeReceiver.c(eVar);
        Logs.d("PlaybackFragment", "initNetListener finished " + z7.a());
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(com.android.car.media.main.R$layout.fragment_playback, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        NetStateChangeReceiver.d(this.H);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        this.d = (BackgroundImageView) view.findViewById(com.android.car.media.main.R$id.playback_background);
        this.i = (CarUiRecyclerView) view.findViewById(com.android.car.media.main.R$id.queue_list);
        this.j = view.findViewById(com.android.car.media.main.R$id.queue_list_bottom);
        this.k = (ViewGroup) view.findViewById(com.android.car.media.main.R$id.playback_seek_bar_container);
        this.l = (SeekBar) view.findViewById(com.android.car.media.main.R$id.playback_seek_bar);
        if (!a1.a()) {
            int i = a1.a;
        }
        this.l.setContentDescription("com.android.car.ui.utils.DIRECT_MANIPULATION");
        final int i2 = 1;
        m mVar = new m(view.getContext(), com.android.car.ui.pluginsupport.c.a(view.getContext()).installBaseLayoutAround(view, new l4(view), true, true));
        this.c = mVar;
        mVar.c.setTitle(com.android.car.media.main.R$string.fragment_playback_title);
        j jVar = this.c.c;
        final int i3 = 0;
        if (jVar != null) {
            jVar.showLineView(false);
        }
        this.c.c.setBackgroundShown(false);
        this.c.e(s7.DOWN);
        MediaSourceViewModel.a(BaseMediaApplication.b, 1).c.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i3) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i4 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i5 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i6 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i7 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        uc.e(view.findViewById(com.android.car.media.main.R$id.background_scrim), false);
        View viewFindViewById = view.findViewById(com.android.car.media.main.R$id.control_bar_scrim);
        this.e = viewFindViewById;
        if (viewFindViewById != null) {
            uc.e(viewFindViewById, false);
            this.e.setOnClickListener(new k8(this, 0));
            this.e.setClickable(false);
        }
        Resources resources = getResources();
        this.y = resources.getBoolean(com.android.car.media.main.R$bool.show_time_for_now_playing_queue_list_item);
        this.z = resources.getBoolean(com.android.car.media.main.R$bool.show_icon_for_now_playing_queue_list_item);
        if (getContext() != null) {
            this.A = getContext().getResources().getBoolean(com.android.car.media.main.R$bool.show_thumbnail_for_queue_list_item);
            this.C = getContext().getResources().getBoolean(com.android.car.media.main.R$bool.show_linear_progress_bar);
            this.B = getContext().getResources().getBoolean(com.android.car.media.main.R$bool.show_subtitle_for_queue_list_item);
        } else {
            Log.e("PlaybackFragment", "context == null !!!");
        }
        this.E = (MediaActivity.ViewModel) ViewModelProviders.of(requireActivity()).get(MediaActivity.ViewModel.class);
        d().i.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i2) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i4 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i5 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i6 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i7 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        PlaybackControlsActionBar playbackControlsActionBar = (PlaybackControlsActionBar) view.findViewById(com.android.car.media.main.R$id.playback_controls);
        this.f = playbackControlsActionBar;
        PlaybackViewModel playbackViewModelD = d();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        playbackControlsActionBar.t.d(playbackViewModelD, viewLifecycleOwner);
        playbackControlsActionBar.getContext();
        c3.a(viewLifecycleOwner, playbackViewModelD, playbackControlsActionBar.s, playbackControlsActionBar.u);
        final int i4 = 3;
        d().j.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i4) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i5 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i6 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i7 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        this.f.m = new n8(this);
        this.g = (MinimizedPlaybackControlBar) view.findViewById(com.android.car.media.main.R$id.minimized_playback_controls);
        this.g.f(d(), getViewLifecycleOwner(), s6.a(BaseMediaApplication.b));
        final int i5 = 4;
        d().j.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i5) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i6 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i7 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        ImageView imageView = (ImageView) view.findViewById(com.android.car.media.main.R$id.album_art);
        new com.android.car.media.common.a(getViewLifecycleOwner(), d(), (TextView) view.findViewById(com.android.car.media.main.R$id.title), (TextView) view.findViewById(com.android.car.media.main.R$id.artist), (TextView) view.findViewById(com.android.car.media.main.R$id.album_title), (TextView) view.findViewById(com.android.car.media.main.R$id.outer_separator), (TextView) view.findViewById(com.android.car.media.main.R$id.current_time), (TextView) view.findViewById(com.android.car.media.main.R$id.inner_separator), (TextView) view.findViewById(com.android.car.media.main.R$id.max_time), this.C ? this.l : null, imageView, s6.a(view.getContext()));
        this.D = getResources().getInteger(com.android.car.media.main.R$integer.fragment_playback_queue_fade_duration_ms);
        this.i.addItemDecoration(new d(getResources().getDimensionPixelSize(com.android.car.media.main.R$dimen.playback_queue_list_padding_top), 0));
        this.i.setVerticalFadingEdgeEnabled(getResources().getBoolean(com.android.car.media.main.R$bool.queue_fading_edge_length_enabled));
        this.h = new c();
        this.i.addOnScrollListener(this.I);
        final int i6 = 5;
        d().j.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i6) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i7 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        final int i7 = 6;
        d().e.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i7) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        this.i.setAdapter(this.h);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        this.r = defaultItemAnimator;
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.i.setItemAnimator(this.r);
        h(this.w);
        com.android.car.ui.toolbar.b bVar = this.F;
        if (bVar != null) {
            bVar.c(this.x);
        }
        final int i8 = 7;
        d().f.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i8) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        final int i9 = 8;
        d().g.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i9) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        final int i10 = 9;
        d().l.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i10) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        final int i11 = 10;
        d().k.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i11) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        n8 n8Var = new n8(this);
        this.m = (ArrayList) uc.a(view, resources, com.android.car.media.main.R$array.playback_views_to_hide_when_showing_custom_actions, n8Var);
        this.n = (ArrayList) uc.a(view, resources, com.android.car.media.main.R$array.playback_views_to_hide_when_queue_is_visible, n8Var);
        this.o = (ArrayList) uc.a(view, resources, com.android.car.media.main.R$array.playback_views_to_show_when_queue_is_visible, null);
        this.p = (ArrayList) uc.a(view, resources, com.android.car.media.main.R$array.playback_views_to_hide_immediately_when_queue_is_visible, n8Var);
        this.q = (ArrayList) uc.a(view, resources, com.android.car.media.main.R$array.playback_views_to_show_immediately_when_queue_is_visible, null);
        this.b = new v4<>(v4.b.BACKGROUND, s6.a(getContext()), new m8(this, i3));
        final int i12 = 2;
        d().e.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.l8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i12) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        b7 b7Var = (b7) obj;
                        m mVar2 = playbackFragment.c;
                        mVar2.o = b7Var != null ? new BitmapDrawable(playbackFragment.getResources(), b7Var.c()) : null;
                        mVar2.f();
                        break;
                    case 1:
                        this.b.t = (PlaybackViewModel.d) obj;
                        break;
                    case 2:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.b.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                    case 3:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 4:
                        PlaybackFragment.c(this.b, (PlaybackViewModel.e) obj);
                        break;
                    case 5:
                        PlaybackFragment playbackFragment3 = this.b;
                        PlaybackViewModel.e eVar = (PlaybackViewModel.e) obj;
                        int i42 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment3);
                        Long lValueOf = eVar != null ? Long.valueOf(eVar.c.getActiveQueueItemId()) : null;
                        if (!Objects.equals(playbackFragment3.u, lValueOf)) {
                            playbackFragment3.u = lValueOf;
                            playbackFragment3.h.h(false);
                        }
                        break;
                    case 6:
                        PlaybackFragment playbackFragment4 = this.b;
                        MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj;
                        int i52 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment4);
                        if (mediaItemMetadata2 != null) {
                            CharSequence charSequenceF = mediaItemMetadata2.f();
                            if (!Objects.equals(playbackFragment4.v, charSequenceF)) {
                                playbackFragment4.v = charSequenceF;
                                playbackFragment4.h.h(false);
                            }
                            break;
                        }
                        break;
                    case 7:
                        List listEmptyList = (List) obj;
                        PlaybackFragment.c cVar = this.b.h;
                        Objects.requireNonNull(cVar);
                        if (listEmptyList == null) {
                            listEmptyList = Collections.emptyList();
                        }
                        ArrayList arrayList = new ArrayList(listEmptyList);
                        if (!arrayList.equals(cVar.b)) {
                            cVar.b = arrayList;
                            cVar.h(true);
                            break;
                        }
                        break;
                    case 8:
                        PlaybackFragment playbackFragment5 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i62 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment5);
                        boolean z = bool != null && bool.booleanValue();
                        playbackFragment5.e(z, z && playbackFragment5.E.c().g, false);
                        break;
                    case 9:
                        PlaybackFragment playbackFragment6 = this.b;
                        u8 u8Var = (u8) obj;
                        if (playbackFragment6.y) {
                            PlaybackFragment.c cVar2 = playbackFragment6.h;
                            String string = ((String) u8Var.b()).toString();
                            if (!cVar2.c.equals(string)) {
                                cVar2.c = string;
                                cVar2.f();
                            }
                            PlaybackFragment.c cVar3 = playbackFragment6.h;
                            String string2 = ((String) u8Var.c()).toString();
                            if (!cVar3.d.equals(string2)) {
                                cVar3.d = string2;
                                cVar3.f();
                            }
                            PlaybackFragment.c cVar4 = playbackFragment6.h;
                            boolean zD = u8Var.d();
                            if (cVar4.f != zD) {
                                cVar4.f = zD;
                                cVar4.f();
                            }
                        }
                        break;
                    default:
                        PlaybackFragment playbackFragment7 = this.b;
                        Boolean bool2 = (Boolean) obj;
                        int i72 = PlaybackFragment.J;
                        Objects.requireNonNull(playbackFragment7);
                        if (bool2 != null) {
                            if ((bool2.booleanValue() && !playbackFragment7.x) || (!bool2.booleanValue() && playbackFragment7.x)) {
                                playbackFragment7.g(true);
                                break;
                            }
                        }
                        break;
                }
            }
        });
        LifeCycleObserverUxrContentLimiter lifeCycleObserverUxrContentLimiter = new LifeCycleObserverUxrContentLimiter(new jc(getContext(), com.android.car.media.main.R$xml.uxr_config));
        this.a = lifeCycleObserverUxrContentLimiter;
        lifeCycleObserverUxrContentLimiter.a(this.h);
        getLifecycle().addObserver(this.a);
    }
}
