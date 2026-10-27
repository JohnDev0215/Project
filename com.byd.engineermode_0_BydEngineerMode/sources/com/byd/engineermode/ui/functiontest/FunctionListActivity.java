package com.byd.engineermode.ui.functiontest;

import android.content.Intent;
import android.hardware.bydauto.dms.BYDAutoDmsDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.bean.FunctionBean;
import com.byd.engineermode.bean.State;
import com.byd.engineermode.db.TestDatabase;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.ui.functiontest.gpstest.GPSLocationActivity;
import com.byd.engineermode.ui.functiontest.other.SpeakerTestActivity;
import com.byd.engineermode.utils.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class FunctionListActivity extends BaseActivity implements View.OnClickListener {
    private FunctionBean A;
    private BYDAutoDmsDevice C;
    private int v;
    private int w;
    private Handler x;
    private com.byd.engineermode.a.a.c z;
    private List<FunctionBean> y = new ArrayList();
    private ExecutorService B = Executors.newSingleThreadExecutor();

    public enum Function {
        Function_Temperature_Display_MID(0),
        Function_GPS_Information_MID(1),
        Function_Source_Test_MID(2),
        Function_Carplay_Android_Test_MID(3),
        Function_MIC_Test_MID(4),
        Function_Loudspeaker_Test_MID(5),
        Function_USB_test_MID(6),
        Function_Center_Control_Key_Test_MID(7),
        Function_Steering_Wheel_Key_Test_MID(8),
        Function_Screen_Test_MID(9),
        Function_Screen_Display_MID(10),
        Function_TP_Test_MID(11),
        Function_DVR_Recording_Playback_MID(12),
        Function_DMS_Test_MID(13),
        Function_Canning_Certificate_MID(14),
        Function_LINK_MARGIN_MID(15);

        private int id;

        Function(int i) {
            this.id = i;
        }

        public static Function find(int i) {
            for (Function function : values()) {
                if (function.ordinal() == i) {
                    return function;
                }
            }
            return null;
        }

        public int id() {
            return this.id;
        }
    }

    class a implements com.byd.engineermode.a.b.a<FunctionBean> {
        a() {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(FunctionBean functionBean, int i, int i2) {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(FunctionBean functionBean, int i, int i2) {
            switch (d.f2397a[Function.find(i).ordinal()]) {
                case 1:
                    Intent intent = new Intent(FunctionListActivity.this, (Class<?>) TemperatureTestActivity.class);
                    intent.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent, 0);
                    com.byd.engineermode.utils.b.a().e("TemperatureTest");
                    break;
                case 2:
                    Intent intent2 = new Intent(FunctionListActivity.this, (Class<?>) GPSLocationActivity.class);
                    intent2.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent2, 0);
                    com.byd.engineermode.utils.b.a().e("GPSLocationTest");
                    break;
                case 3:
                    Intent intent3 = new Intent(FunctionListActivity.this, (Class<?>) SourceTestActivity.class);
                    intent3.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent3, 0);
                    com.byd.engineermode.utils.b.a().e("SourceTest");
                    break;
                case 4:
                    com.byd.engineermode.ui.dialog.a.a(FunctionListActivity.this).b(R.string.function_not_available);
                    break;
                case 5:
                    Intent intent4 = new Intent(FunctionListActivity.this, (Class<?>) MicTestActivity.class);
                    intent4.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent4, 0);
                    com.byd.engineermode.utils.b.a().e("MICTest");
                    break;
                case 6:
                    Intent intent5 = new Intent(FunctionListActivity.this, (Class<?>) SpeakerTestActivity.class);
                    intent5.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent5, 0);
                    com.byd.engineermode.utils.b.a().e("LoudspeakerTest");
                    break;
                case 7:
                    Intent intent6 = new Intent(FunctionListActivity.this, (Class<?>) USBTestActivity.class);
                    intent6.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent6, 0);
                    com.byd.engineermode.utils.b.a().e("USBTest");
                    break;
                case 8:
                    com.byd.engineermode.ui.dialog.a.a(FunctionListActivity.this).b(R.string.function_not_available);
                    break;
                case 9:
                    Intent intent7 = new Intent(FunctionListActivity.this, (Class<?>) SteeringWheelKeysTestActivity.class);
                    intent7.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent7, 0);
                    com.byd.engineermode.utils.b.a().e("SteeringWheelKeyTest");
                    break;
                case 10:
                    Intent intent8 = new Intent(FunctionListActivity.this, (Class<?>) ScreenTestActivity.class);
                    intent8.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent8, 0);
                    com.byd.engineermode.utils.b.a().e("ScreenTest");
                    break;
                case 11:
                    Intent intent9 = new Intent(FunctionListActivity.this, (Class<?>) ScreenDisplayTestActivity.class);
                    intent9.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent9, 0);
                    com.byd.engineermode.utils.b.a().e("ScreenDisplay");
                    break;
                case 12:
                    Intent intent10 = new Intent(FunctionListActivity.this, (Class<?>) TpTestActivity.class);
                    intent10.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent10, 0);
                    com.byd.engineermode.utils.b.a().e("TPClickTest");
                    break;
                case 13:
                    com.byd.engineermode.ui.dialog.a.a(FunctionListActivity.this).b(R.string.function_not_available);
                    com.byd.engineermode.utils.b.a().e("DVRRecordingPlayback");
                    break;
                case 14:
                    FunctionListActivity.this.C.setDmsMode(2);
                    break;
                case 15:
                    Intent intent11 = new Intent(FunctionListActivity.this, (Class<?>) CanningCertificateActivity.class);
                    intent11.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent11, 0);
                    break;
                case 16:
                    Intent intent12 = new Intent(FunctionListActivity.this, (Class<?>) LinkMarginTestActivity.class);
                    intent12.putExtra("function", functionBean);
                    FunctionListActivity.this.A = functionBean;
                    FunctionListActivity.this.startActivityForResult(intent12, 0);
                    break;
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TestDatabase testDatabaseW = TestDatabase.w(FunctionListActivity.this);
            List<FunctionBean> listC = testDatabaseW.v().c();
            com.byd.engineermode.utils.a.a(listC != null ? "list size = " + listC.size() : "list is null");
            if (listC == null || listC.isEmpty()) {
                com.byd.engineermode.utils.a.a("init list");
                listC = new ArrayList<>();
                for (String str : FunctionListActivity.this.getResources().getStringArray(R.array.functions)) {
                    FunctionBean functionBean = new FunctionBean(str);
                    functionBean.i(testDatabaseW.v().a(functionBean));
                    listC.add(functionBean);
                }
            }
            FunctionListActivity.this.y.addAll(listC);
            Iterator it = FunctionListActivity.this.y.iterator();
            while (it.hasNext()) {
                com.byd.engineermode.utils.a.a("functionBean = " + ((FunctionBean) it.next()));
            }
            FunctionListActivity.this.x.sendEmptyMessage(0);
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2395b;

        c(int i) {
            this.f2395b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            FunctionListActivity.this.A.j(this.f2395b == -1 ? State.PASSED : State.FAILED);
            TestDatabase testDatabaseW = TestDatabase.w(FunctionListActivity.this);
            com.byd.engineermode.utils.a.a("updateFunction mCurrentBean = " + FunctionListActivity.this.A);
            testDatabaseW.v().b(FunctionListActivity.this.A);
            FunctionListActivity.this.x.sendEmptyMessage(0);
        }
    }

    static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2397a;

        static {
            int[] iArr = new int[Function.values().length];
            f2397a = iArr;
            try {
                iArr[Function.Function_Temperature_Display_MID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2397a[Function.Function_GPS_Information_MID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2397a[Function.Function_Source_Test_MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2397a[Function.Function_Carplay_Android_Test_MID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2397a[Function.Function_MIC_Test_MID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2397a[Function.Function_Loudspeaker_Test_MID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2397a[Function.Function_USB_test_MID.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2397a[Function.Function_Center_Control_Key_Test_MID.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2397a[Function.Function_Steering_Wheel_Key_Test_MID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2397a[Function.Function_Screen_Test_MID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2397a[Function.Function_Screen_Display_MID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2397a[Function.Function_TP_Test_MID.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f2397a[Function.Function_DVR_Recording_Playback_MID.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f2397a[Function.Function_DMS_Test_MID.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f2397a[Function.Function_Canning_Certificate_MID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f2397a[Function.Function_LINK_MARGIN_MID.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    private static class e extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<FunctionListActivity> f2398a;

        public e(FunctionListActivity functionListActivity) {
            this.f2398a = new WeakReference<>(functionListActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.engineermode.utils.a.a("handleMessage");
            FunctionListActivity functionListActivity = this.f2398a.get();
            if (functionListActivity != null) {
                functionListActivity.z.h();
                functionListActivity.H();
            }
        }
    }

    private void S() {
        L();
        this.B.submit(new b());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.A != null) {
            this.B.submit(new c(i2));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        startActivity(new Intent(this, (Class<?>) ScreenDisplayTestActivity.class));
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_function_list);
        this.x = new e(this);
        this.v = getWindowManager().getDefaultDisplay().getHeight() / 4;
        this.w = (getWindowManager().getDefaultDisplay().getWidth() / 4) - ((int) g.b(2.0f));
        com.byd.engineermode.utils.a.a("mItemHeight = " + this.v);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.h(new com.byd.engineermode.a.c.a((int) g.b(3.0f), getResources().getColor(R.color.black)));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        com.byd.engineermode.a.a.c cVar = new com.byd.engineermode.a.a.c(this.y);
        this.z = cVar;
        cVar.z(this.w, this.v);
        recyclerView.setAdapter(this.z);
        this.C = BYDAutoDmsDevice.getInstance(this);
        this.z.A(new a());
        S();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        ExecutorService executorService = this.B;
        if (executorService != null) {
            executorService.shutdown();
        }
        super.onDestroy();
    }
}
