package com.byd.digitalkey.adapter;

import android.content.Context;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import b.b.a.i.a;
import com.byd.base.view.recyclerview.BaseListRecyclerAdapter;
import com.byd.base.view.recyclerview.BaseViewHolder;
import com.byd.digitalkey.R$drawable;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyItemHolder extends BaseViewHolder {
    public DigitalKeyItemHolder(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, BaseListRecyclerAdapter baseListRecyclerAdapter) {
        super(layoutInflater.inflate(R$layout.item_digital_key, viewGroup, false), baseListRecyclerAdapter);
        a(R$id.iv_key_trash, R$id.key_change_tv);
        viewGroup.getContext();
    }

    @Override // com.byd.base.view.recyclerview.BaseViewHolder
    public void c(Object obj) {
        if (obj instanceof DigitalKeyBean) {
            DigitalKeyBean digitalKeyBean = (DigitalKeyBean) obj;
            Context context = this.itemView.getContext();
            ImageView imageView = (ImageView) d(R$id.key_icon);
            ((TextView) d(R$id.key_type_model_tv)).setText(digitalKeyBean.getDeviceModelStr());
            byte keyType = digitalKeyBean.getKeyType();
            if (keyType == 9) {
                imageView.setImageResource(R$drawable.img_dark_carsetting_digital_key_iphone_1);
            } else if (keyType == 10) {
                imageView.setImageResource(R$drawable.img_dark_carsetting_digital_key_apple_watch);
            }
            ImageView imageView2 = (ImageView) d(R$id.iv_bt);
            ImageView imageView3 = (ImageView) d(R$id.iv_uwb);
            byte uwbTag = digitalKeyBean.getUwbTag();
            if (uwbTag == 0) {
                imageView2.setVisibility(8);
                imageView3.setVisibility(8);
            } else if (uwbTag == 1) {
                imageView2.setVisibility(0);
                imageView3.setVisibility(0);
            } else if (uwbTag == 2) {
                imageView2.setVisibility(0);
                imageView3.setVisibility(8);
            }
            TextView textView = (TextView) d(R$id.key_type_name_tv);
            textView.setText(digitalKeyBean.getKeyNameStr());
            if (a.d()) {
                textView.setGravity(GravityCompat.END);
            } else {
                textView.setGravity(GravityCompat.START);
            }
            TextView textView2 = (TextView) d(R$id.key_change_tv);
            if (a.f533c == 1) {
                textView2.setBackgroundResource(R$drawable.digital_key_change_btn_bg_denz);
            } else {
                textView2.setBackgroundResource(R$drawable.digital_key_change_btn_bg);
            }
            TextView textView3 = (TextView) d(R$id.key_user_name_tv);
            byte userPro = digitalKeyBean.getUserPro();
            if (userPro == 0 || userPro == 2) {
                textView3.setText(R$string.nfc_acc_attr_owner);
                textView2.setVisibility(0);
            } else if (userPro == 3) {
                textView3.setText(R$string.nfc_acc_attr_other);
                textView2.setVisibility(8);
            }
            TextView textView4 = (TextView) d(R$id.tv_youxiao_time);
            byte keyState = digitalKeyBean.getKeyState();
            switch (keyState) {
                case 0:
                    textView4.setText(R$string.nfc_key_type_state_untreated);
                    break;
                case 1:
                    textView4.setText(R$string.nfc_key_type_state_to_be_activated);
                    break;
                case 2:
                    if ("991231".equals(digitalKeyBean.getExpireTime())) {
                        textView4.setText(R$string.nfc_long_term_validity);
                    } else if (digitalKeyBean.getExpireTime().length() >= 6) {
                        String strSubstring = digitalKeyBean.getExpireTime().substring(0, 2);
                        String strSubstring2 = digitalKeyBean.getExpireTime().substring(2, 4);
                        String strSubstring3 = digitalKeyBean.getExpireTime().substring(4, 6);
                        textView4.setText(context.getString(R$string.nfc_valid_until, "20" + strSubstring + strSubstring2 + strSubstring3));
                    }
                    break;
                case 3:
                    textView4.setText(R$string.nfc_key_type_state_removing);
                    break;
                case 4:
                    textView4.setText(R$string.nfc_key_type_state_deleted);
                    break;
                case 5:
                    textView4.setText(R$string.nfc_key_type_state_replacement_failed);
                    break;
                case 6:
                    textView4.setText(R$string.nfc_key_type_state_replacement_success);
                    break;
                case 7:
                    textView4.setText(R$string.nfc_key_type_state_Authorization_change_deletion);
                    break;
                case 8:
                    textView4.setText(R$string.nfc_key_type_state_Authorization_change_removal);
                    break;
                case 9:
                    textView4.setText(R$string.digital_key_no_confirm);
                    break;
                default:
                    switch (keyState) {
                        case 16:
                            textView4.setText(R$string.nfc_key_type_state_disabled);
                            break;
                        case 17:
                            textView4.setText(R$string.nfc_key_type_state_disable);
                            break;
                        case 18:
                            textView4.setText(R$string.nfc_key_type_state_in_recovery);
                            break;
                    }
                    break;
            }
            ImageView imageView4 = (ImageView) d(R$id.iv_key_trash);
            if (digitalKeyBean.getKeyState() != 19) {
                imageView4.setVisibility(0);
            } else {
                imageView4.setVisibility(8);
                textView4.setText(R$string.nfc_key_type_state_offline_removed);
            }
        }
    }
}
