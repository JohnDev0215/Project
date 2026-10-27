package org.chromium.content.browser.input;

import android.app.Activity;
import android.content.Context;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.picker.DateTimeSuggestion;
import org.chromium.ui.picker.InputDialogContainer;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class DateTimeChooserAndroid {
    private final InputDialogContainer mInputDialogContainer;
    private final long mNativeDateTimeChooserAndroid;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeCancelDialog(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeReplaceDateTime(long j, double d);

    private DateTimeChooserAndroid(Context context, long j) {
        this.mNativeDateTimeChooserAndroid = j;
        this.mInputDialogContainer = new InputDialogContainer(context, new InputDialogContainer.InputActionDelegate() { // from class: org.chromium.content.browser.input.DateTimeChooserAndroid.1
            @Override // org.chromium.ui.picker.InputDialogContainer.InputActionDelegate
            public void replaceDateTime(double d) {
                DateTimeChooserAndroid dateTimeChooserAndroid = DateTimeChooserAndroid.this;
                dateTimeChooserAndroid.nativeReplaceDateTime(dateTimeChooserAndroid.mNativeDateTimeChooserAndroid, d);
            }

            @Override // org.chromium.ui.picker.InputDialogContainer.InputActionDelegate
            public void cancelDateTimeDialog() {
                DateTimeChooserAndroid dateTimeChooserAndroid = DateTimeChooserAndroid.this;
                dateTimeChooserAndroid.nativeCancelDialog(dateTimeChooserAndroid.mNativeDateTimeChooserAndroid);
            }
        });
    }

    private void showDialog(int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        this.mInputDialogContainer.showDialog(i, d, d2, d3, d4, dateTimeSuggestionArr);
    }

    private static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid windowAndroid, long j, int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        Activity activity = windowAndroid.getActivity().get();
        if (activity == null) {
            return null;
        }
        DateTimeChooserAndroid dateTimeChooserAndroid = new DateTimeChooserAndroid(activity, j);
        dateTimeChooserAndroid.showDialog(i, d, d2, d3, d4, dateTimeSuggestionArr);
        return dateTimeChooserAndroid;
    }

    private static DateTimeSuggestion[] createSuggestionsArray(int i) {
        return new DateTimeSuggestion[i];
    }

    private static void setDateTimeSuggestionAt(DateTimeSuggestion[] dateTimeSuggestionArr, int i, double d, String str, String str2) {
        dateTimeSuggestionArr[i] = new DateTimeSuggestion(d, str, str2);
    }
}
