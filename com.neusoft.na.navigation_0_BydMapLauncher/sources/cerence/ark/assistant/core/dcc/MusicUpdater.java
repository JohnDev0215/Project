package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.Music;
import cerence.ark.assistant.common.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class MusicUpdater extends AsyncDCUpdater {
    private static final String TAG = "MusicUpdater";

    public MusicUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<Music> music = iDynamicContentProvider.getMusic(cancellationSignal);
        if (music != null && !music.isEmpty()) {
            Logger.info(TAG, "Music count: " + music.size());
            for (int i = 0; i < music.size(); i++) {
                Music music2 = music.get(i);
                if (music2 != null) {
                    if (TextUtils.isEmpty(music2.getTitle()) && TextUtils.isEmpty(music2.getArtist()) && TextUtils.isEmpty(music2.getAlbum())) {
                        Logger.warn(TAG, "The attributes of Music are empty, index = " + i);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(music2.getId()).append(DccConstant.DCC_SEPARATOR).append(music2.getTitle());
                        sb.append(DccConstant.DCC_SEPARATOR);
                        sb.append(music2.getArtistId()).append(DccConstant.DCC_SEPARATOR).append(music2.getArtist());
                        sb.append(DccConstant.DCC_SEPARATOR);
                        sb.append(music2.getAlbumId()).append(DccConstant.DCC_SEPARATOR).append(music2.getAlbum());
                        arrayList.add(sb.toString());
                    }
                } else {
                    Logger.warn(TAG, "music is null, index = " + i);
                }
            }
        }
        return arrayList;
    }
}
