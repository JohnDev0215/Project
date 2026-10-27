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
import java.util.ArrayList;
import r0.g;
import r0.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<a> f1334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f1336d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1337e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TabHost.OnTabChangeListener f1338f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f1339g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1340h;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1341b;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1341b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeString(this.f1341b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1341b = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1342a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f1343b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f1344c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1345d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1334b = new ArrayList<>();
        c(context, null);
    }

    public final j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.f1339g != aVarB) {
            if (jVar == null) {
                jVar = this.f1336d.a();
            }
            a aVar = this.f1339g;
            if (aVar != null && (fragment = aVar.f1345d) != null) {
                jVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1345d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1336d.e().a(this.f1335c.getClassLoader(), aVarB.f1343b.getName());
                    aVarB.f1345d = fragmentA;
                    fragmentA.setArguments(aVarB.f1344c);
                    jVar.b(this.f1337e, aVarB.f1345d, aVarB.f1342a);
                } else {
                    jVar.d(fragment2);
                }
            }
            this.f1339g = aVarB;
        }
        return jVar;
    }

    public final a b(String str) {
        int size = this.f1334b.size();
        for (int i3 = 0; i3 < size; i3++) {
            a aVar = this.f1334b.get(i3);
            if (aVar.f1342a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1337e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1334b.size();
        j jVarA = null;
        for (int i3 = 0; i3 < size; i3++) {
            a aVar = this.f1334b.get(i3);
            Fragment fragmentD = this.f1336d.d(aVar.f1342a);
            aVar.f1345d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f1342a.equals(currentTabTag)) {
                    this.f1339g = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f1336d.a();
                    }
                    jVarA.f(aVar.f1345d);
                }
            }
        }
        this.f1340h = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.e();
            this.f1336d.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1340h = false;
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
        setCurrentTabByTag(savedState.f1341b);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1341b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        j jVarA;
        if (this.f1340h && (jVarA = a(str, null)) != null) {
            jVarA.e();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1338f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1338f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1334b = new ArrayList<>();
        c(context, attributeSet);
    }
}
