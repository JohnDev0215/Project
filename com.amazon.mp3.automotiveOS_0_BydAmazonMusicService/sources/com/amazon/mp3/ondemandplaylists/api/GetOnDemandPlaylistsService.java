package com.amazon.mp3.ondemandplaylists.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.UserAgent;
import com.amazon.mp3.ondemandplaylists.api.model.GetOnDemandPlaylistsResponse;
import com.amazon.mp3.ondemandplaylists.api.model.OnDemandPlaylist;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.mp3.ondemandplaylists.service.UsherServiceClient;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.ConfigurationProviderImpl;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: GetOnDemandPlaylistsService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 92\u00020\u0001:\u00019B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fH\u0002J\u0014\u0010 \u001a\u00020\u001c2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fJ\u0006\u0010\"\u001a\u00020\u001cJ\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0006\u0010&\u001a\u00020\u001cJ\b\u0010'\u001a\u0004\u0018\u00010\u0006J\b\u0010(\u001a\u00020)H\u0002J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fH\u0002J\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001fJ\b\u0010,\u001a\u0004\u0018\u00010\u0017J\u0018\u0010-\u001a\u0004\u0018\u00010\u00062\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fH\u0002J\u0010\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0006J\u0010\u00102\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0006J(\u00103\u001a\u00020\u001c2\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fH\u0002J\u0006\u00105\u001a\u00020\u001cJ\u0010\u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u00020\u0006H\u0007J\n\u00108\u001a\u0004\u0018\u00010%H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/api/GetOnDemandPlaylistsService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_FREE_TIER_ON_DEMAND_PLAYLISTS", "", "SHARED_PREF_NAME", "TIMEOUT_MS", "", "authProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "configurationProvider", "Lcom/amazon/mp3/platform/providers/ConfigurationProviderImpl;", "customerId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "eligibilityTier", Splash.PARAMS_MUSIC_TERRITORY, "region", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sharedPreferences", "Landroid/content/SharedPreferences;", "userAgent", "usherServiceClient", "Lcom/amazon/mp3/ondemandplaylists/service/UsherServiceClient;", "addToOnDemandPlaylistsCache", "", "newPlaylistId", "onDemandPlaylists", "", "cacheOnDemandPlaylists", "playlistAsins", "clearOnDemandPlaylists", "extractPlaylistAsins", "onDemandPlaylistsResponse", "Lcom/amazon/mp3/ondemandplaylists/api/model/GetOnDemandPlaylistsResponse;", "fetchAndCacheOnDemandPlaylists", "getAllAccessUserPlaylistFromCache", "getAllAccessUserPlaylistMaxCount", "", "getOnDemandPlaylists", "getOnDemandPlaylistsFromCache", "getSharedPrefs", "getUserPlaylistToReplace", "onDemandUserPlaylists", "isOnDemandPlaylist", "", "playlistAsin", "isOnDemandUserPlaylist", "replaceInOnDemandPlaylistsCache", "playlistIdToReplace", "updateEligibilityTier", "updateOnDemandUserPlaylist", "playlistId", "usherGetOnDemandPlaylists", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GetOnDemandPlaylistsService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(GetOnDemandPlaylistsService.class).getSimpleName();
    private static GetOnDemandPlaylistsService sInstance;
    private final String KEY_FREE_TIER_ON_DEMAND_PLAYLISTS;
    private final String SHARED_PREF_NAME;
    private final long TIMEOUT_MS;
    private final AuthenticationProvider authProvider;
    private final ConfigurationProviderImpl configurationProvider;
    private final Context context;
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private String eligibilityTier;
    private final String musicTerritory;
    private final String region;
    private final CoroutineScope scope;
    private SharedPreferences sharedPreferences;
    private final String userAgent;
    private final UsherServiceClient usherServiceClient;

    /* JADX INFO: compiled from: GetOnDemandPlaylistsService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/api/GetOnDemandPlaylistsService$Companion;", "", "()V", "ALL_ACCESS_USER_PLAYLIST_MAX_COUNT_LOOKUP_KEY", "", "TAG", "getTAG", "()Ljava/lang/String;", "sInstance", "Lcom/amazon/mp3/ondemandplaylists/api/GetOnDemandPlaylistsService;", "get", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return GetOnDemandPlaylistsService.TAG;
        }

        public final GetOnDemandPlaylistsService get() {
            if (GetOnDemandPlaylistsService.sInstance == null) {
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                GetOnDemandPlaylistsService.sInstance = new GetOnDemandPlaylistsService(applicationContext);
            }
            GetOnDemandPlaylistsService getOnDemandPlaylistsService = GetOnDemandPlaylistsService.sInstance;
            Intrinsics.checkNotNull(getOnDemandPlaylistsService);
            return getOnDemandPlaylistsService;
        }
    }

    public GetOnDemandPlaylistsService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.SHARED_PREF_NAME = Intrinsics.stringPlus("com.amazon.mp3_", TAG);
        this.KEY_FREE_TIER_ON_DEMAND_PLAYLISTS = "FREE_TIER_ON_DEMAND_PLAYLISTS";
        this.TIMEOUT_MS = 4000L;
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(context);
        DeviceAttributes deviceAttributes = new DeviceAttributes(context);
        String strEligibilityTierForUsherService = UserSubscriptionUtil.getSubscription().requests().eligibilityTierForUsherService();
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        this.context = context;
        this.customerId = accountDetailUtil.getCustomerId();
        this.deviceId = deviceAttributes.getDeviceId();
        this.deviceType = deviceAttributes.getDeviceType();
        this.musicTerritory = musicTerritoryOfResidence;
        this.eligibilityTier = strEligibilityTierForUsherService;
        String region = accountDetailUtil.getHomeMarketPlace().getRegion();
        this.region = region;
        AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl(context, null, 2, 0 == true ? 1 : 0);
        this.authProvider = authenticationProviderImpl;
        this.configurationProvider = new ConfigurationProviderImpl();
        String userAgent = UserAgent.getUserAgent();
        this.userAgent = userAgent;
        this.usherServiceClient = new UsherServiceClient(accountDetailUtil.getCustomerId(), deviceAttributes.getDeviceId(), deviceAttributes.getDeviceType(), musicTerritoryOfResidence, strEligibilityTierForUsherService, region, authenticationProviderImpl, userAgent);
    }

    public final void updateEligibilityTier() {
        this.eligibilityTier = UserSubscriptionUtil.getSubscription().requests().eligibilityTierForUsherService();
    }

    public final boolean isOnDemandPlaylist(String playlistAsin) {
        return playlistAsin != null && getOnDemandPlaylists().contains(playlistAsin);
    }

    public final boolean isOnDemandUserPlaylist(String playlistAsin) {
        return (playlistAsin != null && getOnDemandPlaylists().contains(playlistAsin)) && !PrimePlaylistUtil.isAsin(playlistAsin);
    }

    private final Set<String> getOnDemandPlaylists() {
        Set<String> onDemandPlaylistsFromCache = getOnDemandPlaylistsFromCache();
        if (onDemandPlaylistsFromCache != null) {
            return onDemandPlaylistsFromCache;
        }
        fetchAndCacheOnDemandPlaylists();
        return SetsKt__SetsKt.emptySet();
    }

    @WorkerThread
    public final void updateOnDemandUserPlaylist(String playlistId) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Log.d(TAG, Intrinsics.stringPlus("Starts updating on-demand user playlists. new selection is ", playlistId));
        Set<String> onDemandPlaylists = getOnDemandPlaylists();
        ArrayList arrayList = new ArrayList();
        for (Object obj : onDemandPlaylists) {
            if (!PrimePlaylistUtil.isAsin((String) obj)) {
                arrayList.add(obj);
            }
        }
        Set<String> set = CollectionsKt___CollectionsKt.toSet(arrayList);
        if (set.contains(playlistId)) {
            Log.w(TAG, Intrinsics.stringPlus(playlistId, " is already an on-demand user playlist. Skipping"));
            return;
        }
        String str = TAG;
        Log.d(str, Intrinsics.stringPlus("Existing on-demand user playlist(s): ", set));
        int allAccessUserPlaylistMaxCount = getAllAccessUserPlaylistMaxCount();
        if (allAccessUserPlaylistMaxCount <= 0) {
            Log.e(str, "The max count of on-demand user playlist received is <= 0. Please check if this is expected");
        } else if (set.size() < allAccessUserPlaylistMaxCount) {
            addToOnDemandPlaylistsCache(playlistId, onDemandPlaylists);
        } else {
            replaceInOnDemandPlaylistsCache(getUserPlaylistToReplace(set), playlistId, onDemandPlaylists);
        }
    }

    private final String getUserPlaylistToReplace(Set<String> onDemandUserPlaylists) {
        if (onDemandUserPlaylists.isEmpty()) {
            Log.e(TAG, "There is no existing on-demand user playlist that can be replaced");
            return null;
        }
        if (onDemandUserPlaylists.size() == 1) {
            return (String) CollectionsKt___CollectionsKt.first(onDemandUserPlaylists);
        }
        List<String> orderedUserPlaylists = PlaylistUtil.getPlaylistLuidsOrderedByModifiedDate(this.context, CollectionsKt___CollectionsKt.toList(onDemandUserPlaylists));
        if (orderedUserPlaylists.isEmpty()) {
            Log.e(TAG, "Cannot find records whose LUIDs are " + onDemandUserPlaylists + " from Playlist table. Randomly pick one to replace");
            return (String) CollectionsKt___CollectionsKt.first(onDemandUserPlaylists);
        }
        if (onDemandUserPlaylists.size() != orderedUserPlaylists.size()) {
            Log.e(TAG, (onDemandUserPlaylists.size() - orderedUserPlaylists.size()) + " user playlists not found from Playlist table");
        }
        Intrinsics.checkNotNullExpressionValue(orderedUserPlaylists, "orderedUserPlaylists");
        return (String) CollectionsKt___CollectionsKt.first((List) orderedUserPlaylists);
    }

    private final void addToOnDemandPlaylistsCache(String newPlaylistId, Set<String> onDemandPlaylists) {
        Set<String> mutableSet = CollectionsKt___CollectionsKt.toMutableSet(onDemandPlaylists);
        mutableSet.add(newPlaylistId);
        cacheOnDemandPlaylists(mutableSet);
        Log.d(TAG, "Successfully added " + newPlaylistId + " to on-demand playlists cache");
    }

    private final void replaceInOnDemandPlaylistsCache(String playlistIdToReplace, String newPlaylistId, Set<String> onDemandPlaylists) {
        Set<String> mutableSet = CollectionsKt___CollectionsKt.toMutableSet(onDemandPlaylists);
        if (playlistIdToReplace != null) {
            mutableSet.remove(playlistIdToReplace);
        }
        mutableSet.add(newPlaylistId);
        cacheOnDemandPlaylists(mutableSet);
        if (playlistIdToReplace == null) {
            Log.d(TAG, "Successfully added " + newPlaylistId + " to on-demand playlists cache");
            return;
        }
        Log.d(TAG, "Successfully replaced " + ((Object) playlistIdToReplace) + " with " + newPlaylistId + " in on-demand playlists cache");
    }

    private final int getAllAccessUserPlaylistMaxCount() {
        return this.configurationProvider.getIntForKey("all_access_user_playlist_max_count");
    }

    public final String getAllAccessUserPlaylistFromCache() {
        Set<String> onDemandPlaylistsFromCache = getOnDemandPlaylistsFromCache();
        if (onDemandPlaylistsFromCache != null) {
            for (String str : onDemandPlaylistsFromCache) {
                if (isOnDemandUserPlaylist(str)) {
                    Log.i(TAG, "Successfully retrieved all-access user playlist from cache.");
                    return str;
                }
            }
        }
        Log.i(TAG, "Failed to retrieve all-access user playlist from cache.");
        return null;
    }

    public final Set<String> getOnDemandPlaylistsFromCache() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        Set<String> stringSet = null;
        if (sharedPrefs != null) {
            stringSet = sharedPrefs.getStringSet(this.KEY_FREE_TIER_ON_DEMAND_PLAYLISTS, null);
            if (stringSet != null) {
                Log.i(TAG, "Successfully retrieved on-demand playlists from cache.");
            } else {
                Log.i(TAG, "Failed to retrieve on-demand playlists from cache - cache is empty.");
            }
        }
        return stringSet;
    }

    public final void clearOnDemandPlaylists() {
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPrefs.edit();
        editorEdit.remove(this.KEY_FREE_TIER_ON_DEMAND_PLAYLISTS);
        editorEdit.apply();
        Log.i(TAG, "Cleared on-demand playlists cache.");
        GetOnDemandPlaylistsMetricsUtil.INSTANCE.trackEvent(GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_CLEAR_PLAYLISTS_FROM_CACHE);
        sInstance = null;
    }

    public final SharedPreferences getSharedPrefs() {
        SharedPreferences sharedPreferences = this.sharedPreferences;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        SharedPreferences sharedPreferences2 = this.context.getSharedPreferences(Intrinsics.stringPlus(this.SHARED_PREF_NAME, this.KEY_FREE_TIER_ON_DEMAND_PLAYLISTS), 0);
        this.sharedPreferences = sharedPreferences2;
        return sharedPreferences2;
    }

    public final void cacheOnDemandPlaylists(Set<String> playlistAsins) {
        Intrinsics.checkNotNullParameter(playlistAsins, "playlistAsins");
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (sharedPrefs == null) {
            Log.e(TAG, "Failed to cache on-demand playlists - SharedPreferences has not been set.");
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPrefs.edit();
        editorEdit.putStringSet(this.KEY_FREE_TIER_ON_DEMAND_PLAYLISTS, playlistAsins);
        editorEdit.apply();
        Log.i(TAG, "Successfully cached on-demand playlists.");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1, reason: invalid class name */
    /* JADX INFO: compiled from: GetOnDemandPlaylistsService.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1", f = "GetOnDemandPlaylistsService.kt", i = {0}, l = {MediaError.DetailedErrorCode.SEGMENT_NETWORK}, m = "invokeSuspend", n = {"response"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return GetOnDemandPlaylistsService.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef objectRef;
            Exception e;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                try {
                    long j = GetOnDemandPlaylistsService.this.TIMEOUT_MS;
                    C00941 c00941 = new C00941(objectRef2, GetOnDemandPlaylistsService.this, null);
                    this.L$0 = objectRef2;
                    this.label = 1;
                    if (TimeoutKt.withTimeout(j, c00941, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } catch (CancellationException unused) {
                    objectRef = objectRef2;
                    Log.e(GetOnDemandPlaylistsService.INSTANCE.getTAG(), "Timed out while fetching and caching on-demand playlists.");
                } catch (Exception e2) {
                    objectRef = objectRef2;
                    e = e2;
                    Log.e(GetOnDemandPlaylistsService.INSTANCE.getTAG(), "Error while fetching and caching on-demand playlists.", e);
                }
            } else if (i == 1) {
                objectRef = (Ref.ObjectRef) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (CancellationException unused2) {
                    Log.e(GetOnDemandPlaylistsService.INSTANCE.getTAG(), "Timed out while fetching and caching on-demand playlists.");
                } catch (Exception e3) {
                    e = e3;
                    Log.e(GetOnDemandPlaylistsService.INSTANCE.getTAG(), "Error while fetching and caching on-demand playlists.", e);
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            GetOnDemandPlaylistsResponse getOnDemandPlaylistsResponse = (GetOnDemandPlaylistsResponse) objectRef.element;
            if (getOnDemandPlaylistsResponse != null) {
                GetOnDemandPlaylistsService getOnDemandPlaylistsService = GetOnDemandPlaylistsService.this;
                getOnDemandPlaylistsService.cacheOnDemandPlaylists(getOnDemandPlaylistsService.extractPlaylistAsins(getOnDemandPlaylistsResponse));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: GetOnDemandPlaylistsService.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1$1", f = "GetOnDemandPlaylistsService.kt", i = {}, l = {302}, m = "invokeSuspend", n = {}, s = {})
        static final class C00941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<GetOnDemandPlaylistsResponse> $response;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ GetOnDemandPlaylistsService this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00941(Ref.ObjectRef<GetOnDemandPlaylistsResponse> objectRef, GetOnDemandPlaylistsService getOnDemandPlaylistsService, Continuation<? super C00941> continuation) {
                super(2, continuation);
                this.$response = objectRef;
                this.this$0 = getOnDemandPlaylistsService;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00941 c00941 = new C00941(this.$response, this.this$0, continuation);
                c00941.L$0 = obj;
                return c00941;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: GetOnDemandPlaylistsService.kt */
            @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/ondemandplaylists/api/model/GetOnDemandPlaylistsResponse;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
            @DebugMetadata(c = "com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService$fetchAndCacheOnDemandPlaylists$1$1$1", f = "GetOnDemandPlaylistsService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class C00951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super GetOnDemandPlaylistsResponse>, Object> {
                int label;
                final /* synthetic */ GetOnDemandPlaylistsService this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00951(GetOnDemandPlaylistsService getOnDemandPlaylistsService, Continuation<? super C00951> continuation) {
                    super(2, continuation);
                    this.this$0 = getOnDemandPlaylistsService;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00951(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super GetOnDemandPlaylistsResponse> continuation) {
                    return ((C00951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return this.this$0.usherGetOnDemandPlaylists();
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Ref.ObjectRef<GetOnDemandPlaylistsResponse> objectRef;
                T t;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Ref.ObjectRef<GetOnDemandPlaylistsResponse> objectRef2 = this.$response;
                    Deferred deferredAsync$default = BuildersKt__Builders_commonKt.async$default(coroutineScope, Dispatchers.getIO(), null, new C00951(this.this$0, null), 2, null);
                    this.L$0 = objectRef2;
                    this.label = 1;
                    Object objAwait = deferredAsync$default.await(this);
                    if (objAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                    t = objAwait;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    t = obj;
                }
                objectRef.element = t;
                return Unit.INSTANCE;
            }
        }
    }

    public final void fetchAndCacheOnDemandPlaylists() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GetOnDemandPlaylistsResponse usherGetOnDemandPlaylists() {
        try {
            return this.usherServiceClient.getOnDemandPlaylists();
        } catch (Exception e) {
            Log.e(TAG, "Error fetching on-demand playlists from UsherService", e);
            GetOnDemandPlaylistsMetricsUtil.INSTANCE.trackFailureEvent(GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_USHER_SERVICE_CALL, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> extractPlaylistAsins(GetOnDemandPlaylistsResponse onDemandPlaylistsResponse) {
        List<OnDemandPlaylist> onDemandPlaylists;
        Set<String> set = null;
        if (onDemandPlaylistsResponse != null && (onDemandPlaylists = onDemandPlaylistsResponse.getOnDemandPlaylists()) != null) {
            ArrayList arrayList = new ArrayList();
            for (OnDemandPlaylist onDemandPlaylist : onDemandPlaylists) {
                String asin = onDemandPlaylist == null ? null : onDemandPlaylist.getAsin();
                if (asin != null) {
                    arrayList.add(asin);
                }
            }
            set = CollectionsKt___CollectionsKt.toSet(arrayList);
        }
        return set == null ? SetsKt__SetsKt.emptySet() : set;
    }
}
