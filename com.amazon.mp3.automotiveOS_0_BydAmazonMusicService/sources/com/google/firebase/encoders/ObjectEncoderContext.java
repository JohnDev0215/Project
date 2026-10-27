package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public interface ObjectEncoderContext {
    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, int i) throws IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, long j) throws IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull FieldDescriptor fieldDescriptor, @Nullable Object obj) throws IOException;
}
