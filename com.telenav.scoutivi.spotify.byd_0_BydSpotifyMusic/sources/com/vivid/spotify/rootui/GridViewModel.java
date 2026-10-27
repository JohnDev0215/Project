package com.vivid.spotify.rootui;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.text.TextUtils;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.i;
import com.vivid.spotify.common.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class GridViewModel extends BaseViewModel implements androidx.lifecycle.n {
    private static final String TAG = "GridViewModel";
    private final MediaControllerCompat.a callback;
    public final androidx.lifecycle.t<Boolean> loadError;
    private MediaBrowserCompat.MediaItem mMediaItem;
    private final f4.m mMediaManager;
    private final i3.m mMediaPlayController;
    public final androidx.lifecycle.t<List<w>> mediaItems;
    public androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> mediaItemsNotifyLiveData;
    private boolean needRefreshData;
    public final androidx.lifecycle.u<List<MediaBrowserCompat.MediaItem>> parentDataChangeObserver;
    public final ObservableBoolean showBack;
    public final androidx.databinding.l<String> title;

    class a extends MediaControllerCompat.a {
        a() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            super.d(mediaMetadataCompat);
            if (GridViewModel.this.needRefreshData) {
                h3.i.b(GridViewModel.TAG).b("onMetadataChanged refresh data", new Object[0]);
                GridViewModel.this.getMediaList();
            }
        }
    }

    class b implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f() {
            GridViewModel.this.loadError.j(Boolean.TRUE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g() {
            GridViewModel.this.loadError.j(Boolean.TRUE);
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b(GridViewModel.TAG).e(th, "getMediaList onError ", new Object[0]);
            s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.rootui.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5493e.f();
                }
            }, 1000L, TimeUnit.MILLISECONDS);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            h3.i.b(GridViewModel.TAG).g("getMediaList onSuccess " + list.size());
            if (list.size() == 0) {
                s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.rootui.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5494e.g();
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
                return;
            }
            GridViewModel gridViewModel = GridViewModel.this;
            gridViewModel.mediaItems.j(gridViewModel.handleQueryResult(list));
            androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar = GridViewModel.this.mediaItemsNotifyLiveData;
            if (tVar != null) {
                tVar.j(list);
            }
        }
    }

    class c implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        c() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b(GridViewModel.TAG).e(th, "getMediaList onError ", new Object[0]);
            GridViewModel.this.loadError.j(Boolean.TRUE);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            h3.i.b(GridViewModel.TAG).g("getMediaList onSuccess " + list.size());
            if (list.size() == 0) {
                if (GridViewModel.this.mediaItems.e() == null || GridViewModel.this.mediaItems.e().isEmpty()) {
                    GridViewModel.this.loadError.j(Boolean.TRUE);
                    return;
                }
                return;
            }
            GridViewModel gridViewModel = GridViewModel.this;
            gridViewModel.mediaItems.j(gridViewModel.handleQueryResult(list));
            androidx.lifecycle.t<List<MediaBrowserCompat.MediaItem>> tVar = GridViewModel.this.mediaItemsNotifyLiveData;
            if (tVar != null) {
                tVar.j(list);
            }
        }
    }

    public GridViewModel(Application application, f4.m mVar, i3.m mVar2) {
        super(application);
        this.mediaItems = new androidx.lifecycle.t<>();
        this.loadError = new androidx.lifecycle.t<>(Boolean.FALSE);
        this.showBack = new ObservableBoolean(false);
        this.title = new androidx.databinding.l<>();
        this.parentDataChangeObserver = new androidx.lifecycle.u() { // from class: com.vivid.spotify.rootui.e
            @Override // androidx.lifecycle.u
            public final void c(Object obj) {
                this.f5492a.lambda$new$0((List) obj);
            }
        };
        this.needRefreshData = false;
        this.callback = new a();
        this.mMediaManager = mVar;
        this.mMediaPlayController = mVar2;
    }

    private w getMediaItemsByTitle(String str, List<w> list) {
        if (list != null && !list.isEmpty()) {
            for (w wVar : list) {
                if (str.equals(wVar.f5512a)) {
                    return wVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<w> handleQueryResult(List<MediaBrowserCompat.MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (MediaBrowserCompat.MediaItem mediaItem : list) {
            Bundle bundleC = mediaItem.h().c();
            if (bundleC != null) {
                String string = bundleC.getString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT", "");
                w mediaItemsByTitle = getMediaItemsByTitle(string, arrayList);
                if (mediaItemsByTitle == null) {
                    w wVar = new w();
                    wVar.f5512a = string;
                    wVar.f5514c.add(mediaItem);
                    arrayList.add(wVar);
                } else {
                    mediaItemsByTitle.f5514c.add(mediaItem);
                }
            }
        }
        if (arrayList.size() == 1 && TextUtils.isEmpty(arrayList.get(0).f5512a)) {
            arrayList.get(0).f5512a = i3.c.g(this.mMediaItem);
        }
        return arrayList;
    }

    public MediaBrowserCompat.MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public void getMediaList() {
        this.loadError.j(Boolean.FALSE);
        this.rxExecutor.c(this.mMediaManager.s(this.mMediaItem), new c());
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, v3.a
    public void onLanguageChange(String str) {
        super.onLanguageChange(str);
        getMediaList();
    }

    /* JADX INFO: renamed from: onParentListChanged, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0(List<MediaBrowserCompat.MediaItem> list) {
        h3.i.b(TAG).b("onParentListChanged", new Object[0]);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (MediaBrowserCompat.MediaItem mediaItem : list) {
            if (TextUtils.equals(mediaItem.j(), this.mMediaItem.j())) {
                this.title.r(i3.c.g(mediaItem));
                String strQ = this.title.q();
                h3.l lVarB = h3.i.b(TAG);
                StringBuilder sb = new StringBuilder();
                sb.append("title change ");
                sb.append(TextUtils.isEmpty(strQ) ? "" : strQ.substring(0, 1) + "*" + strQ.substring(strQ.length() - 1, strQ.length()));
                lVarB.g(sb.toString());
                return;
            }
        }
    }

    @androidx.lifecycle.v(i.b.ON_PAUSE)
    public void onPause() {
        this.mMediaPlayController.M(this.callback);
    }

    @androidx.lifecycle.v(i.b.ON_RESUME)
    public void onResume() {
        h3.i.b(TAG).b("onResume  needRefreshData = " + this.needRefreshData, new Object[0]);
        this.mMediaPlayController.h(this.callback);
    }

    public void onRootMenuChanged(List<x> list) {
        h3.i.b(TAG).b("onRootMenuChanged", new Object[0]);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (x xVar : list) {
            if (xVar.c() == 0 && TextUtils.equals(xVar.b().j(), this.mMediaItem.j())) {
                MediaBrowserCompat.MediaItem mediaItemB = xVar.b();
                this.mMediaItem = mediaItemB;
                this.title.r(i3.c.g(mediaItemB));
                List<w> listE = this.mediaItems.e();
                if (listE != null && listE.size() == 1) {
                    listE.get(0).f5512a = this.title.q();
                    this.mediaItems.j(listE);
                }
                String strQ = this.title.q();
                h3.l lVarB = h3.i.b(TAG);
                StringBuilder sb = new StringBuilder();
                sb.append("title change ");
                sb.append(TextUtils.isEmpty(strQ) ? "" : strQ.substring(0, 1) + "*" + strQ.substring(strQ.length() - 1, strQ.length()));
                lVarB.g(sb.toString());
                return;
            }
        }
    }

    public void reloadData() {
        this.loadError.m(Boolean.FALSE);
        this.rxExecutor.c(this.mMediaManager.s(this.mMediaItem), new b());
    }

    public boolean setMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        MediaBrowserCompat.MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 != null && TextUtils.equals(mediaItem2.j(), mediaItem.j())) {
            return false;
        }
        this.title.r(i3.c.g(mediaItem));
        this.mMediaItem = mediaItem;
        getMediaList();
        this.needRefreshData = TextUtils.equals("spotify:recently-played", mediaItem.j());
        return true;
    }
}
