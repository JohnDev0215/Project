package com.amazon.minerva.client.thirdparty.serializer;

import com.amazon.ion.IonList;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.system.IonBinaryWriterBuilder;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IonMetricEventConverter {
    private static final IonReaderBuilder ION_READER_BUILDER = IonReaderBuilder.standard();
    private static final IonBinaryWriterBuilder ION_BINARY_WRITER_BUILDER = IonBinaryWriterBuilder.standard();
    private static final IonTextWriterBuilder ION_TEXT_WRITER_BUILDER = IonTextWriterBuilder.standard();
    private static final IonSystem ION_SYSTEM = IonSystemBuilder.standard().build();

    static byte[] convertIonMetricEventsToIonBinary(List<IonMetricEvent> list) throws IOException {
        IonList ionListConvertToIonList = convertToIonList(list);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IonWriter ionWriterBuild = ION_BINARY_WRITER_BUILDER.build(byteArrayOutputStream);
        try {
            ionListConvertToIonList.writeTo(ionWriterBuild);
            if (ionWriterBuild != null) {
                ionWriterBuild.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (ionWriterBuild != null) {
                    try {
                        ionWriterBuild.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    static List<IonMetricEvent> convertIonBinaryToIonMetricEvents(byte[] bArr) throws IOException {
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
                    IonSymbol ionSymbol = null;
                    IonSymbol ionSymbol2 = null;
                    IonTimestamp ionTimestamp = null;
                    IonString ionString = null;
                    IonStruct ionStruct = null;
                    while (ionReaderBuild.next() != null) {
                        String fieldName = ionReaderBuild.getFieldName();
                        if (fieldName.equals("metricGroupId")) {
                            ionSymbol = (IonSymbol) recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                        } else if (fieldName.equals("metricSchemaId")) {
                            ionSymbol2 = (IonSymbol) recursiveReadIonValueByIonType(ionReaderBuild, IonType.SYMBOL);
                        } else if (fieldName.equals("clientTimestamp")) {
                            ionTimestamp = (IonTimestamp) recursiveReadIonValueByIonType(ionReaderBuild, IonType.TIMESTAMP);
                        } else if (fieldName.equals("metricEventId")) {
                            ionString = (IonString) recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRING);
                        } else if (fieldName.equals("keyValuePairs")) {
                            ionStruct = (IonStruct) recursiveReadIonValueByIonType(ionReaderBuild, IonType.STRUCT);
                        }
                    }
                    arrayList.add(new IonMetricEvent(ionSymbol, ionSymbol2, ionTimestamp, ionString, ionStruct));
                    ionReaderBuild.stepOut();
                }
            }
            ionReaderBuild.stepOut();
        }
        return arrayList;
    }

    static String convertIonMetricEventsToIonText(List<IonMetricEvent> list) throws IOException {
        IonList ionListConvertToIonList = convertToIonList(list);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IonWriter ionWriterBuild = ION_TEXT_WRITER_BUILDER.build(byteArrayOutputStream);
        try {
            ionListConvertToIonList.writeTo(ionWriterBuild);
            if (ionWriterBuild != null) {
                ionWriterBuild.close();
            }
            return byteArrayOutputStream.toString();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (ionWriterBuild != null) {
                    try {
                        ionWriterBuild.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    static IonList convertToIonList(List<IonMetricEvent> list) {
        IonList ionListNewEmptyList = ION_SYSTEM.newEmptyList();
        for (IonMetricEvent ionMetricEvent : list) {
            IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
            ionStructNewEmptyStruct.put("metricGroupId", ionMetricEvent.getMetricGroupId());
            ionStructNewEmptyStruct.put("metricSchemaId", ionMetricEvent.getMetricSchemaId());
            ionStructNewEmptyStruct.put("clientTimestamp", ionMetricEvent.getClientTimestamp());
            ionStructNewEmptyStruct.put("metricEventId", ionMetricEvent.getMetricEventId());
            ionStructNewEmptyStruct.put("keyValuePairs", ionMetricEvent.getKeyValuePairs());
            ionListNewEmptyList.add((IonValue) ionStructNewEmptyStruct);
        }
        ionListNewEmptyList.add();
        return ionListNewEmptyList;
    }

    public static IonValue recursiveReadIonValueByIonType(IonReader ionReader, IonType ionType) {
        if (ionType == IonType.SYMBOL) {
            return ION_SYSTEM.newSymbol(ionReader.symbolValue());
        }
        IonType ionType2 = IonType.STRING;
        if (ionType == ionType2) {
            return ION_SYSTEM.newString(ionReader.stringValue());
        }
        if (ionType == IonType.BOOL) {
            return ION_SYSTEM.newBool(ionReader.booleanValue());
        }
        if (ionType == IonType.INT) {
            return ION_SYSTEM.newInt(ionReader.longValue());
        }
        if (ionType == ionType2) {
            return ION_SYSTEM.newString(ionReader.stringValue());
        }
        if (ionType == IonType.FLOAT) {
            return ION_SYSTEM.newFloat(ionReader.doubleValue());
        }
        if (ionType == IonType.TIMESTAMP) {
            return ION_SYSTEM.newTimestamp(ionReader.timestampValue());
        }
        if (ionType != IonType.STRUCT) {
            return null;
        }
        ionReader.stepIn();
        IonStruct ionStructNewEmptyStruct = ION_SYSTEM.newEmptyStruct();
        while (true) {
            IonType next = ionReader.next();
            if (next != null) {
                String fieldName = ionReader.getFieldName();
                IonValue ionValueRecursiveReadIonValueByIonType = recursiveReadIonValueByIonType(ionReader, next);
                if (ionValueRecursiveReadIonValueByIonType != null) {
                    ionStructNewEmptyStruct.put(fieldName, ionValueRecursiveReadIonValueByIonType);
                }
            } else {
                ionReader.stepOut();
                return ionStructNewEmptyStruct;
            }
        }
    }
}
