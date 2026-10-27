package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TabHost;
import b.l.d.p;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<a> f973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f974c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FragmentManager f975d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f976e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TabHost.OnTabChangeListener f977f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f978g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f979h;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f980b;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f980b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f980b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f980b = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f981a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f982b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f983c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f984d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f973b = new ArrayList<>();
        c(context, null);
    }

    public final p a(String str, p pVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.f978g != aVarB) {
            if (pVar == null) {
                pVar = this.f975d.m();
            }
            a aVar = this.f978g;
            if (aVar != null && (fragment = aVar.f984d) != null) {
                pVar.l(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f984d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f975d.r0().a(this.f974c.getClassLoader(), aVarB.f982b.getName());
                    aVarB.f984d = fragmentA;
                    fragmentA.setArguments(aVarB.f983c);
                    pVar.b(this.f976e, aVarB.f984d, aVarB.f981a);
                } else {
                    pVar.g(fragment2);
                }
            }
            this.f978g = aVarB;
        }
        return pVar;
    }

    public final a b(String str) {
        int size = this.f973b.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f973b.get(i2);
            if (aVar.f981a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f976e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f973b.size();
        p pVarM = null;
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f973b.get(i2);
            Fragment fragmentJ0 = this.f975d.j0(aVar.f981a);
            aVar.f984d = fragmentJ0;
            if (fragmentJ0 != null && !fragmentJ0.isDetached()) {
                if (aVar.f981a.equals(currentTabTag)) {
                    this.f978g = aVar;
                } else {
                    if (pVarM == null) {
                        pVarM = this.f975d.m();
                    }
                    pVarM.l(aVar.f984d);
                }
            }
        }
        this.f979h = true;
        p pVarA = a(currentTabTag, pVarM);
        if (pVarA != null) {
            pVarA.h();
            this.f975d.f0();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f979h = false;
    }

    @Override // android.view.View
    @Deprecated
    public void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f980b);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f980b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        p pVarA;
        if (this.f979h && (pVarA = a(str, null)) != null) {
            pVarA.h();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f977f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f977f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f973b = new ArrayList<>();
        c(context, attributeSet);
    }
}
