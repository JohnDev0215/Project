package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import Touch.SyncInterface.v1_0.ImmutableSetWidgetSyncElementsMethod;
import Touch.SyncInterface.v1_0.SetWidgetSyncElementsMethod;
import Touch.SyncInterface.v1_0.WidgetSyncElement;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.skyfire.Mappers;
import com.amazon.music.curate.skyfire.core.MethodEngine;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SyncEngine implements MethodCallback<Method>, MethodsDispatcher<Method> {
    private static final String TAG = "SyncEngine";
    private static final Logger logger = LoggerFactory.getLogger(SyncEngine.class.getSimpleName());
    private final AppDatabase appDatabase;
    private final MethodEngine engine;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener<Method> listener) {
        executeMethod2(method, ownerId, (OnMethodExecutedListener) listener);
    }

    public SyncEngine(final MethodEngine engine, final AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableSetWidgetSyncElementsMethod.class.getCanonicalName());
        engine.registerMethods(hashSet, Collections.emptySet(), this);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetWidgetSyncElementsMethod) {
            SetWidgetSyncElementsMethod setWidgetSyncElementsMethod = (SetWidgetSyncElementsMethod) method;
            List<WidgetSyncElement> listElements = setWidgetSyncElementsMethod.elements();
            if (listElements != null && !listElements.isEmpty()) {
                saveLibraryContextMappings(setWidgetSyncElementsMethod);
                logger.debug("Saved sync response to local db");
            } else {
                logger.warn("Empty sync response");
            }
        }
        listener.onMethodExecuted(method);
    }

    @Override // com.amazon.music.skyfire.platform.MethodsDispatcher
    public void dispatchMethods(final String ownerId, final List<? extends Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    private void saveLibraryContextMappings(final SetWidgetSyncElementsMethod method) {
        try {
            LibraryContextTableRepository.saveMappingsToDB(this.appDatabase, Mappers.mapper().writeValueAsString(method));
            SyncScheduler.saveContextMappingTableLastSync(new Date().getTime());
        } catch (IOException e) {
            logger.error("Error in saveLibraryContextMappings", (Throwable) e);
        }
    }
}
