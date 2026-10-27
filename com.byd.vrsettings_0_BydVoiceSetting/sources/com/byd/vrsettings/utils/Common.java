package com.byd.vrsettings.utils;

import android.car.Car;
import android.car.media.CarAudioManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.byd.common.bean.WuwInfoBean;
import com.byd.vrdownload.model.LanguageName;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.service.OtaDataManager;
import com.chad.library.BuildConfig;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class Common {
    public static final String COUNTRY_CODE_JSON = "[\n  {\n    \"countryandregionname\":\"China\",\n    \"internationaldomain\":\"CN\",\n    \"countrycode\":\"86\"\n  },\n  {\n    \"countryandregionname\":\"Singapore\",\n    \"internationaldomain\":\"SG\",\n    \"countrycode\":\"65\"\n  },\n  {\n    \"countryandregionname\":\"Hong Kong\",\n    \"internationaldomain\":\"HK\",\n    \"countrycode\":\"852\"\n  },\n  {\n    \"countryandregionname\":\"Macao\",\n    \"internationaldomain\":\"MO\",\n    \"countrycode\":\"853\"\n  },\n  {\n    \"countryandregionname\":\"Nepal\",\n    \"internationaldomain\":\"NP\",\n    \"countrycode\":\"977\"\n  },\n  {\n    \"countryandregionname\":\"Australia\",\n    \"internationaldomain\":\"AU\",\n    \"countrycode\":\"61\"\n  },\n  {\n    \"countryandregionname\":\"Thailand\",\n    \"internationaldomain\":\"TH\",\n    \"countrycode\":\"66\"\n  },\n  {\n    \"countryandregionname\":\"Japan\",\n    \"internationaldomain\":\"JP\",\n    \"countrycode\":\"81\"\n  },\n  {\n    \"countryandregionname\":\"Sri Lanka\",\n    \"internationaldomain\":\"LK\",\n    \"countrycode\":\"94\"\n  },\n  {\n    \"countryandregionname\":\"Pakistan\",\n    \"internationaldomain\":\"PK\",\n    \"countrycode\":\"92\"\n  },\n  {\n    \"countryandregionname\":\"India\",\n    \"internationaldomain\":\"IN\",\n    \"countrycode\":\"91\"\n  },\n  {\n    \"countryandregionname\":\"Malaysia\",\n    \"internationaldomain\":\"MY\",\n    \"countrycode\":\"60\"\n  },\n  {\n    \"countryandregionname\":\"Indonesia\",\n    \"internationaldomain\":\"ID\",\n    \"countrycode\":\"62\"\n  },\n  {\n    \"countryandregionname\":\"Myanmar\",\n    \"internationaldomain\":\"MM\",\n    \"countrycode\":\"95\"\n  },\n  {\n    \"countryandregionname\":\"Laos\",\n    \"internationaldomain\":\"LA\",\n    \"countrycode\":\"856\"\n  },\n  {\n    \"countryandregionname\":\"Cambodia\",\n    \"internationaldomain\":\"KH\",\n    \"countrycode\":\"855\"\n  },\n  {\n    \"countryandregionname\":\"Philippines\",\n    \"internationaldomain\":\"PH\",\n    \"countrycode\":\"63\"\n  },\n  {\n    \"countryandregionname\":\"polynesia\",\n    \"internationaldomain\":\"PF\",\n    \"countrycode\":\"689\"\n  },\n  {\n    \"countryandregionname\":\"New Zealand\",\n    \"internationaldomain\":\"NZ\",\n    \"countrycode\":\"64\"\n  },\n  {\n    \"countryandregionname\":\"Vietnam\",\n    \"internationaldomain\":\"VNM\",\n    \"countrycode\":\"84\"\n  },\n  {\n    \"countryandregionname\":\"New Caledonia\",\n    \"internationaldomain\":\"NC\",\n    \"countrycode\":\"687\"\n  },\n  {\n    \"countryandregionname\":\"Bhutan\",\n    \"internationaldomain\":\"BT\",\n    \"countrycode\":\"975\"\n  },\n  {\n    \"countryandregionname\":\"Marshall\",\n    \"internationaldomain\":\"MH\",\n    \"countrycode\":\"692\"\n  },\n  {\n    \"countryandregionname\":\"Mongolia\",\n    \"internationaldomain\":\"MN\",\n    \"countrycode\":\"976\"\n  },\n  {\n    \"countryandregionname\":\"Taiwan\",\n    \"internationaldomain\":\"TW\",\n    \"countrycode\":\"886\"\n  },\n  {\n    \"countryandregionname\":\"North Korea\",\n    \"internationaldomain\":\"KP\",\n    \"countrycode\":\"850\"\n  },\n  {\n    \"countryandregionname\":\"South Korea\",\n    \"internationaldomain\":\"KR\",\n    \"countrycode\":\"82\"\n  },\n  {\n    \"countryandregionname\":\"Chile\",\n    \"internationaldomain\":\"CL\",\n    \"countrycode\":\"56\"\n  },\n  {\n    \"countryandregionname\":\"Uruguay\",\n    \"internationaldomain\":\"UY\",\n    \"countrycode\":\"598\"\n  },\n  {\n    \"countryandregionname\":\"Columbia\",\n    \"internationaldomain\":\"CO\",\n    \"countrycode\":\"57\"\n  },\n  {\n    \"countryandregionname\":\"Dominican\",\n    \"internationaldomain\":\"DO\",\n    \"countrycode\":\"1890\"\n  },\n  {\n    \"countryandregionname\":\"Costa Rica\",\n    \"internationaldomain\":\"CR\",\n    \"countrycode\":\"506\"\n  },\n  {\n    \"countryandregionname\":\"Peru\",\n    \"internationaldomain\":\"PE\",\n    \"countrycode\":\"51\"\n  },\n  {\n    \"countryandregionname\":\"America\",\n    \"internationaldomain\":\"US\",\n    \"countrycode\":\"1\"\n  },\n  {\n    \"countryandregionname\":\"Ecuador\",\n    \"internationaldomain\":\"EC\",\n    \"countrycode\":\"593\"\n  },\n  {\n    \"countryandregionname\":\"Paraguay\",\n    \"internationaldomain\":\"PY\",\n    \"countrycode\":\"595\"\n  },\n  {\n    \"countryandregionname\":\"Brazil\",\n    \"internationaldomain\":\"BR\",\n    \"countrycode\":\"55\"\n  },\n  {\n    \"countryandregionname\":\"Bolivia\",\n    \"internationaldomain\":\"BOL\",\n    \"countrycode\":\"591\"\n  },\n  {\n    \"countryandregionname\":\"Bahamas\",\n    \"internationaldomain\":\"BS\",\n    \"countrycode\":\"1242\"\n  },\n  {\n    \"countryandregionname\":\"Panama\",\n    \"internationaldomain\":\"PA\",\n    \"countrycode\":\"507\"\n  },\n  {\n    \"countryandregionname\":\"Curacao\",\n    \"internationaldomain\":\"CW\",\n    \"countrycode\":\"599\"\n  },\n  {\n    \"countryandregionname\":\"Mexico\",\n    \"internationaldomain\":\"MX\",\n    \"countrycode\":\"52\"\n  },\n  {\n    \"countryandregionname\":\"Canada\",\n    \"internationaldomain\":\"CA\",\n    \"countrycode\":\"1\"\n  },\n  {\n    \"countryandregionname\":\"Guatemala\",\n    \"internationaldomain\":\"GT\",\n    \"countrycode\":\"502\"\n  },\n  {\n    \"countryandregionname\":\"Jamaica\",\n    \"internationaldomain\":\"JM\",\n    \"countrycode\":\"1876\"\n  },\n  {\n    \"countryandregionname\":\"Saudi Arabia\",\n    \"internationaldomain\":\"SA\",\n    \"countrycode\":\"966\"\n  },\n  {\n    \"countryandregionname\":\"United Arab Emirates\",\n    \"internationaldomain\":\"AE\",\n    \"countrycode\":\"971\"\n  },\n  {\n    \"countryandregionname\":\"Egypt\",\n    \"internationaldomain\":\"EG\",\n    \"countrycode\":\"20\"\n  },\n  {\n    \"countryandregionname\":\"Ghana\",\n    \"internationaldomain\":\"GH\",\n    \"countrycode\":\"233\"\n  },\n  {\n    \"countryandregionname\":\"Qatar\",\n    \"internationaldomain\":\"QA\",\n    \"countrycode\":\"974\"\n  },\n  {\n    \"countryandregionname\":\"Morocco\",\n    \"internationaldomain\":\"MA\",\n    \"countrycode\":\"212\"\n  },\n  {\n    \"countryandregionname\":\"Bahrain\",\n    \"internationaldomain\":\"BH\",\n    \"countrycode\":\"973\"\n  },\n  {\n    \"countryandregionname\":\"Oman\",\n    \"internationaldomain\":\"OM\",\n    \"countrycode\":\"968\"\n  },\n  {\n    \"countryandregionname\":\"Kuwait\",\n    \"internationaldomain\":\"KW\",\n    \"countrycode\":\"965\"\n  },\n  {\n    \"countryandregionname\":\"Cape Verde\",\n    \"internationaldomain\":\"CV\",\n    \"countrycode\":\"238\"\n  },\n  {\n    \"countryandregionname\":\"Tunisia\",\n    \"internationaldomain\":\"TN\",\n    \"countrycode\":\"216\"\n  },\n  {\n    \"countryandregionname\":\"Lebanon\",\n    \"internationaldomain\":\"LB\",\n    \"countrycode\":\"961\"\n  },\n  {\n    \"countryandregionname\":\"Jordan\",\n    \"internationaldomain\":\"JO\",\n    \"countrycode\":\"962\"\n  },\n  {\n    \"countryandregionname\":\"South Africa\",\n    \"internationaldomain\":\"ZA\",\n    \"countrycode\":\"27\"\n  },\n  {\n    \"countryandregionname\":\"Reunion\",\n    \"internationaldomain\":\"RE\",\n    \"countrycode\":\"262\"\n  },\n  {\n    \"countryandregionname\":\"Zimbabwe\",\n    \"internationaldomain\":\"ZW\",\n    \"countrycode\":\"263\"\n  },\n  {\n    \"countryandregionname\":\"Zambia\",\n    \"internationaldomain\":\"ZM\",\n    \"countrycode\":\"260\"\n  },\n  {\n    \"countryandregionname\":\"Mauritius\",\n    \"internationaldomain\":\"MU\",\n    \"countrycode\":\"230\"\n  },\n  {\n    \"countryandregionname\":\"Madagascar\",\n    \"internationaldomain\":\"MG\",\n    \"countrycode\":\"261\"\n  },\n  {\n    \"countryandregionname\":\"Angola\",\n    \"internationaldomain\":\"AO\",\n    \"countrycode\":\"244\"\n  },\n  {\n    \"countryandregionname\":\"Norway\",\n    \"internationaldomain\":\"NO\",\n    \"countrycode\":\"47\"\n  },\n  {\n    \"countryandregionname\":\"Denmark\",\n    \"internationaldomain\":\"DK\",\n    \"countrycode\":\"45\"\n  },\n  {\n    \"countryandregionname\":\"Netherlands\",\n    \"internationaldomain\":\"NL\",\n    \"countrycode\":\"31\"\n  },\n  {\n    \"countryandregionname\":\"Britain\",\n    \"internationaldomain\":\"GB\",\n    \"countrycode\":\"44\"\n  },\n  {\n    \"countryandregionname\":\"Portugal\",\n    \"internationaldomain\":\"PT\",\n    \"countrycode\":\"351\"\n  },\n  {\n    \"countryandregionname\":\"Belgium\",\n    \"internationaldomain\":\"BE\",\n    \"countrycode\":\"32\"\n  },\n  {\n    \"countryandregionname\":\"Sweden\",\n    \"internationaldomain\":\"SE\",\n    \"countrycode\":\"46\"\n  },\n  {\n    \"countryandregionname\":\"Switzerland\",\n    \"internationaldomain\":\"CH\",\n    \"countrycode\":\"41\"\n  },\n  {\n    \"countryandregionname\":\"Austria\",\n    \"internationaldomain\":\"AT\",\n    \"countrycode\":\"43\"\n  },\n  {\n    \"countryandregionname\":\"Italy\",\n    \"internationaldomain\":\"IT\",\n    \"countrycode\":\"39\"\n  },\n  {\n    \"countryandregionname\":\"Spain\",\n    \"internationaldomain\":\"ES\",\n    \"countrycode\":\"34\"\n  },\n  {\n    \"countryandregionname\":\"Finland\",\n    \"internationaldomain\":\"FI\",\n    \"countrycode\":\"358\"\n  },\n  {\n    \"countryandregionname\":\"Ireland\",\n    \"internationaldomain\":\"IE\",\n    \"countrycode\":\"353\"\n  },\n  {\n    \"countryandregionname\":\"Greece\",\n    \"internationaldomain\":\"GR\",\n    \"countrycode\":\"30\"\n  },\n  {\n    \"countryandregionname\":\"Germany\",\n    \"internationaldomain\":\"DE\",\n    \"countrycode\":\"49\"\n  },\n  {\n    \"countryandregionname\":\"France\",\n    \"internationaldomain\":\"FR\",\n    \"countrycode\":\"33\"\n  },\n  {\n    \"countryandregionname\":\"Bulgaria\",\n    \"internationaldomain\":\"BG\",\n    \"countrycode\":\"359\"\n  },\n  {\n    \"countryandregionname\":\"Cyprus\",\n    \"internationaldomain\":\"CY\",\n    \"countrycode\":\"357\"\n  },\n  {\n    \"countryandregionname\":\"Croatia\",\n    \"internationaldomain\":\"HR\",\n    \"countrycode\":\"385\"\n  },\n  {\n    \"countryandregionname\":\"Czech\",\n    \"internationaldomain\":\"CZ\",\n    \"countrycode\":\"420\"\n  },\n  {\n    \"countryandregionname\":\"Estonia\",\n    \"internationaldomain\":\"EE\",\n    \"countrycode\":\"372\"\n  },\n  {\n    \"countryandregionname\":\"Hungary\",\n    \"internationaldomain\":\"HU\",\n    \"countrycode\":\"36\"\n  },\n  {\n    \"countryandregionname\":\"Latvia\",\n    \"internationaldomain\":\"LV\",\n    \"countrycode\":\"371\"\n  },\n  {\n    \"countryandregionname\":\"Lithuania\",\n    \"internationaldomain\":\"LT\",\n    \"countrycode\":\"370\"\n  },\n  {\n    \"countryandregionname\":\"Luxembourg\",\n    \"internationaldomain\":\"LU\",\n    \"countrycode\":\"352\"\n  },\n  {\n    \"countryandregionname\":\"Malta\",\n    \"internationaldomain\":\"MT\",\n    \"countrycode\":\"356\"\n  },\n  {\n    \"countryandregionname\":\"poland\",\n    \"internationaldomain\":\"PL\",\n    \"countrycode\":\"48\"\n  },\n  {\n    \"countryandregionname\":\"Romania\",\n    \"internationaldomain\":\"RO\",\n    \"countrycode\":\"40\"\n  },\n  {\n    \"countryandregionname\":\"Slovakia\",\n    \"internationaldomain\":\"SK\",\n    \"countrycode\":\"421\"\n  },\n  {\n    \"countryandregionname\":\"Slovenia\",\n    \"internationaldomain\":\"SI\",\n    \"countrycode\":\"386\"\n  },\n  {\n    \"countryandregionname\":\"Iceland\",\n    \"internationaldomain\":\"IS\",\n    \"countrycode\":\"354\"\n  },\n  {\n    \"countryandregionname\":\"Liechtenstein\",\n    \"internationaldomain\":\"LI\",\n    \"countrycode\":\"423\"\n  },\n  {\n    \"countryandregionname\":\"Turkey\",\n    \"internationaldomain\":\"TR\",\n    \"countrycode\":\"90\"\n  },\n  {\n    \"countryandregionname\":\"Ukraine\",\n    \"internationaldomain\":\"UA\",\n    \"countrycode\":\"380\"\n  },\n  {\n    \"countryandregionname\":\"Israel\",\n    \"internationaldomain\":\"IL\",\n    \"countrycode\":\"972\"\n  },\n  {\n    \"countryandregionname\":\"Montenegro\",\n    \"internationaldomain\":\"ME\",\n    \"countrycode\":\"382\"\n  },\n  {\n    \"countryandregionname\":\"Serbia\",\n    \"internationaldomain\":\"RS\",\n    \"countrycode\":\"381\"\n  },\n  {\n    \"countryandregionname\":\"Bosnia and Herzegovina\",\n    \"internationaldomain\":\"BA\",\n    \"countrycode\":\"387\"\n  },\n  {\n    \"countryandregionname\":\"North Macedonia\",\n    \"internationaldomain\":\"MK\",\n    \"countrycode\":\"389\"\n  },\n  {\n    \"countryandregionname\":\"Albania\",\n    \"internationaldomain\":\"AL\",\n    \"countrycode\":\"355\"\n  },\n  {\n    \"countryandregionname\":\"Armenia\",\n    \"internationaldomain\":\"AM\",\n    \"countrycode\":\"374\"\n  },\n  {\n    \"countryandregionname\":\"Azerbaijan\",\n    \"internationaldomain\":\"AZ\",\n    \"countrycode\":\"994\"\n  },\n  {\n    \"countryandregionname\":\"Belarus\",\n    \"internationaldomain\":\"BY\",\n    \"countrycode\":\"375\"\n  },\n  {\n    \"countryandregionname\":\"Moldova\",\n    \"internationaldomain\":\"MD\",\n    \"countrycode\":\"373\"\n  },\n  {\n    \"countryandregionname\":\"Kazakhstan\",\n    \"internationaldomain\":\"KZ\",\n    \"countrycode\":\"997\"\n  },\n  {\n    \"countryandregionname\":\"Kyrgyzstan\",\n    \"internationaldomain\":\"KG\",\n    \"countrycode\":\"996\"\n  },\n  {\n    \"countryandregionname\":\"Tajikistan\",\n    \"internationaldomain\":\"TJ\",\n    \"countrycode\":\"992\"\n  },\n  {\n    \"countryandregionname\":\"Uzbekistan\",\n    \"internationaldomain\":\"UZ\",\n    \"countrycode\":\"998\"\n  },\n  {\n    \"countryandregionname\":\"Monaco\",\n    \"internationaldomain\":\"MC\",\n    \"countrycode\":\"377\"\n  },\n  {\n    \"countryandregionname\":\"Russia\",\n    \"internationaldomain\":\"RU\",\n    \"countrycode\":\"7\"\n  },\n  {\n    \"countryandregionname\":\"Vatican\",\n    \"internationaldomain\":\"VA\",\n    \"countrycode\":\"379\"\n  }\n]";
    private static final String TAG = "NEU_Common8155";
    private static volatile Common mInstance;
    private CarAudioManager carAudioManager = null;
    static List<Integer> rightDiverAry = Arrays.asList(65, 852, 853, 977, 61, 66, 81, 94, 92, 91, 60, 62, 64, 975, 1242, 1876, 27, 263, 260, 230, 44, 353, 357, 356);
    static List<String> rtlLocale = Arrays.asList("iw");
    public static String kikaPackageName = "com.iqqijni.car.keyboard";
    public static String sougouPackageName = "com.sogou.inputmethod.iot";
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;
    public static String[] dirtyWordsChinese = {"三八", "下三烂", "下贱", "不要脸", "什么鸟", "他妈的", "伦理片", "你个傻比", "你个狗屁", "你丫挺的", "你丫欠揍", "你是个混球", "你老母", "傻逼", "公共汽车", "关你屁事", "兽性", "刹笔", "卵", "发情", "发疯", "变态", "叼", "呻吟", "啪啪啪", "太监", "妈B", "妈的", "妈逼", "妓女", "姘头", "娘炮", "婊子", "小三", "屄", "屌", "屌丝", "屎", "干", "恶棍", "情色", "我靠", "扯蛋", "找死", "操", "放屁", "放荡", "无用", "日批", "昏蛋", "智障", "杂种", "死", "死三八", "母夜叉", "母老虎", "水货", "淫秽", "混蛋", "混账", "烂货", "烂逼", "煞笔", "煞逼", "犯贱", "狐狸精", "狗仔子", "狗娘养的", "狗屁", "狗屎堆", "狗日", "狗杂种", "狗狼养的", "狗腿子", "王八", "痴呆", "白嫩", "白痴", "瞎", "神经病", "禽兽", "窝囊废", "笨蛋", "粉嫩", "群殴", "蠢", "裸聊", "讨厌", "贱", "贱人", "走狗", "野鸡", "风骚", "饭桶", "马子", "马屁精", "骂", "骚货", "鸟人", "鸟话", "鸡", "鸡8", "鸭", "龟儿子", "蠢猪", "老二", "怪物", "屁眼", "x你x", "耍大牌", "书呆子", "流氓", "蠢货", "傻瓜", "滚", "去死", "闭嘴", "胡说", "他X的", "有屁用", "有毛用", "有卵用", "丧心病狂", "嗝屁", "屁股眼", "九游", "鬼三八", "男男", "他娘", "妈个巴子", "你他妈的", "卖淫", "他马的", "低三下四", "你不是人", "你不是东西", "二大爷的", "你去死吧", "你他妈有病", "没个卵用", "狗娘生的", "你傻逼", "精虫", "屁话精", "卵毛", "你奶奶的", "你他奶的", "你妈", "你妈的", "屁股眼儿", "你娘", "你娘的", "畜牲", "牲口", "深井冰", "我日你", "鬼东西", "狗东西", "鸡王", "鸭王", "你有病", "你疯了吗", "脑子进水", "二逼", "B货", "废物", "你真让我恶心", "滚你妹", "妈批", "你基佬", "你马的", "倒蛋", "死全家", "死人", "死贱人", "死货", "贱货", "死基佬", "同性恋", "同性怪物", "屌哥", "屌姐", "屌丝男", "屌丝女", "畜生", "狗娘", "见鬼", "我他妈见鬼了", "乡巴佬", "土狗", "棒槌", "方脑壳", "捣鸡毛", "脑子瓦特了", "弄西特", "龟儿", "赌狗", "死变态", "瘟神", "草", "靠", "白目", "瞎子", "神棍", "屁眼儿", "吃屎", "该死", "该死的", "浑球", "二奶", "包二奶", "2B", "牛逼", "牛B", "牛叉", "无耻", "死定了", "去死吧", "做贼", "低俗", "艳俗", "娼妇", "你妈逼", "逼人", "滚犊子", "滚蛋", "滚蛋吧", "草泥马", "草尼玛", "操你妈", "你他娘的", "你他妹的", "龌龊", "沙雕", "辣鸡", "菜狗", "菜鸡", "二百五", "吃屎吧", "吃屎吧你", "不要逼脸", "碧池", "卑鄙下贱", "卑鄙", "喷粪", "猪狗不如", "狗杂碎", "败类", "下流", "下三滥", "烂狗屎", "天杀的", "傻屌", "傻叼", "去你的", "去你娘的", "贱种", "孬种", "孬货", "鸡巴", "烂人", "基佬", "鸡佬", "男同", "女同", "蕾丝女"};
    public static String[] dirtyWordsEng = {"arse hole", "asshole", "bastard", "bitch", "child fucker", "clit licker", "cock", "cock sucker", "corp whore", "crackwhore", "cunt", "dammit", "damned", "darn", "dick", "dick head", "dickdipper", "dickflipper", "dickhead", "fuck", "fuck ass", "fuck bitch", "fucker", "god damn", "hell", "holy shit", "hoor", "hore", "knob", "motherfucker", "naked", "piece of shit", "pigfucker", "pimpis", "piss", "playboy", "pleasure chest", "pole smoker", "pollock", "ponyplay", "poof", "poon", "porn", "prick", "punta", "pusse", "pussi", "queaf", "queerbait", "queerhole", "quim", "raghead", "raging boner", "rape", "raping", "retard", "rubbish", "sadism", "sadist", "scat", "schlong", "scroat", "scrote", "semen", "sex", "shag", "shagger", "shaved beaver", "shibari", "shit", "slanteye", "slut", "slutbag", "sluts", "smeg", "smegma", "smut", "tainted love", "tard", "taste my", "tea bagging", "teets", "twink", "twinkie", "two girls one cup", "twunt", "twunter", "vibrator", "violet wand", "vjayjay", "vorarephilia", "voyeur", "vulva", "wank", "whore", "you arse hole", "you ass hole", "you bastard", "you bitch", "you cunt", "you dick", "you dick head", "you fuck", "you fucker", "you knob", "you motherfucker", "you prick", "you shit", "you slut", "you son of a bitch", "you wanker", "you whore", "stupid jerk", "stupid idlot", "freak", "damn you", "fuck you", "poser", "nerd", "son of bitch", "suck for you SB", "playing with fire", "pervert", "stupid", "idiot", "get out", "go to hell", "shut up", "bullshit", "god damn it", "SOB", "son of a bitch", "arse", "arsehole", "as useful as tits on a bull", "balls", "beaver", "beef curtains", "bell", "bellend", "bent", "berk", "bint", "blighter", "blimey", "blimey o'reilly", "bloodclaat", "bloody", "bloody hell", "blooming", "bollocks", "bonk", "bugger", "bugger me", "bugger off", "built like a brick shit house", "bukkake", "cack", "cad", "chav", "cheese eating surrender monkey", "choad", "chuffer", "clunge", "cobblers", "cock cheese", "cock jockey", "cock up", "cocksucker", "cockwomble", "codger", "cor blimey", "corey", "cow", "crap", "crikey", "daft", "daft cow", "damn", "did he bollocks!", "did i fuck as like!", "dildo", "dodgy", "duffer", "fanny", "feck", "flaps", "fuck me sideways!", "fucking cunt", "fucktard", "gash", "ginger", "git", "gob shite", "goddam", "gorblimey", "gordon bennett", "gormless", "he’s a knob", "hobknocker", "I'd rather snort my own cum", "jesus christ", "jizz", "knobber", "knobend", "knobhead", "ligger", "like fucking a dying man's handshake", "mad as a hatter", "manky", "minge", "minger", "minging", "munter", "muppet", "naff", "nitwit", "nonce", "numpty", "nutter", "off their rocker", "pillock", "pish", "piss off", "piss flaps", "pissed", "pissed off", "play the five fingered flute", "plonker", "ponce", "pouf", "poxy", "prat", "prickteaser", "punani", "punny", "pussy", "randy", "rapey", "rat arsed", "rotter", "scrubber", "shite", "shitfaced", "skank", "slag", "slapper", "snatch", "sod", "sod off", "spunk", "stick it up your arse!", "swine", "taking the piss", "tart", "tits", "toff", "tosser", "trollop", "tuss", "twat", "twonk", "u fukin wanker", "wally", "wanker", "wankstain", "wazzack"};
    public static String[] dirtyWordsPtbr = {"aleijada", "aleijado", "anus", "arrombado", "baba ovo", "bagos", "baianada", "baitola", "besta", "bicha", "bisca", "biscate", "bixa", "boçal", "boceta", "bocó", "boiola", "bokete", "boquete", "bosseta", "bunda", "busseta", "caceta", "caga", "cagado", "cagão", "cagar", "canalha", "casseta", "cassete", "checheca", "chereca", "chifruda", "chochota", "cona", "cretina", "crica", "crioulo", "cú", "cuzuda", "cuzudo", "débil", "debilóide", "ejacular", "esclerosado", "escrota", "escroto", "foda", "fodão", "fodendo", "foder", "fodida", "fudida", "fufa", "gay", "gozar", "lazarento", "leprosa", "leproso", "louco", "maneta", "masturba", "meter", "mongol", "mongoloide", "otária", "pau", "pedófilo", "pênis", "pica", "picão", "pinar", "piroca", "piru", "prega", "prostituição", "prostituir", "prostituta", "punheta", "putas", "puto", "rabo", "retardada", "retardado", "rola", "siririca", "suicídio", "tetas", "traveco", "trolha", "vadia", "vagabunda", "vagabundo", "vagal", "vagina", "veada", "veadão", "veado", "viada", "viadagem", "viadão", "viado", "xana", "xaninha", "xavasca", "xerereca", "xexeca", "babaca", "bimbo", "bosta", "brioco", "bronha", "buceta", "cacete", "caralha", "caralho", "cretino", "cu", "cuzão", "Desgraçado", "Filha da mãe", "Filho da puta", "fode", "fodeu", "fodido", "fudido", "Idiota", "merda", "otário", "Panaca", "pila", "piroco", "porra", "prostitutas", "puta", "putos", "Safado", "Vai se foder", "xibiu", "xibumba", "xochota", "xota", "xoxota", "Vaca", "Cabrão", "Imbecil", "Pau no cu", "Piranha", "Míserável", "Gado", "Foda se", "Lixo", "Peste", "Desaforado", "Chato", "Feio", "Dedo no cu", "Relação sexual", "Vai tomar no cu", "Bastardo", "Palhaço", "Enfezado", "Cagalhão", "Maluco", "Fuleiro", "Merdeiro", "Cafajeste", "Imoral", "Sem vergonha", "Desculpado", "Fossa", "Arrombamento", "Rato", "Puto da vida", "Merda seca", "Chupeta", "Drogado", "Chupar rola", "Muleque", "Cabeça oca", "Fedorento", "Desgraça", "Cachorrão", "Cu de burro", "Caiu na bosta", "Tapa buraco", "Pau de arara", "Pipoca", "Bunda mole", "Cabelo de cu", "Noia", "Desmandado", "Fraco", "Mamonha", "Abestado", "Churrasquinho", "Favelado", "Cafona", "Esculacho", "Rasteiro", "Bolado", "Traíra", "Jogado fora", "Xexelento", "Cidadão de bem", "Antipático", "Vadio", "Porrada", "Cavalo", "Desentendido", "Bicheira", "Ranhento", "Mercearia", "Franguinho", "Capeta", "Quebra galho"};

    public static Context createLanguageContext(Context context) {
        return context;
    }

    public static Common get() {
        if (mInstance == null) {
            synchronized (Common.class) {
                if (mInstance == null) {
                    mInstance = new Common();
                }
            }
        }
        return mInstance;
    }

    private Common() {
        Car.createCar(BydVoiceSettingApplication.getContext(), null, 5000L, new Car.CarServiceLifecycleListener() { // from class: com.byd.vrsettings.utils.Common.1
            @Override // android.car.Car.CarServiceLifecycleListener
            public void onLifecycleChanged(Car car, boolean z) {
                Common.this.carAudioManager = (CarAudioManager) car.getCarManager("audio");
            }
        });
    }

    public static int GetCurrentLanguageForLayout() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        if (string == null) {
            RLog.d(TAG, "GetCurrentLanguageForLayout KEY_LANGUAGE is null");
            string = BuildConfig.FLAVOR;
        }
        RLog.i("NEU_PropertiesUtil", "GetCurrentLanguageForLayout: " + string);
        if (!TextUtils.isEmpty(string) && string.contains("cmn-CHN")) {
            return (isApkExist(kikaPackageName) || isApkExist(sougouPackageName)) ? 3 : 2;
        }
        return 1;
    }

    public static boolean isApkExist(String str) {
        try {
            Log.d(TAG, "isApkExist: " + BydVoiceSettingApplication.getContext().getPackageManager().getApplicationInfo(str, 0).packageName);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d(TAG, "isApkExist return false " + str);
            return false;
        }
    }

    public int getTTsVolume() {
        try {
            int volumeGroupIdForUsage = this.carAudioManager.getVolumeGroupIdForUsage(16);
            RLog.i(TAG, "getTTsVolume groupId=" + volumeGroupIdForUsage);
            return this.carAudioManager.getGroupVolume(volumeGroupIdForUsage);
        } catch (Exception e) {
            RLog.i(TAG, "getTTsVolume groupId= error" + e);
            return 5;
        }
    }

    public void registerCarVolumeCallback(CarAudioManager.CarVolumeCallback carVolumeCallback) {
        try {
            this.carAudioManager.registerCarVolumeCallback(carVolumeCallback);
        } catch (Exception e) {
            RLog.i(TAG, "registerCarVolumeCallback = error" + e);
        }
    }

    public void unregisterCarVolumeCallback(CarAudioManager.CarVolumeCallback carVolumeCallback) {
        try {
            this.carAudioManager.unregisterCarVolumeCallback(carVolumeCallback);
        } catch (Exception e) {
            RLog.i(TAG, "unregisterCarVolumeCallback = error" + e);
        }
    }

    public int getTTsMixVolume() {
        try {
            int volumeGroupIdForUsage = this.carAudioManager.getVolumeGroupIdForUsage(16);
            RLog.i(TAG, "getTTsVolume groupId=" + volumeGroupIdForUsage);
            return this.carAudioManager.getGroupMinVolume(volumeGroupIdForUsage);
        } catch (Exception e) {
            RLog.i(TAG, "getTTsMixVolume groupId= error" + e);
            return 5;
        }
    }

    public int getTTsMaxVolume() {
        try {
            int volumeGroupIdForUsage = this.carAudioManager.getVolumeGroupIdForUsage(16);
            RLog.i(TAG, "getTTsVolume groupId=" + volumeGroupIdForUsage);
            return this.carAudioManager.getGroupMaxVolume(volumeGroupIdForUsage);
        } catch (Exception e) {
            RLog.i(TAG, "getTTsVolume groupId= error" + e);
            return 24;
        }
    }

    public void TtsVolumeTo(int i) {
        try {
            int volumeGroupIdForUsage = this.carAudioManager.getVolumeGroupIdForUsage(16);
            RLog.i(TAG, "setVolume groupId=" + volumeGroupIdForUsage + ",value=" + i);
            this.carAudioManager.setGroupVolume(volumeGroupIdForUsage, i, 0);
        } catch (Exception e) {
            RLog.i(TAG, "getTTsVolume groupId= error" + e);
        }
    }

    public static String getSystemLanguage(Context context) {
        String language = context.getResources().getConfiguration().locale.getLanguage();
        RLog.i("NEU_Common", "language =" + language);
        return language.endsWith("es") ? "ES" : language.endsWith("nb") ? "NB" : language.endsWith("pt") ? "PT" : language;
    }

    public static void hookWebView() {
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                RLog.i("NEU_Common", "sProviderInstance isn't null");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
            declaredField2.setAccessible(true);
            String str = (String) declaredField2.get(null);
            if (str == null) {
                str = "create";
            }
            Method method = cls2.getMethod(str, cls3);
            if (method != null) {
                objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
            }
            if (objInvoke != null) {
                declaredField.set("sProviderInstance", objInvoke);
                RLog.i("NEU_Common", "Hook success!");
            } else {
                RLog.i("NEU_Common", "Hook failed!");
            }
        } catch (Throwable th) {
            RLog.d("NEU_Common", th);
        }
    }

    public static boolean isRightDriver() {
        int i = SystemPropertiesUtils.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        RLog.e("NEU_Common", "isRightDriver:" + (i == 1));
        return i == 1;
    }

    public static boolean isRtl(Context context) {
        if (context == null || context.getResources().getConfiguration().getLocales() == null || context.getResources().getConfiguration().getLocales().get(0) == null || context.getResources().getConfiguration().getLocales().get(0).getLanguage() == null) {
            RLog.d(TAG, "isRtl context == null");
            return false;
        }
        final String language = context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        boolean zAnyMatch = rtlLocale.stream().anyMatch(new Predicate() { // from class: com.byd.vrsettings.utils.Common$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((String) obj).equals(language);
            }
        });
        RLog.d(TAG, "country:" + language + " isRtl is " + zAnyMatch);
        return zAnyMatch;
    }

    public static String get(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            RLog.e("NEU_Common", "read SystemProperties error", e);
            return null;
        }
    }

    public static void savePrivacyPolicyModel(PrivacyPolicyModel privacyPolicyModel) {
        if (privacyPolicyModel == null) {
            return;
        }
        RLog.i("NEU_Common", "savePrivacyPolicyModel: time =" + privacyPolicyModel.getTime() + "isAgree" + privacyPolicyModel.getIsAgree());
        List<PrivacyPolicyModel> privacyPolicyModel2 = getPrivacyPolicyModel();
        ArrayList arrayList = new ArrayList();
        if (privacyPolicyModel2.size() == 99) {
            for (int i = 1; i < 99; i++) {
                arrayList.add(privacyPolicyModel2.get(i));
            }
        } else {
            for (int i2 = 0; i2 < privacyPolicyModel2.size(); i2++) {
                arrayList.add(privacyPolicyModel2.get(i2));
            }
        }
        arrayList.add(privacyPolicyModel);
        String json = new Gson().toJson(arrayList);
        Environment.getExternalStorageState();
        String absolutePath = BydVoiceSettingApplication.getContext().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath + "/PrivacyPolicy/");
        RLog.i("NEU_Common", "savePath = " + absolutePath + "/PrivacyPolicy/");
        try {
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "log.json"));
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            RLog.i("NEU_Common", "write ok ");
        } catch (Exception e) {
            e.printStackTrace();
            RLog.i("NEU_Common", "write error ");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.byd.vrsettings.utils.PrivacyPolicyModel> getPrivacyPolicyModel() {
        /*
            com.byd.vrsettings.BydVoiceSettingApplication r0 = com.byd.vrsettings.BydVoiceSettingApplication.getContext()
            java.io.File r0 = r0.getFilesDir()
            java.lang.String r0 = r0.getAbsolutePath()
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = "/PrivacyPolicy/"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> L45
            java.lang.String r3 = "log.json"
            r2.<init>(r1, r3)     // Catch: java.lang.Exception -> L45
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L45
            r1.<init>(r2)     // Catch: java.lang.Exception -> L45
        L35:
            int r2 = r1.read()     // Catch: java.lang.Exception -> L45
            r3 = -1
            if (r2 == r3) goto L41
            char r2 = (char) r2     // Catch: java.lang.Exception -> L45
            r0.append(r2)     // Catch: java.lang.Exception -> L45
            goto L35
        L41:
            r1.close()     // Catch: java.lang.Exception -> L45
            goto L49
        L45:
            r1 = move-exception
            r1.printStackTrace()
        L49:
            java.lang.String r1 = r0.toString()
            int r1 = r1.length()
            if (r1 <= 0) goto L6c
            com.google.gson.Gson r1 = new com.google.gson.Gson
            r1.<init>()
            java.lang.String r0 = r0.toString()
            com.byd.vrsettings.utils.Common$2 r2 = new com.byd.vrsettings.utils.Common$2
            r2.<init>()
            java.lang.reflect.Type r2 = r2.getType()
            java.lang.Object r0 = r1.fromJson(r0, r2)
            java.util.List r0 = (java.util.List) r0
            goto L71
        L6c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L71:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getPrivacyPolicyModelCount = "
            r1.<init>(r2)
            int r2 = r0.size()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "NEU_Common"
            com.byd.vrsettings.utils.RLog.i(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrsettings.utils.Common.getPrivacyPolicyModel():java.util.List");
    }

    public static boolean isFastDoubleClick(int i) {
        return isFastDoubleClick(i, DIFF);
    }

    public static boolean isFastDoubleClick(int i, long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = lastClickTime;
        long j3 = jElapsedRealtime - j2;
        if (lastButtonId == i && j2 > 0 && j3 < j) {
            RLog.d("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = jElapsedRealtime;
        lastButtonId = i;
        return false;
    }

    public static String getVehicleType() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, "ro.vehicle.type");
        } catch (Exception e) {
            RLog.e("NEU_Common", "read SystemProperties error", e);
            return null;
        }
    }

    public static ArrayList<View> getCustomPagerView(List<Integer> list, List<CustomViewpagerView> list2) {
        int size = list.size() % 4;
        int size2 = list.size() / 4;
        if (size != 0) {
            size2++;
        }
        ArrayList<View> arrayList = new ArrayList<>();
        int i = 0;
        while (i < size2) {
            CustomViewpagerView customViewpagerView = new CustomViewpagerView(BydVoiceSettingApplication.getContext());
            ArrayList<String> arrayList2 = new ArrayList<>();
            int i2 = i + 1;
            if (i2 == size2) {
                int i3 = 0;
                while (true) {
                    if (i3 < (size == 0 ? 4 : size)) {
                        arrayList2.add(BydVoiceSettingApplication.getContext().getResources().getString(list.get((i * 4) + i3).intValue()));
                        i3++;
                    }
                }
            } else {
                for (int i4 = 0; i4 < 4; i4++) {
                    arrayList2.add(BydVoiceSettingApplication.getContext().getResources().getString(list.get((i * 4) + i4).intValue()));
                }
            }
            customViewpagerView.setTitle(arrayList2);
            arrayList.add(customViewpagerView);
            list2.add(customViewpagerView);
            i = i2;
        }
        return arrayList;
    }

    public static ArrayList<View> getCustomPagerViewWithStrings(List<String> list, List<CustomViewpagerView> list2) {
        int size = list.size() % 4;
        int size2 = list.size() / 4;
        if (size != 0) {
            size2++;
        }
        ArrayList<View> arrayList = new ArrayList<>();
        int i = 0;
        while (i < size2) {
            CustomViewpagerView customViewpagerView = new CustomViewpagerView(BydVoiceSettingApplication.getContext());
            ArrayList<String> arrayList2 = new ArrayList<>();
            int i2 = i + 1;
            if (i2 == size2) {
                int i3 = 0;
                while (true) {
                    if (i3 < (size == 0 ? 4 : size)) {
                        arrayList2.add(list.get((i * 4) + i3));
                        i3++;
                    }
                }
            } else {
                for (int i4 = 0; i4 < 4; i4++) {
                    arrayList2.add(list.get((i * 4) + i4));
                }
            }
            customViewpagerView.setTitle(arrayList2);
            arrayList.add(customViewpagerView);
            list2.add(customViewpagerView);
            i = i2;
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0296, code lost:
    
        if (r0 == false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02a5, code lost:
    
        if (r4.equals("5") != false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02a7, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02e8, code lost:
    
        if (r0 != false) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0312, code lost:
    
        if (r0 == false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0104, code lost:
    
        if (r3 != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0169, code lost:
    
        if (r3 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01eb, code lost:
    
        if (r4.equals("4") != false) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<android.view.View> getCustomPagerViewWithMap(java.util.Map<java.lang.String, java.lang.String> r18, java.util.List<com.byd.vrsettings.utils.CustomViewpagerView> r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 905
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrsettings.utils.Common.getCustomPagerViewWithMap(java.util.Map, java.util.List, java.lang.String):java.util.ArrayList");
    }

    public static boolean isInstallWithKey(String str) {
        int i;
        RLog.i(TAG, "isInstallWithKey key =" + str);
        try {
            i = Settings.System.getInt(BydVoiceSettingApplication.getContext().getContentResolver(), str);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        RLog.i(TAG, str + " = " + i);
        return i == 1;
    }

    public static boolean ifSupportVehicle() {
        boolean zIsInstallWithKey = isInstallWithKey(Constant.KEY_EXIST_AC);
        boolean zIsInstallWithKey2 = isInstallWithKey(Constant.KEY_EXIST_FAN);
        boolean zIsInstallWithKey3 = isInstallWithKey(Constant.KEY_EXIST_LOOP);
        boolean zIsInstallWithKey4 = isInstallWithKey(Constant.KEY_EXIST_SPEED_LEVEL);
        boolean zIsInstallWithKey5 = isInstallWithKey(Constant.KEY_EXIST_AC_DEFROST);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallAC =" + zIsInstallWithKey + "，isInstallFAN =" + zIsInstallWithKey2 + "，isInstall_LOOP =" + zIsInstallWithKey3 + "，isInstallSPEED_LEVEL =" + zIsInstallWithKey4 + "，isInstall_AC_DEFROST =" + zIsInstallWithKey5);
        boolean zIsInstallWithKey6 = isInstallWithKey(Constant.KEY_EXIST_DRIVER_SEAT_HEATING);
        boolean zIsInstallWithKey7 = isInstallWithKey(Constant.KEY_EXIST_SEAT_VENTILATION);
        boolean zIsInstallWithKey8 = isInstallWithKey(Constant.KEY_EXIST_PASSENGER_SEAT_VENTILATION);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallHeating =" + zIsInstallWithKey6 + "，isInstallSeat =" + zIsInstallWithKey7 + "，isInstallPassengerSeat =" + zIsInstallWithKey8);
        boolean zIsInstallWithKey9 = isInstallWithKey(Constant.KEY_EXIST_ATMOSPHERE_LIGHT);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallLight =" + zIsInstallWithKey9);
        boolean zIsInstallWithKey10 = isInstallWithKey(Constant.KEY_EXIST_CAR_DORMANT_WINDOW);
        boolean zIsInstallWithKey11 = isInstallWithKey(Constant.KEY_EXIST_CAR_WINDOW);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallWindow =" + zIsInstallWithKey10 + "，isInstallCarWindow =" + zIsInstallWithKey11);
        boolean zIsInstallWithKey12 = isInstallWithKey(Constant.KEY_EXIST_SUNSHADE);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallSunshade =" + zIsInstallWithKey12);
        boolean zIsInstallWithKey13 = isInstallWithKey(Constant.KEY_EXIST_RAPID_PURIFICATION);
        boolean zIsInstallWithKey14 = isInstallWithKey(Constant.KEY_EXIST_READING_LIGHT);
        RLog.i(TAG, "车控 ifSupportVehicle  isInstallRAPID_PURIFICATION =" + zIsInstallWithKey13 + "，isInstallREADINGLIGHT =" + zIsInstallWithKey14);
        return zIsInstallWithKey || zIsInstallWithKey2 || zIsInstallWithKey3 || zIsInstallWithKey4 || zIsInstallWithKey5 || zIsInstallWithKey6 || zIsInstallWithKey7 || zIsInstallWithKey8 || zIsInstallWithKey9 || zIsInstallWithKey10 || zIsInstallWithKey11 || zIsInstallWithKey12 || zIsInstallWithKey13 || zIsInstallWithKey14;
    }

    public static int getNavigationBarHeight() {
        int identifier = BydVoiceSettingApplication.getContext().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? BydVoiceSettingApplication.getContext().getResources().getDimensionPixelSize(identifier) : 0;
        RLog.i("NEU_Common", "navigationHeight : " + dimensionPixelSize);
        return dimensionPixelSize;
    }

    public static String getSubWakeUpWord() {
        Bundle bundleCall;
        try {
            bundleCall = BydVoiceSettingApplication.getContext().getContentResolver().call(Uri.parse(WuwInfoBean.VRInfoProvider), WuwInfoBean.GetSubWakeUpWord, (String) null, (Bundle) null);
        } catch (Exception e) {
            RLog.d(TAG, "getSubWakeUpWord ERROR e==" + e.toString());
            bundleCall = null;
        }
        if (bundleCall != null && bundleCall.getString(WuwInfoBean.ReturnKey) != null && bundleCall.getString(WuwInfoBean.ReturnKey).equals("true")) {
            RLog.e(TAG, "GetSubWakeUpWord success:" + bundleCall.getString(WuwInfoBean.ValueKey).toString());
            return bundleCall.getString(WuwInfoBean.ValueKey).toString();
        }
        RLog.e(TAG, "GetSubWakeUpWord fail:");
        return null;
    }

    public static boolean setSubWakeUpWord(String str) {
        Bundle bundleCall = null;
        try {
            bundleCall = BydVoiceSettingApplication.getContext().getContentResolver().call(Uri.parse(WuwInfoBean.VRInfoProvider), WuwInfoBean.SetSubWakeUpWord, str, (Bundle) null);
        } catch (Exception e) {
            RLog.d(TAG, "setSubWakeUpWord ERROR e==" + e.toString());
        }
        if (bundleCall == null || bundleCall.getString(WuwInfoBean.ReturnKey) == null || !bundleCall.getString(WuwInfoBean.ReturnKey).equals("true")) {
            return false;
        }
        RLog.e(TAG, "SetSubWakeUpWord success:" + bundleCall.getString(WuwInfoBean.ReturnKey));
        return true;
    }

    public static boolean filterNonChineseCharacters(String str) {
        return str.equals(Pattern.compile("[^\\u4E00-\\u9FA5]", 66).matcher(str).replaceAll(BuildConfig.FLAVOR)) ^ true;
    }

    public static boolean filterNonhebISRCharacters(String str) {
        return str.equals(Pattern.compile("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM\\sיקראטוןםפשדגכעחלךףזסבהנמצתץ]", 66).matcher(str).replaceAll(BuildConfig.FLAVOR)) ^ true;
    }

    public static void DeleteLetterToast() {
        CustomToast.showToast(BydVoiceSettingApplication.getContext(), String.format(BydVoiceSettingApplication.getContext().getResources().getString(R.string.wakeup_favorite_wake_word_hint), OtaDataManager.getDPSCurrentLanguage()), PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    public static String getPrivacyPath(boolean z) {
        if (z) {
            return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_disclaimer_content_night);
        }
        return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_disclaimer_content);
    }

    public static String getUserExperiencePrivacyPath(boolean z) {
        if (z) {
            return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_disclaimer_content_UserExperienceImprovement_night);
        }
        return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_disclaimer_content_UserExperienceImprovement);
    }

    public static int GetCurrentLanguageForUserDefineWakeupWord() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        if (string == null) {
            RLog.d(TAG, "GetCurrentLanguageForWakeupWord KEY_LANGUAGE is null");
            string = BuildConfig.FLAVOR;
        }
        RLog.i("NEU_PropertiesUtil", "GetCurrentLanguageForWakeupWord: " + string);
        if (TextUtils.isEmpty(string)) {
            return 4;
        }
        if (string.contains(LanguageName.ENG) || string.contains("eng-SGP") || string.contains("eng-GBR") || string.contains("eng-XWW") || string.contains("eng-AUS")) {
            return 1;
        }
        if (string.contains("cmn-CHN")) {
            return 2;
        }
        return string.contains(LanguageName.POR) ? 3 : 4;
    }

    public static boolean FilterDirtyWord(String str, String[] strArr) {
        String lowerCase = str.toLowerCase();
        for (String str2 : strArr) {
            if (lowerCase.matches(".*" + ("\\b" + str2.toLowerCase() + "\\b") + ".*")) {
                Log.d(TAG, "filter dirtyWord===" + str2);
                return true;
            }
        }
        return false;
    }

    public static boolean FilterDirtyWordForChinese(String str, String[] strArr) {
        String lowerCase = str.toLowerCase();
        for (String str2 : strArr) {
            if (lowerCase.contains(str2.toLowerCase())) {
                Log.d(TAG, "filter dirtyWord===" + str2);
                return true;
            }
        }
        return false;
    }

    public static void jumpToY(MyWebView myWebView, int i) {
        RLog.d(TAG, "quxiao jump 20250401");
    }
}
