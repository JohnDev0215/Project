package com.vivid.spotify.rootui.category;

import android.app.Application;
import android.support.v4.media.MediaBrowserCompat;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.t;
import com.vivid.spotify.common.base.BaseViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CategoryViewModel extends BaseViewModel {
    private static final String TAG = "com.vivid.spotify.rootui.category.CategoryViewModel";
    private final t<Boolean> errorMsg;
    private final t<List<MediaBrowserCompat.MediaItem>> mCategory;
    f4.m mManager;
    private MediaBrowserCompat.MediaItem mParentItem;
    public float progress;
    public final ObservableBoolean scrollable;

    class a implements q4.g<List<MediaBrowserCompat.MediaItem>> {
        a() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b(CategoryViewModel.TAG).e(th, "getMediaList onError ", new Object[0]);
            CategoryViewModel.this.errorMsg.j(Boolean.TRUE);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            h3.i.b(CategoryViewModel.TAG).g("getMediaList onSuccess " + list.size());
            if (list.isEmpty()) {
                CategoryViewModel.this.errorMsg.j(Boolean.TRUE);
            } else {
                CategoryViewModel.this.errorMsg.j(Boolean.FALSE);
                CategoryViewModel.this.mCategory.j(list);
            }
        }
    }

    public CategoryViewModel(Application application, f4.m mVar) {
        super(application);
        this.mCategory = new t<>();
        this.errorMsg = new t<>(Boolean.FALSE);
        this.scrollable = new ObservableBoolean(false);
        this.mManager = mVar;
    }

    public void load() {
        if (this.mParentItem == null || this.mManager == null) {
            return;
        }
        this.errorMsg.j(Boolean.FALSE);
        this.rxExecutor.c(this.mManager.s(this.mParentItem), new a());
    }

    public LiveData<List<MediaBrowserCompat.MediaItem>> observerCategory() {
        return this.mCategory;
    }

    public LiveData<Boolean> observerError() {
        return this.errorMsg;
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, v3.a
    public void onLanguageChange(String str) {
        super.onLanguageChange(str);
        load();
    }

    public boolean setParentItem(MediaBrowserCompat.MediaItem mediaItem) {
        this.errorMsg.m(Boolean.FALSE);
        if (mediaItem.equals(this.mParentItem)) {
            return false;
        }
        this.mParentItem = mediaItem;
        return true;
    }
}
