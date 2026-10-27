package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccentOutlineBadgeElement extends BadgeElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AccentOutlineBadgeElement");

    public static class Builder extends BadgeElement.Builder {
        @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement.Builder
        public AccentOutlineBadgeElement build() {
            AccentOutlineBadgeElement accentOutlineBadgeElement = new AccentOutlineBadgeElement();
            populate(accentOutlineBadgeElement);
            return accentOutlineBadgeElement;
        }

        protected void populate(AccentOutlineBadgeElement accentOutlineBadgeElement) {
            super.populate((BadgeElement) accentOutlineBadgeElement);
        }

        @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement.Builder
        public Builder withOnItemSelected(List<Method> list) {
            super.withOnItemSelected(list);
            return this;
        }

        @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement.Builder
        public /* bridge */ /* synthetic */ BadgeElement.Builder withOnItemSelected(List list) {
            return withOnItemSelected((List<Method>) list);
        }

        @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement.Builder
        public Builder withText(String str) {
            super.withText(str);
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (sOAObject instanceof AccentOutlineBadgeElement) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof AccentOutlineBadgeElement) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // Podcast.Touch.PodcastDetailTemplateInterface.v2_0.BadgeElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
