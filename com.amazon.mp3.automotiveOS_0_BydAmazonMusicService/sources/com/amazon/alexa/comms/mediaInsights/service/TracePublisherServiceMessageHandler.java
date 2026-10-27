package com.amazon.alexa.comms.mediaInsights.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherServiceMessageHandler extends Handler {

    @NonNull
    private TracePublisherServiceLogic tracePublisherServiceLogic;

    public TracePublisherServiceMessageHandler(@NonNull TracePublisherServiceLogic tracePublisherServiceLogic) {
        if (tracePublisherServiceLogic == null) {
            throw new IllegalArgumentException("tracePublisherServiceLogic is null");
        }
        this.tracePublisherServiceLogic = tracePublisherServiceLogic;
    }

    /* JADX INFO: renamed from: com.amazon.alexa.comms.mediaInsights.service.TracePublisherServiceMessageHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$comms$mediaInsights$common$TracePublisherServiceMessageType;

        static {
            int[] iArr = new int[TracePublisherServiceMessageType.values().length];
            $SwitchMap$com$amazon$alexa$comms$mediaInsights$common$TracePublisherServiceMessageType = iArr;
            try {
                iArr[TracePublisherServiceMessageType.SEND_TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            if (AnonymousClass1.$SwitchMap$com$amazon$alexa$comms$mediaInsights$common$TracePublisherServiceMessageType[TracePublisherServiceMessageType.valueOf(message.what).ordinal()] == 1) {
                addTrace(message);
            } else {
                Log.d("MediaInsightsPublisher", String.format("TracePublisherServiceMessageHandler unknown msg received %s", Integer.valueOf(message.what)));
                super.handleMessage(message);
            }
        } catch (Throwable th) {
            Log.e("MediaInsightsPublisher", String.format("TracePublisherServiceMessageHandler failure handling message %s", Integer.valueOf(message.what)), th);
        }
    }

    private void addTrace(Message message) {
        String string = message.getData().getString(TracePublisherServiceMessageType.TRACE_MESSAGE_KEY);
        if (string != null) {
            this.tracePublisherServiceLogic.addTrace(string);
        } else {
            Log.e("MediaInsightsPublisher", "TracePublisherServiceMessageHandler null trace received in message");
        }
    }
}
