package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import androidx.preference.Preference;
import defpackage.fh3;

/* JADX INFO: loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    public CharSequence[] Y;
    public CharSequence[] Z;
    public String a0;
    public String b0;
    public boolean c0;

    public static final class a implements Preference.f<ListPreference> {
        public static a a;

        @Override // androidx.preference.Preference.f
        public final CharSequence a(Preference preference) {
            ListPreference listPreference = (ListPreference) preference;
            if (!TextUtils.isEmpty(listPreference.Z())) {
                return listPreference.Z();
            }
            return listPreference.e.getString(R$string.not_set);
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPreference, i, 0);
        int i3 = R$styleable.ListPreference_entries;
        int i4 = R$styleable.ListPreference_android_entries;
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(i3);
        this.Y = textArray == null ? typedArrayObtainStyledAttributes.getTextArray(i4) : textArray;
        int i5 = R$styleable.ListPreference_entryValues;
        int i6 = R$styleable.ListPreference_android_entryValues;
        CharSequence[] textArray2 = typedArrayObtainStyledAttributes.getTextArray(i5);
        this.Z = textArray2 == null ? typedArrayObtainStyledAttributes.getTextArray(i6) : textArray2;
        int i7 = R$styleable.ListPreference_useSimpleSummaryProvider;
        if (typedArrayObtainStyledAttributes.getBoolean(i7, typedArrayObtainStyledAttributes.getBoolean(i7, false))) {
            if (a.a == null) {
                a.a = new a();
            }
            this.Q = a.a;
            s();
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, 0);
        this.b0 = fh3.f(typedArrayObtainStyledAttributes2, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        typedArrayObtainStyledAttributes2.recycle();
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
        b0(savedState.e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable E() {
        this.O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.w) {
            return absSavedState;
        }
        SavedState savedState = new SavedState(absSavedState);
        savedState.e = this.a0;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void F(Object obj) {
        b0(o((String) obj));
    }

    @Override // androidx.preference.Preference
    public final void R(CharSequence charSequence) {
        super.R(charSequence);
        if (charSequence == null) {
            this.b0 = null;
        } else {
            this.b0 = charSequence.toString();
        }
    }

    public final int Y(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.Z) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (TextUtils.equals(this.Z[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    public final CharSequence Z() {
        CharSequence[] charSequenceArr;
        int iY = Y(this.a0);
        if (iY < 0 || (charSequenceArr = this.Y) == null) {
            return null;
        }
        return charSequenceArr[iY];
    }

    public void a0(CharSequence[] charSequenceArr) {
        this.Y = charSequenceArr;
    }

    public final void b0(String str) {
        boolean z = !TextUtils.equals(this.a0, str);
        if (z || !this.c0) {
            this.a0 = str;
            this.c0 = true;
            I(str);
            if (z) {
                s();
            }
        }
    }

    @Override // androidx.preference.Preference
    public final CharSequence p() {
        Preference.f fVar = this.Q;
        if (fVar != null) {
            return fVar.a(this);
        }
        CharSequence charSequenceZ = Z();
        CharSequence charSequenceP = super.p();
        String str = this.b0;
        if (str == null) {
            return charSequenceP;
        }
        Object[] objArr = new Object[1];
        if (charSequenceZ == null) {
            charSequenceZ = "";
        }
        objArr[0] = charSequenceZ;
        String str2 = String.format(str, objArr);
        if (TextUtils.equals(str2, charSequenceP)) {
            return charSequenceP;
        }
        Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return str2;
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

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }

    public ListPreference(Context context) {
        this(context, null);
    }
}
