package com.amazon.avod.content.smoothstream.manifest.validation;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestValidatorFactory {
    private final SmoothStreamingPlaybackConfig mConfig;

    public static class NoopManifestValidator implements ManifestValidator {
        @Override // com.amazon.avod.content.smoothstream.manifest.validation.ManifestValidator
        public Manifest validate(Manifest manifest) throws ManifestValidationException {
            return manifest;
        }
    }

    public ManifestValidatorFactory() {
        this(SmoothStreamingPlaybackConfig.INSTANCE);
    }

    ManifestValidatorFactory(SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mConfig = smoothStreamingPlaybackConfig;
    }

    public ManifestValidator createManifestValidator() {
        if (this.mConfig.isManifestValidationEnabled()) {
            return new ManifestStructureValidator();
        }
        return new NoopManifestValidator();
    }
}
