package com.byd.filemanager.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.filemanager.R;

/* JADX INFO: loaded from: classes.dex */
public class EmptyFragment extends BaseFragment {
    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        TextView textView = (TextView) view.findViewById(R.id.tv_desc);
        int iK = com.byd.filemanager.f.h().k();
        if (iK == 0) {
            imageView.setImageResource(R.drawable.img_media_music_playlist_empty);
            textView.setText(Q(R.string.no_content));
        } else if (iK == 1) {
            imageView.setImageResource(R.drawable.img_media_music_no_sd_card);
            textView.setText(Q(R.string.no_content));
        } else {
            if (iK != 2) {
                return;
            }
            imageView.setImageResource(R.drawable.img_media_music_no_usb);
            textView.setText(Q(R.string.no_content));
        }
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return R.layout.fragment_empty;
    }
}
