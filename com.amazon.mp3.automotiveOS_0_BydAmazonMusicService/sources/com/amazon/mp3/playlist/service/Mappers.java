package com.amazon.mp3.playlist.service;

import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequestDeserializer;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequestSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes3.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        AppendTracksToPlaylistRequestSerializer.register(objectMapper);
        AppendTracksToPlaylistRequestDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
