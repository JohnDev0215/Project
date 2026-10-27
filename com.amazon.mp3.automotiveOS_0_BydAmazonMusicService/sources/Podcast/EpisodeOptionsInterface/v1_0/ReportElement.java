package Podcast.EpisodeOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ReportElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.EpisodeOptionsInterface.v1_0.ReportElement");
    private String id;
    private List<Method> onReport;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onReport;

        public ReportElement build() {
            ReportElement reportElement = new ReportElement();
            populate(reportElement);
            return reportElement;
        }

        protected void populate(ReportElement reportElement) {
            super.populate((TemplateElement) reportElement);
            reportElement.setOnReport(this.onReport);
            reportElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnReport(List<Method> list) {
            this.onReport = list;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof ReportElement)) {
            return 1;
        }
        ReportElement reportElement = (ReportElement) sOAObject;
        List<Method> onReport = getOnReport();
        List<Method> onReport2 = reportElement.getOnReport();
        if (onReport != onReport2) {
            if (onReport == null) {
                return -1;
            }
            if (onReport2 == null) {
                return 1;
            }
            if (onReport instanceof Comparable) {
                int iCompareTo = ((Comparable) onReport).compareTo(onReport2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onReport.equals(onReport2)) {
                int iHashCode = onReport.hashCode();
                int iHashCode2 = onReport2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = reportElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportElement)) {
            return false;
        }
        ReportElement reportElement = (ReportElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnReport(), reportElement.getOnReport()) && internalEqualityCheck(getId(), reportElement.getId());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnReport() {
        return this.onReport;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnReport(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnReport(List<Method> list) {
        this.onReport = list;
    }
}
