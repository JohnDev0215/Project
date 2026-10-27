package com.byd.datasource.feature;

import androidx.annotation.NonNull;
import com.byd.car.property.CarPropertyValue;
import spi.c;

/* JADX INFO: loaded from: classes2.dex */
public class FeatureValue<V> extends CarPropertyValue<V> {

    public static class BatchBuilder<T> {
        private String[] featureKeys;
        private T[] values;

        private boolean isSameFeatureValuesType() {
            T[] tArr = this.values;
            if (tArr.length <= 1) {
                return true;
            }
            Class<?> cls = tArr[0].getClass();
            int length = this.values.length;
            for (int i = 1; i < length; i++) {
                if (this.values[i].getClass() != cls) {
                    return false;
                }
            }
            return true;
        }

        public FeatureValue[] create() {
            if (!isSameFeatureValuesType()) {
                throw new IllegalArgumentException("array types should be consistent!");
            }
            T[] tArr = this.values;
            if (tArr == null) {
                throw new IllegalArgumentException("feature values can not be null!");
            }
            String[] strArr = this.featureKeys;
            if (strArr.length != tArr.length) {
                throw new IllegalArgumentException("the length of featureKes and values must equal!");
            }
            FeatureValue[] featureValueArr = new FeatureValue[strArr.length];
            for (int i = 0; i < this.featureKeys.length; i++) {
                featureValueArr[i] = new FeatureValue(this.featureKeys[i], this.values[i]);
            }
            return featureValueArr;
        }

        public BatchBuilder featureKey(String... strArr) {
            this.featureKeys = strArr;
            return this;
        }

        public BatchBuilder setValues(T... tArr) {
            this.values = tArr;
            return this;
        }
    }

    public FeatureValue(@NonNull String str, V v) {
        super(str, v);
    }

    public static BatchBuilder batch() {
        return new BatchBuilder();
    }

    public String getFeatureId() {
        return this.mPropertyId;
    }

    public String getFeatureKey() {
        return this.mPropertyKey;
    }

    @Override // com.byd.car.property.CarPropertyValue
    @NonNull
    public V getValue() {
        return this.mValue;
    }

    public void setFeatureId(String str) {
        setPropertyId(str);
    }

    @Override // com.byd.car.property.CarPropertyValue
    public void setValue(V v) {
        this.mValue = v;
    }

    @Override // com.byd.car.property.CarPropertyValue
    public String toString() {
        StringBuilder sbK = c.k("Feature{ mPropertyId=0x");
        sbK.append(this.mPropertyKey);
        sbK.append(", mValue=");
        sbK.append(this.mValue);
        sbK.append('}');
        return sbK.toString();
    }
}
