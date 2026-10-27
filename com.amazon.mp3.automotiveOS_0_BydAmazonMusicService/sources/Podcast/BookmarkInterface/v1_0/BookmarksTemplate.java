package Podcast.BookmarkInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BookmarksTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.BookmarkInterface.v1_0.BookmarksTemplate");
    private String backgroundImage;
    private String header;

    public static class Builder extends Template.Builder {
        protected String backgroundImage;
        protected String header;

        public BookmarksTemplate build() {
            BookmarksTemplate bookmarksTemplate = new BookmarksTemplate();
            populate(bookmarksTemplate);
            return bookmarksTemplate;
        }

        protected void populate(BookmarksTemplate bookmarksTemplate) {
            super.populate((Template) bookmarksTemplate);
            bookmarksTemplate.setHeader(this.header);
            bookmarksTemplate.setBackgroundImage(this.backgroundImage);
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
        if (!(sOAObject instanceof BookmarksTemplate)) {
            return 1;
        }
        BookmarksTemplate bookmarksTemplate = (BookmarksTemplate) sOAObject;
        String header = getHeader();
        String header2 = bookmarksTemplate.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo = header.compareTo(header2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String backgroundImage = getBackgroundImage();
        String backgroundImage2 = bookmarksTemplate.getBackgroundImage();
        if (backgroundImage != backgroundImage2) {
            if (backgroundImage == null) {
                return -1;
            }
            if (backgroundImage2 == null) {
                return 1;
            }
            int iCompareTo2 = backgroundImage.compareTo(backgroundImage2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof BookmarksTemplate)) {
            return false;
        }
        BookmarksTemplate bookmarksTemplate = (BookmarksTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), bookmarksTemplate.getHeader()) && internalEqualityCheck(getBackgroundImage(), bookmarksTemplate.getBackgroundImage());
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public String getHeader() {
        return this.header;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getBackgroundImage());
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public void setHeader(String str) {
        this.header = str;
    }
}
