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
    private final ArrayList<a> f1400b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1401c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private j f1402d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1403e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TabHost.OnTabChangeListener f1404f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f1405g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1406h;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1407b;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f1407b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f1407b);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1407b = parcel.readString();
        }
    }

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1408a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<?> f1409b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f1410c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Fragment f1411d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f1400b = new ArrayList<>();
        c(context, null);
    }

    private o a(String str, o oVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.f1405g != aVarB) {
            if (oVar == null) {
                oVar = this.f1402d.i();
            }
            a aVar = this.f1405g;
            if (aVar != null && (fragment = aVar.f1411d) != null) {
                oVar.m(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f1411d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.f1402d.f0().a(this.f1401c.getClassLoader(), aVarB.f1409b.getName());
                    aVarB.f1411d = fragmentA;
                    fragmentA.i1(aVarB.f1410c);
                    oVar.b(this.f1403e, aVarB.f1411d, aVarB.f1408a);
                } else {
                    oVar.h(fragment2);
                }
            }
            this.f1405g = aVarB;
        }
        return oVar;
    }

    private a b(String str) {
        int size = this.f1400b.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f1400b.get(i2);
            if (aVar.f1408a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f1403e = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f1400b.size();
        o oVarI = null;
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f1400b.get(i2);
            Fragment fragmentY = this.f1402d.Y(aVar.f1408a);
            aVar.f1411d = fragmentY;
            if (fragmentY != null && !fragmentY.P()) {
                if (aVar.f1408a.equals(currentTabTag)) {
                    this.f1405g = aVar;
                } else {
                    if (oVarI == null) {
                        oVarI = this.f1402d.i();
                    }
                    oVarI.m(aVar.f1411d);
                }
            }
        }
        this.f1406h = true;
        o oVarA = a(currentTabTag, oVarI);
        if (oVarA != null) {
            oVarA.i();
            this.f1402d.U();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1406h = false;
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
        setCurrentTabByTag(savedState.f1407b);
    }

    @Override // android.view.View
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1407b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        o oVarA;
        if (this.f1406h && (oVarA = a(str, null)) != null) {
            oVarA.i();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f1404f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f1404f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1400b = new ArrayList<>();
        c(context, attributeSet);
    }
}
