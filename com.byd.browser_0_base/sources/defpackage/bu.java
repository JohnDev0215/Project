package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: compiled from: ClickableTextBubble.java */
/* JADX INFO: loaded from: classes.dex */
public final class bu extends u73 {
    public final Context q;
    public final LoadingView r;

    public bu(Activity activity, View view, int i, int i2, nn3 nn3Var, int i3, boolean z, View.OnTouchListener onTouchListener) {
        super(activity, view, activity.getString(i), activity.getString(i2), false, nn3Var, AppCompatResources.a(activity, i3), true, z);
        this.q = activity;
        setTouchInterceptor(onTouchListener);
        this.r = (LoadingView) this.n.findViewById(R$id.loading_view);
    }

    @Override // defpackage.u73
    public final void g(TextView textView, boolean z) {
        if (z) {
            textView.setTextAppearance(textView.getContext(), R$style.TextAppearance_ClickableButtonInverse);
        } else {
            textView.setTextAppearance(textView.getContext(), R$style.TextAppearance_ClickableButton);
        }
    }
}
