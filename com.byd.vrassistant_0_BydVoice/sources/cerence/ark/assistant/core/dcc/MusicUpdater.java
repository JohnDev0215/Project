package cerence.ark.assistant.core.dcc;

import android.os.CancellationSignal;
import android.text.TextUtils;
import cerence.ark.assistant.api.dynamic.IDynamicContentProvider;
import cerence.ark.assistant.api.dynamic.Music;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MusicUpdater extends AsyncDCUpdater {
    public MusicUpdater(IDynamicContentProvider iDynamicContentProvider) {
        super(iDynamicContentProvider);
    }

    @Override // cerence.ark.assistant.core.dcc.AsyncDCUpdater
    protected List<String> getContent(String str, IDynamicContentProvider iDynamicContentProvider, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        List<Music> music = iDynamicContentProvider.getMusic(cancellationSignal);
        if (music != null && !music.isEmpty()) {
            for (Music music2 : music) {
                if (music2 != null && (!TextUtils.isEmpty(music2.getTitle()) || !TextUtils.isEmpty(music2.getArtist()) || !TextUtils.isEmpty(music2.getAlbum()))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(music2.getId()).append(DccConstant.SEPARATOR).append(music2.getTitle());
                    sb.append(DccConstant.SEPARATOR);
                    sb.append(music2.getArtistId()).append(DccConstant.SEPARATOR).append(music2.getArtist());
                    sb.append(DccConstant.SEPARATOR);
                    sb.append(music2.getAlbumId()).append(DccConstant.SEPARATOR).append(music2.getAlbum());
                    arrayList.add(sb.toString());
                }
            }
        }
        return arrayList;
    }
}
