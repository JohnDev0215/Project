package com.spotify.offline;

import com.spotify.esperanto.esperanto.Transport;
import java.util.List;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeOfflinePluginRegistry {
    private final List<Entry> entries;

    public static final class Entry {
        private final byte[] serializedPluginMetadata;
        private final Transport transport;

        public Entry(byte[] bArr, Transport transport) {
            v03.j(bArr, "serializedPluginMetadata");
            v03.j(transport, "transport");
            this.serializedPluginMetadata = bArr;
            this.transport = transport;
        }

        public final byte[] getSerializedPluginMetadata() {
            return this.serializedPluginMetadata;
        }

        public final Transport getTransport() {
            return this.transport;
        }
    }

    public NativeOfflinePluginRegistry(List<Entry> list) {
        v03.j(list, "entries");
        this.entries = list;
    }

    public final List<Entry> getEntries() {
        return this.entries;
    }
}
