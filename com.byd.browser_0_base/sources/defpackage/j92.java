package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ReaderModeToolbarButtonController.java */
/* JADX INFO: loaded from: classes.dex */
public final class j92 extends ad {
    public j92(AppCompatActivity appCompatActivity, z2 z2Var, bi1 bi1Var, Drawable drawable) {
        super(z2Var, bi1Var, drawable, appCompatActivity.getString(R$string.reader_view_text_alt), R$string.reader_mode_action_chip_label_simplify_page, 7);
    }

    @Override // defpackage.ad
    public final hw0 a(Tab tab) {
        return new hw0(tab.getContext().getResources(), "IPH_ContextualPageActions_QuietVariant", R$string.reader_mode_message_title, R$string.reader_view_text_alt);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        i92 i92Var;
        Tab tab = this.f.get();
        if (tab == null || (i92Var = (i92) tab.B().b(i92.class)) == null) {
            return;
        }
        i92Var.y1();
    }
}
