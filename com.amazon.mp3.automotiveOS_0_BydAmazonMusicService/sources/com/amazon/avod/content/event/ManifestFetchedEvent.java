package com.amazon.avod.content.event;

import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestFetchedEvent {
    private final Manifest mManifest;

    public ManifestFetchedEvent(@Nonnull Manifest manifest) {
        this.mManifest = (Manifest) Preconditions.checkNotNull(manifest, "manifest");
    }

    public Manifest getManifest() {
        return this.mManifest;
    }
}
