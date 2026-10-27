package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import androidx.preference.Preference;
import defpackage.fh3;

/* JADX INFO: loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    public String Y;

    public interface a {
    }

    public static final class b implements Preference.f<EditTextPreference> {
        public static b a;

        @Override // androidx.preference.Preference.f
        public final CharSequence a(Preference preference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if (!TextUtils.isEmpty(editTextPreference.Y)) {
                return editTextPreference.Y;
            }
            return editTextPreference.e.getString(R$string.not_set);
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.EditTextPreference, i, 0);
        int i3 = R$styleable.EditTextPreference_useSimpleSummaryProvider;
        if (typedArrayObtainStyledAttributes.getBoolean(i3, typedArrayObtainStyledAttributes.getBoolean(i3, false))) {
            if (b.a == null) {
                b.a = new b();
            }
            this.Q = b.a;
            s();
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final Object C(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    @Override // androidx.preference.Preference
    public final void D(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.D(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.D(savedState.getSuperState());
        Y(savedState.e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable E() {
        this.O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.w) {
            return absSavedState;
        }
        SavedState savedState = new SavedState(absSavedState);
        savedState.e = this.Y;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void F(Object obj) {
        Y(o((String) obj));
    }

    @Override // androidx.preference.Preference
    public final boolean V() {
        return TextUtils.isEmpty(this.Y) || super.V();
    }

    public final void Y(String str) {
        boolean zV = V();
        this.Y = str;
        I(str);
        boolean zV2 = V();
        if (zV2 != zV) {
            u(zV2);
        }
        s();
    }

    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String e;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            public final SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.e = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.e);
        }

        public SavedState(AbsSavedState absSavedState) {
            super(absSavedState);
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.editTextPreferenceStyle, R.attr.editTextPreferenceStyle));
    }

    public EditTextPreference(Context context) {
        this(context, null);
    }

    public void setOnBindEditTextListener(a aVar) {
    }
}
