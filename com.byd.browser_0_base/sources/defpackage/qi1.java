package defpackage;

import android.view.View;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.features.tasks.TasksView;

/* JADX INFO: compiled from: MostVisitedSuggestionsUiDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qi1 extends lw2 {
    public final View e;

    public qi1(TasksView tasksView, ri1 ri1Var, Profile profile) {
        super(ri1Var, profile, null);
        this.e = tasksView;
    }

    @Override // defpackage.lw2, defpackage.kw2
    public final boolean isVisible() {
        View view = this.e;
        return view.getVisibility() == 0 && view.findViewById(R$id.mv_tiles_layout).getVisibility() == 0;
    }
}
