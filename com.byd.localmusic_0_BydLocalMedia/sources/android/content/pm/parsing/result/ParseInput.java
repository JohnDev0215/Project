package android.content.pm.parsing.result;

/* JADX INFO: loaded from: classes.dex */
public interface ParseInput {

    public interface Callback {
        boolean isChangeEnabled(long j, String str, int i);
    }

    public static final class DeferredError {
        public static final long EMPTY_INTENT_ACTION_CATEGORY = 151163173;
        public static final long MISSING_APP_TAG = 150776642;
        public static final long RESOURCES_ARSC_COMPRESSED = 132742131;

        public static int getTargetSdkForChange(long j) {
            return (j == MISSING_APP_TAG || j == EMPTY_INTENT_ACTION_CATEGORY || j == RESOURCES_ARSC_COMPRESSED) ? 29 : -1;
        }
    }

    ParseResult<?> deferError(String str, long j);

    ParseResult<?> enableDeferredError(String str, int i);

    <ResultType> ParseResult<ResultType> error(int i);

    <ResultType> ParseResult<ResultType> error(int i, String str);

    <ResultType> ParseResult<ResultType> error(int i, String str, Exception exc);

    <ResultType> ParseResult<ResultType> error(ParseResult<?> parseResult);

    <ResultType> ParseResult<ResultType> error(String str);

    <ResultType> ParseResult<ResultType> skip(String str);

    <ResultType> ParseResult<ResultType> success(ResultType resulttype);
}
