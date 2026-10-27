package defpackage;

import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.widget.Checkable;
import java.util.HashMap;

/* JADX INFO: renamed from: fx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0799fx {
    public C0743ex a;
    public HashMap b;

    public C0799fx(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
        this.a = new C0743ex(contentCaptureSession, autofillId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static C0799fx a(View view) {
        ViewStructure viewStructureNewViewStructure;
        AutofillId autofillId;
        ContentCaptureSession contentCaptureSession = view.getContentCaptureSession();
        if (contentCaptureSession == null || (autofillId = (viewStructureNewViewStructure = contentCaptureSession.newViewStructure(view)).getAutofillId()) == null) {
            return null;
        }
        viewStructureNewViewStructure.setDimens(view.getLeft(), view.getTop(), 0, 0, view.getRight() - view.getLeft(), view.getBottom() - view.getTop());
        viewStructureNewViewStructure.setVisibility(view.getVisibility());
        viewStructureNewViewStructure.setEnabled(view.isEnabled());
        viewStructureNewViewStructure.setClickable(view.isClickable());
        viewStructureNewViewStructure.setFocusable(view.isFocusable());
        viewStructureNewViewStructure.setFocused(view.isFocused());
        viewStructureNewViewStructure.setAccessibilityFocused(view.isAccessibilityFocused());
        viewStructureNewViewStructure.setSelected(view.isSelected());
        viewStructureNewViewStructure.setActivated(view.isActivated());
        viewStructureNewViewStructure.setLongClickable(view.isLongClickable());
        if (view instanceof Checkable) {
            viewStructureNewViewStructure.setCheckable(true);
            if (((Checkable) view).isChecked()) {
                viewStructureNewViewStructure.setChecked(true);
            }
        }
        if (view.isOpaque()) {
            viewStructureNewViewStructure.setOpaque(true);
        }
        if (view.isContextClickable()) {
            viewStructureNewViewStructure.setContextClickable(true);
        }
        CharSequence accessibilityClassName = view.getAccessibilityClassName();
        if (accessibilityClassName != null) {
            viewStructureNewViewStructure.setClassName(accessibilityClassName.toString());
        }
        viewStructureNewViewStructure.setContentDescription(view.getContentDescription());
        return new C0799fx(contentCaptureSession, autofillId);
    }

    public HashMap b() {
        if (this.b == null) {
            this.b = new HashMap();
        }
        return this.b;
    }

    public C0743ex c() {
        return this.a;
    }
}
