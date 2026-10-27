package com.byd.secretcode.activity;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.safetybelt.AbsBYDAutoSafetyBeltListener;
import android.hardware.bydauto.safetybelt.BYDAutoSafetyBeltDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.mg;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnKeyListener {
    public int A;
    public int A0;
    public int A1;
    public int A2;
    public int B;
    public int B0;
    public int B1;
    public int B2;
    public int C;
    public int C0;
    public int C1;
    public int C2;
    public int D;
    public int D0;
    public int D1;
    public int D2;
    public int E;
    public int E0;
    public int E1;
    public int E2;
    public int F;
    public int F0;
    public int F1;
    public int F2;
    public int G;
    public int G0;
    public int G1;
    public int G2;
    public int H;
    public int H0;
    public int H1;
    public int H2;
    public int I;
    public int I0;
    public int I1;
    public int I2;
    public int J;
    public int J0;
    public int J1;
    public int J2;
    public int K;
    public int K0;
    public int K1;
    public int K2;
    public int L;
    public int L0;
    public int L1;
    public int M;
    public int M0;
    public int M1;
    public int N;
    public int N0;
    public int N1;
    public ListView N2;
    public int O;
    public int O0;
    public int O1;
    public EditText O2;
    public int P;
    public int P0;
    public int P1;
    public TextView P2;
    public int Q;
    public int Q0;
    public int Q1;
    public int R;
    public int R0;
    public int R1;
    public BYDAutoStudy R2;
    public int S;
    public int S0;
    public int S1;
    public int T;
    public int T0;
    public int T1;
    public int U;
    public int U0;
    public int U1;
    public BYDAutoBodyworkDevice U2;
    public int V;
    public int V0;
    public int V1;
    public defpackage.a V2;
    public int W;
    public int W0;
    public int W1;
    public BYDAutoGearboxDevice W2;
    public int X;
    public int X0;
    public int X1;
    public BYDAutoPowerDevice X2;
    public int Y;
    public int Y0;
    public int Y1;
    public BYDAutoTestDevice Y2;
    public int Z;
    public int Z0;
    public int Z1;
    public BYDAutoSafetyBeltDevice Z2;
    public int a0;
    public int a1;
    public int a2;
    public int b;
    public int b0;
    public int b1;
    public int b2;
    public int c;
    public int c0;
    public int c1;
    public int c2;
    public int d;
    public int d0;
    public int d1;
    public int d2;
    public int e;
    public int e0;
    public int e1;
    public int e2;
    public int f;
    public int f0;
    public int f1;
    public int f2;
    public int g;
    public int g0;
    public int g1;
    public int g2;
    public int h;
    public int h0;
    public int h1;
    public int h2;
    public int i;
    public int i0;
    public int i1;
    public int i2;
    public int j;
    public int j0;
    public int j1;
    public int j2;
    public int k;
    public int k0;
    public int k1;
    public int k2;
    public int l;
    public int l0;
    public int l1;
    public int l2;
    public int m;
    public int m0;
    public int m1;
    public int m2;
    public int n;
    public int n0;
    public int n1;
    public int n2;
    public int o;
    public int o0;
    public int o1;
    public int o2;
    public int p;
    public int p0;
    public int p1;
    public int p2;
    public int q;
    public int q0;
    public int q1;
    public int q2;
    public int r;
    public int r0;
    public int r1;
    public int r2;
    public int s;
    public int s0;
    public int s1;
    public int s2;
    public int t;
    public int t0;
    public int t1;
    public int t2;
    public int u;
    public int u0;
    public int u1;
    public int u2;
    public int v;
    public int v0;
    public int v1;
    public int v2;
    public int w;
    public int w0;
    public int w1;
    public int w2;
    public int x;
    public int x0;
    public int x1;
    public int x2;
    public int y;
    public int y0;
    public int y1;
    public int y2;
    public int z;
    public int z0;
    public int z1;
    public int z2;
    public final String[] L2 = {"0x347-1.0:售前模式配置支持（有/无）", "0x341-7.0-7.2:整车驱动配置", "0x34F-2.5~2.6:DM平台类型（EV/DM2.5/DM2.0）", "0x433-3.2~3.3:有无后空调（有/无）", "0x433-3.0~3.1:有无后排空调开关按键（有/无）", "0x433-2.2~2.3:有无后排空调按键锁功能（有/无）", "0x433-2.0~2.1:是否具有后空调出风模式调节功能（有/无）", "0x433-1.6~1.7:是否具有后空调自动调节功能（有/无）", "0x433-1.4~1.5:是否具有后空调 AC 功能（有/无）", "0x433-1.2~1.3:是否具有后空调温度调节功能（有/无）", "0x433-1.0~1.1:是否具有后空调出风档位调节功能（有/无）", "0x433-5.0-5.1:空调自动按键能否关闭(是/否)", "0x429-1.4-1.5:方向盘有无加热配置", "0x4FA/ECU:0x402/ECU:0x42F:有无座椅按摩功能（有/无）（左前/右前/左后/右后）", "0x294-5.2-5.4:获取左域平台配置", "0x4F6-6.6~6.7:PM 2.5 检测单双通道功能", "0x433-4.5-4.6:有无负离子配置标志位（有/无）", "0x433-4.3-4.4:有无高温消毒杀菌模式（有/无）", "0x4A1-6.3:获取有无香氛（有/无）", "0x235-4.0-4.1:获取车类型（域车或非域车）", "0x31E-2.3-2.4:获取自学习是否支持解锁迎宾/闭锁迎宾", "域车：0x3FF/非域车:根据车类型获取大灯高度调节显示支持能力", "0x3FF-6.5:获取大灯高度档位配置", "0x3E3-2.0-2.1:车辆设置恢复出厂设置", "域车：0x407/非域车：0x396:根据车类型获取是否支持智能迎宾功能", "域车：0x38A/非域车：0x3AC或0x3AD:根据车类型获取是否支持日间行车灯功能", "域车：0x3FF-1.1:根据车类型获取是否支持室内灯", "0x4A9-5.0-5.1:是否支持倒车雷达", "有无维温功能配置/0x438/4.2-4.3", "满电解锁标志位（模式支持智能/不支持智能）/0x24A/6.7", "充电限制值配置（可选/不可选）/0x438/4.4-4.5", "OBC类型（非隔离车载/双向/单向隔离车载）/0x24A/3.2-3.3", "是否支持外后视镜随动/域车：0x407/3.2-3.3", "是否支持制动踏板感模式/0x0D5/2.3", "是否支持舒适停车/0x0D5/3.6", "是否支持预紧式安全带/0x481/学习ID", "是否支持转向助力模式设置/0x24C/3.0", "是否支持自动雨刮器/域车：0x475/非域车：0x475", "是否支持地面预瞄功能", "是否支持蓝牙通话自动降风速", "是否支持驻车内循环", "是否支持自动净化/4f6/ 6.6-6.7", "HUD自学习HUD项是否显示/0x38B/hasmessage", "是否支持智能分区/域车：0x433（DM车型有此设置项）/5.4~5.5", "是否支持后背门高度设置/域车：0x401", "有无配置智能领航/交通拥堵辅助/0x432（0x432 1.7和0x432  2.5同时为1代表支持）", "是否支持高速智能领航辅助/0x432/3.3", "是否支持城市智能领航辅助/0x454/2.0", "是否支持E2E智能领航辅助/0x454/2.2", "交互式变道辅助/0x432/3.4", "有无偏离预警/0x432/1.4", "有无车道保持/0x432/1.5", "有无偏离抑制/0x432/2.6", "紧急车道辅助/0x432/2.7", "交通标志识别/0x432/1.6", "智能限速报警/0x432/3.2", "智能限速控制/0x432/3.6", "交通信号灯识别/0x432/3.5", "开门预警/0x453/1.3", "后方碰撞预警/0x453/1.2", "后方横向来车预警/0x453/1", "前方横向来车预警/0x432/3", "前方横向来车制动/0x432/3.1", "有无配置振动报警方式/0x432(0x432 1.4=1且0x316的2.4=1)", "有无配置交通标志识别/0x432/1.6", "预测性碰撞报警模式/0x432/4.0", "有无配置自动紧急制动/0x432/1.1", "有无配置智能关怀/暂无协议，学习到DiTrainer则显示，DiTrainer 2.0上线后删除该选项", "有无配置防油门误踩/实习关怀（智能关怀）模式功能互斥，根据车型进行配置/0x34F/5.5", "有无配置自动泊车/APA/0x1D1/7.0-7.1", "有无代客泊车/0x1D1/3.6-3.7", "有无记忆泊车/0x1D1/3.4-3.5", "有无配置盲区监测", "有无配置后向穿行制动/0x453/1.1", "有无配置智能远近光灯/0x432/1.3", "有无配置智能矩阵光灯/0x3AC/4.2~4.3", "全景影像配置（2D高清全景影像/倒车影像+右前影像/倒车影像/标清全景影像/3D高清全景影像）/0x4F4/4.5-4.7", "有无配置电子车身稳定系统(ESP)车型0x321/7.2~7.3", "是否支持展车模式", "是否支持数字钥匙/0x436/1.2-1.3", "是否支持应用使用限制", "是否支持播报音量设置", "是否支持引擎音模拟器/48F/2.3", "是否支持车辆提示音源", "是否支持播报内容设置", "是否支持具体某项的播报/hasMessage/218(43)/40D(1)/2BC(无)/1D1(48)", "主驾座椅调节配置/0x494/5.2~5.3", "主驾电动腿托调节/0x494/6", "主驾电动头枕调节/0x494/ 6.1-6.2", "主驾座椅调节配置/0x494/3.0~3.2", "主驾电动腿托调节/0x437/7", "主驾电动头枕调节/0x437/7.1-7.2", "副驾座椅记忆功能配置/0x3CA/4.1", "副驾电动调节配置/0x3CA/5.4-5.6", "副驾腿托调节配置/0x3CA/7", "副驾头枕配置/0x3CA/7.1-7.2", "副驾腿托调节配置/0x437/4", "副驾头枕配置/0x437/4.1-4.2", "后排左侧水平调节开关显示/0x402/4", "后排左侧靠背调节开关显示/0x402/4.1", "后排左侧腿托调节开关显示/0x402/4.2", "后排左侧坐盆调节开关显示/0x402/4.3", "后排左侧高度调节开关显示/0x402/4.4", "后排左侧头枕水平调节开关显示/0x402/4.5", "后排左侧头枕高度调节开关显示/0x402/4.6", "后排左记忆开关显示/0x402/4.7", "左后座椅一键放倒配置/0x402/6.7", "左后座椅靠背调节配置/0x486/6", "左后座椅水平调节配置/0x486/6.2", "左后座椅腿托调节配置/0x486/7.4", "左后座椅记忆功能/0x486/7.6", "后排座椅一键放倒配置(左、右)/0x486/8", "后排右侧水平调节开关显示/0x42F/4", "后排右侧靠背调节开关显示/0x42F/4.1", "后排右侧腿托调节开关显示/0x42F/4.2", "后排右侧坐盆调节开关显示/0x42F/4.3", "后排右侧高度调节开关显示/0x42F/4.4", "后排右侧头枕水平调节开关显示/0x42F/4.5", "后排右侧头枕高度调节开关显示/0x42F/4.6", "后排右记忆开关显示/0x42F/4.7", "右后座椅一键放倒配置/0x42F/6.7", "右后座椅靠背调节配置/0x486/6.1", "右后座椅水平调节配置/0x486/6.3", "右后座椅腿托调节配置/0x486/7.5", "右后座椅记忆功能/0x486/7.7", "主驾驶位置是否支持方向盘调节/0x494/4.2-4.3", "主驾驶位置是否支持后视镜调节/0x494/7.4-7.5", "是否支持制热、制冷模式/0x36B/2.5-2.6", "根据车子类型自学习氛围灯颜色支持种类/域车：0x3FF/1.2-1.5", "根据车子类型自学习氛围灯亮度支持/域车：0x3FF/8.0-8.2", "根据车子类型自学习氛围灯支持的区域/域车：0x3FF/8.3-8.5", "根据车子类型自学习氛围灯是否支持多色彩模式/域车：0x288/非域车：0x3FF", "是否支持HUD高度调节", "是否支持HUD亮度调节", "是否支持仪表主题设置", "是否支持仪表高度调节/0x4BF/5.5", "是否支持仪表显示里程类型设置", "是否支持车辆放电功能/0x334/5.6", "是否支持续航里程显示标准配置/0x347/4", "是否支持e-pedal模式配置/0x34F/7.0-7.1", "续驶里程显示模式是否可选/0x245/7.6-7.7", "电子儿童锁配置", "车窗防夹标识位", "是否支持舒适加速/0x37F/7.2", "是支持行车自动关门", "是否支持仪表导航显示模式", "是否支持仪表中心常显", "是否支持加速计时器", "是否支持系统按键音", "直接式胎压界面状态/0x3A7/1.0-1.1", "燃油消耗量界面状态/0x3A7/1.6-1.7", "累计平均能耗界面状态/0x3A7/2.0-2.1", "累计平均油耗界面状态/0x3A7/2.2-2.3", "累计平均电耗界面状态/0x3A7/2.4-2.5", "最近 50KM 平均能耗界面状 态/0x3A7/2.6-2.7", "最近 50km 平均油耗界面状 态/0x3A7/3.0-3.1", "最近 50km 平均电耗界面状 态/0x3A7/3.2-3.3", "瞬时油耗界面状态/0x3A7/3.4-3.5", "外接充电电量界面状态/0x3A7/3.6-3.7", "能量流程图界面状态/0x3A7/4.0-4.1", "续驶里程（油）界面状态/0x3A7/4.2-4.3", "平均车速界面状态/0x3A7/4.4-4.5", "行驶时间界面状态（单次行 程）/0x3A7/4.6-4.7", "获取ADAS系统状态/0x454/1.4-1.7", "车辆是否支持智慧充电（混动车型）/0x470 子ID 0x2/2.1", "是否能接收或有无对应CAN信号//", "极限续航模式配置状态位/0x4BF/8.4", "是否支持电动前仓盖功能/0x44F/3.2-3.3", "充电预热配置支持（有/无）/0x347/5", "是否配备间接式DMS/0x454/2.4", "辅助驾驶语音播报开关/0x432", "座椅账号联动功能配置/0x4B3/32.3", "显示后排头枕屏设置项且显示“左后屏”/0x3BB(左RSE)/hasMessage(161)", "显示后排头枕屏设置项且显示“右后屏”和“定时关机”0x3C8（右RSE）/hasMessage(163)", "“模式切换”，选项为“标准模式”和“越野模式”/0x38B/4.4", "智能投屏开关（设置项取消/0x38B/7.2", "前排电动风口标志位/0x4BB/55.4-55.7", "有无座椅按摩功能（有/无）（左后）/ECU   :0x402/ 左后:5.6", "有无座椅按摩功能（有/无）（右后）/ECU   :0x42F/ 右后:5.6", "车辆需要驻车制动设置项/0x218/", "【后备箱】/0x401/3.3", "天窗遮阳 帘配置/左车身 控制器0x41A /5.4-5.6 ", "获取UI风格（海洋/王朝/腾势）", "判断是否是海洋风格", "判断是否是王朝风格", "判断是否是腾势风格", "判断当前车是否支持AFS(自适应大灯)/0x4AC/5.4~4.6", "有无配置智能领航/交通拥堵辅助/0x432/2.5", "预测性碰撞报警模式/0x432/1.2（4.0为1且1.2为1时显示）", "功放配置/0x4FD/6.0-6.5", "PAB软开关配置//", "前灯集成调光控制配置/0x3AC/2.7", "前灯集成调光控制配置/0x3AD/2.7", "限速变化声音提示软开关/0x454/2.5-2.6", "矢量控制功能项/0x1FD/5.3-5.4", "(CAN协议)DiSus显示功能项/0x1A7/4.7-5.2", " (CANFD协议)DiSus显示功能项/0x109/35.0-35.3", "警告辅助开关设置/0x288/58.4-58.5", "欢迎仪式开关设置/0x288/58.6-58.7", "环境模式开关设置/0x288/59.0-59.1", "星空顶开关设置/0x273/1.0-1.1", "舒适停车/0x37F/8", "低速紧急制动（开/关）(On/Off)/0x1D1/7.2-7.3", "侧边距离报警（开/关）(On/Off)/0x31A-0x02/3.0-3.1", "DiPilot /V2X/智能互联系统/0x346/2", "拖车行驶模式配置/0x475/7.4-7.5", "第二排座椅一键放倒(B3/通用控制器)/0x271/4.6", "第三排左侧水平调节开关显示/0x271/7", "第三排左侧靠背调节开关显示/0x271/7.1", "第三排左侧腿托调节开关显示/0x271/7.2", "第三排左侧坐盆调节开关显示/0x271/7.3", "第三排左侧高度调节开关显示/0x271/7.4", "第三排左侧头枕水平调节开关显 示/0x271/7.5", "第三排左侧头枕高度调节开关显 示/0x271/7.6", "第三排左后座椅一键放倒配置/0x271/3.6", "第三排座椅一键放倒开关显示/0x271/4.7", "第二排、第三排座椅一键放倒开关显示（所有座椅）/0x271/8.6", "第三排右侧水平调节开关显示/0x271/7.7", "第三排右侧靠背调节开关显示/0x271/8", "第三排右侧腿托调节开关显示/0x271/8.1", "第三排右侧坐盆调节开关显示/0x271/8.2", "第三排右侧高度调节开关显示/0x271/8.3", "第三排右侧头枕水平调节开关显 示/0x271/8.4", "第三排右侧头枕高度调节开关显 示/0x271/8.5", "第三排右后座椅一键放倒配置/0x271/3.7", "是否支持即插即充/0x347/4.3", "根据车类型获取是否支持door灯/0x3FF/1.0", "是否支持车速提醒/域车：0x235/4.2-4.4", "有无配置盲区监测/0x418/（学习到0x418 代表支持）hasmessage()", "智能限速地图版本显示/0x1ED/1.3-1.6", "主驾座椅热石按摩配置/ECU/0x4B3/61/", "副驾座椅热石按摩配置/ECU/0x4B3/61.1", "左后座椅热石按摩配置/ECU/0x4B3/61.2", "右后座椅热石按摩配置/ECU/0x4B3/61.3", "有无配置自动驻车/0x321/7.0~7.1等于2则显示", "后排控制面板标志位/0x3F1/1", "调光天幕配置/0x4BB/56.2-56.3", "报警模式/0x432 2.4=1/2.4", "是否支持BSIS(盲点信息系统)/0x22A/1.0", "MOIS 自学习配置判断/0x21E/1.0", "是否配置预约用车/0x347/5.1/0x0：有预约用车配置/0x1：无预约用车配置", "是否支持主动悬架模式/0x37F/8.1", "是否支持主动悬架模式/0x258/6.3", "支持手动、自动智能限速控制模式/0x454/8.0-8.1", "尾门类型标志/0x4BF/3.4-3.7", "TSR流量开关（ISA地图）/0x432/5.2-5.3/", "二次弹框归属地/0x432/5.0-5.1/", "是否支持SLW功能/0x432/7.4-7.5/", "是否支持交流充电电流限值功能配置/0x185/8.3-8.4/", "路面模式种类/0x46C/7.6-7.7/", "RNC功能开关/0x4FD/8.6/", "机油寿命开发自学习/0x38D/8.4-8.5", "变速器油开发自学习/0x38D/8.6-8.7", "制动液寿命开发自学习/0x38D/9.0-9.1", "刀片电池冷却液寿命开发自学习/0x38D/9.4-9.5", "长效冷却液寿命开发自学习/0x38D/9.2-9.3", "流媒体后视镜是否显示/0x4D2/3.6", "Cpd_SW_Mem_Status_S信号（是否记忆）自学习/0x167/4.6", "舱内移动监测功能设置项自学习/0x330/1.0-1.1", "[persist.vendor.bosch.cfg.supplies.a2b.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.adas.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.armrest.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.isp]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.lens]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.product.category]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.resolution]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.serializer]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.video.format]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.camera.video.frame.rate]: [-1]", "[persist.vendor.bosch.cfg.supplies.avm.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.camera.oms.ims]: [0]", "[persist.vendor.bosch.cfg.supplies.central.control.screen]: [-1]", "[persist.vendor.bosch.cfg.supplies.co.driver.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.isp]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.lens]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.product.category]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.resolution]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.serializer]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.video.format]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.camera.video.frame.rate]: [-1]", "[persist.vendor.bosch.cfg.supplies.dms.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.isp]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.lens]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.product.category]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.resolution]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.serializer]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.video.format]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.camera.video.frame.rate]: [-1]", "[persist.vendor.bosch.cfg.supplies.dvr.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.ethernet.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.face.id.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.headlightleft.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.headlightright.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.hud.eth.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.hud.headlight.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.isp]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.lens]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.product.category]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.resolution]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.serializer]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.video.format]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.camera.video.frame.rate]: [-1]", "[persist.vendor.bosch.cfg.supplies.ims.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.instrument.panel.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.panel.id]: [0,0]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.resolution]: [0]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.screen.size]: [0]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.touch.func]: [0]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.type]: [0]", "[persist.vendor.bosch.cfg.supplies.ipc.disp.usage.type]: [0]", "[persist.vendor.bosch.cfg.supplies.oms.camera.isp]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.lens]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.product.category]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.resolution]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.serializer]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.video.format]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.camera.video.frame.rate]: [-1]", "[persist.vendor.bosch.cfg.supplies.oms.name]: [-1]", "[persist.vendor.bosch.cfg.supplies.steamvideo.name]: [-1]"};
    public List<String> M2 = new ArrayList();
    public int Q2 = -1;
    public int S2 = -1;
    public String T2 = "";
    public BYDAutoInstrumentDevice a3 = null;

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b extends AbsBYDAutoSafetyBeltListener {
        public b() {
        }

        @Override // android.hardware.bydauto.safetybelt.AbsBYDAutoSafetyBeltListener
        public void onMainSeatStateChanged(int i) {
            super.onMainSeatStateChanged(i);
            BydLogHelper.logE(" onMainSeatStateChanged state = " + i);
            BydLogHelper.logE(" onMainSeatStateChanged stateMain = " + TestActivity.this.Z2.getMainSeatState());
        }
    }

    public class c extends AbsBYDAutoInstrumentListener {
        public c() {
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMusicResourceConfigChanged(int i, int i2) {
            BydLogHelper.logE(" onMusicResourceConfigChanged1234 type = " + i + ", state = " + i2);
        }
    }

    public class d extends AbsBYDAutoGearboxListener {
        public d() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i) {
            super.onGearChanged(i);
            BydLogHelper.logE(" onGearChanged / " + i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeLevelChanged(int i) {
            super.onGearboxAutoModeLevelChanged(i);
            BydLogHelper.logE(" onGearboxAutoModeLevelChanged / " + i);
        }
    }

    public class e implements DisplayManager.DisplayListener {
        public e() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    }

    public class f extends BaseAdapter {
        public List<String> b;

        public class a {
            public TextView a;

            public a() {
            }
        }

        public f(List<String> list) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            arrayList.clear();
            this.b.addAll(list);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(TestActivity.this.getApplicationContext()).inflate(R.layout.item_title_list, (ViewGroup) null);
                aVar = new a();
                aVar.a = (TextView) view.findViewById(R.id.btn_title);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText(this.b.get(i));
            return view;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (h(currentFocus, motionEvent)) {
                g(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void f() {
        this.g = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.MODEL_VALUE);
        this.u = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.PANORAMA_ONLINE);
        this.q = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.GO_HOME_TIME);
        this.t = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.LEAVE_HOME_TIME);
        this.C = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.SYSTEM_UNIT_VISIBLE);
        this.D = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.UNIT_POWER_VISIBLE);
        this.A = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.UNIT_TEMPERATURE_VISIBLE);
        this.B = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.UNIT_AIR_PRESSURE_VISIBLE);
        this.l = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.UNIT_DISTANCE_FUEL_CONSUMPTION_VISIBLE);
        this.K = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.REAR_AC_ONLINE);
        this.G = CarSettings.UserTableData.getInt(getContentResolver(), "energy_state");
        this.H = CarSettings.UserTableData.getInt(getContentResolver(), "drive_config");
        this.I = CarSettings.UserTableData.getInt(getContentResolver(), "dm_type");
        this.L = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_switch_config");
        this.e = CarSettings.UserTableData.getInt(getContentResolver(), "ac_wind_level");
        this.M = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_key_lock");
        this.N = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_wind_mode_control");
        this.O = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_auto_control");
        this.P = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_compressor");
        this.Q = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_tempertrue_control");
        this.R = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_wind_level_control");
        this.S = CarSettings.UserTableData.getInt(getContentResolver(), "ac_fault_num_shown_state");
        this.T = CarSettings.UserTableData.getInt(getContentResolver(), "ac_temperature_control_mode");
        this.U = CarSettings.UserTableData.getInt(getContentResolver(), "ac_wind_mode_num");
        this.V = CarSettings.UserTableData.getInt(getContentResolver(), "ac_auto_key_can_close");
        this.f = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.AC_HEAT_MODE_VISIBLE);
        this.z = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.Global.STEERING_WHEEL_HEATING_VISIBLE);
        this.o = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.Global.SEAT_MASSAGE_VISIBLE_LEFT);
        this.p = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.Global.SEAT_MASSAGE_VISIBLE_RIGHT);
        this.w = CarSettings.UserTableData.getInt(getContentResolver(), "seat_massage_visible_left_rear");
        this.x = CarSettings.UserTableData.getInt(getContentResolver(), "seat_massage_visible_right_rear");
        this.W = CarSettings.UserTableData.getInt(getContentResolver(), "left_plat_form_configs");
        this.X = CarSettings.UserTableData.getInt(getContentResolver(), "negative_ion");
        this.Y = CarSettings.UserTableData.getInt(getContentResolver(), "fragrance");
        this.Z = CarSettings.UserTableData.getInt(getContentResolver(), "car_type");
        this.a0 = CarSettings.UserTableData.getInt(getContentResolver(), "lamp_singnal");
        this.b0 = CarSettings.UserTableData.getInt(getContentResolver(), "afs_switch");
        this.r = CarSettings.UserTableData.getInt(getContentResolver(), "head_lamp");
        this.c0 = CarSettings.UserTableData.getInt(getContentResolver(), "head_lamp_level_config");
        this.y = CarSettings.UserTableData.getInt(getContentResolver(), "smart_welcome_light");
        this.k = CarSettings.UserTableData.getInt(getContentResolver(), "daytime_running_light");
        this.d0 = CarSettings.UserTableData.getInt(getContentResolver(), "door_light_state");
        this.i = CarSettings.UserTableData.getInt(getContentResolver(), "charge_temperature_control");
        this.e0 = CarSettings.UserTableData.getInt(getContentResolver(), "charge_toaggle_guard");
        this.f0 = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.CHARGE_LIMIT);
        this.F = CarSettings.UserTableData.getInt(getContentResolver(), "vtol");
        this.g0 = CarSettings.UserTableData.getInt(getContentResolver(), "auto_power_on");
        this.h0 = CarSettings.UserTableData.getInt(getContentResolver(), "auto_power_off");
        this.i0 = CarSettings.UserTableData.getInt(getContentResolver(), "rear_mirror_follow_up");
        this.j0 = CarSettings.UserTableData.getInt(getContentResolver(), "driver_seat_auto_return");
        this.k0 = CarSettings.UserTableData.getInt(getContentResolver(), "steer_position_auto_return");
        this.l0 = CarSettings.UserTableData.getInt(getContentResolver(), "brake_pedal_mode");
        this.j = CarSettings.UserTableData.getInt(getContentResolver(), "comfortable_parking");
        this.m0 = CarSettings.UserTableData.getInt(getContentResolver(), "motor_pretensioner_seatbelt");
        this.n0 = CarSettings.UserTableData.getInt(getContentResolver(), "steer_assis");
        this.o0 = CarSettings.UserTableData.getInt(getContentResolver(), "auto_wiper");
        this.p0 = CarSettings.UserTableData.getInt(getContentResolver(), "dynamic_suspension_mode");
        this.q0 = CarSettings.UserTableData.getInt(getContentResolver(), "remote_key_start_ac_time");
        this.r0 = CarSettings.UserTableData.getInt(getContentResolver(), "ac_auto_air");
        this.s0 = CarSettings.UserTableData.getInt(getContentResolver(), "auto_purification");
        this.t0 = CarSettings.UserTableData.getInt(getContentResolver(), "ac_tunnel_cycle");
        this.u0 = CarSettings.UserTableData.getInt(getContentResolver(), "hud_show_params");
        this.v0 = CarSettings.UserTableData.getInt(getContentResolver(), "rear_ac_panel_auto_lock_time");
        this.w0 = CarSettings.UserTableData.getInt(getContentResolver(), "ac_warm_type_online_state");
        this.x0 = CarSettings.UserTableData.getInt(getContentResolver(), "intelligent_ac_area");
        this.y0 = CarSettings.UserTableData.getInt(getContentResolver(), "door_height");
        this.z0 = CarSettings.UserTableData.getInt(getContentResolver(), "moon_roof_config");
        this.E = CarSettings.UserTableData.getInt(getContentResolver(), "urban_intelligent_navigation");
        this.s = CarSettings.UserTableData.getInt(getContentResolver(), "high_speed_intelligent_navigation");
        this.A0 = CarSettings.UserTableData.getInt(getContentResolver(), "urban_navigation");
        this.B0 = CarSettings.UserTableData.getInt(getContentResolver(), "e2e_navigation");
        this.C0 = CarSettings.UserTableData.getInt(getContentResolver(), "lane_change_assist");
        this.D0 = CarSettings.UserTableData.getInt(getContentResolver(), "lane_departure_warning");
        this.E0 = CarSettings.UserTableData.getInt(getContentResolver(), "lane_keep");
        this.F0 = CarSettings.UserTableData.getInt(getContentResolver(), "lane_departure_stop");
        this.G0 = CarSettings.UserTableData.getInt(getContentResolver(), "emergency_lane_keep_assist");
        this.H0 = CarSettings.UserTableData.getInt(getContentResolver(), "traffic_sign_recogniton");
        this.I0 = CarSettings.UserTableData.getInt(getContentResolver(), "intellignet_speed_limit_alert");
        this.J0 = CarSettings.UserTableData.getInt(getContentResolver(), "intellignet_speed_limit_control");
        this.K0 = CarSettings.UserTableData.getInt(getContentResolver(), "traffic_light_attention");
        this.m = CarSettings.UserTableData.getInt(getContentResolver(), "door_open_warning");
        this.v = CarSettings.UserTableData.getInt(getContentResolver(), "rear_collision_warning");
        this.L0 = CarSettings.UserTableData.getInt(getContentResolver(), "rear_traffic_crossing_alert");
        this.M0 = CarSettings.UserTableData.getInt(getContentResolver(), "front_crossing_traffic_alert");
        this.N0 = CarSettings.UserTableData.getInt(getContentResolver(), "front_crossing_traffic_brake");
        this.O0 = CarSettings.UserTableData.getInt(getContentResolver(), "shake_alert");
        this.P0 = CarSettings.UserTableData.getInt(getContentResolver(), "traffic_signs_recognition");
        this.Q0 = CarSettings.UserTableData.getInt(getContentResolver(), "predict_crash_alert_mode");
        this.R0 = CarSettings.UserTableData.getInt(getContentResolver(), "ai_emergency_brake");
        this.S0 = CarSettings.UserTableData.getInt(getContentResolver(), "mistake_gas");
        this.h = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.AUTO_PARKING);
        this.T0 = CarSettings.UserTableData.getInt(getContentResolver(), "valet_parking");
        this.U0 = CarSettings.UserTableData.getInt(getContentResolver(), "memory_parking");
        this.V0 = CarSettings.UserTableData.getInt(getContentResolver(), "blind_spot_assist");
        this.W0 = CarSettings.UserTableData.getInt(getContentResolver(), "rear_cross_brake");
        this.X0 = CarSettings.UserTableData.getInt(getContentResolver(), "ai_distance_light");
        this.Y0 = CarSettings.UserTableData.getInt(getContentResolver(), "ai_matrix_light");
        this.n = CarSettings.UserTableData.getInt(getContentResolver(), "esp_config");
        this.Z0 = CarSettings.UserTableData.getInt(getContentResolver(), "remote_control_lock_window_state");
        this.a1 = CarSettings.UserTableData.getInt(getContentResolver(), "remote_control_unlock_window_state");
        this.b1 = CarSettings.UserTableData.getInt(getContentResolver(), "micro_switch_lock_window_state");
        this.c1 = CarSettings.UserTableData.getInt(getContentResolver(), "micro_switch_unlock_window_state");
        this.d1 = CarSettings.UserTableData.getInt(getContentResolver(), "lock_car_rise_window");
        this.e1 = CarSettings.UserTableData.getInt(getContentResolver(), "over_speed_lock");
        this.f1 = CarSettings.UserTableData.getInt(getContentResolver(), "rain_auto_lock_window_state");
        this.g1 = CarSettings.UserTableData.getInt(getContentResolver(), "doorknob_auto_shrink_state");
        this.h1 = CarSettings.UserTableData.getInt(getContentResolver(), "lock_off");
        this.i1 = CarSettings.UserTableData.getInt(getContentResolver(), "digtal_key");
        this.j1 = CarSettings.UserTableData.getInt(getContentResolver(), "intelligent_unlock_state");
        this.k1 = CarSettings.UserTableData.getInt(getContentResolver(), "intelligent_lock_state");
        this.l1 = CarSettings.UserTableData.getInt(getContentResolver(), "remind_speed");
        this.m1 = CarSettings.UserTableData.getInt(getContentResolver(), "engine_sound");
        this.n1 = CarSettings.UserTableData.getInt(getContentResolver(), "main_driver_seat_adjustment");
        this.o1 = CarSettings.UserTableData.getInt(getContentResolver(), "main_drive_electric_leg_support_adjustment");
        this.p1 = CarSettings.UserTableData.getInt(getContentResolver(), "Main_Drive_Electric_Headrest_Adjustment");
        this.q1 = CarSettings.UserTableData.getInt(getContentResolver(), "Passenger_Seat_Memory_Function");
        this.r1 = CarSettings.UserTableData.getInt(getContentResolver(), "Passenger_Seat_Electric_Adjustment");
        this.s1 = CarSettings.UserTableData.getInt(getContentResolver(), "Passenger_Seat_Leg_Support_Adjustment");
        this.t1 = CarSettings.UserTableData.getInt(getContentResolver(), "Passenger_Seat_Headrest");
        this.u1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Level_Adjustment");
        this.v1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Backrest_Adjustment");
        this.w1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Leg_Adjustment");
        this.x1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Bidet_Adjustment");
        this.y1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Height_Adjustment");
        this.z1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Headrest_Level_Adjustment");
        this.A1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Headrest_Height_Adjustment");
        this.B1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Left_Memory_Switch");
        this.C1 = CarSettings.UserTableData.getInt(getContentResolver(), "Left_Rear_Seat_Recline");
        this.D1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Seat_Recline");
        this.E1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Level_Adjustment");
        this.F1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Backrest_Adjustment");
        this.G1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Leg_Support_Adjustment");
        this.H1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Bidet_Adjustment");
        this.I1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Height_Adjustment");
        this.J1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Headrest_Level_Adjustment");
        this.K1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Headrest_Height_Adjustment");
        this.L1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rear_Right_Memory_Switch");
        this.M1 = CarSettings.UserTableData.getInt(getContentResolver(), "Right_Rear_Seat_Recline");
        this.N1 = CarSettings.UserTableData.getInt(getContentResolver(), "Steering_Wheel");
        this.O1 = CarSettings.UserTableData.getInt(getContentResolver(), "Rearview_Mirror");
        this.P1 = CarSettings.UserTableData.getInt(getContentResolver(), "Refrigerator_Mode");
        this.Q1 = CarSettings.UserTableData.getInt(getContentResolver(), "Remenber_Power_Off_State");
        this.R1 = CarSettings.UserTableData.getInt(getContentResolver(), "Disinf_State");
        this.S1 = CarSettings.UserTableData.getInt(getContentResolver(), "Tray_Init_State");
        this.T1 = CarSettings.UserTableData.getInt(getContentResolver(), "Ambient_Light_Color");
        this.U1 = CarSettings.UserTableData.getInt(getContentResolver(), "Ambient_Light");
        this.V1 = CarSettings.UserTableData.getInt(getContentResolver(), "Ambient_Area");
        this.W1 = CarSettings.UserTableData.getInt(getContentResolver(), "Ambient_Colors");
        this.X1 = CarSettings.UserTableData.getInt(getContentResolver(), "Hud_Mode_Params");
        this.Y1 = CarSettings.UserTableData.getInt(getContentResolver(), "Instrument_Height");
        this.Z1 = CarSettings.UserTableData.getInt(getContentResolver(), "Discharge");
        this.a2 = CarSettings.UserTableData.getInt(getContentResolver(), "Drive_Distance_Show_Standard");
        this.b2 = CarSettings.UserTableData.getInt(getContentResolver(), "EPedal_State");
        this.c2 = CarSettings.UserTableData.getInt(getContentResolver(), "Drive_Distance_Show_Mode");
        this.d2 = CarSettings.UserTableData.getInt(getContentResolver(), "Electroni_Child_Lock_Config");
        this.e2 = CarSettings.UserTableData.getInt(getContentResolver(), "Window_Anti_Pinch_Config");
        this.f2 = CarSettings.UserTableData.getInt(getContentResolver(), "Comfortable_Speed_Up");
        this.g2 = CarSettings.UserTableData.getInt(getContentResolver(), "Tire_Pressure_Interface_Status");
        this.i2 = CarSettings.UserTableData.getInt(getContentResolver(), "Cumulative_Average_Energy_Consumption_Interface_Status");
        this.j2 = CarSettings.UserTableData.getInt(getContentResolver(), "Cumulative_Average_Fuel_Consumption_Interface_Status");
        this.k2 = CarSettings.UserTableData.getInt(getContentResolver(), "Cumulative_Average_Power_Consumption_Interface_Status");
        this.l2 = CarSettings.UserTableData.getInt(getContentResolver(), "The_Last_50KM_Average_Energy_Consumption_Interface_State");
        this.m2 = CarSettings.UserTableData.getInt(getContentResolver(), "The_Last_50KM_Average_Fuel_Consumption_Interface_State");
        this.n2 = CarSettings.UserTableData.getInt(getContentResolver(), "The_Last_50KM_Average_Power_Consumption_Interface_State");
        this.o2 = CarSettings.UserTableData.getInt(getContentResolver(), "Instantaneous_Fuel_Consumption_Interface_Status");
        this.p2 = CarSettings.UserTableData.getInt(getContentResolver(), "External_Charging_Power_Interface_Status");
        this.q2 = CarSettings.UserTableData.getInt(getContentResolver(), "Energy_Flow_Diagram_Interface_Status");
        this.r2 = CarSettings.UserTableData.getInt(getContentResolver(), "Driving_Range_Interface_Status_For_Fuel");
        this.s2 = CarSettings.UserTableData.getInt(getContentResolver(), "Average_Speed_Interface_Status");
        this.t2 = CarSettings.UserTableData.getInt(getContentResolver(), "Drive_Time_Interface_Status_For_Single_Trip");
        this.u2 = CarSettings.UserTableData.getInt(getContentResolver(), "ADAS_System_State");
        this.v2 = CarSettings.UserTableData.getInt(getContentResolver(), "Smart_Charging_PHEV");
        this.w2 = CarSettings.UserTableData.getInt(getContentResolver(), "Extreme");
        this.x2 = CarSettings.UserTableData.getInt(getContentResolver(), "Car_Plan");
        this.y2 = CarSettings.UserTableData.getInt(getContentResolver(), "No_Sense_Charge");
        this.z2 = CarSettings.UserTableData.getInt(getContentResolver(), "Electric_Front_Hatch_Configuration");
        this.A2 = CarSettings.UserTableData.getInt(getContentResolver(), "Charge_Preheat");
        this.B2 = CarSettings.UserTableData.getInt(getContentResolver(), "DMS_Assemble_Status");
        this.C2 = CarSettings.UserTableData.getInt(getContentResolver(), "Driving_Voice_Broadcast_SwitchConfig");
        this.D2 = CarSettings.UserTableData.getInt(getContentResolver(), "Rest_Mode_Configuration");
        this.E2 = CarSettings.UserTableData.getInt(getContentResolver(), "HUD_Mode_Configuration");
        this.F2 = CarSettings.UserTableData.getInt(getContentResolver(), "Intelligent_Screen_Casting_Switch");
        this.G2 = CarSettings.UserTableData.getInt(getContentResolver(), "FRElectric_Air_Outlet");
        this.H2 = CarSettings.UserTableData.getInt(getContentResolver(), "Left_Rear_Massage_Chair_Configuration");
        this.I2 = CarSettings.UserTableData.getInt(getContentResolver(), "Trunk_Configutarion");
        this.J2 = CarSettings.UserTableData.getInt(getContentResolver(), "Panoramic_Sunroof_Configutarion");
        this.K2 = CarSettings.UserTableData.getInt(getContentResolver(), "PreSale_Mode_Config");
        this.d = CarSettings.UserTableData.getInt(getContentResolver(), "elec_defrost");
        this.h2 = CarSettings.UserTableData.getInt(getContentResolver(), "Fuel_Consumption_Interface_Status");
        BydLogHelper.logD("getAutoFunctionFromDB: mPreSaleModeConfigByDB = " + this.K2);
        BydLogHelper.logD("getAutoFunctionFromDB: mELVINByDB = " + this.b);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossBrakingByDB = " + this.c);
        BydLogHelper.logD("getAutoFunctionFromDB: mElecDefrostByDB = " + this.d);
        BydLogHelper.logD("getAutoFunctionFromDB: mACWindLevelByDB = " + this.e);
        BydLogHelper.logD("getAutoFunctionFromDB: mAirConditionHeatModeByDB = " + this.f);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoModelStatusByDB = " + this.g);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoParkingByDB = " + this.h);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeTemperatureControlByDB = " + this.i);
        BydLogHelper.logD("getAutoFunctionFromDB: mComfortableParkingByDB = " + this.j);
        BydLogHelper.logD("getAutoFunctionFromDB: mDaytimeRunningLightByDB = " + this.k);
        BydLogHelper.logD("getAutoFunctionFromDB: mDistanceFuelConsumeByDB = " + this.l);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorOpenWarningByDB = " + this.m);
        BydLogHelper.logD("getAutoFunctionFromDB: mESPByDB = " + this.n);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontLeftSeatMassageByDB = " + this.o);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontRightSeatMassageByDB = " + this.p);
        BydLogHelper.logD("getAutoFunctionFromDB: mGoHomeTimeStatusByDB = " + this.q);
        BydLogHelper.logD("getAutoFunctionFromDB: mHeadlampByDB = " + this.r);
        BydLogHelper.logD("getAutoFunctionFromDB: mHighSpeedIntelligentNavigationByDB = " + this.s);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeaveHomeTimeStatusByDB = " + this.t);
        BydLogHelper.logD("getAutoFunctionFromDB: mPanoramaOnlineStatusByDB = " + this.u);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearCollisionWarningByDB = " + this.v);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftSeatMassageByDB = " + this.w);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightSeatMassageByDB = " + this.x);
        BydLogHelper.logD("getAutoFunctionFromDB: mSmartWelcomeLightByDB = " + this.y);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteeringWheelHeatByDB = " + this.z);
        BydLogHelper.logD("getAutoFunctionFromDB: mTemperatureUnitByDB = " + this.A);
        BydLogHelper.logD("getAutoFunctionFromDB: mTyrePressByDB = " + this.B);
        BydLogHelper.logD("getAutoFunctionFromDB: mUnitByDB = " + this.C);
        BydLogHelper.logD("getAutoFunctionFromDB: mUnitPowerByDB = " + this.D);
        BydLogHelper.logD("getAutoFunctionFromDB: mUrbanIntelligentNavigationByDB = " + this.E);
        BydLogHelper.logD("getAutoFunctionFromDB: mVTOLByDB = " + this.F);
        BydLogHelper.logD("getAutoFunctionFromDB: mEnergyStateByDB = " + this.G);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveConfigByDB = " + this.H);
        BydLogHelper.logD("getAutoFunctionFromDB: mDMTypeByDB = " + this.I);
        BydLogHelper.logD("getAutoFunctionFromDB: mInstrumentConfigureByDB = " + this.J);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcConfigByDB = " + this.K);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcSwitchConfigByDB = " + this.L);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcKeyLockByDB = " + this.M);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcWindModeControlByDB = " + this.N);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcAutoControlByDB = " + this.O);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcCompressorByDB = " + this.P);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcTempertureControlByDB = " + this.Q);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcWindLevelControlByDB = " + this.R);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcFaultNumShownStateByDB = " + this.S);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcTemperatureControlModeByDB = " + this.T);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcWindModeNumByDB = " + this.U);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcAutoKeyCanCloseByDB = " + this.V);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftPlatformConfigsByDB = " + this.W);
        BydLogHelper.logD("getAutoFunctionFromDB: mNegativeIonByDB = " + this.X);
        BydLogHelper.logD("getAutoFunctionFromDB: mFragranceByDB = " + this.Y);
        BydLogHelper.logD("getAutoFunctionFromDB: mCarTypeByDB = " + this.Z);
        BydLogHelper.logD("getAutoFunctionFromDB: mLampSingnalByDB = " + this.a0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAFSSwitchByDB = " + this.b0);
        BydLogHelper.logD("getAutoFunctionFromDB: mHeadlampLevelConfigByDB = " + this.c0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorLightStateByDB = " + this.d0);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeToaggleGuardByDB = " + this.e0);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargeLimitByDB = " + this.f0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPowerOnByDB = " + this.g0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPowerOffByDB = " + this.h0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearMirrorFollowUpByDB = " + this.i0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriverSeatAutoReturnByDB = " + this.j0);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteerPositionAutoReturnByDB = " + this.k0);
        BydLogHelper.logD("getAutoFunctionFromDB: mBrakePedalModeByDB = " + this.l0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMotorPretensionerSeatbeltByDB = " + this.m0);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteerAssisByDB = " + this.n0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoWiperByDB = " + this.o0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDynamicSuspensionModeByDB = " + this.p0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteKeyStartAcTimeByDB = " + this.q0);
        BydLogHelper.logD("getAutoFunctionFromDB: mACAutoAirByDB = " + this.r0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAutoPurificationByDB = " + this.s0);
        BydLogHelper.logD("getAutoFunctionFromDB: mACTunnelCycleByDB = " + this.t0);
        BydLogHelper.logD("getAutoFunctionFromDB: mHudShowParamsByDB = " + this.u0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearAcPanelAutoLockTimeByDB = " + this.v0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAcWarmTypeOnlineStateByDB = " + this.w0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentAcAreaByDB = " + this.x0);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorHeightByDB = " + this.y0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMoonRoofConfigByDB = " + this.z0);
        BydLogHelper.logD("getAutoFunctionFromDB: mUrbanNavigationByDB = " + this.A0);
        BydLogHelper.logD("getAutoFunctionFromDB: mEndToEndNavigationByDB = " + this.B0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneChangeAssistByDB = " + this.C0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneDepartureWarningByDB = " + this.D0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneKeepByDB = " + this.E0);
        BydLogHelper.logD("getAutoFunctionFromDB: mLaneDepartureStopByDB = " + this.F0);
        BydLogHelper.logD("getAutoFunctionFromDB: mEmergencyLaneKeepAssistByDB = " + this.G0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficSignRecognitonByDB = " + this.H0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntellignetSpeedLimitAlertByDB = " + this.I0);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntellignetSpeedLimitControlByDB = " + this.J0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficLightAttentionByDB = " + this.K0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearTrafficCrossingAlertByDB = " + this.L0);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossingTrafficAlertByDB = " + this.M0);
        BydLogHelper.logD("getAutoFunctionFromDB: mFrontCrossingTrafficBrakeByDB = " + this.N0);
        BydLogHelper.logD("getAutoFunctionFromDB: mShakeAlertByDB = " + this.O0);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrafficSignsRecognitionByDB = " + this.P0);
        BydLogHelper.logD("getAutoFunctionFromDB: mPredictCrashAlertModeByDB = " + this.Q0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiEmergencyBrakeByDB = " + this.R0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMistakeGasByDB = " + this.S0);
        BydLogHelper.logD("getAutoFunctionFromDB: mValetParkingByDB = " + this.T0);
        BydLogHelper.logD("getAutoFunctionFromDB: mMemoryParkingByDB = " + this.U0);
        BydLogHelper.logD("getAutoFunctionFromDB: mBlindSpotAssistByDB = " + this.V0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearCrossBrakeByDB = " + this.W0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiDistanceLightByDB = " + this.X0);
        BydLogHelper.logD("getAutoFunctionFromDB: mAiMatrixLightByDB = " + this.Y0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteControlLockWindowStateByDB = " + this.Z0);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemoteControlUnlockWindowStateByDB = " + this.a1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMicroSwitchLockWindowStateByDB = " + this.b1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMicroSwitchUnlockWindowStateByDB = " + this.c1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLockCarRiseWindowByDB = " + this.d1);
        BydLogHelper.logD("getAutoFunctionFromDB: mOverspeedLockByDB = " + this.e1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRainAutoLockWindowStateByDB = " + this.f1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDoorknobAutoShrinkStateByDB = " + this.g1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLockOffByDB = " + this.h1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDigtalKeySupportByDB = " + this.i1);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentUnlockStateByDB = " + this.j1);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentLockStateByDB = " + this.k1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemindSpeedSupportByDB = " + this.l1);
        BydLogHelper.logD("getAutoFunctionFromDB: mEngineSoundSupportByDB = " + this.m1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriverSeatAdjustmentByDB = " + this.n1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriveElectricLegSupportAdjustmentByDB = " + this.o1);
        BydLogHelper.logD("getAutoFunctionFromDB: mMainDriveElectricHeadrestAdjustmentByDB = " + this.p1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatMemoryFunctionByDB = " + this.q1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatElectricAdjustmentByDB = " + this.r1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatLegSupportAdjustmentByDB = " + this.s1);
        BydLogHelper.logD("getAutoFunctionFromDB: mPassengerSeatHeadrestByDB = " + this.t1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftLevelAdjustmentByDB = " + this.u1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftBackrestAdjustmentByDB = " + this.v1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftLegAdjustmentByDB = " + this.w1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftBidetAdjustmentByDB = " + this.x1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeightAdjustmentByDB = " + this.y1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeadrestLevelAdjustmentByDB = " + this.z1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftHeadrestHeightAdjustmentByDB = " + this.A1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearLeftMemorySwitchByDB = " + this.B1);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftRearSeatReclineByDB = " + this.C1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearSeatReclineByDB = " + this.D1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightLevelAdjustmentByDB = " + this.E1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightBackrestAdjustmentByDB = " + this.F1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightLegSupportAdjustmentByDB = " + this.G1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightBidetAdjustmentByDB = " + this.H1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeightAdjustmentByDB = " + this.I1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeadrestLevelAdjustmentByDB = " + this.J1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightHeadrestHeightAdjustmentByDB = " + this.K1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearRightMemorySwitchByDB = " + this.L1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRightRearSeatReclineByDB = " + this.M1);
        BydLogHelper.logD("getAutoFunctionFromDB: mSteeringWheelByDB = " + this.N1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRearviewMirrorByDB = " + this.O1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRefrigeratorModeByDB = " + this.P1);
        BydLogHelper.logD("getAutoFunctionFromDB: mRemenberPowerOffStateByDB = " + this.Q1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDisinfStateByDB = " + this.R1);
        BydLogHelper.logD("getAutoFunctionFromDB: mmTrayInitStateByDB = " + this.S1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientLightColorByDB = " + this.T1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientLightByDB = " + this.U1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientAreaByDB = " + this.V1);
        BydLogHelper.logD("getAutoFunctionFromDB: mAmbientColorsByDB = " + this.W1);
        BydLogHelper.logD("getAutoFunctionFromDB: mHudModeParamsByDB = " + this.X1);
        BydLogHelper.logD("getAutoFunctionFromDB: mInstrumentHeightByDB = " + this.Y1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDischargeByDB = " + this.Z1);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveDistanceShowStandardByDB = " + this.a2);
        BydLogHelper.logD("getAutoFunctionFromDB: mEPedalStateByDB = " + this.b2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveDistanceShowModeByDB = " + this.c2);
        BydLogHelper.logD("getAutoFunctionFromDB: mElectroniChildLockConfigByDB = " + this.d2);
        BydLogHelper.logD("getAutoFunctionFromDB: mWindowAntiPinchConfigByDB = " + this.e2);
        BydLogHelper.logD("getAutoFunctionFromDB: mComfortableSpeedUpByDB = " + this.f2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTirePressureInterfaceStatusByDB = " + this.g2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFuelConsumptionInterfaceStatusByDB = " + this.h2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCumulativeAverageEnergyConsumptionInterfaceStatusByDB = " + this.i2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCumulativeAverageFuelConsumptionInterfaceStatusByDB = " + this.j2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCumulativeAveragePowerConsumptionInterfaceStatusByDB = " + this.k2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTheLast50KMAverageEnergyConsumptionInterfaceStateByDB = " + this.l2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTheLast50KMAverageFuelConsumptionInterfaceStateByDB = " + this.m2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTheLast50KMAveragePowerConsumptionInterfaceStateByDB = " + this.n2);
        BydLogHelper.logD("getAutoFunctionFromDB: mInstantaneousFuelConsumptionInterfaceStatusByDB = " + this.o2);
        BydLogHelper.logD("getAutoFunctionFromDB: mExternalChargingPowerInterfaceStatusByDB = " + this.p2);
        BydLogHelper.logD("getAutoFunctionFromDB: mEnergyFlowDiagramInterfaceStatusByDB = " + this.q2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDrivingRangeInterfaceStatusForFuelByDB = " + this.r2);
        BydLogHelper.logD("getAutoFunctionFromDB: mAverageSpeedInterfaceStatusByDB = " + this.s2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDriveTimeInterfaceStatusForSingleTripByDB = " + this.t2);
        BydLogHelper.logD("getAutoFunctionFromDB: mADASSystemStateByDB = " + this.u2);
        BydLogHelper.logD("getAutoFunctionFromDB: mSmartChargingPHEVByDB = " + this.v2);
        BydLogHelper.logD("getAutoFunctionFromDB: mExtremeByDB = " + this.w2);
        BydLogHelper.logD("getAutoFunctionFromDB: mCarPlanByDB = " + this.x2);
        BydLogHelper.logD("getAutoFunctionFromDB: mNoSenseChargeByDB = " + this.y2);
        BydLogHelper.logD("getAutoFunctionFromDB: mElectricFrontHatchConfigurationByDB = " + this.z2);
        BydLogHelper.logD("getAutoFunctionFromDB: mChargePreheatByDB = " + this.A2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDMSAssembleStatusByDB = " + this.B2);
        BydLogHelper.logD("getAutoFunctionFromDB: mDrivingVoiceBroadcastSwitchConfigByDB = " + this.C2);
        BydLogHelper.logD("getAutoFunctionFromDB: mRestModeConfigurationByDB = " + this.D2);
        BydLogHelper.logD("getAutoFunctionFromDB: mHUDModeConfigurationByDB = " + this.E2);
        BydLogHelper.logD("getAutoFunctionFromDB: mIntelligentScreenCastingSwitchByDB = " + this.F2);
        BydLogHelper.logD("getAutoFunctionFromDB: mFRElectricAirOutletByDB = " + this.G2);
        BydLogHelper.logD("getAutoFunctionFromDB: mLeftRearMassageChairConfigurationByDB = " + this.H2);
        BydLogHelper.logD("getAutoFunctionFromDB: mTrunkConfigutarionByDB = " + this.I2);
        BydLogHelper.logD("getAutoFunctionFromDB: mPanoramicSunroofConfigutarionByDB = " + this.J2);
    }

    public final void g(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public final int getValue() {
        if (!TextUtils.isEmpty(this.O2.getText().toString().trim())) {
            return Integer.parseInt(this.O2.getText().toString().trim());
        }
        mg.b(getApplicationContext(), "没有输入参数");
        return -1;
    }

    public final boolean h(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public final void initData() {
        this.R2 = BYDAutoStudy.getInstance(getApplicationContext());
        this.V2 = defpackage.a.a(getApplicationContext());
        this.U2 = BYDAutoBodyworkDevice.getInstance(getApplicationContext());
        this.W2 = BYDAutoGearboxDevice.getInstance(getApplicationContext());
        this.X2 = BYDAutoPowerDevice.getInstance(getApplication());
        this.Y2 = BYDAutoTestDevice.getInstance(getApplication());
        this.a3 = BYDAutoInstrumentDevice.getInstance(getApplication());
        BYDAutoSafetyBeltDevice bYDAutoSafetyBeltDevice = BYDAutoSafetyBeltDevice.getInstance(getApplication());
        this.Z2 = bYDAutoSafetyBeltDevice;
        bYDAutoSafetyBeltDevice.registerListener((AbsBYDAutoSafetyBeltListener) new b());
        this.a3.registerListener((AbsBYDAutoInstrumentListener) new c());
        int length = this.L2.length;
        for (int i = 0; i < length; i++) {
            this.M2.add(this.L2[i]);
        }
        this.N2.setAdapter((ListAdapter) new f(this.M2));
        this.W2.registerListener((AbsBYDAutoGearboxListener) new d());
        ((DisplayManager) getSystemService("display")).registerDisplayListener(new e(), null);
    }

    public final void initView() {
        ListView listView = (ListView) findViewById(R.id.listview);
        this.N2 = listView;
        listView.setOnItemClickListener(this);
        this.P2 = (TextView) findViewById(R.id.tv_result);
        EditText editText = (EditText) findViewById(R.id.edit_text);
        this.O2 = editText;
        editText.addTextChangedListener(new a());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_test);
        initView();
        initData();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        BydLogHelper.logD(" study onItemClick position / " + i);
        String string = this.O2.getText().toString();
        switch (i) {
            case 0:
                this.S2 = this.R2.getPreSaleModelSupport();
                this.T2 = "getPreSaleModelSupport() = ";
                BydLogHelper.logE(" study onItemClick mUiStyle.getUiStyle() / " + this.V2.b() + ", mUiStyle.isDenza() / " + this.V2.c() + ", mUiStyle.isDynasty() / " + this.V2.d() + ", mUiStyle.isOcean() / " + this.V2.e());
                break;
            case 1:
                int i2 = CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.POWER_TYPE);
                this.S2 = this.R2.getDriveConfig();
                this.T2 = "carType = " + i2 + "getDriveConfig() = ";
                f();
                break;
            case 2:
                this.S2 = this.R2.getDMType();
                this.T2 = "getDMType() = ";
                break;
            case 3:
                this.S2 = this.R2.getSupportRearAc();
                this.T2 = "getSupportRearAc() = ";
                break;
            case 4:
                this.S2 = this.R2.getSupportRearAcSwitch();
                this.T2 = "getSupportRearAcSwitch() = ";
                break;
            case 5:
                this.S2 = this.R2.getSupportRearAcKeyLock();
                this.T2 = "getSupportRearAcKeyLock() = ";
                break;
            case 6:
                this.S2 = this.R2.getSupportRearAcWindModeControl();
                this.T2 = "getSupportRearAcWindModeControl() = ";
                break;
            case 7:
                this.S2 = this.R2.getSupportRearAcAutoControl();
                this.T2 = "getSupportRearAcAutoControl() = ";
                break;
            case 8:
                this.S2 = this.R2.getSupportRearAcCompressor();
                this.T2 = "getSupportRearAcCompressor() = ";
                break;
            case 9:
                this.S2 = this.R2.getSupportRearAcTempertureControl();
                this.T2 = "getSupportRearAcTempertureControl() = ";
                break;
            case 10:
                this.S2 = this.R2.getSupportRearAcWindLevelControl();
                this.T2 = "getSupportRearAcWindLevelControl() = ";
                break;
            case 11:
                this.S2 = this.R2.getSupportAcAutoKeyCanClose();
                this.T2 = "getSupportAcAutoKeyCanClose() = ";
                break;
            case 12:
                this.S2 = this.R2.getSteeringWheelHeatingConfiguration();
                this.T2 = "getSteeringWheelHeatingConfiguration() = ";
                break;
            case 13:
                this.S2 = this.R2.getSupportSeatMassage(getValue());
                this.T2 = "getSupportSeatMassage(int area) = ";
                break;
            case 14:
                this.S2 = this.R2.getLeftPlatformConfigs();
                this.T2 = "getLeftPlatformConfigs() = ";
                break;
            case 15:
                this.S2 = this.R2.getSupportPM25Mode();
                this.T2 = "getSupportPM25Mode() = ";
                break;
            case 16:
                this.S2 = this.R2.getSupportNegativeIon();
                this.T2 = "getSupportNegativeIon() = ";
                break;
            case 17:
                this.S2 = this.R2.getSupportHighTemperatureSterilize();
                this.T2 = "getSupportHighTemperatureSterilize() = ";
                break;
            case 18:
                this.S2 = this.R2.getSupportFragrance();
                this.T2 = "getSupportFragrance() = ";
                break;
            case 19:
                this.S2 = this.R2.getCarType();
                this.T2 = "getCarType() = ";
                break;
            case 20:
                this.S2 = this.R2.getSupportLampSingnal();
                this.T2 = "getSupportLampSingnal() = ";
                break;
            case 21:
                this.S2 = this.R2.getHeadlampSupport();
                this.T2 = "getHeadlampSupport() = ";
                break;
            case 22:
                this.S2 = this.R2.getHeadlampLevelConfig();
                this.T2 = "getHeadlampLevelConfig() = ";
                break;
            case 23:
                this.S2 = this.R2.restoreFactorySettings();
                this.T2 = "restoreFactorySettings() = ";
                break;
            case 24:
                this.S2 = this.R2.getUsherLightSupport();
                this.T2 = "getUsherLightSupport() = ";
                break;
            case 25:
                this.S2 = this.R2.getDayLightSupport();
                this.T2 = "getDayLightSupport() = ";
                break;
            case 26:
                this.S2 = this.R2.getIndoorLightSupport();
                this.T2 = "getIndoorLightSupport() = ";
                break;
            case 27:
                this.S2 = this.R2.getSupportReverseRadar();
                this.T2 = "getSupportReverseRadar() = ";
                break;
            case 28:
                this.S2 = this.R2.getIntelligentKeepTemSupport();
                this.T2 = "getIntelligentKeepTemSupport() = ";
                break;
            case 29:
                this.S2 = this.R2.getChargeToaggleGuardSupport();
                this.T2 = "getChargeToaggleGuardSupport() = ";
                break;
            case 30:
                this.S2 = this.R2.getChargeLimitSupport();
                this.T2 = "getChargeLimitSupport() = ";
                break;
            case 31:
                this.S2 = this.R2.getOBCType();
                this.T2 = "getOBCType() = ";
                break;
            case 32:
                this.S2 = this.R2.getAutoExternalRearMirrorFollowUpSwitchSupport();
                this.T2 = "getAutoExternalRearMirrorFollowUpSwitchSupport() = ";
                break;
            case 33:
                this.S2 = this.R2.getBrakePedalModeSupport();
                this.T2 = "getBrakePedalModeSupport() = ";
                break;
            case 34:
                this.S2 = this.R2.getComfortableParkingSupport();
                this.T2 = "getComfortableParkingSupport() = ";
                break;
            case 35:
                this.S2 = this.R2.getMotorPretensionerSeatbeltSupport();
                this.T2 = "getMotorPretensionerSeatbeltSupport() = ";
                break;
            case 36:
                this.S2 = this.R2.getSteerAssisSupport();
                this.T2 = "getSteerAssisSupport() = ";
                break;
            case 37:
                this.S2 = this.R2.getAutoWiperSupport();
                this.T2 = "getAutoWiperSupport() = ";
                break;
            case 38:
                this.S2 = this.R2.getLandAdvanceAtSupport();
                this.T2 = "getLandAdvanceAtSupport() = ";
                break;
            case 39:
                this.S2 = this.R2.getACBTWindSupport();
                this.T2 = "getACBTWindSupport() = ";
                break;
            case 40:
                this.S2 = this.R2.getACPauseCycleSupport();
                this.T2 = "getACPauseCycleSupport() = ";
                break;
            case 41:
                this.S2 = this.R2.getAutoPurificationSupport();
                this.T2 = "getAutoPurificationSupport() = ";
                break;
            case 42:
                this.S2 = this.U2.hasMessage(156);
                this.T2 = "getHudShowParamsSupport() = ";
                break;
            case 43:
                this.S2 = this.R2.getIntelligentAcAreaSupport();
                this.T2 = "getIntelligentAcAreaSupport() = ";
                break;
            case 44:
                this.S2 = this.R2.getDoorHeightSupport();
                this.T2 = "getDoorHeightSupport() = ";
                break;
            case 45:
                this.S2 = this.R2.getSupportIntelligentNavigation();
                this.T2 = "getSupportIntelligentNavigation() = ";
                break;
            case 46:
                this.S2 = this.R2.getSupportHighwayNavigation();
                this.T2 = "getSupportHighwayNavigation() = ";
                break;
            case 47:
                this.S2 = this.R2.getSupportUrbanNavigation();
                this.T2 = "getSupportUrbanNavigation() = ";
                break;
            case 48:
                this.S2 = this.R2.getSupportEndToEndNavigation();
                this.T2 = "getSupportEndToEndNavigation() = ";
                break;
            case 49:
                this.S2 = this.R2.getSupportLaneChangeAssist();
                this.T2 = "getSupportLaneChangeAssist() = ";
                break;
            case 50:
                this.S2 = this.R2.getSupportLaneDepartureWarning();
                this.T2 = "getSupportLaneDepartureWarning() = ";
                break;
            case 51:
                this.S2 = this.R2.getSupportLaneKeep();
                this.T2 = "getSupportLaneKeep() = ";
                break;
            case 52:
                this.S2 = this.R2.getSupportLaneDepartureStop();
                this.T2 = "getSupportLaneDepartureStop() = ";
                break;
            case 53:
                this.S2 = this.R2.getSupportEmergencyLaneKeepAssist();
                this.T2 = "getSupportEmergencyLaneKeepAssist() = ";
                break;
            case 54:
                this.S2 = this.R2.getSupportTrafficSignRecogniton();
                this.T2 = "getSupportTrafficSignRecogniton() = ";
                break;
            case 55:
                this.S2 = this.R2.getSupportIntellignetSpeedLimitAlert();
                this.T2 = "getSupportIntellignetSpeedLimitAlert() = ";
                break;
            case 56:
                this.S2 = this.R2.getSupportIntellignetSpeedLimitControl();
                this.T2 = "getSupportIntellignetSpeedLimitControl() = ";
                break;
            case 57:
                this.S2 = this.R2.getSupportTrafficLightAttention();
                this.T2 = "getSupportTrafficLightAttention() = ";
                break;
            case 58:
                this.S2 = this.R2.getSupportDoorOpenWarning();
                this.T2 = "getSupportDoorOpenWarning() = ";
                break;
            case 59:
                this.S2 = this.R2.getSupportRearCollisionWarning();
                this.T2 = "getSupportRearCollisionWarning() = ";
                break;
            case 60:
                this.S2 = this.R2.getSupportRearTrafficCrossingAlert();
                this.T2 = "getSupportRearTrafficCrossingAlert() = ";
                break;
            case 61:
                this.S2 = this.R2.getSupportFrontCrossingTrafficAlert();
                this.T2 = "getSupportFrontCrossingTrafficAlert() = ";
                break;
            case 62:
                this.S2 = this.R2.getSupportFrontCrossingTrafficBrake();
                this.T2 = "getSupportFrontCrossingTrafficBrake() = ";
                break;
            case 63:
                this.S2 = this.R2.getSupportShakeAlert();
                this.T2 = "getSupportShakeAlert() = ";
                break;
            case 64:
                this.S2 = this.R2.getSupportTrafficSignsRecognition();
                this.T2 = "getSupportTrafficSignsRecognition() = ";
                break;
            case 65:
                this.S2 = this.R2.getSupportPredictCrashAlertMode();
                this.T2 = "getSupportPredictCrashAlertMode() = ";
                break;
            case 66:
                this.S2 = this.R2.getSupportAiEmergencyBrake();
                this.T2 = "getSupportAiEmergencyBrake() = ";
                break;
            case 67:
                this.S2 = this.R2.getSupportAiCare();
                this.T2 = "getSupportAiCare() = ";
                break;
            case 68:
                this.S2 = this.R2.getSupportMistakeGas();
                this.T2 = "getSupportMistakeGas() = ";
                break;
            case 69:
                this.S2 = this.R2.getSupportAutoParking();
                this.T2 = "getSupportAutoParking() = ";
                break;
            case 70:
                this.S2 = this.R2.getValetParking();
                this.T2 = "getValetParking() = ";
                break;
            case 71:
                this.S2 = this.R2.getMemoryParking();
                this.T2 = "getMemoryParking() = ";
                break;
            case 72:
                this.S2 = this.R2.getSupportBlindSpotAssist();
                this.T2 = "getSupportBlindSpotAssist() = ";
                break;
            case 73:
                this.S2 = this.R2.getSupportRearCrossBrake();
                this.T2 = "getSupportRearCrossBrake() = ";
                break;
            case 74:
                this.S2 = this.R2.getSupportAiDistanceLight();
                this.T2 = "getSupportAiDistanceLight() = ";
                break;
            case 75:
                this.S2 = this.R2.getSupportAiMatrixLight();
                this.T2 = "getSupportAiMatrixLight() = ";
                break;
            case 76:
                this.S2 = this.R2.getSupportPanoramaImage();
                this.T2 = "getSupportPanoramaImage() = ";
                break;
            case 77:
                this.S2 = this.R2.getSupportESP();
                this.T2 = "getSupportESP() = ";
                break;
            case 78:
                this.S2 = this.R2.getSupportShowCarModel();
                this.T2 = "getSupportShowCarModel() = ";
                break;
            case 79:
                this.S2 = this.R2.getDigtalKeySupport();
                this.T2 = "getDigtalKeySupport() = ";
                break;
            case 80:
                this.S2 = this.R2.getAppUseLimitSupport();
                this.T2 = "getAppUseLimitSupport() = ";
                break;
            case 81:
                this.S2 = this.R2.getReportVolumeSupport();
                this.T2 = "getReportVolumeSupport() = ";
                break;
            case 82:
                this.S2 = this.R2.getEngineSoundSupport();
                this.T2 = "getEngineSoundSupport() = ";
                break;
            case 83:
                this.S2 = this.R2.getCarTipsSoundSupport();
                this.T2 = "getCarTipsSoundSupport() = ";
                break;
            case 84:
                this.S2 = this.R2.getReportContentSetSupport();
                this.T2 = "getReportContentSetSupport() = ";
                break;
            case 85:
                this.S2 = this.U2.hasMessage(getValue());
                this.T2 = "mBYDAutoBodyworkDevice.hasMessage(getValue()) = ";
                BydLogHelper.logD("getReportItemsSetSupport = " + this.S2);
                break;
            case 86:
                this.S2 = this.R2.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
                this.T2 = "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() = ";
                break;
            case 87:
                this.S2 = this.R2.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
                this.T2 = "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() = ";
                break;
            case 88:
                this.S2 = this.R2.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
                this.T2 = "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() = ";
                break;
            case 89:
                this.S2 = this.R2.getMainDriverSeatAdjustmentConfigurationByGCtrl();
                this.T2 = "getMainDriverSeatAdjustmentConfigurationByGCtrl() = ";
                break;
            case 90:
                this.S2 = this.R2.getMainDriveElectricLegSupportAdjustmentByGCtrl();
                this.T2 = "getMainDriveElectricLegSupportAdjustmentByGCtrl() = ";
                break;
            case 91:
                this.S2 = this.R2.getMainDriveElectricHeadrestAdjustmentByGCtrl();
                this.T2 = "getMainDriveElectricHeadrestAdjustmentByGCtrl() = ";
                break;
            case 92:
                this.S2 = this.R2.getPassengerSeatMemoryFunctionCanByECU();
                this.T2 = "getPassengerSeatMemoryFunctionCanByECU() = ";
                break;
            case 93:
                this.S2 = this.R2.getPassengerSeatElectricAdjustmentConfigurationByECU();
                this.T2 = "getPassengerSeatElectricAdjustmentConfigurationByECU() = ";
                break;
            case 94:
                this.S2 = this.R2.getPassengerSeatLegSupportAdjustmentConfigurationByECU();
                this.T2 = "getPassengerSeatLegSupportAdjustmentConfigurationByECU() = ";
                break;
            case 95:
                this.S2 = this.R2.getPassengerSeatHeadrestConfigurationByECU();
                this.T2 = "getPassengerSeatHeadrestConfigurationByECU() = ";
                break;
            case 96:
                this.S2 = this.R2.getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
                this.T2 = "getPassengerSeatHeadrestConfigurationByGCtrl() = ";
                break;
            case 97:
                this.S2 = this.R2.getPassengerSeatHeadrestConfigurationByGCtrl();
                this.T2 = "getPassengerSeatHeadrestConfigurationByGCtrl() = ";
                break;
            case 98:
                this.S2 = this.R2.getRearLeftLevelAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftLevelAdjustmentConfigurationByECU() = ";
                break;
            case 99:
                this.S2 = this.R2.getRearLeftBackrestAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftBackrestAdjustmentConfigurationByECU() = ";
                break;
            case 100:
                this.S2 = this.R2.getRearLeftLegSupportAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftLegSupportAdjustmentConfigurationByECU() = ";
                break;
            case 101:
                this.S2 = this.R2.getRearLeftBidetAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftBidetAdjustmentConfigurationByECU() = ";
                break;
            case 102:
                this.S2 = this.R2.getRearLeftHeightAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftHeightAdjustmentConfigurationByECU() = ";
                break;
            case 103:
                this.S2 = this.R2.getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftHeadrestLevelAdjustmentConfigurationByECU() = ";
                break;
            case 104:
                this.S2 = this.R2.getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
                this.T2 = "getRearLeftHeadrestHeightAdjustmentConfigurationByECU() = ";
                break;
            case 105:
                this.S2 = this.R2.getRearLeftMemorySwitchConfigurationByECU();
                this.T2 = "getRearLeftMemorySwitchConfigurationByECU() = ";
                break;
            case 106:
                this.S2 = this.R2.getLeftRearSeatReclineConfigurationByECU();
                this.T2 = "getLeftRearSeatReclineConfigurationByECU() = ";
                break;
            case 107:
                this.S2 = this.R2.getLeftRearSeatBackAdjustmentConfiguration();
                this.T2 = "getLeftRearSeatBackAdjustmentConfiguration() = ";
                break;
            case 108:
                this.S2 = this.R2.getLeftRearSeatHorizontalAdjustmentConfiguration();
                this.T2 = "getLeftRearSeatHorizontalAdjustmentConfiguration() = ";
                break;
            case 109:
                this.S2 = this.R2.getLeftRearSeatLegBracket();
                this.T2 = "getLeftRearSeatLegBracket() = ";
                break;
            case 110:
                this.S2 = this.R2.getLeftRearMemoryConfiguration();
                this.T2 = "getLeftRearMemoryConfiguration() = ";
                break;
            case 111:
                this.S2 = this.R2.getRearSeatReclineConfiguration();
                this.T2 = "getRearSeatReclineConfiguration() = ";
                break;
            case 112:
                this.S2 = this.R2.getRearRightLevelAdjustmentConfigurationByECU();
                this.T2 = "getRearRightLevelAdjustmentConfigurationByECU() = ";
                break;
            case 113:
                this.S2 = this.R2.getRearRightBackrestAdjustmentConfigurationByECU();
                this.T2 = "getRearRightBackrestAdjustmentConfigurationByECU() = ";
                break;
            case 114:
                this.S2 = this.R2.getRearRightLegSupportAdjustmentConfigurationByECU();
                this.T2 = "getRearRightLegSupportAdjustmentConfigurationByECU() = ";
                break;
            case 115:
                this.S2 = this.R2.getRearRightBidetAdjustmentConfigurationByECU();
                this.T2 = "getRearRightBidetAdjustmentConfigurationByECU() = ";
                break;
            case 116:
                this.S2 = this.R2.getRearRightHeightAdjustmentConfigurationByECU();
                this.T2 = "getRearRightHeightAdjustmentConfigurationByECU() = ";
                break;
            case 117:
                this.S2 = this.R2.getRearRightHeadrestLevelAdjustmentConfigurationByECU();
                this.T2 = "getRearRightHeadrestLevelAdjustmentConfigurationByECU() = ";
                break;
            case 118:
                this.S2 = this.R2.getRearRightHeadrestHeightAdjustmentConfigurationByECU();
                this.T2 = "getRearRightHeadrestHeightAdjustmentConfigurationByECU() = ";
                break;
            case 119:
                this.S2 = this.R2.getRearRightMemorySwitchConfigurationByECU();
                this.T2 = "getRearRightMemorySwitchConfigurationByECU() = ";
                break;
            case 120:
                this.S2 = this.R2.getRightRearSeatReclineConfigurationByEUC();
                this.T2 = "getRightRearSeatReclineConfigurationByEUC() = ";
                break;
            case 121:
                this.S2 = this.R2.getRightRearSeatBackAdjustmentConfiguration();
                this.T2 = "getRightRearSeatBackAdjustmentConfiguration() = ";
                break;
            case 122:
                this.S2 = this.R2.getRightRearSeatHorizontalAdjustmentConfiguration();
                this.T2 = "getRightRearSeatHorizontalAdjustmentConfiguration() = ";
                break;
            case 123:
                this.S2 = this.R2.getRightRearSeatLegBracket();
                this.T2 = "getRightRearSeatLegBracket() = ";
                break;
            case 124:
                this.S2 = this.R2.getRightRearMemoryConfiguration();
                this.T2 = "getRightRearMemoryConfiguration() = ";
                break;
            case 125:
                this.S2 = this.R2.getSteeringWheelSupport();
                this.T2 = "getSteeringWheelSupport() = ";
                break;
            case 126:
                this.S2 = this.R2.getRearviewMirrorSupport();
                this.T2 = "getRearviewMirrorSupport() = ";
                break;
            case 127:
                this.S2 = this.R2.getRefrigeratorModeSupport();
                this.T2 = "getRefrigeratorModeSupport() = ";
                break;
            case 128:
                this.S2 = this.R2.getAmbientLightColorSupport();
                this.T2 = "getAmbientLightColorSupport() = ";
                break;
            case 129:
                this.S2 = this.R2.getAmbientLightSupport();
                this.T2 = "getAmbientLightSupport() = ";
                break;
            case 130:
                this.S2 = this.R2.getAmbientAreaSupport();
                this.T2 = "getAmbientAreaSupport() = ";
                break;
            case 131:
                this.S2 = this.R2.getAmbientColorsSupport();
                this.T2 = "getAmbientColorsSupport() = ";
                break;
            case 132:
                this.S2 = this.R2.getHudHeightAdjustSupport();
                this.T2 = "getHudHeightAdjustSupport() = ";
                break;
            case 133:
                this.S2 = this.R2.getHudLightSdjustSupport();
                this.T2 = "getHudLightSdjustSupport() = ";
                break;
            case 134:
                this.S2 = this.R2.getInstrumentThemeSupport();
                this.T2 = "getInstrumentThemeSupport() = ";
                break;
            case 135:
                this.S2 = this.R2.getInstrumentHeightSupport();
                this.T2 = "getInstrumentHeightSupport() = ";
                break;
            case 136:
                this.S2 = this.R2.getInstrumentContentSupport();
                this.T2 = "getInstrumentContentSupport() = ";
                break;
            case 137:
                this.S2 = this.R2.getDischargeSupport();
                this.T2 = "getDischargeSupport() = ";
                break;
            case 138:
                this.S2 = this.R2.getDriveDistanceShowStandardSupport();
                this.T2 = "getDriveDistanceShowStandardSupport() = ";
                break;
            case 139:
                this.S2 = this.R2.getEPedalStateSupport();
                this.T2 = "getEPedalStateSupport() = ";
                break;
            case 140:
                this.S2 = this.R2.getDriveDistanceShowModeSupport();
                this.T2 = "getDriveDistanceShowModeSupport() = ";
                break;
            case 141:
                this.S2 = this.R2.getElectroniChildLockConfig();
                this.T2 = "getElectroniChildLockConfig() = ";
                break;
            case 142:
                this.S2 = this.R2.getWindowAntiPinchConfig();
                this.T2 = "getWindowAntiPinchConfig() = ";
                break;
            case 143:
                this.S2 = this.R2.getComfortableSpeedUp();
                this.T2 = "getComfortableSpeedUp() = ";
                break;
            case 144:
                this.S2 = this.R2.getRuningCloseDoorSupport();
                this.T2 = "getRuningCloseDoorSupport() = ";
                break;
            case 145:
                this.T2 = "getInstrumentNavDisplay（） = ";
                break;
            case 146:
                this.T2 = "getInstrumentCenterDisplay(mValue) = ";
                if (this.Q2 == -1 && "".equals(string)) {
                    Toast.makeText(this, "请输入参数！", 0).show();
                }
                break;
            case 147:
                this.T2 = "getSpeedUpCountSupport() = ";
                break;
            case 148:
                this.T2 = "getKeySystemVoiceSupport() = ";
                break;
            case 149:
                this.S2 = this.R2.getTirePressureInterfaceStatus();
                this.T2 = "getTirePressureInterfaceStatus() = ";
                break;
            case 150:
                this.S2 = this.R2.getFuelConsumptionInterfaceStatus();
                this.T2 = "getFuelConsumptionInterfaceStatus() = ";
                break;
            case 151:
                this.S2 = this.R2.getCumulativeAverageEnergyConsumptionInterfaceStatus();
                this.T2 = "getCumulativeAverageEnergyConsumptionInterfaceStatus() = ";
                break;
            case 152:
                this.S2 = this.R2.getCumulativeAverageFuelConsumptionInterfaceStatus();
                this.T2 = "getCumulativeAverageFuelConsumptionInterfaceStatus() = ";
                break;
            case 153:
                this.S2 = this.R2.getCumulativeAveragePowerConsumptionInterfaceStatus();
                this.T2 = "getCumulativeAveragePowerConsumptionInterfaceStatus() = ";
                break;
            case 154:
                this.S2 = this.R2.getTheLast50KMAverageEnergyConsumptionInterfaceState();
                this.T2 = "getTheLast50KMAverageEnergyConsumptionInterfaceState() = ";
                break;
            case 155:
                this.S2 = this.R2.getTheLast50KMAverageFuelConsumptionInterfaceState();
                this.T2 = "getTheLast50KMAverageFuelConsumptionInterfaceState() = ";
                break;
            case 156:
                this.S2 = this.R2.getTheLast50KMAveragePowerConsumptionInterfaceState();
                this.T2 = "getTheLast50KMAveragePowerConsumptionInterfaceState() = ";
                break;
            case 157:
                this.S2 = this.R2.getInstantaneousFuelConsumptionInterfaceStatus();
                this.T2 = "getInstantaneousFuelConsumptionInterfaceStatus() = ";
                break;
            case 158:
                this.S2 = this.R2.getExternalChargingPowerInterfaceStatus();
                this.T2 = "getExternalChargingPowerInterfaceStatus() = ";
                break;
            case 159:
                this.S2 = this.R2.getEnergyFlowDiagramInterfaceStatus();
                this.T2 = "getEnergyFlowDiagramInterfaceStatus() = ";
                break;
            case 160:
                this.S2 = this.R2.getDrivingRangeInterfaceStatusForFuel();
                this.T2 = "getDrivingRangeInterfaceStatusForFuel() = ";
                break;
            case 161:
                this.S2 = this.R2.getAverageSpeedInterfaceStatus();
                this.T2 = "getAverageSpeedInterfaceStatus() = ";
                break;
            case 162:
                this.S2 = this.R2.getDriveTimeInterfaceStatusForSingleTrip();
                this.T2 = "getDriveTimeInterfaceStatusForSingleTrip() = ";
                break;
            case 163:
                this.S2 = this.R2.getADASSystemState();
                this.T2 = "getADASSystemState = ";
                break;
            case 164:
                this.S2 = this.R2.getSupportSmartChargingPHEV();
                this.T2 = "getSupportSmartChargingPHEV() = ";
                break;
            case 165:
                this.T2 = "getSupportCAN(int canId) = ";
                if (this.Q2 == -1 && "".equals(string)) {
                    Toast.makeText(this, "请输入参数！", 0).show();
                }
                break;
            case 166:
                this.S2 = this.R2.getSupportExtreme();
                this.T2 = "getSupportExtreme() = ";
                break;
            case 167:
                this.S2 = this.R2.getElectricFrontHatchConfiguration();
                this.T2 = "getElectricFrontHatchConfiguration() = ";
                break;
            case 168:
                this.S2 = this.R2.getChargePreheatSupport();
                this.T2 = "getChargePreheatSupport() = ";
                break;
            case 169:
                this.S2 = this.R2.getDMSAssembleStatus();
                this.T2 = "getDMSAssembleStatus() = ";
                break;
            case 170:
                this.S2 = this.R2.getDrivingVoiceBroadcastSwitchConfig();
                this.T2 = "getDrivingVoiceBroadcastSwitchConfig() = ";
                break;
            case 171:
                this.S2 = this.R2.getRestModeConfiguration();
                this.T2 = "getRestModeConfiguration() = ";
                break;
            case 172:
                this.S2 = this.U2.hasMessage(161);
                this.T2 = "mBYDAutoBodyworkDevice.hasMessage(161) = ";
                break;
            case 173:
                this.S2 = this.U2.hasMessage(163);
                this.T2 = "mBYDAutoBodyworkDevice.hasMessage(163) = ";
                break;
            case 174:
                this.S2 = this.R2.getHUDModeConfiguration();
                this.T2 = "getHUDModeConfiguration() = ";
                break;
            case 175:
                this.S2 = this.R2.getIntelligentScreenCastingSwitchConfiguration();
                this.T2 = "getIntelligentScreenCastingSwitchConfiguration() = ";
                break;
            case 176:
                this.S2 = this.R2.getSupportFRElectricAirOutlet();
                this.T2 = "getSupportFRElectricAirOutlet() = ";
                break;
            case 177:
                this.S2 = this.R2.getLeftRearMassageChairConfigurationForECU();
                this.T2 = "getLeftRearMassageChairConfigurationForECU() = ";
                break;
            case 178:
                this.S2 = this.R2.getRightRearMassageChairConfigurationForECU();
                this.T2 = "getLeftRearMassageChairConfigurationForECU() = ";
                break;
            case 179:
                this.S2 = this.R2.getElectronicParkingBrakeConfigutarion();
                this.T2 = "getElectronicParkingBrakeConfigutarion() = ";
                break;
            case 180:
                this.S2 = this.R2.getTrunkConfigutarion();
                BydLogHelper.logE("banmState / " + this.S2);
                this.T2 = "getTrunkConfigutarion() = ";
                break;
            case 181:
                this.S2 = this.R2.getPanoramicSunroofConfigutarion();
                this.T2 = "getPanoramicSunroofConfigutarion() = ";
                break;
            case 186:
                this.S2 = this.R2.getSupportAFS();
                this.T2 = "getSupportAFS() = ";
                break;
            case 187:
                this.S2 = this.R2.getSupportIntelligentNavigationBy432();
                this.T2 = "getSupportIntelligentNavigationBy432() = ";
                break;
            case 188:
                this.S2 = this.R2.getHasPredictCrashAlertMode();
                this.T2 = "getHasPredictCrashAlertMode() = ";
                break;
            case 189:
                this.S2 = this.R2.getAmplifierConfiguration();
                this.T2 = "getAmplifierConfiguration() = ";
                break;
            case 190:
                this.S2 = this.R2.getPABSwitchState();
                this.T2 = "getPABSwitchState() = ";
                break;
            case 191:
                this.S2 = this.R2.getFrontLightDimmingCtrlConfigBy3AC();
                this.T2 = "getFrontLightDimmingCtrlConfigBy3AC() = ";
                break;
            case 192:
                this.S2 = this.R2.getFrontLightDimmingCtrlConfigBy3AD();
                this.T2 = "getFrontLightDimmingCtrlConfigBy3AD() = ";
                break;
            case 193:
                this.S2 = this.R2.getTSRLitSpdChgSwitchState();
                this.T2 = "getTSRLitSpdChgSwitchState() = ";
                break;
            case 194:
                this.S2 = this.R2.getCVCConfig();
                this.T2 = "getCVCConfig() = ";
                break;
            case 195:
                this.S2 = this.R2.getDiSusShowStateByCan();
                this.T2 = "getDiSusShowStateByCan() = ";
                break;
            case 196:
                this.S2 = this.R2.getDiSusShowStateByCanFD();
                this.T2 = "getDiSusShowStateByCanFD() = ";
                break;
            case 197:
                this.S2 = this.R2.getWarningAuxiliarySwitchSetting();
                this.T2 = "getWarningAuxiliarySwitchSetting() = ";
                break;
            case 198:
                this.S2 = this.R2.getWelcomeCeremonySwitchSetting();
                this.T2 = "getWelcomeCeremonySwitchSetting() = ";
                break;
            case 199:
                this.S2 = this.R2.getAmbientModeSwitchSettings();
                this.T2 = "getAmbientModeSwitchSettings() = ";
                break;
            case 200:
                this.S2 = this.R2.getStarTopSwitchSetting();
                this.T2 = "getStarTopSwitchSetting() = ";
                break;
            case 201:
                this.S2 = this.R2.getComfortableParkingBy37F();
                this.T2 = "getComfortableParkingBy37F() = ";
                break;
            case 202:
                this.S2 = this.R2.getLowSpeedEmergencyBrakingConfig();
                this.T2 = "getLowSpeedEmergencyBrakingConfig() = ";
                break;
            case 203:
                this.S2 = this.R2.getSideDistanceWarningConfig();
                this.T2 = "getSideDistanceWarningConfig() = ";
                break;
            case 204:
                this.S2 = this.R2.getV2XConfig();
                this.T2 = "getV2XConfig() = ";
                break;
            case 205:
                this.S2 = this.R2.getTrailerModeConfig();
                this.T2 = "getTrailerModeConfig() = ";
                break;
            case 206:
                this.S2 = this.R2.getFoldDownTheSecondRowOfSeatsConfig();
                this.T2 = "getFoldDownTheSecondRowOfSeatsConfig() = ";
                break;
            case 207:
                this.S2 = this.R2.getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 208:
                this.S2 = this.R2.getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 209:
                this.S2 = this.R2.getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 210:
                this.S2 = this.R2.getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 211:
                this.S2 = this.R2.getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 212:
                this.S2 = this.R2.getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 213:
                this.S2 = this.R2.getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
                this.T2 = "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() = ";
                break;
            case 214:
                this.S2 = this.R2.getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
                this.T2 = "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() = ";
                break;
            case 215:
                this.S2 = this.R2.getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
                this.T2 = "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() = ";
                break;
            case 216:
                this.S2 = this.R2.getAllRearSeatOneButtonDownSwitchDisplayConfig();
                this.T2 = "getAllRearSeatOneButtonDownSwitchDisplayConfig() = ";
                break;
            case 217:
                this.S2 = this.R2.getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 218:
                this.S2 = this.R2.getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 219:
                this.S2 = this.R2.getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 220:
                this.S2 = this.R2.getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 221:
                this.S2 = this.R2.getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 222:
                this.S2 = this.R2.getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 223:
                this.S2 = this.R2.getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
                this.T2 = "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() = ";
                break;
            case 224:
                this.S2 = this.R2.getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
                this.T2 = "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() = ";
                break;
            case 225:
                this.S2 = this.R2.getNoSenseChargeSupport();
                this.T2 = "getNoSenseChargeSupport() = ";
                break;
            case 226:
                this.S2 = this.R2.getDoorLightSupport();
                this.T2 = "getDoorLightSupport() = ";
                break;
            case 227:
                this.S2 = this.R2.getRemindSpeedSupport();
                this.T2 = "getRemindSpeedSupport() = ";
                break;
            case 228:
                this.S2 = this.U2.hasMessage(11);
                this.T2 = "mBYDAutoBodyworkDevice.hasMessage(11) = ";
                break;
            case 229:
                this.S2 = this.R2.getIntelligentSpeedLimitMapVersionDisplay();
                this.T2 = "uiStyleConfig.getIntelligentSpeedLimitMapVersionDisplay() = ";
                break;
            case 230:
                this.S2 = this.R2.getDriverSeatHotStoneMassageConfigurationByECU();
                this.T2 = "uiStyleConfig.getDriverSeatHotStoneMassageConfigurationByECU() = ";
                break;
            case 231:
                this.S2 = this.R2.getPassengerSeatHotStoneMassageConfigurationByECU();
                this.T2 = "uiStyleConfig.getPassengerSeatHotStoneMassageConfigurationByECU() = ";
                break;
            case 232:
                this.S2 = this.R2.getLeftRearSeatHotStoneMassageConfigurationByECU();
                this.T2 = "uiStyleConfig.getLeftRearSeatHotStoneMassageConfigurationByECU() = ";
                break;
            case 233:
                this.S2 = this.R2.getRightRearSeatHotStoneMassageConfigurationByECU();
                this.T2 = "uiStyleConfig.getRightRearSeatHotStoneMassageConfigurationByECU() = ";
                break;
            case 234:
                this.S2 = this.R2.getSupportAVH();
                this.T2 = "uiStyleConfig.getSupportAVH() = ";
                break;
            case 235:
                this.S2 = this.R2.getSupportRearControlPanelSign();
                this.T2 = "uiStyleConfig.getSupportRearControlPanelSign() = ";
                break;
            case 236:
                this.S2 = this.R2.getSupportDimmingCanopyConfiguration();
                this.T2 = "uiStyleConfig.getSupportDimmingCanopyConfiguration() = ";
                break;
            case 237:
                this.S2 = this.R2.getSupportAlarmMode();
                this.T2 = "uiStyleConfig.getSupportAlarmMode() = ";
                break;
            case 238:
                this.S2 = this.R2.getBSISSupport();
                this.T2 = "uiStyleConfig.getBSISSupport() = ";
                break;
            case 239:
                this.S2 = this.R2.getMOISSupport();
                this.T2 = "uiStyleConfig.getMOISSupport() = ";
                break;
            case 240:
                this.S2 = this.R2.getCarPlanSupport();
                this.T2 = "uiStyleConfig.getCarPlanSupport() = ";
                break;
            case 241:
                this.S2 = this.R2.getDynamicSuspensionModeByCan();
                this.T2 = "uiStyleConfig.getDynamicSuspensionModeByCan() = ";
                break;
            case 242:
                this.S2 = this.R2.getDynamicSuspensionModeByCanFD();
                this.T2 = "uiStyleConfig.getDynamicSuspensionModeByCanFD() = ";
                break;
            case 243:
                this.S2 = this.R2.getSupportManualModel();
                this.T2 = "uiStyleConfig.getSupportManualModel() = ";
                break;
            case 244:
                this.S2 = this.R2.getTailgateTypeMarking();
                this.T2 = "uiStyleConfig.getTailgateTypeMarking() = ";
                break;
            case 245:
                this.S2 = this.R2.getSupportTSRFlow();
                this.T2 = "uiStyleConfig.getSupportTSRFlow() = ";
                break;
            case 246:
                this.S2 = this.R2.getSupportDoubleConfirm();
                this.T2 = "uiStyleConfig.getSupportDoubleConfirm() = ";
                break;
            case 247:
                this.S2 = this.R2.getSupportSLW();
                this.T2 = "uiStyleConfig.getSupportSLW() = ";
                break;
            case 248:
                this.S2 = this.R2.getSupportAcChargingLimit();
                this.T2 = "uiStyleConfig.getSupportAcChargingLimit() = ";
                break;
            case 249:
                this.S2 = this.R2.getSupportRoadMode();
                this.T2 = "uiStyleConfig.getSupportRoadMode() = ";
                break;
            case 250:
                this.S2 = this.R2.getSupportRnc();
                this.T2 = "uiStyleConfig.getSupportRNC() = ";
                break;
            case 251:
                this.S2 = this.R2.getSupportOilEngine();
                this.T2 = "uiStyleConfig.getSupportOilEngine() = ";
                break;
            case 252:
                this.S2 = this.R2.getSupportOilTransmission();
                this.T2 = "uiStyleConfig.getSupportOilTransmission() = ";
                break;
            case 253:
                this.S2 = this.R2.getSupportOilBrake();
                this.T2 = "uiStyleConfig.getSupportOilBrake() = ";
                break;
            case 254:
                this.S2 = this.R2.getSupportOilBlade();
                this.T2 = "uiStyleConfig.getSupportOilBlade() = ";
                break;
            case 255:
                this.S2 = this.R2.getSupportOilCool();
                this.T2 = "uiStyleConfig.getSupportOilCool() = ";
                break;
            case 256:
                this.S2 = this.R2.getSupportSmrm();
                this.T2 = "uiStyleConfig.getSupportSMRM() = ";
                break;
            case 257:
                this.S2 = this.R2.getCpdSwMemStatus();
                this.T2 = "uiStyleConfig.getCpdSwMemStatus() = ";
                break;
            case 258:
                this.S2 = this.R2.getCabinMovementMonitoringFunction();
                this.T2 = "uiStyleConfig.getCabinMovementMonitoringFunction() = ";
                break;
            case 259:
                BydLogHelper.logD("a2b.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.a2b.name", "-1"));
                BydLogHelper.logD("adas.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.adas.name", "-1"));
                BydLogHelper.logD("armrest.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.armrest.name", "-1"));
                BydLogHelper.logD("avm.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.isp", "-1"));
                BydLogHelper.logD("avm.camera.lens / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.lens", "-1"));
                BydLogHelper.logD("avm.camera.product.category / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.product.category", "-1"));
                BydLogHelper.logD("avm.camera.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.resolution", "-1"));
                BydLogHelper.logD("avm.camera.serializer / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.serializer", "-1"));
                BydLogHelper.logD("avm.camera.video.format / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.video.format", "-1"));
                BydLogHelper.logD("avm.camera.video.frame.rate / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.video.frame.rate", "-1"));
                BydLogHelper.logD("avm.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.name", "-1"));
                BydLogHelper.logD("camera.oms.ims / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.camera.oms.ims", "-1"));
                BydLogHelper.logD("central.control.screen / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.central.control.screen", "-1"));
                BydLogHelper.logD("co.driver.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.co.driver.name", "-1"));
                BydLogHelper.logD("dms.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.isp", "-1"));
                BydLogHelper.logD("dms.camera.lens / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.lens", "-1"));
                BydLogHelper.logD("dms.camera.product.category / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.product.category", "-1"));
                BydLogHelper.logD("dms.camera.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.resolution", "-1"));
                BydLogHelper.logD("dms.camera.serializer / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.serializer", "-1"));
                BydLogHelper.logD("dms.camera.video.format / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.video.format", "-1"));
                BydLogHelper.logD("dms.camera.video.frame.rate / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.video.frame.rate", "-1"));
                BydLogHelper.logD("dvr.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.name", "-1"));
                BydLogHelper.logD("dvr.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.isp", "-1"));
                BydLogHelper.logD("dvr.camera.lens / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.lens", "-1"));
                BydLogHelper.logD("dvr.camera.product.category / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.product.category", "-1"));
                BydLogHelper.logD("dvr.camera.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.resolution", "-1"));
                BydLogHelper.logD("dvr.camera.serializer / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.serializer", "-1"));
                BydLogHelper.logD("dvr.camera.video.format / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.video.format", "-1"));
                BydLogHelper.logD("dvr.camera.video.frame.rate / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.camera.video.frame.rate", "-1"));
                BydLogHelper.logD("dvr.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.dvr.name", "-1"));
                BydLogHelper.logD("ethernet.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ethernet.name", "-1"));
                BydLogHelper.logD("face.id.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.face.id.name", "-1"));
                BydLogHelper.logD("headlightleft.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.headlightleft.name", "-1"));
                BydLogHelper.logD("headlightright.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.headlightright.name", "-1"));
                BydLogHelper.logD("hud.eth.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.hud.eth.name", "-1"));
                BydLogHelper.logD("hud.headlight.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.hud.headlight.name", "-1"));
                BydLogHelper.logD("ims.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.isp", "-1"));
                BydLogHelper.logD("ims.camera.lens / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.lens", "-1"));
                BydLogHelper.logD("ims.camera.product.category / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.product.category", "-1"));
                BydLogHelper.logD("ims.camera.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.resolution", "-1"));
                BydLogHelper.logD("ims.camera.serializer / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.serializer", "-1"));
                BydLogHelper.logD("ims.camera.video.format / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.video.format", "-1"));
                BydLogHelper.logD("ims.camera.video.frame.rate / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.camera.video.frame.rate", "-1"));
                BydLogHelper.logD("ims.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ims.name", "-1"));
                BydLogHelper.logD("instrument.panel.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.instrument.panel.name", "-1"));
                BydLogHelper.logD("ipc.disp.panel.id / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.panel.id", "-1"));
                BydLogHelper.logD("ipc.disp.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.resolution", "-1"));
                BydLogHelper.logD("ipc.disp.screen.size / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.screen.size", "-1"));
                BydLogHelper.logD("ipc.disp.touch.func / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.touch.func", "-1"));
                BydLogHelper.logD("ipc.disp.type / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.type", "-1"));
                BydLogHelper.logD("ipc.disp.usage.type / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.ipc.disp.usage.type", "-1"));
                BydLogHelper.logD("oms.camera.isp / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.isp", "-1"));
                BydLogHelper.logD("oms.camera.lens / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.lens", "-1"));
                BydLogHelper.logD("oms.camera.product.category / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.product.category", "-1"));
                BydLogHelper.logD("oms.camera.resolution / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.resolution", "-1"));
                BydLogHelper.logD("oms.camera.serializer / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.serializer", "-1"));
                BydLogHelper.logD("oms.camera.video.format / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.video.format", "-1"));
                BydLogHelper.logD("oms.camera.video.frame.rate / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.camera.video.frame.rate", "-1"));
                BydLogHelper.logD("oms.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.oms.name", "-1"));
                BydLogHelper.logD("steamvideo.name / " + SystemProperties.get("persist.vendor.bosch.cfg.supplies.steamvideo.name", "-1"));
                break;
        }
        BydLogHelper.logD(this.T2 + this.S2);
        this.Q2 = -1;
        this.P2.setText(this.T2 + "返回值为：" + this.S2);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        BydLogHelper.logE("onKey: keyCode = " + i);
        if (keyEvent.getAction() == 0) {
            return i == 4 || i == 82 || i == 87;
        }
        return false;
    }
}
