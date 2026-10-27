package com.vivid.spotify.rootui;

import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import androidx.databinding.ObservableBoolean;
import com.vivid.spotify.common.base.BaseViewModel;
import com.vivid.spotify.z;
import i3.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainUIViewModel extends BaseViewModel {
    private static final String TAG = "MainUIViewModel";
    public final ObservableBoolean hasData;
    public final androidx.lifecycle.t<Boolean> isInSafeMode;
    public final ObservableBoolean isNowPlayingBarShow;
    public final ObservableBoolean isRightDriver;
    private final z.a listener;
    public final androidx.databinding.l<String> loginButton;
    public final androidx.databinding.l<String> loginText;
    private final f4.m mMediaManager;
    private final c4.g mSearchKeywordsManager;
    private final y3.b mToastUtil;
    private final i3.b mediaErrorHandler;
    public final ObservableBoolean needLogin;
    public final ObservableBoolean needShowSplash;
    private final b.a onMediaErrorListener;
    public final androidx.lifecycle.t<List<x>> rootMenus;
    private final z safeModeManager;
    public final androidx.lifecycle.t<Integer> selectedIndex;

    class a implements b.a {
        a() {
        }

        @Override // i3.b.a
        public void a(String str) {
            if (str == null || str.length() <= 0) {
                return;
            }
            MainUIViewModel.this.mToastUtil.c(str);
        }

        @Override // i3.b.a
        public void b(String str, Bundle bundle) {
            h3.i.b(MainUIViewModel.TAG).b("f", new Object[0]);
            MainUIViewModel.this.handleSignOut(str, bundle);
        }
    }

    class b implements q4.g<List<MediaBrowserCompat.MediaItem>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f5436e;

        b(boolean z6) {
            this.f5436e = z6;
        }

        @Override // q4.g
        public void a(Throwable th) {
            if (th instanceof k3.a) {
                h3.i.b(MainUIViewModel.TAG).b("e", new Object[0]);
                MainUIViewModel.this.handleSignOut(th.getMessage(), ((k3.a) th).a());
            } else {
                h3.i.b(MainUIViewModel.TAG).e(th, th.toString(), new Object[0]);
            }
            MainUIViewModel.this.needShowSplash.r(false);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(List<MediaBrowserCompat.MediaItem> list) {
            MainUIViewModel.this.needLogin.r(false);
            MainUIViewModel.this.hasData.r(true);
            if (this.f5436e || MainUIViewModel.this.rootMenus.e() == null || MainUIViewModel.this.rootMenus.e().isEmpty()) {
                MainUIViewModel.this.setMediaItems(list);
            }
            MainUIViewModel.this.needShowSplash.r(false);
            h3.i.b(MainUIViewModel.TAG).g("getRootMenu onNext " + list.size());
        }
    }

    class c implements q4.g<Boolean> {
        c() {
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b(MainUIViewModel.TAG).e(th, "a", new Object[0]);
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(Boolean bool) {
            h3.i.b(MainUIViewModel.TAG).g("a  onSuccess  " + bool);
        }
    }

    public MainUIViewModel(Application application, i3.b bVar, f4.m mVar, c4.g gVar, z zVar, y3.b bVar2) {
        super(application);
        this.rootMenus = new androidx.lifecycle.t<>(Collections.emptyList());
        this.selectedIndex = new androidx.lifecycle.t<>(0);
        this.isNowPlayingBarShow = new ObservableBoolean(true);
        this.hasData = new ObservableBoolean(false);
        ObservableBoolean observableBoolean = new ObservableBoolean(false);
        this.needShowSplash = observableBoolean;
        this.needLogin = new ObservableBoolean();
        this.isInSafeMode = new androidx.lifecycle.t<>(Boolean.FALSE);
        this.loginText = new androidx.databinding.l<>();
        this.loginButton = new androidx.databinding.l<>();
        ObservableBoolean observableBoolean2 = new ObservableBoolean();
        this.isRightDriver = observableBoolean2;
        z.a aVar = new z.a() { // from class: com.vivid.spotify.rootui.t
            @Override // com.vivid.spotify.z.a
            public final void a(boolean z6) {
                this.f5511a.lambda$new$0(z6);
            }
        };
        this.listener = aVar;
        a aVar2 = new a();
        this.onMediaErrorListener = aVar2;
        this.mToastUtil = bVar2;
        this.mMediaManager = mVar;
        this.mSearchKeywordsManager = gVar;
        observableBoolean.r(true);
        this.safeModeManager = zVar;
        zVar.d(aVar);
        this.mediaErrorHandler = bVar;
        bVar.o(aVar2);
        h3.i.b(TAG).g("MainViewModel is created");
        if (application instanceof com.vivid.spotify.common.base.e) {
            observableBoolean2.r(((com.vivid.spotify.common.base.e) application).k());
        }
    }

    private void clearSearchHistory() {
        this.rxExecutor.c(this.mSearchKeywordsManager.d(), new c());
    }

    private x getSearchItem() {
        return new x(new MediaBrowserCompat.MediaItem(new MediaDescriptionCompat.b().f("vivid:search").i(getApplication().getString(com.vivid.spotify.x.search)).a(), 1), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSignOut(String str, Bundle bundle) {
        if (!this.needLogin.q()) {
            clearSearchHistory();
            this.needLogin.r(true);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.rootMenus.m(new ArrayList());
                this.selectedIndex.m(0);
            } else {
                this.rootMenus.j(new ArrayList());
                this.selectedIndex.j(0);
            }
        }
        this.loginText.r(str);
        this.loginButton.r(bundle.getString("android.media.extras.ERROR_RESOLUTION_ACTION_LABEL"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(boolean z6) {
        h3.i.b(TAG).b("isInSafeMode:%s", Boolean.valueOf(z6));
        this.isInSafeMode.j(Boolean.valueOf(z6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaItems(List<MediaBrowserCompat.MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaBrowserCompat.MediaItem> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new x(it.next()));
        }
        arrayList.add(getSearchItem());
        this.rootMenus.j(arrayList);
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, androidx.lifecycle.a0
    protected void onCleared() {
        super.onCleared();
        this.safeModeManager.e(this.listener);
        this.mediaErrorHandler.p(this.onMediaErrorListener);
    }

    @Override // com.vivid.spotify.common.base.BaseViewModel, v3.a
    public void onLanguageChange(String str) {
        super.onLanguageChange(str);
        refreshRoot(true);
    }

    public void refreshRoot(boolean z6) {
        this.rxExecutor.c(this.mMediaManager.g(), new b(z6));
    }
}
