package com.vivid.spotify.details;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.BidiFormatter;
import android.text.TextUtils;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.rootui.w;
import com.vivid.spotify.x;
import i3.m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DetailViewModel extends BaseViewModel {
    private final String TAG;
    public final androidx.databinding.l<Uri> coverUri;
    public final ObservableBoolean favoriteChecked;
    public final ObservableBoolean isPlayable;
    public final ObservableBoolean isPlaying;
    public final ObservableBoolean isPodCast;
    public final ObservableBoolean isShimmerShow;
    public final ObservableBoolean isTabLayoutShow;
    public final androidx.lifecycle.t<Boolean> loadError;
    private final Map<String, androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>>> mDataMap;
    private final f4.c mMediaCollectionManager;
    private MediaBrowserCompat.MediaItem mMediaItem;
    private final f4.m mMediaManager;
    private final i3.m mMediaPlayController;
    private final m.f mOnCollectStateChangeListener;
    private MediaBrowserCompat.MediaItem mParentMediaItem;
    private final androidx.lifecycle.t<MediaBrowserCompat.MediaItem> mParentMediaItemLiveData;
    private final y3.b mToastUtil;
    public final androidx.lifecycle.t<List<w>> pageItems;
    public final ObservableInt placeHolder;
    public float progress;
    public final ObservableBoolean showFavoriteBtn;
    public final androidx.databinding.l<String> subtitle;
    public final androidx.databinding.l<String> title;

    class a implements m.f {
        a() {
        }

        @Override // i3.m.f
        public void a(String str) {
            if (DetailViewModel.this.mParentMediaItem == null || !f4.d.p(DetailViewModel.this.mMediaItem) || TextUtils.isEmpty(str)) {
                return;
            }
            DetailViewModel detailViewModel = DetailViewModel.this;
            detailViewModel.refreshLikedSongsBaseInfo(detailViewModel.mParentMediaItem);
        }
    }

    class b implements q4.g<Boolean> {
        b() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("DetailViewModel").e(th, "getFavoriteStatus", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Boolean bool) {
            DetailViewModel.this.favoriteChecked.r(bool.booleanValue());
            DetailViewModel.this.showFavoriteBtn.r(true);
        }
    }

    class c implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e() {
            DetailViewModel.this.loadError.m(Boolean.TRUE);
            DetailViewModel.this.isShimmerShow.r(false);
        }

        @Override // q4.g
        public void a(Throwable th) {
            s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.details.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5299e.e();
                }
            }, 1000L, TimeUnit.MILLISECONDS);
            h3.i.b("DetailViewModel").d("loadData failed:" + th.getMessage(), new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            DetailViewModel detailViewModel = DetailViewModel.this;
            detailViewModel.isPlayable.r(detailViewModel.mMediaItem.m() && !list.isEmpty());
            DetailViewModel.this.handleQueryResult(list);
        }
    }

    class d implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e() {
            DetailViewModel.this.loadError.m(Boolean.TRUE);
            DetailViewModel.this.isShimmerShow.r(false);
        }

        @Override // q4.g
        public void a(Throwable th) {
            s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.details.p
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5300e.e();
                }
            }, 1000L, TimeUnit.MILLISECONDS);
            h3.i.b("DetailViewModel").d("loadData failed:" + th.getMessage(), new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            DetailViewModel detailViewModel = DetailViewModel.this;
            detailViewModel.isPlayable.r(detailViewModel.mMediaItem.m() && !list.isEmpty());
            DetailViewModel.this.handleQueryResult(list);
            DetailViewModel.this.subtitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(DetailViewModel.this.mMediaItem)));
        }
    }

    class e implements q4.g<Boolean> {
        e() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("DetailViewModel").e(th, "a failed", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Boolean bool) {
            DetailViewModel.this.favoriteChecked.r(false);
            DetailViewModel.this.mToastUtil.c(DetailViewModel.this.getApplication().getString(x.removed_from_your_library));
        }
    }

    class f implements q4.g<Boolean> {
        f() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("DetailViewModel").e(th, "b failed", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Boolean bool) {
            DetailViewModel.this.favoriteChecked.r(true);
            DetailViewModel.this.mToastUtil.c(DetailViewModel.this.getApplication().getString(x.added_to_your_library));
        }
    }

    class g implements q4.g<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MediaBrowserCompat.MediaItem f5272e;

        g(MediaBrowserCompat.MediaItem mediaItem) {
            this.f5272e = mediaItem;
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("DetailViewModel").e(th, "refreshLikedSongsBaseInfo onError", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            if (list.isEmpty()) {
                return;
            }
            for (MediaBrowserCompat.MediaItem mediaItem : list) {
                if (f4.d.p(mediaItem)) {
                    DetailViewModel.this.updateMediaItem(mediaItem);
                    DetailViewModel.this.loadData();
                    return;
                }
            }
            DetailViewModel.this.updateMediaItem(f4.d.l(this.f5272e.j(), DetailViewModel.this.getApplication()));
            DetailViewModel.this.subtitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(DetailViewModel.this.mMediaItem)));
            DetailViewModel.this.loadError.j(Boolean.TRUE);
            DetailViewModel.this.isShimmerShow.r(false);
            Iterator it = DetailViewModel.this.mDataMap.keySet().iterator();
            while (it.hasNext()) {
                ((androidx.lifecycle.t) DetailViewModel.this.mDataMap.get((String) it.next())).j(Collections.emptyList());
            }
        }
    }

    public DetailViewModel(Application application, f4.m mVar, i3.m mVar2, y3.b bVar, f4.c cVar) {
        super(application);
        this.TAG = "DetailViewModel";
        this.title = new androidx.databinding.l<>();
        this.subtitle = new androidx.databinding.l<>();
        this.coverUri = new androidx.databinding.l<>();
        this.isPlayable = new ObservableBoolean();
        this.isPlaying = new ObservableBoolean();
        this.showFavoriteBtn = new ObservableBoolean();
        this.isPodCast = new ObservableBoolean(false);
        this.favoriteChecked = new ObservableBoolean(false);
        this.loadError = new androidx.lifecycle.t<>(Boolean.FALSE);
        this.placeHolder = new ObservableInt();
        this.pageItems = new androidx.lifecycle.t<>();
        this.isTabLayoutShow = new ObservableBoolean();
        this.isShimmerShow = new ObservableBoolean();
        this.mDataMap = new HashMap();
        this.mParentMediaItemLiveData = new androidx.lifecycle.t<>();
        this.mOnCollectStateChangeListener = new a();
        this.mMediaManager = mVar;
        this.mToastUtil = bVar;
        this.mMediaPlayController = mVar2;
        this.mMediaCollectionManager = cVar;
    }

    private void getFavoriteStatus() {
        this.rxExecutor.c(this.mMediaManager.G(this.mMediaItem), new b());
    }

    private w getMediaItemsByTitle(String str, List<w> list) {
        if (str != null && list != null && !list.isEmpty()) {
            for (w wVar : list) {
                if (str.equals(wVar.f5512a)) {
                    return wVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQueryResult(List<MediaBrowserCompat.MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        if (list.isEmpty()) {
            Iterator<String> it = this.mDataMap.keySet().iterator();
            while (it.hasNext()) {
                this.mDataMap.get(it.next()).j(Collections.emptyList());
            }
            s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.details.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5298e.lambda$handleQueryResult$0();
                }
            }, 1000L, TimeUnit.MILLISECONDS);
        } else {
            for (MediaBrowserCompat.MediaItem mediaItem : list) {
                Bundle bundleC = mediaItem.h().c();
                if (bundleC != null) {
                    String string = bundleC.getString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT", "");
                    w mediaItemsByTitle = getMediaItemsByTitle(string, arrayList);
                    if (mediaItemsByTitle == null) {
                        mediaItemsByTitle = new w();
                        mediaItemsByTitle.f5512a = string;
                        arrayList.add(mediaItemsByTitle);
                    }
                    mediaItemsByTitle.f5514c.add(mediaItem);
                }
            }
        }
        this.pageItems.j(arrayList);
        for (w wVar : arrayList) {
            androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar = this.mDataMap.get(wVar.f5512a);
            if (tVar == null) {
                tVar = new androidx.lifecycle.t<>();
                this.mDataMap.put(wVar.f5512a, tVar);
            }
            tVar.j(wVar.f5514c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleQueryResult$0() {
        this.isShimmerShow.r(false);
        this.loadError.j(Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLikedSongsBaseInfo(MediaBrowserCompat.MediaItem mediaItem) {
        this.rxExecutor.c(this.mMediaManager.s(mediaItem), new g(mediaItem));
    }

    public MediaBrowserCompat.MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public void loadData() {
        this.loadError.j(Boolean.FALSE);
        this.rxExecutor.c(this.mMediaManager.s(this.mMediaItem), new d());
    }

    public LiveData<List<MediaBrowserCompat.MediaItem>> observeListData(String str) {
        androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar = this.mDataMap.get(str);
        if (tVar != null) {
            return tVar;
        }
        androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar2 = new androidx.lifecycle.t<>();
        this.mDataMap.put(str, tVar2);
        return tVar2;
    }

    public LiveData<MediaBrowserCompat.MediaItem> observeParentMediaItem() {
        return this.mParentMediaItemLiveData;
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, androidx.lifecycle.a0
    protected void onCleared() {
        super.onCleared();
        if (f4.d.p(this.mMediaItem)) {
            this.mMediaCollectionManager.d(this.mOnCollectStateChangeListener);
        }
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, v3.a
    public void onLanguageChange(String str) {
        super.onLanguageChange(str);
        MediaBrowserCompat.MediaItem mediaItem = this.mParentMediaItem;
        if (mediaItem != null) {
            refreshLikedSongsBaseInfo(mediaItem);
        } else {
            loadData();
        }
    }

    public void onPlayAllButtonClicked() {
        if (this.isPlaying.q()) {
            this.mMediaPlayController.H();
        } else if (TextUtils.equals(com.vivid.spotify.nowplaying.b.l(this.mMediaPlayController.n()), i3.c.d(this.mMediaItem))) {
            this.mMediaPlayController.I();
        } else {
            this.mMediaManager.r(this.mMediaItem);
        }
    }

    public void reloadData() {
        this.loadError.j(Boolean.FALSE);
        this.isShimmerShow.r(true);
        this.rxExecutor.c(this.mMediaManager.s(this.mMediaItem), new c());
    }

    public boolean setMediaItem(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2) {
        this.mParentMediaItem = mediaItem;
        this.mParentMediaItemLiveData.j(mediaItem);
        MediaBrowserCompat.MediaItem mediaItem3 = this.mMediaItem;
        if (mediaItem3 != null && TextUtils.equals(mediaItem3.j(), mediaItem2.j())) {
            return false;
        }
        this.mMediaItem = mediaItem2;
        this.isPlayable.r(mediaItem2.m());
        this.isPodCast.r(f4.d.q(this.mMediaItem));
        this.coverUri.r(Uri.parse(i3.c.a(mediaItem2)));
        this.title.r(BidiFormatter.getInstance().unicodeWrap(i3.c.g(mediaItem2)));
        this.subtitle.r(BidiFormatter.getInstance().unicodeWrap(i3.c.f(mediaItem2)));
        this.placeHolder.r(f4.d.m(i3.c.d(mediaItem2)));
        loadData();
        getFavoriteStatus();
        if (!f4.d.p(this.mMediaItem)) {
            return true;
        }
        this.mMediaCollectionManager.b(this.mOnCollectStateChangeListener);
        return true;
    }

    public void toggleFavStatus() {
        if (this.favoriteChecked.q()) {
            this.rxExecutor.c(this.mMediaManager.Q(this.mMediaItem), new e());
        } else {
            this.rxExecutor.c(this.mMediaManager.F(this.mMediaItem), new f());
        }
    }

    public void updateMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        this.isPlayable.r(mediaItem.m());
        this.title.r(BidiFormatter.getInstance().unicodeWrap(i3.c.g(mediaItem)));
    }
}
