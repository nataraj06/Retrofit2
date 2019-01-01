package android.com.retrofit2.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import okhttp3.ResponseBody;

public class DataError {

    private boolean isError = false;
    private String url;
    private String message;
    private String statusCode;
    private boolean isNetworkError;
    private ResponseBody retrofitError;

    public DataError(String url, String code, String errorMsg) {
        this(url, code, errorMsg, false);
    }

    public DataError(String url, String code, String errorMsg, boolean isNetworkError) {
        isError = true;
        this.url = url;
        this.statusCode = code;
        this.message = errorMsg;
        this.isNetworkError = isNetworkError;
    }

    public DataError(String url, String code, String errorMsg, boolean isNetworkError, ResponseBody retrofitError) {
        isError = true;
        this.url = url;
        this.statusCode = code;
        this.message = errorMsg;
        this.isNetworkError = isNetworkError;
        this.retrofitError = retrofitError;
    }

    public String getUrl() {
        return url;
    }

    public String getErrorMsg() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public ResponseBody getRetrofitError() {
        return this.retrofitError;
    }

    public boolean isNetworkError() {
        return isNetworkError;
    }

    public JsonElement getJsonElementOfRetrofitError() {
        return getJsonElementOfRetrofitError(this.retrofitError);
    }

    public static JsonElement getJsonElementOfRetrofitError(ResponseBody responseBody) {
        JsonElement jsonElement = null;
        try {
            if (responseBody != null) {
                jsonElement = new Gson().fromJson(responseBody.string(), JsonElement.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonElement;
    }
}
