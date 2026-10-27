package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.preference.Preference;
import b.a.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PreferenceGroup extends Preference {
    final g<String, Long> P;
    private List<Preference> Q;
    private int R;
    private a S;

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface a {
        void a();
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.P = new g<>();
        new Handler();
        this.R = Integer.MAX_VALUE;
        this.S = null;
        this.Q = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PreferenceGroup, i, i2);
        int i3 = R$styleable.PreferenceGroup_orderingFromXml;
        androidx.core.content.res.f.b(typedArrayObtainStyledAttributes, i3, i3, true);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.PreferenceGroup_initialExpandedChildrenCount)) {
            int i4 = R$styleable.PreferenceGroup_initialExpandedChildrenCount;
            K0(androidx.core.content.res.f.d(typedArrayObtainStyledAttributes, i4, i4, Integer.MAX_VALUE));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Nullable
    public <T extends Preference> T E0(@NonNull CharSequence charSequence) {
        T t;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (TextUtils.equals(p(), charSequence)) {
            return this;
        }
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            PreferenceGroup preferenceGroup = (T) H0(i);
            if (TextUtils.equals(preferenceGroup.p(), charSequence)) {
                return preferenceGroup;
            }
            if ((preferenceGroup instanceof PreferenceGroup) && (t = (T) preferenceGroup.E0(charSequence)) != null) {
                return t;
            }
        }
        return null;
    }

    public int F0() {
        return this.R;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public a G0() {
        return this.S;
    }

    public Preference H0(int i) {
        return this.Q.get(i);
    }

    public int I0() {
        return this.Q.size();
    }

    @Override // androidx.preference.Preference
    public void J(boolean z) {
        super.J(z);
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            H0(i).S(this, z);
        }
    }

    protected boolean J0() {
        return true;
    }

    public void K0(int i) {
        if (i != Integer.MAX_VALUE && !C()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.R = i;
    }

    @Override // androidx.preference.Preference
    public void L() {
        super.L();
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            H0(i).L();
        }
    }

    void L0() {
        synchronized (this) {
            Collections.sort(this.Q);
        }
    }

    @Override // androidx.preference.Preference
    public void P() {
        super.P();
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            H0(i).P();
        }
    }

    @Override // androidx.preference.Preference
    protected void T(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.T(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.R = savedState.f1049c;
        super.T(savedState.getSuperState());
    }

    @Override // androidx.preference.Preference
    protected Parcelable U() {
        return new SavedState(super.U(), this.R);
    }

    @Override // androidx.preference.Preference
    protected void g(Bundle bundle) {
        super.g(bundle);
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            H0(i).g(bundle);
        }
    }

    @Override // androidx.preference.Preference
    protected void h(Bundle bundle) {
        super.h(bundle);
        int iI0 = I0();
        for (int i = 0; i < iI0; i++) {
            H0(i).h(bundle);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1049c;

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
            this.f1049c = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1049c);
        }

        SavedState(Parcelable parcelable, int i) {
            super(parcelable);
            this.f1049c = i;
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
