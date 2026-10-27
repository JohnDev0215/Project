package com.byd.vrassistant.bean;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.content.ContextCompat;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes.dex */
public enum ContentState implements Parcelable, VrText {
    INIT_MSG { // from class: com.byd.vrassistant.bean.ContentState.1
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_55_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_55_dark);
        }
    },
    HINT { // from class: com.byd.vrassistant.bean.ContentState.2
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_55_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_55_dark);
        }

        @Override // com.byd.vrassistant.bean.VrText
        public String getText(Context context, VrContent vrContent) {
            return context.getString(R.string.voice_notice_txt) + vrContent.getHints().get(0) + ", " + vrContent.getHints().get(1);
        }
    },
    HINT_CONTINUE { // from class: com.byd.vrassistant.bean.ContentState.3
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_55_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_55_dark);
        }

        @Override // com.byd.vrassistant.bean.VrText
        public String getText(Context context, VrContent vrContent) {
            return context.getString(R.string.voice_continue_notice_txt);
        }
    },
    ISSUING { // from class: com.byd.vrassistant.bean.ContentState.4
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_85_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_85_dark);
        }
    },
    VALID { // from class: com.byd.vrassistant.bean.ContentState.5
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            return ContextCompat.getColor(context, R.color.voice_bar_text_85);
        }
    },
    INVALID { // from class: com.byd.vrassistant.bean.ContentState.6
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_55_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_55_dark);
        }
    },
    RESPONSE { // from class: com.byd.vrassistant.bean.ContentState.7
        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            return ContextCompat.getColor(context, R.color.voice_bar_text);
        }
    },
    NONE { // from class: com.byd.vrassistant.bean.ContentState.8
        @Override // com.byd.vrassistant.bean.VrText
        public String getText(Context context, VrContent vrContent) {
            return "";
        }

        @Override // com.byd.vrassistant.bean.VrText
        public int getTextColor(Context context, boolean z) {
            if (z) {
                return ContextCompat.getColor(context, R.color.list_text_55_light);
            }
            return ContextCompat.getColor(context, R.color.list_text_55_dark);
        }
    };

    public static final Parcelable.Creator<ContentState> CREATOR = new Parcelable.Creator<ContentState>() { // from class: com.byd.vrassistant.bean.ContentState.9
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentState createFromParcel(Parcel parcel) {
            return ContentState.values()[parcel.readInt()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContentState[] newArray(int i) {
            return new ContentState[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
