package spi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import com.android.car.media.main.R$id;
import com.android.car.media.main.R$layout;
import com.android.car.media.main.R$string;
import com.android.car.media.main.R$style;
import java.util.Objects;

/* JADX INFO: compiled from: TestDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ua extends Dialog {
    public static final /* synthetic */ int a = 0;

    public ua(@NonNull Context context) {
        super(context, R$style.CountDownDialogStyle);
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.layout_test_setting);
        final int i = 0;
        setCanceledOnTouchOutside(false);
        CheckBox checkBox = (CheckBox) findViewById(R$id.log_all);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: spi.ta
            public final /* synthetic */ ua b;

            {
                this.b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i) {
                    case 0:
                        Objects.requireNonNull(this.b);
                        compoundButton.setText(z ? R$string.test_log_open : R$string.test_log_close);
                        break;
                    default:
                        Objects.requireNonNull(this.b);
                        compoundButton.setText(z ? R$string.test_log_gear_box_p : R$string.test_log_gear_box_un_p);
                        break;
                }
            }
        });
        checkBox.setChecked(false);
        checkBox.setText(R$string.test_log_close);
        CheckBox checkBox2 = (CheckBox) findViewById(R$id.gear_box);
        final int i2 = 1;
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: spi.ta
            public final /* synthetic */ ua b;

            {
                this.b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i2) {
                    case 0:
                        Objects.requireNonNull(this.b);
                        compoundButton.setText(z ? R$string.test_log_open : R$string.test_log_close);
                        break;
                    default:
                        Objects.requireNonNull(this.b);
                        compoundButton.setText(z ? R$string.test_log_gear_box_p : R$string.test_log_gear_box_un_p);
                        break;
                }
            }
        });
        checkBox2.setText(R$string.test_log_gear_box_un_p);
        checkBox2.setChecked(false);
        findViewById(R$id.test_setting_close).setOnClickListener(new k8(this, 2));
    }

    @Override // android.app.Dialog
    public final void show() {
        if (isShowing()) {
            dismiss();
        }
        super.show();
    }
}
