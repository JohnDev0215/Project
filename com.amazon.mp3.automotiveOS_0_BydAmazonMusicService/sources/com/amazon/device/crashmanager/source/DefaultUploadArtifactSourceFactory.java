package com.amazon.device.crashmanager.source;

import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultUploadArtifactSourceFactory implements ArtifactSourceFactory {
    protected SettingsStore mSettingsStore;

    public DefaultUploadArtifactSourceFactory(SettingsStore settingsStore) {
        if (settingsStore == null) {
            throw new IllegalArgumentException("SettingsStore must not be null.");
        }
        this.mSettingsStore = settingsStore;
    }

    @Override // com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new RamDumpArtifactSource(this.mSettingsStore));
        return linkedList;
    }
}
