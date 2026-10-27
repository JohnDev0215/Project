package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.chromium.content.R$dimen;
import org.chromium.content.R$drawable;
import org.chromium.content.R$id;
import org.chromium.content.R$layout;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SuggestionsPopupWindow.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class jw2 implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, View.OnClickListener {
    public final Context e;
    public final TextSuggestionHost f;
    public final View g;
    public WindowAndroid h;
    public Activity i;
    public DisplayMetrics j;
    public PopupWindow k;
    public LinearLayout l;
    public String m;
    public int n;
    public TextView o;
    public TextView p;
    public ListView q;
    public LinearLayout r;
    public View s;
    public int t;
    public boolean u;

    /* JADX INFO: compiled from: SuggestionsPopupWindow.java */
    public class a extends BaseAdapter {
        public final LayoutInflater e;

        public a() {
            this.e = (LayoutInflater) jw2.this.e.getSystemService("layout_inflater");
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return jw2.this.n;
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return jw2.this.b(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = (TextView) view;
            if (textView == null) {
                textView = (TextView) this.e.inflate(R$layout.text_edit_suggestion_item, viewGroup, false);
            }
            textView.setText(jw2.this.c(i));
            return textView;
        }
    }

    public jw2(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        this.e = context;
        this.f = textSuggestionHost;
        this.h = windowAndroid;
        this.g = view;
        PopupWindow popupWindow = new PopupWindow();
        this.k = popupWindow;
        popupWindow.setWidth(-2);
        this.k.setHeight(-2);
        this.k.setBackgroundDrawable(c6.c(context.getResources(), R$drawable.floating_popup_background, 0));
        this.k.setElevation(context.getResources().getDimensionPixelSize(R$dimen.text_suggestion_popup_elevation));
        this.k.setInputMethodMode(2);
        this.k.setFocusable(true);
        this.k.setClippingEnabled(false);
        this.k.setOnDismissListener(this);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.l = (LinearLayout) layoutInflater.inflate(R$layout.text_edit_suggestion_container, (ViewGroup) null);
        this.t = context.getResources().getDimensionPixelSize(R$dimen.text_suggestion_popup_vertical_margin);
        ListView listView = (ListView) this.l.findViewById(R$id.suggestionContainer);
        this.q = listView;
        listView.setDivider(null);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R$layout.text_edit_suggestion_list_footer, (ViewGroup) null);
        this.r = linearLayout;
        this.q.addFooterView(linearLayout, null, false);
        this.q.setAdapter((ListAdapter) new a());
        this.q.setOnItemClickListener(this);
        this.s = this.l.findViewById(R$id.divider);
        TextView textView = (TextView) this.l.findViewById(R$id.addToDictionaryButton);
        this.o = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.l.findViewById(R$id.deleteButton);
        this.p = textView2;
        textView2.setOnClickListener(this);
        this.k.setContentView(this.l);
    }

    public abstract void a(int i);

    public abstract Object b(int i);

    public abstract SpannableString c(int i);

    public abstract int d();

    public final void e(double d, double d2, String str) {
        int i;
        this.n = d();
        this.m = str;
        Activity activity = (Activity) this.h.k().get();
        this.i = activity;
        Context context = this.e;
        if (activity != null) {
            this.j = activity.getResources().getDisplayMetrics();
        } else {
            this.j = context.getResources().getDisplayMetrics();
        }
        Activity activity2 = this.i;
        if (activity2 == null || activity2.isInMultiWindowMode()) {
            i = 0;
        } else {
            Rect rect = new Rect();
            this.i.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            i = rect.top;
        }
        this.r.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = ((((this.j.heightPixels - i) - this.r.getMeasuredHeight()) - (this.t * 2)) - this.l.getPaddingTop()) - this.l.getPaddingBottom();
        int iMin = Math.min(this.n, measuredHeight > 0 ? measuredHeight / context.getResources().getDimensionPixelSize(R$dimen.text_edit_suggestion_item_layout_height) : 0);
        this.n = iMin;
        if (iMin == 0) {
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
        }
        int paddingRight = this.l.getPaddingRight() + this.l.getPaddingLeft() + uh3.a(this.q.getAdapter(), null);
        this.l.measure(View.MeasureSpec.makeMeasureSpec(paddingRight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.j.heightPixels, Integer.MIN_VALUE));
        this.k.setWidth(paddingRight);
        int measuredWidth = this.l.getMeasuredWidth();
        int measuredHeight2 = this.l.getMeasuredHeight();
        int iRound = (int) Math.round(d - ((double) (measuredWidth / 2.0f)));
        int iRound2 = (int) Math.round(d2);
        int[] iArr = new int[2];
        View view = this.g;
        view.getLocationInWindow(iArr);
        this.k.showAtLocation(view, 0, Math.max(-this.l.getPaddingLeft(), Math.min(this.l.getPaddingRight() + (this.j.widthPixels - measuredWidth), iRound + iArr[0])), Math.min((iRound2 + iArr[1]) - this.l.getPaddingTop(), ((this.j.heightPixels - measuredHeight2) - this.l.getPaddingTop()) - this.t));
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        TextView textView = this.o;
        TextSuggestionHost textSuggestionHost = this.f;
        if (view != textView) {
            if (view == this.p) {
                N.MCBTtv2g(textSuggestionHost.e, textSuggestionHost);
                this.u = true;
                this.k.dismiss();
                return;
            }
            return;
        }
        Intent intent = new Intent("com.android.settings.USER_DICTIONARY_INSERT");
        intent.putExtra("word", this.m);
        intent.setFlags(intent.getFlags() | 268435456);
        this.e.startActivity(intent);
        N.MpJ8AQhr(textSuggestionHost.e, textSuggestionHost, this.m);
        this.u = true;
        this.k.dismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        boolean z = this.u;
        TextSuggestionHost textSuggestionHost = this.f;
        if (!z) {
            N.MnvYa0QF(textSuggestionHost.e, textSuggestionHost);
        }
        textSuggestionHost.k = null;
        textSuggestionHost.l = null;
        this.u = false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= this.n) {
            return;
        }
        a(i);
        this.u = true;
        this.k.dismiss();
    }
}
