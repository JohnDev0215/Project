package androidx.work.impl.model;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import com.amazon.music.destination.parser.ParserUtil;

/* JADX INFO: loaded from: classes2.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"work_spec_id"}, entity = WorkSpec.class, onDelete = 5, onUpdate = 5, parentColumns = {"id"})}, indices = {@Index({"work_spec_id"})}, primaryKeys = {ParserUtil.TAG_QUERY_PARAM_NAME, "work_spec_id"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkTag {

    @NonNull
    @ColumnInfo(name = ParserUtil.TAG_QUERY_PARAM_NAME)
    public final String tag;

    @NonNull
    @ColumnInfo(name = "work_spec_id")
    public final String workSpecId;

    public WorkTag(@NonNull String tag, @NonNull String workSpecId) {
        this.tag = tag;
        this.workSpecId = workSpecId;
    }
}
