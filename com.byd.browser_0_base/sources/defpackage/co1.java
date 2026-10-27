package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import org.chromium.chrome.browser.content_creation.notes.NoteCreationDialog;

/* JADX INFO: compiled from: NoteCreationDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class co1 extends View.AccessibilityDelegate {
    public final /* synthetic */ NoteCreationDialog a;

    public co1(NoteCreationDialog noteCreationDialog) {
        this.a = noteCreationDialog;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        NoteCreationDialog noteCreationDialog = this.a;
        if (eventType == 1) {
            noteCreationDialog.x0.run();
        } else if (eventType == 32768) {
            view.setClickable(true);
            noteCreationDialog.t0 = ((Integer) view.getTag()).intValue();
            noteCreationDialog.R0();
        }
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }
}
