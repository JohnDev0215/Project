package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TwoStatePreference extends Preference {
    protected boolean P;
    private CharSequence Q;
    private CharSequence R;
    private boolean S;
    private boolean T;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public boolean E0() {
        return this.P;
    }

    public void F0(boolean z) {
        boolean z2 = this.P != z;
        if (z2 || !this.S) {
            this.P = z;
            this.S = true;
            X(z);
            if (z2) {
                J(z0());
                I();
            }
        }
    }

    public void G0(boolean z) {
        this.T = z;
    }

    public void H0(CharSequence charSequence) {
        this.R = charSequence;
        if (E0()) {
            return;
        }
        I();
    }

    public void I0(CharSequence charSequence) {
        this.Q = charSequence;
        if (E0()) {
            I();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void J0(android.view.View r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.widget.TextView
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.TextView r4 = (android.widget.TextView) r4
            r0 = 1
            boolean r1 = r3.P
            r2 = 0
            if (r1 == 0) goto L1c
            java.lang.CharSequence r1 = r3.Q
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L1c
            java.lang.CharSequence r0 = r3.Q
            r4.setText(r0)
        L1a:
            r0 = r2
            goto L2e
        L1c:
            boolean r1 = r3.P
            if (r1 != 0) goto L2e
            java.lang.CharSequence r1 = r3.R
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L2e
            java.lang.CharSequence r0 = r3.R
            r4.setText(r0)
            goto L1a
        L2e:
            if (r0 == 0) goto L3e
            java.lang.CharSequence r3 = r3.y()
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 != 0) goto L3e
            r4.setText(r3)
            r0 = r2
        L3e:
            r3 = 8
            if (r0 != 0) goto L43
            goto L44
        L43:
            r2 = r3
        L44:
            int r3 = r4.getVisibility()
            if (r2 == r3) goto L4d
            r4.setVisibility(r2)
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.TwoStatePreference.J0(android.view.View):void");
    }

    protected void K0(f fVar) {
        J0(fVar.M(R.id.summary));
    }

    @Override // androidx.preference.Preference
    protected void N() {
        super.N();
        boolean z = !E0();
        if (d(Boolean.valueOf(z))) {
            F0(z);
        }
    }

    @Override // androidx.preference.Preference
    protected Object Q(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    @Override // androidx.preference.Preference
    protected void T(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.T(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.T(savedState.getSuperState());
        F0(savedState.f1055c);
    }

    @Override // androidx.preference.Preference
    protected Parcelable U() {
        Parcelable parcelableU = super.U();
        if (F()) {
            return parcelableU;
        }
        SavedState savedState = new SavedState(parcelableU);
        savedState.f1055c = E0();
        return savedState;
    }

    @Override // androidx.preference.Preference
    public boolean z0() {
        return (this.T ? this.P : !this.P) || super.z0();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1055c;

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
            this.f1055c = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1055c ? 1 : 0);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
