package com.sun.xml.bind.v2.runtime.unmarshaller;

import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.sun.xml.bind.v2.runtime.output.Pcdata;
import com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class IntData extends Pcdata {
    private static final int[] sizeTable = {9, 99, 999, 9999, BYDAutoStatisticDevice.MILEAGE_MAX, BYDAutoStatisticDevice.STATISTIC_MILEAGE_MAX, 9999999, BYDAutoInstrumentDevice.MILE_PH_MAX, 999999999, Integer.MAX_VALUE};
    private int data;
    private int length;

    public void reset(int i) {
        this.data = i;
        if (i == Integer.MIN_VALUE) {
            this.length = 11;
        } else {
            this.length = i < 0 ? stringSizeOfInt(-i) + 1 : stringSizeOfInt(i);
        }
    }

    private static int stringSizeOfInt(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata, java.lang.CharSequence
    public String toString() {
        return String.valueOf(this.data);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return toString().charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return toString().substring(i, i2);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata
    public void writeTo(UTF8XmlOutput uTF8XmlOutput) throws IOException {
        uTF8XmlOutput.text(this.data);
    }
}
