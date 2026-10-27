package com.byd.ui.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import c.a.e.c;
import c.a.e.d;
import c.a.e.e;
import c.a.e.f;

/* JADX INFO: loaded from: classes.dex */
public class TimesDialog extends View {
    public String E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3146d;
    public View s;
    public WindowManager x;
    public b y;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TimesDialog.this.a();
        }
    }

    public interface b {
        void a();
    }

    public TimesDialog(Context context) {
        super(context);
        context.setTheme(f.f2652a);
        View viewInflate = LayoutInflater.from(context).inflate(d.f2638e, (ViewGroup) null);
        this.s = viewInflate;
        this.f3146d = (TextView) viewInflate.findViewById(c.f2633i);
        TextView textView = (TextView) this.s.findViewById(c.f2632h);
        this.f3145c = textView;
        textView.setOnClickListener(new a());
        this.E3 = context.getResources().getString(e.f2644b);
        this.x = (WindowManager) context.getSystemService("window");
    }

    public void a() {
        View view = this.s;
        if (view != null) {
            this.x.removeView(view);
            this.y.a();
        }
    }

    public void b() {
        if (this.s != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.gravity = 48;
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.format = 1;
            layoutParams.flags = 8;
            layoutParams.type = 2045;
            this.x.addView(this.s, layoutParams);
        }
    }

    public void setMessageInfo(String str) {
        this.f3146d.setText(String.format(this.E3, str));
    }

    public void setTimesAction(b bVar) {
        this.y = bVar;
    }

    public void setMessageInfo(int i2) {
        this.f3146d.setText(i2);
    }
}
