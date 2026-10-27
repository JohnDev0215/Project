package com.byd.music.protocol;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ProtocolResultCode {
    public static final int RESULT_CALLBACK_ERROR = 10002;
    public static final int RESULT_CAN_NO_GET_MUSIC_INFO = 11001;
    public static final int RESULT_FAIL = 10003;
    public static final Map<Integer, String> RESULT_MESSAGE;
    public static final int RESULT_NETWORK_ERROR = 10004;
    public static final int RESULT_NO_FOUND = 10005;
    public static final int RESULT_NO_NETWORK_NO_DATA = 10006;
    public static final int RESULT_NO_PLAYING_MUSIC = 11002;
    public static final int RESULT_OK = 10000;
    public static final int RESULT_PARAM_ERROR = 10001;
    public static final int RESULT_UNKNOWN_ERROR = 10007;
    public static final int RESULT_WITHOUT_PERMISSION = 10008;

    static {
        HashMap map = new HashMap();
        RESULT_MESSAGE = map;
        map.put(10000, "请求成功");
        RESULT_MESSAGE.put(10001, "非法参数");
        RESULT_MESSAGE.put(10004, "网络不畅");
        RESULT_MESSAGE.put(10006, "无网无数据");
        RESULT_MESSAGE.put(10007, "未知错误");
        RESULT_MESSAGE.put(10005, "未找到结果");
        RESULT_MESSAGE.put(10002, "回调异常");
        RESULT_MESSAGE.put(10003, "请求失败");
        RESULT_MESSAGE.put(10008, "无权限访问");
        RESULT_MESSAGE.put(Integer.valueOf(RESULT_CAN_NO_GET_MUSIC_INFO), "无法获取播放音乐信息");
        RESULT_MESSAGE.put(Integer.valueOf(RESULT_NO_PLAYING_MUSIC), "无播放曲目");
    }
}
