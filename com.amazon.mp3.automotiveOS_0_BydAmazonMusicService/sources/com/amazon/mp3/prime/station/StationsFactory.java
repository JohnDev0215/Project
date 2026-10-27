package com.amazon.mp3.prime.station;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.station.Configuration;
import com.amazon.music.station.StationContentSelector;
import com.amazon.music.station.StationManager;
import com.amazon.music.station.StationService;
import com.amazon.musicensembleservice.AllowedParentalControls;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class StationsFactory {
    private static volatile boolean sNeedsRefresh = false;
    private static volatile StationsFactory sStationsFactory;
    private final Context mContext;
    private final StationManager mStationManager;
    private final StationService mStationService;
    private PublishSubject stationsSubject = PublishSubject.create();
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.prime.station.StationsFactory.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE) || set.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE)) {
                AccountManagerSingleton.get().deregisterListener(StationsFactory.this.mAccountStateChangeListener);
                boolean unused = StationsFactory.sNeedsRefresh = true;
                StationsFactory.this.stationsSubject.onNext(null);
            }
        }
    };

    public static StationsFactory getInstance(Context context) {
        StationsFactory stationsFactory;
        StationContentSelector stationContentSelection = new BrowseContentSelectionProvider().getStationContentSelection(context);
        AccountDetailUtil.get(context.getApplicationContext());
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        StationsFactory stationsFactory2 = sStationsFactory;
        if (stationsFactory2 != null && !sNeedsRefresh) {
            return stationsFactory2;
        }
        synchronized (StationsFactory.class) {
            stationsFactory = sStationsFactory;
            if (stationsFactory == null || sNeedsRefresh) {
                StationsFactory stationsFactory3 = new StationsFactory(context.getApplicationContext(), musicTerritoryOfResidence, deviceId, deviceType, stationContentSelection);
                sStationsFactory = stationsFactory3;
                sNeedsRefresh = false;
                stationsFactory = stationsFactory3;
            }
        }
        return stationsFactory;
    }

    private StationsFactory(Context context, String musicTerritory, String deviceId, String deviceType, StationContentSelector contentSelector) {
        Configuration.Builder builder = new Configuration.Builder(musicTerritory, deviceId, deviceType, MusicURL.getStationURL(), MusicURL.getMuseUrl(), new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor(), contentSelector);
        builder.withLanguage(new BrowseLanguageProvider().getLocale(context).toString());
        StationService stationService = new StationService(builder.build());
        this.mStationService = stationService;
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()));
        this.mStationManager = new StationManager(stationService, allowedParentalControls, ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context));
        this.mContext = context.getApplicationContext();
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    public StationService getStationService() {
        return this.mStationService;
    }

    public StationPresenterWrapper createStationPresenterWrapper() {
        return new StationPresenterWrapper(this.mStationManager);
    }

    public StationManager getStationManager() {
        return this.mStationManager;
    }

    public Observable<Void> registerStationsObserver() {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.mp3.prime.station.StationsFactory.2
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Void> subscriber) {
                StationsFactory.this.stationsSubject.subscribe((Subscriber) subscriber);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }
}
