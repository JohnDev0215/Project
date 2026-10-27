package com.amazon.music.curate.skyfire;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.TemplateLaunchMode;
import DefaultTemplateInterface.v1_0.ImmutableDefaultTemplate;
import QueuesInterface.v1_0.ImmutableSingleThreadedQueue;
import TemplateContainerInterface.v1_0.ImmutableCreateManagedContainerMethod;
import TemplateListInterface.v1_0.ImmutableRemoveTemplateMethod;
import android.content.Context;
import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.EndpointSettingsProvider;
import com.amazon.music.curate.provider.InternalProvider;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.local.LibraryContextTableRepository;
import com.amazon.music.curate.skyfire.local.ShowLibraryHomeSkill;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.music.curate.skyfire.network.FlexEventMetrics;
import com.amazon.music.curate.skyfire.network.InvokeHttpSkillMethod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateMethods {
    private static final String LIBRARY_HOME_SKILL_PATH = "/showLibraryHomeMobile";
    private static final String RECS_WIDGET_SKILL_BASE_PATH = "/showPlaylistRecommendations?playlistId=";
    private static final String TAG = "CurateMethods";
    private static final Logger logger = LoggerFactory.getLogger(CurateMethods.class.getSimpleName());

    static List<Method> onStart(final Context context) {
        logger.debug("App start");
        resetLastSyncInBackground();
        return Collections.emptyList();
    }

    static List<Method> onUserSignedIn(final Context context) {
        logger.debug("New account, reset last sync time");
        resetLastSyncInBackground();
        return Collections.emptyList();
    }

    static void clearAppState(final Context context) {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.CurateMethods.1
            @Override // java.lang.Runnable
            public void run() {
                CurateMethods.resetDatabaseInBackground();
                SyncScheduler.resetContextMappingTableLastSync();
                CurateMethods.logger.debug("Clear all curate states and local data");
            }
        });
    }

    static List<Method> onUserSignedOut(final Context context) {
        clearAppState(context);
        logger.debug("Account removed, clear all tables");
        return Collections.emptyList();
    }

    static List<Method> onDeviceConfigurationChanged(final Context context) {
        return new ArrayList();
    }

    static List<Method> onLibraryFrontPageOpened(final Context context, final AppDatabase appDatabase, final CurateBootstrapProviders curateBootstrapProviders) {
        EndpointSettingsProvider endpointSettingsProvider = curateBootstrapProviders.getEndpointSettingsProvider();
        String currentOverrideId = endpointSettingsProvider.getCurrentOverrideId();
        String marketPlaceId = endpointSettingsProvider.getMarketPlaceId(context);
        boolean zIsContextMappingEmpty = SyncScheduler.isContextMappingEmpty(appDatabase);
        boolean zIsConnected = curateBootstrapProviders.getNetworkProvider().isConnected();
        boolean zShouldSyncContextMappingTable = SyncScheduler.shouldSyncContextMappingTable();
        Logger logger2 = logger;
        logger2.debug("isInitialState = {}, isNetworkConnected = {}, shouldSync = {}" + zIsContextMappingEmpty, Boolean.valueOf(zIsConnected), Boolean.valueOf(zShouldSyncContextMappingTable));
        if (zIsContextMappingEmpty && !zIsConnected) {
            logger2.debug("Context mapping records not found in DB, network not connected, load default asset file to DB as a fallback");
            loadDefaultTemplateToDB(context, appDatabase);
            FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_LIBRARY_LOAD_DEFAULT_TEMPLATE, "Initial State and No Network", "");
        }
        ArrayList arrayList = new ArrayList();
        if ((zIsConnected && zShouldSyncContextMappingTable) || zIsContextMappingEmpty) {
            arrayList.add(createDefaultTemplateMethod());
            arrayList.add(InvokeHttpSkillMethod.get(CurateEndpoints.url(marketPlaceId, currentOverrideId), LIBRARY_HOME_SKILL_PATH, null, onServiceError(context, appDatabase)));
            logger2.debug("Loading from service");
        } else {
            arrayList.addAll(new ShowLibraryHomeSkill(context).invoke());
            FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_LIBRARY_LOAD_DATABASE, "isNetworkConnected = " + zIsConnected + ", shouldSync = " + zShouldSyncContextMappingTable, "");
            logger2.debug("Loading from db, isNetworkConnected = {}, shouldSync ={} ", Boolean.valueOf(zIsConnected), Boolean.valueOf(zShouldSyncContextMappingTable));
        }
        return arrayList;
    }

    private static void loadDefaultTemplateToDB(final Context context, final AppDatabase appDatabase) {
        LibraryContextTableRepository.saveMappingsToDB(appDatabase, FileUtil.readAssetFile(context, ContextMappingConstants.LIBRARY_CONTEXT_MAPPING_ASSET));
    }

    static Method createDefaultTemplateMethod() {
        return ImmutableCreateManagedContainerMethod.builder().queue(templateQueue()).template(ImmutableDefaultTemplate.builder().launchMode(TemplateLaunchMode.standard).build()).build();
    }

    static List<Method> fetchPlaylistRecommendations(final Context context, final CurateBootstrapProviders curateBootstrapProviders, final String playlistId) {
        boolean zIsConnected = curateBootstrapProviders.getNetworkProvider().isConnected();
        ArrayList arrayList = new ArrayList();
        if (!zIsConnected) {
            logger.debug("Network not connected, cannot fetch Playlist Recommendations");
            return arrayList;
        }
        EndpointSettingsProvider endpointSettingsProvider = curateBootstrapProviders.getEndpointSettingsProvider();
        String currentOverrideId = endpointSettingsProvider.getCurrentOverrideId();
        arrayList.add(InvokeHttpSkillMethod.get(CurateEndpoints.url(endpointSettingsProvider.getMarketPlaceId(context), currentOverrideId), RECS_WIDGET_SKILL_BASE_PATH + playlistId, null));
        return arrayList;
    }

    private static List<Method> onServiceError(final Context context, final AppDatabase appDatabase) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ImmutableRemoveTemplateMethod.builder().queue(templateQueue()).build());
        if (SyncScheduler.isContextMappingEmpty(appDatabase)) {
            logger.debug("Mapping records not found in DB, if not receiving response from Skyfire, load default asset file to DB as a fallback");
            loadDefaultTemplateToDB(context, appDatabase);
            FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_LIBRARY_LOAD_DEFAULT_TEMPLATE, "No data found in DB, load default template", "");
        }
        arrayList.addAll(new ShowLibraryHomeSkill(context).invoke());
        return arrayList;
    }

    private static Queue templateQueue() {
        return ImmutableSingleThreadedQueue.builder().id("TEMPLATE").build();
    }

    private static Queue bootstrapQueue() {
        return ImmutableSingleThreadedQueue.builder().id("BOOTSTRAP").build();
    }

    private static void resetLastSyncInBackground() {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.CurateMethods.2
            @Override // java.lang.Runnable
            public void run() {
                SyncScheduler.resetContextMappingTableLastSync();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resetDatabaseInBackground() {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.CurateMethods.3
            @Override // java.lang.Runnable
            public void run() {
                InternalProvider internalProvider = InternalProvider.Singleton.INSTANCE.get();
                AppDatabase database = internalProvider == null ? null : internalProvider.getDatabase();
                if (database != null) {
                    database.clearAllTables();
                }
                CurateMethods.logger.debug("Account removed, clear all tables");
            }
        });
    }
}
