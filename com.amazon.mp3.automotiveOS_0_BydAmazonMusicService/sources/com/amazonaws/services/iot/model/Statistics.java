package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Statistics implements Serializable {
    private Double average;
    private Integer count;
    private Double maximum;
    private Double minimum;
    private Double stdDeviation;
    private Double sum;
    private Double sumOfSquares;
    private Double variance;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public Statistics withCount(Integer num) {
        this.count = num;
        return this;
    }

    public Double getAverage() {
        return this.average;
    }

    public void setAverage(Double d) {
        this.average = d;
    }

    public Statistics withAverage(Double d) {
        this.average = d;
        return this;
    }

    public Double getSum() {
        return this.sum;
    }

    public void setSum(Double d) {
        this.sum = d;
    }

    public Statistics withSum(Double d) {
        this.sum = d;
        return this;
    }

    public Double getMinimum() {
        return this.minimum;
    }

    public void setMinimum(Double d) {
        this.minimum = d;
    }

    public Statistics withMinimum(Double d) {
        this.minimum = d;
        return this;
    }

    public Double getMaximum() {
        return this.maximum;
    }

    public void setMaximum(Double d) {
        this.maximum = d;
    }

    public Statistics withMaximum(Double d) {
        this.maximum = d;
        return this;
    }

    public Double getSumOfSquares() {
        return this.sumOfSquares;
    }

    public void setSumOfSquares(Double d) {
        this.sumOfSquares = d;
    }

    public Statistics withSumOfSquares(Double d) {
        this.sumOfSquares = d;
        return this;
    }

    public Double getVariance() {
        return this.variance;
    }

    public void setVariance(Double d) {
        this.variance = d;
    }

    public Statistics withVariance(Double d) {
        this.variance = d;
        return this;
    }

    public Double getStdDeviation() {
        return this.stdDeviation;
    }

    public void setStdDeviation(Double d) {
        this.stdDeviation = d;
    }

    public Statistics withStdDeviation(Double d) {
        this.stdDeviation = d;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCount() != null) {
            sb.append("count: " + getCount() + ",");
        }
        if (getAverage() != null) {
            sb.append("average: " + getAverage() + ",");
        }
        if (getSum() != null) {
            sb.append("sum: " + getSum() + ",");
        }
        if (getMinimum() != null) {
            sb.append("minimum: " + getMinimum() + ",");
        }
        if (getMaximum() != null) {
            sb.append("maximum: " + getMaximum() + ",");
        }
        if (getSumOfSquares() != null) {
            sb.append("sumOfSquares: " + getSumOfSquares() + ",");
        }
        if (getVariance() != null) {
            sb.append("variance: " + getVariance() + ",");
        }
        if (getStdDeviation() != null) {
            sb.append("stdDeviation: " + getStdDeviation());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getCount() == null ? 0 : getCount().hashCode()) + 31) * 31) + (getAverage() == null ? 0 : getAverage().hashCode())) * 31) + (getSum() == null ? 0 : getSum().hashCode())) * 31) + (getMinimum() == null ? 0 : getMinimum().hashCode())) * 31) + (getMaximum() == null ? 0 : getMaximum().hashCode())) * 31) + (getSumOfSquares() == null ? 0 : getSumOfSquares().hashCode())) * 31) + (getVariance() == null ? 0 : getVariance().hashCode())) * 31) + (getStdDeviation() != null ? getStdDeviation().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Statistics)) {
            return false;
        }
        Statistics statistics = (Statistics) obj;
        if ((statistics.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        if (statistics.getCount() != null && !statistics.getCount().equals(getCount())) {
            return false;
        }
        if ((statistics.getAverage() == null) ^ (getAverage() == null)) {
            return false;
        }
        if (statistics.getAverage() != null && !statistics.getAverage().equals(getAverage())) {
            return false;
        }
        if ((statistics.getSum() == null) ^ (getSum() == null)) {
            return false;
        }
        if (statistics.getSum() != null && !statistics.getSum().equals(getSum())) {
            return false;
        }
        if ((statistics.getMinimum() == null) ^ (getMinimum() == null)) {
            return false;
        }
        if (statistics.getMinimum() != null && !statistics.getMinimum().equals(getMinimum())) {
            return false;
        }
        if ((statistics.getMaximum() == null) ^ (getMaximum() == null)) {
            return false;
        }
        if (statistics.getMaximum() != null && !statistics.getMaximum().equals(getMaximum())) {
            return false;
        }
        if ((statistics.getSumOfSquares() == null) ^ (getSumOfSquares() == null)) {
            return false;
        }
        if (statistics.getSumOfSquares() != null && !statistics.getSumOfSquares().equals(getSumOfSquares())) {
            return false;
        }
        if ((statistics.getVariance() == null) ^ (getVariance() == null)) {
            return false;
        }
        if (statistics.getVariance() != null && !statistics.getVariance().equals(getVariance())) {
            return false;
        }
        if ((statistics.getStdDeviation() == null) ^ (getStdDeviation() == null)) {
            return false;
        }
        return statistics.getStdDeviation() == null || statistics.getStdDeviation().equals(getStdDeviation());
    }
}
