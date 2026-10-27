package com.byd.music.beans;

import com.byd.music.fragment.bluetooth.BluetoothMusicBean;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.service.RadioServiceWrapper;
import com.vivid.sdk.spotify.Metadata;
import ts.car.service.androidauto.data.MediaMetadata;
import ts.car.service.carplay.data.NowPlayingInfo;

/* JADX INFO: loaded from: classes5.dex */
public class MultiMusicBean {
    public String albumTAG;
    public Object mEntity;

    public MultiMusicBean(Object obj) {
        this.mEntity = obj;
    }

    public String getAlbumTAG() {
        Object obj = this.mEntity;
        if (obj instanceof NowPlayingInfo) {
            return ((NowPlayingInfo) obj).getArtworkPath();
        }
        if (obj instanceof MediaMetadata) {
            return this.albumTAG;
        }
        if ((obj instanceof RadioDBBean) && RadioServiceWrapper.isDABBean((RadioDBBean) obj)) {
            long iconId = ((RadioDBBean) this.mEntity).getIconId() + ((RadioDBBean) this.mEntity).getArtId();
            return String.valueOf(iconId == 0 ? "" : Long.valueOf(iconId));
        }
        return this.albumTAG;
    }

    public void setAlbumTAG(String str) {
        Object obj = this.mEntity;
        if (obj instanceof NowPlayingInfo) {
            ((NowPlayingInfo) obj).setArtworkPath(str);
        }
        this.albumTAG = str;
    }

