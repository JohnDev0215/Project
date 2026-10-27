package com.amazon.avod.content.smoothstream.manifest.validation;

import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class ManifestStructureValidator implements ManifestValidator {
    ManifestStructureValidator() {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.validation.ManifestValidator
    public Manifest validate(Manifest manifest) throws ManifestValidationException {
        List<StreamIndex> streams = manifest.getStreams();
        if (streams.size() <= 0) {
            throw new ManifestValidationException("There is no stream inside the manifest");
        }
        boolean z = false;
        boolean z2 = false;
        for (StreamIndex streamIndex : streams) {
            if (streamIndex.isAudio()) {
                z = true;
            } else if (streamIndex.isVideo()) {
                z2 = true;
            } else {
                continue;
            }
            QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(0);
            if (sortedQualityLevels == null) {
                throw new ManifestValidationException("stream.getSortedQualityLevels returns null");
            }
            if (sortedQualityLevels.length <= 0) {
                throw new ManifestValidationException(String.format(Locale.US, "There is no quality level in stream %s", Integer.valueOf(streamIndex.getIndex())));
            }
        }
        if (!z) {
            throw new ManifestValidationException("There is no audio stream");
        }
        if (z2) {
            return manifest;
        }
        throw new ManifestValidationException("There is no video stream");
    }
}
