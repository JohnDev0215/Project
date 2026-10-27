package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes6.dex */
public interface DataEncoder {
    void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException;
}
