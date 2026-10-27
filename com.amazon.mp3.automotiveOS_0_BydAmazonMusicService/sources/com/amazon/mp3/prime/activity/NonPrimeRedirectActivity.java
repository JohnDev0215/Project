package com.amazon.mp3.prime.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.prime.Branding;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class NonPrimeRedirectActivity extends Activity {
    private static final String INTENT_ACTION_NON_PRIME_CUSTOMER;
    private static final String INTENT_ACTION_NON_PRIME_MARKET;
    private static final String INTENT_NAMESPACE;
    private static final String TAG = "NonPrimeRedirectActivity";

    static {
        String str = NonPrimeRedirectActivity.class.getName() + '.';
        INTENT_NAMESPACE = str;
        INTENT_ACTION_NON_PRIME_CUSTOMER = str + "ACTION_NON_PRIME_CUSTOMER";
        INTENT_ACTION_NON_PRIME_MARKET = str + "ACTION_NON_PRIME_MARKET";
    }

    public static Intent getNonPrimeCustomerIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) NonPrimeRedirectActivity.class);
        intent.setAction(INTENT_ACTION_NON_PRIME_CUSTOMER);
        return intent;
    }

    public static Intent getNonPrimeMarketIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) NonPrimeRedirectActivity.class);
        intent.setAction(INTENT_ACTION_NON_PRIME_MARKET);
        return intent;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getAction();
        if (action == null) {
            Log.warning(TAG, "No action specified");
            LauncherActivity.start(this);
            finish();
        } else if (INTENT_ACTION_NON_PRIME_MARKET.equals(action)) {
            showNonPrimeMarketDialog();
        } else {
            if (INTENT_ACTION_NON_PRIME_CUSTOMER.equals(action)) {
                showNonPrimeCustomerDialog();
                return;
            }
            Log.warning(TAG, "Invalid action specified: %s", action);
            LauncherActivity.start(this);
            finish();
        }
    }

    private void showNonPrimeMarketDialog() {
        new OneButtonErrorDialog(this, getString(R.string.dmusic_dialog_non_prime_market_title), getString(R.string.dmusic_dialog_non_prime_market_message, EndpointsProvider.get().getDomain()), new ErrorDialogButton(this, R.string.dmusic_dialog_non_prime_market_button, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.prime.activity.NonPrimeRedirectActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                this.startActivity(HTMLStoreActivityFactory.getStartIntent(this));
                NonPrimeRedirectActivity.this.finish();
            }
        })).show();
    }

    private void showNonPrimeCustomerDialog() {
        new OneButtonErrorDialog(this, getString(R.string.dmusic_dialog_non_prime_customer_title, Branding.getPrimeBranding(this)), getString(R.string.dmusic_dialog_non_prime_customer_message, Branding.getPrimeBranding(this)), new ErrorDialogButton(getString(R.string.dmusic_dialog_non_prime_customer_button, Branding.getPrimeMusicBranding(this)), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.prime.activity.NonPrimeRedirectActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                this.startActivity(PrimeActivityFactory.getPrimeSectionIntent(this));
                NonPrimeRedirectActivity.this.finish();
            }
        })).show();
    }
}
