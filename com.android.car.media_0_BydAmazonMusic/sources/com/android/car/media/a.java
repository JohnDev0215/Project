package com.android.car.media;

import android.car.utils.Logs;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.media.session.MediaControllerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.car.apps.common.LoadingView;
import com.android.car.media.MediaActivity;
import com.android.car.media.browse.BrowseAdapter;
import com.android.car.media.browse.LimitedBrowseAdapter;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.d;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.uxr.LifeCycleObserverUxrContentLimiter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import spi.b0;
import spi.d4;
import spi.e0;
import spi.jc;
import spi.uc;

/* JADX INFO: compiled from: BrowseViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final c a;
    public final FocusArea b;
    public final MediaItemMetadata c;
    public final MediaItemsRepository.MediaItemsLiveData d;
    public final boolean e;
    public final LifeCycleObserverUxrContentLimiter f;
    public final View g;
    public final CarUiRecyclerView h;
    public final ImageView i;
    public final TextView j;
    public final LoadingView k;
    public final TabLayout l;
    public final LimitedBrowseAdapter m;
    public final int n;
    public final int o;
    public final boolean p;
    public boolean q = false;
    public String r = "";
    public final Handler s = new Handler();
    public final MediaActivity.ViewModel t;
    public final C0006a u;
    public int v;
    public final Observer<d4<List<MediaItemMetadata>>> w;
    public b x;

    /* JADX INFO: renamed from: com.android.car.media.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BrowseViewController.java */
    public class C0006a extends BrowseAdapter.c {
        public C0006a() {
        }

        @Override // com.android.car.media.browse.BrowseAdapter.c
        public final void a(@NonNull MediaItemMetadata mediaItemMetadata) {
            d.e eVar = (d.e) a.this.a;
            d.d(d.this);
            d dVar = d.this;
            dVar.p(false);
            dVar.m(false);
            dVar.h().push(mediaItemMetadata);
            dVar.m(true);
            dVar.q();
        }

        @Override // com.android.car.media.browse.BrowseAdapter.c
        public final void b(@NonNull MediaItemMetadata mediaItemMetadata) {
            d.e eVar = (d.e) a.this.a;
            d.d(d.this);
            MediaActivity mediaActivity = (MediaActivity) d.this.j;
            PlaybackViewModel.d dVar = mediaActivity.b;
            Objects.requireNonNull(dVar);
            StringBuilder sb = new StringBuilder();
            sb.append("playItem");
            sb.append(dVar.a == null ? ",mMediaController is null" : "");
            Logs.d("PlaybackViewModel", sb.toString());
            MediaControllerCompat mediaControllerCompat = dVar.a;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.getTransportControls().playFromMediaId(mediaItemMetadata.b(), null);
            }
            if (mediaActivity.getResources().getBoolean(com.android.car.media.main.R$bool.switch_to_playback_view_when_playable_item_is_clicked)) {
                mediaActivity.a(MediaActivity.d.PLAYBACK);
            }
            mediaActivity.setIntent(null);
        }
    }

    /* JADX INFO: compiled from: BrowseViewController.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            uc.e(a.this.j, false);
            a.this.k.setRotate(true);
            a aVar = a.this;
            uc.g(aVar.k, aVar.n, null);
        }
    }

    /* JADX INFO: compiled from: BrowseViewController.java */
    public interface c {
    }

    private a(c cVar, ViewGroup viewGroup, @Nullable MediaItemMetadata mediaItemMetadata, MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData, int i, int i2, boolean z) {
        C0006a c0006a = new C0006a();
        this.u = c0006a;
        b0 b0Var = new b0(this, 0);
        this.w = b0Var;
        this.x = new b();
        StringBuilder sb = new StringBuilder();
        sb.append("BrowseViewController mediaItems:");
        sb.append(mediaItemsLiveData);
        Logs.d("BrowseViewController", sb.toString() != null ? mediaItemsLiveData.toString() : "");
        this.a = cVar;
        this.c = mediaItemMetadata;
        this.d = mediaItemsLiveData;
        this.e = z;
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(com.android.car.media.main.R$layout.browse_node, viewGroup, false);
        this.g = viewInflate;
        viewInflate.setAlpha(0.0f);
        viewGroup.addView(viewInflate);
        this.o = viewInflate.getContext().getResources().getInteger(com.android.car.media.main.R$integer.progress_indicator_delay);
        this.p = viewInflate.getContext().getResources().getBoolean(com.android.car.media.main.R$bool.set_browse_list_focus_area_highlight_above_minimized_control_bar);
        this.b = (FocusArea) viewInflate.findViewById(com.android.car.media.main.R$id.focus_area);
        CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) viewInflate.findViewById(com.android.car.media.main.R$id.browse_list);
        this.h = carUiRecyclerView;
        this.i = (ImageView) viewInflate.findViewById(com.android.car.media.main.R$id.error_icon);
        this.j = (TextView) viewInflate.findViewById(com.android.car.media.main.R$id.error_message);
        this.k = (LoadingView) viewInflate.findViewById(com.android.car.media.main.R$id.loading_icon_view);
        this.n = viewInflate.getContext().getResources().getInteger(com.android.car.media.main.R$integer.new_album_art_fade_in_duration);
        FragmentActivity fragmentActivityA = ((d.e) cVar).a();
        this.t = (MediaActivity.ViewModel) ViewModelProviders.of(fragmentActivityA).get(MediaActivity.ViewModel.class);
        BrowseAdapter browseAdapter = new BrowseAdapter(carUiRecyclerView.getContext());
        LimitedBrowseAdapter limitedBrowseAdapter = new LimitedBrowseAdapter(carUiRecyclerView, browseAdapter, c0006a);
        this.m = limitedBrowseAdapter;
        carUiRecyclerView.setAdapter(limitedBrowseAdapter);
        carUiRecyclerView.setClipToPadding(true);
        LifeCycleObserverUxrContentLimiter lifeCycleObserverUxrContentLimiter = new LifeCycleObserverUxrContentLimiter(new jc(fragmentActivityA, com.android.car.media.main.R$xml.uxr_config));
        this.f = lifeCycleObserverUxrContentLimiter;
        lifeCycleObserverUxrContentLimiter.a(limitedBrowseAdapter);
        fragmentActivityA.getLifecycle().addObserver(lifeCycleObserverUxrContentLimiter);
        browseAdapter.d = browseAdapter.e(i);
        browseAdapter.e = browseAdapter.e(i2);
        mediaItemsLiveData.observe(fragmentActivityA, b0Var);
        TabLayout tabLayout = (TabLayout) viewInflate.findViewById(com.android.car.media.main.R$id.car_ui_toolbar_tabs_sub);
        this.l = tabLayout;
        tabLayout.setSelectedTabIndicator((Drawable) null);
    }

    public static a e(c cVar, ViewGroup viewGroup, @NonNull MediaItemMetadata mediaItemMetadata, MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("BrowseViewController newBrowseController:");
        sb.append(mediaItemsLiveData);
        Logs.d("BrowseViewController", sb.toString() == null ? "" : mediaItemsLiveData.toString());
        return new a(cVar, viewGroup, mediaItemMetadata, mediaItemsLiveData, i, i2, true);
    }

    public static a f(c cVar, ViewGroup viewGroup, MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData) {
        StringBuilder sb = new StringBuilder();
        sb.append("BrowseViewController newRootController:");
        sb.append(mediaItemsLiveData);
        Logs.d("BrowseViewController", sb.toString() == null ? "" : mediaItemsLiveData.toString());
        return new a(cVar, viewGroup, null, mediaItemsLiveData, 0, 0, false);
    }

    public static a g(c cVar, ViewGroup viewGroup, MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData) {
        StringBuilder sb = new StringBuilder();
        sb.append("BrowseViewController newSearchResultsController:");
        sb.append(mediaItemsLiveData);
        Logs.d("BrowseViewController", sb.toString() == null ? "" : mediaItemsLiveData.toString());
        return new a(cVar, viewGroup, null, mediaItemsLiveData, 0, 0, true);
    }

    public final int a(List<String> list, String str, boolean z) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder("calculateIndexByVoiceFilter: ");
        sb.append(" filter=");
        sb.append(str);
        if (z && str.equals("Playlists")) {
            if (list.contains("Top Results")) {
                str = "Top Results";
            } else if (list.contains("Songs")) {
                str = "Songs";
            }
            sb.append(" Playlists convert to ");
            sb.append(str);
        }
        sb.append(" index= ");
        sb.append(list.indexOf(str));
        sb.append(" groupTab=");
        sb.append(list);
        Logs.d("BrowseViewController", sb.toString());
        int iIndexOf = list.indexOf(str);
        if (iIndexOf < 0) {
            return 0;
        }
        return iIndexOf;
    }

    public final void b() {
        ViewGroup viewGroup;
        ((d.e) this.a).a().getLifecycle().removeObserver(this.f);
        this.d.removeObserver(this.w);
        View view = this.g;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeView(view);
        }
        this.r = "";
    }

    public final FragmentActivity c() {
        return ((d.e) this.a).a();
    }

    public final List<MediaItemMetadata> d(List<MediaItemMetadata> list, String str) {
        return (List) list.stream().filter(new e0(str, 0)).collect(Collectors.toList());
    }

    public final void h(@NonNull Insets insets) {
        int paddingLeft = this.h.getPaddingLeft();
        int paddingRight = this.h.getPaddingRight();
        int paddingBottom = Boolean.TRUE.equals(this.t.e.getValue()) ? this.h.getPaddingBottom() : 0;
        int top = insets.getTop();
        if (this.q) {
            top = 240;
        }
        this.h.setPadding(paddingLeft, top, paddingRight, paddingBottom);
        Logs.d("BrowseViewController", "onCarUiInsetsChanged mBrowseList.setPadding top insets.getTop():" + insets.getTop());
        if (paddingBottom > this.v) {
            this.v = paddingBottom;
        }
        this.b.b(paddingLeft, insets.getTop(), paddingRight, this.v);
        this.b.a(paddingLeft, insets.getTop(), paddingRight, paddingBottom);
    }

    public final void i() {
        int paddingLeft = this.h.getPaddingLeft();
        int paddingTop = this.h.getPaddingTop();
        int paddingRight = this.h.getPaddingRight();
        int dimensionPixelOffset = Boolean.TRUE.equals(this.t.e.getValue()) ? c().getResources().getDimensionPixelOffset(com.android.car.media.main.R$dimen.browse_fragment_bottom_padding) : 0;
        this.h.setPadding(paddingLeft, paddingTop, paddingRight, dimensionPixelOffset);
        Logs.d("BrowseViewController", "onPlaybackControlsChanged mBrowseList.setPadding topPadding:" + paddingTop);
        int i = this.p ? dimensionPixelOffset : 0;
        if (i > this.v) {
            this.v = i;
        }
        this.b.b(paddingLeft, paddingTop, paddingRight, this.v);
        this.b.a(paddingLeft, paddingTop, paddingRight, dimensionPixelOffset);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.j.getLayoutParams();
        marginLayoutParams.bottomMargin = dimensionPixelOffset;
        this.j.setLayoutParams(marginLayoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
        marginLayoutParams2.bottomMargin = dimensionPixelOffset;
        this.k.setLayoutParams(marginLayoutParams2);
    }

    public final void j(int i) {
        TabLayout tabLayout = this.l;
        if (tabLayout == null || !this.q) {
            return;
        }
        tabLayout.setVisibility(i);
        if (i != 0) {
            Logs.v("BrowseViewController", "BrowseViewController setTabLayoutVisible top 2: ");
            return;
        }
        int paddingLeft = this.h.getPaddingLeft();
        int paddingRight = this.h.getPaddingRight();
        int paddingBottom = this.h.getPaddingBottom();
        this.h.getPaddingTop();
        this.l.getHeight();
        if (!Boolean.TRUE.equals(this.t.e.getValue())) {
            paddingBottom = 0;
        }
        this.h.setPadding(paddingLeft, 240, paddingRight, paddingBottom);
        Logs.v("BrowseViewController", "BrowseViewController setTabLayoutVisible  mSubTabLayout.getHeight(): " + this.l.getHeight());
        Logs.v("BrowseViewController", "BrowseViewController setTabLayoutVisible top: 240");
    }

    public final void k() {
        this.s.removeCallbacks(this.x);
        uc.b(this.j, this.n, null);
        this.k.setRotate(false);
        uc.b(this.k, 0, null);
    }
}
