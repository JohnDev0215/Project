package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Behavior implements Serializable {
    private BehaviorCriteria criteria;
    private String metric;
    private MetricDimension metricDimension;
    private String name;
    private Boolean suppressAlerts;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Behavior withName(String str) {
        this.name = str;
        return this;
    }

    public String getMetric() {
        return this.metric;
    }

    public void setMetric(String str) {
        this.metric = str;
    }

    public Behavior withMetric(String str) {
        this.metric = str;
        return this;
    }

    public MetricDimension getMetricDimension() {
        return this.metricDimension;
    }

    public void setMetricDimension(MetricDimension metricDimension) {
        this.metricDimension = metricDimension;
    }

    public Behavior withMetricDimension(MetricDimension metricDimension) {
        this.metricDimension = metricDimension;
        return this;
    }

    public BehaviorCriteria getCriteria() {
        return this.criteria;
    }

    public void setCriteria(BehaviorCriteria behaviorCriteria) {
        this.criteria = behaviorCriteria;
    }

    public Behavior withCriteria(BehaviorCriteria behaviorCriteria) {
        this.criteria = behaviorCriteria;
        return this;
    }

    public Boolean isSuppressAlerts() {
        return this.suppressAlerts;
    }

    public Boolean getSuppressAlerts() {
        return this.suppressAlerts;
    }

    public void setSuppressAlerts(Boolean bool) {
        this.suppressAlerts = bool;
    }

    public Behavior withSuppressAlerts(Boolean bool) {
        this.suppressAlerts = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getMetric() != null) {
            sb.append("metric: " + getMetric() + ",");
        }
        if (getMetricDimension() != null) {
            sb.append("metricDimension: " + getMetricDimension() + ",");
        }
        if (getCriteria() != null) {
            sb.append("criteria: " + getCriteria() + ",");
        }
        if (getSuppressAlerts() != null) {
            sb.append("suppressAlerts: " + getSuppressAlerts());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getMetric() == null ? 0 : getMetric().hashCode())) * 31) + (getMetricDimension() == null ? 0 : getMetricDimension().hashCode())) * 31) + (getCriteria() == null ? 0 : getCriteria().hashCode())) * 31) + (getSuppressAlerts() != null ? getSuppressAlerts().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Behavior)) {
            return false;
        }
        Behavior behavior = (Behavior) obj;
        if ((behavior.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (behavior.getName() != null && !behavior.getName().equals(getName())) {
            return false;
        }
        if ((behavior.getMetric() == null) ^ (getMetric() == null)) {
            return false;
        }
        if (behavior.getMetric() != null && !behavior.getMetric().equals(getMetric())) {
            return false;
        }
        if ((behavior.getMetricDimension() == null) ^ (getMetricDimension() == null)) {
            return false;
        }
        if (behavior.getMetricDimension() != null && !behavior.getMetricDimension().equals(getMetricDimension())) {
            return false;
        }
        if ((behavior.getCriteria() == null) ^ (getCriteria() == null)) {
            return false;
        }
        if (behavior.getCriteria() != null && !behavior.getCriteria().equals(getCriteria())) {
            return false;
        }
        if ((behavior.getSuppressAlerts() == null) ^ (getSuppressAlerts() == null)) {
            return false;
        }
        return behavior.getSuppressAlerts() == null || behavior.getSuppressAlerts().equals(getSuppressAlerts());
    }
}
