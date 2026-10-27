package com.amazon.device.crashmanager.source;

import android.os.DropBoxManager;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultNotBootupUploadArtifactSourceFactory extends DefaultUploadArtifactSourceFactory {
    private DropBoxManager mDropBoxManager;
    private final Set<String> mDropBoxTags;
    private final Map<String, Pattern> mWhitelistedAppPatternsPerTag;

    public DefaultNotBootupUploadArtifactSourceFactory(SettingsStore settingsStore, DropBoxManager dropBoxManager, Set<String> set, Map<String, Pattern> map) {
        super(settingsStore);
        if (dropBoxManager == null) {
            throw new IllegalArgumentException("DropBoxManager must not be null.");
        }
        if (set == null) {
            throw new IllegalArgumentException("dropBoxTags must not be null.");
        }
        this.mDropBoxManager = dropBoxManager;
        this.mDropBoxTags = set;
        this.mWhitelistedAppPatternsPerTag = map;
    }

    @Override // com.amazon.device.crashmanager.source.DefaultUploadArtifactSourceFactory, com.amazon.device.crashmanager.source.ArtifactSourceFactory
    public List<ArtifactSource> constructArtifactSources() {
        List<ArtifactSource> listConstructArtifactSources = super.constructArtifactSources();
        listConstructArtifactSources.add(new DropBoxArtifactSource(this.mDropBoxManager, this.mSettingsStore, this.mDropBoxTags));
        listConstructArtifactSources.add(new WhitelistAppArtifactSource(this.mDropBoxManager, this.mSettingsStore, this.mWhitelistedAppPatternsPerTag));
        return listConstructArtifactSources;
    }
}
