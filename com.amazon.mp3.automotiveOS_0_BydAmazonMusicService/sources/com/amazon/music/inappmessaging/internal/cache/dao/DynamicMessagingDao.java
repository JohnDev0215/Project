package com.amazon.music.inappmessaging.internal.cache.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.cache.entity.DynamicMessagesPerTrigger;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
@Dao
public interface DynamicMessagingDao {
    @Insert(onConflict = 1)
    void addMessages(List<DynamicMessagesPerTrigger> dynamicMessagesPerTriggers);

    @Query("DELETE FROM dynamicMessages")
    void deleteAllEntries();

    @Query("SELECT * FROM dynamicMessages WHERE `trigger` = :trigger")
    DynamicMessagesPerTrigger getDynamicMessages(Trigger trigger);
}
