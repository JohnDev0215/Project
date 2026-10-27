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
    private final ArrayList<a> f1361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private g f1363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1364e;
    private TabHost.OnTabChangeListener f;
    private a g;
    private boolean h;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1365b;

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
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1365b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1365b);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1365b = parcel.readString();
        }
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1366a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f1367b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f1368c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f1369d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1361b = new ArrayList<>();
        c(context, null);
    }

    private k a(String str, k kVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.g != aVarB) {
            if (kVar == null) {
                kVar = this.f1363d.a();
            }
            a aVar = this.g;
            if (aVar != null && (fragment = aVar.f1369d) != null) {
                kVar.h(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1369d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1363d.e().a(this.f1362c.getClassLoader(), aVarB.f1367b.getName());
                    aVarB.f1369d = fragmentA;
                    fragmentA.setArguments(aVarB.f1368c);
                    kVar.b(this.f1364e, aVarB.f1369d, aVarB.f1366a);
                } else {
                    kVar.d(fragment2);
                }
            }
            this.g = aVarB;
        }
        return kVar;
    }

    private a b(String str) {
        int size = this.f1361b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f1361b.get(i);
            if (aVar.f1366a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1364e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1361b.size();
        k kVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f1361b.get(i);
            Fragment fragmentD = this.f1363d.d(aVar.f1366a);
            aVar.f1369d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f1366a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (kVarA == null) {
                        kVarA = this.f1363d.a();
                    }
                    kVarA.h(aVar.f1369d);
                }
            }
        }
        this.h = true;
        k kVarA2 = a(currentTabTag, kVarA);
        if (kVarA2 != null) {
            kVarA2.e();
            this.f1363d.c();
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
        setCurrentTabByTag(savedState.f1365b);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1365b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        k kVarA;
        if (this.h && (kVarA = a(str, null)) != null) {
            kVarA.e();
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
        this.f1361b = new ArrayList<>();
        c(context, attributeSet);
    }
}
