package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate");
    private String backgroundImage;
    private PTCTemplateShardsElement shards;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected PTCTemplateShardsElement shards;

        public PTCTemplate build() {
            PTCTemplate pTCTemplate = new PTCTemplate();
            populate(pTCTemplate);
            return pTCTemplate;
        }

        protected void populate(PTCTemplate pTCTemplate) {
            super.populate((Template) pTCTemplate);
            pTCTemplate.setBackgroundImage(this.backgroundImage);
            pTCTemplate.setShards(this.shards);
        }

        public Builder withBackgroundImage(String str) {
            this.backgroundImage = str;
            return this;
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
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

        public Builder withShards(PTCTemplateShardsElement pTCTemplateShardsElement) {
            this.shards = pTCTemplateShardsElement;
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
        if (!(sOAObject instanceof PTCTemplate)) {
            return 1;
        }
        PTCTemplate pTCTemplate = (PTCTemplate) sOAObject;
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = pTCTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PTCTemplateShardsElement shards = getShards();
        PTCTemplateShardsElement shards2 = pTCTemplate.getShards();
        if (shards != shards2) {
            if (shards == null) {
                return -1;
            }
            if (shards2 == null) {
                return 1;
            }
            int iCompareTo2 = shards.compareTo(shards2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PTCTemplate)) {
            return false;
        }
        PTCTemplate pTCTemplate = (PTCTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getBackgroundImage(), pTCTemplate.getBackgroundImage()) && internalEqualityCheck(getShards(), pTCTemplate.getShards());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public PTCTemplateShardsElement getShards() {
        return this.shards;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundImage(), getShards());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setShards(PTCTemplateShardsElement pTCTemplateShardsElement) {
        this.shards = pTCTemplateShardsElement;
    }
}
