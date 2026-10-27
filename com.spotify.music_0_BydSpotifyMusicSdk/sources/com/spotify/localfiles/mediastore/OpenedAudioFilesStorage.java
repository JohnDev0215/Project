package com.spotify.localfiles.mediastore;

import android.net.Uri;
import java.util.Set;
import kotlin.collections.EmptySet;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface OpenedAudioFilesStorage {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final OpenedAudioFilesStorage createRAMStorage() {
            return new OpenedAudioFilesStorage() { // from class: com.spotify.localfiles.mediastore.OpenedAudioFilesStorage$Companion$createRAMStorage$1
                private Set<? extends Uri> uris = EmptySet.c;

                @Override // com.spotify.localfiles.mediastore.OpenedAudioFilesStorage
                public Set<Uri> load() {
                    return this.uris;
                }

                @Override // com.spotify.localfiles.mediastore.OpenedAudioFilesStorage
                public void save(Set<? extends Uri> set) {
                    v03.j(set, "uris");
                    this.uris = set;
                }
            };
        }
    }

    static OpenedAudioFilesStorage createRAMStorage() {
        return Companion.createRAMStorage();
    }

    Set<Uri> load();

    void save(Set<? extends Uri> set);
}
