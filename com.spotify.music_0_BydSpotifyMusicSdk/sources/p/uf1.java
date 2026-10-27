package p;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Process;
import android.support.v4.media.session.MediaSessionCompat$Token;

/* JADX INFO: loaded from: classes.dex */
public abstract class uf1 implements ag1 {
    public final Context a;
    public final MediaBrowser b;
    public final Bundle c;
    public final sf1 d = new sf1(this);
    public final nb e = new nb(0);
    public g90 f;
    public Messenger g;
    public MediaSessionCompat$Token h;

    public uf1(Context context, ComponentName componentName, gu0 gu0Var) {
        this.a = context;
        Bundle bundle = new Bundle();
        this.c = bundle;
        bundle.putInt("extra_client_version", 1);
        bundle.putInt("extra_calling_pid", Process.myPid());
        gu0Var.b = this;
        this.b = new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) gu0Var.a, bundle);
    }
}
