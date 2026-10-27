package com.vivid.spotify.search;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.rootui.w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchViewModel extends BaseViewModel {
    public static final int STATUS_LOAD_DATA_FAILED = 3;
    public static final int STATUS_LOAD_DATA_IDLE = 0;
    public static final int STATUS_LOAD_DATA_LOADING = 1;
    public static final int STATUS_LOAD_DATA_SUCCESS = 2;
    private final String TAG;
    public final androidx.databinding.l<String> input;
    private String latestInputText;
    public final ObservableInt loadDataState;
    public final ObservableBoolean loadHistoryError;
    private final f4.m mMediaManager;
    private final c4.g mSearchKeywordsManager;
    public final androidx.lifecycle.t<List<w>> pageItems;
    public final androidx.databinding.k<String> searchKeywords;
    public final androidx.lifecycle.t<Float> topAlpha;

    class a implements q4.g<List<c4.a>> {
        a() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            SearchViewModel.this.loadHistoryError.r(true);
            h3.i.b("SearchViewModel").e(th, "a failed:", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<c4.a> list) {
            h3.i.b("SearchViewModel").g("a success : " + list.size());
            SearchViewModel.this.searchKeywords.clear();
            Iterator<c4.a> it = list.iterator();
            while (it.hasNext()) {
                SearchViewModel.this.searchKeywords.add(it.next().f3602b);
            }
            SearchViewModel.this.loadHistoryError.r(list.size() == 0);
        }
    }

    class b implements q4.g<Boolean> {
        b() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("SearchViewModel").e(th, "b failed:", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Boolean bool) {
            h3.i.b("SearchViewModel").b("b result : " + bool, new Object[0]);
            SearchViewModel.this.getSearchKeywords();
        }
    }

    class c implements q4.g<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f5536e;

        c(String str) {
            this.f5536e = str;
        }

        @Override // q4.g
        public void a(Throwable th) {
            if (this.f5536e.equals(SearchViewModel.this.input.q())) {
                SearchViewModel.this.loadDataState.r(3);
                h3.i.b("SearchViewModel").e(th, "c failed:", new Object[0]);
            } else {
                h3.l lVarB = h3.i.b("SearchViewModel");
                StringBuilder sb = new StringBuilder();
                sb.append("ignore onError  keyword = ");
                String str = this.f5536e;
                sb.append(str == null ? "0" : Integer.valueOf(str.length()));
                lVarB.e(th, sb.toString(), new Object[0]);
            }
            SearchViewModel.this.latestInputText = null;
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            if (this.f5536e.equals(SearchViewModel.this.input.q())) {
                SearchViewModel.this.handleQueryResult(list);
                SearchViewModel.this.loadDataState.r(list.size() == 0 ? 3 : 2);
                return;
            }
            h3.l lVarB = h3.i.b("SearchViewModel");
            StringBuilder sb = new StringBuilder();
            sb.append("ignore result  keyword = ");
            String str = this.f5536e;
            sb.append(str == null ? "0" : Integer.valueOf(str.length()));
            lVarB.g(sb.toString());
        }
    }

    class d implements q4.g<Long> {
        d() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("SearchViewModel").e(th, "insert failed:", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Long l7) {
            h3.i.b("SearchViewModel").b("insert success : " + l7, new Object[0]);
        }
    }

    public SearchViewModel(Application application, f4.m mVar, c4.g gVar) {
        super(application);
        this.TAG = "SearchViewModel";
        this.input = new androidx.databinding.l<>("");
        this.loadHistoryError = new ObservableBoolean(false);
        this.pageItems = new androidx.lifecycle.t<>();
        this.searchKeywords = new androidx.databinding.k<>();
        this.loadDataState = new ObservableInt(0);
        this.topAlpha = new androidx.lifecycle.t<>(Float.valueOf(0.0f));
        this.latestInputText = "";
        this.mMediaManager = mVar;
        this.mSearchKeywordsManager = gVar;
        getSearchKeywords();
    }

    private w getMediaItemsByTitle(String str, List<w> list) {
        if (!TextUtils.isEmpty(str) && list != null && !list.isEmpty()) {
            for (w wVar : list) {
                if (str.equals(wVar.f5512a)) {
                    return wVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSearchKeywords() {
        this.rxExecutor.c(this.mSearchKeywordsManager.j(), new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQueryResult(List<MediaBrowserCompat.MediaItem> list) {
        h3.i.b("SearchViewModel").g("media list size is:" + list.size());
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            w wVar = null;
            for (MediaBrowserCompat.MediaItem mediaItem : list) {
                Bundle bundleC = mediaItem.h().c();
                if (bundleC != null) {
                    String string = bundleC.getString("android.media.browse.CONTENT_STYLE_GROUP_TITLE_HINT");
                    if (TextUtils.isEmpty(string)) {
                        if (wVar == null) {
                            wVar = new w();
                        }
                        wVar.f5514c.add(mediaItem);
                    } else {
                        w mediaItemsByTitle = getMediaItemsByTitle(string, arrayList);
                        if (mediaItemsByTitle == null) {
                            w wVar2 = new w();
                            wVar2.f5512a = string;
                            wVar2.f5514c.add(mediaItem);
                            arrayList.add(wVar2);
                        } else {
                            mediaItemsByTitle.f5514c.add(mediaItem);
                        }
                    }
                }
            }
            if (wVar != null) {
                arrayList.add(0, wVar);
            }
        }
        this.pageItems.j(arrayList);
    }

    public void clearInput() {
        this.input.r("");
        this.latestInputText = null;
    }

    public void clearSearchKeywords() {
        this.rxExecutor.c(this.mSearchKeywordsManager.d(), new b());
    }

    public boolean isEmpty() {
        List<w> listE = this.pageItems.e();
        if (listE != null && !listE.isEmpty()) {
            Iterator<w> it = listE.iterator();
            while (it.hasNext()) {
                List<MediaBrowserCompat.MediaItem> list = it.next().f5514c;
                if (list != null && !list.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetData() {
        getSearchKeywords();
        this.loadDataState.r(0);
        this.pageItems.j(new ArrayList());
        this.input.r("");
        this.latestInputText = null;
    }

    public void saveSearchKeyword() {
        String strQ = this.input.q();
        if (TextUtils.isEmpty(strQ) || TextUtils.isEmpty(strQ.trim())) {
            return;
        }
        this.rxExecutor.c(this.mSearchKeywordsManager.e(strQ), new d());
    }

    public void search() {
        String strQ = this.input.q();
        if (TextUtils.isEmpty(strQ) || TextUtils.isEmpty(strQ.trim()) || TextUtils.equals(this.latestInputText, strQ)) {
            return;
        }
        this.latestInputText = strQ;
        this.loadDataState.r(1);
        this.rxExecutor.c(this.mMediaManager.u(strQ), new c(strQ));
    }

    public boolean searchIfNeeded() {
        if (3 != this.loadDataState.q()) {
            return false;
        }
        search();
        return true;
    }
}
