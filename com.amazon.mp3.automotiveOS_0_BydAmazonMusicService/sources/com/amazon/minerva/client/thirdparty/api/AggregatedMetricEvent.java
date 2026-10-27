package com.amazon.minerva.client.thirdparty.api;

import com.amazon.minerva.client.thirdparty.aggregation.AggregatedDoubleKeyValPair;
import com.amazon.minerva.client.thirdparty.aggregation.AggregatedLongKeyValPair;
import com.amazon.minerva.client.thirdparty.metric.AggregatedDouble;
import com.amazon.minerva.client.thirdparty.metric.AggregatedLong;
import com.amazon.minerva.client.thirdparty.metric.ValueType;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AggregatedMetricEvent extends MetricEvent {
    private Map<String, AggregatedDoubleKeyValPair> mAggregatedDoubles;
    private Map<String, AggregatedLongKeyValPair> mAggregatedLongs;

    public void convertAggregatedToKeyValuePairs() {
        for (Map.Entry<String, AggregatedLongKeyValPair> entry : this.mAggregatedLongs.entrySet()) {
            AggregatedLong aggregatedValue = entry.getValue().getAggregatedValue();
            if (aggregatedValue != null) {
                if (aggregatedValue.getCounts().size() == 1 && aggregatedValue.getCounts().get(0).longValue() == 1) {
                    super.addLong(entry.getKey(), aggregatedValue.getValues().get(0).longValue());
                } else {
                    super.getKeyValuePairs().put(entry.getKey(), ValueType.AGGREGATED_INTEGER.of(aggregatedValue));
                }
            }
        }
        for (Map.Entry<String, AggregatedDoubleKeyValPair> entry2 : this.mAggregatedDoubles.entrySet()) {
            AggregatedDouble aggregatedValue2 = entry2.getValue().getAggregatedValue();
            if (aggregatedValue2 != null) {
                if (aggregatedValue2.getCounts().size() == 1 && aggregatedValue2.getCounts().get(0).doubleValue() < 2.0d) {
                    super.addDouble(entry2.getKey(), aggregatedValue2.getValues().get(0).doubleValue());
                } else {
                    super.getKeyValuePairs().put(entry2.getKey(), ValueType.AGGREGATED_FLOAT.of(aggregatedValue2));
                }
            }
        }
        this.mAggregatedLongs.clear();
        this.mAggregatedDoubles.clear();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public int getKeyValuePairCount() {
        return super.getKeyValuePairCount() + this.mAggregatedLongs.size() + this.mAggregatedDoubles.size();
    }

    @Override // com.amazon.minerva.client.thirdparty.api.MetricEvent
    public void clear() {
        this.mAggregatedLongs.clear();
        this.mAggregatedDoubles.clear();
        super.clear();
    }
}
