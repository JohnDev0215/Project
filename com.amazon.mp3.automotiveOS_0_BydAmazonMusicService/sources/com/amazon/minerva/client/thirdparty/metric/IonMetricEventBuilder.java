package com.amazon.minerva.client.thirdparty.metric;

import android.util.Log;
import com.amazon.ion.IonList;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class IonMetricEventBuilder {
    private static final String TAG = "IonMetricEventBuilder";
    private IonStruct mKeyValuePairs;
    private Integer mLocalOffsetMinutes;
    private IonString mMetricEventId;
    private IonSymbol mMetricGroupId;
    private String mRegion;
    private IonSymbol mSchemaId;
    private Long mUtcTimestamp;
    static final IonSystem ION_SYSTEM = IonSystemBuilder.standard().build();
    static final ValueFactory VALUE_FACTORY = IonSystemBuilder.standard().build();
    boolean dataPointsValid = true;
    long totalDataPointSize = 0;

    public IonMetricEventBuilder withRegion(String str) {
        Objects.requireNonNull(str, "parameter region can not be null.");
        this.mRegion = str;
        return this;
    }

    public IonMetricEventBuilder withMetricGroupId(String str) {
        Objects.requireNonNull(str, "parameter metricGroupId can not be null.");
        this.mMetricGroupId = ION_SYSTEM.newSymbol(str);
        return this;
    }

    public IonMetricEventBuilder withSchemaId(String str) {
        Objects.requireNonNull(str, "parameter schemaId can not be null.");
        this.mSchemaId = ION_SYSTEM.newSymbol(str);
        return this;
    }

    public IonMetricEventBuilder withMetricEventId(String str) {
        Objects.requireNonNull(str, "parameter metricEventId can not be null.");
        this.mMetricEventId = ION_SYSTEM.newString(str);
        return this;
    }

    public IonMetricEventBuilder withUtcTimestamp(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("parameter utcTimestamp can not be negative number.");
        }
        this.mUtcTimestamp = Long.valueOf(j);
        return this;
    }

    public IonMetricEventBuilder withLocalOffsetMinutes(int i) {
        this.mLocalOffsetMinutes = Integer.valueOf(i);
        return this;
    }

    public IonMetricEventBuilder withDataPoints(List<DataPoint> list) {
        Objects.requireNonNull(list, "parameter dataPoints can not be null.");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("parameter dataPoints can not be empty.");
        }
        this.totalDataPointSize = 0L;
        this.mKeyValuePairs = ION_SYSTEM.newEmptyStruct();
        Iterator<DataPoint> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DataPoint next = it.next();
            if (next == null) {
                this.dataPointsValid = false;
                Log.e(TAG, "datapoint is null.");
                break;
            }
            SizedIonValue sizedIonValueCreateIonValue = null;
            try {
                sizedIonValueCreateIonValue = createIonValue(next.getType(), next.getValue());
            } catch (Exception unused) {
                Log.e(TAG, String.format("exception happened when creating IonValue from datapoint (%s,%s,%s)", next.getName(), next.getType(), next.getValue()));
            }
            if (sizedIonValueCreateIonValue != null) {
                this.mKeyValuePairs.put(next.getName(), sizedIonValueCreateIonValue.getIonValue());
                this.totalDataPointSize += ((long) next.getName().length()) + sizedIonValueCreateIonValue.getSize();
            } else {
                this.dataPointsValid = false;
                break;
            }
        }
        return this;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder.SizedIonValue createIonValue(com.amazon.minerva.client.thirdparty.metric.ValueType r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder.createIonValue(com.amazon.minerva.client.thirdparty.metric.ValueType, java.lang.String):com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder$SizedIonValue");
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType;

        static {
            int[] iArr = new int[ValueType.values().length];
            $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType = iArr;
            try {
                iArr[ValueType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.TIMESTAMP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.STRING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.SYMBOL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.AGGREGATED_INTEGER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$minerva$client$thirdparty$metric$ValueType[ValueType.AGGREGATED_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    static IonValue createAggregatedLongIonValue(AggregatedLong aggregatedLong) {
        IonStruct ionStructNewEmptyStruct = VALUE_FACTORY.newEmptyStruct();
        IonList ionListNewEmptyList = ionStructNewEmptyStruct.add("values").newEmptyList();
        IonList ionListNewEmptyList2 = ionStructNewEmptyStruct.add("counts").newEmptyList();
        List<Long> values = aggregatedLong.getValues();
        List<Long> counts = aggregatedLong.getCounts();
        for (int i = 0; i < values.size(); i++) {
            ionListNewEmptyList.add().newInt(values.get(i));
            ionListNewEmptyList2.add().newInt(counts.get(i));
        }
        return ionStructNewEmptyStruct;
    }

    static IonValue createAggregatedDoubleIonValue(AggregatedDouble aggregatedDouble) {
        IonStruct ionStructNewEmptyStruct = VALUE_FACTORY.newEmptyStruct();
        IonList ionListNewEmptyList = ionStructNewEmptyStruct.add("values").newEmptyList();
        IonList ionListNewEmptyList2 = ionStructNewEmptyStruct.add("counts").newEmptyList();
        List<Double> values = aggregatedDouble.getValues();
        List<Double> counts = aggregatedDouble.getCounts();
        for (int i = 0; i < values.size(); i++) {
            ionListNewEmptyList.add().newFloat(values.get(i).doubleValue());
            ionListNewEmptyList2.add().newFloat(counts.get(i).doubleValue());
        }
        return ionStructNewEmptyStruct;
    }

    public IonMetricEvent build() {
        if (this.mRegion == null) {
            throw new IllegalStateException("withRegion should be called before build.");
        }
        IonSymbol ionSymbol = this.mMetricGroupId;
        if (ionSymbol == null) {
            throw new IllegalStateException("withMetricGroupId should be called before build.");
        }
        IonSymbol ionSymbol2 = this.mSchemaId;
        if (ionSymbol2 == null) {
            throw new IllegalStateException("withSchemaId should be called before build.");
        }
        if (this.mMetricEventId == null) {
            throw new IllegalStateException("withMetricEventId should be called before build.");
        }
        Long l = this.mUtcTimestamp;
        if (l == null) {
            throw new IllegalStateException("withUtcTimestamp should be called before build.");
        }
        if (this.mLocalOffsetMinutes == null) {
            throw new IllegalStateException("withLocalOffsetMinutes should be called before build.");
        }
        if (this.mKeyValuePairs == null) {
            throw new IllegalStateException("withDataPoints should be called before build.");
        }
        if (!this.dataPointsValid) {
            throw new IllegalStateException("There are invalid datapoints.");
        }
        IonMetricEvent ionMetricEvent = new IonMetricEvent(ionSymbol, ionSymbol2, ION_SYSTEM.newTimestamp(com.amazon.ion.Timestamp.forMillis(l.longValue(), this.mLocalOffsetMinutes)), this.mMetricEventId, this.mKeyValuePairs);
        ionMetricEvent.setRegion(this.mRegion);
        ionMetricEvent.setSizeInByte(this.totalDataPointSize + 128);
        return ionMetricEvent;
    }

    public static class SizedIonValue {
        private final IonValue ionValue;
        private final long size;

        public SizedIonValue(IonValue ionValue, long j) {
            this.ionValue = ionValue;
            this.size = j;
        }

        public IonValue getIonValue() {
            return this.ionValue;
        }

        public long getSize() {
            return this.size;
        }
    }
}
