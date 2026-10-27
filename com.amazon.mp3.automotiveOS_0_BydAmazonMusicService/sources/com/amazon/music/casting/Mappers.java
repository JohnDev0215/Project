package com.amazon.music.casting;

import com.amazon.musicplayqueueservice.client.common.CreateQueueRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.LoginRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.LoginResponseDeserializer;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequestSerializer;
import com.amazon.musicplayqueueservice.client.common.TransferQueueResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        CreateQueueRequestSerializer.register(objectMapper);
        CreateQueueResponseDeserializer.register(objectMapper);
        GetConnectionInfoRequestSerializer.register(objectMapper);
        GetConnectionInfoResponseDeserializer.register(objectMapper);
        GetDeviceListRequestSerializer.register(objectMapper);
        GetDeviceListResponseDeserializer.register(objectMapper);
        LoginRequestSerializer.register(objectMapper);
        LoginResponseDeserializer.register(objectMapper);
        TransferQueueRequestSerializer.register(objectMapper);
        TransferQueueResponseDeserializer.register(objectMapper);
    }

    private Mappers() {
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
