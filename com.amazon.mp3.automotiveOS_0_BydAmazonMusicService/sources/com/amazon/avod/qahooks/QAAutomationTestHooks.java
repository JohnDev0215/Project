package com.amazon.avod.qahooks;

import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class QAAutomationTestHooks {
    private static volatile boolean sIsEnabled = false;
    private final Map<Class<QATestFeature>, QATestFeature> mFeatures;

    private static class SingletonHolder {
        private static final QAAutomationTestHooks INSTANCE;
        private static final QAAutomationTestHooks NO_OP_INSTANCE;

        private SingletonHolder() {
        }

        static {
            INSTANCE = new QAAutomationTestHooks();
            NO_OP_INSTANCE = new NoOpQAAutomationTestHooks();
        }
    }

    public static final QAAutomationTestHooks getInstance() {
        return sIsEnabled ? SingletonHolder.INSTANCE : SingletonHolder.NO_OP_INSTANCE;
    }

    private QAAutomationTestHooks() {
        this.mFeatures = new HashMap();
    }

    public static void setEnabled(boolean z) {
        sIsEnabled = z;
        if (z) {
            QALog.newQALog(CoreQAEvent.TOGGLE_QA_HOOKS).addMetric(CoreQAMetric.ENABLED, z).send();
        }
    }

    public void activateFeature(@Nonnull QATestFeature qATestFeature) {
        activateFeature(qATestFeature, null, null);
    }

    public void activateFeature(@Nonnull QATestFeature qATestFeature, @Nullable QALog.QALoggableMetric qALoggableMetric, @Nullable String str) {
        activateFeature(qATestFeature, QALog.newQALog(CoreQAEvent.FEATURE_ACTIVATION).addMetricIf((qALoggableMetric == null || str == null) ? false : true, qALoggableMetric, str));
    }

    private void activateFeature(@Nonnull QATestFeature qATestFeature, @Nonnull QALog qALog) {
        Preconditions.checkNotNull(qATestFeature, "feature");
        Preconditions.checkNotNull(qALog, "log");
        if (this.mFeatures.containsKey(qATestFeature.getClass())) {
            DLog.errorf("Attempted to activate the %s feature that has already been activated.", qATestFeature.getClass().getSimpleName());
        } else {
            qALog.addMetric((QALog.QALoggableMetric) CoreQAMetric.FEATURE, qATestFeature.getClass().getSimpleName()).send();
            this.mFeatures.put((Class<QATestFeature>) qATestFeature.getClass(), qATestFeature);
        }
    }

    @Nullable
    public QATestFeature getFeature(Class<? extends QATestFeature> cls) {
        return this.mFeatures.get(cls);
    }

    private static class NoOpQAAutomationTestHooks extends QAAutomationTestHooks {
        @Override // com.amazon.avod.qahooks.QAAutomationTestHooks
        public void activateFeature(QATestFeature qATestFeature) {
        }

        @Override // com.amazon.avod.qahooks.QAAutomationTestHooks
        public QATestFeature getFeature(Class<? extends QATestFeature> cls) {
            return null;
        }

        private NoOpQAAutomationTestHooks() {
            super();
        }
    }
}
