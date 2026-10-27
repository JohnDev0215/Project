package com.amazon.avod.qahooks;

import android.util.Log;
import com.amazon.avod.util.DLog;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QALog {
    private static final QALog NO_OP_QALOG = new NoOpQALog();
    private static boolean sIsLoggingEnabled = false;
    private final QALoggableEvent mEvent;
    private final Map<QALoggableMetric, String> mMetrics;

    @Nonnull
    public interface QALoggableEvent {
    }

    @Nonnull
    public interface QALoggableMetric {
        QALoggableMetric getQAMetric();
    }

    private QALog(QALoggableEvent qALoggableEvent) {
        if (qALoggableEvent == null) {
            this.mEvent = CoreQAEvent.NONE;
        } else {
            this.mEvent = qALoggableEvent;
        }
        this.mMetrics = new HashMap();
        addMetric((QALoggableMetric) CoreQAMetric.TIMESTAMP, new SimpleDateFormat("HH.mm.ss.SSS", Locale.US).format(Long.valueOf(System.currentTimeMillis())));
    }

    private QALog() {
        this.mEvent = null;
        this.mMetrics = null;
    }

    public static QALog newQALog(QALoggableEvent qALoggableEvent) {
        return sIsLoggingEnabled ? new QALog(qALoggableEvent) : NO_OP_QALOG;
    }

    public QALog addMetric(QALoggableMetric qALoggableMetric, String str) {
        if (qALoggableMetric == null && str == null) {
            this.mMetrics.put(CoreQAMetric.NONE, "none");
        } else if (qALoggableMetric == null) {
            this.mMetrics.put(CoreQAMetric.NONE, str);
        } else if (str == null) {
            this.mMetrics.put(qALoggableMetric.getQAMetric(), "null");
        } else {
            this.mMetrics.put(qALoggableMetric.getQAMetric(), str);
        }
        return this;
    }

    public QALog addMetricIf(boolean z, QALoggableMetric qALoggableMetric, String str) {
        return z ? addMetric(qALoggableMetric, str) : this;
    }

    public QALog addMetric(QALoggableMetric qALoggableMetric, Object obj) {
        return addMetric(qALoggableMetric, obj.toString());
    }

    public QALog addMetric(QALoggableMetric qALoggableMetric, boolean z) {
        return addMetric(qALoggableMetric, Boolean.toString(z));
    }

    public QALog addMetric(QALoggableMetric qALoggableMetric, int i) {
        return addMetric(qALoggableMetric, Integer.toString(i));
    }

    public QALog addMetric(QALoggableMetric qALoggableMetric, long j) {
        return addMetric(qALoggableMetric, Long.toString(j));
    }

    public void send() {
        printInternal();
    }

    private void printInternal() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry<QALoggableMetric, String> entry : this.mMetrics.entrySet()) {
            tryJsonPut(jSONObject2, entry.getKey().toString(), entry.getValue());
        }
        tryJsonPut(jSONObject, this.mEvent.toString(), jSONObject2);
        Log.v("AmazonVideo.QAMessage", jSONObject.toString());
    }

    private void tryJsonPut(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            DLog.exceptionf(e, "Unable to print %s using JSONObject", this.mEvent);
        }
    }

    private static class NoOpQALog extends QALog {
        @Override // com.amazon.avod.qahooks.QALog
        public QALog addMetric(QALoggableMetric qALoggableMetric, int i) {
            return this;
        }

        @Override // com.amazon.avod.qahooks.QALog
        public QALog addMetric(QALoggableMetric qALoggableMetric, long j) {
            return this;
        }

        @Override // com.amazon.avod.qahooks.QALog
        public QALog addMetric(QALoggableMetric qALoggableMetric, Object obj) {
            return this;
        }

        @Override // com.amazon.avod.qahooks.QALog
        public QALog addMetric(QALoggableMetric qALoggableMetric, String str) {
            return this;
        }

        @Override // com.amazon.avod.qahooks.QALog
        public QALog addMetric(QALoggableMetric qALoggableMetric, boolean z) {
            return this;
        }

        @Override // com.amazon.avod.qahooks.QALog
        public void send() {
        }

        private NoOpQALog() {
            super();
        }
    }
}
