package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.music.platform.model.Document;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Hit implements Comparable<Hit> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.Hit");
    private String blockRef;
    private Document document;
    private Explanation explanation;
    private List<Highlight> highlights;
    private String query;
    private Float score;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hit hit) {
        if (hit == null) {
            return -1;
        }
        if (hit == this) {
            return 0;
        }
        List<Highlight> highlights = getHighlights();
        List<Highlight> highlights2 = hit.getHighlights();
        if (highlights != highlights2) {
            if (highlights == null) {
                return -1;
            }
            if (highlights2 == null) {
                return 1;
            }
            if (highlights instanceof Comparable) {
                int iCompareTo = ((Comparable) highlights).compareTo(highlights2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!highlights.equals(highlights2)) {
                int iHashCode = highlights.hashCode();
                int iHashCode2 = highlights2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = hit.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo2 = blockRef.compareTo(blockRef2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String query = getQuery();
        String query2 = hit.getQuery();
        if (query != query2) {
            if (query == null) {
                return -1;
            }
            if (query2 == null) {
                return 1;
            }
            int iCompareTo3 = query.compareTo(query2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Document document = getDocument();
        Document document2 = hit.getDocument();
        if (document != document2) {
            if (document == null) {
                return -1;
            }
            if (document2 == null) {
                return 1;
            }
            int iCompareTo4 = document.compareTo(document2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Float score = getScore();
        Float score2 = hit.getScore();
        if (score != score2) {
            if (score == null) {
                return -1;
            }
            if (score2 == null) {
                return 1;
            }
            int iCompareTo5 = score.compareTo(score2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Explanation explanation = getExplanation();
        Explanation explanation2 = hit.getExplanation();
        if (explanation != explanation2) {
            if (explanation == null) {
                return -1;
            }
            if (explanation2 == null) {
                return 1;
            }
            int iCompareTo6 = explanation.compareTo(explanation2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Hit)) {
            return false;
        }
        Hit hit = (Hit) obj;
        return internalEqualityCheck(getHighlights(), hit.getHighlights()) && internalEqualityCheck(getBlockRef(), hit.getBlockRef()) && internalEqualityCheck(getQuery(), hit.getQuery()) && internalEqualityCheck(getDocument(), hit.getDocument()) && internalEqualityCheck(getScore(), hit.getScore()) && internalEqualityCheck(getExplanation(), hit.getExplanation());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Document getDocument() {
        return this.document;
    }

    public Explanation getExplanation() {
        return this.explanation;
    }

    public List<Highlight> getHighlights() {
        return this.highlights;
    }

    public String getQuery() {
        return this.query;
    }

    public Float getScore() {
        return this.score;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getHighlights(), getBlockRef(), getQuery(), getDocument(), getScore(), getExplanation());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    public void setHighlights(List<Highlight> list) {
        this.highlights = list;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public void setScore(Float f) {
        this.score = f;
    }
}
