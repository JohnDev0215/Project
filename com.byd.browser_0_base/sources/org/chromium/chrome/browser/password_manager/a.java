package org.chromium.chrome.browser.password_manager;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ViewOnClickListenerC0202o0;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: AccountChooserDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends ArrayAdapter<Credential> {
    public final /* synthetic */ AccountChooserDialog e;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.password_manager.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccountChooserDialog.java */
    public class ViewOnClickListenerC0104a implements View.OnClickListener {
        public final /* synthetic */ String e;

        public ViewOnClickListenerC0104a(String str) {
            this.e = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            AccountChooserDialog accountChooserDialog = a.this.e;
            String strMR6Af3ZS = N.MR6Af3ZS(this.e, 0);
            int i = R$layout.material_tooltip;
            accountChooserDialog.getClass();
            Context context = view.getContext();
            Resources resources = context.getResources();
            TextView textView = (TextView) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
            textView.setText(strMR6Af3ZS);
            textView.announceForAccessibility(strMR6Af3ZS);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            int width = view.getLayoutDirection() == 1 ? iArr[0] : (iArr[0] + view.getWidth()) - textView.getMeasuredWidth();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = ((iArr[1] - resources.getDimensionPixelSize(R$dimen.psl_info_tooltip_margin)) - (identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0)) - textView.getMeasuredHeight();
            int i2 = view.getLayoutDirection() == 1 ? 8388613 : 8388611;
            Toast toast = new Toast(context, textView);
            toast.d(i2 | 48, width, dimensionPixelSize);
            toast.a.setDuration(0);
            toast.e();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(AccountChooserDialog accountChooserDialog, Context context, Credential[] credentialArr) {
        super(context, 0, credentialArr);
        this.e = accountChooserDialog;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R$layout.account_chooser_dialog_item, viewGroup, false);
        }
        view.setSelected(false);
        view.setOnClickListener(new ViewOnClickListenerC0202o0(i, 0, this));
        view.setTag(Integer.valueOf(i));
        Credential item = getItem(i);
        ImageView imageView = (ImageView) view.findViewById(R$id.profile_image);
        Drawable drawableA = item.f;
        if (drawableA == null) {
            drawableA = AppCompatResources.a(getContext(), R$drawable.logo_avatar_anonymous);
        }
        imageView.setImageDrawable(drawableA);
        TextView textView = (TextView) view.findViewById(R$id.main_name);
        TextView textView2 = (TextView) view.findViewById(R$id.secondary_name);
        String str = item.d;
        boolean zIsEmpty = str.isEmpty();
        String str2 = item.a;
        if (zIsEmpty) {
            String str3 = item.b;
            if (str3.isEmpty()) {
                textView.setText(str2);
                textView2.setVisibility(8);
            } else {
                textView.setText(str3);
                textView2.setText(str2);
                textView2.setVisibility(0);
            }
        } else {
            textView.setText(str2);
            textView2.setText(str);
            textView2.setVisibility(0);
        }
        ImageButton imageButton = (ImageButton) view.findViewById(R$id.psl_info_btn);
        String str4 = item.c;
        if (!str4.isEmpty()) {
            imageButton.setVisibility(0);
            imageButton.setOnClickListener(new ViewOnClickListenerC0104a(str4));
        }
        return view;
    }
}
