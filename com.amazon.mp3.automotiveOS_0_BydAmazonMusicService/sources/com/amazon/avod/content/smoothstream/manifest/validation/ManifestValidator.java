package com.amazon.avod.content.smoothstream.manifest.validation;

import com.amazon.avod.content.smoothstream.manifest.Manifest;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestValidator {
    Manifest validate(Manifest manifest) throws ManifestValidationException;
}
