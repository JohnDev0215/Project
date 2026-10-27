package com.dee.app.contacts.common.utils;

import android.annotation.TargetApi;
import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes5.dex */
public enum Client {
    AlexaApp("Amazon Alexa"),
    PhotosApp("Amazon Photos"),
    MusicApp("Amazon Music"),
    Unknown("Unknown");

    private static final ImmutableMap<String, Client> CLIENTNAME_TO_CLIENT_MAP;
    private static final ContactsLogger LOG;
    private final String clientName;

    static {
        Client client = AlexaApp;
        Client client2 = PhotosApp;
        Client client3 = MusicApp;
        CLIENTNAME_TO_CLIENT_MAP = ImmutableMap.builder().put("Alexa", client).put("Amazon Alexa", client).put("AmazonAlexa", client).put("AlexaComms", client).put("PitanguiBridge", client).put("Photos", client2).put("Amazon Photos", client2).put("AmazonPhotos", client2).put("Music", client3).put("Amazon Music", client3).put("AmazonMusic", client3).put("CloudPlayer", client3).put("AmazonCloudPlayer", client3).build();
        LOG = ContactsLogger.getLogger("TAG", Client.class);
    }

    Client(String str) {
        this.clientName = str;
    }

    @TargetApi(24)
    public static Client get(String str) {
        ImmutableMap<String, Client> immutableMap = CLIENTNAME_TO_CLIENT_MAP;
        if (immutableMap.containsKey(str)) {
            return immutableMap.getOrDefault(str, Unknown);
        }
        LOG.i("Client = " + str + " is not supported.");
        return Unknown;
    }
}
