package com.byd.vrassistant.bean;

import com.byd.vrassistant.ui.R;
import com.google.common.base.Enums;

/* JADX INFO: loaded from: classes.dex */
public enum NoticeState implements NoticeImg {
    SEAT_OFF { // from class: com.byd.vrassistant.bean.NoticeState.1
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_off_light;
        }
    },
    SEAT_ROW_FRONT { // from class: com.byd.vrassistant.bean.NoticeState.2
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_light;
        }
    },
    SEAT_ROW_1_RIGHT { // from class: com.byd.vrassistant.bean.NoticeState.3
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_right_top_light;
        }
    },
    SEAT_ROW_1_LEFT { // from class: com.byd.vrassistant.bean.NoticeState.4
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_left_top_light;
        }
    },
    SEAT_ROW_2_RIGHT { // from class: com.byd.vrassistant.bean.NoticeState.5
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_right_bot_light;
        }
    },
    SEAT_ROW_2_LEFT { // from class: com.byd.vrassistant.bean.NoticeState.6
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_audio_left_bot_light;
        }
    },
    NET_OFF { // from class: com.byd.vrassistant.bean.NoticeState.7
        @Override // com.byd.vrassistant.bean.NoticeImg
        public int getDrawableRes() {
            return R.mipmap.icon_net_off_light;
        }
    };

    public static NoticeState getIfPresent(String str) {
        return (NoticeState) Enums.getIfPresent(NoticeState.class, str).orNull();
    }
}
