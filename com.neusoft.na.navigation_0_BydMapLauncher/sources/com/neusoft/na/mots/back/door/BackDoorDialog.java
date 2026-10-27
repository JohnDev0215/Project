package com.neusoft.na.mots.back.door;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.neusoft.na.common.Tool;
import com.neusoft.na.navigation.R;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BackDoorDialog extends Dialog {
    private final String CONFIG_PATH;
    Button close;
    Context context;
    Button cp;
    private File iniDirPath;
    Button rm;
    String str;
    TextView textView;

    public BackDoorDialog(Context context) {
        super(context);
        this.str = "";
        this.CONFIG_PATH = "/naviexternconfig";
        this.context = context;
        init();
    }

    public BackDoorDialog(Context context, int i) {
        super(context, i);
        this.str = "";
        this.CONFIG_PATH = "/naviexternconfig";
        this.context = context;
        init();
    }

    protected BackDoorDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.str = "";
        this.CONFIG_PATH = "/naviexternconfig";
        this.context = context;
        init();
    }

    private void init() {
        requestWindowFeature(1);
        this.iniDirPath = Environment.getExternalStorageDirectory();
        setContentView(R.layout.dialog_back_door);
        Button button = (Button) findViewById(R.id.close);
        this.close = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.mots.back.door.BackDoorDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackDoorDialog.this.dismiss();
            }
        });
        Button button2 = (Button) findViewById(R.id.cp);
        this.cp = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.mots.back.door.BackDoorDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws Throwable {
                BackDoorDialog.this.str = "";
                BackDoorDialog.this.setLog("Start to find the USB stick");
                List<String> uSBPaths = Tool.getUSBPaths(BackDoorDialog.this.context);
                if (uSBPaths != null && uSBPaths.size() > 0) {
                    for (int i = 0; i < uSBPaths.size(); i++) {
                        String str = ((Object) uSBPaths.get(i)) + "/naviexternconfig";
                        BackDoorDialog.this.setLog("Determine whether" + str + "exists");
                        if (Tool.fileIsExists(str)) {
                            BackDoorDialog.this.setLog("Start copying");
                            Tool.copyFolder(str, BackDoorDialog.this.iniDirPath.getPath() + "/naviexternconfig");
                            BackDoorDialog.this.setLog("copy complete");
                            return;
                        }
                        BackDoorDialog.this.setLog("Non-existent");
                    }
                    return;
                }
                BackDoorDialog.this.setLog("No USB stick");
            }
        });
        Button button3 = (Button) findViewById(R.id.rm);
        this.rm = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.mots.back.door.BackDoorDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BackDoorDialog.this.str = "";
                BackDoorDialog.this.setLog("Start deletion");
                if (Tool.delete(BackDoorDialog.this.iniDirPath.getPath() + "/naviexternconfig")) {
                    BackDoorDialog.this.setLog("Delete successful");
                } else {
                    BackDoorDialog.this.setLog("Delete failed");
                }
            }
        });
        this.textView = (TextView) findViewById(R.id.text_log);
    }

    public void setLog(String str) {
        String str2 = this.str + str + "\n";
        this.str = str2;
        this.textView.setText(str2);
    }
}
