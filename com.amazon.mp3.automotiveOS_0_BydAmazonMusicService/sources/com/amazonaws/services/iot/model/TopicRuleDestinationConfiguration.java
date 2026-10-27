package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TopicRuleDestinationConfiguration implements Serializable {
    private HttpUrlDestinationConfiguration httpUrlConfiguration;
    private VpcDestinationConfiguration vpcConfiguration;

    public HttpUrlDestinationConfiguration getHttpUrlConfiguration() {
        return this.httpUrlConfiguration;
    }

    public void setHttpUrlConfiguration(HttpUrlDestinationConfiguration httpUrlDestinationConfiguration) {
        this.httpUrlConfiguration = httpUrlDestinationConfiguration;
    }

    public TopicRuleDestinationConfiguration withHttpUrlConfiguration(HttpUrlDestinationConfiguration httpUrlDestinationConfiguration) {
        this.httpUrlConfiguration = httpUrlDestinationConfiguration;
        return this;
    }

    public VpcDestinationConfiguration getVpcConfiguration() {
        return this.vpcConfiguration;
    }

    public void setVpcConfiguration(VpcDestinationConfiguration vpcDestinationConfiguration) {
        this.vpcConfiguration = vpcDestinationConfiguration;
    }

    public TopicRuleDestinationConfiguration withVpcConfiguration(VpcDestinationConfiguration vpcDestinationConfiguration) {
        this.vpcConfiguration = vpcDestinationConfiguration;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getHttpUrlConfiguration() != null) {
            sb.append("httpUrlConfiguration: " + getHttpUrlConfiguration() + ",");
        }
        if (getVpcConfiguration() != null) {
            sb.append("vpcConfiguration: " + getVpcConfiguration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getHttpUrlConfiguration() == null ? 0 : getHttpUrlConfiguration().hashCode()) + 31) * 31) + (getVpcConfiguration() != null ? getVpcConfiguration().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopicRuleDestinationConfiguration)) {
            return false;
        }
        TopicRuleDestinationConfiguration topicRuleDestinationConfiguration = (TopicRuleDestinationConfiguration) obj;
        if ((topicRuleDestinationConfiguration.getHttpUrlConfiguration() == null) ^ (getHttpUrlConfiguration() == null)) {
            return false;
        }
        if (topicRuleDestinationConfiguration.getHttpUrlConfiguration() != null && !topicRuleDestinationConfiguration.getHttpUrlConfiguration().equals(getHttpUrlConfiguration())) {
            return false;
        }
        if ((topicRuleDestinationConfiguration.getVpcConfiguration() == null) ^ (getVpcConfiguration() == null)) {
            return false;
        }
        return topicRuleDestinationConfiguration.getVpcConfiguration() == null || topicRuleDestinationConfiguration.getVpcConfiguration().equals(getVpcConfiguration());
    }
}
