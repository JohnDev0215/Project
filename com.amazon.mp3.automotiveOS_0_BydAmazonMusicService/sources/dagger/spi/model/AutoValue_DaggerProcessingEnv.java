package dagger.spi.model;

import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;

/* JADX INFO: loaded from: classes7.dex */
final class AutoValue_DaggerProcessingEnv extends DaggerProcessingEnv {
    private final XProcessingEnv xprocessing;

    AutoValue_DaggerProcessingEnv(XProcessingEnv xprocessing) {
        if (xprocessing == null) {
            throw new NullPointerException("Null xprocessing");
        }
        this.xprocessing = xprocessing;
    }

    @Override // dagger.spi.model.DaggerProcessingEnv
    public XProcessingEnv xprocessing() {
        return this.xprocessing;
    }

    public String toString() {
        return "DaggerProcessingEnv{xprocessing=" + this.xprocessing + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof DaggerProcessingEnv) {
            return this.xprocessing.equals(((DaggerProcessingEnv) o).xprocessing());
        }
        return false;
    }

    public int hashCode() {
        return this.xprocessing.hashCode() ^ 1000003;
    }
}
