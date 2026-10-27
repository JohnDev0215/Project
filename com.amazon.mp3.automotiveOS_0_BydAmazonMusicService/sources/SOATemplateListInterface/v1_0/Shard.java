package SOATemplateListInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Shard extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOATemplateListInterface.v1_0.Shard");
    private Template create;
    private Template createAndBind;

    public static class Builder extends SOAObject.Builder {
        protected Template create;
        protected Template createAndBind;

        public Shard build() {
            Shard shard = new Shard();
            populate(shard);
            return shard;
        }

        protected void populate(Shard shard) {
            super.populate((SOAObject) shard);
            shard.setCreate(this.create);
            shard.setCreateAndBind(this.createAndBind);
        }

        public Builder withCreate(Template template) {
            this.create = template;
            return this;
        }

        public Builder withCreateAndBind(Template template) {
            this.createAndBind = template;
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
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof Shard)) {
            return 1;
        }
        Shard shard = (Shard) sOAObject;
        Template create = getCreate();
        Template create2 = shard.getCreate();
        if (create != create2) {
            if (create == null) {
                return -1;
            }
            if (create2 == null) {
                return 1;
            }
            int iCompareTo = create.compareTo(create2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Template createAndBind = getCreateAndBind();
        Template createAndBind2 = shard.getCreateAndBind();
        if (createAndBind != createAndBind2) {
            if (createAndBind == null) {
                return -1;
            }
            if (createAndBind2 == null) {
                return 1;
            }
            int iCompareTo2 = createAndBind.compareTo(createAndBind2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof Shard)) {
            return false;
        }
        Shard shard = (Shard) obj;
        return super.equals(obj) && internalEqualityCheck(getCreate(), shard.getCreate()) && internalEqualityCheck(getCreateAndBind(), shard.getCreateAndBind());
    }

    public Template getCreate() {
        return this.create;
    }

    public Template getCreateAndBind() {
        return this.createAndBind;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCreate(), getCreateAndBind());
    }

    public void setCreate(Template template) {
        this.create = template;
    }

    public void setCreateAndBind(Template template) {
        this.createAndBind = template;
    }
}
