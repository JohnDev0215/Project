package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.xz;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.autofill.prefeditor.EditorDialogToolbar;
import org.chromium.chrome.browser.autofill.prefeditor.ExpandableGridView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.autofill.prefeditor.EditorTextField;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: compiled from: EditorDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class tf0 extends o4 implements View.OnClickListener, DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    public static final /* synthetic */ int D = 0;
    public boolean A;
    public final Profile B;
    public qh3 C;
    public final Activity e;
    public final Handler f;
    public final pf0 g;
    public final int h;
    public final ArrayList i;
    public final ArrayList j;
    public final ArrayList k;
    public final qf0 l;
    public final w10 m;
    public final boolean n;
    public TextWatcher o;
    public View p;
    public il q;
    public Button r;
    public boolean s;
    public boolean t;
    public ViewGroup u;
    public View v;
    public AutoCompleteTextView w;
    public AutoCompleteTextView x;
    public Animator y;
    public final Runnable z;

    /* JADX INFO: compiled from: EditorDialog.java */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            tf0 tf0Var = tf0.this;
            tf0Var.y = null;
            tf0Var.dismiss();
        }
    }

    /* JADX INFO: compiled from: EditorDialog.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            tf0 tf0Var = tf0.this;
            if (tf0Var.q == null) {
                return;
            }
            tf0Var.f();
            tf0Var.g();
            int i = tf0.D;
        }
    }

    /* JADX INFO: compiled from: EditorDialog.java */
    public class c implements CompoundButton.OnCheckedChangeListener {
        public final /* synthetic */ xf0 e;

        public c(xf0 xf0Var) {
            this.e = xf0Var;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            xf0 xf0Var = this.e;
            xf0Var.getClass();
            SharedPreferences.Editor editorEdit = xz.a.a.edit();
            editorEdit.putBoolean(xf0Var.n.toString(), z);
            editorEdit.apply();
            int i = tf0.D;
        }
    }

    /* JADX INFO: compiled from: EditorDialog.java */
    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            tf0 tf0Var = tf0.this;
            int i = 0;
            tf0Var.p.setLayerType(0, null);
            while (true) {
                ArrayList arrayList = tf0Var.j;
                if (i >= arrayList.size()) {
                    tf0Var.y = null;
                    tf0Var.f.post(new wq0(11, tf0Var));
                    return;
                } else {
                    ((EditText) arrayList.get(i)).setEnabled(true);
                    i++;
                }
            }
        }
    }

    public tf0(Activity activity, wv wvVar, Profile profile, boolean z) {
        super(activity, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.e = activity;
        this.f = new Handler();
        this.A = false;
        this.g = new pf0(this);
        this.h = activity.getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_large_spacing);
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new qf0(Pattern.compile("^[\\d- ]*$"));
        this.m = new w10();
        this.z = wvVar;
        this.B = profile;
        this.n = z;
    }

    public final View a(ViewGroup viewGroup, xf0 xf0Var) {
        qf0 qf0Var;
        TextWatcher textWatcher;
        View view;
        View view2;
        int i = xf0Var.a;
        Activity activity = this.e;
        if (i == 13) {
            View viewInflate = LayoutInflater.from(activity).inflate(R$layout.editable_option_editor_icons, viewGroup, false);
            ((TextView) viewInflate.findViewById(R$id.label)).setText(xf0Var.m);
            ((ExpandableGridView) viewInflate.findViewById(R$id.icons_container)).setAdapter((ListAdapter) new zf0(activity));
            view2 = viewInflate;
        } else if (i == 15) {
            View viewInflate2 = LayoutInflater.from(activity).inflate(R$layout.payment_request_editor_label, viewGroup, false);
            ((TextView) viewInflate2.findViewById(R$id.top_label)).setText(xf0Var.m);
            ((TextView) viewInflate2.findViewById(R$id.mid_label)).setText((CharSequence) null);
            ((TextView) viewInflate2.findViewById(R$id.bottom_label)).setText((CharSequence) null);
            ((ImageView) viewInflate2.findViewById(R$id.icon)).setImageDrawable(AppCompatResources.a(activity, 0));
            view2 = viewInflate2;
        } else {
            ArrayList arrayList = this.i;
            if (i == 12) {
                wf0 wf0Var = new wf0(activity, viewGroup, xf0Var, new b(), this.n);
                arrayList.add(wf0Var);
                this.k.add(wf0Var.h);
                view2 = wf0Var.g;
            } else {
                if (i == 14) {
                    CheckBox checkBox = new CheckBox(this.p.getContext());
                    checkBox.setId(R$id.payments_edit_checkbox);
                    checkBox.setText(xf0Var.m);
                    ku2 ku2VarT = ku2.t();
                    try {
                        boolean z = xz.a.a.getBoolean(xf0Var.n.toString(), true);
                        ku2VarT.close();
                        checkBox.setChecked(z);
                        checkBox.setMinimumHeight(activity.getResources().getDimensionPixelSize(R$dimen.editor_dialog_checkbox_min_height));
                        checkBox.setOnCheckedChangeListener(new c(xf0Var));
                        view = checkBox;
                    } catch (Throwable th) {
                        try {
                            ku2VarT.close();
                        } catch (Throwable unused) {
                        }
                        throw th;
                    }
                } else {
                    if (i == 7) {
                        qf0Var = this.l;
                        textWatcher = this.m;
                    } else if (i == 1) {
                        TextWatcher textWatcher2 = xf0Var.g;
                        this.o = textWatcher2;
                        textWatcher = textWatcher2;
                        qf0Var = null;
                    } else {
                        qf0Var = null;
                        textWatcher = null;
                    }
                    EditorTextField editorTextField = new EditorTextField(this.e, xf0Var, this.g, qf0Var, textWatcher, this.n);
                    arrayList.add(editorTextField);
                    AutoCompleteTextView editText = editorTextField.getEditText();
                    this.j.add(editText);
                    int i2 = xf0Var.a;
                    view = editorTextField;
                    if (i2 == 7) {
                        this.w = editText;
                        view = editorTextField;
                    } else if (i2 == 1) {
                        this.x = editText;
                        view = editorTextField;
                    }
                }
                view2 = view;
            }
        }
        viewGroup.addView(view2);
        return view2;
    }

    public final void b() {
        if (this.y == null && isShowing()) {
            if (getCurrentFocus() != null) {
                t41.f.e(getCurrentFocus());
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.p, (Property<View, Float>) View.TRANSLATION_Y, 0.0f, r0.getHeight());
            View view = this.p;
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, view.getAlpha(), 0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            this.y = animatorSet;
            animatorSet.setDuration(195L);
            this.y.setInterpolator(h21.d);
            this.y.addListener(new a());
            this.y.start();
        }
    }

    public final ArrayList c(boolean z) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            ArrayList arrayList2 = this.i;
            if (i >= arrayList2.size()) {
                break;
            }
            yf0 yf0Var = (yf0) arrayList2.get(i);
            if (!yf0Var.isValid()) {
                arrayList.add(yf0Var);
                if (!z) {
                    break;
                }
            }
            i++;
        }
        return arrayList;
    }

    public final void e() {
        qh3 qh3Var = this.C;
        if (qh3Var != null) {
            qh3Var.c();
        } else if (this.u != null) {
            int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R$dimen.settings_wide_display_min_padding);
            qh3 qh3Var2 = new qh3(this.u);
            this.C = qh3Var2;
            new on3(this.u, qh3Var2, 0, dimensionPixelSize).a();
        }
    }

    public final void f() {
        xf0 xf0Var;
        h();
        ViewGroup viewGroup = (ViewGroup) this.p.findViewById(R$id.contents);
        this.u = viewGroup;
        viewGroup.removeAllViews();
        this.i.clear();
        this.j.clear();
        this.k.clear();
        int i = 0;
        while (i < ((List) this.q.f).size()) {
            xf0 xf0Var2 = (xf0) ((List) this.q.f).get(i);
            boolean z = i == ((List) this.q.f).size() - 1;
            boolean z2 = xf0Var2.q;
            if (z || z2) {
                xf0Var = null;
            } else {
                xf0Var = (xf0) ((List) this.q.f).get(i + 1);
                if (xf0Var.q) {
                    z2 = true;
                }
            }
            if (!z && !z2) {
                if ((xf0Var2.a == 12) != (xf0Var.a == 12)) {
                    z2 = true;
                }
            }
            if (z2 || z) {
                a(this.u, xf0Var2);
            } else {
                LinearLayout linearLayout = new LinearLayout(this.e);
                this.u.addView(linearLayout);
                View viewA = a(linearLayout, xf0Var2);
                View viewA2 = a(linearLayout, xf0Var);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewA.getLayoutParams();
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewA2.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                layoutParams.setMarginEnd(this.h);
                layoutParams2.width = 0;
                layoutParams2.weight = 1.0f;
                i++;
            }
            i++;
        }
        this.u.addView(this.v);
    }

    public final void g() {
        int i;
        TextView textView = (TextView) this.p.findViewById(R$id.required_fields_notice);
        if (this.n) {
            int i2 = 0;
            while (true) {
                ArrayList arrayList = this.i;
                if (i2 >= arrayList.size()) {
                    break;
                }
                if (((yf0) arrayList.get(i2)).b()) {
                    i = 0;
                    break;
                }
                i2++;
            }
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        TextView textView2 = (TextView) this.p.findViewById(R$id.footer_message);
        String str = (String) this.q.c;
        if (str == null) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(str);
            textView2.setVisibility(0);
        }
    }

    public final void h() {
        AutoCompleteTextView autoCompleteTextView = this.w;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.removeTextChangedListener(this.m);
            this.w.setFilters(new InputFilter[0]);
            this.w = null;
        }
        AutoCompleteTextView autoCompleteTextView2 = this.x;
        if (autoCompleteTextView2 != null) {
            autoCompleteTextView2.removeTextChangedListener(this.o);
            this.x = null;
        }
    }

    public final void i(il ilVar) {
        Activity activity = this.e;
        if (activity.isFinishing()) {
            return;
        }
        setOnShowListener(this);
        setOnDismissListener(this);
        this.q = ilVar;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.payment_request_editor, (ViewGroup) null);
        this.p = viewInflate;
        setContentView(viewInflate);
        this.v = LayoutInflater.from(activity).inflate(R$layout.editable_option_editor_footer, (ViewGroup) null, false);
        EditorDialogToolbar editorDialogToolbar = (EditorDialogToolbar) this.p.findViewById(R$id.action_bar);
        editorDialogToolbar.setBackgroundColor(ii2.a(editorDialogToolbar.getContext()));
        Context context = editorDialogToolbar.getContext();
        int i = R$style.TextAppearance_Headline_Primary;
        editorDialogToolbar.p = i;
        AppCompatTextView appCompatTextView = editorDialogToolbar.f;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(context, i);
        }
        editorDialogToolbar.setTitle((String) this.q.a);
        editorDialogToolbar.setShowDeleteMenuItem(this.z != null);
        editorDialogToolbar.setOnMenuItemClickListener(new rf0(this));
        editorDialogToolbar.setNavigationContentDescription(R$string.cancel);
        editorDialogToolbar.setNavigationIcon(za3.b(getContext(), R$drawable.ic_arrow_back_white_24dp, R$color.default_icon_color_tint_list));
        editorDialogToolbar.setNavigationOnClickListener(new sf0(this));
        FadingEdgeScrollView fadingEdgeScrollView = (FadingEdgeScrollView) this.p.findViewById(R$id.scroll_view);
        fadingEdgeScrollView.h = 0;
        fadingEdgeScrollView.i = 1;
        fadingEdgeScrollView.invalidate();
        View viewFindViewById = this.p.findViewById(R$id.shadow);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
        layoutParams.topMargin = editorDialogToolbar.getLayoutParams().height;
        viewFindViewById.setLayoutParams(layoutParams);
        fadingEdgeScrollView.getViewTreeObserver().addOnScrollChangedListener(new pj2(fadingEdgeScrollView, viewFindViewById));
        f();
        g();
        Button button = (Button) this.p.findViewById(R$id.button_primary);
        this.r = button;
        button.setId(R$id.editor_dialog_done_button);
        this.r.setOnClickListener(this);
        String str = (String) this.q.b;
        if (str != null) {
            this.r.setText(str);
        }
        Button button2 = (Button) this.p.findViewById(R$id.button_secondary);
        button2.setId(R$id.payments_edit_cancel_button);
        button2.setOnClickListener(this);
        e();
        this.p.setVisibility(4);
        show();
    }

    public final boolean j() {
        ArrayList arrayListC = c(true);
        int i = 0;
        while (true) {
            ArrayList arrayList = this.i;
            if (i >= arrayList.size()) {
                break;
            }
            yf0 yf0Var = (yf0) arrayList.get(i);
            yf0Var.c(arrayListC.contains(yf0Var));
            i++;
        }
        if (!arrayListC.isEmpty()) {
            View currentFocus = getCurrentFocus();
            yf0 yf0Var2 = ((currentFocus instanceof TextView) && currentFocus.getParent() != null && (currentFocus.getParent() instanceof yf0)) ? (yf0) currentFocus.getParent() : (!(currentFocus instanceof Spinner) || currentFocus.getTag() == null) ? null : (yf0) currentFocus.getTag();
            if (arrayListC.contains(yf0Var2)) {
                yf0Var2.a();
            } else {
                ((yf0) arrayListC.get(0)).a();
            }
        }
        arrayListC.isEmpty();
        return arrayListC.isEmpty();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        il ilVar;
        if (this.y != null) {
            return;
        }
        if (view.getId() != R$id.editor_dialog_done_button) {
            if (view.getId() == R$id.payments_edit_cancel_button) {
                b();
            }
        } else if (j()) {
            if (this.t && (ilVar = this.q) != null) {
                Runnable runnable = (Runnable) ilVar.g;
                if (runnable != null) {
                    runnable.run();
                }
                ilVar.g = null;
                ilVar.h = null;
                this.q = null;
            }
            this.s = true;
            b();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.A = true;
        il ilVar = this.q;
        if (ilVar != null) {
            if (this.s) {
                Runnable runnable = (Runnable) ilVar.g;
                if (runnable != null) {
                    runnable.run();
                }
                ilVar.g = null;
                ilVar.h = null;
                this.s = false;
            } else {
                Runnable runnable2 = (Runnable) ilVar.h;
                if (runnable2 != null) {
                    runnable2.run();
                }
                ilVar.g = null;
                ilVar.h = null;
            }
            this.q = null;
        }
        h();
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        if (this.y != null && this.A) {
            return;
        }
        if (getCurrentFocus() != null) {
            t41.f.e(getCurrentFocus());
        }
        int i = 0;
        while (true) {
            ArrayList arrayList = this.j;
            if (i >= arrayList.size()) {
                this.p.setVisibility(0);
                this.p.setLayerType(2, null);
                this.p.buildLayer();
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.p, (Property<View, Float>) View.TRANSLATION_Y, r0.getHeight(), 0.0f);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.p, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
                this.y = animatorSet;
                animatorSet.setDuration(300L);
                this.y.setInterpolator(h21.g);
                this.y.addListener(new d());
                this.y.start();
                return;
            }
            ((EditText) arrayList.get(i)).setEnabled(false);
            i++;
        }
    }
}
