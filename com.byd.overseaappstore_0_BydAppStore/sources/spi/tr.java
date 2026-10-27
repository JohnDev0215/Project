package spi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface tr {
    @Query("SELECT * FROM SoftwareLocal WHERE state >=6 and state<=12")
    @Transaction
    @Nullable
    Object a(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =3 or state =4 or state =10 or state =11 or state =2 or state =9")
    @Transaction
    @Nullable
    Object b(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =3 or state =4 or state =2 ")
    @Transaction
    @Nullable
    Object c(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =:state ")
    @Transaction
    @Nullable
    Object d(int i, @NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state >=1 and state<=5")
    @Transaction
    @Nullable
    Object e(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal")
    @Transaction
    @Nullable
    Object f(@NotNull Continuation<? super List<qq>> continuation);

    @Insert(onConflict = 1)
    @Nullable
    Object g(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =3 or state =10")
    @Transaction
    @Nullable
    Object h(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =5 or state =12")
    @Transaction
    @Nullable
    Object i(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE packageName = :packageName")
    @Transaction
    @Nullable
    Object j(@NotNull String str, @NotNull Continuation<? super qq> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =7 or state =11")
    @Transaction
    @Nullable
    Object k(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =1 or state =8")
    @Transaction
    @Nullable
    Object l(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state =10 or state =11 or state =9")
    @Transaction
    @Nullable
    Object m(@NotNull Continuation<? super List<qq>> continuation);

    @Query("SELECT * FROM SoftwareLocal WHERE state >=7 and state<=12")
    @Transaction
    @Nullable
    Object n(@NotNull Continuation<? super List<qq>> continuation);

    @Insert(onConflict = 1)
    @Nullable
    Object o(@NotNull List<oq> list, @NotNull Continuation<? super Unit> continuation);

    @Update(onConflict = 1)
    @Nullable
    Object p(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation);

    @Query("UPDATE SoftwareLocal SET state=:state ,versionCode=:versionCode WHERE packageName = :packageName")
    @Nullable
    Object q(@NotNull String str, int i, int i2, @NotNull Continuation<? super Unit> continuation);

    @Insert(onConflict = 1)
    @Nullable
    Object r(@NotNull oq oqVar, @NotNull Continuation<? super Unit> continuation);

    @Query("UPDATE SoftwareLocal SET state=:state WHERE packageName = :packageName")
    @Nullable
    Object s(@NotNull String str, int i, @NotNull Continuation<? super Unit> continuation);

    @Query("UPDATE SoftwareLocal SET state=:state ,downloadUrl=:downloadUrl WHERE packageName = :packageName")
    @Nullable
    Object t(@NotNull String str, int i, @NotNull String str2, @NotNull Continuation<? super Unit> continuation);
}
