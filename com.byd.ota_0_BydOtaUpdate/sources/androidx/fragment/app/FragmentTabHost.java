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
import b.j.a.f;
import b.j.a.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    public a E3;
    public boolean F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<a> f513c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f514d;
    public f s;
    public int x;
    public TabHost.OnTabChangeListener y;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f515c;

        public static class a implements Parcelable.Creator<SavedState> {
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
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f515c + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f515c);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f515c = parcel.readString();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f516a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Class<?> f517b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f518c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Fragment f519d;
    }

    @Deprecated
    public FragmentTabHost(Context context) {
        super(context, null);
        this.f513c = new ArrayList<>();
        c(context, null);
    }

    public final i a(String str, i iVar) {
        Fragment fragment;
        a aVarB = b(str);
        if (this.E3 != aVarB) {
            if (iVar == null) {
                iVar = this.s.a();
            }
            a aVar = this.E3;
            if (aVar != null && (fragment = aVar.f519d) != null) {
                iVar.f(fragment);
            }
            if (aVarB != null) {
                Fragment fragment2 = aVarB.f519d;
                if (fragment2 == null) {
                    Fragment fragmentA = this.s.e().a(this.f514d.getClassLoader(), aVarB.f517b.getName());
                    aVarB.f519d = fragmentA;
                    fragmentA.d1(aVarB.f518c);
                    iVar.b(this.x, aVarB.f519d, aVarB.f516a);
                } else {
                    iVar.d(fragment2);
                }
            }
            this.E3 = aVarB;
        }
        return iVar;
    }

    public final a b(String str) {
        int size = this.f513c.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f513c.get(i2);
            if (aVar.f516a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.x = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f513c.size();
        i iVarA = null;
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = this.f513c.get(i2);
            Fragment fragmentD = this.s.d(aVar.f516a);
            aVar.f519d = fragmentD;
            if (fragmentD != null && !fragmentD.M()) {
                if (aVar.f516a.equals(currentTabTag)) {
                    this.E3 = aVar;
                } else {
                    if (iVarA == null) {
                        iVarA = this.s.a();
                    }
                    iVarA.f(aVar.f519d);
                }
            }
        }
        this.F3 = true;
        i iVarA2 = a(currentTabTag, iVarA);
        if (iVarA2 != null) {
            iVarA2.e();
            this.s.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.F3 = false;
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
        setCurrentTabByTag(savedState.f515c);
    }

    @Override // android.view.View
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f515c = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(String str) {
        i iVarA;
        if (this.F3 && (iVarA = a(str, null)) != null) {
            iVarA.e();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.y;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.y = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f513c = new ArrayList<>();
        c(context, attributeSet);
    }
}
