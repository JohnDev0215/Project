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
import b.k.a.g;
import b.k.a.j;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<a> f389b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f390c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f391d;
    public int e;
    public TabHost.OnTabChangeListener f;
    public a g;
    public boolean h;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f392b;

        public static class a implements Parcelable.Creator<SavedState> {
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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f392b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f392b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f392b = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f393a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f394b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f395c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f396d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f389b = new ArrayList<>();
        c(context, null);
    }

    public final j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.g != aVarB) {
            if (jVar == null) {
                jVar = this.f391d.a();
            }
            a aVar = this.g;
            if (aVar != null && (fragment = aVar.f396d) != null) {
                jVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f396d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f391d.e().a(this.f390c.getClassLoader(), aVarB.f394b.getName());
                    aVarB.f396d = fragmentA;
                    fragmentA.setArguments(aVarB.f395c);
                    jVar.b(this.e, aVarB.f396d, aVarB.f393a);
                } else {
                    jVar.d(fragment2);
                }
            }
            this.g = aVarB;
        }
        return jVar;
    }

    public final a b(String str) {
        int size = this.f389b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f389b.get(i);
            if (aVar.f393a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f389b.size();
        j jVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f389b.get(i);
            Fragment fragmentD = this.f391d.d(aVar.f393a);
            aVar.f396d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f393a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f391d.a();
                    }
                    jVarA.f(aVar.f396d);
                }
            }
        }
        this.h = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.e();
            this.f391d.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
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
        setCurrentTabByTag(savedState.f392b);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f392b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        j jVarA;
        if (this.h && (jVarA = a(str, null)) != null) {
            jVarA.e();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f389b = new ArrayList<>();
        c(context, attributeSet);
    }
}
