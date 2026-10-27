package com.android.systemui.shared.system;

import android.app.Activity;
import android.view.View;
import android.view.ViewHierarchyEncoder;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ActivityCompat {
    private final Activity mWrapped;

    public ActivityCompat(Activity activity) {
        this.mWrapped = activity;
    }

    public void registerRemoteAnimations(RemoteAnimationDefinitionCompat definition) {
        this.mWrapped.registerRemoteAnimations(definition.getWrapped());
    }

    public void unregisterRemoteAnimations() {
        this.mWrapped.unregisterRemoteAnimations();
    }

    public boolean encodeViewHierarchy(ByteArrayOutputStream out) {
        View view = (this.mWrapped.getWindow() == null || this.mWrapped.getWindow().peekDecorView() == null || this.mWrapped.getWindow().peekDecorView().getViewRootImpl() == null) ? null : this.mWrapped.getWindow().peekDecorView().getViewRootImpl().getView();
        if (view == null) {
            return false;
        }
        ViewHierarchyEncoder viewHierarchyEncoder = new ViewHierarchyEncoder(out);
        int[] locationOnScreen = view.getLocationOnScreen();
        viewHierarchyEncoder.addProperty("window:left", locationOnScreen[0]);
        viewHierarchyEncoder.addProperty("window:top", locationOnScreen[1]);
        view.encode(viewHierarchyEncoder);
        viewHierarchyEncoder.endStream();
        return true;
    }

    public int getDisplayId() {
        return this.mWrapped.getDisplayId();
    }
}
