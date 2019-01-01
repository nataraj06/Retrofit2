package android.com.retrofit2.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    protected static Retrofit getApiClient() {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(getHttpLoggingInterceptor()).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                                @Override
                                public boolean shouldSkipField(FieldAttributes field) {
                                    return field.getAnnotation(JsonIgnore.class) != null;
                                }

                                @Override
                                public boolean shouldSkipClass(Class<?> clazz) {
                                    return false;
                                }
                            })
                            .setPrettyPrinting()
                            .setLenient()
                            .create()))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}

