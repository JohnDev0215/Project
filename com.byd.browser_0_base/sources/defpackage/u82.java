package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Size;
import android.widget.RemoteViews;
import defpackage.zf2;
import org.chromium.chrome.browser.ui.quickactionsearchwidget.R;

/* JADX INFO: compiled from: QuickActionSearchWidgetProviderDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u82 {
    public final ComponentName a;
    public final Intent b;
    public final Intent c;
    public final b d;
    public final b e;
    public final b f;
    public final b g;

    /* JADX INFO: compiled from: QuickActionSearchWidgetProviderDelegate.java */
    public static class a {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d = true;

        public a(zf2.a aVar) {
            this.a = aVar.c;
            this.b = aVar.e;
            this.c = aVar.d;
        }

        public final void a(int i) {
            boolean z = this.a;
            int i2 = !z ? 1 : 0;
            boolean z2 = this.b;
            int i3 = i2 + (!z2 ? 1 : 0);
            boolean z3 = this.c;
            int i4 = i3 + (!z3 ? 1 : 0);
            boolean z4 = this.d;
            int i5 = i4 + (!z4 ? 1 : 0);
            if (i > i5 && z4) {
                this.d = false;
                i5++;
            }
            if (i > i5 && z3) {
                this.c = false;
                i5++;
            }
            if (i > i5 && z2) {
                this.b = false;
                i5++;
            }
            if (i <= i5 || !z) {
                return;
            }
            this.a = false;
        }
    }

    /* JADX INFO: compiled from: QuickActionSearchWidgetProviderDelegate.java */
    public static class b {
        public final int a;
        public final int b;
        public final int c;
        public final int d;

        public b(Context context, int i, int i2, int i3, int i4, int i5) {
            Resources resources = context.getResources();
            this.a = i;
            this.b = b(resources, i2, 0);
            this.c = b(resources, i3, 0);
            this.d = b(resources, i4, i5);
        }

        public static int b(Resources resources, int i, int i2) {
            if (i == 0) {
                return 0;
            }
            float f = resources.getDisplayMetrics().density;
            float dimension = resources.getDimension(i);
            if (i2 != 0) {
                dimension += resources.getDimension(i2) * 2.0f;
            }
            return (int) (dimension / f);
        }

        public final int a(int i) {
            return (int) Math.max(0.0d, Math.ceil((((double) (this.b - i)) * 1.0d) / ((double) this.d)));
        }
    }

    public u82(Context context, ComponentName componentName, Intent intent, Intent intent2) {
        this.a = componentName;
        this.b = intent;
        this.c = intent2;
        Context applicationContext = context.getApplicationContext();
        this.d = new b(applicationContext, R.layout.quick_action_search_widget_medium_layout, R.dimen.quick_action_search_widget_medium_width, R.dimen.quick_action_search_widget_medium_height, R.dimen.quick_action_search_widget_medium_button_width, R.dimen.quick_action_search_widget_medium_button_horizontal_margin);
        this.e = new b(applicationContext, R.layout.quick_action_search_widget_small_layout, R.dimen.quick_action_search_widget_small_width, R.dimen.quick_action_search_widget_small_height, R.dimen.quick_action_search_widget_small_button_width, R.dimen.quick_action_search_widget_small_button_horizontal_margin);
        this.f = new b(applicationContext, R.layout.quick_action_search_widget_xsmall_layout, R.dimen.quick_action_search_widget_xsmall_width, R.dimen.quick_action_search_widget_xsmall_height, R.dimen.quick_action_search_widget_xsmall_button_width, R.dimen.quick_action_search_widget_xsmall_button_horizontal_margin);
        this.g = new b(applicationContext, R.layout.quick_action_search_widget_dino_layout, R.dimen.quick_action_search_widget_dino_size, R.dimen.quick_action_search_widget_dino_size, 0, 0);
    }

    public static void a(RemoteViews remoteViews, zf2.a aVar, b bVar, int i) {
        a aVar2 = new a(aVar);
        aVar2.a(bVar.a(i));
        remoteViews.setViewVisibility(R.id.voice_search_quick_action_button, aVar2.a ? 0 : 8);
        remoteViews.setViewVisibility(R.id.incognito_quick_action_button, aVar2.b ? 0 : 8);
        remoteViews.setViewVisibility(R.id.lens_quick_action_button, aVar2.c ? 0 : 8);
        remoteViews.setViewVisibility(R.id.dino_quick_action_button, aVar2.d ? 0 : 8);
    }

    public static PendingIntent c(Context context, Intent intent) {
        intent.putExtra("org.chromium.chrome.browser.ui.searchactivityutils.FROM_QUICK_ACTION_SEARCH_WIDGET", true);
        return PendingIntent.getActivity(context, 0, intent, q11.d(false) | 134217728);
    }

    public final RemoteViews b(Context context, int i, int i2) {
        RemoteViews remoteViewsE = e(context, R.layout.quick_action_search_widget_dino_layout);
        f(context.getApplicationContext().getResources(), remoteViewsE, i, i2);
        return remoteViewsE;
    }

    public final PendingIntent d(Context context, String str) {
        Intent intent = new Intent(str);
        intent.setComponent(this.a);
        intent.addFlags(268435456);
        q11.a(intent);
        return c(context, intent);
    }

    public final RemoteViews e(Context context, int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i);
        remoteViews.setOnClickPendingIntent(R.id.quick_action_search_widget_search_bar_container, d(context, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_EXTENDED_TEXT_SEARCH"));
        remoteViews.setOnClickPendingIntent(R.id.voice_search_quick_action_button, d(context, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_EXTENDED_VOICE_SEARCH"));
        remoteViews.setOnClickPendingIntent(R.id.incognito_quick_action_button, c(context, this.b));
        remoteViews.setOnClickPendingIntent(R.id.lens_quick_action_button, d(context, "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_LENS_SEARCH"));
        remoteViews.setOnClickPendingIntent(R.id.dino_quick_action_button, c(context, this.c));
        return remoteViews;
    }

    public final void f(Resources resources, RemoteViews remoteViews, int i, int i2) {
        float f = resources.getDisplayMetrics().density;
        int iMin = Math.min(i, i2);
        Size size = new Size((int) (((i - iMin) / 2.0f) * f), (int) (((i2 - iMin) / 2.0f) * f));
        remoteViews.setViewPadding(R.id.dino_quick_action_area, size.getWidth(), size.getHeight(), size.getWidth(), size.getHeight());
        float fMin = (Math.min(i, i2) * 1.0f) / this.g.b;
        float dimension = resources.getDimension(R.dimen.quick_action_search_widget_dino_padding_vertical) * fMin;
        float dimension2 = resources.getDimension(R.dimen.quick_action_search_widget_dino_padding_start) * fMin;
        float f2 = resources.getConfiguration().getLayoutDirection() == 1 ? 0.0f : dimension2;
        if (!(resources.getConfiguration().getLayoutDirection() == 1)) {
            dimension2 = 0.0f;
        }
        int i3 = (int) dimension;
        remoteViews.setViewPadding(R.id.dino_quick_action_button, (int) f2, i3, (int) dimension2, i3);
        remoteViews.setFloat(R.id.dino_quick_action_text, "setTextSize", (resources.getDimension(R.dimen.quick_action_search_widget_dino_text_size) * fMin) / resources.getDisplayMetrics().scaledDensity);
    }
}
