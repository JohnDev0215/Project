package com.amazon.avod.qahooks;

import com.amazon.avod.qahooks.QALog;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;

/* JADX INFO: loaded from: classes2.dex */
public enum CoreQAEvent implements QALog.QALoggableEvent {
    TOGGLE_QA_LOGGING("QA", "ToggleLogging"),
    NONE("None", "None"),
    TOGGLE_QA_HOOKS("QA", "ToggleHooks"),
    FEATURE_ACTIVATION("QAHooks", "FeatureActivation"),
    FEATURE_DEACTIVATION("QAHooks", "FeatureDeactivation");

    private final String mEvent;
    private final String mFeature;

    public QALog.QALoggableEvent getQAEvent() {
        return this;
    }

    CoreQAEvent(String str, String str2) {
        this.mFeature = str;
        this.mEvent = str2;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mFeature + (Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mEvent);
    }
}
