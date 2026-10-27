package org.chromium.content.browser.input;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import defpackage.AbstractC0566bn;
import defpackage.C0927iC;
import defpackage.C0982jC;
import defpackage.C1589uC;
import defpackage.DialogInterfaceOnClickListenerC1038kC;
import defpackage.DialogInterfaceOnDismissListenerC1094lC;
import defpackage.QA;
import java.util.Arrays;
import org.chromium.content.browser.picker.DateTimeSuggestion;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DateTimeChooserAndroid {
    public final long a;
    public final C1589uC b;

    public DateTimeChooserAndroid(Context context, long j) {
        this.a = j;
        this.b = new C1589uC(context, new QA(this));
    }

    public static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid windowAndroid, long j, int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        Activity activity = (Activity) windowAndroid.Q().get();
        if (activity == null) {
            return null;
        }
        DateTimeChooserAndroid dateTimeChooserAndroid = new DateTimeChooserAndroid(activity, j);
        C1589uC c1589uC = dateTimeChooserAndroid.b;
        c1589uC.a();
        if (dateTimeSuggestionArr == null) {
            c1589uC.c(i, d, d2, d3, d4);
            return dateTimeChooserAndroid;
        }
        ListView listView = new ListView(c1589uC.a);
        C0927iC c0927iC = new C0927iC(c1589uC.a, Arrays.asList(dateTimeSuggestionArr));
        listView.setAdapter((ListAdapter) c0927iC);
        listView.setOnItemClickListener(new C0982jC(c1589uC, c0927iC, i, d, d2, d3, d4));
        int i2 = AbstractC0566bn.n1;
        if (i == 12) {
            i2 = AbstractC0566bn.I2;
        } else if (i == 9 || i == 10) {
            i2 = AbstractC0566bn.o1;
        } else if (i == 11) {
            i2 = AbstractC0566bn.S1;
        } else if (i == 13) {
            i2 = AbstractC0566bn.P2;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(c1589uC.a).setTitle(i2).setView(listView).setNegativeButton(c1589uC.a.getText(R.string.cancel), new DialogInterfaceOnClickListenerC1038kC(c1589uC)).create();
        c1589uC.c = alertDialogCreate;
        alertDialogCreate.setOnDismissListener(new DialogInterfaceOnDismissListenerC1094lC(c1589uC));
        c1589uC.b = false;
        c1589uC.c.show();
        return dateTimeChooserAndroid;
    }

    public static DateTimeSuggestion[] createSuggestionsArray(int i) {
        return new DateTimeSuggestion[i];
    }

    public static void setDateTimeSuggestionAt(DateTimeSuggestion[] dateTimeSuggestionArr, int i, double d, String str, String str2) {
        dateTimeSuggestionArr[i] = new DateTimeSuggestion(d, str, str2);
    }
}
