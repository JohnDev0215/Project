package defpackage;

import android.view.View;
import com.google.android.material.internal.NavigationMenuItemView;
import java.util.Objects;

/* JADX INFO: renamed from: Fk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0100Fk extends AbstractC0750f3 {
    public final /* synthetic */ NavigationMenuItemView c;

    public C0100Fk(NavigationMenuItemView navigationMenuItemView) {
        this.c = navigationMenuItemView;
    }

    @Override // defpackage.AbstractC0750f3
    public void b(View view, C1525t3 c1525t3) {
        super.b(view, c1525t3);
        Objects.requireNonNull(this.c);
        c1525t3.a.setCheckable(false);
    }
}
