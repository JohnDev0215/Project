package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import androidx.preference.Preference;
import androidx.preference.e;
import defpackage.wm2;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {
    public final wm2<String, Long> S;
    public final Handler T;
    public final ArrayList U;
    public boolean V;
    public int W;
    public boolean X;
    public int Y;
    public b Z;
    public final a a0;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (this) {
                PreferenceGroup.this.S.clear();
            }
        }
    }

    public interface b {
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.S = new wm2<>();
        this.T = new Handler(Looper.getMainLooper());
        this.V = true;
        this.W = 0;
        this.X = false;
        this.Y = Integer.MAX_VALUE;
        this.Z = null;
        this.a0 = new a();
        this.U = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PreferenceGroup, i, i2);
        int i3 = R$styleable.PreferenceGroup_orderingFromXml;
        this.V = typedArrayObtainStyledAttributes.getBoolean(i3, typedArrayObtainStyledAttributes.getBoolean(i3, true));
        int i4 = R$styleable.PreferenceGroup_initialExpandedChildrenCount;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            f0(typedArrayObtainStyledAttributes.getInt(i4, typedArrayObtainStyledAttributes.getInt(i4, Integer.MAX_VALUE)));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final void B() {
        X();
        this.X = false;
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            a0(i).B();
        }
    }

    @Override // androidx.preference.Preference
    public final void D(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.D(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.Y = savedState.e;
        super.D(savedState.getSuperState());
    }

    @Override // androidx.preference.Preference
    public final Parcelable E() {
        this.O = true;
        return new SavedState(AbsSavedState.EMPTY_STATE, this.Y);
    }

    public final void Y(Preference preference) {
        long jLongValue;
        if (this.U.contains(preference)) {
            return;
        }
        if (preference.p != null) {
            PreferenceGroup preferenceGroup = this;
            while (true) {
                PreferenceGroup preferenceGroup2 = preferenceGroup.N;
                if (preferenceGroup2 == null) {
                    break;
                } else {
                    preferenceGroup = preferenceGroup2;
                }
            }
            String str = preference.p;
            if (preferenceGroup.Z(str) != null) {
                Log.e("PreferenceGroup", "Found duplicated key: \"" + str + "\". This can cause unintended behaviour, please use unique keys for every preference.");
            }
        }
        if (preference.k == Integer.MAX_VALUE) {
            if (this.V) {
                int i = this.W;
                this.W = i + 1;
                preference.N(i);
            }
            if (preference instanceof PreferenceGroup) {
                ((PreferenceGroup) preference).V = this.V;
            }
        }
        int iBinarySearch = Collections.binarySearch(this.U, preference);
        if (iBinarySearch < 0) {
            iBinarySearch = (iBinarySearch * (-1)) - 1;
        }
        boolean zV = V();
        if (preference.A == zV) {
            preference.A = !zV;
            preference.u(preference.V());
            preference.s();
        }
        synchronized (this) {
            this.U.add(iBinarySearch, preference);
        }
        PreferenceManager preferenceManager = this.f;
        String str2 = preference.p;
        if (str2 == null || !this.S.containsKey(str2)) {
            synchronized (preferenceManager) {
                jLongValue = preferenceManager.b;
                preferenceManager.b = 1 + jLongValue;
            }
        } else {
            jLongValue = this.S.get(str2).longValue();
            this.S.remove(str2);
        }
        preference.g = jLongValue;
        preference.h = true;
        try {
            preference.x(preferenceManager);
            preference.h = false;
            if (preference.N != null) {
                throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
            }
            preference.N = this;
            if (this.X) {
                preference.v();
            }
            Preference.b bVar = this.L;
            if (bVar != null) {
                e eVar = (e) bVar;
                Handler handler = eVar.l;
                e.a aVar = eVar.m;
                handler.removeCallbacks(aVar);
                handler.post(aVar);
            }
        } catch (Throwable th) {
            preference.h = false;
            throw th;
        }
    }

    public final <T extends Preference> T Z(CharSequence charSequence) {
        T t;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (TextUtils.equals(this.p, charSequence)) {
            return this;
        }
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            PreferenceGroup preferenceGroup = (T) a0(i);
            if (TextUtils.equals(preferenceGroup.p, charSequence)) {
                return preferenceGroup;
            }
            if ((preferenceGroup instanceof PreferenceGroup) && (t = (T) preferenceGroup.Z(charSequence)) != null) {
                return t;
            }
        }
        return null;
    }

    public final Preference a0(int i) {
        return (Preference) this.U.get(i);
    }

    public final int b0() {
        return this.U.size();
    }

    public final void c0() {
        synchronized (this) {
            ArrayList arrayList = this.U;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else {
                    e0((Preference) arrayList.get(0));
                }
            }
        }
        Preference.b bVar = this.L;
        if (bVar != null) {
            e eVar = (e) bVar;
            Handler handler = eVar.l;
            e.a aVar = eVar.m;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    public final void d0(Preference preference) {
        e0(preference);
        Preference.b bVar = this.L;
        if (bVar != null) {
            e eVar = (e) bVar;
            Handler handler = eVar.l;
            e.a aVar = eVar.m;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    public final boolean e0(Preference preference) {
        boolean zRemove;
        synchronized (this) {
            try {
                preference.X();
                if (preference.N == this) {
                    preference.N = null;
                }
                zRemove = this.U.remove(preference);
                if (zRemove) {
                    String str = preference.p;
                    if (str != null) {
                        this.S.put(str, Long.valueOf(preference.n()));
                        this.T.removeCallbacks(this.a0);
                        this.T.post(this.a0);
                    }
                    if (this.X) {
                        preference.B();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zRemove;
    }

    public final void f0(int i) {
        if (i != Integer.MAX_VALUE && !(!TextUtils.isEmpty(this.p))) {
            Log.e("PreferenceGroup", getClass().getSimpleName().concat(" should have a key defined if it contains an expandable preference"));
        }
        this.Y = i;
    }

    @Override // androidx.preference.Preference
    public final void j(Bundle bundle) {
        super.j(bundle);
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            a0(i).j(bundle);
        }
    }

    @Override // androidx.preference.Preference
    public final void l(Bundle bundle) {
        super.l(bundle);
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            a0(i).l(bundle);
        }
    }

    public void setOnExpandButtonClickListener(b bVar) {
        this.Z = bVar;
    }

    @Override // androidx.preference.Preference
    public final void u(boolean z) {
        super.u(z);
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            Preference preferenceA0 = a0(i);
            if (preferenceA0.A == z) {
                preferenceA0.A = !z;
                preferenceA0.u(preferenceA0.V());
                preferenceA0.s();
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void v() {
        super.v();
        this.X = true;
        int iB0 = b0();
        for (int i = 0; i < iB0; i++) {
            a0(i).v();
        }
    }

    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public final int e;

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
            this.e = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.e);
        }

        public SavedState(AbsSavedState absSavedState, int i) {
            super(absSavedState);
            this.e = i;
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
