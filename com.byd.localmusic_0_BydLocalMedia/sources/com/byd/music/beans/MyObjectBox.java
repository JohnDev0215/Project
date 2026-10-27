package com.byd.music.beans;

import android.provider.Telephony;
import io.objectbox.BoxStoreBuilder;
import io.objectbox.ModelBuilder;

/* JADX INFO: loaded from: classes5.dex */
public class MyObjectBox {
    public static BoxStoreBuilder builder() {
        BoxStoreBuilder boxStoreBuilder = new BoxStoreBuilder(getModel());
        boxStoreBuilder.entity(LocalMusicBean_.__INSTANCE);
        boxStoreBuilder.entity(PlayStateDBBean_.__INSTANCE);
        boxStoreBuilder.entity(RadioDBBean_.__INSTANCE);
        boxStoreBuilder.entity(RadioEpgDBBean_.__INSTANCE);
        boxStoreBuilder.entity(SearchHistoryBean_.__INSTANCE);
        boxStoreBuilder.entity(TrackRecordBean_.__INSTANCE);
        return boxStoreBuilder;
    }

    private static byte[] getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.lastEntityId(10, 1951021858594199158L);
        modelBuilder.lastIndexId(3, 4435225145587278975L);
        modelBuilder.lastRelationId(0, 0L);
        buildEntityLocalMusicBean(modelBuilder);
        buildEntityPlayStateDBBean(modelBuilder);
        buildEntityRadioDBBean(modelBuilder);
        buildEntityRadioEpgDBBean(modelBuilder);
        buildEntitySearchHistoryBean(modelBuilder);
        buildEntityTrackRecordBean(modelBuilder);
        return modelBuilder.build();
    }

    private static void buildEntityLocalMusicBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("LocalMusicBean");
        entityBuilderEntity.id(3, 4341206689835964433L).lastPropertyId(18, 3161690330378474303L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 2333263351486333591L).flags(1);
        entityBuilderEntity.property("musicId", 5).id(2, 7764306901076150716L).flags(12).indexId(1, 8991698620988431041L);
        entityBuilderEntity.property("title", 9).id(3, 9005961477157092878L);
        entityBuilderEntity.property("uri", 9).id(4, 8133149865124801267L);
        entityBuilderEntity.property("duration", 5).id(5, 4522009747842358185L).flags(4);
        entityBuilderEntity.property("createTime", 6).id(6, 2235390852571432085L).flags(4);
        entityBuilderEntity.property("playTime", 6).id(7, 6371538147962779695L).flags(4);
        entityBuilderEntity.property("artist", 9).id(8, 1304871604855894749L);
        entityBuilderEntity.property("artistId", 6).id(9, 3678436205941671211L).flags(4);
        entityBuilderEntity.property("album", 9).id(10, 5597609742635915442L);
        entityBuilderEntity.property("albumId", 6).id(11, 7366674584998191775L).flags(4);
        entityBuilderEntity.property("playing", 1).id(12, 3785455072580987990L).flags(4);
        entityBuilderEntity.property("isCollect", 1).id(13, 2178411021912375052L).flags(4);
        entityBuilderEntity.property("isLocal", 1).id(14, 1944581528463858485L).flags(4);
        entityBuilderEntity.property("storageType", 5).id(15, 7659426213609128693L).flags(4);
        entityBuilderEntity.property("state", 5).id(16, 1646446923222795429L).flags(4);
        entityBuilderEntity.property("progress", 5).id(17, 3531438428452006592L).flags(4);
        entityBuilderEntity.property("pinyin", 9).id(18, 3161690330378474303L);
        entityBuilderEntity.entityDone();
    }

    private static void buildEntityPlayStateDBBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("PlayStateDBBean");
        entityBuilderEntity.id(7, 7648448621006876453L).lastPropertyId(3, 7617654605427722056L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 6721678607652708807L).flags(1);
        entityBuilderEntity.property("playState", 1).id(2, 4660702121541471857L).flags(4);
        entityBuilderEntity.property("timeStamp", 6).id(3, 7617654605427722056L).flags(4);
        entityBuilderEntity.entityDone();
    }

    private static void buildEntityRadioDBBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("RadioDBBean");
        entityBuilderEntity.id(10, 1951021858594199158L).lastPropertyId(16, 2014811986957881293L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 1877730785379086255L).flags(1);
        entityBuilderEntity.property("type", 5).id(2, 3349820099246348570L).flags(4);
        entityBuilderEntity.property("radioFrequency", 5).id(3, 4309419154173007979L).flags(4);
        entityBuilderEntity.property("radioName", 9).id(10, 2755973658368997151L);
        entityBuilderEntity.property("DAB_SID", 6).id(8, 2407299465232233674L).flags(4);
        entityBuilderEntity.property("RDS_RT", 9).id(9, 2736183686817568953L);
        entityBuilderEntity.entityDone();
    }

    private static void buildEntityRadioEpgDBBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("RadioEpgDBBean");
        entityBuilderEntity.id(9, 2185292789539560927L).lastPropertyId(5, 6085904354243937315L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 4983635899367155677L).flags(1);
        entityBuilderEntity.property("dabSid", 6).id(2, 8840378614927058760L).flags(4);
        entityBuilderEntity.property("epgStartTime", 6).id(3, 6019399284118093488L).flags(4);
        entityBuilderEntity.property("epgEndTime", 6).id(4, 3319644608025905812L).flags(4);
        entityBuilderEntity.property("desc", 9).id(5, 6085904354243937315L);
        entityBuilderEntity.entityDone();
    }

    private static void buildEntitySearchHistoryBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("SearchHistoryBean");
        entityBuilderEntity.id(4, 3847178152985946125L).lastPropertyId(4, 4573859769145492889L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 9206046289204852768L).flags(1);
        entityBuilderEntity.property("title", 9).id(4, 4573859769145492889L);
        entityBuilderEntity.property("type", 5).id(3, 2489409643336861991L).flags(4);
        entityBuilderEntity.entityDone();
    }

    private static void buildEntityTrackRecordBean(ModelBuilder modelBuilder) {
        ModelBuilder.EntityBuilder entityBuilderEntity = modelBuilder.entity("TrackRecordBean");
        entityBuilderEntity.id(5, 7128123494232968301L).lastPropertyId(16, 8507961256102269405L);
        entityBuilderEntity.flags(1);
        entityBuilderEntity.property("id", 6).id(1, 1529725373839141298L).flags(1);
        entityBuilderEntity.property("track_id", 6).id(2, 2301411379994007516L).flags(44).indexId(2, 3625897246859707435L);
        entityBuilderEntity.property("duration", 8).id(3, 6788665846202524750L).flags(4);
        entityBuilderEntity.property("played_secs", 8).id(4, 8888088286045038747L).flags(4);
        entityBuilderEntity.property("started_at", 6).id(5, 5685697314780343806L).flags(4);
        entityBuilderEntity.property("ended_at", 6).id(6, 2632363819120278808L).flags(4);
        entityBuilderEntity.property("play_type", 5).id(7, 8814298782565599872L).flags(4);
        entityBuilderEntity.property("rec_src", 9).id(8, 393958568898139348L);
        entityBuilderEntity.property("rec_track", 9).id(9, 4496563955484185884L);
        entityBuilderEntity.property("play_source", 9).id(10, 4847111622340074732L);
        entityBuilderEntity.property("album_title", 9).id(11, 7019084554078892284L);
        entityBuilderEntity.property("track_title", 9).id(12, 1621898980227427114L);
        entityBuilderEntity.property("imgUrl", 9).id(13, 5359725594157173926L);
        entityBuilderEntity.property("album_id", 6).id(14, 8020698995963321049L).flags(44).indexId(3, 4435225145587278975L);
        entityBuilderEntity.property(Telephony.RcsColumns.RcsFileTransferColumns.CONTENT_TYPE_COLUMN, 5).id(15, 3346850404457355693L).flags(4);
        entityBuilderEntity.property("totalDuration", 5).id(16, 8507961256102269405L).flags(4);
        entityBuilderEntity.entityDone();
    }
}
