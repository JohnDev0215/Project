package l3;

import android.media.session.MediaController;
import android.os.Bundle;

/* JADX INFO: renamed from: l3.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1738C extends h6.q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaController.TransportControls f18512b;

    public AbstractC1738C(MediaController.TransportControls transportControls) {
        this.f18512b = transportControls;
    }

    public final void A(String str, Bundle bundle) {
        if (str != null && ((str.equals("android.support.v4.media.session.action.FOLLOW") || str.equals("android.support.v4.media.session.action.UNFOLLOW")) && (bundle == null || !bundle.containsKey("android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE")))) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.i("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ", str, "."));
        }
        this.f18512b.sendCustomAction(str, bundle);
    }
}
