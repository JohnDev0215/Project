package com.amazon.musicrelationshipservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class EdgesRequest extends MusicRelationshipServiceBaseRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicrelationshipservice.model.EdgesRequest");
    private List<Edge> edges;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicrelationshipservice.model.MusicRelationshipServiceBaseRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicRelationshipServiceBaseRequest musicRelationshipServiceBaseRequest) {
        if (musicRelationshipServiceBaseRequest == null) {
            return -1;
        }
        if (musicRelationshipServiceBaseRequest == this) {
            return 0;
        }
        if (!(musicRelationshipServiceBaseRequest instanceof EdgesRequest)) {
            return 1;
        }
        List<Edge> edges = getEdges();
        List<Edge> edges2 = ((EdgesRequest) musicRelationshipServiceBaseRequest).getEdges();
        if (edges != edges2) {
            if (edges == null) {
                return -1;
            }
            if (edges2 == null) {
                return 1;
            }
            if (edges instanceof Comparable) {
                int iCompareTo = ((Comparable) edges).compareTo(edges2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!edges.equals(edges2)) {
                int iHashCode = edges.hashCode();
                int iHashCode2 = edges2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(musicRelationshipServiceBaseRequest);
    }

    @Override // com.amazon.musicrelationshipservice.model.MusicRelationshipServiceBaseRequest
    public boolean equals(Object obj) {
        if (obj instanceof EdgesRequest) {
            return super.equals(obj) && internalEqualityCheck(getEdges(), ((EdgesRequest) obj).getEdges());
        }
        return false;
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    @Override // com.amazon.musicrelationshipservice.model.MusicRelationshipServiceBaseRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEdges());
    }

    public void setEdges(List<Edge> list) {
        this.edges = list;
    }
}
