package com.android.ex.editstyledtext;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public class EditStyledText extends EditText {

    private static class SoftKeyReceiver extends ResultReceiver {
        EditStyledText mEST;
        int mNewEnd;
        int mNewStart;

        @Override // android.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            if (i != 2) {
                Selection.setSelection(this.mEST.getText(), this.mNewStart, this.mNewEnd);
            }
        }
    }

    public static class SavedStyledTextState extends View.BaseSavedState {
        public int mBackgroundColor;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mBackgroundColor);
        }

        public String toString() {
            return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + this.mBackgroundColor + "}";
        }
    }
}
