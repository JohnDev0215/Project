package io.branch.referral.validators;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.amazonaws.mobile.client.internal.oauth2.OAuth2Client;
import io.branch.referral.Branch;
import io.branch.referral.Defines$Jsonkey;
import io.branch.referral.PrefHelper;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class DeepLinkRoutingValidator {
    private static WeakReference<Activity> current_activity_reference;

    public static void validate(WeakReference<Activity> weakReference) {
        current_activity_reference = weakReference;
        if (TextUtils.isEmpty(getLatestReferringLink()) || weakReference == null) {
            return;
        }
        final JSONObject latestReferringParams = Branch.getInstance().getLatestReferringParams();
        if (latestReferringParams.optInt("_branch_validate") == 60514) {
            if (latestReferringParams.optBoolean(Defines$Jsonkey.Clicked_Branch_Link.getKey())) {
                validateDeeplinkRouting(latestReferringParams);
                return;
            } else {
                displayErrorMessage();
                return;
            }
        }
        if (latestReferringParams.optBoolean("bnc_validate")) {
            new Handler().postDelayed(new Runnable() { // from class: io.branch.referral.validators.DeepLinkRoutingValidator.1
                @Override // java.lang.Runnable
                public void run() {
                    DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(latestReferringParams, ""));
                }
            }, 500L);
        }
    }

    private static void validateDeeplinkRouting(final JSONObject jSONObject) {
        if (current_activity_reference.get() != null) {
            new AlertDialog.Builder(current_activity_reference.get(), R.style.Theme.Material.Dialog.Alert).setTitle("Branch Deeplinking Routing").setMessage("Good news - we got link data. Now a question for you, astute developer: did the app deep link to the specific piece of content you expected to see?").setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: io.branch.referral.validators.DeepLinkRoutingValidator.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(jSONObject, "g"));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: io.branch.referral.validators.DeepLinkRoutingValidator.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeepLinkRoutingValidator.launchTestTemplate(DeepLinkRoutingValidator.getUpdatedLinkWithTestStat(jSONObject, "r"));
                }
            }).setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: io.branch.referral.validators.DeepLinkRoutingValidator.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setCancelable(false).setIcon(R.drawable.sym_def_app_icon).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void launchTestTemplate(String str) {
        if (current_activity_reference.get() != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str).buildUpon().appendQueryParameter("$uri_redirect_mode", "2").build());
            intent.addFlags(268435456);
            intent.setPackage(OAuth2Client.CUSTOM_TABS_PACKAGE_NAME);
            current_activity_reference.get().getPackageManager().queryIntentActivities(intent, 0);
            try {
                current_activity_reference.get().startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                intent.setPackage(null);
                current_activity_reference.get().startActivity(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getUpdatedLinkWithTestStat(JSONObject jSONObject, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = "";
        try {
            str7 = jSONObject.getString("~" + Defines$Jsonkey.ReferringLink.getKey()).split("\\?")[0];
        } catch (Exception unused) {
            PrefHelper.Debug("Failed to get referring link");
        }
        String string = str7 + "?validate=true";
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                if (jSONObject.getString("ct").equals("t1")) {
                    str2 = "&t1=" + str;
                } else {
                    str2 = "&t1=" + jSONObject.getString("t1");
                }
                sb.append(str2);
                String string2 = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string2);
                if (jSONObject.getString("ct").equals("t2")) {
                    str3 = "&t2=" + str;
                } else {
                    str3 = "&t2=" + jSONObject.getString("t2");
                }
                sb2.append(str3);
                String string3 = sb2.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(string3);
                if (jSONObject.getString("ct").equals("t3")) {
                    str4 = "&t3=" + str;
                } else {
                    str4 = "&t3=" + jSONObject.getString("t3");
                }
                sb3.append(str4);
                String string4 = sb3.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(string4);
                if (jSONObject.getString("ct").equals("t4")) {
                    str5 = "&t4=" + str;
                } else {
                    str5 = "&t4=" + jSONObject.getString("t4");
                }
                sb4.append(str5);
                String string5 = sb4.toString();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(string5);
                if (jSONObject.getString("ct").equals("t5")) {
                    str6 = "&t5=" + str;
                } else {
                    str6 = "&t5=" + jSONObject.getString("t5");
                }
                sb5.append(str6);
                string = sb5.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return string + "&os=android";
    }

    private static void displayErrorMessage() {
        if (current_activity_reference.get() != null) {
            new AlertDialog.Builder(current_activity_reference.get(), R.style.Theme.Material.Dialog.Alert).setTitle("Branch Deeplink Routing Support").setMessage("Bummer. It seems like +clicked_branch_link is false - we didn't deep link.  Double check that the link you're clicking has the same branch_key that is being used in your Manifest file. Return to Chrome when you're ready to test again.").setNeutralButton("Got it", new DialogInterface.OnClickListener() { // from class: io.branch.referral.validators.DeepLinkRoutingValidator.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setCancelable(false).setIcon(R.drawable.sym_def_app_icon).show();
        }
    }

    private static String getLatestReferringLink() {
        if (Branch.getInstance() == null || Branch.getInstance().getLatestReferringParams() == null) {
            return "";
        }
        return Branch.getInstance().getLatestReferringParams().optString("~" + Defines$Jsonkey.ReferringLink.getKey());
    }
}
