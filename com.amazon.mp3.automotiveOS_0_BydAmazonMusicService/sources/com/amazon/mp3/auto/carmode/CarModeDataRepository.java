package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.DMAutomotiveConstants$Platform;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.catalog.fragment.datasource.dao.CarModeAlbumsDao;
import com.amazon.mp3.catalog.fragment.datasource.dao.CarModePlaylistsDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.apollographql.apollo3.mpp.UtilsKt;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: CarModeDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0006J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0016\u001a\u00020\u0006J \u0010\u0017\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00180\u0012H\u0003R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeDataRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "cirrusLibraryPlaylistsDataSource", "Landroid/net/Uri;", "cirrusUserPlaylistsDataSource", "count", "", "getCount", "()I", "localAlbumsDataSource", "localPrimePlaylistsDataSource", "fetchCarModeDetailPage", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "stringUri", "fetchCarModePage", "uri", "fetchPresetIdentifiers", "", "", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeDataRepository {
    private final String TAG;
    private final Uri cirrusLibraryPlaylistsDataSource;
    private final Uri cirrusUserPlaylistsDataSource;
    private final Context context;
    private final int count;
    private final Uri localAlbumsDataSource;
    private final Uri localPrimePlaylistsDataSource;

    public CarModeDataRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = CarModeDataRepository.class.getSimpleName();
        int presetsLimit = AmazonApplication.getCapabilities().isCarModeV2Enabled() ? CarModeConfigs.INSTANCE.getPresetsLimit() : 5;
        this.count = presetsLimit;
        this.cirrusUserPlaylistsDataSource = MediaProvider.UdoPlaylists.getContentUri("cirrus").buildUpon().appendQueryParameter("include_smart_playlists", "false").appendQueryParameter("compact", "true").appendQueryParameter("limit", String.valueOf(presetsLimit)).build();
        this.cirrusLibraryPlaylistsDataSource = MediaProvider.Playlists.getContentUri("cirrus").buildUpon().appendQueryParameter("include_smart_playlists", "false").appendQueryParameter("compact", "true").appendQueryParameter("limit", String.valueOf(presetsLimit)).build();
        this.localPrimePlaylistsDataSource = MediaProvider.PrimePlaylists.getContentUri("cirrus-local").buildUpon().appendQueryParameter("compact", "true").appendQueryParameter("limit", String.valueOf(presetsLimit)).build();
        this.localAlbumsDataSource = MediaProvider.Albums.getContentUri("cirrus-local").buildUpon().appendQueryParameter("limit", String.valueOf(presetsLimit)).build();
    }

    public final int getCount() {
        return this.count;
    }

    @WorkerThread
    private final Observable<Map<String, List<PresetIdentifier>>> fetchPresetIdentifiers() {
        final boolean zIsCarModeV2Enabled = AmazonApplication.getCapabilities().isCarModeV2Enabled();
        if (!Feature.is_car_mode_presets_update_enabled.isEnabled() || this.count <= 0) {
            Observable<Map<String, List<PresetIdentifier>>> observableJust = Observable.just(MapsKt__MapsKt.emptyMap());
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(emptyMap())");
            return observableJust;
        }
        Observable<Map<String, List<PresetIdentifier>>> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.auto.carmode.CarModeDataRepository$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeDataRepository.m255fetchPresetIdentifiers$lambda0(this.f$0, zIsCarModeV2Enabled, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { emitter ->\n    …r.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchPresetIdentifiers$lambda-0, reason: not valid java name */
    public static final void m255fetchPresetIdentifiers$lambda0(CarModeDataRepository this$0, boolean z, Subscriber subscriber) {
        List listEmptyList;
        Collection collectionEmptyList;
        List<PresetIdentifier> listEmptyList2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long jCurrentTimeMillis = UtilsKt.currentTimeMillis();
        CarModePlaylistsDao carModePlaylistsDao = new CarModePlaylistsDao(this$0.context);
        try {
            if (z) {
                Uri cirrusLibraryPlaylistsDataSource = this$0.cirrusLibraryPlaylistsDataSource;
                Intrinsics.checkNotNullExpressionValue(cirrusLibraryPlaylistsDataSource, "cirrusLibraryPlaylistsDataSource");
                listEmptyList = carModePlaylistsDao.libraryPlaylistsQuery(cirrusLibraryPlaylistsDataSource, Integer.valueOf(this$0.count)).query();
            } else {
                Uri cirrusUserPlaylistsDataSource = this$0.cirrusUserPlaylistsDataSource;
                Intrinsics.checkNotNullExpressionValue(cirrusUserPlaylistsDataSource, "cirrusUserPlaylistsDataSource");
                listEmptyList = carModePlaylistsDao.userPlaylistsQuery(cirrusUserPlaylistsDataSource, Integer.valueOf(this$0.count)).query();
            }
        } catch (Exception e) {
            Log.error(this$0.TAG, "Fail to get recently modified user playlists", e);
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        try {
            Uri localPrimePlaylistsDataSource = this$0.localPrimePlaylistsDataSource;
            Intrinsics.checkNotNullExpressionValue(localPrimePlaylistsDataSource, "localPrimePlaylistsDataSource");
            collectionEmptyList = carModePlaylistsDao.primePlaylistsQuery(localPrimePlaylistsDataSource, Integer.valueOf(this$0.count)).query();
        } catch (Exception e2) {
            Log.error(this$0.TAG, "Fail to get downloaded playlists", e2);
            collectionEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(collectionEmptyList);
        if (mutableList.size() < this$0.count) {
            try {
                CarModeAlbumsDao carModeAlbumsDao = new CarModeAlbumsDao(this$0.context);
                Uri localAlbumsDataSource = this$0.localAlbumsDataSource;
                Intrinsics.checkNotNullExpressionValue(localAlbumsDataSource, "localAlbumsDataSource");
                mutableList.addAll(carModeAlbumsDao.queryAlbums(localAlbumsDataSource, Integer.valueOf(this$0.count - mutableList.size())).query());
            } catch (Exception e3) {
                Log.error(this$0.TAG, "Fail to get downloaded albums", e3);
            }
        }
        try {
            listEmptyList2 = RecentlyPlayedManager.getInstance(this$0.context).queryRecentItems(Integer.valueOf(this$0.count));
            Intrinsics.checkNotNullExpressionValue(listEmptyList2, "{\n                Recent…tems(count)\n            }");
        } catch (Exception e4) {
            Log.error(this$0.TAG, "Fail to get recently played items", e4);
            listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
        }
        long jCurrentTimeMillis2 = UtilsKt.currentTimeMillis() - jCurrentTimeMillis;
        Log.info(this$0.TAG, "Use " + jCurrentTimeMillis2 + " millis in total to get Preset identifiers from database");
        subscriber.onNext(MapsKt__MapsKt.mapOf(TuplesKt.to("downloaded", mutableList), TuplesKt.to("recently_modified", listEmptyList), TuplesKt.to("recently_played", listEmptyList2)));
        subscriber.onCompleted();
    }

    public final Observable<PageGridViewModel> fetchCarModePage(final String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BrushLatencyReportingUtil.INSTANCE.trackLatency(uri, LatencyTrackingLeg.REQUEST);
        Observable observableConcatMap = fetchPresetIdentifiers().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.auto.carmode.CarModeDataRepository$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return CarModeDataRepository.m254fetchCarModePage$lambda1(this.f$0, uri, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "identifierObservable.con…s\n            )\n        }");
        return observableConcatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchCarModePage$lambda-1, reason: not valid java name */
    public static final Observable m254fetchCarModePage$lambda1(CarModeDataRepository this$0, String uri, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        PresetIdentifierHelper presetIdentifierHelper = PresetIdentifierHelper.INSTANCE;
        String strAssemble = presetIdentifierHelper.assemble((List) map.get("downloaded"));
        String strAssemble2 = presetIdentifierHelper.assemble((List) map.get("recently_played"));
        String strAssemble3 = presetIdentifierHelper.assemble((List) map.get("recently_modified"));
        return new BrushV3ApiPageModelDataRepository(this$0.context).fetchCarModePage(uri, DMAutomotiveConstants$Platform.ANDROID.getValue(), (64 & 4) != 0 ? null : presetIdentifierHelper.assemble(presetIdentifierHelper.getPresetIdentifiers(this$0.context)), (64 & 8) != 0 ? null : strAssemble, (64 & 16) != 0 ? null : strAssemble2, (64 & 32) != 0 ? null : strAssemble3, (64 & 64) != 0 ? null : null);
    }

    public final Observable<PageGridViewModel> fetchCarModeDetailPage(String stringUri) {
        Intrinsics.checkNotNullParameter(stringUri, "stringUri");
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", LatencyTrackingLeg.REQUEST);
        Uri uri = Uri.parse(stringUri);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        String queryParameter = uri.getQueryParameter("downloadedIds");
        String queryParameter2 = uri.getQueryParameter("recentMusicIds");
        String queryParameter3 = uri.getQueryParameter("recentlyCreatedLibraryPlaylistIds");
        Observable<PageGridViewModel> observableSubscribeOn = new BrushV3ApiPageModelDataRepository(this.context).fetchCarModePage("carmode-detail", DMAutomotiveConstants$Platform.ANDROID.getValue(), uri.getQueryParameter("presetIds"), queryParameter, queryParameter2, queryParameter3, uri.getQueryParameter("widgetId")).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "BrushV3ApiPageModelDataR…scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }
}
