package org.chromium.components.infobars;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.wz0;
import defpackage.xz0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.NearOomInfoBar;
import org.chromium.components.browser_ui.widget.DualControlLayout;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InfoBar implements wz0, xz0 {
    public final int e;
    public final Bitmap f;
    public final int g;
    public final CharSequence h;
    public a i;
    public View j;
    public Context k;
    public boolean l;
    public boolean m = true;
    public long n;

    public interface a {
    }

    public InfoBar(int i, int i2, CharSequence charSequence, Bitmap bitmap) {
        this.e = i;
        this.f = bitmap;
        this.g = i2;
        this.h = charSequence;
    }

    @Override // defpackage.xz0
    public final void b() {
        this.m = true;
    }

    @CalledByNative
    public final boolean closeInfoBar() {
        if (this.l) {
            return false;
        }
        this.l = true;
        if (!((InfoBarContainer) this.i).n) {
            t();
            InfoBarContainer infoBarContainer = (InfoBarContainer) this.i;
            ArrayList<InfoBar> arrayList = infoBarContainer.g;
            if (arrayList.remove(this)) {
                Iterator<InfoBarContainer.f> it = infoBarContainer.h.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    }
                    InfoBarContainer.f fVar = (InfoBarContainer.f) aVar.next();
                    arrayList.isEmpty();
                    fVar.d(this);
                }
                InfoBarContainerLayout infoBarContainerLayout = infoBarContainer.q.q;
                infoBarContainerLayout.g.remove(this);
                infoBarContainerLayout.l();
            }
        }
        this.i = null;
        this.j = null;
        this.k = null;
        return true;
    }

    @Override // defpackage.wz0
    public void d() {
        long j = this.n;
        if (j != 0) {
            N.M2s4r3u7(j, this);
        }
    }

    public int e() {
        return 2;
    }

    @Override // defpackage.xz0
    public final View getView() {
        return this.j;
    }

    @Override // defpackage.xz0
    public final String i() {
        View view = this.j;
        if (view == null) {
            return "";
        }
        TextView textView = (TextView) view.findViewById(R$id.infobar_message);
        CharSequence charSequenceR = r(textView != null ? textView.getText() : null);
        if (charSequenceR.length() > 0) {
            charSequenceR = ((Object) charSequenceR) + " ";
        }
        return ((Object) charSequenceR) + this.k.getString(R$string.bottom_bar_screen_position);
    }

    @Override // defpackage.wz0
    public void k() {
        long j = this.n;
        if (j == 0 || this.l) {
            return;
        }
        N.MKozrBH2(j, this);
    }

    public boolean m() {
        return this.m;
    }

    @Override // defpackage.xz0
    public final int n() {
        long j = this.n;
        if (j == 0) {
            return -1;
        }
        return N.MIZvQmze(j, this);
    }

    public final View q() {
        if (u()) {
            InfoBarCompactLayout infoBarCompactLayout = new InfoBarCompactLayout(this.k, this, this.e, this.g, this.f);
            o(infoBarCompactLayout);
            this.j = infoBarCompactLayout;
        } else {
            InfoBarLayout infoBarLayout = new InfoBarLayout(this.k, this, this.e, this.g, this.f, this.h);
            p(infoBarLayout);
            ChromeImageView chromeImageView = infoBarLayout.o;
            if (chromeImageView != null) {
                infoBarLayout.addView(chromeImageView);
            }
            infoBarLayout.addView(infoBarLayout.k);
            Iterator it = infoBarLayout.l.iterator();
            while (it.hasNext()) {
                infoBarLayout.addView((View) it.next());
            }
            DualControlLayout dualControlLayout = infoBarLayout.p;
            if (dualControlLayout != null) {
                infoBarLayout.addView(dualControlLayout);
            }
            ViewGroup viewGroup = infoBarLayout.m;
            if (viewGroup != null) {
                infoBarLayout.addView(viewGroup);
            }
            infoBarLayout.addView(infoBarLayout.j);
            this.j = infoBarLayout;
        }
        return this.j;
    }

    public CharSequence r(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence;
    }

    @CalledByNative
    public void resetNativeInfoBar() {
        this.n = 0L;
    }

    public final void s(int i) {
        long j = this.n;
        if (j != 0) {
            N.MQGsrOhB(j, this, i);
        }
    }

    @CalledByNative
    public final void setNativeInfoBar(long j) {
        this.n = j;
    }

    public boolean u() {
        return this instanceof NearOomInfoBar;
    }

    public void t() {
    }

    @Override // defpackage.wz0
    public void c(boolean z) {
    }

    public void o(InfoBarCompactLayout infoBarCompactLayout) {
    }

    public void p(InfoBarLayout infoBarLayout) {
    }
}
