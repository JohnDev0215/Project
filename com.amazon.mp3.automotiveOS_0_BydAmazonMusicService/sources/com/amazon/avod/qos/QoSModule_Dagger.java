package com.amazon.avod.qos;

import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.qos.internal.event.QosEventModelMediaComponent;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;

/* JADX INFO: loaded from: classes2.dex */
public class QoSModule_Dagger {
    MediaComponent provideEventModelMediaComponent(QosEventModelMediaComponent qosEventModelMediaComponent) {
        return qosEventModelMediaComponent;
    }

    EventReporterFactory provideEventReporterFactory(QosEventReporterFactory qosEventReporterFactory) {
        return qosEventReporterFactory;
    }
}
