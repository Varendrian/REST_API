package api;

public class ApiConfig{
    public static ApiService getApiService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("https://restapi.telenurse.web.id/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit.create(ApiService.class);
    }
}