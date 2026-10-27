package com.amazon.podcast.metrics.ui;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "ui_metric_operations")
public class UiMetricOperations {

    @NonNull
    @ColumnInfo(name = "actionType")
    private String actionType;

    @ColumnInfo(name = "containerIndex")
    private int containerIndex;

    @NonNull
    @ColumnInfo(name = "containerInstId")
    private String containerInstId;

    @NonNull
    @ColumnInfo(name = "containerType")
    private String containerType;

    @NonNull
    @ColumnInfo(name = "contentName")
    private String contentName;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.CONTENT_SRC)
    private String contentSrc;

    @NonNull
    @ColumnInfo(name = "detailPageItemId")
    private String detailPageItemId;

    @NonNull
    @ColumnInfo(name = "detailPageItemIdType")
    private String detailPageItemIdType;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.ENTITY_ID)
    private String entityId;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.ENTITY_ID_TYPE)
    private String entityIdType;

    @ColumnInfo(name = "entityPos")
    private int entityPos;

    @NonNull
    @ColumnInfo(name = PageUriUtils.ENTITY_TYPE_QUERY_PARAM)
    private String entityType;

    @ColumnInfo(name = "firstViewableIndex")
    private int firstViewableIndex;

    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "interactionType")
    private String interactionType;

    @ColumnInfo(name = "isProcessed")
    private boolean isProcessed;

    @ColumnInfo(name = "lastViewableIndex")
    private int lastViewableIndex;

    @NonNull
    @ColumnInfo(name = "metricsPreset")
    private String metricsPreset;

    @ColumnInfo(name = "numItemsAccessible")
    private int numItemsAccessible;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "operationId")
    private String operationId;

    @NonNull
    @ColumnInfo(name = "pageType")
    private String pageType;

    @NonNull
    @ColumnInfo(name = "refMarker")
    private String refMarker;

    @NonNull
    @ColumnInfo(name = "signalType")
    private String signalType;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public UiMetricOperations(@NonNull final String operationId, @NonNull final String id, @NonNull final String signalType, @NonNull final String metricsPreset, @NonNull final String pageType, @NonNull final String contentSrc, @NonNull final String containerType, @NonNull final String entityType, @NonNull final String entityIdType, @NonNull final String entityId, @NonNull final String detailPageItemId, @NonNull final String detailPageItemIdType, @NonNull final String refMarker, @NonNull final String actionType, @NonNull final String interactionType, @NonNull final String contentName, final int containerIndex, final int entityPos, final int firstViewableIndex, final int lastViewableIndex, final long updatedTime, final boolean isProcessed, final int numItemsAccessible, @NonNull final String containerInstId) {
        this.operationId = operationId;
        this.id = id;
        this.signalType = signalType;
        this.metricsPreset = metricsPreset;
        this.pageType = pageType;
        this.contentSrc = contentSrc;
        this.containerType = containerType;
        this.entityType = entityType;
        this.entityIdType = entityIdType;
        this.entityId = entityId;
        this.detailPageItemId = detailPageItemId;
        this.detailPageItemIdType = detailPageItemIdType;
        this.refMarker = refMarker;
        this.actionType = actionType;
        this.interactionType = interactionType;
        this.contentName = contentName;
        this.containerIndex = containerIndex;
        this.entityPos = entityPos;
        this.firstViewableIndex = firstViewableIndex;
        this.lastViewableIndex = lastViewableIndex;
        this.updatedTime = updatedTime;
        this.isProcessed = isProcessed;
        this.numItemsAccessible = numItemsAccessible;
        this.containerInstId = containerInstId;
    }

    @NonNull
    public String getOperationId() {
        return this.operationId;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getSignalType() {
        return this.signalType;
    }

    @NonNull
    public String getMetricsPreset() {
        return this.metricsPreset;
    }

    @NonNull
    public String getPageType() {
        return this.pageType;
    }

    @NonNull
    public String getContentSrc() {
        return this.contentSrc;
    }

    @NonNull
    public String getContainerType() {
        return this.containerType;
    }

    @NonNull
    public String getEntityType() {
        return this.entityType;
    }

    @NonNull
    public String getEntityIdType() {
        return this.entityIdType;
    }

    @NonNull
    public String getEntityId() {
        return this.entityId;
    }

    @NonNull
    public String getDetailPageItemId() {
        return this.detailPageItemId;
    }

    @NonNull
    public String getDetailPageItemIdType() {
        return this.detailPageItemIdType;
    }

    @NonNull
    public String getRefMarker() {
        return this.refMarker;
    }

    @NonNull
    public String getActionType() {
        return this.actionType;
    }

    @NonNull
    public String getInteractionType() {
        return this.interactionType;
    }

    @NonNull
    public String getContentName() {
        return this.contentName;
    }

    public int getContainerIndex() {
        return this.containerIndex;
    }

    public int getEntityPos() {
        return this.entityPos;
    }

    public int getFirstViewableIndex() {
        return this.firstViewableIndex;
    }

    public int getLastViewableIndex() {
        return this.lastViewableIndex;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    public boolean isProcessed() {
        return this.isProcessed;
    }

    public int getNumItemsAccessible() {
        return this.numItemsAccessible;
    }

    @NonNull
    public String getContainerInstId() {
        return this.containerInstId;
    }
}
