package com.amazon.identity.mobi.common.ui;

/* JADX INFO: loaded from: classes3.dex */
public final class UIMetrics {
    public static final String DROP_MESSAGE_FROM_QUEUE_HEAD = "MAP_DROP_MESSAGE_FROM_QUEUE_HEAD";
    public static final String DROP_MESSAGE_FROM_QUEUE_TAIL = "MAP_DROP_MESSAGE_FROM_QUEUE_TAIL";

    private UIMetrics() {
    }

    public static String buildMetricForUiTask(String str, UITask... uITaskArr) {
        if (uITaskArr == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        int i = 0;
        for (UITask uITask : uITaskArr) {
            sb.append(uITask.getEntryPoint());
            if (i < uITaskArr.length - 1) {
                sb.append(":");
            }
            i++;
        }
        return sb.toString();
    }
}
