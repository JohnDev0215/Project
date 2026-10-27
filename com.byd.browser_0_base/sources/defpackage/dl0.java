package defpackage;

import J.N;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import defpackage.g50;
import java.util.HashMap;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.feed.feedmanagement.FeedManagementActivity;
import org.chromium.chrome.browser.app.feed.followmanagement.FollowManagementActivity;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: FeedManagementMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class dl0 {
    public final Context a;
    public final b b;
    public final a c;
    public final int d;

    /* JADX INFO: compiled from: FeedManagementMediator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: FeedManagementMediator.java */
    public interface b {
    }

    public dl0(Context context, ya1 ya1Var, b bVar, a aVar, int i) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = i;
        final int i2 = 0;
        ya1Var.t(new xa1(0, a(R$string.feed_manage_activity, R$string.feed_manage_activity_description, new View.OnClickListener(this) { // from class: cl0
            public final /* synthetic */ dl0 f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                dl0 dl0Var = this.f;
                switch (i3) {
                    case 0:
                        N.MxULk9PS(dl0Var.d, 16);
                        dl0Var.b("https://myactivity.google.com/myactivity?product=50");
                        break;
                    default:
                        N.MxULk9PS(dl0Var.d, 30);
                        Context context2 = dl0Var.a;
                        ((FeedManagementActivity) dl0Var.b).getClass();
                        try {
                            context2.startActivity(new Intent(context2, (Class<?>) FollowManagementActivity.class));
                        } catch (Exception e) {
                            e.toString();
                            return;
                        }
                        break;
                }
            }
        })));
        ya1Var.t(new xa1(0, a(R$string.feed_manage_interests, R$string.feed_manage_interests_description, new ie0(7, this))));
        ya1Var.t(new xa1(0, a(R$string.feed_manage_hidden, R$string.feed_manage_hidden_description, new by1(9, this))));
        if (N.MqhKj56k()) {
            ya1Var.t(new xa1(0, a(R$string.feed_manage_autoplay, R$string.feed_manage_autoplay_description, new vt(9, this))));
        }
        final int i3 = 1;
        ya1Var.t(new xa1(0, a(R$string.feed_manage_following, R$string.feed_manage_following_description, new View.OnClickListener(this) { // from class: cl0
            public final /* synthetic */ dl0 f;

            {
                this.f = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i32 = i3;
                dl0 dl0Var = this.f;
                switch (i32) {
                    case 0:
                        N.MxULk9PS(dl0Var.d, 16);
                        dl0Var.b("https://myactivity.google.com/myactivity?product=50");
                        break;
                    default:
                        N.MxULk9PS(dl0Var.d, 30);
                        Context context2 = dl0Var.a;
                        ((FeedManagementActivity) dl0Var.b).getClass();
                        try {
                            context2.startActivity(new Intent(context2, (Class<?>) FollowManagementActivity.class));
                        } catch (Exception e) {
                            e.toString();
                            return;
                        }
                        break;
                }
            }
        })));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
    public final PropertyModel a(int i, int i2, View.OnClickListener onClickListener) {
        Context context = this.a;
        ?? string = context.getResources().getString(i);
        ?? string2 = context.getResources().getString(i2);
        HashMap mapE = PropertyModel.e(bl0.d);
        PropertyModel.r<String> rVar = bl0.a;
        int i3 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(rVar, gVar);
        PropertyModel.r<String> rVar2 = bl0.b;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = string2;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<View.OnClickListener> rVar3 = bl0.c;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = onClickListener;
        mapE.put(rVar3, gVar3);
        return new PropertyModel(mapE);
    }

    public final void b(String str) {
        g50.a aVar = new g50.a();
        aVar.d();
        aVar.c();
        g50 g50VarA = aVar.a();
        Uri uri = Uri.parse(str);
        Intent intent = g50VarA.a;
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        Context context = this.a;
        intent.setClassName(context, "org.chromium.chrome.browser.customtabs.CustomTabActivity");
        intent.addFlags(268435456);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.setPackage(context.getPackageName());
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName(context.getPackageName(), "FakeClass"));
        intent.putExtra("trusted_application_code_extra", PendingIntent.getActivity(context, 0, intent2, 67108864));
        context.startActivity(intent);
    }
}
