package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ValidateSecurityProfileBehaviorsRequest extends AmazonWebServiceRequest implements Serializable {
    private List<Behavior> behaviors;

    public List<Behavior> getBehaviors() {
        return this.behaviors;
    }

    public void setBehaviors(Collection<Behavior> collection) {
        if (collection == null) {
            this.behaviors = null;
        } else {
            this.behaviors = new ArrayList(collection);
        }
    }

    public ValidateSecurityProfileBehaviorsRequest withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public ValidateSecurityProfileBehaviorsRequest withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBehaviors() != null) {
            sb.append("behaviors: " + getBehaviors());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getBehaviors() == null ? 0 : getBehaviors().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidateSecurityProfileBehaviorsRequest)) {
            return false;
        }
        ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest = (ValidateSecurityProfileBehaviorsRequest) obj;
        if ((validateSecurityProfileBehaviorsRequest.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        return validateSecurityProfileBehaviorsRequest.getBehaviors() == null || validateSecurityProfileBehaviorsRequest.getBehaviors().equals(getBehaviors());
    }
}
