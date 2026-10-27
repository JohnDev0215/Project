package defpackage;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ShareSheetPropertyModelBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cl2 {
    public final ji a;
    public final PackageManager b;
    public final Profile c;

    static {
        new ArrayList(Arrays.asList("com.whatsapp.ContactPicker", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", "com.google.android.gm.ComposeActivityGmailExternal", "com.instagram.share.handleractivity.StoryShareHandlerActivity", "com.facebook.messenger.intents.ShareIntentHandler", "com.google.android.apps.messaging.ui.conversationlist.ShareIntentActivity", "com.twitter.composer.ComposerActivity", "com.snap.mushroom.MainActivity", "com.pinterest.activity.create.PinItActivity", "com.linkedin.android.publishing.sharing.LinkedInDeepLinkActivity", "jp.naver.line.android.activity.selectchat.SelectChatActivityLaunchActivity", "com.facebook.lite.composer.activities.ShareIntentMultiPhotoAlphabeticalAlias", "com.facebook.mlite.share.view.ShareActivity", "com.samsung.android.email.ui.messageview.MessageFileView", "com.yahoo.mail.ui.activities.ComposeActivity", "org.telegram.ui.LaunchActivity", "com.tencent.mm.ui.tools.ShareImgUI"));
    }

    public cl2(ji jiVar, PackageManager packageManager, Profile profile) {
        this.a = jiVar;
        this.b = packageManager;
        this.c = profile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PropertyModel a(Drawable drawable, String str, String str2, View.OnClickListener onClickListener, boolean z) {
        HashMap mapE = PropertyModel.e(yk2.f);
        PropertyModel.r<Drawable> rVar = yk2.a;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = drawable;
        mapE.put(rVar, gVar);
        PropertyModel.r<String> rVar2 = yk2.b;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = str;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<View.OnClickListener> rVar3 = yk2.d;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = onClickListener;
        mapE.put(rVar3, gVar3);
        PropertyModel.n nVar = yk2.e;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(nVar, aVar);
        if (str2 != 0) {
            PropertyModel.r<String> rVar4 = yk2.c;
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = str2;
            mapE.put(rVar4, gVar4);
        }
        return new PropertyModel(mapE);
    }
}
