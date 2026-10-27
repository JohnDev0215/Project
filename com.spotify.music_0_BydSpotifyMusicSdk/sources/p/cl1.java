package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.icu.text.DateFormat;
import android.icu.util.TimeZone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.spotify.music.R;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cl1 extends BaseAdapter {
    public static final int l = bs2.c(null).getMaximum(4);
    public final bl1 c;
    public jh1 e;
    public final ip k;

    public cl1(bl1 bl1Var, ip ipVar) {
        this.c = bl1Var;
        this.k = ipVar;
        throw null;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final Long getItem(int i) {
        bl1 bl1Var = this.c;
        if (i < bl1Var.c() || i > b()) {
            return null;
        }
        int iC = (i - bl1Var.c()) + 1;
        Calendar calendarA = bs2.a(bl1Var.c);
        calendarA.set(5, iC);
        return Long.valueOf(calendarA.getTimeInMillis());
    }

    public final int b() {
        bl1 bl1Var = this.c;
        return (bl1Var.c() + bl1Var.m) - 1;
    }

    public final void c(TextView textView, long j) {
        if (textView == null) {
            return;
        }
        if (j >= ((c60) this.k.k).c) {
            textView.setEnabled(true);
            throw null;
        }
        textView.setEnabled(false);
        pv0 pv0Var = (pv0) this.e.g;
        pv0Var.getClass();
        qf1 qf1Var = new qf1();
        qf1 qf1Var2 = new qf1();
        oc2 oc2Var = (oc2) pv0Var.g;
        qf1Var.setShapeAppearanceModel(oc2Var);
        qf1Var2.setShapeAppearanceModel(oc2Var);
        qf1Var.k((ColorStateList) pv0Var.e);
        float f = pv0Var.b;
        ColorStateList colorStateList = (ColorStateList) pv0Var.f;
        qf1Var.c.k = f;
        qf1Var.invalidateSelf();
        pf1 pf1Var = qf1Var.c;
        if (pf1Var.d != colorStateList) {
            pf1Var.d = colorStateList;
            qf1Var.onStateChange(qf1Var.getState());
        }
        ColorStateList colorStateList2 = (ColorStateList) pv0Var.d;
        textView.setTextColor(colorStateList2);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList2.withAlpha(30), qf1Var, qf1Var2);
        Rect rect = (Rect) pv0Var.c;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        WeakHashMap weakHashMap = zt2.a;
        it2.q(textView, insetDrawable);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        bl1 bl1Var = this.c;
        return bl1Var.c() + bl1Var.m;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i / this.c.l;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if (this.e == null) {
            this.e = new jh1(context);
        }
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day, viewGroup, false);
        }
        bl1 bl1Var = this.c;
        int iC = i - bl1Var.c();
        if (iC < 0 || iC >= bl1Var.m) {
            textView.setVisibility(8);
            textView.setEnabled(false);
        } else {
            int i2 = iC + 1;
            textView.setTag(bl1Var);
            textView.setText(String.format(textView.getResources().getConfiguration().locale, "%d", Integer.valueOf(i2)));
            Calendar calendarA = bs2.a(bl1Var.c);
            calendarA.set(5, i2);
            long timeInMillis = calendarA.getTimeInMillis();
            Calendar calendarB = bs2.b();
            calendarB.set(5, 1);
            Calendar calendarA2 = bs2.a(calendarB);
            calendarA2.get(2);
            int i3 = calendarA2.get(1);
            calendarA2.getMaximum(7);
            calendarA2.getActualMaximum(5);
            calendarA2.getTimeInMillis();
            if (bl1Var.k == i3) {
                DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton("MMMEd", Locale.getDefault());
                instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
                textView.setContentDescription(instanceForSkeleton.format(new Date(timeInMillis)));
            } else {
                DateFormat instanceForSkeleton2 = DateFormat.getInstanceForSkeleton("yMMMEd", Locale.getDefault());
                instanceForSkeleton2.setTimeZone(TimeZone.getTimeZone("UTC"));
                textView.setContentDescription(instanceForSkeleton2.format(new Date(timeInMillis)));
            }
            textView.setVisibility(0);
            textView.setEnabled(true);
        }
        Long item = getItem(i);
        if (item != null) {
            c(textView, item.longValue());
        }
        return textView;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
