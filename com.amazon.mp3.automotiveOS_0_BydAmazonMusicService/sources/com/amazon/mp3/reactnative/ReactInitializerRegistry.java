package com.amazon.mp3.reactnative;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ReactInitializerRegistry {
    public static Map<Key, ReactInitializer> Registry = new HashMap();

    public enum Key {
        FEED,
        MODERATION,
        PLAYLIST_SONG_SEARCH
    }
}
