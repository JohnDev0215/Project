package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MetricValue implements Serializable {
    private List<String> cidrs;
    private Long count;
    private Double number;
    private List<Double> numbers;
    private List<Integer> ports;
    private List<String> strings;

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long l) {
        this.count = l;
    }

    public MetricValue withCount(Long l) {
        this.count = l;
        return this;
    }

    public List<String> getCidrs() {
        return this.cidrs;
    }

    public void setCidrs(Collection<String> collection) {
        if (collection == null) {
            this.cidrs = null;
        } else {
            this.cidrs = new ArrayList(collection);
        }
    }

    public MetricValue withCidrs(String... strArr) {
        if (getCidrs() == null) {
            this.cidrs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.cidrs.add(str);
        }
        return this;
    }

    public MetricValue withCidrs(Collection<String> collection) {
        setCidrs(collection);
        return this;
    }

    public List<Integer> getPorts() {
        return this.ports;
    }

    public void setPorts(Collection<Integer> collection) {
        if (collection == null) {
            this.ports = null;
        } else {
            this.ports = new ArrayList(collection);
        }
    }

    public MetricValue withPorts(Integer... numArr) {
        if (getPorts() == null) {
            this.ports = new ArrayList(numArr.length);
        }
        for (Integer num : numArr) {
            this.ports.add(num);
        }
        return this;
    }

    public MetricValue withPorts(Collection<Integer> collection) {
        setPorts(collection);
        return this;
    }

    public Double getNumber() {
        return this.number;
    }

    public void setNumber(Double d) {
        this.number = d;
    }

    public MetricValue withNumber(Double d) {
        this.number = d;
        return this;
    }

    public List<Double> getNumbers() {
        return this.numbers;
    }

    public void setNumbers(Collection<Double> collection) {
        if (collection == null) {
            this.numbers = null;
        } else {
            this.numbers = new ArrayList(collection);
        }
    }

    public MetricValue withNumbers(Double... dArr) {
        if (getNumbers() == null) {
            this.numbers = new ArrayList(dArr.length);
        }
        for (Double d : dArr) {
            this.numbers.add(d);
        }
        return this;
    }

    public MetricValue withNumbers(Collection<Double> collection) {
        setNumbers(collection);
        return this;
    }

    public List<String> getStrings() {
        return this.strings;
    }

    public void setStrings(Collection<String> collection) {
        if (collection == null) {
            this.strings = null;
        } else {
            this.strings = new ArrayList(collection);
        }
    }

    public MetricValue withStrings(String... strArr) {
        if (getStrings() == null) {
            this.strings = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.strings.add(str);
        }
        return this;
    }

    public MetricValue withStrings(Collection<String> collection) {
        setStrings(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCount() != null) {
            sb.append("count: " + getCount() + ",");
        }
        if (getCidrs() != null) {
            sb.append("cidrs: " + getCidrs() + ",");
        }
        if (getPorts() != null) {
            sb.append("ports: " + getPorts() + ",");
        }
        if (getNumber() != null) {
            sb.append("number: " + getNumber() + ",");
        }
        if (getNumbers() != null) {
            sb.append("numbers: " + getNumbers() + ",");
        }
        if (getStrings() != null) {
            sb.append("strings: " + getStrings());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getCount() == null ? 0 : getCount().hashCode()) + 31) * 31) + (getCidrs() == null ? 0 : getCidrs().hashCode())) * 31) + (getPorts() == null ? 0 : getPorts().hashCode())) * 31) + (getNumber() == null ? 0 : getNumber().hashCode())) * 31) + (getNumbers() == null ? 0 : getNumbers().hashCode())) * 31) + (getStrings() != null ? getStrings().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MetricValue)) {
            return false;
        }
        MetricValue metricValue = (MetricValue) obj;
        if ((metricValue.getCount() == null) ^ (getCount() == null)) {
            return false;
        }
        if (metricValue.getCount() != null && !metricValue.getCount().equals(getCount())) {
            return false;
        }
        if ((metricValue.getCidrs() == null) ^ (getCidrs() == null)) {
            return false;
        }
        if (metricValue.getCidrs() != null && !metricValue.getCidrs().equals(getCidrs())) {
            return false;
        }
        if ((metricValue.getPorts() == null) ^ (getPorts() == null)) {
            return false;
        }
        if (metricValue.getPorts() != null && !metricValue.getPorts().equals(getPorts())) {
            return false;
        }
        if ((metricValue.getNumber() == null) ^ (getNumber() == null)) {
            return false;
        }
        if (metricValue.getNumber() != null && !metricValue.getNumber().equals(getNumber())) {
            return false;
        }
        if ((metricValue.getNumbers() == null) ^ (getNumbers() == null)) {
            return false;
        }
        if (metricValue.getNumbers() != null && !metricValue.getNumbers().equals(getNumbers())) {
            return false;
        }
        if ((metricValue.getStrings() == null) ^ (getStrings() == null)) {
            return false;
        }
        return metricValue.getStrings() == null || metricValue.getStrings().equals(getStrings());
    }
}
