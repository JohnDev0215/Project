package com.amazon.avod.media.events.dagger;

import android.content.Context;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.json.JacksonJsonFactoryCache;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.BatchDispatcherListener;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.PersistentMediaEventQueue;
import com.amazon.avod.media.events.clientapi.BoltMediaReportClient;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import com.amazon.avod.media.events.dao.MediaEventDAO;
import com.amazon.avod.media.events.dao.MediaEventsDatabase;
import com.amazon.avod.media.events.dao.MediaEventsDatabaseNoOpUpgradeManager;
import com.amazon.avod.media.events.dao.MediaReportDAO;
import com.amazon.avod.media.events.dao.SQLiteMediaEventDAO;
import com.amazon.avod.media.events.dao.SQLiteMediaReportDAO;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventModule_Dagger {
    public ScheduledExecutorService provideScheduledExecutorService() {
        return Executors.newScheduledThreadPool(2);
    }

    public DBOpenHelperSupplier provideDBOpenHelperSupplier(Context context) {
        return new DBOpenHelperSupplier(context.getApplicationContext(), new MediaEventsDatabaseNoOpUpgradeManager(context.getApplicationContext()), MediaEventsDatabase.getInstance());
    }

    public MediaEventDAO provideMediaEventDAO(DBOpenHelperSupplier dBOpenHelperSupplier) {
        return new SQLiteMediaEventDAO(dBOpenHelperSupplier);
    }

    public MediaReportDAO provideMediaReportDAO(DBOpenHelperSupplier dBOpenHelperSupplier, Context context) {
        return new SQLiteMediaReportDAO(dBOpenHelperSupplier, AloysiusConfig.getInstance(), context);
    }

    public MediaEventQueue provideMediaEventQueue(MediaEventDAO mediaEventDAO) {
        return new PersistentMediaEventQueue(mediaEventDAO, new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE));
    }

    public BatchDispatcherListener provideBatchDispatcherListener(Set<MediaEventProducer> set) {
        return new BatchDispatcherListener(set);
    }

    public MediaEventDispatcher provideMediaEventDispatcher(MediaEventQueue mediaEventQueue, MediaReportDAO mediaReportDAO, MediaReportDispatcherServices mediaReportDispatcherServices, ScheduledExecutorService scheduledExecutorService, QosEventReporterFactory qosEventReporterFactory, BatchDispatcherListener batchDispatcherListener) {
        return new MediaEventDispatcher(mediaEventQueue, mediaReportDAO, mediaReportDispatcherServices, scheduledExecutorService, qosEventReporterFactory, batchDispatcherListener);
    }

    public MediaReportDispatcherServices provideMediaReportDispatcherServices(MediaReportClient mediaReportClient) {
        return new MediaReportDispatcherServices(mediaReportClient, new MediaReportDispatcherServices.MediaReportDispatcherServicesConfig(), NetworkConnectionManager.getInstance());
    }

    public MediaReportClient provideMediaReportClient() {
        return new BoltMediaReportClient(new BoltMediaReportClient.PlaybackHttpRequestBuilderFactory(), JacksonJsonFactoryCache.JSON_FACTORY, ServiceClient.getInstance(), new BoltMediaReportClient.MediaReportClientConfig());
    }

    public AloysiusBootstrapLoader provideAppBootstrapLoader(MediaReportClient mediaReportClient, MediaEventDispatcher mediaEventDispatcher, MediaReportDispatcherServices mediaReportDispatcherServices, ScheduledExecutorService scheduledExecutorService) {
        return new AloysiusBootstrapLoader(mediaReportClient, mediaEventDispatcher, mediaReportDispatcherServices, AloysiusConfig.getInstance(), AloysiusBootstrapLoader.AloysiusBootstrapLoaderConfig.getInstance(), scheduledExecutorService);
    }
}
