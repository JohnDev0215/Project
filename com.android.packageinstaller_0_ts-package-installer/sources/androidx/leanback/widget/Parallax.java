package androidx.leanback.widget;

import android.util.Property;
import androidx.leanback.widget.ParallaxEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Parallax<PropertyT extends Property> {
    private final List<ParallaxEffect> mEffects;
    private float[] mFloatValues;
    final List<PropertyT> mProperties;
    final List<PropertyT> mPropertiesReadOnly;
    private int[] mValues;

    public abstract PropertyT createProperty(String str, int i);

    public abstract float getMaxValue();

    public Parallax() {
        ArrayList arrayList = new ArrayList();
        this.mProperties = arrayList;
        this.mPropertiesReadOnly = Collections.unmodifiableList(arrayList);
        this.mValues = new int[4];
        this.mFloatValues = new float[4];
        this.mEffects = new ArrayList(4);
    }

    public static class PropertyMarkerValue<PropertyT> {
        private final PropertyT mProperty;

        public PropertyMarkerValue(PropertyT property) {
            this.mProperty = property;
        }

        public PropertyT getProperty() {
            return this.mProperty;
        }
    }

    public static class IntProperty extends Property<Parallax, Integer> {
        public static final int UNKNOWN_AFTER = Integer.MAX_VALUE;
        public static final int UNKNOWN_BEFORE = Integer.MIN_VALUE;
        private final int mIndex;

        public IntProperty(String name, int index) {
            super(Integer.class, name);
            this.mIndex = index;
        }

        @Override // android.util.Property
        public final Integer get(Parallax object) {
            return Integer.valueOf(object.getIntPropertyValue(this.mIndex));
        }

        @Override // android.util.Property
        public final void set(Parallax object, Integer value) {
            object.setIntPropertyValue(this.mIndex, value.intValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final int getValue(Parallax object) {
            return object.getIntPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax object, int value) {
            object.setIntPropertyValue(this.mIndex, value);
        }

        public final PropertyMarkerValue atAbsolute(int absoluteValue) {
            return new IntPropertyMarkerValue(this, absoluteValue, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new IntPropertyMarkerValue(this, 0, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new IntPropertyMarkerValue(this, 0);
        }

        public final PropertyMarkerValue atFraction(float fractionOfMaxValue) {
            return new IntPropertyMarkerValue(this, 0, fractionOfMaxValue);
        }

        public final PropertyMarkerValue at(int offsetValue, float fractionOfMaxParentVisibleSize) {
            return new IntPropertyMarkerValue(this, offsetValue, fractionOfMaxParentVisibleSize);
        }
    }

    static class IntPropertyMarkerValue extends PropertyMarkerValue<IntProperty> {
        private final float mFactionOfMax;
        private final int mValue;

        IntPropertyMarkerValue(IntProperty property, int value) {
            this(property, value, 0.0f);
        }

        IntPropertyMarkerValue(IntProperty property, int value, float fractionOfMax) {
            super(property);
            this.mValue = value;
            this.mFactionOfMax = fractionOfMax;
        }

        final int getMarkerValue(Parallax source) {
            return this.mFactionOfMax == 0.0f ? this.mValue : this.mValue + Math.round(source.getMaxValue() * this.mFactionOfMax);
        }
    }

    public static class FloatProperty extends Property<Parallax, Float> {
        public static final float UNKNOWN_AFTER = Float.MAX_VALUE;
        public static final float UNKNOWN_BEFORE = -3.4028235E38f;
        private final int mIndex;

        public FloatProperty(String name, int index) {
            super(Float.class, name);
            this.mIndex = index;
        }

        @Override // android.util.Property
        public final Float get(Parallax object) {
            return Float.valueOf(object.getFloatPropertyValue(this.mIndex));
        }

        @Override // android.util.Property
        public final void set(Parallax object, Float value) {
            object.setFloatPropertyValue(this.mIndex, value.floatValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final float getValue(Parallax object) {
            return object.getFloatPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax object, float value) {
            object.setFloatPropertyValue(this.mIndex, value);
        }

        public final PropertyMarkerValue atAbsolute(float markerValue) {
            return new FloatPropertyMarkerValue(this, markerValue, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new FloatPropertyMarkerValue(this, 0.0f, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new FloatPropertyMarkerValue(this, 0.0f);
        }

        public final PropertyMarkerValue atFraction(float fractionOfMaxParentVisibleSize) {
            return new FloatPropertyMarkerValue(this, 0.0f, fractionOfMaxParentVisibleSize);
        }

        public final PropertyMarkerValue at(float offsetValue, float fractionOfMaxParentVisibleSize) {
            return new FloatPropertyMarkerValue(this, offsetValue, fractionOfMaxParentVisibleSize);
        }
    }

    static class FloatPropertyMarkerValue extends PropertyMarkerValue<FloatProperty> {
        private final float mFactionOfMax;
        private final float mValue;

        FloatPropertyMarkerValue(FloatProperty property, float value) {
            this(property, value, 0.0f);
        }

        FloatPropertyMarkerValue(FloatProperty property, float value, float fractionOfMax) {
            super(property);
            this.mValue = value;
            this.mFactionOfMax = fractionOfMax;
        }

        final float getMarkerValue(Parallax source) {
            return this.mFactionOfMax == 0.0f ? this.mValue : this.mValue + (source.getMaxValue() * this.mFactionOfMax);
        }
    }

    final int getIntPropertyValue(int index) {
        return this.mValues[index];
    }

    final void setIntPropertyValue(int index, int value) {
        if (index >= this.mProperties.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mValues[index] = value;
    }

    public final PropertyT addProperty(String str) {
        int size = this.mProperties.size();
        PropertyT propertyt = (PropertyT) createProperty(str, size);
        if (propertyt instanceof IntProperty) {
            int length = this.mValues.length;
            if (length == size) {
                int[] iArr = new int[length * 2];
                for (int i = 0; i < length; i++) {
                    iArr[i] = this.mValues[i];
                }
                this.mValues = iArr;
            }
            this.mValues[size] = Integer.MAX_VALUE;
        } else if (propertyt instanceof FloatProperty) {
            int length2 = this.mFloatValues.length;
            if (length2 == size) {
                float[] fArr = new float[length2 * 2];
                for (int i2 = 0; i2 < length2; i2++) {
                    fArr[i2] = this.mFloatValues[i2];
                }
                this.mFloatValues = fArr;
            }
            this.mFloatValues[size] = Float.MAX_VALUE;
        } else {
            throw new IllegalArgumentException("Invalid Property type");
        }
        this.mProperties.add(propertyt);
        return propertyt;
    }

    void verifyIntProperties() throws IllegalStateException {
        if (this.mProperties.size() < 2) {
            return;
        }
        int last = getIntPropertyValue(0);
        for (int i = 1; i < this.mProperties.size(); i++) {
            int v = getIntPropertyValue(i);
            if (v < last) {
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", Integer.valueOf(i), this.mProperties.get(i).getName(), Integer.valueOf(i - 1), this.mProperties.get(i - 1).getName()));
            }
            if (last == Integer.MIN_VALUE && v == Integer.MAX_VALUE) {
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", Integer.valueOf(i - 1), this.mProperties.get(i - 1).getName(), Integer.valueOf(i), this.mProperties.get(i).getName()));
            }
            last = v;
        }
    }

    final void verifyFloatProperties() throws IllegalStateException {
        if (this.mProperties.size() < 2) {
            return;
        }
        float last = getFloatPropertyValue(0);
        for (int i = 1; i < this.mProperties.size(); i++) {
            float v = getFloatPropertyValue(i);
            if (v < last) {
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", Integer.valueOf(i), this.mProperties.get(i).getName(), Integer.valueOf(i - 1), this.mProperties.get(i - 1).getName()));
            }
            if (last == -3.4028235E38f && v == Float.MAX_VALUE) {
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", Integer.valueOf(i - 1), this.mProperties.get(i - 1).getName(), Integer.valueOf(i), this.mProperties.get(i).getName()));
            }
            last = v;
        }
    }

    final float getFloatPropertyValue(int index) {
        return this.mFloatValues[index];
    }

    final void setFloatPropertyValue(int index, float value) {
        if (index >= this.mProperties.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mFloatValues[index] = value;
    }

    public final List<PropertyT> getProperties() {
        return this.mPropertiesReadOnly;
    }

    public void updateValues() {
        for (int i = 0; i < this.mEffects.size(); i++) {
            this.mEffects.get(i).performMapping(this);
        }
    }

    public List<ParallaxEffect> getEffects() {
        return this.mEffects;
    }

    public void removeEffect(ParallaxEffect effect) {
        this.mEffects.remove(effect);
    }

    public void removeAllEffects() {
        this.mEffects.clear();
    }

    public ParallaxEffect addEffect(PropertyMarkerValue... ranges) {
        ParallaxEffect effect;
        if (ranges[0].getProperty() instanceof IntProperty) {
            effect = new ParallaxEffect.IntEffect();
        } else {
            effect = new ParallaxEffect.FloatEffect();
        }
        effect.setPropertyRanges(ranges);
        this.mEffects.add(effect);
        return effect;
    }
}
