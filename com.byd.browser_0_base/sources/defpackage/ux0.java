package defpackage;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.download.home.list.view.CircularProgressView;

/* JADX INFO: compiled from: InProgressViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ux0 extends i81 {
    public static final /* synthetic */ int D = 0;
    public final TextView A;
    public final CircularProgressView B;
    public final ImageButton C;
    public final boolean z;

    public ux0(View view, boolean z) {
        super(view);
        this.z = z;
        this.A = (TextView) view.findViewById(R$id.caption);
        this.B = (CircularProgressView) view.findViewById(R$id.action_button);
        this.C = (ImageButton) view.findViewById(R$id.cancel_button);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0116  */
    @Override // defpackage.i81
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void u(org.chromium.ui.modelutil.PropertyModel r19, defpackage.f81 r20) {
        /*
            Method dump skipped, instruction units count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ux0.u(org.chromium.ui.modelutil.PropertyModel, f81):void");
    }
}
