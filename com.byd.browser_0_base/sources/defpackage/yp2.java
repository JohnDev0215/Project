package defpackage;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.browser.ui.messages.R$color;
import org.chromium.chrome.browser.ui.messages.R$dimen;
import org.chromium.chrome.browser.ui.messages.R$drawable;
import org.chromium.chrome.browser.ui.messages.R$id;
import org.chromium.chrome.browser.ui.messages.R$layout;
import org.chromium.chrome.browser.ui.messages.R$string;
import org.chromium.chrome.browser.ui.messages.R$style;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SnackbarView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yp2 {
    public final WindowAndroid a;
    public final ViewGroup b;
    public final ViewGroup c;
    public final TemplatePreservingTextView d;
    public final TextView e;
    public final ImageView f;
    public final int g;
    public final boolean h;
    public final ViewGroup i;
    public ViewGroup j;
    public tp2 k;
    public final View l;
    public final Rect m = new Rect();
    public final Rect n = new Rect();
    public final int[] o = new int[2];
    public final a p = new a();

    /* JADX INFO: compiled from: SnackbarView.java */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            yp2 yp2Var = yp2.this;
            ViewGroup viewGroup = yp2Var.j;
            Rect rect = yp2Var.m;
            viewGroup.getWindowVisibleDisplayFrame(rect);
            Rect rect2 = yp2Var.n;
            if (rect.equals(rect2)) {
                return;
            }
            rect2.set(rect);
            ViewGroup viewGroup2 = yp2Var.b;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup2.getLayoutParams();
            int i9 = layoutParams.bottomMargin;
            int i10 = layoutParams.width;
            int i11 = layoutParams.gravity;
            ViewGroup viewGroup3 = yp2Var.j;
            int[] iArr = yp2Var.o;
            viewGroup3.getLocationInWindow(iArr);
            layoutParams.bottomMargin = Math.max(0, (yp2Var.j.getHeight() + iArr[1]) - rect.bottom);
            if (yp2Var.h) {
                layoutParams.width = Math.min(yp2Var.j.getResources().getDimensionPixelSize(R$dimen.snackbar_width_tablet), yp2Var.j.getWidth() - (yp2Var.j.getResources().getDimensionPixelSize(R$dimen.snackbar_margin_tablet) * 2));
                layoutParams.gravity = 81;
            }
            if (i9 == layoutParams.bottomMargin && i10 == layoutParams.width && i11 == layoutParams.gravity) {
                return;
            }
            viewGroup2.setLayoutParams(layoutParams);
        }
    }

    public yp2(Activity activity, View.OnClickListener onClickListener, tp2 tp2Var, ViewGroup viewGroup, WindowAndroid windowAndroid) {
        this.h = DeviceFormFactor.a(activity);
        this.i = viewGroup;
        this.a = windowAndroid;
        this.l = activity.findViewById(R.id.content);
        this.j = viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(activity).inflate(R$layout.snackbar, this.j, false);
        this.b = viewGroup2;
        if (n3.O0()) {
            viewGroup2.setLayoutDirection(1);
        } else {
            viewGroup2.setLayoutDirection(0);
        }
        this.c = (ViewGroup) viewGroup2.findViewById(R$id.snackbar);
        this.g = viewGroup2.getResources().getInteger(R.integer.config_mediumAnimTime);
        this.d = (TemplatePreservingTextView) viewGroup2.findViewById(R$id.snackbar_message);
        TextView textView = (TextView) viewGroup2.findViewById(R$id.snackbar_button);
        this.e = textView;
        textView.setOnClickListener(onClickListener);
        this.f = (ImageView) viewGroup2.findViewById(R$id.snackbar_profile_image);
        d(tp2Var, false);
    }

    public final void a() {
        TemplatePreservingTextView templatePreservingTextView = this.d;
        StringBuilder sb = new StringBuilder(templatePreservingTextView.getContentDescription());
        TextView textView = this.e;
        if (textView.getContentDescription() != null) {
            sb.append(". ");
            sb.append(textView.getContentDescription());
            sb.append(". ");
            sb.append(this.b.getResources().getString(R$string.bottom_bar_screen_position));
        }
        templatePreservingTextView.announceForAccessibility(sb);
    }

    public final void b(ViewGroup viewGroup) {
        View view = this.l;
        a aVar = this.p;
        view.removeOnLayoutChangeListener(aVar);
        if (viewGroup == null) {
            viewGroup = this.i;
        }
        this.j = viewGroup;
        ViewGroup viewGroup2 = this.b;
        if (viewGroup2.getParent() != null) {
            ((ViewGroup) viewGroup2.getParent()).removeView(viewGroup2);
        }
        this.j.addView(viewGroup2);
        view.addOnLayoutChangeListener(aVar);
    }

    public final void c(TextView textView, CharSequence charSequence, boolean z) {
        if (textView.getText().toString().equals(charSequence)) {
            return;
        }
        textView.animate().cancel();
        if (!z) {
            textView.setText(charSequence);
            return;
        }
        textView.setAlpha(0.0f);
        textView.setText(charSequence);
        textView.animate().alpha(1.0f).setDuration(this.g).setListener(null);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final boolean d(tp2 tp2Var, boolean z) {
        int i;
        int iC;
        if (this.k == tp2Var) {
            return false;
        }
        this.k = tp2Var;
        int i2 = tp2Var.i ? 1 : 5;
        TemplatePreservingTextView templatePreservingTextView = this.d;
        templatePreservingTextView.setMaxLines(i2);
        templatePreservingTextView.setTemplate(tp2Var.c);
        c(templatePreservingTextView, tp2Var.b, z);
        if (tp2Var.n == 1) {
            i = R$style.TextAppearance_TextMedium_Primary_OnAccent1;
        } else {
            i = tp2Var.h;
            if (i == 0) {
                i = R$style.TextAppearance_TextMedium_Primary;
            }
        }
        templatePreservingTextView.setTextAppearance(templatePreservingTextView.getContext(), i);
        int i3 = tp2Var.n == 1 ? R$style.TextAppearance_Button_Text_Filled : R$style.TextButton;
        TextView textView = this.e;
        c6.f(textView, i3);
        int i4 = tp2Var.n;
        ViewGroup viewGroup = this.b;
        if (i4 == 1) {
            iC = viewGroup.getContext().getColor(R$color.default_control_color_active_baseline);
        } else {
            iC = tp2Var.g;
            if (iC == 0) {
                Context context = viewGroup.getContext();
                String str = ii2.a;
                iC = yp.c(context, org.chromium.components.browser_ui.styles.R$dimen.snackbar_background_color_elev);
            }
        }
        boolean z2 = this.h;
        ViewGroup viewGroup2 = this.c;
        if (z2) {
            viewGroup2.setBackgroundResource(R$drawable.snackbar_background_tablet);
            ((GradientDrawable) viewGroup2.getBackground().mutate()).setColor(iC);
        } else {
            viewGroup2.setBackgroundColor(iC);
        }
        if (tp2Var.d != null) {
            textView.setVisibility(0);
            textView.setContentDescription(tp2Var.d);
            c(textView, tp2Var.d, z);
            if (templatePreservingTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) templatePreservingTextView.getLayoutParams();
                layoutParams.setMarginEnd(0);
                templatePreservingTextView.setLayoutParams(layoutParams);
            }
        } else {
            textView.setVisibility(8);
            if (templatePreservingTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) templatePreservingTextView.getLayoutParams();
                layoutParams2.setMarginEnd(this.j.getResources().getDimensionPixelSize(R$dimen.snackbar_text_view_margin));
                templatePreservingTextView.setLayoutParams(layoutParams2);
            }
        }
        Drawable drawable = tp2Var.k;
        ImageView imageView = this.f;
        if (drawable != null) {
            imageView.setVisibility(0);
            imageView.setImageDrawable(drawable);
        } else {
            imageView.setVisibility(8);
        }
        if (z2) {
            viewGroup.findViewById(R$id.snackbar_shadow_left).setVisibility(0);
            viewGroup.findViewById(R$id.snackbar_shadow_right).setVisibility(0);
        }
        return true;
    }
}
