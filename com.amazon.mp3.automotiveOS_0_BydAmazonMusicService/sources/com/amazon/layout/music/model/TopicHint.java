package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class TopicHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.TopicHint");
    private String topic;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof TopicHint)) {
            return 1;
        }
        String topic = getTopic();
        String topic2 = ((TopicHint) hint).getTopic();
        if (topic != topic2) {
            if (topic == null) {
                return -1;
            }
            if (topic2 == null) {
                return 1;
            }
            int iCompareTo = topic.compareTo(topic2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (obj instanceof TopicHint) {
            return super.equals(obj) && internalEqualityCheck(getTopic(), ((TopicHint) obj).getTopic());
        }
        return false;
    }

    public String getTopic() {
        return this.topic;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTopic());
    }

    public void setTopic(String str) {
        this.topic = str;
    }
}
