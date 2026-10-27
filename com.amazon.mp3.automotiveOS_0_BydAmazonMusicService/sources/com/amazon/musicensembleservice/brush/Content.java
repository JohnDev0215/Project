package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Content implements Comparable<Content> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Content");
    private List<Block> blocks;
    private List<Entity> entities;
    private String nextToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Content content) {
        if (content == null) {
            return -1;
        }
        if (content == this) {
            return 0;
        }
        List<Entity> entities = getEntities();
        List<Entity> entities2 = content.getEntities();
        if (entities != entities2) {
            if (entities == null) {
                return -1;
            }
            if (entities2 == null) {
                return 1;
            }
            if (entities instanceof Comparable) {
                int iCompareTo = ((Comparable) entities).compareTo(entities2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!entities.equals(entities2)) {
                int iHashCode = entities.hashCode();
                int iHashCode2 = entities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String nextToken = getNextToken();
        String nextToken2 = content.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo2 = nextToken.compareTo(nextToken2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Block> blocks = getBlocks();
        List<Block> blocks2 = content.getBlocks();
        if (blocks != blocks2) {
            if (blocks == null) {
                return -1;
            }
            if (blocks2 == null) {
                return 1;
            }
            if (blocks instanceof Comparable) {
                int iCompareTo3 = ((Comparable) blocks).compareTo(blocks2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!blocks.equals(blocks2)) {
                int iHashCode3 = blocks.hashCode();
                int iHashCode4 = blocks2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Content)) {
            return false;
        }
        Content content = (Content) obj;
        return internalEqualityCheck(getEntities(), content.getEntities()) && internalEqualityCheck(getNextToken(), content.getNextToken()) && internalEqualityCheck(getBlocks(), content.getBlocks());
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEntities(), getNextToken(), getBlocks());
    }

    public void setBlocks(List<Block> list) {
        this.blocks = list;
    }

    public void setEntities(List<Entity> list) {
        this.entities = list;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }
}
