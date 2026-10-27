package com.vivid.spotify.rootui.category;

import android.app.Application;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.i;
import androidx.lifecycle.t;
import androidx.lifecycle.v;
import com.vivid.spotify.common.base.BaseViewModel;
import i3.m;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class GridViewModel extends BaseViewModel implements androidx.lifecycle.n {
    private static final String TAG = "com.vivid.spotify.rootui.category.GridViewModel";
    private final m.f listener;
    private final t<Boolean> mErrorData;
    private final t<List<MediaBrowserCompat.MediaItem>> mList;
    private MediaBrowserCompat.MediaItem mMedia;
    private final f4.c mMediaCollectionManager;
    private final f4.m mMediaManager;
    private final i3.m mediaPlayController;

    class a implements m.f {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
            GridViewModel.this.refresh();
        }

        @Override // i3.m.f
        public void a(String str) {
            if (GridViewModel.this.mMedia == null || TextUtils.isEmpty(str) || !f4.d.o(GridViewModel.this.mMedia)) {
                return;
            }
            s4.a.a().b(new Runnable() { // from class: com.vivid.spotify.rootui.category.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5479e.c();
                }
            });
        }
    }

    class b implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void g() {
            GridViewModel.this.mErrorData.m(Boolean.TRUE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void h() {
            GridViewModel.this.mErrorData.m(Boolean.TRUE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void i(List list) {
            GridViewModel.this.mList.m(list);
            GridViewModel.this.mErrorData.m(Boolean.FALSE);
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b(GridViewModel.TAG).e(th, "getMediaList onError ", new Object[0]);
            s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.rootui.category.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5481e.g();
                }
            }, 1000L, TimeUnit.MILLISECONDS);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public void b(final List<MediaBrowserCompat.MediaItem> list) {
            h3.i.b(GridViewModel.TAG).g("getMediaList onSuccess " + list.size());
            if (!list.isEmpty()) {
                s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.rootui.category.o
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5482e.i(list);
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
            } else if (GridViewModel.this.mList.e() == 0 || ((List) GridViewModel.this.mList.e()).isEmpty()) {
                s4.a.a().c(new Runnable() { // from class: com.vivid.spotify.rootui.category.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5480e.h();
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
            }
        }
    }

    public GridViewModel(Application application, f4.m mVar, i3.m mVar2, f4.c cVar) {
        super(application);
        this.listener = new a();
        this.mList = new t<>();
        this.mErrorData = new t<>(Boolean.FALSE);
        this.mMediaManager = mVar;
        this.mediaPlayController = mVar2;
        this.mMediaCollectionManager = cVar;
    }

    public LiveData<Boolean> observerError() {
        return this.mErrorData;
    }

    public LiveData<List<MediaBrowserCompat.MediaItem>> observerList() {
        return this.mList;
    }

    @v(i.b.ON_PAUSE)
    public void onPause() {
        this.mMediaCollectionManager.d(this.listener);
    }

    @v(i.b.ON_RESUME)
    public void onResume() {
        this.mMediaCollectionManager.b(this.listener);
    }

    public void refresh() {
        if (this.mMedia == null) {
            return;
        }
        this.mErrorData.m(Boolean.FALSE);
        this.rxExecutor.c(this.mMediaManager.s(this.mMedia), new b());
    }

    public boolean setMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.equals(this.mMedia)) {
            return false;
        }
        this.mErrorData.m(Boolean.FALSE);
        this.mMedia = mediaItem;
        return true;
    }
}
