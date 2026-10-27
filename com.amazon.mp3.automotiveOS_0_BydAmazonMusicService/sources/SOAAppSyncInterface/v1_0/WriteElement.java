package SOAAppSyncInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class WriteElement implements Comparable<WriteElement> {
    private static final int classNameHashCode = internalHashCodeCompute("SOAAppSyncInterface.v1_0.WriteElement");
    private String id;

    public static abstract class Builder {
        protected String id;

        protected void populate(WriteElement writeElement) {
            writeElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        String id;
        String id2;
        if (writeElement == null) {
            return -1;
        }
        if (writeElement != this && (id = getId()) != (id2 = writeElement.getId())) {
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof WriteElement) {
            return internalEqualityCheck(getId(), ((WriteElement) obj).getId());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getId());
    }

    public void setId(String str) {
        this.id = str;
    }
}
