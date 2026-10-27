package org.chromium.components.permissions;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import defpackage.ez;
import defpackage.j90;
import defpackage.z21;
import org.chromium.components.permissions.R$id;
import org.chromium.components.permissions.a;
import org.chromium.components.permissions.b;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: compiled from: ItemChooserDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements a.InterfaceC0139a {
    public final Context a;
    public final Window b;
    public z21 c;
    public final c d;
    public final TextViewWithClickableSpans e;
    public final ProgressBar f;
    public final ListView g;
    public final TextView h;
    public final Button i;
    public final C0140b j;
    public final org.chromium.components.permissions.a k;
    public boolean l;

    /* JADX INFO: compiled from: ItemChooserDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            j90 item;
            b bVar = b.this;
            c cVar = bVar.d;
            org.chromium.components.permissions.a aVar = bVar.k;
            int i = aVar.i;
            cVar.a((i == -1 || (item = aVar.getItem(i)) == null) ? "" : item.a);
            bVar.c.setOnDismissListener(null);
            bVar.c.dismiss();
        }
    }

    /* JADX INFO: renamed from: org.chromium.components.permissions.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ItemChooserDialog.java */
    public static class C0140b {
        public final CharSequence a;
        public final CharSequence b;
        public final CharSequence c;
        public final CharSequence d;
        public final CharSequence e;
        public final CharSequence f;
        public final CharSequence g;

        public C0140b(SpannableString spannableString, CharSequence charSequence, String str, SpannableString spannableString2, SpannableString spannableString3, SpannableString spannableString4, String str2) {
            this.a = spannableString;
            this.b = charSequence;
            this.c = str;
            this.d = spannableString2;
            this.e = spannableString3;
            this.f = spannableString4;
            this.g = str2;
        }
    }

    /* JADX INFO: compiled from: ItemChooserDialog.java */
    public interface c {
        void a(String str);
    }

    public b(Context context, Window window, c cVar, C0140b c0140b) {
        this.a = context;
        this.b = window;
        this.d = cVar;
        this.j = c0140b;
        final LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.item_chooser_dialog, (ViewGroup) null);
        ListView listView = (ListView) linearLayout.findViewById(R$id.items);
        this.g = listView;
        this.f = (ProgressBar) linearLayout.findViewById(R$id.progress);
        TextView textView = (TextView) linearLayout.findViewById(R$id.status);
        this.h = textView;
        TextViewWithClickableSpans textViewWithClickableSpans = (TextViewWithClickableSpans) linearLayout.findViewById(R$id.dialog_title);
        TextViewWithClickableSpans textViewWithClickableSpans2 = (TextViewWithClickableSpans) linearLayout.findViewById(R$id.not_found_message);
        this.e = textViewWithClickableSpans2;
        textViewWithClickableSpans.setText(c0140b.a);
        textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
        textViewWithClickableSpans2.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Button button = (Button) linearLayout.findViewById(R$id.positive);
        this.i = button;
        button.setText(c0140b.g);
        button.setEnabled(false);
        a aVar = new a();
        org.chromium.components.permissions.a aVar2 = new org.chromium.components.permissions.a(R$layout.item_chooser_dialog_row, context, true);
        this.k = aVar2;
        aVar2.setNotifyOnChange(true);
        aVar2.m = this;
        button.setOnClickListener(aVar);
        listView.setOnItemClickListener(aVar2);
        listView.setAdapter((ListAdapter) aVar2);
        listView.setChoiceMode(1);
        listView.setEmptyView(textViewWithClickableSpans2);
        listView.setDivider(null);
        b(1);
        this.l = false;
        z21 z21Var = new z21(this, context);
        this.c = z21Var;
        z21Var.requestWindowFeature(1);
        this.c.setCanceledOnTouchOutside(true);
        this.c.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.c.setOnDismissListener(new ez(1, this));
        Window window2 = this.c.getWindow();
        if (!DeviceFormFactor.a(context)) {
            window2.setBackgroundDrawable(new ColorDrawable(-1));
            window2.setGravity(48);
            window2.setLayout(-1, -2);
        }
        this.c.show();
        linearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: y21
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                b bVar = this.e;
                bVar.getClass();
                if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                    return;
                }
                View viewFindViewById = linearLayout.findViewById(R$id.container);
                int height = bVar.b.getDecorView().getHeight();
                viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(a8.o((Math.round((((height / r1) * 0.3f) / 48.0f) - 0.5f) + 0.5f) * 48.0f, 72.0f, 408.0f) * bVar.a.getResources().getDisplayMetrics().density)));
            }
        });
    }

    public final void a(CharSequence charSequence, SpannableString spannableString) {
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        TextViewWithClickableSpans textViewWithClickableSpans = this.e;
        textViewWithClickableSpans.setText(charSequence);
        textViewWithClickableSpans.setVisibility(0);
        this.h.setText(spannableString);
    }

    public final void b(int i) {
        ProgressBar progressBar = this.f;
        ListView listView = this.g;
        TextViewWithClickableSpans textViewWithClickableSpans = this.e;
        if (i != 0) {
            TextView textView = this.h;
            C0140b c0140b = this.j;
            if (i != 1) {
                if (i == 2) {
                    textView.setText(c0140b.d);
                    progressBar.setVisibility(8);
                    listView.setVisibility(0);
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    boolean zIsEmpty = this.k.isEmpty();
                    textView.setText(zIsEmpty ? c0140b.e : c0140b.f);
                    textViewWithClickableSpans.setText(c0140b.c);
                    textViewWithClickableSpans.setVisibility(zIsEmpty ? 0 : 8);
                    return;
                }
            }
            textView.setText(c0140b.b);
        }
        listView.setVisibility(8);
        progressBar.setVisibility(0);
        textViewWithClickableSpans.setVisibility(8);
    }
}
