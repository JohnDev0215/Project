package androidx.preference;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import androidx.preference.Preference;
import defpackage.fh3;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MultiSelectListPreference extends DialogPreference {
    public final CharSequence[] Y;
    public final CharSequence[] Z;
    public final HashSet a0;

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        this.a0 = new HashSet();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MultiSelectListPreference, i, 0);
        int i3 = R$styleable.MultiSelectListPreference_entries;
        int i4 = R$styleable.MultiSelectListPreference_android_entries;
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(i3);
        this.Y = textArray == null ? typedArrayObtainStyledAttributes.getTextArray(i4) : textArray;
        int i5 = R$styleable.MultiSelectListPreference_entryValues;
        int i6 = R$styleable.MultiSelectListPreference_android_entryValues;
        CharSequence[] textArray2 = typedArrayObtainStyledAttributes.getTextArray(i5);
        this.Z = textArray2 == null ? typedArrayObtainStyledAttributes.getTextArray(i6) : textArray2;
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final Object C(TypedArray typedArray, int i) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
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
        savedState.e = this.a0;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void F(Object obj) {
        Set<String> stringSet = (Set) obj;
        if (W()) {
            stringSet = this.f.c().getStringSet(this.p, stringSet);
        }
        Y(stringSet);
    }

    public final void Y(Set<String> set) {
        HashSet hashSet = this.a0;
        hashSet.clear();
        hashSet.addAll(set);
        if (W()) {
            if (!set.equals(W() ? this.f.c().getStringSet(this.p, null) : null)) {
                SharedPreferences.Editor editorB = this.f.b();
                editorB.putStringSet(this.p, set);
                if (!this.f.e) {
                    editorB.apply();
                }
            }
        }
        s();
    }

    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public HashSet e;

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
            int i = parcel.readInt();
            this.e = new HashSet();
            String[] strArr = new String[i];
            parcel.readStringArray(strArr);
            Collections.addAll(this.e, strArr);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e.size());
            HashSet hashSet = this.e;
            parcel.writeStringArray((String[]) hashSet.toArray(new String[hashSet.size()]));
        }

        public SavedState(AbsSavedState absSavedState) {
            super(absSavedState);
        }
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MultiSelectListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, fh3.a(context, R$attr.dialogPreferenceStyle, R.attr.dialogPreferenceStyle));
    }

    public MultiSelectListPreference(Context context) {
        this(context, null);
    }
}
