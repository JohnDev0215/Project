package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.internal.CheckableImageButton;
import defpackage.cc1;
import defpackage.f12;
import defpackage.ht3;
import defpackage.in3;
import defpackage.kc1;
import defpackage.ks1;
import defpackage.l01;
import defpackage.lc1;
import defpackage.nc1;
import defpackage.oj2;
import defpackage.om3;
import defpackage.qk3;
import defpackage.r90;
import defpackage.rt3;
import defpackage.sc1;
import defpackage.tc1;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<S> extends r90 {
    public static final /* synthetic */ int L0 = 0;
    public boolean A0;
    public int B0;
    public int C0;
    public CharSequence D0;
    public int E0;
    public CharSequence F0;
    public TextView G0;
    public CheckableImageButton H0;
    public sc1 I0;
    public Button J0;
    public boolean K0;
    public final LinkedHashSet<nc1<? super S>> p0 = new LinkedHashSet<>();
    public final LinkedHashSet<View.OnClickListener> q0 = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnCancelListener> r0 = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnDismissListener> s0 = new LinkedHashSet<>();
    public int t0;
    public DateSelector<S> u0;
    public f12<S> v0;
    public CalendarConstraints w0;
    public com.google.android.material.datepicker.a<S> x0;
    public int y0;
    public CharSequence z0;

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            f fVar = f.this;
            for (nc1<? super S> nc1Var : fVar.p0) {
                fVar.N0().a0();
                nc1Var.a();
            }
            fVar.I0(false, false);
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            f fVar = f.this;
            Iterator<View.OnClickListener> it = fVar.q0.iterator();
            while (it.hasNext()) {
                it.next().onClick(view);
            }
            fVar.I0(false, false);
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    public class c extends ks1<S> {
        public c() {
        }

        @Override // defpackage.ks1
        public final void a(S s) {
            int i = f.L0;
            f fVar = f.this;
            fVar.S0();
            fVar.J0.setEnabled(fVar.N0().T());
        }
    }

    public static int O0(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_content_padding);
        Month month = new Month(qk3.c());
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_horizontal_padding);
        int i = month.h;
        return ((i - 1) * dimensionPixelOffset2) + (dimensionPixelSize * i) + (dimensionPixelOffset * 2);
    }

    public static boolean P0(Context context) {
        return Q0(context, R.attr.windowFullscreen);
    }

    public static boolean Q0(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(cc1.b(R$attr.materialCalendarStyle, context, com.google.android.material.datepicker.a.class.getCanonicalName()).data, new int[]{i});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        Context contextX0 = x0();
        x0();
        int iM = this.t0;
        if (iM == 0) {
            iM = N0().M();
        }
        Dialog dialog = new Dialog(contextX0, iM);
        Context context = dialog.getContext();
        this.A0 = P0(context);
        int i = cc1.b(R$attr.colorSurface, context, f.class.getCanonicalName()).data;
        sc1 sc1Var = new sc1(context, null, R$attr.materialCalendarStyle, R$style.Widget_MaterialComponents_MaterialCalendar);
        this.I0 = sc1Var;
        sc1Var.k(context);
        this.I0.n(ColorStateList.valueOf(i));
        sc1 sc1Var2 = this.I0;
        View decorView = dialog.getWindow().getDecorView();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        sc1Var2.m(om3.i.e(decorView));
        return dialog;
    }

    public final DateSelector<S> N0() {
        if (this.u0 == null) {
            this.u0 = (DateSelector) this.j.getParcelable("DATE_SELECTOR_KEY");
        }
        return this.u0;
    }

    public final void R0() {
        f12<S> tc1Var;
        x0();
        int iM = this.t0;
        if (iM == 0) {
            iM = N0().M();
        }
        DateSelector<S> dateSelectorN0 = N0();
        CalendarConstraints calendarConstraints = this.w0;
        com.google.android.material.datepicker.a<S> aVar = new com.google.android.material.datepicker.a<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", iM);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelectorN0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.h);
        aVar.C0(bundle);
        this.x0 = aVar;
        if (this.H0.isChecked()) {
            DateSelector<S> dateSelectorN02 = N0();
            CalendarConstraints calendarConstraints2 = this.w0;
            tc1Var = new tc1<>();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", iM);
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelectorN02);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            tc1Var.C0(bundle2);
        } else {
            tc1Var = this.x0;
        }
        this.v0 = tc1Var;
        S0();
        FragmentManager fragmentManagerJ = J();
        fragmentManagerJ.getClass();
        androidx.fragment.app.a aVar2 = new androidx.fragment.app.a(fragmentManagerJ);
        aVar2.e(R$id.mtrl_calendar_frame, this.v0, null);
        aVar2.i();
        this.v0.H0(new c());
    }

    public final void S0() {
        DateSelector<S> dateSelectorN0 = N0();
        K();
        String strO = dateSelectorN0.o();
        this.G0.setContentDescription(String.format(O(R$string.mtrl_picker_announce_current_selection), strO));
        this.G0.setText(strO);
    }

    public final void T0(CheckableImageButton checkableImageButton) {
        this.H0.setContentDescription(this.H0.isChecked() ? checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(R$string.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle == null) {
            bundle = this.j;
        }
        this.t0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.u0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.w0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.y0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.z0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.B0 = bundle.getInt("INPUT_MODE_KEY");
        this.C0 = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.D0 = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.E0 = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.F0 = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.A0 ? R$layout.mtrl_picker_fullscreen : R$layout.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.A0) {
            viewInflate.findViewById(R$id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(O0(context), -2));
        } else {
            viewInflate.findViewById(R$id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(O0(context), -1));
        }
        TextView textView = (TextView) viewInflate.findViewById(R$id.mtrl_picker_header_selection_text);
        this.G0 = textView;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.g.e(textView, 1);
        this.H0 = (CheckableImageButton) viewInflate.findViewById(R$id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) viewInflate.findViewById(R$id.mtrl_picker_title_text);
        CharSequence charSequence = this.z0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.y0);
        }
        this.H0.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.H0;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, AppCompatResources.a(context, R$drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], AppCompatResources.a(context, R$drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.H0.setChecked(this.B0 != 0);
        om3.k(this.H0, null);
        T0(this.H0);
        this.H0.setOnClickListener(new lc1(this));
        this.J0 = (Button) viewInflate.findViewById(R$id.confirm_button);
        if (N0().T()) {
            this.J0.setEnabled(true);
        } else {
            this.J0.setEnabled(false);
        }
        this.J0.setTag("CONFIRM_BUTTON_TAG");
        CharSequence charSequence2 = this.D0;
        if (charSequence2 != null) {
            this.J0.setText(charSequence2);
        } else {
            int i = this.C0;
            if (i != 0) {
                this.J0.setText(i);
            }
        }
        this.J0.setOnClickListener(new a());
        Button button = (Button) viewInflate.findViewById(R$id.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        CharSequence charSequence3 = this.F0;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i2 = this.E0;
            if (i2 != 0) {
                button.setText(i2);
            }
        }
        button.setOnClickListener(new b());
        return viewInflate;
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        super.n0(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.t0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.u0);
        CalendarConstraints.b bVar = new CalendarConstraints.b(this.w0);
        Month month = this.x0.d0;
        if (month != null) {
            bVar.c = Long.valueOf(month.j);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", bVar.e);
        Month monthF = Month.f(bVar.a);
        Month monthF2 = Month.f(bVar.b);
        CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l = bVar.c;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(monthF, monthF2, dateValidator, l == null ? null : Month.f(l.longValue()), bVar.d));
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.y0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.z0);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.C0);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.D0);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.E0);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.F0);
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void o0() {
        super.o0();
        Window window = K0().getWindow();
        if (this.A0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.I0);
            if (!this.K0) {
                View viewFindViewById = y0().findViewById(R$id.fullscreen_header);
                Integer numValueOf = viewFindViewById.getBackground() instanceof ColorDrawable ? Integer.valueOf(((ColorDrawable) viewFindViewById.getBackground()).getColor()) : null;
                boolean z = numValueOf == null || numValueOf.intValue() == 0;
                int iO = oj2.o(window.getContext(), R.attr.colorBackground, -16777216);
                if (z) {
                    numValueOf = Integer.valueOf(iO);
                }
                Integer numValueOf2 = Integer.valueOf(iO);
                ht3.a(window, false);
                window.getContext();
                window.getContext();
                window.setStatusBarColor(0);
                window.setNavigationBarColor(0);
                new rt3(window, window.getDecorView()).a.d(oj2.x(0) || oj2.x(numValueOf.intValue()));
                new rt3(window, window.getDecorView()).a.c(oj2.x(0) || oj2.x(numValueOf2.intValue()));
                kc1 kc1Var = new kc1(viewFindViewById.getLayoutParams().height, viewFindViewById, viewFindViewById.getPaddingTop());
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.i.m(viewFindViewById, kc1Var);
                this.K0 = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = N().getDimensionPixelOffset(R$dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.I0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new l01(K0(), rect));
        }
        R0();
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.r0.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.s0.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.f12J;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void p0() {
        this.v0.Z.clear();
        super.p0();
    }
}
