package com.byd.music.view.homeMusic.lrc;

import android.media.MediaMetrics;
import android.provider.SettingsStringUtil;
import com.bumptech.glide.load.Key;
import com.byd.music.beans.LrcContent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LrcProcess {
    private static final String TAG = "LrcProcess";
    private LrcContent mLrcContent = new LrcContent();
    private List<LrcContent> lrcList = new ArrayList();

    public String readLRC(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str.replace(".mp3", ".lrc"))), Key.STRING_CHARSET_NAME));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String[] strArrSplit = line.replace("[", "").replace("]", "@").split("@");
                if (strArrSplit.length > 1) {
                    this.mLrcContent.setLrcStr(strArrSplit[1]);
                    this.mLrcContent.setLrcTime(time2Str(strArrSplit[0]));
                    this.lrcList.add(this.mLrcContent);
                    this.mLrcContent = new LrcContent();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            sb.append("木有歌词文件，赶紧去下载！...");
        } catch (IOException e2) {
            e2.printStackTrace();
            sb.append("木有读取到歌词哦！");
        }
        return sb.toString();
    }

    public int time2Str(String str) {
        String[] strArrSplit = str.replace(SettingsStringUtil.DELIMITER, MediaMetrics.SEPARATOR).replace(MediaMetrics.SEPARATOR, "@").split("@");
        try {
            int i = strArrSplit.length >= 1 ? Integer.parseInt(strArrSplit[0]) : 0;
            return ((strArrSplit.length >= 3 ? Integer.parseInt(strArrSplit[2]) : 0) * 10) + (((i * 60) + (strArrSplit.length >= 2 ? Integer.parseInt(strArrSplit[1]) : 0)) * 1000);
        } catch (Exception unused) {
            return 0;
        }
    }

    public List<LrcContent> getLrcList() {
        return this.lrcList;
    }
}
