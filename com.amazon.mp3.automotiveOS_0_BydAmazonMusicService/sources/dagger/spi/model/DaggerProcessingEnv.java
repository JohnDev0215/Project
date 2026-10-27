package dagger.spi.model;

import com.google.auto.value.AutoValue;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class DaggerProcessingEnv {
    public abstract XProcessingEnv xprocessing();

    public static DaggerProcessingEnv from(XProcessingEnv processingEnv) {
        return new AutoValue_DaggerProcessingEnv(processingEnv);
    }
}
