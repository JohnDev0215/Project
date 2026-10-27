package p;

import android.content.Context;
import android.content.Intent;
import com.spotify.automotive.baseui.login.AAMFullscreenDialogActivity;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements k0 {
    public final Context a;

    public l0(Context context) {
        this.a = context;
    }

    public final void a(int i, int i2) {
        Context context = this.a;
        String string = context.getString(i);
        String string2 = context.getString(i2);
        int i3 = AAMFullscreenDialogActivity.H;
        Intent intent = new Intent(context, (Class<?>) AAMFullscreenDialogActivity.class);
        intent.putExtra("title", string);
        intent.putExtra("content", string2);
        context.startActivity(intent);
    }
}
