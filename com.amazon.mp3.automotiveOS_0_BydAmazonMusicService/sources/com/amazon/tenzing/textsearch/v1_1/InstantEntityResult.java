package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.music.platform.model.Document;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class InstantEntityResult extends InstantSearchResult {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantEntityResult");
    private Document document;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated InstantSearchResult instantSearchResult) {
        if (instantSearchResult == null) {
            return -1;
        }
        if (instantSearchResult == this) {
            return 0;
        }
        if (!(instantSearchResult instanceof InstantEntityResult)) {
            return 1;
        }
        Document document = getDocument();
        Document document2 = ((InstantEntityResult) instantSearchResult).getDocument();
        if (document != document2) {
            if (document == null) {
                return -1;
            }
            if (document2 == null) {
                return 1;
            }
            int iCompareTo = document.compareTo(document2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(instantSearchResult);
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult
    public boolean equals(Object obj) {
        if (obj instanceof InstantEntityResult) {
            return super.equals(obj) && internalEqualityCheck(getDocument(), ((InstantEntityResult) obj).getDocument());
        }
        return false;
    }

    public Document getDocument() {
        return this.document;
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDocument());
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
