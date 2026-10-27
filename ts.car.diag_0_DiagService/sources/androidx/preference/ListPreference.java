package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class ListPreference extends DialogPreference {
    private CharSequence[] V;
    private CharSequence[] W;
    private String X;
    private String Y;
    private boolean Z;

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements Preference.f<ListPreference> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f1035a;

        private a() {
        }

        public static a b() {
            if (f1035a == null) {
                f1035a = new a();
            }
            return f1035a;
        }

        @Override // androidx.preference.Preference.f
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CharSequence a(ListPreference listPreference) {
            return TextUtils.isEmpty(listPreference.M0()) ? listPreference.j().getString(R$string.not_set) : listPreference.M0();
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPreference, i, i2);
        this.V = androidx.core.content.res.f.q(typedArrayObtainStyledAttributes, R$styleable.ListPreference_entries, R$styleable.ListPreference_android_entries);
        this.W = androidx.core.content.res.f.q(typedArrayObtainStyledAttributes, R$styleable.ListPreference_entryValues, R$styleable.ListPreference_android_entryValues);
        int i3 = R$styleable.ListPreference_useSimpleSummaryProvider;
        if (androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i3, i3, false)) {
            v0(a.b());
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, i2);
        this.Y = androidx.core.content.res.f.o(typedArrayObtainStyledAttributes2, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        typedArrayObtainStyledAttributes2.recycle();
    }

    private int P0() {
        return K0(this.X);
    }

    public int K0(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.W) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (TextUtils.equals(this.W[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    public CharSequence[] L0() {
        return this.V;
    }

    public CharSequence M0() {
        CharSequence[] charSequenceArr;
        int iP0 = P0();
        if (iP0 < 0 || (charSequenceArr = this.V) == null) {
            return null;
        }
        return charSequenceArr[iP0];
    }

    public CharSequence[] N0() {
        return this.W;
    }

    public String O0() {
        return this.X;
    }

    @Override // androidx.preference.Preference
    protected Object Q(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    public void Q0(String str) {
        boolean z = !TextUtils.equals(this.X, str);
        if (z || !this.Z) {
            this.X = str;
            this.Z = true;
            Z(str);
            if (z) {
                I();
            }
        }
    }

    @Override // androidx.preference.Preference
    protected void T(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.T(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.T(savedState.getSuperState());
        Q0(savedState.f1034c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable U() {
        Parcelable parcelableU = super.U();
        if (F()) {
            return parcelableU;
        }
        SavedState savedState = new SavedState(parcelableU);
        savedState.f1034c = O0();
        return savedState;
    }

    @Override // androidx.preference.Preference
    public void u0(CharSequence charSequence) {
        super.u0(charSequence);
        if (charSequence == null) {
            this.Y = null;
        } else {
            this.Y = charSequence.toString();
        }
    }

    @Override // androidx.preference.Preference
    public CharSequence y() {
        if (z() != null) {
            return z().a(this);
        }
        CharSequence charSequenceM0 = M0();
        CharSequence charSequenceY = super.y();
        String str = this.Y;
        if (str == null) {
            return charSequenceY;
        }
        Object[] objArr = new Object[1];
        if (charSequenceM0 == null) {
            charSequenceM0 = "";
        }
        objArr[0] = charSequenceM0;
        String str2 = String.format(str, objArr);
        if (TextUtils.equals(str2, charSequenceY)) {
            return charSequenceY;
        }
        Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return str2;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f1034c;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1034c = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1034c);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }
}
