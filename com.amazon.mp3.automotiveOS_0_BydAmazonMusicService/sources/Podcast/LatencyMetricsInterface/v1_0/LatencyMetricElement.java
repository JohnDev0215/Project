package Podcast.LatencyMetricsInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class LatencyMetricElement implements Comparable<LatencyMetricElement> {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement");
    private String pageType;

    public static class Builder {
        protected String pageType;

        public LatencyMetricElement build() {
            LatencyMetricElement latencyMetricElement = new LatencyMetricElement();
            populate(latencyMetricElement);
            return latencyMetricElement;
        }

        protected void populate(LatencyMetricElement latencyMetricElement) {
            latencyMetricElement.setPageType(this.pageType);
        }

        public Builder withPageType(String str) {
            this.pageType = str;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LatencyMetricElement latencyMetricElement) {
        String pageType;
        String pageType2;
        if (latencyMetricElement == null) {
            return -1;
        }
        if (latencyMetricElement != this && (pageType = getPageType()) != (pageType2 = latencyMetricElement.getPageType())) {
            if (pageType == null) {
                return -1;
            }
            if (pageType2 == null) {
                return 1;
            }
            int iCompareTo = pageType.compareTo(pageType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LatencyMetricElement) {
            return internalEqualityCheck(getPageType(), ((LatencyMetricElement) obj).getPageType());
        }
        return false;
    }

    public String getPageType() {
        return this.pageType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPageType());
    }

    public void setPageType(String str) {
        this.pageType = str;
    }
}