    public Object getAlbum() {
        Object obj = this.mEntity;
        if (obj instanceof Metadata) {
            return ((Metadata) obj).getCoverUrl();
        }
        if (obj instanceof NowPlayingInfo) {
            return ((NowPlayingInfo) obj).getArtworkData();
        }
        if (obj instanceof MediaMetadata) {
            return ((MediaMetadata) obj).getAlbumArt();
        }
        if (obj instanceof BluetoothMusicBean) {
            return ((BluetoothMusicBean) obj).getImgUrl();
        }
        if (obj instanceof LocalMusicBean) {
            if (LocalMusicUtils.checkMusicFileExist(((LocalMusicBean) obj).getUri())) {
                return LocalMusicUtils.getAlbumUri(((LocalMusicBean) this.mEntity).getAlbumId());
            }
            return null;
        }
        if (obj instanceof RadioDBBean) {
            if (RadioServiceWrapper.isDABBean((RadioDBBean) obj)) {
                return RadioServiceWrapper.getDabAlbumPreferArt((RadioDBBean) this.mEntity);
            }
        } else if (obj instanceof SampleMusicBean) {
            return ((SampleMusicBean) obj).getAlbum();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getMusicName() {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.beans.MultiMusicBean.getMusicName():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getArtist() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mEntity
            boolean r1 = r0 instanceof com.byd.music.beans.RadioDBBean
            java.lang.String r2 = "FM"
            if (r1 == 0) goto L5c
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            boolean r0 = com.byd.music.service.RadioServiceWrapper.isFmBean(r0)
            if (r0 == 0) goto L31
            java.lang.Object r0 = r3.mEntity
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            boolean r0 = com.byd.music.service.RadioServiceWrapper.isRDSBean(r0)
            if (r0 == 0) goto Lb5
            java.lang.Object r0 = r3.mEntity
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            java.lang.String r0 = r0.getRDS_PS()
            if (r0 == 0) goto Lb5
            java.lang.String r1 = " MHz"
            boolean r0 = r0.endsWith(r1)
            if (r0 != 0) goto Lb5
            java.lang.String r0 = "RDS"
            r2 = r0
            goto Lb5
        L31:
            java.lang.Object r0 = r3.mEntity
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            boolean r0 = com.byd.music.service.RadioServiceWrapper.isAmBean(r0)
            if (r0 == 0) goto L47
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            int r1 = com.byd.music.R.string.module_name_am
            java.lang.String r2 = r0.getString(r1)
            goto Lb5
        L47:
            java.lang.Object r0 = r3.mEntity
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            boolean r0 = com.byd.music.service.RadioServiceWrapper.isDABBean(r0)
            if (r0 == 0) goto Lb4
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            int r1 = com.byd.music.R.string.module_name_dab
            java.lang.String r2 = r0.getString(r1)
            goto Lb5
        L5c:
            boolean r1 = r0 instanceof com.byd.music.beans.LocalMusicBean
            if (r1 == 0) goto L7d
            com.byd.music.beans.LocalMusicBean r0 = (com.byd.music.beans.LocalMusicBean) r0
            java.lang.String r2 = r0.getArtist()
            java.lang.String r0 = "<unknown>"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto Lb5
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            android.content.Context r0 = r0.getApplicationContext()
            int r1 = com.byd.music.R.string.unknown_artist
            java.lang.String r2 = r0.getString(r1)
            goto Lb5
        L7d:
            boolean r1 = r0 instanceof com.byd.music.fragment.bluetooth.BluetoothMusicBean
            if (r1 == 0) goto L88
            com.byd.music.fragment.bluetooth.BluetoothMusicBean r0 = (com.byd.music.fragment.bluetooth.BluetoothMusicBean) r0
            java.lang.String r2 = r0.getArtist()
            goto Lb5
        L88:
            boolean r1 = r0 instanceof com.vivid.sdk.spotify.Metadata
            if (r1 == 0) goto L93
            com.vivid.sdk.spotify.Metadata r0 = (com.vivid.sdk.spotify.Metadata) r0
            java.lang.String r2 = r0.getSubTitle()
            goto Lb5
        L93:
            boolean r1 = r0 instanceof ts.car.service.androidauto.data.MediaMetadata
            if (r1 == 0) goto L9e
            ts.car.service.androidauto.data.MediaMetadata r0 = (ts.car.service.androidauto.data.MediaMetadata) r0
            java.lang.String r2 = r0.getArtist()
            goto Lb5
        L9e:
            boolean r1 = r0 instanceof ts.car.service.carplay.data.NowPlayingInfo
            if (r1 == 0) goto La9
            ts.car.service.carplay.data.NowPlayingInfo r0 = (ts.car.service.carplay.data.NowPlayingInfo) r0
            java.lang.String r2 = r0.getMediaItemArtist()
            goto Lb5
        La9:
            boolean r1 = r0 instanceof com.byd.music.beans.SampleMusicBean
            if (r1 == 0) goto Lb4
            com.byd.music.beans.SampleMusicBean r0 = (com.byd.music.beans.SampleMusicBean) r0
            java.lang.String r2 = r0.getArtist()
            goto Lb5
        Lb4:
            r2 = 0
        Lb5:
            if (r2 == 0) goto Lb8
            return r2
        Lb8:
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.byd.music.R.string.unknown_artist
            java.lang.String r0 = r0.getString(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.beans.MultiMusicBean.getArtist():java.lang.String");
    }

    public long getDuration() {
        Object obj = this.mEntity;
        if (obj instanceof LocalMusicBean) {
            return ((LocalMusicBean) obj).getDuration();
        }
        if (obj instanceof BluetoothMusicBean) {
            return ((BluetoothMusicBean) obj).getDuration();
        }
        if (obj instanceof Metadata) {
            return ((Metadata) obj).getDuration();
        }
        if (obj instanceof MediaMetadata) {
            return ((MediaMetadata) obj).getDurationSeconds() * 1000;
        }
        if (obj instanceof NowPlayingInfo) {
            return ((NowPlayingInfo) obj).getMediaItemPlaybackDurationMs();
        }
        if (obj instanceof SampleMusicBean) {
            return ((SampleMusicBean) obj).getDuration();
        }
        return 0L;
    }

    public Object getEntity() {
        return this.mEntity;
    }
}
