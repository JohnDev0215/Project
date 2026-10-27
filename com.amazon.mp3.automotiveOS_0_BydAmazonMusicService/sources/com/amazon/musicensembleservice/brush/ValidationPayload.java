package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ValidationPayload implements Comparable<ValidationPayload> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.ValidationPayload");
    private Map<String, String> campaignMetadata;
    private Boolean includeAllWidgets;
    private String widgetGroupId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ValidationPayload validationPayload) {
        if (validationPayload == null) {
            return -1;
        }
        if (validationPayload == this) {
            return 0;
        }
        String widgetGroupId = getWidgetGroupId();
        String widgetGroupId2 = validationPayload.getWidgetGroupId();
        if (widgetGroupId != widgetGroupId2) {
            if (widgetGroupId == null) {
                return -1;
            }
            if (widgetGroupId2 == null) {
                return 1;
            }
            int iCompareTo = widgetGroupId.compareTo(widgetGroupId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIncludeAllWidgets = isIncludeAllWidgets();
        Boolean boolIsIncludeAllWidgets2 = validationPayload.isIncludeAllWidgets();
        if (boolIsIncludeAllWidgets != boolIsIncludeAllWidgets2) {
            if (boolIsIncludeAllWidgets == null) {
                return -1;
            }
            if (boolIsIncludeAllWidgets2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIncludeAllWidgets.compareTo(boolIsIncludeAllWidgets2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Map<String, String> campaignMetadata = getCampaignMetadata();
        Map<String, String> campaignMetadata2 = validationPayload.getCampaignMetadata();
        if (campaignMetadata != campaignMetadata2) {
            if (campaignMetadata == null) {
                return -1;
            }
            if (campaignMetadata2 == null) {
                return 1;
            }
            if (campaignMetadata instanceof Comparable) {
                int iCompareTo3 = ((Comparable) campaignMetadata).compareTo(campaignMetadata2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!campaignMetadata.equals(campaignMetadata2)) {
                int iHashCode = campaignMetadata.hashCode();
                int iHashCode2 = campaignMetadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ValidationPayload)) {
            return false;
        }
        ValidationPayload validationPayload = (ValidationPayload) obj;
        return internalEqualityCheck(getWidgetGroupId(), validationPayload.getWidgetGroupId()) && internalEqualityCheck(isIncludeAllWidgets(), validationPayload.isIncludeAllWidgets()) && internalEqualityCheck(getCampaignMetadata(), validationPayload.getCampaignMetadata());
    }

    public Map<String, String> getCampaignMetadata() {
        return this.campaignMetadata;
    }

    public String getWidgetGroupId() {
        return this.widgetGroupId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getWidgetGroupId(), isIncludeAllWidgets(), getCampaignMetadata());
    }

    public Boolean isIncludeAllWidgets() {
        return this.includeAllWidgets;
    }
}
