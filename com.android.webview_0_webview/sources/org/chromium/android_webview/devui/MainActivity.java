package org.chromium.android_webview.devui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.AbstractC0281Pm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC0443Ym;
import defpackage.AbstractC0622cn;
import defpackage.C0107Fr;
import defpackage.C1238nr;
import defpackage.C1567tr;
import defpackage.C1787xr;
import defpackage.C3;
import defpackage.K3;
import defpackage.LayoutInflaterFactory2C0974j4;
import defpackage.O3;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.android_webview.devui.MainActivity;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends O3 {
    public C0107Fr C;
    public boolean D;
    public final Map E = new HashMap();

    public final void i(int i) {
        K3 c1787xr;
        if (i == 0) {
            c1787xr = new C1787xr();
        } else if (i == 1) {
            c1787xr = new C1238nr();
        } else if (i != 2) {
            i = 0;
            c1787xr = new C1787xr();
        } else {
            c1787xr = new C1567tr();
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = (LayoutInflaterFactory2C0974j4) e();
        Objects.requireNonNull(layoutInflaterFactory2C0974j4);
        C3 c3 = new C3(layoutInflaterFactory2C0974j4);
        int i2 = AbstractC0371Um.q0;
        if (i2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        c3.f(i2, c1787xr, null, 2);
        c3.d();
        LinearLayout linearLayout = (LinearLayout) findViewById(AbstractC0371Um.B2);
        int childCount = linearLayout.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = linearLayout.getChildAt(i3);
            int iIntValue = ((Integer) this.E.get(Integer.valueOf(childAt.getId()))).intValue();
            TextView textView = (TextView) childAt;
            boolean z = i == iIntValue;
            textView.setTextAppearance(textView.getContext(), z ? AbstractC0622cn.Z3 : AbstractC0622cn.B8);
            int color = z ? getResources().getColor(AbstractC0281Pm.Y4) : getResources().getColor(AbstractC0281Pm.Z4);
            for (Drawable drawable : textView.getCompoundDrawables()) {
                if (drawable != null) {
                    drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                }
            }
        }
    }

    @Override // defpackage.O3, defpackage.J1, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(AbstractC0425Xm.C);
        this.D = true;
        C0107Fr c0107Fr = new C0107Fr(this);
        this.C = c0107Fr;
        if (c0107Fr.b()) {
            c0107Fr.c.show();
        }
        this.E.put(Integer.valueOf(AbstractC0371Um.F2), 0);
        this.E.put(Integer.valueOf(AbstractC0371Um.C2), 1);
        this.E.put(Integer.valueOf(AbstractC0371Um.D2), 2);
        LinearLayout linearLayout = (LinearLayout) findViewById(AbstractC0371Um.B2);
        View.OnClickListener onClickListener = new View.OnClickListener(this) { // from class: yr
            public final MainActivity s;

            {
                this.s = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity mainActivity = this.s;
                mainActivity.i(((Integer) mainActivity.E.get(Integer.valueOf(view.getId()))).intValue());
            }
        };
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            linearLayout.getChildAt(i).setOnClickListener(onClickListener);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(AbstractC0443Ym.b, menu);
        if (Build.VERSION.SDK_INT >= 24) {
            return true;
        }
        menu.findItem(AbstractC0371Um.P2).setVisible(false);
        return true;
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        this.D = intent.hasExtra("fragment-id");
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == AbstractC0371Um.P2 && Build.VERSION.SDK_INT >= 24) {
            startActivity(new Intent("android.settings.WEBVIEW_SETTINGS"));
            return true;
        }
        if (menuItem.getItemId() != AbstractC0371Um.O2) {
            return super.onOptionsItemSelected(menuItem);
        }
        startActivity(new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("https").authority("bugs.chromium.org").path("/p/chromium/issues/entry").appendQueryParameter("template", "Webview+Bugs").appendQueryParameter("labels", "Via-WebView-DevTools").build()));
        return true;
    }

    @Override // defpackage.O3, android.app.Activity
    public void onResume() {
        super.onResume();
        C0107Fr c0107Fr = this.C;
        if (c0107Fr.b()) {
            c0107Fr.a.a.setVisibility(0);
        } else {
            c0107Fr.a.a();
        }
        if (this.D) {
            this.D = false;
            Bundle extras = getIntent().getExtras();
            i(extras != null ? extras.getInt("fragment-id", 0) : 0);
        }
    }
}
