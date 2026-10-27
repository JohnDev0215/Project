package com.amazon.client.metrics.thirdparty.codec;

import com.amazon.client.metrics.thirdparty.CodecException;
import com.amazon.client.metrics.thirdparty.MetricBatch;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class MetricBatchToStringCodec implements MetricBatchCodec {
    @Override // com.amazon.client.metrics.thirdparty.codec.MetricBatchCodec
    public byte[] encode(MetricBatch metricBatch) throws CodecException {
        try {
            return (metricBatch.toString() + "\n").getBytes(ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new CodecException(e);
        }
    }
}
