package com.amazonaws.services.iot.model;

import com.google.android.gms.cast.MediaError;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum TopicRuleDestinationStatus {
    ENABLED("ENABLED"),
    IN_PROGRESS("IN_PROGRESS"),
    DISABLED("DISABLED"),
    ERROR(MediaError.ERROR_TYPE_ERROR),
    DELETING("DELETING");

    private static final Map<String, TopicRuleDestinationStatus> enumMap;
    private String value;

    static {
        TopicRuleDestinationStatus topicRuleDestinationStatus = ENABLED;
        TopicRuleDestinationStatus topicRuleDestinationStatus2 = IN_PROGRESS;
        TopicRuleDestinationStatus topicRuleDestinationStatus3 = DISABLED;
        TopicRuleDestinationStatus topicRuleDestinationStatus4 = ERROR;
        TopicRuleDestinationStatus topicRuleDestinationStatus5 = DELETING;
        HashMap map = new HashMap();
        enumMap = map;
        map.put("ENABLED", topicRuleDestinationStatus);
        map.put("IN_PROGRESS", topicRuleDestinationStatus2);
        map.put("DISABLED", topicRuleDestinationStatus3);
        map.put(MediaError.ERROR_TYPE_ERROR, topicRuleDestinationStatus4);
        map.put("DELETING", topicRuleDestinationStatus5);
    }

    TopicRuleDestinationStatus(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static TopicRuleDestinationStatus fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        Map<String, TopicRuleDestinationStatus> map = enumMap;
        if (map.containsKey(str)) {
            return map.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
