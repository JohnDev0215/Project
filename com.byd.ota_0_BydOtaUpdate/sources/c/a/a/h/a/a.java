package c.a.a.h.a;

/* JADX INFO: compiled from: BaseState.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public String a(int i2) {
        switch (i2) {
            case 0:
                return "基础状态";
            case 10:
                return "防盗状态判断";
            case 11:
                return "防盗状态判断 LOCKED";
            case 12:
                return "防盗状态判断 UNLOCKED";
            case 20:
                return "电源档位判断";
            case 21:
                return "电源档位判断 ON";
            case 22:
                return "电源档位判断 OK";
            case 23:
                return "电源档位判断 OFF";
            case 24:
                return "电源档位判断 ACC";
            case 30:
                return "升级条件判断";
            case 31:
                return "升级条件判断 SUCCESS";
            case 32:
                return "升级条件判断 FAIL";
            case 40:
                return "关闭媒体call状态判断";
            case 41:
                return "关闭媒体call状态判断 SUCCESS";
            case 42:
                return "关闭媒体call状态判断 FAIL";
            case 50:
                return "充放电状态判断";
            case 51:
                return "充放电状态判断 WITH CHARGING";
            case 52:
                return "充放电状态判断 WITHOUT CHARGING";
            case 60:
                return "请求退出充放电";
            case 61:
                return "请求退出充放电 SUCCESS";
            case 62:
                return "请求退出充放电 FAIL";
            case 70:
                return "智能充电判断";
            case 71:
                return "智能充电判断 WITH SMART CHARGE";
            case 72:
                return "智能充电判断 WITHOUT SMART CHARGE";
            case 80:
                return "请求退出智能充电";
            case 81:
                return "请求退出智能充电 SUCCESS";
            case 82:
                return "请求退出智能充电 FAIL";
            case 90:
                return "bcm ota状态判断";
            case 91:
                return "bcm ota状态判断 SUCCESS";
            case 92:
                return "bcm ota状态判断 FAIL";
            case 100:
                return "禁止上电状态判断";
            case 101:
                return "禁止上电状态判断 SUCCESS";
            case 102:
                return "禁止上电状态判断 FAIL";
            case 110:
                return "30S倒计时弹框";
            case 111:
                return "30S倒计时弹框 SURE";
            case 112:
                return "30S倒计时弹框 CANCEL";
            case 120:
                return "二次升级条件判断";
            case 121:
                return "二次升级条件判断 SUCCESS";
            case 122:
                return "二次升级条件判断 FAIL";
            case 130:
                return "退出国标";
            case 131:
                return "退出国标 SUCCESS";
            case 140:
                return "请求退电";
            case 141:
                return "请求退电 SUCCESS";
            case 142:
                return "请求退电 FAIL";
            case 150:
                return "请求上电";
            case 151:
                return "请求上电 SUCCESS";
            case 152:
                return "请求上电 FAIL";
            case 160:
                return "请求高压放电";
            case 161:
                return "请求高压放电 SUCCESS";
            case 162:
                return "请求高压放电 FAIL";
            case 170:
                return "维持高压放电";
            case 171:
                return "维持高压放电 SUCCESS";
            case 172:
                return "维持高压放电 FAIL";
            case 180:
                return "判断DC是否正常降压";
            case 181:
                return "判断DC是否正常降压 SUCCESS";
            case 182:
                return "判断DC是否正常降压 FAIL";
            case 190:
                return "判断7DF是否存在";
            case 191:
                return "判断7DF是否存在 NO EXIST";
            case 192:
                return "判断7DF是否存在 EXIST";
            case 200:
                return "工厂模式升级包下载";
            case 201:
                return "工厂模式升级包下载 SUCCESS";
            case 202:
                return "工厂模式升级包下载 FAIL";
            case 210:
                return "工厂模式升级计时时间判断";
            case 211:
                return "工厂模式升级计时时间判断 SUCCESS";
            case 212:
                return "工厂模式升级计时时间判断 FAIL";
            case 220:
                return "背光状态判断";
            case 221:
                return "背光状态判断 ON";
            case 222:
                return "背光状态判断 OFF";
            case 230:
                return "[直接升级]bcm ota状态判断";
            case 231:
                return "[直接升级]bcm ota状态判断 SUCCESS";
            case 232:
                return "[直接升级]bcm ota状态判断 FAIL";
            case 240:
                return "[直接升级]请求退电";
            case 241:
                return "[直接升级]请求退电 SUCCESS";
            case 242:
                return "[直接升级]请求退电 FAIL";
            case 250:
                return "[直接升级]请求上电";
            case 251:
                return "[直接升级]请求上电 SUCCESS";
            case 252:
                return "[直接升级]请求上电 FAIL";
            case 1000:
                return "开始状态判断";
            case 1001:
                return "退出状态判断";
            case 1002:
                return "开始真正升级";
            case 1003:
                return "[直接升级]开始状态判断";
            case 1004:
                return "[直接升级]退出状态判断";
            case 1005:
                return "[直接升级]开始真正升级";
            case 1006:
                return "获取升级状态";
            default:
                return "undefined state";
        }
    }

    public abstract int b(int i2);

    public abstract void c(b bVar);

    public abstract void d(int i2);

    public void e(int i2) {
        b bVar = new b();
        bVar.f2227a = b(i2);
        c(bVar);
    }
}
