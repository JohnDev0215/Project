package io.objectbox.converter;

import io.objectbox.flatbuffers.ArrayReadWriteBuf;
import io.objectbox.flatbuffers.FlexBuffers;
import io.objectbox.flatbuffers.FlexBuffersBuilder;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public class StringMapConverter implements PropertyConverter<Map<String, String>, byte[]> {
    private static final AtomicReference<FlexBuffersBuilder> cachedBuilder = new AtomicReference<>();

    @Override // io.objectbox.converter.PropertyConverter
    public byte[] convertToDatabaseValue(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        FlexBuffersBuilder andSet = cachedBuilder.getAndSet(null);
        if (andSet == null) {
            andSet = new FlexBuffersBuilder(new ArrayReadWriteBuf(512), 3);
        }
        int iStartMap = andSet.startMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Map keys or values must not be null");
            }
            andSet.putString(entry.getKey(), entry.getValue());
        }
        andSet.endMap(null, iStartMap);
        ByteBuffer byteBufferFinish = andSet.finish();
        byte[] bArr = new byte[byteBufferFinish.limit()];
        byteBufferFinish.get(bArr);
        if (byteBufferFinish.limit() <= 262144) {
            andSet.clear();
            cachedBuilder.getAndSet(andSet);
        }
        return bArr;
    }

    @Override // io.objectbox.converter.PropertyConverter
    public Map<String, String> convertToEntityProperty(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        FlexBuffers.Map mapAsMap = FlexBuffers.getRoot(new ArrayReadWriteBuf(bArr, bArr.length)).asMap();
        int size = mapAsMap.size();
        FlexBuffers.KeyVector keyVectorKeys = mapAsMap.keys();
        FlexBuffers.Vector vectorValues = mapAsMap.values();
        HashMap map = new HashMap((int) ((((double) size) / 0.75d) + 1.0d));
        for (int i = 0; i < size; i++) {
            map.put(keyVectorKeys.get(i).toString(), vectorValues.get(i).asString());
        }
        return map;
    }
}
