package com.android.permissioncontroller.incident;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IncidentManager;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.incident.ReportDetails;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class ConfirmationActivity extends Activity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    private static final String TAG = "ConfirmationActivity";
    private static ConfirmationActivity sCurrentActivity;
    private static Uri sCurrentUri;

    public static void finishCurrent() {
        ConfirmationActivity confirmationActivity = sCurrentActivity;
        if (confirmationActivity != null) {
            confirmationActivity.finish();
        }
    }

    public static Uri getCurrentUri() {
        return sCurrentUri;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Formatting formatting = new Formatting(this);
        Uri uri = getIntent().getData();
        Log.d(TAG, "uri=" + uri);
        if (uri == null) {
            Log.w(TAG, "No uri in intent: " + getIntent());
            finish();
            return;
        }
        IncidentManager.PendingReport pending = new IncidentManager.PendingReport(uri);
        String appLabel = formatting.getAppLabel(pending.getRequestingPackage());
        Resources res = getResources();
        try {
            ReportDetails details = ReportDetails.parseIncidentReport(this, uri);
            View content = getLayoutInflater().inflate(R.layout.incident_confirmation, (ViewGroup) null);
            ArrayList<String> reasons = details.getReasons();
            int reasonsSize = reasons.size();
            if (reasonsSize > 0) {
                content.findViewById(R.id.reasonIntro).setVisibility(0);
                TextView reasonTextView = (TextView) content.findViewById(R.id.reasons);
                reasonTextView.setVisibility(0);
                int bulletSize = (int) (res.getDimension(R.dimen.incident_reason_bullet_size) + 0.5f);
                int bulletIndent = (int) (res.getDimension(R.dimen.incident_reason_bullet_indent) + 0.5f);
                int bulletColor = getColor(R.color.incident_reason_bullet_color);
                StringBuilder text = new StringBuilder();
                int i = 0;
                while (i < reasonsSize) {
                    Uri uri2 = uri;
                    StringBuilder text2 = text;
                    text2.append(reasons.get(i));
                    if (i != reasonsSize - 1) {
                        text2.append("\n");
                    }
                    i++;
                    text = text2;
                    uri = uri2;
                }
                SpannableString spannable = new SpannableString(text.toString());
                int spanStart = 0;
                int i2 = 0;
                while (i2 < reasonsSize) {
                    int length = reasons.get(i2).length();
                    ArrayList<String> reasons2 = reasons;
                    BulletSpan bulletSpan = new BulletSpan(bulletIndent, bulletColor, bulletSize);
                    int bulletIndent2 = bulletIndent;
                    int bulletIndent3 = spanStart + length;
                    spannable.setSpan(bulletSpan, spanStart, bulletIndent3, 33);
                    spanStart += length + 1;
                    i2++;
                    reasons = reasons2;
                    bulletIndent = bulletIndent2;
                    reasonsSize = reasonsSize;
                }
                reasonTextView.setText(spannable);
            }
            String message = getString(R.string.incident_report_dialog_text, new Object[]{appLabel, formatting.getDate(pending.getTimestamp()), formatting.getTime(pending.getTimestamp()), appLabel});
            ((TextView) content.findViewById(R.id.message)).setText(message);
            ArrayList<Drawable> images = details.getImages();
            int imagesSize = images.size();
            if (imagesSize > 0) {
                content.findViewById(R.id.imageScrollView).setVisibility(0);
                LinearLayout imageList = (LinearLayout) content.findViewById(R.id.imageList);
                int width = res.getDimensionPixelSize(R.dimen.incident_image_width);
                int height = res.getDimensionPixelSize(R.dimen.incident_image_height);
                int i3 = 0;
                while (i3 < imagesSize) {
                    images.get(i3);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageDrawable(images.get(i3));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageList.addView(imageView, new LinearLayout.LayoutParams(width, height));
                    i3++;
                    details = details;
                }
            }
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.incident_report_dialog_title).setPositiveButton(R.string.incident_report_dialog_allow_label, this).setNegativeButton(R.string.incident_report_dialog_deny_label, this).setOnDismissListener(this).setView(content).create();
            if (Settings.canDrawOverlays(this)) {
                Window w = dialog.getWindow();
                w.setType(2038);
            }
            dialog.show();
        } catch (ReportDetails.ParseException e) {
            Log.w("Rejecting report because it couldn't be parsed", e);
            IncidentManager incidentManager = (IncidentManager) getSystemService(IncidentManager.class);
            incidentManager.denyReport(getIntent().getData());
            new AlertDialog.Builder(this).setTitle(R.string.incident_report_dialog_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.incident.ConfirmationActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog2, int which) {
                    ConfirmationActivity.this.finish();
                }
            }).setMessage(getString(R.string.incident_report_error_dialog_text, new Object[]{appLabel})).setOnDismissListener(this).show();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        sCurrentActivity = this;
        sCurrentUri = getIntent().getData();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        sCurrentActivity = null;
        sCurrentUri = null;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        finish();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        IncidentManager incidentManager = (IncidentManager) getSystemService(IncidentManager.class);
        if (which == -2) {
            incidentManager.denyReport(getIntent().getData());
            PendingList.getInstance().updateState(this, 0);
        } else if (which == -1) {
            incidentManager.approveReport(getIntent().getData());
            PendingList.getInstance().updateState(this, 0);
        }
        finish();
    }
}
