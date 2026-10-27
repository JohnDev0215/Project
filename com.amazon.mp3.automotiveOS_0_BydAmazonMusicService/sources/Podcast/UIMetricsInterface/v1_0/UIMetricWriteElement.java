package Podcast.UIMetricsInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class UIMetricWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.UIMetricsInterface.v1_0.UIMetricWriteElement");
    private String actionType;
    private Integer containerIndex;
    private String containerInstId;
    private String containerType;
    private String contentName;
    private String contentSrc;
    private String detailPageItemId;
    private String detailPageItemIdType;
    private String entityId;
    private String entityIdType;
    private Integer entityPos;
    private String entityType;
    private Integer firstViewableIndex;
    private String interactionType;
    private Integer lastViewableIndex;
    private String metricsPreset;
    private Integer numItemsAccessible;
    private String pageType;
    private String refMarker;
    private String referer;
    private String signalType;

    public static class Builder extends WriteElement.Builder {
        protected String actionType;
        protected Integer containerIndex;
        protected String containerInstId;
        protected String containerType;
        protected String contentName;
        protected String contentSrc;
        protected String detailPageItemId;
        protected String detailPageItemIdType;
        protected String entityId;
        protected String entityIdType;
        protected Integer entityPos;
        protected String entityType;
        protected Integer firstViewableIndex;
        protected String interactionType;
        protected Integer lastViewableIndex;
        protected String metricsPreset;
        protected Integer numItemsAccessible;
        protected String pageType;
        protected String refMarker;
        protected String referer;
        protected String signalType;

        public UIMetricWriteElement build() {
            UIMetricWriteElement uIMetricWriteElement = new UIMetricWriteElement();
            populate(uIMetricWriteElement);
            return uIMetricWriteElement;
        }

        protected void populate(UIMetricWriteElement uIMetricWriteElement) {
            super.populate((WriteElement) uIMetricWriteElement);
            uIMetricWriteElement.setSignalType(this.signalType);
            uIMetricWriteElement.setContentSrc(this.contentSrc);
            uIMetricWriteElement.setFirstViewableIndex(this.firstViewableIndex);
            uIMetricWriteElement.setNumItemsAccessible(this.numItemsAccessible);
            uIMetricWriteElement.setContainerInstId(this.containerInstId);
            uIMetricWriteElement.setDetailPageItemIdType(this.detailPageItemIdType);
            uIMetricWriteElement.setEntityPos(this.entityPos);
            uIMetricWriteElement.setActionType(this.actionType);
            uIMetricWriteElement.setDetailPageItemId(this.detailPageItemId);
            uIMetricWriteElement.setRefMarker(this.refMarker);
            uIMetricWriteElement.setEntityType(this.entityType);
            uIMetricWriteElement.setLastViewableIndex(this.lastViewableIndex);
            uIMetricWriteElement.setContainerIndex(this.containerIndex);
            uIMetricWriteElement.setEntityIdType(this.entityIdType);
            uIMetricWriteElement.setContainerType(this.containerType);
            uIMetricWriteElement.setEntityId(this.entityId);
            uIMetricWriteElement.setReferer(this.referer);
            uIMetricWriteElement.setPageType(this.pageType);
            uIMetricWriteElement.setContentName(this.contentName);
            uIMetricWriteElement.setInteractionType(this.interactionType);
            uIMetricWriteElement.setMetricsPreset(this.metricsPreset);
        }

        public Builder withActionType(String str) {
            this.actionType = str;
            return this;
        }

        public Builder withContainerIndex(Integer num) {
            this.containerIndex = num;
            return this;
        }

        public Builder withContainerInstId(String str) {
            this.containerInstId = str;
            return this;
        }

        public Builder withContainerType(String str) {
            this.containerType = str;
            return this;
        }

        public Builder withContentName(String str) {
            this.contentName = str;
            return this;
        }

        public Builder withContentSrc(String str) {
            this.contentSrc = str;
            return this;
        }

        public Builder withDetailPageItemId(String str) {
            this.detailPageItemId = str;
            return this;
        }

        public Builder withDetailPageItemIdType(String str) {
            this.detailPageItemIdType = str;
            return this;
        }

        public Builder withEntityId(String str) {
            this.entityId = str;
            return this;
        }

        public Builder withEntityIdType(String str) {
            this.entityIdType = str;
            return this;
        }

        public Builder withEntityPos(Integer num) {
            this.entityPos = num;
            return this;
        }

        public Builder withEntityType(String str) {
            this.entityType = str;
            return this;
        }

        public Builder withFirstViewableIndex(Integer num) {
            this.firstViewableIndex = num;
            return this;
        }

        @Override // SOAAppSyncInterface.v1_0.WriteElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withInteractionType(String str) {
            this.interactionType = str;
            return this;
        }

        public Builder withLastViewableIndex(Integer num) {
            this.lastViewableIndex = num;
            return this;
        }

        public Builder withMetricsPreset(String str) {
            this.metricsPreset = str;
            return this;
        }

        public Builder withNumItemsAccessible(Integer num) {
            this.numItemsAccessible = num;
            return this;
        }

        public Builder withPageType(String str) {
            this.pageType = str;
            return this;
        }

        public Builder withRefMarker(String str) {
            this.refMarker = str;
            return this;
        }

        public Builder withReferer(String str) {
            this.referer = str;
            return this;
        }

        public Builder withSignalType(String str) {
            this.signalType = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof UIMetricWriteElement)) {
            return 1;
        }
        UIMetricWriteElement uIMetricWriteElement = (UIMetricWriteElement) writeElement;
        String signalType = getSignalType();
        String signalType2 = uIMetricWriteElement.getSignalType();
        if (signalType != signalType2) {
            if (signalType == null) {
                return -1;
            }
            if (signalType2 == null) {
                return 1;
            }
            int iCompareTo = signalType.compareTo(signalType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String contentSrc = getContentSrc();
        String contentSrc2 = uIMetricWriteElement.getContentSrc();
        if (contentSrc != contentSrc2) {
            if (contentSrc == null) {
                return -1;
            }
            if (contentSrc2 == null) {
                return 1;
            }
            int iCompareTo2 = contentSrc.compareTo(contentSrc2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer firstViewableIndex = getFirstViewableIndex();
        Integer firstViewableIndex2 = uIMetricWriteElement.getFirstViewableIndex();
        if (firstViewableIndex != firstViewableIndex2) {
            if (firstViewableIndex == null) {
                return -1;
            }
            if (firstViewableIndex2 == null) {
                return 1;
            }
            int iCompareTo3 = firstViewableIndex.compareTo(firstViewableIndex2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer numItemsAccessible = getNumItemsAccessible();
        Integer numItemsAccessible2 = uIMetricWriteElement.getNumItemsAccessible();
        if (numItemsAccessible != numItemsAccessible2) {
            if (numItemsAccessible == null) {
                return -1;
            }
            if (numItemsAccessible2 == null) {
                return 1;
            }
            int iCompareTo4 = numItemsAccessible.compareTo(numItemsAccessible2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String containerInstId = getContainerInstId();
        String containerInstId2 = uIMetricWriteElement.getContainerInstId();
        if (containerInstId != containerInstId2) {
            if (containerInstId == null) {
                return -1;
            }
            if (containerInstId2 == null) {
                return 1;
            }
            int iCompareTo5 = containerInstId.compareTo(containerInstId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String detailPageItemIdType = getDetailPageItemIdType();
        String detailPageItemIdType2 = uIMetricWriteElement.getDetailPageItemIdType();
        if (detailPageItemIdType != detailPageItemIdType2) {
            if (detailPageItemIdType == null) {
                return -1;
            }
            if (detailPageItemIdType2 == null) {
                return 1;
            }
            int iCompareTo6 = detailPageItemIdType.compareTo(detailPageItemIdType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Integer entityPos = getEntityPos();
        Integer entityPos2 = uIMetricWriteElement.getEntityPos();
        if (entityPos != entityPos2) {
            if (entityPos == null) {
                return -1;
            }
            if (entityPos2 == null) {
                return 1;
            }
            int iCompareTo7 = entityPos.compareTo(entityPos2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String actionType = getActionType();
        String actionType2 = uIMetricWriteElement.getActionType();
        if (actionType != actionType2) {
            if (actionType == null) {
                return -1;
            }
            if (actionType2 == null) {
                return 1;
            }
            int iCompareTo8 = actionType.compareTo(actionType2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String detailPageItemId = getDetailPageItemId();
        String detailPageItemId2 = uIMetricWriteElement.getDetailPageItemId();
        if (detailPageItemId != detailPageItemId2) {
            if (detailPageItemId == null) {
                return -1;
            }
            if (detailPageItemId2 == null) {
                return 1;
            }
            int iCompareTo9 = detailPageItemId.compareTo(detailPageItemId2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String refMarker = getRefMarker();
        String refMarker2 = uIMetricWriteElement.getRefMarker();
        if (refMarker != refMarker2) {
            if (refMarker == null) {
                return -1;
            }
            if (refMarker2 == null) {
                return 1;
            }
            int iCompareTo10 = refMarker.compareTo(refMarker2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String entityType = getEntityType();
        String entityType2 = uIMetricWriteElement.getEntityType();
        if (entityType != entityType2) {
            if (entityType == null) {
                return -1;
            }
            if (entityType2 == null) {
                return 1;
            }
            int iCompareTo11 = entityType.compareTo(entityType2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Integer lastViewableIndex = getLastViewableIndex();
        Integer lastViewableIndex2 = uIMetricWriteElement.getLastViewableIndex();
        if (lastViewableIndex != lastViewableIndex2) {
            if (lastViewableIndex == null) {
                return -1;
            }
            if (lastViewableIndex2 == null) {
                return 1;
            }
            int iCompareTo12 = lastViewableIndex.compareTo(lastViewableIndex2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Integer containerIndex = getContainerIndex();
        Integer containerIndex2 = uIMetricWriteElement.getContainerIndex();
        if (containerIndex != containerIndex2) {
            if (containerIndex == null) {
                return -1;
            }
            if (containerIndex2 == null) {
                return 1;
            }
            int iCompareTo13 = containerIndex.compareTo(containerIndex2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String entityIdType = getEntityIdType();
        String entityIdType2 = uIMetricWriteElement.getEntityIdType();
        if (entityIdType != entityIdType2) {
            if (entityIdType == null) {
                return -1;
            }
            if (entityIdType2 == null) {
                return 1;
            }
            int iCompareTo14 = entityIdType.compareTo(entityIdType2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String containerType = getContainerType();
        String containerType2 = uIMetricWriteElement.getContainerType();
        if (containerType != containerType2) {
            if (containerType == null) {
                return -1;
            }
            if (containerType2 == null) {
                return 1;
            }
            int iCompareTo15 = containerType.compareTo(containerType2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String entityId = getEntityId();
        String entityId2 = uIMetricWriteElement.getEntityId();
        if (entityId != entityId2) {
            if (entityId == null) {
                return -1;
            }
            if (entityId2 == null) {
                return 1;
            }
            int iCompareTo16 = entityId.compareTo(entityId2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String referer = getReferer();
        String referer2 = uIMetricWriteElement.getReferer();
        if (referer != referer2) {
            if (referer == null) {
                return -1;
            }
            if (referer2 == null) {
                return 1;
            }
            int iCompareTo17 = referer.compareTo(referer2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String pageType = getPageType();
        String pageType2 = uIMetricWriteElement.getPageType();
        if (pageType != pageType2) {
            if (pageType == null) {
                return -1;
            }
            if (pageType2 == null) {
                return 1;
            }
            int iCompareTo18 = pageType.compareTo(pageType2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String contentName = getContentName();
        String contentName2 = uIMetricWriteElement.getContentName();
        if (contentName != contentName2) {
            if (contentName == null) {
                return -1;
            }
            if (contentName2 == null) {
                return 1;
            }
            int iCompareTo19 = contentName.compareTo(contentName2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String interactionType = getInteractionType();
        String interactionType2 = uIMetricWriteElement.getInteractionType();
        if (interactionType != interactionType2) {
            if (interactionType == null) {
                return -1;
            }
            if (interactionType2 == null) {
                return 1;
            }
            int iCompareTo20 = interactionType.compareTo(interactionType2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String metricsPreset = getMetricsPreset();
        String metricsPreset2 = uIMetricWriteElement.getMetricsPreset();
        if (metricsPreset != metricsPreset2) {
            if (metricsPreset == null) {
                return -1;
            }
            if (metricsPreset2 == null) {
                return 1;
            }
            int iCompareTo21 = metricsPreset.compareTo(metricsPreset2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof UIMetricWriteElement)) {
            return false;
        }
        UIMetricWriteElement uIMetricWriteElement = (UIMetricWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSignalType(), uIMetricWriteElement.getSignalType()) && internalEqualityCheck(getContentSrc(), uIMetricWriteElement.getContentSrc()) && internalEqualityCheck(getFirstViewableIndex(), uIMetricWriteElement.getFirstViewableIndex()) && internalEqualityCheck(getNumItemsAccessible(), uIMetricWriteElement.getNumItemsAccessible()) && internalEqualityCheck(getContainerInstId(), uIMetricWriteElement.getContainerInstId()) && internalEqualityCheck(getDetailPageItemIdType(), uIMetricWriteElement.getDetailPageItemIdType()) && internalEqualityCheck(getEntityPos(), uIMetricWriteElement.getEntityPos()) && internalEqualityCheck(getActionType(), uIMetricWriteElement.getActionType()) && internalEqualityCheck(getDetailPageItemId(), uIMetricWriteElement.getDetailPageItemId()) && internalEqualityCheck(getRefMarker(), uIMetricWriteElement.getRefMarker()) && internalEqualityCheck(getEntityType(), uIMetricWriteElement.getEntityType()) && internalEqualityCheck(getLastViewableIndex(), uIMetricWriteElement.getLastViewableIndex()) && internalEqualityCheck(getContainerIndex(), uIMetricWriteElement.getContainerIndex()) && internalEqualityCheck(getEntityIdType(), uIMetricWriteElement.getEntityIdType()) && internalEqualityCheck(getContainerType(), uIMetricWriteElement.getContainerType()) && internalEqualityCheck(getEntityId(), uIMetricWriteElement.getEntityId()) && internalEqualityCheck(getReferer(), uIMetricWriteElement.getReferer()) && internalEqualityCheck(getPageType(), uIMetricWriteElement.getPageType()) && internalEqualityCheck(getContentName(), uIMetricWriteElement.getContentName()) && internalEqualityCheck(getInteractionType(), uIMetricWriteElement.getInteractionType()) && internalEqualityCheck(getMetricsPreset(), uIMetricWriteElement.getMetricsPreset());
    }

    public String getActionType() {
        return this.actionType;
    }

    public Integer getContainerIndex() {
        return this.containerIndex;
    }

    public String getContainerInstId() {
        return this.containerInstId;
    }

    public String getContainerType() {
        return this.containerType;
    }

    public String getContentName() {
        return this.contentName;
    }

    public String getContentSrc() {
        return this.contentSrc;
    }

    public String getDetailPageItemId() {
        return this.detailPageItemId;
    }

    public String getDetailPageItemIdType() {
        return this.detailPageItemIdType;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getEntityIdType() {
        return this.entityIdType;
    }

    public Integer getEntityPos() {
        return this.entityPos;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public Integer getFirstViewableIndex() {
        return this.firstViewableIndex;
    }

    public String getInteractionType() {
        return this.interactionType;
    }

    public Integer getLastViewableIndex() {
        return this.lastViewableIndex;
    }

    public String getMetricsPreset() {
        return this.metricsPreset;
    }

    public Integer getNumItemsAccessible() {
        return this.numItemsAccessible;
    }

    public String getPageType() {
        return this.pageType;
    }

    public String getRefMarker() {
        return this.refMarker;
    }

    public String getReferer() {
        return this.referer;
    }

    public String getSignalType() {
        return this.signalType;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSignalType(), getContentSrc(), getFirstViewableIndex(), getNumItemsAccessible(), getContainerInstId(), getDetailPageItemIdType(), getEntityPos(), getActionType(), getDetailPageItemId(), getRefMarker(), getEntityType(), getLastViewableIndex(), getContainerIndex(), getEntityIdType(), getContainerType(), getEntityId(), getReferer(), getPageType(), getContentName(), getInteractionType(), getMetricsPreset());
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public void setContainerIndex(Integer num) {
        this.containerIndex = num;
    }

    public void setContainerInstId(String str) {
        this.containerInstId = str;
    }

    public void setContainerType(String str) {
        this.containerType = str;
    }

    public void setContentName(String str) {
        this.contentName = str;
    }

    public void setContentSrc(String str) {
        this.contentSrc = str;
    }

    public void setDetailPageItemId(String str) {
        this.detailPageItemId = str;
    }

    public void setDetailPageItemIdType(String str) {
        this.detailPageItemIdType = str;
    }

    public void setEntityId(String str) {
        this.entityId = str;
    }

    public void setEntityIdType(String str) {
        this.entityIdType = str;
    }

    public void setEntityPos(Integer num) {
        this.entityPos = num;
    }

    public void setEntityType(String str) {
        this.entityType = str;
    }

    public void setFirstViewableIndex(Integer num) {
        this.firstViewableIndex = num;
    }

    public void setInteractionType(String str) {
        this.interactionType = str;
    }

    public void setLastViewableIndex(Integer num) {
        this.lastViewableIndex = num;
    }

    public void setMetricsPreset(String str) {
        this.metricsPreset = str;
    }

    public void setNumItemsAccessible(Integer num) {
        this.numItemsAccessible = num;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }

    public void setRefMarker(String str) {
        this.refMarker = str;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public void setSignalType(String str) {
        this.signalType = str;
    }
}
