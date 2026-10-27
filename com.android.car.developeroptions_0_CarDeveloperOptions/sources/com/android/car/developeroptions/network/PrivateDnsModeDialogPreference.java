package com.android.car.developeroptions.network;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkUtils;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.utils.AnnotationSpan;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PrivateDnsModeDialogPreference extends CustomDialogPreferenceCompat implements DialogInterface.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {
    static final String HOSTNAME_KEY = "private_dns_specifier";
    static final String MODE_KEY = "private_dns_mode";
    private static final Map<String, Integer> PRIVATE_DNS_MAP;
    EditText mEditText;
    String mMode;
    RadioGroup mRadioGroup;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    static {
        HashMap map = new HashMap();
        PRIVATE_DNS_MAP = map;
        map.put("off", Integer.valueOf(R.id.private_dns_mode_off));
        PRIVATE_DNS_MAP.put("opportunistic", Integer.valueOf(R.id.private_dns_mode_opportunistic));
        PRIVATE_DNS_MAP.put("hostname", Integer.valueOf(R.id.private_dns_mode_provider));
    }

    public static String getModeFromSettings(ContentResolver contentResolver) {
        String string = Settings.Global.getString(contentResolver, MODE_KEY);
        if (!PRIVATE_DNS_MAP.containsKey(string)) {
            string = Settings.Global.getString(contentResolver, "private_dns_default_mode");
        }
        return PRIVATE_DNS_MAP.containsKey(string) ? string : "opportunistic";
    }

    public static String getHostnameFromSettings(ContentResolver contentResolver) {
        return Settings.Global.getString(contentResolver, HOSTNAME_KEY);
    }

    public PrivateDnsModeDialogPreference(Context context) {
        super(context);
        $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU __lambda_privatednsmodedialogpreference_wfm5pxkn2z5hcebowg_1y2hjqku = $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU.INSTANCE;
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU __lambda_privatednsmodedialogpreference_wfm5pxkn2z5hcebowg_1y2hjqku = $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU.INSTANCE;
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU __lambda_privatednsmodedialogpreference_wfm5pxkn2z5hcebowg_1y2hjqku = $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU.INSTANCE;
        initialize();
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU __lambda_privatednsmodedialogpreference_wfm5pxkn2z5hcebowg_1y2hjqku = $$Lambda$PrivateDnsModeDialogPreference$wfM5PXkn2Z5hcEboWG_1Y2hJQkU.INSTANCE;
        initialize();
    }

    static /* synthetic */ void lambda$new$0(View view) {
        Context context = view.getContext();
        Intent helpIntent = HelpUtils.getHelpIntent(context, context.getString(R.string.help_uri_private_dns), context.getClass().getName());
        if (helpIntent != null) {
            try {
                view.startActivityForResult(helpIntent, 0);
            } catch (ActivityNotFoundException unused) {
                Log.w("PrivateDnsModeDialog", "Activity was not found for intent, " + helpIntent.toString());
            }
        }
    }

    private void initialize() {
        setWidgetLayoutResource(R.layout.restricted_icon);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (isDisabledByAdmin()) {
            preferenceViewHolder.itemView.setEnabled(true);
        }
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.restricted_icon);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(isDisabledByAdmin() ? 0 : 8);
        }
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    protected void onBindDialogView(View view) {
        Context context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        this.mMode = getModeFromSettings(context.getContentResolver());
        EditText editText = (EditText) view.findViewById(R.id.private_dns_mode_provider_hostname);
        this.mEditText = editText;
        editText.addTextChangedListener(this);
        this.mEditText.setText(getHostnameFromSettings(contentResolver));
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.private_dns_radio_group);
        this.mRadioGroup = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.mRadioGroup.check(PRIVATE_DNS_MAP.getOrDefault(this.mMode, Integer.valueOf(R.id.private_dns_mode_opportunistic)).intValue());
        TextView textView = (TextView) view.findViewById(R.id.private_dns_help_info);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo(context, "url", HelpUtils.getHelpIntent(context, context.getString(R.string.help_uri_private_dns), context.getClass().getName()));
        if (linkInfo.isActionable()) {
            textView.setText(AnnotationSpan.linkify(context.getText(R.string.private_dns_help_message), linkInfo));
        }
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            Context context = getContext();
            if (this.mMode.equals("hostname")) {
                Settings.Global.putString(context.getContentResolver(), HOSTNAME_KEY, this.mEditText.getText().toString());
            }
            FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 1249, this.mMode);
            Settings.Global.putString(context.getContentResolver(), MODE_KEY, this.mMode);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.private_dns_mode_off /* 2131362614 */:
                this.mMode = "off";
                break;
            case R.id.private_dns_mode_opportunistic /* 2131362615 */:
                this.mMode = "opportunistic";
                break;
            case R.id.private_dns_mode_provider /* 2131362616 */:
                this.mMode = "hostname";
                break;
        }
        updateDialogInfo();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        updateDialogInfo();
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = getEnforcedAdmin();
        if (enforcedAdmin == null) {
            super.performClick();
        } else {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), enforcedAdmin);
        }
    }

    private RestrictedLockUtils.EnforcedAdmin getEnforcedAdmin() {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "disallow_config_private_dns", UserHandle.myUserId());
    }

    private boolean isDisabledByAdmin() {
        return getEnforcedAdmin() != null;
    }

    private Button getSaveButton() {
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog == null) {
            return null;
        }
        return alertDialog.getButton(-1);
    }

    private void updateDialogInfo() {
        boolean zEquals = "hostname".equals(this.mMode);
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.setEnabled(zEquals);
        }
        Button saveButton = getSaveButton();
        if (saveButton != null) {
            saveButton.setEnabled(zEquals ? NetworkUtils.isWeaklyValidatedHostname(this.mEditText.getText().toString()) : true);
        }
    }
}
