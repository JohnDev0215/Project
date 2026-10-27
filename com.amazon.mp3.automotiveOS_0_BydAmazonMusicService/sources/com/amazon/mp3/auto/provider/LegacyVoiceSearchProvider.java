package com.amazon.mp3.auto.provider;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.find.dagger.FindModule;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.ExecutionContextDefault;
import com.amazon.music.find.service.SearchService;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyVoiceSearchProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\b\u0010\u000e\u001a\u00020\bH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchProvider;", "", "context", "Landroid/content/Context;", "autoMediaBrowser", "Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchBrowserImpl;", "(Landroid/content/Context;Lcom/amazon/mp3/auto/provider/LegacyVoiceSearchBrowserImpl;)V", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "searchService", "Lcom/amazon/music/find/service/SearchService;", "getAllSongsUri", "Landroid/net/Uri;", "allSongsUri", "getExecutionContext", "searchLibrary", "", SearchIntents.EXTRA_QUERY, "", "searchPrime", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LegacyVoiceSearchProvider {
    private static final String TAG = LegacyVoiceSearchProvider.class.getSimpleName();
    private final LegacyVoiceSearchBrowserImpl autoMediaBrowser;
    private final Context context;
    private final ExecutionContext executionContext;
    private final SearchService searchService;

    public LegacyVoiceSearchProvider(Context context, LegacyVoiceSearchBrowserImpl autoMediaBrowser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(autoMediaBrowser, "autoMediaBrowser");
        this.context = context;
        this.autoMediaBrowser = autoMediaBrowser;
        this.executionContext = getExecutionContext();
        this.searchService = FindModule.INSTANCE.initializeSearchService(context);
    }

    public final void searchLibrary(String query) {
        if (query != null) {
            this.searchService.library(this.executionContext, query, SearchLibrarySource.CLOUD).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.auto.provider.LegacyVoiceSearchProvider$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    LegacyVoiceSearchProvider.m357searchLibrary$lambda0(this.f$0, (SearchResponse) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.auto.provider.LegacyVoiceSearchProvider$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    LegacyVoiceSearchProvider.m358searchLibrary$lambda1((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchLibrary$lambda-0, reason: not valid java name */
    public static final void m357searchLibrary$lambda0(LegacyVoiceSearchProvider this$0, SearchResponse searchResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LegacyVoiceSearchBrowserImpl legacyVoiceSearchBrowserImpl = this$0.autoMediaBrowser;
        Intrinsics.checkNotNullExpressionValue(searchResponse, "searchResponse");
        legacyVoiceSearchBrowserImpl.onLibrarySearchResponseLoaded(searchResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchLibrary$lambda-1, reason: not valid java name */
    public static final void m358searchLibrary$lambda1(Throwable th) {
        Log.error(TAG, "Exception on getting search response ", th);
    }

    public final void searchPrime(String query) {
        if (query != null) {
            this.searchService.catalog(this.executionContext, query).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.auto.provider.LegacyVoiceSearchProvider$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    LegacyVoiceSearchProvider.m359searchPrime$lambda2(this.f$0, (SearchResponse) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.auto.provider.LegacyVoiceSearchProvider$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    LegacyVoiceSearchProvider.m360searchPrime$lambda3((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchPrime$lambda-2, reason: not valid java name */
    public static final void m359searchPrime$lambda2(LegacyVoiceSearchProvider this$0, SearchResponse searchResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LegacyVoiceSearchBrowserImpl legacyVoiceSearchBrowserImpl = this$0.autoMediaBrowser;
        Intrinsics.checkNotNullExpressionValue(searchResponse, "searchResponse");
        legacyVoiceSearchBrowserImpl.onPrimeSearchResponseLoaded(searchResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchPrime$lambda-3, reason: not valid java name */
    public static final void m360searchPrime$lambda3(Throwable th) {
        Log.error(TAG, "Exception on getting search response ", th);
    }

    private final ExecutionContext getExecutionContext() {
        return new ExecutionContextDefault.Builder().build();
    }

    public final Uri getAllSongsUri(Uri allSongsUri) {
        return allSongsUri != null ? allSongsUri : MediaProvider.Tracks.getContentUri("cirrus");
    }
}
