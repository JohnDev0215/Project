package com.amazon.musicensembleservice;

import com.amazon.layout.music.model.Root;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailCatalogResponse extends Root {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ArtistDetailCatalogResponse");
    private List<Error> errors;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Root, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Root root) {
        if (root == null) {
            return -1;
        }
        if (root == this) {
            return 0;
        }
        if (!(root instanceof ArtistDetailCatalogResponse)) {
            return 1;
        }
        List<Error> errors = getErrors();
        List<Error> errors2 = ((ArtistDetailCatalogResponse) root).getErrors();
        if (errors != errors2) {
            if (errors == null) {
                return -1;
            }
            if (errors2 == null) {
                return 1;
            }
            if (errors instanceof Comparable) {
                int iCompareTo = ((Comparable) errors).compareTo(errors2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!errors.equals(errors2)) {
                int iHashCode = errors.hashCode();
                int iHashCode2 = errors2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(root);
    }

    @Override // com.amazon.layout.music.model.Root
    public boolean equals(Object obj) {
        if (obj instanceof ArtistDetailCatalogResponse) {
            return super.equals(obj) && internalEqualityCheck(getErrors(), ((ArtistDetailCatalogResponse) obj).getErrors());
        }
        return false;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    @Override // com.amazon.layout.music.model.Root
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getErrors());
    }

    public void setErrors(List<Error> list) {
        this.errors = list;
    }
}
