package a.b.p;

import a.b.k.h;
import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
import androidx.appcompat.app.AlertController;

/* JADX INFO: loaded from: classes.dex */
public class w extends Spinner {
    public static final int[] i = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a.b.p.e f370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h0 f372c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SpinnerAdapter f373d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f374e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f375f;
    public int g;
    public final Rect h;

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!w.this.getInternalPopup().a()) {
                w.this.b();
            }
            ViewTreeObserver viewTreeObserver = w.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
        }
    }

    public class b implements f, DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.b.k.h f377a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ListAdapter f378b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f379c;

        public b() {
        }

        @Override // a.b.p.w.f
        public boolean a() {
            a.b.k.h hVar = this.f377a;
            if (hVar != null) {
                return hVar.isShowing();
            }
            return false;
        }

        @Override // a.b.p.w.f
        public CharSequence b() {
            return this.f379c;
        }

        @Override // a.b.p.w.f
        public void c(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // a.b.p.w.f
        public int d() {
            return 0;
        }

        @Override // a.b.p.w.f
        public void dismiss() {
            a.b.k.h hVar = this.f377a;
            if (hVar != null) {
                hVar.dismiss();
                this.f377a = null;
            }
        }

        @Override // a.b.p.w.f
        public void f(int i, int i2) {
            if (this.f378b == null) {
                return;
            }
            h.a aVar = new h.a(w.this.getPopupContext());
            CharSequence charSequence = this.f379c;
            if (charSequence != null) {
                aVar.f16a.f1400f = charSequence;
            }
            ListAdapter listAdapter = this.f378b;
            int selectedItemPosition = w.this.getSelectedItemPosition();
            AlertController.b bVar = aVar.f16a;
            bVar.l = listAdapter;
            bVar.m = this;
            bVar.p = selectedItemPosition;
            bVar.o = true;
            a.b.k.h hVarA = aVar.a();
            this.f377a = hVarA;
            ListView listView = hVarA.f15c.g;
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.f377a.show();
        }

        @Override // a.b.p.w.f
        public void h(CharSequence charSequence) {
            this.f379c = charSequence;
        }

        @Override // a.b.p.w.f
        public int j() {
            return 0;
        }

        @Override // a.b.p.w.f
        public void l(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // a.b.p.w.f
        public void m(int i) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // a.b.p.w.f
        public Drawable n() {
            return null;
        }

        @Override // a.b.p.w.f
        public void o(ListAdapter listAdapter) {
            this.f378b = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            w.this.setSelection(i);
            if (w.this.getOnItemClickListener() != null) {
                w.this.performItemClick(null, i, this.f378b.getItemId(i));
            }
            a.b.k.h hVar = this.f377a;
            if (hVar != null) {
                hVar.dismiss();
                this.f377a = null;
            }
        }

        @Override // a.b.p.w.f
        public void p(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }
    }

    public static class c implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SpinnerAdapter f381a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ListAdapter f382b;

        public c(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f381a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f382b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof t0) {
                    t0 t0Var = (t0) spinnerAdapter;
                    if (t0Var.getDropDownViewTheme() == null) {
                        t0Var.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f382b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f381a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f382b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f381a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public class d extends k0 implements f {
        public CharSequence C;
        public ListAdapter D;
        public final Rect E;
        public int F;

        public class a implements AdapterView.OnItemClickListener {
            public a(w wVar) {
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                w.this.setSelection(i);
                if (w.this.getOnItemClickListener() != null) {
                    d dVar = d.this;
                    w.this.performItemClick(view, i, dVar.D.getItemId(i));
                }
                d.this.dismiss();
            }
        }

        public class b implements ViewTreeObserver.OnGlobalLayoutListener {
            public b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                d dVar = d.this;
                w wVar = w.this;
                if (dVar == null) {
                    throw null;
                }
                if (!(a.h.k.q.B(wVar) && wVar.getGlobalVisibleRect(dVar.E))) {
                    d.this.dismiss();
                } else {
                    d.this.u();
                    d.super.i();
                }
            }
        }

        public class c implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f385a;

            public c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f385a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = w.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f385a);
                }
            }
        }

        public d(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i, 0);
            this.E = new Rect();
            this.r = w.this;
            s(true);
            this.p = 0;
            this.s = new a(w.this);
        }

        @Override // a.b.p.w.f
        public CharSequence b() {
            return this.C;
        }

        @Override // a.b.p.w.f
        public void f(int i, int i2) {
            ViewTreeObserver viewTreeObserver;
            boolean zA = a();
            u();
            this.B.setInputMethodMode(2);
            super.i();
            f0 f0Var = this.f305c;
            f0Var.setChoiceMode(1);
            f0Var.setTextDirection(i);
            f0Var.setTextAlignment(i2);
            int selectedItemPosition = w.this.getSelectedItemPosition();
            f0 f0Var2 = this.f305c;
            if (a() && f0Var2 != null) {
                f0Var2.setListSelectionHidden(false);
                f0Var2.setSelection(selectedItemPosition);
                if (f0Var2.getChoiceMode() != 0) {
                    f0Var2.setItemChecked(selectedItemPosition, true);
                }
            }
            if (zA || (viewTreeObserver = w.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            this.B.setOnDismissListener(new c(bVar));
        }

        @Override // a.b.p.w.f
        public void h(CharSequence charSequence) {
            this.C = charSequence;
        }

        @Override // a.b.p.k0, a.b.p.w.f
        public void o(ListAdapter listAdapter) {
            super.o(listAdapter);
            this.D = listAdapter;
        }

        @Override // a.b.p.w.f
        public void p(int i) {
            this.F = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0096  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void u() {
            /*
                r8 = this;
                android.graphics.drawable.Drawable r0 = r8.n()
                r1 = 0
                if (r0 == 0) goto L26
                a.b.p.w r1 = a.b.p.w.this
                android.graphics.Rect r1 = r1.h
                r0.getPadding(r1)
                a.b.p.w r0 = a.b.p.w.this
                boolean r0 = a.b.p.b1.b(r0)
                if (r0 == 0) goto L1d
                a.b.p.w r0 = a.b.p.w.this
                android.graphics.Rect r0 = r0.h
                int r0 = r0.right
                goto L24
            L1d:
                a.b.p.w r0 = a.b.p.w.this
                android.graphics.Rect r0 = r0.h
                int r0 = r0.left
                int r0 = -r0
            L24:
                r1 = r0
                goto L2e
            L26:
                a.b.p.w r0 = a.b.p.w.this
                android.graphics.Rect r0 = r0.h
                r0.right = r1
                r0.left = r1
            L2e:
                a.b.p.w r0 = a.b.p.w.this
                int r0 = r0.getPaddingLeft()
                a.b.p.w r2 = a.b.p.w.this
                int r2 = r2.getPaddingRight()
                a.b.p.w r3 = a.b.p.w.this
                int r3 = r3.getWidth()
                a.b.p.w r4 = a.b.p.w.this
                int r5 = r4.g
                r6 = -2
                if (r5 != r6) goto L78
                android.widget.ListAdapter r5 = r8.D
                android.widget.SpinnerAdapter r5 = (android.widget.SpinnerAdapter) r5
                android.graphics.drawable.Drawable r6 = r8.n()
                int r4 = r4.a(r5, r6)
                a.b.p.w r5 = a.b.p.w.this
                android.content.Context r5 = r5.getContext()
                android.content.res.Resources r5 = r5.getResources()
                android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
                int r5 = r5.widthPixels
                a.b.p.w r6 = a.b.p.w.this
                android.graphics.Rect r6 = r6.h
                int r7 = r6.left
                int r5 = r5 - r7
                int r6 = r6.right
                int r5 = r5 - r6
                if (r4 <= r5) goto L70
                r4 = r5
            L70:
                int r5 = r3 - r0
                int r5 = r5 - r2
                int r4 = java.lang.Math.max(r4, r5)
                goto L7e
            L78:
                r4 = -1
                if (r5 != r4) goto L82
                int r4 = r3 - r0
                int r4 = r4 - r2
            L7e:
                r8.r(r4)
                goto L85
            L82:
                r8.r(r5)
            L85:
                a.b.p.w r4 = a.b.p.w.this
                boolean r4 = a.b.p.b1.b(r4)
                if (r4 == 0) goto L96
                int r3 = r3 - r2
                int r0 = r8.f307e
                int r3 = r3 - r0
                int r0 = r8.F
                int r3 = r3 - r0
                int r3 = r3 + r1
                goto L9b
            L96:
                int r2 = r8.F
                int r0 = r0 + r2
                int r3 = r0 + r1
            L9b:
                r8.f308f = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: a.b.p.w.d.u():void");
        }
    }

    public static class e extends View.BaseSavedState {
        public static final Parcelable.Creator<e> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f387a;

        public class a implements Parcelable.Creator<e> {
            @Override // android.os.Parcelable.Creator
            public e createFromParcel(Parcel parcel) {
                return new e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public e[] newArray(int i) {
                return new e[i];
            }
        }

        public e(Parcel parcel) {
            super(parcel);
            this.f387a = parcel.readByte() != 0;
        }

        public e(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.f387a ? (byte) 1 : (byte) 0);
        }
    }

    public interface f {
        boolean a();

        CharSequence b();

        void c(int i);

        int d();

        void dismiss();

        void f(int i, int i2);

        void h(CharSequence charSequence);

        int j();

        void l(Drawable drawable);

        void m(int i);

        Drawable n();

        void o(ListAdapter listAdapter);

        void p(int i);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public w(android.content.Context r9, android.util.AttributeSet r10, int r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.p.w.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
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
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
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
        drawable.getPadding(this.h);
        Rect rect = this.h;
        return iMax2 + rect.left + rect.right;
    }

    public void b() {
        this.f375f.f(getTextDirection(), getTextAlignment());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            eVar.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        f fVar = this.f375f;
        return fVar != null ? fVar.d() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        f fVar = this.f375f;
        return fVar != null ? fVar.j() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f375f != null ? this.g : super.getDropDownWidth();
    }

    public final f getInternalPopup() {
        return this.f375f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        f fVar = this.f375f;
        return fVar != null ? fVar.n() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f371b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        f fVar = this.f375f;
        return fVar != null ? fVar.b() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f fVar = this.f375f;
        if (fVar == null || !fVar.a()) {
            return;
        }
        this.f375f.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f375f == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        e eVar = (e) parcelable;
        super.onRestoreInstanceState(eVar.getSuperState());
        if (!eVar.f387a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new a());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        e eVar = new e(super.onSaveInstanceState());
        f fVar = this.f375f;
        eVar.f387a = fVar != null && fVar.a();
        return eVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        h0 h0Var = this.f372c;
        if (h0Var == null || !h0Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        f fVar = this.f375f;
        if (fVar == null) {
            return super.performClick();
        }
        if (fVar.a()) {
            return true;
        }
        b();
        return true;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f374e) {
            this.f373d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f375f != null) {
            Context context = this.f371b;
            if (context == null) {
                context = getContext();
            }
            this.f375f.o(new c(spinnerAdapter, context.getTheme()));
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            eVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            eVar.f(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        f fVar = this.f375f;
        if (fVar == null) {
            super.setDropDownHorizontalOffset(i2);
        } else {
            fVar.p(i2);
            this.f375f.c(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        f fVar = this.f375f;
        if (fVar != null) {
            fVar.m(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f375f != null) {
            this.g = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        f fVar = this.f375f;
        if (fVar != null) {
            fVar.l(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(a.b.l.a.a.b(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        f fVar = this.f375f;
        if (fVar != null) {
            fVar.h(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            eVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        a.b.p.e eVar = this.f370a;
        if (eVar != null) {
            eVar.i(mode);
        }
    }
}
