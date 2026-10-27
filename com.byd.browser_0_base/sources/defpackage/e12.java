package defpackage;

import android.transition.Transition;
import java.util.HashSet;
import org.chromium.components.browser_ui.photo_picker.PickerCategoryView;

/* JADX INFO: compiled from: PickerCategoryView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e12 implements Transition.TransitionListener {
    public final /* synthetic */ HashSet e;
    public final /* synthetic */ PickerCategoryView f;

    public e12(PickerCategoryView pickerCategoryView, HashSet hashSet) {
        this.f = pickerCategoryView;
        this.e = hashSet;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        PickerCategoryView pickerCategoryView = this.f;
        pickerCategoryView.x = false;
        pickerCategoryView.p.h(this.e);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
    }
}
