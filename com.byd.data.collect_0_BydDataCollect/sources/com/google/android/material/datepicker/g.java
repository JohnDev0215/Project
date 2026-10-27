package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
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
import b.g.k.t;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<S> extends androidx.fragment.app.b {
    static final Object m0 = "CONFIRM_BUTTON_TAG";
    static final Object n0 = "CANCEL_BUTTON_TAG";
    static final Object o0 = "TOGGLE_BUTTON_TAG";
    private boolean A0;
    private int B0;
    private TextView C0;
    private CheckableImageButton D0;
    private d.b.a.a.x.h E0;
    private Button F0;
    private final LinkedHashSet<h<? super S>> p0 = new LinkedHashSet<>();
    private final LinkedHashSet<View.OnClickListener> q0 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnCancelListener> r0 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnDismissListener> s0 = new LinkedHashSet<>();
    private int t0;
    private DateSelector<S> u0;
    private m<S> v0;
    private CalendarConstraints w0;
    private f<S> x0;
    private int y0;
    private CharSequence z0;

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class a implements View.OnClickListener {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = g.this.p0.iterator();
            while (it.hasNext()) {
                ((h) it.next()).a(g.this.L1());
            }
            g.this.r1();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = g.this.q0.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            g.this.r1();
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class c extends l<S> {
        c() {
        }

        @Override // com.google.android.material.datepicker.l
        void a() {
            g.this.F0.setEnabled(false);
        }

        @Override // com.google.android.material.datepicker.l
        public void b(S s) {
            g.this.R1();
            g.this.F0.setEnabled(g.this.u0.k());
        }
    }

    /* JADX INFO: compiled from: MaterialDatePicker.java */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.F0.setEnabled(g.this.u0.k());
            g.this.D0.toggle();
            g gVar = g.this;
            gVar.S1(gVar.D0);
            g.this.P1();
        }
    }

    private static Drawable H1(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, b.a.k.a.a.d(context, d.b.a.a.e.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], b.a.k.a.a.d(context, d.b.a.a.e.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    private static int I1(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_days_of_week_height);
        int i2 = j.f3991b;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_day_height) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_bottom_padding);
    }

    private static int K1(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_content_padding);
        int i2 = Month.f().f3926f;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_day_width) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_month_horizontal_padding));
    }

    private int M1(Context context) {
        int i2 = this.t0;
        return i2 != 0 ? i2 : this.u0.g(context);
    }

    private void N1(Context context) {
        this.D0.setTag(o0);
        this.D0.setImageDrawable(H1(context));
        this.D0.setChecked(this.B0 != 0);
        t.j0(this.D0, null);
        S1(this.D0);
        this.D0.setOnClickListener(new d());
    }

    static boolean O1(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(d.b.a.a.u.b.c(context, d.b.a.a.b.materialCalendarStyle, f.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    public void P1() {
        this.x0 = f.G1(this.u0, M1(c1()), this.w0);
        this.v0 = this.D0.isChecked() ? i.r1(this.u0, this.w0) : this.x0;
        R1();
        androidx.fragment.app.o oVarI = o().i();
        oVarI.q(d.b.a.a.f.mtrl_calendar_frame, this.v0);
        oVarI.k();
        this.v0.p1(new c());
    }

    public static long Q1() {
        return Month.f().f3928h;
    }

    public void R1() {
        String strJ1 = J1();
        this.C0.setContentDescription(String.format(I(d.b.a.a.j.mtrl_picker_announce_current_selection), strJ1));
        this.C0.setText(strJ1);
    }

    public void S1(CheckableImageButton checkableImageButton) {
        this.D0.setContentDescription(this.D0.isChecked() ? checkableImageButton.getContext().getString(d.b.a.a.j.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(d.b.a.a.j.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // androidx.fragment.app.b, androidx.fragment.app.Fragment
    public void A0() {
        super.A0();
        Window window = w1().getWindow();
        if (this.A0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.E0);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = C().getDimensionPixelOffset(d.b.a.a.d.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.E0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new d.b.a.a.p.a(w1(), rect));
        }
        P1();
    }

    @Override // androidx.fragment.app.b, androidx.fragment.app.Fragment
    public void B0() {
        this.v0.q1();
        super.B0();
    }

    public String J1() {
        return this.u0.e(p());
    }

    public final S L1() {
        return this.u0.b();
    }

    @Override // androidx.fragment.app.b, androidx.fragment.app.Fragment
    public final void d0(Bundle bundle) {
        super.d0(bundle);
        if (bundle == null) {
            bundle = n();
        }
        this.t0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.u0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.w0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.y0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.z0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.B0 = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(this.A0 ? d.b.a.a.h.mtrl_picker_fullscreen : d.b.a.a.h.mtrl_picker_dialog, viewGroup);
        Context context = viewInflate.getContext();
        if (this.A0) {
            viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(K1(context), -2));
        } else {
            View viewFindViewById = viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_main_pane);
            View viewFindViewById2 = viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_frame);
            viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(K1(context), -1));
            viewFindViewById2.setMinimumHeight(I1(c1()));
        }
        TextView textView = (TextView) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_header_selection_text);
        this.C0 = textView;
        t.l0(textView, 1);
        this.D0 = (CheckableImageButton) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_title_text);
        CharSequence charSequence = this.z0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.y0);
        }
        N1(context);
        this.F0 = (Button) viewInflate.findViewById(d.b.a.a.f.confirm_button);
        if (this.u0.k()) {
            this.F0.setEnabled(true);
        } else {
            this.F0.setEnabled(false);
        }
        this.F0.setTag(m0);
        this.F0.setOnClickListener(new a());
        Button button = (Button) viewInflate.findViewById(d.b.a.a.f.cancel_button);
        button.setTag(n0);
        button.setOnClickListener(new b());
        return viewInflate;
    }

    @Override // androidx.fragment.app.b, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.r0.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.b, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.s0.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) L();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.b
    public final Dialog v1(Bundle bundle) {
        Dialog dialog = new Dialog(c1(), M1(c1()));
        Context context = dialog.getContext();
        this.A0 = O1(context);
        int iC = d.b.a.a.u.b.c(context, d.b.a.a.b.colorSurface, g.class.getCanonicalName());
        d.b.a.a.x.h hVar = new d.b.a.a.x.h(context, null, d.b.a.a.b.materialCalendarStyle, d.b.a.a.k.Widget_MaterialComponents_MaterialCalendar);
        this.E0 = hVar;
        hVar.N(context);
        this.E0.X(ColorStateList.valueOf(iC));
        this.E0.W(t.u(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.b, androidx.fragment.app.Fragment
    public final void z0(Bundle bundle) {
        super.z0(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.t0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.u0);
        CalendarConstraints.b bVar = new CalendarConstraints.b(this.w0);
        if (this.x0.C1() != null) {
            bVar.b(this.x0.C1().f3928h);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar.a());
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.y0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.z0);
    }
}
