package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DownloadElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.DownloadElement");
    private String id;
    private List<Method> onDownload;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onDownload;

        public DownloadElement build() {
            DownloadElement downloadElement = new DownloadElement();
            populate(downloadElement);
            return downloadElement;
        }

        protected void populate(DownloadElement downloadElement) {
            super.populate((TemplateElement) downloadElement);
            downloadElement.setId(this.id);
            downloadElement.setOnDownload(this.onDownload);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnDownload(List<Method> list) {
            this.onDownload = list;
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
        if (!(sOAObject instanceof DownloadElement)) {
            return 1;
        }
        DownloadElement downloadElement = (DownloadElement) sOAObject;
        String id = getId();
        String id2 = downloadElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onDownload = getOnDownload();
        List<Method> onDownload2 = downloadElement.getOnDownload();
        if (onDownload != onDownload2) {
            if (onDownload == null) {
                return -1;
            }
            if (onDownload2 == null) {
                return 1;
            }
            if (onDownload instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onDownload).compareTo(onDownload2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onDownload.equals(onDownload2)) {
                int iHashCode = onDownload.hashCode();
                int iHashCode2 = onDownload2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DownloadElement)) {
            return false;
        }
        DownloadElement downloadElement = (DownloadElement) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), downloadElement.getId()) && internalEqualityCheck(getOnDownload(), downloadElement.getOnDownload());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnDownload() {
        return this.onDownload;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getOnDownload());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnDownload(List<Method> list) {
        this.onDownload = list;
    }
}
