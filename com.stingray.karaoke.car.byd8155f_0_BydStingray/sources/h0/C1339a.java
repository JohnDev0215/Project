package h0;

import android.view.autofill.AutofillManager;
import androidx.compose.ui.platform.AndroidComposeView;

/* JADX INFO: renamed from: h0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1339a implements InterfaceC1340b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AndroidComposeView f15151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f15152b;
    public final AutofillManager c;

    public C1339a(AndroidComposeView androidComposeView, f fVar) {
        this.f15151a = androidComposeView;
        this.f15152b = fVar;
        AutofillManager autofillManager = (AutofillManager) androidComposeView.getContext().getSystemService(AutofillManager.class);
        if (autofillManager == null) {
            throw new IllegalStateException("Autofill service could not be located.");
        }
        this.c = autofillManager;
        androidComposeView.setImportantForAutofill(1);
    }
}
