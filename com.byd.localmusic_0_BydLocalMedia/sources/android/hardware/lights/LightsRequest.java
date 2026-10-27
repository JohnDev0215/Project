package android.hardware.lights;

import android.annotation.SystemApi;
import android.util.SparseArray;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class LightsRequest {
    final int[] mLightIds;
    final LightState[] mLightStates;

    private LightsRequest(SparseArray<LightState> sparseArray) {
        int size = sparseArray.size();
        this.mLightIds = new int[size];
        this.mLightStates = new LightState[size];
        for (int i = 0; i < size; i++) {
            this.mLightIds[i] = sparseArray.keyAt(i);
            this.mLightStates[i] = sparseArray.valueAt(i);
        }
    }

    public static final class Builder {
        private final SparseArray<LightState> mChanges = new SparseArray<>();

        public Builder setLight(Light light, LightState lightState) {
            Preconditions.checkNotNull(light);
            Preconditions.checkNotNull(lightState);
            this.mChanges.put(light.getId(), lightState);
            return this;
        }

        public Builder clearLight(Light light) {
            Preconditions.checkNotNull(light);
            this.mChanges.put(light.getId(), null);
            return this;
        }

        public LightsRequest build() {
            return new LightsRequest(this.mChanges);
        }
    }
}
