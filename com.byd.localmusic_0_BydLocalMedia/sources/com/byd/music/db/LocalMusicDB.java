package com.byd.music.db;

import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.ArtistBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.LocalMusicBean_;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicDB {
    private static final String TAG = "LocalMusicDB";

    private static Box<LocalMusicBean> getBox() {
        return BoxManager.get().getBoxStore().boxFor(LocalMusicBean.class);
    }

    public static LocalMusicBean getLocalMusicById(int i) {
        return getBox().query().equal(LocalMusicBean_.musicId, i).build().findUnique();
    }

    public static List<LocalMusicBean> getCollectLocalBeanList(boolean z, int i) {
        return getBox().query().equal(LocalMusicBean_.isCollect, z).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getAlbumMusic(long j) {
        return getBox().query().equal(LocalMusicBean_.albumId, j).build().find();
    }

    public static List<LocalMusicBean> getAlbumMusicWithStorageType(long j, boolean z, boolean z2) {
        if (z && z2) {
            return getBox().query(LocalMusicBean_.albumId.equal(j)).build().find();
        }
        if (z) {
            return getBox().query(LocalMusicBean_.albumId.equal(j).and(LocalMusicBean_.storageType.equal(0).or(LocalMusicBean_.storageType.equal(2)))).build().find();
        }
        if (z2) {
            return getBox().query(LocalMusicBean_.albumId.equal(j).and(LocalMusicBean_.storageType.equal(0).or(LocalMusicBean_.storageType.equal(1)))).build().find();
        }
        return getBox().query(LocalMusicBean_.albumId.equal(j).and(LocalMusicBean_.storageType.equal(0))).build().find();
    }

    public static List<LocalMusicBean> getArtistMusic(long j) {
        return getBox().query().equal(LocalMusicBean_.artistId, j).build().find();
    }

    public static List<LocalMusicBean> getArtistMusicWithStorageType(long j, boolean z, boolean z2) {
        if (z && z2) {
            return getBox().query(LocalMusicBean_.artistId.equal(j)).build().find();
        }
        if (z) {
            return getBox().query(LocalMusicBean_.artistId.equal(j).and(LocalMusicBean_.storageType.equal(0).or(LocalMusicBean_.storageType.equal(2)))).build().find();
        }
        if (z2) {
            return getBox().query(LocalMusicBean_.artistId.equal(j).and(LocalMusicBean_.storageType.equal(0).or(LocalMusicBean_.storageType.equal(1)))).build().find();
        }
        return getBox().query(LocalMusicBean_.artistId.equal(j).and(LocalMusicBean_.storageType.equal(0))).build().find();
    }

    public static List<LocalMusicBean> getAlbumMusicWithIdAndStorageType(long j, int i) {
        return getBox().query().equal(LocalMusicBean_.albumId, j).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getMusicWithTitleAndStorageType(String str, int i) {
        return getBox().query(LocalMusicBean_.title.equal(str)).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getMusicWithTitleAndStorageTypeMatch(String str, int i) {
        return getBox().query(LocalMusicBean_.title.contains(str)).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getArtistMusicIdAndStorageType(long j, int i) {
        return getBox().query().equal(LocalMusicBean_.artistId, j).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getArtistNameAndStorageType(String str, int i) {
        return getBox().query(LocalMusicBean_.artist.equal(str)).and().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getSongIdListWithStorageType(boolean z, boolean z2) {
        if (z && z2) {
            return getSongIdList();
        }
        if (z) {
            return getLocalMusicByStorageTypeLocalIncluded(2);
        }
        if (z2) {
            return getLocalMusicByStorageTypeLocalIncluded(1);
        }
        return getLocalMusicByStorageType(0);
    }

    public static List<LocalMusicBean> getSongIdList() {
        return getBox().query().build().find();
    }

    public static List<LocalMusicBean> getLocalMusicByStorageType(int i) {
        return getBox().query().equal(LocalMusicBean_.storageType, i).build().find();
    }

    public static List<LocalMusicBean> getLocalMusicByStorageTypeLocalIncluded(int i) {
        return getBox().query().equal(LocalMusicBean_.storageType, i).or().equal(LocalMusicBean_.storageType, 0L).build().find();
    }

    public static List<LocalMusicBean> getLocalMusicFromUsbSd(int i, int i2) {
        return getBox().query().equal(LocalMusicBean_.storageType, i).or().equal(LocalMusicBean_.storageType, i2).build().find();
    }

    public static ObjectBoxLiveData<LocalMusicBean> getLocalMusicLiveData() {
        return new ObjectBoxLiveData<>(getBox().query().orderDesc(LocalMusicBean_.id).build());
    }

    public static List<LocalMusicBean> getSongIdListOrderDown1() {
        return getBox().query().orderDesc(LocalMusicBean_.id).build().find();
    }

    public static void update(LocalMusicBean localMusicBean) {
        LocalMusicBean localMusicBeanFindUnique;
        if (localMusicBean == null || (localMusicBeanFindUnique = getBox().query().equal(LocalMusicBean_.musicId, localMusicBean.getMusicId()).and().equal(LocalMusicBean_.storageType, localMusicBean.getStorageType()).build().findUnique()) == null) {
            return;
        }
        localMusicBeanFindUnique.setCollect(!localMusicBeanFindUnique.isCollect());
        getBox().put(localMusicBeanFindUnique);
    }

    public static void insert(LocalMusicBean localMusicBean) {
        getBox().put(localMusicBean);
    }

    public static void updateAll(List<LocalMusicBean> list, List<LocalMusicBean> list2) {
        if (list2.size() <= 0) {
            insertAll(list);
            return;
        }
        for (LocalMusicBean localMusicBean : list2) {
            if (!list.contains(localMusicBean) || !LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                deleteLocalMusicByIdAndStorageType(localMusicBean.getMusicId(), localMusicBean.getStorageType());
            }
        }
        for (LocalMusicBean localMusicBean2 : list) {
            if (!list2.contains(localMusicBean2)) {
                insertMusic(localMusicBean2);
            }
        }
    }

    public static void insertMusic(LocalMusicBean localMusicBean) {
        getBox().put(localMusicBean);
    }

    public static void insertAll(List<LocalMusicBean> list) {
        getBox().put(list);
    }

    public static void deleteLocalMusicById(int i) {
        getBox().remove(getBox().query().equal(LocalMusicBean_.musicId, i).build().find());
    }

    public static void deleteLocalMusicByIdAndStorageType(int i, int i2) {
        getBox().remove(getBox().query().equal(LocalMusicBean_.musicId, i).and().equal(LocalMusicBean_.storageType, i2).build().find());
    }

    public static void delete() {
        getBox().removeAll();
    }

    public static void deleteByStorageType(int i) {
        getBox().remove(getLocalMusicByStorageType(i));
    }

    public static List<ArtistBean> getArtistList() {
        List<LocalMusicBean> songIdList = getSongIdList();
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        for (LocalMusicBean localMusicBean : songIdList) {
            if (linkedList.size() == 0 || !linkedList.contains(Long.valueOf(localMusicBean.getArtistId()))) {
                linkedList.add(Long.valueOf(localMusicBean.getArtistId()));
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            List<LocalMusicBean> artistMusic = getArtistMusic(((Long) it.next()).longValue());
            ArtistBean artistBean = new ArtistBean(artistMusic.get(0).getArtistId(), 0, artistMusic.get(0).getArtist());
            artistBean.addAllMusic(artistMusic);
            artistBean.setMusicCount(artistMusic.size());
            arrayList.add(artistBean);
        }
        return arrayList;
    }

    public static List<ArtistBean> getArtistListWithStorageType(boolean z, boolean z2) {
        List<LocalMusicBean> songIdListWithStorageType = getSongIdListWithStorageType(z, z2);
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        for (LocalMusicBean localMusicBean : songIdListWithStorageType) {
            if (linkedList.size() == 0 || !linkedList.contains(Long.valueOf(localMusicBean.getArtistId()))) {
                linkedList.add(Long.valueOf(localMusicBean.getArtistId()));
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            List<LocalMusicBean> artistMusicWithStorageType = getArtistMusicWithStorageType(((Long) it.next()).longValue(), z, z2);
            ArtistBean artistBean = new ArtistBean(artistMusicWithStorageType.get(0).getArtistId(), 0, artistMusicWithStorageType.get(0).getArtist());
            artistBean.addAllMusic(artistMusicWithStorageType);
            artistBean.setMusicCount(artistMusicWithStorageType.size());
            arrayList.add(artistBean);
        }
        return arrayList;
    }

    public static List<AlbumBean> getAlbumList() {
        List<LocalMusicBean> songIdList = getSongIdList();
        LinkedList linkedList = new LinkedList();
        for (LocalMusicBean localMusicBean : songIdList) {
            if (linkedList.size() == 0 || !compareAlbumInfo(linkedList, localMusicBean)) {
                AlbumBean albumBeanFrom = AlbumBean.from(localMusicBean, localMusicBean.getStorageType());
                if (albumBeanFrom != null) {
                    List<LocalMusicBean> albumMusic = getAlbumMusic(albumBeanFrom.getId());
                    albumBeanFrom.addAllMusic(albumMusic);
                    albumBeanFrom.setMusicCount(albumMusic.size());
                    linkedList.add(albumBeanFrom);
                }
            }
        }
        return linkedList;
    }

    public static List<AlbumBean> getAlbumListByStorageType(boolean z, boolean z2) {
        List<LocalMusicBean> songIdListWithStorageType = getSongIdListWithStorageType(z, z2);
        LinkedList linkedList = new LinkedList();
        for (LocalMusicBean localMusicBean : songIdListWithStorageType) {
            if (linkedList.size() == 0 || !compareAlbumInfo(linkedList, localMusicBean)) {
                AlbumBean albumBeanFrom = AlbumBean.from(localMusicBean, localMusicBean.getStorageType());
                if (albumBeanFrom != null) {
                    List<LocalMusicBean> albumMusicWithStorageType = getAlbumMusicWithStorageType(albumBeanFrom.getId(), z, z2);
                    albumBeanFrom.addAllMusic(albumMusicWithStorageType);
                    albumBeanFrom.setMusicCount(albumMusicWithStorageType.size());
                    linkedList.add(albumBeanFrom);
                }
            }
        }
        return linkedList;
    }

    public static boolean compareAlbumInfo(List<AlbumBean> list, LocalMusicBean localMusicBean) {
        Iterator<AlbumBean> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == localMusicBean.getAlbumId()) {
                return true;
            }
        }
        return false;
    }
}
