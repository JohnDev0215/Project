package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BasePeriod extends AbstractPeriod implements Serializable {
    private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod() { // from class: org.joda.time.base.BasePeriod.1
        @Override // org.joda.time.ReadablePeriod
        public int getValue(int i) {
            return 0;
        }

        @Override // org.joda.time.ReadablePeriod
        public PeriodType getPeriodType() {
            return PeriodType.time();
        }
    };
    private final PeriodType iType;
    private final int[] iValues;

    protected BasePeriod(long j) {
        this.iType = PeriodType.standard();
        int[] iArr = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, j);
        int[] iArr2 = new int[8];
        this.iValues = iArr2;
        System.arraycopy(iArr, 0, iArr2, 4, 4);
    }

    protected BasePeriod(long j, PeriodType periodType, Chronology chronology) {
        PeriodType periodTypeCheckPeriodType = checkPeriodType(periodType);
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iType = periodTypeCheckPeriodType;
        this.iValues = chronology2.get(this, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected BasePeriod(Object obj, PeriodType periodType, Chronology chronology) {
        PeriodConverter periodConverter = ConverterManager.getInstance().getPeriodConverter(obj);
        PeriodType periodTypeCheckPeriodType = checkPeriodType(periodType == null ? periodConverter.getPeriodType(obj) : periodType);
        this.iType = periodTypeCheckPeriodType;
        if (this instanceof ReadWritablePeriod) {
            this.iValues = new int[size()];
            periodConverter.setInto((ReadWritablePeriod) this, obj, DateTimeUtils.getChronology(chronology));
        } else {
            this.iValues = new MutablePeriod(obj, periodTypeCheckPeriodType, chronology).getValues();
        }
    }

    protected BasePeriod(int[] iArr, PeriodType periodType) {
        this.iType = periodType;
        this.iValues = iArr;
    }

    protected PeriodType checkPeriodType(PeriodType periodType) {
        return DateTimeUtils.getPeriodType(periodType);
    }

    @Override // org.joda.time.ReadablePeriod
    public PeriodType getPeriodType() {
        return this.iType;
    }

    @Override // org.joda.time.ReadablePeriod
    public int getValue(int i) {
        return this.iValues[i];
    }

    private void checkAndUpdate(DurationFieldType durationFieldType, int[] iArr, int i) {
        int iIndexOf = indexOf(durationFieldType);
        if (iIndexOf != -1) {
            iArr[iIndexOf] = i;
        } else {
            if (i == 0) {
                return;
            }
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType.getName() + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            setValues(new int[size()]);
        } else {
            setPeriodInternal(readablePeriod);
        }
    }

    private void setPeriodInternal(ReadablePeriod readablePeriod) {
        int[] iArr = new int[size()];
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            checkAndUpdate(readablePeriod.getFieldType(i), iArr, readablePeriod.getValue(i));
        }
        setValues(iArr);
    }

    protected void setField(DurationFieldType durationFieldType, int i) {
        setFieldInto(this.iValues, durationFieldType, i);
    }

    protected void setFieldInto(int[] iArr, DurationFieldType durationFieldType, int i) {
        int iIndexOf = indexOf(durationFieldType);
        if (iIndexOf != -1) {
            iArr[iIndexOf] = i;
            return;
        }
        if (i != 0 || durationFieldType == null) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType + "'");
        }
    }

    protected int[] mergePeriodInto(int[] iArr, ReadablePeriod readablePeriod) {
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            checkAndUpdate(readablePeriod.getFieldType(i), iArr, readablePeriod.getValue(i));
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setValue(int i, int i2) {
        this.iValues[i] = i2;
    }

    protected void setValues(int[] iArr) {
        int[] iArr2 = this.iValues;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
    }
}
