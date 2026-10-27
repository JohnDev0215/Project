package Podcast.Touch.DetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.Shard;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DetailTemplateShardElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.DetailTemplateShardElement");
    private String header;
    private Shard shard;

    public static class Builder extends TemplateElement.Builder {
        protected String header;
        protected Shard shard;

        public DetailTemplateShardElement build() {
            DetailTemplateShardElement detailTemplateShardElement = new DetailTemplateShardElement();
            populate(detailTemplateShardElement);
            return detailTemplateShardElement;
        }

        protected void populate(DetailTemplateShardElement detailTemplateShardElement) {
            super.populate((TemplateElement) detailTemplateShardElement);
            detailTemplateShardElement.setHeader(this.header);
            detailTemplateShardElement.setShard(this.shard);
        }

        public Builder withHeader(String str) {
            this.header = str;
            return this;
        }

        public Builder withShard(Shard shard) {
            this.shard = shard;
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
        if (!(sOAObject instanceof DetailTemplateShardElement)) {
            return 1;
        }
        DetailTemplateShardElement detailTemplateShardElement = (DetailTemplateShardElement) sOAObject;
        String header = getHeader();
        String header2 = detailTemplateShardElement.getHeader();
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
        Shard shard = getShard();
        Shard shard2 = detailTemplateShardElement.getShard();
        if (shard != shard2) {
            if (shard == null) {
                return -1;
            }
            if (shard2 == null) {
                return 1;
            }
            int iCompareTo2 = shard.compareTo(shard2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DetailTemplateShardElement)) {
            return false;
        }
        DetailTemplateShardElement detailTemplateShardElement = (DetailTemplateShardElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeader(), detailTemplateShardElement.getHeader()) && internalEqualityCheck(getShard(), detailTemplateShardElement.getShard());
    }

    public String getHeader() {
        return this.header;
    }

    public Shard getShard() {
        return this.shard;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeader(), getShard());
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setShard(Shard shard) {
        this.shard = shard;
    }
}
