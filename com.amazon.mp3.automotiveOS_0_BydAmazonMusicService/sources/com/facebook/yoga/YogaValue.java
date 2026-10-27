package com.facebook.yoga;

import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public class YogaValue {
    public final YogaUnit unit;
    public final float value;
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.value = f;
        this.unit = yogaUnit;
    }

    YogaValue(float f, int i) {
        this(f, YogaUnit.fromInt(i));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof YogaValue)) {
            return false;
        }
        YogaValue yogaValue = (YogaValue) obj;
        YogaUnit yogaUnit = this.unit;
        if (yogaUnit == yogaValue.unit) {
            return yogaUnit == YogaUnit.UNDEFINED || yogaUnit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    /* JADX INFO: renamed from: com.facebook.yoga.YogaValue$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit;

        static {
            int[] iArr = new int[YogaUnit.values().length];
            $SwitchMap$com$facebook$yoga$YogaUnit = iArr;
            try {
                iArr[YogaUnit.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String toString() {
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.unit.ordinal()];
        if (i == 1) {
            return SQLiteCommandFactory.UNDEFINED;
        }
        if (i == 2) {
            return Float.toString(this.value);
        }
        if (i != 3) {
            if (i == 4) {
                return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
            }
            throw new IllegalStateException();
        }
        return this.value + "%";
    }
}
