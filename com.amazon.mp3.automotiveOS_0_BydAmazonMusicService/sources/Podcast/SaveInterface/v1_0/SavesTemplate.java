package Podcast.SaveInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SavesTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.SaveInterface.v1_0.SavesTemplate");
    private String header;
    private RefinementBarElement refinementBar;

    public static class Builder extends Template.Builder {
        protected String header;
        protected RefinementBarElement refinementBar;

        public SavesTemplate build() {
            SavesTemplate savesTemplate = new SavesTemplate();
            populate(savesTemplate);
            return savesTemplate;
        }

        protected void populate(SavesTemplate savesTemplate) {
            super.populate((Template) savesTemplate);
            savesTemplate.setRefinementBar(this.refinementBar);
            savesTemplate.setHeader(this.header);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            super.withLatencyMetricElement(latencyMetricElement);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withLaunchMode(String str) {
            super.withLaunchMode(str);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnBound(List<Method> list) {
            super.withOnBound(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnBound(List list) {
            return withOnBound((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnCreated(List<Method> list) {
            super.withOnCreated(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnCreated(List list) {
            return withOnCreated((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnDismissed(List<Method> list) {
            super.withOnDismissed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnDismissed(List list) {
            return withOnDismissed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public /* bridge */ /* synthetic */ Template.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        @Deprecated
        public Builder withPageType(String str) {
            super.withPageType(str);
            return this;
        }

        public Builder withRefinementBar(RefinementBarElement refinementBarElement) {
            this.refinementBar = refinementBarElement;
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
    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof SavesTemplate)) {
            return 1;
        }
        SavesTemplate savesTemplate = (SavesTemplate) sOAObject;
        RefinementBarElement refinementBar = getRefinementBar();
        RefinementBarElement refinementBar2 = savesTemplate.getRefinementBar();
        if (refinementBar != refinementBar2) {
            if (refinementBar == null) {
                return -1;
            }
            if (refinementBar2 == null) {
                return 1;
            }
            int iCompareTo = refinementBar.compareTo(refinementBar2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String header = getHeader();
        String header2 = savesTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo2 = header.compareTo(header2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SavesTemplate)) {
            return false;
        }
        SavesTemplate savesTemplate = (SavesTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getRefinementBar(), savesTemplate.getRefinementBar()) && internalEqualityCheck(getHeader(), savesTemplate.getHeader());
    }

    public String getHeader() {
        return this.header;
    }

    public RefinementBarElement getRefinementBar() {
        return this.refinementBar;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getRefinementBar(), getHeader());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setRefinementBar(RefinementBarElement refinementBarElement) {
        this.refinementBar = refinementBarElement;
    }
}
