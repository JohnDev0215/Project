package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class Icon extends Block {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Icon");
    private Hint hint;
    private String type;
    private String url;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof Icon)) {
            return 1;
        }
        Icon icon = (Icon) block;
        String url = getUrl();
        String url2 = icon.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo = url.compareTo(url2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Hint hint = getHint();
        Hint hint2 = icon.getHint();
        if (hint != hint2) {
            if (hint == null) {
                return -1;
            }
            if (hint2 == null) {
                return 1;
            }
            int iCompareTo2 = hint.compareTo(hint2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String type = getType();
        String type2 = icon.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo3 = type.compareTo(type2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof Icon)) {
            return false;
        }
        Icon icon = (Icon) obj;
        return super.equals(obj) && internalEqualityCheck(getUrl(), icon.getUrl()) && internalEqualityCheck(getHint(), icon.getHint()) && internalEqualityCheck(getType(), icon.getType());
    }

    public Hint getHint() {
        return this.hint;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getUrl(), getHint(), getType());
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
