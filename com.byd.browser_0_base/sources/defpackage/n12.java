package defpackage;

import android.net.Uri;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.widget.Checkable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.chromium.components.content_capture.ContentCaptureFrame;
import org.chromium.components.content_capture.PlatformContentCaptureController;

/* JADX INFO: compiled from: PlatformContentCaptureConsumer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n12 implements iy {
    public q12 a;
    public final View b;

    public n12(View view) {
        this.b = view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.iy
    public final void a(jq0 jq0Var, ContentCaptureFrame contentCaptureFrame) {
        q12 q12Var;
        ViewStructure viewStructureNewViewStructure;
        AutofillId autofillId;
        if (this.a == null) {
            View view = this.b;
            ContentCaptureSession contentCaptureSession = view.getContentCaptureSession();
            if (contentCaptureSession == null || (autofillId = (viewStructureNewViewStructure = contentCaptureSession.newViewStructure(view)).getAutofillId()) == null) {
                q12Var = null;
            } else {
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
                q12Var = new q12(contentCaptureSession, autofillId);
            }
            this.a = q12Var;
            if (q12Var == null) {
                return;
            }
        }
        new ly(jq0Var, contentCaptureFrame, this.a).c(i9.f);
    }

    @Override // defpackage.iy
    public final void b(jq0 jq0Var, ContentCaptureFrame contentCaptureFrame) {
        if (this.a == null) {
            return;
        }
        new yy(jq0Var, contentCaptureFrame, this.a).c(i9.f);
    }

    @Override // defpackage.iy
    public final void c(ContentCaptureFrame contentCaptureFrame) {
        if (this.a == null) {
            return;
        }
        jq0 jq0Var = new jq0(1);
        jq0Var.add(contentCaptureFrame);
        new sk0(jq0Var, this.a).c(i9.f);
    }

    @Override // defpackage.iy
    public final void d(ContentCaptureFrame contentCaptureFrame) {
        if (this.a == null) {
            return;
        }
        new bb3(contentCaptureFrame, this.a).c(i9.f);
    }

    @Override // defpackage.iy
    public final void e(jq0 jq0Var) {
        if (jq0Var.isEmpty() || this.a == null) {
            return;
        }
        new cj2(jq0Var, this.a).c(i9.f);
    }

    @Override // defpackage.iy
    public final boolean f(String[] strArr) {
        v72 v72Var = PlatformContentCaptureController.d.b;
        if (v72Var != null) {
            List list = (List) v72Var.b;
            Object obj = v72Var.a;
            if (list == null && ((HashSet) obj) == null) {
                return false;
            }
            for (String str : strArr) {
                String host = Uri.parse(str).getHost();
                if (host != null) {
                    HashSet hashSet = (HashSet) obj;
                    if (hashSet == null || !hashSet.contains(host)) {
                        if (list != null) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if (((Pattern) it.next()).matcher(host).find()) {
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // defpackage.iy
    public final void g(jq0 jq0Var, long[] jArr) {
        if (jq0Var.isEmpty() || this.a == null) {
            return;
        }
        new sy(jq0Var, jArr, this.a).c(i9.f);
    }
}
