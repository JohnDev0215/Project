package com.amazon.layout.music.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Position implements Comparable<Position> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.Position");
    private Integer blockIndex;
    private Integer positionIndex;
    private List<SubTitle> subtitles;
    private String text;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Position position) {
        if (position == null) {
            return -1;
        }
        if (position == this) {
            return 0;
        }
        Integer positionIndex = getPositionIndex();
        Integer positionIndex2 = position.getPositionIndex();
        if (positionIndex != positionIndex2) {
            if (positionIndex == null) {
                return -1;
            }
            if (positionIndex2 == null) {
                return 1;
            }
            int iCompareTo = positionIndex.compareTo(positionIndex2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<SubTitle> subtitles = getSubtitles();
        List<SubTitle> subtitles2 = position.getSubtitles();
        if (subtitles != subtitles2) {
            if (subtitles == null) {
                return -1;
            }
            if (subtitles2 == null) {
                return 1;
            }
            if (subtitles instanceof Comparable) {
                int iCompareTo2 = ((Comparable) subtitles).compareTo(subtitles2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!subtitles.equals(subtitles2)) {
                int iHashCode = subtitles.hashCode();
                int iHashCode2 = subtitles2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String text = getText();
        String text2 = position.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo3 = text.compareTo(text2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer blockIndex = getBlockIndex();
        Integer blockIndex2 = position.getBlockIndex();
        if (blockIndex != blockIndex2) {
            if (blockIndex == null) {
                return -1;
            }
            if (blockIndex2 == null) {
                return 1;
            }
            int iCompareTo4 = blockIndex.compareTo(blockIndex2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position position = (Position) obj;
        return internalEqualityCheck(getPositionIndex(), position.getPositionIndex()) && internalEqualityCheck(getSubtitles(), position.getSubtitles()) && internalEqualityCheck(getText(), position.getText()) && internalEqualityCheck(getBlockIndex(), position.getBlockIndex());
    }

    public Integer getBlockIndex() {
        return this.blockIndex;
    }

    public Integer getPositionIndex() {
        return this.positionIndex;
    }

    public List<SubTitle> getSubtitles() {
        return this.subtitles;
    }

    public String getText() {
        return this.text;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPositionIndex(), getSubtitles(), getText(), getBlockIndex());
    }

    public void setBlockIndex(Integer num) {
        this.blockIndex = num;
    }

    public void setPositionIndex(Integer num) {
        this.positionIndex = num;
    }

    public void setSubtitles(List<SubTitle> list) {
        this.subtitles = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
