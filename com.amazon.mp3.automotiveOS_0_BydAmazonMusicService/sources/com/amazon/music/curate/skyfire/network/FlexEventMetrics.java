package com.amazon.music.curate.skyfire.network;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;

/* JADX INFO: loaded from: classes4.dex */
public final class FlexEventMetrics {
    public static final String CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_REQUEST = "curateInvokeHttpSkillTaskRequest";
    public static final String CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_FAILURE = "curateInvokeHttpSkillTaskResponseFailure";
    public static final String CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_FIRST_FAILURE = "curateInvokeHttpSkillTaskResponseFirstFailure";
    public static final String CURATE_FLEX_INVOKE_HTTP_SKILLS_TASK_RESPONSE_SUCCESS = "curateInvokeHttpSkillTaskResponseSuccess";
    public static final String CURATE_FLEX_LIBRARY_LOAD_DATABASE = "curateFlexLibraryLoadDatabase";
    public static final String CURATE_FLEX_LIBRARY_LOAD_DEFAULT_TEMPLATE = "curateFlexLibraryLoadDefaultTemplate";

    public static void sendEvent(final String eventName, final String flexStr1, final String flexStr2) {
        sendEvent(eventName, flexStr1, flexStr2, null);
    }

    public static void sendEvent(final String eventName, final String flexStr1, final String flexStr2, final Float flexNum1) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(eventName).withFlexStr1(flexStr1).withFlexStr2(flexStr2).withFlexNum1(flexNum1).build());
    }
}
