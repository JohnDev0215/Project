package p;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import coil.target.ImageViewTarget;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.automotive.socialconnect.SocialConnectController;
import com.spotify.automotive.socialconnect.endpoint.SocialConnectSession;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.sessionstate.SessionState;
import com.spotify.imageloader.coilloaderbase.NoClearImageTarget;
import com.spotify.music.R;
import com.spotify.webapi.service.models.Image;
import com.spotify.webapi.service.models.UserPrivate;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lc2 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ o23 e;

    public /* synthetic */ lc2(o23 o23Var, int i) {
        this.c = i;
        this.e = o23Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        o23 o23Var = this.e;
        switch (i) {
            case 0:
                SocialConnectSession socialConnectSession = (SocialConnectSession) obj;
                o23Var.getClass();
                boolean zIsValid = socialConnectSession.isValid();
                Object obj2 = o23Var.r;
                if (!zIsValid) {
                    Logger.b("Did not receive a response when trying to start a social connect session.", new Object[0]);
                    SettingsActivity settingsActivity = (SettingsActivity) ((mc2) obj2);
                    settingsActivity.w();
                    Integer numValueOf = Integer.valueOf(R.string.social_connect_error_invalid_session);
                    settingsActivity.T.setVisibility(0);
                    settingsActivity.T.setText(numValueOf.intValue());
                } else {
                    ((SettingsActivity) ((mc2) obj2)).x(((SocialConnectController) o23Var.o).getScannableUrl(socialConnectSession));
                }
                break;
            case 1:
                Throwable th = (Throwable) obj;
                o23Var.getClass();
                Logger.c(th, "Unexpected error while trying to start a social connect session.", new Object[0]);
                SettingsActivity settingsActivity2 = (SettingsActivity) ((mc2) o23Var.r);
                settingsActivity2.w();
                String localizedMessage = th.getLocalizedMessage();
                settingsActivity2.T.setVisibility(0);
                settingsActivity2.T.setText(localizedMessage);
                break;
            case 2:
                ((mc2) o23Var.r).finish();
                break;
            case 3:
                UserPrivate userPrivate = (UserPrivate) obj;
                mc2 mc2Var = (mc2) o23Var.r;
                String str = userPrivate.display_name;
                int i2 = it1.a;
                if (str == null) {
                    str = "";
                }
                SettingsActivity settingsActivity3 = (SettingsActivity) mc2Var;
                ((TextView) settingsActivity3.findViewById(R.id.login_username)).setText(str);
                String str2 = userPrivate.product;
                String str3 = str2 != null ? str2 : "";
                if ("open".equalsIgnoreCase(str3)) {
                    str3 = SessionState.PRODUCT_TYPE_FREE;
                }
                TextView textView = (TextView) settingsActivity3.findViewById(R.id.product_type);
                Object[] objArr = new Object[2];
                objArr[0] = settingsActivity3.getString(R.string.app_name);
                Locale locale = Locale.ENGLISH;
                objArr[1] = str3.length() > 1 ? str3.substring(0, 1).toUpperCase(locale) + str3.substring(1) : str3.toUpperCase(locale);
                textView.setText(String.format("%s %s", objArr));
                List<Image> list = userPrivate.images;
                Uri uri = (list == null || list.isEmpty()) ? Uri.EMPTY : Uri.parse(list.get(0).url);
                if (uri != Uri.EMPTY) {
                    fu fuVarA = ((m60) o23Var.q).a(uri);
                    fuVarA.a(new qo());
                    ImageView imageView = (ImageView) settingsActivity3.findViewById(R.id.profile_image);
                    v03.j(imageView, "imageView");
                    yi2 imageViewTarget = fuVarA.d ? new ImageViewTarget(imageView) : new NoClearImageTarget(imageView);
                    o31 o31Var = fuVarA.b;
                    o31Var.d = imageViewTarget;
                    o31Var.M = null;
                    o31Var.N = null;
                    o31Var.O = null;
                    o31Var.r = Boolean.valueOf(fuVarA.e);
                    o31Var.e = new bu(fuVarA.c);
                    ((coil.b) fuVarA.a).b(o31Var.a());
                }
                break;
            default:
                SocialConnectSession socialConnectSession2 = (SocialConnectSession) obj;
                o23Var.getClass();
                boolean zIsValid2 = socialConnectSession2.isValid();
                Object obj3 = o23Var.r;
                if (!zIsValid2) {
                    ((SettingsActivity) ((mc2) obj3)).w();
                } else {
                    ((SettingsActivity) ((mc2) obj3)).x(((SocialConnectController) o23Var.o).getScannableUrl(socialConnectSession2));
                }
                break;
        }
    }
}
