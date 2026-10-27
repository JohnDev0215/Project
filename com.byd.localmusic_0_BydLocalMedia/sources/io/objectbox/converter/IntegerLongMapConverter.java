package io.objectbox.converter;

import io.objectbox.flatbuffers.FlexBuffers;

/* JADX INFO: loaded from: classes5.dex */
public class IntegerLongMapConverter extends IntegerFlexMapConverter {
    @Override // io.objectbox.converter.FlexObjectConverter
    protected boolean shouldRestoreAsLong(FlexBuffers.Reference reference) {
        return true;
    }
}
