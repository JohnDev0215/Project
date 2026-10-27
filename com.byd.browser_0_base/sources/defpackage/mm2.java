package defpackage;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import org.chromium.chrome.browser.ui.signin.fre.SigninFirstRunView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SigninFirstRunViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mm2 {
    public static void a(SigninFirstRunView signinFirstRunView, PropertyModel propertyModel) {
        boolean zK = propertyModel.k(lm2.h);
        signinFirstRunView.getmMessage().setVisibility(0);
        signinFirstRunView.getContinueButtonView().setVisibility(zK ? 8 : 0);
    }

    public static void b(SigninFirstRunView signinFirstRunView, PropertyModel propertyModel) {
        if (propertyModel.k(lm2.f) || propertyModel.k(lm2.g)) {
            TransitionManager.beginDelayedTransition(signinFirstRunView, new AutoTransition().setStartDelay(0L).setDuration(50L));
        }
        signinFirstRunView.getContinueButtonView().setVisibility(0);
        signinFirstRunView.getSigninProgressSpinner().setVisibility(8);
        signinFirstRunView.getSigninProgressText().setVisibility(8);
    }
}
