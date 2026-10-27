package Podcast.Desktop.AlertTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AlertTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.AlertTemplateInterface.v1_0.AlertTemplate");
    private String image;
    private NegativeButtonElement negativeButton;
    private PositiveButtonElement positiveButton;
    private String subtitle;
    private String title;

    public static class Builder extends Template.Builder {
        protected String image;
        protected NegativeButtonElement negativeButton;
        protected PositiveButtonElement positiveButton;
        protected String subtitle;
        protected String title;

        public AlertTemplate build() {
            AlertTemplate alertTemplate = new AlertTemplate();
            populate(alertTemplate);
            return alertTemplate;
        }

        protected void populate(AlertTemplate alertTemplate) {
            super.populate((Template) alertTemplate);
            alertTemplate.setTitle(this.title);
            alertTemplate.setNegativeButton(this.negativeButton);
            alertTemplate.setPositiveButton(this.positiveButton);
            alertTemplate.setImage(this.image);
            alertTemplate.setSubtitle(this.subtitle);
        }

        @Override // SOATemplateListInterface.v1_0.Template.Builder
        public Builder withCacheSeconds(Long l) {
            super.withCacheSeconds(l);
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
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

        public Builder withNegativeButton(NegativeButtonElement negativeButtonElement) {
            this.negativeButton = negativeButtonElement;
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

        public Builder withPositiveButton(PositiveButtonElement positiveButtonElement) {
            this.positiveButton = positiveButtonElement;
            return this;
        }

        public Builder withSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
        if (!(sOAObject instanceof AlertTemplate)) {
            return 1;
        }
        AlertTemplate alertTemplate = (AlertTemplate) sOAObject;
        String title = getTitle();
        String title2 = alertTemplate.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        NegativeButtonElement negativeButton = getNegativeButton();
        NegativeButtonElement negativeButton2 = alertTemplate.getNegativeButton();
        if (negativeButton != negativeButton2) {
            if (negativeButton == null) {
                return -1;
            }
            if (negativeButton2 == null) {
                return 1;
            }
            int iCompareTo2 = negativeButton.compareTo(negativeButton2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        PositiveButtonElement positiveButton = getPositiveButton();
        PositiveButtonElement positiveButton2 = alertTemplate.getPositiveButton();
        if (positiveButton != positiveButton2) {
            if (positiveButton == null) {
                return -1;
            }
            if (positiveButton2 == null) {
                return 1;
            }
            int iCompareTo3 = positiveButton.compareTo(positiveButton2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String image = getImage();
        String image2 = alertTemplate.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo4 = image.compareTo(image2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = alertTemplate.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo5 = subtitle.compareTo(subtitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AlertTemplate)) {
            return false;
        }
        AlertTemplate alertTemplate = (AlertTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), alertTemplate.getTitle()) && internalEqualityCheck(getNegativeButton(), alertTemplate.getNegativeButton()) && internalEqualityCheck(getPositiveButton(), alertTemplate.getPositiveButton()) && internalEqualityCheck(getImage(), alertTemplate.getImage()) && internalEqualityCheck(getSubtitle(), alertTemplate.getSubtitle());
    }

    public String getImage() {
        return this.image;
    }

    public NegativeButtonElement getNegativeButton() {
        return this.negativeButton;
    }

    public PositiveButtonElement getPositiveButton() {
        return this.positiveButton;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getNegativeButton(), getPositiveButton(), getImage(), getSubtitle());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setNegativeButton(NegativeButtonElement negativeButtonElement) {
        this.negativeButton = negativeButtonElement;
    }

    public void setPositiveButton(PositiveButtonElement positiveButtonElement) {
        this.positiveButton = positiveButtonElement;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
