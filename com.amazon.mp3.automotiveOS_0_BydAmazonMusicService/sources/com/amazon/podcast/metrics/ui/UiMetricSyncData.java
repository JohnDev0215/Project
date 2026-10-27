package com.amazon.podcast.metrics.ui;

import Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethod;
import Podcast.UIMetricsInterface.v1_0.UIMetricWriteElement;
import Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientState;
import SOAAppSyncInterface.v1_0.ConditionElement;
import SOAAppSyncInterface.v1_0.OperationElement;
import SOAAppSyncInterface.v1_0.TimeConditionElement;
import SOAAppSyncInterface.v1_0.WriteElement;
import SOACoreInterface.v1_0.Method;
import UIMetricsInterface.v1_0.UISignalType;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.appsync.QueryParameters;
import com.amazon.podcast.appsync.SyncData;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewCacheItem;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.podcast.views.Strings;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class UiMetricSyncData extends SyncData<UIMetricWriteElement, WriteElement, Void, QueryParameters> implements MethodCallback {
    private final Context context;
    private final UiMetricOperationsDao uiMetricOperationsDao;
    private final Logger logger = LoggerFactory.getLogger("UiMetricSyncData");
    private final UIContentViewWidgetIndexCache contentViewedCache = UIContentViewWidgetIndexCache.INSTANCE;

    private Integer checkForNullIntOrDefault(Integer val, Integer defaultVal) {
        return val == null ? defaultVal : val;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public Void read(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<Void> readState(final String id) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Void>> readStates() {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public LiveData<List<Void>> readStates(final Set<String> ids) {
        return null;
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeMetadata(final WriteElement writeElement) {
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public boolean writeState(final UIMetricWriteElement element, final ConditionElement conditionElement, final long updatedTime) {
        return true;
    }

    public UiMetricSyncData(final Engine engine, final String ownerId, final Context context, final PodcastRoomDatabase database) {
        this.context = context;
        this.uiMetricOperationsDao = database.uiMetricOperationsDao();
        engine.registerOwner(ownerId);
        HashSet hashSet = new HashSet();
        hashSet.add(SetOnReportOperationsMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void writeToOperations(final UIMetricWriteElement element, final long updatedTime) {
        try {
            if (UISignalType.UI_CONTENT_VIEW.equals(element.getSignalType())) {
                writeUiContentViewToOperation(element, updatedTime);
            } else {
                this.uiMetricOperationsDao.insert(new UiMetricOperations(UUID.randomUUID().toString(), Strings.checkNullString(element.getId()), Strings.checkNullString(element.getSignalType()), Strings.checkNullString(element.getMetricsPreset()), Strings.checkNullString(element.getPageType()), Strings.checkNullString(element.getContentSrc()), Strings.checkNullString(element.getContainerType()), Strings.checkNullString(element.getEntityType()), Strings.checkNullString(element.getEntityIdType()), Strings.checkNullString(element.getEntityId()), Strings.checkNullString(element.getDetailPageItemId()), Strings.checkNullString(element.getDetailPageItemIdType()), Strings.checkNullString(element.getRefMarker()), Strings.checkNullString(element.getActionType()), Strings.checkNullString(element.getInteractionType()), Strings.checkNullString(element.getContentName()), checkForNullInt(element.getContainerIndex()).intValue(), checkForNullInt(element.getEntityPos()).intValue(), checkForNullInt(element.getFirstViewableIndex()).intValue(), checkForNullInt(element.getLastViewableIndex()).intValue(), updatedTime, false, checkForNullInt(element.getNumItemsAccessible()).intValue(), Strings.checkNullString(element.getContainerInstId())));
            }
        } catch (Exception e) {
            this.logger.error("Error in writeToOperations method", (Throwable) e);
        }
    }

    private void writeUiContentViewToOperation(final UIMetricWriteElement element, final long updatedTime) {
        String containerInstId = element.getContainerInstId();
        if (shouldExcludeReporting(containerInstId)) {
            return;
        }
        UIContentViewCacheItem widgetCacheItem = getWidgetCacheItem(containerInstId);
        this.uiMetricOperationsDao.insert(new UiMetricOperations(UUID.randomUUID().toString(), Strings.checkNullString(element.getId()), Strings.checkNullString(element.getSignalType()), Strings.checkNullString(element.getMetricsPreset()), Strings.checkNullString(element.getPageType()), Strings.checkNullString(element.getContentSrc()), Strings.checkNullString(element.getContainerType()), Strings.checkNullString(element.getEntityType()), Strings.checkNullString(element.getEntityIdType()), Strings.checkNullString(element.getEntityId()), Strings.checkNullString(element.getDetailPageItemId()), Strings.checkNullString(element.getDetailPageItemIdType()), Strings.checkNullString(element.getRefMarker()), Strings.checkNullString(element.getActionType()), Strings.checkNullString(element.getInteractionType()), Strings.checkNullString(element.getContentName()), checkForNullInt(element.getContainerIndex()).intValue(), checkForNullInt(element.getEntityPos()).intValue(), checkForNullIntOrDefault(element.getFirstViewableIndex(), Integer.valueOf(widgetCacheItem.getFirstViewableIndex())).intValue(), checkForNullIntOrDefault(element.getLastViewableIndex(), Integer.valueOf(widgetCacheItem.getLastViewableIndex())).intValue(), updatedTime, false, checkForNullIntOrDefault(element.getNumItemsAccessible(), Integer.valueOf(widgetCacheItem.getNumItemsAccessible())).intValue(), Strings.checkNullString(containerInstId)));
        updateReportedIndices(containerInstId, widgetCacheItem.getFirstBatchOfUnreportedIndices());
    }

    private boolean shouldExcludeReporting(final String widgetId) {
        UIContentViewCacheItem widget = this.contentViewedCache.getWidget(widgetId);
        if (widget == null) {
            return false;
        }
        return widget.getFirstBatchOfUnreportedIndices().isEmpty();
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public List<Method> reportOperationMethods() {
        return readReportOperationsMethods(this.context);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public ClientStates.Entry getClientState(final String name) {
        if (!name.equals(UIMetricsOperationsClientState.class.getCanonicalName())) {
            return null;
        }
        List<UiMetricOperations> unProcessed = this.uiMetricOperationsDao.getUnProcessed();
        UIMetricsOperationsClientState uIMetricsOperationsClientStateConvertOperationsToClientState = convertOperationsToClientState(unProcessed);
        ArrayList arrayList = new ArrayList();
        Iterator<UiMetricOperations> it = unProcessed.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getOperationId());
        }
        this.uiMetricOperationsDao.updateToProcessing(arrayList);
        return ClientStates.Entry.create("uiMetric", uIMetricsOperationsClientStateConvertOperationsToClientState);
    }

    @Override // com.amazon.podcast.appsync.SyncData
    public void deleteOperations(final List<String> ids) {
        this.uiMetricOperationsDao.deleteIds(ids);
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof SetOnReportOperationsMethod) {
            saveReportOperations(this.context, (SetOnReportOperationsMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    public static void clearData(final Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("UiMetricSync", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private UIContentViewCacheItem getWidgetCacheItem(final String widgetId) {
        if (StringUtils.isBlank(widgetId)) {
            return new UIContentViewCacheItem();
        }
        UIContentViewCacheItem widget = this.contentViewedCache.getWidget(widgetId);
        return widget == null ? new UIContentViewCacheItem() : widget;
    }

    private void updateReportedIndices(final String widgetId, final Set<Integer> indices) {
        if (StringUtils.isBlank(widgetId)) {
            return;
        }
        this.contentViewedCache.updateReportedIndices(widgetId, Lists.newArrayList(indices));
    }

    private List<Method> readReportOperationsMethods(final Context context) {
        String string = getSharedPreferences(context).getString("onReportOperations", null);
        if (string == null) {
            return Collections.emptyList();
        }
        try {
            return ((SetOnReportOperationsMethod) Mappers.mapper().readValue(string, SetOnReportOperationsMethod.class)).getOnReportOperations();
        } catch (IOException e) {
            this.logger.error("Error in readReportOperationsMethods method", (Throwable) e);
            return Collections.emptyList();
        }
    }

    private Integer checkForNullInt(Integer val) {
        return Integer.valueOf(val == null ? -1 : val.intValue());
    }

    private SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("UiMetricSync", 0);
    }

    private UIMetricsOperationsClientState convertOperationsToClientState(final List<UiMetricOperations> Operations) {
        ArrayList arrayList = new ArrayList();
        for (UiMetricOperations uiMetricOperations : Operations) {
            UIMetricWriteElement uIMetricWriteElementConvertOperationsToUiMetricWriteElement = convertOperationsToUiMetricWriteElement(uiMetricOperations);
            arrayList.add(OperationElement.builder().withId(uiMetricOperations.getOperationId()).withElement(uIMetricWriteElementConvertOperationsToUiMetricWriteElement).withCondition(TimeConditionElement.builder().withUpdatedTime(uiMetricOperations.getUpdatedTime()).build()).build());
        }
        return UIMetricsOperationsClientState.builder().withOperations(arrayList).build();
    }

    private UIMetricWriteElement convertOperationsToUiMetricWriteElement(final UiMetricOperations operation) {
        return UIMetricWriteElement.builder().withId(operation.getOperationId()).withSignalType(operation.getSignalType()).withMetricsPreset(operation.getMetricsPreset()).withPageType(operation.getPageType()).withContentSrc(operation.getContentSrc()).withContainerIndex(Integer.valueOf(operation.getContainerIndex())).withContainerType(operation.getContainerType()).withEntityType(operation.getEntityType()).withEntityIdType(operation.getEntityIdType()).withEntityId(operation.getEntityId()).withEntityPos(Integer.valueOf(operation.getEntityPos())).withDetailPageItemId(operation.getDetailPageItemId()).withDetailPageItemIdType(operation.getDetailPageItemIdType()).withRefMarker(operation.getRefMarker()).withActionType(operation.getActionType()).withInteractionType(operation.getInteractionType()).withContentName(operation.getContentName()).withFirstViewableIndex(Integer.valueOf(operation.getFirstViewableIndex())).withLastViewableIndex(Integer.valueOf(operation.getLastViewableIndex())).withNumItemsAccessible(Integer.valueOf(operation.getNumItemsAccessible())).withContainerInstId(operation.getContainerInstId()).build();
    }

    private void saveReportOperations(final Context context, final SetOnReportOperationsMethod method) {
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(method);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("UiMetricSync", 0).edit();
            editorEdit.putString("onReportOperations", strWriteValueAsString);
            editorEdit.apply();
        } catch (IOException e) {
            this.logger.error("Error in saveReportOperations method", (Throwable) e);
        }
    }
}
