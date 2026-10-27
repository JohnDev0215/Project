package com.byd.data.collect;

import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.p;
import com.byd.data.common.d.a;
import com.byd.data.common.f.d;
import com.byd.data.common.present.MainTestPresent;
import java.io.File;
import java.net.Socket;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements com.byd.data.common.server.e.c, com.byd.data.common.c.d.d {
    private final String TAG = "MainActivity";
    private androidx.navigation.v.a appBarConfiguration;
    private com.byd.data.collect.i.a binding;
    private volatile int clickNum;
    private com.byd.data.common.server.e.a fileOperator;
    private Handler handle;
    private MainTestPresent present;
    private int sendCount;
    private com.byd.data.collect.server.k.a socketDataPickService;

    private byte[] M() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2100);
        for (int i2 = 0; i2 < 100; i2++) {
            byteBufferAllocate.put((byte) 1).put((byte) (this.sendCount + 1)).put((byte) 3).put((byte) 2).putChar('d').putInt(1000).putChar((char) 144).putChar('\n').put((byte) 20).put((byte) 1).put((byte) 2).putChar(',').put((byte) 25).put((byte) ((this.sendCount + 101) & 255));
        }
        return byteBufferAllocate.array();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean N(Message message) {
        if (message.what != 9 || this.fileOperator == null) {
            return false;
        }
        if (checkPermission("android.permission.READ_EXTERNAL_STORAGE", 1, 1) == -1) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 11);
        }
        byte[] bArr = (byte[]) message.obj;
        int length = bArr.length;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length + 24 + 1);
        byteBufferAllocate.put((byte) 35);
        byteBufferAllocate.put((byte) 35);
        byteBufferAllocate.put((byte) (a.f.GB_FILE_LIST.ordinal() & 255));
        byteBufferAllocate.put((byte) 1).put((byte) 3).putChar((char) (length & 65535)).put(bArr).put(com.byd.data.common.i.d.a(byteBufferAllocate.array(), byteBufferAllocate.capacity() - 1));
        this.fileOperator.h(byteBufferAllocate.array(), true);
        S(1000);
        this.sendCount++;
        return false;
    }

    static /* synthetic */ void P() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5 */
    public void R(View view) {
        ?? r1 = 1;
        switch (view.getId()) {
            case R.id.clear_temp_sn_btn /* 2131296365 */:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    boolean zEquals = textView.getText().equals("setting_flag");
                    textView.setText(zEquals ? "reset_flag" : "setting_flag");
                    r1 = zEquals;
                }
                com.byd.data.common.i.g.d("log_level", r1);
                com.byd.data.common.server.b.e().l(false, r1 != 0 ? 61 : 0);
                String str = r1 != 0 ? "设置采集Log完成" : "清除采集Log完成";
                Toast.makeText(this, str, 0).show();
                Log.i("MainActivity", "toast show " + str);
                break;
            case R.id.main_test_btn /* 2131296460 */:
                com.byd.data.common.f.d.getInstance().a(1, "MainActivity", "updateLoad", new d.a() { // from class: com.byd.data.collect.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3127b.T();
                    }
                });
                break;
            case R.id.show_dialog_btn /* 2131296562 */:
                MainTestPresent mainTestPresent = this.present;
                if (mainTestPresent != null) {
                    mainTestPresent.e();
                }
                break;
            case R.id.stop_service /* 2131296587 */:
                com.byd.data.common.f.d.getInstance().a(2, "MainActivity", "testHttp", new d.a() { // from class: com.byd.data.collect.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.P();
                    }
                });
                break;
        }
    }

    private void S(int i2) {
        Message messageObtainMessage = this.handle.obtainMessage(9);
        messageObtainMessage.obj = M();
        this.handle.sendMessageDelayed(messageObtainMessage, i2);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean H() {
        return androidx.navigation.v.b.a(p.a(this, R.id.nav_host_fragment_content_main), this.appBarConfiguration) || super.H();
    }

    public void T() {
        File file = new File(com.byd.data.common.g.a.i().h());
        if (file.exists()) {
            this.present.f(true, file);
        }
    }

    @Override // com.byd.data.common.c.d.a
    public void b(String str) {
    }

    @Override // com.byd.data.common.server.e.c
    public void c(byte[] bArr) {
    }

    @Override // com.byd.data.common.server.e.c
    public void f(a.g gVar) {
    }

    @Override // com.byd.data.common.c.d.a
    public void k(com.byd.data.common.b.b bVar) {
    }

    @Override // com.byd.data.common.server.e.c
    public Socket l() {
        return null;
    }

    @Override // com.byd.data.common.c.d.a
    public void m() {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.byd.data.collect.i.a aVarC = com.byd.data.collect.i.a.c(getLayoutInflater());
        this.binding = aVarC;
        setContentView(aVarC.b());
        J(this.binding.f3141c);
        this.binding.b().findViewById(R.id.show_dialog_btn).setOnClickListener(new View.OnClickListener() { // from class: com.byd.data.collect.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3130b.R(view);
            }
        });
        this.binding.b().findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() { // from class: com.byd.data.collect.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3130b.R(view);
            }
        });
        this.binding.b().findViewById(R.id.main_test_btn).setOnClickListener(new View.OnClickListener() { // from class: com.byd.data.collect.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3130b.R(view);
            }
        });
        Button button = (Button) this.binding.b().findViewById(R.id.clear_temp_sn_btn);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.byd.data.collect.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3130b.R(view);
            }
        });
        this.handle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.byd.data.collect.e
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3131b.N(message);
            }
        });
        this.present = new MainTestPresent(this);
        androidx.core.content.a.a(this, "android.permission.INTERNET");
        requestPermissions(new String[]{"android.permission.INTERNET"}, 112);
        ((TextView) this.binding.b().findViewById(R.id.get_transport_mode)).setText("TransportMOde is " + BYDAutoVehicleHealthDevice.getInstance(this).getTransportMode());
        button.setText(com.byd.data.common.i.g.a("log_level") == 1 ? "reset_flag" : "setting_flag");
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}
