package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTracker;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTrackerMetrics;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.PreWidevineDBUpdateResult;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.PreWidevineDBUpdater;
import com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation;
import com.amazon.mp3.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

/* JADX INFO: compiled from: PreWidevineDBUpdateOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB'\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u0005\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\u0003\u0010\u0004R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00118\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PreWidevineDBUpdateOperation;", "Lcom/amazon/mp3/library/service/sync/SyncOperation;", "", "sync$DigitalMusicAndroid3P_automotiveProdRelease", "()I", BaseJavaModule.METHOD_TYPE_SYNC, "Landroid/database/sqlite/SQLiteDatabase;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdater;", "dbUpdater", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdater;", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTracker;", "migrationTracker", "Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTracker;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Success_Reason", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/PreWidevineDBUpdater;Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTracker;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PreWidevineDBUpdateOperation extends SyncOperation {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isRunning;
    private static ReplaySubject<Boolean> sMigrationSubject;
    private final String Success_Reason;
    private final String TAG;
    private final SQLiteDatabase db;
    private final PreWidevineDBUpdater dbUpdater;
    private final WidevineMigrationTracker migrationTracker;

    /* JADX INFO: compiled from: PreWidevineDBUpdateOperation.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PreWidevineDBUpdateResult.values().length];
            iArr[PreWidevineDBUpdateResult.SUCCESS.ordinal()] = 1;
            iArr[PreWidevineDBUpdateResult.NO_DRM_TRACKS_TO_UPDATE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final Observable<Boolean> getMigrationBannerObservable(Context context) {
        return INSTANCE.getMigrationBannerObservable(context);
    }

    @JvmStatic
    public static final boolean isAlreadyRunning() {
        return INSTANCE.isAlreadyRunning();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreWidevineDBUpdateOperation(Context context, SQLiteDatabase db, PreWidevineDBUpdater dbUpdater, WidevineMigrationTracker migrationTracker) {
        super(context, db);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(dbUpdater, "dbUpdater");
        Intrinsics.checkNotNullParameter(migrationTracker, "migrationTracker");
        this.db = db;
        this.dbUpdater = dbUpdater;
        this.migrationTracker = migrationTracker;
        this.TAG = PreWidevineDBUpdateOperation.class.getSimpleName();
        this.Success_Reason = "Successful_Empty_Library";
    }

    /* JADX INFO: compiled from: PreWidevineDBUpdateOperation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u0007\u001a\u00020\u0005H\u0007R(\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/library/service/sync/PreWidevineDBUpdateOperation$Companion;", "", "Landroid/content/Context;", "context", "Lrx/Observable;", "", "getMigrationBannerObservable", "isAlreadyRunning", "Lrx/subjects/ReplaySubject;", "sMigrationSubject", "Lrx/subjects/ReplaySubject;", "getSMigrationSubject", "()Lrx/subjects/ReplaySubject;", "setSMigrationSubject", "(Lrx/subjects/ReplaySubject;)V", "isRunning", "Z", "()Z", "setRunning", "(Z)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReplaySubject<Boolean> getSMigrationSubject() {
            return PreWidevineDBUpdateOperation.sMigrationSubject;
        }

        public final boolean isRunning() {
            return PreWidevineDBUpdateOperation.isRunning;
        }

        @JvmStatic
        public final Observable<Boolean> getMigrationBannerObservable(final Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!SettingsUtil.hasCompletedWidevineDatabaseUpdate(context)) {
                return getSMigrationSubject();
            }
            Observable<Boolean> observableSubscribeOn = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.library.service.sync.PreWidevineDBUpdateOperation$Companion$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PreWidevineDBUpdateOperation.Companion.m1347getMigrationBannerObservable$lambda0(context, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.io());
            Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "{\n                Observ…ulers.io())\n            }");
            return observableSubscribeOn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: getMigrationBannerObservable$lambda-0, reason: not valid java name */
        public static final void m1347getMigrationBannerObservable$lambda0(Context context, Subscriber subscriber) {
            Intrinsics.checkNotNullParameter(context, "$context");
            subscriber.onNext(Boolean.valueOf(SettingsUtil.shouldShowRedownloadUIUntilAcknowledged(context)));
            subscriber.onCompleted();
        }

        @JvmStatic
        public final boolean isAlreadyRunning() {
            return isRunning();
        }
    }

    static {
        ReplaySubject<Boolean> replaySubjectCreate = ReplaySubject.create();
        Intrinsics.checkNotNullExpressionValue(replaySubjectCreate, "create()");
        sMigrationSubject = replaySubjectCreate;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    /* JADX INFO: renamed from: sync$DigitalMusicAndroid3P_automotiveProdRelease, reason: merged with bridge method [inline-methods] */
    public int sync() {
        Log.info(this.TAG, "Begin marking downloaded tracks and playlists as requiring redownloading.");
        isRunning = true;
        PreWidevineDBUpdateResult preWidevineDBUpdateResultUpdate = this.dbUpdater.update(this.db);
        new PreWidevineDRMContentDeletionOperation(getContext()).sync();
        int i = WhenMappings.$EnumSwitchMapping$0[preWidevineDBUpdateResultUpdate.ordinal()];
        if (i == 1) {
            Log.info(this.TAG, "Successfully marked downloaded tracks and playlists as requiring redownloading.");
            SyncService.setPreWidevineDBUpdateNeeded(false);
            SettingsUtil.setHasCompletedWidevineDatabaseUpdate(getContext(), true);
            SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(getContext(), true);
            sMigrationSubject.onNext(Boolean.TRUE);
            sMigrationSubject.onCompleted();
            this.migrationTracker.init();
            WidevineMigrationTrackerMetrics.INSTANCE.sendMigrationAssessed(this.migrationTracker.getOriginalLibraryTracksCount(), this.migrationTracker.getOriginalCatalogPlaylistTracksCount());
            isRunning = false;
            return 0;
        }
        if (i == 2) {
            SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(getContext(), false);
            Log.info(this.TAG, "No downloaded DRM tracks in database. Skipping Widevine database update.");
            SyncService.setPreWidevineDBUpdateNeeded(false);
            SettingsUtil.setHasCompletedWidevineDatabaseUpdate(getContext(), true);
            SettingsUtil.setHasCompletedWidevineMigration(getContext(), true);
            sMigrationSubject.onNext(Boolean.FALSE);
            sMigrationSubject.onCompleted();
            WidevineMigrationTrackerMetrics widevineMigrationTrackerMetrics = WidevineMigrationTrackerMetrics.INSTANCE;
            widevineMigrationTrackerMetrics.sendMigrationAssessed(0, 0);
            widevineMigrationTrackerMetrics.sendMigrationCompletedSuccessful(this.Success_Reason, 0, 0);
            isRunning = false;
            return 0;
        }
        SettingsUtil.setShouldShowRedownloadUIUntilAcknowledged(getContext(), false);
        Log.error(this.TAG, "Failed to mark downloaded tracks and playlists as requiring redownloading.");
        SyncService.setPreWidevineDBUpdateNeeded(true);
        sMigrationSubject.onNext(Boolean.FALSE);
        sMigrationSubject.onCompleted();
        WidevineMigrationTrackerMetrics.INSTANCE.sendMigrationAsssessmentFailed();
        isRunning = false;
        return 5;
    }
}
