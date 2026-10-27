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
import v0.g;
import v0.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList<a> f1419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f1420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g f1421f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1422g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TabHost.OnTabChangeListener f1423h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f1424i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1425j;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f1426d;

        public static class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1426d + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeString(this.f1426d);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1426d = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1427a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f1428b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f1429c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f1430d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1419d = new ArrayList<>();
        c(context, null);
    }

    public final j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.f1424i != aVarB) {
            if (jVar == null) {
                jVar = this.f1421f.a();
            }
            a aVar = this.f1424i;
            if (aVar != null && (fragment = aVar.f1430d) != null) {
                jVar.h(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1430d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1421f.f().a(this.f1420e.getClassLoader(), aVarB.f1428b.getName());
                    aVarB.f1430d = fragmentA;
                    fragmentA.setArguments(aVarB.f1429c);
                    jVar.b(this.f1422g, aVarB.f1430d, aVarB.f1427a);
                } else {
                    jVar.e(fragment2);
                }
            }
            this.f1424i = aVarB;
        }
        return jVar;
    }

    public final a b(String str) {
        int size = this.f1419d.size();
        for (int i8 = 0; i8 < size; i8++) {
            a aVar = this.f1419d.get(i8);
            if (aVar.f1427a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1422g = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1419d.size();
        j jVarA = null;
        for (int i8 = 0; i8 < size; i8++) {
            a aVar = this.f1419d.get(i8);
            Fragment fragmentD = this.f1421f.d(aVar.f1427a);
            aVar.f1430d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f1427a.equals(currentTabTag)) {
                    this.f1424i = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f1421f.a();
                    }
                    jVarA.h(aVar.f1430d);
                }
            }
        }
        this.f1425j = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.f();
            this.f1421f.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1425j = false;
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
        setCurrentTabByTag(savedState.f1426d);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1426d = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        j jVarA;
        if (this.f1425j && (jVarA = a(str, null)) != null) {
            jVarA.f();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1423h;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1423h = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1419d = new ArrayList<>();
        c(context, attributeSet);
    }
}
