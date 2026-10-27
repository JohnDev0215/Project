package com.amazon.music.browse;

import com.amazon.layout.music.model.RootDeserializer;
import com.amazon.music.storeservice.model.LookupRequestSerializer;
import com.amazon.music.storeservice.model.LookupResponseDeserializer;
import com.amazon.musicensembleservice.ArtistDetailCatalogRequestSerializer;
import com.amazon.musicensembleservice.ArtistDetailCatalogResponseDeserializer;
import com.amazon.musicensembleservice.ArtistDetailsMetadataRequestSerializer;
import com.amazon.musicensembleservice.ArtistDetailsMetadataResponseDeserializer;
import com.amazon.musicensembleservice.BrowseHierarchyRequestSerializer;
import com.amazon.musicensembleservice.BrowseHierarchyResponseDeserializer;
import com.amazon.musicensembleservice.BrowseHierarchyV2RequestSerializer;
import com.amazon.musicensembleservice.BrowseHierarchyV2ResponseDeserializer;
import com.amazon.musicensembleservice.GetHomeRequestSerializer;
import com.amazon.musicensembleservice.GetHomeResponseDeserializer;
import com.amazon.musicensembleservice.GetTopMusicEntitiesRequestSerializer;
import com.amazon.musicensembleservice.GetTopMusicEntitiesResponseDeserializer;
import com.amazon.musicensembleservice.SeeMoreRequestSerializer;
import com.amazon.musicensembleservice.brush.PageRequestSerializer;
import com.amazon.musicensembleservice.brush.PageResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        GetHomeRequestSerializer.register(objectMapper);
        GetHomeResponseDeserializer.register(objectMapper);
        BrowseHierarchyRequestSerializer.register(objectMapper);
        BrowseHierarchyResponseDeserializer.register(objectMapper);
        BrowseHierarchyV2RequestSerializer.register(objectMapper);
        BrowseHierarchyV2ResponseDeserializer.register(objectMapper);
        GetTopMusicEntitiesRequestSerializer.register(objectMapper);
        GetTopMusicEntitiesResponseDeserializer.register(objectMapper);
        LookupRequestSerializer.register(objectMapper);
        LookupResponseDeserializer.register(objectMapper);
        ArtistDetailsMetadataRequestSerializer.register(objectMapper);
        ArtistDetailsMetadataResponseDeserializer.register(objectMapper);
        ArtistDetailCatalogRequestSerializer.register(objectMapper);
        ArtistDetailCatalogResponseDeserializer.register(objectMapper);
        SeeMoreRequestSerializer.register(objectMapper);
        RootDeserializer.register(objectMapper);
        PageRequestSerializer.register(objectMapper);
        PageResponseDeserializer.register(objectMapper);
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
