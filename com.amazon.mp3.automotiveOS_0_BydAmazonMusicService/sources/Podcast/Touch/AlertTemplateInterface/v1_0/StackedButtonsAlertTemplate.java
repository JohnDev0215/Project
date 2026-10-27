package Podcast.Touch.AlertTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Template;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StackedButtonsAlertTemplate extends Template {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate");
    private String image;
    private NegativeButtonElement negativeButton;
    private List<Method> onDismiss;
    private PositiveButtonElement positiveButton;
    private String subtitle;
    private String title;

    public static class Builder extends Template.Builder {
        protected String image;
        protected NegativeButtonElement negativeButton;
        protected List<Method> onDismiss;
        protected PositiveButtonElement positiveButton;
        protected String subtitle;
        protected String title;

        public StackedButtonsAlertTemplate build() {
            StackedButtonsAlertTemplate stackedButtonsAlertTemplate = new StackedButtonsAlertTemplate();
            populate(stackedButtonsAlertTemplate);
            return stackedButtonsAlertTemplate;
        }

        protected void populate(StackedButtonsAlertTemplate stackedButtonsAlertTemplate) {
            super.populate((Template) stackedButtonsAlertTemplate);
            stackedButtonsAlertTemplate.setSubtitle(this.subtitle);
            stackedButtonsAlertTemplate.setImage(this.image);
            stackedButtonsAlertTemplate.setOnDismiss(this.onDismiss);
            stackedButtonsAlertTemplate.setTitle(this.title);
            stackedButtonsAlertTemplate.setPositiveButton(this.positiveButton);
            stackedButtonsAlertTemplate.setNegativeButton(this.negativeButton);
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

        public Builder withOnDismiss(List<Method> list) {
            this.onDismiss = list;
            return this;
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
        if (!(sOAObject instanceof StackedButtonsAlertTemplate)) {
            return 1;
        }
        StackedButtonsAlertTemplate stackedButtonsAlertTemplate = (StackedButtonsAlertTemplate) sOAObject;
        String subtitle = getSubtitle();
        String subtitle2 = stackedButtonsAlertTemplate.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo = subtitle.compareTo(subtitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = stackedButtonsAlertTemplate.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo2 = image.compareTo(image2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onDismiss = getOnDismiss();
        List<Method> onDismiss2 = stackedButtonsAlertTemplate.getOnDismiss();
        if (onDismiss != onDismiss2) {
            if (onDismiss == null) {
                return -1;
            }
            if (onDismiss2 == null) {
                return 1;
            }
            if (onDismiss instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onDismiss).compareTo(onDismiss2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onDismiss.equals(onDismiss2)) {
                int iHashCode = onDismiss.hashCode();
                int iHashCode2 = onDismiss2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = stackedButtonsAlertTemplate.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PositiveButtonElement positiveButton = getPositiveButton();
        PositiveButtonElement positiveButton2 = stackedButtonsAlertTemplate.getPositiveButton();
        if (positiveButton != positiveButton2) {
            if (positiveButton == null) {
                return -1;
            }
            if (positiveButton2 == null) {
                return 1;
            }
            int iCompareTo5 = positiveButton.compareTo(positiveButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        NegativeButtonElement negativeButton = getNegativeButton();
        NegativeButtonElement negativeButton2 = stackedButtonsAlertTemplate.getNegativeButton();
        if (negativeButton != negativeButton2) {
            if (negativeButton == null) {
                return -1;
            }
            if (negativeButton2 == null) {
                return 1;
            }
            int iCompareTo6 = negativeButton.compareTo(negativeButton2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.Template, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof StackedButtonsAlertTemplate)) {
            return false;
        }
        StackedButtonsAlertTemplate stackedButtonsAlertTemplate = (StackedButtonsAlertTemplate) obj;
        return super.equals(obj) && internalEqualityCheck(getSubtitle(), stackedButtonsAlertTemplate.getSubtitle()) && internalEqualityCheck(getImage(), stackedButtonsAlertTemplate.getImage()) && internalEqualityCheck(getOnDismiss(), stackedButtonsAlertTemplate.getOnDismiss()) && internalEqualityCheck(getTitle(), stackedButtonsAlertTemplate.getTitle()) && internalEqualityCheck(getPositiveButton(), stackedButtonsAlertTemplate.getPositiveButton()) && internalEqualityCheck(getNegativeButton(), stackedButtonsAlertTemplate.getNegativeButton());
    }

    public String getImage() {
        return this.image;
    }

    public NegativeButtonElement getNegativeButton() {
        return this.negativeButton;
    }

    public List<Method> getOnDismiss() {
        return this.onDismiss;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubtitle(), getImage(), getOnDismiss(), getTitle(), getPositiveButton(), getNegativeButton());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setNegativeButton(NegativeButtonElement negativeButtonElement) {
        this.negativeButton = negativeButtonElement;
    }

    public void setOnDismiss(List<Method> list) {
        this.onDismiss = list;
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
