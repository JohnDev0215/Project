package com.ts.androidauto.app.proxy.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import com.ts.androidauto.app.R;

/* JADX INFO: loaded from: classes2.dex */
public class FrxDialog extends BaseDialog {
    private static final String TAG = "AAP" + FrxDialog.class.getSimpleName();
    private final Context mContext;
    private Window window;

    public FrxDialog(Context context) {
        super(context);
        this.window = null;
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(512);
        requestWindowFeature(1);
        setContentView(R.layout.frx_dialog_layout);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        initView();
        initWindowView();
    }

    @Override // com.ts.androidauto.app.proxy.dialog.BaseDialog
    public void initView() {
        Button button = (Button) findViewById(R.id.frx_btn);
        button.setOnClickListener(this);
    }

    @Override // com.ts.androidauto.app.proxy.dialog.BaseDialog
    public void initWindowView() {
        Window window = getWindow();
        this.window = window;
        window.getDecorView().setPadding(0, 0, 0, 0);
        this.window.setGravity(17);
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController controller = getWindow().getInsetsController();
            controller.hide(WindowInsets.Type.statusBars());
        }
        this.window.setLayout(-2, -2);
        WindowManager.LayoutParams layoutParams = this.window.getAttributes();
        layoutParams.width = -2;
        layoutParams.height = -2;
        this.window.setAttributes(layoutParams);
        this.window.setType(2008);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        dismiss();
    }
}
