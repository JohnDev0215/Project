package CoreInterface.v1_0;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonDeserialize(as = ImmutableResponse.class)
@JsonSerialize(as = ImmutableResponse.class)
@Value.Immutable
public abstract class Response {
    public abstract List<Method> methods();
}
