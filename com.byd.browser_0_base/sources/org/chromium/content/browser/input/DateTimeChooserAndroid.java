package org.chromium.content.browser.input;

import J.N;
import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.g01;
import defpackage.xz;
import defpackage.y60;
import java.util.Arrays;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.DateTimeSuggestion;
import org.chromium.content.browser.picker.d;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class DateTimeChooserAndroid {
    public long a;
    public final org.chromium.content.browser.picker.d b;

    public class a implements d.f {
        public a() {
        }

        public final void a(double d) {
            DateTimeChooserAndroid dateTimeChooserAndroid = DateTimeChooserAndroid.this;
            long j = dateTimeChooserAndroid.a;
            if (j == 0) {
                return;
            }
            N.MgUhdCLo(j, dateTimeChooserAndroid, d);
        }
    }

    public DateTimeChooserAndroid(Context context, long j) {
        this.a = j;
        this.b = new org.chromium.content.browser.picker.d(context, new a());
    }

    @CalledByNative
    public static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid windowAndroid, long j, int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        Context context = windowAndroid.i.get();
        if (context == null || xz.a(context) == null) {
            return null;
        }
        DateTimeChooserAndroid dateTimeChooserAndroid = new DateTimeChooserAndroid(context, j);
        org.chromium.content.browser.picker.d dVar = dateTimeChooserAndroid.b;
        dVar.a();
        if (dateTimeSuggestionArr == null) {
            dVar.c(i, d, d2, d3, d4);
            return dateTimeChooserAndroid;
        }
        Context context2 = dVar.a;
        ListView listView = new ListView(context2);
        y60 y60Var = new y60(context2, Arrays.asList(dateTimeSuggestionArr));
        listView.setAdapter((ListAdapter) y60Var);
        listView.setOnItemClickListener(new org.chromium.content.browser.picker.b(dVar, y60Var, i, d, d2, d3, d4));
        int i2 = R$string.date_picker_dialog_title;
        if (i == 12) {
            i2 = R$string.time_picker_dialog_title;
        } else if (i == 9 || i == 10) {
            i2 = R$string.date_time_picker_dialog_title;
        } else if (i == 11) {
            i2 = R$string.month_picker_dialog_title;
        } else if (i == 13) {
            i2 = R$string.week_picker_dialog_title;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context2).setTitle(i2).setView(listView).setNegativeButton(context2.getText(R.string.cancel), new g01(dVar)).create();
        dVar.c = alertDialogCreate;
        alertDialogCreate.setOnDismissListener(new org.chromium.content.browser.picker.c(dVar));
        dVar.b = false;
        dVar.c.show();
        return dateTimeChooserAndroid;
    }

    @CalledByNative
    public static DateTimeSuggestion[] createSuggestionsArray(int i) {
        return new DateTimeSuggestion[i];
    }

    @CalledByNative
    public static void setDateTimeSuggestionAt(DateTimeSuggestion[] dateTimeSuggestionArr, int i, double d, String str, String str2) {
        dateTimeSuggestionArr[i] = new DateTimeSuggestion(str, str2, d);
    }

    @CalledByNative
    public final void dismissAndDestroy() {
        this.a = 0L;
        this.b.a();
    }
}
