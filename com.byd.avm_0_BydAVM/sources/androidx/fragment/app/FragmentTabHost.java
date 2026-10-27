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
    private final ArrayList<a> f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private g f994d;
    private int e;
    private TabHost.OnTabChangeListener f;
    private a g;
    private boolean h;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f995b;

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
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f995b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f995b);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f995b = parcel.readString();
        }
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f998c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f999d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f992b = new ArrayList<>();
        c(context, null);
    }

    private j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.g != aVarB) {
            if (jVar == null) {
                jVar = this.f994d.a();
            }
            a aVar = this.g;
            if (aVar != null && (fragment = aVar.f999d) != null) {
                jVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f999d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f994d.e().a(this.f993c.getClassLoader(), aVarB.f997b.getName());
                    aVarB.f999d = fragmentA;
                    fragmentA.setArguments(aVarB.f998c);
                    jVar.b(this.e, aVarB.f999d, aVarB.f996a);
                } else {
                    jVar.d(fragment2);
                }
            }
            this.g = aVarB;
        }
        return jVar;
    }

    private a b(String str) {
        int size = this.f992b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f992b.get(i);
            if (aVar.f996a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f992b.size();
        j jVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f992b.get(i);
            Fragment fragmentD = this.f994d.d(aVar.f996a);
            aVar.f999d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f996a.equals(currentTabTag)) {
                    this.g = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f994d.a();
                    }
                    jVarA.f(aVar.f999d);
                }
            }
        }
        this.h = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.e();
            this.f994d.c();
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
        setCurrentTabByTag(savedState.f995b);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f995b = getCurrentTabTag();
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
        this.f992b = new ArrayList<>();
        c(context, attributeSet);
    }
}
