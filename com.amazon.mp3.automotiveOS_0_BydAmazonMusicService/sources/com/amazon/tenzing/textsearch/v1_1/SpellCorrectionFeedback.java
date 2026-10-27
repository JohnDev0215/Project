package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpellCorrectionFeedback implements Comparable<SpellCorrectionFeedback> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpellCorrectionFeedback");
    private String id;

    public static class Builder {
        protected String id;

        public SpellCorrectionFeedback build() {
            SpellCorrectionFeedback spellCorrectionFeedback = new SpellCorrectionFeedback();
            populate(spellCorrectionFeedback);
            return spellCorrectionFeedback;
        }

        protected void populate(SpellCorrectionFeedback spellCorrectionFeedback) {
            spellCorrectionFeedback.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SpellCorrectionFeedback spellCorrectionFeedback) {
        String id;
        String id2;
        if (spellCorrectionFeedback == null) {
            return -1;
        }
        if (spellCorrectionFeedback != this && (id = getId()) != (id2 = spellCorrectionFeedback.getId())) {
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
        if (obj instanceof SpellCorrectionFeedback) {
            return internalEqualityCheck(getId(), ((SpellCorrectionFeedback) obj).getId());
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
