package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Review implements Comparable<Review> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Review");
    private Double average;
    private Integer fiveStar;
    private Integer fourStar;
    private Integer oneStar;
    private Integer threeStar;
    private Integer total;
    private Integer twoStar;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Review review) {
        if (review == null) {
            return -1;
        }
        if (review == this) {
            return 0;
        }
        Integer threeStar = getThreeStar();
        Integer threeStar2 = review.getThreeStar();
        if (threeStar != threeStar2) {
            if (threeStar == null) {
                return -1;
            }
            if (threeStar2 == null) {
                return 1;
            }
            int iCompareTo = threeStar.compareTo(threeStar2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Double average = getAverage();
        Double average2 = review.getAverage();
        if (average != average2) {
            if (average == null) {
                return -1;
            }
            if (average2 == null) {
                return 1;
            }
            int iCompareTo2 = average.compareTo(average2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer total = getTotal();
        Integer total2 = review.getTotal();
        if (total != total2) {
            if (total == null) {
                return -1;
            }
            if (total2 == null) {
                return 1;
            }
            int iCompareTo3 = total.compareTo(total2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer fiveStar = getFiveStar();
        Integer fiveStar2 = review.getFiveStar();
        if (fiveStar != fiveStar2) {
            if (fiveStar == null) {
                return -1;
            }
            if (fiveStar2 == null) {
                return 1;
            }
            int iCompareTo4 = fiveStar.compareTo(fiveStar2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Integer twoStar = getTwoStar();
        Integer twoStar2 = review.getTwoStar();
        if (twoStar != twoStar2) {
            if (twoStar == null) {
                return -1;
            }
            if (twoStar2 == null) {
                return 1;
            }
            int iCompareTo5 = twoStar.compareTo(twoStar2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer oneStar = getOneStar();
        Integer oneStar2 = review.getOneStar();
        if (oneStar != oneStar2) {
            if (oneStar == null) {
                return -1;
            }
            if (oneStar2 == null) {
                return 1;
            }
            int iCompareTo6 = oneStar.compareTo(oneStar2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Integer fourStar = getFourStar();
        Integer fourStar2 = review.getFourStar();
        if (fourStar != fourStar2) {
            if (fourStar == null) {
                return -1;
            }
            if (fourStar2 == null) {
                return 1;
            }
            int iCompareTo7 = fourStar.compareTo(fourStar2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Review)) {
            return false;
        }
        Review review = (Review) obj;
        return internalEqualityCheck(getThreeStar(), review.getThreeStar()) && internalEqualityCheck(getAverage(), review.getAverage()) && internalEqualityCheck(getTotal(), review.getTotal()) && internalEqualityCheck(getFiveStar(), review.getFiveStar()) && internalEqualityCheck(getTwoStar(), review.getTwoStar()) && internalEqualityCheck(getOneStar(), review.getOneStar()) && internalEqualityCheck(getFourStar(), review.getFourStar());
    }

    public Double getAverage() {
        return this.average;
    }

    public Integer getFiveStar() {
        return this.fiveStar;
    }

    public Integer getFourStar() {
        return this.fourStar;
    }

    public Integer getOneStar() {
        return this.oneStar;
    }

    public Integer getThreeStar() {
        return this.threeStar;
    }

    public Integer getTotal() {
        return this.total;
    }

    public Integer getTwoStar() {
        return this.twoStar;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getThreeStar(), getAverage(), getTotal(), getFiveStar(), getTwoStar(), getOneStar(), getFourStar());
    }

    public void setAverage(Double d) {
        this.average = d;
    }

    public void setFiveStar(Integer num) {
        this.fiveStar = num;
    }

    public void setFourStar(Integer num) {
        this.fourStar = num;
    }

    public void setOneStar(Integer num) {
        this.oneStar = num;
    }

    public void setThreeStar(Integer num) {
        this.threeStar = num;
    }

    public void setTotal(Integer num) {
        this.total = num;
    }

    public void setTwoStar(Integer num) {
        this.twoStar = num;
    }
}
