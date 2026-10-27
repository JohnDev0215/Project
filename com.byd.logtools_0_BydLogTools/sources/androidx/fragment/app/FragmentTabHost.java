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
    private final ArrayList<a> f1491b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1492c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private g f1493d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1494e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TabHost.OnTabChangeListener f1495f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f1496g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1497h;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1498b;

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
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1498b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f1498b);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1498b = parcel.readString();
        }
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1499a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f1500b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f1501c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f1502d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1491b = new ArrayList<>();
        c(context, null);
    }

    private j a(String str, j jVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.f1496g != aVarB) {
            if (jVar == null) {
                jVar = this.f1493d.a();
            }
            a aVar = this.f1496g;
            if (aVar != null && (fragment = aVar.f1502d) != null) {
                jVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1502d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1493d.e().a(this.f1492c.getClassLoader(), aVarB.f1500b.getName());
                    aVarB.f1502d = fragmentA;
                    fragmentA.setArguments(aVarB.f1501c);
                    jVar.b(this.f1494e, aVarB.f1502d, aVarB.f1499a);
                } else {
                    jVar.d(fragment2);
                }
            }
            this.f1496g = aVarB;
        }
        return jVar;
    }

    private a b(String str) {
        int size = this.f1491b.size();
        for (int i = 0; i < size; i++) {
            a aVar = this.f1491b.get(i);
            if (aVar.f1499a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1494e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1491b.size();
        j jVarA = null;
        for (int i = 0; i < size; i++) {
            a aVar = this.f1491b.get(i);
            Fragment fragmentD = this.f1493d.d(aVar.f1499a);
            aVar.f1502d = fragmentD;
            if (fragmentD != null && !fragmentD.isDetached()) {
                if (aVar.f1499a.equals(currentTabTag)) {
                    this.f1496g = aVar;
                } else {
                    if (jVarA == null) {
                        jVarA = this.f1493d.a();
                    }
                    jVarA.f(aVar.f1502d);
                }
            }
        }
        this.f1497h = true;
        j jVarA2 = a(currentTabTag, jVarA);
        if (jVarA2 != null) {
            jVarA2.e();
            this.f1493d.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1497h = false;
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
        setCurrentTabByTag(savedState.f1498b);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1498b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        j jVarA;
        if (this.f1497h && (jVarA = a(str, null)) != null) {
            jVarA.e();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1495f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1495f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1491b = new ArrayList<>();
        c(context, attributeSet);
    }
}
