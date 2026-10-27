package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class EditTextPreference extends DialogPreference {
    private String V;

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements Preference.f<EditTextPreference> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f1033a;

        private a() {
        }

        public static a b() {
            if (f1033a == null) {
                f1033a = new a();
            }
            return f1033a;
        }

        @Override // androidx.preference.Preference.f
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public CharSequence a(EditTextPreference editTextPreference) {
            return TextUtils.isEmpty(editTextPreference.K0()) ? editTextPreference.j().getString(R$string.not_set) : editTextPreference.K0();
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.EditTextPreference, i, i2);
        int i3 = R$styleable.EditTextPreference_useSimpleSummaryProvider;
        if (androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i3, i3, false)) {
            v0(a.b());
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public String K0() {
        return this.V;
    }

    public void L0(String str) {
        boolean zZ0 = z0();
        this.V = str;
        Z(str);
        boolean zZ02 = z0();
        if (zZ02 != zZ0) {
            J(zZ02);
        }
        I();
    }

    @Override // androidx.preference.Preference
    protected Object Q(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    @Override // androidx.preference.Preference
    protected void T(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.T(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.T(savedState.getSuperState());
        L0(savedState.f1032c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable U() {
        Parcelable parcelableU = super.U();
        if (F()) {
            return parcelableU;
        }
        SavedState savedState = new SavedState(parcelableU);
        savedState.f1032c = K0();
        return savedState;
    }

    @Override // androidx.preference.Preference
    public boolean z0() {
        return TextUtils.isEmpty(this.V) || super.z0();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f1032c;

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
            this.f1032c = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1032c);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public EditTextPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.editTextPreferenceStyle, R.attr.editTextPreferenceStyle));
    }
}
