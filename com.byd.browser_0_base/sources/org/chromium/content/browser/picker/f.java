package org.chromium.content.browser.picker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.NumberPicker;
import java.util.Locale;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: MultiFieldTimePickerDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f extends AlertDialog implements DialogInterface.OnClickListener {
    public final NumberPicker e;
    public final NumberPicker f;
    public final NumberPicker g;
    public final NumberPicker h;
    public final NumberPicker i;
    public final b j;
    public final int k;
    public final int l;
    public final boolean m;

    /* JADX INFO: compiled from: MultiFieldTimePickerDialog.java */
    public static class a implements NumberPicker.Formatter {
        public final String a;

        public a(String str) {
            this.a = str;
        }

        @Override // android.widget.NumberPicker.Formatter
        public final String format(int i) {
            return String.format(Locale.getDefault(), this.a, Integer.valueOf(i));
        }
    }

    /* JADX INFO: compiled from: MultiFieldTimePickerDialog.java */
    public interface b {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x020d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public f(android.content.Context r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, boolean r28, org.chromium.content.browser.picker.d.e r29) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.picker.f.<init>(android.content.Context, int, int, int, int, int, int, int, boolean, org.chromium.content.browser.picker.d$e):void");
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        NumberPicker numberPicker = this.e;
        numberPicker.clearFocus();
        int value = numberPicker.getValue();
        NumberPicker numberPicker2 = this.f;
        numberPicker2.clearFocus();
        int value2 = numberPicker2.getValue();
        NumberPicker numberPicker3 = this.g;
        numberPicker3.clearFocus();
        int value3 = numberPicker3.getValue();
        NumberPicker numberPicker4 = this.h;
        numberPicker4.clearFocus();
        int value4 = (numberPicker4.getValue() * this.k) + this.l;
        if (!this.m) {
            NumberPicker numberPicker5 = this.i;
            numberPicker5.clearFocus();
            int value5 = numberPicker5.getValue();
            if (value == 12) {
                value = 0;
            }
            value += value5 * 12;
        }
        d.e eVar = (d.e) this.j;
        d.this.b(eVar.a, 0, 0, 0, value, value2, value3, value4, 0);
    }
}
