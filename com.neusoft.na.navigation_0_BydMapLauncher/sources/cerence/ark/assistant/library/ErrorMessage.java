package cerence.ark.assistant.library;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorMessage {
    public static final String APPEVENT_JSON_JSON_NULL = "app event jsonData is null";
    public static final String ASSISTANT_ALREADY_INITIALIZED = "voice assistant already initialized";
    public static final String ASSISTANT_ALREADY_STARTED = "voice assistant already started";
    public static final String ASSISTANT_CONFIGURATION_NULL = "AssistantConfiguration is null";
    public static final String ASSISTANT_NOT_INITIALIZED = "voice assistant not initialized";
    public static final String ASSISTANT_NOT_STARTED = "voice assistant not started";
    public static final String AUDIO_PLAYER_INIT_FAIL = "audio player initialize fail";
    public static final String AUDIO_RECORD_INIT_FAIL = "Initialize audio recorder error.";
    public static final String AUDIO_RECORD_PERMISSION_DENIED = "the android.permission.RECORD_AUDIO was denied.";
    public static final String BIND_SPEECH_SERVICE_ERROR = "The system couldn't find the service or your client doesn't have permission to bind to it.";
    public static final String CLIENT_HANDLER_NULL = "client handler is null";
    public static final String CONFIG_NULL = "config is null";
    public static final String DATAPACK_INVALID = "Datapack contains errors (missing modules, json config errors etc.), please check with log for details.";
    public static final String DATAPACK_PATH_INVALID = "Data path is not exists or not accessible.";
    public static final String DATAPACK_VERSION_INCOMPATIBLE = "datapack is not compatible with the sdk version";
    public static final String DIALOG_PROMPTER_NAME_NULL = "dialog prompter name is empty";
    public static final String EXCEPTION = "exception";
    public static final String FRAMEWORK_NULL = "framework is null";
    public static final String INVALID_OPERATION = "Invalid operation";
    public static final String LANGUAGE_NULL = "Language is empty";
    public static final String OOM = "Unable to allocate memory";
    public static final String PARAMETER_INVALID = "Parameter(s) is invalid or not matches.";
    public static final String PROMPTER_VOICES_EMPTY = "get available prompter voices is empty";
    public static final String RESPONSE_JSON_NULL = "response jsonData is null";
    public static final String UPDATE_STORAGE_PATH_INVALID = "The update path is invalid.";
    public static final String VIS_NOT_READY = "VIS is not ready";
    public static final String VOICE_CONFIG_INVALID = "tts voice config is invalid";
    public static final String VOICE_NOT_FOUND = "tts voice id not found according to asr language.";
    public static final String WRITABLE_PATH_INVALID = "Writable path is not exists or not accessible or not absolute path.";

    private ErrorMessage() {
    }
}
