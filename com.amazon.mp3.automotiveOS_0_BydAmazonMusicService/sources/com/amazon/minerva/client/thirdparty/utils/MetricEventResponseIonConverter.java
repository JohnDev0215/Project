package com.amazon.minerva.client.thirdparty.utils;

import androidx.core.app.NotificationCompat;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonString;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.minerva.client.thirdparty.serializer.IonMetricEventConverter;
import com.amazon.minerva.client.thirdparty.transport.MetricEventResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MetricEventResponseIonConverter {
    private static final IonSystem ION_SYSTEM = IonSystemBuilder.standard().build();
    private static final IonReaderBuilder ION_READER_BUILDER = IonReaderBuilder.standard();
    private static final IonBinaryWriterBuilder ION_BINARY_WRITER_BUILDER = IonBinaryWriterBuilder.standard();
    private static final IonTextWriterBuilder ION_TEXT_WRITER_BUILDER = IonTextWriterBuilder.standard();

    public static Map<String, String> convertIonBinaryToResponseMap(byte[] bArr) throws IOException {
        IonReader ionReaderBuild = ION_READER_BUILDER.build(bArr);
        ArrayList arrayList = new ArrayList();
        if (ionReaderBuild.next() == IonType.LIST) {
            ionReaderBuild.stepIn();
            while (true) {
                IonType next = ionReaderBuild.next();
                if (next == null) {
                    break;
                }
                if (next == IonType.STRUCT) {
                    ionReaderBuild.stepIn();
                    IonString ionString = null;
                    IonSymbol ionSymbol = null;
                    while (ionReaderBuild.next() != null) {
                        String fieldName = ionReaderBuild.getFieldName();
                        if (fieldName.equals("metricEventId")) {
                            ionString = (IonString) IonMetricEventConverter.recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRING);
                        } else if (fieldName.equals(NotificationCompat.CATEGORY_STATUS)) {
                            ionSymbol = (IonSymbol) IonMetricEventConverter.recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                        }
                    }
                    arrayList.add(new MetricEventResponse(ionString, ionSymbol));
                    ionReaderBuild.stepOut();
                }
            }
            ionReaderBuild.stepOut();
        }
        return convertResponseArrayToResponseMap((MetricEventResponse[]) arrayList.toArray(new MetricEventResponse[0]));
    }

    private static Map<String, String> convertResponseArrayToResponseMap(MetricEventResponse[] metricEventResponseArr) {
        HashMap map = new HashMap();
        for (MetricEventResponse metricEventResponse : metricEventResponseArr) {
            map.put(metricEventResponse.getMetricEventId().stringValue(), metricEventResponse.getStatus().stringValue());
        }
        return map;
    }
}
