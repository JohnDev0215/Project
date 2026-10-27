package com.android.inputmethod.dictionarypack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class DownloadOverMeteredDialog extends Activity {
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String LOCALE_KEY = "locale";
    public static final String SIZE_KEY = "size";
    public static final String WORDLIST_TO_DOWNLOAD_KEY = "wordlist_to_download";
    private String mClientId;
    private String mWordListToDownload;

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.mClientId = intent.getStringExtra(CLIENT_ID_KEY);
        this.mWordListToDownload = intent.getStringExtra(WORDLIST_TO_DOWNLOAD_KEY);
        String stringExtra = intent.getStringExtra("locale");
        long intExtra = intent.getIntExtra(SIZE_KEY, 0);
        setContentView(R.layout.download_over_metered);
        setTexts(stringExtra, intExtra);
    }

    private void setTexts(@Nullable final String localeString, final long size) {
        String string = getString(R.string.should_download_over_metered_prompt);
        String string2 = getString(R.string.download_over_metered);
        ((TextView) findViewById(R.id.download_over_metered_prompt)).setText(Html.fromHtml(String.format(string, localeString == null ? "" : LocaleUtils.constructLocaleFromString(localeString).getDisplayLanguage())));
        ((Button) findViewById(R.id.allow_button)).setText(String.format(string2, Float.valueOf(size / 1048576.0f)));
    }

    public void onClickDeny(final View v) {
        UpdateHandler.setDownloadOverMeteredSetting(this, false);
        finish();
    }

    public void onClickAllow(final View v) {
        UpdateHandler.setDownloadOverMeteredSetting(this, true);
        UpdateHandler.installIfNeverRequested(this, this.mClientId, this.mWordListToDownload);
        finish();
    }
}
