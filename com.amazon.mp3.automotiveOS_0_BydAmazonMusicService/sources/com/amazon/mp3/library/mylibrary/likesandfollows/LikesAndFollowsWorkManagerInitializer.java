package com.amazon.mp3.library.mylibrary.likesandfollows;

import android.content.Context;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsSyncWorker;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LikesAndFollowsWorkManagerInitializer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J4\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/library/mylibrary/likesandfollows/LikesAndFollowsWorkManagerInitializer;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "syncRepeatIntervalInMinutes", "", "(J)V", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "workManager", "Landroidx/work/WorkManager;", "enqueuePeriodicSyncWork", "", "workName", "", "time", "Lkotlin/Pair;", "Ljava/util/concurrent/TimeUnit;", "callType", "Lcom/amazon/mp3/library/mylibrary/likesandfollows/LikesAndFollowsSyncWorker$CallType;", "isDeltaSync", "", "getOnAppStartSyncWorkRequests", "", "Landroidx/work/OneTimeWorkRequest;", "getOneTimeSyncWorkRequest", "onCreate", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "onStop", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LikesAndFollowsWorkManagerInitializer implements DefaultLifecycleObserver {
    private final Context context;
    private long syncRepeatIntervalInMinutes;
    private final WorkManager workManager;

    /* JADX INFO: compiled from: LikesAndFollowsWorkManagerInitializer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LikesAndFollowsSyncWorker.CallType.values().length];
            iArr[LikesAndFollowsSyncWorker.CallType.SYNC_LIKED_ALBUMS.ordinal()] = 1;
            iArr[LikesAndFollowsSyncWorker.CallType.SYNC_FOLLOWED_ARTISTS.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LikesAndFollowsWorkManagerInitializer() {
        this(0L, 1, null);
    }

    public LikesAndFollowsWorkManagerInitializer(long j) {
        this.syncRepeatIntervalInMinutes = j;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        this.context = applicationContext;
        WorkManager workManager = WorkManager.getInstance(applicationContext);
        Intrinsics.checkNotNullExpressionValue(workManager, "getInstance(context)");
        this.workManager = workManager;
    }

    public /* synthetic */ LikesAndFollowsWorkManagerInitializer(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 15L : j);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onCreate(owner);
        this.workManager.beginWith(getOnAppStartSyncWorkRequests()).enqueue();
        Long lValueOf = Long.valueOf(this.syncRepeatIntervalInMinutes);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        Pair<Long, ? extends TimeUnit> pair = new Pair<>(lValueOf, timeUnit);
        LikesAndFollowsSyncWorker.CallType callType = LikesAndFollowsSyncWorker.CallType.SYNC_LIKED_ALBUMS;
        enqueuePeriodicSyncWork("PERIODIC_DELTA_LIKED_ALBUMS_WORK_NAME", pair, callType, true);
        Pair<Long, ? extends TimeUnit> pair2 = new Pair<>(Long.valueOf(this.syncRepeatIntervalInMinutes), timeUnit);
        LikesAndFollowsSyncWorker.CallType callType2 = LikesAndFollowsSyncWorker.CallType.SYNC_FOLLOWED_ARTISTS;
        enqueuePeriodicSyncWork("PERIODIC_DELTA_FOLLOWED_ARTISTS_WORK_NAME", pair2, callType2, true);
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        enqueuePeriodicSyncWork("PERIODIC_FULL_SYNC_LIKED_ALBUMS_WORK_NAME", new Pair<>(24L, timeUnit2), callType, false);
        enqueuePeriodicSyncWork("PERIODIC_FULL_SYNC_FOLLOWED_ARTISTS_WORK_NAME", new Pair<>(24L, timeUnit2), callType2, false);
    }

    private final void enqueuePeriodicSyncWork(String workName, Pair<Long, ? extends TimeUnit> time, LikesAndFollowsSyncWorker.CallType callType, boolean isDeltaSync) {
        Data.Builder builderPutBoolean = new Data.Builder().putString(LikesAndFollowsSyncWorker.CallType.CALL_TYPE_DATA, callType.name()).putBoolean(LikesAndFollowsSyncWorker.CallType.IS_DELTA_SYNC_DATA, isDeltaSync);
        Intrinsics.checkNotNullExpressionValue(builderPutBoolean, "Builder()\n            .p…A_SYNC_DATA, isDeltaSync)");
        PeriodicWorkRequest periodicWorkRequestBuild = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) LikesAndFollowsSyncWorker.class, time.getFirst().longValue(), time.getSecond()).addTag(isDeltaSync ? "PERIODIC_DELTA_FETCH_LIKES_AND_FOLLOWS_REQUEST_TAG" : "PERIODIC_FULL_FETCH_LIKES_AND_FOLLOWS_REQUEST_TAG").setInputData(builderPutBoolean.build()).build();
        Intrinsics.checkNotNullExpressionValue(periodicWorkRequestBuild, "PeriodicWorkRequestBuild…d())\n            .build()");
        this.workManager.enqueueUniquePeriodicWork(workName, ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequestBuild);
    }

    private final List<OneTimeWorkRequest> getOnAppStartSyncWorkRequests() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new OneTimeWorkRequest[]{getOneTimeSyncWorkRequest(LikesAndFollowsSyncWorker.CallType.SYNC_LIKED_ALBUMS), getOneTimeSyncWorkRequest(LikesAndFollowsSyncWorker.CallType.SYNC_FOLLOWED_ARTISTS)});
    }

    private final OneTimeWorkRequest getOneTimeSyncWorkRequest(LikesAndFollowsSyncWorker.CallType callType) {
        boolean zIsDeltaSync = isDeltaSync(callType);
        Data.Builder builderPutBoolean = new Data.Builder().putString(LikesAndFollowsSyncWorker.CallType.CALL_TYPE_DATA, callType.name()).putBoolean(LikesAndFollowsSyncWorker.CallType.IS_DELTA_SYNC_DATA, zIsDeltaSync);
        Intrinsics.checkNotNullExpressionValue(builderPutBoolean, "Builder()\n            .p…A_SYNC_DATA, isDeltaSync)");
        OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(LikesAndFollowsSyncWorker.class).addTag(zIsDeltaSync ? "ONE_TIME_DELTA_FETCH_LIKES_AND_FOLLOWS_REQUEST_TAG" : "ONE_TIME_FULL_FETCH_LIKES_AND_FOLLOWS_REQUEST_TAG").setInputData(builderPutBoolean.build()).build();
        Intrinsics.checkNotNullExpressionValue(oneTimeWorkRequestBuild, "OneTimeWorkRequestBuilde…d())\n            .build()");
        return oneTimeWorkRequestBuild;
    }

    private final boolean isDeltaSync(LikesAndFollowsSyncWorker.CallType callType) {
        int i = WhenMappings.$EnumSwitchMapping$0[callType.ordinal()];
        if (i == 1) {
            LibraryPreferences libraryPreferences = LibraryPreferences.INSTANCE;
            Context context = this.context;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            if (libraryPreferences.readLikedAlbumsCheckpoint(context) == 0) {
                return false;
            }
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            LibraryPreferences libraryPreferences2 = LibraryPreferences.INSTANCE;
            Context context2 = this.context;
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            if (libraryPreferences2.readFollowedArtistsCheckpoint(context2) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onStop(owner);
        this.workManager.cancelAllWorkByTag("PERIODIC_DELTA_FETCH_LIKES_AND_FOLLOWS_REQUEST_TAG");
    }
}
