package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import i.c;
import l0.s;
import n.p;
import o.a0;
import o.k0;
import o.t;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSpinner extends Spinner implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f469b = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o.d f470c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f472e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SpinnerAdapter f473f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f474g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f f475h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f476i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f477j;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f478b;

        public class a implements Parcelable.Creator<SavedState> {
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

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeByte(this.f478b ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f478b = parcel.readByte() != 0;
        }
    }

    public class a extends t {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final /* synthetic */ e f479k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view, e eVar) {
            super(view);
            this.f479k = eVar;
        }

        @Override // o.t
        public p b() {
            return this.f479k;
        }

        @Override // o.t
        @SuppressLint({"SyntheticAccessor"})
        public boolean c() {
            if (AppCompatSpinner.this.getInternalPopup().b()) {
                return true;
            }
            AppCompatSpinner.this.b();
            return true;
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!AppCompatSpinner.this.getInternalPopup().b()) {
                AppCompatSpinner.this.b();
            }
            ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
    }

    public class c implements f, DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public i.c f482b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ListAdapter f483c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f484d;

        public c() {
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public boolean b() {
            i.c cVar = this.f482b;
            if (cVar != null) {
                return cVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void c(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void d(int i3) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void dismiss() {
            i.c cVar = this.f482b;
            if (cVar != null) {
                cVar.dismiss();
                this.f482b = null;
            }
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public int e() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void f(int i3, int i4) {
            if (this.f483c == null) {
                return;
            }
            c.a aVar = new c.a(AppCompatSpinner.this.getPopupContext());
            CharSequence charSequence = this.f484d;
            if (charSequence != null) {
                aVar.h(charSequence);
            }
            i.c cVarA = aVar.g(this.f483c, AppCompatSpinner.this.getSelectedItemPosition(), this).a();
            this.f482b = cVarA;
            ListView listViewE = cVarA.e();
            if (Build.VERSION.SDK_INT >= 17) {
                listViewE.setTextDirection(i3);
                listViewE.setTextAlignment(i4);
            }
            this.f482b.show();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public int h() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public Drawable j() {
            return null;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public CharSequence k() {
            return this.f484d;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void m(CharSequence charSequence) {
            this.f484d = charSequence;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void n(int i3) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void o(ListAdapter listAdapter) {
            this.f483c = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i3) {
            AppCompatSpinner.this.setSelection(i3);
            if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.this.performItemClick(null, i3, this.f483c.getItemId(i3));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void p(int i3) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }
    }

    public static class d implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public SpinnerAdapter f486b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ListAdapter f487c;

        public d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f486b = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f487c = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof a0) {
                    a0 a0Var = (a0) spinnerAdapter;
                    if (a0Var.getDropDownViewTheme() == null) {
                        a0Var.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f487c;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i3, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i3, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i3) {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i3);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i3) {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i3);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i3) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i3, View view, ViewGroup viewGroup) {
            return getDropDownView(i3, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f486b;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i3) {
            ListAdapter listAdapter = this.f487c;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i3);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f486b;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public class e extends ListPopupWindow implements f {
        public CharSequence K;
        public ListAdapter L;
        public final Rect M;
        public int N;

        public class a implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AppCompatSpinner f488b;

            public a(AppCompatSpinner appCompatSpinner) {
                this.f488b = appCompatSpinner;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
                AppCompatSpinner.this.setSelection(i3);
                if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                    e eVar = e.this;
                    AppCompatSpinner.this.performItemClick(view, i3, eVar.L.getItemId(i3));
                }
                e.this.dismiss();
            }
        }

        public class b implements ViewTreeObserver.OnGlobalLayoutListener {
            public b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                e eVar = e.this;
                if (!eVar.U(AppCompatSpinner.this)) {
                    e.this.dismiss();
                } else {
                    e.this.S();
                    e.super.g();
                }
            }
        }

        public class c implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f491b;

            public c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f491b = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f491b);
                }
            }
        }

        public e(Context context, AttributeSet attributeSet, int i3) {
            super(context, attributeSet, i3);
            this.M = new Rect();
            D(AppCompatSpinner.this);
            J(true);
            O(0);
            L(new a(AppCompatSpinner.this));
        }

        public void S() {
            Drawable drawableJ = j();
            int i3 = 0;
            if (drawableJ != null) {
                drawableJ.getPadding(AppCompatSpinner.this.f477j);
                i3 = k0.b(AppCompatSpinner.this) ? AppCompatSpinner.this.f477j.right : -AppCompatSpinner.this.f477j.left;
            } else {
                Rect rect = AppCompatSpinner.this.f477j;
                rect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i4 = appCompatSpinner.f476i;
            if (i4 == -2) {
                int iA = appCompatSpinner.a((SpinnerAdapter) this.L, j());
                int i5 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.f477j;
                int i6 = (i5 - rect2.left) - rect2.right;
                if (iA > i6) {
                    iA = i6;
                }
                F(Math.max(iA, (width - paddingLeft) - paddingRight));
            } else if (i4 == -1) {
                F((width - paddingLeft) - paddingRight);
            } else {
                F(i4);
            }
            d(k0.b(AppCompatSpinner.this) ? i3 + (((width - paddingRight) - z()) - T()) : i3 + paddingLeft + T());
        }

        public int T() {
            return this.N;
        }

        public boolean U(View view) {
            return l0.t.O(view) && view.getGlobalVisibleRect(this.M);
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void f(int i3, int i4) {
            ViewTreeObserver viewTreeObserver;
            boolean zB = b();
            S();
            I(2);
            super.g();
            ListView listViewL = l();
            listViewL.setChoiceMode(1);
            if (Build.VERSION.SDK_INT >= 17) {
                listViewL.setTextDirection(i3);
                listViewL.setTextAlignment(i4);
            }
            P(AppCompatSpinner.this.getSelectedItemPosition());
            if (zB || (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            K(new c(bVar));
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public CharSequence k() {
            return this.K;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void m(CharSequence charSequence) {
            this.K = charSequence;
        }

        @Override // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.widget.AppCompatSpinner.f
        public void o(ListAdapter listAdapter) {
            super.o(listAdapter);
            this.L = listAdapter;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.f
        public void p(int i3) {
            this.N = i3;
        }
    }

    public interface f {
        boolean b();

        void c(Drawable drawable);

        void d(int i3);

        void dismiss();

        int e();

        void f(int i3, int i4);

        int h();

        Drawable j();

        CharSequence k();

        void m(CharSequence charSequence);

        void n(int i3);

        void o(ListAdapter listAdapter);

        void p(int i3);
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i3 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.f477j);
        Rect rect = this.f477j;
        return iMax2 + rect.left + rect.right;
    }

    public void b() {
        if (Build.VERSION.SDK_INT >= 17) {
            this.f475h.f(getTextDirection(), getTextAlignment());
        } else {
            this.f475h.f(-1, -1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        o.d dVar = this.f470c;
        if (dVar != null) {
            dVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        f fVar = this.f475h;
        if (fVar != null) {
            return fVar.e();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        f fVar = this.f475h;
        if (fVar != null) {
            return fVar.h();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.f475h != null) {
            return this.f476i;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public final f getInternalPopup() {
        return this.f475h;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        f fVar = this.f475h;
        if (fVar != null) {
            return fVar.j();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f471d;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        f fVar = this.f475h;
        return fVar != null ? fVar.k() : super.getPrompt();
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        o.d dVar = this.f470c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        o.d dVar = this.f470c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f fVar = this.f475h;
        if (fVar == null || !fVar.b()) {
            return;
        }
        this.f475h.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
        if (this.f475h == null || View.MeasureSpec.getMode(i3) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i3)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.f478b || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        f fVar = this.f475h;
        savedState.f478b = fVar != null && fVar.b();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t tVar = this.f472e;
        if (tVar == null || !tVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        f fVar = this.f475h;
        if (fVar == null) {
            return super.performClick();
        }
        if (fVar.b()) {
            return true;
        }
        b();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        o.d dVar = this.f470c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        o.d dVar = this.f470c;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i3) {
        f fVar = this.f475h;
        if (fVar != null) {
            fVar.p(i3);
            this.f475h.d(i3);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i3);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i3) {
        f fVar = this.f475h;
        if (fVar != null) {
            fVar.n(i3);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i3);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i3) {
        if (this.f475h != null) {
            this.f476i = i3;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i3);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        f fVar = this.f475h;
        if (fVar != null) {
            fVar.c(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i3) {
        setPopupBackgroundDrawable(j.a.d(getPopupContext(), i3));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        f fVar = this.f475h;
        if (fVar != null) {
            fVar.m(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        o.d dVar = this.f470c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        o.d dVar = this.f470c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f474g) {
            this.f473f = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f475h != null) {
            Context context = this.f471d;
            if (context == null) {
                context = getContext();
            }
            this.f475h.o(new d(spinnerAdapter, context.getTheme()));
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i3) {
        this(context, attributeSet, i3, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i3, int i4) {
        this(context, attributeSet, i3, i4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.view.View, android.widget.Spinner, androidx.appcompat.widget.AppCompatSpinner] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppCompatSpinner(android.content.Context r7, android.util.AttributeSet r8, int r9, int r10, android.content.res.Resources.Theme r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}
