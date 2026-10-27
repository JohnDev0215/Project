package com.google.android.material.datepicker;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.spotify.music.R;
import java.util.WeakHashMap;
import p.et2;
import p.n22;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public final class b extends n22 {
    public final TextView t;
    public final MaterialCalendarGridView u;

    public b(LinearLayout linearLayout, boolean z) {
        super(linearLayout);
        TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
        this.t = textView;
        WeakHashMap weakHashMap = zt2.a;
        new et2(R.id.tag_accessibility_heading, 3).b(textView, Boolean.TRUE);
        this.u = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
        if (z) {
            return;
        }
        textView.setVisibility(8);
    }
}
