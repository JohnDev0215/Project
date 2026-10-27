package org.chromium.chrome.browser.infobar;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import defpackage.dm0;
import defpackage.ko0;
import defpackage.q11;
import defpackage.un1;
import defpackage.yn2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;
import org.chromium.components.infobars.InfoBarLayout;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class PermissionInfoBar extends ConfirmInfoBar implements AndroidPermissionRequester.c {
    public final WindowAndroid r;
    public final int[] s;
    public final boolean t;
    public boolean u;
    public boolean v;
    public final String w;
    public final String x;
    public final String y;
    public final String z;

    public PermissionInfoBar(WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        super(i, R$color.infobar_icon_drawable_color, null, str3, null, str6, str7);
        this.r = windowAndroid;
        this.s = iArr;
        this.t = z;
        this.u = false;
        this.v = false;
        this.w = str2;
        this.x = str;
        this.y = str4;
        this.z = str5;
    }

    @CalledByNative
    public static PermissionInfoBar create(WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        return new PermissionInfoBar(windowAndroid, iArr, i, str, str2, str3, str4, str5, str6, str7, z);
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void c(boolean z) {
        this.u = false;
        if (this.k == null) {
            super.c(z);
            return;
        }
        if (z) {
            if (AndroidPermissionRequester.a(this.r, (int[]) this.s.clone(), this)) {
                return;
            }
        } else if (this.t) {
            this.u = true;
            Bundle bundle = new Bundle();
            bundle.putString("category", yn2.m(14));
            Context context = this.k;
            String name = SingleCategorySettings.class.getName();
            Intent intent = new Intent();
            intent.setClass(context, SettingsActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
                intent.addFlags(67108864);
            }
            intent.putExtra("show_fragment", name);
            intent.putExtra("show_fragment_args", bundle);
            ComponentName componentName = q11.a;
            try {
                context.startActivity(intent, null);
            } catch (ActivityNotFoundException unused) {
            }
        }
        super.c(z);
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void d() {
        if (!this.v) {
            this.v = true;
            this.j = q();
            InfoBarContainerView infoBarContainerView = ((InfoBarContainer) this.i).q;
            if (infoBarContainerView != null) {
                infoBarContainerView.q.l();
            }
        }
        super.d();
    }

    @Override // org.chromium.components.permissions.AndroidPermissionRequester.c
    public final void g() {
        super.c(true);
    }

    @Override // org.chromium.components.permissions.AndroidPermissionRequester.c
    public final void j() {
        k();
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.xz0
    public final boolean m() {
        return this.m || this.u;
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void o(InfoBarCompactLayout infoBarCompactLayout) {
        InfoBarCompactLayout.a aVar = new InfoBarCompactLayout.a(infoBarCompactLayout);
        aVar.b = this.x;
        aVar.b(this.w, new ko0(10, this));
        aVar.a();
    }

    @Override // org.chromium.components.infobars.ConfirmInfoBar, org.chromium.components.infobars.InfoBar
    public final void p(InfoBarLayout infoBarLayout) {
        super.p(infoBarLayout);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.y);
        String str = this.z;
        if (str != null && !str.isEmpty()) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new un1(infoBarLayout.getContext(), new dm0(9, this)), 0, spannableString.length(), 17);
            spannableStringBuilder.append((CharSequence) " ").append((CharSequence) spannableString);
        }
        infoBarLayout.getMessageLayout().a(0, spannableStringBuilder);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final boolean u() {
        return !this.v;
    }
}
