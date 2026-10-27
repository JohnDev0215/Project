package com.android.car.developeroptions.nfc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class HowItWorks extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.nfc_payment_how_it_works);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ((Button) findViewById(R.id.nfc_how_it_works_button)).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.nfc.HowItWorks.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HowItWorks.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
