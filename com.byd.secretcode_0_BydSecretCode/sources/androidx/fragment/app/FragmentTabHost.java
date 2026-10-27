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
import defpackage.nb;
import defpackage.qb;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    public final ArrayList<a> b;
    public Context c;
    public nb d;
    public int e;
    public TabHost.OnTabChangeListener f;
    public a g;
    public boolean h;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String b;

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
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.b = parcel.readString();
        }
    }

    public static final class a {
        public final String a;
        public final Class<?> b;
        public final Bundle c;
        public Fragment d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.b = new ArrayList<>();
        c(context, null);
    }

    public final qb a(String str, qb qbVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.g != aVarB) {
            if (qbVar == null) {
                qbVar = this.d.a();
            }
            a aVar = this.g;
            if (aVar != null && (fragment = aVar.d) != null) {
                qbVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.d.e().a(this.c.getClassLoader(), aVarB.b.getName());
                    aVarB.d = fragmentA;
                    fragmentA.setArguments(aVarB.c);
                    qbVar.b(this.e, aVarB.d, aVarB.a);
                } else {
                    qbVar.d(fragment2);
                }
            }
            this.g = aVarB;
        }
        return qbVar;
    }

    public final a b(String str) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.b.get(i);
            if (aVar.a.equals(str)) {
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
        int size = this.b.size();
        qb qbVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.b.get(i);
            Fragment fragmentD = this.d.d(aVar.a);
            aVar.d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (qbVarA == null) {
                        qbVarA = this.d.a();
                    }
                    qbVarA.f(aVar.d);
                }
            }
        }
        this.h = true;
        qb qbVarA2 = a(currentTabTag, qbVarA);
        if (qbVarA2 != null) {
            qbVarA2.e();
            this.d.c();
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
        setCurrentTabByTag(savedState.b);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        qb qbVarA;
        if (this.h && (qbVarA = a(str, null)) != null) {
            qbVarA.e();
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
        this.b = new ArrayList<>();
        c(context, attributeSet);
    }
}
