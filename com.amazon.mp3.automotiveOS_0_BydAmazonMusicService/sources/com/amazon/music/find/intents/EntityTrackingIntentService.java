package com.amazon.music.find.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.app.JobIntentService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.find.intents.EntityTrackingIntent;
import com.amazon.music.find.service.EntityTrackingService;
import dagger.android.AndroidInjection;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: EntityTrackingIntentService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/intents/EntityTrackingIntentService;", "Landroidx/core/app/JobIntentService;", "()V", "entityTrackingService", "Lcom/amazon/music/find/service/EntityTrackingService;", "getEntityTrackingService", "()Lcom/amazon/music/find/service/EntityTrackingService;", "setEntityTrackingService", "(Lcom/amazon/music/find/service/EntityTrackingService;)V", "handleEntityRefreshIntent", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "handleEntityVisitIntent", "onCreate", "onHandleWork", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EntityTrackingIntentService extends JobIntentService {
    private static final String TAG = "SPL";
    public EntityTrackingService entityTrackingService;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(EntityTrackingIntentService.class.getName());
    private static final int JOB_ID = EntityTrackingIntentService.class.getSimpleName().hashCode();

    @JvmStatic
    public static final void processEntityRefresh(Context context, EntityRefreshIntentInfo entityRefreshIntentInfo) {
        INSTANCE.processEntityRefresh(context, entityRefreshIntentInfo);
    }

    @JvmStatic
    public static final void processEntityVisit(Context context, EntityVisitIntentInfo entityVisitIntentInfo) {
        INSTANCE.processEntityVisit(context, entityVisitIntentInfo);
    }

    public final EntityTrackingService getEntityTrackingService() {
        EntityTrackingService entityTrackingService = this.entityTrackingService;
        if (entityTrackingService != null) {
            return entityTrackingService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entityTrackingService");
        return null;
    }

    public final void setEntityTrackingService(EntityTrackingService entityTrackingService) {
        Intrinsics.checkNotNullParameter(entityTrackingService, "<set-?>");
        this.entityTrackingService = entityTrackingService;
    }

    /* JADX INFO: compiled from: EntityTrackingIntentService.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/find/intents/EntityTrackingIntentService$Companion;", "", "()V", "JOB_ID", "", "TAG", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "processEntityRefresh", "", "context", "Landroid/content/Context;", "entityRefreshInfo", "Lcom/amazon/music/find/intents/EntityRefreshIntentInfo;", "processEntityVisit", "entityVisitInfo", "Lcom/amazon/music/find/intents/EntityVisitIntentInfo;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void processEntityVisit(Context context, EntityVisitIntentInfo entityVisitInfo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(entityVisitInfo, "entityVisitInfo");
            EntityTrackingIntent.Companion companion = EntityTrackingIntent.INSTANCE;
            Intent intent = new Intent(companion.getHANDLE_ENTITY_VISIT());
            intent.putExtra(companion.getEXTRA_ENTITY_VISIT_INFO(), entityVisitInfo);
            JobIntentService.enqueueWork(context, (Class<?>) EntityTrackingIntentService.class, EntityTrackingIntentService.JOB_ID, intent);
        }

        @JvmStatic
        public final void processEntityRefresh(Context context, EntityRefreshIntentInfo entityRefreshInfo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(entityRefreshInfo, "entityRefreshInfo");
            EntityTrackingIntent.Companion companion = EntityTrackingIntent.INSTANCE;
            Intent intent = new Intent(companion.getHANDLE_ENTITY_REFRESH());
            intent.putExtra(companion.getEXTRA_ENTITY_REFRESH_INFO(), entityRefreshInfo);
            JobIntentService.enqueueWork(context, (Class<?>) EntityTrackingIntentService.class, EntityTrackingIntentService.JOB_ID, intent);
        }
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        EntityTrackingIntent.Companion companion = EntityTrackingIntent.INSTANCE;
        if (Intrinsics.areEqual(action, companion.getHANDLE_ENTITY_VISIT())) {
            handleEntityVisitIntent(intent);
        } else if (Intrinsics.areEqual(action, companion.getHANDLE_ENTITY_REFRESH())) {
            handleEntityRefreshIntent(intent);
        } else {
            logger.warn(Intrinsics.stringPlus("SPL::Unknown intent action received: ", intent.getAction()));
        }
    }

    private final void handleEntityVisitIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        Parcelable parcelable = extras.getParcelable(EntityTrackingIntent.INSTANCE.getEXTRA_ENTITY_VISIT_INFO());
        final EntityVisitIntentInfo entityVisitIntentInfo = parcelable instanceof EntityVisitIntentInfo ? (EntityVisitIntentInfo) parcelable : null;
        Logger logger2 = logger;
        StringBuilder sb = new StringBuilder();
        sb.append("SPL::handling entity visit intent for title: ");
        sb.append((Object) (entityVisitIntentInfo == null ? null : entityVisitIntentInfo.getTitle()));
        sb.append(", entityId: ");
        sb.append((Object) (entityVisitIntentInfo == null ? null : entityVisitIntentInfo.getEntityId()));
        sb.append(", entityTypeId: ");
        sb.append(entityVisitIntentInfo == null ? null : entityVisitIntentInfo.getEntityIdType());
        sb.append(", entityType: ");
        sb.append(entityVisitIntentInfo == null ? null : entityVisitIntentInfo.getEntityType());
        logger2.debug(sb.toString());
        if (entityVisitIntentInfo == null) {
            logger2.warn("SPL::Not processing invalid entity visit intent.");
        } else {
            getEntityTrackingService().trackEntity(null, entityVisitIntentInfo, System.currentTimeMillis()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.intents.EntityTrackingIntentService$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EntityTrackingIntentService.m2035handleEntityVisitIntent$lambda2$lambda0(entityVisitIntentInfo, (Unit) obj);
                }
            }, new Consumer() { // from class: com.amazon.music.find.intents.EntityTrackingIntentService$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EntityTrackingIntentService.m2036handleEntityVisitIntent$lambda2$lambda1(entityVisitIntentInfo, (Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEntityVisitIntent$lambda-2$lambda-0, reason: not valid java name */
    public static final void m2035handleEntityVisitIntent$lambda2$lambda0(EntityVisitIntentInfo entityVisitIntentInfo, Unit unit) {
        logger.debug("SPL::put search suggestion in table for title: " + entityVisitIntentInfo.getTitle() + ", entityId: " + entityVisitIntentInfo.getEntityId() + ", entityTypeId: " + entityVisitIntentInfo.getEntityIdType() + ", entityType: " + entityVisitIntentInfo.getEntityType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEntityVisitIntent$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2036handleEntityVisitIntent$lambda2$lambda1(EntityVisitIntentInfo entityVisitIntentInfo, Throwable th) {
        logger.debug("SPL::put search suggestion in table for title failed: " + entityVisitIntentInfo.getTitle() + ", entityId: " + entityVisitIntentInfo.getEntityId() + ", entityTypeId: " + entityVisitIntentInfo.getEntityIdType() + ", entityType: " + entityVisitIntentInfo.getEntityType());
    }

    private final void handleEntityRefreshIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        Parcelable parcelable = extras.getParcelable(EntityTrackingIntent.INSTANCE.getEXTRA_ENTITY_REFRESH_INFO());
        final EntityRefreshIntentInfo entityRefreshIntentInfo = parcelable instanceof EntityRefreshIntentInfo ? (EntityRefreshIntentInfo) parcelable : null;
        Logger logger2 = logger;
        logger2.debug(Intrinsics.stringPlus("SPL::handling entity refresh intent for ", entityRefreshIntentInfo));
        if (entityRefreshIntentInfo == null) {
            logger2.warn("SPL::Not processing invalid entity refresh intent.");
        } else {
            getEntityTrackingService().refreshEntityLastAccessTime(null, entityRefreshIntentInfo, System.currentTimeMillis()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.intents.EntityTrackingIntentService$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EntityTrackingIntentService.m2033handleEntityRefreshIntent$lambda5$lambda3(entityRefreshIntentInfo, (Unit) obj);
                }
            }, new Consumer() { // from class: com.amazon.music.find.intents.EntityTrackingIntentService$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EntityTrackingIntentService.m2034handleEntityRefreshIntent$lambda5$lambda4(entityRefreshIntentInfo, (Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEntityRefreshIntent$lambda-5$lambda-3, reason: not valid java name */
    public static final void m2033handleEntityRefreshIntent$lambda5$lambda3(EntityRefreshIntentInfo entityRefreshIntentInfo, Unit unit) {
        logger.debug(Intrinsics.stringPlus("SPL::update last access time for ", entityRefreshIntentInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEntityRefreshIntent$lambda-5$lambda-4, reason: not valid java name */
    public static final void m2034handleEntityRefreshIntent$lambda5$lambda4(EntityRefreshIntentInfo entityRefreshIntentInfo, Throwable th) {
        logger.debug(Intrinsics.stringPlus("SPL::update last access time for ", entityRefreshIntentInfo));
    }
}
