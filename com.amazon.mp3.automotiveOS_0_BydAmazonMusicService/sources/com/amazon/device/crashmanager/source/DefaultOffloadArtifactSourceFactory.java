package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultOffloadArtifactSourceFactory implements ArtifactSourceFactory {
    private final DropBoxManager mDropBoxManager;
    private final Set<String> mDropBoxTags;
    private final SettingsStore mSettingsStore;

    public DefaultOffloadArtifactSourceFactory(SettingsStore settingsStore, DropBoxManager dropBoxManager, Set<String> set) {
        if (settingsStore == null) {
            throw new IllegalArgumentException("SettingsStore must not be null.");
        }
        if (dropBoxManager == null) {
            throw new IllegalArgumentException("DropBoxManager must not be null.");
        }
        if (set == null) {
            throw new IllegalArgumentException("dropBoxTags must not be null.");
        }
        this.mSettingsStore = settingsStore;
        this.mDropBoxManager = dropBoxManager;
        this.mDropBoxTags = set;
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new DropBoxArtifactSource(this.mDropBoxManager, this.mSettingsStore, this.mDropBoxTags));
        return linkedList;
    }
}
