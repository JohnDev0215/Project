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

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<a> f1113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private g f1115d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1116e;
    private TabHost.OnTabChangeListener f;
    private a g;
    private boolean h;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1117b;

        static class a implements Parcelable.Creator<SavedState> {
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

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1117b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1117b);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1117b = parcel.readString();
        }
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1118a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f1119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f1120c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f1121d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1113b = new ArrayList<>();
        c(context, null);
    }

    private j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.g != aVarB) {
            if (jVar == null) {
                jVar = this.f1115d.a();
            }
            a aVar = this.g;
            if (aVar != null && (fragment = aVar.f1121d) != null) {
                jVar.h(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1121d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1115d.e().a(this.f1114c.getClassLoader(), aVarB.f1119b.getName());
                    aVarB.f1121d = fragmentA;
                    fragmentA.q1(aVarB.f1120c);
                    jVar.b(this.f1116e, aVarB.f1121d, aVarB.f1118a);
                } else {
                    jVar.e(fragment2);
                }
            }
            this.g = aVarB;
        }
        return jVar;
    }

    private a b(String str) {
        int size = this.f1113b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f1113b.get(i);
            if (aVar.f1118a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1116e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1113b.size();
        j jVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f1113b.get(i);
            Fragment fragmentD = this.f1115d.d(aVar.f1118a);
            aVar.f1121d = fragmentD;
            if (fragmentD != null && !fragmentD.X()) {
                if (aVar.f1118a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f1115d.a();
                    }
                    jVarA.h(aVar.f1121d);
                }
            }
        }
        this.h = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.f();
            this.f1115d.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    @Override // android.view.View
    @Deprecated
    protected void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f1117b);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1117b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        j jVarA;
        if (this.h && (jVarA = a(str, null)) != null) {
            jVarA.f();
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
        this.f1113b = new ArrayList<>();
        c(context, attributeSet);
    }
}
