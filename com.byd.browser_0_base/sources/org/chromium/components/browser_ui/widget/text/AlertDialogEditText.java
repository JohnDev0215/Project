package org.chromium.components.browser_ui.widget.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStructure;
import androidx.appcompat.widget.AppCompatEditText;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class AlertDialogEditText extends AppCompatEditText {
    public GURL j;

    public AlertDialogEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    public final void onProvideAutofillStructure(ViewStructure viewStructure, int i) {
        GURL gurl = this.j;
        if (gurl != null && !gurl.k()) {
            viewStructure.setWebDomain(this.j.j());
        }
        super.onProvideAutofillStructure(viewStructure, i);
    }

    public void setUrl(GURL gurl) {
        this.j = gurl;
    }
}
