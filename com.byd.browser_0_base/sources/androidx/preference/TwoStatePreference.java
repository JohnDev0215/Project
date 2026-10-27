package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {
    public boolean S;
    public CharSequence T;
    public CharSequence U;
    public boolean V;
    public boolean W;

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public final Object C(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
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
        savedState.e = this.S;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void F(Object obj) {
        if (obj == null) {
            obj = Boolean.FALSE;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (W()) {
            zBooleanValue = this.f.c().getBoolean(this.p, zBooleanValue);
        }
        Y(zBooleanValue);
    }

    @Override // androidx.preference.Preference
    public final boolean V() {
        return (this.W ? this.S : !this.S) || super.V();
    }

    public final void Y(boolean z) {
        boolean z2 = this.S != z;
        if (z2 || !this.V) {
            this.S = z;
            this.V = true;
            if (W()) {
                boolean z3 = !z;
                if (W()) {
                    z3 = this.f.c().getBoolean(this.p, z3);
                }
                if (z != z3) {
                    SharedPreferences.Editor editorB = this.f.b();
                    editorB.putBoolean(this.p, z);
                    if (!this.f.e) {
                        editorB.apply();
                    }
                }
            }
            if (z2) {
                u(V());
                s();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Z(android.view.View r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.widget.TextView
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.TextView r4 = (android.widget.TextView) r4
            boolean r0 = r3.S
            r1 = 0
            if (r0 == 0) goto L1b
            java.lang.CharSequence r0 = r3.T
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L1b
            java.lang.CharSequence r0 = r3.T
            r4.setText(r0)
        L19:
            r0 = r1
            goto L2e
        L1b:
            boolean r0 = r3.S
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.U
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.U
            r4.setText(r0)
            goto L19
        L2d:
            r0 = 1
        L2e:
            if (r0 == 0) goto L3e
            java.lang.CharSequence r3 = r3.p()
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L3e
            r4.setText(r3)
            r0 = r1
        L3e:
            if (r0 != 0) goto L41
            goto L43
        L41:
            r1 = 8
        L43:
            int r3 = r4.getVisibility()
            if (r1 == r3) goto L4c
            r4.setVisibility(r1)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.TwoStatePreference.Z(android.view.View):void");
    }

    @Override // androidx.preference.Preference
    public void z() {
        boolean z = !this.S;
        if (h(Boolean.valueOf(z))) {
            Y(z);
        }
    }

    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean e;

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
            this.e = parcel.readInt() == 1;
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e ? 1 : 0);
        }

        public SavedState(AbsSavedState absSavedState) {
            super(absSavedState);
        }
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoStatePreference(Context context) {
        this(context, null);
    }
}
