package com.google.android.gms.cast.framework;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface OptionsProvider {
    @Nullable
    List<SessionProvider> getAdditionalSessionProviders(@NonNull Context context);

    @NonNull
    CastOptions getCastOptions(@NonNull Context context);
}
